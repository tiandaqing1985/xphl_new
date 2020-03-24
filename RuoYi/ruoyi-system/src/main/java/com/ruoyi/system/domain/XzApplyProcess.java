package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 采购审批流程表 xz_apply_process
 * 
 * @author ruoyi
 * @date 2019-12-06
 */
public class XzApplyProcess extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 审批人 */
	private String approverId;
	/** 审批等级 */
	private String level;
	/** 审批状态 */
	private String status;
	/** 审批时间 */
	private Date appTime;

	private Long applyId;

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setApproverId(String approverId) 
	{
		this.approverId = approverId;
	}

	public String getApproverId() 
	{
		return approverId;
	}
	public void setLevel(String level) 
	{
		this.level = level;
	}

	public String getLevel() 
	{
		return level;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setAppTime(Date appTime) 
	{
		this.appTime = appTime;
	}

	public Date getAppTime() 
	{
		return appTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("approverId", getApproverId())
            .append("level", getLevel())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("appTime", getAppTime())
            .toString();
    }
}
