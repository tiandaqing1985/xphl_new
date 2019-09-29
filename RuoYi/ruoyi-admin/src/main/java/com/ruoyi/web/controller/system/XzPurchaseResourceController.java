package com.ruoyi.web.controller.system;

import java.util.Date;
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
import com.ruoyi.system.domain.XzPurchaseResource;
import com.ruoyi.system.service.IXzPurchaseResourceService;
import com.ruoyi.system.service.IXzResourceTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 采购资源 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-09-18
 */
@Controller
@RequestMapping("/system/xzPurchaseResource")
public class XzPurchaseResourceController extends BaseController
{
    private String prefix = "system/xzPurchaseResource";
	
	@Autowired
	private IXzPurchaseResourceService xzPurchaseResourceService;
	
	@Autowired
	private IXzResourceTypeService xzResourceTypeService;
	
	@RequiresPermissions("system:xzPurchaseResource:view")
	@GetMapping()
	public String xzPurchaseResource()
	{
	    return prefix + "/xzPurchaseResource";
	}
	
	/**
	 * 查询采购资源列表
	 */
	@PostMapping("/toList")
	@ResponseBody
	public TableDataInfo toList(String code)
	{
		startPage();
        List<XzPurchaseResource> list = xzPurchaseResourceService.selectXzPurchaseResourceListByCode(code);
		return getDataTable(list);
	}
	
	/**
	 * 查询采购资源列表
	 */
	@PostMapping("/detailList/{code}")
	@ResponseBody
	public TableDataInfo detailList(@PathVariable("code")String code)
	{
		startPage();
        List<XzPurchaseResource> list = xzPurchaseResourceService.selectXzPurchaseResourceListByCode(code);
		return getDataTable(list);
	}
	
	/**
	 * 补录采购信息
	 */
	@GetMapping("/toEdit/{id}")
	public String toEdit(@PathVariable("id") Long id, ModelMap mmap)
	{
		XzPurchaseResource xzPurchaseResource = xzPurchaseResourceService.selectXzPurchaseResourceById(id);
		mmap.put("xzPurchaseResource", xzPurchaseResource);
	    return "system/xzPurchaseResourceApply/toEdit";
	}
	
	/**
	 * 补录保存采购资源
	 */
	@Log(title = "采购资源", businessType = BusinessType.UPDATE)
	@PostMapping("/toEdit")
	@ResponseBody
	public AjaxResult toEditSave(XzPurchaseResource xzPurchaseResource)
	{		
		xzPurchaseResource.setFloatPrice(xzPurchaseResource.getActualPrice()-xzPurchaseResource.getSumPrice());
		xzPurchaseResource.setUpdateBy(ShiroUtils.getUserId().toString());
		xzPurchaseResource.setUpdateTime(new Date());
		return toAjax(xzPurchaseResourceService.updateXzPurchaseResource(xzPurchaseResource));
	}
	
	
	/**
	 * 查询采购资源列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(XzPurchaseResource xzPurchaseResource)
	{
		startPage();
		List<XzPurchaseResource> list = xzPurchaseResourceService.selectXzPurchaseResourceList(xzPurchaseResource);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出采购资源列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XzPurchaseResource xzPurchaseResource)
    {
    	List<XzPurchaseResource> list = xzPurchaseResourceService.selectXzPurchaseResourceList(xzPurchaseResource);
        ExcelUtil<XzPurchaseResource> util = new ExcelUtil<XzPurchaseResource>(XzPurchaseResource.class);
        return util.exportExcel(list, "xzPurchaseResource");
    }
	
	/**
	 * 新增采购资源
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存采购资源
	 */
	@Log(title = "采购资源", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(XzPurchaseResource xzPurchaseResource)
	{		
		xzPurchaseResource.setCreateBy(ShiroUtils.getUserId().toString());
		xzPurchaseResource.setCreateTime(new Date());
		return toAjax(xzPurchaseResourceService.insertXzPurchaseResource(xzPurchaseResource));
	}

	/**
	 * 修改采购资源
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		
		XzPurchaseResource xzPurchaseResource = xzPurchaseResourceService.selectXzPurchaseResourceById(id);
		mmap.put("xzPurchaseResource", xzPurchaseResource);
		mmap.put("typeList", xzResourceTypeService.selectXzResourceTypeByAll());
		mmap.put("dataList", xzResourceTypeService.xzResourceDataByParentId(xzPurchaseResource.getResourceParentId()));
		mmap.put("createByName",ShiroUtils.getSysUser().getUserName());
	    return "system/xzPurchaseResourceApply/edit";
	}
	
	/**
	 * 修改保存采购资源
	 */
	@Log(title = "采购资源", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(XzPurchaseResource xzPurchaseResource)
	{		
		xzPurchaseResource.setUpdateBy(ShiroUtils.getUserId().toString());
		xzPurchaseResource.setUpdateTime(new Date());
		return toAjax(xzPurchaseResourceService.updateXzPurchaseResource(xzPurchaseResource));
	}
	
	
	
	/**
	 * 删除采购资源
	 */
	@Log(title = "采购资源", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(xzPurchaseResourceService.deleteXzPurchaseResourceByIds(ids));
	}
	
}
