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
	/** 用户名 */
	private String userName;
	/** 打卡日期 */
	private Date workDate;
	/** 星期几 */
	private String weekDay;
	/** 考勤类型 OnDuty：上班；OffDuty：下班 */
	private String checkType;
	/** 实际打卡时间 */
	private Date userCheckTime;
	/** 时间结果
Normal：正常;Early：早退;Late：迟到;SeriousLate：严重迟到；Absenteeism：旷工迟到；NotSigned：未打卡 */
	private String timeResult;
	/** 是否更新在钉钉考勤表中：0 未更新  1已更新 */
	private String status;
	/**申请状态（1 待审批，2已撤回，3申请成功，4申请失败）*/
	private String applyState;
    /** 部门名称 */
    private String deptName;
    /** 路径 */
	private String path;
	/** 图片上传时间 */
	private Date createTime;
	
	private String time;
	
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getApplyState() {
		return applyState;
	}

	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

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

	@Override
	public String toString() {
		return "OaDingding [userId=" + userId + ", userName=" + userName + ", workDate=" + workDate + ", weekDay="
				+ weekDay + ", checkType=" + checkType + ", userCheckTime=" + userCheckTime + ", timeResult="
				+ timeResult + ", status=" + status + ", applyState=" + applyState + ", deptName=" + deptName
				+ ", path=" + path + ", createTime=" + createTime + "]";
	}
}
