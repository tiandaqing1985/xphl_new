package com.ruoyi.web.controller.test;

/**
 * @program: ruoyi->ErrorMessage
 * @author: gakki
 * @create: 2019-07-26 15:16
 **/
public class ErrorMessage {
    private String propertyPath;

    private String message;

    public ErrorMessage(String propertyPath, String message) {
        this.propertyPath = propertyPath;
        this.message = message;
    }

    public String getPropertyPath() {
        return propertyPath;
    }

    public void setPropertyPath(String propertyPath) {
        this.propertyPath = propertyPath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
