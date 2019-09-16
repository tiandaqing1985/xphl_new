package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资产子类型表 xz_asset_data
 * 
 * @author ruoyi
 * @date 2019-08-15
 */
public class XzAssetData extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 父资产ID */
	private Long parentId;
	/** 资产类型名称 */
	private String name;
	
	private String code;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setParentId(Long parentId) 
	{
		this.parentId = parentId;
	}

	public Long getParentId() 
	{
		return parentId;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
  
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	  @Override
		public String toString() {
			return "XzAssetData [id=" + id + ", parentId=" + parentId + ", name=" + name + ", code=" + code + "]";
		}

}