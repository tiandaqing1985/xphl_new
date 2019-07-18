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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.MfwysParameter;
import com.ruoyi.system.service.IMfwysParameterService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 马蜂窝原生参数 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-18
 */
@Controller
@RequestMapping("/system/mfwysParameter")
public class MfwysParameterController extends BaseController
{
    private String prefix = "system/mfwysParameter";
	
	@Autowired
	private IMfwysParameterService mfwysParameterService;
	
	@RequiresPermissions("system:mfwysParameter:view")
	@GetMapping()
	public String mfwysParameter()
	{
	    return prefix + "/mfwysParameter";
	}
	
	/**
	 * 查询马蜂窝原生参数列表
	 */
	@RequiresPermissions("system:mfwysParameter:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MfwysParameter mfwysParameter)
	{
		startPage();
        List<MfwysParameter> list = mfwysParameterService.selectMfwysParameterList(mfwysParameter);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出马蜂窝原生参数列表
	 */
	@RequiresPermissions("system:mfwysParameter:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MfwysParameter mfwysParameter)
    {
    	List<MfwysParameter> list = mfwysParameterService.selectMfwysParameterList(mfwysParameter);
        ExcelUtil<MfwysParameter> util = new ExcelUtil<MfwysParameter>(MfwysParameter.class);
        return util.exportExcel(list, "mfwysParameter");
    }
	
	/**
	 * 新增马蜂窝原生参数
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存马蜂窝原生参数
	 */
	@RequiresPermissions("system:mfwysParameter:add")
	@Log(title = "马蜂窝原生参数", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MfwysParameter mfwysParameter)
	{		
		return toAjax(mfwysParameterService.insertMfwysParameter(mfwysParameter));
	}

	/**
	 * 修改马蜂窝原生参数
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		MfwysParameter mfwysParameter = mfwysParameterService.selectMfwysParameterById(id);
		mmap.put("mfwysParameter", mfwysParameter);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存马蜂窝原生参数
	 */
	@RequiresPermissions("system:mfwysParameter:edit")
	@Log(title = "马蜂窝原生参数", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MfwysParameter mfwysParameter)
	{		
		return toAjax(mfwysParameterService.updateMfwysParameter(mfwysParameter));
	}
	
	/**
	 * 删除马蜂窝原生参数
	 */
	@RequiresPermissions("system:mfwysParameter:remove")
	@Log(title = "马蜂窝原生参数", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(mfwysParameterService.deleteMfwysParameterByIds(ids));
	}
	
}
