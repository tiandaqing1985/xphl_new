package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class BwZhanXian extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 月份
     */
    @Excel(name = "月份")
    private String yearMonth;
    /**
     * 日期
     */
    @Excel(name = "日期")
    private String date;
    /**
     * 展现
     */
    @Excel(name = "展现")
    private Integer sumShow;
    /**
     * 点击
     */
    @Excel(name = "点击")
    private Integer sumclick;
    /**
     * CTR
     */
    private double ctr;
    @Excel(name = "CTR")
    private String ctr1;
    /**
     * CPC
     */
    private double cpc;
    @Excel(name = "CPC")
    private String cpc1;
    /**
     * 消费
     */
    @Excel(name = "消费")
    private double sumCost;

    public String getCtr1() {
        return ctr1;
    }

    public void setCtr1(String ctr1) {
        this.ctr1 = ctr1;
    }

    public String getCpc1() {
        return cpc1;
    }

    public void setCpc1(String cpc1) {
        this.cpc1 = cpc1;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getSumShow() {
        return sumShow;
    }

    public void setSumShow(Integer sumShow) {
        this.sumShow = sumShow;
    }

    public Integer getSumclick() {
        return sumclick;
    }

    public void setSumclick(Integer sumclick) {
        this.sumclick = sumclick;
    }

    public double getCtr() {
        return ctr;
    }

    public void setCtr(double ctr) {
        this.ctr = ctr;
    }

    public double getCpc() {
        return cpc;
    }

    public void setCpc(double cpc) {
        this.cpc = cpc;
    }

    public double getSumCost() {
        return sumCost;
    }

    public void setSumCost(double sumCost) {
        this.sumCost = sumCost;
    }

    @Override
    public String toString() {
        return "BwZhanXian{" +
                "yearMonth='" + yearMonth + '\'' +
                ", date='" + date + '\'' +
                ", sumShow=" + sumShow +
                ", sumclick=" + sumclick +
                ", ctr=" + ctr +
                ", ctr1='" + ctr1 + '\'' +
                ", cpc=" + cpc +
                ", cpc1='" + cpc1 + '\'' +
                ", sumCost=" + sumCost +
                '}';
    }
}
