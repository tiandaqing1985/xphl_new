package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.system.domain.finance.FacCostDetailApply;
import com.ruoyi.system.domain.finance.FacCostDetailReimburse;
import com.ruoyi.system.service.finance.IFacCostDetailReimburseService;
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
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 差旅申请详细报销列 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-11-12
 */
@Controller
@RequestMapping("/system/facCostDetailReimburse")
public class FacCostDetailReimburseController extends BaseController
{
    private String prefix = "system/facCostDetailReimburse";
	
	@Autowired
	private IFacCostDetailReimburseService facCostDetailReimburseService;
	
	@RequiresPermissions("system:facCostDetailReimburse:view")
	@GetMapping()
	public String facCostDetailReimburse()
	{
	    return prefix + "/facCostDetailReimburse";
	}
	
	/**
	 * 查询差旅申请详细报销列列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacCostDetailReimburse facCostDetailReimburse)
	{
		startPage();
		facCostDetailReimburse.setReason(null);
        List<FacCostDetailReimburse> list = facCostDetailReimburseService.selectFacCostDetailReimburseList(facCostDetailReimburse);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出差旅申请详细报销列列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacCostDetailReimburse facCostDetailReimburse)
    {
    	List<FacCostDetailReimburse> list = facCostDetailReimburseService.selectFacCostDetailReimburseList(facCostDetailReimburse);
        ExcelUtil<FacCostDetailReimburse> util = new ExcelUtil<FacCostDetailReimburse>(FacCostDetailReimburse.class);
        return util.exportExcel(list, "facCostDetailReimburse");
    }
	
	/**
	 * 新增差旅申请详细报销列
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存差旅申请详细报销列
	 */
	@Log(title = "差旅申请详细报销列", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FacCostDetailReimburse facCostDetailReimburse)
	{		
		return toAjax(facCostDetailReimburseService.insertFacCostDetailReimburse(facCostDetailReimburse));
	}

	/**
	 * 修改差旅申请详细报销列
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		FacCostDetailReimburse facCostDetailReimburse = facCostDetailReimburseService.selectFacCostDetailReimburseById(id);
		mmap.put("facCostDetailApply", facCostDetailReimburse);
		return "system/facCostApply/editTra";
	}
	
	/**
	 * 修改保存差旅申请详细报销列
	 */
	@Log(title = "差旅申请详细报销列", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacCostDetailReimburse facCostDetailReimburse)
	{		
		return toAjax(facCostDetailReimburseService.updateFacCostDetailReimburse(facCostDetailReimburse));
	}
	
	/**
	 * 删除差旅申请详细报销列
	 */
	@Log(title = "差旅申请详细报销列", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(facCostDetailReimburseService.deleteFacCostDetailReimburseByIds(ids));
	}
	
}
