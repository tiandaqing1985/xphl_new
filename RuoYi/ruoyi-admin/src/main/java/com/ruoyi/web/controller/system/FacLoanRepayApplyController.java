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

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.finance.FacLoanApply;
import com.ruoyi.system.domain.finance.FacLoanRepayApply;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.IFacLoanRepayApplyService;

/**
 * 借款还款 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-10-12
 */
@Controller
@RequestMapping("/system/facLoanRepayApply")
public class FacLoanRepayApplyController extends BaseController
{
    private String prefix = "system/facLoanRepayApply";
    @Autowired
    private ISysUserService sysUserService;
	@Autowired
	private IFacLoanRepayApplyService facLoanRepayApplyService;
	
	@RequiresPermissions("system:facLoanRepayApply:view")
	@GetMapping()
	public String facLoanRepayApply()
	{
	    return prefix + "/facLoanRepayApply";
	}
	
	/**
	 * 查询借款还款列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacLoanRepayApply facLoanRepayApply)
	{
		startPage();
        List<FacLoanRepayApply> list = facLoanRepayApplyService.selectFacLoanRepayApplyList(facLoanRepayApply);
        
        for (FacLoanRepayApply v : list) { 
        v.setPayerName(sysUserService.selectUserById(v.getPayer()).getUserName());
        }
		return getDataTable(list);
	}
	
	
	/**
	 * 导出借款还款列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacLoanRepayApply facLoanRepayApply)
    {
    	List<FacLoanRepayApply> list = facLoanRepayApplyService.selectFacLoanRepayApplyList(facLoanRepayApply);
        ExcelUtil<FacLoanRepayApply> util = new ExcelUtil<FacLoanRepayApply>(FacLoanRepayApply.class);
        return util.exportExcel(list, "facLoanRepayApply");
    }
	
	/**
	 * 新增借款还款
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存借款还款
	 */
	@Log(title = "借款还款", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FacLoanRepayApply facLoanRepayApply)
	{		
		return toAjax(facLoanRepayApplyService.insertFacLoanRepayApply(facLoanRepayApply));
	}

	/**
	 * 修改借款还款
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		FacLoanRepayApply facLoanRepayApply = facLoanRepayApplyService.selectFacLoanRepayApplyById(id);
		mmap.put("facLoanRepayApply", facLoanRepayApply);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存借款还款
	 */
	@Log(title = "借款还款", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacLoanRepayApply facLoanRepayApply)
	{		 
		facLoanRepayApply.setMethod(facLoanRepayApply.getRepayAmount()); 
		facLoanRepayApply.setAmount(facLoanRepayApply.getArrears()-facLoanRepayApply.getRepayAmount());
		facLoanRepayApply.setRepayTime(new Date());
		
		if(facLoanRepayApply.getAmount()>0){
			facLoanRepayApply.setStates("还款中");
		}else{
			facLoanRepayApply.setStates("还款结束");
		} 
		facLoanRepayApply.setVoucher("1");
		return toAjax(facLoanRepayApplyService.updateFacLoanRepayApply(facLoanRepayApply));
	}
	
	/**
	 * 删除借款还款
	 */
	@Log(title = "借款还款", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(facLoanRepayApplyService.deleteFacLoanRepayApplyByIds(ids));
	}
	
}
