package com.ruoyi.system.task;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/**
 * @program: ruoyi->testaa
 * @author: gakki
 * @create: 2020-04-28 11:45
 **/
public class testaa {



    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub

        String url3 = "http://119.61.22.42:5555/profile/upload/2019/12/16/416b69775f16a8f64469ec824bc02b56.jpg";


        downPictor(url3,"1111.jpg");
        System.out.println("1111.jpg");
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
