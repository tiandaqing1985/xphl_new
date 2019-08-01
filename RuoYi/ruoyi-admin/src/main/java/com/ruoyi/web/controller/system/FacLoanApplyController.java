package com.ruoyi.web.controller.system;


import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.finance.FacLoanApply;
import com.ruoyi.system.service.finance.IFacLoanApplyService;
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

import java.util.List;


/**
 * 借款申请 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-30
 */
@Controller
@RequestMapping("/system/facLoanApply")
public class FacLoanApplyController extends BaseController
{
    private String prefix = "system/facLoanApply";
	
	@Autowired
	private IFacLoanApplyService facLoanApplyService;
	
	@GetMapping()
	public String facLoanApply()
	{
	    return prefix + "/facLoanApply";
	}
	
	/**
	 * 查询借款申请列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacLoanApply facLoanApply)
	{
		startPage();
        List<FacLoanApply> list = facLoanApplyService.selectFacLoanApplyList(facLoanApply);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出借款申请列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacLoanApply facLoanApply)
    {
    	List<FacLoanApply> list = facLoanApplyService.selectFacLoanApplyList(facLoanApply);
        ExcelUtil<FacLoanApply> util = new ExcelUtil<FacLoanApply>(FacLoanApply.class);
        return util.exportExcel(list, "facLoanApply");
    }
	
	/**
	 * 新增借款申请
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存借款申请
	 */
	@Log(title = "借款申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FacLoanApply facLoanApply)
	{		
		return AjaxResult.warn("ss");
	}

	/**
	 * 修改借款申请
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		FacLoanApply facLoanApply = facLoanApplyService.selectFacLoanApplyById(id);
		mmap.put("facLoanApply", facLoanApply);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存借款申请
	 */
	@Log(title = "借款申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacLoanApply facLoanApply)
	{		
		return toAjax(facLoanApplyService.updateFacLoanApply(facLoanApply));
	}
	
	/**
	 * 删除借款申请
	 */
	@Log(title = "借款申请", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(facLoanApplyService.deleteFacLoanApplyByIds(ids));
	}
	
}
