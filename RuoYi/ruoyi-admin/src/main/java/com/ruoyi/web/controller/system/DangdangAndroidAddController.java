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
import com.ruoyi.system.domain.DangdangAndroidAdd;
import com.ruoyi.system.service.IDangdangAndroidAddService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 当当安卓后端数据 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-15
 */
@Controller
@RequestMapping("/system/dangdangAndroidAdd")
public class DangdangAndroidAddController extends BaseController
{
    private String prefix = "system/dangdangAndroidAdd";
	
	@Autowired
	private IDangdangAndroidAddService dangdangAndroidAddService;
	
	@RequiresPermissions("system:dangdangAndroidAdd:view")
	@GetMapping()
	public String dangdangAndroidAdd()
	{
	    return prefix + "/dangdangAndroidAdd";
	}
	
	/**
	 * 查询当当安卓后端数据列表
	 */
	@RequiresPermissions("system:dangdangAndroidAdd:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DangdangAndroidAdd dangdangAndroidAdd)
	{
		startPage();
        List<DangdangAndroidAdd> list = dangdangAndroidAddService.selectDangdangAndroidAddList(dangdangAndroidAdd);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出当当安卓后端数据列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangdangAndroidAdd dangdangAndroidAdd)
    {
    	List<DangdangAndroidAdd> list = dangdangAndroidAddService.selectDangdangAndroidAddList(dangdangAndroidAdd);
        ExcelUtil<DangdangAndroidAdd> util = new ExcelUtil<DangdangAndroidAdd>(DangdangAndroidAdd.class);
        return util.exportExcel(list, "dangdangAndroidAdd");
    }
	
	/**
	 * 新增当当安卓后端数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存当当安卓后端数据
	 */
	@Log(title = "当当安卓后端数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DangdangAndroidAdd dangdangAndroidAdd)
	{		
		return toAjax(dangdangAndroidAddService.insertDangdangAndroidAdd(dangdangAndroidAdd));
	}

	/**
	 * 修改当当安卓后端数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DangdangAndroidAdd dangdangAndroidAdd = dangdangAndroidAddService.selectDangdangAndroidAddById(id);
		mmap.put("dangdangAndroidAdd", dangdangAndroidAdd);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存当当安卓后端数据
	 */
	@Log(title = "当当安卓后端数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DangdangAndroidAdd dangdangAndroidAdd)
	{		
		return toAjax(dangdangAndroidAddService.updateDangdangAndroidAdd(dangdangAndroidAdd));
	}
	
	/**
	 * 删除当当安卓后端数据
	 */
	@Log(title = "当当安卓后端数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangdangAndroidAddService.deleteDangdangAndroidAddByIds(ids));
	}


	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<DangdangAndroidAdd> util = new ExcelUtil<>(DangdangAndroidAdd.class);
		return util.importTemplateExcel("搜索");
	}


	@Log(title = "当当安卓后端", businessType = BusinessType.IMPORT)
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file) throws Exception {
		ExcelUtil<DangdangAndroidAdd> util = new ExcelUtil<>(DangdangAndroidAdd.class);
		List<DangdangAndroidAdd> dangdangSearchAdds= util.importExcel(file.getInputStream(), 0, 1);
		String operName = ShiroUtils.getSysUser().getLoginName();
		String message = dangdangAndroidAddService.importBwFront(dangdangSearchAdds, false, operName);
		return AjaxResult.success(message);
	}
}
