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
import com.ruoyi.system.domain.XzPersonalAsset;
import com.ruoyi.system.service.IXzAssetHandRecordService;
import com.ruoyi.system.service.IXzAssetReturnService;
import com.ruoyi.system.service.IXzAsstesService;
import com.ruoyi.system.service.IXzPersonalAssetService;
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
	
	@Autowired
	private IXzPersonalAssetService xzPersonalAssetService;

	
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
		//查看报备未操作的数据
		xzAssetReturn.setReturnStatus("3");
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
	 * 新增资产归还（id为个人资产id）
	 */
	@GetMapping("/add/{id}")
	public String add(@PathVariable("id") Long id, ModelMap mmap)
	{
		XzPersonalAsset xzPersonalAsset = xzPersonalAssetService.selectXzPersonalAssetById(id);
		mmap.put("id", id);
		mmap.put("xzAsstes", xzAsstesService.selectXzAsstesById(xzPersonalAsset.getAssetId()));
		mmap.put("xzHand", xzAssetHandRecordService.selectXzAssetHandRecordByAssetId(xzPersonalAsset.getAssetId()));
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资产归还
	 */
	@Log(title = "资产归还", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(XzAssetReturn xzAssetReturn)
	{	//增加一条资产归还宝贝记录
		xzAssetReturn.setReturnStatus("3");////1同意 2驳回 3未操作
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
		mmap.put("id", id);
	    return prefix + "/approvalModer";
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
	
	/**
	 * 资产归还->报废
	 */
	@Log(title = "资产归还-报废", businessType = BusinessType.UPDATE)
	@PostMapping( "/scrap")
	@ResponseBody
	public AjaxResult scrap(Long id)
	{		
		XzAssetReturn xzAssetReturn = xzAssetReturnService.selectXzAssetReturnById(id);
		xzAssetReturn.setReturnStatus("1");
		return toAjax(xzAssetReturnService.updateXzAssetScrap(xzAssetReturn));
	}
	
	
	/**
	 * 同意申请
	 */
	@PostMapping("/agree")
	@ResponseBody
	public AjaxResult agree(Long id,String remark)
	{	
		XzAssetReturn xzAssetReturn = xzAssetReturnService.selectXzAssetReturnById(id);
		XzAssetReturn xz = new XzAssetReturn();
		xz.setId(id);
		//个人资产id 非资产id
		xz.setAssetId(xzAssetReturn.getAssetId());
		xz.setHandlerId(ShiroUtils.getUserId());
		xz.setReturnStatus("1");//1同意 2驳回 3未操作
		xz.setRemark2(remark);
		xz.setUpdateBy(ShiroUtils.getUserId().toString());
		xz.setUpdateTime(new Date());
		//在申请数据上修改审批记录
		xzAssetReturnService.updateXzAssetReturn(xz);
		return toAjax(1);
	}
	

	/**
	 * 驳回申请
	 */
	@PostMapping("/reject")
	@ResponseBody
	public AjaxResult reject(Long id, String remark)
	{	
		XzAssetReturn xzAssetReturn = xzAssetReturnService.selectXzAssetReturnById(id);
		XzAssetReturn xz = new XzAssetReturn();
		xz.setId(id);
		xz.setAssetId(xzAssetReturn.getAssetId());
		xz.setHandlerId(ShiroUtils.getUserId());
		xz.setRemark2(remark);
		xz.setReturnStatus("2");//1同意 2驳回 3未操作
		xz.setUpdateBy(ShiroUtils.getUserId().toString());
		xz.setUpdateTime(new Date());
		//在申请记录上修改审批记录
		xzAssetReturnService.updateXzAssetReturn(xz);
		return toAjax(1);
	}
	
}
