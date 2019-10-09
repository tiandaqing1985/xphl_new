package com.ruoyi.system.service.finance.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.ruoyi.system.domain.finance.FacSysUserApproval;
import com.ruoyi.system.mapper.finance.ApprovalProcessMapper;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.finance.FacHospitalityApply;
import com.ruoyi.system.mapper.finance.FacHospitalityApplyMapper;
import com.ruoyi.system.service.finance.IFacHospitalityApplyService;

/**
 * 招待费申请 服务层实现
 * 
 * @author ruoyi
 * @date 2019-09-06
 */
@Service
public class FacHospitalityApplyServiceImpl implements IFacHospitalityApplyService 
{
	@Autowired
	private FacHospitalityApplyMapper facHospitalityApplyMapper;
	@Autowired
	private ApprovalProcessMapper approvalProcessMapper;
	@Autowired
	private ISysUserService iSysUserService;

	/**
     * 查询招待费申请信息
     * 
     * @param id 招待费申请ID
     * @return 招待费申请信息
     */
    @Override
	public FacHospitalityApply selectFacHospitalityApplyById(Long id)
	{
	    return facHospitalityApplyMapper.selectFacHospitalityApplyById(id);
	}
	
	/**
     * 查询招待费申请列表
     * 
     * @param facHospitalityApply 招待费申请信息
     * @return 招待费申请集合
     */
	@Override
	public List<FacHospitalityApply> selectFacHospitalityApplyList(FacHospitalityApply facHospitalityApply)
	{
	    return facHospitalityApplyMapper.selectFacHospitalityApplyList(facHospitalityApply);
	}
	
    /**
     * 新增招待费申请
     * 
     * @param facHospitalityApply 招待费申请信息
     * @return 结果
     */
	@Override
	public int insertFacHospitalityApply(FacHospitalityApply facHospitalityApply)
	{
		facHospitalityApply.setStates("3");
		FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
		facSysUserApproval.setApprovalId(facHospitalityApply.getUserId());
		facSysUserApproval.setApprovalTime(new Date());
		facSysUserApproval.setApprovalLevel(1);
		facSysUserApproval.setApplicantId(facHospitalityApply.getUserId());
		facSysUserApproval.setApprovalState("3");
		facSysUserApproval.setApprovalSight("1");
		Long leaderId = iSysUserService
				.selectApproverIdByApplyerId(facHospitalityApply.getUserId());// 所在部门负责人id
		Long upLeaderId = iSysUserService
				.selectUpApproverIdByApplyerId(facHospitalityApply.getUserId());// 所在部门负责人的上级leader
		Long approvalId = 0L;// 部门负责人id 审批人
		if (leaderId.equals(facHospitalityApply.getUserId())) { // 判断用户是否部门负责人
															// 确定一、二级审批人id
			facSysUserApproval.setApproverId(upLeaderId); // 一级审批人id
			approvalId = upLeaderId;
		} else {
			facSysUserApproval.setApproverId(leaderId);
			approvalId = leaderId;
		}
		approvalProcessMapper.insert(facSysUserApproval); // 插入一级审批记录
		if (facHospitalityApply.getUserId() == 103
				|| facHospitalityApply.getUserId() == 101) {
			facHospitalityApply.setStates("1");
			return facHospitalityApplyMapper.insertFacHospitalityApply(facHospitalityApply);
					 
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
					return facHospitalityApplyMapper.insertFacHospitalityApply(facHospitalityApply);
				}
				approvalProcessMapper.insert(center);
			}
		} 
	    return facHospitalityApplyMapper.insertFacHospitalityApply(facHospitalityApply);
	}
	
	/**
     * 修改招待费申请
     * 
     * @param facHospitalityApply 招待费申请信息
     * @return 结果
     */
	@Override
	public int updateFacHospitalityApply(FacHospitalityApply facHospitalityApply)
	{
	    return facHospitalityApplyMapper.updateFacHospitalityApply(facHospitalityApply);
	}

	/**
     * 删除招待费申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFacHospitalityApplyByIds(String ids)
	{
		return facHospitalityApplyMapper.deleteFacHospitalityApplyByIds(Convert.toStrArray(ids));
	}
	
}