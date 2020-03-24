package com.ruoyi.system.domain;

public class XzExpenseDetailRequestVO {

    private String flg;

    private Long expenseTypeParent;

    private String[] depts;
    private String startTime;
    private String endTime;
    private String region;

    private String[] deptsCompare;
    private String startTimeCompare;
    private String endTimeCompare;
    private String regionCompare;

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionCompare() {
        return regionCompare;
    }

    public void setRegionCompare(String regionCompare) {
        this.regionCompare = regionCompare;
    }

    public Long getExpenseTypeParent() {
        return expenseTypeParent;
    }

    public void setExpenseTypeParent(Long expenseTypeParent) {
        this.expenseTypeParent = expenseTypeParent;
    }

    public String[] getDepts() {
        return depts;
    }

    public void setDepts(String[] depts) {
        this.depts = depts;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String[] getDeptsCompare() {
        return deptsCompare;
    }

    public void setDeptsCompare(String[] deptsCompare) {
        this.deptsCompare = deptsCompare;
    }

    public String getStartTimeCompare() {
        return startTimeCompare;
    }

    public void setStartTimeCompare(String startTimeCompare) {
        this.startTimeCompare = startTimeCompare;
    }

    public String getEndTimeCompare() {
        return endTimeCompare;
    }

    public void setEndTimeCompare(String endTimeCompare) {
        this.endTimeCompare = endTimeCompare;
    }
}
