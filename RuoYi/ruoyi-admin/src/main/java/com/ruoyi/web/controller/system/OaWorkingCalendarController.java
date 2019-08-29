package com.ruoyi.web.controller.system;

import java.util.List;
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
import com.ruoyi.system.domain.WorkingCalendar;
import com.ruoyi.system.service.IOaWorkingCalendarService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 节假日数据 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-08-27
 */
@Controller
@RequestMapping("/system/workingCalendar")
public class OaWorkingCalendarController extends BaseController
{
    private String prefix = "system/workingCalendar";
	
	@Autowired
	private IOaWorkingCalendarService workingCalendarService;
	
	/**
	 * 查询节假日总数
	 */
	@PostMapping("/count")
	@ResponseBody
	public int count(WorkingCalendar workingCalendar)
	{
		startPage();
        List<WorkingCalendar> list = workingCalendarService.selectWorkingCalendarList(workingCalendar);
		return list.size();
	}
	
	//------------------
	@GetMapping()
	public String workingCalendar()
	{
	    return prefix + "/workingCalendar";
	}
	
	/**
	 * 查询节假日数据列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WorkingCalendar workingCalendar)
	{
		startPage();
        List<WorkingCalendar> list = workingCalendarService.selectWorkingCalendarList(workingCalendar);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出节假日数据列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WorkingCalendar workingCalendar)
    {
    	List<WorkingCalendar> list = workingCalendarService.selectWorkingCalendarList(workingCalendar);
        ExcelUtil<WorkingCalendar> util = new ExcelUtil<WorkingCalendar>(WorkingCalendar.class);
        return util.exportExcel(list, "workingCalendar");
    }
	
	/**
	 * 新增节假日数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存节假日数据
	 */
	@Log(title = "节假日数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WorkingCalendar workingCalendar)
	{		
		return toAjax(workingCalendarService.insertWorkingCalendar(workingCalendar));
	}

	/**
	 * 修改节假日数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		WorkingCalendar workingCalendar = workingCalendarService.selectWorkingCalendarById(id);
		mmap.put("workingCalendar", workingCalendar);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存节假日数据
	 */
	@Log(title = "节假日数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WorkingCalendar workingCalendar)
	{		
		return toAjax(workingCalendarService.updateWorkingCalendar(workingCalendar));
	}
	
	/**
	 * 删除节假日数据
	 */
	@Log(title = "节假日数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(workingCalendarService.deleteWorkingCalendarByIds(ids));
	}
	
}
