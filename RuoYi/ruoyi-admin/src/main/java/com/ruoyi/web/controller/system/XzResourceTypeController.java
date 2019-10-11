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
import com.ruoyi.system.domain.XzResourceType;
import com.ruoyi.system.service.IXzResourceTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 采购类型 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-09-16
 */
@Controller
@RequestMapping("/system/xzResourceType")
public class XzResourceTypeController extends BaseController
{
    private String prefix = "system/xzResourceType";
	
	@Autowired
	private IXzResourceTypeService xzResourceTypeService;
	
	@RequiresPermissions("system:xzResourceType:view")
	@GetMapping()
	public String xzResourceType()
	{
	    return prefix + "/type/xzResourceType";
	}
	
	/**
	 * 查询采购类型列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(XzResourceType xzResourceType)
	{
		startPage();
        List<XzResourceType> list = xzResourceTypeService.selectXzResourceTypeList(xzResourceType);
		return getDataTable(list);
	}
	
	/**
	 * 查询采购类型列表
	 */
	@PostMapping("/DataList")
	@ResponseBody
	public TableDataInfo DataList(XzResourceType xzResourceType)
	{
		startPage();
        List<XzResourceType> list = xzResourceTypeService.selectXzResourceDataList(xzResourceType);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出采购类型列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XzResourceType xzResourceType)
    {
    	List<XzResourceType> list = xzResourceTypeService.selectXzResourceTypeList(xzResourceType);
        ExcelUtil<XzResourceType> util = new ExcelUtil<XzResourceType>(XzResourceType.class);
        return util.exportExcel(list, "xzResourceType");
    }
	
	/**
	 * 新增采购类型
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/type/add";
	}
	
	/**
	 * 新增采购类型
	 */
	@GetMapping("/add/{parentId}")
	public String addData(@PathVariable("parentId") Long parentId, ModelMap mmap)
	{
		XzResourceType xzResourceType = xzResourceTypeService.selectXzResourceTypeById(parentId);
		mmap.put("xzResourceType", xzResourceType);
	    return prefix + "/data/add";
	}
	
	
	/**
	 * 新增保存采购类型
	 */
	@Log(title = "采购类型", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(XzResourceType xzResourceType)
	{		
		
		try {
			if(xzResourceType.getParentId()!=null){
				xzResourceType.setSourceType(xzResourceTypeService.selectXzResourceTypeById(xzResourceType.getParentId()).getSourceType());
				if(xzResourceTypeService.selectXzResourceTypeByName(xzResourceType.getSourceType(),xzResourceType.getName())>0){
					return error("已存在"+xzResourceType.getName()+"资源类型，请重新输入!");
				}
			}else{
				if(xzResourceTypeService.selectXzResourceTypeByName(xzResourceType.getSourceType(),xzResourceType.getName())>0){
					return error("已存在"+xzResourceType.getName()+"资源类型，请重新输入!");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error(e.getMessage());
		}
		xzResourceType.setCreateBy(ShiroUtils.getUserId().toString());
		xzResourceType.setCreateTime(new Date());
		return toAjax(xzResourceTypeService.insertXzResourceType(xzResourceType));
	}

	/**
	 * 修改采购类型
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		XzResourceType xzResourceType = xzResourceTypeService.selectXzResourceTypeById(id);
		mmap.put("xzResourceType", xzResourceType);
	    return prefix + "/type/edit";
	}
	/**
	 * 修改采购类型
	 */
	@GetMapping("/editData/{id}")
	public String editData(@PathVariable("id") Long id, ModelMap mmap)
	{
		XzResourceType xzResourceType = xzResourceTypeService.selectXzResourceTypeById(id);
		mmap.put("xzResourceType", xzResourceType);
		return prefix + "/data/edit";
	}
	
	/**
	 * 修改保存采购类型
	 */
	@Log(title = "采购类型", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(XzResourceType xzResourceType)
	{		
		xzResourceType.setUpdateBy(ShiroUtils.getUserId().toString());
		xzResourceType.setUpdateTime(new Date());
		return toAjax(xzResourceTypeService.updateXzResourceType(xzResourceType));
	}
	
	/**
	 * 删除采购类型
	 */
	@Log(title = "采购类型", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(Long id)
	{		
		return toAjax(xzResourceTypeService.deleteXzResourceTypeByIds(id.toString()));
	}
	
	/**
	 * 查询资产类型详细
	 */
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long id, ModelMap mmap) {
		/* mmap.put("id", id); */
		mmap.put("type", xzResourceTypeService.selectXzResourceTypeById(id));
		mmap.put("typeList", xzResourceTypeService.selectXzResourceTypeByAll());
		return "system/xzResourceType/data/xzResourceData";
	}
	
}
