package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 玖富前端表 jf_front
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
public class JfFront extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;
	/** 账户 */
	@Excel(name = "账户")
	private String account;
	/** 日期 */
	@Excel(name = "日期")
	private Date frontDate;
	/** 计划 */
	@Excel(name = "推广计划")
	private String plan;
	/** 单元 */
	@Excel(name = "推广单元")
	private String unit;
	/** 关键词 */
	@Excel(name = "关键词")
	private String keyword;
	/** 展示 */
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
            .append("frontDate", getFrontDate())
            .append("account", getAccount())
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
