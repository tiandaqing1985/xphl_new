package com.ruoyi.system.service.finance;

import com.ruoyi.system.domain.finance.FacCostPutupReimburse;

import java.util.List;

/**
 * 差旅住宿报销 服务层
 * 
 * @author ruoyi
 * @date 2019-11-12
 */
public interface IFacCostPutupReimburseService 
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
     * 删除差旅住宿报销信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacCostPutupReimburseByIds(String ids);

	//查询总金额
    public Double selectAmountByNum(String num);
}
