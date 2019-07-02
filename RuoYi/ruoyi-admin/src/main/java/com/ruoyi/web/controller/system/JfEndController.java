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
import com.ruoyi.system.domain.JfEnd;
import com.ruoyi.system.domain.JfMatching;
import com.ruoyi.system.service.IJfEndService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 玖富后端 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
@Controller
@RequestMapping("/system/jfEnd")
public class JfEndController extends BaseController
{
    private String prefix = "system/jfEnd";
	
	@Autowired
	private IJfEndService jfEndService;
	
	@RequiresPermissions("system:jfEnd:view")
	@GetMapping()
	public String jfEnd()
	{
	    return prefix + "/jfEnd";
	}
	
	/**
	 * 查询玖富后端列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(JfEnd jfEnd)
	{
		startPage();
        List<JfEnd> list = jfEndService.selectJfEndList(jfEnd);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出玖富后端列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(JfEnd jfEnd)
    {
    	List<JfEnd> list = jfEndService.selectJfEndList(jfEnd);
        ExcelUtil<JfEnd> util = new ExcelUtil<JfEnd>(JfEnd.class);
        return util.exportExcel(list, "jfEnd");
    }
	
	/**
	 * 新增玖富后端
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存玖富后端
	 */
	@Log(title = "玖富后端", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(JfEnd jfEnd)
	{		
		return toAjax(jfEndService.insertJfEnd(jfEnd));
	}

	/**
	 * 修改玖富后端
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		JfEnd jfEnd = jfEndService.selectJfEndById(id);
		mmap.put("jfEnd", jfEnd);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存玖富后端
	 */
	@Log(title = "玖富后端", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(JfEnd jfEnd)
	{		
		return toAjax(jfEndService.updateJfEnd(jfEnd));
	}
	
	/**
	 * 删除玖富后端
	 */
	@Log(title = "玖富后端", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(jfEndService.deleteJfEndByIds(ids));
	}
	
	@GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<JfEnd> util = new ExcelUtil<JfEnd>(JfEnd.class);
        return util.importTemplateExcel("玖富匹配数据");
    }
    
    
    @Log(title = "玖富匹配", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<JfEnd> util = new ExcelUtil<JfEnd>(JfEnd.class);
        List<JfEnd> jeList = util.importExcel(file.getInputStream(),0,1);
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = jfEndService.importEnd(jeList, false, operName);
        return AjaxResult.success(message);
    }
}
