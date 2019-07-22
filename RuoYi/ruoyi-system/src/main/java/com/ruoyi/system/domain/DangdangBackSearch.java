package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 当当pc端搜索广告后端表 dangdang_back_search
 * 
 * @author ruoyi
 * @date 2019-07-21
 */
public class DangdangBackSearch extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 账户 */
	@Excel(name="账户")
	private String account;
	/** 计划 */
	@Excel(name="计划")
	private String plan;
	/** 关键词组 */
	@Excel(name="关键词组")
	private String unit;
	/** 关键词 */
	@Excel(name="关键词")
	private String keyword;
	/** UV */
	@Excel(name="UV")
	private Integer uv;
	/** 组合 */
	private String combination;
	/** 日期 */
	@Excel(name="日期" ,dateFormat = "yyyy-mm-dd")
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date ddDate;

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
	public void setUv(Integer uv) 
	{
		this.uv = uv;
	}

	public Integer getUv() 
	{
		return uv;
	}

	public String getCombination() {
		return combination;
	}

	public void setCombination(String combination) {
		this.combination = combination;
	}

	public void setDdDate(Date ddDate)
	{
		this.ddDate = ddDate;
	}

	public Date getDdDate() 
	{
		return ddDate;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("account", getAccount())
            .append("plan", getPlan())
            .append("unit", getUnit())
            .append("keyword", getKeyword())
            .append("uv", getUv())
            .append("combination", getCombination())
            .append("ddDate", getDdDate())
            .toString();
    }
}
