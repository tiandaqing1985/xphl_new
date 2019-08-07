package com.ruoyi.system.domain.finance;

import java.util.Date;

/**
 * 行政或其他报销申请
 *
 * @program: ruoyi->ReiAdiApply
 * @author: gakki
 * @create: 2019-08-05 11:25
 **/
public class ReiAdiApply {

    private Integer id;
    /**
     * 日期
     */
    private Date ddDate;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 科目
     */
    private String project;
    /**
     * 单据数
     */
    private Integer documentNum;
    /**
     * 事由
     */
    private String reason;
    /**
     * 报销编号
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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getddDate() {
        return ddDate;
    }

    public void setddDate(Date ddDate) {
        this.ddDate = ddDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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
}
