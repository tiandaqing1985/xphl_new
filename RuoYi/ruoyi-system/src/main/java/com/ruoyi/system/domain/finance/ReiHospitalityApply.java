package com.ruoyi.system.domain.finance;

import java.util.Date;

/**
 * 招待费报销申请
 * @program: ruoyi->HospitalityReiApply
 * @author: gakki
 * @create: 2019-08-05 11:33
 **/
public class ReiHospitalityApply {
    private Integer id;
    /**
     * 日期
     */
    private Date ddDate;
    /**
     * 参加人
     */
    private  String addUser;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 目标单位简称
     */
    private String targetUnit;
    /**
     * 单据数
     */
    private Integer documentNum;
    /**
     * 事由
     */
    private String reason;
    /**
     *
     */
    private String num;
    /**
     * 审批状态
     */
    private String status;
    /**
     * 类型（1:行政2：其他）
     */
    private String type;

    /**
     * 申请编号
     */
    private String applyNum;
    /**
     * 申请人
     */
    private  Long user;

    /**
     * 地点
     */
    private  String location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDdDate() {
        return ddDate;
    }

    public void setDdDate(Date ddDate) {
        this.ddDate = ddDate;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTargetUnit() {
        return targetUnit;
    }

    public void setTargetUnit(String targetUnit) {
        this.targetUnit = targetUnit;
    }

    public Integer getDocumentNum() {
        return documentNum;
    }

    public void setDocumentNum(Integer documentNum) {
        this.documentNum = documentNum;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(String applyNum) {
        this.applyNum = applyNum;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
