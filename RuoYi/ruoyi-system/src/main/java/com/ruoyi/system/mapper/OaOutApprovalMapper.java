package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.OaOutApproval;
import java.util.List;	

/**
 * 外出报备审批 数据层
 * 
 * @author ruoyi
 * @date 2019-08-01
 */
public interface OaOutApprovalMapper 
{
	/**
     * 查询外出报备审批信息
     * 
     * @param id 外出报备审批ID
     * @return 外出报备审批信息
     */
	public OaOutApproval selectOaOutApprovalById(Long id);
	
	/**
     * 查询外出报备审批列表
     * 
     * @param oaOutApproval 外出报备审批信息
     * @return 外出报备审批集合
     */
	public List<OaOutApproval> selectOaOutApprovalList(OaOutApproval oaOutApproval);
	
	/**
     * 新增外出报备审批
     * 
     * @param oaOutApproval 外出报备审批信息
     * @return 结果
     */
	public int insertOaOutApproval(OaOutApproval oaOutApproval);
	
	/**
     * 修改外出报备审批
     * 
     * @param oaOutApproval 外出报备审批信息
     * @return 结果
     */
	public int updateOaOutApproval(OaOutApproval oaOutApproval);
	
	/**
	 * 根据审批人id修改
	 * @param oaOutApproval
	 * @return
	 */
	public int updateOaOutApprovalByApprovalId(OaOutApproval oaOutApproval);
	
	/**
     * 删除外出报备审批
     * 
     * @param id 外出报备审批ID,外出报备outId
     * @return 结果
     */
	public int deleteOaOutApproval(OaOutApproval oaOutApproval);
	
	/**
     * 批量删除外出报备审批
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOaOutApprovalByOutIds(String[] ids);
	
}