package com.ruoyi.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OaDingdingMapper;
import com.ruoyi.system.mapper.OaDingdingUserMapper;
import com.ruoyi.system.mapper.OaOutMapper;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.mapper.UserApplyMapper;
import com.ruoyi.system.domain.Dingding;
import com.ruoyi.system.domain.OaDingding;
import com.ruoyi.system.domain.OaDingdingUser;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.UserApply;
import com.ruoyi.system.service.IOaDingdingService;
import com.ruoyi.common.core.text.Convert;

/**
 * 钉钉考勤数据 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-26
 */
@Service
public class OaDingdingServiceImpl implements IOaDingdingService 
{
	private static final Set<SysDept> dSet = new TreeSet<SysDept>();  //部门集合                                                                   

	@Autowired
	private OaDingdingMapper oaDingdingMapper;
	@Autowired
    private SysUserMapper userMapper;
	@Autowired
    private SysDeptMapper deptMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private UserApplyMapper applyMapper;
    @Autowired
    private OaOutMapper outMapper;
    @Autowired
    private OaDingdingUserMapper oaDingdingUserMapper;
    
    
	/**
     * 查询钉钉考勤数据信息
     * 
     * @param userId 钉钉考勤数据ID
     * @return 钉钉考勤数据信息
     */
    @Override
	public OaDingding selectOaDingdingById(String userId)
	{
	    return oaDingdingMapper.selectOaDingdingById(userId);
	}
	
	/**
     * 查询钉钉考勤数据列表
     * 
     * @param oaDingding 钉钉考勤数据信息
     * @return 钉钉考勤数据集合
     */
	@Override
	public List<Dingding> selectOaDingdingList(Dingding ding)
	{
		if(ding.getUserId() == 1){//admin用户
			return oaDingdingMapper.selectDingData(ding);
		}
		
		SysUser user = userMapper.selectUserById(ding.getUserId());
		
		//人事总监
		SysUser user2 = new SysUser();
		user2.setRoleId(6L);//人事总监
		Long chiefId = userRoleMapper.selectUserIdByRoleId(user2);//人事总监id
		if(chiefId.longValue() == user.getUserId().longValue()){
			ding.setUserId(1L);
			return oaDingdingMapper.selectDingData(ding);
		}
		
		Long upLeaderId =userMapper.selectUpApproverIdByApplyerId(ding.getUserId());//所在部门负责人的上级leader
		user.setRoleId(3L);//人事专员
		Long hrId = userRoleMapper.selectUserIdByRoleId(user);//人事专员id
		SysDept dept = deptMapper.selectDeptByUserId(ding.getUserId());//查询用户所在部门
		Long leaderId = userMapper.selectApproverIdByApplyerId(ding.getUserId());//所在部门负责人

			//人事专员
		if(user.getUserId().longValue()==hrId.longValue()){
			ding.setUserId(1L);
			return oaDingdingMapper.selectDingData(ding);
			
		}
		//其他人事和普通员工这里还有考虑？？？
		else if(user.getUserId().longValue()!=hrId.longValue() && chiefId.longValue() == upLeaderId.longValue()){
			//其他人事==普通员工
			return oaDingdingMapper.selectDingData(ding);
			
		}
		else if(user.getUserId().longValue()!=hrId.longValue() && chiefId.longValue() != upLeaderId.longValue() && leaderId.longValue() != user.getUserId().longValue()){
			//普通员工
			return oaDingdingMapper.selectDingData(ding);
			
		}else{
			//leader
			dept = new SysDept();
			dept.setLeader(user.getUserName());
			dSet.clear();
			getDeptList(dept);	
			ding.setdSet(dSet);
			ding.setUserId(null);
			return oaDingdingMapper.selectDingData(ding);
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
    /**
     * 新增钉钉考勤数据
     * 
     * @param oaDingding 钉钉考勤数据信息
     * @return 结果
     */
	@Override
	public int insertOaDingding(OaDingding oaDingding)
	{
	    return oaDingdingMapper.insertOaDingding(oaDingding);
	}
	
	/**
     * 修改钉钉考勤数据
     * 
     * @param oaDingding 钉钉考勤数据信息
     * @return 结果
     */
	@Override
	public int updateOaDingding(OaDingding oaDingding)
	{
	    return oaDingdingMapper.updateOaDingding(oaDingding);
	}

	/**
     * 删除钉钉考勤数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOaDingdingByIds(String ids)
	{
		return oaDingdingMapper.deleteOaDingdingByIds(Convert.toStrArray(ids));
	}

	@Override
	public int insertForeach(List<OaDingding> dingList) {
		
		return oaDingdingMapper.insertForeach(dingList);
	}

	@Override
	public int updateOaDingDingByOutAndApply() {
		//（一）根据请假情况更新钉钉考勤表
		UserApply apply = new UserApply();
		apply.setApplyState("3");//申请状态（1 待审批，2已撤回，3申请成功，4申请失败）
		apply.setApplyType("1");//申请类型（1请假，2加班，销假）
		apply.setStatus("0");//是否更新在钉钉考勤表中：0 未更新  1已更新
		List<UserApply> applyList = applyMapper.selectUserApplyList(apply);//申请成功的请假记录
		
		if(applyList == null){
			return 0;//没有申请成功的请假记录需要更新在钉钉考勤表中
		}
		
		for(UserApply a : applyList){
			OaDingdingUser dingUser = new OaDingdingUser();
			dingUser.setUserName(a.getSysUser().getUserName());			
			List<OaDingdingUser> dingUserList = oaDingdingUserMapper.selectOaDingdingUserList(dingUser);//根据userName查找钉钉考勤用户表中的userId
		
			//非哺乳假 	上午(10:00——15:00) 下午(15:00——19:00)
			if(a.getTimelength()%1 == 0 && !a.getLeaveType().equals("10")){//1）请n天假(判断是否为整数)
				
				if(a.getTimeapart1().equals("1") && a.getTimeapart1().equals("2")){//请全天假
					Dingding ding = new Dingding();
					ding.setUserId(Long.parseLong(dingUserList.get(0).getUserId()));
					ding.setTimeResult("apply");//apply 请假
					ding.setLeaveType(a.getLeaveType());//请假类型
					ding.setStartTime(a.getStarttime());
					ding.setEndTime(a.getEndtime());
					oaDingdingMapper.updateOaDingDingByTime(ding);
					
				}else{//请当天下午(2)、第n天上午的假 (1) if(a.getTimeapart1().equals("2") && a.getTimeapart1().equals("1"))	
					Date standardDate = getWorkDate(a.getStarttime(),15);//打卡标准值：15点前
					//修改下班考勤
					OaDingding ding = new OaDingding();
					ding.setUserId(dingUserList.get(0).getUserId());
					ding.setCheckType("OffDuty");
					ding.setWorkDate(a.getStarttime());
					List<OaDingding> dingList = oaDingdingMapper.selectOaDingdingList(ding);//查询实际打卡时间
					
					if(dingList.get(0).getUserCheckTime().compareTo(standardDate) > 0){//打卡时间标准值内
						Dingding ding2 = new Dingding();
						ding2.setUserId(Long.parseLong(dingUserList.get(0).getUserId()));
						ding2.setTimeResult("apply");//apply 请假
						ding2.setLeaveType(a.getLeaveType());//请假类型
						ding2.setStartTime(a.getStarttime());
						ding2.setEndTime(a.getEndtime());
						oaDingdingMapper.updateOaDingDingByTime(ding2);
					}
					
					//修改上班考勤
					standardDate = getWorkDate(a.getEndtime(),15);//打卡标准值：15点前
					OaDingding ding2 = new OaDingding();
					ding2.setUserId(dingUserList.get(0).getUserId());
					ding2.setCheckType("OnDuty");
					ding2.setWorkDate(a.getEndtime());
					List<OaDingding> dingList2 = oaDingdingMapper.selectOaDingdingList(ding2);//查询实际打卡时间
					
					if(dingList2.get(0).getUserCheckTime().compareTo(standardDate) < 0){//打卡时间在标准值内
						Dingding ding3 = new Dingding();
						ding3.setUserId(Long.parseLong(dingUserList.get(0).getUserId()));
						ding3.setTimeResult("apply");//apply 请假
						ding3.setLeaveType(a.getLeaveType());//请假类型
						ding3.setStartTime(a.getStarttime());
						ding3.setEndTime(a.getEndtime());
						oaDingdingMapper.updateOaDingDingByTime(ding3);
					}
				} 
				
			}else if(a.getTimelength() < 1){//2）请半天假
				if(a.getTimeapart1().equals("1")){//情况一：上午请假时段	
					Date standardDate = getWorkDate(a.getStarttime(),15);//打卡标准值：15点前
					OaDingding ding = new OaDingding();
					ding.setUserId(dingUserList.get(0).getUserId());
					ding.setCheckType("OnDuty");
					ding.setWorkDate(a.getStarttime());
					List<OaDingding> dingList = oaDingdingMapper.selectOaDingdingList(ding);//查询实际打卡时间
					
					if(dingList.get(0).getUserCheckTime().compareTo(standardDate) < 0){//打卡时间在标准值内
						Dingding ding2 = new Dingding();
						ding2.setUserId(Long.parseLong(dingUserList.get(0).getUserId()));
						ding2.setTimeResult("apply");//apply 请假
						ding2.setLeaveType(a.getLeaveType());//请假类型
						ding2.setStartTime(a.getStarttime());
						ding2.setEndTime(a.getEndtime());
						oaDingdingMapper.updateOaDingDingByTime(ding2);
					}
				}else{//情况二：下午请假
					//时段（1上午，2下午）		
					Date standardDate = getWorkDate(a.getStarttime(),15);//打卡标准值：15点前
					
					OaDingding ding = new OaDingding();
					ding.setUserId(dingUserList.get(0).getUserId());
					ding.setCheckType("OffDuty");
					ding.setWorkDate(a.getStarttime());
					List<OaDingding> dingList = oaDingdingMapper.selectOaDingdingList(ding);//查询实际打卡时间
					
					if(dingList.get(0).getUserCheckTime().compareTo(standardDate) > 0){//打卡时间标准值内
						Dingding ding2 = new Dingding();
						ding2.setUserId(Long.parseLong(dingUserList.get(0).getUserId()));
						ding2.setTimeResult("apply");//apply 请假
						ding2.setLeaveType(a.getLeaveType());//请假类型
						ding2.setStartTime(a.getStarttime());
						ding2.setEndTime(a.getEndtime());
						oaDingdingMapper.updateOaDingDingByTime(ding2);
					}
				}
			}else{//3）请n天+半天假
//				int n = (int) Math.floor(a.getTimelength());//取请假时长的整数部分
				//11 22
				Date startDate = getWorkDate(a.getStarttime(),15);//打卡标准值：15点前
				Date endDate = getWorkDate(a.getEndtime(),15);//打卡标准值：15点前					
				Dingding ding2 = new Dingding();
				ding2.setUserId(Long.parseLong(dingUserList.get(0).getUserId()));
				ding2.setTimeResult("apply");//apply 请假
				ding2.setLeaveType(a.getLeaveType());//请假类型
				ding2.setStartTime(startDate);
				ding2.setEndTime(endDate);
				oaDingdingMapper.updateOaDingDingByTime(ding2);
			}
			
			//哺乳假 早上(10:00——11:00)	 下午(18:00——19:00)
			if(a.getLeaveType().equals("10")){
				if(a.getTimeapart3().equals("1")){//上午
					Date standardDate = getWorkDate(a.getStarttime(),11);//打卡标准值：11点前
					Dingding ding = new Dingding();
					ding.setUserId(Long.parseLong(dingUserList.get(0).getUserId()));
					ding.setCheckType("OnDuty");
					ding.setStartTime(a.getStarttime());
					ding.setEndTime(a.getEndtime());
					List<Dingding> dingList = oaDingdingMapper.selectOaDingdingListByCondition(ding);//查询实际打卡时间
					for(Dingding d : dingList){
						if(d.getUserCheckTime().compareTo(standardDate) < 0){//打卡时间在标准值内
							Dingding ding2 = new Dingding();
							ding2.setUserId(Long.parseLong(dingUserList.get(0).getUserId()));
							ding2.setCheckType("OnDuty");
							ding2.setTimeResult("apply");//apply 请假
							ding2.setLeaveType(a.getLeaveType());//请假类型
							ding2.setStartTime(a.getStarttime());
							ding2.setEndTime(a.getEndtime());
							oaDingdingMapper.updateOaDingDingByTime(ding2);
						}
					}
					
				}else{
					//下午
					Date standardDate = getWorkDate(a.getStarttime(),11);//打卡标准值：11点前
					Dingding ding = new Dingding();
					ding.setUserId(Long.parseLong(dingUserList.get(0).getUserId()));
					ding.setCheckType("OffDuty");
					ding.setStartTime(a.getStarttime());
					ding.setEndTime(a.getEndtime());
					List<Dingding> dingList = oaDingdingMapper.selectOaDingdingListByCondition(ding);//查询实际打卡时间
					for(Dingding d : dingList){
						if(d.getUserCheckTime().compareTo(standardDate) > 0){//打卡时间在标准值内
							Dingding ding2 = new Dingding();
							ding2.setUserId(Long.parseLong(dingUserList.get(0).getUserId()));
							ding2.setCheckType("OffDuty");
							ding2.setTimeResult("apply");//apply 请假
							ding2.setLeaveType(a.getLeaveType());//请假类型
							ding2.setStartTime(a.getStarttime());
							ding2.setEndTime(a.getEndtime());
							oaDingdingMapper.updateOaDingDingByTime(ding2);
						}
					}
				}
			}
			applyMapper.updateUserApplyStatusById(a.getApplyId());//更新在钉钉考勤表中：0 未更新  1已更新
		}
		
		//（一）根据外出报备情况更新钉钉考勤表

		
		return 1;
	}
	
	/**
	 * 计算请假后的打卡时间
	 * @param 请假时间
	 * @return 标准打卡时间
	 */
	private Date getWorkDate(Date d,int n){
		long t = d.getTime();
		t+=n*60*60*1000;
		d.setTime(t);
		return d ; 
	}

}
