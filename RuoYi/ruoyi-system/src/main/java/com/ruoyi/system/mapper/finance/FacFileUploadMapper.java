package com.ruoyi.system.mapper.finance;

import com.ruoyi.system.domain.finance.FacFileUpload;

import java.util.List;

/**
 * 财务文件上传记录 数据层
 * 
 * @author ruoyi
 * @date 2020-04-22
 */
public interface FacFileUploadMapper 
{
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
     * 删除财务文件上传记录
     * 
     * @param id 财务文件上传记录ID
     * @return 结果
     */
	public int deleteFacFileUploadById(Long id);
	
	/**
     * 批量删除财务文件上传记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacFileUploadByIds(String[] ids);


	public List<FacFileUpload> ifPicUpload(String num);

	public int updateNum(FacFileUpload facFileUpload);

	
}