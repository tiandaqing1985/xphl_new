package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.DangdangIosAdd;
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
import com.ruoyi.system.domain.DangdangPcFront;
import com.ruoyi.system.service.IDangdangPcFrontService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 当当pc前端 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-19
 */
@Controller
@RequestMapping("/system/dangdangPcFront")
public class DangdangPcFrontController extends BaseController
{
    private String prefix = "system/dangdangPcFront";
	
	@Autowired
	private IDangdangPcFrontService dangdangPcFrontService;
	
	@RequiresPermissions("system:dangdangPcFront:view")
	@GetMapping()
	public String dangdangPcFront()
	{
	    return prefix + "/dangdangPcFront";
	}
	
	/**
	 * 查询当当pc前端列表
	 */
	@RequiresPermissions("system:dangdangPcFront:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DangdangPcFront dangdangPcFront)
	{
		startPage();
        List<DangdangPcFront> list = dangdangPcFrontService.selectDangdangPcFrontList(dangdangPcFront);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出当当pc前端列表
	 */
	@RequiresPermissions("system:dangdangPcFront:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangdangPcFront dangdangPcFront)
    {
    	List<DangdangPcFront> list = dangdangPcFrontService.selectDangdangPcFrontList(dangdangPcFront);
        ExcelUtil<DangdangPcFront> util = new ExcelUtil<DangdangPcFront>(DangdangPcFront.class);
        return util.exportExcel(list, "dangdangPcFront");
    }
	
	/**
	 * 新增当当pc前端
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存当当pc前端
	 */
	@RequiresPermissions("system:dangdangPcFront:add")
	@Log(title = "当当pc前端", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DangdangPcFront dangdangPcFront)
	{		
		return toAjax(dangdangPcFrontService.insertDangdangPcFront(dangdangPcFront));
	}

	/**
	 * 修改当当pc前端
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DangdangPcFront dangdangPcFront = dangdangPcFrontService.selectDangdangPcFrontById(id);
		mmap.put("dangdangPcFront", dangdangPcFront);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存当当pc前端
	 */
	@RequiresPermissions("system:dangdangPcFront:edit")
	@Log(title = "当当pc前端", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DangdangPcFront dangdangPcFront)
	{		
		return toAjax(dangdangPcFrontService.updateDangdangPcFront(dangdangPcFront));
	}
	
	/**
	 * 删除当当pc前端
	 */
	@RequiresPermissions("system:dangdangPcFront:remove")
	@Log(title = "当当pc前端", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangdangPcFrontService.deleteDangdangPcFrontByIds(ids));
	}

	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<DangdangPcFront> util = new ExcelUtil<>(DangdangPcFront.class);
		return util.importTemplateExcel("IOS");
	}


	@Log(title = "当当pc后端", businessType = BusinessType.IMPORT)
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file) throws Exception {

		ExcelUtil<DangdangPcFront> util = new ExcelUtil<>(DangdangPcFront.class);
		List<DangdangPcFront> dangdangIosAdds= util.importExcel(file.getInputStream(), 0, 1);
		String operName = ShiroUtils.getSysUser().getLoginName();
		String message = dangdangPcFrontService.importBwFront(dangdangIosAdds, false, operName);
		return AjaxResult.success(message);
	}
}
