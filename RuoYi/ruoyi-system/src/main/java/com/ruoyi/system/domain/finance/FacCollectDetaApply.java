package com.ruoyi.system.domain.finance;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 团建明细表 fac_collect_deta_apply
 * 
 * @author ruoyi
 * @date 2019-09-04
 */
public class FacCollectDetaApply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 团建编号 */
	private String num;
	/** 部门名称 */
	private String depaName;
	/** 地区 */
	private String region;
	/** 团建人数 */
	private String number;
	/** 上月结转 */
	private Double lastMonth;
	/** 预算额度 */
	private Double budgetMargin;
	/** 剩余额度 */
	private Double remaMargin;
	/** 部门负责人账号 */
	private String email;
	/** 团建时间 */
	private Date collectTime;

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
	public void setDepaName(String depaName) 
	{
		this.depaName = depaName;
	}

	public String getDepaName() 
	{
		return depaName;
	}
	public void setRegion(String region) 
	{
		this.region = region;
	}

	public String getRegion() 
	{
		return region;
	}
	public void setNumber(String number) 
	{
		this.number = number;
	}

	public String getNumber() 
	{
		return number;
	}
	public void setLastMonth(Double lastMonth) 
	{
		this.lastMonth = lastMonth;
	}

	public Double getLastMonth() 
	{
		return lastMonth;
	}
	public void setBudgetMargin(Double budgetMargin) 
	{
		this.budgetMargin = budgetMargin;
	}

	public Double getBudgetMargin() 
	{
		return budgetMargin;
	}
	public void setRemaMargin(Double remaMargin) 
	{
		this.remaMargin = remaMargin;
	}

	public Double getRemaMargin() 
	{
		return remaMargin;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getEmail() 
	{
		return email;
	}
	public void setCollectTime(Date collectTime) 
	{
		this.collectTime = collectTime;
	}

	public Date getCollectTime() 
	{
		return collectTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("num", getNum())
            .append("depaName", getDepaName())
            .append("region", getRegion())
            .append("number", getNumber())
            .append("lastMonth", getLastMonth())
            .append("budgetMargin", getBudgetMargin())
            .append("remaMargin", getRemaMargin())
            .append("email", getEmail())
            .append("collectTime", getCollectTime())
            .toString();
    }
}
