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
import com.ruoyi.system.domain.finance.FacCollectDetaApply;
import com.ruoyi.system.service.finance.IFacCollectDetaApplyService;

/**
 * 团建明细 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-09-04
 */
@Controller
@RequestMapping("/system/facCollectDetaApply")
public class FacCollectDetaApplyController extends BaseController
{
    private String prefix = "system/facCollectDetaApply";
	
	@Autowired
	private IFacCollectDetaApplyService facCollectDetaApplyService;
	
	@RequiresPermissions("system:facCollectDetaApply:view")
	@GetMapping()
	public String facCollectDetaApply()
	{
	    return prefix + "/facCollectDetaApply";
	}
	
	/**
	 * 查询团建明细列表
	 */

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacCollectDetaApply facCollectDetaApply)
	{
		startPage();
        List<FacCollectDetaApply> list = facCollectDetaApplyService.selectFacCollectDetaApplyList(facCollectDetaApply);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出团建明细列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacCollectDetaApply facCollectDetaApply)
    {
    	List<FacCollectDetaApply> list = facCollectDetaApplyService.selectFacCollectDetaApplyList(facCollectDetaApply);
        ExcelUtil<FacCollectDetaApply> util = new ExcelUtil<FacCollectDetaApply>(FacCollectDetaApply.class);
        return util.exportExcel(list, "facCollectDetaApply");
    }
	
	/**
	 * 新增团建明细
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存团建明细
	 */
	@Log(title = "团建明细", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FacCollectDetaApply facCollectDetaApply)
	{		
		return toAjax(facCollectDetaApplyService.insertFacCollectDetaApply(facCollectDetaApply));
	}

	/**
	 * 修改团建明细
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		FacCollectDetaApply facCollectDetaApply = facCollectDetaApplyService.selectFacCollectDetaApplyById(id);
		mmap.put("facCollectDetaApply", facCollectDetaApply);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存团建明细
	 */
	@Log(title = "团建明细", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacCollectDetaApply facCollectDetaApply)
	{		
		return toAjax(facCollectDetaApplyService.updateFacCollectDetaApply(facCollectDetaApply));
	}
	
	/**
	 * 删除团建明细
	 */
	@Log(title = "团建明细", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(facCollectDetaApplyService.deleteFacCollectDetaApplyByIds(ids));
	}
	
}
