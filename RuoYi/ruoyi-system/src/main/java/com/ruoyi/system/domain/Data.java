package com.ruoyi.system.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Data {

	private String deptName;
	
	private long deptId;
	
	private int count;

	private String ratio1;//离职率
	
	private String ratio2;//入职率

	/** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date starttime;
	/** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endtime;
    
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

	public String getRatio1() {
		return ratio1;
	}

	public void setRatio1(String ratio1) {
		this.ratio1 = ratio1;
	}

	public String getRatio2() {
		return ratio2;
	}

	public void setRatio2(String ratio2) {
		this.ratio2 = ratio2;
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
