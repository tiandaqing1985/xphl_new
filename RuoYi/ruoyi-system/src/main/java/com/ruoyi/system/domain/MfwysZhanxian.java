package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 马蜂窝原生展现表 mfwys_zhanxian
 * 
 * @author ruoyi
 * @date 2019-07-05
 */
public class MfwysZhanxian extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;
	/** 日期 */
	private Date frontDate;
	/** 账户 */
	private String accountname;
	/** 推广计划 */
	private String plan;
	/** 推广计划ID */
	private Long planid;
	/** 推广单元 */
	private String unit;
	/** 推广单元ID */
	private Long unitid;
	/** 创意名称 */
	private String keyword;
	/** 创意ID */
	private Long keywordid;
	/** 标题 */
	private String title;
	/** 展现 */
	private Integer showdata;
	/** 点击 */
	private Integer click;
	/** 消费 */
	private Double consume;
	/** 点击率 */
	private Double clickRate;
	/**  */
	private Double clickAvg;
	/** 千次展现消费 */
	private Double thousandConsume;
	/** 组合 */
	private String groupword;
	/** 系统 */
	private String mfwSystem;
	/** 渠道包名 */
	private String channelPackage;
	/** 渠道 */
	private String channel;
	/** 新增设备数 */
	private Integer newFacility;
	/** 账户id */
	private String account;
	/** aid */
	private Long aid;
	/** 次日留存 */
	private Double keep;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setFrontDate(Date frontDate) 
	{
		this.frontDate = frontDate;
	}

	public Date getFrontDate() 
	{
		return frontDate;
	}
	public void setAccountname(String accountname) 
	{
		this.accountname = accountname;
	}

	public String getAccountname() 
	{
		return accountname;
	}
	public void setPlan(String plan) 
	{
		this.plan = plan;
	}

	public String getPlan() 
	{
		return plan;
	}
	public void setPlanid(Long planid) 
	{
		this.planid = planid;
	}

	public Long getPlanid() 
	{
		return planid;
	}
	public void setUnit(String unit) 
	{
		this.unit = unit;
	}

	public String getUnit() 
	{
		return unit;
	}
	public void setUnitid(Long unitid) 
	{
		this.unitid = unitid;
	}

	public Long getUnitid() 
	{
		return unitid;
	}
	public void setKeyword(String keyword) 
	{
		this.keyword = keyword;
	}

	public String getKeyword() 
	{
		return keyword;
	}
	public void setKeywordid(Long keywordid) 
	{
		this.keywordid = keywordid;
	}

	public Long getKeywordid() 
	{
		return keywordid;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getTitle() 
	{
		return title;
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
	public void setClickRate(Double clickRate) 
	{
		this.clickRate = clickRate;
	}

	public Double getClickRate() 
	{
		return clickRate;
	}
	public void setClickAvg(Double clickAvg) 
	{
		this.clickAvg = clickAvg;
	}

	public Double getClickAvg() 
	{
		return clickAvg;
	}
	public void setThousandConsume(Double thousandConsume) 
	{
		this.thousandConsume = thousandConsume;
	}

	public Double getThousandConsume() 
	{
		return thousandConsume;
	}
	public void setGroupword(String groupword) 
	{
		this.groupword = groupword;
	}

	public String getGroupword() 
	{
		return groupword;
	}
	public void setMfwSystem(String mfwSystem) 
	{
		this.mfwSystem = mfwSystem;
	}

	public String getMfwSystem() 
	{
		return mfwSystem;
	}
	public void setChannelPackage(String channelPackage) 
	{
		this.channelPackage = channelPackage;
	}

	public String getChannelPackage() 
	{
		return channelPackage;
	}
	public void setChannel(String channel) 
	{
		this.channel = channel;
	}

	public String getChannel() 
	{
		return channel;
	}
	public void setNewFacility(Integer newFacility) 
	{
		this.newFacility = newFacility;
	}

	public Integer getNewFacility() 
	{
		return newFacility;
	}
	public void setAccount(String account) 
	{
		this.account = account;
	}

	public String getAccount() 
	{
		return account;
	}
	public void setAid(Long aid) 
	{
		this.aid = aid;
	}

	public Long getAid() 
	{
		return aid;
	}
	public void setKeep(Double keep) 
	{
		this.keep = keep;
	}

	public Double getKeep() 
	{
		return keep;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("frontDate", getFrontDate())
            .append("accountname", getAccountname())
            .append("plan", getPlan())
            .append("planid", getPlanid())
            .append("unit", getUnit())
            .append("unitid", getUnitid())
            .append("keyword", getKeyword())
            .append("keywordid", getKeywordid())
            .append("title", getTitle())
            .append("showdata", getShowdata())
            .append("click", getClick())
            .append("consume", getConsume())
            .append("clickRate", getClickRate())
            .append("clickAvg", getClickAvg())
            .append("thousandConsume", getThousandConsume())
            .append("groupword", getGroupword())
            .append("mfwSystem", getMfwSystem())
            .append("channelPackage", getChannelPackage())
            .append("channel", getChannel())
            .append("newFacility", getNewFacility())
            .append("account", getAccount())
            .append("aid", getAid())
            .append("keep", getKeep())
            .toString();
    }
}
