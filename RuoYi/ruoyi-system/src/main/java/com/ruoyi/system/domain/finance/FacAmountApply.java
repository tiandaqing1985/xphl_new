package com.ruoyi.system.domain.finance;

import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 财务金额统计
 *
 * @author ruoyi
 * @date 2019-09-27
 */
public class FacAmountApply extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 加班交通费
     */
    private Double bxJBamount;
    /**
     * 公出交通费
     */
    private Double bxGCamount;
    /**
     * 其他报销费
     */
    private Double bxQTamount;
    /**
     * 招待费
     */
    private Double zdAmount;
    /**
     * 对公支付费
     */
    private Double dgAmount;
    /**
     * 团建费
     */
    private Double tjAmount;
    /**
     * 差旅费
     */
    private Double clAmount;
    /**
     * 交通费
     */
    private Double amount;
    /**
     * 申请时间
     * */
    private Date sqtime;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getSqtime() {
        return sqtime;
    }

    public void setSqtime(Date sqtime) {
        this.sqtime = sqtime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Double getBxJBamount() {
        return bxJBamount;
    }

    public void setBxJBamount(Double bxJBamount) {
        this.bxJBamount = bxJBamount;
    }

    public Double getBxGCamount() {
        return bxGCamount;
    }

    public void setBxGCamount(Double bxGCamount) {
        this.bxGCamount = bxGCamount;
    }

    public Double getBxQTamount() {
        return bxQTamount;
    }

    public void setBxQTamount(Double bxQTamount) {
        this.bxQTamount = bxQTamount;
    }

    public Double getZdAmount() {
        return zdAmount;
    }

    public void setZdAmount(Double zdAmount) {
        this.zdAmount = zdAmount;
    }

    public Double getDgAmount() {
        return dgAmount;
    }

    public void setDgAmount(Double dgAmount) {
        this.dgAmount = dgAmount;
    }

    public Double getTjAmount() {
        return tjAmount;
    }

    public void setTjAmount(Double tjAmount) {
        this.tjAmount = tjAmount;
    }

    public Double getClAmount() {
        return clAmount;
    }

    public void setClAmount(Double clAmount) {
        this.clAmount = clAmount;
    }
}
