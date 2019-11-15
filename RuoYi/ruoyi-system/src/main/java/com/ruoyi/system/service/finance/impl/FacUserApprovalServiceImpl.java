package com.ruoyi.system.service.finance.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.finance.FacSysUserApproval;
import com.ruoyi.system.domain.finance.ReiTrafficApply;
import com.ruoyi.system.mapper.finance.ApprovalProcessMapper;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.finance.ApprovalProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.finance.FacReimburseApply;
import com.ruoyi.system.domain.finance.FacUserApproval;
import com.ruoyi.system.mapper.finance.FacUserApprovalMapper;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.IFacReimburseApplyService;
import com.ruoyi.system.service.finance.IFacUserApprovalService;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 财务审批 服务层实现
 *
 * @author ruoyi
 * @date 2019-09-27
 */
@Service
public class FacUserApprovalServiceImpl implements IFacUserApprovalService {
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
    ISysUserService iSysUserService;
    @Autowired
    ISysDeptService sysDeptService;

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
        FacUserApproval facUserApproval = new FacUserApproval();
        facUserApproval.setApplyId(num);
        facUserApproval.setApplicantId(userId);
        facUserApproval.setApprovalSight("1");
        FacUserApproval facUserApproval2 = new FacUserApproval();
        facUserApproval2.setApplyId(num);
        facUserApproval2.setApplicantId(userId);
        facUserApproval2.setApprovalSight("1");
        facUserApproval2.setApprovalState("3");
        FacUserApproval facUserApproval3;

        List<FacUserApproval> list = facUserApprovalMapper
                .selectFacUserApprovalList(facUserApproval2);

        if (list != null && list.size() > 0) {
            facUserApproval3 = list.get(0);
        } else {
            facUserApproval3 = null;
        }
        if (facUserApproval3 != null) {
            return facUserApproval3;
        } else {

            FacUserApproval FacUserApproval5 = facUserApprovalMapper.selectApprovaIdlList(facUserApproval);

            return FacUserApproval5;
        }
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
}
