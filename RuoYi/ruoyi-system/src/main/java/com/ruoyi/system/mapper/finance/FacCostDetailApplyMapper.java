 package com.ruoyi.system.mapper.finance;
 
import java.util.List;

import com.ruoyi.system.domain.finance.FacCostDetailApply;	

/**
 * 差旅申请详细列  数据层
 * 
 * @author ruoyi
 * @date 2019-10-15
 */
public interface FacCostDetailApplyMapper 
{
	/**
     * 查询差旅申请详细列 信息
     * 
     * @param id 差旅申请详细列 ID
     * @return 差旅申请详细列 信息
     */
	public FacCostDetailApply selectFacCostDetailApplyById(Long id);
	
	/**
     * 查询差旅申请详细列 列表
     * 
     * @param facCostDetailApply 差旅申请详细列 信息
     * @return 差旅申请详细列 集合
     */
	public List<FacCostDetailApply> selectFacCostDetailApplyList(FacCostDetailApply facCostDetailApply);
	
	/**
     * 新增差旅申请详细列 
     * 
     * @param facCostDetailApply 差旅申请详细列 信息
     * @return 结果
     */
	public int insertFacCostDetailApply(FacCostDetailApply facCostDetailApply);
	
	/**
     * 修改差旅申请详细列 
     * 
     * @param facCostDetailApply 差旅申请详细列 信息
     * @return 结果
     */
	public int updateFacCostDetailApply(FacCostDetailApply facCostDetailApply);
	
	/**
     * 删除差旅申请详细列 
     * 
     * @param id 差旅申请详细列 ID
     * @return 结果
     */
	public int deleteFacCostDetailApplyById(Long id);
	
	/**
     * 批量删除差旅申请详细列 
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacCostDetailApplyByIds(String[] ids);
	
}