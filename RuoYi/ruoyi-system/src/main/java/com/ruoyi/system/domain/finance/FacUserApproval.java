package com.ruoyi.system.domain.finance;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 财务审批表 fac_sys_user_approval
 * 
 * @author ruoyi
 * @date 2019-09-27
 */
public class FacUserApproval extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 审批记录ID */
	private Long approvalId;
	/** 申请编号 */
	private String applyId;
	/** 审批意见（1同意，2驳回 ，3未操作） */
	private String approvalState;
	/** 审批人ID */
	private Long approverId;
	/** 审批操作时间 */
	private Date approvalTime;
	/** 可见性（1可见 0不可见） */
	private String approvalSight;
	/** 审批等级 */
	private Integer approvalLevel;
	/** 审批意见 */
	private String opi;
	/** 申请人ID */
	private Long applicantId;
	private Double amount;
	private String applicantName;
	private String approverName;
	//报销名称
	private String name;
	private String projectName;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public Long getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(Long approvalId) {
		this.approvalId = approvalId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getApplyId() {
		return applyId;
	}
	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}

	public String getApprovalState() {
		return approvalState;
	}
	public void setApproverId(Long approverId) {
		this.approverId = approverId;
	}

	public Long getApproverId() {
		return approverId;
	}
	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	public Date getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalSight(String approvalSight) {
		this.approvalSight = approvalSight;
	}

	public String getApprovalSight() {
		return approvalSight;
	}
	public void setApprovalLevel(Integer approvalLevel) {
		this.approvalLevel = approvalLevel;
	}

	public Integer getApprovalLevel() {
		return approvalLevel;
	}
	public void setOpi(String opi) {
		this.opi = opi;
	}

	public String getOpi() {
		return opi;
	}

	public Long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("approvalId", getApprovalId())
				.append("applyId", getApplyId())
				.append("approvalState", getApprovalState())
				.append("approverId", getApproverId())
				.append("approvalTime", getApprovalTime())
				.append("approvalSight", getApprovalSight())
				.append("approvalLevel", getApprovalLevel())
				.append("opi", getOpi()).append("createTime", getCreateTime())
				.append("applicantId", getApplicantId()).toString();
	}
}
