package com.ruoyi.common.enums;

/**
 * 财务管理 申请类型
 */
public enum FacApplyType {

    GROUP_BUILDING("团建申请", "TJ"),
    HOSPITALITY("招待费申请", "ZD"),
    LOAN("借款申请", "JK"),
    PAY_PUBLIC("对公支付申请", "DG"),
    REIMBURSE("报销申请", "BX"),
    TRAVEL("差旅申请", "CL");

    private final String Identification;
    private final String type;

    FacApplyType(String type, String Identification) {
        this.Identification = Identification;
        this.type = type;
    }

    public String getIdentification() {
        return Identification;
    }

    public String getType() {
        return type;
    }

}

