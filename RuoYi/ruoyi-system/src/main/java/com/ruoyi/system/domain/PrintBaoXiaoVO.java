package com.ruoyi.system.domain;

public class PrintBaoXiaoVO {
    private String type;
    private Double money;
    private Double documentNum;
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getDocumentNum() {
        return documentNum;
    }

    public void setDocumentNum(Double documentNum) {
        this.documentNum = documentNum;
    }
}
