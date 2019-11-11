package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.system.domain.finance.FacHospitalityApply;
import com.ruoyi.system.domain.finance.ReiHospitalityApply;
import com.ruoyi.system.service.finance.IFacHospitalityApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.finance.FacReimburseApply;
import com.ruoyi.system.domain.finance.FacUserApproval;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.IFacReimburseApplyService;
import com.ruoyi.system.service.finance.IFacUserApprovalService;

/**
 * 财务审批 信息操作处理
 *
 * @author ruoyi
 * @date 2019-09-27
 */
@Controller
@RequestMapping("/system/facUserApproval")
public class FacUserApprovalController extends BaseController {
    private String prefix = "system/facUserApproval";

    @Autowired
    private IFacUserApprovalService facUserApprovalService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IFacReimburseApplyService facReimburseApplyService;
    @Autowired
    private IFacHospitalityApplyService facHospitalityApplyService;

    // @RequiresPermissions("system:facUserApproval:view")
    @GetMapping()
    public String facUserApproval() {
        return prefix + "/facUserApproval";
    }

    /**
     * 查询财务审批列表
     */

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FacUserApproval facUserApproval) {
        startPage();
        facUserApproval.setApprovalState("3");
        facUserApproval.setApproverId(ShiroUtils.getUserId());
        facUserApproval.setApprovalSight("1");
        List<FacUserApproval> list = facUserApprovalService
                .selectFacUserApprovalList(facUserApproval);
        for (FacUserApproval v : list) {
            SysUser applicant = sysUserService.selectUserById(v.getApplicantId());
            SysUser approver = sysUserService.selectUserById(v.getApproverId());
            if (applicant != null) {
                v.setApplicantName(sysUserService.selectUserById(v.getApplicantId()).getUserName());
            }
            if (approver != null) {
                v.setApproverName(sysUserService.selectUserById(v.getApproverId()).getUserName());
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出财务审批列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacUserApproval facUserApproval) {
        List<FacUserApproval> list = facUserApprovalService
                .selectFacUserApprovalList(facUserApproval);
        ExcelUtil<FacUserApproval> util = new ExcelUtil<FacUserApproval>(
                FacUserApproval.class);
        return util.exportExcel(list, "facUserApproval");
    }

    /**
     * 新增财务审批
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存财务审批
     */
    @Log(title = "财务审批", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FacUserApproval facUserApproval) {
        return toAjax(
                facUserApprovalService.insertFacUserApproval(facUserApproval));
    }

    /**
     * 修改财务审批
     */
    @GetMapping("/edit/{approvalId}")
    public String edit(@PathVariable("approvalId") Long approvalId,
                       ModelMap map) {

        FacUserApproval facUserApproval = facUserApprovalService
                .selectFacUserApprovalById(approvalId);
        FacReimburseApply facReimburseApply = new FacReimburseApply();
        facReimburseApply.setNum(facUserApproval.getApplyId());
        List<FacReimburseApply> facReimburseApplies = facReimburseApplyService
                .selectFacReimburseApplyList(facReimburseApply);
        if (facReimburseApplies.size() > 0) {
            facUserApproval.setName(facReimburseApplies.get(0).getName());
        }

        map.put("facUserApproval", facUserApproval);
        map.put("num", facUserApproval.getApplyId());
        map.put("msg", "1");
        map.put("deptName", ShiroUtils.getSysUser().getDept().getDeptName());
        map.put("userId", ShiroUtils.getUserId());
        map.put("name", facUserApproval.getProjectName());
        map.put("userName", facUserApproval.getName());

        String nums = facUserApproval.getApplyId().substring(0, 2);
        if (nums.equals("BX")) {
            return prefix + "/baoxiaoDetails";
        } else if (nums.equals("JK")) {
            return prefix + "/jiekuanDetails";
        } else if (nums.equals("CL")) {
            return prefix + "/chailvDetails";
        } else if (nums.equals("HK")) {
            return prefix + "/huankuanDetails";
        } else if (nums.equals("DG")) {
            return prefix + "/duigongDetails";
        } else if (nums.equals("TJ")) {
            return prefix + "/tuanjianDetails";
        } else {
            return prefix + "/zhaodaiDetails";
        }

    }


    /**
     * 修改财务审批
     */
    @GetMapping("/editSa/{approvalId}")
    public String editSa(@PathVariable("approvalId") Long approvalId,
                         ModelMap map) {

        FacUserApproval facUserApproval = facUserApprovalService
                .selectFacUserApprovalById(approvalId);
        map.put("facUserApproval", facUserApproval);
        map.put("approvalId", facUserApproval.getApprovalId());
        return prefix + "/editSa";

    }


    /**
     * 修改保存财务审批
     */
    @Log(title = "财务审批", businessType = BusinessType.UPDATE)
    @PostMapping("/editSa")
    @ResponseBody
    public AjaxResult editSa(FacUserApproval facUserApproval) {
        FacUserApproval fac = facUserApprovalService
                .selectFacUserApprovalById(facUserApproval.getApprovalId());
        fac.setApprovalState(facUserApproval.getApprovalState());
        fac.setOpi(facUserApproval.getOpi());
        return toAjax(facUserApprovalService.updateFacUserApproval(fac));
    }


    /**
     * 修改保存财务审批
     */
    @Log(title = "财务审批", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FacUserApproval facUserApproval) {

        FacUserApproval facUser = new FacUserApproval();
        facUser.setApplyId(facUserApproval.getApplyId());
        facUser.setApproverId(facUserApproval.getApproverId());
        List<FacUserApproval> list = facUserApprovalService.selectFacUserApprovalList(facUser);
        FacUserApproval fac = list.get(0);
        fac.setApprovalState(facUserApproval.getApprovalState());
        fac.setOpi(facUserApproval.getOpi());
        int status = facUserApprovalService.updateFacUserApproval(fac);
        //当前申请所有审批人已经同意
        if (status == 0) {
            if(facUserApproval.getApplyId().startsWith("ZD")){
                //招待费同意则生成招待费的报销信息
                FacHospitalityApply selectHospitalityApply = new FacHospitalityApply();
                selectHospitalityApply.setNum(facUserApproval.getApplyId());
                List<FacHospitalityApply> facHospitalityApplies = facHospitalityApplyService.selectFacHospitalityApplyList(selectHospitalityApply);

                if(facHospitalityApplies.size()>0){

                    selectHospitalityApply = facHospitalityApplies.get(0);
                    ReiHospitalityApply reiHospitalityApply = new ReiHospitalityApply();
                    reiHospitalityApply.setDdDate(selectHospitalityApply.getZdDate());
                    reiHospitalityApply.setAmount(selectHospitalityApply.getAmount());
                    reiHospitalityApply.setAddUser(selectHospitalityApply.getLoanId());
                    reiHospitalityApply.setReason(selectHospitalityApply.getReason());
                    reiHospitalityApply.setUser(selectHospitalityApply.getUserId());
                    reiHospitalityApply.setApplyNum(selectHospitalityApply.getNum());
                    facReimburseApplyService.insertReiHospitalityApply(reiHospitalityApply);

                }
            }
        }
        return toAjax(1);
    }


    /**
     * 查看借款详情
     */
    @PostMapping("/querys")
    @ResponseBody
    public TableDataInfo detail1s(String num) {
        startPage();
        FacUserApproval facUserApproval = new FacUserApproval();
        facUserApproval.setApplyId(num);
        facUserApproval.setApplicantId(ShiroUtils.getUserId());

        List<FacUserApproval> list = facUserApprovalService
                .selectFacUserApprovalList(facUserApproval);
        if (list != null) {

            return getDataTable(list);
        } else {
            List<String> a = new ArrayList<>();
            return getDataTable(a);
        }
    }

    /**
     * 查看详情
     */
    @GetMapping("/detail")
    public String detail(@RequestParam("approvalId") Long approvalId,
                         ModelMap map) {

        FacUserApproval facUserApproval = facUserApprovalService
                .selectFacUserApprovalById(approvalId);

        FacReimburseApply facReimburseApply = new FacReimburseApply();
        facReimburseApply.setNum(facUserApproval.getApplyId());
        List<FacReimburseApply> facReimburseApplies = facReimburseApplyService
                .selectFacReimburseApplyList(facReimburseApply);
        if (facReimburseApplies.size() > 0) {
            facUserApproval.setName(facReimburseApplies.get(0).getName());
        }

        map.put("facUserApproval", facUserApproval);
        map.put("num", facUserApproval.getApplyId());
        map.put("msg", "1");
        map.put("deptName", ShiroUtils.getSysUser().getDept().getDeptName());
        map.put("userId", ShiroUtils.getUserId());
        map.put("name", facUserApproval.getName());

        map.put("opi", facUserApproval.getOpi());

        map.put("approvalState", facUserApproval.getApprovalState());

        String nums = facUserApproval.getApplyId().substring(0, 2);
        if (nums.equals("BX")) {
            return prefix + "/baoxiao";
        } else if (nums.equals("JK")) {
            return prefix + "/jiekuan";
        } else if (nums.equals("CL")) {
            return prefix + "/chailv";
        } else if (nums.equals("HK")) {
            return prefix + "/huankuan";
        } else if (nums.equals("DG")) {
            return prefix + "/duigong";
        } else if (nums.equals("TJ")) {
            return prefix + "/tuanjian";
        } else {
            return prefix + "/zhaodai";
        }

    }

    /**
     * 修改保存驳回财务审批
     */
    @Log(title = "财务审批", businessType = BusinessType.UPDATE)
    @PostMapping("/editnot")
    @ResponseBody
    public AjaxResult editNot(FacUserApproval facUserApproval) {
        List<FacUserApproval> list = facUserApprovalService
                .selectFacUserApprovalList(facUserApproval);
        FacUserApproval fac = list.get(0);
        fac.setApprovalState("2");
        fac.setApproverId(ShiroUtils.getUserId());
        return toAjax(
                facUserApprovalService.updateFacUserApproval(fac));
    }

    /**
     * 删除财务审批
     */
    @Log(title = "财务审批", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(facUserApprovalService.deleteFacUserApprovalByIds(ids));
    }

    /**
     * 已审批
     */
    @GetMapping("/endfacUserApproval")
    public String myApproval() {
        return prefix + "/endfacUserApproval";
    }

    /**
     * 已审批
     */
    @PostMapping("/endfacUserApproval")
    @ResponseBody
    public TableDataInfo endfacUserApproval(FacUserApproval facUserApproval) {
        startPage();
        facUserApproval.setApproverId(ShiroUtils.getUserId());
        List<FacUserApproval> list = facUserApprovalService
                .selectApplicantIdList(facUserApproval);

        for (FacUserApproval v : list) {
            SysUser applicant = sysUserService.selectUserById(v.getApplicantId());
            SysUser approver = sysUserService.selectUserById(v.getApproverId());
            if (applicant != null) {
                v.setApplicantName(sysUserService.selectUserById(v.getApplicantId()).getUserName());
            }
            if (approver != null) {
                v.setApproverName(sysUserService.selectUserById(v.getApproverId()).getUserName());
            }
        }

        return getDataTable(list);
    }

    /**
     * 我的审批
     */
    @GetMapping("/myApproval")
    public String approval() {
        return prefix + "/myApproval";
    }

    /**
     * 我的审批
     */
    @PostMapping("/myApproval")
    @ResponseBody
    public TableDataInfo approval(FacUserApproval facUserApproval) {
        startPage();
        facUserApproval.setApplicantId(ShiroUtils.getUserId());
        List<FacUserApproval> list = facUserApprovalService
                .selectApplicantIdList(facUserApproval);
        return getDataTable(list);
    }

    /**
     * 我的审批
     */
    @GetMapping("/apply")
    public String apply() {
        return prefix + "/apply";
    }

    /**
     * 我的审批
     */
    @PostMapping("/apply")
    @ResponseBody
    public TableDataInfo apply(FacUserApproval facUserApproval) {
        startPage();
        facUserApproval.setApproverId(ShiroUtils.getUserId());
        List<FacUserApproval> list = facUserApprovalService
                .selectApproverIdList(facUserApproval);
        return getDataTable(list);
    }

}
