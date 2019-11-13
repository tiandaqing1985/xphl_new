package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.XzAssetAccountRecord;
import java.util.List;	

/**
 * 资产台账记录 数据层
 * 
 * @author ruoyi
 * @date 2019-08-08
 */
public interface XzAssetAccountRecordMapper 
{
	/**
     * 查询资产台账记录信息
     * 
     * @param id 资产台账记录ID
     * @return 资产台账记录信息
     */
	public XzAssetAccountRecord selectXzAssetAccountRecordById(Long id);
	
	/**
     * 查询资产台账记录列表
     * 
     * @param xzAssetAccountRecord 资产台账记录信息
     * @return 资产台账记录集合
     */
	public List<XzAssetAccountRecord> selectXzAssetAccountRecordList(XzAssetAccountRecord xzAssetAccountRecord);
	
	/**
     * 新增资产台账记录
     * 
     * @param xzAssetAccountRecord 资产台账记录信息
     * @return 结果
     */
	public int insertXzAssetAccountRecord(XzAssetAccountRecord xzAssetAccountRecord);
	
	/**
     * 修改资产台账记录
     * 
     * @param xzAssetAccountRecord 资产台账记录信息
     * @return 结果
     */
	public int updateXzAssetAccountRecord(XzAssetAccountRecord xzAssetAccountRecord);
	
	/**
     * 删除资产台账记录
     * 
     * @param id 资产台账记录ID
     * @return 结果
     */
	public int deleteXzAssetAccountRecordById(Long id);
	
	/**
     * 批量删除资产台账记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzAssetAccountRecordByIds(String[] ids);
	
}