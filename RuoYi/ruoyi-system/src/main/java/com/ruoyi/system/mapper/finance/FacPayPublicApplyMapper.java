package com.ruoyi.system.mapper.finance;
 

import java.util.List;

import com.ruoyi.system.domain.finance.FacPayPublicApply;	

/**
 * 对公申请 数据层
 * 
 * @author ruoyi
 * @date 2019-10-10
 */
public interface FacPayPublicApplyMapper 
{
	/**
     * 查询对公申请信息
     * 
     * @param id 对公申请ID
     * @return 对公申请信息
     */
	public FacPayPublicApply selectFacPayPublicApplyById(Integer id);
	
	/**
     * 查询对公申请列表
     * 
     * @param facPayPublicApply 对公申请信息
     * @return 对公申请集合
     */
	public List<FacPayPublicApply> selectFacPayPublicApplyList(FacPayPublicApply facPayPublicApply);
	
	/**
     * 新增对公申请
     * 
     * @param facPayPublicApply 对公申请信息
     * @return 结果
     */
	public int insertFacPayPublicApply(FacPayPublicApply facPayPublicApply);
	
	/**
     * 修改对公申请
     * 
     * @param facPayPublicApply 对公申请信息
     * @return 结果
     */
	public int updateFacPayPublicApply(FacPayPublicApply facPayPublicApply);
	
	/**
     * 删除对公申请
     * 
     * @param id 对公申请ID
     * @return 结果
     */
	public int deleteFacPayPublicApplyById(Integer id);
	
	/**
     * 批量删除对公申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacPayPublicApplyByIds(String[] ids);
	
	/**
	 * 对公详细信息汇总
	 * @param num
	 * @return
	 */
	FacPayPublicApply detail(String num);

	
}