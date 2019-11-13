package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 宝沃前端表 bw_front
 * 
 * @author ruoyi
 * @date 2019-07-09
 */
public class BwFront extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 账户 */
	@Excel(name = "账户")
	private String account;
	/** 日期 */
	@Excel(name = "日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date bpDate;
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
	/** 点击率 */
	@Excel(name = "点击率")
	private Double clickRate;
	/** 平均点击价格 */
	@Excel(name = "平均点击价格")
	private Double averageClickPrice;
	/** 首页平均排名*/
	@Excel(name = "首页平均排名")
	private Double homepageAverageRanking;
	/** 出价 */
	@Excel(name = "出价")
	private Double bid;
	/** 匹配模式 */
	@Excel(name = "匹配模式")
	private String matchingMode;

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
	public void setBpDate(Date bpDate) 
	{
		this.bpDate = bpDate;
	}

	public Date getBpDate() 
	{
		return bpDate;
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
	public void setClickRate(Double clickRate) 
	{
		this.clickRate = clickRate;
	}

	public Double getClickRate() 
	{
		return clickRate;
	}
	public void setAverageClickPrice(Double averageClickPrice) 
	{
		this.averageClickPrice = averageClickPrice;
	}

	public Double getAverageClickPrice() 
	{
		return averageClickPrice;
	}
	public void setHomepageAverageRanking(Double homepageAverageRanking) 
	{
		this.homepageAverageRanking = homepageAverageRanking;
	}

	public Double getHomepageAverageRanking() 
	{
		return homepageAverageRanking;
	}
	public void setBid(Double bid) 
	{
		this.bid = bid;
	}

	public Double getBid() 
	{
		return bid;
	}
	public void setMatchingMode(String matchingMode) 
	{
		this.matchingMode = matchingMode;
	}

	public String getMatchingMode() 
	{
		return matchingMode;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("account", getAccount())
            .append("bpDate", getBpDate())
            .append("plan", getPlan())
            .append("unit", getUnit())
            .append("keyword", getKeyword())
            .append("showdata", getShowdata())
            .append("click", getClick())
            .append("cost", getCost())
            .append("clickRate", getClickRate())
            .append("averageClickPrice", getAverageClickPrice())
            .append("homepageAverageRanking", getHomepageAverageRanking())
            .append("bid", getBid())
            .append("matchingMode", getMatchingMode())
            .toString();
    }
}
