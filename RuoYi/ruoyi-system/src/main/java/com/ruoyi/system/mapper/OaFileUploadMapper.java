package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.OaFileUpload;
import java.util.List;	

/**
 * 文件上传记录 数据层
 * 
 * @author ruoyi
 * @date 2019-08-16
 */
/**
 * @author admin
 *
 */
public interface OaFileUploadMapper 
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
	 * 上传图片后关联申请表和文件记录表
	 * @param oaFileUpload
	 * @return
	 */
	public int updateOaFileUploadByApply(OaFileUpload oaFileUpload);
	
	/**
     * 删除文件上传记录
     * 
     * @param fileId 文件上传记录ID
     * @return 结果
     */
	public int deleteOaFileUploadById(Long fileId);
	
	/**
     * 批量删除文件上传记录
     * 
     * @param fileIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteOaFileUploadByIds(String[] fileIds);
	
	/**
	 * 查询图片是否上传
	 * @param loginName
	 * @return
	 */
	public List<OaFileUpload> selectPicList(String loginName);
	
}