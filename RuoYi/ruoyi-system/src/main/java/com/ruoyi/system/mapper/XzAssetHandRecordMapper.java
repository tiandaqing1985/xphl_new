package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.XzAssetHandRecord;
import java.util.List;	

/**
 * 资产分配记录 数据层
 * 
 * @author ruoyi
 * @date 2019-08-07
 */
public interface XzAssetHandRecordMapper 
{
	/**
     * 查询资产分配记录信息
     * 
     * @param id 资产分配记录ID
     * @return 资产分配记录信息
     */
	public XzAssetHandRecord selectXzAssetHandRecordById(Long id);

	public XzAssetHandRecord selectXzAssetHandRecordByAssetsCode(String id);

	/**
     * 查询资产分配记录列表
     * 
     * @param xzAssetHandRecord 资产分配记录信息
     * @return 资产分配记录集合
     */
	public List<XzAssetHandRecord> selectXzAssetHandRecordList(XzAssetHandRecord xzAssetHandRecord);
	
	/**
     * 新增资产分配记录
     * 
     * @param xzAssetHandRecord 资产分配记录信息
     * @return 结果
     */
	public int insertXzAssetHandRecord(XzAssetHandRecord xzAssetHandRecord);
	
	/**
     * 修改资产分配记录
     * 
     * @param xzAssetHandRecord 资产分配记录信息
     * @return 结果
     */
	public int updateXzAssetHandRecord(XzAssetHandRecord xzAssetHandRecord);
	
	/**
     * 删除资产分配记录
     * 
     * @param id 资产分配记录ID
     * @return 结果
     */
	public int deleteXzAssetHandRecordById(Long id);
	
	/**
     * 批量删除资产分配记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzAssetHandRecordByIds(String[] ids);

	/**
	 * 查询资产分配记录信息 assetId
	 * @param assetId
	 * @return
	 */
	public XzAssetHandRecord selectXzAssetHandRecordByAssetId(Long assetId);
	
}