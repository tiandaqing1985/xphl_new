package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 资产表 xz_asstes
 * 
 * @author ruoyi
 * @date 2019-08-02
 */
public class XzAsstes extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 资产编码 */
	private String assetsCode;
	/** 资产名称 */
	@Excel(name = "资产名称", prompt = "资产名称")
	private String assetsName;
	/** 资产父类型 */
	private Long assetsType;
	@Excel(name = "资产类型", prompt = "资产类型")
	private String assetsTypeName;
	/** 资产子类型 */
	private Long assetsType2;
	@Excel(name = "资产二级类型", prompt = "资产二级类型")
	private String assetsType2Name;
	/** 品牌 */
	private String brand;
	@Excel(name = "品牌", prompt = "品牌")
	private String brandName;
	/** 分类码s/n */
	@Excel(name = "分类码s/n", prompt = "分类码s/n")
	private String category;
	/** 资产型号 */
	@Excel(name = "资产型号", prompt = "资产型号")
	private String assetsModel;
	/** 单价 */
	@Excel(name = "单价", prompt = "单价")
	private String price;
	/** 单位 */
	private String unit;
	@Excel(name = "单位", prompt = "单位")
	private String unitName;
	/** 数量 */
	@Excel(name = "数量", prompt = "数量")
	private String count;
	/** 地域 */
	private String region;
	@Excel(name = "所在区域", prompt = "所在区域")
	private String regionName;
	/** 归属公司 */
	private String attach;
	@Excel(name = "归属公司", prompt = "归属公司")
	private String attachName;
	/** 采购单号 */
	@Excel(name = "采购单号", prompt = "采购单号")
	private String purchaseNum;
	/** 采购人 */
	private Long purchaseBy;
	@Excel(name = "采购人", prompt = "采购人")
	private String purchaseName;
	/** 购入时间 */
	@Excel(name = "购入时间", prompt = "购入时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date buyDate;
	/** 购买地址（如网上购买链接） */
	@Excel(name = "购买地址", prompt = "购买地址")
	private String buyAddress;
	/** 发票类型 */
	private String invoiceType;
	@Excel(name = "发票类型", prompt = "发票类型")
	private String invoiceTypeName;
	/** 发票编号 */
	@Excel(name = "发票编号", prompt = "发票编号")
	private String invoiceNum;
	/** 扩展金额 */
	@Excel(name = "扩展金额", prompt = "扩展金额")
	private String extendMoney;
	/** 扩展时间 */
	@Excel(name = "扩展时间", prompt = "扩展时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date extendTime;
	/** 扩展内容 */
	@Excel(name = "扩展内容", prompt = "扩展内容")
	private String extendContent;
	/** 资产状态 */
	private String assetsStatus;
	private String assetsStatusName;
	/** 使用状态 */
	private String useStatus;
	private String useStatusName;
	/** 前当使用人 */
	private Long useBy;
	private String useName;
	/** 使用部门 */
	private Long department;
	private String departmentName;
	/** 使用时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date useTime;
	/** 制造商 */
	private String manufacturer;
	/** 供应商 */
	private String supplier;
	/** 附件 */
	private String annex;
	/** 备注 */
	private String remarks;
	/** 资产提交时间 */
	private String subBy;
	/** 资产提交人*/
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date subTime;
	/** 资产提交方式 */
	private String submitType;
	/** 资产报废日期 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date scrapDate;
	/** 资产入库方式 */
	private String storeWay;
	/** 资产分类（1固定资产、2办公用品资产）**/
	private String sort;
	
	private SysUser user;
	
	private SysDept dept;

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public SysDept getDept() {
		return dept;
	}

	public void setDept(SysDept dept) {
		this.dept = dept;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setAssetsCode(String assetsCode) 
	{
		this.assetsCode = assetsCode;
	}

	public String getAssetsCode() 
	{
		return assetsCode;
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
	public void setPurchaseNum(String purchaseNum) 
	{
		this.purchaseNum = purchaseNum;
	}

	public String getPurchaseNum() 
	{
		return purchaseNum;
	}
	public void setCategory(String category) 
	{
		this.category = category;
	}

	public String getCategory() 
	{
		return category;
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
	public void setManufacturer(String manufacturer) 
	{
		this.manufacturer = manufacturer;
	}

	public String getManufacturer() 
	{
		return manufacturer;
	}
	public void setSupplier(String supplier) 
	{
		this.supplier = supplier;
	}

	public String getSupplier() 
	{
		return supplier;
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
	public void setExtendMoney(String extendMoney) 
	{
		this.extendMoney = extendMoney;
	}

	public String getExtendMoney() 
	{
		return extendMoney;
	}
	public void setExtendTime(Date extendTime) 
	{
		this.extendTime = extendTime;
	}

	public Date getExtendTime() 
	{
		return extendTime;
	}
	public void setExtendContent(String extendContent) 
	{
		this.extendContent = extendContent;
	}

	public String getExtendContent() 
	{
		return extendContent;
	}
	public void setAssetsStatus(String assetsStatus) 
	{
		this.assetsStatus = assetsStatus;
	}

	public String getAssetsStatus() 
	{
		return assetsStatus;
	}
	public void setUseStatus(String useStatus) 
	{
		this.useStatus = useStatus;
	}

	public String getUseStatus() 
	{
		return useStatus;
	}
	public void setUseBy(Long useBy) 
	{
		this.useBy = useBy;
	}

	public Long getUseBy() 
	{
		return useBy;
	}
	public void setDepartment(Long department) 
	{
		this.department = department;
	}

	public Long getDepartment() 
	{
		return department;
	}
	public void setUseTime(Date useTime) 
	{
		this.useTime = useTime;
	}

	public Date getUseTime() 
	{
		return useTime;
	}
	public void setSubmitType(String submitType) 
	{
		this.submitType = submitType;
	}

	public String getSubmitType() 
	{
		return submitType;
	}
	public void setScrapDate(Date scrapDate) 
	{
		this.scrapDate = scrapDate;
	}

	public Date getScrapDate() 
	{
		return scrapDate;
	}
	public void setStoreWay(String storeWay) 
	{
		this.storeWay = storeWay;
	}

	public String getStoreWay() 
	{
		return storeWay;
	}
	
	public String getSubBy() {
		return subBy;
	}

	public void setSubBy(String subBy) {
		this.subBy = subBy;
	}

	public Date getSubTime() {
		return subTime;
	}

	public void setSubTime(Date date) {
		this.subTime = date;
	}

	public String getPurchaseName() {
		return purchaseName;
	}

	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}

	public String getUseName() {
		return useName;
	}

	public void setUseName(String useName) {
		this.useName = useName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	public String getInvoiceTypeName() {
		return invoiceTypeName;
	}

	public void setInvoiceTypeName(String invoiceTypeName) {
		this.invoiceTypeName = invoiceTypeName;
	}

	public String getAssetsStatusName() {
		return assetsStatusName;
	}

	public void setAssetsStatusName(String assetsStatusName) {
		this.assetsStatusName = assetsStatusName;
	}

	public String getUseStatusName() {
		return useStatusName;
	}

	public void setUseStatusName(String useStatusName) {
		this.useStatusName = useStatusName;
	}

	@Override
	public String toString() {
		return "XzAsstes [id=" + id + ", assetsCode=" + assetsCode + ", assetsName=" + assetsName + ", assetsType="
				+ assetsType + ", assetsTypeName=" + assetsTypeName + ", assetsType2=" + assetsType2
				+ ", assetsType2Name=" + assetsType2Name + ", brand=" + brand + ", brandName=" + brandName
				+ ", category=" + category + ", assetsModel=" + assetsModel + ", price=" + price + ", unit=" + unit
				+ ", unitName=" + unitName + ", count=" + count + ", region=" + region + ", regionName=" + regionName
				+ ", attach=" + attach + ", attachName=" + attachName + ", purchaseNum=" + purchaseNum + ", purchaseBy="
				+ purchaseBy + ", purchaseName=" + purchaseName + ", buyDate=" + buyDate + ", buyAddress=" + buyAddress
				+ ", invoiceType=" + invoiceType + ", invoiceTypeName=" + invoiceTypeName + ", invoiceNum=" + invoiceNum
				+ ", extendMoney=" + extendMoney + ", extendTime=" + extendTime + ", extendContent=" + extendContent
				+ ", assetsStatus=" + assetsStatus + ", assetsStatusName=" + assetsStatusName + ", useStatus="
				+ useStatus + ", useStatusName=" + useStatusName + ", useBy=" + useBy + ", useName=" + useName
				+ ", department=" + department + ", departmentName=" + departmentName + ", useTime=" + useTime
				+ ", manufacturer=" + manufacturer + ", supplier=" + supplier + ", annex=" + annex + ", remarks="
				+ remarks + ", subBy=" + subBy + ", subTime=" + subTime + ", submitType=" + submitType + ", scrapDate="
				+ scrapDate + ", storeWay=" + storeWay + ", sort=" + sort + ", user=" + user + ", dept=" + dept + "]";
	}

    
}