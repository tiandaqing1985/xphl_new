package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 马蜂窝原生参数表 mfwys_parameter
 * 
 * @author ruoyi
 * @date 2019-07-18
 */
public class MfwysParameter extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 计划名称 */
	private String plan;
	/** 渠道包 */
	private String channel;
	/** 账户 */
	private String account;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setPlan(String plan) 
	{
		this.plan = plan;
	}

	public String getPlan() 
	{
		return plan;
	}
	public void setChannel(String channel) 
	{
		this.channel = channel;
	}

	public String getChannel() 
	{
		return channel;
	}
	public void setAccount(String account) 
	{
		this.account = account;
	}

	public String getAccount() 
	{
		return account;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("plan", getPlan())
            .append("channel", getChannel())
            .append("account", getAccount())
            .toString();
    }
}
