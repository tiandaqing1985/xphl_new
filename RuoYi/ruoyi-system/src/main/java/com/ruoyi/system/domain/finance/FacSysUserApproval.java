package com.ruoyi.system.domain.finance;

import com.ruoyi.common.utils.DefaultFiled;

import java.util.Date;

/**
 * @program: ruoyi->FacSysUserApproval
 * @author: gakki
 * @create: 2019-07-31 14:40
 **/
public class FacSysUserApproval {
	/** 审批记录ID */
	private Long approvalId;
	/** 申请ID */
	private String applyId;
	/** 审批状态（1通过，2驳回，3未操作） */
	private String approvalState;
	/** 审批人ID */
	private Long approverId;
	/** 可视性（0不可见，1可见） */
	private String approvalSight;
	/** 审批时间 */
	private Date approvalTime;
	/** 审批等级 */
	private Integer approvalLevel;
	/**
	 * 审批意见
	 */
	private String opi;

	/**
	 * 编号
	 **/

	private Long applicantId;

	/**
	 * 申请创建时间
	 */
	@DefaultFiled(date = "date")
	private Date createTime;

	private Double amount;

	private String projectName;


	private String deptName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(Long approvalId) {
		this.approvalId = approvalId;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getApprovalState() {
		return approvalState;
	}

	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}

	public Long getApproverId() {
		return approverId;
	}

	public void setApproverId(Long approverId) {
		this.approverId = approverId;
	}

	public String getApprovalSight() {
		return approvalSight;
	}

	public void setApprovalSight(String approvalSight) {
		this.approvalSight = approvalSight;
	}

	public Date getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	public Integer getApprovalLevel() {
		return approvalLevel;
	}

	public void setApprovalLevel(Integer approvalLevel) {
		this.approvalLevel = approvalLevel;
	}

	public String getOpi() {
		return opi;
	}

	public void setOpi(String opi) {
		this.opi = opi;
	}

	public Long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
