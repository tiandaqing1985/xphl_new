package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.XzAssetHandRecordMapper;
import com.ruoyi.system.domain.XzAssetHandRecord;
import com.ruoyi.system.service.IXzAssetHandRecordService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资产分配记录 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-07
 */
@Service
public class XzAssetHandRecordServiceImpl implements IXzAssetHandRecordService 
{
	@Autowired
	private XzAssetHandRecordMapper xzAssetHandRecordMapper;

	/**
     * 查询资产分配记录信息
     * 
     * @param id 资产分配记录ID
     * @return 资产分配记录信息
     */
    @Override
	public XzAssetHandRecord selectXzAssetHandRecordById(Long id)
	{
	    return xzAssetHandRecordMapper.selectXzAssetHandRecordById(id);
	}
	
	/**
     * 查询资产分配记录列表
     * 
     * @param xzAssetHandRecord 资产分配记录信息
     * @return 资产分配记录集合
     */
	@Override
	public List<XzAssetHandRecord> selectXzAssetHandRecordList(XzAssetHandRecord xzAssetHandRecord)
	{
	    return xzAssetHandRecordMapper.selectXzAssetHandRecordList(xzAssetHandRecord);
	}
	
    /**
     * 新增资产分配记录
     * 
     * @param xzAssetHandRecord 资产分配记录信息
     * @return 结果
     */
	@Override
	public int insertXzAssetHandRecord(XzAssetHandRecord xzAssetHandRecord)
	{
	    return xzAssetHandRecordMapper.insertXzAssetHandRecord(xzAssetHandRecord);
	}
	
	/**
     * 修改资产分配记录
     * 
     * @param xzAssetHandRecord 资产分配记录信息
     * @return 结果
     */
	@Override
	public int updateXzAssetHandRecord(XzAssetHandRecord xzAssetHandRecord)
	{
	    return xzAssetHandRecordMapper.updateXzAssetHandRecord(xzAssetHandRecord);
	}

	/**
     * 删除资产分配记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteXzAssetHandRecordByIds(String ids)
	{
		return xzAssetHandRecordMapper.deleteXzAssetHandRecordByIds(Convert.toStrArray(ids));
	}
	
}