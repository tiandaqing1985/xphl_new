package com.ruoyi.system.service.finance.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.finance.FacLoanApply;
import com.ruoyi.system.domain.finance.FacSysUserApproval;
import com.ruoyi.system.mapper.finance.ApprovalProcessMapper;
import com.ruoyi.system.mapper.finance.FacLoanApplyMapper;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.IFacLoanApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 借款申请 服务层实现
 *
 * @author ruoyi
 * @date 2019-07-30
 */
@Service
public class FacLoanApplyServiceImpl implements IFacLoanApplyService {

    @Autowired
    ApprovalProcessMapper approvalProcessMapper;
    @Autowired
    private FacLoanApplyMapper facLoanApplyMapper;
    @Autowired
    private ISysUserService iSysUserService;

    /**
     * 查询借款申请信息
     *
     * @param id 借款申请ID
     * @return 借款申请信息
     */
    @Override
    public FacLoanApply selectFacLoanApplyById(String id) {
        return facLoanApplyMapper.selectFacLoanApplyById(id);
    }

    /**
     * 查询借款申请列表
     *
     * @param facLoanApply 借款申请信息
     * @return 借款申请集合
     */
    @Override
    public List<FacLoanApply> selectFacLoanApplyList(
            FacLoanApply facLoanApply) {
        return facLoanApplyMapper.selectFacLoanApplyList(facLoanApply);
    }

    /**
     * 新增借款申请
     *
     * @param facLoanApply 借款申请信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertFacLoanApply(FacLoanApply facLoanApply) {

        facLoanApply.setApplyStatus("3");
        FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
        facSysUserApproval.setApprovalId(facLoanApply.getLoanUser());
        facSysUserApproval.setApprovalTime(new Date());
        facSysUserApproval.setApprovalLevel(1);
        facSysUserApproval.setApplicantId(facLoanApply.getLoanUser());
        facSysUserApproval.setApprovalState("3");
        facSysUserApproval.setApprovalSight("1");
        Long leaderId = iSysUserService
                .selectApproverIdByApplyerId(facLoanApply.getLoanUser());// 所在部门负责人id
        Long upLeaderId = iSysUserService
                .selectUpApproverIdByApplyerId(facLoanApply.getLoanUser());// 所在部门负责人的上级leader
        Long approvalId = 0L;// 部门负责人id 审批人
        if (leaderId.equals(facLoanApply.getLoanUser())) { // 判断用户是否部门负责人
            // 确定一、二级审批人id
            facSysUserApproval.setApproverId(upLeaderId); // 一级审批人id
            approvalId = upLeaderId;
        } else {
            facSysUserApproval.setApproverId(leaderId);
            approvalId = leaderId;
        }
        approvalProcessMapper.insert(facSysUserApproval); // 插入一级审批记录
        if (facLoanApply.getLoanUser() == 103
                || facLoanApply.getLoanUser() == 101) {
            facLoanApply.setApplyStatus("1");
            return facLoanApplyMapper.insertFacLoanApply(facLoanApply);
        }
        Long approverId2 = iSysUserService.selectUpApproverIdByApplyerId(
                facSysUserApproval.getApproverId()); // 审批人id的上级id
        int level = 1;
        LinkedList<Long> centerId = (LinkedList<Long>) iSysUserService
                .selectCenterIdByUserId(approvalId);
        if (centerId != null && centerId.size() > 0) {
            centerId.remove(approverId2);
            for (int i = centerId.size() - 1; i >= 0; i--) {
                FacSysUserApproval center = new FacSysUserApproval();// 中心负责人
                center.setApproverId(centerId.get(i));// 审批人ID
                center.setApprovalLevel(++level);// 审批等级
                center.setApplyId(facSysUserApproval.getApplyId()); // 申请人ID
                center.setApprovalState("3");
                center.setApprovalSight("0");// 可见性
                center.setCreateTime(new Date());// 创建时间
                if (center.getApproverId() == 103) { // 如果是审批人是 coo 直接结束
                    return facLoanApplyMapper.insertFacLoanApply(facLoanApply);
                }
                approvalProcessMapper.insert(center);
            }
        }
        return facLoanApplyMapper.insertFacLoanApply(facLoanApply);
    }

    /**
     * 修改借款申请
     *
     * @param facLoanApply 借款申请信息
     * @return 结果
     */
    @Override
    public int updateFacLoanApply(FacLoanApply facLoanApply) {
        return facLoanApplyMapper.updateFacLoanApply(facLoanApply);
    }

    /**
     * 删除借款申请对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFacLoanApplyByIds(String ids) {
        return facLoanApplyMapper
                .deleteFacLoanApplyByIds(Convert.toStrArray(ids));
    }

    @Override
    public List<FacLoanApply> deatils(String num) {
        return facLoanApplyMapper
                .detail(num);


    }

}
