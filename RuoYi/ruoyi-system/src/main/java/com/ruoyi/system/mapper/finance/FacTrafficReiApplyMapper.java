package com.ruoyi.system.mapper.finance;

import java.util.List;

import com.ruoyi.system.domain.finance.FacTrafficReiApply;	

/**
 * 交通报销申请 数据层
 * 
 * @author ruoyi
 * @date 2019-10-30
 */
public interface FacTrafficReiApplyMapper 
{
	/**
     * 查询交通报销申请信息
     * 
     * @param id 交通报销申请ID
     * @return 交通报销申请信息
     */
	public FacTrafficReiApply selectFacTrafficReiApplyById(Integer id);
	
	/**
     * 查询交通报销申请列表
     * 
     * @param facTrafficReiApply 交通报销申请信息
     * @return 交通报销申请集合
     */
	public List<FacTrafficReiApply> selectFacTrafficReiApplyList(FacTrafficReiApply facTrafficReiApply);
	
	/**
     * 新增交通报销申请
     * 
     * @param facTrafficReiApply 交通报销申请信息
     * @return 结果
     */
	public int insertFacTrafficReiApply(FacTrafficReiApply facTrafficReiApply);
	
	/**
     * 修改交通报销申请
     * 
     * @param facTrafficReiApply 交通报销申请信息
     * @return 结果
     */
	public int updateFacTrafficReiApply(FacTrafficReiApply facTrafficReiApply);
	
	/**
     * 删除交通报销申请
     * 
     * @param id 交通报销申请ID
     * @return 结果
     */
	public int deleteFacTrafficReiApplyById(Integer id); 
	
	/**
     * 批量删除交通报销申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacTrafficReiApplyByIds(String[] ids);
	
	/***查每个月的钱*/
	public double selectAmount(long applyUser);
}