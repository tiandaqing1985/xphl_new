package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.system.domain.finance.FacReimburseApply;
import com.ruoyi.system.service.finance.IFacReimburseApplyService;
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

/**
 * 报销 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-31
 */
@Controller
@RequestMapping("/system/facReimburseApply")
public class FacReimburseApplyController extends BaseController
{
    private String prefix = "system/facReimburseApply";
	
	@Autowired
	private IFacReimburseApplyService facReimburseApplyService;
	
	@RequiresPermissions("system:facReimburseApply:view")
	@GetMapping()
	public String facReimburseApply()
	{
	    return prefix + "/facReimburseApply";
	}
	
	/**
	 * 查询报销列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacReimburseApply facReimburseApply)
	{
		startPage();
        List<FacReimburseApply> list = facReimburseApplyService.selectFacReimburseApplyList(facReimburseApply);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出报销列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacReimburseApply facReimburseApply)
    {
    	List<FacReimburseApply> list = facReimburseApplyService.selectFacReimburseApplyList(facReimburseApply);
        ExcelUtil<FacReimburseApply> util = new ExcelUtil<FacReimburseApply>(FacReimburseApply.class);
        return util.exportExcel(list, "facReimburseApply");
    }
	
	/**
	 * 新增报销
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存报销
	 */
	@Log(title = "报销", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FacReimburseApply facReimburseApply)
	{		
		return facReimburseApplyService.insertFacReimburseApply(facReimburseApply);
	}

	/**
	 * 修改报销
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		FacReimburseApply facReimburseApply = facReimburseApplyService.selectFacReimburseApplyById(id);
		mmap.put("facReimburseApply", facReimburseApply);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存报销
	 */
	@Log(title = "报销", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacReimburseApply facReimburseApply)
	{		
		return toAjax(facReimburseApplyService.updateFacReimburseApply(facReimburseApply));
	}
	
	/**
	 * 删除报销
	 */
	@Log(title = "报销", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(facReimburseApplyService.deleteFacReimburseApplyByIds(ids));
	}
	
}
