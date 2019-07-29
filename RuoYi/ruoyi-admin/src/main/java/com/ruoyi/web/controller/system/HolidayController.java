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
import com.ruoyi.system.domain.RestHoliday;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.IHolidayRecordService;
import com.ruoyi.system.service.IHolidayService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 假期 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-06-17
 */
@Controller
@RequestMapping("/system/holiday")
public class HolidayController extends BaseController
{
    private String prefix = "system/holiday";
	
	@Autowired
	private IHolidayService holidayService;
	
	@Autowired
	private IHolidayRecordService holidayRecordService;
	
	@RequiresPermissions("system:holiday:view")
	@GetMapping()
	public String holiday()
	{
	    return prefix + "/holiday";
	}
	
	/**
	 * 查询假期详情
	 */
	@GetMapping("/holidayRestDetail/{userId}")
	public String holidayRest(@PathVariable("userId") Long userId,  ModelMap m)
	{
		m.put("userId", userId);
	    return prefix + "/holidayRestDetail";
	}
	
	/**
	 * 查询假期余额列表
	 */
	@PostMapping("/holidayList")
	@ResponseBody
	public TableDataInfo holidayList(SysUser sysUser)
	{
		
		startPage();
		sysUser.setUserId(ShiroUtils.getUserId());
		List<RestHoliday> list = holidayService.selectRestByUserId(sysUser);    
		return getDataTable(list);
	}
	
	/**
	 * 查询我的假期余额列表
	 */
	@PostMapping("/myHolidayList")
	@ResponseBody
	public TableDataInfo myHolidayList(SysUser sysUser)
	{
		
		startPage();
		sysUser.setUserId(ShiroUtils.getUserId());
        List<RestHoliday> list = holidayService.selectMyRestByUserId(sysUser);
        
		return getDataTable(list);
	}
	/**
	 * 查询有效年假列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Holiday holiday)
	{
		startPage();
		holiday.setAvailability("1");
		holiday.setHolidayType("1");//年假类型
        List<Holiday> list = holidayService.selectHolidayList(holiday);
		return getDataTable(list);
	}
	
	/**
	 * 查询有效调休列表
	 */
	@PostMapping("/olist")
	@ResponseBody
	public TableDataInfo olist(Holiday holiday)
	{
		startPage();
		holiday.setAvailability("1");
		holiday.setHolidayType("2");//年假类型
        List<Holiday> list = holidayService.selectHolidayList(holiday);
		return getDataTable(list);
	}
	
	/**
	 * 查询调休年假记录列表
	 */
	@PostMapping("/annualLeaveRecordList")
	@ResponseBody
	public TableDataInfo annualLeaveRecordList(HolidayRecord holidayRecord)
	{
		startPage();
		holidayRecord.setRecordHolidayType("1");
		List<HolidayRecord> list = holidayRecordService.selectUsedHolidayRecordList(holidayRecord);
		return getDataTable(list);
	}
	
	/**
	 * 查询调休使用记录列表
	 */
	@PostMapping("/paidLeaveRecordList")
	@ResponseBody
	public TableDataInfo paidLeaveRecordList(HolidayRecord holidayRecord)
	{
		startPage();
		holidayRecord.setRecordHolidayType("2");
        List<HolidayRecord> list = holidayRecordService.selectUsedHolidayRecordList(holidayRecord);
		return getDataTable(list);
	}
	
	/**
	 * 导出假期列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Holiday holiday)
    {
    	List<Holiday> list = holidayService.selectHolidayList(holiday);
        ExcelUtil<Holiday> util = new ExcelUtil<Holiday>(Holiday.class);
        return util.exportExcel(list, "holiday");
    }
	
	/**
	 * 新增假期
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存假期
	 */
	@Log(title = "假期", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Holiday holiday)
	{		
		return toAjax(holidayService.insertHoliday(holiday));
	}

	/**
	 * 修改假期
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Holiday holiday = holidayService.selectHolidayById(id);
		mmap.put("holiday", holiday);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存假期
	 */
	@Log(title = "假期", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Holiday holiday)
	{		
		return toAjax(holidayService.updateHoliday(holiday));
	}
	
	/**
	 * 删除假期
	 */
	@Log(title = "假期", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(holidayService.deleteHolidayByIds(ids));
	}
	
	
}
