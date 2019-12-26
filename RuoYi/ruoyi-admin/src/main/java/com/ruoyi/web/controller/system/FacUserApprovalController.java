package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.finance.*;
import com.ruoyi.system.mapper.finance.FacReiAdiApplyMapper;
import com.ruoyi.system.mapper.finance.FacReimburseApplyMapper;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @Autowired
    private IFacCollectApplyService facCollectApplyService;
    @Autowired
    private IFacCostReimburseService facCostReimburseService;
    @Autowired
    private IFacZhaoDaiLimitService facZhaoDaiLimitService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private FacReiAdiApplyMapper facReiAdiApplyMapper;
    @Autowired
    private FacReimburseApplyMapper facReimburseApplyMapper;

    @Autowired
    private ISysDeptService sysDeptService;


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

        if (ShiroUtils.getUserId() == 1L) {
            facUserApproval.setApprovalState("3");
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
     * 查询统计财务金额
     */

    @PostMapping("/user")
    @ResponseBody
    public TableDataInfo lists(SysUser user) {

        Map<String, FacAmountApply> map = facUserApprovalService.selectDept(user);
        List<FacAmountApply> list = new ArrayList<>();
        for (FacAmountApply s : map.values()) {//遍历map的值
            list.add(s);
        }
        //String[] order = {"新普互联", "效果广告业务事业部", "搜索业务部", "百度营销服务一组", "百度营销服务二组", "百度营销服务三组", "大客户业务部", "新媒体业务部", "商务支持部", "SEO", "华北大区业务部", "内容创意与视频部", "策略策划部", "技术部", "品牌广告业务事业部", "直客销售部", "客服部", "运营管理中心", "财务部", "人力资源部", "行政部", "CW-北京", "CW-新疆", "CW-上海", "HR-北京", "HR-上海", "AD-北京", "AD-上海", "总办", "市场拓展部", "运营管理部", "法务部", "运营管理-北京", "运营管理-新疆", "运营培训总监", "大客户营销服务一部", "大客户营销服务二部", "创意设计部", "华南大区业务部", "数字营销服务部（深圳）", "上海优寰分公司", "SH-销售部", "SH-媒介部", "SH-营销服务部", "SH-商务支持", "SH-策略部", "销售部", "销售一组", "销售二组", "媒介部", "华北媒介部", "头条组", "快手组", "AD-深圳",  "总计"};
        String[] order = sysDeptService.selectDeptNames();
        List<String> definedOrder = Arrays.asList(order);
        Collections.sort(list, new Comparator<FacAmountApply>() {
            public int compare(FacAmountApply o1, FacAmountApply o2) {
                int io1 = definedOrder.indexOf(o1.getDeptName());
                int io2 = definedOrder.indexOf(o2.getDeptName());
                return io1 - io2;
            }
        });
        return getDataTable(list);
    }

    /**
     * 新增财务审批金额统计
     */
    @GetMapping("/user")
    public String amount() {
        return prefix + "/user";
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
    public String edit(@PathVariable("approvalId") Long approvalId, ModelMap map) {

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
        map.put("userIdName", sysUserService.selectUserById(facUserApproval.getApplicantId()).getUserName());
        map.put("facCollectApply", new FacCollectApply());
        map.put("edit1", "false");
        map.put("edit2", "false");
        map.put("edit3", "false");

        String nums = facUserApproval.getApplyId().substring(0, 2);
        if (nums.equals("BX")) {
            FacReimburseApply reimburseApply = new FacReimburseApply();
            reimburseApply.setNum(facUserApproval.getApplyId());
            List<FacReimburseApply> reimburseApplies = facReimburseApplyService.selectFacReimburseApplyList(reimburseApply);
            String type = reimburseApplies.get(0).getType();
            if (type.equals("日常报销")) {
                map.put("type", "rcbx");

                //查询本报销是否有招待费报销项
                ReiHospitalityApply reiHospitalityApplyVO = new ReiHospitalityApply();
                reiHospitalityApplyVO.setNum(facUserApproval.getApplyId());
                List<ReiHospitalityApply> reiHospitalityApplies1 = facReimburseApplyService.selectReiHospitalityApplyList(reiHospitalityApplyVO);
                //查询招待费限额
                Double limitAmount = 0.00;
                List<Long> roleIds = facReimburseApplyService.selectRole(facUserApproval.getApplicantId());
                for (Long roleId : roleIds) {
                    FacZhaoDaiLimit facZhaoDaiLimit = facZhaoDaiLimitService.selectFacZhaoDaiLimitByRoleId(roleId);
                    if (facZhaoDaiLimit == null) {
                        continue;
                    } else if (facZhaoDaiLimit.getLimitAmount().doubleValue() > limitAmount) {
                        limitAmount = facZhaoDaiLimit.getLimitAmount().doubleValue();
                    }
                }

                List<ReiTrafficApply> reiTrafficApply1 = facReimburseApplyService.selectReiTrafficApply(facUserApproval.getApplyId());
                List<FacReiAdiApply> facReimburseApplys = facReimburseApplyService.selectFacReiAdiApply(facUserApproval.getApplyId());
                if (reiHospitalityApplies1 != null && reiHospitalityApplies1.size() > 0) {
                    map.put("edit1", "ture");
                }
                if (reiTrafficApply1 != null && reiTrafficApply1.size() > 0) {
                    map.put("edit2", "ture");
                }
                if (facReimburseApplys != null && facReimburseApplys.size() > 0) {
                    map.put("edit3", "ture");
                }

                //查询招待费当前已用额度
                //计算报销人当月已审批和审批中的招待费报销金额
                Double amount = 0.00;
                FacReimburseApply facReimburseApplyVO = new FacReimburseApply();
                facReimburseApplyVO.setLoanUser(facUserApproval.getApplicantId());
                List<FacReimburseApply> facReimburseApplyList = facReimburseApplyService.selectCurrentMonthFacReimburseApplyList(facReimburseApplyVO);
                for (FacReimburseApply apply : facReimburseApplyList) {

                    if (apply.getType().equals("日常报销")) {
                        //计算 待审批的 审批中的 审批成功的
                        if (apply.getStatus() == null || apply.getStatus().equals("1") || apply.getStatus().equals("3")) {
                            ReiHospitalityApply hospitalityApply = new ReiHospitalityApply();
                            hospitalityApply.setNum(apply.getNum());
                            List<ReiHospitalityApply> reiHospitalityApplies = facReimburseApplyService.selectCurrentMonthReiHospitalityApplyList(hospitalityApply);
                            for (ReiHospitalityApply reiHospitalityApply : reiHospitalityApplies) {
                                amount = amount + reiHospitalityApply.getAmount();
                            }
                        }

                    }

                }
                //本次报销有招待费且大于限额
                if (reiHospitalityApplies1.size() > 0 && limitAmount < amount) {
                    map.put("isOverAmountLimit", "true");
                } else {
                    map.put("isOverAmountLimit", "false");
                }

                //判断公出费是否超额
                List<Long> longs = facReimburseApplyService.selectRole(facUserApproval.getApplicantId());
                for (Long roleId : longs) {
                    if (roleId == 9 || roleId == 10) {
                        //查询当前创建人一个月内审批中 审批通过 未审批的公出交通费金额
                        Double sumAmount = 0.00;
                        FacReimburseApply selectVO = new FacReimburseApply();
                        selectVO.setLoanUser(facUserApproval.getApplicantId());
                        List<FacReimburseApply> facReimburseApplies1 = facReimburseApplyService.selectCurrentMonthFacReimburseApplyList(selectVO);
                        for (FacReimburseApply apply : facReimburseApplies1) {

                            if (apply.getType().equals("日常报销")) {
                                //计算 待审批的 审批中的 审批成功的
                                if (apply.getStatus() == null || apply.getStatus().equals("1") || apply.getStatus().equals("3")) {
                                    List<ReiTrafficApply> reiTrafficApplyList = facReimburseApplyService.selectReiTrafficApply(apply.getNum());
                                    for (ReiTrafficApply trafficApply : reiTrafficApplyList) {
                                        if ("公出".equals(trafficApply.getType())) {
                                            sumAmount = sumAmount + trafficApply.getAmount();
                                        }
                                    }
                                }

                            }

                        }
                        List<ReiTrafficApply> reiTrafficApplyList = facReimburseApplyService.selectReiTrafficApply(facUserApproval.getApplyId());
                        map.put("isOverGongChuLimit", "false");
                        for (int i = 0; i < reiTrafficApplyList.size(); i++) {
                            if (reiTrafficApplyList.get(i).getType().equals("公出")) {
                                if (sumAmount > 800) {
                                    map.put("isOverGongChuLimit", "true");
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }

            } else if (type.equals("团建报销")) {
                FacCollectApply facCollectApply = null;
                facCollectApply = facCollectApplyService.selectFacCollectApplyByNum(reimburseApplies.get(0).getJKnum());
                map.put("type", "tjbx");
                map.put("facCollectApply", facCollectApply);
            } else if (type.equals("差旅报销")) {
                map.put("type", "clbx");
            }
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
     * 批量财务审批
     */
    @Log(title = "批量财务审批", businessType = BusinessType.UPDATE)
    @PostMapping("/piliang")
    @ResponseBody
    public AjaxResult piLiang() {

        FacUserApproval facUserApproval = new FacUserApproval();
        facUserApproval.setApproverId(ShiroUtils.getUserId());
        facUserApproval.setApprovalState("3");
        facUserApproval.setApprovalSight("1");
        List<FacUserApproval> fac = facUserApprovalService.selectFacUserApprovalList(facUserApproval);
        if (ShiroUtils.getUserId() == 103L) {
            if (fac != null && fac.size() > 0) {
                for (FacUserApproval f : fac) {
                    f.setApprovalState("1");
                    facUserApprovalService.updatepiliang(f);
                }
            }
        } else {
            if (fac != null && fac.size() > 0) {
                for (FacUserApproval f : fac) {
                    f.setApprovalState("1");
                    facUserApprovalService.updateFacUserApproval(f);
                }
            }
        }

        return toAjax(1);
    }

    /**
     * 批量财务审批
     */
    @Log(title = "批量财务审批", businessType = BusinessType.UPDATE)
    @PostMapping("/piliang3")
    @ResponseBody
    public AjaxResult piLiang3(String ids) {
        if (ids != null) {
            List<FacUserApproval> fac = facUserApprovalService.selectApprovalByIds(ids);

            if (ShiroUtils.getUserId() == 103L) {
                if (fac != null && fac.size() > 0) {
                    for (FacUserApproval f : fac) {
                        f.setApprovalState("1");
                        facUserApprovalService.updatepiliang(f);
                    }
                }
            } else {
                if (fac != null && fac.size() > 0) {
                    for (FacUserApproval f : fac) {
                        f.setApprovalState("1");
                        facUserApprovalService.updateFacUserApproval(f);
                    }
                }
            }

            return toAjax(1);
        }
        return toAjax(1);
    }

    /**
     * 批量财务审批
     */
    @Log(title = "批量财务审批", businessType = BusinessType.UPDATE)
    @PostMapping("/piliang4")
    @ResponseBody
    public AjaxResult piLiang4(String ids) {

        List<FacUserApproval> fac = facUserApprovalService.selectApprovalByIds(ids);
        if (ShiroUtils.getUserId() == 103L) {
            if (fac != null && fac.size() > 0) {
                for (FacUserApproval f : fac) {
                    f.setApprovalState("2");
                    facUserApprovalService.updatepiliang(f);
                }
            }
        } else {
            if (fac != null && fac.size() > 0) {
                for (FacUserApproval f : fac) {
                    f.setApprovalState("2");
                    facUserApprovalService.updateFacUserApproval(f);
                }
            }
        }

        return toAjax(1);
    }

    /**
     * 批量财务审批
     */
    @Log(title = "批量财务审批", businessType = BusinessType.UPDATE)
    @PostMapping("/piliangs")
    @ResponseBody
    public AjaxResult piLiang2() {

        FacUserApproval facUserApproval = new FacUserApproval();
        facUserApproval.setApproverId(ShiroUtils.getUserId());
        facUserApproval.setApprovalState("3");
        facUserApproval.setApprovalSight("1");
        List<FacUserApproval> fac = facUserApprovalService.selectFacUserApprovalList(facUserApproval);
        if (ShiroUtils.getUserId() == 103L) {
            if (fac != null && fac.size() > 0) {
                for (FacUserApproval f : fac) {
                    f.setApprovalState("2");
                    facUserApprovalService.updatepiliang(f);
                }
            }
        } else {
            if (fac != null && fac.size() > 0) {
                for (FacUserApproval f : fac) {
                    f.setApprovalState("2");
                    facUserApprovalService.updateFacUserApproval(f);
                }
            }
        }

        return toAjax(1);
    }


    /**
     * 修改财务审批
     */
    @GetMapping("/piliang")
    public String piLianga() {
        return prefix + "/piliang";

    }


    /**
     * 批量修改财务审批
     */
    @GetMapping("/approvalModer")
    public String PLshenpi(String ids, ModelMap map) {
        map.put("applyId", ids);
        map.put("approvalId", ids);
        return prefix + "/approvalModer";
    }


    /**
     * 批量财务审批
     */
    @Log(title = "批量财务审批", businessType = BusinessType.UPDATE)
    @PostMapping("/approvalModer")
    @ResponseBody
    public AjaxResult PLshenpi(FacUserApproval facUserApproval) {
        if (facUserApproval.getApplyId() != null && facUserApproval.getApplyId() != "") {
            List<FacUserApproval> fac = facUserApprovalService.selectApprovalByIds(facUserApproval.getApplyId());

            if (ShiroUtils.getUserId() == 103L) {
                if (fac != null && fac.size() > 0) {
                    for (FacUserApproval f : fac) {
                        f.setApprovalState(facUserApproval.getApprovalState());
                        f.setOpi(facUserApproval.getOpi());
                        facUserApprovalService.updatepiliang(f);
                        if (f.getApplyId().startsWith("ZD")) {
                            //招待费同意则生成招待费的报销信息
                            FacHospitalityApply selectHospitalityApply = new FacHospitalityApply();
                            selectHospitalityApply.setNum(f.getApplyId());
                            List<FacHospitalityApply> facHospitalityApplies = facHospitalityApplyService.selectFacHospitalityApplyList(selectHospitalityApply);
                            if (facHospitalityApplies.size() > 0) {
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
                }
            } else {
                if (fac != null && fac.size() > 0) {
                    for (FacUserApproval f : fac) {
                        f.setApprovalState(facUserApproval.getApprovalState());
                        f.setOpi(facUserApproval.getOpi());
                        facUserApprovalService.updateFacUserApproval(f);
                    }
                }
            }

            return toAjax(1);
        }
        return toAjax(1);
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
            if (facUserApproval.getApplyId().startsWith("ZD")) {
                //招待费同意则生成招待费的报销信息
                FacHospitalityApply selectHospitalityApply = new FacHospitalityApply();
                selectHospitalityApply.setNum(facUserApproval.getApplyId());
                List<FacHospitalityApply> facHospitalityApplies = facHospitalityApplyService.selectFacHospitalityApplyList(selectHospitalityApply);

                if (facHospitalityApplies.size() > 0) {

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

        map.put("edit1", "false");
        map.put("edit2", "false");
        map.put("edit3", "false");
        ReiHospitalityApply reiHospitalityApplyVO = new ReiHospitalityApply();
        reiHospitalityApplyVO.setNum(facUserApproval.getApplyId());
        List<ReiHospitalityApply> reiHospitalityApplies1 = facReimburseApplyService.selectReiHospitalityApplyList(reiHospitalityApplyVO);
        List<ReiTrafficApply> reiTrafficApply1 = facReimburseApplyService.selectReiTrafficApply(facUserApproval.getApplyId());
        List<FacReiAdiApply> facReimburseApplys = facReimburseApplyService.selectFacReiAdiApply(facUserApproval.getApplyId());
        if (reiHospitalityApplies1 != null && reiHospitalityApplies1.size() > 0) {
            map.put("edit1", "ture");
        }
        if (reiTrafficApply1 != null && reiTrafficApply1.size() > 0) {
            map.put("edit2", "ture");
        }
        if (facReimburseApplys != null && facReimburseApplys.size() > 0) {
            map.put("edit3", "ture");
        }
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
        if (ShiroUtils.getUserId() == 1L) {
            facUserApproval.setApprovalSight("1");
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
