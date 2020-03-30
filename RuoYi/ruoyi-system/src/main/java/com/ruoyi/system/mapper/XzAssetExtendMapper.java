package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.XzAssetExtend;
import java.util.List;	

/**
 * 资产扩展 数据层
 * 
 * @author ruoyi
 * @date 2020-03-27
 */
public interface XzAssetExtendMapper 
{
	/**
     * 查询资产扩展信息
     * 
     * @param id 资产扩展ID
     * @return 资产扩展信息
     */
	public XzAssetExtend selectXzAssetExtendById(Long id);
	/**
	 * 查询资产扩展信息
	 *
	 * @param id 资产ID
	 * @return 资产扩展信息
	 */
	public XzAssetExtend selectXzAssetExtendByAssetId(Long id);
	
	/**
     * 查询资产扩展列表
     * 
     * @param xzAssetExtend 资产扩展信息
     * @return 资产扩展集合
     */
	public List<XzAssetExtend> selectXzAssetExtendList(XzAssetExtend xzAssetExtend);
	
	/**
     * 新增资产扩展
     * 
     * @param xzAssetExtend 资产扩展信息
     * @return 结果
     */
	public int insertXzAssetExtend(XzAssetExtend xzAssetExtend);
	
	/**
     * 修改资产扩展
     * 
     * @param xzAssetExtend 资产扩展信息
     * @return 结果
     */
	public int updateXzAssetExtend(XzAssetExtend xzAssetExtend);
	
	/**
     * 删除资产扩展
     * 
     * @param id 资产扩展ID
     * @return 结果
     */
	public int deleteXzAssetExtendById(Long id);
	
	/**
     * 批量删除资产扩展
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzAssetExtendByIds(String[] ids);
	
}