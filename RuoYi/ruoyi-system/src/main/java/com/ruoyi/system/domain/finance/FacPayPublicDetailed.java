package com.ruoyi.system.domain.finance;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 对公明细表 fac_pay_public_detailed
 * 
 * @author ruoyi
 * @date 2019-10-16
 */
public class FacPayPublicDetailed extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 对公编号 */
	private String num;
	/** 项目名称 */
	private String name;
	/** 付款事由 */
	private String reson;
	/** 付款金额 */
	private Double money;
	/** 详情 */
	private String detail;

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
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setReson(String reson) 
	{
		this.reson = reson;
	}

	public String getReson() 
	{
		return reson;
	}
	public void setMoney(Double money) 
	{
		this.money = money;
	}

	public Double getMoney() 
	{
		return money;
	}
	public void setDetail(String detail) 
	{
		this.detail = detail;
	}

	public String getDetail() 
	{
		return detail;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("num", getNum())
            .append("name", getName())
            .append("reson", getReson())
            .append("money", getMoney())
            .append("detail", getDetail())
            .toString();
    }
}
