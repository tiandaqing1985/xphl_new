package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
import java.util.List;

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
	/** 用户名*/
	private String userName;
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
	/** 审批意见（1同意，2驳回 ，3未操作） */
	private String approvalState;
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
	/** 补卡类型：上班/下班 */
	private String ctype;
	/** 有时间后缀的时长*/
	private String timelengthPlus;
	
	private SysUser sysUser;
	
	private SysDept sysDept;
	
	private UserApproval userApproval; 
	
	private String approverName;
	
	/** 所有审批人及其审批状态*/
	private String allAppNames;
	
	/**申请表对应的上传图片集合*/
	private List<String> fileList;
	
	/** 传入String类型的时间 */
	private String time;
	
    public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getCtype() {
		return ctype;
	}


	public void setCtype(String ctype) {
		this.ctype = ctype;
	}


	public List<String> getFileList() {
		return fileList;
	}


	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}


	public String getAllAppNames() {
		return allAppNames;
	}


	public void setAllAppNames(String allAppNames) {
		this.allAppNames = allAppNames;
	}


	public String getApprovalState() {
		return approvalState;
	}


	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
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


	@Override
	public String toString() {
		return "UserApply [applyId=" + applyId + ", userId=" + userId + ", userName=" + userName + ", listNum="
				+ listNum + ", starttime=" + starttime + ", endtime=" + endtime + ", timeapart1=" + timeapart1
				+ ", timeapart2=" + timeapart2 + ", timeapart3=" + timeapart3 + ", timelength=" + timelength
				+ ", applyType=" + applyType + ", applyState=" + applyState + ", approvalState=" + approvalState
				+ ", details=" + details + ", leaveType=" + leaveType + ", approvalS=" + approvalS + ", applyTime="
				+ applyTime + ", forApplyId=" + forApplyId + ", prove=" + prove + ", ctype=" + ctype
				+ ", timelengthPlus=" + timelengthPlus + ", sysUser=" + sysUser + ", sysDept=" + sysDept
				+ ", userApproval=" + userApproval + ", approverName=" + approverName + ", allAppNames=" + allAppNames
				+ ", fileList=" + fileList + ", time=" + time + "]";
	}


}
