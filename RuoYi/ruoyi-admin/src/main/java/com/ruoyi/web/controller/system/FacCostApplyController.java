package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.finance.FacCostApply;
import com.ruoyi.system.domain.finance.FacCostDetailApply;
import com.ruoyi.system.domain.finance.FacCostPutupApply;
import com.ruoyi.system.domain.finance.FacReimburseApply;
import com.ruoyi.system.domain.finance.FacUserApproval;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.IFacCostApplyService;
import com.ruoyi.system.service.finance.IFacCostPutupApplyService;
import com.ruoyi.system.service.finance.IFacNumberTableService;
import com.ruoyi.system.service.finance.IFacReimburseApplyService;
import com.ruoyi.system.service.finance.IFacUserApprovalService;

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
	@Autowired
	private IFacUserApprovalService facUserApprovalService;
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private IFacReimburseApplyService facReimburseApplyService;
	@Autowired
	private IFacNumberTableService facNumberTableService;
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

		for (FacCostApply v : list) {

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

	@GetMapping("/addSave")
	public String addSave(String id, ModelMap map) {
		map.put("id", id);
		FacCostApply facCostApply = facCostApplyService.selectFacCostApplyById(Long.valueOf(id).longValue()); 
		map.put("amount", facCostApplyService.selectDouble(facCostApply.getNum()));
		return prefix + "/addSave";
	}

	/**
	 * 新增团建申请
	 *
	 * @throws Exception
	 */
	@GetMapping("/baoxiao")
	public String Bao(String id, ModelMap mmap) {
		FacCostApply facCostApply = facCostApplyService
				.selectFacCostApplyById(Long.valueOf(id));
		FacReimburseApply facReimburseApply = new FacReimburseApply();
		facReimburseApply.setNum(
				facNumberTableService.getNum("BX", ShiroUtils.getDateId()));
		facReimburseApply.setName(facCostApply.getBusName());// 报销名
		facReimburseApply.setAmount(facCostApply.getMoneyEs());
		facReimburseApply.setLoanUser(facCostApply.getUserId());
		facReimburseApply.setCreateTime(ShiroUtils.getDate());
		facReimburseApply.setReimburseTime(facCostApply.getApplicationTime());
		facReimburseApply.setReason(facCostApply.getReason());
		facReimburseApply.setType("差旅报销");
		facReimburseApply.setJKnum(facCostApply.getNum());
		mmap.put("facReimburseApply", facReimburseApply);
		return prefix + "/baoxiao";
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
		List<FacCostPutupApply> list = facCostPutupApplyService
				.selectFacCostPutupApplyList(facCostPutupApply);
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
		mmp.put("num",
				facNumberTableService.getNum("CL", ShiroUtils.getDateId()));
		return prefix + "/add";
	}

	/**
	 * 新增保存差旅申请
	 */
	@Log(title = "差旅申请", businessType = BusinessType.INSERT) 
	@PostMapping("/add")
	@ResponseBody 
	public AjaxResult adSave(FacCostApply facCostApply) {
		facCostApply.setUserId(ShiroUtils.getUserId());
		facCostApply.setApplicationTime(new Date());
		if (facCostApply.getId() == null) {
			facCostApply.setUserId(ShiroUtils.getUserId());
		} else {
			// 更新
			facCostApply = facCostApplyService
					.selectFacCostApplyById(facCostApply.getId());
			facCostApplyService
					.deleteFacCostApplyByIds(facCostApply.getId() + "");
		}
		
//		if(facCostApply.getMoneyEs()!=null){
//			
//		}else{
//			//return  AjaxResult.success();
//			return AjaxResult.error("请补全数据");
//		} 
		return toAjax(facCostApplyService.insertFacCostApply(facCostApply));
	}

	/**
	 * 新增保存
	 *
	 * @throws Exception
	 */
	@Log(title = "差旅申请", businessType = BusinessType.INSERT)
	@PostMapping("/addSove")
	@ResponseBody
	public AjaxResult addSove(FacCostApply facCostApply) throws Exception {
		facCostApply.setUserId(ShiroUtils.getUserId());
		facCostApply.setApplicationTime(new Date());
		return toAjax(facCostApplyService.insertApply(facCostApply));
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
		map.put("busName", facReimburseApplies.get(0).getBusName());
		map.put("outTime", facReimburseApplies.get(0).getOutTime());
		map.put("backTimeEs", facReimburseApplies.get(0).getBackTimeEs());
		map.put("userName", ShiroUtils.getSysUser().getUserName());
		map.put("moneyEs", facReimburseApplies.get(0).getMoneyEs());

		return prefix + "/costDetail";
	}

	/**
	 * 行程安排
	 */
	@GetMapping("/tranDetail")
	public String tranDetail(@RequestParam String num, ModelMap map) {
		map.put("num", num);
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
		map.put("num", num);
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

	/**
	 * 修改交通
	 */
	@GetMapping("/editTra/{id}")
	public String editTran(@PathVariable("id") Long id, ModelMap mmap) {
		FacCostDetailApply facCostDetailApply = facCostApplyService
				.selectFacCostDetailApplyById(id);
		mmap.put("facCostDetailApply", facCostDetailApply);
		return prefix + "/editTra";
	}

	
	
	
	
	@GetMapping("/clDetail/{id}")
	public String clDetail(@PathVariable("id") Long id, ModelMap map) {
		FacCostApply facCostApply = new FacCostApply();
		facCostApply.setId(id);
		List<FacCostApply> facReimburseApplies = facCostApplyService
				.selectFacCostApplyList(facCostApply);
		map.put("rid", id);
		map.put("num", facReimburseApplies.get(0).getNum());
		map.put("status", facReimburseApplies.get(0).getStatus());
		return prefix + "/clDetail";
	}
	/**
	 * 修改住宿
	 */
	@GetMapping("/editPut/{id}")
	public String editPut(@PathVariable("id") Long id, ModelMap mmap) {
		FacCostPutupApply facCostPutupApply = facCostApplyService
				.selectFacCostPutupApplyById(id);
		mmap.put("facCostPutupApply", facCostPutupApply);
		return prefix + "/editPut";
	}

	/**
	 * 修改保存差旅交通申请详细列
	 */

	@Log(title = "差旅申请详细列 ", businessType = BusinessType.UPDATE)
	@PostMapping("/editTra")
	@ResponseBody
	public AjaxResult editSave(FacCostDetailApply facCostDetailApply) {
		return toAjax(facCostApplyService
				.updateFacCostDetailApply(facCostDetailApply));
	}

	/**
	 * 修改保存差旅住宿
	 */

	@Log(title = "差旅住宿", businessType = BusinessType.UPDATE)
	@PostMapping("/editPut")
	@ResponseBody
	public AjaxResult editSave(FacCostPutupApply facCostPutupApply) {
		return toAjax(facCostPutupApplyService
				.updateFacCostPutupApply(facCostPutupApply));
	}

	/**
	 * 删除差旅住宿
	 */

	@Log(title = "差旅", businessType = BusinessType.DELETE)
	@PostMapping("/removeTra")
	@ResponseBody
	public AjaxResult removeTra(String id) {
		return toAjax(facCostApplyService.deleteFacCostDetailApplyByIds(id));
	}

	/**
	 * 删除差旅申请详细列
	 */

	@Log(title = "差旅申请详细列 ", businessType = BusinessType.DELETE)
	@PostMapping("/removePut")
	@ResponseBody
	public AjaxResult removePut(String id) {
		return toAjax(
				facCostPutupApplyService.deleteFacCostPutupApplyByIds(id));
	}
	
	/**
	 * 新增团建申请
	 *
	 * @throws Exception
	 */
	@GetMapping("/baoxiaoEdit") 
	public String Baoxiao(String id, ModelMap mmap)
	{
		FacCostApply facCostApply = facCostApplyService
				.selectFacCostApplyById( Long.valueOf(id).longValue());
		mmap.put("facCostApply", facCostApply);
		return prefix + "/baoxiaoEdit";
	} 
	
	/**
	 * 修改保存团建申请
	 */
	@Transactional
	@Log(title = "差旅申请", businessType = BusinessType.UPDATE)
	@PostMapping("/addEdit")
	@ResponseBody
	public AjaxResult addEdit(FacCostApply facCostApply) {
		FacCostApply facCostApplys = facCostApplyService
				.selectFacCostApplyById(facCostApply.getId());
		FacReimburseApply facReimburseApply = new FacReimburseApply();
		facReimburseApply.setNum(
				facNumberTableService.getNum("BX", ShiroUtils.getDateId()));
		facReimburseApply.setName(facCostApply.getBusName());// 报销名
		facReimburseApply.setAmount(facCostApply.getMoneyEs());
		facReimburseApply.setLoanUser(facCostApply.getUserId());
		facReimburseApply.setCreateTime(ShiroUtils.getDate());
		facReimburseApply.setReimburseTime(facCostApplys.getApplicationTime());
		facReimburseApply.setReason(facCostApply.getReason());
		facReimburseApply.setType("差旅报销");
		facReimburseApply.setJKnum(facCostApply.getNum());
		facReimburseApply.setLoanUser(ShiroUtils.getUserId());
		facReimburseApply.setCreateBy(ShiroUtils.getUserId().toString());
		facCostApply.setStatus("6");
		if (facCostApply.getMoneyEs() <= facCostApplys.getMoneyEs()) {
			// 不需要二次审批
			facReimburseApply.setStatus("1");
			facReimburseApply.setSubmitStatus("submit");
			facReimburseApplyService.insertApply(facReimburseApply);
		} else {
			// 需要二次审批
			facCostApply.setNum(
					facNumberTableService.getNum("CL", ShiroUtils.getDateId()));
			facCostApply.setUserId(ShiroUtils.getUserId());
			facCostApply.setBusName(facCostApplys.getBusName());
			facCostApply.setApplicationTime(facCostApplys.getApplicationTime());
			FacCostApply fac = facCostApply;
			fac.setStatus("1");
			List<FacCostDetailApply> list = facCostApplyService
					.deatils(facCostApplys.getNum());
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					list.get(i).setNum(facCostApply.getNum());
					facCostApplyService.insertFacCostDetailApply(list.get(i));
				}
			}

			FacCostPutupApply facCostPutupApply = new FacCostPutupApply();
			facCostPutupApply.setNum(facCostApplys.getNum());
			List<FacCostPutupApply> putList = facCostPutupApplyService
					.selectFacCostPutupApplyList(facCostPutupApply);

			if (putList != null) {
				for (int i = 0; i < putList.size(); i++) {
					putList.get(i).setNum(facCostApply.getNum());
					facCostApplyService.insertFacCostPutupApply(putList.get(i));
				}
			}

			facCostApplyService.insertFacCostApply(fac);
		}
		return toAjax(facCostApplyService.updateFacCostApply(facCostApply));
	}

}
