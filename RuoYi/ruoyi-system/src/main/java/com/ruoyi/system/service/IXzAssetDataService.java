package com.ruoyi.system.service;

import com.ruoyi.system.domain.XzAssetData;
import java.util.List;

/**
 * 资产子类型 服务层
 * 
 * @author ruoyi
 * @date 2019-08-15
 */
public interface IXzAssetDataService 
{
	/**
     * 查询资产子类型信息
     * 
     * @param id 资产子类型ID
     * @return 资产子类型信息
     */
	public XzAssetData selectXzAssetDataById(Long id);
	
	/**
     * 查询资产子类型列表
     * 
     * @param xzAssetData 资产子类型信息
     * @return 资产子类型集合
     */
	public List<XzAssetData> selectXzAssetDataList(XzAssetData xzAssetData);
	
	/**
     * 新增资产子类型
     * 
     * @param xzAssetData 资产子类型信息
     * @return 结果
     */
	public int insertXzAssetData(XzAssetData xzAssetData);
	
	/**
     * 修改资产子类型
     * 
     * @param xzAssetData 资产子类型信息
     * @return 结果
     */
	public int updateXzAssetData(XzAssetData xzAssetData);
		
	/**
     * 删除资产子类型信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzAssetDataByIds(String ids);
	
}