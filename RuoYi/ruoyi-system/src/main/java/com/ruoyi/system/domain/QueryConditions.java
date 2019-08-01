package com.ruoyi.system.domain;

import java.util.Date;
import java.util.Set;

public class QueryConditions {
	/** 用户id */
	private long userId;
	/** 用户名*/
	private String userName;
	
	/** 审批记录ID */
	private Long approvalId;
	/** 审批人ID  */
	private Long approverId;
	/** 申请ID  */
	private Long applyId;
	/** 申请人ID  */
	private Long applyerId;
	/** 申请单号 */
	private String listNum;
	/** 申请人姓名 */
	private String applyerName;
	/** 审批人姓名 */
	private String approverName;
	/** 申请类型（1请假，2加班） */
	private String applyType;
	/** 请假类型 */
	private String leaveType;
	/** 时长 */
	private Double timelength;
	/** 申请状态（1保存，2审批中，3审批完） */
	private String applyState;
	/** 申请时间*/
	private String applyTime;
	/** 审批时间*/
	private String approvalTime;
	/** 审批意见（1通过，2驳回，3未操作） */
	private String approvalState;
	/** 部门名称  */
	private String deptName;
	/** 可视性（0不可见，1可见） */
	private String approvalSight;
	/** 有时间后缀的时长*/
	private String timelengthPlus;
	/** 开始时间 */
	private Date starttime;
	/** 结束时间 */
	private Date endtime;
	/** 证明附件 */
	private String prove;
	/** 详情 */
	private String details;
	
	private long status;//1我的审批
	
	/**部门对象集合*/
	private Set<SysDept> dSet;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getProve() {
		return prove;
	}
	public void setProve(String prove) {
		this.prove = prove;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Set<SysDept> getdSet() {
		return dSet;
	}
	public void setdSet(Set<SysDept> dSet) {
		this.dSet = dSet;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	public String getTimelengthPlus() {
		return timelengthPlus;
	}
	public void setTimelengthPlus(String timelengthPlus) {
		this.timelengthPlus = timelengthPlus;
	}
	public String getApplyerName() {
		return applyerName;
	}
	public void setApplyerName(String applyerName) {
		this.applyerName = applyerName;
	}
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public Double getTimelength() {
		return timelength;
	}
	public void setTimelength(Double timelength) {
		this.timelength = timelength;
	}
	public String getApplyState() {
		return applyState;
	}
	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}
	public String getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getApprovalSight() {
		return approvalSight;
	}
	public void setApprovalSight(String approvalSight) {
		this.approvalSight = approvalSight;
	}
	public Long getApprovalId() {
		return approvalId;
	}
	public void setApprovalId(Long approvalId) {
		this.approvalId = approvalId;
	}
	public String getListNum() {
		return listNum;
	}
	public void setListNum(String listNum) {
		this.listNum = listNum;
	}
	
	public Long getApproverId() {
		return approverId;
	}
	public void setApproverId(Long approverId) {
		this.approverId = approverId;
	}
	
	public Long getApplyerId() {
		return applyerId;
	}
	public void setApplyerId(Long apprlyerId) {
		this.applyerId = apprlyerId;
	}
	
	public Long getApplyId() {
		return applyId;
	}
	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}
	@Override
	public String toString() {
		return "QueryConditions [approvalId=" + approvalId + ", approverId=" + approverId + ", applyId=" + applyId
				+ ", applyerId=" + applyerId + ", listNum=" + listNum + ", applyerName=" + applyerName
				+ ", approverName=" + approverName + ", applyType=" + applyType + ", leaveType=" + leaveType
				+ ", timelength=" + timelength + ", applyState=" + applyState + ", applyTime=" + applyTime
				+ ", approvalTime=" + approvalTime + ", approvalState=" + approvalState + ", deptName=" + deptName
				+ ", approvalSight=" + approvalSight + ", timelengthPlus=" + timelengthPlus + "]";
	}	
}
