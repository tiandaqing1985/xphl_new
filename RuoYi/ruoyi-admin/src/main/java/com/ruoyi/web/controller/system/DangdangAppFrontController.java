package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.DangdangMatch;
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
import com.ruoyi.system.domain.DangdangAppFront;
import com.ruoyi.system.service.IDangdangAppFrontService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 当当APP前端 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-11
 */
@Controller
@RequestMapping("/system/dangdangAppFront")
public class DangdangAppFrontController extends BaseController
{
    private String prefix = "system/dangdangAppFront";
	
	@Autowired
	private IDangdangAppFrontService dangdangAppFrontService;
	
	@RequiresPermissions("system:dangdangAppFront:view")
	@GetMapping()
	public String dangdangAppFront()
	{
	    return prefix + "/dangdangAppFront";
	}
	
	/**
	 * 查询当当APP前端列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DangdangAppFront dangdangAppFront)
	{
		startPage();
        List<DangdangAppFront> list = dangdangAppFrontService.selectDangdangAppFrontList(dangdangAppFront);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出当当APP前端列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangdangAppFront dangdangAppFront)
    {
    	List<DangdangAppFront> list = dangdangAppFrontService.selectDangdangAppFrontList(dangdangAppFront);
        ExcelUtil<DangdangAppFront> util = new ExcelUtil<DangdangAppFront>(DangdangAppFront.class);
        return util.exportExcel(list, "dangdangAppFront");
    }
	
	/**
	 * 新增当当APP前端
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存当当APP前端
	 */
	@Log(title = "当当APP前端", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DangdangAppFront dangdangAppFront)
	{		
		return toAjax(dangdangAppFrontService.insertDangdangAppFront(dangdangAppFront));
	}

	/**
	 * 修改当当APP前端
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DangdangAppFront dangdangAppFront = dangdangAppFrontService.selectDangdangAppFrontById(id);
		mmap.put("dangdangAppFront", dangdangAppFront);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存当当APP前端
	 */
	@Log(title = "当当APP前端", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DangdangAppFront dangdangAppFront)
	{		
		return toAjax(dangdangAppFrontService.updateDangdangAppFront(dangdangAppFront));
	}
	
	/**
	 * 删除当当APP前端
	 */
	@Log(title = "当当APP前端", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangdangAppFrontService.deleteDangdangAppFrontByIds(ids));
	}

	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<DangdangAppFront> util = new ExcelUtil<DangdangAppFront>(DangdangAppFront.class);
		return util.importTemplateExcel("当当前端数据");
	}


	@Log(title = "当当APP前端", businessType = BusinessType.IMPORT)
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file) throws Exception {
		ExcelUtil<DangdangAppFront> util = new ExcelUtil<DangdangAppFront>(DangdangAppFront.class);
		List<DangdangAppFront> dangdangAppFront = util.importExcel(file.getInputStream(), 0, 1);
		String operName = ShiroUtils.getSysUser().getLoginName();
		String message = dangdangAppFrontService.importBwFront(dangdangAppFront, false, operName);
		return AjaxResult.success(message);
	}
}
