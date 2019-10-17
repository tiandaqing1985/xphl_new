package com.ruoyi.system.service.finance;


import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.finance.FacReiAdiApply;
import com.ruoyi.system.domain.finance.FacReimburseApply; 
import com.ruoyi.system.domain.finance.ReiHospitalityApply;
import com.ruoyi.system.domain.finance.ReiTrafficApply;

import java.math.BigDecimal;
import java.util.List;

/**
 * 报销 服务层
 * 
 * @author ruoyi
 * @date 2019-07-31
 */
public interface IFacReimburseApplyService 
{
	/**
     * 查询报销信息
     * 
     * @param id 报销ID
     * @return 报销信息
     */
	public FacReimburseApply selectFacReimburseApplyById(String id);


	/**
	 * 根据编号查询报销申请详情
	 * @param num
	 * @return
	 */
	FacReimburseApply deatil(String num);
	
	/**
     * 查询报销列表
     * 
     * @param facReimburseApply 报销信息
     * @return 报销集合
     */
	public List<FacReimburseApply> selectFacReimburseApplyList(FacReimburseApply facReimburseApply);
	
	/**
     * 查询其他报销列表
     * 
     * @param facReimburseApply 其他报销信息
     * @return 报销集合
     */
	public List<FacReiAdiApply>  selectFacReiAdiApply(String  num);
	
	/**
     * 查询交通报销列表
     * 
     * @param facReimburseApply 其他报销信息
     * @return 报销集合
     */
	public List<ReiTrafficApply>  selectReiTrafficApply(String  num);
	 
	
	/**
     * 新增报销
     * 
     * @param facReimburseApply 报销信息
     * @return 结果
     */
	public AjaxResult insertFacReimburseApply(FacReimburseApply facReimburseApply);
	
	/**
     * 修改报销
     * 
     * @param facReimburseApply 报销信息
     * @return 结果
     */
	public int updateFacReimburseApply(FacReimburseApply facReimburseApply);
		
	/**
     * 删除报销信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacReimburseApplyByIds(String ids);

 
	/**
     * 新增交通报销
     * 
     * @param facReimburseApply 报销信息
     * @return 结果
     */
	public int insertReiTrafficApply(ReiTrafficApply reiTrafficApply);
	/**
     * 新增招待报销
     * 
     * @param facReimburseApply 报销信息
     * @return 结果
     */
	public int insertReiHospitalityApply(ReiHospitalityApply reiHospitalityApply);
	/**
     * 新增其他报销
     * 
     * @param facReimburseApply 报销信息
     * @return 结果
     */
	public int insertFacreiAdiApply(FacReiAdiApply reiAdiApply);
	
	
	
	

}
