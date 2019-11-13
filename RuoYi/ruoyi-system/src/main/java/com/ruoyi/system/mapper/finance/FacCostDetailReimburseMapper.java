package com.ruoyi.system.mapper.finance;

import com.ruoyi.system.domain.finance.FacCostDetailReimburse;

import java.util.List;

/**
 * 差旅申请详细报销列 数据层
 * 
 * @author ruoyi
 * @date 2019-11-12
 */
public interface FacCostDetailReimburseMapper 
{
	/**
     * 查询差旅申请详细报销列信息
     * 
     * @param id 差旅申请详细报销列ID
     * @return 差旅申请详细报销列信息
     */
	public FacCostDetailReimburse selectFacCostDetailReimburseById(Long id);
	
	/**
     * 查询差旅申请详细报销列列表
     * 
     * @param facCostDetailReimburse 差旅申请详细报销列信息
     * @return 差旅申请详细报销列集合
     */
	public List<FacCostDetailReimburse> selectFacCostDetailReimburseList(FacCostDetailReimburse facCostDetailReimburse);
	
	/**
     * 新增差旅申请详细报销列
     * 
     * @param facCostDetailReimburse 差旅申请详细报销列信息
     * @return 结果
     */
	public int insertFacCostDetailReimburse(FacCostDetailReimburse facCostDetailReimburse);
	
	/**
     * 修改差旅申请详细报销列
     * 
     * @param facCostDetailReimburse 差旅申请详细报销列信息
     * @return 结果
     */
	public int updateFacCostDetailReimburse(FacCostDetailReimburse facCostDetailReimburse);
	
	/**
     * 删除差旅申请详细报销列
     * 
     * @param id 差旅申请详细报销列ID
     * @return 结果
     */
	public int deleteFacCostDetailReimburseById(Long id);
	
	/**
     * 批量删除差旅申请详细报销列
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacCostDetailReimburseByIds(String[] ids);

    public Double selectAmountByNum(String num);
}