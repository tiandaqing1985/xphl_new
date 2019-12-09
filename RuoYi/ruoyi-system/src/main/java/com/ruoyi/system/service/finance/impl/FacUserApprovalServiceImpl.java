package com.ruoyi.system.service.finance.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.finance.FacAmountApply;
import com.ruoyi.system.domain.finance.FacSysUserApproval;
import com.ruoyi.system.domain.finance.FacUserApproval;
import com.ruoyi.system.mapper.finance.ApprovalProcessMapper;
import com.ruoyi.system.mapper.finance.FacReiAdiApplyMapper;
import com.ruoyi.system.mapper.finance.FacReimburseApplyMapper;
import com.ruoyi.system.mapper.finance.FacUserApprovalMapper;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.ApprovalProcessService;
import com.ruoyi.system.service.finance.IFacReimburseApplyService;
import com.ruoyi.system.service.finance.IFacUserApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 财务审批 服务层实现
 *
 * @author ruoyi
 * @date 2019-09-27
 */
@Service
public class FacUserApprovalServiceImpl implements IFacUserApprovalService {
    @Autowired
    ISysUserService iSysUserService;
    @Autowired
    ISysDeptService sysDeptService;
    @Autowired
    private FacUserApprovalMapper facUserApprovalMapper;
    @Autowired
    private IFacReimburseApplyService facReimburseApplyService;
    @Autowired
    private ApprovalProcessMapper approvalProcessMapper;
    @Autowired
    private ApprovalProcessService approvalProcessService;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private FacReiAdiApplyMapper facReiAdiApplyMapper;
    @Autowired
    private FacReimburseApplyMapper facReimburseApplyMapper;

    /**
     * 查询财务审批信息
     *
     * @param approvalId 财务审批ID
     * @return 财务审批信息
     */
    @Override
    public FacUserApproval selectFacUserApprovalById(Long approvalId) {
        return facUserApprovalMapper.selectFacUserApprovalById(approvalId);
    }

    /**
     * 查询财务审批列表
     *
     * @param facUserApproval 财务审批信息
     * @return 财务审批集合
     */
    @Override
    public List<FacUserApproval> selectFacUserApprovalList(
            FacUserApproval facUserApproval) {
        return facUserApprovalMapper.selectFacUserApprovalList(facUserApproval);
    }

    /**
     * 新增财务审批
     *
     * @param facUserApproval 财务审批信息
     * @return 结果
     */
    @Override
    public int insertFacUserApproval(FacUserApproval facUserApproval) {
        return facUserApprovalMapper.insertFacUserApproval(facUserApproval);
    }

    /**
     * 修改财务审批
     *
     * @param facUserApproval 财务审批信息
     * @return 结果 0表示当前申请所有审批人已经同意 1表示当前申请当前审批人已经同意 2表示当前申请当前审批人已经驳回
     */
    @Override
    public int updateFacUserApproval(FacUserApproval facUserApproval) {

        // 更新当前审批步骤中的状态
        facUserApproval.setApprovalTime(new Date());
        facUserApprovalMapper.updateFacUserApproval(facUserApproval);
        // 若是审批通过
        if (facUserApproval.getApprovalState().equals("1")) {
            // 查询下一个审批步骤
            FacUserApproval nextFac = new FacUserApproval();
            nextFac.setApplyId(facUserApproval.getApplyId());
            nextFac.setApprovalLevel(facUserApproval.getApprovalLevel() + 1);
            List<FacUserApproval> approvals = facUserApprovalMapper.selectFacUserApprovalList(nextFac);
            // 若无下一步则审批成功，否则审批中
            if (approvals.size() != 0) {

                FacUserApproval next = approvals.get(0);
                next.setApprovalSight("1");
                facUserApprovalMapper.updateFacUserApproval(next);
            } else {
                //当前申请所有审批人已经同意
                return 0;
            }
        } else {
            //当前申请当前审批人已经驳回
            return 2;
        }
        return 1;
    }

    /**
     * 删除财务审批对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFacUserApprovalByIds(String ids) {
        return facUserApprovalMapper
                .deleteFacUserApprovalByIds(Convert.toStrArray(ids));
    }

    @Override
    public List<FacUserApproval> selectApplicantIdList(
            FacUserApproval facUserApproval) {
        return facUserApprovalMapper.selectApplicantIdList(facUserApproval);
    }

    @Override
    public List<FacUserApproval> selectApproverIdList(
            FacUserApproval facUserApproval) {
        return facUserApprovalMapper.selectApproverIdList(facUserApproval);
    }

    @Override
    public List<FacUserApproval> selectEndFacUserApprovalList(
            FacUserApproval facUserApproval) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public FacUserApproval selectApproval(String num, Long userId) {
        FacUserApproval selectVo = new FacUserApproval();
        selectVo.setApplyId(num);
        List<FacUserApproval> facUserApprovals = facUserApprovalMapper.selectFacUserApprovalList(selectVo);
        FacUserApproval last = null;
        for (FacUserApproval facUserApproval : facUserApprovals) {
            if (facUserApproval.getApprovalSight().equals("1")) {
                if (last == null) {
                    last = facUserApproval;
                } else {
                    if (facUserApproval.getApprovalLevel() > last.getApprovalLevel()) {
                        last = facUserApproval;
                    }
                }
            }
        }
        return last;
    }

    /**
     * 创建报销的审批流
     *
     * @param num
     * @param userId
     */
    @Override
    public void createPublicPayApprovalProcess(String num, Double amount, String processName, Long userId) {


        try {
            int level = 1;
            // 获取总的报销申请金额
            FacSysUserApproval center = new FacSysUserApproval();// 中心负责人
            center.setApplicantId(userId);
            center.setApplyId(num);
            center.setCreateTime(new Date());
            center.setApprovalState("3");
            center.setApprovalTime(new Date());
            center.setAmount(amount);

            Long leaderId = iSysUserService.selectApproverIdByApplyerId(userId);
            Long upLeaderId = iSysUserService.selectUpApproverIdByApplyerId(userId);
            // 报销审批流开始
            String roleName = "";
            List<String> queryRoleName = approvalProcessMapper.queryRoleName(userId);
            for (String name : queryRoleName) {
                roleName = roleName + "," + name.trim().toLowerCase();
            }
            if (roleName.contains("ceo")) {
                // 是ceo 直接通过

                // 如果为coo申请 交给ceo审批
            } else if (roleName.contains("coo")) {
                FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
                facSysUserApproval.setApprovalId(new Long("101"));
                facSysUserApproval.setApprovalTime(new Date());
                facSysUserApproval.setApprovalLevel(level++);
                facSysUserApproval.setApplyId(num);
                facSysUserApproval.setProjectName(processName);
                facSysUserApproval.setApprovalState("3");
                approvalProcessService.insert(facSysUserApproval);
            }
            // 普通员工
            else {
                if (!roleName.contains("部门leader")) {
                    center.setApproverId(leaderId);
                    center.setApprovalLevel(level++);
                    center.setProjectName(processName);
                    approvalProcessService.insert(center);
                }
                // 获取该申请人下的所有领导
                LinkedList<Long> centerId = (LinkedList<Long>) iSysUserService.selectCenterIdByUserId(userId);

                if (centerId != null && centerId.size() > 0) {
                    for (int i = centerId.size() - 1; i >= 0; i--) {
                        boolean flg = false;
                        if (centerId.get(i).equals(userId)) {
                            centerId.remove(centerId.get(i));
                        }
                        if (centerId.get(i) == leaderId) {
                            if (roleName.contains("部门leader")) {
                                center.setApproverId(centerId.get(i));
                                center.setApprovalLevel(level++);
                                center.setProjectName(processName);
                                approvalProcessService.insert(center);
                            }
                        } else {
                            center.setApproverId(centerId.get(i));
                            center.setApprovalLevel(level++);
                            center.setProjectName(processName);
                            approvalProcessService.insert(center);
                        }
                        List<SysRole> sysRoles = sysRoleService.selectRolesByUserId(centerId.get(i));
                        boolean isCaywu = false;
                        boolean isRenshi = false;
                        boolean isXingzheng = false;
                        for (SysRole role : sysRoles) {
                            if (role.isFlag()) {
                                if (role.getRoleId() == 13) {
                                    // 是否财务
                                    isCaywu = true;
                                } else if (role.getRoleId().equals(6) || role.getRoleId().equals(3)) {
                                    // 是否人事
                                    isRenshi = true;
                                } else if (role.getRoleId().equals(16) || role.getRoleId().equals(17)) {
                                    // 是否行政
                                    isXingzheng = true;
                                }
                            }
                        }

                        if (centerId.get(i).intValue() == 103) { // 如果是审批人是 coo
                            // 直接结束
                            flg = true;
                        } else if (isCaywu || isRenshi) {// 财务审批规则

                            if (amount <= 2000.00) {
                                flg = true;
                            } else if (amount > 2000.00) {
                                center.setApproverId(new Long(194));
                                center.setApprovalLevel(level++);
                                center.setProjectName(processName);
                                approvalProcessService.insert(center);
                                if (amount > 10000.00) {
                                    center.setApproverId(new Long(103));
                                    center.setApprovalLevel(level++);
                                    center.setProjectName(processName);
                                    approvalProcessService.insert(center);
                                }
                                flg = true;
                            }
                        } else if (isXingzheng) {
                            if (amount <= 10000) {
                                center.setApproverId(new Long(194));
                                center.setApprovalLevel(level++);
                                center.setProjectName(processName);
                                approvalProcessService.insert(center);
                                flg = true;
                            } else if (amount > 10000.00) {
                                center.setApproverId(new Long(103));
                                center.setApprovalLevel(level++);
                                center.setProjectName(processName);
                                approvalProcessService.insert(center);
                                flg = true;
                            }
                        } else if (centerId.get(i).intValue() == 194) {// 到韩总处

                            if (amount <= 10000.00) {
                                flg = true;
                            }
                        } else {
                        }
                        if (flg) {
                            return;
                        }
                    }
                }
            }
            return;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("对公支付" + e.getMessage());
            e.printStackTrace();
            return;

        }

    }

    @Override
    public String approverName(String applyId) {
        return facUserApprovalMapper.getAllAppNames(applyId);
    }


    @Override
    public List<FacUserApproval> selectChengGong() {

        return facUserApprovalMapper.selectChenggong();
    }

    @Override
    public List<FacUserApproval> select(Long userId) {
        return facUserApprovalMapper.selectUserId(userId);
    }

    @Override
    public Map<String, FacAmountApply> selectDept(SysUser user) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String intime = null;
        String loginDate = null;
        int intNum = 0;
        int loginDateNum = 0;
        if (user.getIntime() != null) {
            intime = sdf.format(user.getIntime());
            intNum = Integer.valueOf(intime).intValue();
            if (user.getLoginDate() != null) {
                loginDate = sdf.format(user.getLoginDate());
                loginDateNum = Integer.valueOf(loginDate).intValue();
            } else {
                loginDate = sdf.format(new Date());
                loginDateNum = Integer.valueOf(loginDate).intValue();
            }
        }
        List<FacAmountApply> list = new ArrayList<>();
        list.addAll(facUserApprovalMapper.selectcl());
        list.addAll(facUserApprovalMapper.selectdg());
        list.addAll(facUserApprovalMapper.selectJT());
        list.addAll(facUserApprovalMapper.selectqt());
        list.addAll(facUserApprovalMapper.selecttj());
        list.addAll(facUserApprovalMapper.selectZD());
        Map<String, FacAmountApply> map = new HashMap();


        if (intime != null) {
            for (FacAmountApply f : list) {
                int SqtimeNum = Integer.valueOf(sdf.format(f.getSqtime())).intValue();
                if (intNum >= SqtimeNum) continue;
                if (loginDateNum <= SqtimeNum) continue;
                String key = f.getDeptName();
                if (key == null) continue;
                FacAmountApply fac = map.get(key);
                if (fac == null) {
                    fac = new FacAmountApply();
                    fac.setBxJBamount(0.00);
                    fac.setBxGCamount(0.00);
                    fac.setBxQTamount(0.00);
                    fac.setZdAmount(0.00);
                    fac.setDgAmount(0.00);
                    fac.setTjAmount(0.00);
                    fac.setClAmount(0.00);
                    fac.setDeptName(key);
                }

                if (f.getType() != null) {
                    if (f.getType().equals("加班")) {
                        fac.setBxJBamount(fac.getBxJBamount() + f.getAmount());//加班
                    } else if (f.getType().equals("公出")) {
                        fac.setBxGCamount(fac.getBxGCamount() + f.getAmount());//公出
                    }
                }

                if (f.getBxQTamount() != null) {
                    fac.setBxQTamount(fac.getBxQTamount() + f.getBxQTamount());//其他
                }

                if (f.getZdAmount() != null) {
                    fac.setZdAmount(fac.getZdAmount() + f.getZdAmount());//招待
                }

                if (f.getDgAmount() != null) {
                    fac.setDgAmount(fac.getDgAmount() + f.getDgAmount());//对公
                }

                if (f.getTjAmount() != null) {
                    fac.setTjAmount(fac.getTjAmount() + f.getTjAmount());//团建
                }

                if (f.getClAmount() != null) {
                    fac.setClAmount(fac.getClAmount() + f.getClAmount());//差旅
                }
                map.put(key, fac);
            }
        } else {
            for (FacAmountApply f : list) {
                String key = f.getDeptName();
                if (key == null) continue;
                FacAmountApply fac = map.get(key);
                if (fac == null) {
                    fac = new FacAmountApply();
                    fac.setBxJBamount(0.00);
                    fac.setBxGCamount(0.00);
                    fac.setBxQTamount(0.00);
                    fac.setZdAmount(0.00);
                    fac.setDgAmount(0.00);
                    fac.setTjAmount(0.00);
                    fac.setClAmount(0.00);
                    fac.setDeptName(key);
                }

                if (f.getType() != null) {
                    if (f.getType().equals("加班")) {
                        fac.setBxJBamount(fac.getBxJBamount() + f.getAmount());//加班
                    } else if (f.getType().equals("公出")) {
                        fac.setBxGCamount(fac.getBxGCamount() + f.getAmount());//公出
                    }
                }

                if (f.getBxQTamount() != null) {
                    fac.setBxQTamount(fac.getBxQTamount() + f.getBxQTamount());//其他
                }

                if (f.getZdAmount() != null) {
                    fac.setZdAmount(fac.getZdAmount() + f.getZdAmount());//招待
                }

                if (f.getDgAmount() != null) {
                    fac.setDgAmount(fac.getDgAmount() + f.getDgAmount());//对公
                }

                if (f.getTjAmount() != null) {
                    fac.setTjAmount(fac.getTjAmount() + f.getTjAmount());//团建
                }

                if (f.getClAmount() != null) {
                    fac.setClAmount(fac.getClAmount() + f.getClAmount());//差旅
                }
                map.put(key, fac);
            }
        }

        FacAmountApply facAmountApply = new FacAmountApply();
        double a = 0.00;
        double b = 0.00;
        double c = 0.00;
        double d = 0.00;
        double e = 0.00;
        double f = 0.00;
        double g = 0.00;

        for (FacAmountApply s : map.values()) {//遍历map的值
            a = a + s.getBxJBamount();
            b = b + s.getBxGCamount();
            c = c + s.getBxQTamount();
            d = d + s.getClAmount();
            e = e + s.getTjAmount();
            f = f + s.getZdAmount();
            g = g + s.getDgAmount();


        }
        facAmountApply.setDeptName("总计");
        facAmountApply.setBxJBamount(a);
        facAmountApply.setBxGCamount(b);
        facAmountApply.setBxQTamount(c);
        facAmountApply.setClAmount(d);
        facAmountApply.setTjAmount(e);
        facAmountApply.setZdAmount(f);
        facAmountApply.setDgAmount(g);


        map.put("总计", facAmountApply);
        return map;
    }


}
