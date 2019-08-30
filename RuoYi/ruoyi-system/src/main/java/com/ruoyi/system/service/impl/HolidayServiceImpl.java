package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.HolidayMapper;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.domain.Holiday;
import com.ruoyi.system.domain.RestHoliday;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.UserApply;
import com.ruoyi.system.service.IHolidayService;
import com.ruoyi.system.service.IUserApplyService;
import com.ruoyi.common.core.text.Convert;

/**
 * 假期 服务层实现
 * 
 * @author ruoyi
 * @date 2019-06-17
 */
@Transactional
@Service
public class HolidayServiceImpl implements IHolidayService 
{
	private static final Set<SysDept> dSet = new TreeSet<SysDept>();  //部门集合                                                                   

	@Autowired
	private HolidayMapper holidayMapper;
	@Autowired
	private IUserApplyService userApplyService;
    @Autowired
    private SysDeptMapper deptMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    
	/**
     * 查询假期信息
     * 
     * @param id 假期ID
     * @return 假期信息
     */
    @Override
	public Holiday selectHolidayById(Integer id)
	{
	    return holidayMapper.selectHolidayById(id);
	}
	
	/**
     * 查询假期列表
     * 
     * @param holiday 假期信息
     * @return 假期集合
     */
	@Override
	public List<Holiday> selectHolidayList(Holiday holiday)
	{
	    return holidayMapper.selectHolidayList(holiday);
	}
	
    /**
     * 新增假期
     * 
     * @param holiday 假期信息
     * @return 结果
     */
	@Override
	public int insertHoliday(Holiday holiday)
	{
	    return holidayMapper.insertHoliday(holiday);
	}
	
	/**
     * 修改假期
     * 
     * @param holiday 假期信息
     * @return 结果
     */
	@Override
	public int updateHoliday(Holiday holiday)
	{
	    return holidayMapper.updateHoliday(holiday);
	}

	/**
     * 删除假期对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHolidayByIds(String ids)
	{
		return holidayMapper.deleteHolidayByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据生成日期查出失效日期
	 */
	@Override
	public String selectOverdateByCreatedate(String createdate) {
		
		return holidayMapper.selectOverdateByCreatedate(createdate);
	}

	/**
	 * 根据用户id查出年假余额
	 */
	@Override
	public Double selectHolidaySumByUserId(Holiday holiday) {
		
		return holidayMapper.selectHolidaySumByUserId(holiday);
	}

	@Override
	public List<RestHoliday> selectHolidayRestList(List<SysUser> userList){
		
		List<RestHoliday> list = new ArrayList<RestHoliday>();
		Long userId = 0L;
		String userName = null;
		for(SysUser user : userList){
		
			userId = user.getUserId();
			userName = user.getUserName();
			
			Holiday holiday = new Holiday();
			holiday.setUserId(userId);
			holiday.setHolidayType("1");
	
			Holiday holiday2 = new Holiday();
			holiday2.setUserId(userId);
			holiday2.setHolidayType("2");
			
			UserApply userApply1 = new UserApply();
			userApply1.setUserId(userId);
			userApply1.setLeaveType("1");
			
			UserApply userApply2 = new UserApply();
			userApply2.setUserId(userId);
			userApply2.setLeaveType("2");
			
			RestHoliday restHoliday =new RestHoliday();
			restHoliday.setUserId(userId);
			restHoliday.setUserName(userName);
			
			Double restAnnualLeave = holidayMapper.selectHolidaySumByUserId(holiday);
			Double usedAnnualLeave = userApplyService.selectTimeLengthSumByUserApply(userApply1);
			Double restHolidayShift = holidayMapper.selectHolidaySumByUserId(holiday2);
			Double usedHolidayShift = userApplyService.selectTimeLengthSumByUserApply(userApply2);
			
			restHoliday.setRestAnnualLeave(restAnnualLeave == null ? 0 : restAnnualLeave);
			restHoliday.setUsedAnnualLeave(usedAnnualLeave == null ? 0 : usedAnnualLeave);
			restHoliday.setRestHolidayShift(restHolidayShift == null ? 0 : restHolidayShift);
			restHoliday.setUsedHolidayShift(usedHolidayShift == null ? 0 : usedHolidayShift);
			list.add(restHoliday);
		}
		return list;
	}

	/**
	 * 根据日期查出当月的生成的年假
	 * @param holiday
	 * @return
	 */
	@Override
	public Holiday selectHolidayByDate(Holiday holiday) {
		
		return holidayMapper.selectHolidayByDate(holiday);
	}
     	
	/**
	 * 查询假期余额
	 * @param sysUser
	 * @return
	 */
	@Override
	public List<RestHoliday> selectRestByUserId(SysUser sysUser) {
		if(sysUser.getUserId() == 1){//admin用户
			return holidayMapper.selectRestByUserId(sysUser);
		}
		SysUser user = userMapper.selectUserById(sysUser.getUserId());//查出当前用户

		//人事总监
		SysUser user2 = new SysUser();
		user2.setRoleId(6L);//人事总监
		Long chiefId = userRoleMapper.selectUserIdByRoleId(user2);//人事总监id
		if(chiefId.longValue() == user.getUserId().longValue()){
			sysUser.setUserId(1L);
			return holidayMapper.selectRestByUserId(sysUser);
		}
		
		if(sysUser.getUserId() == 103L){//COO
			//leader
			SysDept dept = deptMapper.selectDeptByUserId(sysUser.getUserId());
			dept = new SysDept();
			dept.setLeader(user.getUserName());
			dSet.clear();
			getDeptList(dept);	
			sysUser.setdSet(dSet);
			sysUser.setUserId(0L);
			return holidayMapper.selectRestByUserId(sysUser);
		}
		
		if(sysUser.getUserId() == 101L){//CEO
			sysUser.setUserId(0L);
			return holidayMapper.selectRestByUserId(sysUser);
		}
		
		Long upLeaderId =userMapper.selectUpApproverIdByApplyerId(sysUser.getUserId());//所在部门负责人的上级leader
		user.setRoleId(3L);//人事专员
		Long hrId = userRoleMapper.selectUserIdByRoleId(user);//人事专员id

			//人事专员
		if(user.getUserId().longValue()==hrId.longValue()){
			sysUser.setUserId(1L);
			return holidayMapper.selectRestByUserId(sysUser);
			
		}else if(user.getUserId().longValue()!=hrId.longValue() && user.getUserId().longValue() == upLeaderId.longValue()){
			//其他人事==普通员工
			return holidayMapper.selectRestByUserId(sysUser);
			
		}
		//普通员工看不到假期余额菜单
	/*	else if(user.getUserId().longValue()!=hrId.longValue() && user.getUserId().longValue() != upLeaderId.longValue()){
			//普通员工
			return holidayMapper.selectRestByUserId(sysUser);
			
		}*/
		else{
			//leader
			SysDept dept = deptMapper.selectDeptByUserId(sysUser.getUserId());
			dept = new SysDept();
			dept.setLeader(user.getUserName());
			dSet.clear();
			getDeptList(dept);	
			sysUser.setdSet(dSet);
			sysUser.setUserId(null);
			return holidayMapper.selectRestByUserId(sysUser);
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
	 * 查询我的假期余额
	 * @param sysUser
	 * @return
	 */
	@Override
	public List<RestHoliday> selectMyRestByUserId(SysUser sysUser) {
		return holidayMapper.selectMyRestByUserId(sysUser);
	}
}
