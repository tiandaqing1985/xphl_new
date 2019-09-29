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
import com.ruoyi.system.domain.finance.FacHospitalityApply;
import com.ruoyi.system.service.finance.IFacHospitalityApplyService;

/**
 * 招待费申请 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-09-06
 */
@Controller
@RequestMapping("/system/facHospitalityApply")
public class FacHospitalityApplyController extends BaseController
{
    private String prefix = "system/facHospitalityApply";
	
	@Autowired
	private IFacHospitalityApplyService facHospitalityApplyService;
	
	@RequiresPermissions("system:facHospitalityApply:view")
	@GetMapping()
	public String facHospitalityApply()
	{
	    return prefix + "/facHospitalityApply";
	}
	
	/**
	 * 查询招待费申请列表
	 */
	@RequiresPermissions("system:facHospitalityApply:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacHospitalityApply facHospitalityApply)
	{
		startPage();
        List<FacHospitalityApply> list = facHospitalityApplyService.selectFacHospitalityApplyList(facHospitalityApply);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出招待费申请列表
	 */
	@RequiresPermissions("system:facHospitalityApply:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacHospitalityApply facHospitalityApply)
    {
    	List<FacHospitalityApply> list = facHospitalityApplyService.selectFacHospitalityApplyList(facHospitalityApply);
        ExcelUtil<FacHospitalityApply> util = new ExcelUtil<FacHospitalityApply>(FacHospitalityApply.class);
        return util.exportExcel(list, "facHospitalityApply");
    }
	
	/**
	 * 新增招待费申请
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存招待费申请
	 */
	@RequiresPermissions("system:facHospitalityApply:add")
	@Log(title = "招待费申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FacHospitalityApply facHospitalityApply)
	{		
		return toAjax(facHospitalityApplyService.insertFacHospitalityApply(facHospitalityApply));
	}

	/**
	 * 修改招待费申请
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		FacHospitalityApply facHospitalityApply = facHospitalityApplyService.selectFacHospitalityApplyById(id);
		mmap.put("facHospitalityApply", facHospitalityApply);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存招待费申请
	 */
	@RequiresPermissions("system:facHospitalityApply:edit")
	@Log(title = "招待费申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacHospitalityApply facHospitalityApply)
	{		
		return toAjax(facHospitalityApplyService.updateFacHospitalityApply(facHospitalityApply));
	}
	
	/**
	 * 删除招待费申请
	 */
	@RequiresPermissions("system:facHospitalityApply:remove")
	@Log(title = "招待费申请", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(facHospitalityApplyService.deleteFacHospitalityApplyByIds(ids));
	}
	
}
