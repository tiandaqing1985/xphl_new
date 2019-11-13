package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.XzOfficeAsstes;
import java.util.List;	

/**
 * 办公资产记录 数据层
 * 
 * @author ruoyi
 * @date 2019-09-03
 */
public interface XzOfficeAsstesMapper 
{
	/**
     * 查询办公资产记录信息
     * 
     * @param id 办公资产记录ID
     * @return 办公资产记录信息
     */
	public XzOfficeAsstes selectXzOfficeAsstesById(Long id);
	
	/**
     * 查询办公资产记录列表
     * 
     * @param xzOfficeAsstes 办公资产记录信息
     * @return 办公资产记录集合
     */
	public List<XzOfficeAsstes> selectXzOfficeAsstesList(XzOfficeAsstes xzOfficeAsstes);
	
	/**
     * 新增办公资产记录
     * 
     * @param xzOfficeAsstes 办公资产记录信息
     * @return 结果
     */
	public int insertXzOfficeAsstes(XzOfficeAsstes xzOfficeAsstes);
	
	/**
     * 修改办公资产记录
     * 
     * @param xzOfficeAsstes 办公资产记录信息
     * @return 结果
     */
	public int updateXzOfficeAsstes(XzOfficeAsstes xzOfficeAsstes);
	
	/**
     * 删除办公资产记录
     * 
     * @param id 办公资产记录ID
     * @return 结果
     */
	public int deleteXzOfficeAsstesById(Long id);
	
	/**
     * 批量删除办公资产记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzOfficeAsstesByIds(String[] ids);
	
}