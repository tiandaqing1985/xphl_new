package com.ruoyi.system.mapper.finance;

import java.util.Date;
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
	public  FacUserApproval  selectEndFacUserApprovalList(
			FacUserApproval facUserApproval); 
	
	public   FacUserApproval  selectApprovaIdlList(
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
	 
	 /**
	  * 查所有审批的名字
	  * */
	public String getAllAppNames(String applyId);


	/**
	 * 查询审批驳回
	 * */
	public List<FacUserApproval> selectBoHui();

	/**
	 * 查询审批成功需发送邮件
	 * */
	public List<FacUserApproval> selectChenggong();
	/**
	 * 查询审批成功
	 * */
	public List<FacUserApproval> selectUserId(Long userId);
	/**
	 * 查询审批成功部门详细信息
	 * */
	//public List<FacUserApproval> selectDept(Date approvalTime, Date createTime);

	public List<FacUserApproval> selectDept();

}