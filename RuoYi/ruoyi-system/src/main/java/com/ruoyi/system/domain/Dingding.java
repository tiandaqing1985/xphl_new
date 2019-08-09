package com.ruoyi.system.domain;

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
	/** 请假类型 */
	private String leaveType;
	/** 是否更新在钉钉考勤表中：0 未更新  1已更新 */
	private String status;
    /**
     * 部门对象集合
     */
    private Set<SysDept> dSet;
	
	private Date startTime;
	
	private Date endTime;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
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

	@Override
	public String toString() {
		return "Dingding [userId=" + userId + ", userName=" + userName + ", workDate=" + workDate + ", checkType="
				+ checkType + ", userCheckTime=" + userCheckTime + ", timeResult=" + timeResult + ", leaveType="
				+ leaveType + ", status=" + status + ", dSet=" + dSet + ", startTime=" + startTime + ", endTime="
				+ endTime + "]";
	}

 
}
