package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 当当pc端品专表 dangdang_pz_back
 * 
 * @author ruoyi
 * @date 2019-07-21
 */
public class DangdangPzBack extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 日期 */
	@Excel(name="日期",dateFormat = "yyyy-MM-DD")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date ddDate;
	/** uv */
	@Excel(name="UV")
	private Integer uv;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setDdDate(Date ddDate) 
	{
		this.ddDate = ddDate;
	}

	public Date getDdDate() 
	{
		return ddDate;
	}
	public void setUv(Integer uv) 
	{
		this.uv = uv;
	}

	public Integer getUv() 
	{
		return uv;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ddDate", getDdDate())
            .append("uv", getUv())
            .toString();
    }
}
