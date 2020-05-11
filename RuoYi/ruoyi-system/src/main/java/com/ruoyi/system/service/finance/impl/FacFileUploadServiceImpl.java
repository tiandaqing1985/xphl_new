package com.ruoyi.system.service.finance.impl;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.finance.FacFileUpload;
import com.ruoyi.system.mapper.finance.FacFileUploadMapper;
import com.ruoyi.system.service.finance.IFacFileUploadService;
import com.ruoyi.system.task.FileUtilsTest;
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

    static void copy(String srcPathStr, String desPathStr) {
        //获取源文件的名称
        String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("29") + 1); //目标文件地址
        System.out.println("源文件:" + newFileName);
        desPathStr = desPathStr + File.separator + newFileName; //源文件地址
        System.out.println("目标文件地址:" + desPathStr);
        try {
            FileInputStream fis = new FileInputStream(srcPathStr);//创建输入流对象
            FileOutputStream fos = new FileOutputStream(desPathStr); //创建输出流对象
            byte datas[] = new byte[1024 * 8];//创建搬运工具
            int len = 0;//创建长度
            while ((len = fis.read(datas)) != -1)//循环读取数据
            {
                fos.write(datas, 0, len);
            }
            fis.close();//释放资源
            fis.close();//释放资源
        } catch (Exception e) {
            e.printStackTrace();
        }
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
     * 修改上传信息
     *
     * @return 结果
     */
    @Override
    public int updateNum(String num, String fileNum) {
        List<FacFileUpload> list = facFileUploadMapper.ifPicUpload(num);
        if (list != null) {
            for (FacFileUpload a : list) {
                a.setNum(fileNum);
                facFileUploadMapper.updateFacFileUpload(a);
            }
            return 1;
        } else {
            return 2;
        }

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
            String filePath = Global.getUploadPath();
            File file = new File(filePath + num);
            file.mkdirs();
            String filePaths = "/opt/tomcat_prod/webapps/upload/upload/";
            for (int i = 0; list.size() > i; i++) {

                FacFileUploadServiceImpl aaa = new FacFileUploadServiceImpl();
                try {
                    aaa.transferFile(filePaths + list.get(i).getFilePath().substring(list.get(i).getFilePath().indexOf("202")), "/opt/tomcat_prod/webapps/upload/upload/" + num + File.separator + num + i + ".jpg");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            FileUtilsTest.compressToZip(filePath + num, filePath + num + "aaa", num + ".zip");

            return "下载成功";
        }
        return "无图片文件";
    }

    private void transferFile(String oldPath, String newPath) throws Exception {

        int byteread = 0;
        File oldFile = new File(oldPath);
        FileInputStream fin = null;
        FileOutputStream fout = null;
        System.out.println(oldPath);
        System.out.println(newPath);
        try {
            if (oldFile.exists()) {
                fin = new FileInputStream(oldFile);
                fout = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = fin.read(buffer)) != -1) {
                    fout.write(buffer, 0, byteread);
                }
                if (fin != null) {
                    fin.close();//如果流不关闭,则删除不了旧文件
                }
            } else {
                throw new Exception("需要转移的文件不存在!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (fin != null) {
                fin.close();
            }
        }
    }

    public void xiazai(String num, String nums, String path, String filePath) throws IOException {

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
            outputStream = new FileOutputStream(new File(filePath + num + File.separator + nums + ".jpg"));
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
