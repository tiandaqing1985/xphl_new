package com.ruoyi.system.domain.finance;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 借款申请表 fac_loan_apply
 *
 * @author ruoyi
 * @date 2019-07-30
 */
public class FacLoanApply extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;
    /**
     * 借款编号
     */
    private String num;
    /**
     * 借款名称
     */
    private String loanName;
    /**
     * 借款金额
     */
    private Double amount;
    /**
     * 借款人
     */
    private Long loanUser;
    /**
     * 借款时间
     */
    private Date loanTime;
    /**
     * 预计还款时间
     */
    private Date repayTime;
    /**
     * 财务操作
     */
    private String facOperate;
    /**
     * 借款事由
     */
    private String reason;
    /**
     * 申请人操作
     */
    private String applyStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更改时间
     */
    private Date updateTime;
    /**
     * 借款人姓名
     */
    private String userName;
    /**
     * 审批人姓名
     **/
    private String approver;

    private String approvalStatus;
    /**
     * 所有审批人姓名
     */
    private String allName;

    /**
     * 公司名称
     */
    private String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAllName() {
        return allName;
    }

    public void setAllName(String allName) {
        this.allName = allName;
    }


    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getLoanUser() {
        return loanUser;
    }

    public void setLoanUser(Long loanUser) {
        this.loanUser = loanUser;
    }

    public Date getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public String getFacOperate() {
        return facOperate;
    }

    public void setFacOperate(String facOperate) {
        this.facOperate = facOperate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId()).append("num", getNum())
                .append("loanName", getLoanName()).append("amount", getAmount())
                .append("loanUser", getLoanUser())
                .append("createTime", getCreateTime())
                .append("loanTime", getLoanTime())
                .append("repayTime", getRepayTime())
                .append("facOperate", getFacOperate())
                .append("reason", getReason())
                .append("applyStatus", getApplyStatus())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime()).toString();
    }
}
