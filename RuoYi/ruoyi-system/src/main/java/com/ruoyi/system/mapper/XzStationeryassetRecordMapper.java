package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.XzStationeryassetRecord;
import java.util.List;	

/**
 * 办公用品分配记录 数据层
 * 
 * @author ruoyi
 * @date 2019-08-12
 */
public interface XzStationeryassetRecordMapper 
{
	/**
     * 查询办公用品分配记录信息
     * 
     * @param id 办公用品分配记录ID
     * @return 办公用品分配记录信息
     */
	public XzStationeryassetRecord selectXzStationeryassetRecordById(Long id);
	
	/**
     * 查询办公用品分配记录列表
     * 
     * @param xzStationeryassetRecord 办公用品分配记录信息
     * @return 办公用品分配记录集合
     */
	public List<XzStationeryassetRecord> selectXzStationeryassetRecordList(XzStationeryassetRecord xzStationeryassetRecord);
	
	/**
     * 新增办公用品分配记录
     * 
     * @param xzStationeryassetRecord 办公用品分配记录信息
     * @return 结果
     */
	public int insertXzStationeryassetRecord(XzStationeryassetRecord xzStationeryassetRecord);
	
	/**
     * 修改办公用品分配记录
     * 
     * @param xzStationeryassetRecord 办公用品分配记录信息
     * @return 结果
     */
	public int updateXzStationeryassetRecord(XzStationeryassetRecord xzStationeryassetRecord);
	
	/**
     * 删除办公用品分配记录
     * 
     * @param id 办公用品分配记录ID
     * @return 结果
     */
	public int deleteXzStationeryassetRecordById(Long id);
	
	/**
     * 批量删除办公用品分配记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzStationeryassetRecordByIds(String[] ids);
	
}