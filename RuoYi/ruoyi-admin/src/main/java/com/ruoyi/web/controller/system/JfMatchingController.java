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
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.JfMatching;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.IJfMatchingService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 玖富匹配 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-06-28
 */
@Controller
@RequestMapping("/system/jfMatching")
public class JfMatchingController extends BaseController
{
    private String prefix = "system/jfMatching";
	
	@Autowired
	private IJfMatchingService jfMatchingService;
	
	@RequiresPermissions("system:jfMatching:view")
	@GetMapping()
	public String jfMatching()
	{
	    return prefix + "/jfMatching";
	}
	
	/**
	 * 查询玖富匹配列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(JfMatching jfMatching)
	{
		startPage();
        List<JfMatching> list = jfMatchingService.selectJfMatchingList(jfMatching);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出玖富匹配列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(JfMatching jfMatching)
    {
    	List<JfMatching> list = jfMatchingService.selectJfMatchingList(jfMatching);
        ExcelUtil<JfMatching> util = new ExcelUtil<JfMatching>(JfMatching.class);
        return util.exportExcel(list, "jfMatching");
    }
	
	/**
	 * 新增玖富匹配
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存玖富匹配
	 */
	@Log(title = "玖富匹配", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(JfMatching jfMatching)
	{		
		return toAjax(jfMatchingService.insertJfMatching(jfMatching));
	}

	/**
	 * 修改玖富匹配
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		JfMatching jfMatching = jfMatchingService.selectJfMatchingById(id);
		mmap.put("jfMatching", jfMatching);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存玖富匹配
	 */
	@Log(title = "玖富匹配", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(JfMatching jfMatching)
	{		
		return toAjax(jfMatchingService.updateJfMatching(jfMatching));
	}
	
	/**
	 * 删除玖富匹配
	 */
	@RequiresPermissions("system:jfMatching:remove")
	@Log(title = "玖富匹配", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(jfMatchingService.deleteJfMatchingByIds(ids));
	}
	
	
	@GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<JfMatching> util = new ExcelUtil<JfMatching>(JfMatching.class);
        return util.importTemplateExcel("玖富匹配数据");
    }
    
    
    @Log(title = "玖富匹配", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<JfMatching> util = new ExcelUtil<JfMatching>(JfMatching.class);
        List<JfMatching> jfList = util.importExcel(file.getInputStream(),0,1);
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = jfMatchingService.importMatching(jfList, false, operName);
        return AjaxResult.success(message);
    }
}
