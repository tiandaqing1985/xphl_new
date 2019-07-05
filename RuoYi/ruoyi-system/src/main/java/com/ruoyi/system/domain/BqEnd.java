package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 北汽后端表 bq_end
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
public class BqEnd extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;
	/** 日期 */
	@Excel(name = "日" , dateFormat = "yyyyMMdd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String bqDate;
	/** 账户 */
	@Excel(name = "账户")
	private String account;
	/** 计划 */
	@Excel(name = "计划")
	private String plan;
	/** 单元 */
	@Excel(name = "单元")
	private String unit;
	/** 关键词 */
	@Excel(name = "关键词")
	private String keyword;
	/** 监测点击量 */
	@Excel(name = "监测点击量")
	private Integer click;
	/** 订单量 */
	@Excel(name = "订单量")
	private Integer orderCount;

	private String groupword;
	
	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setBqDate(String bqDate) 
	{
		this.bqDate = bqDate;
	}

	public String getBqDate() 
	{
		return bqDate;
	}
	public void setAccount(String account) 
	{
		this.account = account;
	}

	public String getAccount() 
	{
		return account;
	}
	public void setPlan(String plan) 
	{
		this.plan = plan;
	}

	public String getPlan() 
	{
		return plan;
	}
	public void setUnit(String unit) 
	{
		this.unit = unit;
	}

	public String getUnit() 
	{
		return unit;
	}
	public void setKeyword(String keyword) 
	{
		this.keyword = keyword;
	}

	public String getKeyword() 
	{
		return keyword;
	}
	public void setClick(Integer click) 
	{
		this.click = click;
	}

	public Integer getClick() 
	{
		return click;
	}
    public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public String getGroupword() {
		return groupword;
	}

	public void setGroupword(String groupword) {
		this.groupword = groupword;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bqDate", getBqDate())
            .append("account", getAccount())
            .append("plan", getPlan())
            .append("unit", getUnit())
            .append("keyword", getKeyword())
            .append("click", getClick())
            .append("order", getOrderCount())
            .append("groupword", getGroupword())
            .toString();
    }
}
