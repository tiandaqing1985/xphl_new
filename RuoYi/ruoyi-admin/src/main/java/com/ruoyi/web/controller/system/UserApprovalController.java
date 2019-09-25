package com.ruoyi.web.controller.system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Holiday;
import com.ruoyi.system.domain.HolidayRecord;
import com.ruoyi.system.domain.QueryConditions;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.UserApply;
import com.ruoyi.system.domain.UserApproval;
import com.ruoyi.system.service.IHolidayService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.IUserApplyService;
import com.ruoyi.system.service.IUserApprovalService;
import com.ruoyi.system.service.impl.HolidayRecordServiceImpl;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 审批 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-06-04
 */
@Controller
@RequestMapping("/system/userApproval")
public class UserApprovalController extends BaseController
{
    private String prefix = "system/userApproval";
	
	@Autowired
	private IUserApprovalService userApprovalService;
	
	@Autowired
	private IUserApplyService userApplyService;
	
	@Autowired
	private ISysUserService iSysUserService;
	
	@Autowired
	private IHolidayService iHolidayService;
	
	@Autowired
	private HolidayRecordServiceImpl holidayRecordServiceImpl;
	
	@Autowired
	private ISysRoleService iSysRoleService;
	
	@RequiresPermissions("system:userApproval:view")
	@GetMapping()
	public String userApproval()
	{
	    return prefix + "/userApproval";
	}
	
	
	@GetMapping("/approvalModer")
	public String approvalModer(Long ids,Model m)
	{
		m.addAttribute("ids", ids);
	    return prefix + "/approvalModer";
	}
	
	/**
	 * 查询待审批列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(QueryConditions queryConditions)
	{
		startPage();
		//userApproval.setApproverId(ShiroUtils.getUserId());  
		//userApproval.setApprovalState("3");
		queryConditions.setApproverId(ShiroUtils.getUserId());//审批人是登录的用户
		queryConditions.setApprovalState("3");//审批意见是，未操作
		queryConditions.setApprovalSight("1");//审批为可见状态
		queryConditions.setApplyState("1");//申请状态 待审批
		
		List<QueryConditions> list = userApprovalService.selectQueryConditionsList(queryConditions);
        //List<UserApproval> list = userApprovalService.selectUserApprovalList(userApproval);
		return getDataTable(list);
	}
	
	/**
	 * 查询申请记录
	 */
	@PostMapping("/allList")
	@ResponseBody
	public TableDataInfo allList(QueryConditions queryConditions)
	{
		startPage();
		queryConditions.setUserId(ShiroUtils.getUserId());
		queryConditions.setApprovalSight("1");
		List<QueryConditions> list = userApprovalService.selectAllQueryConditionsList(queryConditions);
		return getDataTable(list);
	}
	
	/**
	 * 我的审批
	 */
	@GetMapping("/myApproval")
	public String myApproval(Model m)
	{
		
		boolean showFlag = false;
		SysUser user = iSysUserService.selectUserById(ShiroUtils.getUserId());
		user.setRoleId(3L);
		Long personnelId = iSysRoleService.selectUserIdByRoleId(user);//查询人事id
		if(personnelId != null  && personnelId.equals(ShiroUtils.getUserId())){
			showFlag = true;
		}
		m.addAttribute("showFlag", showFlag);
	    return prefix + "/myApproval";
	}
	/**
	 * 我的审批
	 */
	@PostMapping("/myApproval")
	@ResponseBody
	public AjaxResult holiday(UserApply userApply)
	{
		
		
        
		return toAjax(1);
	}
	
	/**
	 * 查询我的审批列表
	 */
	@PostMapping("/myApprovalList")
	@ResponseBody
	public TableDataInfo myApprovalList(QueryConditions queryConditions)
	{
		startPage();
		
		queryConditions.setApproverId(ShiroUtils.getUserId());//审批人是登录的用户
		//queryConditions.setApprovalState("3");//审批意见是，未操作
		//queryConditions.setApprovalSight("1");//审批为可见状态
		//queryConditions.setApplyState("1");//申请状态 待审批
		queryConditions.setStatus(1);
		List<QueryConditions> list = userApprovalService.selectQueryConditionsList(queryConditions);
		return getDataTable(list);
	}
	
	/**
	 * 所有审批
	 */
	@GetMapping("/allApprovalByMe")
	public String allApprovalByMe()
	{
	    return prefix + "/allApprovalByMe";
	}
	/**
	 * 所有审批
	 */
	@PostMapping("/allApprovalByMe")
	@ResponseBody
	public AjaxResult allApprovalByMe(UserApply userApply)
	{
		
		
        
		return toAjax(1);
	}
	
	
	/**
	 * 查询我审批的列表
	 */
	@PostMapping("/approvalByMeList")
	@ResponseBody
	public TableDataInfo approvalByMeList(QueryConditions queryConditions)
	{
		startPage();
		
		queryConditions.setApproverId(ShiroUtils.getUserId());//审批人是登录的用户
		
		List<QueryConditions> list = userApprovalService.selectQueryConditionsList(queryConditions);
		return getDataTable(list);
	}
	
	/**
	 * 所有审批
	 */
	@GetMapping("/allApproval")
	public String allApproval()
	{
	    return prefix + "/allApproval";
	}
	/**
	 * 所有审批
	 */
	@PostMapping("/allApproval")
	@ResponseBody
	public AjaxResult openAllApproval(UserApply userApply)
	{
		
		
        
		return toAjax(1);
	}
	
	/**
	 * 查询所有审批列表
	 */
	@PostMapping("/allApprovalList")
	@ResponseBody
	public TableDataInfo allApprovalList(QueryConditions queryConditions)
	{
		startPage();
		
		List<QueryConditions> list = userApprovalService.selectAllQueryConditionsList(queryConditions);
		return getDataTable(list);
	}
		
	/**
	 * 导出审批列表
	 */
	@RequiresPermissions("system:userApproval:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserApproval userApproval)
    {
    	List<UserApproval> list = userApprovalService.selectUserApprovalList(userApproval);
        ExcelUtil<UserApproval> util = new ExcelUtil<UserApproval>(UserApproval.class);
        return util.exportExcel(list, "userApproval");
    }
	
	/**
	 * 新增审批
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存审批
	 */
	@Log(title = "审批", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(UserApproval userApproval)
	{		
		return toAjax(userApprovalService.insertUserApproval(userApproval));
	}

	/**
	 * 修改审批
	 */
	@GetMapping("/edit/{approvalId}")
	public String edit(@PathVariable("approvalId") Long approvalId, ModelMap mmap)
	{
		UserApproval userApproval = userApprovalService.selectUserApprovalById(approvalId);
		mmap.put("userApproval", userApproval);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存审批
	 */
	@RequiresPermissions("system:userApproval:edit")
	@Log(title = "审批", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(UserApproval userApproval)
	{		
		return toAjax(userApprovalService.updateUserApproval(userApproval));
	}
	
	/**
	 * 删除审批
	 */
	@RequiresPermissions("system:userApproval:remove")
	@Log(title = "审批", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(userApprovalService.deleteUserApprovalByIds(ids));
	}
	
	/**
	 * 查看某个申请
	 */
	@GetMapping("/toView/{approvalId}")
	public String toCheck(@PathVariable("approvalId") Long approvalId,  Model m)
	{
		boolean showFlag = false;
		SysUser user = iSysUserService.selectUserById(ShiroUtils.getUserId());
		user.setRoleId(3L);
		Long personnelId = iSysRoleService.selectUserIdByRoleId(user);//查询人事id
		if(personnelId.equals(ShiroUtils.getUserId())){
			showFlag = true;
		}
		UserApproval approval = userApprovalService.selectUserApprovalById(approvalId);
		List<UserApply> applyList = userApplyService.selectUserApplyById(approval.getApplyId());
		for(UserApply apply : applyList){
			if(apply.getRemark() != null)
				m.addAttribute("userApply", apply);
		}
		if(!m.containsAttribute("userApply"))
			m.addAttribute("userApply", applyList.get(0));
		m.addAttribute("showFlag", showFlag);
	    return prefix + "/toView";
	}
	
	/**
	 * 同意申请
	 */
	@PostMapping("/agree")
	@ResponseBody
	public AjaxResult agree(Long ids)
	{	
		UserApproval userApproval = new UserApproval();
		userApproval.setApprovalId(ids);
		userApproval.setApprovalState("1"); //1同意 2驳回 3未操作
		
		
		//查出此审批单 
		UserApproval userApproval1 = userApprovalService.selectUserApprovalById(ids);
		//查同个申请id有没有下一级审批人
		UserApproval userApproval2 = new UserApproval();
		userApproval2.setApplyId(userApproval1.getApplyId());
		userApproval2.setApprovalLevel(userApproval1.getApprovalLevel()+1);

		UserApproval userApproval3 = userApprovalService.selectUserApprovalByUserApproval(userApproval2);
		
		if (userApproval3 == null){//没有查到下级审批人  把申请单的状态改为申请成功，通过申请ID找到
			UserApply userApply = new UserApply();
			userApply.setApplyId(userApproval1.getApplyId());
			userApply.setApplyState("3");
			userApplyService.updateUserApply(userApply);
			//通过申请后,判断该申请是什么申请
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
			String applyType = userApproval1.getUserApply().getApplyType();
			Double timelength = userApproval1.getUserApply().getTimelength();
			//如果是加班申请，根据加班时长，生成调休
			if( applyType.equals("2") ){
				SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
				
				Holiday holiday = new Holiday();
				holiday.setUserId(userApproval1.getUserApply().getUserId());
				holiday.setHolidayType("2");
				holiday.setCreatedate(s.format(userApproval1.getUserApply().getStarttime()));
				try {
					holiday.setOverdate(s.format(iSysUserService.getDate(s.format(userApproval1.getUserApply().getStarttime()), 3)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				holiday.setApplyId(userApproval1.getApplyId());
				int temp = (int)(timelength/2)*2;
				Double value = (double)temp;
				holiday.setValue(value);
				iHolidayService.insertHoliday(holiday);
				
			}
			//如果是销假申请
			else if(applyType.equals("3") ){
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
					List<HolidayRecord> holidayRecordList = holidayRecordServiceImpl.selectHolidayRecordList(holidayRecord);
					
					for(HolidayRecord holidayRecord1 : holidayRecordList){
						
						Holiday holiday = new Holiday();
						//还原本此条假期信息
						Holiday holiday1 = iHolidayService.selectHolidayById(holidayRecord1.getHolidayId());
						holiday.setId(holidayRecord1.getHolidayId());
						holiday.setValue(holidayRecord1.getValue() + holiday1.getValue());
						iHolidayService.updateHoliday(holiday);
						
						//把原本假期使用记录状态改为被销假
						HolidayRecord holidayRecord2 = new HolidayRecord();
						holidayRecord2.setId(holidayRecord1.getId());
						holidayRecord2.setUseState("5");
						holidayRecordServiceImpl.updateHolidayRecord(holidayRecord2);
						
						//新增一条假期使用记录的销假记录
						HolidayRecord holidayRecord3 = new HolidayRecord();
						holidayRecord3.setHolidayId(holidayRecord1.getHolidayId());
						holidayRecord3.setApplyId(userApproval1.getApplyId());
						holidayRecord3.setValue(holidayRecord1.getValue());
						holidayRecord3.setUseState("4");//
						holidayRecordServiceImpl.insertHolidayRecord(holidayRecord3);
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
						Holiday changeHoliday = iHolidayService.selectHolidayByDate(iholiday);
						if( changeHoliday != null && changeHoliday.getValue() == 0.0){
							Holiday iholiday1 = new Holiday();
							iholiday1.setId(changeHoliday.getId());
							iholiday.setHolidayType("1");
							iholiday1.setValue(1.0);
							iHolidayService.updateHoliday(iholiday1);
						}
					}
					if(sf.format(starttime).equals(sf.format(endtime))){
						System.out.println("111111111111");
					}
					else{
						Double leaveCount2 = userApplyService.leaveCount("本月", userApproval1.getUserApply().getUserId(), endtime);
						if(leaveCount2 < 15.0){
							Holiday iholiday = new Holiday();
							iholiday.setUserId(userApproval1.getUserApply().getUserId());
							iholiday.setHolidayType("1");
							iholiday.setIdate(endtime);
							Holiday changeHoliday = iHolidayService.selectHolidayByDate(iholiday);
							if( changeHoliday != null && changeHoliday.getValue() == 0.0){
								Holiday iholiday1 = new Holiday();
								iholiday1.setId(changeHoliday.getId());
								iholiday1.setValue(1.0);
								iHolidayService.updateHoliday(iholiday1);
							}
						}
					}
					
				}
			}
			//如果是请假申请
			else if(applyType.equals("1")){
				String leaveType = userApproval1.getUserApply().getLeaveType();
				//如果是年假或调休申请
				if(leaveType.equals("1")  || leaveType.equals("2")){
					//将假期使用记录中的申请状态改为已使用
					HolidayRecord holidayRecord = new HolidayRecord();
					holidayRecord.setApplyId(userApproval1.getApplyId());
					List<HolidayRecord> holidayRecordList = holidayRecordServiceImpl.selectHolidayRecordList(holidayRecord);
					if(holidayRecordList != null){
						for(HolidayRecord holidayRecord1 : holidayRecordList){
							
							HolidayRecord holidayRecord2 = new HolidayRecord();
							holidayRecord2.setId(holidayRecord1.getId());
							holidayRecord2.setUseState("2");
							holidayRecordServiceImpl.updateHolidayRecord(holidayRecord2);
						}
					}
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
						Holiday changeHoliday = iHolidayService.selectHolidayByDate(iholiday);
						if( changeHoliday != null && changeHoliday.getValue() != 0.0){
							Holiday iholiday1 = new Holiday();
							iholiday1.setId(changeHoliday.getId());
							iholiday1.setValue(0.0);
							iholiday1.setHolidayDetail("员工一个月休假时间超过 15 天（含），则当月没有年假。");
							iHolidayService.updateHoliday(iholiday1);
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
							Holiday changeHoliday = iHolidayService.selectHolidayByDate(iholiday);
							if( changeHoliday != null && changeHoliday.getValue() != 0.0){
								Holiday iholiday1 = new Holiday();
								iholiday1.setId(changeHoliday.getId());
								iholiday1.setValue(0.0);
								iholiday1.setHolidayDetail("员工一个月休假时间超过 15 天（含），则当月没有年假。");
								iHolidayService.updateHoliday(iholiday1);
							}
						}
					}
				}
			}
			
		}
		else{//有下级审批人 把下级审批人的审批单改为 可视 状态   通过审批id找到
			UserApproval userApproval4 = new UserApproval();
			userApproval4.setApprovalId(userApproval3.getApprovalId());
			userApproval4.setApprovalSight("1");
			userApprovalService.updateUserApproval(userApproval4);
			userApproval.setApprovalSight("0");
		}
		
		userApprovalService.updateUserApproval(userApproval);
		return toAjax(1);
	}
	
	/**
	 * 驳回申请
	 */
	@PostMapping("/reject")
	@ResponseBody
	public AjaxResult reject(Long ids, String remark)
	{	
		
		UserApproval userApproval = new UserApproval();
		userApproval.setApprovalId(ids);
		userApproval.setApprovalState("2");
		userApproval.setRemark(remark);
		userApprovalService.updateUserApproval(userApproval);
		
		Long applyId = userApprovalService.selectUserApprovalById(ids).getApplyId();
		
		UserApply userApply = new UserApply();
		userApply.setApplyId(applyId);
		userApply.setApplyState("4");
		userApplyService.updateUserApply(userApply);
		
		
		//在年假调休使用记录中找到该申请生成的记录
		HolidayRecord holidayRecord = new HolidayRecord();
		holidayRecord.setApplyId(applyId);
		List<HolidayRecord> holidayRecordList = holidayRecordServiceImpl.selectHolidayRecordList(holidayRecord);
		if(holidayRecordList != null){
			for(HolidayRecord holidayRecord1 : holidayRecordList){
				
				Holiday holiday = new Holiday();
				//原本此条假期信息
				Holiday holiday1 = iHolidayService.selectHolidayById(holidayRecord1.getHolidayId());
				holiday.setId(holidayRecord1.getHolidayId());
				holiday.setValue(holidayRecord1.getValue() + holiday1.getValue());
				iHolidayService.updateHoliday(holiday);
				
				HolidayRecord holidayRecord2 = new HolidayRecord();
				holidayRecord2.setId(holidayRecord1.getId());
				holidayRecord2.setUseState("6");
				holidayRecordServiceImpl.updateHolidayRecord(holidayRecord2);
			}
		}
		
			
		
		
		return toAjax(1);
	}
}
