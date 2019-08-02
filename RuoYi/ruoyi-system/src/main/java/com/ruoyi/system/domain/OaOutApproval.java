package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 外出报备审批表 oa_out_approval
 * 
 * @author ruoyi
 * @date 2019-08-01
 */
public class OaOutApproval extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 唯一标识 */
	private Long id;
	/** 外出报备id */
	private Long outId;
	/** 审批状态（1同意，2驳回 ，3未操作） */
	private String approvalState;
	/** 审批人user_id */
	private Long approvalId;
	/** 审批时间 */
	private Date approvalDate;
	/** 1可见  0不可见 */
	private String approvalSight;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setOutId(Long outId) 
	{
		this.outId = outId;
	}

	public Long getOutId() 
	{
		return outId;
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
	public void setApprovalSight(String approvalSight) 
	{
		this.approvalSight = approvalSight;
	}

	public String getApprovalSight() 
	{
		return approvalSight;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("outId", getOutId())
            .append("approvalState", getApprovalState())
            .append("approvalId", getApprovalId())
            .append("approvalDate", getApprovalDate())
            .append("approvalSight", getApprovalSight())
            .append("remark", getRemark())
            .toString();
    }
}
