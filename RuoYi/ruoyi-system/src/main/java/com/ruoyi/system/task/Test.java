package com.ruoyi.system.task;

import net.coobird.thumbnailator.Thumbnails;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Test {
    public static void merge(String srcFiles, String destFilePath) {
        //判断原文件路径是否为空
        if (srcFiles == null) {
            throw new IllegalArgumentException(srcFiles + " must be not null");
        }
        //创建目标文件夹，并且判断目标文件夹是否存在
        File destFile = new File(destFilePath);
        if (!destFile.exists()) {
            boolean effect = destFile.mkdirs();
            if (!effect) {
                throw new IllegalArgumentException(destFile + " create failed");
            }
        }
        //获取原文件夹中所有的文件
        File[] files = listAllFiles(srcFiles);
        for (File src : files) {
            //如果文件格式不是jpg或者png，就不参与文件合并
            if (!src.getName().endsWith("jpg") && !src.getName().endsWith("png")) {
                continue;
            }
            //记录每个参与合并的文件的字节长度
            int length = (int) src.length();
            //进行文件传入和目的文件的传出
            try (FileInputStream in = new FileInputStream(src);
                 FileOutputStream out = new FileOutputStream(new File(destFile, "mergeP2.jpg"), true)) {
                //将每个参与合并的图片文件的长度加在文件字节前，用于记录参与合并的文件长度，并且在解压文件时起到指示此文件解压完成的作用
                //这里需要将文件的长度转换成byte数组，再传入目的文件中
                //length -> byte[]   82(int) -> 32(bit)
                for (int i = 0; i < trans(length).length; i++) {
//                    System.out.println(trans(length)[i]);
                    out.write(trans(length)[i]);
                }
                //读取原文件中的字节内容
                byte[] buff = new byte[1024];
                int len = -1;
                while ((len = in.read(buff)) != -1) {
                    out.write(buff, 0, len);
                }
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //实现int类型转换为byte数组
    public static byte[] trans(int data) {
        //int转换成byte[] 不需要 & 0xFF
        //0xFF是字面量：00000000 00000000 00000000 11111111
        byte[] ret = new byte[4];
        ret[0] = (byte) (data >> 24);
        ret[1] = (byte) (data >> 16);
        ret[2] = (byte) (data >> 8);
        ret[3] = (byte) data;
        return ret;
    }

    //实现byte数组转换成int类型
    public static int byteArrayToInt(byte[] buff) {
        //byte[] 转换成int 必须 & 0xFF
        int values = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (3 - i) * 8;
            values += (buff[i] & 0xFF) << shift;
        }
        return values;
    }

    //用来显示原文件夹下所有的文件，简化添加图片文件的步骤
    public static File[] listAllFiles(String srcFilePath) {
        File srcFile = new File(srcFilePath);
        if (srcFile.isFile()) {
            return new File[]{srcFile};
        }
        return srcFile.listFiles();
    }






    public static void main(String [] args) throws IOException{
        String path = "RuoYi\\aaaa.jpg";
        Thumbnails.of(path).scale(1f)
                .outputQuality(0.5f)
                .toFile("E:"+File.separator+"image_200x300.jpg");

    }




}
