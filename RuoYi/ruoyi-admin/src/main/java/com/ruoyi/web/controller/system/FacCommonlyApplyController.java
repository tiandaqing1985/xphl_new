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
import com.ruoyi.system.domain.finance.FacCommonlyApply;
import com.ruoyi.system.service.finance.IFacCommonlyApplyService;

/**
 * 对公常显 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-10-24
 */
@Controller
@RequestMapping("/system/facCommonlyApply")
public class FacCommonlyApplyController extends BaseController
{
    private String prefix = "system/facCommonlyApply";
	
	@Autowired
	private IFacCommonlyApplyService facCommonlyApplyService;
	
	 
	@GetMapping()
	public String facCommonlyApply()
	{
	    return prefix + "/facCommonlyApply";
	}
	
	/**
	 * 查询对公常显列表
	 */ 
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacCommonlyApply facCommonlyApply)
	{
		startPage();
        List<FacCommonlyApply> list = facCommonlyApplyService.selectFacCommonlyApplyList(facCommonlyApply);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出对公常显列表
	 */ 
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacCommonlyApply facCommonlyApply)
    {
    	List<FacCommonlyApply> list = facCommonlyApplyService.selectFacCommonlyApplyList(facCommonlyApply);
        ExcelUtil<FacCommonlyApply> util = new ExcelUtil<FacCommonlyApply>(FacCommonlyApply.class);
        return util.exportExcel(list, "facCommonlyApply");
    }
	
	/**
	 * 新增对公常显
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	 
	
	
	/**
	 * 新增保存对公常显
	 */
	 
	@Log(title = "对公常显", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FacCommonlyApply facCommonlyApply)
	{		
		return toAjax(facCommonlyApplyService.insertFacCommonlyApply(facCommonlyApply));
	}

	/**
	 * 修改对公常显
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		FacCommonlyApply facCommonlyApply = facCommonlyApplyService.selectFacCommonlyApplyById(id);
		mmap.put("facCommonlyApply", facCommonlyApply);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存对公常显
	 */
	 
	@Log(title = "对公常显", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacCommonlyApply facCommonlyApply)
	{		
		return toAjax(facCommonlyApplyService.updateFacCommonlyApply(facCommonlyApply));
	}
	
	/**
	 * 删除对公常显
	 */
	 
	@Log(title = "对公常显", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(facCommonlyApplyService.deleteFacCommonlyApplyByIds(ids));
	}
	
}
