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
import com.ruoyi.system.domain.JfZhanxian;
import com.ruoyi.system.domain.MfwysZhanxian;
import com.ruoyi.system.service.IMfwysZhanxianService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 马蜂窝原生展现 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-05
 */
@Controller
@RequestMapping("/system/mfwysZhanxian")
public class MfwysZhanxianController extends BaseController
{
    private String prefix = "system/mfwysZhanxian";
	
	@Autowired
	private IMfwysZhanxianService mfwysZhanxianService;
	
	@RequiresPermissions("system:mfwysZhanxian:view")
	@GetMapping()
	public String mfwysZhanxian()
	{
	    return prefix + "/mfwysZhanxian";
	}
	
	/**
	 * 查询马蜂窝原生展现列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MfwysZhanxian mfwysZhanxian)
	{
		startPage();
        List<MfwysZhanxian> list = mfwysZhanxianService.selectMfwysZhanxianList(mfwysZhanxian);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出马蜂窝原生展现列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MfwysZhanxian mfwysZhanxian)
    {
    	List<MfwysZhanxian> list = mfwysZhanxianService.selectMfwysZhanxianList(mfwysZhanxian);
        ExcelUtil<MfwysZhanxian> util = new ExcelUtil<MfwysZhanxian>(MfwysZhanxian.class);
        return util.exportExcel(list, "mfwysZhanxian");
    }
	
	/**
	 * 新增马蜂窝原生展现
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存马蜂窝原生展现
	 */
	@Log(title = "马蜂窝原生展现", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MfwysZhanxian mfwysZhanxian)
	{		
		return toAjax(mfwysZhanxianService.insertMfwysZhanxian(mfwysZhanxian));
	}

	/**
	 * 修改马蜂窝原生展现
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		MfwysZhanxian mfwysZhanxian = mfwysZhanxianService.selectMfwysZhanxianById(id);
		mmap.put("mfwysZhanxian", mfwysZhanxian);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存马蜂窝原生展现
	 */
	@Log(title = "马蜂窝原生展现", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MfwysZhanxian mfwysZhanxian)
	{		
		return toAjax(mfwysZhanxianService.updateMfwysZhanxian(mfwysZhanxian));
	}
	
	/**
	 * 删除马蜂窝原生展现
	 */
	@Log(title = "马蜂窝原生展现", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(mfwysZhanxianService.deleteMfwysZhanxianByIds(ids));
	}
	
	
	@PostMapping("/createMfwysZhanxianData")
	@ResponseBody
	public AjaxResult createMfwysZhanxianData()
	{		
		return toAjax(mfwysZhanxianService.createMfwysZhanxianData());
	}
	
}
