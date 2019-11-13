package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.system.domain.finance.FacCostPutupApply;
import com.ruoyi.system.domain.finance.FacCostPutupReimburse;
import com.ruoyi.system.service.finance.IFacCostPutupReimburseService;
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
 * 差旅住宿报销 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-11-12
 */
@Controller
@RequestMapping("/system/facCostPutupReimburse")
public class FacCostPutupReimburseController extends BaseController
{
    private String prefix = "system/facCostPutupReimburse";
	
	@Autowired
	private IFacCostPutupReimburseService facCostPutupReimburseService;
	
	@RequiresPermissions("system:facCostPutupReimburse:view")
	@GetMapping()
	public String facCostPutupReimburse()
	{
	    return prefix + "/facCostPutupReimburse";
	}
	
	/**
	 * 查询差旅住宿报销列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacCostPutupReimburse facCostPutupReimburse)
	{
		startPage();
        List<FacCostPutupReimburse> list = facCostPutupReimburseService.selectFacCostPutupReimburseList(facCostPutupReimburse);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出差旅住宿报销列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacCostPutupReimburse facCostPutupReimburse)
    {
    	List<FacCostPutupReimburse> list = facCostPutupReimburseService.selectFacCostPutupReimburseList(facCostPutupReimburse);
        ExcelUtil<FacCostPutupReimburse> util = new ExcelUtil<FacCostPutupReimburse>(FacCostPutupReimburse.class);
        return util.exportExcel(list, "facCostPutupReimburse");
    }
	
	/**
	 * 新增差旅住宿报销
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存差旅住宿报销
	 */
	@Log(title = "差旅住宿报销", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FacCostPutupReimburse facCostPutupReimburse)
	{		
		return toAjax(facCostPutupReimburseService.insertFacCostPutupReimburse(facCostPutupReimburse));
	}

	/**
	 * 修改差旅住宿报销
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		FacCostPutupReimburse facCostPutupReimburse = facCostPutupReimburseService.selectFacCostPutupReimburseById(id);
		mmap.put("facCostPutupApply", facCostPutupReimburse);
		return "system/facCostApply/editPut";

	}
	
	/**
	 * 修改保存差旅住宿报销
	 */
	@Log(title = "差旅住宿报销", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacCostPutupReimburse facCostPutupReimburse)
	{		
		return toAjax(facCostPutupReimburseService.updateFacCostPutupReimburse(facCostPutupReimburse));
	}
	
	/**
	 * 删除差旅住宿报销
	 */
	@Log(title = "差旅住宿报销", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(facCostPutupReimburseService.deleteFacCostPutupReimburseByIds(ids));
	}
	
}
