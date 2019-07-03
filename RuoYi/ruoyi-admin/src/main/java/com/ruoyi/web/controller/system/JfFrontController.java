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
import com.ruoyi.system.domain.JfFront;
import com.ruoyi.system.service.IJfFrontService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 玖富前端 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
@Controller
@RequestMapping("/system/jfFront")
public class JfFrontController extends BaseController
{
    private String prefix = "system/jfFront";
	
	@Autowired
	private IJfFrontService jfFrontService;
	
	@RequiresPermissions("system:jfFront:view")
	@GetMapping()
	public String jfFront()
	{
	    return prefix + "/jfFront";
	}
	
	/**
	 * 查询玖富前端列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(JfFront jfFront)
	{
		startPage();
        List<JfFront> list = jfFrontService.selectJfFrontList(jfFront);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出玖富前端列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(JfFront jfFront)
    {
    	List<JfFront> list = jfFrontService.selectJfFrontList(jfFront);
        ExcelUtil<JfFront> util = new ExcelUtil<JfFront>(JfFront.class);
        return util.exportExcel(list, "jfFront");
    }
	
	/**
	 * 新增玖富前端
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存玖富前端
	 */
	@Log(title = "玖富前端", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(JfFront jfFront)
	{		
		return toAjax(jfFrontService.insertJfFront(jfFront));
	}

	/**
	 * 修改玖富前端
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		JfFront jfFront = jfFrontService.selectJfFrontById(id);
		mmap.put("jfFront", jfFront);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存玖富前端
	 */
	@Log(title = "玖富前端", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(JfFront jfFront)
	{		
		return toAjax(jfFrontService.updateJfFront(jfFront));
	}
	
	/**
	 * 删除玖富前端
	 */
	@Log(title = "玖富前端", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(jfFrontService.deleteJfFrontByIds(ids));
	}
	
	@GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<JfFront> util = new ExcelUtil<JfFront>(JfFront.class);
        return util.importTemplateExcel("玖富前端数据");
    }
    
    
    @Log(title = "玖富前端", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<JfFront> util = new ExcelUtil<JfFront>(JfFront.class);
        List<JfFront> jffList = util.importExcel(file.getInputStream(),7,8);
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = jfFrontService.importJfFront(jffList, false, operName);
        return AjaxResult.success(message);
    }
    
    
	@PostMapping( "/deleteAllJfFront")
	@ResponseBody
	public AjaxResult deleteAllJfFront()
	{		
		return toAjax(jfFrontService.deleteAllJfFront());
	}
}
