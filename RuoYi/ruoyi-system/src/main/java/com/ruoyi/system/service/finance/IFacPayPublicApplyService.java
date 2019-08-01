package com.ruoyi.system.service.finance;


import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.finance.FacPayPublicApply;

import java.util.List;

/**
 * 对公申请 服务层
 * 
 * @author ruoyi
 * @date 2019-08-01
 */
public interface IFacPayPublicApplyService 
{
	/**
     * 查询对公申请信息
     * 
     * @param num 对公申请ID
     * @return 对公申请信息
     */
	FacPayPublicApply selectFacPayPublicApplyById(String num);
	
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
	public AjaxResult insertFacPayPublicApply(FacPayPublicApply facPayPublicApply);
	
	/**
     * 修改对公申请
     * 
     * @param facPayPublicApply 对公申请信息
     * @return 结果
     */
	public int updateFacPayPublicApply(FacPayPublicApply facPayPublicApply);
		
	/**
     * 删除对公申请信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacPayPublicApplyByIds(String ids);
	
}
