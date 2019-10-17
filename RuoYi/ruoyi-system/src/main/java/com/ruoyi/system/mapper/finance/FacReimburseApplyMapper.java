package com.ruoyi.system.mapper.finance;


import com.ruoyi.system.domain.finance.FacReiAdiApply;
import com.ruoyi.system.domain.finance.FacReimburseApply; 
import com.ruoyi.system.domain.finance.ReiHospitalityApply;
import com.ruoyi.system.domain.finance.ReiTrafficApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 报销 数据层
 * 
 * @author ruoyi
 * @date 2019-07-31
 */
public interface FacReimburseApplyMapper
{


	/**
	 * 报销详细信息汇总
	 * @param num
	 * @return
	 */
	FacReimburseApply detail(String num);

	 

	/**
	 * 招待报销申请详情
	 * @param num
	 * @return
	 */
	List<ReiHospitalityApply> hosTail(String num);

	/**
	 * 公共交通和加班交通
	 * @param num
	 * @return
	 */
	List<ReiTrafficApply> traTail(String num);

	/**
     * 查询报销信息
     * 
     * @param id 报销ID
     * @return 报销信息
     */
	public FacReimburseApply selectFacReimburseApplyById(String id);
	
	/**
     * 查询报销列表
     * 
     * @param facReimburseApply 报销信息
     * @return 报销集合
     */
	public List<FacReimburseApply> selectFacReimburseApplyList(FacReimburseApply facReimburseApply);
	
	 
	/**
     * 新增报销
     * 
     * @param facReimburseApply 报销信息
     * @return 结果
     */
	public int insertFacReimburseApply(FacReimburseApply facReimburseApply);
	
	/**
     * 修改报销
     * 
     * @param facReimburseApply 报销信息
     * @return 结果
     */
	public int updateFacReimburseApply(FacReimburseApply facReimburseApply);
	
	/**
     * 删除报销
     * 
     * @param id 报销ID
     * @return 结果
     */
	public int deleteFacReimburseApplyById(String id);
	
	/**
     * 批量删除报销
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacReimburseApplyByIds(String[] ids);


	/**
	 * 批量增加公共交通和加班交通申请
	 * @param reiTrafficApplies
	 * @return
	 */
	int TrafficBatchInsert(@Param("list") List<ReiTrafficApply> reiTrafficApplies);

	/**
	 * 批量增加招待费报销申请
	 * @param hospitalityApplies
	 * @return
	 */
	int HospBatchInsert(@Param("list") List<ReiHospitalityApply> hospitalityApplies);

	/**
	 * 批量增加行政或其他报销费用·1
	 * @param adiApplies
	 * @return
	 */
	int AdiBatchInsert(@Param("list") List<FacReiAdiApply> adiApplies);
	
	
}