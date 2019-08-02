package com.ruoyi.system.service.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.HolidayMapper;
import com.ruoyi.system.mapper.HolidayRecordMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.UserApplyMapper;
import com.ruoyi.system.mapper.UserApprovalMapper;
import com.ruoyi.system.domain.Holiday;
import com.ruoyi.system.domain.HolidayRecord;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.UserApply;
import com.ruoyi.system.domain.UserApplyList;
import com.ruoyi.system.domain.UserApproval;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.IUserApplyService;
import com.ruoyi.common.core.text.Convert;

/**
 * 申请 服务层实现
 * 
 * @author ruoyi
 * @date 2019-06-05
 */
@Service
public class UserApplyServiceImpl implements IUserApplyService 
{
	@Autowired
	private UserApplyMapper userApplyMapper;
	
	@Autowired
	private HolidayMapper holidayMapper;
	
	@Autowired
	private ISysUserService iSysUserService;
	
	@Autowired
	private HolidayRecordMapper holidayRecordMapper;
	
	@Autowired
	private UserApprovalMapper userApprovalMapper;
	
	@Autowired
	private ISysRoleService iSysRoleService;
	
    @Autowired
    private SysUserMapper userMapper;
    
	/**
     * 查询申请信息
     * 
     * @param applyId 申请ID
     * @return 申请信息
     */
    @Override
	public List<UserApply> selectUserApplyById(Long applyId)
	{
	    return userApplyMapper.selectUserApplyById(applyId);
	}
	
	/**
     * 查询申请列表
     * 
     * @param userApply 申请信息
     * @return 申请集合
     */
	@Override
	public List<UserApply> selectUserApplyList(UserApply userApply)
	{
	    return userApplyMapper.selectUserApplyList(userApply);
	}
	/**
	 * 查询申请列表
	 */
	@Override
	public List<UserApplyList> selectUserApplyAsList(UserApply userApply) {
		
		return userApplyMapper.selectUserApplyAsList(userApply);
	}
    /**
     * 新增申请
     * 
     * @param userApply 申请信息
     * @return 结果
     */
	@Override
	@Transactional
	public int insertUserApply(UserApply userApply,Long userId)
	{
		try{
		userApply.setUserId(userId);
		userApplyMapper.insertUserApply(userApply);
		
		Double timeLength = userApply.getTimelength();
		//申请发出后锁定相应的年假或调休天数
		
		//是年假
		Holiday holiday = new Holiday();
		if(userApply.getLeaveType().equals("1")){
			//查找登录用户下所有有效年假
			holiday.setUserId(userId);//申请人id
			holiday.setHolidayType("1");//类型为年假假期
			holiday.setAvailability("1");//有效
			List<Holiday> holidayList = holidayMapper.selectHolidayList(holiday);
			Double tl = timeLength;
			for(Holiday holiday1 : holidayList){
				if(holiday1.getValue() != 0.0){
					//请假的天数比检索的到的第一条假期余额大的时候
					if(tl >= holiday1.getValue()){
						tl = tl - holiday1.getValue();
						//每一条假期信息申请后 新增一条使用记录
						HolidayRecord holidayRecord = new HolidayRecord();
						holidayRecord.setHolidayId(holiday1.getId());
						holidayRecord.setUseApplyId(userApply.getApplyId());
						holidayRecord.setValue(holiday1.getValue());
						holidayRecord.setUseState("1");//假期使用状态是申请中
						holidayRecordMapper.insertHolidayRecord(holidayRecord);
						//把假期
						Holiday holiday2 = new Holiday();
						holiday2.setId(holiday1.getId());
						holiday2.setValue(0.0);
						holidayMapper.updateHoliday(holiday2);
						if(tl == 0.0){
							break;
						}
					}
					else{
						HolidayRecord holidayRecord = new HolidayRecord();
						holidayRecord.setHolidayId(holiday1.getId());
						holidayRecord.setUseApplyId(userApply.getApplyId());
						holidayRecord.setValue(tl);
						holidayRecord.setUseState("1");
						holidayRecordMapper.insertHolidayRecord(holidayRecord);
						
						Holiday holiday2 = new Holiday();
						holiday2.setId(holiday1.getId());
						holiday2.setValue(holiday1.getValue() - tl);
						holidayMapper.updateHoliday(holiday2);
						break;
					}
				}
			}
		}
		//是调休
		if(userApply.getLeaveType().equals("2")){
			//查找登录用户下所有有效调休
			holiday.setUserId(userId);
			holiday.setHolidayType("2");
			holiday.setAvailability("1");
			List<Holiday> holidayList = holidayMapper.selectHolidayList(holiday);
			Double tl = timeLength*8;
			for(Holiday holiday1 : holidayList){
				if(holiday1.getValue() != 0.0){
					if(tl >= holiday1.getValue()){
						tl = tl - holiday1.getValue();
						HolidayRecord holidayRecord = new HolidayRecord();
						holidayRecord.setHolidayId(holiday1.getId());
						holidayRecord.setUseApplyId(userApply.getApplyId());
						holidayRecord.setValue(holiday1.getValue());
						holidayRecord.setUseState("1");
						holidayRecordMapper.insertHolidayRecord(holidayRecord);
						
						Holiday holiday2 = new Holiday();
						holiday2.setId(holiday1.getId());
						holiday2.setValue(0.0);
						holidayMapper.updateHoliday(holiday2);
						if(tl == 0.0){
							break;
						}
					}
					else{
						HolidayRecord holidayRecord = new HolidayRecord();
						holidayRecord.setHolidayId(holiday1.getId());
						holidayRecord.setUseApplyId(userApply.getApplyId());
						holidayRecord.setValue(tl);
						holidayRecord.setUseState("1");
						holidayRecordMapper.insertHolidayRecord(holidayRecord);
						
						Holiday holiday2 = new Holiday();
						holiday2.setId(holiday1.getId());
						holiday2.setValue(holiday1.getValue() - tl);
						holidayMapper.updateHoliday(holiday2);
						break;
					}
				}
			}
		}
		
		//审批记录
		int level = 1;
		
		UserApproval userApproval = new UserApproval();//一级审批人  *必审
		userApproval.setApplyId(userApply.getApplyId());
		userApproval.setApprovalSight("1");
		userApproval.setApprovalLevel(level);
		Long leaderId = iSysUserService.selectApproverIdByApplyerId(userId);//所在部门负责人id
		Long upLeaderId =iSysUserService.selectUpApproverIdByApplyerId(userId);//所在部门负责人的上级leader
		Long approvalId = 0L;
		if(leaderId.equals(userId)){	//判断用户是否部门负责人  确定一、二级审批人id
			userApproval.setApproverId(upLeaderId); //一级审批人id	
			approvalId = upLeaderId;
		}
		else{
			userApproval.setApproverId(leaderId);
			approvalId = leaderId;
		}
		userApprovalMapper.insertUserApproval(userApproval); //插入一级审批记录
				
		//人事审批
		UserApproval personnel = new UserApproval();//人事审批  *必审
		personnel.setApplyId(userApply.getApplyId());
		personnel.setApprovalLevel(++level);
		SysUser user = userMapper.selectUserById(userApply.getUserId());
		
		user.setRoleId(6L);//人事总监
		user.setArea("1");
		Long hrId = iSysRoleService.selectUserIdByRoleId(user);//人事总监id
		
		//当前用户的上级是人事leader，不进行人事总监审批
		if(upLeaderId.longValue() != hrId.longValue() && approvalId.longValue() != hrId.longValue()){//审批人是否是人事总监
			//分区域分配人事审批人
			personnel.setApproverId(iSysRoleService.selectUserIdByRoleId(user));
			userApprovalMapper.insertUserApproval(personnel);	
		}
		
		if(userApproval.getApproverId()!= null && userApproval.getApproverId()==103){ //如果是审批人是 coo 直接结束
			return 1;
		}
		
		if(timeLength > 3){
		
		//二级审批记录
		String leaveType = userApply.getLeaveType();
		boolean flag = true;
		UserApproval userApproval1 = new UserApproval();//二级审批人
		Long approverId2 = iSysUserService.selectUpApproverIdByApplyerId(userApproval.getApproverId());
		if(approverId2 != null){
			
			if(leaveType.equals("1") && timeLength < 3){ //少于3天的年假不需隔级审批
				flag=false;
			}
			else if(leaveType.equals("2") && timeLength <1){ //少于一天的调休不需隔级审批
				flag=false;
			}
			else if(leaveType.equals("3") && timeLength <3){ //少于3天的事假不需隔级审批
				flag=false;
			}
			else if(leaveType.equals("4") && timeLength <=1){ //少于等于一天的病假不需隔级审批
				flag=false;
			}
			else if(leaveType.equals("10")){
				flag=false;
			}
			if(flag){
				if(approverId2!=userApproval.getApproverId()){
					userApproval1.setApproverId(approverId2);
					userApproval1.setApplyId(userApply.getApplyId());
					userApproval1.setApprovalLevel(++level);
					userApprovalMapper.insertUserApproval(userApproval1);
				}
			}
		}
		
		if(approverId2==103){ //如果是审批人是 coo 直接结束
			return 1;
		}
		
		//中心负责人审批记录
		
			LinkedList<Long> centerId = (LinkedList<Long>)iSysUserService.selectCenterIdByUserId(userId);
			if(centerId!=null && centerId.size()>0){
				centerId.remove(approverId2);
				
				for(int i=centerId.size()-1;i>=0;i--){
					UserApproval center = new UserApproval();//中心负责人
					center.setApproverId(centerId.get(i));
					center.setApprovalLevel(++level);
					center.setApplyId(userApply.getApplyId());
					userApprovalMapper.insertUserApproval(center);
					
					if(center.getApproverId()==103){ //如果是审批人是 coo 直接结束
						userApprovalMapper.deleteChongFuShenHe(userApply.getApplyId());
						return 1;
					}
				}
				
				userApprovalMapper.deleteChongFuShenHe(userApply.getApplyId());
			}

			
//
//			if(centerId.equals(userApproval.getApproverId()) || centerId.equals(userApproval1.getApproverId()) || centerId.equals(userId)){
//			}
//			else{
//				if(centerId != null){
//					center.setApproverId(centerId);
//					center.setApprovalLevel(++level);
//					center.setApplyId(userApply.getApplyId());
//					userApprovalMapper.insertUserApproval(center);
//				}
//			}
//		
//		
//		UserApproval COO = new UserApproval(); //COO审批人
//			Long COOId = iSysUserService.selectUserIdByDeptId(100L);
//
//			if(COOId.equals(userApproval.getApproverId()) || COOId.equals(userApproval1.getApproverId()) || COOId.equals(userId)){
//			}
//			else{
//				if(COOId != null){
//					COO.setApproverId(COOId);
//					COO.setApprovalLevel(++level);
//					COO.setApplyId(userApply.getApplyId());
//					userApprovalMapper.insertUserApproval(COO);
//				}
//			}
		
		}

		
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return 1;
	
		
	   /* return userApplyMapper.insertUserApply(userApply);*/
	}
	
	/**
     * 修改申请
     * 
     * @param userApply 申请信息
     * @return 结果
     */
	@Override
	public int updateUserApply(UserApply userApply)
	{
	    return userApplyMapper.updateUserApply(userApply);
	}

	/**
     * 删除申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUserApplyByIds(String ids)
	{
		return userApplyMapper.deleteUserApplyByIds(Convert.toStrArray(ids));
	}
	/**
	 * 计算请假时长
	 */
	@Override
	public Double countTime(Date beginTime, Date endtime, String timepart1 ,String timepart2) {
		Double timeLength = 0.0;
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
		
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(beginTime);
        long time1 = cal.getTimeInMillis();
        cal.setTime(endtime);
        long time2 = cal.getTimeInMillis();      
        long between_days=(time2-time1)/(1000*3600*24);  
        int dayBetween = Integer.parseInt(String.valueOf(between_days));   
        
        Double part1 = 1.0;
        Double part2 = 0.0;
        
        if(timepart1.equals("2") ){
        	part1 = 0.5;
        }
        if(timepart2.equals("1") ){
        	part2 = 0.5;
        }

        
        timeLength = dayBetween + part1 - part2;
		return timeLength;
	}
	
	/**
	 * 计算加班时长
	 */
	@Override
	public Double countTimeLength(Date beginTime, Date endTime){
		long between = (endTime.getTime() - beginTime.getTime())/1000;  //相差的秒数
		Double hour = between/3600.0;
		
		DecimalFormat    df   = new DecimalFormat("######.00");   
		Double timeLength= Double.parseDouble(df.format(hour));
	
		return timeLength;
	
	}

	@Override
	public String createListNumber(Long id) {
		SimpleDateFormat s=new SimpleDateFormat("yyyyMMdd-HHmmss");
		s.format(new Date());
		
		String ln = "sp" + s.format(new Date()) + id;
		return ln;
	}

	/**
	 * 根据申请Id查询申请信息
	 */
	@Override
	public UserApply selectUserApplyByApplyId(Long id){
		
		return userApplyMapper.selectUserApplyByApplyId(id);
	}

	@Override
	public int updateUserApplyStateById(Long applyId) {
		
		return userApplyMapper.updateUserApplyStateById(applyId);
	}
	
	/**
	 * 根据申请id销假（修改销假状态和和销假原因）
	 */
	@Override
	public UserApply updateConfirmMasageById(UserApply userApply){
		
		return userApplyMapper.updateConfirmMasageById(userApply);
	}
	
	/**
	 * 查出事假病假的请假单
	 */
	@Override
	public Double selectLeaveUserApplyByuserId(UserApply userApply){
		
		return userApplyMapper.selectLeaveUserApplyByuserId(userApply);
	}
	
	@Override
	public List<UserApply> selectLeaveUserApplyByuserIdUp(UserApply userApply){
		return userApplyMapper.selectLeaveUserApplyByuserIdUp(userApply);
	}
	
	@Override
	public List<UserApply> selectLeaveUserApplyByuserIdDown(UserApply userApply){
		return userApplyMapper.selectLeaveUserApplyByuserIdDown(userApply);
	}
	/**
	 * 拿到某天上个月的第一天或最后一天
	 */
	public Date getDate(String monthName, Date date , String day){
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		int minDay = c.getActualMinimum(Calendar.DAY_OF_MONTH);
		int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		int month = 0;
		if(monthName == "本月"){
			month = c.get(Calendar.MONTH);
		}
		else if(monthName == "上月"){
			if (c.get(Calendar.MONTH) == 1){
				month = 1;
			}
			else{
				month = c.get(Calendar.MONTH) - 1;
			}
		}
		
		
		int year = c.get(Calendar.YEAR);
		
		
		if(day == "第一天"){
			c.set(year, month, minDay);
		}
		if(day == "最后一天"){
			c.set(year, month, maxDay);
		}
		date = c.getTime();
		
		return date;
	}
	
	/**
	 * 拿到某天上一个月份的 病、事假的请假天数的和
	 */
	@Override
	public Double leaveCount(String monthName, Long userId, Date date){
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		
		//拿到上个月的第一天和最后一天
		Date firstDay = getDate(monthName, date ,"第一天");
		Date lastDay = getDate(monthName, date ,"最后一天");
		
		//请假
		UserApply userApply = new UserApply();
		userApply.setUserId(userId);
		userApply.setStarttime(firstDay);
		userApply.setEndtime(lastDay);
		userApply.setApplyType("1");
		
		
		
		//本月请假申请成功的天数
		Double mid = 0.0;
		if(selectLeaveUserApplyByuserId(userApply) != null) {
			mid = selectLeaveUserApplyByuserId(userApply);
		}
		
		Double pre = 0.0;
		List<UserApply> preList = selectLeaveUserApplyByuserIdUp(userApply);
		if (preList != null){
			for(UserApply userApply1 : preList){
				pre = pre + countTime(firstDay, userApply1.getEndtime(), userApply1.getTimeapart1(), userApply1.getTimeapart2());
			}
		}
		
		Double suf = 0.0;
		List<UserApply> sufList = selectLeaveUserApplyByuserIdDown(userApply);
		if (sufList != null){
			for(UserApply userApply2 : sufList){
				suf = suf + countTime(userApply2.getStarttime(), lastDay, userApply2.getTimeapart1(), userApply2.getTimeapart2());
			}
		}
		
		Double result1 = mid + pre + suf;
		//上月销假申请成功的天数
		UserApply userApplyUndo = new UserApply();
		userApplyUndo.setUserId(userId);
		userApplyUndo.setStarttime(firstDay);
		userApplyUndo.setEndtime(lastDay);
		userApplyUndo.setApplyType("3");
		
		Double umid = 0.0;
		if(selectLeaveUserApplyByuserId(userApplyUndo) != null) {
			umid = selectLeaveUserApplyByuserId(userApplyUndo);
		}
		Double upre = 0.0;
		
		List<UserApply> upreList = selectLeaveUserApplyByuserIdUp(userApplyUndo);
		if (upreList != null){
			for(UserApply userApply1 : upreList){
				upre = upre + countTime(firstDay, userApply1.getEndtime(), userApply1.getTimeapart1(), userApply1.getTimeapart2());
			}
		}
		Double usuf = 0.0;
		List<UserApply> usufList = selectLeaveUserApplyByuserIdDown(userApplyUndo);
		if (usufList != null){
			for(UserApply userApply2 : usufList){
				usuf = usuf + countTime(userApply2.getStarttime(), lastDay, userApply2.getTimeapart1(), userApply2.getTimeapart2());
			}
		}
		Double result2 = umid + upre + usuf;
		
		Double total = result1 - result2;
		return total;
	}
	
	/**
	 * 查出某日期是否在产假申请所占的月份里
	 */
	@Override
	public UserApply selcetMaternityLeaveByUserApply(UserApply userApply){
		return userApplyMapper.selcetMaternityLeaveByUserApply(userApply);
	}

	/**
	 * 根据条件查询已休时长 
	 * @param userApply
	 * @return
	 */
	@Override
	public Double selectTimeLengthSumByUserApply(UserApply userApply) {
		
		return userApplyMapper.selectTimeLengthSumByUserApply(userApply);
	}

	@Override
	public UserApply selectUserApplyByIdForUndo(Long applyId) {
		
		return userApplyMapper.selectUserApplyByIdForUndo(applyId);
	}

	/**
	 * 根据条件查询需要人事确认的（待审批和撤回的）申请列表
	 * @param userApply
	 * @return
	 */
	@Override
	public List<UserApplyList> selectUserApplyConfirmAsList(UserApply userApply) {
		
		return userApplyMapper.selectUserApplyConfirmAsList(userApply);
	}

	/**
	 * 根据条件查询小于一天的病假
	 * @param userApply
	 * @return
	 */
	@Override
	public UserApply selcetSickLeaveByUserApply(UserApply userApply) {
		
		return userApplyMapper.selcetSickLeaveByUserApply(userApply);
	}
	
	@Override
	public String changeChar(String leaveTypeName){
		String leaveType = null;
		switch(leaveTypeName){
		case "年假":
			leaveType = "1";
		    break;
		case "调休":
			leaveType = "2";
		    break;
		case "事假":
			leaveType = "3";
		    break;
		case "病假":
			leaveType = "4";
		    break;
		case "婚假":
			leaveType = "5";
		    break;
		case "产假":
			leaveType = "6";
		    break;
		case "陪产假":
			leaveType = "7";
		    break;
		case "产检假":
			leaveType = "8";
		    break;
		case "丧假":
			leaveType = "9";
		    break;
		case "哺乳期":
			leaveType = "10";
		    break;
		}
		return leaveType;
	}
	/**
	 * 验证加班开始时间是否超过可申请日期
	 * @param date1
	 * @param date2
	 * @param n
	 * @return
	 */
	@Override
	public  int compare(Date date1 ,Date date2,int n){
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date1);
		
		int m1 = c.get(Calendar.MONTH);
		int y1 = c.get(Calendar.YEAR);
		int d1 = c.get(Calendar.DAY_OF_MONTH);
		
		if(m1 > n){
			m1 = m1 - n;
		}
		else{
			m1 = 12 + m1 -n;
			y1 = y1 - 1;
		}
		c.set(y1, m1, 1);
		int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		if(d1 > maxDay){
			c.set(y1, m1, maxDay);
		}
		else{
			c.set(y1, m1,d1);
		}
		
		Date beforeDay = c.getTime();
		
		String format = s.format(beforeDay);
		String format2 = s.format(date2);
		try {
			beforeDay = s.parse(format);
			date2 = s.parse(format2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date2.compareTo(beforeDay);
	}

	
	/**
	 * 查找申请时间段有没有和已有申请冲突
	 */
	@Override
	public List<UserApply> selectUserApplyListByStartTime(UserApply userApply) {
		
		return userApplyMapper.selectUserApplyListByStartTime(userApply);
	}
	@Override
	public List<UserApply> selectUserApplyListByEndTime(UserApply userApply) {
		
		return userApplyMapper.selectUserApplyListByEndTime(userApply);
	}

	@Override
	@Transactional
	public int undoSave(UserApply userApply, Long userId) {
		
	
		
		UserApply userApply1 = new UserApply();
		userApply1.setUserId(userId);  //申请人
		userApply1.setApplyType("3");  //类型为销假
		userApply1.setApplyState("1");  //状态待审批
		String leaveType = changeChar(userApply.getLeaveType());
		userApply1.setLeaveType(leaveType); //请假类型
		userApply1.setTimeapart1(userApply.getTimeapart1());
		userApply1.setTimeapart2(userApply.getTimeapart2());
		userApply1.setStarttime(userApply.getStarttime()); //开始时间
		userApply1.setEndtime(userApply.getEndtime());		//结束时间
		
		Double timeLength = countTime(userApply.getStarttime(), userApply.getEndtime(), userApply.getTimeapart1(), userApply.getTimeapart2());
		
		userApply1.setTimelength(timeLength);  //时长
		userApply1.setForApplyId(userApply.getApplyId()); 
		
		userApplyMapper.insertUserApply(userApply1); 
		
		//审批记录
		int level = 1;
		
		UserApproval userApproval = new UserApproval();//一级审批人  *必审
		userApproval.setApplyId(userApply.getApplyId());
		userApproval.setApprovalSight("1");
		userApproval.setApprovalLevel(level);
		Long leaderId = iSysUserService.selectApproverIdByApplyerId(userId);//所在部门负责人id
		Long upLeaderId =iSysUserService.selectUpApproverIdByApplyerId(userId);
		if(leaderId.equals(userId)){	//判断用户是否部门负责人  确定一、二级审批人id
			userApproval.setApproverId(upLeaderId); //一级审批人id	
		}
		else{
			userApproval.setApproverId(leaderId);
		}
		userApprovalMapper.insertUserApproval(userApproval); //插入一级审批记录
		
		//二级审批记录
		boolean flag = true;
		UserApproval userApproval1 = new UserApproval();//二级审批人
		Long approverId2 = iSysUserService.selectUpApproverIdByApplyerId(userApproval.getApproverId());
		if(approverId2 != null){
			if(timeLength >= 3){
				userApproval1.setApproverId(approverId2);
				userApproval1.setApplyId(userApply.getApplyId());
				userApproval1.setApprovalLevel(++level);
				userApprovalMapper.insertUserApproval(userApproval1);
			}
		}
		
//		//中心负责人审批记录
//		UserApproval center = new UserApproval();//中心负责人
//		if(timeLength >= 3){
//			Long centerId = iSysUserService.selectCenterIdByUserId(userId);
//
//			if(centerId.equals(userApproval.getApproverId()) || centerId.equals(userApproval1.getApproverId()) || centerId.equals(userId)){
//			}
//			else{
//				if(centerId != null){
//					center.setApproverId(centerId);
//					center.setApprovalLevel(++level);
//					center.setApplyId(userApply.getApplyId());
//					userApprovalMapper.insertUserApproval(center);
//				}
//			}
//		}
		
		//人事审批
		UserApproval personnel = new UserApproval();//人事审批  *必审
		
		personnel.setApplyId(userApply.getApplyId());
		personnel.setApprovalLevel(++level);
		SysUser user = userMapper.selectUserById(userApply.getUserId());
		user.setRoleId(3L);
		personnel.setApproverId(iSysRoleService.selectUserIdByRoleId(user));
		userApprovalMapper.insertUserApproval(personnel);
		
		
		UserApproval COO = new UserApproval(); //COO审批人
		if(timeLength >= 3){
			Long COOId = iSysUserService.selectUserIdByDeptId(100L);

			if(COOId.equals(userApproval.getApproverId()) || COOId.equals(userApproval1.getApproverId()) || COOId.equals(userId)){
			}
			else{
				if(COOId != null){
//					center.setApproverId(COOId);
//					center.setApprovalLevel(++level);
//					center.setApplyId(userApply.getApplyId());
//					userApprovalMapper.insertUserApproval(center);
				}
			}
		}	
	
		return 1;
	}

	@Override
	@Transactional
	public int addOvertimeSave(UserApply userApply, Long userId) {
		
		
		//Date beginTime = userApply.getStarttime();
		//Date endtime = userApply.getEndtime();
		//Double timeLength = userApplyService.countTimeLength(beginTime, endtime);
		System.out.println(userApply.getTimelength());
		
		userApply.setUserId(userId);
		userApply.setApplyState("1");
		//userApply.setTimelength(timeLength);
		userApplyMapper.insertUserApply(userApply);
		
		Long leaderId = iSysUserService.selectApproverIdByApplyerId(userId);
		Long upLeaderId =iSysUserService.selectUpApproverIdByApplyerId(userId);
		UserApproval userApproval = new UserApproval();
		userApproval.setApplyId(userApply.getApplyId());
		userApproval.setApprovalSight("1");//可视
		userApproval.setApprovalLevel(1);//审批等级 —— 上级审批 1级
		if(leaderId.equals(userId)){	//判断用户是否部门负责人
			userApproval.setApproverId(upLeaderId);
		}
		else{
			userApproval.setApproverId(leaderId);
		}
		userApproval.getApproverId();
		userApprovalMapper.insertUserApproval(userApproval);
		
		//人事审批
		UserApproval personnel = new UserApproval();
		personnel.setApplyId(userApply.getApplyId());
		personnel.setApprovalLevel(2);
		SysUser user = userMapper.selectUserById(userApply.getUserId());
		user.setRoleId(3L);
		personnel.setApproverId(iSysRoleService.selectUserIdByRoleId(user));
		userApprovalMapper.insertUserApproval(personnel);
	
		return 1;
	}
}
