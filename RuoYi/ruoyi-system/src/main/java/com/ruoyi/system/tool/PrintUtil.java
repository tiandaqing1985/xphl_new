package com.ruoyi.system.tool;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class PrintUtil {

    private static String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();

    public static Template getTemplate(String name) {
        try {
            // 通过Freemaker的Configuration读取相应的ftl
            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            // 设定去哪里读取相应的ftl模板文件
            ClassUtils.getDefaultClassLoader().getResourceAsStream("");
           cfg.setDirectoryForTemplateLoading(new File(path + File.separator + "templates" + File.separator + "printModel"));
//                        cfg.setDirectoryForTemplateLoading(new File("/root/caiwu/model"));
//            cfg.setDirectoryForTemplateLoading(new File("G:\\testdmeo\\模板"));
            //获取模板（template）
            Template template = cfg.getTemplate(name);
            return template;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将解析之后的文件内容返回字符串
     *
     * @param name 模板文件名
     * @param root 数据Map
     * @return
     */
    public static String printString(String name, Map<String, Object> root) {
        StringWriter out = new StringWriter();
        try {
            //通过一个文件输出流，就可以写到相应的文件中
            Template temp = getTemplate(name);
            temp.process(root, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return out.toString();
    }
}
