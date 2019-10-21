package com.ruoyi.system.service.finance;



import java.util.List;

import com.ruoyi.system.domain.finance.FacCostApply;
import com.ruoyi.system.domain.finance.FacHospitalityApply;

/**
 * 招待费申请 服务层
 * 
 * @author ruoyi
 * @date 2019-09-06
 */
public interface IFacHospitalityApplyService 
{
	/**
     * 查询招待费申请信息
     * 
     * @param id 招待费申请ID
     * @return 招待费申请信息
     */
	public FacHospitalityApply selectFacHospitalityApplyById(Long id);
	
	/**
     * 查询招待费申请列表
     * 
     * @param facHospitalityApply 招待费申请信息
     * @return 招待费申请集合
     */
	public List<FacHospitalityApply> selectFacHospitalityApplyList(FacHospitalityApply facHospitalityApply);
	
	/**
     * 新增招待费申请
     * 
     * @param facHospitalityApply 招待费申请信息
     * @return 结果
     */
	public int insertFacHospitalityApply(FacHospitalityApply facHospitalityApply);
	
	/**
     * 新增保存
     * 
     * @param facHospitalityApply 招待费申请信息
     * @return 结果
     */
	 
	public int insertApply(FacHospitalityApply facHospitalityApply);
	
	
	
	
	/**
     * 修改招待费申请
     * 
     * @param facHospitalityApply 招待费申请信息
     * @return 结果
     */
	public int updateFacHospitalityApply(FacHospitalityApply facHospitalityApply);
		
	/**
     * 删除招待费申请信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFacHospitalityApplyByIds(String ids);
	
	/**
	 * 查询招待费申请
	 * @param num
	 * @return
	 */
	FacHospitalityApply deatil(String num);
	
	public int insertFacReiHospitalityApply(FacHospitalityApply facHospitalityApply);
	
	/***查询部门*/
	public String selectDeptName(Long dept);
	
}
