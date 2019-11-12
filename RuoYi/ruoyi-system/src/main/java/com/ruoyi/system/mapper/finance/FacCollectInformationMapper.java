package com.ruoyi.system.mapper.finance;

import java.util.List;

import com.ruoyi.system.domain.finance.FacCollectInformation;	

/**
 * 团建费报销 数据层
 * 
 * @author ruoyi
 * @date 2019-11-07
 */
public interface FacCollectInformationMapper 
{
	/**
     * 查询团建费报销信息
     * 
     * @param id 团建费报销ID
     * @return 团建费报销信息
     */
	public FacCollectInformation selectFacCollectInformationById(Long id);
	
	/**
     * 查询团建费报销列表
     * 
     * @param facCollectInformation 团建费报销信息
     * @return 团建费报销集合
     */
	public List<FacCollectInformation> selectFacCollectInformationList(FacCollectInformation facCollectInformation);
	
	/**
     * 新增团建费报销
     * 
     * @param facCollectInformation 团建费报销信息
     * @return 结果
     */
	public int insertFacCollectInformation(FacCollectInformation facCollectInformation);
	
	/**
     * 修改团建费报销
     * 
     * @param facCollectInformation 团建费报销信息
     * @return 结果
     */
	public int updateFacCollectInformation(FacCollectInformation facCollectInformation);
	
	/**
     * 删除团建费报销
     * 
     * @param id 团建费报销ID
     * @return 结果
     */
	public int deleteFacCollectInformationById(Long id);
	
	/**
     * 批量删除团建费报销
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacCollectInformationByIds(String[] ids);
	
	public double selectAmount(String num);
	
}