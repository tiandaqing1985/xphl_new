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
import com.ruoyi.system.domain.RxFront;
import com.ruoyi.system.service.IRxFrontService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 瑞幸前端 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-05
 */
@Controller
@RequestMapping("/system/rxFront")
public class RxFrontController extends BaseController
{
    private String prefix = "system/rxFront";
	
	@Autowired
	private IRxFrontService rxFrontService;
	
	@RequiresPermissions("system:rxFront:view")
	@GetMapping()
	public String rxFront()
	{
	    return prefix + "/rxFront";
	}
	
	/**
	 * 查询瑞幸前端列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(RxFront rxFront)
	{
		startPage();
        List<RxFront> list = rxFrontService.selectRxFrontList(rxFront);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出瑞幸前端列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RxFront rxFront)
    {
    	List<RxFront> list = rxFrontService.selectRxFrontList(rxFront);
        ExcelUtil<RxFront> util = new ExcelUtil<RxFront>(RxFront.class);
        return util.exportExcel(list, "rxFront");
    }
	
	/**
	 * 新增瑞幸前端
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存瑞幸前端
	 */
	@Log(title = "瑞幸前端", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(RxFront rxFront)
	{		
		return toAjax(rxFrontService.insertRxFront(rxFront));
	}

	/**
	 * 修改瑞幸前端
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		RxFront rxFront = rxFrontService.selectRxFrontById(id);
		mmap.put("rxFront", rxFront);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存瑞幸前端
	 */
	@Log(title = "瑞幸前端", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(RxFront rxFront)
	{		
		return toAjax(rxFrontService.updateRxFront(rxFront));
	}
	
	/**
	 * 删除瑞幸前端
	 */
	@Log(title = "瑞幸前端", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(rxFrontService.deleteRxFrontByIds(ids));
	}
	
	@GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<RxFront> util = new ExcelUtil<RxFront>(RxFront.class);
        return util.importTemplateExcel("瑞幸前端数据");
    }
    
    
    @Log(title = "瑞幸前端", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<RxFront> util = new ExcelUtil<RxFront>(RxFront.class);
        List<RxFront> rxfList = util.importExcel(file.getInputStream(),7,8);
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = rxFrontService.importRxFront(rxfList, false, operName);
        return AjaxResult.success(message);
    }
    
    @PostMapping("/deleteAllRxFront")
	@ResponseBody
    public AjaxResult deleteAllRxFront(){
    	
    	return toAjax(rxFrontService.deleteAllRxFront());
    }
}
