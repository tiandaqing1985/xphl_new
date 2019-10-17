package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.system.service.ISysUserService;
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
import com.ruoyi.system.domain.XzAsstes;
import com.ruoyi.system.domain.XzPersonalAsset;
import com.ruoyi.system.service.IXzAsstesService;
import com.ruoyi.system.service.IXzPersonalAssetService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 个人资产 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-08-27
 */
@Controller
@RequestMapping("/system/xzPersonalAsset")
public class XzPersonalAssetController extends BaseController {
	private String prefix = "system/xzPersonalAsset";

	@Autowired
	private IXzPersonalAssetService xzPersonalAssetService;

	@Autowired
	private IXzAsstesService xzAsstesService;

	@Autowired
	private ISysUserService sysUserService;

	@RequiresPermissions("system:xzPersonalAsset:view")
	@GetMapping()
	public String xzPersonalAsset() {
		return prefix + "/xzPersonalAsset";
	}

	@RequiresPermissions("system:xzPersonalAssetQuery:view")
	@GetMapping("/queryByName")
	public String queryXzPersonalAsset() {
		return prefix + "/xzPersonalAssetQuery";
	}

	/**
	 * 查询个人资产列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(XzPersonalAsset xzPersonalAsset) {
		startPage();
		Long person=ShiroUtils.getUserId();
		if(person==1){
			//可查看全部
		}else{
			xzPersonalAsset.setUserId(ShiroUtils.getUserId());
		}
		List<XzPersonalAsset> list = xzPersonalAssetService.selectXzPersonalAssetList(xzPersonalAsset);
		return getDataTable(list);
	}
	/**
	 * 查询个人资产列表
	 */
	@PostMapping("/queryList")
	@ResponseBody
	public TableDataInfo queryList(String name) {
		Long aLong = sysUserService.selectUserIdByUserNameOnly(name);
		XzPersonalAsset xzPersonalAsset = new XzPersonalAsset();
		xzPersonalAsset.setUserId(aLong);
		if(aLong==null){
			return getDataTable(new ArrayList<>());
		}
		startPage();
		List<XzPersonalAsset> list = xzPersonalAssetService.selectXzPersonalAssetList(xzPersonalAsset);
		return getDataTable(list);
	}

	/**
	 * 导出个人资产列表
	 */
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(XzPersonalAsset xzPersonalAsset) {
		List<XzPersonalAsset> list = xzPersonalAssetService.selectXzPersonalAssetList(xzPersonalAsset);
		ExcelUtil<XzPersonalAsset> util = new ExcelUtil<XzPersonalAsset>(XzPersonalAsset.class);
		return util.exportExcel(list, "xzPersonalAsset");
	}

	/**
	 * 新增个人资产
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存个人资产
	 */
	@Log(title = "个人资产", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(XzPersonalAsset xzPersonalAsset) {
		return toAjax(xzPersonalAssetService.insertXzPersonalAsset(xzPersonalAsset));
	}

	/**
	 * 修改个人资产
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		XzPersonalAsset xzPersonalAsset = xzPersonalAssetService.selectXzPersonalAssetById(id);
		mmap.put("xzPersonalAsset", xzPersonalAsset);
		return prefix + "/edit";
	}

	/**
	 * 修改保存个人资产
	 */
	@Log(title = "个人资产", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(XzPersonalAsset xzPersonalAsset) {
		return toAjax(xzPersonalAssetService.updateXzPersonalAsset(xzPersonalAsset));
	}

	/**
	 * 删除个人资产
	 */
	@Log(title = "个人资产", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(xzPersonalAssetService.deleteXzPersonalAssetByIds(ids));
	}

	/**
	 * 个人资产-一键领取
	 */
	@Log(title = "个人资产", businessType = BusinessType.UPDATE)
	@PostMapping("/allDraw")
	@ResponseBody
	public AjaxResult allDraw() {
		Long userId=ShiroUtils.getUserId();
		// 查询资产表中userID的个人未领取资产
		if (xzAsstesService.countXzAsstesByAllDraw(userId) > 0) {
			String str = xzAsstesService.updateXzAsstesByAllDraw(userId);
			return success(str);
		} else {
			String mag = "暂无可领取资产";
			return success(mag);
		}

	}

	/**
	 * 个人资产-领取
	 */
	@Log(title = "个人资产", businessType = BusinessType.UPDATE)
	@PostMapping("/draw")
	@ResponseBody
	public AjaxResult draw(Long assetId) {
		Long userId=ShiroUtils.getUserId();
		if (userId.equals(xzAsstesService.selectXzAsstesById(assetId).getUseBy())){
			XzAsstes asset=new XzAsstes();
			asset.setId(assetId);
			asset.setUseTime(new Date());
			asset.setUseBy(userId);
			String str = xzAsstesService.updateXzAsstesByAssetId(asset);
			
			return success(str);
		}else{
			return error("非本人不可领取");
		}
		
	}
}
