package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.XzAssetAccountRecordMapper;
import com.ruoyi.system.domain.XzAssetAccountRecord;
import com.ruoyi.system.service.IXzAssetAccountRecordService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资产台账记录 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-08
 */
@Service
public class XzAssetAccountRecordServiceImpl implements IXzAssetAccountRecordService 
{
	@Autowired
	private XzAssetAccountRecordMapper xzAssetAccountRecordMapper;

	/**
     * 查询资产台账记录信息
     * 
     * @param id 资产台账记录ID
     * @return 资产台账记录信息
     */
    @Override
	public XzAssetAccountRecord selectXzAssetAccountRecordById(Long id)
	{
	    return xzAssetAccountRecordMapper.selectXzAssetAccountRecordById(id);
	}
	
	/**
     * 查询资产台账记录列表
     * 
     * @param xzAssetAccountRecord 资产台账记录信息
     * @return 资产台账记录集合
     */
	@Override
	public List<XzAssetAccountRecord> selectXzAssetAccountRecordList(XzAssetAccountRecord xzAssetAccountRecord)
	{
	    return xzAssetAccountRecordMapper.selectXzAssetAccountRecordList(xzAssetAccountRecord);
	}
	
    /**
     * 新增资产台账记录
     * 
     * @param xzAssetAccountRecord 资产台账记录信息
     * @return 结果
     */
	@Override
	public int insertXzAssetAccountRecord(XzAssetAccountRecord xzAssetAccountRecord)
	{
	    return xzAssetAccountRecordMapper.insertXzAssetAccountRecord(xzAssetAccountRecord);
	}
	
	/**
     * 修改资产台账记录
     * 
     * @param xzAssetAccountRecord 资产台账记录信息
     * @return 结果
     */
	@Override
	public int updateXzAssetAccountRecord(XzAssetAccountRecord xzAssetAccountRecord)
	{
	    return xzAssetAccountRecordMapper.updateXzAssetAccountRecord(xzAssetAccountRecord);
	}

	/**
     * 删除资产台账记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteXzAssetAccountRecordByIds(String ids)
	{
		return xzAssetAccountRecordMapper.deleteXzAssetAccountRecordByIds(Convert.toStrArray(ids));
	}
	
}