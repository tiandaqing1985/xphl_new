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
import com.ruoyi.system.domain.XzAssetType;
import com.ruoyi.system.service.IXzAssetTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 资产父类型 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-08-15
 */
@Controller
@RequestMapping("/system/xzAssetType")
public class XzAssetTypeController extends BaseController {
	private String prefix = "system/xzAssetType/type";

	@Autowired
	private IXzAssetTypeService xzAssetTypeService;

	@RequiresPermissions("system:xzAssetType:view")
	@GetMapping()
	public String xzAssetType() {
		return prefix + "/type";
	}

	/**
	 * 查询资产父类型列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(XzAssetType xzAssetType) {
		startPage();
		List<XzAssetType> list = xzAssetTypeService.selectXzAssetTypeList(xzAssetType);
		return getDataTable(list);
	}

	/**
	 * 导出资产父类型列表
	 */
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(XzAssetType xzAssetType) {
		List<XzAssetType> list = xzAssetTypeService.selectXzAssetTypeList(xzAssetType);
		ExcelUtil<XzAssetType> util = new ExcelUtil<XzAssetType>(XzAssetType.class);
		return util.exportExcel(list, "xzAssetType");
	}

	/**
	 * 新增资产父类型
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存资产父类型
	 */
	@Log(title = "资产类型", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(XzAssetType xzAssetType) {
		
		try {
			if(xzAssetTypeService.selectXzAssetTypeByName(xzAssetType.getSort(),xzAssetType.getName())!=null){
				return error("已存在"+xzAssetType.getName()+"资产类型!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		xzAssetType.setCreateTime(new Date());
		xzAssetType.setCreateBy(ShiroUtils.getLoginName());
		String suc = xzAssetTypeService.insertXzAssetType(xzAssetType);
		return success(suc);
	}

	/**
	 * 修改资产父类型
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		XzAssetType xzAssetType = xzAssetTypeService.selectXzAssetTypeById(id);
		mmap.put("xzAssetType", xzAssetType);
		return prefix + "/edit";
	}

	/**
	 * 修改保存资产父类型
	 */
	@Log(title = "资产类型", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(XzAssetType xzAssetType) {
		xzAssetType.setUpdateBy(ShiroUtils.getLoginName());
		xzAssetType.setUpdateTime(new Date());
		return toAjax(xzAssetTypeService.updateXzAssetType(xzAssetType));
	}

	/**
	 * 删除资产父类型
	 */
	@Log(title = "资产类型", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		try {
			return toAjax(xzAssetTypeService.deleteXzAssetTypeByIds(ids));
		} catch (Exception e) {
			return error(e.getMessage());
		}

	}

	/**
	 * 查询资产类型详细
	 */
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long id, ModelMap mmap) {
		/* mmap.put("id", id); */
		mmap.put("type", xzAssetTypeService.selectXzAssetTypeById(id));
		mmap.put("typeList", xzAssetTypeService.selectXzAssetTypeAll());
		return "system/xzAssetType/data/data";
	}

}