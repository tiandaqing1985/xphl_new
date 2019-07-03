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
import com.ruoyi.system.service.IJfZhanxianService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 玖富展现 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
@Controller
@RequestMapping("/system/jfZhanxian")
public class JfZhanxianController extends BaseController
{
    private String prefix = "system/jfZhanxian";
	
	@Autowired
	private IJfZhanxianService jfZhanxianService;
	
	@RequiresPermissions("system:jfZhanxian:view")
	@GetMapping()
	public String jfZhanxian()
	{
	    return prefix + "/jfZhanxian";
	}
	
	/**
	 * 查询玖富展现列表
	 */
/*	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(JfZhanxian jfZhanxian)
	{
		startPage();
        List<JfZhanxian> list = jfZhanxianService.selectJfZhanxianList(jfZhanxian);
		return getDataTable(list);
	}*/
	
	
	
	/**
	 * 查询玖富展现列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(JfZhanxian jfZhanxian,String selectflag)
	{
		startPage();
        List<JfZhanxian> list = jfZhanxianService.selectJfZhanxianSumList(jfZhanxian,selectflag);
		return getDataTable(list);
	}
	
	/**
	 * 导出玖富展现列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(JfZhanxian jfZhanxian ,String selectflag)
    {
    	List<JfZhanxian> list = jfZhanxianService.selectJfZhanxianSumList(jfZhanxian,selectflag);
        ExcelUtil<JfZhanxian> util = new ExcelUtil<JfZhanxian>(JfZhanxian.class);
        return util.exportExcel(list, "jfZhanxian");
    }
	
	/**
	 * 新增玖富展现
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存玖富展现
	 */
	@Log(title = "玖富展现", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(JfZhanxian jfZhanxian)
	{		
		return toAjax(jfZhanxianService.insertJfZhanxian(jfZhanxian));
	}

	/**
	 * 修改玖富展现
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		JfZhanxian jfZhanxian = jfZhanxianService.selectJfZhanxianById(id);
		mmap.put("jfZhanxian", jfZhanxian);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存玖富展现
	 */
	@Log(title = "玖富展现", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(JfZhanxian jfZhanxian)
	{		
		return toAjax(jfZhanxianService.updateJfZhanxian(jfZhanxian));
	}
	
	/**
	 * 删除玖富展现
	 */
	@Log(title = "玖富展现", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(jfZhanxianService.deleteJfZhanxianByIds(ids));
	}
	

	@PostMapping("/createJfZhanxianData")
	@ResponseBody
	public AjaxResult createJfZhanxianData(JfZhanxian jfZhanxian)
	{		
		return toAjax(jfZhanxianService.createJfZhanxianData(jfZhanxian));
	}
	
	
	@PostMapping( "/deleteAllJfZhanxian")
	@ResponseBody
	public AjaxResult deleteAllJfZhanxian()
	{		
		return toAjax(jfZhanxianService.deleteAllJfZhanxian());
	}
	
	
}
