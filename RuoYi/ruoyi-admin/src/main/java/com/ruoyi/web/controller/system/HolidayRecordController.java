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
import com.ruoyi.system.domain.Holiday;
import com.ruoyi.system.domain.HolidayRecord;
import com.ruoyi.system.service.IHolidayRecordService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 假期使用记录 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-06-24
 */
@Controller
@RequestMapping("/system/holidayRecord")
public class HolidayRecordController extends BaseController
{
    private String prefix = "system/holidayRecord";
	
	@Autowired
	private IHolidayRecordService holidayRecordService;
	
	@RequiresPermissions("system:holidayRecord:view")
	@GetMapping()
	public String holidayRecord()
	{
	    return prefix + "/holidayRecord";
	}
	
	
	
	
	/**
	 * 导出假期使用记录列表
	 */
	@RequiresPermissions("system:holidayRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HolidayRecord holidayRecord)
    {
    	List<HolidayRecord> list = holidayRecordService.selectHolidayRecordList(holidayRecord);
        ExcelUtil<HolidayRecord> util = new ExcelUtil<HolidayRecord>(HolidayRecord.class);
        return util.exportExcel(list, "holidayRecord");
    }
	
	/**
	 * 新增假期使用记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存假期使用记录
	 */
	@RequiresPermissions("system:holidayRecord:add")
	@Log(title = "假期使用记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HolidayRecord holidayRecord)
	{		
		return toAjax(holidayRecordService.insertHolidayRecord(holidayRecord));
	}

	/**
	 * 修改假期使用记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		HolidayRecord holidayRecord = holidayRecordService.selectHolidayRecordById(id);
		mmap.put("holidayRecord", holidayRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存假期使用记录
	 */
	@RequiresPermissions("system:holidayRecord:edit")
	@Log(title = "假期使用记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HolidayRecord holidayRecord)
	{		
		return toAjax(holidayRecordService.updateHolidayRecord(holidayRecord));
	}
	
	/**
	 * 删除假期使用记录
	 */
	@RequiresPermissions("system:holidayRecord:remove")
	@Log(title = "假期使用记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(holidayRecordService.deleteHolidayRecordByIds(ids));
	}
	
}
