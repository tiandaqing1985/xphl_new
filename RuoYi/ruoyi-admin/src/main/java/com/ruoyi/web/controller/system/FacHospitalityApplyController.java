package com.ruoyi.web.controller.system;

import java.util.ArrayList;
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
import com.ruoyi.system.domain.finance.FacHospitalityApply;
import com.ruoyi.system.domain.finance.FacUserApproval;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.IFacHospitalityApplyService;
import com.ruoyi.system.service.finance.IFacUserApprovalService;

/**
 * 招待费申请 信息操作处理
 *
 * @author ruoyi
 * @date 2019-09-06
 */
@Controller
@RequestMapping("/system/facHospitalityApply")
public class FacHospitalityApplyController extends BaseController {
	private String prefix = "system/facHospitalityApply";

	@Autowired
	private IFacHospitalityApplyService facHospitalityApplyService;
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private IFacUserApprovalService facUserApprovalService;
	
	@RequiresPermissions("system:facHospitalityApply:view")
	@GetMapping()
	public String facHospitalityApply() {
		return prefix + "/facHospitalityApply";
	}

	/**
	 * 查询招待费申请列表
	 */

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacHospitalityApply facHospitalityApply) {
		startPage();
		facHospitalityApply.setUserId(ShiroUtils.getUserId());
		List<FacHospitalityApply> list = facHospitalityApplyService
				.selectFacHospitalityApplyList(facHospitalityApply); 
		for (FacHospitalityApply v : list) {
			v.setUserIdName(
					sysUserService.selectUserById(v.getUserId()).getUserName());  
			 FacUserApproval name=facUserApprovalService.selectApproval(v.getNum(), v.getUserId());
	            if(name!=null){
	                v.setApprover(sysUserService.selectUserById(name.getApproverId()).getUserName());
	                v.setApprovalStatus(name.getApprovalState());
	                
	            }else{
	                v.setApprover("--");
	                v.setApprovalStatus("--");
	            }  
		} 
		return getDataTable(list);
	}

	/**
	 * 导出招待费申请列表
	 */
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(FacHospitalityApply facHospitalityApply) {
		List<FacHospitalityApply> list = facHospitalityApplyService
				.selectFacHospitalityApplyList(facHospitalityApply);
		ExcelUtil<FacHospitalityApply> util = new ExcelUtil<FacHospitalityApply>(
				FacHospitalityApply.class);
		return util.exportExcel(list, "facHospitalityApply");
	}

	/**
	 * 新增招待费申请
	 */
	@GetMapping("/add")
	public String add(ModelMap mmp) {
		mmp.put("dept", ShiroUtils.getSysUser().getDept().getDeptName());
		return prefix + "/add";
	}

	/**
	 * 新增保存招待费申请
	 * 
	 * @throws Exception
	 */
	@Log(title = "招待费申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FacHospitalityApply facHospitalityApply)
			throws Exception {

		IdWorker idWorker = new IdWorker(0, 1);
		facHospitalityApply.setNum("ZD" + idWorker.nextId());
		facHospitalityApply.setUserId(ShiroUtils.getUserId());
		facHospitalityApply.setApplicationTime(new Date());
		facHospitalityApply.setAmount(facHospitalityApply.getStandardAmount()
				* Double.valueOf(facHospitalityApply.getTotalNumber()));// 获取预计金额

		if (facHospitalityApply.getId() == null) {
			// 直接添加
			facHospitalityApply.setNum("CL" + idWorker.nextId());
			facHospitalityApply.setUserId(ShiroUtils.getUserId());
		} else {
			// 更新
			facHospitalityApply = facHospitalityApplyService
					.selectFacHospitalityApplyById(facHospitalityApply.getId());
			facHospitalityApplyService.deleteFacHospitalityApplyByIds(
					facHospitalityApply.getId() + "");
		}
		return toAjax(facHospitalityApplyService
				.insertFacHospitalityApply(facHospitalityApply));
	}

	/**
	 * 新增保存
	 *
	 * @throws Exception
	 */
	@Log(title = "差旅申请", businessType = BusinessType.INSERT)
	@PostMapping("/addSove")
	@ResponseBody
	public AjaxResult addSove(FacHospitalityApply facHospitalityApply)
			throws Exception {
		IdWorker idWorker = new IdWorker(0, 1);
		facHospitalityApply.setNum("ZD" + idWorker.nextId());
		facHospitalityApply.setUserId(ShiroUtils.getUserId());
		facHospitalityApply.setApplicationTime(new Date());
		facHospitalityApply.setAmount(facHospitalityApply.getStandardAmount()
				* Double.valueOf(facHospitalityApply.getTotalNumber()));// 获取预计金额
		return toAjax(
				facHospitalityApplyService.insertApply(facHospitalityApply));
	}

	/**
	 * 修改招待费申请
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		FacHospitalityApply facHospitalityApply = facHospitalityApplyService
				.selectFacHospitalityApplyById(id);
		mmap.put("facHospitalityApply", facHospitalityApply);
		return prefix + "/edit";
	}

	/**
	 * 修改保存招待费申请
	 */
	@Log(title = "招待费申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacHospitalityApply facHospitalityApply) {
		return toAjax(facHospitalityApplyService
				.updateFacHospitalityApply(facHospitalityApply));
	}

	/**
	 * 删除招待费申请
	 */
	@Log(title = "招待费申请", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(
				facHospitalityApplyService.deleteFacHospitalityApplyByIds(ids));
	}

	/**
	 * 查看详情
	 */
	@GetMapping("/detail")
	public String detail(@RequestParam("id") Long id, ModelMap map) {
		FacHospitalityApply facHospitalityApply = new FacHospitalityApply();
		facHospitalityApply.setId(id);
		List<FacHospitalityApply> facReimburseApplies = facHospitalityApplyService
				.selectFacHospitalityApplyList(facHospitalityApply);
		map.put("rid", id);
		map.put("num", facReimburseApplies.get(0).getNum());
		return prefix + "/detail";
	}

	/**
	 * 查看行程安排详情
	 */
	@PostMapping("/detail")
	@ResponseBody
	public TableDataInfo detail1(@RequestParam("num") String num) {
		startPage();
		FacHospitalityApply facHospitalityApply = facHospitalityApplyService
				.deatil(num);
		if (facHospitalityApply != null) {
			List<FacHospitalityApply> facReimburseApplies = new ArrayList<>();
			facReimburseApplies.add(facHospitalityApply);
			return getDataTable(facReimburseApplies);
		} else {
			List<String> a = new ArrayList<>();
			return getDataTable(a);
		}
	}

	/**
	 * 新增报销申请
	 */
	@GetMapping("/reimbuseDetail")
	public String reimbuseDetail(@RequestParam("id") Long id, ModelMap map) {
		FacHospitalityApply facHospitalityApply = facHospitalityApplyService
				.selectFacHospitalityApplyById(id);
		map.put("amount", facHospitalityApply.getAmount());
		IdWorker idWorker = new IdWorker(0, 1);
		map.put("num", "BX" + idWorker.nextId());

		return prefix + "/reimbuseDetail";
	}

}
