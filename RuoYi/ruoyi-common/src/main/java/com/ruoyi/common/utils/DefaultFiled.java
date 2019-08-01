package com.ruoyi.common.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Decription:默认值设置
 * @program: ruoyi->A
 * @author: gakki
 * @create: 2019-07-26 15:46
 **/
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultFiled {

    String msg() default "";
    String date() default "date";
}
