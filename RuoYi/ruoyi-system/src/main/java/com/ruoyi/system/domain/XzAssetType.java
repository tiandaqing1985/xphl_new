package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
	private String assetsType;
	/**  */
	private String name;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setAssetsType(String assetsType) 
	{
		this.assetsType = assetsType;
	}

	public String getAssetsType() 
	{
		return assetsType;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("assetsType", getAssetsType())
            .append("name", getName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
