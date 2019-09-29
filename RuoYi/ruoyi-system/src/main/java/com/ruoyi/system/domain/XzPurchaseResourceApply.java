package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 * 采购资源申请表 xz_purchase_resource_apply
 * 
 * @author ruoyi
 * @date 2019-09-17
 */
public class XzPurchaseResourceApply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 采购单号 */
	private String code;
	/** 资源ids */
	private String applyId;
	private String applyName;
	/** 申请人 */
	private Long applyUserId;
	private String applyUserName;
	/** 采购人 */
	private Long purchaseUserId;
	/** 申请金额 */
	private Double applyPrice;
	/** 实际金额 */
	private Double actualPrice;
	/** 浮动金额 */
	private Double floatPrice;
	/** 提交时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date subTime;
	/** 提交状态 */
	private String subType;
	/** 审批时间 */
	private Date approvalTime;
	/** 审批状态 */
	private String approvalStatus;
	/** 是否关联 */
	private String isRelation;
	/** 采购用途 */
	private String purpose;
	/** 采购物资列表*/
	private List<XzPurchaseResource> xzPurchaseResource;
	private XzPurchaseApproval xzPurchaseApproval;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setCode(String code) 
	{
		this.code = code;
	}

	public String getCode() 
	{
		return code;
	}
	public void setApplyId(String applyId) 
	{
		this.applyId = applyId;
	}

	public String getApplyId() 
	{
		return applyId;
	}
	public void setApplyUserId(Long applyUserId) 
	{
		this.applyUserId = applyUserId;
	}

	public Long getApplyUserId() 
	{
		return applyUserId;
	}
	public void setPurchaseUserId(Long purchaseUserId) 
	{
		this.purchaseUserId = purchaseUserId;
	}

	public Long getPurchaseUserId() 
	{
		return purchaseUserId;
	}
	public void setApplyPrice(Double applyPrice) 
	{
		this.applyPrice = applyPrice;
	}

	public Double getApplyPrice() 
	{
		return applyPrice;
	}
	public void setActualPrice(Double actualPrice) 
	{
		this.actualPrice = actualPrice;
	}

	public Double getActualPrice() 
	{
		return actualPrice;
	}
	public void setFloatPrice(Double floatPrice) 
	{
		this.floatPrice = floatPrice;
	}

	public Double getFloatPrice() 
	{
		return floatPrice;
	}
	public void setSubTime(Date subTime) 
	{
		this.subTime = subTime;
	}

	public Date getSubTime() 
	{
		return subTime;
	}
	public void setSubType(String subType) 
	{
		this.subType = subType;
	}

	public String getSubType() 
	{
		return subType;
	}
	public void setApprovalTime(Date approvalTime) 
	{
		this.approvalTime = approvalTime;
	}

	public Date getApprovalTime() 
	{
		return approvalTime;
	}
	public void setApprovalStatus(String approvalStatus) 
	{
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalStatus() 
	{
		return approvalStatus;
	}
	public void setIsRelation(String isRelation) 
	{
		this.isRelation = isRelation;
	}

	public String getIsRelation() 
	{
		return isRelation;
	}
   
	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	
	public List<XzPurchaseResource> getXzPurchaseResource() {
		return xzPurchaseResource;
	}

	public void setXzPurchaseResource(List<XzPurchaseResource> xzPurchaseResource) {
		this.xzPurchaseResource = xzPurchaseResource;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public XzPurchaseApproval getXzPurchaseApproval() {
		return xzPurchaseApproval;
	}

	public void setXzPurchaseApproval(XzPurchaseApproval xzPurchaseApproval) {
		this.xzPurchaseApproval = xzPurchaseApproval;
	}

}
