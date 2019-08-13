package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 资产分配记录表 xz_asset_hand_record
 * 
 * @author ruoyi
 * @date 2019-08-07
 */
public class XzAssetHandRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 资产ID */
	private String assetId;
	/** 发人分ID */
	private String distributor;
	/** 接收人ID */
	private String recipient;
	/** 分发资产日期 */
	private Date distributionDate;
	/** 认确领用日期 */
	private Date confirmDate;
	/** 计划归还日期 */
	private Date planReturnDate;
	/** 是否认领 */
	private String isClaim;
	/** 否是分配 */
	private String isMsg;
	/** 分配类型 */
	private String sourceType;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setAssetId(String assetId) 
	{
		this.assetId = assetId;
	}

	public String getAssetId() 
	{
		return assetId;
	}
	public void setDistributor(String distributor) 
	{
		this.distributor = distributor;
	}

	public String getDistributor() 
	{
		return distributor;
	}
	public void setRecipient(String recipient) 
	{
		this.recipient = recipient;
	}

	public String getRecipient() 
	{
		return recipient;
	}
	public void setDistributionDate(Date distributionDate) 
	{
		this.distributionDate = distributionDate;
	}

	public Date getDistributionDate() 
	{
		return distributionDate;
	}
	public void setConfirmDate(Date confirmDate) 
	{
		this.confirmDate = confirmDate;
	}

	public Date getConfirmDate() 
	{
		return confirmDate;
	}
	public void setPlanReturnDate(Date planReturnDate) 
	{
		this.planReturnDate = planReturnDate;
	}

	public Date getPlanReturnDate() 
	{
		return planReturnDate;
	}
	public void setIsClaim(String isClaim) 
	{
		this.isClaim = isClaim;
	}

	public String getIsClaim() 
	{
		return isClaim;
	}
	public void setIsMsg(String isMsg) 
	{
		this.isMsg = isMsg;
	}

	public String getIsMsg() 
	{
		return isMsg;
	}
	public void setSourceType(String sourceType) 
	{
		this.sourceType = sourceType;
	}

	public String getSourceType() 
	{
		return sourceType;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("assetId", getAssetId())
            .append("distributor", getDistributor())
            .append("recipient", getRecipient())
            .append("distributionDate", getDistributionDate())
            .append("confirmDate", getConfirmDate())
            .append("planReturnDate", getPlanReturnDate())
            .append("isClaim", getIsClaim())
            .append("isMsg", getIsMsg())
            .append("sourceType", getSourceType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
