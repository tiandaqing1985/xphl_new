package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.XzPersonalAssetMapper;
import com.ruoyi.system.domain.XzPersonalAsset;
import com.ruoyi.system.service.IXzPersonalAssetService;
import com.ruoyi.common.core.text.Convert;

/**
 * 个人资产 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-27
 */
@Service
public class XzPersonalAssetServiceImpl implements IXzPersonalAssetService 
{
	@Autowired
	private XzPersonalAssetMapper xzPersonalAssetMapper;

	/**
     * 查询个人资产信息
     * 
     * @param id 个人资产ID
     * @return 个人资产信息
     */
    @Override
	public XzPersonalAsset selectXzPersonalAssetById(Long id)
	{
	    return xzPersonalAssetMapper.selectXzPersonalAssetById(id);
	}
	
	/**
     * 查询个人资产列表
     * 
     * @param xzPersonalAsset 个人资产信息
     * @return 个人资产集合
     */
	@Override
	public List<XzPersonalAsset> selectXzPersonalAssetList(XzPersonalAsset xzPersonalAsset)
	{
	    return xzPersonalAssetMapper.selectXzPersonalAssetList(xzPersonalAsset);
	}
	
    /**
     * 新增个人资产
     * 
     * @param xzPersonalAsset 个人资产信息
     * @return 结果
     */
	@Override
	public int insertXzPersonalAsset(XzPersonalAsset xzPersonalAsset)
	{
	    return xzPersonalAssetMapper.insertXzPersonalAsset(xzPersonalAsset);
	}
	
	/**
     * 修改个人资产
     * 
     * @param xzPersonalAsset 个人资产信息
     * @return 结果
     */
	@Override
	public int updateXzPersonalAsset(XzPersonalAsset xzPersonalAsset)
	{
	    return xzPersonalAssetMapper.updateXzPersonalAsset(xzPersonalAsset);
	}

	/**
     * 删除个人资产对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteXzPersonalAssetByIds(String ids)
	{
		return xzPersonalAssetMapper.deleteXzPersonalAssetByIds(Convert.toStrArray(ids));
	}
	
}
