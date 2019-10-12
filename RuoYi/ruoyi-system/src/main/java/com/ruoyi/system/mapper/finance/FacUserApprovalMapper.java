package com.ruoyi.system.mapper.finance;

import java.util.List;

import com.ruoyi.system.domain.finance.FacUserApproval;

/**
 * 财务审批 数据层
 * 
 * @author ruoyi
 * @date 2019-09-27
 */
public interface FacUserApprovalMapper {
	/**
	 * 查询财务审批信息
	 * 
	 * @param approvalId
	 *            财务审批ID
	 * @return 财务审批信息
	 */
	public FacUserApproval selectFacUserApprovalById(Long approvalId);

	/**
	 * 查询财务审批列表
	 * 
	 * @param facUserApproval
	 *            财务审批信息
	 * @return 财务审批集合
	 */
	public List<FacUserApproval> selectFacUserApprovalList(
			FacUserApproval facUserApproval);
	 
	
	/**
	 * 查询财务审批列表  ----->  已审批
	 * 
	 * @param facUserApproval
	 *            财务审批信息
	 * @return 财务审批集合
	 */
	public List<FacUserApproval> selectEndFacUserApprovalList(
			FacUserApproval facUserApproval);

	/**
	 * 查询财务审批列表  ----->  我的申请
	 * 
	 * @param facUserApproval
	 *            财务审批信息
	 * @return 财务审批集合
	 */
	public List<FacUserApproval> selectApplicantIdList(
			FacUserApproval facUserApproval);

	/**
	 * 查询财务审批列表  ----->  我的审批
	 * 
	 * @param facUserApproval
	 *            财务审批信息
	 * @return 财务审批集合
	 */
	public List<FacUserApproval> selectApproverIdList(
			FacUserApproval facUserApproval);

	/**
	 * 新增财务审批
	 * 
	 * @param facUserApproval
	 *            财务审批信息
	 * @return 结果
	 */
	public int insertFacUserApproval(FacUserApproval facUserApproval);

	/**
	 * 修改财务审批
	 * 
	 * @param facUserApproval
	 *            财务审批信息
	 * @return 结果
	 */
	public int updateFacUserApproval(FacUserApproval facUserApproval);

	/**
	 * 删除财务审批
	 * 
	 * @param approvalId
	 *            财务审批ID
	 * @return 结果
	 */
	public int deleteFacUserApprovalById(Long approvalId);

	/**
	 * 批量删除财务审批
	 * 
	 * @param approvalIds
	 *            需要删除的数据ID
	 * @return 结果
	 */
	public int deleteFacUserApprovalByIds(String[] approvalIds);

}