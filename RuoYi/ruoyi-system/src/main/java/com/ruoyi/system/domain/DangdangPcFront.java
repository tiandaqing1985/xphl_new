package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 当当pc前端表 dangdang_pc_front
 *
 * @author ruoyi
 * @date 2019-07-19
 */
public class DangdangPcFront extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;
    /**
     * 账户
     */
    @Excel(name = "账户")
    private String account;
    /**
     * 日期
     */
    @Excel(name = "日期")

    private Date ddDate;
    /**
     * 推广计划
     */
    @Excel(name = "推广计划")

    private String plan;
    /**
     * 推广单元
     */
    @Excel(name = "推广单元")

    private String unit;
    /**
     * 关键词
     */
    @Excel(name = "关键词")

    private String keyword;
    /**
     * 展现
     */
    @Excel(name = "展现")

    private Integer showdata;
    /**
     * 点击
     */
    @Excel(name = "点击")

    private Integer click;
    /**
     * 消费
     */
    @Excel(name = "消费")

    private Double cost;
    /**
     * 点击率
     */
    @Excel(name = "点击率")

    private String clickRate;
    /**
     * 平均点击价格
     */
    @Excel(name = "平均点击价格")

    private Double avgClickPrice;
    /**
     * 首页平均排名
     */
    @Excel(name = "首页平均排名")

    private Double homeAvgRank;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setDdDate(Date ddDate) {
        this.ddDate = ddDate;
    }

    public Date getDdDate() {
        return ddDate;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getPlan() {
        return plan;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setShowdata(Integer showdata) {
        this.showdata = showdata;
    }

    public Integer getShowdata() {
        return showdata;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Integer getClick() {
        return click;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getCost() {
        return cost;
    }

    public void setClickRate(String clickRate) {
        this.clickRate = clickRate;
    }

    public String getClickRate() {
        return clickRate;
    }

    public void setAvgClickPrice(Double avgClickPrice) {
        this.avgClickPrice = avgClickPrice;
    }

    public Double getAvgClickPrice() {
        return avgClickPrice;
    }

    public void setHomeAvgRank(Double homeAvgRank) {
        this.homeAvgRank = homeAvgRank;
    }

    public Double getHomeAvgRank() {
        return homeAvgRank;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getBid() {
        return bid;
    }

    public void setPartSpeech(String partSpeech) {
        this.partSpeech = partSpeech;
    }

    public String getPartSpeech() {
        return partSpeech;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("account", getAccount())
                .append("ddDate", getDdDate())
                .append("plan", getPlan())
                .append("unit", getUnit())
                .append("keyword", getKeyword())
                .append("showdata", getShowdata())
                .append("click", getClick())
                .append("cost", getCost())
                .append("clickRate", getClickRate())
                .append("avgClickPrice", getAvgClickPrice())
                .append("homeAvgRank", getHomeAvgRank())
                .append("bid", getBid())
                .append("partSpeech", getPartSpeech())
                .toString();
    }
}
