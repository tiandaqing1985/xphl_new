package com.ruoyi.system.service.finance;

import com.ruoyi.system.domain.finance.FacZhaoDaiLimit;

import java.util.List;

/**
 * 招待费报销限额 服务层
 * 
 * @author ruoyi
 * @date 2019-11-12
 */
public interface IFacZhaoDaiLimitService 
{
	/**
     * 查询招待费报销限额信息
     * 
     * @param id 招待费报销限额ID
     * @return 招待费报销限额信息
     */
	public FacZhaoDaiLimit selectFacZhaoDaiLimitById(Long id);
	
	/**
     * 查询招待费报销限额列表
     * 
     * @param facZhaoDaiLimit 招待费报销限额信息
     * @return 招待费报销限额集合
     */
	public List<FacZhaoDaiLimit> selectFacZhaoDaiLimitList(FacZhaoDaiLimit facZhaoDaiLimit);
	
	/**
     * 新增招待费报销限额
     * 
     * @param facZhaoDaiLimit 招待费报销限额信息
     * @return 结果
     */
	public int insertFacZhaoDaiLimit(FacZhaoDaiLimit facZhaoDaiLimit);
	
	/**
     * 修改招待费报销限额
     * 
     * @param facZhaoDaiLimit 招待费报销限额信息
     * @return 结果
     */
	public int updateFacZhaoDaiLimit(FacZhaoDaiLimit facZhaoDaiLimit);
		
	/**
     * 删除招待费报销限额信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacZhaoDaiLimitByIds(String ids);

	FacZhaoDaiLimit selectFacZhaoDaiLimitByRoleId(Long roleId);
}
