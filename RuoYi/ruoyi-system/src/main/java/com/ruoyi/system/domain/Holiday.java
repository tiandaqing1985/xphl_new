package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 假期表 sys_holiday
 * 
 * @author ruoyi
 * @date 2019-06-17
 */
/**
 * @author ziyoushazi
 *
 */
public class Holiday extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 假期id */
	private Integer id;
	/** 员工id */
	private Long userId;
	private Long applyId;
	/** 假期类型（1年假，2调休） */
	private String holidayType;
	/** 是否有效（0否 1是） */
	private String availability;
	/** 生成（效）日期 */
	private String createdate;
	/** 失效日期 */
	private String overdate;
	/** 年假值（天） */
	private Double value;
	/** 备注 */
	private String holidayDetail;
	/** 日期 */
	private Date idate;

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}

	public Long getUserId() 
	{
		return userId;
	}
	public void setHolidayType(String holidayType) 
	{
		this.holidayType = holidayType;
	}

	public String getHolidayType() 
	{
		return holidayType;
	}
	public void setAvailability(String availability) 
	{
		this.availability = availability;
	}

	public String getAvailability() 
	{
		return availability;
	}
	public void setCreatedate(String createdate) 
	{
		this.createdate = createdate;
	}

	public String getCreatedate() 
	{
		return createdate;
	}
	public void setOverdate(String overdate) 
	{
		this.overdate = overdate;
	}

	public String getOverdate() 
	{
		return overdate;
	}
	public void setValue(Double value) 
	{
		this.value = value;
	}

	public Double getValue() 
	{
		return value;
	}

	public Date getIdate() {
		return idate;
	}

	public void setIdate(Date idate) {
		this.idate = idate;
	}

	public String getHolidayDetail() {
		return holidayDetail;
	}

	public void setHolidayDetail(String holidayDetail) {
		this.holidayDetail = holidayDetail;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("holidayType", getHolidayType())
            .append("availability", getAvailability())
            .append("createdate", getCreatedate())
            .append("overdate", getOverdate())
            .append("value", getValue())
            .toString();
    }
}
