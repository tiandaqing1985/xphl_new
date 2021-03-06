package com.ruoyi.system.mapper.finance;


import java.util.List;

import com.ruoyi.system.domain.finance.FacCollectApply;	

/**
 * 团建申请 数据层
 * 
 * @author ruoyi
 * @date 2019-09-03
 */
public interface FacCollectApplyMapper 
{
	/**
     * 查询团建申请信息
     * 
     * @param id 团建申请ID
     * @return 团建申请信息
     */
	public FacCollectApply selectFacCollectApplyById(Long id);
	
	/**
     * 查询团建申请列表
     * 
     * @param facCollectApply 团建申请信息
     * @return 团建申请集合
     */
	public List<FacCollectApply> selectFacCollectApplyList(FacCollectApply facCollectApply);
	
	/**
     * 新增团建申请
     * 
     * @param facCollectApply 团建申请信息
     * @return 结果
     */
	public int insertFacCollectApply(FacCollectApply facCollectApply);
	
	/**
     * 修改团建申请
     * 
     * @param facCollectApply 团建申请信息
     * @return 结果
     */
	public int updateFacCollectApply(FacCollectApply facCollectApply);
	
	/**
     * 删除团建申请
     * 
     * @param id 团建申请ID
     * @return 结果
     */
	public int deleteFacCollectApplyById(Long id);
	
	/**
     * 批量删除团建申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacCollectApplyByIds(String[] ids);

    FacCollectApply selectFacCollectApplyByNum(String num);
}