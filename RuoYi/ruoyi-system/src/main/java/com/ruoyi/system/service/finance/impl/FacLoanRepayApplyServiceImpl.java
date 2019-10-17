package com.ruoyi.system.service.finance.impl;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.finance.FacLoanRepayApply;
import com.ruoyi.system.mapper.finance.FacLoanApplyMapper;
import com.ruoyi.system.mapper.finance.FacLoanRepayApplyMapper;
import com.ruoyi.system.service.finance.IFacLoanRepayApplyService;

/**
 * 借款还款 服务层实现
 * 
 * @author ruoyi
 * @date 2019-10-12
 */
@Service
public class FacLoanRepayApplyServiceImpl implements IFacLoanRepayApplyService 
{
	@Autowired
	private FacLoanRepayApplyMapper facLoanRepayApplyMapper;

	@Autowired
	private FacLoanApplyMapper facLoanApplyMapper;
	
	/**
     * 查询借款还款信息
     * 
     * @param id 借款还款ID
     * @return 借款还款信息
     */
    @Override
	public FacLoanRepayApply selectFacLoanRepayApplyById(Long id)
	{
	    return facLoanRepayApplyMapper.selectFacLoanRepayApplyById(id);
	}
	
	/**
     * 查询借款还款列表
     * 
     * @param facLoanRepayApply 借款还款信息
     * @return 借款还款集合
     */
	@Override
	public List<FacLoanRepayApply> selectFacLoanRepayApplyList(FacLoanRepayApply facLoanRepayApply)
	{
	    return facLoanRepayApplyMapper.selectFacLoanRepayApplyList(facLoanRepayApply);
	}
	
    /**
     * 新增借款还款
     * 
     * @param facLoanRepayApply 借款还款信息
     * @return 结果
     */
	@Override
	public int insertFacLoanRepayApply(FacLoanRepayApply facLoanRepayApply)
	{ 	facLoanRepayApply.getRepayAmount();
		double money=facLoanApplyMapper.FacLoanAmount(facLoanRepayApply.getNum());//总欠款额
		List<FacLoanRepayApply> repayApply=facLoanRepayApplyMapper.selectFacLoanRepayApplyList(facLoanRepayApply);
		if(repayApply==null || repayApply.equals("")){
			facLoanRepayApply.setArrears(money-facLoanRepayApply.getRepayAmount());//实际欠款金额（元）
		}else{
			double s = facLoanRepayApplyMapper.selectAmount(facLoanRepayApply.getNum());
			facLoanRepayApply.setArrears(money-facLoanRepayApply.getRepayAmount());
		}
		facLoanRepayApply.setRepayTime(new Date());//还款时间 
		//facLoanRepayApply.setMethod(method); //财务确认还款金额
		//facLoanRepayApply.setAmount(amount);//核实后欠款金额
		facLoanRepayApply.setStates("3");//还款状态 
	    return facLoanRepayApplyMapper.insertFacLoanRepayApply(facLoanRepayApply);
	}
	
	/**
     * 修改借款还款
     * 
     * @param facLoanRepayApply 借款还款信息
     * @return 结果
     */
	@Override
	public int updateFacLoanRepayApply(FacLoanRepayApply facLoanRepayApply)
	{
	    return facLoanRepayApplyMapper.updateFacLoanRepayApply(facLoanRepayApply);
	}

	/**
     * 删除借款还款对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFacLoanRepayApplyByIds(String ids)
	{
		return facLoanRepayApplyMapper.deleteFacLoanRepayApplyByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<FacLoanRepayApply> selectList(String num) {
		 
		return facLoanRepayApplyMapper.selectList(num);
	}

	@Override
	public List<FacLoanRepayApply> selectPayer(Long payer) {
		return facLoanRepayApplyMapper.selectPayer(payer);
	}
	
}
