package com.ruoyi.system.domain.finance;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.DefaultFiled;

/**
 * 对公申请表 fac_pay_public_apply
 *
 * @author ruoyi
 * @date 2019-08-01
 */

public class FacPayPublicApply extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;
    /**
     * 申请编号
     */
    private String num;
    /**
     * 申请名称
     */
    private String name;
    /**
     * 申请人id
     */
    private Long user;
    /**
     * 是否有发票
     */
    private String weatherInvoice;
    /**
     * 付款金额
     */
    private Double amount;
    /**
     * 收款人
     */
    private String payee;
    /**
     * 收款人开户行
     */
    private String payeeBank;
    /**
     * 收款人账号
     */
    private String payeeAccount;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 审批状态
     */
    private String status;
    /**
     * 操作状态
     */
    private String oprTime;
    /**
     * 申请人姓名
     */
    private String userName;
    /**
     * 部门名称
     */
    private String deptname;
    /**
     *公司名称
     */
    private String companyName;
    /**
     * 申请时间
     */
    @DefaultFiled
    private  Date createTime;

    /**
     * 是否提交申请
     */
    private  String wetherSave;
    
    
    private  String  unit;
    
    /** 审批人姓名 **/
	private String approver;

	private String approvalStatus;
    /**所有审批人姓名*/
    private String allName;
    /**是否保存*/
    private String isKeep;
    /**是否有票*/
    private String weatherNum;

    public String getWeatherNum() {
        return weatherNum;
    }

    public void setWeatherNum(String weatherNum) {
        this.weatherNum = weatherNum;
    }

    public String getIsKeep() {
        return isKeep;
    }

    public void setIsKeep(String isKeep) {
        this.isKeep = isKeep;
    }

    public String getAllName() {
        return allName;
    }

    public void setAllName(String allName) {
        this.allName = allName;
    }




    public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getUser() {
        return user;
    }

    public void setWeatherInvoice(String weatherInvoice) {
        this.weatherInvoice = weatherInvoice;
    }

    public String getWeatherInvoice() {
        return weatherInvoice;
    } 

    public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayeeBank(String payeeBank) {
        this.payeeBank = payeeBank;
    }

    public String getPayeeBank() {
        return payeeBank;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setOprTime(String oprTime) {
        this.oprTime = oprTime;
    }

    public String getOprTime() {
        return oprTime;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("num", getNum())
                .append("name", getName())
                .append("user", getUser())
                .append("weatherInvoice", getWeatherInvoice())
                .append("createTime", getCreateTime())
                .append("amount", getAmount())
                .append("payee", getPayee())
                .append("payeeBank", getPayeeBank())
                .append("payeeAccount", getPayeeAccount())
                .append("remarks", getRemarks())
                .append("updateTime", getUpdateTime())
                .append("status", getStatus())
                .append("oprTime", getOprTime())
                .toString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getWetherSave() {
        return wetherSave;
    }

    public void setWetherSave(String wetherSave) {
        this.wetherSave = wetherSave;
    }
}
