package com.ruoyi.system.service;

import com.ruoyi.system.domain.XzResourceType;
import java.util.List;

/**
 * 采购类型 服务层
 * 
 * @author ruoyi
 * @date 2019-09-16
 */
public interface IXzResourceTypeService 
{
	/**
     * 查询采购类型信息
     * 
     * @param id 采购类型ID
     * @return 采购类型信息
     */
	public XzResourceType selectXzResourceTypeById(Long id);
	
	/**
     * 查询采购类型列表
     * 
     * @param xzResourceType 采购类型信息
     * @return 采购类型集合
     */
	public List<XzResourceType> selectXzResourceTypeList(XzResourceType xzResourceType);
	
	/**
     * 新增采购类型
     * 
     * @param xzResourceType 采购类型信息
     * @return 结果
     */
	public int insertXzResourceType(XzResourceType xzResourceType);
	
	/**
     * 修改采购类型
     * 
     * @param xzResourceType 采购类型信息
     * @return 结果
     */
	public int updateXzResourceType(XzResourceType xzResourceType);
		
	/**
     * 删除采购类型信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzResourceTypeByIds(String ids);

	public int selectXzResourceTypeByName(Integer sourceType, String name);

	public List<XzResourceType> selectXzResourceTypeByAll();

	public List<XzResourceType> selectXzResourceDataList(XzResourceType xzResourceType);

	public List<XzResourceType> xzResourceDataByParentId(Long id);

	public List<XzResourceType> selectByFeiList();
	
}
