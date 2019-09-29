package com.ruoyi.system.domain.finance;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 差旅申请详细列 表 fac_cost_detail_apply
 * 
 * @author ruoyi
 * @date 2019-09-09
 */
public class FacCostDetailApply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 差旅编号 */
	private String num;
	/** 时间 */
	private Date costDate;
	/** 交通工具 */
	private String vehicle;
	/** 出发的 */
	private String departure;
	/** 目的地 */
	private String target;
	/** 预计金额 */
	private Double amount;
	/** 工作内容 */
	private String reason;

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
	public void setCostDate(Date costDate) 
	{
		this.costDate = costDate;
	}

	public Date getCostDate() 
	{
		return costDate;
	}
	public void setVehicle(String vehicle) 
	{
		this.vehicle = vehicle;
	}

	public String getVehicle() 
	{
		return vehicle;
	}
	public void setDeparture(String departure) 
	{
		this.departure = departure;
	}

	public String getDeparture() 
	{
		return departure;
	}
	public void setTarget(String target) 
	{
		this.target = target;
	}

	public String getTarget() 
	{
		return target;
	}
	public void setAmount(Double amount) 
	{
		this.amount = amount;
	}

	public Double getAmount() 
	{
		return amount;
	}
	public void setReason(String reason) 
	{
		this.reason = reason;
	}

	public String getReason() 
	{
		return reason;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("num", getNum())
            .append("costDate", getCostDate())
            .append("vehicle", getVehicle())
            .append("departure", getDeparture())
            .append("target", getTarget())
            .append("amount", getAmount())
            .append("reason", getReason())
            .toString();
    }
}
