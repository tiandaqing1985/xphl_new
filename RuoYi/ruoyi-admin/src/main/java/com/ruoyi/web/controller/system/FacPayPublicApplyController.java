package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.finance.FacCostApply;
import com.ruoyi.system.domain.finance.FacPayPublicApply;
import com.ruoyi.system.service.finance.IFacPayPublicApplyService;

/**
 * 对公申请 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-10-10
 */
@Controller
@RequestMapping("/system/facPayPublicApply")
public class FacPayPublicApplyController extends BaseController {
	private String prefix = "system/facPayPublicApply";

	@Autowired
	private IFacPayPublicApplyService facPayPublicApplyService;

	@RequiresPermissions("system:facPayPublicApply:view")
	@GetMapping()
	public String facPayPublicApply() {
		return prefix + "/facPayPublicApply";
	}

	/**
	 * 查询对公申请列表
	 */
	@RequiresPermissions("system:facPayPublicApply:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacPayPublicApply facPayPublicApply) {
		startPage();
		List<FacPayPublicApply> list = facPayPublicApplyService
				.selectFacPayPublicApplyList(facPayPublicApply);
		return getDataTable(list);
	}

	/**
	 * 导出对公申请列表
	 */
	@RequiresPermissions("system:facPayPublicApply:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(FacPayPublicApply facPayPublicApply) {
		List<FacPayPublicApply> list = facPayPublicApplyService
				.selectFacPayPublicApplyList(facPayPublicApply);
		ExcelUtil<FacPayPublicApply> util = new ExcelUtil<FacPayPublicApply>(
				FacPayPublicApply.class);
		return util.exportExcel(list, "facPayPublicApply");
	}

	/**
	 * 新增对公申请
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存对公申请
	 */
	@RequiresPermissions("system:facPayPublicApply:add")
	@Log(title = "对公申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FacPayPublicApply facPayPublicApply) {
		facPayPublicApply.setUser(ShiroUtils.getUserId());
		return toAjax(facPayPublicApplyService
				.insertFacPayPublicApply(facPayPublicApply));
	}

	/**
	 * 修改对公申请
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
		FacPayPublicApply facPayPublicApply = facPayPublicApplyService
				.selectFacPayPublicApplyById(id);
		mmap.put("facPayPublicApply", facPayPublicApply);
		return prefix + "/edit";
	}

	/**
	 * 修改保存对公申请
	 */
	@RequiresPermissions("system:facPayPublicApply:edit")
	@Log(title = "对公申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacPayPublicApply facPayPublicApply) {
		return toAjax(facPayPublicApplyService
				.updateFacPayPublicApply(facPayPublicApply));
	}

	/**
	 * 删除对公申请
	 */
	@RequiresPermissions("system:facPayPublicApply:remove")
	@Log(title = "对公申请", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(
				facPayPublicApplyService.deleteFacPayPublicApplyByIds(ids));
	}

	/**
	 * 查看行程安排详情
	 */
	@PostMapping("/query")
	@ResponseBody
	public TableDataInfo detail1(String num) {
		startPage();
		FacPayPublicApply facCostApply = facPayPublicApplyService.deatil(num);
		if (facCostApply != null) {
			List<FacPayPublicApply> facReimburseApplies = new ArrayList<>();
			facReimburseApplies.add(facCostApply);
			return getDataTable(facReimburseApplies);
		} else {
			List<String> a = new ArrayList<>();
			return getDataTable(a);
		}
	}

	/**
	 * 查看详情
	 */
	@RequiresPermissions("system:facCostApply:detail")
	@GetMapping("/detail")
	public String detail(@RequestParam("id") Long id, ModelMap map) {
		FacPayPublicApply facPayPublicApply = new FacPayPublicApply();
		List<FacPayPublicApply> facReimburseApplies = facPayPublicApplyService
				.selectFacPayPublicApplyList(facPayPublicApply);
		map.put("rid", id);
		map.put("num", facReimburseApplies.get(0).getNum());
		map.put("status", facReimburseApplies.get(0).getStatus());
		return prefix + "/public";
	}

}
