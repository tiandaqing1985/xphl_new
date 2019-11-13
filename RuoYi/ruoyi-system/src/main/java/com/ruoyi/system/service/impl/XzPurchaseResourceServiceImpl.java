package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.XzPurchaseResourceMapper;
import com.ruoyi.system.domain.XzPurchaseResource;
import com.ruoyi.system.domain.XzPurchaseResourceApply;
import com.ruoyi.system.service.IXzPurchaseResourceService;
import com.ruoyi.common.core.text.Convert;

/**
 * 采购资源 服务层实现
 * 
 * @author ruoyi
 * @date 2019-09-18
 */
@Service
public class XzPurchaseResourceServiceImpl implements IXzPurchaseResourceService 
{
	@Autowired
	private XzPurchaseResourceMapper xzPurchaseResourceMapper;

	/**
     * 查询采购资源信息
     * 
     * @param id 采购资源ID
     * @return 采购资源信息
     */
    @Override
	public XzPurchaseResource selectXzPurchaseResourceById(Long id)
	{
	    return xzPurchaseResourceMapper.selectXzPurchaseResourceById(id);
	}
	
	/**
     * 查询采购资源列表
     * 
     * @param xzPurchaseResource 采购资源信息
     * @return 采购资源集合
     */
	@Override
	public List<XzPurchaseResource> selectXzPurchaseResourceList(XzPurchaseResource xzPurchaseResource)
	{
	    return xzPurchaseResourceMapper.selectXzPurchaseResourceList(xzPurchaseResource);
	}
	
    /**
     * 新增采购资源
     * 
     * @param xzPurchaseResource 采购资源信息
     * @return 结果
     */
	@Override
	@Transactional
	public int insertXzPurchaseResource(XzPurchaseResource xzPurchaseResource)
	{
	    return xzPurchaseResourceMapper.insertXzPurchaseResource(xzPurchaseResource);
	}
	
	/**
     * 修改采购资源
     * 
     * @param xzPurchaseResource 采购资源信息
     * @return 结果
     */
	@Override
	public int updateXzPurchaseResource(XzPurchaseResource xzPurchaseResource)
	{
	    return xzPurchaseResourceMapper.updateXzPurchaseResource(xzPurchaseResource);
	}

	/**
     * 删除采购资源对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteXzPurchaseResourceByIds(String ids)
	{
		return xzPurchaseResourceMapper.deleteXzPurchaseResourceByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<XzPurchaseResource> selectXzPurchaseResourceListByCode(String code) {
		return xzPurchaseResourceMapper.selectXzPurchaseResourceListByCode(code);
	}
	@Override
	public XzPurchaseResourceApply selectXzPurchaseResourceIdByCode(String code) {
		return xzPurchaseResourceMapper.selectXzPurchaseResourceIdByCode(code);
	}
	
	
}
