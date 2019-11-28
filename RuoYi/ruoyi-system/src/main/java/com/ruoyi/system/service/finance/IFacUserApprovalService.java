package com.ruoyi.system.service.finance;

import com.ruoyi.system.domain.finance.FacUserApproval;

import java.util.List;

/**
 * 财务审批 服务层
 *
 * @author ruoyi
 * @date 2019-09-27
 */
public interface IFacUserApprovalService {
    /**
     * 查询财务审批信息
     *
     * @param approvalId 财务审批ID
     * @return 财务审批信息
     */
    public FacUserApproval selectFacUserApprovalById(Long approvalId);

    /**
     * 查询财务审批列表
     *
     * @param facUserApproval 财务审批信息
     * @return 财务审批集合
     */
    public List<FacUserApproval> selectFacUserApprovalList(
            FacUserApproval facUserApproval);

    /**
     * 查询财务审批列表
     *
     * @param facUserApproval 财务审批信息
     * @return 财务审批集合
     */
    public List<FacUserApproval> selectEndFacUserApprovalList(
            FacUserApproval facUserApproval);

    public FacUserApproval selectApproval(String num, Long userId);

    /**
     * 查询财务审批列表 --我的申请
     *
     * @param facUserApproval 财务审批信息
     * @return 财务审批集合
     */
    public List<FacUserApproval> selectApplicantIdList(
            FacUserApproval facUserApproval);

    /**
     * 查询财务审批列表 --我的审批
     *
     * @param facUserApproval 财务审批信息
     * @return 财务审批集合
     */
    public List<FacUserApproval> selectApproverIdList(
            FacUserApproval facUserApproval);

    /**
     * 新增财务审批
     *
     * @param facUserApproval 财务审批信息
     * @return 结果
     */
    public int insertFacUserApproval(FacUserApproval facUserApproval);

    /**
     * 修改财务审批
     *
     * @param facUserApproval 财务审批信息
     * @return 结果
     */
    public int updateFacUserApproval(FacUserApproval facUserApproval);

    /**
     * 删除财务审批信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFacUserApprovalByIds(String ids);

    //创建报销的审批流
    public void createPublicPayApprovalProcess(String num, Double amount, String processName, Long userId);

    /**
     * 查询所有审批人
     **/
    public String approverName(String applyId);

    /***
     *查询审批通过数据
     *
     * **/
    public List<FacUserApproval> selectChengGong();
}
