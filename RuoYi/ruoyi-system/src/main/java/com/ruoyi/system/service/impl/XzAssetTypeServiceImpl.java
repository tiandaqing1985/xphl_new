package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.mapper.XzAssetDataMapper;
import com.ruoyi.system.mapper.XzAssetTypeMapper;
import com.ruoyi.system.domain.XzAssetType;
import com.ruoyi.system.service.IXzAssetTypeService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;

/**
 * 资产父类型 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-15
 */
@Service
public class XzAssetTypeServiceImpl implements IXzAssetTypeService 
{
	@Autowired
	private XzAssetTypeMapper xzAssetTypeMapper;
	
	@Autowired
	private XzAssetDataMapper xzAssetDataMapper;

	/**
     * 查询资产父类型信息
     * 
     * @param id 资产父类型ID
     * @return 资产父类型信息
     */
    @Override
	public XzAssetType selectXzAssetTypeById(Long id)
	{
	    return xzAssetTypeMapper.selectXzAssetTypeById(id);
	}
	
	/**
     * 查询资产父类型列表
     * 
     * @param xzAssetType 资产父类型信息
     * @return 资产父类型集合
     */
	@Override
	public List<XzAssetType> selectXzAssetTypeList(XzAssetType xzAssetType)
	{
	    return xzAssetTypeMapper.selectXzAssetTypeList(xzAssetType);
	}
	
    /**
     * 新增资产父类型
     * 
     * @param xzAssetType 资产父类型信息
     * @return 结果
     */
	@Override
	public String insertXzAssetType(XzAssetType xzAssetType)
	{
		//新增一条资产类型数据
		xzAssetTypeMapper.insertXzAssetType(xzAssetType);
	    return "添加成功";
	}
	
	/**
     * 修改资产父类型
     * 
     * @param xzAssetType 资产父类型信息
     * @return 结果
     */
	@Override
	public int updateXzAssetType(XzAssetType xzAssetType)
	{
	    return xzAssetTypeMapper.updateXzAssetType(xzAssetType);
	}

	/**
     * 批量删除资产父类型对象
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
	@Override
	public int deleteXzAssetTypeByIds(String ids) throws BusinessException
	{
		//转为Long数组
		 Long[] typeIds = Convert.toLongArray(ids);
		 for(Long typeId : typeIds){
			 //根据父资产ID查询子类型数量，判断是否大于0
			 XzAssetType type=xzAssetTypeMapper.selectXzAssetTypeById(typeId);
			 if(xzAssetDataMapper.countTypeDataByType(typeId)>0){
				 throw new BusinessException(String.format("%1$s存在二级分类,不能删除",type.getName()));
			 }
		 }
		return xzAssetTypeMapper.deleteXzAssetTypeByIds(Convert.toStrArray(ids));
	}
	
	 /**
     * 根据所有资产父类型
     * 
     * @return 资产父类型集合信息
     */
    @Override
    public List<XzAssetType> selectXzAssetTypeAll()
    {
        return xzAssetTypeMapper.selectXzAssetTypeAll();
    }

	@Override
	public XzAssetType selectXzAssetTypeByName(String assetsType, String name) {
		return xzAssetTypeMapper.selectXzAssetTypeByName(assetsType, name);
	}

	@Override
	public List<XzAssetType> selectXzAssetTypeByAssAll() {
		return xzAssetTypeMapper.selectXzAssetTypeByAssAll();
	}

	@Override
	public List<XzAssetType> selectXzAssetTypeByStaAll() {
		return xzAssetTypeMapper.selectXzAssetTypeByStaAll();
	}
    
}