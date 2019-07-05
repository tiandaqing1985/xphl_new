package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 瑞幸前端表 rx_front
 * 
 * @author ruoyi
 * @date 2019-07-05
 */
public class RxFront extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 账户 */
	@Excel(name = "账户")
	private String account;
	/** 日期 */
	
	@Excel(name = "日期",dateFormat = "yyyy/MM/dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date rxDate;
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
	private Double consume;
	/** 组合 */
	private String groupword;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
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
	public void setRxDate(Date rxDate) 
	{
		this.rxDate = rxDate;
	}

	public Date getRxDate() 
	{
		return rxDate;
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
	public void setConsume(Double consume) 
	{
		this.consume = consume;
	}

	public Double getConsume() 
	{
		return consume;
	}
	public void setGroupword(String groupword) 
	{
		this.groupword = groupword;
	}

	public String getGroupword() 
	{
		return groupword;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("account", getAccount())
            .append("rxDate", getRxDate())
            .append("plan", getPlan())
            .append("unit", getUnit())
            .append("keyword", getKeyword())
            .append("showdata", getShowdata())
            .append("click", getClick())
            .append("consume", getConsume())
            .append("groupword", getGroupword())
            .toString();
    }
}
