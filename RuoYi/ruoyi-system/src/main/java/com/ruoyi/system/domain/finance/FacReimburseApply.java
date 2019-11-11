package com.ruoyi.system.domain.finance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.DefaultFiled;

import java.util.Date;
import java.util.List;

/**
 * 报销表 fac_reimburse_apply
 *
 * @author ruoyi
 * @date 2019-07-31
 */
public class FacReimburseApply extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private Long id;
	/**
	 *
	 */
	private String num;
	/**
	 * 报销名称
	 */
	private String name;
	/**
	 * 报销人名称
	 */
	private String userName;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 报销金额
	 */
	private Double amount;
	/**
	 * 报销人
	 */
	private Long loanUser;
	/**
	 * 报销时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date reimburseTime;
	/**
	 * 财务操作
	 */
	private String facOperate;
	/**
	 * 借款事由
	 */
	private String reason;
	/**
	 * 申请人操作
	 */
	private String applyStatus;
	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 核票通过时间
	 */
	private Date passTime;
	/**
	 * 类型
	 */
	@DefaultFiled(msg = "type")
	private String type;
	/**
	 * 状态
	 */
	private String status;

	private String JKnum;

	/**
	 * 提交状态 save,submit
	 */
	private String submitStatus;
	/**
	 * 公出交通申请
	 */
	private List<ReiTrafficApply> trafficReiApplyList;

	/**
	 * 其他费用报销申请
	 */
	private List<FacReiAdiApply> otherReiAdiApplies;

	/**
	 * 招待费报销申请
	 */
	private List<ReiHospitalityApply> hospitalityApplies;

	private String approveName;

	/** 审批人姓名 **/
	private String approver;

	private String approvalStatus;

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApproveName() {
		return approveName;
	}

	public void setApproveName(String approveName) {
		this.approveName = approveName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<ReiTrafficApply> getTrafficReiApplyList() {
		return trafficReiApplyList;
	}

	public void setTrafficReiApplyList(
			List<ReiTrafficApply> trafficReiApplyList) {
		this.trafficReiApplyList = trafficReiApplyList;
	}

	public List<FacReiAdiApply> getOtherReiAdiApplies() {
		return otherReiAdiApplies;
	}

	public void setOtherReiAdiApplies(List<FacReiAdiApply> otherReiAdiApplies) {
		this.otherReiAdiApplies = otherReiAdiApplies;
	}

	public String getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
	}

	public String getJKnum() {
		return JKnum;
	}

	public void setJKnum(String jKnum) {
		JKnum = jKnum;
	}

	public List<ReiHospitalityApply> getHospitalityApplies() {
		return hospitalityApplies;
	}

	public void setHospitalityApplies(
			List<ReiHospitalityApply> hospitalityApplies) {
		this.hospitalityApplies = hospitalityApplies;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getLoanUser() {
		return loanUser;
	}

	public void setLoanUser(Long loanUser) {
		this.loanUser = loanUser;
	}

	public Date getReimburseTime() {
		return reimburseTime;
	}

	public void setReimburseTime(Date reimburseTime) {
		this.reimburseTime = reimburseTime;
	}

	public String getFacOperate() {
		return facOperate;
	}

	public void setFacOperate(String facOperate) {
		this.facOperate = facOperate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public Date getPassTime() {
		return passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
