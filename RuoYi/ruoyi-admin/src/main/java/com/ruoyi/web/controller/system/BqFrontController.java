package com.ruoyi.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.BqFront;
import com.ruoyi.system.service.IBqFrontService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 北汽前端 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
@Controller
@RequestMapping("/system/bqFront")
public class BqFrontController extends BaseController
{
    private String prefix = "system/bqFront";
	
	@Autowired
	private IBqFrontService bqFrontService;
	
	@RequiresPermissions("system:bqFront:view")
	@GetMapping()
	public String bqFront()
	{
	    return prefix + "/bqFront";
	}
	
	/**
	 * 查询北汽前端列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BqFront bqFront)
	{
		startPage();
        List<BqFront> list = bqFrontService.selectBqFrontList(bqFront);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出北汽前端列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BqFront bqFront)
    {
    	List<BqFront> list = bqFrontService.selectBqFrontList(bqFront);
        ExcelUtil<BqFront> util = new ExcelUtil<BqFront>(BqFront.class);
        return util.exportExcel(list, "bqFront");
    }
	
	/**
	 * 新增北汽前端
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存北汽前端
	 */
	@Log(title = "北汽前端", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BqFront bqFront)
	{		
		return toAjax(bqFrontService.insertBqFront(bqFront));
	}

	/**
	 * 修改北汽前端
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		BqFront bqFront = bqFrontService.selectBqFrontById(id);
		mmap.put("bqFront", bqFront);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存北汽前端
	 */
	@Log(title = "北汽前端", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BqFront bqFront)
	{		
		return toAjax(bqFrontService.updateBqFront(bqFront));
	}
	
	/**
	 * 删除北汽前端
	 */
	@Log(title = "北汽前端", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(bqFrontService.deleteBqFrontByIds(ids));
	}
	
	
	@GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<BqFront> util = new ExcelUtil<BqFront>(BqFront.class);
        return util.importTemplateExcel("北汽前端数据");
    }
    
    
    @Log(title = "北汽前端", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<BqFront> util = new ExcelUtil<BqFront>(BqFront.class);
        List<BqFront> bgfList = util.importExcel(file.getInputStream(),7,8);
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = bqFrontService.importBqFront(bgfList, false, operName);
        return AjaxResult.success(message);
    }
    
    @PostMapping("/deleteAllBqFront")
	@ResponseBody
    public AjaxResult deleteAllBqFront(){
    	
    	return toAjax(bqFrontService.deleteAllBqFront());
    }
}
