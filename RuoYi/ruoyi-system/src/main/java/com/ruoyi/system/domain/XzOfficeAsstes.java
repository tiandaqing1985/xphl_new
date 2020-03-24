package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 办公资产记录表 xz_office_asstes
 * 
 * @author ruoyi
 * @date 2019-09-03
 */
public class XzOfficeAsstes extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 地域 */
	private String region;
	/** 资产名称 */
	private String assetsName;
	/** 资产父类型 */
	private Long assetsType;
	private String assetsTypeName;
	/** 资产子类型 */
	private Long assetsType2;
	private String assetsType2Name;
	/** 资产型号 */
	private String assetsModel;
	/** 单价 */
	private String price;
	/** 单位 */
	private String unit;
	/** 数量 */
	private String count;
	/** 归属公司 */
	private String attach;
	/** 品牌 */
	private String brand;
	/** 购买地址（如网上购买链接） */
	private String buyAddress;
	/** 发票类型 */
	private String invoiceType;
	/** 发票编号 */
	private String invoiceNum;
	/** 采购人 */
	private Long purchaseBy;
	private String purchaseName;
	/** 购入时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date buyDate;
	/** 附件 */
	private String annex;
	/** 备注 */
	private String remarks;
	/** 资产提交方式（1保存2提交） */
	private String submitType;
	/** 提交时间 */
	private Date subTime;
	/** 提交人 */
	private String subBy;

	private String isReturn;
	private String isRepair;

	public String getIsRepair() {
		return isRepair;
	}

	public void setIsRepair(String isRepair) {
		this.isRepair = isRepair;
	}

	public String getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setRegion(String region) 
	{
		this.region = region;
	}

	public String getRegion() 
	{
		return region;
	}
	public void setAssetsName(String assetsName) 
	{
		this.assetsName = assetsName;
	}

	public String getAssetsName() 
	{
		return assetsName;
	}
	public void setAssetsType(Long assetsType) 
	{
		this.assetsType = assetsType;
	}

	public Long getAssetsType() 
	{
		return assetsType;
	}
	public void setAssetsType2(Long assetsType2) 
	{
		this.assetsType2 = assetsType2;
	}

	public Long getAssetsType2() 
	{
		return assetsType2;
	}
	public void setAssetsModel(String assetsModel) 
	{
		this.assetsModel = assetsModel;
	}

	public String getAssetsModel() 
	{
		return assetsModel;
	}
	public void setPrice(String price) 
	{
		this.price = price;
	}

	public String getPrice() 
	{
		return price;
	}
	public void setUnit(String unit) 
	{
		this.unit = unit;
	}

	public String getUnit() 
	{
		return unit;
	}
	public void setCount(String count) 
	{
		this.count = count;
	}

	public String getCount() 
	{
		return count;
	}
	public void setAttach(String attach) 
	{
		this.attach = attach;
	}

	public String getAttach() 
	{
		return attach;
	}
	public void setBrand(String brand) 
	{
		this.brand = brand;
	}

	public String getBrand() 
	{
		return brand;
	}
	public void setBuyAddress(String buyAddress) 
	{
		this.buyAddress = buyAddress;
	}

	public String getBuyAddress() 
	{
		return buyAddress;
	}
	public void setInvoiceType(String invoiceType) 
	{
		this.invoiceType = invoiceType;
	}

	public String getInvoiceType() 
	{
		return invoiceType;
	}
	public void setInvoiceNum(String invoiceNum) 
	{
		this.invoiceNum = invoiceNum;
	}

	public String getInvoiceNum() 
	{
		return invoiceNum;
	}
	public void setPurchaseBy(Long purchaseBy) 
	{
		this.purchaseBy = purchaseBy;
	}

	public Long getPurchaseBy() 
	{
		return purchaseBy;
	}
	public void setBuyDate(Date buyDate) 
	{
		this.buyDate = buyDate;
	}

	public Date getBuyDate() 
	{
		return buyDate;
	}
	public void setAnnex(String annex) 
	{
		this.annex = annex;
	}

	public String getAnnex() 
	{
		return annex;
	}
	public void setRemarks(String remarks) 
	{
		this.remarks = remarks;
	}

	public String getRemarks() 
	{
		return remarks;
	}
	public void setSubmitType(String submitType) 
	{
		this.submitType = submitType;
	}

	public String getSubmitType() 
	{
		return submitType;
	}
	public void setSubTime(Date subTime) 
	{
		this.subTime = subTime;
	}

	public Date getSubTime() 
	{
		return subTime;
	}
	public void setSubBy(String subBy) 
	{
		this.subBy = subBy;
	}

	public String getSubBy() 
	{
		return subBy;
	}

	public String getPurchaseName() {
		return purchaseName;
	}

	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}
	
	public String getAssetsTypeName() {
		return assetsTypeName;
	}

	public void setAssetsTypeName(String assetsTypeName) {
		this.assetsTypeName = assetsTypeName;
	}

	public String getAssetsType2Name() {
		return assetsType2Name;
	}

	public void setAssetsType2Name(String assetsType2Name) {
		this.assetsType2Name = assetsType2Name;
	}

	 @Override
		public String toString() {
			return "XzOfficeAsstes [id=" + id + ", region=" + region + ", assetsName=" + assetsName + ", assetsType="
					+ assetsType + ", assetsTypeName=" + assetsTypeName + ", assetsType2=" + assetsType2
					+ ", assetsType2Name=" + assetsType2Name + ", assetsModel=" + assetsModel + ", price=" + price
					+ ", unit=" + unit + ", count=" + count + ", attach=" + attach + ", brand=" + brand + ", buyAddress="
					+ buyAddress + ", invoiceType=" + invoiceType + ", invoiceNum=" + invoiceNum + ", purchaseBy="
					+ purchaseBy + ", purchaseName=" + purchaseName + ", buyDate=" + buyDate + ", annex=" + annex
					+ ", remarks=" + remarks + ", submitType=" + submitType + ", subTime=" + subTime + ", subBy=" + subBy
					+ "]";
		}

}
