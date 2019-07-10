package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 当当词性匹配表 dangdang_match
 * 
 * @author ruoyi
 * @date 2019-07-10
 */
public class DangdangMatch extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 账户名 */
	@Excel(name="账户名")
	private String account;
	/** 推广计划名称 */
	@Excel(name="推广计划名称")
	private String plan;
	/** 推广单元名称 */
	@Excel(name="推广单元名称")
	private String unit;
	/** 关键词名称 */
	@Excel(name="关键词名称")
	private String keyword;
	/** 词性 */
	@Excel(name="词性")
	private String partSpeech;
	/** 二级分类 */
	@Excel(name="二级类目")
	private String secondaryClassification;
	/** 三级分类 */
	@Excel(name="三级类目")
	private String threeLevelClassification;
	/** 组合 */
	private String combination;
	/** 移动访问url */
	@Excel(name="移动访问URL")
	private String mobileAccessUrl;
	/** 小程序url */
	@Excel(name="小程序URL")
	private String appletUrl;
	/** 小程序更改后 */
	@Excel(name="小程序更改后")
	private String appletDone;

	/**
	 * 备注
	 */
	@Excel(name="备注")
	private  String remarks;

	/**
	 * 渠道号
	 */
	@Excel(name="渠道号")
	private  String channelNumber;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getChannelNumber() {
		return channelNumber;
	}

	public void setChannelNumber(String channelNumber) {
		this.channelNumber = channelNumber;
	}

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
	public void setPartSpeech(String partSpeech) 
	{
		this.partSpeech = partSpeech;
	}

	public String getPartSpeech() 
	{
		return partSpeech;
	}
	public void setSecondaryClassification(String secondaryClassification) 
	{
		this.secondaryClassification = secondaryClassification;
	}

	public String getSecondaryClassification() 
	{
		return secondaryClassification;
	}
	public void setThreeLevelClassification(String threeLevelClassification) 
	{
		this.threeLevelClassification = threeLevelClassification;
	}

	public String getThreeLevelClassification() 
	{
		return threeLevelClassification;
	}
	public void setCombination(String combination) 
	{
		this.combination = combination;
	}

	public String getCombination() 
	{
		return combination;
	}
	public void setMobileAccessUrl(String mobileAccessUrl) 
	{
		this.mobileAccessUrl = mobileAccessUrl;
	}

	public String getMobileAccessUrl() 
	{
		return mobileAccessUrl;
	}
	public void setAppletUrl(String appletUrl) 
	{
		this.appletUrl = appletUrl;
	}

	public String getAppletUrl() 
	{
		return appletUrl;
	}
	public void setAppletDone(String appletDone) 
	{
		this.appletDone = appletDone;
	}

	public String getAppletDone() 
	{
		return appletDone;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("account", getAccount())
            .append("plan", getPlan())
            .append("unit", getUnit())
            .append("keyword", getKeyword())
            .append("partSpeech", getPartSpeech())
            .append("secondaryClassification", getSecondaryClassification())
            .append("threeLevelClassification", getThreeLevelClassification())
            .append("combination", getCombination())
            .append("mobileAccessUrl", getMobileAccessUrl())
            .append("appletUrl", getAppletUrl())
            .append("appletDone", getAppletDone())
            .toString();
    }
}
