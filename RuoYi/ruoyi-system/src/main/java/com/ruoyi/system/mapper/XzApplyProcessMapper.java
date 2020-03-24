package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.XzApplyProcess;
import java.util.List;	

/**
 * 采购审批流程 数据层
 * 
 * @author ruoyi
 * @date 2019-12-06
 */
public interface XzApplyProcessMapper 
{
	/**
     * 查询采购审批流程信息
     * 
     * @param id 采购审批流程ID
     * @return 采购审批流程信息
     */
	public XzApplyProcess selectXzApplyProcessById(Long id);
	
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
     * 删除采购审批流程
     * 
     * @param id 采购审批流程ID
     * @return 结果
     */
	public int deleteXzApplyProcessById(Long id);
	
	/**
     * 批量删除采购审批流程
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzApplyProcessByIds(String[] ids);

    String selectApplyProcessApproverNameByApplyId(Long id);
}