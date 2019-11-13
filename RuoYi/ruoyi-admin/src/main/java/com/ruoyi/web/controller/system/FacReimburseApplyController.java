package com.ruoyi.web.controller.system;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.finance.*;
import com.ruoyi.system.service.finance.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.finance.FacReiAdiApplyMapper;
import com.ruoyi.system.mapper.finance.FacReimburseApplyMapper;
import com.ruoyi.system.mapper.finance.FacTrafficReiApplyMapper;
import com.ruoyi.system.mapper.finance.FacUserApprovalMapper;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import scala.collection.generic.Shrinkable;

/**
 * 报销 信息操作处理
 *
 * @author ruoyi
 * @date 2019-07-31
 */
@Controller
@RequestMapping("/system/facReimburseApply")
public class FacReimburseApplyController extends BaseController {
    private String prefix = "system/facReimburseApply";

    @Autowired
    private IFacReimburseApplyService facReimburseApplyService;
    @Autowired
    private FacUserApprovalMapper facUserApprovalMapper;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private IFacLoanRepayApplyService facLoanRepayApplyService;
    @Autowired
    private IFacUserApprovalService facUserApprovalService;

    @Autowired
    private FacReiAdiApplyMapper facReiAdiApplyMapper;
    @Autowired
    private FacTrafficReiApplyMapper facTrafficReiApplyMapper;
    @Autowired
    private IFacNumberTableService facNumberTableService;

    @Autowired
    private FacReimburseApplyMapper facReimburseApplyMapper;
    @Autowired
    private IFacHospitalityApplyService facHospitalityApplyService;
    @Autowired
    private ApprovalProcessService approvalProcessService;
    @Autowired
    private IFacCostReimburseService facCostReimburseService;
    @Autowired
    private IFacCostApplyService facCostApplyService;
    @Autowired
    private IFacZhaoDaiLimitService facZhaoDaiLimitService;

    @GetMapping()
    public String facReimburseApply() {
        return prefix + "/facReimburseApply";
    }

    //差旅和团建的报销申请入口
    @PostMapping("/applyReimburse")
    @ResponseBody
    public AjaxResult applyReimburse(String num, String type) {

        FacReimburseApply facReimburseApply = new FacReimburseApply();
        //center 是直接审批成功的
        FacSysUserApproval center = new FacSysUserApproval();//审批流
        center.setApplicantId(ShiroUtils.getUserId());
        center.setApplyId(facNumberTableService.getNum("BX", ShiroUtils.getDateId()));
        center.setCreateTime(new Date());
        center.setApprovalState("1");
        center.setApprovalTime(new Date());
        center.setApprovalLevel(1);
        center.setApprovalSight("1");
//        approvalProcessService.insert(center);
        if (type == null) {

            return AjaxResult.error("参数错误");

        } else if (type.equals("chailv")) {

            FacCostReimburse facCostReimburse = facCostReimburseService.selectFacCostReimburseByNum(num);
            //构建报销信息
            if (facCostReimburse != null) {
                facReimburseApply.setJKnum(num);
                facReimburseApply.setReimburseTime(DateUtils.getNowDate());
                facReimburseApply.setAmount(facCostReimburse.getMoneyEs());
                facReimburseApply.setNum(center.getApplyId());
                facReimburseApply.setLoanUser(ShiroUtils.getUserId());
                facReimburseApply.setCreateBy(ShiroUtils.getUserId().toString());
                facReimburseApply.setCreateTime(DateUtils.getNowDate());
                facReimburseApply.setType("差旅报销");
                facReimburseApply.setSubmitStatus("submit");
                facReimburseApply.setName(facCostReimburse.getBusName());
                //如果满足条件则直接通过，否则走报销审批流
                if (validateChaiLvRule(facCostReimburse)) {
                    facReimburseApplyMapper.insertFacReimburseApply(facReimburseApply);
                    approvalProcessService.insert(center);
                } else {
                    facReimburseApplyService.insertFacReimburseApply(facReimburseApply);
                }
                FacCostApply facCostApply = new FacCostApply();
                facCostApply.setNum(facCostReimburse.getNum());
                List<FacCostApply> facCostApplies = facCostApplyService.selectFacCostApplyList(facCostApply);
                facCostApply.setId(facCostApplies.get(0).getId());
                facCostApply.setStatus("1");
                facCostApplyService.updateFacCostApply(facCostApply);
            }

        } else if (type.equals("tuanjian")) {


        } else {

            return AjaxResult.error("参数错误");

        }

        return AjaxResult.success("操作成功");

    }

    //判断差旅报销是否直接通过
    private boolean validateChaiLvRule(FacCostReimburse facCostReimburse) {

        FacCostApply facCostApply = new FacCostApply();
        facCostApply.setNum(facCostReimburse.getNum());
        List<FacCostApply> facCostApplies = facCostApplyService.selectFacCostApplyList(facCostApply);
        Double moneyEs = facCostApplies.get(0).getMoneyEs();
        if (moneyEs >= facCostReimburse.getMoneyEs()) {

            return true;

        } else {

            return false;

        }

    }

    /**
     * 查询报销列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FacReimburseApply facReimburseApply, String loanUserName, ModelMap map) {
        // 查出这个人的信息
        if (loanUserName != null && !loanUserName.equals("")) {
            Long loanUserId = sysUserService.selectUserIdByUserNameOnly(loanUserName);
            if (loanUserId == null) {
                return getDataTable(new ArrayList<>());
            }
            facReimburseApply.setLoanUser(loanUserId);
        }

        SysUser user = ShiroUtils.getSysUser();
        SysDept sysDept = sysDeptService.selectDeptById(user.getDeptId());
        List<SysRole> sysRoles = sysRoleService.selectRolesByUserId(user.getUserId());
        for (SysRole sysRole : sysRoles) {

            if (sysRole.isFlag() && sysRole.getRoleId() == 13) {
                // 若是财务
                startPage();
                List<FacReimburseApply> list = facReimburseApplyService.selectFacReimburseApplyList(facReimburseApply);
                FacUserApproval facUserApproval = null;
                for (FacReimburseApply facReimburseApply1 : list) {
                    facUserApproval = new FacUserApproval();
                    facUserApproval.setApprovalSight("1");
                    facUserApproval.setApplyId(facReimburseApply1.getNum());
                    facUserApproval.setApprovalState("3");
                    List<FacUserApproval> facUserApprovals = facUserApprovalMapper.selectFacUserApprovalList(facUserApproval);
                    if (facUserApprovals.size() > 0) {
                        SysUser sysUser = sysUserService.selectUserById(facUserApprovals.get(0).getApproverId());
                        if (sysUser != null) {
                            facReimburseApply1.setApproveName(sysUser.getUserName());
                        }
                    }
                    FacUserApproval name = facUserApprovalService.selectApproval(facReimburseApply1.getNum(), facReimburseApply1.getLoanUser());
                    if (name != null) {
                        if (name.getApproverId() != null) {
                            facReimburseApply1.setApprover(sysUserService.selectUserById(name.getApproverId()).getUserName());
                        }
                        facReimburseApply1.setApprovalStatus(name.getApprovalState());
                        if (ShiroUtils.getUserId() == 103 && ShiroUtils.getUserId() == 101) {
                            facReimburseApply1.setApprovalStatus("1");
                        }
                        if (name.getStates() != null) {
                            facReimburseApply1.setApplyStatus(name.getStates());
                        }
                    } else {
                        facReimburseApply1.setApprover("--");
                        facReimburseApply1.setApprovalStatus("--");
                    }
                }
                return getDataTable(list);
            }
        }
        // 查询自己的
        facReimburseApply.setCreateBy(ShiroUtils.getUserId().toString());
        startPage();
        List<FacReimburseApply> list = facReimburseApplyService.selectFacReimburseApplyList(facReimburseApply);
        FacUserApproval facUserApproval = null;
        for (FacReimburseApply facReimburseApply1 : list) {
//            if (facReimburseApply1.getType() != null) {
//                if (facReimburseApply1.getType().equals("日常报销")) {
//
//                } else {
//                    facReimburseApply1.setStatus("1");
//                }
//            }

            facUserApproval = new FacUserApproval();
            facUserApproval.setApprovalSight("1");
            facUserApproval.setApplyId(facReimburseApply1.getNum());
            facUserApproval.setApprovalState("3");
            List<FacUserApproval> facUserApprovals = facUserApprovalMapper.selectFacUserApprovalList(facUserApproval);
            if (facUserApprovals.size() > 0) {

                if (facUserApprovals.get(0).getApproverId() != null) {
                    SysUser sysUser = sysUserService.selectUserById(facUserApprovals.get(0).getApproverId());
                    facReimburseApply1.setApproveName(sysUser.getUserName());
                }
            }

            FacUserApproval name = facUserApprovalService.selectApproval(facReimburseApply1.getNum(), facReimburseApply1.getLoanUser());
            if (name != null) {

                if (name.getApproverId() != null) {
                    facReimburseApply1.setApprover(sysUserService.selectUserById(name.getApproverId()).getUserName());
                }
                facReimburseApply1.setApprovalStatus(name.getApprovalState());
                if (ShiroUtils.getUserId() == 103 && ShiroUtils.getUserId() == 101) {
                    facReimburseApply1.setApprovalStatus("1");
                }

                if (name.getStates() != null) {
                    facReimburseApply1.setApplyStatus(name.getStates());
                }
            } else {
                facReimburseApply1.setApprover("--");
                facReimburseApply1.setApprovalStatus("--");
            }

        }
        return getDataTable(list);

    }

    /**
     * 导出报销列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacReimburseApply facReimburseApply) {
        List<FacReimburseApply> list = facReimburseApplyService.selectFacReimburseApplyList(facReimburseApply);
        ExcelUtil<FacReimburseApply> util = new ExcelUtil<FacReimburseApply>(FacReimburseApply.class);
        return util.exportExcel(list, "facReimburseApply");
    }

    /**
     * 查看详情
     */
    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id, ModelMap map) {
        FacReimburseApply facReimburseApply = new FacReimburseApply();
        facReimburseApply.setId(id);
        List<FacReimburseApply> facReimburseApplies = facReimburseApplyService.selectFacReimburseApplyList(facReimburseApply);
        map.put("rid", id);
        map.put("num", facReimburseApplies.get(0).getNum());
        map.put("status", facReimburseApplies.get(0).getStatus());
        map.put("deptName", facReimburseApplies.get(0).getDeptName());
        map.put("name", facReimburseApplies.get(0).getName());
        map.put("userName", facReimburseApplies.get(0).getUserName());
        map.put("date", facReimburseApplies.get(0).getPassTime());
        return prefix + "/detail";
    }

    /**
     * 新增报销
     *
     * @throws Exception
     */
    @GetMapping("/add")
    public String add(ModelMap mmp, Long id, String queryType) throws Exception {
        if (id == null) {
            mmp.put("num", facNumberTableService.getNum("BX", ShiroUtils.getDateId()));
        } else {
            FacReimburseApply facReimburseApply = new FacReimburseApply();
            facReimburseApply.setId(id);
            List<FacReimburseApply> facReimburseApplies = facReimburseApplyService.selectFacReimburseApplyList(facReimburseApply);
            mmp.put("num", facReimburseApplies.get(0).getNum());
            mmp.put("name", facReimburseApplies.get(0).getName());
        }
        mmp.put("msg", "1");
        mmp.put("userName", ShiroUtils.getSysUser().getUserName());
        mmp.put("userId", ShiroUtils.getUserId());
        mmp.put("deptId", ShiroUtils.getDeptId());
        mmp.put("deptName", ShiroUtils.getSysUser().getDept().getDeptName());

        List<Long> longs = facReimburseApplyService.selectRole(ShiroUtils.getUserId());
        for (Long l : longs) {
            if (l == 10 || l == 9) {
                mmp.put("dept", 10);
                break;
            } else {
                mmp.put("dept", 11);
            }
        }
        if (queryType != null && queryType.equals("all")) {
            return prefix + "/addReimbuseDetail";
        } else {
            return prefix + "/reimbuseDetail";
        }
    }

    /**
     * 新增保存报销
     */
    @Log(title = "报销", businessType = BusinessType.INSERT)
    @PostMapping("/addSave")
    @ResponseBody
    @Transactional
    public AjaxResult addSave(FacReimburseApply facReimburseApply) {

        //查询招待费
        ReiHospitalityApply reiHospitalityApply = new ReiHospitalityApply();
        reiHospitalityApply.setNum(facReimburseApply.getNum());
        List<ReiHospitalityApply> list = facReimburseApplyService.selectReiHospitalityApplyList(reiHospitalityApply);
        //根据编号查询是否申请存在
        FacReimburseApply selectReimburseApplyVO = new FacReimburseApply();
        selectReimburseApplyVO.setNum(facReimburseApply.getNum());
        List<FacReimburseApply> facReimburseApplies = facReimburseApplyService.selectFacReimburseApplyList(selectReimburseApplyVO);

        double amount = facReimburseApplyService.selectDouble(facReimburseApply.getNum());
        facReimburseApply.setAmount(amount);
        //若存在则更新，否则新增
        if (facReimburseApplies.size() > 0) {

            facReimburseApply.setId(facReimburseApplies.get(0).getId());
            facReimburseApply.setUpdateTime(DateUtils.getNowDate());
            facReimburseApply.setUpdateBy(ShiroUtils.getLoginName());
            return toAjax(facReimburseApplyService.updateFacReimburseApply(facReimburseApply));

        } else {
            List<ReiHospitalityApply> reiHospitalityApplies = facReimburseApplyService.selectHospitalityApplyListByUser(ShiroUtils.getUserId());
            double hAmount = facReimburseApply.getAmount();
            for (ReiHospitalityApply apply : reiHospitalityApplies) {
                apply.setNum(facReimburseApply.getNum());
                facReimburseApplyService.updateFacReiHospitalityApply(apply);
                hAmount = hAmount + apply.getAmount();
            }
            facReimburseApply.setAmount(hAmount);
            facReimburseApply.setLoanUser(ShiroUtils.getUserId());
            facReimburseApply.setCreateBy(ShiroUtils.getUserId().toString());
            if (facReimburseApply.getId() != null) {
                FacReimburseApply fac = facReimburseApplyService.selectFacReimburseApplyById(facReimburseApply.getId() + "");
                facReimburseApplyService.deleteFacReimburseApplyById(facReimburseApply.getId() + "");
                facReimburseApply.setUpdateTime(new Date());
                facReimburseApply.setCreateTime(fac.getCreateTime());
                String zdnum = facReimburseApply.getJKnum().substring(0, 2);
                if (list != null && zdnum.equals("ZD")) {


                }
            } else {
                facReimburseApply.setCreateTime(new Date());
            }
            facReimburseApplyService.insertSaveFacReimburseApply(facReimburseApply);
            return toAjax(1);
        }

    }

    /**
     * 新增提交报销
     */
    @Log(title = "报销", businessType = BusinessType.INSERT)
    @PostMapping("/addSubmit")
    @ResponseBody
    @Transactional
    public AjaxResult addSubmit(FacReimburseApply facReimburseApply) {
        //查询是否有交通费报销
        FacTrafficReiApply selectTrafficVO = new FacTrafficReiApply();
        selectTrafficVO.setNum(facReimburseApply.getNum());
        List<FacTrafficReiApply> facTrafficReiApplies = facTrafficReiApplyMapper.selectFacTrafficReiApplyList(selectTrafficVO);

        //查询是否有招待费报销
        ReiHospitalityApply reiHospitalityApply = new ReiHospitalityApply();
        reiHospitalityApply.setNum(facReimburseApply.getNum());
        List<ReiHospitalityApply> reiHospitalityApplies = facReimburseApplyService.selectReiHospitalityApplyList(reiHospitalityApply);

        //查询是否有其他费用报销
        List<FacReiAdiApply> facReiAdiApplies = facReimburseApplyService.selectFacReiAdiApply(facReimburseApply.getNum());
        //以上三不能全为空
        if (facTrafficReiApplies.size() == 0 && reiHospitalityApplies.size() == 0 && facReiAdiApplies.size() == 0) {

            List<ReiHospitalityApply> hospitalityApplies = facReimburseApplyService.selectHospitalityApplyListByUser(ShiroUtils.getUserId());
            if (hospitalityApplies.size() == 0) {
                return AjaxResult.error("请添加至少一条报销明细");
            }

        }

        // 删除重新插入
        FacReimburseApply selectVO = new FacReimburseApply();
        selectVO.setNum(facReimburseApply.getNum());
        List<FacReimburseApply> facReimburseApplies = facReimburseApplyService.selectFacReimburseApplyList(selectVO);
        if (facReimburseApplies.size() > 0) {
            selectVO = facReimburseApplies.get(0);
            facReimburseApplyService.deleteFacReimburseApplyById(selectVO.getId().toString());
            facReimburseApply.setCreateBy(selectVO.getCreateBy());
            facReimburseApply.setCreateTime(selectVO.getCreateTime());
            facReimburseApply.setUpdateBy(ShiroUtils.getUserId().toString());
            facReimburseApply.setUpdateTime(DateUtils.getNowDate());
            facReimburseApply.setLoanUser(selectVO.getLoanUser());
        } else {
            //招待费申请生成的报销
            boolean flg = false;
            List<ReiHospitalityApply> hospitalityApplies = facReimburseApplyService.selectHospitalityApplyListByUser(ShiroUtils.getUserId());
            FacHospitalityApply facHospitalityApply = new FacHospitalityApply();
            for (ReiHospitalityApply apply : hospitalityApplies) {
                apply.setNum(facReimburseApply.getNum());
                facReimburseApplyService.updateFacReiHospitalityApply(apply);
                facHospitalityApply.setNum(apply.getApplyNum());
                List<FacHospitalityApply> facHospitalityApplies = facHospitalityApplyService.selectFacHospitalityApplyList(facHospitalityApply);
                Double amount = facHospitalityApplies.get(0).getAmount();
                //招待申请金额若小于招待报销的金额，则需要走报销审批
                if (amount >= apply.getAmount() && flg == false) {
                    flg = false;
                } else {
                    flg = true;
                }
            }
            facReimburseApply.setCreateTime(new Date());
            facReimburseApply.setCreateBy(ShiroUtils.getUserId().toString());
            //当存在其他类型的报销时一定要走审批
            if (!flg) {
                if (facTrafficReiApplies.size() == 0 && facReiAdiApplies.size() == 0 && reiHospitalityApplies.size() == 0) {
                    //此时不走审批
                    facReimburseApply.setLoanUser(ShiroUtils.getUserId());
                    facReimburseApply.setSubmitStatus("submit");
                    facReimburseApply.setApplyStatus("1");
                    facReimburseApply.setAmount(facReimburseApplyService.selectDouble(facReimburseApply.getNum()));
                    facReimburseApply.setReimburseTime(DateUtils.getNowDate());
                    facReimburseApply.setType("日常报销");
                    facReimburseApplyMapper.insertFacReimburseApply(facReimburseApply);
                    FacSysUserApproval center = new FacSysUserApproval();//审批流
                    center.setApplicantId(facReimburseApply.getLoanUser());
                    center.setApplyId(facReimburseApply.getNum());
                    center.setCreateTime(new Date());
                    center.setApprovalState("1");
                    center.setApprovalTime(new Date());
                    center.setApprovalLevel(1);
                    center.setApprovalSight("1");
                    approvalProcessService.insert(center);
                    return AjaxResult.success("操作成功");
                }
            }
        }
        facReimburseApply.setLoanUser(ShiroUtils.getUserId());
        facReimburseApply.setSubmitStatus("submit");
        facReimburseApply.setType("日常报销");
        return facReimburseApplyService.insertFacReimburseApply(facReimburseApply);
    }

    /**
     * 新增提交报销
     */
    @Log(title = "报销", businessType = BusinessType.INSERT)
    @PostMapping("/addSubmits")
    @ResponseBody
    @Transactional
    public AjaxResult addSubmits(FacReimburseApply facReimburseApply) {
        if (facReimburseApply.getId() == null) {
            // 直接添加
            facReimburseApply.setLoanUser(ShiroUtils.getUserId());
            facReimburseApply.setCreateTime(new Date());
            facReimburseApply.setCreateBy(ShiroUtils.getUserId().toString());
        } else {
            // 更新
            facReimburseApply = facReimburseApplyService.selectFacReimburseApplyById(facReimburseApply.getId() + "");
            facReimburseApply.setLoanUser(ShiroUtils.getUserId());
            facReimburseApply.setUpdateTime(new Date());
            facReimburseApplyService.deleteFacReimburseApplyById(facReimburseApply.getId() + "");
            facReimburseApply.setId(null);
            facReimburseApply.setCreateBy(ShiroUtils.getUserId().toString());
        }
        facReimburseApply.setSubmitStatus("submit");

        return facReimburseApplyService.insertFacReimburseApply(facReimburseApply);
    }

    /**
     * 新增提交报销
     */
    @Log(title = "报销", businessType = BusinessType.INSERT)
    @PostMapping("/addSubmit2")
    @ResponseBody
    @Transactional
    public AjaxResult addSubmit2(FacReimburseApply facReimburseApply) {
        if (facReimburseApply.getId() == null) {
            // 直接添加
            facReimburseApply.setLoanUser(ShiroUtils.getUserId());
            facReimburseApply.setCreateTime(new Date());
            facReimburseApply.setCreateBy(ShiroUtils.getUserId().toString());
        } else {
            // 更新
            facReimburseApply = facReimburseApplyService.selectFacReimburseApplyById(facReimburseApply.getId() + "");
            facReimburseApply.setLoanUser(ShiroUtils.getUserId());
            facReimburseApply.setUpdateTime(new Date());
            facReimburseApplyService.deleteFacReimburseApplyById(facReimburseApply.getId() + "");
            facReimburseApply.setId(null);
            facReimburseApply.setCreateBy(ShiroUtils.getUserId().toString());
        }
        facReimburseApply.setSubmitStatus("submit");
        FacLoanRepayApply facLoanRepayApply = new FacLoanRepayApply();
        facLoanRepayApply.setNum(facReimburseApply.getJKnum());
        facLoanRepayApply.setPayer(ShiroUtils.getUserId());
        facLoanRepayApply.setRepayAmount(facReimburseApplyService.selectDouble(facReimburseApply.getNum()));
        facLoanRepayApply.setRepayMethod("冲抵");
        facLoanRepayApplyService.insertFacLoanRepayApply(facLoanRepayApply);
        return facReimburseApplyService.insertFacReimburseApply(facReimburseApply);
    }

    /**
     * 查询招待费报销列表
     */
    @Log(title = "招待费报销", businessType = BusinessType.INSERT)
    @PostMapping("/addAll")
    @ResponseBody
    public TableDataInfo addAllSave(FacReimburseApply facReimburseApply, String queryType) {
        facReimburseApply.setLoanUser(ShiroUtils.getUserId());
        ReiHospitalityApply reiHospitalityApply = new ReiHospitalityApply();
        reiHospitalityApply.setNum(facReimburseApply.getNum());
        List<ReiHospitalityApply> list = null;
        if (queryType != null && queryType.equals("all")) {
            list = facReimburseApplyService.selectHospitalityApplyListByUser(ShiroUtils.getUserId());
            List<ReiHospitalityApply> reiHospitalityApplies = facReimburseApplyService.selectReiHospitalityApplyList(reiHospitalityApply);
            list.addAll(reiHospitalityApplies);
        } else {
            list = facReimburseApplyService.selectReiHospitalityApplyList(reiHospitalityApply);
        }
        for (ReiHospitalityApply v : list) {

            SysUser applicant = sysUserService.selectUserById(v.getUser());
            if (applicant != null) {
                v.setUserName(sysUserService.selectUserById(v.getUser()).getUserName());
            }
        }
        return getDataTable(list);
    }

    /**
     * 招待费用报销
     */
    @GetMapping("/addAll")
    public String addAll(@RequestParam String num, ModelMap map) {
        map.put("num", num);
        return prefix + "/addAll";
    }

    /**
     * 新增其他报销
     */
    @Log(title = "招待费报销", businessType = BusinessType.INSERT)
    @PostMapping("/addAllSave")
    @ResponseBody
    public AjaxResult addAllSave2(ReiHospitalityApply reiHospitalityApply) {

        reiHospitalityApply.setUser(ShiroUtils.getUserId());
        facReimburseApplyService.insertFacreiHospitalityApply(reiHospitalityApply);
        //查询当前用户的额度
        Double limitAmount = 0.00;
        List<Long> roleIds = facReimburseApplyService.selectRole(ShiroUtils.getUserId());
        for (Long roleId : roleIds) {
            FacZhaoDaiLimit facZhaoDaiLimit = facZhaoDaiLimitService.selectFacZhaoDaiLimitByRoleId(roleId);
            if (facZhaoDaiLimit == null) {
                continue;
            } else if (facZhaoDaiLimit.getLimitAmount().doubleValue() > limitAmount) {
                limitAmount = facZhaoDaiLimit.getLimitAmount().doubleValue();
            }
        }
        if (limitAmount == 0.00) {
            return AjaxResult.success("操作成功");
        } else {
            //计算报销人当月已审批和审批中的招待费报销金额
            Double amount = 0.00;
            FacReimburseApply facReimburseApply = new FacReimburseApply();
            facReimburseApply.setLoanUser(ShiroUtils.getUserId());
            List<FacReimburseApply> facReimburseApplies = facReimburseApplyService.selectCurrentMonthFacReimburseApplyList(facReimburseApply);
            for (FacReimburseApply reimburseApply : facReimburseApplies) {

                if (reimburseApply.getType().equals("日常报销")) {
                    //计算 待审批的 审批中的 审批成功的
                    if (reimburseApply.getStatus() == null || reimburseApply.getStatus().equals("1") || reimburseApply.getStatus().equals("3")) {
                        ReiHospitalityApply hospitalityApply = new ReiHospitalityApply();
                        hospitalityApply.setNum(reimburseApply.getNum());
                        List<ReiHospitalityApply> reiHospitalityApplies = facReimburseApplyService.selectReiHospitalityApplyList(hospitalityApply);
                        for (ReiHospitalityApply apply : reiHospitalityApplies) {
                            amount = amount + apply.getAmount();
                        }
                    }

                }

            }
            ReiHospitalityApply hospitalityApply = new ReiHospitalityApply();
            hospitalityApply.setNum(reiHospitalityApply.getNum());
            List<ReiHospitalityApply> reiHospitalityApplies = facReimburseApplyService.selectReiHospitalityApplyList(hospitalityApply);
            for (ReiHospitalityApply apply : reiHospitalityApplies) {
                amount = amount + apply.getAmount();
            }

            if (limitAmount < amount) {
                return AjaxResult.success("已超出额度");
            }else{
                return AjaxResult.success("操作成功");
            }

        }

    }

    /**
     * 查询报销列表
     */
    @PostMapping("/selecthost")
    @ResponseBody
    public TableDataInfo selecthost(ReiHospitalityApply reiHospitalityApply) {
        startPage();
        reiHospitalityApply.setUser(ShiroUtils.getUserId());
        List<ReiHospitalityApply> list = facReimburseApplyService.selectReiHospitalityApplyList(reiHospitalityApply);
        return getDataTable(list);
    }

    /**
     * 其他报销申请
     */
    @GetMapping("/otherDetail")
    public String otherDetails(@RequestParam String num, ModelMap map) {
        map.put("num", num);
        return prefix + "/otherDetail";

    }

    /**
     * 新增其他报销
     */
    @Log(title = "招待费报销", businessType = BusinessType.INSERT)
    @PostMapping("/Arbitrarily")
    @ResponseBody
    public AjaxResult Save(FacReiAdiApply reiAdiApply) {
        return toAjax(facReimburseApplyService.insertFacreiAdiApply(reiAdiApply));
    }

    /**
     * 新增交通费用
     */
    @Log(title = "交通费报销", businessType = BusinessType.INSERT)
    @PostMapping("/tranDetail")
    @ResponseBody
    public AjaxResult tranDetailSave(ReiTrafficApply reiTrafficApply) {
        reiTrafficApply.setApplyUser(ShiroUtils.getUserId());
        List<ReiTrafficApply> list = new ArrayList<>();
        list.add(reiTrafficApply);
        FacReimburseApply facReimburseApply = new FacReimburseApply();
        facReimburseApply.setLoanUser(ShiroUtils.getUserId());
        facReimburseApply.setTrafficReiApplyList(list);
        return toAjax(facReimburseApplyService.insertReiTrafficApply(reiTrafficApply));
    }

    /**
     * 交通费用报销
     */
    @GetMapping("/tranDetail")
    public String tranDetail(@RequestParam String num, ModelMap map) {
        map.put("num", num);
        return prefix + "/tranDetail";
    }

    /**
     * 查看报销详情
     */
    @PostMapping("/query")
    @ResponseBody
    public TableDataInfo detail1(String num) {
        startPage();
        FacReimburseApply facReimburseApply = facReimburseApplyService.deatil(num);
        if (facReimburseApply != null) {
            List<FacReimburseApply> facReimburseApplies = new ArrayList<>();
            facReimburseApplies.add(facReimburseApply);
            return getDataTable(facReimburseApplies);
        } else {
            List<String> a = new ArrayList<>();
            return getDataTable(a);
        }
    }

    /**
     * 查看招待费报销申请详情
     */
    @PostMapping("/hospDetail")
    @ResponseBody
    public TableDataInfo hospDtail(String num) {
        startPage();
        FacReimburseApply facReimburseApply = facReimburseApplyService.deatil(num);
        if (facReimburseApply != null) {
            List<ReiHospitalityApply> reiAdiApplies = facReimburseApply.getHospitalityApplies();

            if (reiAdiApplies != null && reiAdiApplies.size() > 0) {
                return getDataTable(reiAdiApplies);
            } else {
                List<String> a = new ArrayList<>();
                return getDataTable(a);
            }
        } else {
            List<String> a = new ArrayList<>();
            return getDataTable(a);
        }
    }

    /**
     * 查看交通费报销申请详情
     */
    @PostMapping("/addTraDetail")
    @ResponseBody
    public TableDataInfo addTraDetail(String num) {
        startPage();
        List<ReiTrafficApply> facReimburseApply = facReimburseApplyService.selectReiTrafficApply(num);

        if (facReimburseApply != null) {
            for (ReiTrafficApply list : facReimburseApply) {

                // applyUser 通过userID获取角色

                double amount = facTrafficReiApplyMapper.selectAmount(list.getApplyUser());

                if (amount > 800) {
                    list.setExcess("1");
                } else {
                    list.setExcess("0");
                }
            }
            return getDataTable(facReimburseApply);

        } else {
            List<String> a = new ArrayList<>();
            return getDataTable(a);
        }
    }

    /**
     * 查看交通费报销申请详情
     */
    @PostMapping("/lookTraDetail")
    @ResponseBody
    public TableDataInfo LookTraDetail(String num) {
        startPage();
        List<ReiTrafficApply> facReimburseApply = facReimburseApplyService.selectReiTrafficApply(num);
        Long user = ShiroUtils.getUserId();
        if (facReimburseApply != null) {
            for (int i = 0; i < facReimburseApply.size(); i++) {
                if (user == 253 || user == 257) {
                    if (facReimburseApply.get(i).getType().equals("公出")) {
                        facReimburseApply.remove(i);
                    }
                }
                double amount = facTrafficReiApplyMapper.selectAmount(facReimburseApply.get(i).getApplyUser());
                if (amount > 800) {
                    facReimburseApply.get(i).setExcess("1");
                } else {
                    facReimburseApply.get(i).setExcess("0");
                }
            }
            return getDataTable(facReimburseApply);
        } else {
            List<String> a = new ArrayList<>();
            return getDataTable(a);
        }
    }

    /**
     * 查看其他报销费申请详情
     */

    @PostMapping("/otherDetail")
    @ResponseBody
    public TableDataInfo otherDetail(@RequestParam String num) {
        startPage();

        List<FacReiAdiApply> facReimburseApply = facReimburseApplyService.selectFacReiAdiApply(num);

        if (facReimburseApply != null) {
            return getDataTable(facReimburseApply);
        } else {
            List<String> a = new ArrayList<>();
            return getDataTable(a);
        }
    }

    @GetMapping("/addSave")
    public String addSave(String id, ModelMap map) {
        map.put("id", id);
        FacReimburseApply facReimburseApply = facReimburseApplyService.selectFacReimburseApplyById(id);
        map.put("amount", facReimburseApplyService.selectDouble(facReimburseApply.getNum()));
        return prefix + "/addSave";
    }

    /**
     * 修改保存报销
     */
    @Log(title = "报销", businessType = BusinessType.OTHER)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FacReimburseApply facReimburseApply) {

        facReimburseApply.setAmount(facReimburseApplyService.selectDouble(facReimburseApply.getNum()));

        return toAjax(facReimburseApplyService.updateFacReimburseApply(facReimburseApply));
    }

    /**
     * 修改报销
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, String edit, ModelMap mmap) {
        FacReimburseApply facReimburseApply = facReimburseApplyService.selectFacReimburseApplyById(id);
        //用于判断前端页面是否可以修改
        if (edit == null) {
            edit = "";
        }
        mmap.put("edit", edit);

        mmap.put("facReimburseApply", facReimburseApply);
        mmap.put("name", facReimburseApply.getName());
        mmap.put("num", facReimburseApply.getNum());
        mmap.put("msg", "1");
        mmap.put("userName", ShiroUtils.getSysUser().getUserName());
        mmap.put("userId", ShiroUtils.getUserId());
        mmap.put("deptId", ShiroUtils.getDeptId());
        mmap.put("deptName", ShiroUtils.getSysUser().getDept().getDeptName());
        mmap.put("id", id);
        List<Long> longs = facReimburseApplyService.selectRole(ShiroUtils.getUserId());
        Double limitDouble = 0.00;
        for (Long roleId : longs) {
            FacZhaoDaiLimit facZhaoDaiLimit = facZhaoDaiLimitService.selectFacZhaoDaiLimitByRoleId(roleId);
            if (facZhaoDaiLimit == null) {
                continue;
            }
            if (limitDouble < facZhaoDaiLimit.getLimitAmount().doubleValue()) {
                limitDouble = facZhaoDaiLimit.getLimitAmount().doubleValue();
            }
        }
        mmap.put("limitAmount", limitDouble);
        for (Long l : longs) {
            if (l == 10 || l == 9) {
                mmap.put("dept", 10);
                break;
            } else {
                mmap.put("dept", 11);
            }
        }
        if (facReimburseApply != null) {
            ReiHospitalityApply reiHospitalityApply = new ReiHospitalityApply();
            reiHospitalityApply.setNum(facReimburseApply.getNum());
            List<ReiHospitalityApply> reiHospitalityApplies = facReimburseApplyService.selectReiHospitalityApplyList(reiHospitalityApply);
            if (reiHospitalityApplies.size() > 0) {
                mmap.put("dept", 10);
            }
        }
        return prefix + "/reimbuseDetail.html";
    }

    /**
     * 修改报销
     */
    @GetMapping("/editTran/{id}")
    public String editTran(@PathVariable("id") Long id, ModelMap mmap) {
        ReiTrafficApply facReimburseApply = facReimburseApplyService.selectFacTransById(id);
        mmap.put("data", facReimburseApply);
        return prefix + "/editTran";
    }

    /**
     * 修改报销
     */
    @PostMapping("/editTran")
    @ResponseBody
    public AjaxResult editTranSave(ReiTrafficApply reiTrafficApply) {
        return toAjax(facReimburseApplyService.updateReiTrafficApplyById(reiTrafficApply));
    }

    /**
     * 修改报销
     */
    @GetMapping("/editZhao/{id}")
    public String editZhao(@PathVariable("id") Long id, ModelMap mmap) {
        ReiHospitalityApply ReiHospitalityApply = facReimburseApplyService.selectFacHostById(id);
        mmap.put("facReiHospitalityApply", ReiHospitalityApply);
        return prefix + "/editZhao";
    }

    /**
     * 修改报销
     */
    @PostMapping("/editZhao")
    @ResponseBody
    public AjaxResult editZhaoSave(ReiHospitalityApply reiHospitalityApply) {
        return toAjax(facReimburseApplyService.updateFacReiHospitalityApply(reiHospitalityApply));
    }

    /**
     * 修改报销
     */
    @GetMapping("/editQi/{id}")
    public String editQi(@PathVariable("id") Long id, ModelMap mmap) {
        FacReiAdiApply facReiAdiApply = facReiAdiApplyMapper.selectFacReiAdiApplyById(id);
        mmap.put("facReiAdiApply", facReiAdiApply);
        return prefix + "/editQi";
    }

    /**
     * 修改报销
     */
    @PostMapping("/editQi")
    @ResponseBody
    public AjaxResult editQiSave(FacReiAdiApply facReiAdiApply) {
        return toAjax(facReiAdiApplyMapper.updateFacReiAdiApply(facReiAdiApply));
    }

    /**
     * 删除报销
     */
    @PostMapping("/removeTran")
    @ResponseBody
    public AjaxResult removeTran(String ids) {
        return toAjax(facReimburseApplyService.deleteReiTrafficApplyById(ids));
    }

    /**
     * 删除报销
     */
    @PostMapping("/removeZhao")
    @ResponseBody
    public AjaxResult removeZhao(String id) {
        return toAjax(facReimburseApplyService.deleteZhaodaiById(id));
    }

    /**
     * 删除报销
     */
    @PostMapping("/removeQi")
    @ResponseBody
    public AjaxResult removeQi(String id) {
        return toAjax(facReimburseApplyService.deleteFacReiAdiApplyByIds(Long.valueOf(id)));
    }

    /**
     * 删除报销
     */
    @Log(title = "报销", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(facReimburseApplyService.deleteFacReimburseApplyByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 保存团建申请报销
     */
    @Log(title = "团建申请报销", businessType = BusinessType.UPDATE)
    @PostMapping("/baoxiao")
    @ResponseBody
    public AjaxResult editSave1(FacReimburseApply facReimburseApply) {
        facReimburseApply.setLoanUser(ShiroUtils.getUserId());
        facReimburseApply.setCreateBy(ShiroUtils.getUserId().toString());
        return toAjax(facReimburseApplyService.insertApply(facReimburseApply));
    }
}