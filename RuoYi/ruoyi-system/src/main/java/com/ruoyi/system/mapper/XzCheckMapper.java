package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.XzAsstes;
import com.ruoyi.system.domain.XzCheck;
import java.util.List;	

/**
 * 盘点记录 数据层
 * 
 * @author ruoyi
 * @date 2019-12-26
 */
public interface XzCheckMapper 
{
	/**
     * 查询盘点记录信息
     * 
     * @param id 盘点记录ID
     * @return 盘点记录信息
     */
	public XzCheck selectXzCheckById(Long id);
	
	/**
     * 查询盘点记录列表
     * 
     * @param xzCheck 盘点记录信息
     * @return 盘点记录集合
     */
	public List<XzCheck> selectXzCheckList(XzCheck xzCheck);
	
	/**
     * 新增盘点记录
     * 
     * @param xzCheck 盘点记录信息
     * @return 结果
     */
	public int insertXzCheck(XzCheck xzCheck);
	
	/**
     * 修改盘点记录
     * 
     * @param xzCheck 盘点记录信息
     * @return 结果
     */
	public int updateXzCheck(XzCheck xzCheck);
	
	/**
     * 删除盘点记录
     * 
     * @param id 盘点记录ID
     * @return 结果
     */
	public int deleteXzCheckById(Long id);
	
	/**
     * 批量删除盘点记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzCheckByIds(String[] ids);

	public List<XzAsstes> selectXzAsstesList(XzAsstes xzAsstes);

}