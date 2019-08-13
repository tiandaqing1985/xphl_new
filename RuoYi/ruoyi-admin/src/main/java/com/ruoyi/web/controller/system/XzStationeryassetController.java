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
import com.ruoyi.system.domain.XzStationeryasset;
import com.ruoyi.system.service.IXzStationeryassetService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 办公用品 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-08-12
 */
@Controller
@RequestMapping("/system/xzStationeryasset")
public class XzStationeryassetController extends BaseController
{
    private String prefix = "system/xzStationeryasset";
	
	@Autowired
	private IXzStationeryassetService xzStationeryassetService;
	
	@RequiresPermissions("system:xzStationeryasset:view")
	@GetMapping()
	public String xzStationeryasset()
	{
	    return prefix + "/xzStationeryasset";
	}
	
	/**
	 * 查询办公用品列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(XzStationeryasset xzStationeryasset)
	{
		startPage();
        List<XzStationeryasset> list = xzStationeryassetService.selectXzStationeryassetList(xzStationeryasset);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出办公用品列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XzStationeryasset xzStationeryasset)
    {
    	List<XzStationeryasset> list = xzStationeryassetService.selectXzStationeryassetList(xzStationeryasset);
        ExcelUtil<XzStationeryasset> util = new ExcelUtil<XzStationeryasset>(XzStationeryasset.class);
        return util.exportExcel(list, "xzStationeryasset");
    }
	
	/**
	 * 新增办公用品
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存办公用品
	 */
	@Log(title = "办公用品", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(XzStationeryasset xzStationeryasset)
	{		
		return toAjax(xzStationeryassetService.insertXzStationeryasset(xzStationeryasset));
	}

	/**
	 * 修改办公用品
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		XzStationeryasset xzStationeryasset = xzStationeryassetService.selectXzStationeryassetById(id);
		mmap.put("xzStationeryasset", xzStationeryasset);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存办公用品
	 */
	@Log(title = "办公用品", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(XzStationeryasset xzStationeryasset)
	{		
		return toAjax(xzStationeryassetService.updateXzStationeryasset(xzStationeryasset));
	}
	
	/**
	 * 删除办公用品
	 */
	@Log(title = "办公用品", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(xzStationeryassetService.deleteXzStationeryassetByIds(ids));
	}
	
}
