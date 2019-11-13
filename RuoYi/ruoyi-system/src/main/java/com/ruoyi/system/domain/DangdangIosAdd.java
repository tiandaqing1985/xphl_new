package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 当当ios后端数据表 dangdang_ios_add
 * 
 * @author ruoyi
 * @date 2019-07-15
 */
public class DangdangIosAdd extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	/** 日期 */
	@Excel(name="日期",dateFormat = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date ddDate;
	/** ID */
	private Integer id;
	/** 新增激活 */
	@Excel(name="新增激活")
	private Integer newActive;
	/** 历史收订订单数 */
	@Excel(name="历史收订订单数")
	private Integer historyReceiveOrderNum;

	/** 历史收订新客数 */
	@Excel(name="历史收订新客数")
	private Integer historyReceiveNewCusNum;
	/** 历史收订金额 */
	@Excel(name="历史收订金额")
	private Double historyReceiveAmount;
	/** 历史出库订单数 */
	@Excel(name="历史出库订单数")
	private Integer historyOutLibraryOrders;
	/** 历史出库新客数 */
	@Excel(name = "历史出库新客数")
	private Integer historyOutLibraryCusNum;
	/** 历史出库金额 */
	@Excel(name="历史出库金额")
	private Double historyOutLibraryCusAmount;
	/** 标识 */
	private String identification;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setNewActive(Integer newActive) 
	{
		this.newActive = newActive;
	}

	public Integer getNewActive() 
	{
		return newActive;
	}
	public void setHistoryReceiveOrderNum(Integer historyReceiveOrderNum) 
	{
		this.historyReceiveOrderNum = historyReceiveOrderNum;
	}

	public Integer getHistoryReceiveOrderNum() 
	{
		return historyReceiveOrderNum;
	}
	public void setDdDate(Date ddDate) 
	{
		this.ddDate = ddDate;
	}

	public Date getDdDate() 
	{
		return ddDate;
	}
	public void setHistoryReceiveNewCusNum(Integer historyReceiveNewCusNum) 
	{
		this.historyReceiveNewCusNum = historyReceiveNewCusNum;
	}

	public Integer getHistoryReceiveNewCusNum() 
	{
		return historyReceiveNewCusNum;
	}
	public void setHistoryReceiveAmount(Double historyReceiveAmount) 
	{
		this.historyReceiveAmount = historyReceiveAmount;
	}

	public Double getHistoryReceiveAmount() 
	{
		return historyReceiveAmount;
	}
	public void setHistoryOutLibraryOrders(Integer historyOutLibraryOrders) 
	{
		this.historyOutLibraryOrders = historyOutLibraryOrders;
	}

	public Integer getHistoryOutLibraryOrders() 
	{
		return historyOutLibraryOrders;
	}
	public void setHistoryOutLibraryCusNum(Integer historyOutLibraryCusNum) 
	{
		this.historyOutLibraryCusNum = historyOutLibraryCusNum;
	}

	public Integer getHistoryOutLibraryCusNum() 
	{
		return historyOutLibraryCusNum;
	}
	public void setHistoryOutLibraryCusAmount(Double historyOutLibraryCusAmount) 
	{
		this.historyOutLibraryCusAmount = historyOutLibraryCusAmount;
	}

	public Double getHistoryOutLibraryCusAmount() 
	{
		return historyOutLibraryCusAmount;
	}
	public void setIdentification(String identification) 
	{
		this.identification = identification;
	}

	public String getIdentification() 
	{
		return identification;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("newActive", getNewActive())
            .append("historyReceiveOrderNum", getHistoryReceiveOrderNum())
            .append("ddDate", getDdDate())
            .append("historyReceiveNewCusNum", getHistoryReceiveNewCusNum())
            .append("historyReceiveAmount", getHistoryReceiveAmount())
            .append("historyOutLibraryOrders", getHistoryOutLibraryOrders())
            .append("historyOutLibraryCusNum", getHistoryOutLibraryCusNum())
            .append("historyOutLibraryCusAmount", getHistoryOutLibraryCusAmount())
            .append("identification", getIdentification())
            .toString();
    }
}
