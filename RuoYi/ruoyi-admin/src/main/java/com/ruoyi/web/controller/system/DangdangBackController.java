package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.DangdangBaiduAdd;
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
import com.ruoyi.system.domain.DangdangBack;
import com.ruoyi.system.service.IDangdangBackService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 当当后端汇总 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-18
 */
@Controller
@RequestMapping("/system/dangdangBack")
public class DangdangBackController extends BaseController
{
    private String prefix = "system/dangdangBack";
	
	@Autowired
	private IDangdangBackService dangdangBackService;
	
	@RequiresPermissions("system:dangdangBack:view")
	@GetMapping()
	public String dangdangBack()
	{
	    return prefix + "/dangdangBack";
	}
	
	/**
	 * 查询当当后端汇总列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DangdangBack dangdangBack)
	{
		startPage();
        List<DangdangBack> list = dangdangBackService.selectDangdangBackList(dangdangBack);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出当当后端汇总列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangdangBack dangdangBack)
    {
    	List<DangdangBack> list = dangdangBackService.selectDangdangBackList(dangdangBack);
        ExcelUtil<DangdangBack> util = new ExcelUtil<DangdangBack>(DangdangBack.class);
        return util.exportExcel(list, "dangdangBack");
    }
	
	/**
	 * 新增当当后端汇总
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存当当后端汇总
	 */
	@Log(title = "当当后端汇总", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DangdangBack dangdangBack)
	{		
		return toAjax(dangdangBackService.insertDangdangBack(dangdangBack));
	}

	/**
	 * 修改当当后端汇总
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DangdangBack dangdangBack = dangdangBackService.selectDangdangBackById(id);
		mmap.put("dangdangBack", dangdangBack);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存当当后端汇总
	 */
	@Log(title = "当当后端汇总", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DangdangBack dangdangBack)
	{		
		return toAjax(dangdangBackService.updateDangdangBack(dangdangBack));
	}
	
	/**
	 * 删除当当后端汇总
	 */
	@Log(title = "当当后端汇总", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangdangBackService.deleteDangdangBackByIds(ids));
	}
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<DangdangBack> util = new ExcelUtil<>(DangdangBack.class);
		return util.importTemplateExcel("当当后端");
	}


	@Log(title = "当当后端汇总", businessType = BusinessType.IMPORT)
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file) throws Exception {
		if (file==null){
			return AjaxResult.success("导入数据为空");
		}
		ExcelUtil<DangdangBack> util = new ExcelUtil<>(DangdangBack.class);
		List<DangdangBack> dangdangBaiduAdd= util.importExcel(file.getInputStream(), 0, 1);
		String operName = ShiroUtils.getSysUser().getLoginName();
		String message = dangdangBackService.importBwFront(dangdangBaiduAdd, false, operName,file.getOriginalFilename());
		return AjaxResult.success(message);
	}
	
}
