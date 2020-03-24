package com.ruoyi.system.domain;

import java.util.Map;

public class XzExpenseCompareVO {

    private String flg;

    /** 地域*/
    private String regionCompare;
    /** 部门 查询用 */
    private String[] deptsCompare;

    /** 请求参数 */
    private Map<String, Object> paramsCompare;

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public String getRegionCompare() {
        return regionCompare;
    }

    public void setRegionCompare(String regionCompare) {
        this.regionCompare = regionCompare;
    }

    public String[] getDeptsCompare() {
        return deptsCompare;
    }

    public void setDeptsCompare(String[] deptsCompare) {
        this.deptsCompare = deptsCompare;
    }

    public Map<String, Object> getParamsCompare() {
        return paramsCompare;
    }

    public void setParamsCompare(Map<String, Object> paramsCompare) {
        this.paramsCompare = paramsCompare;
    }
}
