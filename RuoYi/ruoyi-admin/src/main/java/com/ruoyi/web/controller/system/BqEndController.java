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


import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.BqEnd;
import com.ruoyi.system.domain.BqFront;
import com.ruoyi.system.service.IBqEndService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 北汽后端 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
@Controller
@RequestMapping("/system/bqEnd")
public class BqEndController extends BaseController
{
    private String prefix = "system/bqEnd";
	
	@Autowired
	private IBqEndService bqEndService;
	
	@RequiresPermissions("system:bqEnd:view")
	@GetMapping()
	public String bqEnd()
	{
	    return prefix + "/bqEnd";
	}
	
	/**
	 * 查询北汽后端列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BqEnd bqEnd)
	{
		startPage();
        List<BqEnd> list = bqEndService.selectBqEndList(bqEnd);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出北汽后端列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BqEnd bqEnd)
    {
    	List<BqEnd> list = bqEndService.selectBqEndList(bqEnd);
        ExcelUtil<BqEnd> util = new ExcelUtil<BqEnd>(BqEnd.class);
        return util.exportExcel(list, "bqEnd");
    }
	
	/**
	 * 新增北汽后端
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存北汽后端
	 */
	@Log(title = "北汽后端", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BqEnd bqEnd)
	{		
		return toAjax(bqEndService.insertBqEnd(bqEnd));
	}

	/**
	 * 修改北汽后端
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		BqEnd bqEnd = bqEndService.selectBqEndById(id);
		mmap.put("bqEnd", bqEnd);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存北汽后端
	 */
	@Log(title = "北汽后端", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BqEnd bqEnd)
	{		
		return toAjax(bqEndService.updateBqEnd(bqEnd));
	}
	
	/**
	 * 删除北汽后端
	 */
	@Log(title = "北汽后端", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(bqEndService.deleteBqEndByIds(ids));
	}
	
	@GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<BqEnd> util = new ExcelUtil<BqEnd>(BqEnd.class);
        return util.importTemplateExcel("北汽后端数据");
    }
    
    
    @Log(title = "北汽后端", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<BqEnd> util = new ExcelUtil<BqEnd>(BqEnd.class);
        List<BqEnd> bgeList = util.importExcel(file.getInputStream(),0,1);
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = bqEndService.importBqEnd(bgeList, false, operName);
        return AjaxResult.success(message);
    }
    
    @PostMapping("/deleteAllBqEnd")
	@ResponseBody
	public AjaxResult deleteAllBqEnd()
	{		
		return toAjax(bqEndService.deleteAllBqEnd());
	}
}
