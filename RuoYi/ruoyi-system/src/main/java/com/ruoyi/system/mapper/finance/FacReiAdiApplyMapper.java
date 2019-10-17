package com.ruoyi.system.mapper.finance;

 
import java.util.List;

import com.ruoyi.system.domain.finance.FacReiAdiApply;	

/**
 * 其他报销 数据层
 * 
 * @author ruoyi
 * @date 2019-10-16
 */
public interface FacReiAdiApplyMapper 
{
	/**
     * 查询其他报销信息
     * 
     * @param id 其他报销ID
     * @return 其他报销信息
     */
	public FacReiAdiApply selectFacReiAdiApplyById(Long id);
	
	/**
     * 查询其他报销列表
     * 
     * @param facReiAdiApply 其他报销信息
     * @return 其他报销集合
     */
	public List<FacReiAdiApply> selectFacReiAdiApplyList(FacReiAdiApply facReiAdiApply);
	
	/**
     * 新增其他报销
     * 
     * @param facReiAdiApply 其他报销信息
     * @return 结果
     */
	public int insertFacReiAdiApply(FacReiAdiApply facReiAdiApply);
	
	/**
     * 修改其他报销
     * 
     * @param facReiAdiApply 其他报销信息
     * @return 结果
     */
	public int updateFacReiAdiApply(FacReiAdiApply facReiAdiApply);
	
	/**
     * 删除其他报销
     * 
     * @param id 其他报销ID
     * @return 结果
     */
	public int deleteFacReiAdiApplyById(Long id);
	
	/**
     * 批量删除其他报销
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacReiAdiApplyByIds(String[] ids);
	
	public double selectAmount(String num);
	
}