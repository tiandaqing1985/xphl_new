package com.ruoyi.system.domain.finance;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 财务金额统计
 *
 * @author ruoyi
 * @date 2019-09-27
 */
public class FacAmountApply extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 部门名称
     */
    private String deptNAme;

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

    public String getDeptNAme() {
        return deptNAme;
    }

    public void setDeptNAme(String deptNAme) {
        this.deptNAme = deptNAme;
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
