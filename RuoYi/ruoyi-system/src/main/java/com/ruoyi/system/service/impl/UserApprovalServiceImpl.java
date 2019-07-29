package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserApprovalMapper;
import com.ruoyi.system.domain.QueryConditions;
import com.ruoyi.system.domain.UserApproval;
import com.ruoyi.system.service.IUserApprovalService;
import com.ruoyi.common.core.text.Convert;

/**
 * 审批 服务层实现
 * 
 * @author ruoyi
 * @date 2019-06-04
 */
@Service
public class UserApprovalServiceImpl implements IUserApprovalService 
{
	@Autowired
	private UserApprovalMapper userApprovalMapper;
	
	/**
     * 查询审批信息
     * 
     * @param approvalId 审批ID
     * @return 审批信息
     */
    @Override
	public UserApproval selectUserApprovalById(Long approvalId)
	{
	    return userApprovalMapper.selectUserApprovalById(approvalId);
	}
	
	/**
     * 查询审批列表
     * 
     * @param userApproval 审批信息
     * @return 审批集合
     */
	@Override
	public List<UserApproval> selectUserApprovalList(UserApproval userApproval)
	{
	    return userApprovalMapper.selectUserApprovalList(userApproval);
	}
	
    /**
     * 新增审批
     * 
     * @param userApproval 审批信息
     * @return 结果
     */
	@Override
	public int insertUserApproval(UserApproval userApproval)
	{
	    return userApprovalMapper.insertUserApproval(userApproval);
	}
	
	/**
     * 修改审批
     * 
     * @param userApproval 审批信息
     * @return 结果
     */
	@Override
	public int updateUserApproval(UserApproval userApproval)
	{
	    return userApprovalMapper.updateUserApproval(userApproval);
	}

	/**
     * 删除审批对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUserApprovalByIds(String ids)
	{
		return userApprovalMapper.deleteUserApprovalByIds(Convert.toStrArray(ids));
	}

	@Override
	public UserApproval selectUserApprovalByUserApproval(UserApproval userApproval) {
		
		return userApprovalMapper.selectUserApprovalByUserApproval(userApproval);
	}

	/**
     * 查询审批列表
     * 
     * @param userApproval 审批信息
     * @return 审批集合
     */
	@Override
	public List<QueryConditions> selectQueryConditionsList(QueryConditions queryConditions) {
		
		return userApprovalMapper.selectQueryConditionsList(queryConditions);
	}

	/**
     * 查询所有审批列表
     * 
     * @param userApproval 审批信息
     * @return 审批集合
     */
	@Override
	public List<QueryConditions> selectAllQueryConditionsList(QueryConditions queryConditions) {
		
		return userApprovalMapper.selectAllQueryConditionsList(queryConditions);
	}
	
}
