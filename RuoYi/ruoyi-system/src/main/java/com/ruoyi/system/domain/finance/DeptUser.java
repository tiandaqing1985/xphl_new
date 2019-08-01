package com.ruoyi.system.domain.finance;

/**
 * @program: ruoyi->DeptUser
 * @author: gakki
 * @create: 2019-07-31 17:40
 **/
public class DeptUser {
    /**
     * 用户名
     */
    private  String userName;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 部门id
     */
    private  Long deptId;
    /**
     * 父部门id
     */
    private  Long parentId;
    /**
     * 部门名称
     */
    private  String deptName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "DeptUser{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                ", deptId=" + deptId +
                ", parentId=" + parentId +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
