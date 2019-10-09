package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
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
	/** 申请id */
	private Long applyId;
	/** 资产ID */
	@Excel(name = "资产id", prompt = "资产id")
	private Long assetId;
	/** 资产名称 */
	@Excel(name = "资产名称", prompt = "资产名称")
	private String assetName;
	/** 发人分ID */
	private Long distributor;
	/** 发人分名称 */
	@Excel(name = "分配人", prompt = "分配人")
	private String distributorName;
	@Excel(name = "审批状态", prompt = "审批状态")
	private String distributorStatus;
	/** 取消分配原因*/
	private String reason;
	/** 接收人ID */
	private Long recipient;
	/** 接收人名称 */
	@Excel(name = "使用人", prompt = "使用人")
	private String recipientName;
	/** 使用部门 */
	@Excel(name = "使用部门", prompt = "使用部门")
	private String departmentName;
	/** 分发资产日期 */
	@Excel(name = "资产分发时间", prompt = "资产分发时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
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
	/** 分配数量*/
	private int count;
	/** 资产子类型id*/
	private Long assetType2Id;
	/** 地域*/
	private String region;
	

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Long getAssetType2Id() {
		return assetType2Id;
	}

	public void setAssetType2Id(Long assetType2Id) {
		this.assetType2Id = assetType2Id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

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
	public void setDistributor(Long distributor) 
	{
		this.distributor = distributor;
	}

	public Long getDistributor() 
	{
		return distributor;
	}
	public void setRecipient(Long recipient) 
	{
		this.recipient = recipient;
	}

	public Long getRecipient() 
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
	
	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getDistributorStatus() {
		return distributorStatus;
	}

	public void setDistributorStatus(String distributorStatus) {
		this.distributorStatus = distributorStatus;
	}


	@Override
	public String toString() {
		return "XzAssetHandRecord [id=" + id + ", applyId=" + applyId + ", assetId=" + assetId + ", assetName="
				+ assetName + ", distributor=" + distributor + ", distributorName=" + distributorName
				+ ", distributorStatus=" + distributorStatus + ", reason=" + reason + ", recipient=" + recipient
				+ ", recipientName=" + recipientName + ", departmentName=" + departmentName + ", distributionDate="
				+ distributionDate + ", confirmDate=" + confirmDate + ", planReturnDate=" + planReturnDate
				+ ", isClaim=" + isClaim + ", isMsg=" + isMsg + ", sourceType=" + sourceType + ", count=" + count
				+ ", assetType2Id=" + assetType2Id + ", region=" + region + "]";
	}
}