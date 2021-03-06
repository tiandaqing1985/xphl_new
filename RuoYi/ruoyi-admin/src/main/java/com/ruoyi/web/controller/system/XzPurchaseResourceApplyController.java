package com.ruoyi.web.controller.system;

import java.util.*;

import com.ruoyi.framework.web.service.PermissionService;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import com.ruoyi.system.tool.PrintUtil;
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
import com.ruoyi.common.utils.IdWorker;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 采购资源申请 信息操作处理
 *
 * @author ruoyi
 * @date 2019-09-17
 */
@Controller
@RequestMapping("/system/xzPurchaseResourceApply")
public class XzPurchaseResourceApplyController extends BaseController {
    private String prefix = "system/xzPurchaseResourceApply";

    @Autowired
    private IXzPurchaseResourceApplyService xzPurchaseResourceApplyService;

    @Autowired
    private IXzPurchaseResourceService xzPurchaseResourceService;

    @Autowired
    private IXzResourceTypeService xzResourceTypeService;

    @Autowired
    private IXzApplyProcessService xzApplyProcessService;

    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private PermissionService permission;

    @RequiresPermissions("system:xzPurchaseResourceApply:view")
    @GetMapping()
    public String xzPurchaseResourceApply() {
        return prefix + "/xzPurchaseResourceApply";
    }

    /**
     * 查询采购资源申请列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(XzPurchaseResourceApply xzPurchaseResourceApply) {
        SysDept dept = sysDeptService.selectDeptById(ShiroUtils.getSysUser().getDeptId());
        if (ShiroUtils.getUserId() == 1 || ShiroUtils.getUserId() == 103 || permission.hasRole("xzzj").equals("")) { //超级管理员 和 任总看所有数据

        } else {
            if (ShiroUtils.getSysUser().getUserName().equals(dept.getLeader())) {
                //部门leader看部门所有
                xzPurchaseResourceApply.setDeptId(ShiroUtils.getDeptId());
            } else {
                //员工看自己的
                xzPurchaseResourceApply.setApplyUserId(ShiroUtils.getUserId());
            }
        }

        startPage();
        List<XzPurchaseResourceApply> list = xzPurchaseResourceApplyService.selectXzPurchaseResourceApplyList(xzPurchaseResourceApply);
        return getDataTable(list);
    }

    /**
     * 查询采购资源列表
     */
    @PostMapping("/toList")
    @ResponseBody
    public TableDataInfo toList(String code) {
        startPage();
        XzPurchaseResourceApply xzPra = xzPurchaseResourceApplyService.detail(code);
        if (xzPra != null) {
            List<XzPurchaseResourceApply> list = new ArrayList<>();
            list.add(xzPra);
            return getDataTable(list);
        } else {
            List<String> a = new ArrayList<>();
            return getDataTable(a);
        }
    }

    /**
     * 查看待审批申请leader
     */
    @PostMapping("/unlist")
    @ResponseBody
    public TableDataInfo unlist(XzPurchaseResourceApply xzPurchaseResourceApply, String approvalStatus) {
        //审批状态 1同意 2驳回 3未审批
        xzPurchaseResourceApply.setApprovalStatus(approvalStatus);
        if (xzPurchaseResourceApply.getApprovalStatus() == null || xzPurchaseResourceApply.getApprovalStatus().isEmpty()) {
            xzPurchaseResourceApply.setApprovalStatus("3");
            xzPurchaseResourceApply.setStatus("wait");
        } else {
            xzPurchaseResourceApply.setApprovalStatus(approvalStatus);
            if (approvalStatus.equals("1")) {
                xzPurchaseResourceApply.setStatus("success");
            } else if (approvalStatus.equals("2")) {
                xzPurchaseResourceApply.setStatus("fail");
            }
        }
        startPage();
        if (ShiroUtils.getUserId() != 1&&!permission.hasRole("xzzj").equals("")) {
            xzPurchaseResourceApply.setApproverId(ShiroUtils.getUserId());
        }
        List<XzPurchaseResourceApply> list = xzPurchaseResourceApplyService.selectXzPurchaseResourceApplyListByApprover(xzPurchaseResourceApply);
        return getDataTable(list);
    }

    /**
     * 查看采购申请-物资详情
     */
    @GetMapping("/toDetail/{id}")
    public String toDetail(@PathVariable("id") Long id, ModelMap mmap) {
        XzPurchaseResourceApply xzPurchaseResourceApply = xzPurchaseResourceApplyService.selectXzPurchaseResourceApplyById(id);
        List<XzPurchaseResource> xzPurchaseResources = xzPurchaseResourceService.selectXzPurchaseResourceListByCode(xzPurchaseResourceApply.getCode());
        xzPurchaseResourceApply.setXzPurchaseResource(xzPurchaseResources);
        XzApplyProcess xzApplyProcess = new XzApplyProcess();
        xzApplyProcess.setApplyId(xzPurchaseResourceApply.getId());
        List<XzApplyProcess> xzApplyProcesses = xzApplyProcessService.selectXzApplyProcessList(xzApplyProcess);

        mmap.put("code", xzPurchaseResourceApply.getCode());
        mmap.put("xzPurchaseResourceApply", xzPurchaseResourceApply);
        mmap.put("xzApplyProcesses", xzApplyProcesses);
        return prefix + "/detailTable";
    }

    /**
     * 查看采购申请-物资详情
     */
    @GetMapping("/toDetailList/{id}/{flg}")
    public String toDetailList(@PathVariable("id") Long id, @PathVariable String flg, ModelMap mmap) {
        XzPurchaseResourceApply xzPurchaseResourceApply = xzPurchaseResourceApplyService.selectXzPurchaseResourceApplyById(id);
        mmap.put("code", xzPurchaseResourceApply.getCode());
        mmap.put("isRelation", xzPurchaseResourceApply.getIsRelation());
        mmap.put("flg", flg);
        return prefix + "/detailList";
    }

    /**
     * 补录采购信息
     */
    @GetMapping("/toEdit/{id}")
    public String toEdit(@PathVariable("id") Long id, ModelMap mmap) {
        XzPurchaseResourceApply xzPurchaseResourceApply = xzPurchaseResourceApplyService.selectXzPurchaseResourceApplyById(id);
        mmap.put("xzPurchaseResourceApply", xzPurchaseResourceApply);
        return prefix + "/toEdit";
    }

    /**
     * 导出采购资源申请列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XzPurchaseResourceApply xzPurchaseResourceApply) {
        List<XzPurchaseResourceApply> list = xzPurchaseResourceApplyService.selectXzPurchaseResourceApplyList(xzPurchaseResourceApply);
        ExcelUtil<XzPurchaseResourceApply> util = new ExcelUtil<XzPurchaseResourceApply>(XzPurchaseResourceApply.class);
        return util.exportExcel(list, "xzPurchaseResourceApply");
    }

    /**
     * 新增采购资源申请
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        IdWorker idWorker = new IdWorker(0, 1);
        mmap.put("code", "cg" + idWorker.nextId());
        mmap.put("region", ShiroUtils.getSysUser().getArea());
        //获取采购类型
        mmap.put("typeList", xzResourceTypeService.selectXzResourceTypeByAll());
        mmap.put("userName", ShiroUtils.getSysUser().getUserName());
        return prefix + "/purResDetail";
    }

    /**
     * 新增保存采购资源申请
     */
    @Log(title = "采购资源申请", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(XzPurchaseResourceApply xz) {
        XzPurchaseResourceApply xzPurchaseResourceApply = new XzPurchaseResourceApply();
        xzPurchaseResourceApply.setCode(xz.getCode());
        xzPurchaseResourceApply.setPurpose(xz.getPurpose());
        xzPurchaseResourceApply.setPurposeComment(xz.getPurposeComment());
        xzPurchaseResourceApply.setApplyUserId(ShiroUtils.getUserId());
        xzPurchaseResourceApply.setPurchaseUserId(ShiroUtils.getUserId());
        xzPurchaseResourceApply.setCreateBy(ShiroUtils.getUserId().toString());
        xzPurchaseResourceApply.setCreateTime(new Date());
        //提交状态：1保存 2提交
        xzPurchaseResourceApply.setSubType("1");
        return toAjax(xzPurchaseResourceApplyService.insertXzPurchaseResourceApply(xzPurchaseResourceApply));
    }

    /**
     * 新增提交采购资源申请
     */
    @Log(title = "采购资源申请", businessType = BusinessType.INSERT)
    @PostMapping("/addSub")
    @ResponseBody
    public AjaxResult subAddSave(XzPurchaseResourceApply xz) {
        XzPurchaseResourceApply xzPurchaseResourceApply = new XzPurchaseResourceApply();
        xzPurchaseResourceApply.setCode(xz.getCode());
        xzPurchaseResourceApply.setPurpose(xz.getPurpose());
        xzPurchaseResourceApply.setPurposeComment(xz.getPurposeComment());
        xzPurchaseResourceApply.setApplyUserId(ShiroUtils.getUserId());
        xzPurchaseResourceApply.setPurchaseUserId(ShiroUtils.getUserId());
        xzPurchaseResourceApply.setCreateBy(ShiroUtils.getUserId().toString());
        xzPurchaseResourceApply.setCreateTime(new Date());
        xzPurchaseResourceApply.setSubTime(new Date());
        //提交状态：1保存 2提交
        xzPurchaseResourceApply.setSubType("2");
        return toAjax(xzPurchaseResourceApplyService.insertXzPurchaseResourceApply(xzPurchaseResourceApply));
    }

    /**
     * 修改采购资源申请
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        XzPurchaseResourceApply xzPurchaseResourceApply = xzPurchaseResourceApplyService.selectXzPurchaseResourceApplyById(id);
        mmap.put("code", xzPurchaseResourceApply.getCode());
        mmap.put("region", ShiroUtils.getSysUser().getArea());
        mmap.put("purpose", xzPurchaseResourceApply.getPurpose());
        mmap.put("purposeComment", xzPurchaseResourceApply.getPurposeComment());
        mmap.put("typeList", xzResourceTypeService.selectXzResourceTypeByAll());
        mmap.put("userName", ShiroUtils.getSysUser().getUserName());
        return prefix + "/purResDetail";
    }

    /**
     * 修改保存采购资源申请
     */
    @Log(title = "采购资源申请", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(XzPurchaseResourceApply xzPurchaseResourceApply) {
        return toAjax(xzPurchaseResourceApplyService.updateXzPurchaseResourceApply(xzPurchaseResourceApply));
    }

    /**
     * 查询采购资源列表
     */
    @PostMapping("/editSub/{code}")
    @ResponseBody
    public AjaxResult editSub(@PathVariable("code") String code) {
        //查询出该条数据
        XzPurchaseResourceApply xzPurchaseResourceApply = xzPurchaseResourceApplyService.selectXzPurchaseResourceApplyByCode(code);
        //更正实际总金额及浮动金额
        //查询现在各个物资的实际总金额
        xzPurchaseResourceApply.setActualPrice(xzPurchaseResourceApply.getActualPrice());
        xzPurchaseResourceApply.setFloatPrice(xzPurchaseResourceApply.getFloatPrice());
        return toAjax(xzPurchaseResourceApplyService.updateXzPurchaseResourceApply(xzPurchaseResourceApply));
    }


    /**
     * 删除采购资源申请
     */
    @Log(title = "采购资源申请", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long id) {
        return toAjax(xzPurchaseResourceApplyService.deleteXzPurchaseResourceApplyByIds(id.toString()));
    }


    /**
     * 查询资产子类型详细
     */
    @PostMapping(value = "/onSelect/{id}")
    @ResponseBody
    public List<XzResourceType> onSelect(@PathVariable("id") Long id) {
        List<XzResourceType> dataInfo = xzResourceTypeService.xzResourceDataByParentId(id);
        return dataInfo;
    }
}
