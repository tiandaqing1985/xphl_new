package com.ruoyi.web.controller.system;

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
import com.ruoyi.system.domain.QueryConditions;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.UserApply;
import com.ruoyi.system.domain.UserApproval;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.IUserApplyService;
import com.ruoyi.system.service.IUserApprovalService;
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
	private ISysRoleService iSysRoleService;
	
	@RequiresPermissions("system:userApproval:view")
	@GetMapping()
	public String userApproval()
	{
//	    return prefix + "/userApproval";
	    return prefix + "/tabs_panels";

	}
	
	
	@GetMapping("/approvalModer")
	public String approvalModer(String ids,Model m)
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
		SysUser user2 = new SysUser();
		user2.setRoleId(3L);
		user2.setArea(user.getArea());
		Long personnelId = iSysRoleService.selectUserIdByRoleId(user2);//查询人事id
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
		boolean showFlag2 = false;
		if("1".equals(ShiroUtils.getUserId())){//管理员
			showFlag = true;
		}
		SysUser user = iSysUserService.selectUserById(ShiroUtils.getUserId());
		SysUser user2 = new SysUser();
		user2.setRoleId(3L);
		user2.setArea(user.getArea());
		Long personnelId = iSysRoleService.selectUserIdByRoleId(user2);//查询人事id
		if(personnelId.equals(ShiroUtils.getUserId())){
			showFlag = true;
		}
		user2.setRoleId(6L);// 人事总监
		user2.setArea("1");
		Long hrId = iSysRoleService.selectUserIdByRoleId(user2);// 人事总监id
		if(hrId.equals(ShiroUtils.getUserId())){
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
		m.addAttribute("approvalId", approvalId);
		
		if(approval.getApproverId().equals(ShiroUtils.getUserId()) && approval.getApprovalState().equals("3")){
			showFlag2 = true;
		}
		m.addAttribute("showFlag2", showFlag2);
	    return prefix + "/toView";
	}
	
	/**
	 * 同意申请
	 */
	@PostMapping("/agree")
	@ResponseBody
	public AjaxResult agree(String ids)
	{	
		userApprovalService.agree(ids);
		return toAjax(1);
	}
	
	/**
	 * 驳回申请
	 */
	@PostMapping("/reject")
	@ResponseBody
	public AjaxResult reject(String ids, String remark)
	{	
		userApprovalService.reject(ids, remark);					
		return toAjax(1);
	}
}
