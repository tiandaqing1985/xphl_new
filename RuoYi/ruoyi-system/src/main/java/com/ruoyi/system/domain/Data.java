package com.ruoyi.system.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Data {

	private String deptName;
	
	private long deptId;
	
	private int count;//团建人数
	
	private int status;//帐号状态（0正常 1停用）
	
	private String ratio;//入离职率
	

	/** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date starttime;
	/** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endtime;
    
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
