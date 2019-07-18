package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 当当后端汇总表 dangdang_back
 * 
 * @author ruoyi
 * @date 2019-07-18
 */
public class DangdangBack extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** ID */
	private Integer id;
	/** 日期 */
	@Excel(name="日期")
	private Date ddDate;
	/** 渠道id */
	@Excel(name = "渠道id")
	private String channelId;
	/** uv */
	@Excel(name = "uv")
	private Integer uv;
	/** 新增激活用户 */
	@Excel(name="新增激活用户数")
	private Integer addActiveUser;
	/** 收订纯新客 */
	@Excel(name="收订纯新客")
	private Integer coollectingPureNewCustomers;
	/** 收订新客（含纯和渗透） */
	@Excel(name="收订新客(含纯和渗透)")
	private Integer bookingNewCustomersPureInfiltration;
	/** 收订订单数 */
	@Excel(name="收订订单数")
	private Integer orderingOrders;
	/** 收订金额 */
	@Excel(name="收订金额")
	private Double receivingAmount;
	/** 出库纯新客 */
	@Excel(name="出库纯新客")
	private Integer pureOutOfTheLibrary;
	/** 出库新客(含纯和渗透) */
	@Excel(name="出库新客(含纯和渗透)")
	private Integer outboundNewCustomersPureInfiltration;
	/** 出库订单数 */
	@Excel(name="出库订单数")
	private Integer outboundOrderNumber;
	/** 出库金额 */
	@Excel(name="出库金额")
	private Double outboundAmount;
	/** 标识 */
	private String identification;
	/** 区分 */
	private String dis;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setDdDate(Date ddDate) 
	{
		this.ddDate = ddDate;
	}

	public Date getDdDate() 
	{
		return ddDate;
	}
	public void setChannelId(String channelId) 
	{
		this.channelId = channelId;
	}

	public String getChannelId() 
	{
		return channelId;
	}
	public void setUv(Integer uv) 
	{
		this.uv = uv;
	}

	public Integer getUv() 
	{
		return uv;
	}
	public void setAddActiveUser(Integer addActiveUser) 
	{
		this.addActiveUser = addActiveUser;
	}

	public Integer getAddActiveUser() 
	{
		return addActiveUser;
	}
	public void setCoollectingPureNewCustomers(Integer coollectingPureNewCustomers) 
	{
		this.coollectingPureNewCustomers = coollectingPureNewCustomers;
	}

	public Integer getCoollectingPureNewCustomers() 
	{
		return coollectingPureNewCustomers;
	}
	public void setBookingNewCustomersPureInfiltration(Integer bookingNewCustomersPureInfiltration) 
	{
		this.bookingNewCustomersPureInfiltration = bookingNewCustomersPureInfiltration;
	}

	public Integer getBookingNewCustomersPureInfiltration() 
	{
		return bookingNewCustomersPureInfiltration;
	}
	public void setOrderingOrders(Integer orderingOrders) 
	{
		this.orderingOrders = orderingOrders;
	}

	public Integer getOrderingOrders() 
	{
		return orderingOrders;
	}
	public void setReceivingAmount(Double receivingAmount) 
	{
		this.receivingAmount = receivingAmount;
	}

	public Double getReceivingAmount() 
	{
		return receivingAmount;
	}
	public void setPureOutOfTheLibrary(Integer pureOutOfTheLibrary) 
	{
		this.pureOutOfTheLibrary = pureOutOfTheLibrary;
	}

	public Integer getPureOutOfTheLibrary() 
	{
		return pureOutOfTheLibrary;
	}
	public void setOutboundNewCustomersPureInfiltration(Integer outboundNewCustomersPureInfiltration) 
	{
		this.outboundNewCustomersPureInfiltration = outboundNewCustomersPureInfiltration;
	}

	public Integer getOutboundNewCustomersPureInfiltration() 
	{
		return outboundNewCustomersPureInfiltration;
	}
	public void setOutboundOrderNumber(Integer outboundOrderNumber) 
	{
		this.outboundOrderNumber = outboundOrderNumber;
	}

	public Integer getOutboundOrderNumber() 
	{
		return outboundOrderNumber;
	}
	public void setOutboundAmount(Double outboundAmount) 
	{
		this.outboundAmount = outboundAmount;
	}

	public Double getOutboundAmount() 
	{
		return outboundAmount;
	}
	public void setIdentification(String identification) 
	{
		this.identification = identification;
	}

	public String getIdentification() 
	{
		return identification;
	}
	public void setDis(String dis) 
	{
		this.dis = dis;
	}

	public String getDis() 
	{
		return dis;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ddDate", getDdDate())
            .append("channelId", getChannelId())
            .append("uv", getUv())
            .append("addActiveUser", getAddActiveUser())
            .append("coollectingPureNewCustomers", getCoollectingPureNewCustomers())
            .append("bookingNewCustomersPureInfiltration", getBookingNewCustomersPureInfiltration())
            .append("orderingOrders", getOrderingOrders())
            .append("receivingAmount", getReceivingAmount())
            .append("pureOutOfTheLibrary", getPureOutOfTheLibrary())
            .append("outboundNewCustomersPureInfiltration", getOutboundNewCustomersPureInfiltration())
            .append("outboundOrderNumber", getOutboundOrderNumber())
            .append("outboundAmount", getOutboundAmount())
            .append("identification", getIdentification())
            .append("dis", getDis())
            .toString();
    }
}
