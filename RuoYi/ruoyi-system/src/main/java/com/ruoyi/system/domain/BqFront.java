package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 北汽前端表 bq_front
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
public class BqFront extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;
	/** 账户 */
	@Excel(name = "账户")
	private String account;
	/** 日期 */
	@Excel(name = "日期")
	private Date bqDate;
	/** 推广计划 */
	@Excel(name = "推广计划")
	private String plan;
	/** 推广单元 */
	@Excel(name = "推广单元")
	private String unit;
	/** 关键词 */
	@Excel(name = "关键词")
	private String keyword;
	/** 展现 */
	@Excel(name = "展现")
	private Integer showdata;
	/** 点击 */
	@Excel(name = "点击")
	private Integer click;
	/** 消费 */
	@Excel(name = "消费")
	private Double cost;
	
	private String groupword;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setAccount(String account) 
	{
		this.account = account;
	}

	public String getAccount() 
	{
		return account;
	}
	public void setBqDate(Date bqDate) 
	{
		this.bqDate = bqDate;
	}

	public Date getBqDate() 
	{
		return bqDate;
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
	public void setShowdata(Integer showdata) 
	{
		this.showdata = showdata;
	}

	public Integer getShowdata() 
	{
		return showdata;
	}
	public void setClick(Integer click) 
	{
		this.click = click;
	}

	public Integer getClick() 
	{
		return click;
	}
	public void setCost(Double cost) 
	{
		this.cost = cost;
	}

	public Double getCost() 
	{
		return cost;
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
            .append("account", getAccount())
            .append("bqDate", getBqDate())
            .append("plan", getPlan())
            .append("unit", getUnit())
            .append("keyword", getKeyword())
            .append("show", getShowdata())
            .append("click", getClick())
            .append("cost", getCost())
            .append("groupword", getGroupword())
            .toString();
    }
}
