package com.ruoyi.web.controller.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruoyi.common.config.Global;
import com.ruoyi.system.domain.Holiday;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.email.EmailSend;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.UserApprovalMapper;
import com.ruoyi.system.service.IHolidayService;
import com.ruoyi.system.service.ISysUserService;

@Component("email")
public class EmailTask {
	
	@Autowired
	UserApprovalMapper userApprovalMapper;
	@Autowired
	SysUserMapper userMapper;
	@Autowired
	SysDeptMapper deptMapper;
	@Autowired
	private ISysUserService userService;
	@Autowired
	private IHolidayService holidayService;
	
	//假期到期提醒
	public void sendEmail3(){
		List<SysUser> userList = userService.selectAllUser();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		String today = s.format(new Date());
		String overdate = "";
		try {
			overdate = s.format(userService.getDate(today, 1));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Double ynum = 0.0;//记录即将到期年假天数
		String yoverDate = "";//记录年假到期时间
		Double tnum = 0.0;//记录即将到期调休时长
		String toverDate = "";//记录调休到期时间
		int count = 0;//记录发送邮件数量
		for(SysUser user : userList){
			ynum = 0.0;//记录即将到期年假天数
			tnum = 0.0;//记录即将到期调休时长
			
			Holiday holiday = new Holiday();
			holiday.setUserId(user.getUserId());
			holiday.setAvailability("1");
			holiday.setHolidayType("1");//年假
	        List<Holiday> yearList = holidayService.selectHolidayList(holiday);
	        if(yearList != null && yearList.size() != 0){
		        yoverDate = yearList.get(0).getOverdate();
	        	if(overdate.equals(yoverDate))
		        ynum = yearList.get(0).getValue();
	        }
	        	        
	        holiday.setAvailability("1");
			holiday.setHolidayType("2");//调休
	        List<Holiday> offList = holidayService.selectHolidayList(holiday);
	        if(offList != null && offList.size() != 0){
		        toverDate = offList.get(0).getOverdate();
		        if(overdate.equals(toverDate))
	        	tnum = offList.get(0).getValue();
	        }
	     
	        if(ynum == 0 && tnum == 0) continue;

	        if(ynum != 0 && tnum != 0){
	        	  EmailSend es = new EmailSend();
	  			try {
	  				es.sendMail(user.getEmail(), 
//	  		  		es.sendMail("wugaofang@perfect-cn.cn", 
	  						"假期到期提醒",  
	  						user.getUserName()+"：<br/>&#12288;&#12288;您有"+ynum+"天年假、"+tnum+"小时调休假即将到期，过期时间为"+toverDate+"，请尽快使用~", 
	  						Global.getEmail(), Global.getPassword());
	  				count ++;
	  			} catch (Exception e) {
	  				// TODO Auto-generated catch block
	  				e.printStackTrace();
	  			}	
	        }else if(ynum != 0 && tnum == 0){
	        	 EmailSend es = new EmailSend();
		  			try {
		  				es.sendMail(user.getEmail(), 
//		  		  		es.sendMail("wugaofang@perfect-cn.cn", 
		  						"假期到期提醒",  
		  						user.getUserName()+"：<br/>&#12288;&#12288;您有"+ynum+"天年假即将到期，过期时间为"+yoverDate+"，请尽快使用~", 
		  						Global.getEmail(), Global.getPassword());
		  				count ++;
		  			} catch (Exception e) {
		  				// TODO Auto-generated catch block
		  				e.printStackTrace();
		  			}	
	        }else{
	        	 EmailSend es = new EmailSend();
		  			try {
		  				es.sendMail(user.getEmail(), 
//		  		  		es.sendMail("wugaofang@perfect-cn.cn", 
		  						"假期到期提醒",  
		  						user.getUserName()+"：<br/>&#12288;&#12288;您有"+tnum+"小时调休假即将到期，过期时间为"+toverDate+"，请尽快使用~", 
		  						Global.getEmail(), Global.getPassword());
		  				count ++;
		  			} catch (Exception e) {
		  				// TODO Auto-generated catch block
		  				e.printStackTrace();
		  			}	
	        }
	      
		}
		System.out.println("\n假期到期提醒邮件已发送数量："+count+"\n");
	}
	
	//司龄提醒
	public void sendEmail2(){
		try {
			List<SysUser> userList = userService.selectAllUser();
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			
			int count = 0;
			
			for(SysUser user : userList){
				String intime = s.format(user.getIntime());
						
				//现在是入职的第n个月
				int n = userService.countMonth(intime);
				
				if(n<12){
					continue;
				}
				
				//司龄不为整年
				if(n%12 != 0){
					continue;
				}
				
				//本月满一年的日期
				String creatDate = s.format(userService.getDate(intime, n));
				
				//今天是否是满一年的日期
				String today = s.format(new Date());
				if(creatDate.equals(today)){
					int year = today.compareTo(intime);//满几年
					long second = secondsBetween(intime,today);
					long minute = second/60;
					long hour = minute/60;
					long day = hour/24;
					
					EmailSend es = new EmailSend();
						try {
							es.sendMail(user.getEmail(), 
//			  		  		es.sendMail("wugaofang@perfect-cn.cn", 
									"司龄提醒", 
									"亲爱的"+user.getUserName()+"：<br/><br>&#12288;&#12288;不知不觉你和新普相互陪伴走过了<font color='red' size='4'>"+year+"</font>年，<font color='red' size='4'>"
									+day+"</font>天，<font color='red' size='4'>"+hour+"</font>小时，<font color='red' size='4'>"+minute+"</font>分钟，<font color='red' size='4'>"+second+"</font>秒。"
									+"<br><br>&#12288;&#12288;这段时间有快乐，有辛酸，有难过，有感动。<br><br>&#12288;&#12288;新普见证了你从青涩变成熟，你见证了新普飞跃的发展从北五环到CBD。<br><br>&#12288;&#12288;<font color='red' size='4'>"+day
									+"</font>多天的陪伴不仅成就了彼此，也使我们之间更加默契。<br><br>&#12288;&#12288;未来我们将继续携手前行，一起走过最美的花路。"
									, 
									Global.getEmail(), Global.getPassword());
							count ++;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}					
				}
			}
			System.out.println("\n 司龄提醒已发送邮件数量："+count+"\n");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//邮件审批提醒
	public void sendEmail(){
		//查询所有审批人	
		List<SysUser> leaderList = userMapper.selectLeaderList();
		
		int count = 0;//记录邮件发送数量
		for(SysUser user : leaderList){
			EmailSend es = new EmailSend();
			 try {
				es.sendMail(user.getEmail(), 
//		  		es.sendMail("wugaofang@perfect-cn.cn", 
						"审批提醒",  
						"您的人事OA系统中有未审批的申请，烦请尽快完成审批。谢谢 ！<br/><br/> OA系统登陆网址："+
						"<a href=\"\\192.168.88.191\"\">http://192.168.88.191/</a>", 
						Global.getEmail(), Global.getPassword());
				count++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("\n 审批提醒已发送邮件数量："+count+"\n");
	}
	 /** 
	    *字符串的日期格式的计算 
	    */  
	 private long secondsBetween(String smdate,String bdate){  
	    long time1 = 0L;
		long time2 = 0L;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
			 Calendar cal = Calendar.getInstance();    
			 cal.setTime(sdf.parse(smdate));    
			 time1 = cal.getTimeInMillis();                 
			 cal.setTime(sdf.parse(bdate));    
			 time2 = cal.getTimeInMillis()+24*3600*1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
	     return (time2-time1)/1000;     
	 }  
}
