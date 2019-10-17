package com.ruoyi.system.service.finance;



import java.util.List;

import com.ruoyi.system.domain.finance.FacCostPutupApply;

/**
 * 差旅住宿 服务层
 * 
 * @author ruoyi
 * @date 2019-10-14
 */
public interface IFacCostPutupApplyService 
{
	/**
     * 查询差旅住宿信息
     * 
     * @param id 差旅住宿ID
     * @return 差旅住宿信息
     */
	public FacCostPutupApply selectFacCostPutupApplyById(Long id);
	
	/**
     * 查询差旅住宿列表
     * 
     * @param facCostPutupApply 差旅住宿信息
     * @return 差旅住宿集合
     */
	public List<FacCostPutupApply> selectFacCostPutupApplyList(FacCostPutupApply facCostPutupApply);
	
	/**
     * 新增差旅住宿
     * 
     * @param facCostPutupApply 差旅住宿信息
     * @return 结果
     */
	public int insertFacCostPutupApply(FacCostPutupApply facCostPutupApply);
	
	/**
     * 修改差旅住宿
     * 
     * @param facCostPutupApply 差旅住宿信息
     * @return 结果
     */
	public int updateFacCostPutupApply(FacCostPutupApply facCostPutupApply);
		
	/**
     * 删除差旅住宿信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacCostPutupApplyByIds(String ids);
	
}
