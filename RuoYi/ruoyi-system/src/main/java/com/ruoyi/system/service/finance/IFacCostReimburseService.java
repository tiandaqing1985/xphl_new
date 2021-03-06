package com.ruoyi.system.service.finance;

import com.ruoyi.system.domain.finance.FacCostReimburse;

import java.util.List;

/**
 * 差旅报销 服务层
 * 
 * @author ruoyi
 * @date 2019-11-12
 */
public interface IFacCostReimburseService 
{
	/**
     * 查询差旅报销信息
     * 
     * @param id 差旅报销ID
     * @return 差旅报销信息
     */
	public FacCostReimburse selectFacCostReimburseById(Long id);
	
	/**
     * 查询差旅报销列表
     * 
     * @param facCostReimburse 差旅报销信息
     * @return 差旅报销集合
     */
	public List<FacCostReimburse> selectFacCostReimburseList(FacCostReimburse facCostReimburse);
	
	/**
     * 新增差旅报销
     * 
     * @param facCostReimburse 差旅报销信息
     * @return 结果
     */
	public int insertFacCostReimburse(FacCostReimburse facCostReimburse);
	
	/**
     * 修改差旅报销
     * 
     * @param facCostReimburse 差旅报销信息
     * @return 结果
     */
	public int updateFacCostReimburse(FacCostReimburse facCostReimburse);
		
	/**
     * 删除差旅报销信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacCostReimburseByIds(String ids);

    public FacCostReimburse selectFacCostReimburseByNum(String num);
}
