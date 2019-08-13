package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 资产台账记录表 xz_asset_account_record
 * 
 * @author ruoyi
 * @date 2019-08-08
 */
public class XzAssetAccountRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 资产ID */
	private Long assetId;
	/** 处理人 */
	private Long disposeUser;
	/** 处理时间 */
	private Date disposeDate;
	/** 处理前的设备状态 */
	private Integer currentStatus;
	/** 处理后的设备状态 */
	private Integer targetStatus;
	/** 备注 */
	private String comment;
	/**  */
	private Integer count;

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
	public void setDisposeUser(Long disposeUser) 
	{
		this.disposeUser = disposeUser;
	}

	public Long getDisposeUser() 
	{
		return disposeUser;
	}
	public void setDisposeDate(Date disposeDate) 
	{
		this.disposeDate = disposeDate;
	}

	public Date getDisposeDate() 
	{
		return disposeDate;
	}
	public void setCurrentStatus(Integer currentStatus) 
	{
		this.currentStatus = currentStatus;
	}

	public Integer getCurrentStatus() 
	{
		return currentStatus;
	}
	public void setTargetStatus(Integer targetStatus) 
	{
		this.targetStatus = targetStatus;
	}

	public Integer getTargetStatus() 
	{
		return targetStatus;
	}
	public void setComment(String comment) 
	{
		this.comment = comment;
	}

	public String getComment() 
	{
		return comment;
	}
	public void setCount(Integer count) 
	{
		this.count = count;
	}

	public Integer getCount() 
	{
		return count;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("assetId", getAssetId())
            .append("disposeUser", getDisposeUser())
            .append("disposeDate", getDisposeDate())
            .append("currentStatus", getCurrentStatus())
            .append("targetStatus", getTargetStatus())
            .append("comment", getComment())
            .append("count", getCount())
            .toString();
    }
}
