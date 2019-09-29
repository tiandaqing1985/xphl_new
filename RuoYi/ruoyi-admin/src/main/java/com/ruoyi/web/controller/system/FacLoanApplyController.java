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
import com.ruoyi.common.utils.IdWorker;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.finance.FacLoanApply;
import com.ruoyi.system.service.finance.IFacLoanApplyService;

/**
 * 借款申请 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-30
 */
@Controller
@RequestMapping("/system/facLoanApply")
public class FacLoanApplyController extends BaseController {
	private String prefix = "system/facLoanApply";

	@Autowired
	private IFacLoanApplyService facLoanApplyService;

	@GetMapping()
	public String facLoanApply() {
		return prefix + "/facLoanApply";
	}

	/**
	 * 查询借款申请列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacLoanApply facLoanApply) {
		startPage();
		List<FacLoanApply> list = facLoanApplyService
				.selectFacLoanApplyList(facLoanApply);
		return getDataTable(list);
	}

	/**
	 * 导出借款申请列表
	 */
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(FacLoanApply facLoanApply) {
		List<FacLoanApply> list = facLoanApplyService
				.selectFacLoanApplyList(facLoanApply);
		ExcelUtil<FacLoanApply> util = new ExcelUtil<FacLoanApply>(
				FacLoanApply.class);
		return util.exportExcel(list, "facLoanApply");
	}

	
	/**
	 * 新增借款申请
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存借款申请
	 */
	@Log(title = "借款申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public int addSave(FacLoanApply facLoanApply) {
		IdWorker idWorker = new IdWorker(0, 1);
		facLoanApply.setNum("CL" + idWorker.nextId());
		return facLoanApplyService.insertFacLoanApply(facLoanApply);
	}

	/**
	 * 修改借款申请
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap) {
		FacLoanApply facLoanApply = facLoanApplyService
				.selectFacLoanApplyById(id);
		mmap.put("facLoanApply", facLoanApply);
		return prefix + "/edit";
	}

	/**
	 * 修改保存借款申请
	 */
	@Log(title = "借款申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacLoanApply facLoanApply) {
		return toAjax(facLoanApplyService.updateFacLoanApply(facLoanApply));
	}

	/**
	 * 删除借款申请
	 */
	@Log(title = "借款申请", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(facLoanApplyService.deleteFacLoanApplyByIds(ids));
	}

	/**
	 * 查看借款详情
	 */
	@PostMapping("/querys")
	@ResponseBody
	public TableDataInfo detail1s(String num) {
		startPage();
		List<FacLoanApply> list = facLoanApplyService.deatils(num);
		if (list != null) {

			return getDataTable(list);
		} else {
			List<String> a = new ArrayList<>();
			return getDataTable(a);
		}
	}
	/**
	 * 查看详情
	 */
	@RequiresPermissions("system:facLostApply:detail")
	@GetMapping("/detail")
	public String detail(@RequestParam("id") Long id, ModelMap map) {
		FacLoanApply facLostApply = new FacLoanApply();
		facLostApply.setId(id);
		List<FacLoanApply> facReimburseApplies = facLoanApplyService
				.selectFacLoanApplyList(facLostApply);
		map.put("rid", id);
		map.put("num", facReimburseApplies.get(0).getNum());
		map.put("status", facReimburseApplies.get(0).getApplyStatus());
		return prefix + "/detail";
	}
	/** 还款 **/

	@RequiresPermissions("system:facLostApply:repayment")
	@GetMapping("/repayment")
	public String repayment(@RequestParam("id") Long id, ModelMap map) {
		FacLoanApply facLostApply = new FacLoanApply();
		facLostApply.setId(id);
		List<FacLoanApply> facReimburseApplies = facLoanApplyService
				.selectFacLoanApplyList(facLostApply);
		map.put("rid", id);
		map.put("num", facReimburseApplies.get(0).getNum());
		map.put("status", facReimburseApplies.get(0).getApplyStatus());
		return prefix + "/repayment";
	}

}
