package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.XzAssetType;
import java.util.List;	

/**
 * 资产父类型 数据层
 * 
 * @author ruoyi
 * @date 2019-08-15
 */
public interface XzAssetTypeMapper 
{
	/**
     * 查询资产父类型信息
     * 
     * @param id 资产父类型ID
     * @return 资产父类型信息
     */
	public XzAssetType selectXzAssetTypeById(Long id);
	
	/**
     * 查询资产父类型列表
     * 
     * @param xzAssetType 资产父类型信息
     * @return 资产父类型集合
     */
	public List<XzAssetType> selectXzAssetTypeList(XzAssetType xzAssetType);
	
	/**
     * 新增资产父类型
     * 
     * @param xzAssetType 资产父类型信息
     * @return 结果
     */
	public int insertXzAssetType(XzAssetType xzAssetType);
	
	/**
     * 修改资产父类型
     * 
     * @param xzAssetType 资产父类型信息
     * @return 结果
     */
	public int updateXzAssetType(XzAssetType xzAssetType);
	
	/**
     * 删除资产父类型
     * 
     * @param id 资产父类型ID
     * @return 结果
     */
	public int deleteXzAssetTypeById(Long id);
	
	/**
     * 批量删除资产父类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzAssetTypeByIds(String[] ids);

	/**
	 * 根据资产父类型
	 * @return 资产父类型集合信息
	 */
	public List<XzAssetType> selectXzAssetTypeAll();

	
}