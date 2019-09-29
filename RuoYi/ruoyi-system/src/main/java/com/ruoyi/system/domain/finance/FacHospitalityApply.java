package com.ruoyi.system.domain.finance;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 招待费申请表 fac_hospitality_apply
 * 
 * @author ruoyi
 * @date 2019-09-06
 */
public class FacHospitalityApply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 招待费编号 */
	private String num;
	/** 部门 */
	private String dept;
	/** 申请人 */
	private Long userId;
	/** 项目名称 */
	private String zdName;
	/** 己方人员 */
	private Long loanId;
	/** 招待时间 */
	private Date zdDate;
	/** 招待事由 */
	private String reason;
	/** 对方人员 */
	private String addUser;
	/** 总人数 */
	private String totalNumber;
	/** 招待费标准 */
	private BigDecimal standardAmount;
	/** 预算金额 */
	private BigDecimal amount;
	/** 申请时间 */
	private Date applicationTime;
	/** 审批结果 */
	private String states;

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
	public void setDept(String dept) 
	{
		this.dept = dept;
	}

	public String getDept() 
	{
		return dept;
	}
	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}

	public Long getUserId() 
	{
		return userId;
	}
	public void setZdName(String zdName) 
	{
		this.zdName = zdName;
	}

	public String getZdName() 
	{
		return zdName;
	}
	public void setLoanId(Long loanId) 
	{
		this.loanId = loanId;
	}

	public Long getLoanId() 
	{
		return loanId;
	}
	public void setZdDate(Date zdDate) 
	{
		this.zdDate = zdDate;
	}

	public Date getZdDate() 
	{
		return zdDate;
	}
	public void setReason(String reason) 
	{
		this.reason = reason;
	}

	public String getReason() 
	{
		return reason;
	}
	public void setAddUser(String addUser) 
	{
		this.addUser = addUser;
	}

	public String getAddUser() 
	{
		return addUser;
	}
	public void setTotalNumber(String totalNumber) 
	{
		this.totalNumber = totalNumber;
	}

	public String getTotalNumber() 
	{
		return totalNumber;
	}
	public void setStandardAmount(BigDecimal standardAmount) 
	{
		this.standardAmount = standardAmount;
	}

	public BigDecimal getStandardAmount() 
	{
		return standardAmount;
	}
	public void setAmount(BigDecimal amount) 
	{
		this.amount = amount;
	}

	public BigDecimal getAmount() 
	{
		return amount;
	}
	public void setApplicationTime(Date applicationTime) 
	{
		this.applicationTime = applicationTime;
	}

	public Date getApplicationTime() 
	{
		return applicationTime;
	}
	 

    public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("num", getNum())
            .append("dept", getDept())
            .append("userId", getUserId())
            .append("zdName", getZdName())
            .append("loanId", getLoanId())
            .append("zdDate", getZdDate())
            .append("reason", getReason())
            .append("addUser", getAddUser())
            .append("totalNumber", getTotalNumber())
            .append("standardAmount", getStandardAmount())
            .append("amount", getAmount())
            .append("applicationTime", getApplicationTime())
            .append("states", getStates())
            .toString();
    }
}
