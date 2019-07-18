package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;

/**
 * 当当移动端匹配后台数据
 * @program: ruoyi->DangDangModBack
 * @author: gakki
 * @create: 2019-07-17 10:00
 * @descripion
 **/
public class DangDangModBack {

    private Integer modUv;
    /**
     * 移动端新增激活用户
     */
    private Integer modAddActiveUser;
    /**
     * 移动端收订纯新客
     */
    private Integer modCoollectingPureNewCustomers;
    /**
     * 移动端收订新客（含纯和渗透）
     */
    private Integer modBookingNewCustomersPureInfiltration;
    /**
     * 移动端收订订单数
     */
    private Integer modOrderingOrders;
    /**
     * 移动端收订金额
     */
    private Double modReceivingAmount;
    /**
     * 移动端出库纯新客
     */
    private Integer modPureOutOfTheLibrary;
    /**
     * 移动端出库新客(含纯和渗透)
     */
    private Integer modOutboundNewCustomersPureInfiltration;
    /**
     * 移动端出库订单数
     */
    private Integer modOutboundOrderNumber;

    private Double modOutboundAmount;

    public Integer getModUv() {
        return modUv;
    }

    public void setModUv(Integer modUv) {
        this.modUv = modUv;
    }

    public Integer getModAddActiveUser() {
        return modAddActiveUser;
    }

    public void setModAddActiveUser(Integer modAddActiveUser) {
        this.modAddActiveUser = modAddActiveUser;
    }

    public Integer getModCoollectingPureNewCustomers() {
        return modCoollectingPureNewCustomers;
    }

    public void setModCoollectingPureNewCustomers(Integer modCoollectingPureNewCustomers) {
        this.modCoollectingPureNewCustomers = modCoollectingPureNewCustomers;
    }

    public Integer getModBookingNewCustomersPureInfiltration() {
        return modBookingNewCustomersPureInfiltration;
    }

    public void setModBookingNewCustomersPureInfiltration(Integer modBookingNewCustomersPureInfiltration) {
        this.modBookingNewCustomersPureInfiltration = modBookingNewCustomersPureInfiltration;
    }

    public Integer getModOrderingOrders() {
        return modOrderingOrders;
    }

    public void setModOrderingOrders(Integer modOrderingOrders) {
        this.modOrderingOrders = modOrderingOrders;
    }

    public Double getModReceivingAmount() {
        return modReceivingAmount;
    }

    public void setModReceivingAmount(Double modReceivingAmount) {
        this.modReceivingAmount = modReceivingAmount;
    }

    public Integer getModPureOutOfTheLibrary() {
        return modPureOutOfTheLibrary;
    }

    public void setModPureOutOfTheLibrary(Integer modPureOutOfTheLibrary) {
        this.modPureOutOfTheLibrary = modPureOutOfTheLibrary;
    }

    public Integer getModOutboundNewCustomersPureInfiltration() {
        return modOutboundNewCustomersPureInfiltration;
    }

    public void setModOutboundNewCustomersPureInfiltration(Integer modOutboundNewCustomersPureInfiltration) {
        this.modOutboundNewCustomersPureInfiltration = modOutboundNewCustomersPureInfiltration;
    }

    public Integer getModOutboundOrderNumber() {
        return modOutboundOrderNumber;
    }

    public void setModOutboundOrderNumber(Integer modOutboundOrderNumber) {
        this.modOutboundOrderNumber = modOutboundOrderNumber;
    }

    public Double getModOutboundAmount() {
        return modOutboundAmount;
    }

    public void setModOutboundAmount(Double modOutboundAmount) {
        this.modOutboundAmount = modOutboundAmount;
    }
}

