package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 审批表 sys_user_approval
 * 
 * @author ruoyi
 * @date 2019-06-04
 */
public class UserApproval extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 审批记录ID */
	private Long approvalId;
	/** 申请ID */
	private Long applyId;
	/** 审批状态（1通过，2驳回） */
	private String approvalState;
	/** 审批人ID */
	private Long approverId;
	/** 可视性（0不可见，1可见） */
	private String approvalSight;
	/** 审批时间*/
	private String approvalTime;
	/** 审批等级*/
	private int approvalLevel;
	
	
	/** 申请人*/
	private SysUser sysUser;
	/** 申请记录*/
	private UserApply userApply;
	/** 申请人部门*/
	private SysDept sysDept;
	/** 有时间后缀的时长*/
	private String timelengthPlus;
	
//	/** 申请单号 */
//	private String listNum;
//	/** 申请类型（1请假，2加班） */
//	private String applyType;
//	/** 申请状态（1保存，2审批中，3审批完） */
//	private String applyState;
//	/** 请假类型 */
//	private String leaveType;
//	/** 申请时间 */
//	private Date applyTime;
//	/** 时长 */
//	private Double timelength;
//	/** 用户名称 */
//    @Excel(name = "用户名称")
//    private String userName;
//    
	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public UserApply getUserApply() {
		return userApply;
	}

	public void setUserApply(UserApply userApply) {
		this.userApply = userApply;
	}

	public SysDept getSysDept() {
		return sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}

	public int getApprovalLevel() {
		return approvalLevel;
	}

	public void setApprovalLevel(int approvalLevel) {
		this.approvalLevel = approvalLevel;
	}

	public String getApprovalSight() {
		return approvalSight;
	}

	public void setApprovalSight(String approvalSight) {
		this.approvalSight = approvalSight;
	}

	public String getApprovalTime() {
		return approvalTime;
	}

	public void setApprovaTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}

	public void setApprovalId(Long approvalId) 
	{
		this.approvalId = approvalId;
	}

	public Long getApprovalId() 
	{
		return approvalId;
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
	public void setApproverId(Long approverId) 
	{
		this.approverId = approverId;
	}

	public Long getApproverId() 
	{
		return approverId;
	}
	

    public String getTimelengthPlus() {
		return timelengthPlus;
	}

	public void setTimelengthPlus(String timelengthPlus) {
		this.timelengthPlus = timelengthPlus;
	}

	public void setApprovalTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("approvalId", getApprovalId())
            .append("applyId", getApplyId())
            .append("approvalState", getApprovalState())
            .append("approverId", getApproverId())
            .toString();
    }

	
}
