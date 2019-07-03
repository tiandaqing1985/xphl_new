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
import com.ruoyi.system.domain.MfwysEnd;
import com.ruoyi.system.service.IMfwysEndService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 马蜂窝原生后端 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
@Controller
@RequestMapping("/system/mfwysEnd")
public class MfwysEndController extends BaseController
{
    private String prefix = "system/mfwysEnd";
	
	@Autowired
	private IMfwysEndService mfwysEndService;
	
	@RequiresPermissions("system:mfwysEnd:view")
	@GetMapping()
	public String mfwysEnd()
	{
	    return prefix + "/mfwysEnd";
	}
	
	/**
	 * 查询马蜂窝原生后端列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MfwysEnd mfwysEnd)
	{
		startPage();
        List<MfwysEnd> list = mfwysEndService.selectMfwysEndList(mfwysEnd);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出马蜂窝原生后端列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MfwysEnd mfwysEnd)
    {
    	List<MfwysEnd> list = mfwysEndService.selectMfwysEndList(mfwysEnd);
        ExcelUtil<MfwysEnd> util = new ExcelUtil<MfwysEnd>(MfwysEnd.class);
        return util.exportExcel(list, "mfwysEnd");
    }
	
	/**
	 * 新增马蜂窝原生后端
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存马蜂窝原生后端
	 */
	@Log(title = "马蜂窝原生后端", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MfwysEnd mfwysEnd)
	{		
		return toAjax(mfwysEndService.insertMfwysEnd(mfwysEnd));
	}

	/**
	 * 修改马蜂窝原生后端
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		MfwysEnd mfwysEnd = mfwysEndService.selectMfwysEndById(id);
		mmap.put("mfwysEnd", mfwysEnd);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存马蜂窝原生后端
	 */
	@Log(title = "马蜂窝原生后端", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MfwysEnd mfwysEnd)
	{		
		return toAjax(mfwysEndService.updateMfwysEnd(mfwysEnd));
	}
	
	/**
	 * 删除马蜂窝原生后端
	 */
	@Log(title = "马蜂窝原生后端", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(mfwysEndService.deleteMfwysEndByIds(ids));
	}
	
	@GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<MfwysEnd> util = new ExcelUtil<MfwysEnd>(MfwysEnd.class);
        return util.importTemplateExcel("马蜂窝原生后端");
    }
    
    
    @Log(title = "马蜂窝原生后端", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<MfwysEnd> util = new ExcelUtil<MfwysEnd>(MfwysEnd.class);
        List<MfwysEnd> mfwysList = util.importExcel(file.getInputStream(),0,1);
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = mfwysEndService.importMfwysEnd(mfwysList, false, operName);
        return AjaxResult.success(message);
    }
	
}
