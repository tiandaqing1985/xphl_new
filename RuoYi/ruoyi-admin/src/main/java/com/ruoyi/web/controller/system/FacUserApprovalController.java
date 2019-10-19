package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.finance.FacUserApproval;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.IFacUserApprovalService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 财务审批 信息操作处理
 *
 * @author ruoyi
 * @date 2019-09-27
 */
@Controller
@RequestMapping("/system/facUserApproval")
public class FacUserApprovalController extends BaseController {
	private String prefix = "system/facUserApproval";

	@Autowired
	private IFacUserApprovalService facUserApprovalService;
	@Autowired
	private ISysUserService sysUserService;

	@RequiresPermissions("system:facUserApproval:view")
	@GetMapping()
	public String facUserApproval() {
		return prefix + "/facUserApproval";
	}

	/**
	 * 查询财务审批列表
	 */

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacUserApproval facUserApproval) {
		startPage();
		facUserApproval.setApprovalState("3");
		facUserApproval.setApproverId(ShiroUtils.getUserId());
		facUserApproval.setApprovalSight("1");
		List<FacUserApproval> list = facUserApprovalService
				.selectFacUserApprovalList(facUserApproval);
		for (FacUserApproval v : list) {
			v.setApplicantName(sysUserService.selectUserById(v.getApplicantId())
					.getUserName());
			v.setApproverName(sysUserService.selectUserById(v.getApproverId())
					.getUserName());
		}
		return getDataTable(list);
	}

	/**
	 * 导出财务审批列表
	 */
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(FacUserApproval facUserApproval) {
		List<FacUserApproval> list = facUserApprovalService
				.selectFacUserApprovalList(facUserApproval);
		ExcelUtil<FacUserApproval> util = new ExcelUtil<FacUserApproval>(
				FacUserApproval.class);
		return util.exportExcel(list, "facUserApproval");
	}

	/**
	 * 新增财务审批
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存财务审批
	 */
	@Log(title = "财务审批", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FacUserApproval facUserApproval) {
		return toAjax(
				facUserApprovalService.insertFacUserApproval(facUserApproval));
	}

	/**
	 * 修改财务审批
	 */
	@GetMapping("/edit/{approvalId}")
	public String edit(@PathVariable("approvalId") Long approvalId,
			ModelMap map) {
		FacUserApproval facUserApproval = facUserApprovalService
				.selectFacUserApprovalById(approvalId);
		map.put("facUserApproval", facUserApproval);
		map.put("num",facUserApproval.getApplyId());
		map.put("msg", "1");
		map.put("deptName", ShiroUtils.getSysUser().getDept().getDeptName());
		map.put("userId", ShiroUtils.getUserId());
		String nums=facUserApproval.getApplyId().substring(0,2);
		if (nums.equals("BX")) {
			return prefix + "/baoxiaoDetails";
		} else if (nums.equals("JK")) {
			return prefix + "/jiekuanDetails";
		} else if (nums.equals("CL")) {
			return prefix + "/chailvDetails";
		} else if (nums.equals("HK")) {
			return prefix + "/huankuanDetails";
		} else if (nums.equals("DG")) {
			return prefix + "/duigongDetails";
		} else if (nums.equals("TJ")) {
			return prefix + "/tuanjianDetails";
		} else {
			return prefix + "/zhaodaiDetails";
		}

	}

	/**
	 * 修改保存财务审批
	 */
	@Log(title = "财务审批", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacUserApproval facUserApproval) { 
		List<FacUserApproval> list = facUserApprovalService
				.selectFacUserApprovalList(facUserApproval);
		FacUserApproval fac=list.get(0);
		fac.setApprovalState("1");
		return toAjax(
				facUserApprovalService.updateFacUserApproval(fac));
	}


	/**
	 * 修改保存驳回财务审批
	 */
	@Log(title = "财务审批", businessType = BusinessType.UPDATE)
	@PostMapping("/editnot")
	@ResponseBody
	public AjaxResult editNot(FacUserApproval facUserApproval) { 
		List<FacUserApproval> list = facUserApprovalService
				.selectFacUserApprovalList(facUserApproval);
		FacUserApproval fac=list.get(0);
		fac.setApprovalState("2");
		return toAjax(
				facUserApprovalService.updateFacUserApproval(facUserApproval));
	}
	
	
	/**
	 * 删除财务审批
	 */
	@Log(title = "财务审批", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(facUserApprovalService.deleteFacUserApprovalByIds(ids));
	}

	/**
	 * 已审批
	 */
	@GetMapping("/endfacUserApproval")
	public String myApproval() {
		return prefix + "/endfacUserApproval";
	}

	/**
	 * 已审批
	 */
	@PostMapping("/endfacUserApproval")
	@ResponseBody
	public TableDataInfo endfacUserApproval(FacUserApproval facUserApproval) {
		startPage();
		facUserApproval.setApproverId(ShiroUtils.getUserId());
		List<FacUserApproval> list = facUserApprovalService
				.selectApplicantIdList(facUserApproval);

		for (FacUserApproval v : list) {
			v.setApplicantName(sysUserService.selectUserById(v.getApplicantId())
					.getUserName());
			v.setApproverName(sysUserService.selectUserById(v.getApproverId())
					.getUserName());
		}

		return getDataTable(list);
	}

	/**
	 * 我的审批
	 */
	@GetMapping("/myApproval")
	public String approval() {
		return prefix + "/myApproval";
	}

	/**
	 * 我的审批
	 */
	@PostMapping("/myApproval")
	@ResponseBody
	public TableDataInfo approval(FacUserApproval facUserApproval) {
		startPage();
		facUserApproval.setApplicantId(ShiroUtils.getUserId());
		List<FacUserApproval> list = facUserApprovalService
				.selectApplicantIdList(facUserApproval);
		return getDataTable(list);
	}

	/**
	 * 我的审批
	 */
	@GetMapping("/apply")
	public String apply() {
		return prefix + "/apply";
	}

	/**
	 * 我的审批
	 */
	@PostMapping("/apply")
	@ResponseBody
	public TableDataInfo apply(FacUserApproval facUserApproval) {
		startPage();
		facUserApproval.setApproverId(ShiroUtils.getUserId());
		List<FacUserApproval> list = facUserApprovalService
				.selectApproverIdList(facUserApproval);
		return getDataTable(list);
	}

}
