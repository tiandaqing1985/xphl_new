package com.ruoyi.system.mapper.finance;

 
import java.util.List;

import com.ruoyi.system.domain.finance.FacCommonlyApply;	

/**
 * 对公常显 数据层
 * 
 * @author ruoyi
 * @date 2019-10-24
 */
public interface FacCommonlyApplyMapper 
{
	/**
     * 查询对公常显信息
     * 
     * @param id 对公常显ID
     * @return 对公常显信息
     */
	public FacCommonlyApply selectFacCommonlyApplyById(Long id);
	
	/**
     * 查询对公常显列表
     * 
     * @param facCommonlyApply 对公常显信息
     * @return 对公常显集合
     */
	public List<FacCommonlyApply> selectFacCommonlyApplyList(FacCommonlyApply facCommonlyApply);
	
	/**
     * 新增对公常显
     * 
     * @param facCommonlyApply 对公常显信息
     * @return 结果
     */
	public int insertFacCommonlyApply(FacCommonlyApply facCommonlyApply);
	
	/**
     * 修改对公常显
     * 
     * @param facCommonlyApply 对公常显信息
     * @return 结果
     */
	public int updateFacCommonlyApply(FacCommonlyApply facCommonlyApply);
	
	/**
     * 删除对公常显
     * 
     * @param id 对公常显ID
     * @return 结果
     */
	public int deleteFacCommonlyApplyById(Long id);
	
	/**
     * 批量删除对公常显
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacCommonlyApplyByIds(String[] ids);
	
}