package com.ruoyi.system.mapper.finance;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.finance.FacReimburseApply;
import com.ruoyi.system.domain.finance.ReiHospitalityApply;
import com.ruoyi.system.domain.finance.ReiTrafficApply;

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
	
	public List<ReiHospitalityApply> selectReiHospitalityApplyList(ReiHospitalityApply reiHospitalityApply);
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

	public int updateFacReimburseApplyByNum(FacReimburseApply facReimburseApply);

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
	
 

//	int AdiBatchInsert(@Param("list") List<FacReiAdiApply> adiApplies);

	public double selectTraAmount(String num);

	public double selectHospAmount(String num);


	public String  selectsysuser(Long id); 
	
	int updateFacReiHospitalityApply(ReiHospitalityApply reiHospitalityApply);
	
	
	ReiTrafficApply selectFacTransById(long id);


	ReiHospitalityApply selectFacHostById(long id);
	
	int updateReiTrafficApplyById(ReiTrafficApply reiTrafficApply);

	int deleteReiTrafficApplyById(String id);

	
	int deleteZhaodaiById(String id);
	
	
	List<FacReimburseApply> selectFacReimburseApplyListByCreateBy(List<SysUser> sysUsersList);
	
	/***查报销每个月每个人的钱*/
	public double selectHospitailAmount(long user);

	List<ReiHospitalityApply> selectHospitalityApplyListByUser(Long userId);

	List<FacReimburseApply> selectCurrentMonthFacReimburseApplyList(FacReimburseApply facReimburseApply);

	List<ReiHospitalityApply> selectCurrentMonthReiHospitalityApplyList(ReiHospitalityApply hospitalityApply);

	public int updateTrPreservation(String num);

	public int updateHospPreservation(String num);

}