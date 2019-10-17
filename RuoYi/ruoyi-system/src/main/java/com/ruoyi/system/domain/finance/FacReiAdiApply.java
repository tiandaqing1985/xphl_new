package com.ruoyi.system.domain.finance;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 其他报销表 fac_rei_adi_apply
 * 
 * @author ruoyi
 * @date 2019-10-16
 */
public class FacReiAdiApply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;
	/** 日期 */
	private Date ddDate;
	/** 金额 */
	private Double amount;
	/** 科目 */
	private String project;
	/** 单据数 */
	private Integer documentNum;
	/** 事由 */
	private String reason;
	/** 审批状态 */
	private String status;
	/** 报销编号 */
	private String num;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setDdDate(Date ddDate) 
	{
		this.ddDate = ddDate;
	}

	public Date getDdDate() 
	{
		return ddDate;
	}
	public void setAmount(Double amount) 
	{
		this.amount = amount;
	}

	public Double getAmount() 
	{
		return amount;
	}
	public void setProject(String project) 
	{
		this.project = project;
	}

	public String getProject() 
	{
		return project;
	}
	public void setDocumentNum(Integer documentNum) 
	{
		this.documentNum = documentNum;
	}

	public Integer getDocumentNum() 
	{
		return documentNum;
	}
	public void setReason(String reason) 
	{
		this.reason = reason;
	}

	public String getReason() 
	{
		return reason;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setNum(String num) 
	{
		this.num = num;
	}

	public String getNum() 
	{
		return num;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ddDate", getDdDate())
            .append("amount", getAmount())
            .append("project", getProject())
            .append("documentNum", getDocumentNum())
            .append("reason", getReason())
            .append("status", getStatus())
            .append("num", getNum())
            .append("createTime", getCreateTime())
            .toString();
    }
}
