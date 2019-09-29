package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 采购申请审批表 xz_purchase_approval
 * 
 * @author ruoyi
 * @date 2019-09-24
 */
public class XzPurchaseApproval extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 唯一标识 */
	private Long id;
	/** 采购申请id */
	private Long applyId;
	/** 审批状态（1同意，2驳回 ，3未操作） */
	private String approvalState;
	/** 审批人user_id */
	private Long approvalId;
	private String approvalName;
	/** 审批时间 */
	private Date approvalDate;
	/** 备注 */
    private String remarks;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setApplyId(Long applyId) 
	{
		this.applyId = applyId;
	}

	public Long getApplyId() 
	{
		return applyId;
	}
	public void setApprovalState(String approvalState) 
	{
		this.approvalState = approvalState;
	}

	public String getApprovalState() 
	{
		return approvalState;
	}
	public void setApprovalId(Long approvalId) 
	{
		this.approvalId = approvalId;
	}

	public Long getApprovalId() 
	{
		return approvalId;
	}
	public void setApprovalDate(Date approvalDate) 
	{
		this.approvalDate = approvalDate;
	}

	public Date getApprovalDate() 
	{
		return approvalDate;
	}

	public String getApprovalName() {
		return approvalName;
	}

	public void setApprovalName(String approvalName) {
		this.approvalName = approvalName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
