package com.ruoyi.web.controller.system;

import java.util.Date;
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
import com.ruoyi.system.domain.XzStationeryassetRecord;
import com.ruoyi.system.service.IXzStationeryassetRecordService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 办公用品分配记录 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-08-12
 */
@Controller
@RequestMapping("/system/xzStationeryassetRecord")
public class XzStationeryassetRecordController extends BaseController
{
    private String prefix = "system/xzStationeryassetRecord";
	
	@Autowired
	private IXzStationeryassetRecordService xzStationeryassetRecordService;
	
	@RequiresPermissions("system:xzStationeryassetRecord:view")
	@GetMapping()
	public String xzStationeryassetRecord()
	{
	    return prefix + "/xzStationeryassetRecord";
	}
	
	/**
	 * 查询办公用品分配记录列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(XzStationeryassetRecord xzStationeryassetRecord)
	{
		startPage();
        List<XzStationeryassetRecord> list = xzStationeryassetRecordService.selectXzStationeryassetRecordList(xzStationeryassetRecord);
		System.out.println(list);
        return getDataTable(list);
	}
	
	
	/**
	 * 导出办公用品分配记录列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XzStationeryassetRecord xzStationeryassetRecord)
    {
    	List<XzStationeryassetRecord> list = xzStationeryassetRecordService.selectXzStationeryassetRecordList(xzStationeryassetRecord);
        ExcelUtil<XzStationeryassetRecord> util = new ExcelUtil<XzStationeryassetRecord>(XzStationeryassetRecord.class);
        return util.exportExcel(list, "xzStationeryassetRecord");
    }
	
	/**
	 * 新增办公用品分配记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存办公用品分配记录
	 */
	@Log(title = "办公用品分配记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public String addSave(XzStationeryassetRecord xzSar)
	{		
		//总额
		int price=Integer.parseInt(xzSar.getPrice()),
			count=xzSar.getCount();
		String sum=Integer.toString(price*count);
		xzSar.setSumPrice(sum);
		//未入库、无、保存
		xzSar.setAssetsStatus("1");
		xzSar.setUseStatus("1");
		xzSar.setCreateBy(ShiroUtils.getSysUser().getUserName());
		xzSar.setCreateTime(new Date());
		xzSar.setSubmitType("1");
		xzStationeryassetRecordService.insertXzStationeryassetRecord(xzSar);
		
		return "添加成功";
	}
	
	/**
	 * 新增保存办公用品分配记录
	 */
	@Log(title = "办公用品分配记录", businessType = BusinessType.INSERT)
	@PostMapping("/addSub")
	@ResponseBody
	public String addSubSave(XzStationeryassetRecord xzSar)
	{		
		//总额
		int price=Integer.parseInt(xzSar.getPrice()),
			count=xzSar.getCount();
		String sum=Integer.toString(price*count);
		xzSar.setSumPrice(sum);
		//在库、无、提交
		xzSar.setAssetsStatus("2");
		xzSar.setUseStatus("1");
		xzSar.setCreateBy(ShiroUtils.getSysUser().getUserName());
		xzSar.setCreateTime(new Date());
		xzSar.setSubmitType("2");
		xzStationeryassetRecordService.insertXzStationeryassetRecord(xzSar);
		
		return "添加成功";
	}

	/**
	 * 修改办公用品分配记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		XzStationeryassetRecord xzStationeryassetRecord = xzStationeryassetRecordService.selectXzStationeryassetRecordById(id);
		mmap.put("xzStationeryassetRecord", xzStationeryassetRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存办公用品分配记录
	 */
	@Log(title = "办公用品分配记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(XzStationeryassetRecord xzStationeryassetRecord)
	{		
		return toAjax(xzStationeryassetRecordService.updateXzStationeryassetRecord(xzStationeryassetRecord));
	}
	
	/**
	 * 删除办公用品分配记录
	 */
	@Log(title = "办公用品分配记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(xzStationeryassetRecordService.deleteXzStationeryassetRecordByIds(ids));
	}
	
}