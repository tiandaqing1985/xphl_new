package com.ruoyi.web.controller.system;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
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
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.XzAsstes;
import com.ruoyi.system.domain.XzOfficeAsstes;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.IXzAssetDataService;
import com.ruoyi.system.service.IXzAssetTypeService;
import com.ruoyi.system.service.IXzAsstesService;
import com.ruoyi.system.service.IXzOfficeAsstesService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 办公资产记录 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-09-03
 */
@Controller
@RequestMapping("/system/xzOfficeAsstes")
public class XzOfficeAsstesController extends BaseController {
	private String prefix = "system/xzOfficeAsstes";

	@Autowired
	private IXzOfficeAsstesService xzOfficeAsstesService;

	@Autowired
	private IXzAsstesService xzAsstesService;

	@Autowired
	private IXzAssetTypeService xzAssetTypeService;

	@Autowired
	private IXzAssetDataService xzAssetDataService;
	
	@Autowired
	private ISysDeptService sysDeptService;

	@RequiresPermissions("system:xzOfficeAsstes:view")
	@GetMapping()
	public String xzOfficeAsstes() {
		return prefix + "/xzOfficeAsstes";
	}

	/**
	 * 查询办公资产记录列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(XzOfficeAsstes xzOfficeAsstes) {
		SysDept dept = sysDeptService.selectDeptById(ShiroUtils.getSysUser().getDeptId());

		if(ShiroUtils.getUserId()==1 || ShiroUtils.getUserId()==103 || ShiroUtils.getSysUser().getUserName().equals(dept.getLeader())){ //超级管理员 和 任总 行政部门leader看所有数据
			xzOfficeAsstes.setRegion(xzOfficeAsstes.getRegion());
		}else{
			String region=ShiroUtils.getSysUser().getArea();
			if(xzOfficeAsstes.getRegion()==null || xzOfficeAsstes.getRegion().equals("")){
				xzOfficeAsstes.setRegion(region);
			}
		}
		startPage();
		List<XzOfficeAsstes> list = xzOfficeAsstesService.selectXzOfficeAsstesList(xzOfficeAsstes);
		return getDataTable(list);
	}

	/**
	 * 导出办公资产记录列表
	 */
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(XzOfficeAsstes xzOfficeAsstes) {
		List<XzOfficeAsstes> list = xzOfficeAsstesService.selectXzOfficeAsstesList(xzOfficeAsstes);
		ExcelUtil<XzOfficeAsstes> util = new ExcelUtil<XzOfficeAsstes>(XzOfficeAsstes.class);
		return util.exportExcel(list, "xzOfficeAsstes");
	}

	/**
	 * 新增办公资产记录
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap) {
		// 获取办公用品资产父级类型
		mmap.put("typeList", xzAssetTypeService.selectXzAssetTypeByStaAll());
		return prefix + "/add";
	}

	/**
	 * 新增保存办公资产记录
	 */
	@Log(title = "办公资产记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(XzOfficeAsstes xzOfficeAsstes) {
		Date date = new Date();
		xzOfficeAsstes.setCreateBy(ShiroUtils.getSysUser().getUserId().toString());
		xzOfficeAsstes.setCreateTime(date);
		// 新增时初始值:资产状态-未入库1、资产使用状态-无1、提交方式-保存1
		xzOfficeAsstes.setSubmitType("1");
		return toAjax(xzOfficeAsstesService.insertXzOfficeAsstes(xzOfficeAsstes));
	}

	/**
	 * 新增提交办公资产记录
	 */
	@Log(title = "办公资产记录", businessType = BusinessType.INSERT)
	@PostMapping("/addSub")
	@ResponseBody
	public AjaxResult addSubSave(XzOfficeAsstes xzOfficeAsstes) {
		Date date = new Date();
		xzOfficeAsstes.setCreateBy(ShiroUtils.getSysUser().getUserId().toString());
		xzOfficeAsstes.setCreateTime(date);
		xzOfficeAsstes.setSubBy(ShiroUtils.getUserId().toString());
		xzOfficeAsstes.setSubTime(new Date());
		// 新增时初始值:资产状态-未入库1、资产使用状态-无1、提交方式-保存1
		xzOfficeAsstes.setSubmitType("2");
		XzAsstes xzAsstes = new XzAsstes();
		xzAsstes.setAssetsStatus("2");
		xzAsstes.setUseStatus("1");
		xzAsstes.setSubmitType(xzOfficeAsstes.getSubmitType());
		xzAsstes.setSort("2");
		try {
			BeanUtils.copyProperties(xzAsstes, xzOfficeAsstes);

			System.out.println(xzAsstes + "*******************************");
			xzAsstesService.insertXzAsstes(xzAsstes);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return toAjax(xzOfficeAsstesService.insertXzOfficeAsstes(xzOfficeAsstes));
	}

	/**
	 * 修改办公资产记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		XzOfficeAsstes xzOfficeAsstes = xzOfficeAsstesService.selectXzOfficeAsstesById(id);
		mmap.put("xzOfficeAsstes", xzOfficeAsstes);
		// 获取办公用品资产父级类型
		mmap.put("typeList", xzAssetTypeService.selectXzAssetTypeByStaAll());
		mmap.put("dataList", xzAssetDataService.selectXzAssetDataByParentId(xzOfficeAsstes.getAssetsType()));
		return prefix + "/edit";
	}

	/**
	 * 修改保存办公资产记录
	 */
	@Log(title = "办公资产记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(XzOfficeAsstes xzOfficeAsstes) {
		xzOfficeAsstes.setUpdateBy(ShiroUtils.getUserId().toString());
		xzOfficeAsstes.setUpdateTime(new Date());
		return toAjax(xzOfficeAsstesService.updateXzOfficeAsstes(xzOfficeAsstes));
	}

	/**
	 * 修改提交办公资产记录
	 */
	@Log(title = "办公资产记录", businessType = BusinessType.UPDATE)
	@PostMapping("/editSub")
	@ResponseBody
	public AjaxResult editSubSave(XzOfficeAsstes xzOfficeAsstes) {
		xzOfficeAsstes.setUpdateBy(ShiroUtils.getUserId().toString());
		xzOfficeAsstes.setUpdateTime(new Date());
		xzOfficeAsstes.setSubBy(ShiroUtils.getUserId().toString());
		xzOfficeAsstes.setSubTime(new Date());
		if (("1").equals(xzOfficeAsstes.getSubmitType())) {
			xzOfficeAsstes.setSubmitType("2");

			XzAsstes xzAsstes = new XzAsstes();
			xzAsstes.setAssetsStatus("2");
			xzAsstes.setUseStatus("1");
			xzAsstes.setSubmitType(xzOfficeAsstes.getSubmitType());
			xzAsstes.setSort("2");
			try {
				BeanUtils.copyProperties(xzAsstes, xzOfficeAsstes);

			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			xzAsstesService.insertXzAsstes(xzAsstes);

		}
		return toAjax(xzOfficeAsstesService.updateXzOfficeAsstes(xzOfficeAsstes));
	}

	/**
	 * 删除办公资产记录
	 */
	@Log(title = "办公资产记录", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(Long id) {
		return toAjax(xzOfficeAsstesService.deleteXzOfficeAsstesByIds(id.toString()));
	}
	
	
	
	/**
	 * 单个提交
	 */
	@Log(title = "办公资产", businessType = BusinessType.INSERT)
	@PostMapping("/toSubmit")
	@ResponseBody
	public AjaxResult toSubmit(Long id) {
		XzOfficeAsstes xzOfficeAsstes = xzOfficeAsstesService.selectXzOfficeAsstesById(id);
		xzOfficeAsstes.setSubBy(ShiroUtils.getUserId().toString());
		xzOfficeAsstes.setSubTime(new Date());
		// 新增时初始值:资产状态-在库2、资产使用状态-无1、提交方式-提交2
		if (("1").equals(xzOfficeAsstes.getSubmitType())) {
			xzOfficeAsstes.setSubmitType("2");

			XzAsstes xzAsstes = new XzAsstes();
			xzAsstes.setAssetsStatus("2");
			xzAsstes.setUseStatus("1");
			xzAsstes.setSubmitType(xzOfficeAsstes.getSubmitType());
			xzAsstes.setSort("2");
			try {
				BeanUtils.copyProperties(xzAsstes, xzOfficeAsstes);

			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			xzAsstesService.insertXzAsstes(xzAsstes);

		}
		return toAjax(xzOfficeAsstesService.updateXzOfficeAsstes(xzOfficeAsstes));
	}


}
