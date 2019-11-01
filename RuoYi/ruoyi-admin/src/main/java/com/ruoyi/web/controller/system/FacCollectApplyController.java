package com.ruoyi.web.controller.system;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.finance.FacCollectApply;
import com.ruoyi.system.domain.finance.FacReimburseApply;
import com.ruoyi.system.domain.finance.FacUserApproval;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.IFacCollectApplyService;
import com.ruoyi.system.service.finance.IFacNumberTableService;
import com.ruoyi.system.service.finance.IFacReimburseApplyService;
import com.ruoyi.system.service.finance.IFacUserApprovalService;

/**
 * 团建申请 信息操作处理
 *
 * @author ruoyi
 * @date 2019-09-03
 */
@Controller
@RequestMapping("/system/facCollectApply")
public class FacCollectApplyController extends BaseController {
	private String prefix = "system/facCollectApply";

	@Autowired
	private IFacCollectApplyService facCollectApplyService;
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private IFacReimburseApplyService facReimburseApplyService;

	@Autowired
	private IFacUserApprovalService facUserApprovalService;
	
	@Autowired
	private IFacNumberTableService facNumberTableService;
	
	

	//@RequiresPermissions("system:facCollectApply:view")
	@GetMapping()
	public String facCollectApply() {
		return prefix + "/facCollectApply";
	}

	/**
	 * 查询团建申请列表
	 */

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacCollectApply facCollectApply) {
		startPage();
		facCollectApply.setApplicant(ShiroUtils.getUserId());
		List<FacCollectApply> list = facCollectApplyService
				.selectFacCollectApplyList(facCollectApply);

		for (FacCollectApply v : list) {
			v.setApplicantName(sysUserService.selectUserById(v.getApplicant())
					.getUserName());

			FacUserApproval name = facUserApprovalService
					.selectApproval(v.getNum(), v.getApplicant());
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

	@PostMapping("/listId")
	@ResponseBody
	public TableDataInfo listId(FacCollectApply facCollectApply) {
		startPage();
		List<FacCollectApply> list = facCollectApplyService
				.selectFacCollectApplyList(facCollectApply);
		for (FacCollectApply v : list) {
			v.setApplicantName(sysUserService.selectUserById(v.getApplicant())
					.getUserName()); 
		}
		 
		return getDataTable(list);
	}

	/**
	 * 导出团建申请列表
	 */
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(FacCollectApply facCollectApply) {
		List<FacCollectApply> list = facCollectApplyService
				.selectFacCollectApplyList(facCollectApply);
		ExcelUtil<FacCollectApply> util = new ExcelUtil<FacCollectApply>(
				FacCollectApply.class);
		return util.exportExcel(list, "facCollectApply");
	}

	/**
	 * 新增团建申请
	 *
	 * @throws Exception
	 */
	@GetMapping("/add")
	public String add(ModelMap mmp) throws Exception {
		mmp.put("num", facNumberTableService.getNum("TJ", ShiroUtils.getDateId()));  
		mmp.put("deptName", ShiroUtils.getSysUser().getDept().getDeptName());
		return prefix + "/add";
	}

	
	/**
	 * 新增团建申请
	 *
	 * @throws Exception
	 */
	@GetMapping("/baoxiao") 
	public String Bao(String id, ModelMap mmap)
	{
		FacCollectApply facCollectApply = facCollectApplyService
				.selectFacCollectApplyById(Long.valueOf(id)); 
		FacReimburseApply facReimburseApply = new FacReimburseApply();
		facReimburseApply.setNum(facNumberTableService.getNum("BX", ShiroUtils.getDateId()));
		facReimburseApply.setName(facCollectApply.getLeagueProject());//报销名
		facReimburseApply.setAmount(facCollectApply.getAmount());
		facReimburseApply.setLoanUser(facCollectApply.getApplicant());
		facReimburseApply.setCreateTime(ShiroUtils.getDate());
		facReimburseApply.setReimburseTime(facCollectApply.getStartDate()); 
		facReimburseApply.setReason(facCollectApply.getActivityForm()); 
		facReimburseApply.setType("团建报销");   
		facReimburseApply.setJKnum(facCollectApply.getNum());
		mmap.put("facReimburseApply", facReimburseApply);
		return prefix + "/baoxiao";
	}
	
	
	
	
	/**
	 * 新增保存团建申请
	 */
	@Log(title = "团建申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FacCollectApply facCollectApply) {
		facCollectApply.setApplicationTime(new Date());
		facCollectApply.setApplicant(ShiroUtils.getUserId());
		if (facCollectApply.getId() == null) {
			// 直接添加
			
		} else {
			// 更新
			facCollectApply = facCollectApplyService
					.selectFacCollectApplyById(facCollectApply.getId());
			facCollectApplyService
					.deleteFacCollectApplyByIds(facCollectApply.getId() + "");
		}
		return toAjax(
				facCollectApplyService.insertFacCollectApply(facCollectApply));
	}

	/**
	 * 新增保存
	 *
	 * @throws Exception
	 */
	@Log(title = "差旅申请", businessType = BusinessType.INSERT)
	@PostMapping("/addSove")
	@ResponseBody
	public AjaxResult addSove(FacCollectApply facCollectApply)
			throws Exception {
		 
		facCollectApply.setNum(facNumberTableService.getNum("TJ", ShiroUtils.getDateId()));
		facCollectApply.setApplicant(ShiroUtils.getUserId());
		facCollectApply.setApplicationTime(new Date());
		return toAjax(facCollectApplyService.insertApply(facCollectApply));
	}

	/**
	 * 修改团建申请
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		FacCollectApply facCollectApply = facCollectApplyService
				.selectFacCollectApplyById(id);

		facCollectApply.setApplicantName(sysUserService
				.selectUserById(facCollectApply.getApplicant()).getUserName());
		mmap.put("facCollectApply", facCollectApply);
		return prefix + "/edit";
	}

	/**
	 * 修改保存团建申请
	 */
	@Log(title = "团建申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacCollectApply facCollectApply) {
		return toAjax(
				facCollectApplyService.updateFacCollectApply(facCollectApply));
	}

	/**
	 * 删除团建申请
	 */
	@Log(title = "团建申请", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(facCollectApplyService.deleteFacCollectApplyByIds(ids));
	}

}
