package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 资产扩展表 xz_asset_extend
 * 
 * @author ruoyi
 * @date 2020-03-27
 */
public class XzAssetExtend extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 资产id */
	private Long assetId;
	/** 扩展内容 */
	private String extendDesc;
	/** 扩展金额 */
	private Float extendPrice;
	/** 扩展日期 */
	private Date extendDate;
	/** 更新时间 */
	private Date lastUpdateTime;
	/** 展示字段 */
	private String extendDateFormat;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setAssetId(Long assetId) 
	{
		this.assetId = assetId;
	}

	public Long getAssetId() 
	{
		return assetId;
	}
	public void setExtendDesc(String extendDesc) 
	{
		this.extendDesc = extendDesc;
	}

	public String getExtendDesc() 
	{
		return extendDesc;
	}
	public void setExtendPrice(Float extendPrice) 
	{
		this.extendPrice = extendPrice;
	}

	public Float getExtendPrice() 
	{
		return extendPrice;
	}
	public void setExtendDate(Date extendDate) 
	{
		this.extendDate = extendDate;
	}

	public Date getExtendDate() 
	{
		return extendDate;
	}
	public void setLastUpdateTime(Date lastUpdateTime) 
	{
		this.lastUpdateTime = lastUpdateTime;
	}

	public Date getLastUpdateTime() 
	{
		return lastUpdateTime;
	}
	public void setExtendDateFormat(String extendDateFormat) 
	{
		this.extendDateFormat = extendDateFormat;
	}

	public String getExtendDateFormat() 
	{
		return extendDateFormat;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("assetId", getAssetId())
            .append("extendDesc", getExtendDesc())
            .append("extendPrice", getExtendPrice())
            .append("extendDate", getExtendDate())
            .append("lastUpdateTime", getLastUpdateTime())
            .append("extendDateFormat", getExtendDateFormat())
            .toString();
    }
}
