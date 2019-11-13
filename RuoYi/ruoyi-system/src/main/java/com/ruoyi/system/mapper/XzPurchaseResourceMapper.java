package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.XzPurchaseResource;
import com.ruoyi.system.domain.XzPurchaseResourceApply;

import java.util.List;	

/**
 * 采购资源 数据层
 * 
 * @author ruoyi
 * @date 2019-09-18
 */
public interface XzPurchaseResourceMapper 
{
	/**
     * 查询采购资源信息
     * 
     * @param id 采购资源ID
     * @return 采购资源信息
     */
	public XzPurchaseResource selectXzPurchaseResourceById(Long id);
	
	/**
     * 查询采购资源列表
     * 
     * @param xzPurchaseResource 采购资源信息
     * @return 采购资源集合
     */
	public List<XzPurchaseResource> selectXzPurchaseResourceList(XzPurchaseResource xzPurchaseResource);
	
	/**
     * 新增采购资源
     * 
     * @param xzPurchaseResource 采购资源信息
     * @return 结果
     */
	public int insertXzPurchaseResource(XzPurchaseResource xzPurchaseResource);
	
	/**
     * 修改采购资源
     * 
     * @param xzPurchaseResource 采购资源信息
     * @return 结果
     */
	public int updateXzPurchaseResource(XzPurchaseResource xzPurchaseResource);
	
	/**
     * 删除采购资源
     * 
     * @param id 采购资源ID
     * @return 结果
     */
	public int deleteXzPurchaseResourceById(Long id);
	
	/**
     * 批量删除采购资源
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzPurchaseResourceByIds(String[] ids);

	/**
	 * 查看采购信息
	 * @param code
	 * @return
	 */
	public List<XzPurchaseResource> selectXzPurchaseResourceListByCode(String code);

	/**
	 * 查询采购信息id
	 */
	public XzPurchaseResourceApply selectXzPurchaseResourceIdByCode(String code);
	
}