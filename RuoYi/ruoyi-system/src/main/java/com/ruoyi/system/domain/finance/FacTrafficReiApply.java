package com.ruoyi.system.domain.finance;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 交通报销申请表 fac_traffic_rei_apply
 * 
 * @author ruoyi
 * @date 2019-10-30
 */
public class FacTrafficReiApply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 日期 */
	private Date ddDate;
	/** 目标单位简称 */
	private String targetUnit;
	/** 金额 */
	private Double amount;
	/** 人员 */
	private String user;
	/** 出发地 */
	private String departure;
	/** 目的地 */
	private String target;
	/** 申请人id */
	private Long applyUser;
	/** 单据数 */
	private Integer documentNum;
	/** 事由 */
	private String reason;
	/** 类型(1:公出 2：加班) */
	private String type;
	/** 报销编号 */
	private String num;
	/** 申请状态 */
	private String status;
	private String deptName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
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
	public void setTargetUnit(String targetUnit) 
	{
		this.targetUnit = targetUnit;
	}

	public String getTargetUnit() 
	{
		return targetUnit;
	}
	public void setAmount(Double amount) 
	{
		this.amount = amount;
	}

	public Double getAmount() 
	{
		return amount;
	}
	public void setUser(String user) 
	{
		this.user = user;
	}

	public String getUser() 
	{
		return user;
	}
	public void setDeparture(String departure) 
	{
		this.departure = departure;
	}

	public String getDeparture() 
	{
		return departure;
	}
	public void setTarget(String target) 
	{
		this.target = target;
	}

	public String getTarget() 
	{
		return target;
	}
	public void setApplyUser(Long applyUser) 
	{
		this.applyUser = applyUser;
	}

	public Long getApplyUser() 
	{
		return applyUser;
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
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	public void setNum(String num) 
	{
		this.num = num;
	}

	public String getNum() 
	{
		return num;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ddDate", getDdDate())
            .append("targetUnit", getTargetUnit())
            .append("amount", getAmount())
            .append("user", getUser())
            .append("departure", getDeparture())
            .append("target", getTarget())
            .append("applyUser", getApplyUser())
            .append("documentNum", getDocumentNum())
            .append("reason", getReason())
            .append("type", getType())
            .append("num", getNum())
            .append("status", getStatus())
            .toString();
    }
}
