package com.ruoyi.web.controller.system;

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
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.IdWorker;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.finance.FacCollectApply;
import com.ruoyi.system.service.finance.IFacCollectApplyService;

/**
 * 团建申请 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-09-03
 */
@Controller
@RequestMapping("/system/facCollectApply")
public class FacCollectApplyController extends BaseController
{
    private String prefix = "system/facCollectApply";
	
	@Autowired
	private IFacCollectApplyService facCollectApplyService;
	
	@RequiresPermissions("system:facCollectApply:view")
	@GetMapping()
	public String facCollectApply()
	{
	    return prefix + "/facCollectApply";
	}
	
	/**
	 * 查询团建申请列表
	 */

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacCollectApply facCollectApply)
	{
		startPage();
		facCollectApply.setApplicant(ShiroUtils.getUserId());
        List<FacCollectApply> list = facCollectApplyService.selectFacCollectApplyList(facCollectApply);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出团建申请列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacCollectApply facCollectApply)
    {
    	List<FacCollectApply> list = facCollectApplyService.selectFacCollectApplyList(facCollectApply);
        ExcelUtil<FacCollectApply> util = new ExcelUtil<FacCollectApply>(FacCollectApply.class);
        return util.exportExcel(list, "facCollectApply");
    }
	
	/**
	 * 新增团建申请
	 */
	@GetMapping("/add")
	public String add(ModelMap mmp) {
		IdWorker idWorker = new IdWorker(0, 1);
		mmp.put("num", "TJ" + idWorker.nextId());
		mmp.put("deptName",ShiroUtils.getDeptId());
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存团建申请
	 */
	@Log(title = "团建申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FacCollectApply facCollectApply)
	{		
		facCollectApply.setApplicant(ShiroUtils.getUserId());
		facCollectApply.setDeptName(""+ShiroUtils.getDeptId()); 
		return toAjax(facCollectApplyService.insertFacCollectApply(facCollectApply));
	}

	/**
	 * 修改团建申请
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		FacCollectApply facCollectApply = facCollectApplyService.selectFacCollectApplyById(id);
		mmap.put("facCollectApply", facCollectApply);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存团建申请
	 */
	@Log(title = "团建申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacCollectApply facCollectApply)
	{		
		return toAjax(facCollectApplyService.updateFacCollectApply(facCollectApply));
	}
	
	/**
	 * 删除团建申请
	 */
	@Log(title = "团建申请", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(facCollectApplyService.deleteFacCollectApplyByIds(ids));
	}
	
}
