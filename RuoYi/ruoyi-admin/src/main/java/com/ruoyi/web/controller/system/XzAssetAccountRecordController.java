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
import com.ruoyi.system.domain.XzAssetAccountRecord;
import com.ruoyi.system.service.IXzAssetAccountRecordService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 资产台账记录 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-08-08
 */
@Controller
@RequestMapping("/system/xzAssetAccountRecord")
public class XzAssetAccountRecordController extends BaseController
{
    private String prefix = "system/xzAssetAccountRecord";
	
	@Autowired
	private IXzAssetAccountRecordService xzAssetAccountRecordService;
	
	@RequiresPermissions("system:xzAssetAccountRecord:view")
	@GetMapping()
	public String xzAssetAccountRecord()
	{
	    return prefix + "/xzAssetAccountRecord";
	}
	
	/**
	 * 查询资产台账记录列表
	 */
	@RequiresPermissions("system:xzAssetAccountRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(XzAssetAccountRecord xzAssetAccountRecord)
	{
		startPage();
        List<XzAssetAccountRecord> list = xzAssetAccountRecordService.selectXzAssetAccountRecordList(xzAssetAccountRecord);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资产台账记录列表
	 */
	@RequiresPermissions("system:xzAssetAccountRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XzAssetAccountRecord xzAssetAccountRecord)
    {
    	List<XzAssetAccountRecord> list = xzAssetAccountRecordService.selectXzAssetAccountRecordList(xzAssetAccountRecord);
        ExcelUtil<XzAssetAccountRecord> util = new ExcelUtil<XzAssetAccountRecord>(XzAssetAccountRecord.class);
        return util.exportExcel(list, "xzAssetAccountRecord");
    }
	
	/**
	 * 新增资产台账记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资产台账记录
	 */
	@RequiresPermissions("system:xzAssetAccountRecord:add")
	@Log(title = "资产台账记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(XzAssetAccountRecord xzAssetAccountRecord)
	{		
		return toAjax(xzAssetAccountRecordService.insertXzAssetAccountRecord(xzAssetAccountRecord));
	}

	/**
	 * 修改资产台账记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		XzAssetAccountRecord xzAssetAccountRecord = xzAssetAccountRecordService.selectXzAssetAccountRecordById(id);
		mmap.put("xzAssetAccountRecord", xzAssetAccountRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资产台账记录
	 */
	@RequiresPermissions("system:xzAssetAccountRecord:edit")
	@Log(title = "资产台账记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(XzAssetAccountRecord xzAssetAccountRecord)
	{		
		return toAjax(xzAssetAccountRecordService.updateXzAssetAccountRecord(xzAssetAccountRecord));
	}
	
	/**
	 * 删除资产台账记录
	 */
	@RequiresPermissions("system:xzAssetAccountRecord:remove")
	@Log(title = "资产台账记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(xzAssetAccountRecordService.deleteXzAssetAccountRecordByIds(ids));
	}
	
}
