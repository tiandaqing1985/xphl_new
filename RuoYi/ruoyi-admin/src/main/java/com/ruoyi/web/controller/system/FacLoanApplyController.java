package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.finance.FacLoanApply;
import com.ruoyi.system.domain.finance.FacLoanRepayApply;
import com.ruoyi.system.domain.finance.FacUserApproval;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 借款申请 信息操作处理
 *
 * @author ruoyi
 * @date 2019-07-30
 */
@Controller
@RequestMapping("/system/facLoanApply")
public class FacLoanApplyController extends BaseController {
    private String prefix = "system/facLoanApply";
    private String prefixs = "system/facReimburseApply";

    @Autowired
    private IFacLoanApplyService facLoanApplyService;
    @Autowired
    private IFacLoanRepayApplyService facLoanRepayApplyService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IFacReimburseApplyService facReimburseApplyService;
    @Autowired
    private IFacUserApprovalService facUserApprovalService;
    @Autowired
    private IFacNumberTableService facNumberTableService;

    @GetMapping()
    public String facLoanApply() {
        return prefix + "/facLoanApply";
    }

    /**
     * 查询借款申请列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FacLoanApply facLoanApply) {
        startPage();
        if (ShiroUtils.getUserId() == 1L || ShiroUtils.getUserId() == 149L ||ShiroUtils.getUserId()==902L|| ShiroUtils.getUserId() == 110L||ShiroUtils.getUserId()==824L||ShiroUtils.getUserId() == 106L) {
            List<FacLoanApply> lists = facLoanApplyService
                    .selectFacLoanApplyList(facLoanApply);
            for (FacLoanApply v : lists) {
                v.setUserName(sysUserService.selectUserById(v.getLoanUser()).getUserName());
                FacUserApproval name = facUserApprovalService.selectApproval(v.getNum(), v.getLoanUser());
                if (name != null) {
                    if (facUserApprovalService.approverName(v.getNum()) != null) {
                        v.setAllName(facUserApprovalService.approverName(v.getNum()));
                    }
                    if (name.getApproverId() != null) {
                        v.setApprover(sysUserService.selectUserById(name.getApproverId()).getUserName());
                    }

                    if (name.getApprovalState().equals("3")) {
                        v.setApprovalStatus("4");
                    } else {
                        v.setApprovalStatus(name.getApprovalState());
                    }

                    if (name.getStates() != null) {
                        v.setApplyStatus(name.getStates());
                    }
                    if (v.getLoanUser() == 103 && v.getLoanUser() == 101) {
                        v.setApprovalStatus("1");
                    }
                } else {
                    v.setApprover("--");
                    v.setApprovalStatus("--");
                }
            }

            return getDataTable(lists);
        }
        facLoanApply.setLoanUser(ShiroUtils.getUserId());
        List<FacLoanApply> list = facLoanApplyService
                .selectFacLoanApplyList(facLoanApply);
        for (FacLoanApply v : list) {
            v.setUserName(sysUserService.selectUserById(v.getLoanUser()).getUserName());
            FacUserApproval name = facUserApprovalService.selectApproval(v.getNum(), v.getLoanUser());
            if (name != null) {
                if (facUserApprovalService.approverName(v.getNum()) != null) {
                    v.setAllName(facUserApprovalService.approverName(v.getNum()));
                }
                if (name.getApproverId() != null) {
                    v.setApprover(sysUserService.selectUserById(name.getApproverId()).getUserName());
                }
                if (name.getApprovalState().equals("3") && name.getApprovalLevel().equals(1)) {
                    v.setApprovalStatus("4");
                } else {
                    v.setApprovalStatus(name.getApprovalState());
                }

                if (name.getStates() != null) {
                    v.setApplyStatus(name.getStates());
                }
                if (ShiroUtils.getUserId() == 103 && ShiroUtils.getUserId() == 101) {
                    v.setApprovalStatus("1");
                }
            } else {
                v.setApprover("--");
                v.setApprovalStatus("--");
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出借款申请列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacLoanApply facLoanApply) {
        List<FacLoanApply> list = facLoanApplyService
                .selectFacLoanApplyList(facLoanApply);
        ExcelUtil<FacLoanApply> util = new ExcelUtil<FacLoanApply>(
                FacLoanApply.class);
        return util.exportExcel(list, "facLoanApply");
    }

    /**
     * 新增借款申请
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @GetMapping("/addSave")
    public String addSave(String id, ModelMap map) {
        map.put("id", id);
        return prefix + "/addSave";
    }

    /**
     * 新增保存借款申请
     *
     * @throws Exception
     */
    @Log(title = "借款申请", businessType = BusinessType.INSERT)
    @PostMapping("/addSove")
    @ResponseBody
    public AjaxResult addSove(FacLoanApply facLoanApply) throws Exception {

        facLoanApply.setLoanUser(ShiroUtils.getUserId());
        if (facLoanApply.getId() == null) {
            facLoanApply.setNum(facNumberTableService.getNum("JK", ShiroUtils.getDateId()));
            facLoanApplyService.insertApply(facLoanApply);
        } else {
            facLoanApplyService.updateFacLoanApply(facLoanApply);
        }
        return AjaxResult.success("操作成功");

    }

    /**
     * 新增保存借款申请
     *
     * @throws Exception
     */
    @Log(title = "借款申请", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FacLoanApply facLoanApply) throws Exception {
        facLoanApply.setLoanUser(ShiroUtils.getUserId());
        if (facLoanApply.getId() == null) {
            // 直接添加
            facLoanApply.setNum(facNumberTableService.getNum("JK", ShiroUtils.getDateId()));
        } else {
            // 更新
            facLoanApplyService.deleteFacLoanApplyByIds(facLoanApply.getId() + "");
        }
        return toAjax(facLoanApplyService.insertFacLoanApply(facLoanApply));

    }

    /**
     * 修改借款申请
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        FacLoanApply facLoanApply = facLoanApplyService.selectFacLoanApplyById(id);
        mmap.put("facLoanApply", facLoanApply);
        return prefix + "/edit";
    }

    /**
     * 修改保存借款申请
     */
    @Log(title = "借款申请", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FacLoanApply facLoanApply) {
        return toAjax(facLoanApplyService.updateFacLoanApply(facLoanApply));
    }

    /**
     * 删除借款申请
     */
    @Log(title = "借款申请", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(facLoanApplyService.deleteFacLoanApplyByIds(ids));
    }

    /**
     * 查看借款详情
     */
    @PostMapping("/querys")
    @ResponseBody
    public TableDataInfo detail1s(String num) {
        startPage();
        List<FacLoanApply> list = facLoanApplyService.deatils(num);
        if (list != null) {
            for (FacLoanApply facLoanApply : list) {
                SysUser sysUser = sysUserService.selectUserById(facLoanApply.getLoanUser());
                facLoanApply.setUserName(sysUser.getUserName());
            }
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
    public String detail(@RequestParam("id") Long id, ModelMap map) {
        FacLoanApply facLostApply = new FacLoanApply();
        facLostApply.setId(id);
        List<FacLoanApply> facReimburseApplies = facLoanApplyService
                .selectFacLoanApplyList(facLostApply);
        map.put("rid", id);
        map.put("num", facReimburseApplies.get(0).getNum());
        map.put("status", facReimburseApplies.get(0).getApplyStatus());
        return prefix + "/detail";
    }

    /**
     * 修改借款申请
     */
    @GetMapping("/jkDetail/{id}")
    public String jkDetail(@PathVariable("id") Long id, ModelMap map) {
        FacLoanApply facLostApply = new FacLoanApply();
        facLostApply.setId(id);
        List<FacLoanApply> facReimburseApplies = facLoanApplyService
                .selectFacLoanApplyList(facLostApply);
        map.put("rid", id);
        map.put("num", facReimburseApplies.get(0).getNum());
        map.put("status", facReimburseApplies.get(0).getApplyStatus());
        return prefix + "/detail";
    }


    /**
     * 还款
     **/
    @GetMapping("/repayment")
    public String repayment(@RequestParam("id") Long id, ModelMap map) {
        FacLoanApply facLostApply = new FacLoanApply();
        facLostApply.setId(id);
        List<FacLoanApply> facReimburseApplies = facLoanApplyService
                .selectFacLoanApplyList(facLostApply);
        map.put("rid", id);
        map.put("num", facReimburseApplies.get(0).getNum());
        map.put("status", facReimburseApplies.get(0).getApplyStatus());
        return prefix + "/repayment";
    }

    /**
     * 新增保存还款信息
     */
    @Log(title = "还款申请", businessType = BusinessType.INSERT)
    @PostMapping("/repayment")
    @ResponseBody
    public AjaxResult repayment(FacLoanRepayApply facLoanApply) {
        facLoanApply.setPayer(ShiroUtils.getUserId());
        return toAjax(
                facLoanRepayApplyService.insertFacLoanRepayApply(facLoanApply));
    }

    /**
     * 新增借款详情
     */
    @PostMapping("/repay")
    @ResponseBody
    public AjaxResult repay(FacLoanRepayApply facLoanRepayApply) {
        startPage();
        facLoanRepayApply.setPayer(ShiroUtils.getUserId());

        return toAjax(facLoanRepayApplyService
                .insertFacLoanRepayApply(facLoanRepayApply));
    }

    /**
     * 新增借款申请
     */
    @GetMapping("/repay")
    public String repay(@RequestParam String num, ModelMap map) {
        map.put("num", num);
        return prefix + "/repayAdd";
    }

    /**
     * 查看借款详情
     */
    @PostMapping("/repays")
    @ResponseBody
    public TableDataInfo repays(String num) {
        startPage();
        List<FacLoanRepayApply> list = facLoanRepayApplyService.selectList(num);
        if (list != null) {
            for (FacLoanRepayApply v : list) {
                v.setPayerName(sysUserService.selectUserById(v.getPayer()).getUserName());
            }
            return getDataTable(list);
        } else {
            List<String> a = new ArrayList<>();
            return getDataTable(a);
        }
    }

    /**
     * 新增借款申请
     */
    @GetMapping("/offset")
    public String offset(@RequestParam Long payer, ModelMap map) {
        map.put("payer", payer);
        return prefix + "/offset";
    }

    @PostMapping("/offset")
    @ResponseBody
    public TableDataInfo offset(Long payer) {
        startPage();
        FacLoanApply facLoanApply = new FacLoanApply();
        facLoanApply.setLoanUser(payer);
        List<FacLoanApply> list = facLoanApplyService
                .selectFacLoanApplyList(facLoanApply);

        for (FacLoanApply v : list) {
            v.setUserName(sysUserService.selectUserById(v.getLoanUser()).getUserName());
            FacUserApproval name = facUserApprovalService.selectApproval(v.getNum(), v.getLoanUser());
            if (name != null) {
                if (name.getApproverId() != null) {
                    String approid = sysUserService.selectUserById(name.getApproverId()).getUserName();
                    if (approid != null) {
                        v.setApprover(approid);
                    }
                }
                v.setApprovalStatus(name.getApprovalState());
                if (name.getStates() != null) {
                    v.setApplyStatus(name.getStates());
                }
            } else {
                v.setApprover("--");
                v.setApprovalStatus("--");
            }
        }
        return getDataTable(list);
    }

    /**
     * 新增借款申请
     */
    @GetMapping("/off")
    public String off(@RequestParam String id, ModelMap mmp) {
        mmp.put("num", facNumberTableService.getNum("BX", ShiroUtils.getDateId()));
        mmp.put("msg", "1");
        mmp.put("userName", ShiroUtils.getSysUser().getUserName());
        mmp.put("userId", ShiroUtils.getUserId());
        mmp.put("deptId", ShiroUtils.getDeptId());
        mmp.put("deptName", ShiroUtils.getSysUser().getDept().getDeptName());
        FacLoanApply facLoanApply = facLoanApplyService.selectFacLoanApplyById(id + "");
        mmp.put("JKnum", facLoanApply.getNum());
        List<Long> longs = facReimburseApplyService.selectRole(ShiroUtils.getUserId());
        for (Long l : longs) {
            if (l == 10 || l == 9) {
                mmp.put("dept", 10);
                break;
            } else {
                mmp.put("dept", 11);
            }
        }
        return prefixs + "/off";
    }
}
