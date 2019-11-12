package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.finance.FacCollectInformation;
import com.ruoyi.system.service.finance.IFacCollectInformationService;

/**
 * 团建费报销 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-11-07
 */
@Controller
@RequestMapping("/system/facCollectInformation")
public class FacCollectInformationController extends BaseController {
	private String prefix = "system/facCollectInformation";

	@Autowired
	private IFacCollectInformationService facCollectInformationService;

	@GetMapping()
	public String facCollectInformation() {
		return prefix + "/facCollectInformation";
	}

	/**
	 * 查询团建费报销列表
	 */

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacCollectInformation facCollectInformation) {
		startPage();
		List<FacCollectInformation> list = facCollectInformationService
				.selectFacCollectInformationList(facCollectInformation);
		return getDataTable(list);
	}

	/**
	 * 导出团建费报销列表
	 */

	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(FacCollectInformation facCollectInformation) {
		List<FacCollectInformation> list = facCollectInformationService
				.selectFacCollectInformationList(facCollectInformation);
		ExcelUtil<FacCollectInformation> util = new ExcelUtil<FacCollectInformation>(
				FacCollectInformation.class);
		return util.exportExcel(list, "facCollectInformation");
	}
	/**
	 * 查看行程安排详情
	 */
	@PostMapping("/query")
	@ResponseBody
	public TableDataInfo detail1(String num) {
		startPage();
		FacCollectInformation fac = new FacCollectInformation();
		fac.setNum(num);
		List<FacCollectInformation> list = facCollectInformationService
				.selectFacCollectInformationList(fac); 
		if (list != null) { 
			return getDataTable(list);
		} else {
			List<String> a = new ArrayList<>();
			return getDataTable(a);
		}
	}
	/**
	 * 新增团建费报销
	 */
	@GetMapping("/add")
	public String add(String num, ModelMap mmap) {
		mmap.put("num", num);
		return prefix + "/add";
	}

	/**
	 * 新增保存团建费报销
	 */

	@Log(title = "团建费报销", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FacCollectInformation facCollectInformation) {
		facCollectInformation.setMoney(facCollectInformation.getAmount());
		return toAjax(facCollectInformationService
				.insertFacCollectInformation(facCollectInformation));
	}

	/**
	 * 修改团建费报销
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		FacCollectInformation facCollectInformation = facCollectInformationService
				.selectFacCollectInformationById(id);
		mmap.put("facCollectInformation", facCollectInformation);
		return prefix + "/edit";
	}

	/**
	 * 修改保存团建费报销
	 */

	@Log(title = "团建费报销", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacCollectInformation facCollectInformation) {
		return toAjax(facCollectInformationService
				.updateFacCollectInformation(facCollectInformation));
	}

	/**
	 * 删除团建费报销
	 */

	@Log(title = "团建费报销", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(facCollectInformationService
				.deleteFacCollectInformationByIds(ids));
	}

}
