package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.util.Date;

/**
 * @program: ruoyi->All
 * @author: gakki
 * @create: 2019-07-16 19:37
 **/

public class DangDangAll {

    /**
     * 日期
     */
    @Excel(name = "日期", dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fDate;

    /**
     * 推广计划
     */
    @Excel(name = "推广计划", defaultValue = "")
    private String fPlan;

    /**
     * 推广单元
     */
    @Excel(name = "推广单元", defaultValue = "")
    private String fUnit;

    /**
     * 关键词
     */
    @Excel(name = "关键词", defaultValue = "")
    private String fKeyword;

    /**
     * 总展现
     */
    @Excel(name = "总展现")
    private Integer fShowDate;

    /**
     * 总点击
     */
    @Excel(name = "总点击")
    private Integer fClick;

    /**
     * 总消费
     */
    @Excel(name = "总消费")
    private Double fCost;

    /**
     * app展现
     */
    @Excel(name = "app展现")
    private Integer fAppShowData;
    /**
     * app点击
     */
    @Excel(name = "app点击")
    private Integer fAppClick;
    /**
     * app消费
     */
    @Excel(name = "app消费")
    private Double fAppCost;
    /**
     * 小程序展现
     */
    @Excel(name = "小程序展现")
    private Integer fLetsShowData;
    /**
     * 小程序点击
     */
    @Excel(name = "小程序点击")
    private Integer fLetsClick;
    /**
     * 小程序消费
     */
    @Excel(name = "小程序消费")
    private Double fLetsCost;
    /**
     * 组合
     */
    @Excel(name = "组合")
    private String fCombination;

    @Excel(name = "词性")
    private String fPartSpeech;
    @Excel(name = "二级类目")
    private String sec;
    @Excel(name = "三级类目")
    private String tre;
    /**
     * 移动端url
     */
    @Excel(name = "移动端URL")

    private String MobileAccessUrl;
    /**
     * 小程序url
     */
    @Excel(name = "小程序URL")

    private String appletsUrl;
    /**
     * 小程序更改后URl
     */
    @Excel(name = "小程序更改后URl")
    private String appletDone;

    /**
     * 去除app消费
     */
    @Excel(name = "去除app消费")
    private Double removeAppCost;
    /**
     * 去除app实际消费
     */
    @Excel(name = "去除app实际消费")
    private Double appActCost;
    /**
     *
     */
    @Excel(name = "去除app的点击")
    private  Integer removeAppClick;
    /**
     * 点击率
     */
    @Excel(name = "点击率")
    private String clickRate;
    /**
     * 平均点击价格
     */
    @Excel(name = "平均点击价格")
    private String averageClickPrice;
    /**
     * 首页平均排名
     */
    @Excel(name = "首页平均排名")
    private Double homepageAverageRanking;
    /**
     * 出价
     */
    @Excel(name = "出价")
    private Double bid;
    /**
     * 匹配模式
     */
    @Excel(name = "匹配模式")
    private String partSpeech;
//**********************************************************************************************


    //移动端
    @Excel(name = "移动端uv")
    private Integer modUv;
    //小程序URL
    @Excel(name = "小程序URLuv")
    private Integer letUv;
    @Excel(name = "小程序更改后uv")
    private Integer doneUv;
    /**
     * 移动端新增激活用户
     */
    @Excel(name = "移动端新增激活用户数")
    private Integer modAddActiveUser;
    /**
     * 小程序URL新增激活用户
     */
    @Excel(name = "小程序URL新增激活用户数")
    private Integer letAddActiveUser;
    @Excel(name = "小程序更改后新增激活用户数")
    private Integer doneAddActiveUser;
    /**
     * 移动端收订纯新客
     */
    @Excel(name = "移动端收订纯新客")
    private Integer modCoollectingPureNewCustomers;
    /**
     * 小程序URL收订纯新客
     */
    @Excel(name = "小程序URL收订纯新客")
    private Integer letCoollectingPureNewCustomers;
    /**
     * 小程序更改后收订纯新客
     */
    @Excel(name = "小程序更改后收订纯新客")
    private Integer doneCoollectingPureNewCustomers;
    /**
     * 移动端收订新客（含纯和渗透）
     */
    @Excel(name = "移动端收订新客(含纯和渗透)")
    private Integer modBookingNewCustomersPureInfiltration;
    /**
     * 小程序URL收订新客（含纯和渗透）
     */
    @Excel(name = "小程序URL收订新客(含纯和渗透)")
    private Integer letBookingNewCustomersPureInfiltration;
    /**
     * 小程序更改后收订新客（含纯和渗透）
     */
    @Excel(name = "小程序更改后收订新客(含纯和渗透)")
    private Integer doneBookingNewCustomersPureInfiltration;
    /**
     * 移动端收订订单数
     */
    @Excel(name = "移动端收订订单数")
    private Integer modOrderingOrders;
    /**
     * 小程序URL收订订单数
     */
    @Excel(name = "小程序URL收订订单数")
    private Integer letOrderingOrders;
    /**
     * 小程序更改后收订订单数
     */
    @Excel(name = "小程序更改后收订订单数")
    private Integer doneOrderingOrders;
    /**
     * 移动端收订金额
     */
    @Excel(name = "移动端收订金额")
    private Double modReceivingAmount;

    /**
     * 小程序URL收订金额
     */
    @Excel(name = "小程序URL收订金额")
    private Double letReceivingAmount;
    /**
     * 小程序更改后收订金额
     */
    @Excel(name = "小程序更改后收订金额")
    private Double doneReceivingAmount;
    /**
     * 移动端出库纯新客
     */
    @Excel(name = "移动端出库纯新客")
    private Integer modPureOutOfTheLibrary;
    /**
     * 小程序URL出库纯新客
     */
    @Excel(name = "小程序URL出库纯新客")
    private Integer letPureOutOfTheLibrary;
    /**
     * 小程序更改后出库纯新客
     */
    @Excel(name = "小程序更改后出库纯新客")
    private Integer donePureOutOfTheLibrary;
    /**
     * 移动端出库新客(含纯和渗透)
     */
    @Excel(name = "移动端出库新客(含纯和渗透)")
    private Integer modOutboundNewCustomersPureInfiltration;
    /**
     * 小程序URL出库新客(含纯和渗透)
     */
    @Excel(name = "小程序URL出库新客(含纯和渗透)")
    private Integer letOutboundNewCustomersPureInfiltration;
    /**
     * 小程序更改后出库新客(含纯和渗透)
     */
    @Excel(name = "小程序更改后出库新客(含纯和渗透)")
    private Integer doneOutboundNewCustomersPureInfiltration;
    /**
     * 移动端出库订单数
     */
    @Excel(name = "移动端出库订单数")
    private Integer modOutboundOrderNumber;
    /**
     * 小程序URL出库订单数
     */
    @Excel(name = "小程序URL出库订单数")
    private Integer letOutboundOrderNumber;
    /**
     * 小程序更改后出库订单数
     */
    @Excel(name = "小程序更改后出库订单数")
    private Integer doneOutboundOrderNumber;
    /**
     * 移动端出库金额
     */
    @Excel(name = "移动端出库金额")
    private Double modOutboundAmount;
    /**
     * 小程序URL出库金额
     */
    @Excel(name = "小程序URL出库金额")
    private Double letOutboundAmount;
    /**
     * 小程序更改后出库金额
     */
    @Excel(name = "小程序更改后出库金额")
    private Double doneOutboundAmount;

    /**
     * 汇总出库纯新客
     */
    @Excel(name = "小程序汇总出库纯新客")
    private Integer allPureOutOfTheLibrary;
    /**
     * 汇总出库金额
     */
    @Excel(name = "小程序汇总出库金额")
    private Double allOutboundAmount;

    //针对每条汇总
    @Excel(name = "汇总uv")
    private Integer uv;
    /**
     * 汇总新增激活用户
     */
    @Excel(name = "汇总新增激活用户数")
    private Integer addActiveUser;
    /**
     * 汇总收订纯新客
     */
    @Excel(name = "汇总收订纯新客")
    private Integer coollectingPureNewCustomers;
    /**
     * 汇总收订新客（含纯和渗透）
     */
    @Excel(name = "汇总收订新客(含纯和渗透)")
    private Integer bookingNewCustomersPureInfiltration;
    /**
     * 汇总收订订单数
     */
    @Excel(name = "汇总收订订单数")
    private Integer orderingOrders;
    /**
     * 汇总收订金额
     */
    @Excel(name = "汇总收订金额")
    private Double receivingAmount;
    /**
     * 汇总出库纯新客
     */
    @Excel(name = "汇总出库纯新客")
    private Integer pureOutOfTheLibrary;
    /**
     * 汇总出库新客(含纯和渗透)
     */
    @Excel(name = "汇总出库新客(含纯和渗透)")
    private Integer outboundNewCustomersPureInfiltration;
    /**
     * 汇总出库订单数
     */
    @Excel(name = "汇总出库订单数")
    private Integer outboundOrderNumber;
    /**
     * 汇总出库金额
     */
    @Excel(name = "汇总出库金额")
    private Double outboundAmount;

    // *******************************************************************************************************
    /**
     * 出库转化率
     */
    @Excel(name = "出库转化率")
    private String outBoundRate;
    /**
     * CTR
     */
    @Excel(name = "CTR")
    private String CTR;

    /**
     * CPC
     */
    @Excel(name = "CPC")
    private String CPC;
    /**
     * 出库ROI
     */
    @Excel(name = "出库ROI")
    private String outBoundROI;

    /**
     * 出库新客成本
     */
    @Excel(name = "出库新客成本")
    private String outBoubdCost;

    /**
     * 收订订单转化率
     */
    @Excel(name = "收订订单转化率")
    private String orderRate;

    /**
     * 收订ROI
     */
    @Excel(name = "收订ROI")
    private String orderROI;

    /**
     * 收订新客成本
     */
    @Excel(name = "收订新客成本")
    private String orderNewCost;

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getTre() {
        return tre;
    }

    public void setTre(String tre) {
        this.tre = tre;
    }

    public Integer getRemoveAppClick() {
        return removeAppClick;
    }

    public void setRemoveAppClick(Integer removeAppClick) {
        this.removeAppClick = removeAppClick;
    }

    public String getClickRate() {
        return clickRate;
    }

    public void setClickRate(String clickRate) {
        this.clickRate = clickRate;
    }

    public String getAverageClickPrice() {
        return averageClickPrice;
    }

    public void setAverageClickPrice(String averageClickPrice) {
        this.averageClickPrice = averageClickPrice;
    }

    public Double getHomepageAverageRanking() {
        return homepageAverageRanking;
    }

    public void setHomepageAverageRanking(Double homepageAverageRanking) {
        this.homepageAverageRanking = homepageAverageRanking;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public String getPartSpeech() {
        return partSpeech;
    }

    public void setPartSpeech(String partSpeech) {
        this.partSpeech = partSpeech;
    }

    public Double getRemoveAppCost() {
        return removeAppCost;
    }

    public void setRemoveAppCost(Double removeAppCost) {
        this.removeAppCost = removeAppCost;
    }

    public Double getAppActCost() {
        return appActCost;
    }

    public void setAppActCost(Double appActCost) {
        this.appActCost = appActCost;
    }

    public String getOutBoundRate() {
        return outBoundRate;
    }

    public void setOutBoundRate(String outBoundRate) {
        this.outBoundRate = outBoundRate;
    }

    public String getCTR() {
        return CTR;
    }

    public void setCTR(String CTR) {
        this.CTR = CTR;
    }

    public String getCPC() {
        return CPC;
    }

    public void setCPC(String CPC) {
        this.CPC = CPC;
    }

    public String getOutBoundROI() {
        return outBoundROI;
    }

    public void setOutBoundROI(String outBoundROI) {
        this.outBoundROI = outBoundROI;
    }

    public String getOutBoubdCost() {
        return outBoubdCost;
    }

    public void setOutBoubdCost(String outBoubdCost) {
        this.outBoubdCost = outBoubdCost;
    }

    public String getOrderRate() {
        return orderRate;
    }

    public void setOrderRate(String orderRate) {
        this.orderRate = orderRate;
    }

    public String getOrderROI() {
        return orderROI;
    }

    public void setOrderROI(String orderROI) {
        this.orderROI = orderROI;
    }

    public String getOrderNewCost() {
        return orderNewCost;
    }

    public void setOrderNewCost(String orderNewCost) {
        this.orderNewCost = orderNewCost;
    }

    public String getfPartSpeech() {
        return fPartSpeech;
    }

    public void setfPartSpeech(String fPartSpeech) {
        this.fPartSpeech = fPartSpeech;
    }

    @Override
    public String toString() {
        return "DangDangAll{" +
                "fDate=" + fDate +
                ", fPlan='" + fPlan + '\'' +
                ", fUnit='" + fUnit + '\'' +
                ", fKeyword='" + fKeyword + '\'' +
                ", fShowDate=" + fShowDate +
                ", fClick=" + fClick +
                ", fCost=" + fCost +
                ", fAppShowData=" + fAppShowData +
                ", fAppClick=" + fAppClick +
                ", fAppCost=" + fAppCost +
                ", fLetsShowData=" + fLetsShowData +
                ", fLetsClick=" + fLetsClick +
                ", fLetsCost=" + fLetsCost +

                ", allPureOutOfTheLibrary=" + allPureOutOfTheLibrary +
                ", allOutboundAmount=" + allOutboundAmount +
                ", doneUv=" + doneUv +
                ", doneAddActiveUser=" + doneAddActiveUser +
                ", doneCoollectingPureNewCustomers=" + doneCoollectingPureNewCustomers +
                ", doneBookingNewCustomersPureInfiltration=" + doneBookingNewCustomersPureInfiltration +
                ", doneOrderingOrders=" + doneOrderingOrders +
                ", doneReceivingAmount=" + doneReceivingAmount +
                ", donePureOutOfTheLibrary=" + donePureOutOfTheLibrary +
                ", doneOutboundNewCustomersPureInfiltration=" + doneOutboundNewCustomersPureInfiltration +
                ", doneOutboundOrderNumber=" + doneOutboundOrderNumber +
                ", doneOutboundAmount=" + doneOutboundAmount +
                ", modUv=" + modUv +
                ", modAddActiveUser=" + modAddActiveUser +
                ", modCoollectingPureNewCustomers=" + modCoollectingPureNewCustomers +
                ", modBookingNewCustomersPureInfiltration=" + modBookingNewCustomersPureInfiltration +
                ", modOrderingOrders=" + modOrderingOrders +
                ", modReceivingAmount=" + modReceivingAmount +
                ", modPureOutOfTheLibrary=" + modPureOutOfTheLibrary +
                ", modOutboundNewCustomersPureInfiltration=" + modOutboundNewCustomersPureInfiltration +
                ", modOutboundOrderNumber=" + modOutboundOrderNumber +
                ", modOutboundAmount=" + modOutboundAmount +
                ", letUv=" + letUv +
                ", letAddActiveUser=" + letAddActiveUser +
                ", letCoollectingPureNewCustomers=" + letCoollectingPureNewCustomers +
                ", letBookingNewCustomersPureInfiltration=" + letBookingNewCustomersPureInfiltration +
                ", letOrderingOrders=" + letOrderingOrders +
                ", letReceivingAmount=" + letReceivingAmount +
                ", letPureOutOfTheLibrary=" + letPureOutOfTheLibrary +
                ", letOutboundNewCustomersPureInfiltration=" + letOutboundNewCustomersPureInfiltration +
                ", letOutboundOrderNumber=" + letOutboundOrderNumber +
                ", letOutboundAmount=" + letOutboundAmount +
                ", uv=" + uv +
                ", addActiveUser=" + addActiveUser +
                ", coollectingPureNewCustomers=" + coollectingPureNewCustomers +
                ", bookingNewCustomersPureInfiltration=" + bookingNewCustomersPureInfiltration +
                ", orderingOrders=" + orderingOrders +
                ", receivingAmount=" + receivingAmount +
                ", pureOutOfTheLibrary=" + pureOutOfTheLibrary +
                ", outboundNewCustomersPureInfiltration=" + outboundNewCustomersPureInfiltration +
                ", outboundOrderNumber=" + outboundOrderNumber +
                ", outboundAmount=" + outboundAmount +
                '}';
    }

    public String getfCombination() {
        return fCombination;
    }

    public void setfCombination(String fCombination) {
        this.fCombination = fCombination;
    }


    public String getAppletsUrl() {
        return appletsUrl;
    }

    public void setAppletsUrl(String appletsUrl) {
        this.appletsUrl = appletsUrl;
    }

    public String getAppletDone() {
        return appletDone;
    }

    public void setAppletDone(String appletDone) {
        this.appletDone = appletDone;
    }

    public String getMobileAccessUrl() {
        return MobileAccessUrl;
    }

    public void setMobileAccessUrl(String mobileAccessUrl) {
        MobileAccessUrl = mobileAccessUrl;
    }


    public Date getfDate() {
        return fDate;
    }

    public void setfDate(Date fDate) {
        this.fDate = fDate;
    }

    public String getfPlan() {
        return fPlan;
    }

    public void setfPlan(String fPlan) {
        this.fPlan = fPlan;
    }

    public String getfUnit() {
        return fUnit;
    }

    public void setfUnit(String fUnit) {
        this.fUnit = fUnit;
    }

    public String getfKeyword() {
        return fKeyword;
    }

    public void setfKeyword(String fKeyword) {
        this.fKeyword = fKeyword;
    }

    public Integer getfShowDate() {
        return fShowDate;
    }

    public void setfShowDate(Integer fShowDate) {
        this.fShowDate = fShowDate;
    }

    public Integer getfClick() {
        return fClick;
    }

    public void setfClick(Integer fClick) {
        this.fClick = fClick;
    }

    public Double getfCost() {
        return fCost;
    }

    public void setfCost(Double fCost) {
        this.fCost = fCost;
    }

    public Integer getfAppShowData() {
        return fAppShowData;
    }

    public void setfAppShowData(Integer fAppShowData) {
        this.fAppShowData = fAppShowData;
    }

    public Integer getfAppClick() {
        return fAppClick;
    }

    public void setfAppClick(Integer fAppClick) {
        this.fAppClick = fAppClick;
    }

    public Double getfAppCost() {
        return fAppCost;
    }

    public void setfAppCost(Double fAppCost) {
        this.fAppCost = fAppCost;
    }

    public Integer getfLetsShowData() {
        return fLetsShowData;
    }

    public void setfLetsShowData(Integer fLetsShowData) {
        this.fLetsShowData = fLetsShowData;
    }

    public Integer getfLetsClick() {
        return fLetsClick;
    }

    public void setfLetsClick(Integer fLetsClick) {
        this.fLetsClick = fLetsClick;
    }

    public Double getfLetsCost() {
        return fLetsCost;
    }

    public void setfLetsCost(Double fLetsCost) {
        this.fLetsCost = fLetsCost;
    }


    public Integer getAllPureOutOfTheLibrary() {
        return allPureOutOfTheLibrary;
    }

    public void setAllPureOutOfTheLibrary(Integer allPureOutOfTheLibrary) {
        this.allPureOutOfTheLibrary = allPureOutOfTheLibrary;
    }

    public Double getAllOutboundAmount() {
        return allOutboundAmount;
    }

    public void setAllOutboundAmount(Double allOutboundAmount) {
        this.allOutboundAmount = allOutboundAmount;
    }

    public Integer getDoneUv() {
        return doneUv;
    }

    public void setDoneUv(Integer doneUv) {
        this.doneUv = doneUv;
    }

    public Integer getDoneAddActiveUser() {
        return doneAddActiveUser;
    }

    public void setDoneAddActiveUser(Integer doneAddActiveUser) {
        this.doneAddActiveUser = doneAddActiveUser;
    }

    public Integer getDoneCoollectingPureNewCustomers() {
        return doneCoollectingPureNewCustomers;
    }

    public void setDoneCoollectingPureNewCustomers(Integer doneCoollectingPureNewCustomers) {
        this.doneCoollectingPureNewCustomers = doneCoollectingPureNewCustomers;
    }

    public Integer getDoneBookingNewCustomersPureInfiltration() {
        return doneBookingNewCustomersPureInfiltration;
    }

    public void setDoneBookingNewCustomersPureInfiltration(Integer doneBookingNewCustomersPureInfiltration) {
        this.doneBookingNewCustomersPureInfiltration = doneBookingNewCustomersPureInfiltration;
    }

    public Integer getDoneOrderingOrders() {
        return doneOrderingOrders;
    }

    public void setDoneOrderingOrders(Integer doneOrderingOrders) {
        this.doneOrderingOrders = doneOrderingOrders;
    }

    public Double getDoneReceivingAmount() {
        return doneReceivingAmount;
    }

    public void setDoneReceivingAmount(Double doneReceivingAmount) {
        this.doneReceivingAmount = doneReceivingAmount;
    }

    public Integer getDonePureOutOfTheLibrary() {
        return donePureOutOfTheLibrary;
    }

    public void setDonePureOutOfTheLibrary(Integer donePureOutOfTheLibrary) {
        this.donePureOutOfTheLibrary = donePureOutOfTheLibrary;
    }

    public Integer getDoneOutboundNewCustomersPureInfiltration() {
        return doneOutboundNewCustomersPureInfiltration;
    }

    public void setDoneOutboundNewCustomersPureInfiltration(Integer doneOutboundNewCustomersPureInfiltration) {
        this.doneOutboundNewCustomersPureInfiltration = doneOutboundNewCustomersPureInfiltration;
    }

    public Integer getDoneOutboundOrderNumber() {
        return doneOutboundOrderNumber;
    }

    public void setDoneOutboundOrderNumber(Integer doneOutboundOrderNumber) {
        this.doneOutboundOrderNumber = doneOutboundOrderNumber;
    }

    public Double getDoneOutboundAmount() {
        return doneOutboundAmount;
    }

    public void setDoneOutboundAmount(Double doneOutboundAmount) {
        this.doneOutboundAmount = doneOutboundAmount;
    }

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

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public Integer getAddActiveUser() {
        return addActiveUser;
    }

    public void setAddActiveUser(Integer addActiveUser) {
        this.addActiveUser = addActiveUser;
    }

    public Integer getCoollectingPureNewCustomers() {
        return coollectingPureNewCustomers;
    }

    public void setCoollectingPureNewCustomers(Integer coollectingPureNewCustomers) {
        this.coollectingPureNewCustomers = coollectingPureNewCustomers;
    }

    public Integer getBookingNewCustomersPureInfiltration() {
        return bookingNewCustomersPureInfiltration;
    }

    public void setBookingNewCustomersPureInfiltration(Integer bookingNewCustomersPureInfiltration) {
        this.bookingNewCustomersPureInfiltration = bookingNewCustomersPureInfiltration;
    }

    public Integer getOrderingOrders() {
        return orderingOrders;
    }

    public void setOrderingOrders(Integer orderingOrders) {
        this.orderingOrders = orderingOrders;
    }

    public Double getReceivingAmount() {
        return receivingAmount;
    }

    public void setReceivingAmount(Double receivingAmount) {
        this.receivingAmount = receivingAmount;
    }

    public Integer getPureOutOfTheLibrary() {
        return pureOutOfTheLibrary;
    }

    public void setPureOutOfTheLibrary(Integer pureOutOfTheLibrary) {
        this.pureOutOfTheLibrary = pureOutOfTheLibrary;
    }

    public Integer getOutboundNewCustomersPureInfiltration() {
        return outboundNewCustomersPureInfiltration;
    }

    public void setOutboundNewCustomersPureInfiltration(Integer outboundNewCustomersPureInfiltration) {
        this.outboundNewCustomersPureInfiltration = outboundNewCustomersPureInfiltration;
    }

    public Integer getOutboundOrderNumber() {
        return outboundOrderNumber;
    }

    public void setOutboundOrderNumber(Integer outboundOrderNumber) {
        this.outboundOrderNumber = outboundOrderNumber;
    }

    public Double getOutboundAmount() {
        return outboundAmount;
    }

    public void setOutboundAmount(Double outboundAmount) {
        this.outboundAmount = outboundAmount;
    }
}
