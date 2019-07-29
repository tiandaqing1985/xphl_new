package com.ruoyi.system.domain;

import java.util.Date;

public class UserApplyList {
	
	/** 用户ID */
	private Long userId;
	
	/** 申请人姓名*/
	private String applyerName;
	
	/** 申请单号 */
	private String listNum;
	
	/** 申请类型（1请假，2加班） */
	private String applyType;
	
	/** 申请状态（1“”，2审批中，3审批完） */
	private String applyState;
	
	/** 请假类型 */
	private String leaveType;
	
	/** 申请时间 */
	private Date applyTime;

	/** 撤销状态（1正常，2已撤销） */
	private String undoState;
	
	/** 审批人姓名*/
	private String approverName;
	
	/** 时长 */
	private Double timelength;
	
	public String getApplyerName() {
		return applyerName;
	}

	public void setApplyerName(String applyerName) {
		this.applyerName = applyerName;
	}

	public String getListNum() {
		return listNum;
	}

	public void setListNum(String listNum) {
		this.listNum = listNum;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getApplyState() {
		return applyState;
	}

	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}

	public Double getTimelength() {
		return timelength;
	}

	public void setTimelength(Double timelength) {
		this.timelength = timelength;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getUndoState() {
		return undoState;
	}

	public void setUndoState(String undoState) {
		this.undoState = undoState;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	
}
