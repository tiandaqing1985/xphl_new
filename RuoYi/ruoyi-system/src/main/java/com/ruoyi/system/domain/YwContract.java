package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 下单表 yw_contract
 * 
 * @author ruoyi
 * @date 2019-06-12
 */
public class YwContract extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 媒体ID */
	private Long businessId;
	/** 媒体 */
	private String media;
	/** 合同开始日期 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	/** 合同结束日期 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	/** 合同金额 */
	private Double contractMoney;
	
	/** 公司名称 */
	private String companyName;
	/** 广告主 */
	private String advertiser; 

	private String status;
	
	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setBusinessId(Long businessId) 
	{
		this.businessId = businessId;
	}

	public Long getBusinessId() 
	{
		return businessId;
	}
	public void setMedia(String media) 
	{
		this.media = media;
	}

	public String getMedia() 
	{
		return media;
	}
	public void setStartDate(Date startDate) 
	{
		this.startDate = startDate;
	}

	public Date getStartDate() 
	{
		return startDate;
	}
	public void setEndDate(Date endDate) 
	{
		this.endDate = endDate;
	}

	public Date getEndDate() 
	{
		return endDate;
	}
	public void setContractMoney(Double contractMoney) 
	{
		this.contractMoney = contractMoney;
	}

	public Double getContractMoney() 
	{
		return contractMoney;
	}
	

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(String advertiser) {
		this.advertiser = advertiser;
	}

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("businessId", getBusinessId())
            .append("media", getMedia())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("contractMoney", getContractMoney())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("companyName", getCompanyName())
            .append("advertiser", getAdvertiser())
            .toString();
    }
}
