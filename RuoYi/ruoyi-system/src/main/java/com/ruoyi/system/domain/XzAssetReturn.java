package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资产归还表 xz_asset_return
 * 
 * @author ruoyi
 * @date 2019-09-11
 */
public class XzAssetReturn extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 资产id */
	private Long assetId;
	/** 申请人id */
	private Long userId;
	private String userName;
	/** 处理人id */
	private Long handlerId;
	/** 归还状态 */
	private String returnStatus;
	/** 处理备注说明*/
	private String remark2;
	private String createByName;
	private XzAsstes xzAsstes;

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
	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}

	public Long getUserId() 
	{
		return userId;
	}
	public void setHandlerId(Long handlerId) 
	{
		this.handlerId = handlerId;
	}

	public Long getHandlerId() 
	{
		return handlerId;
	}
	public void setReturnStatus(String returnStatus) 
	{
		this.returnStatus = returnStatus;
	}

	public String getReturnStatus() 
	{
		return returnStatus;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	
	public XzAsstes getXzAsstes() {
		return xzAsstes;
	}

	public void setXzAsstes(XzAsstes xzAsstes) {
		this.xzAsstes = xzAsstes;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	  @Override
	public String toString() {
		return "XzAssetReturn [id=" + id + ", assetId=" + assetId + ", userId=" + userId + ", userName=" + userName
				+ ", handlerId=" + handlerId + ", returnStatus=" + returnStatus + ", remark2=" + remark2
				+ ", createByName=" + createByName + ", xzAsstes=" + xzAsstes + "]";
	}


}
