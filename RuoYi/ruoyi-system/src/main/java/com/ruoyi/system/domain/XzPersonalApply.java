package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 个人申请表 xz_personal_apply
 * 
 * @author ruoyi
 * @date 2019-08-27
 */
public class XzPersonalApply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 唯一标识 */
	private Long applyId;
	/** 申请状态 */
	private String applyStatus;
	/** 申请人id */
	private Long userId;
	/** 申请类型（1资产申请，2归还申请） */
	private String applyType;
	/** 父资产类型 */
	private Long assetTypeId;
	/** 父资产类型 */
	private String assetTypeName;
	/** 子资产类型 */
	private Long assetType2Id;
	/** 子资产类型 */
	private String assetType2Name;
	/** 资产型号 */
	private String assetModel;
	/** 地域 */
	private String region;
	/** 数量 */
	private Integer count;
	/** 单位 */
	private String unit;
	/** 用途 */
	private String purpose;
	/** 备注 */
	private String remarks;
	/** 预计使用时间 */
	private Date useDate;
	/** 提交状态（1保存，2提交） */
	private String submitType;
	/** 申请资产分类 */
	private String sort;
	/** 申请人名称 */
	private String createByName;
	

	public void setApplyId(Long applyId) 
	{
		this.applyId = applyId;
	}

	public Long getApplyId() 
	{
		return applyId;
	}
	public void setApplyStatus(String applyStatus) 
	{
		this.applyStatus = applyStatus;
	}

	public String getApplyStatus() 
	{
		return applyStatus;
	}
	public void setApplyType(String applyType) 
	{
		this.applyType = applyType;
	}

	public String getApplyType() 
	{
		return applyType;
	}
	public void setAssetTypeId(Long assetTypeId) 
	{
		this.assetTypeId = assetTypeId;
	}

	public Long getAssetTypeId() 
	{
		return assetTypeId;
	}
	public void setAssetType2Id(Long assetType2Id) 
	{
		this.assetType2Id = assetType2Id;
	}

	public Long getAssetType2Id() 
	{
		return assetType2Id;
	}
	public void setAssetModel(String assetModel) 
	{
		this.assetModel = assetModel;
	}

	public String getAssetModel() 
	{
		return assetModel;
	}
	public void setRegion(String region) 
	{
		this.region = region;
	}

	public String getRegion() 
	{
		return region;
	}
	public void setCount(Integer count) 
	{
		this.count = count;
	}

	public Integer getCount() 
	{
		return count;
	}
	public void setUnit(String unit) 
	{
		this.unit = unit;
	}

	public String getUnit() 
	{
		return unit;
	}
	public void setPurpose(String purpose) 
	{
		this.purpose = purpose;
	}

	public String getPurpose() 
	{
		return purpose;
	}
	public void setRemarks(String remarks) 
	{
		this.remarks = remarks;
	}

	public String getRemarks() 
	{
		return remarks;
	}
	public void setUseDate(Date useDate) 
	{
		this.useDate = useDate;
	}

	public Date getUseDate() 
	{
		return useDate;
	}
	public void setSubmitType(String submitType) 
	{
		this.submitType = submitType;
	}

	public String getSubmitType() 
	{
		return submitType;
	}
	public void setSort(String sort) 
	{
		this.sort = sort;
	}

	public String getSort() 
	{
		return sort;
	}
	public String getAssetTypeName() {
		return assetTypeName;
	}

	public void setAssetTypeName(String assetTypeName) {
		this.assetTypeName = assetTypeName;
	}

	public String getAssetType2Name() {
		return assetType2Name;
	}

	public void setAssetType2Name(String assetType2Name) {
		this.assetType2Name = assetType2Name;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	 @Override
	public String toString() {
		return "XzPersonalApply [applyId=" + applyId + ", applyStatus=" + applyStatus + ", userId=" + userId
				+ ", applyType=" + applyType + ", assetTypeId=" + assetTypeId + ", assetTypeName=" + assetTypeName
				+ ", assetType2Id=" + assetType2Id + ", assetType2Name=" + assetType2Name + ", assetModel=" + assetModel
				+ ", region=" + region + ", count=" + count + ", unit=" + unit + ", purpose=" + purpose + ", remarks="
				+ remarks + ", useDate=" + useDate + ", submitType=" + submitType + ", sort=" + sort + ", createByName="
				+ createByName + "]";
	}
}
