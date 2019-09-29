package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购类型表 xz_resource_type
 * 
 * @author ruoyi
 * @date 2019-09-16
 */
public class XzResourceType extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 上级id */
	private Long parentId;
	private String parentName;
	/** 名称 */
	private String name;
	/** 类型，1采购类型， 2活动类型 */
	private Integer sourceType;

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
	public void setSourceType(Integer sourceType) 
	{
		this.sourceType = sourceType;
	}

	public Integer getSourceType() 
	{
		return sourceType;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	

    @Override
	public String toString() {
		return "XzResourceType [id=" + id + ", parentId=" + parentId + ", parentName=" + parentName + ", name=" + name
				+ ", sourceType=" + sourceType + "]";
	}

}
