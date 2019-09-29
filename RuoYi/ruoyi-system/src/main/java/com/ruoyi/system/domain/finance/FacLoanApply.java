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
	private Long id;
	/** 借款编号 */
	private String num;
	/** 借款名称 */
	private String loanName;
	/** 借款金额 */
	private Double amount;
	/** 借款人 */
	private Long loanUser;
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
	/**创建时间*/
	private Date createTime;
	/**更改时间*/
	private Date updateTime;
	
	
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getLoanUser() {
		return loanUser;
	}

	public void setLoanUser(Long loanUser) {
		this.loanUser = loanUser;
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

    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
