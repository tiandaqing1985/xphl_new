package com.ruoyi.web.controller.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ruoyi->ValidResult
 * @author: gakki
 * @create: 2019-07-26 15:15
 **/
public class ValidResult {
    /**
     * 是否有错误
     */
    private boolean hasErrors;

    /**
     * 错误信息
     */
    private List<ErrorMessage> errors;

    public ValidResult() {
        this.errors = new ArrayList<>();
    }
    public boolean hasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    /**
     * 获取所有验证信息
     * @return 集合形式
     */
    public List<ErrorMessage> getAllErrors() {
        return errors;
    }
    /**
     * 获取所有验证信息
     * @return 字符串形式
     */
    public String getErrors(){
        StringBuilder sb = new StringBuilder();
        for (ErrorMessage error : errors) {
            sb.append(error.getMessage()).append(" ");
        }
        return sb.toString();
    }

    public void addError(String propertyName, String message) {
        this.errors.add(new ErrorMessage(propertyName, message));
    }
}
