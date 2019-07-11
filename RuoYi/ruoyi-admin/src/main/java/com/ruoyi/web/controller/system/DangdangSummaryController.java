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
import com.ruoyi.system.domain.DangdangSummary;
import com.ruoyi.system.service.IDangdangSummaryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 当当前端消费汇总 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-11
 */
@Controller
@RequestMapping("/system/dangdangSummary")
public class DangdangSummaryController extends BaseController
{
    private String prefix = "system/dangdangSummary";
	
	@Autowired
	private IDangdangSummaryService dangdangSummaryService;
	
	@RequiresPermissions("system:dangdangSummary:view")
	@GetMapping()
	public String dangdangSummary()
	{
	    return prefix + "/dangdangSummary";
	}
	
	/**
	 * 查询当当前端消费汇总列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DangdangSummary dangdangSummary)
	{
		startPage();
        List<DangdangSummary> list = dangdangSummaryService.selectDangdangSummaryList(dangdangSummary);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出当当前端消费汇总列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangdangSummary dangdangSummary)
    {
    	List<DangdangSummary> list = dangdangSummaryService.selectDangdangSummaryList(dangdangSummary);
        ExcelUtil<DangdangSummary> util = new ExcelUtil<DangdangSummary>(DangdangSummary.class);
        return util.exportExcel(list, "dangdangSummary");
    }
	
	/**
	 * 新增当当前端消费汇总
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存当当前端消费汇总
	 */
	@Log(title = "当当前端消费汇总", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DangdangSummary dangdangSummary)
	{		
		return toAjax(dangdangSummaryService.insertDangdangSummary(dangdangSummary));
	}

	/**
	 * 修改当当前端消费汇总
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DangdangSummary dangdangSummary = dangdangSummaryService.selectDangdangSummaryById(id);
		mmap.put("dangdangSummary", dangdangSummary);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存当当前端消费汇总
	 */
	@Log(title = "当当前端消费汇总", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DangdangSummary dangdangSummary)
	{		
		return toAjax(dangdangSummaryService.updateDangdangSummary(dangdangSummary));
	}
	
	/**
	 * 删除当当前端消费汇总
	 */
	@Log(title = "当当前端消费汇总", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangdangSummaryService.deleteDangdangSummaryByIds(ids));
	}
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<DangdangSummary> util = new ExcelUtil<>(DangdangSummary.class);
		return util.importTemplateExcel("当当前端数据");
	}


	@Log(title = "当当前端消费汇总", businessType = BusinessType.IMPORT)
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file) throws Exception {
		ExcelUtil<DangdangSummary> util = new ExcelUtil<>(DangdangSummary.class);
		List<DangdangSummary> dangdangSummary = util.importExcel(file.getInputStream(), 0, 1);
		String operName = ShiroUtils.getSysUser().getLoginName();
		String message = dangdangSummaryService.importBwFront(dangdangSummary, false, operName);
		return AjaxResult.success(message);
	}
}
