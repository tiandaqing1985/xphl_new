package com.ruoyi.system.domain;

/**
 * 当当小程序后端匹配
 * @program: ruoyi->DangdangAppletsBack
 * @author: gakki
 * @create: 2019-07-17 10:06
 **/
public class DangdangAppletsBack {
    private Integer letUv;
    /**
     * 小程序URL新增激活用户
     */
    private Integer letAddActiveUser;
    /**
     * 小程序URL收订纯新客
     */
    private Integer letCoollectingPureNewCustomers;
    /**
     * 小程序URL收订新客（含纯和渗透）
     */
    private Integer letBookingNewCustomersPureInfiltration;
    /**
     * 小程序URL收订订单数
     */
    private Integer letOrderingOrders;
    /**
     * 小程序URL收订金额
     */
    private Double letReceivingAmount;
    /**
     * 小程序URL出库纯新客
     */
    private Integer letPureOutOfTheLibrary;
    /**
     * 小程序URL出库新客(含纯和渗透)
     */
    private Integer letOutboundNewCustomersPureInfiltration;
    /**
     * 小程序URL出库订单数
     */
    private Integer letOutboundOrderNumber;
    /**
     * 小程序URL出库金额
     */
    private Double letOutboundAmount;

    @Override
    public String toString() {
        return "DangdangAppletsBack{" +
                "letUv=" + letUv +
                ", letAddActiveUser=" + letAddActiveUser +
                ", letCoollectingPureNewCustomers=" + letCoollectingPureNewCustomers +
                ", letBookingNewCustomersPureInfiltration=" + letBookingNewCustomersPureInfiltration +
                ", letOrderingOrders=" + letOrderingOrders +
                ", letReceivingAmount=" + letReceivingAmount +
                ", letPureOutOfTheLibrary=" + letPureOutOfTheLibrary +
                ", letOutboundNewCustomersPureInfiltration=" + letOutboundNewCustomersPureInfiltration +
                ", letOutboundOrderNumber=" + letOutboundOrderNumber +
                ", letOutboundAmount=" + letOutboundAmount +
                '}';
    }

    public Integer getLetUv() {
        return letUv;
    }

    public void setLetUv(Integer letUv) {
        this.letUv = letUv;
    }

    public Integer getLetAddActiveUser() {
        return letAddActiveUser;
    }

    public void setLetAddActiveUser(Integer letAddActiveUser) {
        this.letAddActiveUser = letAddActiveUser;
    }

    public Integer getLetCoollectingPureNewCustomers() {
        return letCoollectingPureNewCustomers;
    }

    public void setLetCoollectingPureNewCustomers(Integer letCoollectingPureNewCustomers) {
        this.letCoollectingPureNewCustomers = letCoollectingPureNewCustomers;
    }

    public Integer getLetBookingNewCustomersPureInfiltration() {
        return letBookingNewCustomersPureInfiltration;
    }

    public void setLetBookingNewCustomersPureInfiltration(Integer letBookingNewCustomersPureInfiltration) {
        this.letBookingNewCustomersPureInfiltration = letBookingNewCustomersPureInfiltration;
    }

    public Integer getLetOrderingOrders() {
        return letOrderingOrders;
    }

    public void setLetOrderingOrders(Integer letOrderingOrders) {
        this.letOrderingOrders = letOrderingOrders;
    }

    public Double getLetReceivingAmount() {
        return letReceivingAmount;
    }

    public void setLetReceivingAmount(Double letReceivingAmount) {
        this.letReceivingAmount = letReceivingAmount;
    }

    public Integer getLetPureOutOfTheLibrary() {
        return letPureOutOfTheLibrary;
    }

    public void setLetPureOutOfTheLibrary(Integer letPureOutOfTheLibrary) {
        this.letPureOutOfTheLibrary = letPureOutOfTheLibrary;
    }

    public Integer getLetOutboundNewCustomersPureInfiltration() {
        return letOutboundNewCustomersPureInfiltration;
    }

    public void setLetOutboundNewCustomersPureInfiltration(Integer letOutboundNewCustomersPureInfiltration) {
        this.letOutboundNewCustomersPureInfiltration = letOutboundNewCustomersPureInfiltration;
    }

    public Integer getLetOutboundOrderNumber() {
        return letOutboundOrderNumber;
    }

    public void setLetOutboundOrderNumber(Integer letOutboundOrderNumber) {
        this.letOutboundOrderNumber = letOutboundOrderNumber;
    }

    public Double getLetOutboundAmount() {
        return letOutboundAmount;
    }

    public void setLetOutboundAmount(Double letOutboundAmount) {
        this.letOutboundAmount = letOutboundAmount;
    }
}
