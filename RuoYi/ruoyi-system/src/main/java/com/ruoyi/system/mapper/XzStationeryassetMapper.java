package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.XzStationeryasset;
import java.util.List;	

/**
 * 办公用品 数据层
 * 
 * @author ruoyi
 * @date 2019-08-12
 */
public interface XzStationeryassetMapper 
{
	/**
     * 查询办公用品信息
     * 
     * @param id 办公用品ID
     * @return 办公用品信息
     */
	public XzStationeryasset selectXzStationeryassetById(Long id);
	
	/**
     * 查询办公用品列表
     * 
     * @param xzStationeryasset 办公用品信息
     * @return 办公用品集合
     */
	public List<XzStationeryasset> selectXzStationeryassetList(XzStationeryasset xzStationeryasset);
	
	/**
     * 新增办公用品
     * 
     * @param xzStationeryasset 办公用品信息
     * @return 结果
     */
	public int insertXzStationeryasset(XzStationeryasset xzStationeryasset);
	
	/**
     * 修改办公用品
     * 
     * @param xzStationeryasset 办公用品信息
     * @return 结果
     */
	public int updateXzStationeryasset(XzStationeryasset xzStationeryasset);
	
	/**
     * 删除办公用品
     * 
     * @param id 办公用品ID
     * @return 结果
     */
	public int deleteXzStationeryassetById(Long id);
	
	/**
     * 批量删除办公用品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzStationeryassetByIds(String[] ids);

	/**
	 * 数量
	 * @param xzStationeryasset
	 * @return
	 */
	public int selectXzStationeryassetByTypeId(XzStationeryasset xzStationeryasset);
	/**
	 * 对象
	 * @param xzStationeryasset
	 * @return
	 */
	public XzStationeryasset selectXzStationeryassetByType(XzStationeryasset xzStationeryasset);
}