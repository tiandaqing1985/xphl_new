package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.mapper.OaOutApprovalMapper;
import com.ruoyi.system.mapper.OaOutMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.domain.OaOut;
import com.ruoyi.system.domain.OaOutApproval;
import com.ruoyi.system.domain.OutApproval;
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
	@Autowired
	private OaOutMapper oaOutMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private OaOutApprovalMapper oaOutApprovalMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    
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
     * 新增外出报备
     * 
     * @param oaOut 外出报备信息
     * @return 结果
     */
	@Override
	public int insertOaOut(OaOut oaOut)
	{
		oaOutMapper.insertOaOut(oaOut);
		
		//生成审批记录
		//leader
		Long approvalId = userMapper.selectApproverIdByApplyerId(oaOut.getUserId());//所在部门负责人id
		OaOutApproval oaOutApproval =  new OaOutApproval();
		oaOutApproval.setOutId(oaOut.getOutId());//外出报备id
		oaOutApproval.setApprovalId(approvalId);//审批人user_id
		oaOutApproval.setApprovalState("3");//审批状态（1同意，2驳回 ，3未操作）
		oaOutApproval.setApprovalSight("1");//1可见  0不可见
		oaOutApprovalMapper.insertOaOutApproval(oaOutApproval);
		
		//如果是审批人是 coo 直接结束
		if(approvalId != null && approvalId==103){ 
		    return 1;
		}
		
		//分区域分配人事审批人
		SysUser user = userMapper.selectUserById(oaOut.getUserId());//查出当前用户的area值
		user.setRoleId(3L);
		approvalId = userRoleMapper.selectUserIdByRoleId(user);
		
		//当前用户是hr时
		if(user.getUserId().longValue() == approvalId.longValue()){
		    return 1;
		}
		
		//人事审批
		OaOutApproval oaOutApproval2 =  new OaOutApproval();
		oaOutApproval2.setOutId(oaOut.getOutId());//外出报备id
		oaOutApproval2.setApprovalId(approvalId);//审批人user_id
		oaOutApproval2.setApprovalState("3");//审批状态（1同意，2驳回 ，3未操作）
		oaOutApproval2.setApprovalSight("0");//1可见  0不可见
		oaOutApprovalMapper.insertOaOutApproval(oaOutApproval2);
		
	    return 1;
	}
	
	/**
     * 修改外出报备
     * 
     * @param oaOut 外出报备信息
     * @return 结果
     */
	@Override
	public int updateOaOut(OaOut oaOut)
	{
	    return oaOutMapper.updateOaOut(oaOut);
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
	 *  查询外出报备审批结果
	 * @param oaOut
	 * @return 外出报备审批结果
	 */
	@Override
	public List<OutApproval> selectOutApprovalList(OaOut oaOut) {
		return oaOutMapper.selectOutApprovalList(oaOut);
	}
	
}
