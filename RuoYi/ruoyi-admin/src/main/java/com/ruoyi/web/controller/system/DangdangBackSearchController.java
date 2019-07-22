package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.DangdangBack;
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
import com.ruoyi.system.domain.DangdangBackSearch;
import com.ruoyi.system.service.IDangdangBackSearchService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 当当pc端搜索广告后端 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-21
 */
@Controller
@RequestMapping("/system/dangdangBackSearch")
public class DangdangBackSearchController extends BaseController
{
    private String prefix = "system/dangdangBackSearch";
	
	@Autowired
	private IDangdangBackSearchService dangdangBackSearchService;
	
	@RequiresPermissions("system:dangdangBackSearch:view")
	@GetMapping()
	public String dangdangBackSearch()
	{
	    return prefix + "/dangdangBackSearch";
	}
	
	/**
	 * 查询当当pc端搜索广告后端列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DangdangBackSearch dangdangBackSearch)
	{
		startPage();
        List<DangdangBackSearch> list = dangdangBackSearchService.selectDangdangBackSearchList(dangdangBackSearch);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出当当pc端搜索广告后端列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangdangBackSearch dangdangBackSearch)
    {
    	List<DangdangBackSearch> list = dangdangBackSearchService.selectDangdangBackSearchList(dangdangBackSearch);
        ExcelUtil<DangdangBackSearch> util = new ExcelUtil<DangdangBackSearch>(DangdangBackSearch.class);
        return util.exportExcel(list, "dangdangBackSearch");
    }
	
	/**
	 * 新增当当pc端搜索广告后端
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存当当pc端搜索广告后端
	 */
	@Log(title = "当当pc端搜索广告后端", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DangdangBackSearch dangdangBackSearch)
	{		
		return toAjax(dangdangBackSearchService.insertDangdangBackSearch(dangdangBackSearch));
	}

	/**
	 * 修改当当pc端搜索广告后端
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DangdangBackSearch dangdangBackSearch = dangdangBackSearchService.selectDangdangBackSearchById(id);
		mmap.put("dangdangBackSearch", dangdangBackSearch);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存当当pc端搜索广告后端
	 */
	@Log(title = "当当pc端搜索广告后端", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DangdangBackSearch dangdangBackSearch)
	{		
		return toAjax(dangdangBackSearchService.updateDangdangBackSearch(dangdangBackSearch));
	}
	
	/**
	 * 删除当当pc端搜索广告后端
	 */
	@Log(title = "当当pc端搜索广告后端", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangdangBackSearchService.deleteDangdangBackSearchByIds(ids));
	}
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<DangdangBackSearch> util = new ExcelUtil<>(DangdangBackSearch.class);
		return util.importTemplateExcel("当当后端");
	}


	@Log(title = "当当后端汇总", businessType = BusinessType.IMPORT)
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file) throws Exception {
		if (file==null){
			return AjaxResult.success("导入数据为空");
		}
		ExcelUtil<DangdangBackSearch> util = new ExcelUtil<>(DangdangBackSearch.class);
		List<DangdangBackSearch> dangdangBaiduAdd= util.importExcel(file.getInputStream(), 0, 1);
		String operName = ShiroUtils.getSysUser().getLoginName();
		String message = dangdangBackSearchService.importBwFront(dangdangBaiduAdd, false, operName);
		return AjaxResult.success(message);
	}
}
