package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 盘点记录表 xz_check
 * 
 * @author ruoyi
 * @date 2019-12-26
 */
public class XzCheck extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Long id;
	/** 资产编码 */
	private String asstesCode;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setAsstesCode(String asstesCode) 
	{
		this.asstesCode = asstesCode;
	}

	public String getAsstesCode() 
	{
		return asstesCode;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("asstesCode", getAsstesCode())
            .append("createTime", getCreateTime())
            .toString();
    }
}
