package com.ruoyi.system.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruoyi.system.domain.Holiday;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.UserApply;
import com.ruoyi.system.mapper.HolidayMapper;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.impl.HolidayServiceImpl;
import com.ruoyi.system.service.impl.UserApplyServiceImpl;

@Component("annualLeave")
public class AnnualLeaveTask {
	@Autowired
	private HolidayMapper holidayMapper;
	@Autowired
	private ISysUserService userService;
	
	@Autowired
	private HolidayServiceImpl holidayService;
	
	@Autowired
	private UserApplyServiceImpl userApplyService;
	
	public void annualLeaveTask(){

		List<SysUser> userList = userService.selectAllUser();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		
		for(SysUser user : userList){
			try {
			String intime = s.format(user.getIntime());
					
			//现在是入职的第n个月
			int n = userService.countMonth(intime);
			
			//新员工的当月不生成年假
			if(n<1){
				continue;
			}
			
			//本月年假生成（效）时间
			String creatDate = s.format(userService.getDate(intime, n));
			
			//年假生效日期在今天之后的不生成年假
			String today = s.format(new Date());
			if(creatDate.compareTo(today) > 0){
				continue;
			}
			
			//本月生成的年假的失效时间
			String overDate = s.format(userService.getDate(intime, n+12));
			
			System.out.println();
			
			//上个月份病、事请假天数
			String createMonth = creatDate.substring(0, 7);
			Date createDay = null;		
			createDay = s.parse(creatDate);
			
			//查出上月事、病假总天数
			Double holidayCount = userApplyService.leaveCount("上月", user.getUserId(), createDay);
			
			//年假生成月份有没有请产假
			UserApply userApply = new UserApply();
			userApply.setUserId(user.getUserId());
			userApply.setTimeapart1(createMonth);
			UserApply isMaternityLeave = userApplyService.selcetMaternityLeaveByUserApply(userApply);//是否在产假
					
			//如果今天是年假生效日期
			if (today.equals(creatDate)) {
				Holiday holiday1 = new Holiday();
				holiday1.setUserId(user.getUserId()); //user_id 
				holiday1.setHolidayType("1"); //类型为年假
				holiday1.setCreatedate(creatDate); 
				holiday1.setOverdate(overDate);
			
				if (holidayCount >= 10.0 ){
					holiday1.setValue(0.0);
//					holiday1.setHolidayDetail("员工一个月休假时间超过 15 天（含），则当月没有年假");
					holiday1.setHolidayDetail("员工一个月休假时间超过 10 天（含），则下月没有年假");
				}
				else if (isMaternityLeave != null){
					holiday1.setValue(0.0);
					holiday1.setHolidayDetail("员工产假期间的年假包含在产假内一并休完，不再额外计算年假");
				}
				else{
					holiday1.setValue(1.0);
				}
				holidayService.insertHoliday(holiday1);
			}
			
			//根据本月年假生成时间查库，没有就生成一条
			Holiday h = new Holiday();
			h.setUserId(user.getUserId());
			h.setCreatedate(creatDate);
			long count = holidayMapper.selectCountCreateDate(h);
			if(count == 0){//没有及时生成年假
				Holiday holiday1 = new Holiday();
				holiday1.setUserId(user.getUserId()); //user_id 
				holiday1.setHolidayType("1"); //类型为年假
				holiday1.setCreatedate(creatDate); 
				holiday1.setOverdate(overDate);
			
				if (holidayCount >= 10.0 ){
					holiday1.setValue(0.0);
//					holiday1.setHolidayDetail("员工一个月休假时间超过 15 天（含），则当月没有年假");
					holiday1.setHolidayDetail("员工一个月休假时间超过 10 天（含），则下月没有年假");
				}
				else if (isMaternityLeave != null){
					holiday1.setValue(0.0);
					holiday1.setHolidayDetail("员工产假期间的年假包含在产假内一并休完，不再额外计算年假");
				}
				else{
					holiday1.setValue(1.0);
				}
				holidayService.insertHoliday(holiday1);
			}
			 
			
			//数据库中已有的年假失效日期
			Holiday holiday = new Holiday();
			holiday.setUserId(user.getUserId());
			List<Holiday> holidayList = holidayService.selectHolidayList(holiday);
			
			for(Holiday holiday2 : holidayList){
				if(today.equals(holiday2.getOverdate())){
					Holiday holiday3 = new Holiday();
					holiday3.setId(holiday2.getId());
					holiday3.setAvailability("0");
					holidayService.updateHoliday(holiday3);
				}
			}
			
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		//处理年假、调休失效
		String today = s.format(new Date());
		//根据当前时间（年假失效时间/调休失效时间）查询失效假期
		Holiday holiday = new Holiday();
		holiday.setOverdate(today);
		long count = holidayMapper.selectCountHoliday(holiday);
		if(count != 0){
			holidayMapper.updateHolidayValue(holiday);
		}
		
	}
}
