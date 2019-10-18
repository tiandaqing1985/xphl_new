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
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.finance.FacCostApply;
import com.ruoyi.system.domain.finance.FacCostDetailApply;
import com.ruoyi.system.domain.finance.FacCostPutupApply;
import com.ruoyi.system.service.finance.IFacCostApplyService;
import com.ruoyi.system.service.finance.IFacCostPutupApplyService;

/**
 * 差旅申请 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-09-02
 */
@Controller
@RequestMapping("/system/facCostApply")
public class FacCostApplyController extends BaseController {
	private String prefix = "system/facCostApply";

	@Autowired
	private IFacCostApplyService facCostApplyService;
	@Autowired
	private IFacCostPutupApplyService facCostPutupApplyService;
	
	
	@RequiresPermissions("system:facCostApply:view")
	@GetMapping()
	public String facCostApply() {
		return prefix + "/facCostApply";
	}

	/**
	 * 查询差旅申请列表
	 */

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacCostApply facCostApply) {
		startPage();
		facCostApply.setUserId(ShiroUtils.getUserId());
		List<FacCostApply> list = facCostApplyService
				.selectFacCostApplyList(facCostApply);
		return getDataTable(list);
	}

	/**
	 * 查看行程安排详情
	 */
	@PostMapping("/querys/{num}")
	@ResponseBody
	public TableDataInfo detail1s(@PathVariable("num") String num) {
		startPage();
		List<FacCostDetailApply> list = facCostApplyService.deatils(num);
		if (list != null) {

			return getDataTable(list);
		} else {
			List<String> a = new ArrayList<>();
			return getDataTable(a);
		}
	}
	
	/**
	 * 查看住宿安排详情
	 */
	@PostMapping("/sleep/{num}")
	@ResponseBody
	public TableDataInfo sleep(@PathVariable("num") String num) {
		startPage();
		FacCostPutupApply facCostPutupApply = new FacCostPutupApply();
		facCostPutupApply.setNum(num);
		List<FacCostPutupApply> list = facCostPutupApplyService.selectFacCostPutupApplyList(facCostPutupApply);
		if (list != null) {

			return getDataTable(list);
		} else {
			List<String> a = new ArrayList<>();
			return getDataTable(a);
		}
	}
	/**
	 * 导出差旅申请列表
	 */
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(FacCostApply facCostApply) {
		List<FacCostApply> list = facCostApplyService
				.selectFacCostApplyList(facCostApply);
		ExcelUtil<FacCostApply> util = new ExcelUtil<FacCostApply>(
				FacCostApply.class);
		return util.exportExcel(list, "facCostApply");
	}

	/**
	 * 新增差旅申请
	 */
	@GetMapping("/add")
	public String add(ModelMap mmp) {
		IdWorker idWorker = new IdWorker(0, 1);
		mmp.put("num", "CL" + idWorker.nextId());
		return prefix + "/add";
	}

	/**
	 * 新增保存差旅申请
	 */
	@Log(title = "差旅申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FacCostApply facCostApply) {
		facCostApply.setUserId(ShiroUtils.getUserId());
		return toAjax(facCostApplyService.insertFacCostApply(facCostApply));
	}

	/**
	 * 修改差旅申请
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		FacCostApply facCostApply = facCostApplyService
				.selectFacCostApplyById(id);
		mmap.put("facCostApply", facCostApply);
		return prefix + "/edit";
	}

	/**
	 * 修改保存差旅申请
	 */
	@Log(title = "差旅申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacCostApply facCostApply) {
		return toAjax(facCostApplyService.updateFacCostApply(facCostApply));
	}

	/**
	 * 删除差旅申请
	 */
	@Log(title = "差旅申请", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(facCostApplyService.deleteFacCostApplyByIds(ids));
	}

	/**
	 * 查看行程安排详情
	 */
	@PostMapping("/query")
	@ResponseBody
	public TableDataInfo detail1(String num) {
		startPage();
		FacCostApply facCostApply = facCostApplyService.deatil(num);
		if (facCostApply != null) {
			List<FacCostApply> facReimburseApplies = new ArrayList<>();
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
	@GetMapping("/detail")
	public String detail(@RequestParam("id") Long id, ModelMap map) {
		FacCostApply facCostApply = new FacCostApply();
		facCostApply.setId(id);
		List<FacCostApply> facReimburseApplies = facCostApplyService
				.selectFacCostApplyList(facCostApply);
		map.put("rid", id);
		map.put("num", facReimburseApplies.get(0).getNum());
		map.put("status", facReimburseApplies.get(0).getStatus());
		return prefix + "/costDetail";
	}

	/**
	 * 行程安排
	 */
	@GetMapping("/tranDetail")
	public String tranDetail(@RequestParam String num, ModelMap map) {
		map.put("num",num);
		return prefix + "/tranDetail";
	}
	/**
	 * 行程安排添加
	 */
	@Log(title = "行程安排", businessType = BusinessType.INSERT)
	@PostMapping("/tranDetail")
	@ResponseBody
	public AjaxResult tranDetailSave(FacCostDetailApply facCostDetailApply) {

		return toAjax(facCostApplyService
				.insertFacCostDetailApply(facCostDetailApply));
	}

	/**
	 * 查看详情
	 */
	@GetMapping("/putup")
	public String putup(@RequestParam("id") Long id, ModelMap map) {
		FacCostApply facCostApply = new FacCostApply();
		facCostApply.setId(id);
		List<FacCostApply> facReimburseApplies = facCostApplyService
				.selectFacCostApplyList(facCostApply);
		map.put("rid", id);
		map.put("num", facReimburseApplies.get(0).getNum());
		map.put("status", facReimburseApplies.get(0).getStatus());
		return prefix + "/costDetail";
	}

	/**
	 * 住宿安排
	 */
	@GetMapping("/putAdd")
	public String putup(@RequestParam String num, ModelMap map) {
		map.put("num",num);
		return prefix + "/putAdd";
	}
	/**
	 * 住宿安排添加
	 */
	@Log(title = "住宿安排", businessType = BusinessType.INSERT)
	@PostMapping("/putAdd")
	@ResponseBody
	public AjaxResult putup(FacCostPutupApply facCostPutupApply) {
		return toAjax(
				facCostApplyService.insertFacCostPutupApply(facCostPutupApply));
	}

}
