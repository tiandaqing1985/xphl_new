package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 申请表 sys_user_apply
 * 
 * @author ruoyi
 * @date 2019-06-05
 */
public class UserApply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 申请ID */
	private Long applyId;
	/** 用户ID */
	private Long userId;
	/** 申请单号 */
	private String listNum;
	/** 加班开始时间 */
	private Date starttime;
	/** 加班结束时间 */
	private Date endtime;
	/** 时段（1上午，2下午） */
	private String timeapart1;
	/** 时段（1上午，2下午） */
	private String timeapart2;
	/** 时段（1早上(10:00——11:00),2下午(18:00——19:00)） */
	private String timeapart3;
	/** 时长 */
	private Double timelength;
	/** 申请类型（1请假，2加班） */
	private String applyType;
	/** 申请状态（1保存，2审批中，3审批完） */
	private String applyState;
	/** 撤销状态（0未撤销，1已撤销） */
	private String confirmState;
	/** 详情 */
	private String details;
	/** 请假类型 */
	private String leaveType;
	/** 请假类型 */
	private String approvalS;
	/** 申请时间 */
	private Date applyTime;
	/** 销假对应的请假申请id */
	private Long forApplyId;
	/** 证明附件*/
	private String prove;
	/** 有时间后缀的时长*/
	private String timelengthPlus;
	/** 是否更新在钉钉考勤表中：0 未更新  1已更新 */
	private String status;
	
	private SysUser sysUser;
	
	private SysDept sysDept;
	
	private UserApproval userApproval; 
	
	private String approverName;
	
	
    public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Long getApplyId() {
		return applyId;
	}


	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getListNum() {
		return listNum;
	}


	public void setListNum(String listNum) {
		this.listNum = listNum;
	}


	public Date getStarttime() {
		return starttime;
	}


	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}


	public Date getEndtime() {
		return endtime;
	}


	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}


	public String getTimeapart1() {
		return timeapart1;
	}


	public void setTimeapart1(String timeapart1) {
		this.timeapart1 = timeapart1;
	}


	public String getTimeapart2() {
		return timeapart2;
	}


	public void setTimeapart2(String timeapart2) {
		this.timeapart2 = timeapart2;
	}


	public Double getTimelength() {
		return timelength;
	}


	public void setTimelength(Double timelength) {
		this.timelength = timelength;
	}


	public String getApplyType() {
		return applyType;
	}


	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}


	public String getApplyState() {
		return applyState;
	}


	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}


	public String getConfirmState() {
		return confirmState;
	}


	public void setConfirmState(String confirmState) {
		this.confirmState = confirmState;
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}


	public String getLeaveType() {
		return leaveType;
	}


	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}


	public Date getApplyTime() {
		return applyTime;
	}


	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}


	public SysUser getSysUser() {
		return sysUser;
	}


	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}


	public SysDept getSysDept() {
		return sysDept;
	}


	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}


	public UserApproval getUserApproval() {
		return userApproval;
	}


	public void setUserApproval(UserApproval userApproval) {
		this.userApproval = userApproval;
	}


	public String getApproverName() {
		return approverName;
	}


	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}



	public Long getForApplyId() {
		return forApplyId;
	}


	public void setForApplyId(Long forApplyId) {
		this.forApplyId = forApplyId;
	}


	public String getApprovalS() {
		return approvalS;
	}


	public void setApprovalS(String approvalS) {
		this.approvalS = approvalS;
	}


	public String getProve() {
		return prove;
	}


	public void setProve(String prove) {
		this.prove = prove;
	}


	public String getTimeapart3() {
		return timeapart3;
	}


	public void setTimeapart3(String timeapart3) {
		this.timeapart3 = timeapart3;
	}


	public String getTimelengthPlus() {
		return timelengthPlus;
	}


	public void setTimelengthPlus(String timelengthPlus) {
		this.timelengthPlus = timelengthPlus;
	}


	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("applyId", getApplyId())
            .append("userId", getUserId())
            .append("listNum", getListNum())
            .append("starttime", getStarttime())
            .append("endtime", getEndtime())
            .append("timeapart1", getTimeapart1())
            .append("timeapart2", getTimeapart2())
            .append("timelength", getTimelength())
            .append("applyType", getApplyType())
            .append("applyState", getApplyState())
            .append("confirmState", getConfirmState())
            .append("details", getDetails())
            .append("leaveType", getLeaveType())
            .append("applyTime", getApplyTime())
            .toString();
    }
}
