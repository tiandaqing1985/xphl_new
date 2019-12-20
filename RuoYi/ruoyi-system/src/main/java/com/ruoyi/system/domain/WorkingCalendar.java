package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 节假日数据表 working_calendar
 * 
 * @author ruoyi
 * @date 2019-08-27
 */
public class WorkingCalendar extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 日期 */
	private Date date;
	/** 星期 */
	private Integer week;
	/** 是否工作日（0：节假日或周六日，1：工作日） */
	private Integer isWorkDay;
	/** 备注 */
	private String note;
	
	/** 加班开始时间 */
	private Date starttime;
	/** 加班结束时间 */
	private Date endtime;
	
	private String time;
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setDate(Date date) 
	{
		this.date = date;
	}

	public Date getDate() 
	{
		return date;
	}
	public void setWeek(Integer week) 
	{
		this.week = week;
	}

	public Integer getWeek() 
	{
		return week;
	}
	public void setIsWorkDay(Integer isWorkDay) 
	{
		this.isWorkDay = isWorkDay;
	}

	public Integer getIsWorkDay() 
	{
		return isWorkDay;
	}
	public void setNote(String note) 
	{
		this.note = note;
	}

	public String getNote() 
	{
		return note;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("date", getDate())
            .append("week", getWeek())
            .append("isWorkDay", getIsWorkDay())
            .append("note", getNote())
            .toString();
    }
}
