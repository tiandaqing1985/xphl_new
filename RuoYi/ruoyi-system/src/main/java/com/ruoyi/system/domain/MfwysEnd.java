package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 马蜂窝原生后端表 mfwys_end
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
public class MfwysEnd extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;
	/** 新增时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Excel(name = "新增时间",dateFormat="yyyyMMdd")
	private String newtime;
	/** 系统 */
	@Excel(name = "系统")
	private String mfwSystem;
	/** 渠道包名 */
	@Excel(name = "渠道包名")
	private String channelPackage;
	/** 渠道 */
	@Excel(name = "渠道")
	private String channel;
	/** 新增设备数 */
	@Excel(name = "新增设备数")
	private Integer newFacility;
	/** 账户id */
	@Excel(name = "账户id")
	private String account;
	/** aid */
	@Excel(name = "aid")
	private Long aid;
	/** 次日留存 */
	@Excel(name = "次日留存")
	private Double keep;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setNewtime(String newtime) 
	{
		this.newtime = newtime;
	}

	public String getNewtime() 
	{
		return newtime;
	}
	public void setMfwSystem(String mfwSystem) 
	{
		this.mfwSystem = mfwSystem;
	}

	public String getMfwSystem() 
	{
		return mfwSystem;
	}
	public void setChannelPackage(String channelPackage) 
	{
		this.channelPackage = channelPackage;
	}

	public String getChannelPackage() 
	{
		return channelPackage;
	}
	public void setChannel(String channel) 
	{
		this.channel = channel;
	}

	public String getChannel() 
	{
		return channel;
	}
	public void setNewFacility(Integer newFacility) 
	{
		this.newFacility = newFacility;
	}

	public Integer getNewFacility() 
	{
		return newFacility;
	}
	public void setAccount(String account) 
	{
		this.account = account;
	}

	public String getAccount() 
	{
		return account;
	}
	public void setAid(Long aid) 
	{
		this.aid = aid;
	}

	public Long getAid() 
	{
		return aid;
	}
	public void setKeep(Double keep) 
	{
		this.keep = keep;
	}

	public Double getKeep() 
	{
		return keep;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("newtime", getNewtime())
            .append("mfwSystem", getMfwSystem())
            .append("channelPackage", getChannelPackage())
            .append("channel", getChannel())
            .append("newFacility", getNewFacility())
            .append("account", getAccount())
            .append("aid", getAid())
            .append("keep", getKeep())
            .toString();
    }
}
