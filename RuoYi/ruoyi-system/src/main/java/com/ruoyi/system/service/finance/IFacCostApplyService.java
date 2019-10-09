package com.ruoyi.system.service.finance;


import java.util.List;

import com.ruoyi.system.domain.finance.FacCostApply;
import com.ruoyi.system.domain.finance.FacCostDetailApply;

 

/**
 * 差旅申请 服务层
 * 
 * @author ruoyi
 * @date 2019-09-02
 */
public interface IFacCostApplyService 
{
	/**
     * 查询差旅申请信息
     * 
     * @param id 差旅申请ID
     * @return 差旅申请信息
     */
	public FacCostApply selectFacCostApplyById(Long id);
	
	/**
     * 查询差旅申请列表
     * 
     * @param facCostApply 差旅申请信息
     * @return 差旅申请集合
     */
	public List<FacCostApply> selectFacCostApplyList(FacCostApply facCostApply);
	
	/**
     * 新增差旅申请
     * 
     * @param facCostApply 差旅申请信息
     * @return 结果
     */
	public int insertFacCostApply(FacCostApply facCostApply);


	/**
	 * 新增差旅详情
	 *
	 * @param facCostDetailApply 差旅申请信息
	 * @return 结果
	 */

	public int insertFacCostDetailApply(FacCostDetailApply facCostDetailApply);
	/**
     * 修改差旅申请
     * 
     * @param facCostApply 差旅申请信息
     * @return 结果
     */
	public int updateFacCostApply(FacCostApply facCostApply);
		
	/**
     * 删除差旅申请信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacCostApplyByIds(String ids);
	
	
	/**
	 * 根据差旅编号查询差旅申请详情
	 * @param num
	 * @return
	 */
	FacCostApply deatil(String num);
	
	/**
	 * 根据差旅编号查询行程安排详情
	 * @param num
	 * @return
	 */
	List<FacCostDetailApply> deatils(String num);
}