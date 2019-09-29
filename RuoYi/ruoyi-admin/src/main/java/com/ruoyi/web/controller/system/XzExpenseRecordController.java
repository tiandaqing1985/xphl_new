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
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.XzExpenseRecord;
import com.ruoyi.system.domain.XzExpenseSta;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.IXzExpenseRecordService;
import com.ruoyi.system.service.IXzResourceTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 费用记录 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-09-23
 */
@Controller
@RequestMapping("/system/xzExpenseRecord")
public class XzExpenseRecordController extends BaseController
{
    private String prefix = "system/xzExpenseRecord";
	
	@Autowired
	private IXzExpenseRecordService xzExpenseRecordService;
	
	@Autowired
	private IXzResourceTypeService xzResourceTypeService;
	
	@Autowired
	private ISysDeptService sysDeptService;
	
	@RequiresPermissions("system:xzExpenseRecord:view")
	@GetMapping()
	public String xzExpenseRecord()
	{
	    return prefix + "/xzExpenseRecord";
	}
	
	@GetMapping("/xzExpenseSta")
	public String xzExpenseSta() {
		return "system/xzExpenseRecord/xzExpenseList";
	}
	
	/**
	 * 查询类型费用详细统计
	 */
	@GetMapping("/xzExpenseDataList/{expenseTypeParent}")
	public String detailList(@PathVariable("expenseTypeParent") Long expenseTypeParent, ModelMap mmap) {
		mmap.put("expenseTypeParent", expenseTypeParent);
		return "system/xzExpenseRecord/xzExpenseDataList";
	}
	/**
	 * 查询费用记录列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(XzExpenseRecord xzExpenseRecord)
	{
		startPage();
		
		SysDept dept = sysDeptService.selectDeptById(ShiroUtils.getSysUser().getDeptId());
		
		if(ShiroUtils.getUserId()==1 || ShiroUtils.getUserId()==103){ //超级管理员 和 任总看所有数据  
			
		}else{
			if(ShiroUtils.getSysUser().getUserName().equals(dept.getLeader())){  //行政部门leader查看所有
				
			}else{
				String region=ShiroUtils.getSysUser().getArea();
				xzExpenseRecord.setRegion(region);
			}
		}
        List<XzExpenseRecord> list = xzExpenseRecordService.selectXzExpenseRecordList(xzExpenseRecord);
		return getDataTable(list);
	}
	
	/**
	 * 查询费用统计列表
	 */
	@PostMapping("/xzExpenseList")
	@ResponseBody
	public TableDataInfo xzExpenseList(XzExpenseSta xzExpenseSta) {
		startPage();
		List<XzExpenseSta> list = xzExpenseRecordService.selectXzExpenseList(xzExpenseSta);
		return getDataTable(list);
	}
	
	/**
	 * 查询费用统计列表
	 */
	@PostMapping("/xzExpenseDataList/{expenseTypeParent}")
	@ResponseBody
	public TableDataInfo xzExpenseDataList(@PathVariable("expenseTypeParent") Long expenseTypeParent) {
		startPage();
		XzExpenseSta xzExpenseSta=new XzExpenseSta();
		xzExpenseSta.setExpenseTypeParent(expenseTypeParent);
		List<XzExpenseSta> list = xzExpenseRecordService.selectXzExpenseDetailList(xzExpenseSta);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出费用记录列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XzExpenseRecord xzExpenseRecord)
    {
    	List<XzExpenseRecord> list = xzExpenseRecordService.selectXzExpenseRecordList(xzExpenseRecord);
        ExcelUtil<XzExpenseRecord> util = new ExcelUtil<XzExpenseRecord>(XzExpenseRecord.class);
        return util.exportExcel(list, "xzExpenseRecord");
    }
	
	/**
	 * 新增费用记录
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		mmap.put("applyUser", ShiroUtils.getSysUser().getUserName());
		//获取采购类型
		mmap.put("typeList", xzResourceTypeService.selectByFeiList());
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存费用记录
	 */
	@Log(title = "费用记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(XzExpenseRecord xzExpenseRecord)
	{		
		xzExpenseRecord.setCreateBy(ShiroUtils.getUserId().toString());
		xzExpenseRecord.setCreateTime(new Date());
		xzExpenseRecord.setApplyUser(ShiroUtils.getUserId().toString());
		xzExpenseRecord.setSubmitType("1");
		return toAjax(xzExpenseRecordService.insertXzExpenseRecord(xzExpenseRecord));
	}
	/**
	 * 新增保存费用记录
	 */
	@Log(title = "费用记录", businessType = BusinessType.INSERT)
	@PostMapping("/addSub")
	@ResponseBody
	public AjaxResult addSubSave(XzExpenseRecord xzExpenseRecord)
	{		
		xzExpenseRecord.setCreateBy(ShiroUtils.getUserId().toString());
		xzExpenseRecord.setCreateTime(new Date());
		xzExpenseRecord.setApplyUser(ShiroUtils.getUserId().toString());
		xzExpenseRecord.setSubmitType("2");
		return toAjax(xzExpenseRecordService.insertXzExpenseRecord(xzExpenseRecord));
	}

	/**
	 * 修改费用记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		XzExpenseRecord xzExpenseRecord = xzExpenseRecordService.selectXzExpenseRecordById(id);
		mmap.put("xzExpenseRecord", xzExpenseRecord);
		//获取采购类型
		mmap.put("typeList", xzResourceTypeService.selectByFeiList());
		mmap.put("dataList", xzResourceTypeService.xzResourceDataByParentId(xzExpenseRecord.getExpenseTypeParent()));
	    return prefix + "/edit";
	}
	/**
	 * 费用记录详情
	 */
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long id, ModelMap mmap)
	{
		XzExpenseRecord xzExpenseRecord = xzExpenseRecordService.selectXzExpenseRecordById(id);
		mmap.put("xzExpenseRecord", xzExpenseRecord);
		//获取采购类型
		mmap.put("typeList", xzResourceTypeService.selectByFeiList());
		mmap.put("dataList", xzResourceTypeService.xzResourceDataByParentId(xzExpenseRecord.getExpenseTypeParent()));
		return prefix + "/detail";
	}
	
	/**
	 * 修改保存费用记录
	 */
	@Log(title = "费用记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(XzExpenseRecord xzExpenseRecord)
	{		
		xzExpenseRecord.setUpdateBy(ShiroUtils.getUserId().toString());
		xzExpenseRecord.setUpdateTime(new Date());
		xzExpenseRecord.setApplyUser(ShiroUtils.getUserId().toString());
		return toAjax(xzExpenseRecordService.updateXzExpenseRecord(xzExpenseRecord));
	}
	/**
	 * 修改保存费用记录
	 */
	@Log(title = "费用记录", businessType = BusinessType.UPDATE)
	@PostMapping("/editSub")
	@ResponseBody
	public AjaxResult editSubSave(XzExpenseRecord xzExpenseRecord)
	{		
		xzExpenseRecord.setUpdateBy(ShiroUtils.getUserId().toString());
		xzExpenseRecord.setUpdateTime(new Date());
		xzExpenseRecord.setSubmitType("2");
		xzExpenseRecord.setApplyUser(ShiroUtils.getUserId().toString());
		return toAjax(xzExpenseRecordService.updateXzExpenseRecord(xzExpenseRecord));
	}
	
	/**
	 * 删除费用记录
	 */
	@Log(title = "费用记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(xzExpenseRecordService.deleteXzExpenseRecordByIds(ids));
	}
	
}
