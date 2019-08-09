package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 钉钉考勤数据表 oa_dingding
 * 
 * @author ruoyi
 * @date 2019-08-08
 */
public class OaDingding extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 用户id */
	private Long userId;
	/** 打卡日期 */
	private Date workDate;
	/** 考勤类型 OnDuty：上班；OffDuty：下班 */
	private String checkType;
	/** 实际打卡时间 */
	private Date userCheckTime;
	/** 时间结果
Normal：正常;Early：早退;Late：迟到;SeriousLate：严重迟到；Absenteeism：旷工迟到；NotSigned：未打卡 */
	private String timeResult;
	/** 请假类型 */
	private String leaveType;
	/** 是否更新在钉钉考勤表中：0 未更新  1已更新 */
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}

	public Long getUserId() 
	{
		return userId;
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
	public void setLeaveType(String leaveType) 
	{
		this.leaveType = leaveType;
	}

	public String getLeaveType() 
	{
		return leaveType;
	}

	@Override
	public String toString() {
		return "OaDingding [userId=" + userId + ", workDate=" + workDate + ", checkType=" + checkType
				+ ", userCheckTime=" + userCheckTime + ", timeResult=" + timeResult + ", leaveType=" + leaveType
				+ ", status=" + status + "]";
	}

}
