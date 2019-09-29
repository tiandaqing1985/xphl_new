package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 费用记录表 xz_expense_record
 * 
 * @author ruoyi
 * @date 2019-09-23
 */
public class XzExpenseRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 费用名称 */
	private String expenseName;
	/** 费用父类型 */
	private Long expenseTypeParent;
	/** 费用类型 */
	private Long expenseType;
	private String expenseTypeName;
	/** 地区 */
	private String region;
	/** 物品规格 */
	private String devDesc;
	/** 购买渠道 */
	private String purchaseChannel;
	/** 单价 */
	private Double unitPrice;
	/** 数量 */
	private Integer count;
	/** 保存日期 */
	private Date markDate;
	/** 使用日期 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date useDate;
	/** 添加人 */
	private String markUser;
	/** 申请人 */
	private String applyUser;
	private String applyUserName;
	/** 备注 */
	private String comment;
	/** 费用提交方式（1是保存，2是提交） */
	private String submitType;
	/** 活动地址 */
	private String place;
	/** 结束日期 */
	private Date endDate;
	/** 开始日期 */
	private Date startDate;
	/** 活动名称 */
	private String activeName;
	/** 总金额 */
	private Double sumPrice;
	/** 关联费用时资源的id */
	private String resourceId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getExpenseName() {
		return expenseName;
	}
	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}
	public Long getExpenseTypeParent() {
		return expenseTypeParent;
	}
	public void setExpenseTypeParent(Long expenseTypeParent) {
		this.expenseTypeParent = expenseTypeParent;
	}
	public Long getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(Long expenseType) {
		this.expenseType = expenseType;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getDevDesc() {
		return devDesc;
	}
	public void setDevDesc(String devDesc) {
		this.devDesc = devDesc;
	}
	public String getPurchaseChannel() {
		return purchaseChannel;
	}
	public void setPurchaseChannel(String purchaseChannel) {
		this.purchaseChannel = purchaseChannel;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Date getMarkDate() {
		return markDate;
	}
	public void setMarkDate(Date markDate) {
		this.markDate = markDate;
	}
	public Date getUseDate() {
		return useDate;
	}
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	public String getMarkUser() {
		return markUser;
	}
	public void setMarkUser(String markUser) {
		this.markUser = markUser;
	}
	public String getApplyUser() {
		return applyUser;
	}
	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getSubmitType() {
		return submitType;
	}
	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getActiveName() {
		return activeName;
	}
	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}
	public Double getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getExpenseTypeName() {
		return expenseTypeName;
	}
	public void setExpenseTypeName(String expenseTypeName) {
		this.expenseTypeName = expenseTypeName;
	}
	public String getApplyUserName() {
		return applyUserName;
	}
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	
}
