package com.ruoyi.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType; 
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.finance.FacUserApproval;
import com.ruoyi.system.service.finance.IFacUserApprovalService; 
/**
 * 财务审批 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-09-27
 */
@Controller
@RequestMapping("/system/facUserApproval")
public class FacUserApprovalController extends BaseController
{
    private String prefix = "system/facUserApproval";
	
	@Autowired
	private IFacUserApprovalService facUserApprovalService;
	
	@RequiresPermissions("system:facUserApproval:view")
	@GetMapping()
	public String facUserApproval()
	{
	    return prefix + "/facUserApproval";
	}
	
	/**
	 * 查询财务审批列表
	 */
	@RequiresPermissions("system:facUserApproval:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacUserApproval facUserApproval)
	{
		startPage();
        List<FacUserApproval> list = facUserApprovalService.selectFacUserApprovalList(facUserApproval);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出财务审批列表
	 */
	@RequiresPermissions("system:facUserApproval:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacUserApproval facUserApproval)
    {
    	List<FacUserApproval> list = facUserApprovalService.selectFacUserApprovalList(facUserApproval);
        ExcelUtil<FacUserApproval> util = new ExcelUtil<FacUserApproval>(FacUserApproval.class);
        return util.exportExcel(list, "facUserApproval");
    }
	
	/**
	 * 新增财务审批
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存财务审批
	 */
	@RequiresPermissions("system:facUserApproval:add")
	@Log(title = "财务审批", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FacUserApproval facUserApproval)
	{		
		return toAjax(facUserApprovalService.insertFacUserApproval(facUserApproval));
	}

	/**
	 * 修改财务审批
	 */
	@GetMapping("/edit/{approvalId}")
	public String edit(@PathVariable("approvalId") Long approvalId, ModelMap mmap)
	{
		FacUserApproval facUserApproval = facUserApprovalService.selectFacUserApprovalById(approvalId);
		mmap.put("facUserApproval", facUserApproval);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存财务审批
	 */
	@RequiresPermissions("system:facUserApproval:edit")
	@Log(title = "财务审批", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacUserApproval facUserApproval)
	{		
		return toAjax(facUserApprovalService.updateFacUserApproval(facUserApproval));
	}
	
	/**
	 * 删除财务审批
	 */
	@RequiresPermissions("system:facUserApproval:remove")
	@Log(title = "财务审批", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(facUserApprovalService.deleteFacUserApprovalByIds(ids));
	}
	
	
	
	/**
	 * 已审批
	 */
	@GetMapping("/endfacUserApproval")
	public String myApproval( )
	{ 
	    return prefix + "/endfacUserApproval";
	}
	/**
	 * 已审批
	 */
	@PostMapping("/endfacUserApproval")
	@ResponseBody
	public TableDataInfo endfacUserApproval(FacUserApproval facUserApproval)
	{
		startPage();
        List<FacUserApproval> list = facUserApprovalService.selectFacUserApprovalList(facUserApproval);
		return getDataTable(list);
	}
	 
}
