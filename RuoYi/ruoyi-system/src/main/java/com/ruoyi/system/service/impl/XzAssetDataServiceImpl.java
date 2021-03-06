package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.XzAssetDataMapper;
import com.ruoyi.system.domain.XzAssetData;
import com.ruoyi.system.domain.XzAssetType;
import com.ruoyi.system.service.IXzAssetDataService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资产子类型 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-15
 */
@Service
public class XzAssetDataServiceImpl implements IXzAssetDataService 
{
	@Autowired
	private XzAssetDataMapper xzAssetDataMapper;

	/**
     * 查询资产子类型信息
     * 
     * @param id 资产子类型ID
     * @return 资产子类型信息
     */
    @Override
	public XzAssetData selectXzAssetDataById(Long id)
	{
	    return xzAssetDataMapper.selectXzAssetDataById(id);
	}
	
	/**
     * 查询资产子类型列表
     * 
     * @param xzAssetData 资产子类型信息
     * @return 资产子类型集合
     */
	@Override
	public List<XzAssetData> selectXzAssetDataList(XzAssetData xzAssetData)
	{
	    return xzAssetDataMapper.selectXzAssetDataList(xzAssetData);
	}
	
    /**
     * 新增资产子类型
     * 
     * @param xzAssetData 资产子类型信息
     * @return 结果
     */
	@Override
	public int insertXzAssetData(XzAssetData xzAssetData)
	{
		//添加资产子类型时，判断子类型是否存在重复数据
		//添加资产子类型的同时，添加办公室用品管理的一条记录，以资产父类型id及子类型id确定一条数据
		
	    return xzAssetDataMapper.insertXzAssetData(xzAssetData);
	}
	
	/**
     * 修改资产子类型
     * 
     * @param xzAssetData 资产子类型信息
     * @return 结果
     */
	@Override
	public int updateXzAssetData(XzAssetData xzAssetData)
	{
	    return xzAssetDataMapper.updateXzAssetData(xzAssetData);
	}

	/**
     * 删除资产子类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteXzAssetDataByIds(String ids)
	{
		return xzAssetDataMapper.deleteXzAssetDataByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<XzAssetType> selectXzAssetDataByParentId(Long parentId) {
		return xzAssetDataMapper.selectXzAssetDataByParentId(parentId);
	}

	@Override
	public XzAssetData selectXzAssetDataByName(XzAssetData xzAssetData) {
		return xzAssetDataMapper.selectXzAssetDataByName(xzAssetData);
	}
	
}