package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 办公用品表 xz_stationeryAsset
 * 
 * @author ruoyi
 * @date 2019-08-12
 */
public class XzStationeryasset extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 办公资产名称    取自办公类型子类型名称 */
	private String stationeryAssetName;
	/** 办公资产对应的id */
	private Long stationeryAssetId;
	/** 办公资产类型名称    取自办公类型的父类型名称 */
	private String assetTypeName;
	/**  */
	private Integer rukuCount;
	/** 办公资产类型对应的id */
	private String assetTypeId;
	/** 当月入库数量 */
	private Integer fenpeiCount;
	/** 库存中有的资产对应的品牌，多个品牌以，分割 */
	private String brandType;
	/** 当月库存数量 */
	private Integer kucunCount;
	/** 当月剩余数量 */
	private Integer shengyuCount;
	/** 地域 */
	private Integer area;
	/** 品牌的id字符串 */
	private String brand;
	/** model */
	private String model;
	/**  */
	private String baocunType;
	/** 保存状态的资产id */
	private Long assetId;
	/** 时间月份字符串 */
	private String time;
	/** 当月初始数量 */
	private Integer chushiCount;
	/**  */
	private Integer categoryId;
	/**  */
	private Integer guihaiCount;
	/** 计量单位 */
	private String unit;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setStationeryAssetName(String stationeryAssetName) 
	{
		this.stationeryAssetName = stationeryAssetName;
	}

	public String getStationeryAssetName() 
	{
		return stationeryAssetName;
	}
	public void setStationeryAssetId(Long stationeryAssetId) 
	{
		this.stationeryAssetId = stationeryAssetId;
	}

	public Long getStationeryAssetId() 
	{
		return stationeryAssetId;
	}
	public void setAssetTypeName(String assetTypeName) 
	{
		this.assetTypeName = assetTypeName;
	}

	public String getAssetTypeName() 
	{
		return assetTypeName;
	}
	public void setRukuCount(Integer rukuCount) 
	{
		this.rukuCount = rukuCount;
	}

	public Integer getRukuCount() 
	{
		return rukuCount;
	}
	public void setAssetTypeId(String assetTypeId) 
	{
		this.assetTypeId = assetTypeId;
	}

	public String getAssetTypeId() 
	{
		return assetTypeId;
	}
	public void setFenpeiCount(Integer fenpeiCount) 
	{
		this.fenpeiCount = fenpeiCount;
	}

	public Integer getFenpeiCount() 
	{
		return fenpeiCount;
	}
	public void setBrandType(String brandType) 
	{
		this.brandType = brandType;
	}

	public String getBrandType() 
	{
		return brandType;
	}
	public void setKucunCount(Integer kucunCount) 
	{
		this.kucunCount = kucunCount;
	}

	public Integer getKucunCount() 
	{
		return kucunCount;
	}
	public void setShengyuCount(Integer shengyuCount) 
	{
		this.shengyuCount = shengyuCount;
	}

	public Integer getShengyuCount() 
	{
		return shengyuCount;
	}
	public void setArea(Integer area) 
	{
		this.area = area;
	}

	public Integer getArea() 
	{
		return area;
	}
	public void setBrand(String brand) 
	{
		this.brand = brand;
	}

	public String getBrand() 
	{
		return brand;
	}
	public void setModel(String model) 
	{
		this.model = model;
	}

	public String getModel() 
	{
		return model;
	}
	public void setBaocunType(String baocunType) 
	{
		this.baocunType = baocunType;
	}

	public String getBaocunType() 
	{
		return baocunType;
	}
	public void setAssetId(Long assetId) 
	{
		this.assetId = assetId;
	}

	public Long getAssetId() 
	{
		return assetId;
	}
	public void setTime(String time) 
	{
		this.time = time;
	}

	public String getTime() 
	{
		return time;
	}
	public void setChushiCount(Integer chushiCount) 
	{
		this.chushiCount = chushiCount;
	}

	public Integer getChushiCount() 
	{
		return chushiCount;
	}
	public void setCategoryId(Integer categoryId) 
	{
		this.categoryId = categoryId;
	}

	public Integer getCategoryId() 
	{
		return categoryId;
	}
	public void setGuihaiCount(Integer guihaiCount) 
	{
		this.guihaiCount = guihaiCount;
	}

	public Integer getGuihaiCount() 
	{
		return guihaiCount;
	}
	public void setUnit(String unit) 
	{
		this.unit = unit;
	}

	public String getUnit() 
	{
		return unit;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("stationeryAssetName", getStationeryAssetName())
            .append("stationeryAssetId", getStationeryAssetId())
            .append("assetTypeName", getAssetTypeName())
            .append("rukuCount", getRukuCount())
            .append("assetTypeId", getAssetTypeId())
            .append("fenpeiCount", getFenpeiCount())
            .append("brandType", getBrandType())
            .append("kucunCount", getKucunCount())
            .append("shengyuCount", getShengyuCount())
            .append("area", getArea())
            .append("brand", getBrand())
            .append("model", getModel())
            .append("baocunType", getBaocunType())
            .append("assetId", getAssetId())
            .append("time", getTime())
            .append("chushiCount", getChushiCount())
            .append("categoryId", getCategoryId())
            .append("guihaiCount", getGuihaiCount())
            .append("unit", getUnit())
            .toString();
    }
}