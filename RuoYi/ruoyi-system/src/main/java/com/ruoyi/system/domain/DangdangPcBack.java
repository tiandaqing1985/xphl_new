package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 当当pc后端百度数据明细表 dangdang_pc_back
 * 
 * @author ruoyi
 * @date 2019-07-19
 */
public class DangdangPcBack extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 日期 */
	@Excel(name="日期" ,dateFormat = "yyyy-MM-dd")
	private Date ddDate;
	/** 来源明细 */
	@Excel(name="来源明细")
	private String source;
	/** 收订订单数 */
	@Excel(name="收订订单数")
	private Integer receiveOrders;
	/** 收订金额 */
	@Excel(name="收订金额")
	private Double receiveAmount;
	/** 收订新账户数 */
	@Excel(name="收订新账户数")
	private Integer receiveAccount;
	/** 出库订单数 */
	@Excel(name="出库订单数")
	private Integer outOrders;
	/** 出库金额 */
	@Excel(name="出库金额")
	private Double outAmount;
	/** 出库新客数 */
	@Excel(name="出库新客数")
	private Integer outNumber;

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
	public void setSource(String source) 
	{
		this.source = source;
	}

	public String getSource() 
	{
		return source;
	}
	public void setReceiveOrders(Integer receiveOrders) 
	{
		this.receiveOrders = receiveOrders;
	}

	public Integer getReceiveOrders() 
	{
		return receiveOrders;
	}
	public void setReceiveAmount(Double receiveAmount) 
	{
		this.receiveAmount = receiveAmount;
	}

	public Double getReceiveAmount() 
	{
		return receiveAmount;
	}
	public void setReceiveAccount(Integer receiveAccount) 
	{
		this.receiveAccount = receiveAccount;
	}

	public Integer getReceiveAccount() 
	{
		return receiveAccount;
	}
	public void setOutOrders(Integer outOrders) 
	{
		this.outOrders = outOrders;
	}

	public Integer getOutOrders() 
	{
		return outOrders;
	}
	public void setOutAmount(Double outAmount) 
	{
		this.outAmount = outAmount;
	}

	public Double getOutAmount() 
	{
		return outAmount;
	}
	public void setOutNumber(Integer outNumber) 
	{
		this.outNumber = outNumber;
	}

	public Integer getOutNumber() 
	{
		return outNumber;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ddDate", getDdDate())
            .append("source", getSource())
            .append("receiveOrders", getReceiveOrders())
            .append("receiveAmount", getReceiveAmount())
            .append("receiveAccount", getReceiveAccount())
            .append("outOrders", getOutOrders())
            .append("outAmount", getOutAmount())
            .append("outNumber", getOutNumber())
            .toString();
    }
}
