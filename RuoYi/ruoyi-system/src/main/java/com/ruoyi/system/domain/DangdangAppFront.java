package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 当当APP前端表 dangdang_app_front
 * 
 * @author ruoyi
 * @date 2019-07-11
 */
public class DangdangAppFront extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID
 */
	private Integer id;
	/** 日期 */
	@Excel(name="日期")
	private Date date;
	/** 账户 */
	@Excel(name="账户")
	private String account;
	/** 推广计划 */
	@Excel(name="推广计划")
	private String plan;
	/** 推广单元 */
	@Excel(name="推广单元")
	private String unit;
	/** 关键词 */
	@Excel(name="关键词")
	private String keyword;
	/** 展现 */
	@Excel(name="展现")
	private Integer showdata;
	/** 点击 */
	@Excel(name="点击")
	private Integer click;
	/** 消费 */
	@Excel(name="消费")
	private Double cost;
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
	public void setDate(Date date) 
	{
		this.date = date;
	}

	public Date getDate() 
	{
		return date;
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
            .append("date", getDate())
            .append("account", getAccount())
            .append("plan", getPlan())
            .append("unit", getUnit())
            .append("keyword", getKeyword())
            .append("showdata", getShowdata())
            .append("click", getClick())
            .append("cost", getCost())
            .append("identification", getIdentification())
            .toString();
    }
}
