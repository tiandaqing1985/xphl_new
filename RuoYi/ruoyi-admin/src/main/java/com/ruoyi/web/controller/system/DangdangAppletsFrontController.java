package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;

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
import com.ruoyi.system.domain.DangdangAppletsFront;
import com.ruoyi.system.service.IDangdangAppletsFrontService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 当当小程序前端 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-11
 */
@Controller
@RequestMapping("/system/dangdangAppletsFront")
public class DangdangAppletsFrontController extends BaseController
{
    private String prefix = "system/dangdangAppletsFront";
	
	@Autowired
	private IDangdangAppletsFrontService dangdangAppletsFrontService;
	
	@RequiresPermissions("system:dangdangAppletsFront:view")
	@GetMapping()
	public String dangdangAppletsFront()
	{
	    return prefix + "/dangdangAppletsFront";
	}
	
	/**
	 * 查询当当小程序前端列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DangdangAppletsFront dangdangAppletsFront)
	{
		startPage();
        List<DangdangAppletsFront> list = dangdangAppletsFrontService.selectDangdangAppletsFrontList(dangdangAppletsFront);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出当当小程序前端列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangdangAppletsFront dangdangAppletsFront)
    {
    	List<DangdangAppletsFront> list = dangdangAppletsFrontService.selectDangdangAppletsFrontList(dangdangAppletsFront);
        ExcelUtil<DangdangAppletsFront> util = new ExcelUtil<DangdangAppletsFront>(DangdangAppletsFront.class);
        return util.exportExcel(list, "dangdangAppletsFront");
    }
	
	/**
	 * 新增当当小程序前端
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存当当小程序前端
	 */
	@Log(title = "当当小程序前端", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DangdangAppletsFront dangdangAppletsFront)
	{		
		return toAjax(dangdangAppletsFrontService.insertDangdangAppletsFront(dangdangAppletsFront));
	}

	/**
	 * 修改当当小程序前端
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DangdangAppletsFront dangdangAppletsFront = dangdangAppletsFrontService.selectDangdangAppletsFrontById(id);
		mmap.put("dangdangAppletsFront", dangdangAppletsFront);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存当当小程序前端
	 */
	@Log(title = "当当小程序前端", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DangdangAppletsFront dangdangAppletsFront)
	{		
		return toAjax(dangdangAppletsFrontService.updateDangdangAppletsFront(dangdangAppletsFront));
	}
	
	/**
	 * 删除当当小程序前端
	 */
	@Log(title = "当当小程序前端", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangdangAppletsFrontService.deleteDangdangAppletsFrontByIds(ids));
	}
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<DangdangAppletsFront> util = new ExcelUtil<>(DangdangAppletsFront.class);
		return util.importTemplateExcel("当当前端数据");
	}


	@Log(title = "当当小程序前端", businessType = BusinessType.IMPORT)
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file) throws Exception {
		ExcelUtil<DangdangAppletsFront> util = new ExcelUtil<>(DangdangAppletsFront.class);
		List<DangdangAppletsFront> dangdangAppletsFront = util.importExcel(file.getInputStream(), 0, 1);
		String operName = ShiroUtils.getSysUser().getLoginName();
		String message = dangdangAppletsFrontService.importBwFront(dangdangAppletsFront, false, operName);
		return AjaxResult.success(message);
	}
}
