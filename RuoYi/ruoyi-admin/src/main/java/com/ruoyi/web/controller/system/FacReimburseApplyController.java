package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.finance.FacLoanRepayApply;
import com.ruoyi.system.domain.finance.FacReiAdiApply;
import com.ruoyi.system.domain.finance.FacReimburseApply;
import com.ruoyi.system.domain.finance.FacUserApproval;
import com.ruoyi.system.domain.finance.ReiHospitalityApply;
import com.ruoyi.system.domain.finance.ReiTrafficApply;
import com.ruoyi.system.mapper.finance.FacUserApprovalMapper;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.IFacLoanRepayApplyService;
import com.ruoyi.system.service.finance.IFacReimburseApplyService;

/**
 * 报销 信息操作处理
 *
 * @author ruoyi
 * @date 2019-07-31
 */
@Controller
@RequestMapping("/system/facReimburseApply")
public class FacReimburseApplyController extends BaseController {
	private String prefix = "system/facReimburseApply";

	@Autowired
	private IFacReimburseApplyService facReimburseApplyService;
	@Autowired
	private FacUserApprovalMapper facUserApprovalMapper;
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private ISysDeptService sysDeptService;
	@Autowired
	private ISysRoleService sysRoleService;
    @Autowired
    private IFacLoanRepayApplyService facLoanRepayApplyService;
	@RequiresPermissions("system:facReimburseApply:view")
	@GetMapping()
	public String facReimburseApply() {
		return prefix + "/facReimburseApply";
	}
	/**
	 * 查询报销列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacReimburseApply facReimburseApply) {
		//查出这个人的信息
		SysUser user = ShiroUtils.getSysUser();
		SysDept sysDept = sysDeptService.selectDeptById(user.getDeptId());
		List<SysRole> sysRoles = sysRoleService.selectRolesByUserId(user.getUserId());
		for(SysRole sysRole:sysRoles){
			if(sysRole.isFlag()&&sysRole.getRoleId()==13){
				//若是财务
				startPage();
				List<FacReimburseApply> list = facReimburseApplyService.selectFacReimburseApplyList(facReimburseApply);
				FacUserApproval facUserApproval = null;
				for(FacReimburseApply facReimburseApply1:list){
					facUserApproval = new FacUserApproval();
					facUserApproval.setApprovalSight("1");
					facUserApproval.setApplyId(facReimburseApply1.getNum());
					facUserApproval.setApprovalState("3");
					List<FacUserApproval> facUserApprovals = facUserApprovalMapper.selectFacUserApprovalList(facUserApproval);
					if(facUserApprovals.size()>0){
						SysUser sysUser = sysUserService.selectUserById(facUserApprovals.get(0).getApproverId());
						facReimburseApply1.setApproveName(sysUser.getUserName());
					}
				}
				return getDataTable(list);
			}
		}
//		if(sysDept.getLeader().equals(user.getUserName())){
//			//是领导
//			SysUser selectUser = new SysUser();
//			selectUser.setDeptId(user.getDeptId());
//			List<SysUser> sysUsersList = sysUserService.selectUserList(selectUser);
//			startPage();
//			List<FacReimburseApply> list = facReimburseApplyService.selectFacReimburseApplyListByCreateBy(sysUsersList);
//			FacUserApproval facUserApproval = null;
//			for(FacReimburseApply facReimburseApply1:list){
//				facUserApproval = new FacUserApproval();
//				facUserApproval.setApprovalSight("1");
//				facUserApproval.setApplyId(facReimburseApply1.getNum());
//				facUserApproval.setApprovalState("3");
//				List<FacUserApproval> facUserApprovals = facUserApprovalMapper.selectFacUserApprovalList(facUserApproval);
//				if(facUserApprovals.size()>0){
//					SysUser sysUser = sysUserService.selectUserById(facUserApprovals.get(0).getApproverId());
//					facReimburseApply1.setApproveName(sysUser.getUserName());
//				}
//			}
//
//			return getDataTable(list);
//		}else{
//			//不是领导
//			//查询自己的
//			facReimburseApply.setCreateBy(ShiroUtils.getUserId().toString());
//			startPage();
//			List<FacReimburseApply> list = facReimburseApplyService.selectFacReimburseApplyList(facReimburseApply);
//			FacUserApproval facUserApproval = null;
//			for(FacReimburseApply facReimburseApply1:list){
//				facUserApproval = new FacUserApproval();
//				facUserApproval.setApprovalSight("1");
//				facUserApproval.setApplyId(facReimburseApply1.getNum());
//				facUserApproval.setApprovalState("3");
//				List<FacUserApproval> facUserApprovals = facUserApprovalMapper.selectFacUserApprovalList(facUserApproval);
//				if(facUserApprovals.size()>0){
//					SysUser sysUser = sysUserService.selectUserById(facUserApprovals.get(0).getApproverId());
//					facReimburseApply1.setApproveName(sysUser.getUserName());
//				}
//			}
//			return getDataTable(list);
//		}

		//查询自己的
		facReimburseApply.setCreateBy(ShiroUtils.getUserId().toString());
		startPage();
		List<FacReimburseApply> list = facReimburseApplyService.selectFacReimburseApplyList(facReimburseApply);
		FacUserApproval facUserApproval = null;
		for(FacReimburseApply facReimburseApply1:list){
			facUserApproval = new FacUserApproval();
			facUserApproval.setApprovalSight("1");
			facUserApproval.setApplyId(facReimburseApply1.getNum());
			facUserApproval.setApprovalState("3");
			List<FacUserApproval> facUserApprovals = facUserApprovalMapper.selectFacUserApprovalList(facUserApproval);
			if(facUserApprovals.size()>0){
				SysUser sysUser = sysUserService.selectUserById(facUserApprovals.get(0).getApproverId());
				facReimburseApply1.setApproveName(sysUser.getUserName());
			}
		}
		return getDataTable(list);

	}
	/**
	 * 导出报销列表
	 */
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(FacReimburseApply facReimburseApply) {
		List<FacReimburseApply> list = facReimburseApplyService
				.selectFacReimburseApplyList(facReimburseApply);
		ExcelUtil<FacReimburseApply> util = new ExcelUtil<FacReimburseApply>(
				FacReimburseApply.class);
		return util.exportExcel(list, "facReimburseApply");
	}
	/**
	 * 查看详情
	 */
	@GetMapping("/detail")
	public String detail(@RequestParam("id") Long id, ModelMap map) {
		FacReimburseApply facReimburseApply = new FacReimburseApply();
		facReimburseApply.setId(id);
		List<FacReimburseApply> facReimburseApplies = facReimburseApplyService
				.selectFacReimburseApplyList(facReimburseApply);
		map.put("rid", id);
		map.put("num", facReimburseApplies.get(0).getNum());
		map.put("status", facReimburseApplies.get(0).getStatus());
		map.put("deptName", facReimburseApplies.get(0).getDeptName());
		map.put("name", facReimburseApplies.get(0).getName());
		map.put("userName", facReimburseApplies.get(0).getUserName());
		return prefix + "/detail";
	}
	/**
	 * 新增报销
	 * @throws Exception 
	 */
	@GetMapping("/add")
	public String add(ModelMap mmp, Long id) throws Exception {
		if (id == null) {
			IdWorker idWorker = new IdWorker(0, 1);
			mmp.put("num", "BX" +idWorker.nextId());
		} else {
			FacReimburseApply facReimburseApply = new FacReimburseApply();
			facReimburseApply.setId(id);
			List<FacReimburseApply> facReimburseApplies = facReimburseApplyService
					.selectFacReimburseApplyList(facReimburseApply);
			mmp.put("num", facReimburseApplies.get(0).getNum());
			mmp.put("name", facReimburseApplies.get(0).getName());
		}
		mmp.put("msg", "1");
		mmp.put("userName", ShiroUtils.getSysUser().getUserName());
		mmp.put("userId", ShiroUtils.getUserId());
		mmp.put("deptId", ShiroUtils.getDeptId());
		mmp.put("deptName", ShiroUtils.getSysUser().getDept().getDeptName());

		List<Long> longs = facReimburseApplyService.selectRole(ShiroUtils.getUserId());
		for(Long l:longs){
			if(l==10||l==9){
				mmp.put("dept",10);
				break;
			}else {
				mmp.put("dept",11);
			}
		}
		return prefix + "/reimbuseDetail";
	}
	/**
	 * 新增保存报销
	 */
	@Log(title = "报销", businessType = BusinessType.INSERT)
	@PostMapping("/addSave")
	@ResponseBody
	@Transactional
	public AjaxResult addSave(FacReimburseApply facReimburseApply) {
		facReimburseApply.setLoanUser(ShiroUtils.getUserId());
		facReimburseApply.setCreateBy(ShiroUtils.getUserId().toString());
		if (facReimburseApply.getId() != null) {
			FacReimburseApply fac = facReimburseApplyService
					.selectFacReimburseApplyById(facReimburseApply.getId()+"");
			facReimburseApplyService
					.deleteFacReimburseApplyById(facReimburseApply.getId()+"");
			facReimburseApply.setUpdateTime(new Date());
			facReimburseApply.setCreateTime(fac.getCreateTime());
		} else {
			facReimburseApply.setCreateTime(new Date());
		}
		return toAjax(facReimburseApplyService
				.insertSaveFacReimburseApply(facReimburseApply));
	}
	/**
	 * 新增提交报销
	 */
	@Log(title = "报销", businessType = BusinessType.INSERT)
	@PostMapping("/addSubmit")
	@ResponseBody
	@Transactional
	public AjaxResult addSubmit(FacReimburseApply facReimburseApply) {
		if (facReimburseApply.getId() == null) {
			// 直接添加
			facReimburseApply.setLoanUser(ShiroUtils.getUserId());
			facReimburseApply.setCreateTime(new Date());
			facReimburseApply.setCreateBy(ShiroUtils.getUserId().toString());
		} else {
			// 更新
			facReimburseApply = facReimburseApplyService
					.selectFacReimburseApplyById(facReimburseApply.getId()+"");
			facReimburseApply.setLoanUser(ShiroUtils.getUserId());
			facReimburseApply.setUpdateTime(new Date());
			facReimburseApplyService
					.deleteFacReimburseApplyById(facReimburseApply.getId()+"");
			facReimburseApply.setId(null);
			facReimburseApply.setCreateBy(ShiroUtils.getUserId().toString());
		}
		facReimburseApply.setSubmitStatus("submit");
		return facReimburseApplyService
				.insertFacReimburseApply(facReimburseApply);
	}
	
	
	/**
	 * 新增提交报销
	 */
	@Log(title = "报销", businessType = BusinessType.INSERT)
	@PostMapping("/addSubmit2")
	@ResponseBody
	@Transactional
	public AjaxResult addSubmit2(FacReimburseApply facReimburseApply) {
		if (facReimburseApply.getId() == null) {
			// 直接添加
			facReimburseApply.setLoanUser(ShiroUtils.getUserId());
			facReimburseApply.setCreateTime(new Date());
			facReimburseApply.setCreateBy(ShiroUtils.getUserId().toString());
		} else {
			// 更新
			facReimburseApply = facReimburseApplyService
					.selectFacReimburseApplyById(facReimburseApply.getId()+"");
			facReimburseApply.setLoanUser(ShiroUtils.getUserId());
			facReimburseApply.setUpdateTime(new Date());
			facReimburseApplyService
					.deleteFacReimburseApplyById(facReimburseApply.getId()+"");
			facReimburseApply.setId(null);
			facReimburseApply.setCreateBy(ShiroUtils.getUserId().toString());
		}
		facReimburseApply.setSubmitStatus("submit"); 
		FacLoanRepayApply facLoanRepayApply= new FacLoanRepayApply();   
		facLoanRepayApply.setNum(facReimburseApply.getJKnum());
		facLoanRepayApply.setPayer(ShiroUtils.getUserId());
		facLoanRepayApply.setRepayAmount(facReimburseApplyService.selectDouble(facReimburseApply.getNum())); 
		facLoanRepayApply.setRepayMethod("冲抵");
		facLoanRepayApplyService.insertFacLoanRepayApply(facLoanRepayApply);
		return facReimburseApplyService
				.insertFacReimburseApply(facReimburseApply);
	}
	
	
	
	
	/**
	 * 新增招待费用
	 */
	@Log(title = "招待费报销", businessType = BusinessType.INSERT)
	@PostMapping("/addAll")
	@ResponseBody
	public AjaxResult addAllSave(FacReimburseApply facReimburseApply) {
		facReimburseApply.setLoanUser(ShiroUtils.getUserId());
		return facReimburseApplyService
				.insertFacReimburseApply(facReimburseApply);
	}
	/**
	 * 招待费用报销
	 */
	@GetMapping("/addAll")
	public String addAll(@RequestParam String num, ModelMap map) {
		map.put("num", num);
		return prefix + "/addAll";
	}

	 
	/**
	 * 查询报销列表
	 */
	@PostMapping("/selecthost")
	@ResponseBody
	public TableDataInfo selecthost(ReiHospitalityApply reiHospitalityApply) {
		startPage();
		reiHospitalityApply.setUser(ShiroUtils.getUserId());
		List<ReiHospitalityApply> list = facReimburseApplyService
				.selectReiHospitalityApplyList(reiHospitalityApply);
		return getDataTable(list);
	}
	/**
	 *
	 * 其他报销申请
	 */
	@GetMapping("/otherDetail")
	public String otherDetails(@RequestParam String num, ModelMap map) {
		map.put("num", num);
		return prefix + "/otherDetail";

	}

	/**
	 * 新增其他报销
	 */
	@Log(title = "招待费报销", businessType = BusinessType.INSERT)
	@PostMapping("/Arbitrarily")
	@ResponseBody
	public AjaxResult Save(FacReiAdiApply reiAdiApply) {
		return toAjax(
				facReimburseApplyService.insertFacreiAdiApply(reiAdiApply));
	}

	/**
	 * 新增交通费用
	 */
	@Log(title = "交通费报销", businessType = BusinessType.INSERT)
	@PostMapping("/tranDetail")
	@ResponseBody
	public AjaxResult tranDetailSave(ReiTrafficApply reiTrafficApply) {
		List<ReiTrafficApply> list = new ArrayList<>();
		list.add(reiTrafficApply);
		FacReimburseApply facReimburseApply = new FacReimburseApply();
		facReimburseApply.setLoanUser(ShiroUtils.getUserId());

		facReimburseApply.setTrafficReiApplyList(list);
		return toAjax(facReimburseApplyService
				.insertReiTrafficApply(reiTrafficApply));
	}

	/**
	 * 交通费用报销
	 */
	@GetMapping("/tranDetail")
	public String tranDetail(@RequestParam String num, ModelMap map) {
		map.put("num", num);
		return prefix + "/tranDetail";
	}

	/**
	 * 查看报销详情
	 */
	@PostMapping("/query")
	@ResponseBody
	public TableDataInfo detail1(String num) {
		startPage();
		FacReimburseApply facReimburseApply = facReimburseApplyService
				.deatil(num);
		if (facReimburseApply != null) {
			List<FacReimburseApply> facReimburseApplies = new ArrayList<>();
			facReimburseApplies.add(facReimburseApply);
			return getDataTable(facReimburseApplies);
		} else {
			List<String> a = new ArrayList<>();
			return getDataTable(a);
		}
	}

	/**
	 * 查看招待费报销申请详情
	 */
	@PostMapping("/hospDetail")
	@ResponseBody
	public TableDataInfo hospDtail(String num) {
		startPage();
		FacReimburseApply facReimburseApply = facReimburseApplyService
				.deatil(num);
		if (facReimburseApply != null) {
			List<ReiHospitalityApply> reiAdiApplies = facReimburseApply
					.getHospitalityApplies();

			if (reiAdiApplies != null && reiAdiApplies.size() > 0) {
				return getDataTable(reiAdiApplies);
			} else {
				List<String> a = new ArrayList<>();
				return getDataTable(a);
			}
		} else {
			List<String> a = new ArrayList<>();
			return getDataTable(a);
		}
	}

	/**
	 * 查看交通费报销申请详情
	 */
	@PostMapping("/addTraDetail")
	@ResponseBody
	public TableDataInfo addTraDetail(String num) {
		startPage();
		List<ReiTrafficApply> facReimburseApply = facReimburseApplyService
				.selectReiTrafficApply(num);
		if (facReimburseApply != null) {

			return getDataTable(facReimburseApply);

		} else {
			List<String> a = new ArrayList<>();
			return getDataTable(a);
		}
	}

	/**
	 * 查看其他报销费申请详情
	 */

	@PostMapping("/otherDetail")
	@ResponseBody
	public TableDataInfo otherDetail(@RequestParam String num) {
		startPage();

		List<FacReiAdiApply> facReimburseApply = facReimburseApplyService
				.selectFacReiAdiApply(num);

		if (facReimburseApply != null) {
			return getDataTable(facReimburseApply);
		} else {
			List<String> a = new ArrayList<>();
			return getDataTable(a);
		}
	}

	/**
	 * 修改保存报销
	 */
	@Log(title = "报销", businessType = BusinessType.OTHER)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacReimburseApply facReimburseApply) {
		return toAjax(facReimburseApplyService
				.updateFacReimburseApply(facReimburseApply));
	}

	/**
	 * 修改报销
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		FacReimburseApply facReimburseApply = facReimburseApplyService
				.selectFacReimburseApplyById(id+"");
		mmap.put("fac", facReimburseApply);
		return prefix + "/edit";
	}

	/**
	 * 修改报销
	 */
	@GetMapping("/editTran/{id}")
	public String editTran(@PathVariable("id") Long id, ModelMap mmap) {
		ReiTrafficApply facReimburseApply = facReimburseApplyService
				.selectFacTransById(id);
		mmap.put("data", facReimburseApply);
		return prefix + "/editTran";
	}
	/**
	 * 修改报销
	 */
	@PostMapping("/editTran")
	@ResponseBody
	public AjaxResult editTranSave(ReiTrafficApply reiTrafficApply) {
		return toAjax(facReimburseApplyService
				.updateReiTrafficApplyById(reiTrafficApply));
	}

	/**
	 * 删除报销
	 */
	@PostMapping("/removeTran")
	@ResponseBody
	public AjaxResult removeTran(String ids) {
		return toAjax(facReimburseApplyService.deleteReiTrafficApplyById(ids));
	}

	/**
	 * 删除报销
	 */
	@Log(title = "报销", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		try {
			return toAjax(
					facReimburseApplyService.deleteFacReimburseApplyByIds(ids));
		} catch (Exception e) {
			return error(e.getMessage());
		}
	}
}
