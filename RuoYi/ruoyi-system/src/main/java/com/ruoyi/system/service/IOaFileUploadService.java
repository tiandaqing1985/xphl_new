package com.ruoyi.system.service;

import com.ruoyi.system.domain.OaFileUpload;
import java.util.List;

/**
 * 文件上传记录 服务层
 * 
 * @author ruoyi
 * @date 2019-08-16
 */
public interface IOaFileUploadService 
{
	/**
     * 查询文件上传记录信息
     * 
     * @param fileId 文件上传记录ID
     * @return 文件上传记录信息
     */
	public OaFileUpload selectOaFileUploadById(Long fileId);
	
	/**
     * 查询文件上传记录列表
     * 
     * @param oaFileUpload 文件上传记录信息
     * @return 文件上传记录集合
     */
	public List<OaFileUpload> selectOaFileUploadList(OaFileUpload oaFileUpload);
	
	/**
     * 新增文件上传记录
     * 
     * @param oaFileUpload 文件上传记录信息
     * @return 结果
     */
	public int insertOaFileUpload(OaFileUpload oaFileUpload);
	
	/**
     * 修改文件上传记录
     * 
     * @param oaFileUpload 文件上传记录信息
     * @return 结果
     */
	public int updateOaFileUpload(OaFileUpload oaFileUpload);
		
	/**
     * 删除文件上传记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOaFileUploadByIds(String ids);
	
	/**
	 * 验证图片是否已经上传
	 * @param loginName
	 * @return
	 */
	public boolean ifPicUpload(String loginName);
	
}
