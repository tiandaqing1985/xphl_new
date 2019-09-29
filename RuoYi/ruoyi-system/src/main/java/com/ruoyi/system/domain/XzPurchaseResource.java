package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购资源表 xz_purchase_resource
 * 
 * @author ruoyi
 * @date 2019-09-17
 */
public class XzPurchaseResource extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 采购单号*/
	private String code;
	/** 地区 */
	private String region;
	/** 资源类型 */
	private Long resourceTypeId;
	private String resourceTypeName;
	/** 父资源类型 */
	private Long resourceParentId;
	/** 资源名称 */
	private String resourceName;
	/** 数量 */
	private Integer resourceCount;
	/** 单价 */
	private Double resourcePrice;
	/** 资源规格 */
	private String resourceSpec;
	/** 计量单位 */
	private String unit;
	/** 购买渠道 */
	private String purchaseChannel;
	/** 购买地址 */
	private String purchaseAddress;
	/** 品牌 */
	private String brand;
	/** 明细 */
	private String detail;
	/** 附件路径 */
	private String fileUrl;
	/** 附件名称 */
	private String fileName;
	/** 实际金额 */
	private Double actualPrice;
	/** 浮动金额 */
	private Double floatPrice;
	private Double sumPrice;
	/** 关联费用管理时填写的备注 */
	private String floatComment;
	
	private String createByName;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setResourceTypeId(Long resourceTypeId) 
	{
		this.resourceTypeId = resourceTypeId;
	}

	public Long getResourceTypeId() 
	{
		return resourceTypeId;
	}
	public void setResourceParentId(Long resourceParentId) 
	{
		this.resourceParentId = resourceParentId;
	}

	public Long getResourceParentId() 
	{
		return resourceParentId;
	}
	public void setResourceName(String resourceName) 
	{
		this.resourceName = resourceName;
	}

	public String getResourceName() 
	{
		return resourceName;
	}
	public void setResourceCount(Integer resourceCount) 
	{
		this.resourceCount = resourceCount;
	}

	public Integer getResourceCount() 
	{
		return resourceCount;
	}
	public void setResourcePrice(Double resourcePrice) 
	{
		this.resourcePrice = resourcePrice;
	}

	public Double getResourcePrice() 
	{
		return resourcePrice;
	}
	public void setResourceSpec(String resourceSpec) 
	{
		this.resourceSpec = resourceSpec;
	}

	public String getResourceSpec() 
	{
		return resourceSpec;
	}
	public void setPurchaseChannel(String purchaseChannel) 
	{
		this.purchaseChannel = purchaseChannel;
	}

	public String getPurchaseChannel() 
	{
		return purchaseChannel;
	}
	public void setPurchaseAddress(String purchaseAddress) 
	{
		this.purchaseAddress = purchaseAddress;
	}

	public String getPurchaseAddress() 
	{
		return purchaseAddress;
	}
	public void setBrand(String brand) 
	{
		this.brand = brand;
	}

	public String getBrand() 
	{
		return brand;
	}
	public void setDetail(String detail) 
	{
		this.detail = detail;
	}

	public String getDetail() 
	{
		return detail;
	}
	public void setFileUrl(String fileUrl) 
	{
		this.fileUrl = fileUrl;
	}

	public String getFileUrl() 
	{
		return fileUrl;
	}
	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public String getFileName() 
	{
		return fileName;
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
	public void setFloatComment(String floatComment) 
	{
		this.floatComment = floatComment;
	}

	public String getFloatComment() 
	{
		return floatComment;
	}
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getResourceTypeName() {
		return resourceTypeName;
	}

	public void setResourceTypeName(String resourceTypeName) {
		this.resourceTypeName = resourceTypeName;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public Double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}
