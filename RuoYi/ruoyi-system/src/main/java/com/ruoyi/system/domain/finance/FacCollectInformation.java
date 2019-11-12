package com.ruoyi.system.domain.finance;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 团建费报销表 fac_collect_information
 * 
 * @author ruoyi
 * @date 2019-11-07
 */
public class FacCollectInformation extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 项目编号 */
	private String num;
	/** 项目名称 */
	private String name;
	/** 金额 */
	private Double amount;
	/** 单据数 */
	private Long number;
	/** 实际金额 */
	private Double money;

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

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
	public void setAmount(Double amount) 
	{
		this.amount = amount;
	}

	public Double getAmount() 
	{
		return amount;
	}
	public void setNumber(Long number) 
	{
		this.number = number;
	}

	public Long getNumber() 
	{
		return number;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("num", getNum())
            .append("name", getName())
            .append("amount", getAmount())
            .append("number", getNumber())
            .toString();
    }
}
