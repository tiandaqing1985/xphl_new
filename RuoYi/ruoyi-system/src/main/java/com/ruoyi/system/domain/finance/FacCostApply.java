package com.ruoyi.system.domain.finance;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity; 

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

/**
 * 差旅申请表 fac_cost_apply
 * 
 * @author ruoyi
 * @date 2019-09-02
 */
public class FacCostApply extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** ID */
	private Long id;
	/** 出差编号 */
	private String num;
	/** 项目名称 */
	private String busName;
	/** 出差时间 */
	private Date outTime;
	/** 预计返回时间 */
	private Date backTimeEs;
	/** 出差人员 */
	private String outMan;
	/** 申请人 */
	private long userId;
	/** 预计费用 */
	private BigDecimal moneyEs;
	/** 出差目的地 */
	private String toLocal;
	/** 审批状态 */
	private String status;
	/** 事由 */
	private String reason;
	/**差旅申请详细列表*/
	private List<FacCostDetailApply> facCostDetail;

	public List<FacCostDetailApply> getFacCostDetail() {
		return facCostDetail;
	}

	public void setFacCostDetail(List<FacCostDetailApply> facCostDetail) {
		this.facCostDetail = facCostDetail;
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
	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusName() {
		return busName;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public Date getOutTime() {
		return outTime;
	}
	public void setBackTimeEs(Date backTimeEs) {
		this.backTimeEs = backTimeEs;
	}

	public Date getBackTimeEs() {
		return backTimeEs;
	}
	public void setOutMan(String outMan) {
		this.outMan = outMan;
	}

	public String getOutMan() {
		return outMan;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setMoneyEs(BigDecimal moneyEs) {
		this.moneyEs = moneyEs;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getMoneyEs() {
		return moneyEs;
	}
	public void setToLocal(String toLocal) {
		this.toLocal = toLocal;
	}

	public String getToLocal() {
		return toLocal;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId()).append("num", getNum())
				.append("busName", getBusName()).append("outTime", getOutTime())
				.append("backTimeEs", getBackTimeEs())
				.append("outMan", getOutMan()).append("moneyEs", getMoneyEs())
				.append("toLocal", getToLocal()).append("reason", getReason())
				.toString();
	}
}
