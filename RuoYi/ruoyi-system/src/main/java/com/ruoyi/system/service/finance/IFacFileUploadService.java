package com.ruoyi.system.service.finance;

import com.ruoyi.system.domain.finance.FacFileUpload;

import java.util.List;

/**
 * 财务文件上传记录 服务层
 *
 * @author ruoyi
 * @date 2020-04-22
 */
public interface IFacFileUploadService {
    /**
     * 查询财务文件上传记录信息
     *
     * @param id 财务文件上传记录ID
     * @return 财务文件上传记录信息
     */
    public FacFileUpload selectFacFileUploadById(Long id);

    /**
     * 查询财务文件上传记录列表
     *
     * @param facFileUpload 财务文件上传记录信息
     * @return 财务文件上传记录集合
     */
    public List<FacFileUpload> selectFacFileUploadList(FacFileUpload facFileUpload);

    /**
     * 新增财务文件上传记录
     *
     * @param facFileUpload 财务文件上传记录信息
     * @return 结果
     */
    public int insertFacFileUpload(FacFileUpload facFileUpload);

    /**
     * 修改财务文件上传记录
     *
     * @param facFileUpload 财务文件上传记录信息
     * @return 结果
     */
    public int updateFacFileUpload(FacFileUpload facFileUpload);

    /**
     * 删除财务文件上传记录信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFacFileUploadByIds(String ids);

    /**
     * 判断是否存在上传文件数据
     */
    public boolean ifPicUpload(String num);
    /**
     * 通过num下载图片文件夹
     */
    public String xaizainum(String num);

    /**
     * 修改上传信息
     */
    public int updateNum(String num , String fileNum);

}
