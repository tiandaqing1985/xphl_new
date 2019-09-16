package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资产父类型表 xz_asset_type
 * 
 * @author ruoyi
 * @date 2019-08-15
 */
public class XzAssetType extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 父资产类型ID */
	private Long id;
	/** 资产类型ID */
	private String sort;
	/**  */
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
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
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
		return "XzAssetType [id=" + id + ", sort=" + sort + ", name=" + name + ", code=" + code + "]";
	}
}