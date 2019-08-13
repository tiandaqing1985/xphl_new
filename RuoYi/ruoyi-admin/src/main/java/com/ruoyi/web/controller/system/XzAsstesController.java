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
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.XzAsstes;
import com.ruoyi.system.service.IXzAsstesService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 资产 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-08-02
 */
@Controller
@RequestMapping("/system/xzAsstes")
public class XzAsstesController extends BaseController {
	private String prefix = "system/xzAsstes";

	@Autowired
	private IXzAsstesService xzAsstesService;

	@RequiresPermissions("system:xzAsstes:view")
	@GetMapping()
	public String xzAsstes() {
		return prefix + "/xzAsstes";
	}

	@GetMapping("/xzAsstesHand")
	public String xzAsstesHand() {
		return prefix + "/xzAsstesHand";
	}

	/**
	 * 查询资产列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(XzAsstes xzAsstes) {
		startPage();
		List<XzAsstes> list = xzAsstesService.selectXzAsstesList(xzAsstes);
		return getDataTable(list);
	}

	/**
	 * 导出资产列表
	 */
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(XzAsstes xzAsstes) {
		List<XzAsstes> list = xzAsstesService.selectXzAsstesList(xzAsstes);
		ExcelUtil<XzAsstes> util = new ExcelUtil<XzAsstes>(XzAsstes.class);
		return util.exportExcel(list, "xzAsstes");
	}

	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
		ExcelUtil<XzAsstes> util = new ExcelUtil<XzAsstes>(XzAsstes.class);
		List<XzAsstes> assetsList = util.importExcel(file.getInputStream(), 0, 1);
		String operName = ShiroUtils.getSysUser().getLoginName();
		String message = xzAsstesService.importXzAsstes(assetsList, updateSupport, operName);
		return AjaxResult.success(message);
	}

	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<XzAsstes> util = new ExcelUtil<XzAsstes>(XzAsstes.class);
		return util.importTemplateExcel("资产数据");
	}

	/**
	 * 新增资产
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存资产
	 */
	@Log(title = "资产", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(XzAsstes xzAsstes) {
		Date date = new Date();
		xzAsstes.setCreateBy(ShiroUtils.getSysUser().getUserName());
		xzAsstes.setCreateTime(date);
		// 新增时初始值:资产状态-未入库7、资产使用状态-无7、提交方式-保存1
		xzAsstes.setAssetsStatus("7");
		xzAsstes.setUseStatus("7");
		xzAsstes.setSubmitType("1");
		return toAjax(xzAsstesService.insertXzAsstes(xzAsstes));
	}

	/**
	 * 新增提交资产
	 */
	@Log(title = "资产", businessType = BusinessType.INSERT)
	@PostMapping("/addSub")
	@ResponseBody
	public AjaxResult addSubSave(XzAsstes xzAsstes) {
		Date date = new Date();
		xzAsstes.setCreateBy(ShiroUtils.getSysUser().getUserName());
		xzAsstes.setCreateTime(date);
		// 新增时初始值:资产状态-在库1、资产使用状态-未分配1、提交方式-提交2
		xzAsstes.setAssetsStatus("1");
		xzAsstes.setUseStatus("1");
		xzAsstes.setSubmitType("2");
		return toAjax(xzAsstesService.insertXzAsstes(xzAsstes));
	}

	/**
	 * 修改资产
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		XzAsstes xzAsstes = xzAsstesService.selectXzAsstesById(id);
		mmap.put("xzAsstes", xzAsstes);
		return prefix + "/edit";
	}

	/**
	 * 修改保存资产
	 */
	@Log(title = "资产", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(XzAsstes xzAsstes) {
		Date date = new Date();
		xzAsstes.setUpdateBy(ShiroUtils.getSysUser().getUserName());
		xzAsstes.setUpdateTime(date);
		xzAsstes.setSubmitType("1");
		return toAjax(xzAsstesService.updateXzAsstes(xzAsstes));
	}

	/**
	 * 修改提交资产
	 */
	@Log(title = "资产", businessType = BusinessType.UPDATE)
	@PostMapping("/editSub")
	@ResponseBody
	public AjaxResult editSubSave(XzAsstes xzAsstes) {
		Date date = new Date();
		xzAsstes.setSubBy(ShiroUtils.getSysUser().getUserName());
		xzAsstes.setSubTime(date);
		xzAsstes.setUpdateBy(ShiroUtils.getSysUser().getUserName());
		xzAsstes.setUpdateTime(date);
		// 保存状态下修改提交:资产状态-在库1、资产使用状态-未分配1、提交方式-提交2
		if(("1").equals(xzAsstes.getAssetsStatus())){
			xzAsstes.setAssetsStatus("1");
			xzAsstes.setUseStatus("1");
			xzAsstes.setSubmitType("1");
		//办公用品数量增加
			
		}else if(("2").equals(xzAsstes.getAssetsStatus())){
			xzAsstes.setAssetsStatus("1");
			xzAsstes.setUseStatus("1");
			xzAsstes.setSubmitType("2");
			//判断是否分配，分配后修改办
		}
		
		return toAjax(xzAsstesService.updateXzAsstes(xzAsstes));
	}

	/**
	 * 资产详情
	 */
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long id, ModelMap mmap) {
		XzAsstes xzAsstes = xzAsstesService.selectXzAsstesById(id);
		mmap.put("xzAsstes", xzAsstes);
		return prefix + "/detail";
	}

	/**
	 * 删除资产
	 */
	@Log(title = "资产", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(xzAsstesService.deleteXzAsstesByIds(ids));
	}

}
