package com.ruoyi.web.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.DangDangAll;
import com.ruoyi.system.domain.DangdangAdditional;
import com.ruoyi.system.domain.DangdangSearchIosAdd;
import com.ruoyi.system.service.DangDangAllImportService;
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
import com.ruoyi.system.domain.DangdangSearchAdd;
import com.ruoyi.system.service.IDangdangSearchAddService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 当当后端数据（搜索） 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-12
 */
@Controller
@RequestMapping("/system/dangdangSearchAdd")
public class DangdangSearchAddController extends BaseController
{
    private String prefix = "system/dangdangSearchAdd";
	
	@Autowired
	private IDangdangSearchAddService dangdangSearchAddService;
	@Autowired
	private DangDangAllImportService dangDangAllImportService;
	
	@RequiresPermissions("system:dangdangSearchAdd:view")
	@GetMapping()
	public String dangdangSearchAdd()
	{
	    return prefix + "/dangdangSearchAdd";
	}
	
	/**
	 * 查询当当后端数据（搜索）列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DangdangSearchAdd dangdangSearchAdd)
	{
		startPage();
        List<DangdangSearchAdd> list = dangdangSearchAddService.selectDangdangSearchAddList(dangdangSearchAdd);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出当当后端数据（搜索）列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangdangSearchAdd dangdangSearchAdd)
    {

		List<DangDangAll> list = dangDangAllImportService.importDangDangAll(dangdangSearchAdd);
        ExcelUtil<DangDangAll> util = new ExcelUtil<DangDangAll>(DangDangAll.class);
        return util.exportExcel(list, "dangdangAll");
    }
	
	/**
	 * 新增当当后端数据（搜索）
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存当当后端数据（搜索）
	 */
	@Log(title = "当当后端数据（搜索）", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DangdangSearchAdd dangdangSearchAdd)
	{		
		return toAjax(dangdangSearchAddService.insertDangdangSearchAdd(dangdangSearchAdd));
	}

	/**
	 * 修改当当后端数据（搜索）
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DangdangSearchAdd dangdangSearchAdd = dangdangSearchAddService.selectDangdangSearchAddById(id);
		mmap.put("dangdangSearchAdd", dangdangSearchAdd);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存当当后端数据（搜索）
	 */
	@Log(title = "当当后端数据（搜索）", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DangdangSearchAdd dangdangSearchAdd)
	{		
		return toAjax(dangdangSearchAddService.updateDangdangSearchAdd(dangdangSearchAdd));
	}
	
	/**
	 * 删除当当后端数据（搜索）
	 */
	@Log(title = "当当后端数据（搜索）", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangdangSearchAddService.deleteDangdangSearchAddByIds(ids));
	}
	@GetMapping("/importTemplate")

	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<DangdangSearchAdd> util = new ExcelUtil<>(DangdangSearchAdd.class);
		return util.importTemplateExcel("搜索");
	}


	@Log(title = "当当搜索后端", businessType = BusinessType.IMPORT)
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file) throws Exception {
		ExcelUtil<DangdangSearchAdd> util = new ExcelUtil<>(DangdangSearchAdd.class);
		List<DangdangSearchAdd> dangdangSearchAdds= util.importExcel(file.getInputStream(), 0, 1);
		String operName = ShiroUtils.getSysUser().getLoginName();
		String message = dangdangSearchAddService.importBwFront(dangdangSearchAdds, false, operName);
		return AjaxResult.success(message);
	}
}
