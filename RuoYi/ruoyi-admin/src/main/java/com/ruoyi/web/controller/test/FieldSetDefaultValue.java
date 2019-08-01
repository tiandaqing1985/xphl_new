package com.ruoyi.web.controller.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;


/**
 * @Decription:属性设置默认值
 * @program: ruoyi->Default
 * @author: gakki
 * @create: 2019-07-26 15:53
 **/
public class FieldSetDefaultValue {
    private static final Logger log = LoggerFactory.getLogger(FieldSetDefaultValue.class);


    private static final ThreadLocal<Date> LOCAL_DATE_FORMAT = new ThreadLocal<Date>() {
        @Override
        protected Date initialValue() {

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            try {
                String format = sdf1.format(new Date());
                Date now = sdf1.parse(format);
                return now;
            } catch (ParseException e) {
                e.printStackTrace();
                log.info("日期格式化" + e.getMessage());
            }
            return null;
        }
    };

    /**
     * 设置默认值
     * @param s
     */
    public synchronized static void set(Object s) {

        Field[] fields = s.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                boolean fieldHasAnno = field.isAnnotationPresent(DefaultFiled.class);
                if (fieldHasAnno) {
                    DefaultFiled annotation = field.getAnnotation(DefaultFiled.class);
                    if (field.getGenericType().toString().equals("class java.lang.String")) {
                        field.set(s, annotation.msg());
                    }else if (field.getGenericType().toString().equals("class java.lang.Integer")){
                        field.set(s, Integer.valueOf(annotation.msg()));
                    }
                    if (field.get(s) == null) {

                        if (field.getGenericType().toString().equals("class java.util.Date")) {
                            if ("date".equals(annotation.date())){
                                field.set(s, LOCAL_DATE_FORMAT.get());
                            }
                        }
                    }
                }

            } catch (IllegalAccessException e) {
                log.info("反射属性赋值" + e.getMessage());
                e.printStackTrace();
            } finally {
                LOCAL_DATE_FORMAT.remove();

            }


        }


    }
}
