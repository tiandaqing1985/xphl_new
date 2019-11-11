package com.ruoyi.system.service.finance.impl;

import java.util.Date;
import java.util.List;

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
            }else{
                //当前申请所有审批人已经同意
                return 0;
            }
        }else{
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
}
