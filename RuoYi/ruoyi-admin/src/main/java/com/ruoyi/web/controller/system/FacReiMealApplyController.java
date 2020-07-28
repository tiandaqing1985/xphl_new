package com.ruoyi.web.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ruoyi.system.domain.OaDingding;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.UserModel;
import com.ruoyi.system.domain.finance.FacReiMealApply;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.IFacReiMealApplyService;
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
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 加班餐报销 信息操作处理
 * 
 * @author ruoyi
 * @date 2020-07-21
 */
@Controller
@RequestMapping("/system/facReiMealApply")
public class FacReiMealApplyController extends BaseController
{
    private String prefix = "system/facReiMealApply";
	
	@Autowired
	private IFacReiMealApplyService facReiMealApplyService;

	@Autowired
	private ISysUserService userService;

	@GetMapping()
	public String facReiMealApply()
	{
	    return prefix + "/facReiMealApply";
	}
	
	/**
	 * 查询加班餐报销列表
	 */

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FacReiMealApply facReiMealApply)
	{
		startPage();
        List<FacReiMealApply> list = facReiMealApplyService.selectFacReiMealApplyList(facReiMealApply);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出加班餐报销列表
	 */

    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacReiMealApply facReiMealApply)
    {
    	List<FacReiMealApply> list = facReiMealApplyService.selectFacReiMealApplyList(facReiMealApply);
        ExcelUtil<FacReiMealApply> util = new ExcelUtil<FacReiMealApply>(FacReiMealApply.class);
        return util.exportExcel(list, "facReiMealApply");
    }
	
	/**
	 * 新增加班餐报销
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}




	/**
	 * 获取用户数据
	 */
	@GetMapping("/userModel")
	@ResponseBody
	public AjaxResult userModel(Date addDate)
	{
		if(addDate==null){
			return AjaxResult.warn("请选择加班时间");
		}
		List<UserModel> sysUsers = facReiMealApplyService.selectAllUserModel(addDate);
		AjaxResult ajax = new AjaxResult();
		ajax.put("code", 200);
		ajax.put("value", sysUsers);
		return ajax;
	}




	/**
	 * 新增保存加班餐报销
	 */

	@Log(title = "加班餐报销", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FacReiMealApply facReiMealApply)
	{		
		return toAjax(facReiMealApplyService.insertFacReiMealApply(facReiMealApply));
	}

	/**
	 * 修改加班餐报销
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		FacReiMealApply facReiMealApply = facReiMealApplyService.selectFacReiMealApplyById(id);
		mmap.put("facReiMealApply", facReiMealApply);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存加班餐报销
	 */

	@Log(title = "加班餐报销", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FacReiMealApply facReiMealApply)
	{		
		return toAjax(facReiMealApplyService.updateFacReiMealApply(facReiMealApply));
	}
	
	/**
	 * 删除加班餐报销
	 */

	@Log(title = "加班餐报销", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(facReiMealApplyService.deleteFacReiMealApplyByIds(ids));
	}
	
}
