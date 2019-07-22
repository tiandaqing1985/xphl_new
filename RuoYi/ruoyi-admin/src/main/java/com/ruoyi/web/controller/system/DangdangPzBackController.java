package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.DangdangPcMatch;
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
import com.ruoyi.system.domain.DangdangPzBack;
import com.ruoyi.system.service.IDangdangPzBackService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 当当pc端品专 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-21
 */
@Controller
@RequestMapping("/system/dangdangPzBack")
public class DangdangPzBackController extends BaseController
{
    private String prefix = "system/dangdangPzBack";
	
	@Autowired
	private IDangdangPzBackService dangdangPzBackService;
	
	@RequiresPermissions("system:dangdangPzBack:view")
	@GetMapping()
	public String dangdangPzBack()
	{
	    return prefix + "/dangdangPzBack";
	}
	
	/**
	 * 查询当当pc端品专列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DangdangPzBack dangdangPzBack)
	{
		startPage();
        List<DangdangPzBack> list = dangdangPzBackService.selectDangdangPzBackList(dangdangPzBack);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出当当pc端品专列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangdangPzBack dangdangPzBack)
    {
    	List<DangdangPzBack> list = dangdangPzBackService.selectDangdangPzBackList(dangdangPzBack);
        ExcelUtil<DangdangPzBack> util = new ExcelUtil<DangdangPzBack>(DangdangPzBack.class);
        return util.exportExcel(list, "dangdangPzBack");
    }
	
	/**
	 * 新增当当pc端品专
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存当当pc端品专
	 */
	@Log(title = "当当pc端品专", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DangdangPzBack dangdangPzBack)
	{		
		return toAjax(dangdangPzBackService.insertDangdangPzBack(dangdangPzBack));
	}

	/**
	 * 修改当当pc端品专
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DangdangPzBack dangdangPzBack = dangdangPzBackService.selectDangdangPzBackById(id);
		mmap.put("dangdangPzBack", dangdangPzBack);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存当当pc端品专
	 */
	@Log(title = "当当pc端品专", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DangdangPzBack dangdangPzBack)
	{		
		return toAjax(dangdangPzBackService.updateDangdangPzBack(dangdangPzBack));
	}
	
	/**
	 * 删除当当pc端品专
	 */
	@Log(title = "当当pc端品专", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangdangPzBackService.deleteDangdangPzBackByIds(ids));
	}


	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<DangdangPzBack> util = new ExcelUtil<>(DangdangPzBack.class);
		return util.importTemplateExcel("IOS");
	}


	@Log(title = "当当品专后端", businessType = BusinessType.IMPORT)
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file) throws Exception {
		ExcelUtil<DangdangPzBack> util = new ExcelUtil<>(DangdangPzBack.class);
		List<DangdangPzBack> dangdangIosAdds= util.importExcel(file.getInputStream(), 0, 1);
		String operName = ShiroUtils.getSysUser().getLoginName();
		String message = dangdangPzBackService.importBwFront(dangdangIosAdds, false, operName);
		return AjaxResult.success(message);
	}
}
