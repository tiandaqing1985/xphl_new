package com.ruoyi.system.domain.finance;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 差旅住宿报销表 fac_cost_putup_reimburse
 * 
 * @author ruoyi
 * @date 2019-11-12
 */
public class FacCostPutupReimburse extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 差旅编号 */
	private String num;
	/** 入住城市 */
	private String city;
	/** 入店时间 */
	private Date entrytime;
	/** 离店时间 */
	private Date shoptime;
	/** 入住人数 */
	private Long number;
	/** 入住详情 */
	private String details;
	/** 住宿金额 */
	private Double money;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setNum(String num) 
	{
		this.num = num;
	}

	public String getNum() 
	{
		return num;
	}
	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getCity() 
	{
		return city;
	}
	public void setEntrytime(Date entrytime) 
	{
		this.entrytime = entrytime;
	}

	public Date getEntrytime() 
	{
		return entrytime;
	}
	public void setShoptime(Date shoptime) 
	{
		this.shoptime = shoptime;
	}

	public Date getShoptime() 
	{
		return shoptime;
	}
	public void setNumber(Long number) 
	{
		this.number = number;
	}

	public Long getNumber() 
	{
		return number;
	}
	public void setDetails(String details) 
	{
		this.details = details;
	}

	public String getDetails() 
	{
		return details;
	}
	public void setMoney(Double money) 
	{
		this.money = money;
	}

	public Double getMoney() 
	{
		return money;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("num", getNum())
            .append("city", getCity())
            .append("entrytime", getEntrytime())
            .append("shoptime", getShoptime())
            .append("number", getNumber())
            .append("details", getDetails())
            .append("money", getMoney())
            .toString();
    }
}
