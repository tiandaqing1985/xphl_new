package com.ruoyi.web.controller.system;

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
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.UserApply;
import com.ruoyi.system.domain.UserApplyList;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.IUserApplyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 申请 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-06-05
 */
@Controller
@RequestMapping("/system/userApply")
public class UserApplyController extends BaseController
{
    private String prefix = "system/userApply";
	
	@Autowired
	private IUserApplyService userApplyService;
	
	@Autowired
	private ISysUserService iSysUserService;	
	
	@Autowired
	private ISysPostService postService;
	
	@Autowired
	private ISysRoleService iSysRoleService;
	
	@RequiresPermissions("system:userApply:view")
	@GetMapping()
	public String userApply()
	{
	    return prefix + "/userApply";
	}
	
	/**
	 * 查询我的申请列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo myList(UserApply userApply)
	{
		startPage();

		userApply.setUserId(ShiroUtils.getUserId());
		userApply.setApprovalS("1");
        List<UserApplyList> list = userApplyService.selectUserApplyAsList(userApply);
		return getDataTable(list);
	}
	
	/**
	 * 查询我的加班申请列表
	 */
	@PostMapping("/overTimeList")
	@ResponseBody
	public TableDataInfo myOverTimeList(UserApply userApply)
	{
		startPage();

		userApply.setUserId(ShiroUtils.getUserId());
		userApply.setApprovalS("1");
		userApply.setApplyType("2");
        List<UserApplyList> list = userApplyService.selectUserApplyAsList(userApply);
		return getDataTable(list);
	}
	
	/**
	 * 查询我的加班申请列表
	 */
	@PostMapping("/undoList")
	@ResponseBody
	public TableDataInfo myUndoList(UserApply userApply)
	{
		startPage();

		userApply.setUserId(ShiroUtils.getUserId());
		userApply.setApprovalS("1");
		userApply.setApplyType("2");
        List<UserApplyList> list = userApplyService.selectUserApplyAsList(userApply);
		return getDataTable(list);
	}
	/**
	 * 查看某个申请
	 */
	@GetMapping("/toView/{applyId}")
	public String toCheck(@PathVariable("applyId") Long applyId,  Model m)
	{
		boolean showFlag = false;
		SysUser user = iSysUserService.selectUserById(ShiroUtils.getUserId());
		user.setRoleId(3L);
		Long personnelId = iSysRoleService.selectUserIdByRoleId(user);//查询人事id
		if(personnelId.equals(ShiroUtils.getUserId())){
			showFlag = true;
		}
		List<UserApply> applyList = userApplyService.selectUserApplyById(applyId);
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
	 * 打开假期余额
	 */
	@GetMapping("/holidayRest")
	public String holidayRest(ModelMap m)
	{
		long postId = postService.selectPostIdByUserId(ShiroUtils.getUserId());
		m.put("postId", postId);
		
	    return prefix + "/holidayRest";
	}
	/**
	 * 查看假期余额
	 */
	@PostMapping("/holidayRest")
	@ResponseBody
	public AjaxResult holiday(UserApply userApply)
	{
		
        
		return toAjax(1);
	}
	
	/**
	 * 打开我的假期余额
	 */
	@GetMapping("/myHolidayRest")
	public String myHolidayRest(ModelMap m)
	{
		/*long postId = postService.selectPostIdByUserId(ShiroUtils.getUserId());
		m.put("postId", postId);*/
		
	    return prefix + "/myHolidayRest";
	}
	/**
	 * 查看我的假期余额
	 */
	@PostMapping("/myHolidayRest")
	@ResponseBody
	public AjaxResult myHolidayRest(UserApply userApply)
	{
		
        
		return toAjax(1);
	}
	
	/**
	 * 导出申请列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserApply userApply)
    {
		
    	List<UserApply> list = userApplyService.selectUserApplyList(userApply);
        ExcelUtil<UserApply> util = new ExcelUtil<UserApply>(UserApply.class);
        
        return util.exportExcel(list, "userApply");
    }
	
	/**
	 * 新增请假申请
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存请假申请
	 */
	@Log(title = "申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(UserApply userApply)
	{
		userApply.setUserId(ShiroUtils.getUserId());
		int i = userApplyService.insertUserApply(userApply, ShiroUtils.getUserId());
		return toAjax(i);
	}

	/**
	 * 修改申请
	 */
	@GetMapping("/edit/{applyId}")
	public String edit(@PathVariable("applyId") Long applyId, ModelMap mmap)
	{
		UserApply userApply = userApplyService.selectUserApplyByIdForUndo(applyId);
		mmap.put("userApply", userApply);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存申请
	 */
	@Log(title = "申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(UserApply userApply)
	{		
		userApply.setUserId(ShiroUtils.getUserId());
		return toAjax(userApplyService.updateUserApply(userApply));
	}
	
	/**
	 * 删除申请
	 */
	@Log(title = "申请", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(userApplyService.deleteUserApplyByIds(ids));
	}
	
	/**
	 * 撤回申请
	 */
	@PostMapping("/takeBack")
	@ResponseBody
	public AjaxResult takeBack(Long ids)
	{	
		return toAjax(userApplyService.takeBack(ids));
	}
	
	/**
	 * 销假申请
	 */
	@GetMapping("/undo/{applyId}")
	public String undo(@PathVariable("applyId") Long applyId, ModelMap mmap)
	{
		UserApply userApply = userApplyService.selectUserApplyByIdForUndo(applyId);
		System.out.println(userApply.getTimeapart1());
		System.out.println(userApply.getTimeapart2());
		mmap.addAttribute(userApply);
	    return prefix + "/undo";
	}
	/**
	 * 保存销假申请
	 */
	@PostMapping("/undo")
	@ResponseBody
	public AjaxResult undoSave(UserApply userApply)
	{
		int i = userApplyService.undoSave(userApply,ShiroUtils.getUserId());
		
		return toAjax(i);	
	}
	/**
	 * 新增加班申请
	 */
	@GetMapping("/addOther")
	public String addOther()
	{
	    return prefix + "/addOther";
	}
	
	/**
	 * 新增保存加班申请
	 */
	@Log(title = "申请", businessType = BusinessType.INSERT)
	@PostMapping("/addOther")
	@ResponseBody
	public AjaxResult addOvertimeSave(UserApply userApply)
	{
		int i = userApplyService.addOvertimeSave(userApply,ShiroUtils.getUserId());
		return toAjax(i);
	}
	
	/**
	 * 验证员工是否通过试用一期
	 * */
	@PostMapping("/ifPass")
	@ResponseBody
	public String ifPass(){
		return userApplyService.ifPass(ShiroUtils.getUserId());
	}
	
	
	/**
	 * 验证起始时间是否在加班时间范围内
	 */
    @PostMapping("/ifSatisfy")
    @ResponseBody
    public String ifSatisfy(UserApply userApply)
    {
    	userApply.setUserId(ShiroUtils.getUserId());
    	return userApplyService.selectUserApplyListByTime(userApply);
    }
    
	/**
	 * 验证开始时间是否为昨天之前的时间
	 */
    @PostMapping("/ifBefore")
    @ResponseBody
    public String ifBefore(UserApply userApply)
    {
    	Date nowDate = new Date();
		//判断起始时间是否是昨天的时间
		if(userApply.getStarttime().getTime() > nowDate.getTime()){
			return "1";
		}else{
	    	return "0";
		}
    }
    
	/**
	 * 验证是否能做销假操作
	 */
    @PostMapping("/ifUndo")
    @ResponseBody
    public String ifUndo(Long applyId)
    {
    	
    	UserApply userApply = userApplyService.selectUserApplyByIdForUndo(applyId);
    	//请假单是请假申请并且申请成功的时候可以做销假
    	if(userApply.getApplyType().equals("请假") && userApply.getApplyState().equals("申请成功")){
    		return "0";
    	}else{
    		return "1";
    	}
    }
    /**
	 * 验证是否已经审核过
	 */
    @PostMapping("/ifTakeback")
    @ResponseBody
    public String ifTakeback(Long applyId)
    {
    	UserApply userApply = userApplyService.selectUserApplyByIdForUndo(applyId);
    	System.out.println(userApply);
    	//请假单是请假申请并且申请成功的时候可以做销假
    	if(userApply.getApplyState().equals("已撤回")){
    		return "0";
    	}else if(userApply.getApplyState().equals("待审批") && userApply.getApprovalState().equals("未操作")){
    		return "0";
    	}
    	else{
    		return "1";
    	}
    }
    /**
	 * 验证有没有足够的年假或调休可请
	 */
    @PostMapping("/ifHolidayEnough")
    @ResponseBody
    public String ifHolidayEnough(UserApply userApply)
    {
    	userApply.setUserId(ShiroUtils.getUserId());
    	return userApplyService.ifHolidayEnough(userApply);    		
    }
    
    /**
	 * 验证选择的
	 */
    @PostMapping("/ifUndoTimeRight")
    @ResponseBody
    public String ifUndoTimeRight(UserApply userApply){
    	
    	
    	return "0";
    }
    /**
	 * 验证选择的加班开始时间是否超过可选时间
	 */
    @PostMapping("/overWorkTimeRight")
    @ResponseBody
    public String overWorkTimeRight(UserApply userApply){
    	
    	Date starttime = userApply.getStarttime();
    	int res = userApplyService.compare(new Date(), starttime, 3);
    	if(res == 1){
    		return "0";
    	}
    	else{
    		return "1";
    	}
    }
    
    /**
	 * 验证选择的
	 */
    @PostMapping("/ifRepeat")
    @ResponseBody
    public String ifRepeat(UserApply userApply){
    	userApply.setUserId(ShiroUtils.getUserId());
    	
    	List<UserApply> ifStarttimeRepeat = userApplyService.selectUserApplyListByStartTime(userApply);
    	List<UserApply> ifEndtimeRepeat = userApplyService.selectUserApplyListByEndTime(userApply);
    	if(ifStarttimeRepeat != null && ifStarttimeRepeat.size()>0){
    		if(ifStarttimeRepeat.size()==1){
    			//查出本身
    			if(ifStarttimeRepeat.get(0).getApplyId().longValue()==userApply.getApplyId().longValue()){
    				return "0";
    			}
    		}
    		if(userApply.getTimeapart1() != null){
    			for(UserApply sUserApply : ifStarttimeRepeat){
    				if(sUserApply.getTimeapart1().equals("1")){
    						return "1";
    				}else if(userApply.getTimeapart1().equals("2")){
    					if(sUserApply.getTimeapart1().equals("2")){
    						return "1";
    					}
    				}
        		}
    		}else{
    			return "1";
    		}
    		
    	}
    	else if(ifEndtimeRepeat != null && ifEndtimeRepeat.size()>0){
    		if(ifEndtimeRepeat.size()==1){
    			//查出本身
    			if(ifEndtimeRepeat.get(0).getApplyId().longValue()==userApply.getApplyId().longValue()){
    				return "0";
    			}
    		}
    		if(userApply.getTimeapart2() != null){
    			for(UserApply sUserApply : ifEndtimeRepeat){
    				if(sUserApply.getTimeapart2().equals("1")){
    					if(userApply.getTimeapart1().equals("1")){
    						return "1";
    					}
    				}else if(sUserApply.getTimeapart2().equals("2")){
    					return "1";
    				}
        		}
    		}else{
    			return "1";
    		}
    		
    	}
    	
    	
    	return "0";
    	
    }
    /**
	 * 验证选择的
	 */
    @PostMapping("/ifMarriage")
    @ResponseBody
    public String ifMarriage(Long applyId){
    	List<UserApply> userApply = userApplyService.selectUserApplyById(applyId);
    	if(userApply.get(0).getLeaveType().equals("产假")){
    		return "0";
    	}else{
    		return "1";
    	}
    	
    }
    
    /**
 	 * 验证销假时间是否在原请假时间范围内
 	 */
     @PostMapping("/ifBetween")
     @ResponseBody
     public String ifBetween(UserApply userApply){
    	boolean flag =  userApplyService.ifBetween(userApply);
     	if(flag){
     		return "0";
     	}else{
     		return "1";
     	}
     	
     }
    //----------------------------------------
	
	/**
	 * 申请确认列表——人事
	 */
	@GetMapping("/confirm")
	public String confirm()
	{
	    return prefix + "/confirm";
	}
	
	/**
	 * 申请确认列表——人事
	 */
	@PostMapping("/confirm")
	@ResponseBody
	public AjaxResult confirm(UserApply userApply)
	{		
		
		return toAjax(1);
	}
	
	/**
	 * 查询待确认申请列表
	 */
	@PostMapping("/allList")
	@ResponseBody
	public TableDataInfo list(UserApply userApply)
	{
		startPage();
		//待确认的
        userApply.setConfirmState("0");
        List<UserApplyList> list = userApplyService.selectUserApplyConfirmAsList(userApply);
		return getDataTable(list);
	}
	
	/**
	 * 确认申请
	 */
	@PostMapping("/confirmApply")
	@ResponseBody
	public AjaxResult confirmApply(Long ids)
	{	
		UserApply userApply = new UserApply();
		userApply.setApplyId(ids);
		userApply.setConfirmState("1");
		
		
		return toAjax(userApplyService.updateUserApply(userApply));
	}

}
