package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OaFileUploadMapper;
import com.ruoyi.system.domain.OaFileUpload;
import com.ruoyi.system.service.IOaFileUploadService;
import com.ruoyi.common.core.text.Convert;

/**
 * 文件上传记录 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-16
 */
@Service
public class OaFileUploadServiceImpl implements IOaFileUploadService 
{
	@Autowired
	private OaFileUploadMapper oaFileUploadMapper;

	/**
     * 查询文件上传记录信息
     * 
     * @param fileId 文件上传记录ID
     * @return 文件上传记录信息
     */
    @Override
	public OaFileUpload selectOaFileUploadById(Long fileId)
	{
	    return oaFileUploadMapper.selectOaFileUploadById(fileId);
	}
	
	/**
     * 查询文件上传记录列表
     * 
     * @param oaFileUpload 文件上传记录信息
     * @return 文件上传记录集合
     */
	@Override
	public List<OaFileUpload> selectOaFileUploadList(OaFileUpload oaFileUpload)
	{
	    return oaFileUploadMapper.selectOaFileUploadList(oaFileUpload);
	}
	
    /**
     * 新增文件上传记录
     * 
     * @param oaFileUpload 文件上传记录信息
     * @return 结果
     */
	@Override
	public int insertOaFileUpload(OaFileUpload oaFileUpload)
	{
	    return oaFileUploadMapper.insertOaFileUpload(oaFileUpload);
	}
	
	/**
     * 修改文件上传记录
     * 
     * @param oaFileUpload 文件上传记录信息
     * @return 结果
     */
	@Override
	public int updateOaFileUpload(OaFileUpload oaFileUpload)
	{
	    return oaFileUploadMapper.updateOaFileUpload(oaFileUpload);
	}

	/**
     * 删除文件上传记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOaFileUploadByIds(String ids)
	{
		return oaFileUploadMapper.deleteOaFileUploadByIds(Convert.toStrArray(ids));
	}

	@Override
	public boolean ifPicUpload(String loginName) {
		List<OaFileUpload> fList = oaFileUploadMapper.selectPicList(loginName);
		if(fList.size() == 0){
			return false;
		}
		return true;
	}
	
}
