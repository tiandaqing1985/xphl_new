package com.ruoyi.system.mapper.finance;


import com.ruoyi.system.domain.finance.FacLoanApply;

import java.util.List;

/**
 * 借款申请 数据层
 * 
 * @author ruoyi
 * @date 2019-07-30
 */
public interface FacLoanApplyMapper 
{
	/**
     * 查询借款申请信息
     * 
     * @param id 借款申请ID
     * @return 借款申请信息
     */
	public FacLoanApply selectFacLoanApplyById(String id);
	
	/**
     * 查询借款申请列表
     * 
     * @param facLoanApply 借款申请信息
     * @return 借款申请集合
     */
	public List<FacLoanApply> selectFacLoanApplyList(FacLoanApply facLoanApply);
	
	/**
     * 新增借款申请
     * 
     * @param facLoanApply 借款申请信息
     * @return 结果
     */
	public int insertFacLoanApply(FacLoanApply facLoanApply);
	
	/**
     * 修改借款申请
     * 
     * @param facLoanApply 借款申请信息
     * @return 结果
     */
	public int updateFacLoanApply(FacLoanApply facLoanApply);
	
	/**
     * 删除借款申请
     * 
     * @param id 借款申请ID
     * @return 结果
     */
	public int deleteFacLoanApplyById(String id);
	
	/**
     * 批量删除借款申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacLoanApplyByIds(String[] ids);
	
}