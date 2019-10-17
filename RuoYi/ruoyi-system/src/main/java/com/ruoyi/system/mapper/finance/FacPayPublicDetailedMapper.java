package com.ruoyi.system.mapper.finance;

 
import java.util.List;

import com.ruoyi.system.domain.finance.FacPayPublicDetailed;	

/**
 * 对公明细 数据层
 * 
 * @author ruoyi
 * @date 2019-10-16
 */
public interface FacPayPublicDetailedMapper 
{
	/**
     * 查询对公明细信息
     * 
     * @param id 对公明细ID
     * @return 对公明细信息
     */
	public FacPayPublicDetailed selectFacPayPublicDetailedById(Long id);
	
	/**
     * 查询对公明细列表
     * 
     * @param facPayPublicDetailed 对公明细信息
     * @return 对公明细集合
     */
	public List<FacPayPublicDetailed> selectFacPayPublicDetailedList(FacPayPublicDetailed facPayPublicDetailed);
	
	/**
     * 查询对公明细列表
     * 
     * @param facPayPublicDetailed 对公明细信息
     * @return 对公明细集合
     */
	public List<FacPayPublicDetailed> selectDetailedList(String  num);
	
	/**
     * 新增对公明细
     * 
     * @param facPayPublicDetailed 对公明细信息
     * @return 结果
     */
	public int insertFacPayPublicDetailed(FacPayPublicDetailed facPayPublicDetailed);
	
	/**
     * 修改对公明细
     * 
     * @param facPayPublicDetailed 对公明细信息
     * @return 结果
     */
	public int updateFacPayPublicDetailed(FacPayPublicDetailed facPayPublicDetailed);
	
	/**
     * 删除对公明细
     * 
     * @param id 对公明细ID
     * @return 结果
     */
	public int deleteFacPayPublicDetailedById(Long id);
	
	/**
     * 批量删除对公明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacPayPublicDetailedByIds(String[] ids);
	
}