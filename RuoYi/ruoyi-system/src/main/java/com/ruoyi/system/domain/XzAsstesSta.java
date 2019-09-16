package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资产表 xz_asstes
 * 
 * @author ruoyi
 * @date 
 */
public class XzAsstesSta extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 资产父类型 */
	@Excel(name = "资产类型", prompt = "资产类型")
	private Long assetTypeId;
	private String assetTypeIdName;
	/** 资产子类型 */
	private Long assetType2Id;
	private String assetType2IdName;
	/** 品牌 */
	@Excel(name = "品牌", prompt = "品牌")
	private String brand;
	/** 资产型号 */
	@Excel(name = "资产型号", prompt = "资产型号")
	private String assetsModel;
	/** 单位 */
	@Excel(name = "单位", prompt = "单位")
	private String unit;
	/** 地域 */
	@Excel(name = "地域", prompt = "所在区域")
	private String region;
	/** 初始 */
	@Excel(name = "初始", prompt = "初始")
	private int chushiCount;
	/** 分配 */
	@Excel(name = "分配", prompt = "分配")
	private int fenpeiCount;
	/** 入库 */
	@Excel(name = "入库", prompt = "入库")
	private int rukuCount;
	/** 归还 */
	@Excel(name = "归还", prompt = "归还")
	private int guihuanCount;
	/** 剩余 */
	@Excel(name = "剩余", prompt = "剩余")
	private int shengyuCount;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAssetTypeId() {
		return assetTypeId;
	}
	public void setAssetTypeId(Long assetTypeId) {
		this.assetTypeId = assetTypeId;
	}
	public Long getAssetType2Id() {
		return assetType2Id;
	}
	public void setAssetType2Id(Long assetType2Id) {
		this.assetType2Id = assetType2Id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getAssetsModel() {
		return assetsModel;
	}
	public void setAssetsModel(String assetsModel) {
		this.assetsModel = assetsModel;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getChushiCount() {
		return chushiCount;
	}
	public void setChushiCount(int chushiCount) {
		this.chushiCount = chushiCount;
	}
	public int getFenpeiCount() {
		return fenpeiCount;
	}
	public void setFenpeiCount(int fenpeiCount) {
		this.fenpeiCount = fenpeiCount;
	}
	public int getRukuCount() {
		return rukuCount;
	}
	public void setRukuCount(int rukuCount) {
		this.rukuCount = rukuCount;
	}
	public int getGuihuanCount() {
		return guihuanCount;
	}
	public void setGuihuanCount(int guihuanCount) {
		this.guihuanCount = guihuanCount;
	}
	
	public String getAssetTypeIdName() {
		return assetTypeIdName;
	}
	public void setAssetTypeIdName(String assetTypeIdName) {
		this.assetTypeIdName = assetTypeIdName;
	}
	public String getAssetType2IdName() {
		return assetType2IdName;
	}
	public void setAssetType2IdName(String assetType2IdName) {
		this.assetType2IdName = assetType2IdName;
	}
	public int getShengyuCount() {
		return shengyuCount;
	}
	public void setShengyuCount(int shengyuCount) {
		this.shengyuCount = shengyuCount;
	}
	@Override
	public String toString() {
		return "XzAsstesSta [id=" + id + ", assetTypeId=" + assetTypeId + ", assetTypeIdName=" + assetTypeIdName
				+ ", assetType2Id=" + assetType2Id + ", assetType2IdName=" + assetType2IdName + ", brand=" + brand
				+ ", assetsModel=" + assetsModel + ", unit=" + unit + ", region=" + region + ", chushiCount="
				+ chushiCount + ", fenpeiCount=" + fenpeiCount + ", rukuCount=" + rukuCount + ", guihuanCount="
				+ guihuanCount + ", shengyuCount=" + shengyuCount + "]";
	}
	
	
}