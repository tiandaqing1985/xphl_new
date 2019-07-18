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
import com.ruoyi.system.domain.DangdangAdditional;
import com.ruoyi.system.service.IDangdangAdditionalService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 当当后端额外数据（品专） 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-12
 */
@Controller
@RequestMapping("/system/dangdangAdditional")
public class DangdangAdditionalController extends BaseController
{
    private String prefix = "system/dangdangAdditional";
	
	@Autowired
	private IDangdangAdditionalService dangdangAdditionalService;
	
	@RequiresPermissions("system:dangdangAdditional:view")
	@GetMapping()
	public String dangdangAdditional()
	{
	    return prefix + "/dangdangAdditional";
	}
	
	/**
	 * 查询当当后端额外数据（品专，搜索）列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DangdangAdditional dangdangAdditional)
	{
		startPage();
        List<DangdangAdditional> list = dangdangAdditionalService.selectDangdangAdditionalList(dangdangAdditional);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出当当后端额外数据（品专，搜索）列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangdangAdditional dangdangAdditional)
    {
    	List<DangdangAdditional> list = dangdangAdditionalService.selectDangdangAdditionalList(dangdangAdditional);
    	if (list==null||list.size()<1){
			return AjaxResult.error("无数据");
		}
        ExcelUtil<DangdangAdditional> util = new ExcelUtil<DangdangAdditional>(DangdangAdditional.class);
        return util.exportExcel(list, "dangdangAdditional");
    }
	
	/**
	 * 新增当当后端额外数据（品专，搜索）
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存当当后端额外数据（品专，搜索）
	 */
	@Log(title = "当当后端额外数据（品专，搜索）", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DangdangAdditional dangdangAdditional)
	{		
		return toAjax(dangdangAdditionalService.insertDangdangAdditional(dangdangAdditional));
	}

	/**
	 * 修改当当后端额外数据（品专，搜索）
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DangdangAdditional dangdangAdditional = dangdangAdditionalService.selectDangdangAdditionalById(id);
		mmap.put("dangdangAdditional", dangdangAdditional);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存当当后端额外数据（品专，搜索）
	 */
	@Log(title = "当当后端额外数据（品专，搜索）", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DangdangAdditional dangdangAdditional)
	{		
		return toAjax(dangdangAdditionalService.updateDangdangAdditional(dangdangAdditional));
	}
	
	/**
	 * 删除当当后端额外数据（品专，搜索）
	 */
	@Log(title = "当当后端额外数据（品专，搜索）", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangdangAdditionalService.deleteDangdangAdditionalByIds(ids));
	}


    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<DangdangAdditional> util = new ExcelUtil<DangdangAdditional>(DangdangAdditional.class);
        return util.importTemplateExcel("品专");
    }


    @Log(title = "当当品专后端", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception {
		System.out.println("文件名称》》》》》》》》》"+file.getOriginalFilename());
		String fileName =null;
		//获取文件名称
		fileName=file.getOriginalFilename();
		System.out.println("传入后台的名称"+fileName);
		ExcelUtil<DangdangAdditional> util = new ExcelUtil<DangdangAdditional>(DangdangAdditional.class);
        List<DangdangAdditional> dangdangAdditionals = util.importExcel(file.getInputStream(), 0, 1);
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = dangdangAdditionalService.importBwFront(dangdangAdditionals, false, operName,fileName);
        return AjaxResult.success(message);
    }


}
