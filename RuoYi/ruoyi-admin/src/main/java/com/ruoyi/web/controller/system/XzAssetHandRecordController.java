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
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.XzAssetHandRecord;
import com.ruoyi.system.domain.XzAsstes;
import com.ruoyi.system.domain.XzPersonalApply;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.IXzAssetHandRecordService;
import com.ruoyi.system.service.IXzAsstesService;
import com.ruoyi.system.service.IXzPersonalApplyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 资产分配记录 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-08-07
 */
@Controller
@RequestMapping("/system/xzAssetHandRecord")
public class XzAssetHandRecordController extends BaseController
{
    private String prefix = "system/xzAssetHandRecord";
    
    @Autowired
	private IXzAsstesService xzAsstesService;
    
    @Autowired
    private ISysUserService sysUserService;
    
    @Autowired
   	private IXzPersonalApplyService xzPersonalApplyService;
    
	@Autowired
	private IXzAssetHandRecordService xzAssetHandRecordService;
	
	@Autowired
	private ISysDeptService sysDeptService;
	
	@RequiresPermissions("system:xzAssetHandRecord:view")
	@GetMapping()
	public String xzAssetHandRecord()
	{
	    return prefix + "/xzAssetHandRecord";
	}
	
	/**
	 * 查询资产分配记录列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(XzAssetHandRecord xzAssetHandRecord)
	{
		SysDept dept = sysDeptService.selectDeptById(ShiroUtils.getSysUser().getDeptId());
		
		if(ShiroUtils.getUserId()==1 || ShiroUtils.getUserId()==103 || ShiroUtils.getSysUser().getUserName().equals(dept.getLeader())){ //超级管理员 和 任总 行政部门leader看所有数据  
			xzAssetHandRecord.setRegion(xzAssetHandRecord.getRegion());
		}else{
			String region=ShiroUtils.getSysUser().getArea();
			xzAssetHandRecord.setRegion(region);
		}
		startPage();
        List<XzAssetHandRecord> list = xzAssetHandRecordService.selectXzAssetHandRecordList(xzAssetHandRecord);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资产分配记录列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XzAssetHandRecord xzAssetHandRecord)
    {
    	List<XzAssetHandRecord> list = xzAssetHandRecordService.selectXzAssetHandRecordList(xzAssetHandRecord);
        ExcelUtil<XzAssetHandRecord> util = new ExcelUtil<XzAssetHandRecord>(XzAssetHandRecord.class);
        return util.exportExcel(list, "xzAssetHandRecord");
    }
	
	/**
	 * 新增固定资产分配记录
	 */
	@GetMapping("/add/{id}")
	public String add(@PathVariable("id") Long id, ModelMap mmap)
	{
		XzAsstes xzAsstes=xzAsstesService.selectXzAsstesById(id);
		mmap.put("xzAsstes", xzAsstes);
	    return prefix + "/add";
	}
	
	/**
	 * 新增办公用品资产分配记录(申请分配)
	 */
	@GetMapping("/staHand/{applyId}")
	public String addStaHand(@PathVariable("applyId") Long applyId, ModelMap mmap)
	{
		XzPersonalApply apply=xzPersonalApplyService.selectXzPersonalApplyById(applyId);
		XzAsstes xzAsstes=new XzAsstes();
		xzAsstes.setAssetsType(apply.getAssetTypeId());
		xzAsstes.setAssetsType2(apply.getAssetType2Id());
		xzAsstes.setAssetsModel(apply.getAssetModel());
		mmap.put("apply", apply);
	    return prefix + "/staHand";
	}
	
	/**
	 * 新增办公用品资产分配记录(主动分配)
	 */
	@GetMapping("/toStaHand")
	public String toStaHand(Long assetTypeId,Long assetType2Id,String shengyuCount, ModelMap mmap)
	{
		XzAsstes xz =new XzAsstes();
		xz.setAssetsType(assetTypeId);
		xz.setAssetsType2(assetType2Id);
		mmap.put("assetTypeId", assetTypeId);
		mmap.put("assetType2Id", assetType2Id);
		mmap.put("sum", shengyuCount);
		return prefix + "/toStaHand";
	}
	
	/**
	 * 新增办公用品资产分配记录
	 */
	@GetMapping("/cancelHand/{applyId}")
	public String cancelHand(@PathVariable("applyId") Long applyId, ModelMap mmap)
	{
		XzPersonalApply apply=xzPersonalApplyService.selectXzPersonalApplyById(applyId);
		mmap.put("apply", apply);
	    return prefix + "/cancelHand";
	}
	
	/**
	 * 新增固定资产分配记录
	 */
	@Log(title = "资产分配记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(XzAssetHandRecord xzAssetHandRecord)
	{	
		xzAssetHandRecord.setCreateBy(ShiroUtils.getUserId().toString());
		xzAssetHandRecord.setDistributor(ShiroUtils.getUserId());
		String str= xzAssetHandRecordService.insertXzAssetHandRecord(xzAssetHandRecord);
		if(str.equals("0")){
			return success("分配成功");
		}else{
			return error("此人不存在，请重新分配");
		}
		
	}
	
	/**
	 * 新增办公资产分配记录
	 */
	@Log(title = "资产分配记录", businessType = BusinessType.INSERT)
	@PostMapping("/staHand")
	@ResponseBody
	public AjaxResult staHandSave(XzAssetHandRecord xzAssetHandRecord)
	{	
		xzAssetHandRecord.setDistributor(ShiroUtils.getUserId());
		String str= xzAssetHandRecordService.insertXzAssetHandStaRecord(xzAssetHandRecord);
		if(str=="1"){
			return success("分配成功");
		}else if(str=="2"){
			return error("分配失败");	
		}else{
			return AjaxResult.warn("该资产库存不足，请采购！");	
		}
		
	}
	/**
	 * 新增办公资产分配记录
	 */
	@Log(title = "资产分配记录", businessType = BusinessType.INSERT)
	@PostMapping("/toStaHand")
	@ResponseBody
	public AjaxResult toStaHandSave(XzAssetHandRecord xzAssetHandRecord)
	{	
		Long recipientId=sysUserService.selectUserIdByUserNameOnly(xzAssetHandRecord.getRecipientName());
		String region=sysUserService.selectUserById(recipientId).getArea();
		xzAssetHandRecord.setDistributor(ShiroUtils.getUserId());
		xzAssetHandRecord.setRecipient(recipientId);
		xzAssetHandRecord.setRegion(region);
		String str= xzAssetHandRecordService.insertXzAssetHandStaRecord(xzAssetHandRecord);
		if(str=="1"){
			return success("分配成功");
		}else if(str=="2"){
			return error("分配失败");	
		}else{
			return AjaxResult.warn("该资产库存不足，请采购！");	
		}
		
	}
	
	/**
	 * 取消办公资产分配记录
	 */
	@Log(title = "资产分配记录", businessType = BusinessType.INSERT)
	@PostMapping("/cancelHand")
	@ResponseBody
	public AjaxResult cancelHandSave(XzAssetHandRecord xzAssetHandRecord)
	{	
		xzAssetHandRecord.setDistributor(ShiroUtils.getUserId());
		String str= xzAssetHandRecordService.cancelHandRecord(xzAssetHandRecord);
		return success(str);	
	}

	/**
	 * 修改资产分配记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		XzAssetHandRecord xzAssetHandRecord = xzAssetHandRecordService.selectXzAssetHandRecordById(id);
		mmap.put("xzAssetHandRecord", xzAssetHandRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资产分配记录
	 */
	@Log(title = "资产分配记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(XzAssetHandRecord xzAssetHandRecord)
	{		
		return toAjax(xzAssetHandRecordService.updateXzAssetHandRecord(xzAssetHandRecord));
	}
	
	/**
	 * 删除资产分配记录
	 */
	@Log(title = "资产分配记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(xzAssetHandRecordService.deleteXzAssetHandRecordByIds(ids));
	}
	
}