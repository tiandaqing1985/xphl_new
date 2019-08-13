package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
import java.util.Set;

/**
 * 外出报备表 oa_out
 * 
 * @author ruoyi
 * @date 2019-08-01
 */
public class OaOut extends BaseEntity
{

	private static final long serialVersionUID = 1L;
	
	/** 唯一标识 */
	private Long outId;
	/** 用户id */
	private Long userId;
	/** 用户名 */
	private String userName;
	/** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date starttime;
	/** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endtime;
	/** 申请状态（1 待审批，2已撤回，3申请成功，4申请失败） */
	private String state;
	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;
	/** 理由 */
	private String reason;
	/** 联合钉钉考勤：0初始值 1报备记录已更新在钉钉考勤 */
	private String status;
	
	/** 审批人id */
	private Long approvalId;
	/** 审批状态（1同意，2驳回 ，3未操作）*/
	private String approvalState;
	/** 部门集合 */
	private Set<SysDept> dSet;
	/** 唯一标识 */
	private Long id;
	/** 1可见  0不可见 */
	private String approvalSight;
	/** 申请人 */
	private String applyerName;
	/** 部门  */
	private String deptName;
	/** 审批意见  */
	private String remark;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getApplyerName() {
		return applyerName;
	}
	public void setApplyerName(String applyerName) {
		this.applyerName = applyerName;
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
	public Long getOutId() {
		return outId;
	}
	public void setOutId(Long outId) {
		this.outId = outId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Long getApprovalId() {
		return approvalId;
	}
	public void setApprovalId(Long approvalId) {
		this.approvalId = approvalId;
	}
	public String getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}
	public Set<SysDept> getdSet() {
		return dSet;
	}
	public void setdSet(Set<SysDept> dSet) {
		this.dSet = dSet;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "OaOut [outId=" + outId + ", userId=" + userId + ", starttime=" + starttime + ", endtime=" + endtime
				+ ", state=" + state +", createDate=" + createDate + ", reason=" + reason
				+ ", approvalId=" + approvalId + ", approvalState=" + approvalState + ", dSet=" + dSet + ", id=" + id
				+ ", approvalSight=" + approvalSight + ", applyerName=" + applyerName + ", deptName=" + deptName
				+ ", remark=" + remark + "]";
	}
	
	
}
