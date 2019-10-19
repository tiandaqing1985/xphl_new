package com.ruoyi.system.domain.finance;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 团建申请表 fac_collect_apply
 * 
 * @author ruoyi
 * @date 2019-09-03
 */
public class FacCollectApply extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**  */
	private Long id;
	/** 团建费编号 */
	private String num;
	/** 所属公司 */
	private String deptCompany;
	/** 所属部门 */
	private String deptName;
	/** 申请人 */
	private Long applicant;
	/** 申请金额 */
	private Double amount;
	/** 申请时间 */
	private Date applicationTime;
	/** 开始日期 */
	private Date startDate;
	/** 结束日期 */
	private Date endDate;
	/** 团建项目 */
	private String leagueProject;
	/** 参与人 */
	private String participants;
	/** 参与人数 */
	private Long parNumber;
	/** 活动地点 */
	private String activityPlace;
	/** 活动形式 */
	private String activityForm;
	/** 审批状态 */
	private String status;
	/** 备注 */
	private String remarks;
	private String applicantName;

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setNum(String num) {
		this.num = num;
	}

	public String getNum() {
		return num;
	}
	public void setDeptCompany(String deptCompany) {
		this.deptCompany = deptCompany;
	}

	public String getDeptCompany() {
		return deptCompany;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptName() {
		return deptName;
	}
	public void setApplicant(Long applicant) {
		this.applicant = applicant;
	}

	public Long getApplicant() {
		return applicant;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	public Date getApplicationTime() {
		return applicationTime;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartDate() {
		return startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	public void setLeagueProject(String leagueProject) {
		this.leagueProject = leagueProject;
	}

	public String getLeagueProject() {
		return leagueProject;
	}
	public void setParticipants(String participants) {
		this.participants = participants;
	}

	public String getParticipants() {
		return participants;
	}
	public void setParNumber(Long parNumber) {
		this.parNumber = parNumber;
	}

	public Long getParNumber() {
		return parNumber;
	}
	public void setActivityPlace(String activityPlace) {
		this.activityPlace = activityPlace;
	}

	public String getActivityPlace() {
		return activityPlace;
	}
	public void setActivityForm(String activityForm) {
		this.activityForm = activityForm;
	}

	public String getActivityForm() {
		return activityForm;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId()).append("num", getNum())
				.append("deptCompany", getDeptCompany())
				.append("deptName", getDeptName())
				.append("applicant", getApplicant())
				.append("amount", getAmount())
				.append("applicationTime", getApplicationTime())
				.append("startDate", getStartDate())
				.append("endDate", getEndDate())
				.append("leagueProject", getLeagueProject())
				.append("participants", getParticipants())
				.append("parNumber", getParNumber())
				.append("activityPlace", getActivityPlace())
				.append("activityForm", getActivityForm())
				.append("status", getStatus()).append("remarks", getRemarks())
				.toString();
	}
}
