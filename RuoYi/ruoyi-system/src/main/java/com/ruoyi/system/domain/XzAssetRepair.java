package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 资产维修表 xz_asset_repair
 * 
 * @author ruoyi
 * @date 2019-09-09
 */
public class XzAssetRepair extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long repairId;
	/** 个人资产id */
	private Long assetId;
	/** 报修人id */
	private Long userId;
	private String userName;
	/** 故障时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date repairTime;
	/** 问题描述 */
	private String problemContext;
	/** 处理人id */
	private Long handlerId;
	/** 处理时间 */
	private Date handlerTime;
	/** 检修人 */
	private Long inspectorId;
	private String inspectorName;
	/** 维修状态 */
	private String repairStatus;
	/** 地区*/
	private String region;
	/** 维修说明 */
	private String repairContext;
	private String assetsCode;
	private String assetsName;
	private XzAsstes xzAsstes;

	//标识这条记录是否是历史记录
	private String isHistory;

	public String getIsHistory() {
		return isHistory;
	}

	public void setIsHistory(String isHistory) {
		this.isHistory = isHistory;
	}

	public void setRepairId(Long repairId)
	{
		this.repairId = repairId;
	}

	public Long getRepairId() 
	{
		return repairId;
	}
	public void setAssetId(Long assetId) 
	{
		this.assetId = assetId;
	}

	public Long getAssetId() 
	{
		return assetId;
	}
	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}

	public Long getUserId() 
	{
		return userId;
	}
	public void setRepairTime(Date repairTime) 
	{
		this.repairTime = repairTime;
	}

	public Date getRepairTime() 
	{
		return repairTime;
	}
	public void setProblemContext(String problemContext) 
	{
		this.problemContext = problemContext;
	}

	public String getProblemContext() 
	{
		return problemContext;
	}
	public void setHandlerId(Long handlerId) 
	{
		this.handlerId = handlerId;
	}

	public Long getHandlerId() 
	{
		return handlerId;
	}
	public void setHandlerTime(Date handlerTime) 
	{
		this.handlerTime = handlerTime;
	}

	public Date getHandlerTime() 
	{
		return handlerTime;
	}
	public void setInspectorId(Long inspectorId) 
	{
		this.inspectorId = inspectorId;
	}

	public Long getInspectorId() 
	{
		return inspectorId;
	}
	public void setRepairStatus(String repairStatus) 
	{
		this.repairStatus = repairStatus;
	}

	public String getRepairStatus() 
	{
		return repairStatus;
	}
	public void setRepairContext(String repairContext) 
	{
		this.repairContext = repairContext;
	}

	public String getRepairContext() 
	{
		return repairContext;
	}
   
	public XzAsstes getXzAsstes() {
		return xzAsstes;
	}

	public void setXzAsstes(XzAsstes xzAsstes) {
		this.xzAsstes = xzAsstes;
	}
	
	public String getAssetsCode() {
		return assetsCode;
	}

	public void setAssetsCode(String assetsCode) {
		this.assetsCode = assetsCode;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInspectorName() {
		return inspectorName;
	}

	public void setInspectorName(String inspectorName) {
		this.inspectorName = inspectorName;
	}
	

	 @Override
	public String toString() {
		return "XzAssetRepair [repairId=" + repairId + ", assetId=" + assetId + ", userId=" + userId + ", userName="
				+ userName + ", repairTime=" + repairTime + ", problemContext=" + problemContext + ", handlerId="
				+ handlerId + ", handlerTime=" + handlerTime + ", inspectorId=" + inspectorId + ", inspectorName="
				+ inspectorName + ", repairStatus=" + repairStatus + ", repairContext=" + repairContext
				+ ", assetsCode=" + assetsCode + ", assetsName=" + assetsName + ", xzAsstes=" + xzAsstes + "]";
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}
