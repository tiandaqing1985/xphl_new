package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.DangdangSearchAdd;
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
import com.ruoyi.system.domain.DangdangBaiduAdd;
import com.ruoyi.system.service.IDangdangBaiduAddService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 当当后端百度小程序数据 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-12
 */
@Controller
@RequestMapping("/system/dangdangBaiduAdd")
public class DangdangBaiduAddController extends BaseController
{
    private String prefix = "system/dangdangBaiduAdd";
	
	@Autowired
	private IDangdangBaiduAddService dangdangBaiduAddService;
	
	@RequiresPermissions("system:dangdangBaiduAdd:view")
	@GetMapping()
	public String dangdangBaiduAdd()
	{
	    return prefix + "/dangdangBaiduAdd";
	}
	
	/**
	 * 查询当当后端百度小程序数据列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DangdangBaiduAdd dangdangBaiduAdd)
	{
		startPage();
        List<DangdangBaiduAdd> list = dangdangBaiduAddService.selectDangdangBaiduAddList(dangdangBaiduAdd);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出当当后端百度小程序数据列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangdangBaiduAdd dangdangBaiduAdd)
    {
    	List<DangdangBaiduAdd> list = dangdangBaiduAddService.selectDangdangBaiduAddList(dangdangBaiduAdd);
        ExcelUtil<DangdangBaiduAdd> util = new ExcelUtil<DangdangBaiduAdd>(DangdangBaiduAdd.class);
        return util.exportExcel(list, "dangdangBaiduAdd");
    }
	
	/**
	 * 新增当当后端百度小程序数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存当当后端百度小程序数据
	 */
	@Log(title = "当当后端百度小程序数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DangdangBaiduAdd dangdangBaiduAdd)
	{		
		return toAjax(dangdangBaiduAddService.insertDangdangBaiduAdd(dangdangBaiduAdd));
	}

	/**
	 * 修改当当后端百度小程序数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DangdangBaiduAdd dangdangBaiduAdd = dangdangBaiduAddService.selectDangdangBaiduAddById(id);
		mmap.put("dangdangBaiduAdd", dangdangBaiduAdd);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存当当后端百度小程序数据
	 */
	@Log(title = "当当后端百度小程序数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DangdangBaiduAdd dangdangBaiduAdd)
	{		
		return toAjax(dangdangBaiduAddService.updateDangdangBaiduAdd(dangdangBaiduAdd));
	}
	
	/**
	 * 删除当当后端百度小程序数据
	 */
	@Log(title = "当当后端百度小程序数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangdangBaiduAddService.deleteDangdangBaiduAddByIds(ids));
	}
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<DangdangBaiduAdd> util = new ExcelUtil<>(DangdangBaiduAdd.class);
		return util.importTemplateExcel("搜索");
	}


	@Log(title = "当当搜索后端", businessType = BusinessType.IMPORT)
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file) throws Exception {
		ExcelUtil<DangdangBaiduAdd> util = new ExcelUtil<>(DangdangBaiduAdd.class);
		List<DangdangBaiduAdd> dangdangBaiduAdd= util.importExcel(file.getInputStream(), 0, 1);
		String operName = ShiroUtils.getSysUser().getLoginName();
		String message = dangdangBaiduAddService.importBwFront(dangdangBaiduAdd, false, operName);
		return AjaxResult.success(message);
	}
}
