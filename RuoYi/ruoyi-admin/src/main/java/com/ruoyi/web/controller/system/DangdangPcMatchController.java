package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.DangdangPcFront;
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
import com.ruoyi.system.domain.DangdangPcMatch;
import com.ruoyi.system.service.IDangdangPcMatchService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 当当PC匹配 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-19
 */
@Controller
@RequestMapping("/system/dangdangPcMatch")
public class DangdangPcMatchController extends BaseController
{
    private String prefix = "system/dangdangPcMatch";
	
	@Autowired
	private IDangdangPcMatchService dangdangPcMatchService;
	
	@RequiresPermissions("system:dangdangPcMatch:view")
	@GetMapping()
	public String dangdangPcMatch()
	{
	    return prefix + "/dangdangPcMatch";
	}
	
	/**
	 * 查询当当PC匹配列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DangdangPcMatch dangdangPcMatch)
	{
		startPage();
        List<DangdangPcMatch> list = dangdangPcMatchService.selectDangdangPcMatchList(dangdangPcMatch);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出当当PC匹配列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangdangPcMatch dangdangPcMatch)
    {
    	List<DangdangPcMatch> list = dangdangPcMatchService.selectDangdangPcMatchList(dangdangPcMatch);
        ExcelUtil<DangdangPcMatch> util = new ExcelUtil<DangdangPcMatch>(DangdangPcMatch.class);
        return util.exportExcel(list, "dangdangPcMatch");
    }
	
	/**
	 * 新增当当PC匹配
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存当当PC匹配
	 */
	@Log(title = "当当PC匹配", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DangdangPcMatch dangdangPcMatch)
	{		
		return toAjax(dangdangPcMatchService.insertDangdangPcMatch(dangdangPcMatch));
	}

	/**
	 * 修改当当PC匹配
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DangdangPcMatch dangdangPcMatch = dangdangPcMatchService.selectDangdangPcMatchById(id);
		mmap.put("dangdangPcMatch", dangdangPcMatch);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存当当PC匹配
	 */
	@Log(title = "当当PC匹配", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DangdangPcMatch dangdangPcMatch)
	{		
		return toAjax(dangdangPcMatchService.updateDangdangPcMatch(dangdangPcMatch));
	}
	
	/**
	 * 删除当当PC匹配
	 */
	@Log(title = "当当PC匹配", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangdangPcMatchService.deleteDangdangPcMatchByIds(ids));
	}

	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<DangdangPcMatch> util = new ExcelUtil<>(DangdangPcMatch.class);
		return util.importTemplateExcel("IOS");
	}


	@Log(title = "当当pc后端", businessType = BusinessType.IMPORT)
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file) throws Exception {
		ExcelUtil<DangdangPcMatch> util = new ExcelUtil<>(DangdangPcMatch.class);
		List<DangdangPcMatch> dangdangIosAdds= util.importExcel(file.getInputStream(), 0, 1);
		String operName = ShiroUtils.getSysUser().getLoginName();
		String message = dangdangPcMatchService.importBwFront(dangdangIosAdds, false, operName);
		return AjaxResult.success(message);
	}
}
