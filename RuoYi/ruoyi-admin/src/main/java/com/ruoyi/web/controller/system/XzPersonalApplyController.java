package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.ruoyi.framework.web.service.PermissionService;
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
import com.ruoyi.system.domain.XzAssetType;
import com.ruoyi.system.domain.XzPersonalApply;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.IXzAssetDataService;
import com.ruoyi.system.service.IXzAssetTypeService;
import com.ruoyi.system.service.IXzPersonalApplyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 个人申请 信息操作处理
 *
 * @author ruoyi
 * @date 2019-08-27
 */
@Controller
@RequestMapping("/system/xzPersonalApply")
public class XzPersonalApplyController extends BaseController {
    private String prefix = "system/xzPersonalApply";

    @Autowired
    private IXzPersonalApplyService xzPersonalApplyService;

    @Autowired
    private IXzAssetTypeService xzAssetTypeService;

    @Autowired
    private IXzAssetDataService xzAssetDataService;

    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private PermissionService permission;

    @RequiresPermissions("system:xzPersonalApply:view")
    @GetMapping()
    public String xzPersonalApply() {
        return prefix + "/xzPersonalApply";
    }

    @GetMapping("/toDistributionList")
    public String distributionList(ModelMap modelMap) {
        modelMap.put("userid",ShiroUtils.getUserId());
        return prefix + "/xzDistributionList";
    }

    /**
     * 查询个人申请列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(XzPersonalApply xzPersonalApply) {
        startPage();
        xzPersonalApply.setUserId(ShiroUtils.getUserId());
        List<XzPersonalApply> list = xzPersonalApplyService.selectXzPersonalApplyList(xzPersonalApply);
        return getDataTable(list);
    }

    /**
     * 查询待审批申请列表
     */
    @PostMapping("/distributionList")
    @ResponseBody
    public TableDataInfo distributionList(XzPersonalApply xzPersonalApply, String applyStatus) {
        xzPersonalApply.setApplyStatus(applyStatus);
        if (xzPersonalApply.getApplyStatus() == null || xzPersonalApply.getApplyStatus().isEmpty()) {
            xzPersonalApply.setApplyStatus("1");//查看申请状态为 1待审批 的数据
        }
        if (xzPersonalApply.getApplyType().equals("1")) {
            xzPersonalApply.setSort("2");//只查看办公用品的申请
        }
        if (xzPersonalApply.getApplyType().equals("2")) {
            xzPersonalApply.setSort("1");//只查看固定资产的申请
        }
        SysDept dept = sysDeptService.selectDeptById(ShiroUtils.getSysUser().getDeptId());

        if (permission.hasRole("xzzj").equals("") || ShiroUtils.getUserId() == 1 || ShiroUtils.getUserId() == 103 || ShiroUtils.getSysUser().getUserName().equals(dept.getLeader())) { //超级管理员 和 任总 行政部门leader看所有数据

        } else {
//            String region = ShiroUtils.getSysUser().getArea();
//            xzPersonalApply.setRegion(region);
        }

        startPage();
        List<XzPersonalApply> list = xzPersonalApplyService.selectXzPersonalApplyList(xzPersonalApply);
        return getDataTable(list);
    }


    /**
     * 查询已审批申请列表
     */
    @PostMapping("/already")
    @ResponseBody
    public TableDataInfo already(XzPersonalApply xzPersonalApply) {
        startPage();
        xzPersonalApply.setApplyStatus("2,3");//查看申请状态为 2 申请成功 3 申请失败 的数据
        xzPersonalApply.setSort("2");//只查看办公用品的申请
        SysDept dept = sysDeptService.selectDeptById(ShiroUtils.getSysUser().getDeptId());

        if (ShiroUtils.getUserId() == 1 || ShiroUtils.getUserId() == 103 || ShiroUtils.getSysUser().getUserName().equals(dept.getLeader())) { //超级管理员 和 任总 行政部门leader看所有数据
            xzPersonalApply.setRegion(xzPersonalApply.getRegion());
        } else {
            String region = ShiroUtils.getSysUser().getArea();
            xzPersonalApply.setRegion(region);
        }
        List<XzPersonalApply> list = xzPersonalApplyService.selectXzPersonalApplyList(xzPersonalApply);
        return getDataTable(list);
    }

    /**
     * 导出个人申请列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XzPersonalApply xzPersonalApply) {
        List<XzPersonalApply> list = xzPersonalApplyService.selectXzPersonalApplyList(xzPersonalApply);
        ExcelUtil<XzPersonalApply> util = new ExcelUtil<XzPersonalApply>(XzPersonalApply.class);
        return util.exportExcel(list, "xzPersonalApply");
    }

    /**
     * 新增个人申请
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        //获取办公用品资产父级类型
        mmap.put("typeList", xzAssetTypeService.selectXzAssetTypeByStaAll());
        List<String> areas = new ArrayList<>();
        areas.add(ShiroUtils.getSysUser().getArea());
        if(ShiroUtils.getSysUser().getArea().equals("1")){
            areas.add("5");
        }
        mmap.put("areas",areas);
        return prefix + "/add";
    }


    /**
     * 新增保存个人申请
     */
    @Log(title = "个人申请", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(XzPersonalApply xzPersonalApply) {
        xzPersonalApply.setUserId(ShiroUtils.getUserId());
        xzPersonalApply.setCreateBy(ShiroUtils.getUserId().toString());
        xzPersonalApply.setApplyStatus("1");//申请状态：1申请中 2申请成功 3申请失败
        xzPersonalApply.setApplyType("1");//申请类型：1资产申请 2借用申请
        xzPersonalApply.setSubmitType("2");//提交状态：1已保存 2已提交
        xzPersonalApply.setCreateTime(new Date());
        xzPersonalApply.setSort("2");
        return toAjax(xzPersonalApplyService.insertXzPersonalApply(xzPersonalApply));
    }

    /**
     * 修改个人申请
     */
    @GetMapping("/edit/{applyId}")
    public String edit(@PathVariable("applyId") Long applyId, ModelMap mmap) {
        XzPersonalApply xzPersonalApply = xzPersonalApplyService.selectXzPersonalApplyById(applyId);
        mmap.put("xzPersonalApply", xzPersonalApply);
        return prefix + "/edit";
    }

    /**
     * 修改保存个人申请
     */
    @Log(title = "个人申请", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(XzPersonalApply xzPersonalApply) {
        return toAjax(xzPersonalApplyService.updateXzPersonalApply(xzPersonalApply));
    }

    /**
     * 删除个人申请
     */
    @Log(title = "个人申请", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(xzPersonalApplyService.deleteXzPersonalApplyByIds(ids));
    }

    /**
     * 查询资产子类型详细
     */
    @PostMapping(value = "/onSelect/{id}")
    @ResponseBody
    public List<XzAssetType> onSelect(@PathVariable("id") Long id) {
        List<XzAssetType> dataInfo = xzAssetDataService.selectXzAssetDataByParentId(id);
        return dataInfo;
    }
}
