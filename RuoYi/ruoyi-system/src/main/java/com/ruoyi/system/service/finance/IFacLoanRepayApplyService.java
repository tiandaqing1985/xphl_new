package com.ruoyi.system.service.finance;

import java.util.List;

import com.ruoyi.system.domain.finance.FacLoanRepayApply;

/**
 * 借款还款 服务层
 * 
 * @author ruoyi
 * @date 2019-10-12
 */
public interface IFacLoanRepayApplyService {
	/**
	 * 查询借款还款信息
	 * 
	 * @param id
	 *            借款还款ID
	 * @return 借款还款信息
	 */
	public FacLoanRepayApply selectFacLoanRepayApplyById(Long id);

	/**
	 * 查询借款还款列表
	 * 
	 * @param facLoanRepayApply
	 *            借款还款信息
	 * @return 借款还款集合
	 */
	public List<FacLoanRepayApply> selectFacLoanRepayApplyList(
			FacLoanRepayApply facLoanRepayApply);

	/**
	 * 新增借款还款
	 * 
	 * @param facLoanRepayApply
	 *            借款还款信息
	 * @return 结果
	 */
	public int insertFacLoanRepayApply(FacLoanRepayApply facLoanRepayApply);

	/**
	 * 修改借款还款
	 * 
	 * @param facLoanRepayApply
	 *            借款还款信息
	 * @return 结果
	 */
	public int updateFacLoanRepayApply(FacLoanRepayApply facLoanRepayApply);

	/**
	 * 删除借款还款信息
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	public int deleteFacLoanRepayApplyByIds(String ids);
	/**
	 * 查询借款还款列表
	 * 
	 * @param facLoanRepayApply
	 *            借款还款信息
	 * @return 借款还款集合
	 */
	public List<FacLoanRepayApply> selectList(String num);
	/**
	 * 查询借款还款列表
	 * 
	 * @param facLoanRepayApply
	 *            借款还款信息
	 * @return 借款还款集合
	 */
	public List<FacLoanRepayApply> selectPayer(Long id);

}
