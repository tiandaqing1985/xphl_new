package com.ruoyi.system.service;

import com.ruoyi.system.domain.XzApplyProcess;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 采购审批流程 服务层
 * 
 * @author ruoyi
 * @date 2019-12-06
 */
public interface IXzApplyProcessService 
{

	/**
	 * 转译审批状态码
	 * @param status
	 * @return
	 */
	public String transferStatus(String status);
	/**
     * 查询采购审批流程信息
     * 
     * @param id 采购审批流程ID
     * @return 采购审批流程信息
     */
	public XzApplyProcess selectXzApplyProcessById(Long id);

	String selectApplyProcessApproverNameByApplyId(Long id);

	/**
     * 查询采购审批流程列表
     * 
     * @param xzApplyProcess 采购审批流程信息
     * @return 采购审批流程集合
     */
	public List<XzApplyProcess> selectXzApplyProcessList(XzApplyProcess xzApplyProcess);
	
	/**
     * 新增采购审批流程
     * 
     * @param xzApplyProcess 采购审批流程信息
     * @return 结果
     */
	public int insertXzApplyProcess(XzApplyProcess xzApplyProcess);
	
	/**
     * 修改采购审批流程
     * 
     * @param xzApplyProcess 采购审批流程信息
     * @return 结果
     */
	public int updateXzApplyProcess(XzApplyProcess xzApplyProcess);
		
	/**
     * 删除采购审批流程信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzApplyProcessByIds(String ids);

	void insertXzApplyProcess(LinkedHashSet<Long> approverIds, Long id);

	/**
	 * 创建行政采购审批流程
	 * @param id
	 */
	Set<Long> createXzPurchaseResourceProcess(Long id, Long applyUserId, Double amount);
}
