package com.ruoyi.system.domain.finance;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 借款还款表 fac_loan_repay_apply
 * 
 * @author ruoyi
 * @date 2019-10-12
 */
public class FacLoanRepayApply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 借款编号 */
	private String num;
	/** 还款人 */
	private Long payer;
	/** 还款时间 */
	private Date repayTime;
	/** 还款方式 */
	private String repayMethod;
	/** 还款金额（元） */
	private Double repayAmount;
	/** 实际欠款金额（元） */
	private Double arrears;
	/** 还款凭证 */
	private String voucher;
	/** 财务确认还款金额 */
	private Double method;
	/** 核实后欠款金额 */
	private Double amount;
	/** 还款状态 */
	private String states;
	/** 备注 */
	private String remarks;
	private String payerName;
	
	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setNum(String num) 
	{
		this.num = num;
	}

	public String getNum() 
	{
		return num;
	}
	public void setPayer(Long payer) 
	{
		this.payer = payer;
	}

	public Long getPayer() 
	{
		return payer;
	}
	public void setRepayTime(Date repayTime) 
	{
		this.repayTime = repayTime;
	}

	public Date getRepayTime() 
	{
		return repayTime;
	}
	public void setRepayMethod(String repayMethod) 
	{
		this.repayMethod = repayMethod;
	}

	public String getRepayMethod() 
	{
		return repayMethod;
	}
	public void setRepayAmount(Double repayAmount) 
	{
		this.repayAmount = repayAmount;
	}

	public Double getRepayAmount() 
	{
		return repayAmount;
	}
	public void setArrears(Double arrears) 
	{
		this.arrears = arrears;
	}

	public Double getArrears() 
	{
		return arrears;
	}
	public void setVoucher(String voucher) 
	{
		this.voucher = voucher;
	}

	public String getVoucher() 
	{
		return voucher;
	}
	public void setMethod(Double method) 
	{
		this.method = method;
	}

	public Double getMethod() 
	{
		return method;
	}
	public void setAmount(Double amount) 
	{
		this.amount = amount;
	}

	public Double getAmount() 
	{
		return amount;
	}
	public void setStates(String states) 
	{
		this.states = states;
	}

	public String getStates() 
	{
		return states;
	}
	public void setRemarks(String remarks) 
	{
		this.remarks = remarks;
	}

	public String getRemarks() 
	{
		return remarks;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("num", getNum())
            .append("payer", getPayer())
            .append("repayTime", getRepayTime())
            .append("repayMethod", getRepayMethod())
            .append("repayAmount", getRepayAmount())
            .append("arrears", getArrears())
            .append("voucher", getVoucher())
            .append("method", getMethod())
            .append("amount", getAmount())
            .append("states", getStates())
            .append("remarks", getRemarks())
            .toString();
    }
}
