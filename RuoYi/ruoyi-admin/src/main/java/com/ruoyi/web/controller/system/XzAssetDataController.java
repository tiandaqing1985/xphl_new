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
import com.ruoyi.system.domain.XzAssetData;
import com.ruoyi.system.domain.XzAssetType;
import com.ruoyi.system.service.IXzAssetDataService;
import com.ruoyi.system.service.IXzAssetTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 资产子类型 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-08-15
 */
@Controller
@RequestMapping("/system/xzAssetData")
public class XzAssetDataController extends BaseController
{
    private String prefix = "system/xzAssetType/data";
	
	@Autowired
	private IXzAssetDataService xzAssetDataService;
	
	@Autowired
	private IXzAssetTypeService xzAssetTypeService;
	
	@RequiresPermissions("system:xzAssetData:view")
	@GetMapping()
	public String xzAssetData()
	{
	    return prefix + "/data";
	}
	
	/**
	 * 查询资产子类型列表
	 */
	@RequiresPermissions("system:xzAssetData:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(XzAssetData xzAssetData)
	{
		startPage();
        List<XzAssetData> list = xzAssetDataService.selectXzAssetDataList(xzAssetData);
		return getDataTable(list);
	}

	
	
	/**
	 * 导出资产子类型列表
	 */
	@RequiresPermissions("system:xzAssetData:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XzAssetData xzAssetData)
    {
    	List<XzAssetData> list = xzAssetDataService.selectXzAssetDataList(xzAssetData);
        ExcelUtil<XzAssetData> util = new ExcelUtil<XzAssetData>(XzAssetData.class);
        return util.exportExcel(list, "xzAssetData");
    }
	
	/**
	 * 
	 * parentId 父资产类型Id
	 * 新增资产子类型
	 */
	@GetMapping("/add/{parentId}")
	public String add(@PathVariable("parentId") Long parentId,ModelMap mmap)
	{
		XzAssetType xzAssetType = xzAssetTypeService.selectXzAssetTypeById(parentId);
		mmap.put("xzAssetType", xzAssetType);
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资产子类型
	 */
	@RequiresPermissions("system:xzAssetData:add")
	@Log(title = "资产子类型", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(XzAssetData xzAssetData)
	{		
		try {
			if (xzAssetDataService.selectXzAssetDataByName(xzAssetData)>0){
				return error("已存在"+xzAssetData.getName()+"资产类型!");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		xzAssetData.setCreateBy(ShiroUtils.getLoginName());
		xzAssetData.setCreateTime(new Date());
		return toAjax(xzAssetDataService.insertXzAssetData(xzAssetData));
	}

	/**
	 * 修改资产子类型
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		XzAssetData xzAssetData = xzAssetDataService.selectXzAssetDataById(id);
		XzAssetType xzAssetType = xzAssetTypeService.selectXzAssetTypeById(xzAssetData.getParentId());
		mmap.put("xzAssetType", xzAssetType);
		mmap.put("xzAssetData", xzAssetData);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资产子类型
	 */
	@RequiresPermissions("system:xzAssetData:edit")
	@Log(title = "资产子类型", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(XzAssetData xzAssetData)
	{		
		xzAssetData.setUpdateBy(ShiroUtils.getLoginName());
		xzAssetData.setUpdateTime(new Date());
		return toAjax(xzAssetDataService.updateXzAssetData(xzAssetData));
	}
	
	/**
	 * 删除资产子类型
	 */
	@RequiresPermissions("system:xzAssetData:remove")
	@Log(title = "资产子类型", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(xzAssetDataService.deleteXzAssetDataByIds(ids));
	}
}
	