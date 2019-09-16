package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 个人资产表 xz_personal_asset
 * 
 * @author ruoyi
 * @date 2019-08-27
 */
public class XzPersonalAsset extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 用户id */
	private Long userId;
	/** 产资id */
	private Long assetId;
	/** 产资名称 */
	private String assetName;
	/** 状态 */
	private String assetStatus;
	/** 产资父类型名称 */
	private String assetTypeName;
	/** 领用时间 */
	private Date useTime;
	
	private XzAsstes xzAsstes;
	public XzAsstes getXzAsstes() {
		return xzAsstes;
	}

	public void setXzAsstes(XzAsstes xzAsstes) {
		this.xzAsstes = xzAsstes;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}

	public Long getUserId() 
	{
		return userId;
	}
	public void setAssetId(Long assetId) 
	{
		this.assetId = assetId;
	}

	public Long getAssetId() 
	{
		return assetId;
	}
	public void setUseTime(Date useTime) 
	{
		this.useTime = useTime;
	}

	public Date getUseTime() 
	{
		return useTime;
	}
	
	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	
	public String getAssetTypeName() {
		return assetTypeName;
	}

	public void setAssetTypeName(String assetTypeName) {
		this.assetTypeName = assetTypeName;
	}

	public String getAssetStatus() {
		return assetStatus;
	}

	public void setAssetStatus(String assetStatus) {
		this.assetStatus = assetStatus;
	}

	@Override
	public String toString() {
		return "XzPersonalAsset [id=" + id + ", userId=" + userId + ", assetId=" + assetId + ", assetName=" + assetName
				+ ", assetStatus=" + assetStatus + ", assetTypeName=" + assetTypeName + ", useTime=" + useTime
				+ ", xzAsstes=" + xzAsstes + "]";
	}

	

    
}
