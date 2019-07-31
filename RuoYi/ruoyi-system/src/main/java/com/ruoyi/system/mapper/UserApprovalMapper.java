package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.QueryConditions;
import com.ruoyi.system.domain.UserApproval;
import java.util.List;	

/**
 * 审批 数据层
 * 
 * @author ruoyi
 * @date 2019-06-04
 */
public interface UserApprovalMapper 
{
	/**
     * 查询审批信息
     * 
     * @param approvalId 审批ID
     * @return 审批信息
     */
	public UserApproval selectUserApprovalById(Long approvalId);
	
	/**
     * 查询审批列表
     * 
     * @param userApproval 审批信息
     * @return 审批集合
     */
	public List<UserApproval> selectUserApprovalList(UserApproval userApproval);
	
	/**
     * 新增审批
     * 
     * @param userApproval 审批信息
     * @return 结果
     */
	public int insertUserApproval(UserApproval userApproval);
	
	/**
     * 修改审批
     * 
     * @param userApproval 审批信息
     * @return 结果
     */
	public int updateUserApproval(UserApproval userApproval);
	
	/**
     * 删除审批
     * 
     * @param approvalId 审批ID
     * @return 结果
     */
	public int deleteUserApprovalById(Long approvalId);
	
	/**
     * 批量删除审批
     * 
     * @param approvalIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteUserApprovalByIds(String[] approvalIds);
	
	/**
	 * 按条件查询审批单
	 * 
	 * @param userApproval
	 * @return
	 */
	public UserApproval selectUserApprovalByUserApproval(UserApproval userApproval);
	
	/**
     * 查询审批列表
     * 
     * @param userApproval 审批信息
     * @return 审批集合
     */
	public List<QueryConditions> selectQueryConditionsList(QueryConditions queryConditions);
	
	
	/**
     * 查询所有审批列表
     * 
     * @param userApproval 审批信息
     * @return 审批集合
     */
	public List<QueryConditions> selectAllQueryConditionsList(QueryConditions queryConditions);
	
	public int deleteChongFuShenHe(Long applyId);
}