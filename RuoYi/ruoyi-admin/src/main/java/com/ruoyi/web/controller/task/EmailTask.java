package com.ruoyi.web.controller.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruoyi.common.config.Global;
import com.ruoyi.system.domain.Holiday;
import com.ruoyi.system.domain.OaOut;
import com.ruoyi.system.domain.OutApproval;
import com.ruoyi.system.domain.QueryConditions;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.email.EmailSend;
import com.ruoyi.system.mapper.OaOutMapper;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.UserApprovalMapper;
import com.ruoyi.system.service.IHolidayService;
import com.ruoyi.system.service.ISysUserService;

@Component("email")
public class EmailTask {
	
	@Autowired
	private UserApprovalMapper userApprovalMapper;
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysDeptMapper deptMapper;
	@Autowired
	private OaOutMapper oaOutMapper;
	@Autowired
	private ISysUserService userService;
	@Autowired
	private IHolidayService holidayService;
	
	private static final Set<SysDept> dSet = new TreeSet<SysDept>();  //部门集合                                                                   

	//三个月试用期到期提醒(提前两周)
	//六个月试用期到期提醒(提前一个月)
	//收件人：部门leader、宋彬、李嘉欣、辛本荣
	public void sendEmail4(){

		List<SysUser> userList = userService.selectAllUser();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		String today = s.format(new Date());		

		int count = 0;//记录发送邮件数量
		for(SysUser user : userList){
			String firstphase = s.format(user.getFirstphase());//一期到期时间
			String secondphase = s.format(user.getSecondphase());//二期到期时间
			
			long day = secondsBetween(today,firstphase)/3600/24;//两个日期相差的天数
			String second = "";
			try {
				second = s.format(userService.getDate(s.format(user.getIntime()), 5));//5个月以后的入职时间
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(day != 14L & !second.equals(today)) continue;
			
			SysUser leader = userMapper.selectLeaderByUserId(user.getUserId());//查询部门leader
			SysUser upleader = userMapper.selectUpLeaderByUserId(user.getUserId());//查询上上级部门leader
			String leaderEmail = "";//上级邮箱
			String copyto = "";//抄送人邮箱
			if(user.getEmail().equals(leader.getEmail())){
				leaderEmail = upleader.getEmail();
			}else{
				leaderEmail = leader.getEmail();
			}
			
			if(leaderEmail != null && "songbin@perfect-cn.cn".equals(leaderEmail)){
				copyto = "xinbenrong@perfect-cn.cn,lijiaxin@perfect-cn.cn";//抄送人邮箱
			}else{
				copyto = "songbin@perfect-cn.cn,xinbenrong@perfect-cn.cn,lijiaxin@perfect-cn.cn";//抄送人邮箱
			}
			
			if(day == 14L){//提前两周通知
				
				  EmailSend es = new EmailSend();
		  			try {
		  				es.sendMail(leaderEmail, copyto,
//		  		  		es.sendMail("wugaofang@perfect-cn.cn", null,
		  						"三/六个月考核到期提醒",  
		  						"<font size='3'>领导好:<br/><br/>&#12288;&#12288;您部门员工<font size='4' color='red'>"+user.getUserName()+"</font>三个月考核将于<font size='4' color='red'>"
		  						+firstphase+"</font>结束，请在此之前将考核结果反馈给人力资源部。<br/><br/>"
		  						+"&#12288;&#12288;三个月考核员工考核要素分为人力资源评价、工作态度、工作能力三大项，具体如下：<br/><br/>"
		  						+"&#12288;&#12288;(1)人力资源评价：包括出勤情况、公司融合度<br/><br/>" 
		  						+"&#12288;&#12288;(2)工作态度：包括积极性、纪律性、团队意识、责任感<br/><br/>"
		  						+"&#12288;&#12288;(3)工作能力：包括基本知识技能、执行能力、学习能力、表达沟通</font>",
		  						Global.getEmail(), Global.getPassword());
		  				count ++;		  			
		  			} catch (Exception e) {
		  				// TODO Auto-generated catch block
		  				e.printStackTrace();
		  			}	
		        }
			
	    
	        if(second.equals(today)){//提前一个月通知
	        	
	        	  EmailSend es = new EmailSend();
	  			try {
	  				es.sendMail(leaderEmail, copyto,
//	  		  				es.sendMail("wugaofang@perfect-cn.cn", null,
	  						"三/六个月考核到期提醒",  
	  						"<font size='3'>领导好:<br/><br/>&#12288;&#12288;您部门员工<font size='4' color='red'>"+user.getUserName()+"</font>六个月试用期将于<font size='4' color='red'>"
	  						+secondphase+"</font>结束，请在此之前将考核结果反馈给人力资源部。<br/><br/>"
	  						+"&#12288;&#12288;试用期员工考核要素分为人力资源评价、工作态度、工作能力、工作业绩四大项，具体如下：<br/><br/>"
	  						+"&#12288;&#12288;(1)人力资源评价：包括出勤情况、公司融合度<br/><br/>" 
	  						+"&#12288;&#12288;(2)工作态度：包括积极性、团队意识、责任感<br/><br/>"
	  						+"&#12288;&#12288;(3)工作能力：包括执行能力、学习能力、表达沟通<br/><br/>"
	  						+"&#12288;&#12288;(4)工作业绩：包括工作效率、工作质量</font>",
	  						Global.getEmail(), Global.getPassword());
	  				count ++;	  					  	  				
	  			} catch (Exception e) {
	  				// TODO Auto-generated catch block
	  				e.printStackTrace();
	  			}	
	        
	        }
		}
		System.out.println("\n三/六个月考核到期提醒邮件已发送数量："+count+"\n");
	
	}
	
	//假期到期提醒 暂停20190923
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
	  				es.sendMail(user.getEmail(), null,
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
		  				es.sendMail(user.getEmail(),  null,
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
		  				es.sendMail(user.getEmail(),  null,
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
							es.sendMail(user.getEmail(), null,
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
		QueryConditions query = new QueryConditions();
		
		int count = 0;//记录邮件发送数量
		for(SysUser user : leaderList){
			
			//用户已经离职
			if(user.getStatus().equals("1"))continue;
			
			//leader
			SysDept dept = deptMapper.selectDeptByUserId(user.getUserId());//根据审批人id查询其下所有部门
			
			if(dept == null || "".equals(dept)) continue;
			
			dept = new SysDept();
			dept.setLeader(user.getUserName());
			dSet.clear();
			getDeptList(dept);	
			
			//查询未审批请假记录
			query.setdSet(dSet);
			query.setApproverId(user.getUserId());//审批人ID
			query.setApprovalState("3");//审批意见（1同意，2驳回 ，3未操作）
			query.setApprovalSight("1");//可见性（1可见 0不可见）
			query.setApplyState("1");//申请状态（1 待审批，2已撤回，3申请成功，4申请失败）
			List<QueryConditions> cList = userApprovalMapper.selectQueryConditionsList(query);
			
			System.out.println("\n"+"未审批请假总数："+cList.size()+"\n");
			
			//查询外出报备待审批列表
			OaOut oaOut = new OaOut();
			oaOut.setApprovalId(user.getUserId());
			oaOut.setApprovalState("3");//审批状态（1同意，2驳回 ，3未操作）
			oaOut.setApprovalSight("1");
			oaOut.setState("1");//申请状态（1 待审批，2已撤回，3申请成功，4申请失败）
	        List<OutApproval> list = oaOutMapper.selectOutApprovalList(oaOut);
			System.out.println("\n"+"未审批外出报备请假总数："+list.size()+"\n");

			if(cList.size() == 0 && list.size() == 0) continue;

			System.out.println("\n"+"审批人："+user.getUserName()+"\n");
			EmailSend es = new EmailSend();
			 try {
				 if(cList.size() != 0 || list.size() != 0){
					 System.out.println("\n"+user.getEmail()+"\n");

						es.sendMail(user.getEmail(), null,
//				  		es.sendMail("wugaofang@perfect-cn.cn", null,
//				  				"审批提醒",user.getUserName()+"  "+
								"审批提醒",
								"您的人事OA系统中有未审批的申请，烦请尽快完成审批。谢谢 ！！<br/><br/> OA系统登陆网址："+
								"<a href=\"\\192.168.88.191\"\">http://192.168.88.191/</a>", 
								Global.getEmail(), Global.getPassword());
						count++;
				 }
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		System.out.println("\n 审批提醒已发送邮件数量："+count+"\n");
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
			 time2 = cal.getTimeInMillis();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
	     return (time2-time1)/1000;     
	 }  
}
