package com.ruoyi.system.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.HolidayMapper;
import com.ruoyi.system.mapper.HolidayRecordMapper;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.mapper.UserApplyMapper;
import com.ruoyi.system.mapper.UserApprovalMapper;
import com.ruoyi.system.domain.Holiday;
import com.ruoyi.system.domain.HolidayRecord;
import com.ruoyi.system.domain.QueryConditions;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.UserApply;
import com.ruoyi.system.domain.UserApproval;
import com.ruoyi.system.service.IHolidayService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.IUserApplyService;
import com.ruoyi.system.service.IUserApprovalService;
import com.ruoyi.common.core.text.Convert;

/**
 * 审批 服务层实现
 * 
 * @author ruoyi
 * @date 2019-06-04
 */
@Transactional
@Service
public class UserApprovalServiceImpl implements IUserApprovalService 
{
	private static final Set<SysDept> dSet = new TreeSet<SysDept>();  //部门集合                                                                   

	@Autowired
	private UserApprovalMapper userApprovalMapper;
    @Autowired
    private SysDeptMapper deptMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
	@Autowired
	private HolidayMapper holidayMapper;	
	@Autowired
	private HolidayRecordMapper holidayRecordMapper;
	@Autowired
	private IUserApplyService userApplyService;
	@Autowired
	private ISysUserService iSysUserService;
	@Autowired
	private IHolidayService holidayService;
	@Autowired
	private UserApplyMapper userApplyMapper;
	
	/**
     * 查询审批信息
     * 
     * @param approvalId 审批ID
     * @return 审批信息
     */
    @Override
	public UserApproval selectUserApprovalById(Long approvalId)
	{
	    return userApprovalMapper.selectUserApprovalById(approvalId);
	}
	
	/**
     * 查询审批列表
     * 
     * @param userApproval 审批信息
     * @return 审批集合
     */
	@Override
	public List<UserApproval> selectUserApprovalList(UserApproval userApproval)
	{
	    return userApprovalMapper.selectUserApprovalList(userApproval);
	}
	
    /**
     * 新增审批
     * 
     * @param userApproval 审批信息
     * @return 结果
     */
	@Override
	public int insertUserApproval(UserApproval userApproval)
	{
	    return userApprovalMapper.insertUserApproval(userApproval);
	}
	
	/**
     * 修改审批
     * 
     * @param userApproval 审批信息
     * @return 结果
     */
	@Override
	public int updateUserApproval(UserApproval userApproval)
	{
	    return userApprovalMapper.updateUserApproval(userApproval);
	}

	/**
     * 删除审批对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUserApprovalByIds(String ids)
	{
		return userApprovalMapper.deleteUserApprovalByIds(Convert.toStrArray(ids));
	}

	@Override
	public UserApproval selectUserApprovalByUserApproval(UserApproval userApproval) {
		
		return userApprovalMapper.selectUserApprovalByUserApproval(userApproval);
	}

	/**
     * 查询审批列表
     * 
     * @param userApproval 审批信息
     * @return 审批集合
     */
	@Override
	public List<QueryConditions> selectQueryConditionsList(QueryConditions queryConditions) {
		return userApprovalMapper.selectQueryConditionsList(queryConditions);
	}

	/**
     * 查询所有审批列表
     * 
     * @param userApproval 审批信息
     * @return 审批集合
     */
	@Override
	public List<QueryConditions> selectAllQueryConditionsList(QueryConditions queryConditions) {

		if(queryConditions.getUserId() == 1L || queryConditions.getUserId() == 222L){//admin用户、辛本荣
			return userApprovalMapper.selectQueryConditionsList(queryConditions);
		}
		
		SysUser user = userMapper.selectUserById(queryConditions.getUserId());//查出当前用户
		
		//人事总监
		SysUser user2 = new SysUser();
		user2.setRoleId(6L);//人事总监
		Long chiefId = userRoleMapper.selectUserIdByRoleId(user2);//人事总监id
		if(chiefId.longValue() == user.getUserId().longValue()){
			queryConditions.setUserId(1L);
			return userApprovalMapper.selectQueryConditionsList(queryConditions);
		}
		
		if(queryConditions.getUserId() == 103L){//COO
			//leader
			SysDept dept = deptMapper.selectDeptByUserId(queryConditions.getUserId());
			dept = new SysDept();
			dept.setLeader(user.getUserName());
			dSet.clear();
			getDeptList(dept);	
			queryConditions.setdSet(dSet);
			queryConditions.setUserId(0L);
			return userApprovalMapper.selectQueryConditionsList(queryConditions);
		}
		
		if(queryConditions.getUserId() == 101L){//CEO		
			queryConditions.setUserId(0L);
			return userApprovalMapper.selectQueryConditionsList(queryConditions);
		}

		if(user.getArea().equals("3")){
			user.setArea("2");
		}
		Long upLeaderId =userMapper.selectUpApproverIdByApplyerId(queryConditions.getUserId());//所在部门负责人的上级leader
		user.setRoleId(3L);//人事专员
		Long hrId = userRoleMapper.selectUserIdByRoleId(user);//人事专员id

		//查看数据权限
		SysUser user3 = new SysUser();
		user3.setUserId(queryConditions.getUserId());
		user3.setRoleId(15L);
		Long id = userRoleMapper.selectUserIdByRoleId(user3);//具备查看数据权限的用户id
		if(id != null){
			queryConditions.setUserId(1L);
			return userApprovalMapper.selectQueryConditionsList(queryConditions);
		}
		
			//人事专员
		if(hrId != null && user.getUserId().longValue()==hrId.longValue()){
			queryConditions.setUserId(1L);
			return userApprovalMapper.selectQueryConditionsList(queryConditions);
			
		}else if(hrId != null && user.getUserId().longValue()!=hrId.longValue() && user.getUserId().longValue() == upLeaderId.longValue()){
			//其他人事==普通员工
			return userApprovalMapper.selectQueryConditionsList(queryConditions);
			
		}
		//普通员工看不到申请记录菜单
	/*	else if(user.getUserId().longValue()!=hrId.longValue() && user.getUserId().longValue() != upLeaderId.longValue()){
			//普通员工
			return userApprovalMapper.selectQueryConditionsList(queryConditions);
			
		}*/
		else{
			//leader
			SysDept dept = deptMapper.selectDeptByUserId(queryConditions.getUserId());
			dept = new SysDept();
			dept.setLeader(user.getUserName());
			dSet.clear();
			getDeptList(dept);	
			queryConditions.setdSet(dSet);
			queryConditions.setUserId(0L);
			return userApprovalMapper.selectQueryConditionsList(queryConditions);
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
	public void reject(Long ids, String remark) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UserApproval userApproval = new UserApproval();
		userApproval.setApprovalId(ids);
		userApproval.setApprovalState("2");//审批意见（1同意，2驳回 ，3未操作）
		userApproval.setRemark(remark);
		userApproval.setApprovalTime(sdf.format(new Date()));
		userApprovalMapper.updateUserApproval(userApproval);
		
		//根据审批id查询申请id
		Long applyId = userApprovalMapper.selectUserApprovalById(ids).getApplyId();
		
		//修改申请状态为驳回
		UserApply userApply = new UserApply();
		userApply.setApplyId(applyId);
		userApply.setApplyState("4");//申请状态（1 待审批，2已撤回，3申请成功，4申请失败）
		userApplyMapper.updateUserApply(userApply);
		
		//先还原假期表，删除假期使用表
		holidayService.restoreHoliday(applyId, null);
		holidayRecordMapper.deleteHolidayRecordByApplyId(applyId);
		
	}

	@Override
	public void agree(Long ids) {
		
		UserApproval userApproval = new UserApproval();
		userApproval.setApprovalId(ids);
		userApproval.setApprovalState("1"); //1同意 2驳回 3未操作		
		
		//查出此审批单 
		UserApproval userApproval1 = userApprovalMapper.selectUserApprovalById(ids);
		
		//查同个申请id有没有下一级审批人
		UserApproval userApproval2 = new UserApproval();
		userApproval2.setApplyId(userApproval1.getApplyId());
		userApproval2.setApprovalLevel(userApproval1.getApprovalLevel()+1);
	
		UserApproval userApproval3 = userApprovalMapper.selectUserApprovalByUserApproval(userApproval2);
		
		//查同个申请id有没有下下一级审批人
		UserApproval userApproval6 = new UserApproval();
		userApproval6.setApplyId(userApproval1.getApplyId());
		userApproval6.setApprovalLevel(userApproval1.getApprovalLevel()+2);
	
		UserApproval userApproval7 = userApprovalMapper.selectUserApprovalByUserApproval(userApproval6);
		
		
		if (userApproval7 == null && userApproval3 == null){//没有查到下级审批人  把申请单的状态改为申请成功，通过申请ID找到
			UserApply userApply = new UserApply();
			userApply.setApplyId(userApproval1.getApplyId());
			userApply.setApplyState("3");
			userApplyMapper.updateUserApply(userApply);
			//通过申请后,判断该申请是什么申请
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
			String applyType = userApproval1.getUserApply().getApplyType();
			
			//如果是加班申请，根据加班时长，生成调休
			if( applyType.equals("2") ){
				//修改相应调休记录为有效
				Holiday holiday = new Holiday();
				holiday.setApplyId(userApproval1.getApplyId());
				holiday.setAvailability("1");
				int count = holidayMapper.updateHoliday(holiday);
				
				//针对之前提交的加班申请，需要生成调休，结束后再删除
				if(count == 0){
					SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
					
					//查询申请
					UserApply app = userApplyMapper.selectUserApplyByApplyId(userApproval1.getApplyId());
					
					holiday.setUserId(userApproval1.getUserApply().getUserId());
					holiday.setHolidayType("2");
					holiday.setAvailability("1");//是否有效（0否 1是）
					try {
						holiday.setOverdate(s.format(iSysUserService.getDate(s.format(userApproval1.getUserApply().getStarttime()), 3)));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					holiday.setValue(Math.floor(app.getTimelength()));
					holiday.setCreatedate(s.format(userApproval1.getUserApply().getStarttime()));
					holidayMapper.insertHoliday(holiday);										
				}
				
			}
			//如果是销假申请
			else if(applyType.equals("3") ){
				/*
				//把销假申请对应的请假申请状态改为已销假
				UserApply userApply1 = new UserApply();
				userApply1.setApplyId(userApproval1.getUserApply().getForApplyId());
				userApply1.setApplyState("5");
				userApplyService.updateUserApply(userApply1);
				//年假和调休销假后，更改对应的假期记录和假期使用记录
				String leaveType = userApproval1.getUserApply().getLeaveType();
				if(leaveType.equals("1") || leaveType.equals("2")){
					HolidayRecord holidayRecord = new HolidayRecord();
					holidayRecord.setApplyId(userApproval1.getUserApply().getForApplyId());
					holidayRecord.setUseState("2");
					List<HolidayRecord> holidayRecordList = holidayRecordMapper.selectHolidayRecordList(holidayRecord);
					
					for(HolidayRecord holidayRecord1 : holidayRecordList){
						
						Holiday holiday = new Holiday();
						//还原本此条假期信息
						Holiday holiday1 = holidayMapper.selectHolidayById(holidayRecord1.getHolidayId());
						holiday.setId(holidayRecord1.getHolidayId());
						holiday.setValue(holidayRecord1.getValue() + holiday1.getValue());
						holidayMapper.updateHoliday(holiday);
						
						//把原本假期使用记录状态改为被销假
						HolidayRecord holidayRecord2 = new HolidayRecord();
						holidayRecord2.setId(holidayRecord1.getId());
						holidayRecord2.setUseState("5");
						holidayRecordMapper.updateHolidayRecord(holidayRecord2);
						
						//新增一条假期使用记录的销假记录
						HolidayRecord holidayRecord3 = new HolidayRecord();
						holidayRecord3.setHolidayId(holidayRecord1.getHolidayId());
						holidayRecord3.setApplyId(userApproval1.getApplyId());
						holidayRecord3.setValue(holidayRecord1.getValue());
						holidayRecord3.setUseState("4");//
						holidayRecordMapper.insertHolidayRecord(holidayRecord3);
					}
				}
				//如果是事假或者病假
				else if(leaveType.equals("3") || leaveType.equals("4")){
					Date starttime = userApproval1.getUserApply().getStarttime();
					Date endtime = userApproval1.getUserApply().getEndtime();
					Double leaveCount = userApplyService.leaveCount("本月", userApproval1.getUserApply().getUserId(), starttime);
					
					if(leaveCount < 15.0){
						Holiday iholiday = new Holiday();
						iholiday.setUserId(userApproval1.getUserApply().getUserId());
						iholiday.setIdate(starttime);
						Holiday changeHoliday = holidayMapper.selectHolidayByDate(iholiday);
						if( changeHoliday != null && changeHoliday.getValue() == 0.0){
							Holiday iholiday1 = new Holiday();
							iholiday1.setId(changeHoliday.getId());
							iholiday.setHolidayType("1");
							iholiday1.setValue(1.0);
							holidayMapper.updateHoliday(iholiday1);
						}
					}
					else{
						Double leaveCount2 = userApplyService.leaveCount("本月", userApproval1.getUserApply().getUserId(), endtime);
						if(leaveCount2 < 15.0){
							Holiday iholiday = new Holiday();
							iholiday.setUserId(userApproval1.getUserApply().getUserId());
							iholiday.setHolidayType("1");
							iholiday.setIdate(endtime);
							Holiday changeHoliday = holidayMapper.selectHolidayByDate(iholiday);
							if( changeHoliday != null && changeHoliday.getValue() == 0.0){
								Holiday iholiday1 = new Holiday();
								iholiday1.setId(changeHoliday.getId());
								iholiday1.setValue(1.0);
								holidayMapper.updateHoliday(iholiday1);
							}
						}
					}
					
				}
			*/}
			//如果是请假申请
			else if(applyType.equals("1")){
				String leaveType = userApproval1.getUserApply().getLeaveType();
				//如果是年假或调休申请
				if(leaveType.equals("1")  || leaveType.equals("2")){
					//将假期使用记录中的申请状态改为已使用
					HolidayRecord holidayRecord = new HolidayRecord();
					holidayRecord.setApplyId(userApproval1.getApplyId());
					holidayRecord.setUseState("2");
					holidayRecordMapper.updateHolidayRecordByApplyId(holidayRecord);

					/*List<HolidayRecord> holidayRecordList = holidayRecordMapper.selectHolidayRecordList(holidayRecord);
					if(holidayRecordList != null){
						for(HolidayRecord holidayRecord1 : holidayRecordList){
							
							HolidayRecord holidayRecord2 = new HolidayRecord();
							holidayRecord2.setId(holidayRecord1.getId());
							holidayRecord2.setUseState("2");
							holidayRecordMapper.updateHolidayRecord(holidayRecord2);
						}
					}*/
				}
				//如果是事假或者病假
				else if(leaveType.equals("3") || leaveType.equals("4")){
					Date starttime = userApproval1.getUserApply().getStarttime();
					Date endtime = userApproval1.getUserApply().getEndtime();
					Double leaveCount = userApplyService.leaveCount("本月", userApproval1.getUserApply().getUserId(), starttime);
					if(leaveCount >= 15.0){
						Holiday iholiday = new Holiday();
						iholiday.setUserId(userApproval1.getUserApply().getUserId());
						iholiday.setHolidayType("1");
						iholiday.setIdate(starttime);
						Holiday changeHoliday = holidayMapper.selectHolidayByDate(iholiday);
						if( changeHoliday != null && changeHoliday.getValue() != 0.0){
							Holiday iholiday1 = new Holiday();
							iholiday1.setId(changeHoliday.getId());
							iholiday1.setValue(0.0);
							iholiday1.setHolidayDetail("员工一个月休假时间超过 15 天（含），则当月没有年假。");
							holidayMapper.updateHoliday(iholiday1);
						}
					}
					String format = sf.format(starttime);
					String format2 = sf.format(endtime);
					if(format.equals(format2)){
						System.out.println("111111111");
					}
					else{
						Double leaveCount2 = userApplyService.leaveCount("本月", userApproval1.getUserApply().getUserId(), endtime);
						if(leaveCount2 >= 15.0){
							Holiday iholiday = new Holiday();
							iholiday.setUserId(userApproval1.getUserApply().getUserId());
							iholiday.setHolidayType("1");
							iholiday.setIdate(endtime);
							Holiday changeHoliday = holidayMapper.selectHolidayByDate(iholiday);
							if( changeHoliday != null && changeHoliday.getValue() != 0.0){
								Holiday iholiday1 = new Holiday();
								iholiday1.setId(changeHoliday.getId());
								iholiday1.setValue(0.0);
								iholiday1.setHolidayDetail("员工一个月休假时间超过 15 天（含），则当月没有年假。");
								holidayMapper.updateHoliday(iholiday1);
							}
						}
					}
				}
			}
			
		}
		else{//有下级审批人 把下级审批人的审批单改为 可视 状态   通过审批id找到
			if(userApproval3 != null){
				UserApproval userApproval4 = new UserApproval();
				userApproval4.setApprovalId(userApproval3.getApprovalId());
				userApproval4.setApprovalSight("1");
				userApprovalMapper.updateUserApproval(userApproval4);
				userApproval.setApprovalSight("0");
			}
			if(userApproval7 != null){
				UserApproval userApproval4 = new UserApproval();
				userApproval4.setApprovalId(userApproval7.getApprovalId());
				userApproval4.setApprovalSight("1");
				userApprovalMapper.updateUserApproval(userApproval4);
				userApproval.setApprovalSight("0");
			}
			
		}
		
		//修改当前审批人审批状态
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		userApproval.setApprovalTime(sdf.format(new Date()));
		userApprovalMapper.updateUserApproval(userApproval);
		}
}
