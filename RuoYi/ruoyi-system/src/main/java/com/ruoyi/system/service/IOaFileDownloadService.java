package com.ruoyi.system.service;

import com.ruoyi.system.domain.OaFileDownload;
import java.util.List;

/**
 * 文件下载记录 服务层
 * 
 * @author ruoyi
 * @date 2019-08-16
 */
public interface IOaFileDownloadService 
{
	/**
     * 查询文件下载记录信息
     * 
     * @param id 文件下载记录ID
     * @return 文件下载记录信息
     */
	public OaFileDownload selectOaFileDownloadById(Long id);
	
	/**
     * 查询文件下载记录列表
     * 
     * @param oaFileDownload 文件下载记录信息
     * @return 文件下载记录集合
     */
	public List<OaFileDownload> selectOaFileDownloadList(OaFileDownload oaFileDownload);
	
	/**
     * 新增文件下载记录
     * 
     * @param oaFileDownload 文件下载记录信息
     * @return 结果
     */
	public int insertOaFileDownload(OaFileDownload oaFileDownload);
	
	/**
     * 修改文件下载记录
     * 
     * @param oaFileDownload 文件下载记录信息
     * @return 结果
     */
	public int updateOaFileDownload(OaFileDownload oaFileDownload);
		
	/**
     * 删除文件下载记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOaFileDownloadByIds(String ids);
	
}
