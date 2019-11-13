package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.XzResourceTypeMapper;
import com.ruoyi.system.domain.XzResourceType;
import com.ruoyi.system.service.IXzResourceTypeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 采购类型 服务层实现
 * 
 * @author ruoyi
 * @date 2019-09-16
 */
@Service
public class XzResourceTypeServiceImpl implements IXzResourceTypeService 
{
	@Autowired
	private XzResourceTypeMapper xzResourceTypeMapper;

	/**
     * 查询采购类型信息
     * 
     * @param id 采购类型ID
     * @return 采购类型信息
     */
    @Override
	public XzResourceType selectXzResourceTypeById(Long id)
	{
	    return xzResourceTypeMapper.selectXzResourceTypeById(id);
	}
	
	/**
     * 查询采购类型列表
     * 
     * @param xzResourceType 采购类型信息
     * @return 采购类型集合
     */
	@Override
	public List<XzResourceType> selectXzResourceTypeList(XzResourceType xzResourceType)
	{
	    return xzResourceTypeMapper.selectXzResourceTypeList(xzResourceType);
	}
	
    /**
     * 新增采购类型
     * 
     * @param xzResourceType 采购类型信息
     * @return 结果
     */
	@Override
	public int insertXzResourceType(XzResourceType xzResourceType)
	{
	    return xzResourceTypeMapper.insertXzResourceType(xzResourceType);
	}
	
	/**
     * 修改采购类型
     * 
     * @param xzResourceType 采购类型信息
     * @return 结果
     */
	@Override
	public int updateXzResourceType(XzResourceType xzResourceType)
	{
	    return xzResourceTypeMapper.updateXzResourceType(xzResourceType);
	}

	/**
     * 删除采购类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteXzResourceTypeByIds(String ids)
	{
		return xzResourceTypeMapper.deleteXzResourceTypeByIds(Convert.toStrArray(ids));
	}

	@Override
	public int selectXzResourceTypeByName(Integer sourceType, String name) {
		return xzResourceTypeMapper.selectXzResourceTypeByName(sourceType,name);
	}

	@Override
	public List<XzResourceType> selectXzResourceTypeByAll() {
		return xzResourceTypeMapper.selectXzResourceTypeByAll();
	}

	@Override
	public List<XzResourceType> selectXzResourceDataList(XzResourceType xzResourceType) {
		return xzResourceTypeMapper.selectXzResourceDataList(xzResourceType);
	}

	@Override
	public List<XzResourceType> xzResourceDataByParentId(Long id) {
		return xzResourceTypeMapper.xzResourceDataByParentId(id);
	}

	@Override
	public List<XzResourceType> selectByFeiList() {
		return xzResourceTypeMapper.selectByFeiList();
	}
	
}
