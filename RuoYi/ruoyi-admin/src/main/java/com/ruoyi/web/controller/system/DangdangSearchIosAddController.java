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
import com.ruoyi.system.domain.DangdangSearchIosAdd;
import com.ruoyi.system.service.IDangdangSearchIosAddService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 当当ios后端数据 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-15
 */
@Controller
@RequestMapping("/system/dangdangSearchIosAdd")
public class DangdangSearchIosAddController extends BaseController
{
    private String prefix = "system/dangdangSearchIosAdd";
	
	@Autowired
	private IDangdangSearchIosAddService dangdangSearchIosAddService;
	
	@RequiresPermissions("system:dangdangSearchIosAdd:view")
	@GetMapping()
	public String dangdangSearchIosAdd()
	{
	    return prefix + "/dangdangSearchIosAdd";
	}
	
	/**
	 * 查询当当ios后端数据列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DangdangSearchIosAdd dangdangSearchIosAdd)
	{
		startPage();
        List<DangdangSearchIosAdd> list = dangdangSearchIosAddService.selectDangdangSearchIosAddList(dangdangSearchIosAdd);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出当当ios后端数据列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangdangSearchIosAdd dangdangSearchIosAdd)
    {
    	List<DangdangSearchIosAdd> list = dangdangSearchIosAddService.selectDangdangSearchIosAddList(dangdangSearchIosAdd);
        ExcelUtil<DangdangSearchIosAdd> util = new ExcelUtil<DangdangSearchIosAdd>(DangdangSearchIosAdd.class);
        return util.exportExcel(list, "dangdangSearchIosAdd");
    }
	
	/**
	 * 新增当当ios后端数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存当当ios后端数据
	 */
	@Log(title = "当当ios后端数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DangdangSearchIosAdd dangdangSearchIosAdd)
	{		
		return toAjax(dangdangSearchIosAddService.insertDangdangSearchIosAdd(dangdangSearchIosAdd));
	}

	/**
	 * 修改当当ios后端数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DangdangSearchIosAdd dangdangSearchIosAdd = dangdangSearchIosAddService.selectDangdangSearchIosAddById(id);
		mmap.put("dangdangSearchIosAdd", dangdangSearchIosAdd);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存当当ios后端数据
	 */
	@Log(title = "当当ios后端数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DangdangSearchIosAdd dangdangSearchIosAdd)
	{		
		return toAjax(dangdangSearchIosAddService.updateDangdangSearchIosAdd(dangdangSearchIosAdd));
	}
	
	/**
	 * 删除当当ios后端数据
	 */
	@Log(title = "当当ios后端数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangdangSearchIosAddService.deleteDangdangSearchIosAddByIds(ids));
	}
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<DangdangSearchIosAdd> util = new ExcelUtil<>(DangdangSearchIosAdd.class);
		return util.importTemplateExcel("搜索");
	}


	@Log(title = "当当搜索后端", businessType = BusinessType.IMPORT)
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file) throws Exception {
		ExcelUtil<DangdangSearchIosAdd> util = new ExcelUtil<>(DangdangSearchIosAdd.class);
		List<DangdangSearchIosAdd> dangdangSearchIosAdds= util.importExcel(file.getInputStream(), 0, 1);
		String operName = ShiroUtils.getSysUser().getLoginName();
		String message = dangdangSearchIosAddService.importBwFront(dangdangSearchIosAdds, false, operName);
		return AjaxResult.success(message);
	}
}
