package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.XzOfficeAsstesMapper;
import com.ruoyi.system.domain.XzOfficeAsstes;
import com.ruoyi.system.service.IXzOfficeAsstesService;
import com.ruoyi.common.core.text.Convert;

/**
 * 办公资产记录 服务层实现
 * 
 * @author ruoyi
 * @date 2019-09-03
 */
@Service
public class XzOfficeAsstesServiceImpl implements IXzOfficeAsstesService 
{
	@Autowired
	private XzOfficeAsstesMapper xzOfficeAsstesMapper;
	
	@Autowired
	private SysUserMapper sysUserMapper;

	/**
     * 查询办公资产记录信息
     * 
     * @param id 办公资产记录ID
     * @return 办公资产记录信息
     */
    @Override
	public XzOfficeAsstes selectXzOfficeAsstesById(Long id)
	{
	    return xzOfficeAsstesMapper.selectXzOfficeAsstesById(id);
	}
	
	/**
     * 查询办公资产记录列表
     * 
     * @param xzOfficeAsstes 办公资产记录信息
     * @return 办公资产记录集合
     */
	@Override
	public List<XzOfficeAsstes> selectXzOfficeAsstesList(XzOfficeAsstes xzOfficeAsstes)
	{
	    return xzOfficeAsstesMapper.selectXzOfficeAsstesList(xzOfficeAsstes);
	}
	
    /**
     * 新增办公资产记录
     * 
     * @param xzOfficeAsstes 办公资产记录信息
     * @return 结果
     */
	@Override
	public int insertXzOfficeAsstes(XzOfficeAsstes xzOfficeAsstes)
	{
		xzOfficeAsstes.setPurchaseBy(sysUserMapper.selectUserIdByUserNameOnly(xzOfficeAsstes.getPurchaseName()));
	    return xzOfficeAsstesMapper.insertXzOfficeAsstes(xzOfficeAsstes);
	}
	
	/**
     * 修改办公资产记录
     * 
     * @param xzOfficeAsstes 办公资产记录信息
     * @return 结果
     */
	@Override
	public int updateXzOfficeAsstes(XzOfficeAsstes xzOfficeAsstes)
	{
		xzOfficeAsstes.setPurchaseBy(sysUserMapper.selectUserIdByUserNameOnly(xzOfficeAsstes.getPurchaseName()));
	    return xzOfficeAsstesMapper.updateXzOfficeAsstes(xzOfficeAsstes);
	}

	/**
     * 删除办公资产记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteXzOfficeAsstesByIds(String ids)
	{
		return xzOfficeAsstesMapper.deleteXzOfficeAsstesByIds(Convert.toStrArray(ids));
	}
	
}
