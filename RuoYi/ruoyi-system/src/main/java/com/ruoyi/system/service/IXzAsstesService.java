package com.ruoyi.system.service;

import com.ruoyi.system.domain.XzAsstes;
import java.util.List;

/**
 * 资产 服务层
 * 
 * @author ruoyi
 * @date 2019-08-02
 */
public interface IXzAsstesService 
{
	/**
     * 查询资产信息
     * 
     * @param id 资产ID
     * @return 资产信息
     */
	public XzAsstes selectXzAsstesById(Long id);
	
	/**
     * 查询资产列表
     * 
     * @param xzAsstes 资产信息
     * @return 资产集合
     */
	public List<XzAsstes> selectXzAsstesList(XzAsstes xzAsstes);
	
	/**
     * 新增资产
     * 
     * @param xzAsstes 资产信息
     * @return 结果
     */
	public int insertXzAsstes(XzAsstes xzAsstes);
	
	/**
     * 修改资产
     * 
     * @param xzAsstes 资产信息
     * @return 结果
     */
	public int updateXzAsstes(XzAsstes xzAsstes);
		
	/**
     * 删除资产信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzAsstesByIds(String ids);

	/**
	 * 
	 * @param assetsList 资产数据列表
	 * @param updateSupport
	 * @param operName
	 * @return
	 */
	public String importXzAsstes(List<XzAsstes> assetsList, boolean updateSupport, String operName);
	
}