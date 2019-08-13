package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 办公用品分配记录表 xz_stationeryAsset_record
 * 
 * @author ruoyi
 * @date 2019-08-12
 */
public class XzStationeryassetRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 地域 */
	private String region;
	/** 资产名称 */
	private String assetsName;
	/** 资产父类型 */
	private String assetsType;
	/** 资产子类型 */
	private String assetsType2;
	/** 资产型号 */
	private String assetsModel;
	/** 品牌 */
	private String brand;
	/** 单价 */
	private String price;
	/** 单位 */
	private String unit;
	/** 数量 */
	private Integer count;
	/** 总金额 */
	private String sumPrice;
	/** 采购单号 */
	private String purchaseNum;
	/** 采购人 */
	private String purchaseBy;
	/** 购入时间 */
	private Date buyDate;
	/** 购买地址（如网上购买链接） */
	private String buyAddress;
	/** 发票类型 */
	private String invoiceType;
	/** 发票编号 */
	private String invoiceNum;
	/** 附件 */
	private String annex;
	/** 备注 */
	private String remarks;
	/** 资产状态 */
	private String assetsStatus;
	/** 使用状态 */
	private String useStatus;
	/** 分配人 */
	private String distributor;
	/** 分配时间 */
	private Date distributionTime;
	/** 前当使用人 */
	private String useBy;
	/** 使用时间 */
	private Date useTime;
	/** 使用部门 */
	private String department;
	/** 资产提交方式 */
	private String submitType;
	/** 资产报废日期 */
	private Date scrapDate;
	/** 资产入库方式 */
	private String storeWay;
	/** 入库提交时间 */
	private Date subTime;
	/** 提交人 */
	private String subBy;

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
	public void setAssetsType(String assetsType) 
	{
		this.assetsType = assetsType;
	}

	public String getAssetsType() 
	{
		return assetsType;
	}
	public void setAssetsType2(String assetsType2) 
	{
		this.assetsType2 = assetsType2;
	}

	public String getAssetsType2() 
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
	public void setBrand(String brand) 
	{
		this.brand = brand;
	}

	public String getBrand() 
	{
		return brand;
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
	public void setCount(Integer count) 
	{
		this.count = count;
	}

	public Integer getCount() 
	{
		return count;
	}
	public void setSumPrice(String sumPrice) 
	{
		this.sumPrice = sumPrice;
	}

	public String getSumPrice() 
	{
		return sumPrice;
	}
	public void setPurchaseNum(String purchaseNum) 
	{
		this.purchaseNum = purchaseNum;
	}

	public String getPurchaseNum() 
	{
		return purchaseNum;
	}
	public void setPurchaseBy(String purchaseBy) 
	{
		this.purchaseBy = purchaseBy;
	}

	public String getPurchaseBy() 
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
	public void setDistributor(String distributor) 
	{
		this.distributor = distributor;
	}

	public String getDistributor() 
	{
		return distributor;
	}
	public void setDistributionTime(Date distributionTime) 
	{
		this.distributionTime = distributionTime;
	}

	public Date getDistributionTime() 
	{
		return distributionTime;
	}
	public void setUseBy(String useBy) 
	{
		this.useBy = useBy;
	}

	public String getUseBy() 
	{
		return useBy;
	}
	public void setUseTime(Date useTime) 
	{
		this.useTime = useTime;
	}

	public Date getUseTime() 
	{
		return useTime;
	}
	public void setDepartment(String department) 
	{
		this.department = department;
	}

	public String getDepartment() 
	{
		return department;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("region", getRegion())
            .append("assetsName", getAssetsName())
            .append("assetsType", getAssetsType())
            .append("assetsType2", getAssetsType2())
            .append("assetsModel", getAssetsModel())
            .append("brand", getBrand())
            .append("price", getPrice())
            .append("unit", getUnit())
            .append("count", getCount())
            .append("sumPrice", getSumPrice())
            .append("purchaseNum", getPurchaseNum())
            .append("purchaseBy", getPurchaseBy())
            .append("buyDate", getBuyDate())
            .append("buyAddress", getBuyAddress())
            .append("invoiceType", getInvoiceType())
            .append("invoiceNum", getInvoiceNum())
            .append("annex", getAnnex())
            .append("remarks", getRemarks())
            .append("assetsStatus", getAssetsStatus())
            .append("useStatus", getUseStatus())
            .append("distributor", getDistributor())
            .append("distributionTime", getDistributionTime())
            .append("useBy", getUseBy())
            .append("useTime", getUseTime())
            .append("department", getDepartment())
            .append("submitType", getSubmitType())
            .append("scrapDate", getScrapDate())
            .append("storeWay", getStoreWay())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("subTime", getSubTime())
            .append("subBy", getSubBy())
            .toString();
    }
}
