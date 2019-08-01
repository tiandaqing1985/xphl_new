package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.finance.FacPayPublicApply;
import com.ruoyi.system.service.finance.IFacPayPublicApplyService;
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
 * 对公申请 信息操作处理
 *
 * @author ruoyi
 * @date 2019-08-01
 */
@Controller
@RequestMapping("/system/facPayPublicApply")
public class FacPayPublicApplyController extends BaseController {
    private String prefix = "system/facPayPublicApply";

    @Autowired
    private IFacPayPublicApplyService facPayPublicApplyService;

    @GetMapping()
    public String facPayPublicApply() {
        return prefix + "/facPayPublicApply";
    }

    /**
     * 查询对公申请列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FacPayPublicApply facPayPublicApply) {
        startPage();
        List<FacPayPublicApply> list = facPayPublicApplyService.selectFacPayPublicApplyList(facPayPublicApply);
        return getDataTable(list);
    }


    /**
     * 导出对公申请列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacPayPublicApply facPayPublicApply) {
        List<FacPayPublicApply> list = facPayPublicApplyService.selectFacPayPublicApplyList(facPayPublicApply);
        ExcelUtil<FacPayPublicApply> util = new ExcelUtil<FacPayPublicApply>(FacPayPublicApply.class);
        return util.exportExcel(list, "facPayPublicApply");
    }

    /**
     * 新增对公申请
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存对公申请
     */
    @Log(title = "对公申请", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FacPayPublicApply facPayPublicApply) {
        System.out.println("当前登录人id-----"+ShiroUtils.getUserId());
        facPayPublicApply.setUser(new Long("260"));
        return facPayPublicApplyService.insertFacPayPublicApply(facPayPublicApply);
    }

    /**
     * 修改对公申请
     */
    @GetMapping("/edit/{num}")
    public String edit(@PathVariable("num") String num, ModelMap mmap) {
        System.out.println("申请编号"+num);
        FacPayPublicApply facPayPublicApply = facPayPublicApplyService.selectFacPayPublicApplyById(num);
        mmap.put("facPayPublicApply", facPayPublicApply);
        return prefix + "/edit";
    }

    /**
     * 修改保存对公申请
     */
    @Log(title = "对公申请", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FacPayPublicApply facPayPublicApply) {
        return toAjax(facPayPublicApplyService.updateFacPayPublicApply(facPayPublicApply));
    }

    /**
     * 删除对公申请
     */
    @Log(title = "对公申请", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(facPayPublicApplyService.deleteFacPayPublicApplyByIds(ids));
    }

}
