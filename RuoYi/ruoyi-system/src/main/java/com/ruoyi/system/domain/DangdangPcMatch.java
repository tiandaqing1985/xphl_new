package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 当当PC匹配表 dangdang_pc_match
 *
 * @author ruoyi
 * @date 2019-07-19
 */
public class DangdangPcMatch extends BaseEntity {
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
     * 备注
     */
    @Excel(name = "备注")

    private String remarks;
    /**
     * 渠道号
     */
    @Excel(name = "渠道号")

    private String channelId;
    /**
     * 词性
     */
    @Excel(name = "词性")

    private String word;
    /**
     * 二级类目
     */
    @Excel(name = "二级类目")

    private String secondKind;
    /**
     * 三级类目
     */
    @Excel(name = "三级类目")

    private String thiredKind;
    /**
     * 后台组合
     */

    private String back;

    /**
     * uv组合
     */

    private String joinUv;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }



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

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setSecondKind(String secondKind) {
        this.secondKind = secondKind;
    }

    public String getSecondKind() {
        return secondKind;
    }

    public void setThiredKind(String thiredKind) {
        this.thiredKind = thiredKind;
    }

    public String getThiredKind() {
        return thiredKind;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getBack() {
        return back;
    }

    public void setJoinUv(String joinUv) {
        this.joinUv = joinUv;
    }

    public String getJoinUv() {
        return joinUv;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("account", getAccount())
                .append("plan", getPlan())
                .append("unit", getUnit())

                .append("remarks", getRemarks())
                .append("channelId", getChannelId())
                .append("word", getWord())
                .append("secondKind", getSecondKind())
                .append("thiredKind", getThiredKind())
                .append("back", getBack())
                .append("joinUv", getJoinUv())
                .toString();
    }
}
