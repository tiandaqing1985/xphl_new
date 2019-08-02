package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

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
	/** 开始时间 */
	private Date startDate;
	/** 结束时间 */
	private Date endDate;
	/** 申请状态（1 待审批，2已撤回，3申请成功，4申请失败） */
	private String state;
	/** 人事确认状态（0未确认，2已确认） */
	private String hrState;
	/** 创建时间 */
	private Date createDate;
	/** 理由 */
	private String reason;

	public void setOutId(Long outId) 
	{
		this.outId = outId;
	}

	public Long getOutId() 
	{
		return outId;
	}
	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}

	public Long getUserId() 
	{
		return userId;
	}
	public void setStartDate(Date startDate) 
	{
		this.startDate = startDate;
	}

	public Date getStartDate() 
	{
		return startDate;
	}
	public void setEndDate(Date endDate) 
	{
		this.endDate = endDate;
	}

	public Date getEndDate() 
	{
		return endDate;
	}
	public void setState(String state) 
	{
		this.state = state;
	}

	public String getState() 
	{
		return state;
	}
	public void setHrState(String hrState) 
	{
		this.hrState = hrState;
	}

	public String getHrState() 
	{
		return hrState;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}
	public void setReason(String reason) 
	{
		this.reason = reason;
	}

	public String getReason() 
	{
		return reason;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("outId", getOutId())
            .append("userId", getUserId())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("state", getState())
            .append("hrState", getHrState())
            .append("createDate", getCreateDate())
            .append("reason", getReason())
            .toString();
    }
}
