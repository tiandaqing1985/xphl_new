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
import com.ruoyi.system.domain.OaOutApproval;
import com.ruoyi.system.service.IOaOutApprovalService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 外出报备审批 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-08-01
 */
@Controller
@RequestMapping("/system/oaOutApproval")
public class OaOutApprovalController extends BaseController
{
    private String prefix = "system/oaOutApproval";
	
	@Autowired
	private IOaOutApprovalService oaOutApprovalService;
	
	@RequiresPermissions("system:oaOutApproval:view")
	@GetMapping()
	public String oaOutApproval()
	{
	    return prefix + "/oaOutApproval";
	}
	
	/**
	 * 查询外出报备审批列表
	 */
	@RequiresPermissions("system:oaOutApproval:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(OaOutApproval oaOutApproval)
	{
		startPage();
        List<OaOutApproval> list = oaOutApprovalService.selectOaOutApprovalList(oaOutApproval);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出外出报备审批列表
	 */
	@RequiresPermissions("system:oaOutApproval:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OaOutApproval oaOutApproval)
    {
    	List<OaOutApproval> list = oaOutApprovalService.selectOaOutApprovalList(oaOutApproval);
        ExcelUtil<OaOutApproval> util = new ExcelUtil<OaOutApproval>(OaOutApproval.class);
        return util.exportExcel(list, "oaOutApproval");
    }
	
	/**
	 * 新增外出报备审批
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存外出报备审批
	 */
	@RequiresPermissions("system:oaOutApproval:add")
	@Log(title = "外出报备审批", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(OaOutApproval oaOutApproval)
	{		
		return toAjax(oaOutApprovalService.insertOaOutApproval(oaOutApproval));
	}

	/**
	 * 修改外出报备审批
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		OaOutApproval oaOutApproval = oaOutApprovalService.selectOaOutApprovalById(id);
		mmap.put("oaOutApproval", oaOutApproval);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存外出报备审批
	 */
	@RequiresPermissions("system:oaOutApproval:edit")
	@Log(title = "外出报备审批", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(OaOutApproval oaOutApproval)
	{		
		return toAjax(oaOutApprovalService.updateOaOutApproval(oaOutApproval));
	}
	
	/**
	 * 删除外出报备审批
	 */
	@RequiresPermissions("system:oaOutApproval:remove")
	@Log(title = "外出报备审批", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(oaOutApprovalService.deleteOaOutApprovalByIds(ids));
	}
	
}
