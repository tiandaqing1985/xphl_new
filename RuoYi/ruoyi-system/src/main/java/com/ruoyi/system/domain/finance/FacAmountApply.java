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
    /***
     *日常报销
     * **/
    private Double BXamount;
    /**
     * 加班交通费
     */
    private Double BXJBamount;
    /**
     * 公出交通费
     */
    private Double BXGCamount;
    /**
     * 其他报销费
     */
    private Double BXQTamount;
    /**
     * 招待费
     */
    private Double ZDamount;
    /**
     * 对公支付费
     */
    private Double DGamount;
    /**
     * 团建费
     */
    private Double TJamount;
    /**
     * 差旅费
     */
    private Double CLamount;



    public String getDeptNAme() {
        return deptNAme;
    }

    public void setDeptNAme(String deptNAme) {
        this.deptNAme = deptNAme;
    }

    public Double getBXamount() {
        return BXamount;
    }

    public void setBXamount(Double BXamount) {
        this.BXamount = BXamount;
    }

    public Double getBXJBamount() {
        return BXJBamount;
    }

    public void setBXJBamount(Double BXJBamount) {
        this.BXJBamount = BXJBamount;
    }

    public Double getBXGCamount() {
        return BXGCamount;
    }

    public void setBXGCamount(Double BXGCamount) {
        this.BXGCamount = BXGCamount;
    }

    public Double getBXQTamount() {
        return BXQTamount;
    }

    public void setBXQTamount(Double BXQTamount) {
        this.BXQTamount = BXQTamount;
    }

    public Double getZDamount() {
        return ZDamount;
    }

    public void setZDamount(Double ZDamount) {
        this.ZDamount = ZDamount;
    }

    public Double getDGamount() {
        return DGamount;
    }

    public void setDGamount(Double DGamount) {
        this.DGamount = DGamount;
    }

    public Double getTJamount() {
        return TJamount;
    }

    public void setTJamount(Double TJamount) {
        this.TJamount = TJamount;
    }

    public Double getCLamount() {
        return CLamount;
    }

    public void setCLamount(Double CLamount) {
        this.CLamount = CLamount;
    }
}
