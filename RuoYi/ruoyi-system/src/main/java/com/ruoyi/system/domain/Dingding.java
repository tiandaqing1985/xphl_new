package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
import java.util.Set;

/**
 * 钉钉考勤传输表
 * 
 * @author ruoyi
 * @date 2019-07-26
 */
public class Dingding extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
    /** 用户ID */
    @Excel(name = "用户序号", prompt = "用户编号")
    private Long userId;
    
    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;
    
	/** 打卡日期 */
	private Date workDate;
	/** 考勤类型 */
	private String checkType;
	/** 实际打卡时间 */
	private Date userCheckTime;
	/** 时间结果
Normal：正常;Early：早退;Late：迟到;SeriousLate：严重迟到；Absenteeism：旷工迟到；NotSigned：未打卡 */
	private String timeResult;

    /**
     * 部门对象集合
     */
    private Set<SysDept> dSet;
    
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Set<SysDept> getdSet() {
		return dSet;
	}

	public void setdSet(Set<SysDept> dSet) {
		this.dSet = dSet;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setWorkDate(Date workDate) 
	{
		this.workDate = workDate;
	}

	public Date getWorkDate() 
	{
		return workDate;
	}
	public void setCheckType(String checkType) 
	{
		this.checkType = checkType;
	}

	public String getCheckType() 
	{
		return checkType;
	}
	public void setUserCheckTime(Date userCheckTime) 
	{
		this.userCheckTime = userCheckTime;
	}

	public Date getUserCheckTime() 
	{
		return userCheckTime;
	}
	public void setTimeResult(String timeResult) 
	{
		this.timeResult = timeResult;
	}

	public String getTimeResult() 
	{
		return timeResult;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("workDate", getWorkDate())
            .append("checkType", getCheckType())
            .append("userCheckTime", getUserCheckTime())
            .append("timeResult", getTimeResult())
            .toString();
    }
}
