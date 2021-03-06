package com.ruoyi.system.domain.finance;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 差旅报销表 fac_cost_reimburse
 * 
 * @author ruoyi
 * @date 2019-11-12
 */
public class FacCostReimburse extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;
	/** 出差编号 */
	private String num;
	/** 项目名称 */
	private String busName;
	/** 出差时间 */
	private Date outTime;
	/** 预计返回时间 */
	private Date backTimeEs;
	/** 出差人员 */
	private String outMan;
	/** 预计费用 */
	private Double moneyEs;
	/** 出差目的地 */
	private String toLocal;
	/** 事由 */
	private String reason;
	/** 审批状态(1同意，2驳回 ，3未操作) */
	private String status;
	/** 申请人 */
	private Long userId;
	/** 申请时间 */
	private Date applicationTime;

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
	public void setBusName(String busName) 
	{
		this.busName = busName;
	}

	public String getBusName() 
	{
		return busName;
	}
	public void setOutTime(Date outTime) 
	{
		this.outTime = outTime;
	}

	public Date getOutTime() 
	{
		return outTime;
	}
	public void setBackTimeEs(Date backTimeEs) 
	{
		this.backTimeEs = backTimeEs;
	}

	public Date getBackTimeEs() 
	{
		return backTimeEs;
	}
	public void setOutMan(String outMan) 
	{
		this.outMan = outMan;
	}

	public String getOutMan() 
	{
		return outMan;
	}
	public void setMoneyEs(Double moneyEs) 
	{
		this.moneyEs = moneyEs;
	}

	public Double getMoneyEs() 
	{
		return moneyEs;
	}
	public void setToLocal(String toLocal) 
	{
		this.toLocal = toLocal;
	}

	public String getToLocal() 
	{
		return toLocal;
	}
	public void setReason(String reason) 
	{
		this.reason = reason;
	}

	public String getReason() 
	{
		return reason;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}

	public Long getUserId() 
	{
		return userId;
	}
	public void setApplicationTime(Date applicationTime) 
	{
		this.applicationTime = applicationTime;
	}

	public Date getApplicationTime() 
	{
		return applicationTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("num", getNum())
            .append("busName", getBusName())
            .append("outTime", getOutTime())
            .append("backTimeEs", getBackTimeEs())
            .append("outMan", getOutMan())
            .append("moneyEs", getMoneyEs())
            .append("toLocal", getToLocal())
            .append("reason", getReason())
            .append("status", getStatus())
            .append("userId", getUserId())
            .append("applicationTime", getApplicationTime())
            .toString();
    }
}
