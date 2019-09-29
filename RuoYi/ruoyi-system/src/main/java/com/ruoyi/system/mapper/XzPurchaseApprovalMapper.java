package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.XzPurchaseApproval;
import java.util.List;	

/**
 * 采购申请审批 数据层
 * 
 * @author ruoyi
 * @date 2019-09-24
 */
public interface XzPurchaseApprovalMapper 
{
	/**
     * 查询采购申请审批信息
     * 
     * @param id 采购申请审批ID
     * @return 采购申请审批信息
     */
	public XzPurchaseApproval selectXzPurchaseApprovalById(Long id);
	
	/**
     * 查询采购申请审批列表
     * 
     * @param xzPurchaseApproval 采购申请审批信息
     * @return 采购申请审批集合
     */
	public List<XzPurchaseApproval> selectXzPurchaseApprovalList(XzPurchaseApproval xzPurchaseApproval);
	
	/**
     * 新增采购申请审批
     * 
     * @param xzPurchaseApproval 采购申请审批信息
     * @return 结果
     */
	public int insertXzPurchaseApproval(XzPurchaseApproval xzPurchaseApproval);
	
	/**
     * 修改采购申请审批
     * 
     * @param xzPurchaseApproval 采购申请审批信息
     * @return 结果
     */
	public int updateXzPurchaseApproval(XzPurchaseApproval xzPurchaseApproval);
	
	/**
     * 删除采购申请审批
     * 
     * @param id 采购申请审批ID
     * @return 结果
     */
	public int deleteXzPurchaseApprovalById(Long id);
	
	/**
     * 批量删除采购申请审批
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzPurchaseApprovalByIds(String[] ids);
	
}