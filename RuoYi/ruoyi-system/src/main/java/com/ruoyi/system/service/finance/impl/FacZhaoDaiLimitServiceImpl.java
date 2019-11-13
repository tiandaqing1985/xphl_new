package com.ruoyi.system.service.finance.impl;

import java.util.List;

import com.ruoyi.system.domain.finance.FacZhaoDaiLimit;
import com.ruoyi.system.mapper.finance.FacZhaoDaiLimitMapper;
import com.ruoyi.system.service.finance.IFacZhaoDaiLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 招待费报销限额 服务层实现
 * 
 * @author ruoyi
 * @date 2019-11-12
 */
@Service
public class FacZhaoDaiLimitServiceImpl implements IFacZhaoDaiLimitService
{
	@Autowired
	private FacZhaoDaiLimitMapper facZhaoDaiLimitMapper;

	/**
     * 查询招待费报销限额信息
     * 
     * @param id 招待费报销限额ID
     * @return 招待费报销限额信息
     */
    @Override
	public FacZhaoDaiLimit selectFacZhaoDaiLimitById(Long id)
	{
	    return facZhaoDaiLimitMapper.selectFacZhaoDaiLimitById(id);
	}
	
	/**
     * 查询招待费报销限额列表
     * 
     * @param facZhaoDaiLimit 招待费报销限额信息
     * @return 招待费报销限额集合
     */
	@Override
	public List<FacZhaoDaiLimit> selectFacZhaoDaiLimitList(FacZhaoDaiLimit facZhaoDaiLimit)
	{
	    return facZhaoDaiLimitMapper.selectFacZhaoDaiLimitList(facZhaoDaiLimit);
	}
	
    /**
     * 新增招待费报销限额
     * 
     * @param facZhaoDaiLimit 招待费报销限额信息
     * @return 结果
     */
	@Override
	public int insertFacZhaoDaiLimit(FacZhaoDaiLimit facZhaoDaiLimit)
	{
	    return facZhaoDaiLimitMapper.insertFacZhaoDaiLimit(facZhaoDaiLimit);
	}
	
	/**
     * 修改招待费报销限额
     * 
     * @param facZhaoDaiLimit 招待费报销限额信息
     * @return 结果
     */
	@Override
	public int updateFacZhaoDaiLimit(FacZhaoDaiLimit facZhaoDaiLimit)
	{
	    return facZhaoDaiLimitMapper.updateFacZhaoDaiLimit(facZhaoDaiLimit);
	}

	/**
     * 删除招待费报销限额对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFacZhaoDaiLimitByIds(String ids)
	{
		return facZhaoDaiLimitMapper.deleteFacZhaoDaiLimitByIds(Convert.toStrArray(ids));
	}

	@Override
	public FacZhaoDaiLimit selectFacZhaoDaiLimitByRoleId(Long roleId) {
		return facZhaoDaiLimitMapper.selectFacZhaoDaiLimitByRoleId(roleId);
	}
}
