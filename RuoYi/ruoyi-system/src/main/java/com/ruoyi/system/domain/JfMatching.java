package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.Type;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 玖富匹配表 jf_matching
 * 
 * @author ruoyi
 * @date 2019-06-28
 */
public class JfMatching extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;
	/** 编码 */
	@Excel(name = "编码")
	private String code;
	/** 计划 */
	@Excel(name = "推广计划名称")
	private String plan;
	/** 单元 */
	@Excel(name = "推广单元名称")
	private String unit;
	/** 关键词 */
	@Excel(name = "关键词名称")
	private String keyword;
	/** 账户 */
	@Excel(name = "账户")
	private String account;
	/** 组合 */
	private String groupword;
	/** 标识 */
	@Excel(name = "标识" , type = Type.IMPORT)
	private String flag;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setCode(String code) 
	{
		this.code = code;
	}

	public String getCode() 
	{
		return code;
	}
	public void setPlan(String plan) 
	{
		this.plan = plan;
	}

	public String getPlan() 
	{
		return plan;
	}
	public void setUnit(String unit) 
	{
		this.unit = unit;
	}

	public String getUnit() 
	{
		return unit;
	}
	public void setKeyword(String keyword) 
	{
		this.keyword = keyword;
	}

	public String getKeyword() 
	{
		return keyword;
	}
	public void setAccount(String account) 
	{
		this.account = account;
	}

	public String getAccount() 
	{
		return account;
	}
	public void setGroupword(String groupword) 
	{
		this.groupword = groupword;
	}

	public String getGroupword() 
	{
		return groupword;
	}
	public void setFlag(String flag) 
	{
		this.flag = flag;
	}

	public String getFlag() 
	{
		return flag;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("plan", getPlan())
            .append("unit", getUnit())
            .append("keyword", getKeyword())
            .append("account", getAccount())
            .append("groupword", getGroupword())
            .append("flag", getFlag())
            .append("createTime", getCreateTime())
            .toString();
    }
}
