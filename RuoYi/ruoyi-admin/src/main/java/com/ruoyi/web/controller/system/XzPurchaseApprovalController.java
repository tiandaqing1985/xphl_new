package com.ruoyi.web.controller.system;

import java.util.Date;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.XzPurchaseApproval;
import com.ruoyi.system.service.IXzPurchaseApprovalService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 采购申请审批 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-09-24
 */
@Controller
@RequestMapping("/system/xzPurchaseApproval")
public class XzPurchaseApprovalController extends BaseController
{
    private String prefix = "system/xzPurchaseApproval";
	
	@Autowired
	private IXzPurchaseApprovalService xzPurchaseApprovalService;
	
	@RequiresPermissions("system:xzPurchaseApproval:view")
	@GetMapping()
	public String xzPurchaseApproval()
	{
	    return prefix + "/xzPurchaseApproval";
	}
	
	@GetMapping("/approvalModer")
	public String approvalModer(Long id,Model m)
	{
		m.addAttribute("id", id);
	    return prefix + "/approvalModer";
	}
	
	/**
	 * 查询采购申请审批列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(XzPurchaseApproval xzPurchaseApproval)
	{
		startPage();
        List<XzPurchaseApproval> list = xzPurchaseApprovalService.selectXzPurchaseApprovalList(xzPurchaseApproval);
		return getDataTable(list);
	}
	
	/**
	 * 同意申请
	 */
	@PostMapping("/agree")
	@ResponseBody
	public AjaxResult agree(Long applyId,String remark)
	{	
		XzPurchaseApproval xz = new XzPurchaseApproval();
		xz.setApplyId(applyId);
		xz.setApprovalState("1");//1同意 2驳回 3未操作
		xz.setApprovalId(ShiroUtils.getUserId());//审批人id
		xz.setRemarks(remark);
		xz.setApprovalDate(new Date());
		xz.setCreateBy(ShiroUtils.getUserId().toString());
		xz.setCreateTime(new Date());
		//新增一条审批记录
		xzPurchaseApprovalService.insertXzPurchaseApproval(xz);
		return toAjax(1);
	}
	

	/**
	 * 驳回申请
	 */
	@PostMapping("/reject")
	@ResponseBody
	public AjaxResult reject(Long applyId, String remark)
	{	
		XzPurchaseApproval xz = new XzPurchaseApproval();
		xz.setApplyId(applyId);
		xz.setApprovalState("2");//1同意 2驳回 3未操作
		xz.setApprovalId(ShiroUtils.getUserId());//审批人id
		xz.setRemarks(remark);
		xz.setApprovalDate(new Date());
		xz.setCreateBy(ShiroUtils.getUserId().toString());
		xz.setCreateTime(new Date());
		//新增一条审批记录
		xzPurchaseApprovalService.insertXzPurchaseApproval(xz);
		return toAjax(1);
	}
	
	/**
	 * 导出采购申请审批列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XzPurchaseApproval xzPurchaseApproval)
    {
    	List<XzPurchaseApproval> list = xzPurchaseApprovalService.selectXzPurchaseApprovalList(xzPurchaseApproval);
        ExcelUtil<XzPurchaseApproval> util = new ExcelUtil<XzPurchaseApproval>(XzPurchaseApproval.class);
        return util.exportExcel(list, "xzPurchaseApproval");
    }
	
	/**
	 * 新增采购申请审批
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存采购申请审批
	 */
	@Log(title = "采购申请审批", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(XzPurchaseApproval xzPurchaseApproval)
	{		
		return toAjax(xzPurchaseApprovalService.insertXzPurchaseApproval(xzPurchaseApproval));
	}

	/**
	 * 修改采购申请审批
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		XzPurchaseApproval xzPurchaseApproval = xzPurchaseApprovalService.selectXzPurchaseApprovalById(id);
		mmap.put("xzPurchaseApproval", xzPurchaseApproval);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存采购申请审批
	 */
	@Log(title = "采购申请审批", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(XzPurchaseApproval xzPurchaseApproval)
	{		
		return toAjax(xzPurchaseApprovalService.updateXzPurchaseApproval(xzPurchaseApproval));
	}
	
	/**
	 * 删除采购申请审批
	 */
	@Log(title = "采购申请审批", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(xzPurchaseApprovalService.deleteXzPurchaseApprovalByIds(ids));
	}
	
}
