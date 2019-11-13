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
import com.ruoyi.system.mapper.OaDingdingMapper;
import com.ruoyi.system.mapper.OaWorkingCalendarMapper;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.mapper.UserApplyMapper;
import com.ruoyi.system.mapper.UserApprovalMapper;
import com.ruoyi.system.domain.Dingding;
import com.ruoyi.system.domain.Holiday;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.UserApply;
import com.ruoyi.system.domain.UserApplyList;
import com.ruoyi.system.domain.UserApproval;
import com.ruoyi.system.domain.WorkingCalendar;
import com.ruoyi.system.service.IHolidayService;
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
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
	private OaDingdingMapper oaDingdingMapper;
	@Autowired
	private OaWorkingCalendarMapper oaWorkingCalendarMapper;
	@Autowired
	private SysDictDataMapper dictDataMapper;
	@Autowired
	private IHolidayService holidayService;
	
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
			if(userId == null){
				userId = userApply.getUserId();	
			}
			//新增
		if(userApply.getApplyId() == null)	{
			userApplyMapper.insertUserApply(userApply);
		}else{//修改
			userApplyMapper.updateUserApply(userApply);
		}
		
		//如果存在审批记录先删除
		if(userApply.getApplyId() != null)	{
			userApprovalMapper.deleteUserApprovalByApplyId(userApply.getApplyId());
			//先还原假期表，删除假期使用表
			holidayService.restoreHoliday(userApply.getApplyId(), null);
			holidayRecordMapper.deleteHolidayRecordByApplyId(userApply.getApplyId());
		}
		
		Double timeLength = userApply.getTimelength();
		//申请发出后锁定相应的年假或调休天数
		
		//更新假期表、生成假期记录
		holidayService.createHolidayAndRecord(userApply);
		
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
		if(userId == 103L){//COO
			userApproval.setApproverId(101L);
		}
		userApprovalMapper.insertUserApproval(userApproval); //插入一级审批记录
		
		if(upLeaderId ==  null){
			return 1;
		}
		
		//人事审批
		UserApproval personnel = new UserApproval();//人事审批  *必审
		personnel.setApplyId(userApply.getApplyId());
		personnel.setApprovalLevel(++level);
		SysUser user = userMapper.selectUserById(userApply.getUserId());
		
		SysUser user2 = new SysUser();
		user2.setRoleId(6L);//人事总监
		user2.setArea("1");
		Long hrId = iSysRoleService.selectUserIdByRoleId(user2);//人事总监id
		
		SysUser user3 = new SysUser();
		user3.setRoleId(3L);//人事
		user3.setArea(user.getArea());
		
		//当前用户的上级是人事leader，不进行人事总监审批
		if(upLeaderId.longValue() != hrId.longValue() && approvalId.longValue() != hrId.longValue()){//审批人是否是人事总监
			//人事总监审批
//			personnel.setApproverId(iSysRoleService.selectUserIdByRoleId(user3));
			personnel.setApproverId(hrId);
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
     * 删除申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUserApplyByIds(String ids)
	{
		//查询请假类型
		List<UserApply> userApplyList = userApplyMapper.selectUserApplyByIds(Convert.toStrArray(ids));
		
		for(UserApply userApply : userApplyList){
			//先还原假期表，删除假期使用表
			holidayService.restoreHoliday(userApply.getApplyId(), null);
			holidayRecordMapper.deleteHolidayRecordByApplyId(userApply.getApplyId());	
		}
		
		//删除审批记录
		userApprovalMapper.deleteUserApprovalByIds(Convert.toStrArray(ids));
		//删除申请记录
		return userApplyMapper.deleteUserApplyByIds(Convert.toStrArray(ids));
	}
	/**
	 * 计算请假时长
	 */
	@Override
	public Double countTime(Date beginTime, Date endtime, String timepart1 ,String timepart2) {
		Double timeLength = 0.0;
		
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
		//原申请id
		Long applyId = userApply.getApplyId();
		
		//查询原申请
		UserApply oldUserApply = userApplyMapper.selectUserApplyByApplyId(applyId);
		
		//修改原申请状态为销假
		UserApply userApply2 = new UserApply();
		userApply2.setApplyId(applyId);
		userApply2.setApplyType("3");//申请类型（1请假，2加班，3销假）
		userApply2.setApplyState("5");//申请状态（1 待审批，2已撤回，3申请成功，4申请失败，5已销假）
		userApplyMapper.updateUserApply(userApply2);

		//还原假期值,修改假期记录表状态
		holidayService.restoreHoliday(applyId, "4");//使用状态(1申请中，2已使用，3撤销，4销假)
		
		//生成新请假记录
		Date now = new Date();
		UserApply userApply1 = new UserApply();
		userApply1.setUserId(userId);  //申请人
		userApply1.setApplyType("1");  //类型为请假
		userApply1.setApplyState("1");  //状态待审批
		
		String leaveType = oldUserApply.getLeaveType();
		
		userApply1.setLeaveType(leaveType); //请假类型
		userApply1.setTimeapart1(userApply.getTimeapart1());
		userApply1.setTimeapart2(userApply.getTimeapart2());
		userApply1.setStarttime(userApply.getStarttime()); //开始时间
		userApply1.setEndtime(userApply.getEndtime());		//结束时间
		userApply1.setApplyTime(now);
		userApply1.setDetails(userApply.getDetails());
		
		Double timeLength = countTime(userApply.getStarttime(), userApply.getEndtime(), userApply.getTimeapart1(), userApply.getTimeapart2());
		
		userApply1.setTimelength(timeLength);  //时长
		userApply1.setForApplyId(applyId); 
		
		//生成一条新的销假申请
		userApplyMapper.insertUserApply(userApply1); 
		
		//新生成的销假申请id
		Long newAppLyId = userApply1.getApplyId();
		
		//审批记录
		int level = 1;
			
		UserApproval userApproval = new UserApproval();//一级审批人  *必审
		userApproval.setApplyId(newAppLyId);
		userApproval.setApprovalSight("1");
		userApproval.setApprovalLevel(level);
		Long leaderId = iSysUserService.selectApproverIdByApplyerId(userId);//所在部门负责人id
		Long upLeaderId =iSysUserService.selectUpApproverIdByApplyerId(userId);//所在部门负责人的上级leader
		if(leaderId.equals(userId)){	//判断用户是否部门负责人  确定一、二级审批人id
			userApproval.setApproverId(upLeaderId); //一级审批人id	
		}
		else{
			userApproval.setApproverId(leaderId);
		}
		if(userId == 103L){//COO
			userApproval.setApproverId(101L);
		}
		userApprovalMapper.insertUserApproval(userApproval); //插入一级审批记录
		
		if(leaveType.equals("1") || leaveType.equals("2")){
			//更新假期表、生成假期记录
			holidayService.createHolidayAndRecord(userApply1);
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
		userApprovalMapper.insertUserApproval(userApproval);
		
		//人事审批
		UserApproval personnel = new UserApproval();
		personnel.setApplyId(userApply.getApplyId());
		personnel.setApprovalLevel(2);
		SysUser user = userMapper.selectUserById(userApply.getUserId());//根据申请人查询区域
		SysUser user2 = new SysUser();//当前区域hr
		user2.setArea(user.getArea());
		user2.setRoleId(3L);
		personnel.setApproverId(iSysRoleService.selectUserIdByRoleId(user2));
		userApprovalMapper.insertUserApproval(personnel);
	
		return 1;
	}

	@Override
	public String selectUserApplyListByTime(UserApply userApply) {
		Date nowDate = new Date();
		//判断起始时间是否是昨天的时间
		if(userApply.getStarttime().getTime() > nowDate.getTime()){
			return "1";
		}
		
		SysUser user = userMapper.selectUserById(userApply.getUserId());
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String workDate = sd.format(userApply.getStarttime());
		
		Date wDate = null;
		try {
			wDate = sd.parse(workDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//判断申请加班日期是否是特殊节假日
		WorkingCalendar workingCalendar= new WorkingCalendar();
		workingCalendar.setDate(wDate);
		List<WorkingCalendar> wList = oaWorkingCalendarMapper.selectWorkingCalendarList(workingCalendar);
		if(wList.get(0).getIsWorkDay() == 0){
			return "0";
		}
		
		//获取10:00 - 10:30之间的考勤
		Dingding ding = new Dingding();
		ding.setUserName(user.getUserName());
		ding.setWorkDate(wDate);
		List<Dingding> dingList = oaDingdingMapper.selectOaDingByTime2(ding);
		if(dingList.size() == 0) return "0";
		
		Date onduty = dingList.get(0).getUserCheckTime();		
			
		//9h之后的正常下班时间
		Date normal = getWorkDate(onduty,9);
		
		//判断申请加班起始时间是否在加班时间之后
		if(userApply.getStarttime().after(normal)){//a.after(b)返回一个boolean，如果a的时间在b之后（不包括等于）返回true
			return "0";
		}else if(userApply.getStarttime().equals(normal)){
			return "0";
		}
		return "1";
	}
	
	/**
	 * 计算n小时后的时间
	 * @param 请假时间
	 * @return 标准打卡时间
	 */
	private Date getWorkDate(Date d,int n){
		Date result = new Date();
		long t = d.getTime();
		t+=n*60*60*1000;
		result.setTime(t);
		return result; 
	}

	@Override
	public String ifPass(Long userId) {
		//试用一期内员工不可请年假
		SysUser user = userMapper.selectUserById(userId);
		Date today = new Date();
		Date nextMon = null;
		try {
			nextMon = getDate(user.getFirstphase());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user.getFirstphase().after(today)){
			return "1";
		}else if(today.before(nextMon)){
			return "2";
		}else{
			return "0";
		}
	}
	
	/**
	 * 得到下个月1号的时间
	 */
	private Date getDate(Date today) throws ParseException {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
			
		c.set(year, month+1, 1);

		String nextDay = s.format(c.getTime());
		
		return s.parse(nextDay);
	}
	/* *
	 * 修改申请
	 */
	@Override
	public int updateUserApply(UserApply userApply) {
		if(userApply.getLeaveType() != null){
			SysDictData dictData = new SysDictData();
			dictData.setDictLabel(userApply.getLeaveType());
			List<SysDictData> dList = dictDataMapper.selectDictDataList(dictData);
			userApply.setLeaveType(dList.get(0).getDictValue());
		}
		userApply.setApplyState("1");
		userApply.setApplyType(null);
		userApply.setApplyTime(new Date());
		userApplyMapper.updateUserApply(userApply);
		
		insertUserApply(userApply,userApply.getUserId());
		
	    return 1;
	}

	@Override
	public String ifHolidayEnough(UserApply userApply) {
    	Double timeLength = userApply.getTimelength();
    	Double holidayLength = 0.0;
    	if(userApply.getLeaveType().equals("1")){
    		Holiday holiday = new Holiday();
    		holiday.setUserId(userApply.getUserId());
    		holiday.setHolidayType("1");
    		if(holidayMapper.selectHolidaySumByUserId(holiday) != null){
    			holidayLength = holidayMapper.selectHolidaySumByUserId(holiday);
    		}
    	}
    	else if(userApply.getLeaveType().equals("2")){
    		
    		Holiday holiday = new Holiday();
    		holiday.setUserId(userApply.getUserId());
    		holiday.setHolidayType("2");
    		Double selectResult = holidayMapper.selectHolidaySumByUserId(holiday);
    		if(selectResult != null){
    			holidayLength = selectResult/8;
    		}
    		
    	}else if(userApply.getLeaveType().equals("4")){//病假
    		if(userApply.getTimelength() < 1 ){
    			UserApply sickUserApply = new UserApply();
    			sickUserApply.setUserId(userApply.getUserId());
    			sickUserApply.setStarttime(userApply.getStarttime());
    			UserApply sick = userApplyMapper.selcetSickLeaveByUserApply(sickUserApply);
    			if(sick != null){
    				return "1";
    			}else{
    	    		return "0";
    	    	}
    		}else{
        		return "0";
        	}
    	}
    	else{//不是年假也不是调休，不需要比较直接可以通过
    		return "0";
    	}
    	
    	if(userApply.getApplyId() != null){
    		double length = userApplyMapper.selectUserApplyById(userApply.getApplyId()).get(0).getTimelength();
    		//如果申请的时长不超过可申请假期总数，则可以申请
        	if(timeLength <= holidayLength+length){
        		return "0";
        	}
        	else{
        		return "1";
        	}
        	
    	}else{
    		//如果申请的时长不超过可申请假期总数，则可以申请
        	if(timeLength <= holidayLength){
        		return "0";
        	}
        	else{
        		return "1";
        	}
    	}
    }

	@Override
	public int takeBack(Long ids) {	
		List<UserApply> userApplyList = userApplyMapper.selectUserApplyById(ids);
		for(UserApply userApply : userApplyList){
			if(userApply.getLeaveType().equals("年假") || userApply.getLeaveType().equals("调休")){
				//先还原假期表，删除假期使用表
				holidayService.restoreHoliday(userApply.getApplyId(), null);
				holidayRecordMapper.deleteHolidayRecordByApplyId(userApply.getApplyId());	
			}				
		}		
		return userApplyMapper.updateUserApplyStateById(ids);
	}

	@Override
	public boolean ifBetween(UserApply userApply) {
		UserApply apply = userApplyMapper.selectUserApplyByApplyId(userApply.getApplyId());//查出当前申请
		if(apply.getForApplyId() == null){//当前申请不是销假申请
			if(userApply.getTimelength() > apply.getTimelength()){
				return false;
			}
		}else{//当前申请是销假申请
			UserApply oldApply = userApplyMapper.selectUserApplyByApplyId(apply.getForApplyId());//查出原来的申请
			if(userApply.getTimelength() > oldApply.getTimelength()){
				return false;
			}
		}
		return true;
	}

	@Override
	public int addOutSave(UserApply userApply, Long userId) {
		userApply.setUserId(userId);
		userApply.setApplyState("1");//申请状态（1 待审批，2已撤回，3申请成功，4申请失败）
		userApply.setApplyType("4");//申请类型（1请假，2加班，3销假，4外出）
		userApplyMapper.insertUserApply(userApply);

		SysUser user = userMapper.selectUserById(userId);//查出当前用户的area值
		
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
		UserApproval userApproval = new UserApproval();
		userApproval.setApproverId(approvalId);
		userApproval.setApplyId(userApply.getApplyId());
		userApproval.setApprovalSight("1");//可视
		userApproval.setApprovalLevel(1);//审批等级 —— 上级审批 1级
		userApproval.setApprovalState("3");//审批意见（1同意，2驳回 ，3未操作）
		
		if(userId == 103L){//COO
			userApproval.setApprovalId(101L);
		}
		
		userApprovalMapper.insertUserApproval(userApproval);
		
		if(upLeaderId ==  null){
			return 1;
		}
		
		//如果是审批人是 coo 直接结束
		if(approvalId != null && approvalId.longValue()==103){ 
		    return 1;
		}
		
		SysUser user2 = new SysUser();
		user2.setRoleId(6L);//人事总监
		Long hrId = userRoleMapper.selectUserIdByRoleId(user2);//人事总监id	
		
		if(upLeaderId.longValue() == hrId.longValue()){
			return 1;
		}
		
//		//当前用户的leader是人事总监时，只需要leader审批
//		if(approvalId.longValue() == hrId.longValue()){
//			return 1;
//		}
//		
//		//当前用户是hr时，只需要人事总监审批
//		if(upLeaderId.longValue() == hrId.longValue()){
//		    return 1;
//		}
//		
//		//当前用户是人事总监时，只需要leader审批
//		if(user.getUserId().longValue() == hrId.longValue()){
//		    return 1;
//		}
		
		//人事部门审批是两级审批，其他部门员工只需要leader审批
		if(upLeaderId.longValue() == hrId.longValue()){
			//生成一级审批记录
			UserApproval userApproval2 = new UserApproval();
			userApproval2.setApproverId(hrId);
			userApproval2.setApplyId(userApply.getApplyId());
			userApproval2.setApprovalSight("0");//1可见  0不可见
			userApproval2.setApprovalLevel(2);//审批等级 —— 上级审批 1级
			userApproval2.setApprovalState("3");//审批意见（1同意，2驳回 ，3未操作）
			userApprovalMapper.insertUserApproval(userApproval2);
		}
		
	    return 1;
	}
}
