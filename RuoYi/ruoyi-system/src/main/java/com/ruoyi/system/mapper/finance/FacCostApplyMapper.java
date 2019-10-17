package com.ruoyi.system.mapper.finance;

import java.util.List;

import com.ruoyi.system.domain.finance.FacCostApply;
import com.ruoyi.system.domain.finance.FacCostDetailApply;

/**
 * 差旅申请 数据层
 * 
 * @author ruoyi
 * @date 2019-09-02
 */
public interface FacCostApplyMapper {
	/**
	 * 查询差旅申请信息
	 * 
	 * @param id
	 *            差旅申请ID
	 * @return 差旅申请信息
	 */
	public FacCostApply selectFacCostApplyById(Long id);

	/**
	 * 查询差旅申请列表
	 * 
	 * @param facCostApply
	 *            差旅申请信息
	 * @return 差旅申请集合
	 */
	public List<FacCostApply> selectFacCostApplyList(FacCostApply facCostApply);

	/**
	 * 新增差旅申请
	 * 
	 * @param facCostApply
	 *            差旅申请信息
	 * @return 结果
	 */
	public int insertFacCostApply(FacCostApply facCostApply);

	/**
	 * 修改差旅申请
	 * 
	 * @param facCostApply
	 *            差旅申请信息
	 * @return 结果
	 */
	public int updateFacCostApply(FacCostApply facCostApply);

	/**
	 * 删除差旅申请
	 * 
	 * @param id
	 *            差旅申请ID
	 * @return 结果
	 */
	public int deleteFacCostApplyById(Long id);

	/**
	 * 批量删除差旅申请
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	public int deleteFacCostApplyByIds(String[] ids);

	/**
	 * 新增行程安排 FacCostDetailApply
	 * 
	 * @param facCostApply
	 *            行程安排
	 * @return 结果
	 */
	public int insertFacCostDetailApply(FacCostDetailApply facCostDetailApply);

	/**
	 * 差旅详细信息汇总
	 * 
	 * @param num
	 * @return
	 */
	public FacCostApply detail(String num);

	/**
	 * 行程安排
	 * 
	 * @param num
	 * @return
	 */
	public List<FacCostDetailApply> adiTail(String num);
	/**
	 * 查询交通金额
	 *
	 * 
	 */
	public double selectAmount(String num);

}