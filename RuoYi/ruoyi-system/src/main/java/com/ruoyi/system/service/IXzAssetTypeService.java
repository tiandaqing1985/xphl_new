package com.ruoyi.system.service;

import com.ruoyi.system.domain.XzAssetType;
import java.util.List;

/**
 * 资产父类型 服务层
 * 
 * @author ruoyi
 * @date 2019-08-15
 */
public interface IXzAssetTypeService {
	/**
	 * 查询资产父类型信息
	 * 
	 * @param id
	 *            资产父类型ID
	 * @return 资产父类型信息
	 */
	public XzAssetType selectXzAssetTypeById(Long id);

	/**
	 * 查询资产父类型列表
	 * 
	 * @param xzAssetType
	 *            资产父类型信息
	 * @return 资产父类型集合
	 */
	public List<XzAssetType> selectXzAssetTypeList(XzAssetType xzAssetType);

	/**
	 * 新增资产父类型
	 * 
	 * @param xzAssetType
	 *            资产父类型信息
	 * @return 结果
	 */
	public String insertXzAssetType(XzAssetType xzAssetType);

	/**
	 * 修改资产父类型
	 * 
	 * @param xzAssetType
	 *            资产父类型信息
	 * @return 结果
	 */
	public int updateXzAssetType(XzAssetType xzAssetType);

	/**
	 * 删除资产父类型信息
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	public int deleteXzAssetTypeByIds(String ids);

	/**
	 * 根据所有资产父类型
	 * 
	 * @return 资产父类型集合信息
	 */
	public List<XzAssetType> selectXzAssetTypeAll();

	/**
	 * 查询数量
	 * @param assetsType 资产分类
	 * @param name 资产名称
	 * @return 结果
	 */
	public int selectXzAssetTypeByName(String assetsType, String name);

}