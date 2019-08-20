package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.XzStationeryassetMapper;
import com.ruoyi.system.domain.XzStationeryasset;
import com.ruoyi.system.service.IXzStationeryassetService;
import com.ruoyi.common.core.text.Convert;

/**
 * 办公用品 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-12
 */
@Service
public class XzStationeryassetServiceImpl implements IXzStationeryassetService 
{
	@Autowired
	private XzStationeryassetMapper xzStationeryassetMapper;

	/**
     * 查询办公用品信息
     * 
     * @param id 办公用品ID
     * @return 办公用品信息
     */
    @Override
	public XzStationeryasset selectXzStationeryassetById(Long id)
	{
	    return xzStationeryassetMapper.selectXzStationeryassetById(id);
	}
	
	/**
     * 查询办公用品列表
     * 
     * @param xzStationeryasset 办公用品信息
     * @return 办公用品集合
     */
	@Override
	public List<XzStationeryasset> selectXzStationeryassetList(XzStationeryasset xzStationeryasset)
	{
	    return xzStationeryassetMapper.selectXzStationeryassetList(xzStationeryasset);
	}
	
    /**
     * 新增办公用品
     * 
     * @param xzStationeryasset 办公用品信息
     * @return 结果
     */
	@Override
	public int insertXzStationeryasset(XzStationeryasset xzStationeryasset)
	{
	    return xzStationeryassetMapper.insertXzStationeryasset(xzStationeryasset);
	}
	
	/**
     * 修改办公用品
     * 
     * @param xzStationeryasset 办公用品信息
     * @return 结果
     */
	@Override
	public int updateXzStationeryasset(XzStationeryasset xzStationeryasset)
	{
	    return xzStationeryassetMapper.updateXzStationeryasset(xzStationeryasset);
	}

	/**
     * 删除办公用品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteXzStationeryassetByIds(String ids)
	{
		return xzStationeryassetMapper.deleteXzStationeryassetByIds(Convert.toStrArray(ids));
	}
	
	/**
	 * 根据typeId和地域查询数量
	 */
	@Override
	public int selectXzStationeryassetByTypeId(XzStationeryasset xzStationeryasset) {
		return xzStationeryassetMapper.selectXzStationeryassetByTypeId(xzStationeryasset);
	}
	
}