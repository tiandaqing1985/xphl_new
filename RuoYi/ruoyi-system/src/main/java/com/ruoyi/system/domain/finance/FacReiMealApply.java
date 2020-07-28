package com.ruoyi.system.domain.finance;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 加班餐报销表 fac_rei_meal_apply
 * 
 * @author ruoyi
 * @date 2020-07-21
 */
public class FacReiMealApply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 报销编号 */
	private String num;
	/** 加班日期 */
	private Date addDate;
	/** 加班人员 */
	private String userName;
	/** 餐费金额 */
	private Double amount;
	/** 加班地点 */
	private String place;
	/** 单据数 */
	private Integer documentNum;
	/** 事由 */
	private String reason;

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
	public void setAddDate(Date addDate) 
	{
		this.addDate = addDate;
	}

	public Date getAddDate() 
	{
		return addDate;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getUserName() 
	{
		return userName;
	}
	public void setAmount(Double amount) 
	{
		this.amount = amount;
	}

	public Double getAmount() 
	{
		return amount;
	}
	public void setPlace(String place) 
	{
		this.place = place;
	}

	public String getPlace() 
	{
		return place;
	}
	public void setDocumentNum(Integer documentNum) 
	{
		this.documentNum = documentNum;
	}

	public Integer getDocumentNum() 
	{
		return documentNum;
	}
	public void setReason(String reason) 
	{
		this.reason = reason;
	}

	public String getReason() 
	{
		return reason;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("num", getNum())
            .append("addDate", getAddDate())
            .append("userName", getUserName())
            .append("amount", getAmount())
            .append("place", getPlace())
            .append("documentNum", getDocumentNum())
            .append("reason", getReason())
            .append("createTime", getCreateTime())
            .toString();
    }
}
