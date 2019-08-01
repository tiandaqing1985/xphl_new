package com.ruoyi.common.utils;

import com.ruoyi.common.enums.FacApplyType;
import com.ruoyi.common.utils.bean.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * //用来提供生成申请编号,创建时间，添加人，
 *
 * @program: ruoyi->FinanceAddHelper
 * @author: gakki
 * @create: 2019-07-30 17:26
 **/
public class FinanceAddHelper {


    private static final Logger log = LoggerFactory.getLogger(FinanceAddHelper.class);


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
                System.out.println("日期格式化" + e.getMessage());
            }
            return null;
        }
    };

    /**
     * 设置默认值
     *
     * @param s
     */
    public synchronized static void set(Object s, FacApplyType num) {

        Field[] fields = s.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                //增加申请编号
                if (("num").equals(field.getName())) {
                    IdWorker idWorker = new IdWorker(0, 0);
                    field.set(s, num.getIdentification() + String.valueOf(idWorker.nextId()));
                }
                boolean fieldHasAnno = field.isAnnotationPresent(DefaultFiled.class);
                if (fieldHasAnno) {
                    DefaultFiled annotation = field.getAnnotation(DefaultFiled.class);

                    //增加申请类型
                    if (annotation.msg().equals("type")) {
                        field.set(s, num.getType());
                    }
                    //增加申请人id
                    if (annotation.msg().equals("user")) {
                        field.set(s,"");
                    }
                    if (field.get(s) == null) {
                        if (field.getGenericType().toString().equals("class java.util.Date")) {
                            if ("date".equals(annotation.date())) {
                                field.set(s, LOCAL_DATE_FORMAT.get());
                            }
                        }
                    }
                }

            } catch (IllegalAccessException e) {
                System.out.println("反射属性赋值" + e.getMessage());
                e.printStackTrace();
            } finally {
                LOCAL_DATE_FORMAT.remove();

            }


        }


    }


}
