package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 假期使用记录表 sys_holiday_record
 * 
 * @author ruoyi
 * @date 2019-06-24
 */
public class HolidayRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 假期使用记录id */
	private Long id;
	/** 假期id */
	private Integer holidayId;
	/** 用户id */
	private Long userId;
	/** 申请id */
	private Long useApplyId;
	/** 使用天数 */
	private Double value;
	/** 使用状态(1申请中，2已使用，3撤销，4销假，5申请失败) */
	private String useState;
	/** 假期类型 */
	private String recordHolidayType;
	
	/** 假期对象 */
	private Holiday holiday;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setHolidayId(Integer holidayId) 
	{
		this.holidayId = holidayId;
	}

	public Integer getHolidayId() 
	{
		return holidayId;
	}
	public void setUseApplyId(Long useApplyId) 
	{
		this.useApplyId = useApplyId;
	}

	public Long getUseApplyId() 
	{
		return useApplyId;
	}
	public void setValue(Double value) 
	{
		this.value = value;
	}

	public Double getValue() 
	{
		return value;
	}
	public void setUseState(String useState) 
	{
		this.useState = useState;
	}

	public String getUseState() 
	{
		return useState;
	}

	public Holiday getHoliday() {
		return holiday;
	}

	public void setHoliday(Holiday holiday) {
		this.holiday = holiday;
	}

	public String getRecordHolidayType() {
		return recordHolidayType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setRecordHolidayType(String recordHolidayType) {
		this.recordHolidayType = recordHolidayType;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("holidayId", getHolidayId())
            .append("useApplyId", getUseApplyId())
            .append("value", getValue())
            .append("useState", getUseState())
            .toString();
    }
}
