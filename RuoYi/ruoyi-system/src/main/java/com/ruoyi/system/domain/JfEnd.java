package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 玖富后端表 jf_end
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
public class JfEnd extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;
	/** 日期 */
	@Excel(name = "日期")
	private Date jfDate;
	/** 渠道 */
	@Excel(name = "新入渠道")
	private String channle;
	/** 渠道名称 */
	@Excel(name = "渠道名称")
	private String channleName;
	/** 结算方式 */
	@Excel(name = "结算方式")
	private String clearing;
	/** 广告位 */
	@Excel(name = "广告位")
	private String advertising;
	/** 账户数 */
	@Excel(name = "新入账户数")
	private Integer account;
	/** 开卡数 */
	@Excel(name = "开卡人数")
	private Integer card;
	/** 提交人数 */
	@Excel(name = "提交信审人数")
	private Integer putin;
	/** 下单数 */
	@Excel(name = "有效进件首贷人数（下单意愿）")
	private Integer placeOrder;
	/** 激活数 */
	@Excel(name = "额度激活人数")
	private Integer activate;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setJfDate(Date jfDate) 
	{
		this.jfDate = jfDate;
	}

	public Date getJfDate() 
	{
		return jfDate;
	}
	public void setChannle(String channle) 
	{
		this.channle = channle;
	}

	public String getChannle() 
	{
		return channle;
	}
	public void setChannleName(String channleName) 
	{
		this.channleName = channleName;
	}

	public String getChannleName() 
	{
		return channleName;
	}
	public void setClearing(String clearing) 
	{
		this.clearing = clearing;
	}

	public String getClearing() 
	{
		return clearing;
	}
	public void setAdvertising(String advertising) 
	{
		this.advertising = advertising;
	}

	public String getAdvertising() 
	{
		return advertising;
	}
	public void setAccount(Integer account) 
	{
		this.account = account;
	}

	public Integer getAccount() 
	{
		return account;
	}
	public void setCard(Integer card) 
	{
		this.card = card;
	}

	public Integer getCard() 
	{
		return card;
	}
	public void setPutin(Integer putin) 
	{
		this.putin = putin;
	}

	public Integer getPutin() 
	{
		return putin;
	}
	public void setPlaceOrder(Integer placeOrder) 
	{
		this.placeOrder = placeOrder;
	}

	public Integer getPlaceOrder() 
	{
		return placeOrder;
	}
	public void setActivate(Integer activate) 
	{
		this.activate = activate;
	}

	public Integer getActivate() 
	{
		return activate;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("jfDate", getJfDate())
            .append("channle", getChannle())
            .append("channleName", getChannleName())
            .append("clearing", getClearing())
            .append("advertising", getAdvertising())
            .append("account", getAccount())
            .append("card", getCard())
            .append("putin", getPutin())
            .append("placeOrder", getPlaceOrder())
            .append("activate", getActivate())
            .toString();
    }
}
