package com.ruoyi.system.domain;

public class AttendanceQvo {
	private String checkType;     //考勤类型  OnDuty：上班 	 OffDuty：下班
    private String userId;        //员工ID
    private long userCheckTime;//实际打卡时间
    private long workDate;      //工作日
    	/*时间结果
		Normal：正常;
		Early：早退;
		Late：迟到;
		SeriousLate：严重迟到；
		Absenteeism：旷工迟到；
		NotSigned：未打卡*/
    private String timeResult;
    
	public String getTimeResult() {
		return timeResult;
	}
	public void setTimeResult(String timeResult) {
		this.timeResult = timeResult;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getUserCheckTime() {
		return userCheckTime;
	}
	public void setUserCheckTime(long userCheckTime) {
		this.userCheckTime = userCheckTime;
	}
	public long getWorkDate() {
		return workDate;
	}
	public void setWorkDate(long workDate) {
		this.workDate = workDate;
	}


}
