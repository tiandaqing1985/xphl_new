package com.ruoyi.system.mapper.finance;


import com.ruoyi.system.domain.finance.FacReimburseApply;

import java.util.List;

/**
 * 报销 数据层
 * 
 * @author ruoyi
 * @date 2019-07-31
 */
public interface FacReimburseApplyMapper 
{
	/**
     * 查询报销信息
     * 
     * @param id 报销ID
     * @return 报销信息
     */
	public FacReimburseApply selectFacReimburseApplyById(String id);
	
	/**
     * 查询报销列表
     * 
     * @param facReimburseApply 报销信息
     * @return 报销集合
     */
	public List<FacReimburseApply> selectFacReimburseApplyList(FacReimburseApply facReimburseApply);
	
	/**
     * 新增报销
     * 
     * @param facReimburseApply 报销信息
     * @return 结果
     */
	public int insertFacReimburseApply(FacReimburseApply facReimburseApply);
	
	/**
     * 修改报销
     * 
     * @param facReimburseApply 报销信息
     * @return 结果
     */
	public int updateFacReimburseApply(FacReimburseApply facReimburseApply);
	
	/**
     * 删除报销
     * 
     * @param id 报销ID
     * @return 结果
     */
	public int deleteFacReimburseApplyById(String id);
	
	/**
     * 批量删除报销
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacReimburseApplyByIds(String[] ids);
	
}