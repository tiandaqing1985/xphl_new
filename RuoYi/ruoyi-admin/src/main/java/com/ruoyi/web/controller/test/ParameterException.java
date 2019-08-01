package com.ruoyi.web.controller.test;

/**
 * @program: JWT->parameterException
 * @author: gakki
 * @create: 2019-07-26 14:37
 **/
public class ParameterException extends  RuntimeException {
    ParameterException(){}

    ParameterException(String message)
    {
        super(message);
    }
}
