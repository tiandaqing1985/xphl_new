package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.XzAsstes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.XzCheckMapper;
import com.ruoyi.system.domain.XzCheck;
import com.ruoyi.system.service.IXzCheckService;
import com.ruoyi.common.core.text.Convert;

/**
 * 盘点记录 服务层实现
 * 
 * @author ruoyi
 * @date 2019-12-26
 */
@Service
public class XzCheckServiceImpl implements IXzCheckService 
{
	@Autowired
	private XzCheckMapper xzCheckMapper;

	/**
     * 查询盘点记录信息
     * 
     * @param id 盘点记录ID
     * @return 盘点记录信息
     */
    @Override
	public XzCheck selectXzCheckById(Long id)
	{
	    return xzCheckMapper.selectXzCheckById(id);
	}
	
	/**
     * 查询盘点记录列表
     * 
     * @param xzCheck 盘点记录信息
     * @return 盘点记录集合
     */
	@Override
	public List<XzCheck> selectXzCheckList(XzCheck xzCheck)
	{
	    return xzCheckMapper.selectXzCheckList(xzCheck);
	}
	
    /**
     * 新增盘点记录
     * 
     * @param xzCheck 盘点记录信息
     * @return 结果
     */
	@Override
	public int insertXzCheck(XzCheck xzCheck)
	{
	    return xzCheckMapper.insertXzCheck(xzCheck);
	}
	
	/**
     * 修改盘点记录
     * 
     * @param xzCheck 盘点记录信息
     * @return 结果
     */
	@Override
	public int updateXzCheck(XzCheck xzCheck)
	{
	    return xzCheckMapper.updateXzCheck(xzCheck);
	}

	/**
     * 删除盘点记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteXzCheckByIds(String ids)
	{
		return xzCheckMapper.deleteXzCheckByIds(Convert.toStrArray(ids));
	}


	@Override
	public List<XzAsstes> selectXzAsstesList(XzAsstes xzAsstes){
		return xzCheckMapper.selectXzAsstesList(xzAsstes);
	}

}
