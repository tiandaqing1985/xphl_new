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
import com.ruoyi.system.domain.XzAssetReturn;
import com.ruoyi.system.service.IXzAssetHandRecordService;
import com.ruoyi.system.service.IXzAssetReturnService;
import com.ruoyi.system.service.IXzAsstesService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 资产归还 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-09-11
 */
@Controller
@RequestMapping("/system/xzAssetReturn")
public class XzAssetReturnController extends BaseController
{
    private String prefix = "system/xzAssetReturn";
	
	@Autowired
	private IXzAssetReturnService xzAssetReturnService;
	
	@Autowired
	private IXzAsstesService xzAsstesService;
	
	@Autowired
	private IXzAssetHandRecordService xzAssetHandRecordService;

	
	@RequiresPermissions("system:xzAssetReturn:view")
	@GetMapping()
	public String xzAssetReturn()
	{
	    return prefix + "/xzAssetReturn";
	}
	
	/**
	 * 查询资产归还列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(XzAssetReturn xzAssetReturn)
	{
		startPage();
        List<XzAssetReturn> list = xzAssetReturnService.selectXzAssetReturnList(xzAssetReturn);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资产归还列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XzAssetReturn xzAssetReturn)
    {
    	List<XzAssetReturn> list = xzAssetReturnService.selectXzAssetReturnList(xzAssetReturn);
        ExcelUtil<XzAssetReturn> util = new ExcelUtil<XzAssetReturn>(XzAssetReturn.class);
        return util.exportExcel(list, "xzAssetReturn");
    }
	
	/**
	 * 新增资产归还
	 */
	@GetMapping("/add/{assetId}")
	public String add(@PathVariable("assetId") Long assetId, ModelMap mmap)
	{
		mmap.put("xzAsstes", xzAsstesService.selectXzAsstesById(assetId));
		mmap.put("xzHand", xzAssetHandRecordService.selectXzAssetHandRecordByAssetId(assetId));
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资产归还
	 */
	@Log(title = "资产归还", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(XzAssetReturn xzAssetReturn)
	{	//增加一条资产归还记录
		xzAssetReturn.setReturnStatus("1");
		xzAssetReturn.setCreateBy(ShiroUtils.getUserId().toString());
		xzAssetReturn.setCreateTime(new Date());
		return toAjax(xzAssetReturnService.insertXzAssetReturn(xzAssetReturn));
	}

	/**
	 * 修改资产归还
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		XzAssetReturn xzAssetReturn = xzAssetReturnService.selectXzAssetReturnById(id);
		mmap.put("xzAssetReturn", xzAssetReturn);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资产归还
	 */
	@Log(title = "资产归还", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(XzAssetReturn xzAssetReturn)
	{		
		xzAssetReturn.setHandlerId(ShiroUtils.getUserId());
		xzAssetReturn.setUpdateBy(ShiroUtils.getUserId().toString());
		xzAssetReturn.setUpdateTime(new Date());
		return toAjax(xzAssetReturnService.updateXzAssetReturn(xzAssetReturn));
	}
	
	/**
	 * 删除资产归还
	 */
	@Log(title = "资产归还", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(xzAssetReturnService.deleteXzAssetReturnByIds(ids));
	}
	
}
