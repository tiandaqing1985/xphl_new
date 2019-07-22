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
import com.ruoyi.system.domain.DangdangPcBack;
import com.ruoyi.system.service.IDangdangPcBackService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 当当pc后端百度数据明细 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-19
 */
@Controller
@RequestMapping("/system/dangdangPcBack")
public class DangdangPcBackController extends BaseController
{
    private String prefix = "system/dangdangPcBack";
	
	@Autowired
	private IDangdangPcBackService dangdangPcBackService;
	
	@RequiresPermissions("system:dangdangPcBack:view")
	@GetMapping()
	public String dangdangPcBack()
	{
	    return prefix + "/dangdangPcBack";
	}
	
	/**
	 * 查询当当pc后端百度数据明细列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DangdangPcBack dangdangPcBack)
	{
		startPage();
        List<DangdangPcBack> list = dangdangPcBackService.selectDangdangPcBackList(dangdangPcBack);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出当当pc后端百度数据明细列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangdangPcBack dangdangPcBack)
    {
    	List<DangdangPcBack> list = dangdangPcBackService.selectDangdangPcBackList(dangdangPcBack);
        ExcelUtil<DangdangPcBack> util = new ExcelUtil<DangdangPcBack>(DangdangPcBack.class);
        return util.exportExcel(list, "dangdangPcBack");
    }
	
	/**
	 * 新增当当pc后端百度数据明细
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存当当pc后端百度数据明细
	 */
	@Log(title = "当当pc后端百度数据明细", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DangdangPcBack dangdangPcBack)
	{		
		return toAjax(dangdangPcBackService.insertDangdangPcBack(dangdangPcBack));
	}

	/**
	 * 修改当当pc后端百度数据明细
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DangdangPcBack dangdangPcBack = dangdangPcBackService.selectDangdangPcBackById(id);
		mmap.put("dangdangPcBack", dangdangPcBack);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存当当pc后端百度数据明细
	 */
	@Log(title = "当当pc后端百度数据明细", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DangdangPcBack dangdangPcBack)
	{		
		return toAjax(dangdangPcBackService.updateDangdangPcBack(dangdangPcBack));
	}
	
	/**
	 * 删除当当pc后端百度数据明细
	 */
	@Log(title = "当当pc后端百度数据明细", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangdangPcBackService.deleteDangdangPcBackByIds(ids));
	}
	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<DangdangPcBack> util = new ExcelUtil<>(DangdangPcBack.class);
		return util.importTemplateExcel("IOS");
	}


	@Log(title = "当当pc后端", businessType = BusinessType.IMPORT)
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file) throws Exception {
		ExcelUtil<DangdangPcBack> util = new ExcelUtil<>(DangdangPcBack.class);
		List<DangdangPcBack> dangdangIosAdds= util.importExcel(file.getInputStream(), 0, 1);
		String operName = ShiroUtils.getSysUser().getLoginName();
		String message = dangdangPcBackService.importBwFront(dangdangIosAdds, false, operName);
		return AjaxResult.success(message);
	}
}
