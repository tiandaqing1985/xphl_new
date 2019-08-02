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
import com.ruoyi.system.domain.OaOut;
import com.ruoyi.system.domain.OutApproval;
import com.ruoyi.system.service.IOaOutService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 外出报备 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-08-01
 */
@Controller
@RequestMapping("/system/oaOut")
public class OaOutController extends BaseController
{
    private String prefix = "system/oaOut";
	
	@Autowired
	private IOaOutService oaOutService;
	
	@RequiresPermissions("system:oaOut:view")
	@GetMapping()
	public String oaOut()
	{
	    return prefix + "/oaOut";
	}
	
	/**
	 * 查询外出报备列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(OaOut oaOut)
	{
		startPage();
		oaOut.setUserId(ShiroUtils.getUserId());
        List<OutApproval> list = oaOutService.selectOutApprovalList(oaOut);
		return getDataTable(list);
	}
	
	@GetMapping("/toUnApprovalList")
	public String toUnApprovalList()
	{
	    return prefix + "/unApprovalList";
	}
	
	/**
	 * 查询外出报备待审批列表
	 */
	@PostMapping("/unApprovalList")
	@ResponseBody
	public TableDataInfo unApprovalList(OaOut oaOut)
	{
		startPage();
		oaOut.setApprovalId(ShiroUtils.getUserId());
		oaOut.setApprovalState(3L);//审批状态（1同意，2驳回 ，3未操作）
        List<OaOut> list = oaOutService.selectOaOutList(oaOut);
		return getDataTable(list);
	}
	
	/**
	 * 查询外出报备我的审批列表
	 */
	@PostMapping("/myApprovalList")
	@ResponseBody
	public TableDataInfo myApprovalList(OaOut oaOut)
	{
		startPage();
		oaOut.setApprovalId(ShiroUtils.getUserId());
		if(oaOut.getApprovalState() != null && oaOut.getApprovalState().longValue() != 4L){
			 List<OaOut> list = oaOutService.selectOaOutList(oaOut);
				return getDataTable(list);
		}
		oaOut.setApprovalState(4L);//审批状态（1同意，2驳回 ，3未操作）
        List<OaOut> list = oaOutService.selectOaOutList(oaOut);
		return getDataTable(list);
	}
	/**
	 * 导出外出报备列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OaOut oaOut)
    {
    	List<OaOut> list = oaOutService.selectOaOutList(oaOut);
        ExcelUtil<OaOut> util = new ExcelUtil<OaOut>(OaOut.class);
        return util.exportExcel(list, "oaOut");
    }
	
	/**
	 * 新增外出报备
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存外出报备
	 */
	@Log(title = "外出报备", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(OaOut oaOut)
	{		
		oaOut.setUserId(ShiroUtils.getUserId());
		return toAjax(oaOutService.insertOaOut(oaOut));
	}

	/**
	 * 修改外出报备
	 */
	@GetMapping("/edit/{outId}")
	public String edit(@PathVariable("outId") Long outId, ModelMap mmap)
	{
		OaOut oaOut = oaOutService.selectOaOutById(outId);
		mmap.put("oaOut", oaOut);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存外出报备
	 */
	@Log(title = "外出报备", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(OaOut oaOut)
	{		
		return toAjax(oaOutService.updateOaOut(oaOut));
	}
	
	/**
	 * 删除外出报备
	 */
	@Log(title = "外出报备", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(oaOutService.deleteOaOutByIds(ids));
	}
	
}
