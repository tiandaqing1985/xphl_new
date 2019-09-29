package com.ruoyi.system.service.finance;


import java.util.List;

import com.ruoyi.system.domain.finance.FacCollectDetaApply;

/**
 * 团建明细 服务层
 * 
 * @author ruoyi
 * @date 2019-09-04
 */
public interface IFacCollectDetaApplyService 
{
	/**
     * 查询团建明细信息
     * 
     * @param id 团建明细ID
     * @return 团建明细信息
     */
	public FacCollectDetaApply selectFacCollectDetaApplyById(Long id);
	
	/**
     * 查询团建明细列表
     * 
     * @param facCollectDetaApply 团建明细信息
     * @return 团建明细集合
     */
	public List<FacCollectDetaApply> selectFacCollectDetaApplyList(FacCollectDetaApply facCollectDetaApply);
	
	/**
     * 新增团建明细
     * 
     * @param facCollectDetaApply 团建明细信息
     * @return 结果
     */
	public int insertFacCollectDetaApply(FacCollectDetaApply facCollectDetaApply);
	
	/**
     * 修改团建明细
     * 
     * @param facCollectDetaApply 团建明细信息
     * @return 结果
     */
	public int updateFacCollectDetaApply(FacCollectDetaApply facCollectDetaApply);
		
	/**
     * 删除团建明细信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacCollectDetaApplyByIds(String ids);
	
}
