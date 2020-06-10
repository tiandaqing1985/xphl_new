package com.ruoyi.system.task;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/**
 * @program: ruoyi->testaa
 * @author: gakki
 * @create: 2020-04-28 11:45
 **/
public class testaa {


    public static void main(String[] args) {
        // TODO Auto-generated method stub


        /* 3.List中的使用 */
        List<String> list = new ArrayList<String>();
        list.add("我1");
        list.add("爱2");
        list.add("中3");
        list.add("国4");

        // 增强for循环
        for (String item : list){
            System.out.println(item);
        }

        //普通for循环
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        //迭代器遍历
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }


    public static void downPictor(String url,String name){
        CloseableHttpClient httpClient = null;
        InputStream inputStream = null;
        File file = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpGet method = new HttpGet(url);
            HttpResponse result = httpClient.execute(method);
            inputStream = result.getEntity().getContent();
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] data = readInputStream(inputStream);
            //new一个文件对象用来保存图片，默认保存当前工程根目录
            File imageFile = new File("D:"+name);
            //创建输出流
            FileOutputStream outStream = new FileOutputStream(imageFile);
            //写入数据
            outStream.write(data);
            //关闭输出流
            outStream.close();
        } catch (Exception e) {
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {

            }
            try {
                httpClient.close();
            } catch (IOException e) {

            }
        }
    }


    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }





}
