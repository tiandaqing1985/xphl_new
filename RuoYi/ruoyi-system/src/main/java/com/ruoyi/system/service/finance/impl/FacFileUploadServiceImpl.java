package com.ruoyi.system.service.finance.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.finance.FacFileUpload;
import com.ruoyi.system.mapper.finance.FacFileUploadMapper;
import com.ruoyi.system.service.finance.IFacFileUploadService;
import com.ruoyi.system.task.FileUtilsTest;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.poi.hpsf.Thumbnail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * 财务文件上传记录 服务层实现
 *
 * @author ruoyi
 * @date 2020-04-22
 */
@Service
public class FacFileUploadServiceImpl implements IFacFileUploadService {
    @Autowired
    private FacFileUploadMapper facFileUploadMapper;

    private static String[] insert(String[] arr, String str) {
        int size = arr.length;
        String[] tmp = new String[size + 1];
        System.arraycopy(arr, 0, tmp, 0, size);
        tmp[size] = str;
        return tmp;
    }

    /**
     * 查询财务文件上传记录信息
     *
     * @param id 财务文件上传记录ID
     * @return 财务文件上传记录信息
     */
    @Override
    public FacFileUpload selectFacFileUploadById(Long id) {
        return facFileUploadMapper.selectFacFileUploadById(id);
    }

    /**
     * 查询财务文件上传记录列表
     *
     * @param facFileUpload 财务文件上传记录信息
     * @return 财务文件上传记录集合
     */
    @Override
    public List<FacFileUpload> selectFacFileUploadList(FacFileUpload facFileUpload) {
        return facFileUploadMapper.selectFacFileUploadList(facFileUpload);
    }

    /**
     * 新增财务文件上传记录
     *
     * @param facFileUpload 财务文件上传记录信息
     * @return 结果
     */
    @Override
    public int insertFacFileUpload(FacFileUpload facFileUpload) {
        return facFileUploadMapper.insertFacFileUpload(facFileUpload);
    }

    /**
     * 修改财务文件上传记录
     *
     * @param facFileUpload 财务文件上传记录信息
     * @return 结果
     */
    @Override
    public int updateFacFileUpload(FacFileUpload facFileUpload) {
        return facFileUploadMapper.updateFacFileUpload(facFileUpload);
    }

    /**
     * 删除财务文件上传记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFacFileUploadByIds(String ids) {
        return facFileUploadMapper.deleteFacFileUploadByIds(Convert.toStrArray(ids));
    }

    @Override
    public boolean ifPicUpload(String num) {

        List<FacFileUpload> a = facFileUploadMapper.ifPicUpload(num);


        if (a != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String xaizainum(String num) {
        FacFileUpload fileUpload = new FacFileUpload();
        fileUpload.setNum(num);
        List<FacFileUpload> list = facFileUploadMapper.selectFacFileUploadList(fileUpload);
        if (list != null && list.size() > 0) {
            String[] urls = new String[list.size()];
            for (int i = 0; list.size() > i; i++) {
                urls = insert(urls, list.get(i).getFilePath());
                FacFileUploadServiceImpl aaa = new FacFileUploadServiceImpl();
                try{
                    aaa.xiazai(num + i, list.get(i).getFilePath());
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
            FileUtilsTest.compressToZip("E:\\b","E:\\a",num+".zip");
            // sourceFilePath 源文件路径 zipFilePath    压缩后文件存储路径  zipFilename    压缩文件名
            return "下载成功";
        }
        return "无图片文件";
    }

    public void xiazai(String num, String path) throws IOException{
        path = "http://192.168.88.191/upload/2020/04/24/04b9509fb271b6b2755a7905276ff71a.jpg";
        URL url = null;
        //从网络上下载一张图片
        InputStream inputStream = null;
        OutputStream outputStream = null;
        //建立一个网络链接
        HttpURLConnection con = null;
        try {
            url = new URL(path);
            con = (HttpURLConnection) url.openConnection();
            inputStream = con.getInputStream();
            outputStream = new FileOutputStream(new File("E:"+File.separator+"a"+File.separator+"ccccc.jpg"));
            int n = -1;
            byte b[] = new byte[1024];
            while ((n = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, n);
            }
            outputStream.flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
