package com.ruoyi.system.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Data {

	private String deptName;
	
	private int count;

	private String ratio;//比率
	
	/** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date starttime;
	/** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endtime;
    
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
