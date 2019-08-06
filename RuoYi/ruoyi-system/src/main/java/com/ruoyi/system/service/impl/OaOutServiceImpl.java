package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.mapper.OaOutApprovalMapper;
import com.ruoyi.system.mapper.OaOutMapper;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.domain.OaOut;
import com.ruoyi.system.domain.OaOutApproval;
import com.ruoyi.system.domain.OutApproval;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.IOaOutService;
import com.ruoyi.common.core.text.Convert;

/**
 * 外出报备 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-01
 */
@Service
public class OaOutServiceImpl implements IOaOutService 
{
	private static final Set<SysDept> dSet = new TreeSet<SysDept>();  //部门集合                                                                   
	
	@Autowired
	private OaOutMapper oaOutMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private OaOutApprovalMapper oaOutApprovalMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysDeptMapper deptMapper;
    
    
	/**
     * 查询外出报备信息
     * 
     * @param outId 外出报备ID
     * @return 外出报备信息
     */
    @Override
	public OaOut selectOaOutById(Long outId)
	{
	    return oaOutMapper.selectOaOutById(outId);
	}
	
    /**
     * 新增外出报备
     * 
     * @param oaOut 外出报备信息
     * @return 结果
     */
	@Override
	public int insertOaOut(OaOut oaOut)
	{
		oaOutMapper.insertOaOut(oaOut);
		SysUser user = userMapper.selectUserById(oaOut.getUserId());//查出当前用户的area值

		//生成审批记录
		Long approvalId = 0L;//审批人id
		
		//直属上级审批
		Long leaderId = userMapper.selectApproverIdByApplyerId(user.getUserId());//所在部门负责人id(上级)
		Long upLeaderId = userMapper.selectUpApproverIdByApplyerId(user.getUserId());//所在部门负责人的上级（上上级）
		if(leaderId.equals(user.getUserId())){	//判断用户是否部门负责人  
			approvalId = upLeaderId; //上上级作为一级审批人
		}
		else{
			approvalId = leaderId;//上级作为一级审批人
		}
		
		//生成一级审批记录
		OaOutApproval oaOutApproval =  new OaOutApproval();
		oaOutApproval.setOutId(oaOut.getOutId());//外出报备id
		oaOutApproval.setApprovalId(approvalId);//审批人user_id
		oaOutApproval.setApprovalState("3");//审批状态（1同意，2驳回 ，3未操作）
		oaOutApproval.setApprovalSight("1");//1可见  0不可见
		oaOutApproval.setApprovalLevel(1);
		oaOutApprovalMapper.insertOaOutApproval(oaOutApproval);
		
		//如果是审批人是 coo 直接结束
		if(approvalId != null && approvalId.longValue()==103){ 
		    return 1;
		}
		
		//人事审批
					
//		user.setRoleId(3L);//人事专员（分区域分配人事审批人	）
		user.setRoleId(6L);//人事总监
		Long hrId = userRoleMapper.selectUserIdByRoleId(user);//人事总监id
		
		//当前用户的leader是人事总监时，只需要leader审批
		if(approvalId.longValue() == hrId.longValue()){
			return 1;
		}
		
		//当前用户是hr时，只需要人事总监审批
		if(upLeaderId.longValue() == hrId.longValue()){
		    return 1;
		}
		
		//当前用户是人事总监时，只需要leader审批
		if(user.getUserId().longValue() == hrId.longValue()){
		    return 1;
		}
		
		//生成二级审批记录
		OaOutApproval oaOutApproval2 =  new OaOutApproval();
		oaOutApproval2.setOutId(oaOut.getOutId());//外出报备id
		oaOutApproval2.setApprovalId(hrId);//审批人user_id（人事总监）
		oaOutApproval2.setApprovalState("3");//审批状态（1同意，2驳回 ，3未操作）
		oaOutApproval2.setApprovalSight("0");//1可见  0不可见
		oaOutApproval2.setApprovalLevel(2);
		oaOutApprovalMapper.insertOaOutApproval(oaOutApproval2);
		
	    return 1;
	}
	
	/**
     * 审批外出报备
     * 
     * @param oaOut 外出报备信息
     * @return 结果
     */
	@Override
	public int updateOaOut(OaOut oaOut, String remark)
	{
		OaOutApproval approval1 = new OaOutApproval();
		approval1.setApprovalId(oaOut.getApprovalId());
		approval1.setOutId(oaOut.getOutId());
		List<OaOutApproval> approvalList1 = oaOutApprovalMapper.selectOaOutApprovalList(approval1);//查出对应的审批记录(结果是一条)
		approval1.setApprovalSight("1");
		List<OaOutApproval> approvalList = oaOutApprovalMapper.selectOaOutApprovalList(approval1);//查出对应的审批记录(结果是一条)
		OaOutApproval approval = approvalList.get(0);
		
		//1同意 2驳回 3未操作
		if("1".equals(oaOut.getApprovalState())){
						
			if(approvalList1.size()==1 || approval.getApprovalLevel() == 2){//人事审批
				approval.setApprovalState("1");
				oaOutApprovalMapper.updateOaOutApprovalByApprovalId(approval);
				oaOut.setState("3");
				return oaOutMapper.updateOaOut(oaOut);
				
			}else{//leader审批
				approval.setApprovalSight("0");
				approval.setApprovalState("1");
				oaOutApprovalMapper.updateOaOutApprovalByApprovalId(approval);
				
				//同时修改人事审批的状态为1
				approval.setId(null);
				approval.setApprovalId(null);
				approval.setApprovalState(null);
				approval.setApprovalSight("1");
				approval.setApprovalLevel(2);
				oaOutApprovalMapper.updateOaOutApprovalByApprovalId(approval);
			}
			
		}else{//驳回
			approval.setApprovalId(oaOut.getApprovalId());
			approval.setApprovalState("2");
			approval.setRemark(remark);
			oaOutApprovalMapper.updateOaOutApprovalByApprovalId(approval);
			oaOut.setState("4");
			return oaOutMapper.updateOaOut(oaOut);
		}
		return 1;
	}

	/**
     * 删除外出报备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOaOutByIds(String ids)
	{
		return oaOutMapper.deleteOaOutByIds(Convert.toStrArray(ids));
	}
	
	/**
     * 查询外出报备列表
     * 
     * @param oaOut 外出报备信息
     * @return 外出报备集合
     */
	@Override
	public List<OaOut> selectOaOutList(OaOut oaOut)
	{
	    return oaOutMapper.selectOaOutList(oaOut);
	}
	
	/**
	 *  查询外出报备审批结果
	 * @param oaOut
	 * @return 外出报备审批结果
	 */
	@Override
	public List<OutApproval> selectOutApprovalList(OaOut oaOut) {
		//admin查看全部
		if(oaOut.getUserId() != null && oaOut.getUserId().longValue() == 1L){
			oaOut.setUserId(null);
			return oaOutMapper.selectOutApprovalList(oaOut);

		}
		
		SysUser user = userMapper.selectUserById(oaOut.getUserId());//查出当前用户

		//人事总监
		user.setRoleId(6L);//人事总监
		Long chiefId = userRoleMapper.selectUserIdByRoleId(user);//人事总监id
		if(chiefId.longValue() == user.getUserId().longValue()){
			oaOut.setUserId(1L);
			return oaOutMapper.selectOutApprovalList(oaOut);
		}
		
		Long upLeaderId =userMapper.selectUpApproverIdByApplyerId(oaOut.getUserId());//所在部门负责人的上级leader
		user.setRoleId(3L);//人事专员
		Long hrId = userRoleMapper.selectUserIdByRoleId(user);//人事专员id

			//人事专员
		if(user.getUserId().longValue()==hrId.longValue()){
			oaOut.setUserId(1L);
			return oaOutMapper.selectOutApprovalList(oaOut);
			
		}else if(user.getUserId().longValue()!=hrId.longValue() && user.getUserId().longValue() == upLeaderId.longValue()){
			//其他人事==普通员工
			return oaOutMapper.selectOutApprovalList(oaOut);
			
		}
		//普通员工看不到申请记录菜单
	/*	else if(user.getUserId().longValue()!=hrId.longValue() && user.getUserId().longValue() != upLeaderId.longValue()){
			//普通员工
			return userApprovalMapper.selectQueryConditionsList(queryConditions);
			
		}*/
		else{
			//leader
			SysDept dept = deptMapper.selectDeptByUserId(oaOut.getUserId());
			dept = new SysDept();
			dept.setLeader(user.getUserName());
			dSet.clear();
			getDeptList(dept);	
			oaOut.setdSet(dSet);
			oaOut.setUserId(null);
			return oaOutMapper.selectOutApprovalList(oaOut);
		}
	}

	/**
	 * 递归实现获取当前用户负责的所有部门id
	 * @param dept
	 * @author wgf
	 */
	private void getDeptList(SysDept dept){
		SysDept dept2 = new SysDept();
		List<SysDept> deptList = deptMapper.selectDeptList(dept);
		dSet.addAll(deptList);
		for(SysDept d : deptList){
			dept2.setParentId(d.getDeptId());
			List<SysDept> deptList2 = deptMapper.selectDeptList(dept2);
			if(deptList2 != null && !"".equals(deptList2) && deptList2.size() != 0){
				getDeptList(dept2);
			}
		}
	}

	@Override
	public List<OutApproval> selectMyOutApprovalList(OaOut oaOut) {
       return oaOutMapper.selectOutApprovalList(oaOut);
	}

	@Override
	public int takeBack(Long outId) {
		OaOut out = new OaOut();
		out.setOutId(outId);
		out.setState("2");//1 待审批，2已撤回，3申请成功，4申请失败
		return oaOutMapper.updateOaOut(out);
//		OaOutApproval approval = new OaOutApproval();
//		approval.setOutId(outId);
//		return oaOutApprovalMapper.deleteOaOutApproval(approval);
	}

	@Override
	public String ifTakeback(Long outId) {
		OaOut out = oaOutMapper.selectOaOutById(outId);
		if("1".equals(out.getState()) || "2".equals(out.getState())){//1 待审批，2已撤回，3申请成功，4申请失败
			return "0";// 0 未审批 	1 已审批
		}
		
		OaOutApproval approval = new OaOutApproval();
		approval.setOutId(outId);
		approval.setApprovalSight("1");
		List<OaOutApproval> approvalList = oaOutApprovalMapper.selectOaOutApprovalList(approval);//只能查出一条
		if("3".equals(approvalList.get(0).getApprovalState()))//1同意，2驳回 ，3未操作
			return "0";
		
		return "1";// 0 未审批 	1 已审批
	}

	@Override
	public int updateOaOut(OaOut oaOut) {
		oaOut.setState("1");
		return oaOutMapper.updateOaOut(oaOut);
	}
}
