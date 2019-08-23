package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OaFileDownloadMapper;
import com.ruoyi.system.domain.OaFileDownload;
import com.ruoyi.system.service.IOaFileDownloadService;
import com.ruoyi.common.core.text.Convert;

/**
 * 文件下载记录 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-16
 */
@Service
public class OaFileDownloadServiceImpl implements IOaFileDownloadService 
{
	@Autowired
	private OaFileDownloadMapper oaFileDownloadMapper;

	/**
     * 查询文件下载记录信息
     * 
     * @param id 文件下载记录ID
     * @return 文件下载记录信息
     */
    @Override
	public OaFileDownload selectOaFileDownloadById(Long id)
	{
	    return oaFileDownloadMapper.selectOaFileDownloadById(id);
	}
	
	/**
     * 查询文件下载记录列表
     * 
     * @param oaFileDownload 文件下载记录信息
     * @return 文件下载记录集合
     */
	@Override
	public List<OaFileDownload> selectOaFileDownloadList(OaFileDownload oaFileDownload)
	{
	    return oaFileDownloadMapper.selectOaFileDownloadList(oaFileDownload);
	}
	
    /**
     * 新增文件下载记录
     * 
     * @param oaFileDownload 文件下载记录信息
     * @return 结果
     */
	@Override
	public int insertOaFileDownload(OaFileDownload oaFileDownload)
	{
	    return oaFileDownloadMapper.insertOaFileDownload(oaFileDownload);
	}
	
	/**
     * 修改文件下载记录
     * 
     * @param oaFileDownload 文件下载记录信息
     * @return 结果
     */
	@Override
	public int updateOaFileDownload(OaFileDownload oaFileDownload)
	{
	    return oaFileDownloadMapper.updateOaFileDownload(oaFileDownload);
	}

	/**
     * 删除文件下载记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOaFileDownloadByIds(String ids)
	{
		return oaFileDownloadMapper.deleteOaFileDownloadByIds(Convert.toStrArray(ids));
	}
	
}
