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
import com.ruoyi.system.domain.MfwysFront;
import com.ruoyi.system.service.IMfwysFrontService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 马蜂窝原生前端 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-04
 */
@Controller
@RequestMapping("/system/mfwysFront")
public class MfwysFrontController extends BaseController
{
    private String prefix = "system/mfwysFront";
	
	@Autowired
	private IMfwysFrontService mfwysFrontService;
	
	@RequiresPermissions("system:mfwysFront:view")
	@GetMapping()
	public String mfwysFront()
	{
	    return prefix + "/mfwysFront";
	}
	
	/**
	 * 查询马蜂窝原生前端列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MfwysFront mfwysFront)
	{
		startPage();
        List<MfwysFront> list = mfwysFrontService.selectMfwysFrontList(mfwysFront);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出马蜂窝原生前端列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MfwysFront mfwysFront)
    {
    	List<MfwysFront> list = mfwysFrontService.selectMfwysFrontList(mfwysFront);
        ExcelUtil<MfwysFront> util = new ExcelUtil<MfwysFront>(MfwysFront.class);
        return util.exportExcel(list, "mfwysFront");
    }
	
	/**
	 * 新增马蜂窝原生前端
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存马蜂窝原生前端
	 */
	@Log(title = "马蜂窝原生前端", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MfwysFront mfwysFront)
	{		
		return toAjax(mfwysFrontService.insertMfwysFront(mfwysFront));
	}

	/**
	 * 修改马蜂窝原生前端
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		MfwysFront mfwysFront = mfwysFrontService.selectMfwysFrontById(id);
		mmap.put("mfwysFront", mfwysFront);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存马蜂窝原生前端
	 */
	@Log(title = "马蜂窝原生前端", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MfwysFront mfwysFront)
	{		
		return toAjax(mfwysFrontService.updateMfwysFront(mfwysFront));
	}
	
	/**
	 * 删除马蜂窝原生前端
	 */
	@Log(title = "马蜂窝原生前端", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(mfwysFrontService.deleteMfwysFrontByIds(ids));
	}
	
	@GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<MfwysFront> util = new ExcelUtil<MfwysFront>(MfwysFront.class);
        return util.importTemplateExcel("马蜂窝原生前端");
    }
    
    
    @Log(title = "马蜂窝原生前端", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<MfwysFront> util = new ExcelUtil<MfwysFront>(MfwysFront.class);
        List<MfwysFront> mfwysList = util.importExcel(file.getInputStream(),4,5);
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = mfwysFrontService.importMfwysFront(mfwysList, false, operName);
        return AjaxResult.success(message);
    }
}
