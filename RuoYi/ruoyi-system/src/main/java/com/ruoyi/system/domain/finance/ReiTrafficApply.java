package com.ruoyi.system.domain.finance;

import java.util.Date;

/**
 * 加班或公出交通报销申请
 * @program: ruoyi->trafficApply
 * @author: gakki
 * @create: 2019-08-05 11:09
 **/
public class ReiTrafficApply {

    /**
     * id
     */
    private String id;
    /**
     * 日期
     */
    private Date ddDate;
    /**
     * 申请人
     */
    private Long applyUser;

    /**
     * 目标单位简称
     */
    private String targetUnit;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 人员
     */
    private String user;

    /**
     * 出发地
     */
    private String departure;
    /**
     * 目的地
     */
    private String target;
    /**
     * 单据数
     */
    private Integer documentNum;
    /**
     * 事由
     */
    private String reason;
    /**
     * 类型(1:公出 2：加班)
     */
    private String type;
    /**
     *
     */
    private String num;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDdDate() {
        return ddDate;
    }

    public void setDdDate(Date ddDate) {
        this.ddDate = ddDate;
    }

    public Long getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(Long applyUser) {
        this.applyUser = applyUser;
    }

    public String getTargetUnit() {
        return targetUnit;
    }

    public void setTargetUnit(String targetUnit) {
        this.targetUnit = targetUnit;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
