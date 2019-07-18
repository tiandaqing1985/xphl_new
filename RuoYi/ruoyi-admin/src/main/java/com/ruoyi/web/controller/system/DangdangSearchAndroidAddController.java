package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.DangdangSearchIosAdd;
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
import com.ruoyi.system.domain.DangdangSearchAndroidAdd;
import com.ruoyi.system.service.IDangdangSearchAndroidAddService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 当当搜索安卓后端数据 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-15
 */
@Controller
@RequestMapping("/system/dangdangSearchAndroidAdd")
public class DangdangSearchAndroidAddController extends BaseController
{
    private String prefix = "system/dangdangSearchAndroidAdd";
	
	@Autowired
	private IDangdangSearchAndroidAddService dangdangSearchAndroidAddService;
	
	@RequiresPermissions("system:dangdangSearchAndroidAdd:view")
	@GetMapping()
	public String dangdangSearchAndroidAdd()
	{
	    return prefix + "/dangdangSearchAndroidAdd";
	}
	
	/**
	 * 查询当当搜索安卓后端数据列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DangdangSearchAndroidAdd dangdangSearchAndroidAdd)
	{
		startPage();
        List<DangdangSearchAndroidAdd> list = dangdangSearchAndroidAddService.selectDangdangSearchAndroidAddList(dangdangSearchAndroidAdd);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出当当搜索安卓后端数据列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangdangSearchAndroidAdd dangdangSearchAndroidAdd)
    {
    	List<DangdangSearchAndroidAdd> list = dangdangSearchAndroidAddService.selectDangdangSearchAndroidAddList(dangdangSearchAndroidAdd);
        ExcelUtil<DangdangSearchAndroidAdd> util = new ExcelUtil<DangdangSearchAndroidAdd>(DangdangSearchAndroidAdd.class);
        return util.exportExcel(list, "dangdangSearchAndroidAdd");
    }
	
	/**
	 * 新增当当搜索安卓后端数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存当当搜索安卓后端数据
	 */
	@Log(title = "当当搜索安卓后端数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DangdangSearchAndroidAdd dangdangSearchAndroidAdd)
	{		
		return toAjax(dangdangSearchAndroidAddService.insertDangdangSearchAndroidAdd(dangdangSearchAndroidAdd));
	}

	/**
	 * 修改当当搜索安卓后端数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DangdangSearchAndroidAdd dangdangSearchAndroidAdd = dangdangSearchAndroidAddService.selectDangdangSearchAndroidAddById(id);
		mmap.put("dangdangSearchAndroidAdd", dangdangSearchAndroidAdd);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存当当搜索安卓后端数据
	 */
	@Log(title = "当当搜索安卓后端数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DangdangSearchAndroidAdd dangdangSearchAndroidAdd)
	{		
		return toAjax(dangdangSearchAndroidAddService.updateDangdangSearchAndroidAdd(dangdangSearchAndroidAdd));
	}
	
	/**
	 * 删除当当搜索安卓后端数据
	 */
	@RequiresPermissions("system:dangdangSearchAndroidAdd:remove")
	@Log(title = "当当搜索安卓后端数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangdangSearchAndroidAddService.deleteDangdangSearchAndroidAddByIds(ids));
	}

	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<DangdangSearchAndroidAdd> util = new ExcelUtil<>(DangdangSearchAndroidAdd.class);
		return util.importTemplateExcel("搜索");
	}


	@Log(title = "当当搜索安卓后端", businessType = BusinessType.IMPORT)
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file) throws Exception {
		ExcelUtil<DangdangSearchAndroidAdd> util = new ExcelUtil<>(DangdangSearchAndroidAdd.class);
		List<DangdangSearchAndroidAdd> dangdangSearchIosAdds= util.importExcel(file.getInputStream(), 0, 1);
		String operName = ShiroUtils.getSysUser().getLoginName();
		String message = dangdangSearchAndroidAddService.importBwFront(dangdangSearchIosAdds, false, operName);
		return AjaxResult.success(message);
	}
}
