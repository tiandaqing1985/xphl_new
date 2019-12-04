package com.ruoyi.system.service.finance.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.finance.FacCollectApply;
import com.ruoyi.system.domain.finance.FacSysUserApproval;
import com.ruoyi.system.mapper.finance.ApprovalProcessMapper;
import com.ruoyi.system.mapper.finance.FacCollectApplyMapper;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.IFacCollectApplyService;
import com.ruoyi.system.mapper.finance.FacUserApprovalMapper;
import com.ruoyi.system.domain.finance.FacUserApproval;


/**
 * 团建申请 服务层实现
 *
 * @author ruoyi
 * @date 2019-09-03
 */
@Service
public class FacCollectApplyServiceImpl implements IFacCollectApplyService {
    @Autowired
    private FacCollectApplyMapper facCollectApplyMapper;
    @Autowired
    private ApprovalProcessMapper approvalProcessMapper;
    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private  FacUserApprovalMapper facUserApprovalMapper;
    /**
     * 查询团建申请信息
     *
     * @param id 团建申请ID
     * @return 团建申请信息
     */
    @Override
    public FacCollectApply selectFacCollectApplyById(Long id) {
        return facCollectApplyMapper.selectFacCollectApplyById(id);
    }

    /**
     * 查询团建申请列表
     *
     * @param facCollectApply 团建申请信息
     * @return 团建申请集合
     */
    @Override
    public List<FacCollectApply> selectFacCollectApplyList(
            FacCollectApply facCollectApply) {
        return facCollectApplyMapper.selectFacCollectApplyList(facCollectApply);
    }

    /**
     * 新增团建申请
     *
     * @param facCollectApply 团建申请信息
     * @return 结果
     */
    @Override
    public int insertFacCollectApply(FacCollectApply facCollectApply) {
        facCollectApply.setStatus("1");
        FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
        facSysUserApproval.setAmount(facCollectApply.getAmount());
        facSysUserApproval.setApprovalId(facCollectApply.getApplicant());
        facSysUserApproval.setApprovalTime(new Date());
        facSysUserApproval.setApprovalLevel(1);
        facSysUserApproval.setApplicantId(facCollectApply.getApplicant());
        facSysUserApproval.setApprovalState("3");
        facSysUserApproval.setApprovalSight("1");
        facSysUserApproval.setApplyId(facCollectApply.getNum());
        facSysUserApproval.setProjectName(facCollectApply.getLeagueProject());
        Long leaderId = iSysUserService.selectApproverIdByApplyerId(facCollectApply.getApplicant());// 所在部门负责人id
        Long upLeaderId = iSysUserService.selectUpApproverIdByApplyerId(facCollectApply.getApplicant());// 所在部门负责人的上级leader
        Long approvalId = 0L;// 部门负责人id 审批人
        if (facCollectApply.getApplicant().equals(leaderId)) { // 判断用户是否部门负责人
            facSysUserApproval.setApproverId(upLeaderId); // 一级审批人id
            approvalId = upLeaderId;
        } else {
            facSysUserApproval.setApproverId(leaderId);
            approvalId = leaderId;
        }
        if (facCollectApply.getApplicant() == 103 || facCollectApply.getApplicant() == 101) {
            facSysUserApproval.setApproverId(101L);
            facSysUserApproval.setApprovalSight("1");
            facSysUserApproval.setApprovalState("1");
        }
        facSysUserApproval.setCreateTime(new Date());
        facSysUserApproval.setDeptName(iSysUserService.selectDeptName(facSysUserApproval.getApplicantId()));
        approvalProcessMapper.insert(facSysUserApproval); // 插入一级审批记录
        if (facCollectApply.getApplicant() == 103 || facCollectApply.getApplicant() == 101) {
            facCollectApply.setStatus("1");
            return facCollectApplyMapper.insertFacCollectApply(facCollectApply);
        }
        Long approverId2 = iSysUserService.selectUpApproverIdByApplyerId(
                facSysUserApproval.getApproverId()); // 审批人id的上级id
        int level = 1;
        LinkedList<Long> centerId = (LinkedList<Long>) iSysUserService.selectCenterIdByUserId(approvalId);
        if (centerId != null && centerId.size() > 0) {
            if (approverId2.equals(facCollectApply.getApplicant())) {
                centerId.remove(approverId2);
            }
            for (int i = centerId.size() - 1; i >= 0; i--) {
                FacSysUserApproval center = new FacSysUserApproval();// 中心负责人
                center.setApproverId(centerId.get(i));// 审批人ID
                center.setApprovalLevel(++level);// 审批等级
                center.setApplyId(facSysUserApproval.getApplyId()); // 申请人ID
                center.setApprovalState("3");
                center.setApprovalSight("0");// 可见性
                center.setApplicantId(facCollectApply.getApplicant());
                center.setAmount(facCollectApply.getAmount());
                center.setCreateTime(new Date());// 创建时间
                center.setApplyId(facCollectApply.getNum());
                center.setProjectName(facCollectApply.getLeagueProject());
                center.setDeptName(iSysUserService.selectDeptName(facSysUserApproval.getApplicantId()));
                approvalProcessMapper.insert(center);
                if (center.getApproverId() == 103) { // 如果是审批人是 coo 直接结束
                    return facCollectApplyMapper.insertFacCollectApply(facCollectApply);
                }

            }
        }

        return facCollectApplyMapper.insertFacCollectApply(facCollectApply);
    }

    /**
     * 修改团建申请
     *
     * @param facCollectApply 团建申请信息
     * @return 结果
     */
    @Override
    public int updateFacCollectApply(FacCollectApply facCollectApply) {
        return facCollectApplyMapper.updateFacCollectApply(facCollectApply);
    }

    /**
     * 删除团建申请对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFacCollectApplyByIds(String ids) {
        FacCollectApply facCollectApply = facCollectApplyMapper.selectFacCollectApplyById(Long.valueOf(ids));
        FacUserApproval facUserApproval= new FacUserApproval();
        facUserApproval.setApplyId(facCollectApply.getNum());
        List<FacUserApproval> list =facUserApprovalMapper.selectFacUserApprovalList(facUserApproval);
        if(list!=null&&list.size()>0){
            for(FacUserApproval v :list){
                facUserApprovalMapper.deleteFacUserApprovalById(v.getApprovalId());
            }
        }
        return facCollectApplyMapper
                .deleteFacCollectApplyByIds(Convert.toStrArray(ids));
    }

    @Override
    public int insertApply(FacCollectApply facCollectApply) {
        // TODO Auto-generated method stub
        facCollectApply.setStatus("5");
        return facCollectApplyMapper.insertFacCollectApply(facCollectApply);
    }

    @Override
    public FacCollectApply selectFacCollectApplyByNum(String num) {
        return facCollectApplyMapper.selectFacCollectApplyByNum(num);
    }
}
