package com.ruoyi.system.domain.finance;

import com.ruoyi.common.utils.DefaultFiled;


import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 报销表 fac_reimburse_apply
 * 
 * @author ruoyi
 * @date 2019-07-31
 */
public class FacReimburseApply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	/** ID */
	private String id;
	/** 报销编号 */
	private String num;
	/** 报销名称 */
	private String name;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 部门名称
	 */
	private  String deptName;
	/** 报销金额 */
	private Double amount;
	/** 报销人 */
	private Long loanUser;
	/** 报销时间 */
	@DefaultFiled(date = "date")
	private Date reimburseTime;
	/** 财务操作 */
	private String facOperate;
	/** 借款事由 */
	private String reason;
	/** 申请人操作 */
	private String applyStatus;
	/** 核票通过时间 */
	private Date passTime;
	/** 类型 */
	@DefaultFiled(msg = "type")
	private String type;
	/**
	 * 状态
	 */
	private  String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
