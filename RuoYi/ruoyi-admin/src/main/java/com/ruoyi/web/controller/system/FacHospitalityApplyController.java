package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.ruoyi.system.domain.finance.FacHospitalityApply;
import com.ruoyi.system.domain.finance.FacReimburseApply;
import com.ruoyi.system.domain.finance.FacUserApproval;
import com.ruoyi.system.domain.finance.ReiHospitalityApply;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.IFacHospitalityApplyService;
import com.ruoyi.system.service.finance.IFacNumberTableService;
import com.ruoyi.system.service.finance.IFacReimburseApplyService;
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
	@Autowired
	private IFacReimburseApplyService facReimburseApplyService;
	@Autowired
	private IFacNumberTableService facNumberTableService;

	//@RequiresPermissions("system:facHospitalityApply:view")
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
			FacUserApproval name = facUserApprovalService
					.selectApproval(v.getNum(), v.getUserId());
			if (name != null) {
				if (name.getApproverId() != null) {
					v.setApprover(
							sysUserService.selectUserById(name.getApproverId())
									.getUserName());
				}
				v.setApprovalStatus(name.getApprovalState());
				if (ShiroUtils.getUserId() == 103
						&& ShiroUtils.getUserId() == 101) {
					v.setApprovalStatus("1");
				}

			} else {
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

	@GetMapping("/addSave")
	public String addSave(String id, ModelMap map) {
		map.put("id",id);
		return prefix + "/addSave";
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

	 
		facHospitalityApply.setUserId(ShiroUtils.getUserId());

		if (facHospitalityApply.getId() == null) {
			// 直接添加
			facHospitalityApply.setNum(facNumberTableService.getNum("ZD", ShiroUtils.getDateId()));
			facHospitalityApply.setUserId(ShiroUtils.getUserId());
			facHospitalityApply.setApplicationTime(new Date());
			facHospitalityApply
					.setAmount(facHospitalityApply.getStandardAmount() * Double
							.valueOf(facHospitalityApply.getTotalNumber()));// 获取预计金额
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
		 
		facHospitalityApply.setNum(facNumberTableService.getNum("ZD", ShiroUtils.getDateId()));
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
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long id, ModelMap map) {
		FacHospitalityApply facHospitalityApply = new FacHospitalityApply();
		facHospitalityApply.setId(id);
		List<FacHospitalityApply> facReimburseApplies = facHospitalityApplyService
				.selectFacHospitalityApplyList(facHospitalityApply);
		map.put("id", id);
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
		FacHospitalityApply fac = new FacHospitalityApply();
		fac.setNum(num);
		FacHospitalityApply facHospitalityApply = facHospitalityApplyService
				.selectFacHospitalityApplyList(fac).get(0);
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
		 String num=facNumberTableService.getNum("BX", ShiroUtils.getDateId());
		map.put("num", num);
		map.put("name", facHospitalityApply.getZdName()); 
		map.put("deptName", ShiroUtils.getSysUser().getDept().getDeptName());
		ReiHospitalityApply reiHospitalityApply = new ReiHospitalityApply();
		reiHospitalityApply.setDdDate(facHospitalityApply.getZdDate());
		reiHospitalityApply.setAmount(facHospitalityApply.getAmount());
		reiHospitalityApply.setAddUser(facHospitalityApply.getAddUser() + ","
				+ facHospitalityApply.getLoanId());
		reiHospitalityApply.setReason(facHospitalityApply.getReason());
		reiHospitalityApply.setNum(num);
		facReimburseApplyService
				.insertFacreiHospitalityApply(reiHospitalityApply);
		return prefix + "/reimbuseDetail";
	}

	/**
	 * 新增招待申请
	 *
	 * @throws Exception
	 */
	@GetMapping("/baoxiaoEdit") 
	public String Baoxiao(String id, ModelMap mmap)
	{
		FacHospitalityApply facHospitalityApply = facHospitalityApplyService.selectFacHospitalityApplyById( Long.valueOf(id).longValue());
		mmap.put("facHospitalityApply", facHospitalityApply);
		return prefix + "/baoxiaoEdit";
	} 
	
	@Log(title = "差旅申请", businessType = BusinessType.UPDATE)
	@PostMapping("/addEdit")
	@ResponseBody
	public AjaxResult addEdit(FacHospitalityApply facHospitalityApply) {
		FacHospitalityApply facHospitalityApplys = facHospitalityApplyService.selectFacHospitalityApplyById( facHospitalityApply.getId());
		FacReimburseApply facReimburseApply = new FacReimburseApply();
		facReimburseApply.setNum(
				facNumberTableService.getNum("BX", ShiroUtils.getDateId()));
		facReimburseApply.setName(facHospitalityApply.getZdName());// 报销名
		facReimburseApply.setAmount(facHospitalityApply.getAmount());
		facReimburseApply.setLoanUser(facHospitalityApply.getUserId());
		facReimburseApply.setCreateTime(ShiroUtils.getDate());
		facReimburseApply.setReimburseTime(facHospitalityApply.getApplicationTime());
		facReimburseApply.setReason(facHospitalityApply.getReason());
		facReimburseApply.setType("招待报销");
		facReimburseApply.setJKnum(facHospitalityApply.getNum());
		facReimburseApply.setLoanUser(ShiroUtils.getUserId());
		facReimburseApply.setCreateBy(ShiroUtils.getUserId().toString());
		facHospitalityApply.setStates(6L); 
		if (facHospitalityApply.getAmount()<= facHospitalityApplys.getAmount()) {
			// 不需要二次审批
			facReimburseApply.setStatus("1");
			facReimburseApply.setSubmitStatus("save"); 
			ReiHospitalityApply reiHospitalityApply=new ReiHospitalityApply();
			reiHospitalityApply.setUser(ShiroUtils.getUserId()); 
			reiHospitalityApply.setNum(facReimburseApply.getNum());
			
			reiHospitalityApply.setDdDate(facHospitalityApply.getApplicationTime()); 
			reiHospitalityApply.setAddUser(facHospitalityApply.getLoanId());
			reiHospitalityApply.setAmount(facHospitalityApply.getAmount()); 
			//reiHospitalityApply.setTargetUnit(facHospitalityApply.get); //目标单位简称
			//reiHospitalityApply.setDocumentNum(facHospitalityApply.get);  //单据数
			reiHospitalityApply.setReason(facHospitalityApply.getReason());
			
			facReimburseApplyService.insertFacreiHospitalityApply(reiHospitalityApply); 
			facReimburseApplyService.insertApply(facReimburseApply);
		} else {
			// 需要二次审批
			facHospitalityApply.setNum(
					facNumberTableService.getNum("ZD", ShiroUtils.getDateId()));
			facHospitalityApply.setUserId(ShiroUtils.getUserId());
			facHospitalityApply.setZdName(facHospitalityApplys.getZdName());
			facHospitalityApply.setApplicationTime(facHospitalityApplys.getApplicationTime());
			FacHospitalityApply fac = facHospitalityApply;
			fac.setStates(3L); 
			facHospitalityApplyService.insertFacHospitalityApply(fac);
		}
		return toAjax(facHospitalityApplyService.updateFacHospitalityApply(facHospitalityApply));
	}

}
