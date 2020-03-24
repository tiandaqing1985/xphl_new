package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.XzAsstes;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.IXzAsstesService;
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
import com.ruoyi.system.domain.XzCheck;
import com.ruoyi.system.service.IXzCheckService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 盘点记录 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-12-26
 */
@Controller
@RequestMapping("/system/xzCheck")
public class XzCheckController extends BaseController
{
    private String prefix = "system/xzCheck";
	
	@Autowired
	private IXzCheckService xzCheckService;
	@Autowired
	private ISysDeptService sysDeptService;
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private IXzAsstesService xzAsstesService;
	
	@RequiresPermissions("system:xzCheck:view")
	@GetMapping()
	public String xzCheck()
	{
	    return prefix + "/xzCheck";
	}
	
	/**
	 * 查询盘点记录列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(XzAsstes xzAsstes)
	{
		xzAsstes.setSort("1");// 固定资产
		xzAsstes.setSubmitType("2");
		SysDept dept = sysDeptService.selectDeptById(ShiroUtils.getSysUser().getDeptId());

		if (ShiroUtils.getUserId() == 1 || ShiroUtils.getUserId() == 103) { //超级管理员 和 任总看所有数据

		} else {
			if (ShiroUtils.getSysUser().getUserName().equals(dept.getLeader())) {  //行政部门leader查看所有

			} else {
				String region = ShiroUtils.getSysUser().getArea();
				if (xzAsstes.getRegion() == null || xzAsstes.getRegion().equals("")) {
					xzAsstes.setRegion(region);
				}
			}
		}
		xzAsstes.setPurchaseBy(sysUserService.selectUserIdByUserNameOnly(xzAsstes.getPurchaseName()));
		startPage();
		List<XzAsstes> list = xzCheckService.selectXzAsstesList(xzAsstes);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出盘点记录列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XzCheck xzCheck)
    {
    	List<XzCheck> list = xzCheckService.selectXzCheckList(xzCheck);
        ExcelUtil<XzCheck> util = new ExcelUtil<XzCheck>(XzCheck.class);
        return util.exportExcel(list, "xzCheck");
    }
	
	/**
	 * 新增盘点记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存盘点记录
	 */
	@Log(title = "盘点记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(XzCheck xzCheck)
	{		
		return toAjax(xzCheckService.insertXzCheck(xzCheck));
	}

	/**
	 * 修改盘点记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		XzCheck xzCheck = xzCheckService.selectXzCheckById(id);
		mmap.put("xzCheck", xzCheck);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存盘点记录
	 */
	@Log(title = "盘点记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(XzCheck xzCheck)
	{		
		return toAjax(xzCheckService.updateXzCheck(xzCheck));
	}
	
	/**
	 * 删除盘点记录
	 */
	@Log(title = "盘点记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(xzCheckService.deleteXzCheckByIds(ids));
	}
	
}
