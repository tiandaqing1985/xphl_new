package com.ruoyi.system.mapper.finance;

import com.ruoyi.system.domain.finance.FacCostPutupReimburse;

import java.util.List;

/**
 * 差旅住宿报销 数据层
 * 
 * @author ruoyi
 * @date 2019-11-12
 */
public interface FacCostPutupReimburseMapper 
{
	/**
     * 查询差旅住宿报销信息
     * 
     * @param id 差旅住宿报销ID
     * @return 差旅住宿报销信息
     */
	public FacCostPutupReimburse selectFacCostPutupReimburseById(Long id);
	
	/**
     * 查询差旅住宿报销列表
     * 
     * @param facCostPutupReimburse 差旅住宿报销信息
     * @return 差旅住宿报销集合
     */
	public List<FacCostPutupReimburse> selectFacCostPutupReimburseList(FacCostPutupReimburse facCostPutupReimburse);
	
	/**
     * 新增差旅住宿报销
     * 
     * @param facCostPutupReimburse 差旅住宿报销信息
     * @return 结果
     */
	public int insertFacCostPutupReimburse(FacCostPutupReimburse facCostPutupReimburse);
	
	/**
     * 修改差旅住宿报销
     * 
     * @param facCostPutupReimburse 差旅住宿报销信息
     * @return 结果
     */
	public int updateFacCostPutupReimburse(FacCostPutupReimburse facCostPutupReimburse);
	
	/**
     * 删除差旅住宿报销
     * 
     * @param id 差旅住宿报销ID
     * @return 结果
     */
	public int deleteFacCostPutupReimburseById(Long id);
	
	/**
     * 批量删除差旅住宿报销
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacCostPutupReimburseByIds(String[] ids);

    public Double selectAmountByNum(String num);
}