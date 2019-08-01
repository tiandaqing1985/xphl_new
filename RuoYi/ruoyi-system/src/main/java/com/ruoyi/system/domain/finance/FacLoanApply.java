package com.ruoyi.system.domain.finance;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 借款申请表 fac_loan_apply
 * 
 * @author ruoyi
 * @date 2019-07-30
 */
public class FacLoanApply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private String id;
	/** 借款编号 */
	private String num;
	/** 借款名称 */
	private String loanName;
	/** 借款金额 */
	private Double amount;
	/** 借款人 */
	private Date loanUser;
	/** 借款时间 */
	private Date loanTime;
	/** 预计还款时间 */
	private Date repayTime;
	/** 财务操作 */
	private String facOperate;
	/** 借款事由 */
	private String reason;
	/** 申请人操作 */
	private String applyStatus;

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
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
	public void setLoanName(String loanName) 
	{
		this.loanName = loanName;
	}

	public String getLoanName() 
	{
		return loanName;
	}
	public void setAmount(Double amount) 
	{
		this.amount = amount;
	}

	public Double getAmount() 
	{
		return amount;
	}
	public void setLoanUser(Date loanUser) 
	{
		this.loanUser = loanUser;
	}

	public Date getLoanUser() 
	{
		return loanUser;
	}
	public void setLoanTime(Date loanTime) 
	{
		this.loanTime = loanTime;
	}

	public Date getLoanTime() 
	{
		return loanTime;
	}
	public void setRepayTime(Date repayTime) 
	{
		this.repayTime = repayTime;
	}

	public Date getRepayTime() 
	{
		return repayTime;
	}
	public void setFacOperate(String facOperate) 
	{
		this.facOperate = facOperate;
	}

	public String getFacOperate() 
	{
		return facOperate;
	}
	public void setReason(String reason) 
	{
		this.reason = reason;
	}

	public String getReason() 
	{
		return reason;
	}
	public void setApplyStatus(String applyStatus) 
	{
		this.applyStatus = applyStatus;
	}

	public String getApplyStatus() 
	{
		return applyStatus;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("num", getNum())
            .append("loanName", getLoanName())
            .append("amount", getAmount())
            .append("loanUser", getLoanUser())
            .append("createTime", getCreateTime())
            .append("loanTime", getLoanTime())
            .append("repayTime", getRepayTime())
            .append("facOperate", getFacOperate())
            .append("reason", getReason())
            .append("applyStatus", getApplyStatus())
            .toString();
    }
}
