package com.ruoyi.system.service.finance;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.finance.FacCollectApply;
import com.ruoyi.system.domain.finance.FacReiAdiApply;
import com.ruoyi.system.domain.finance.FacReimburseApply;
import com.ruoyi.system.domain.finance.ReiHospitalityApply;
import com.ruoyi.system.domain.finance.ReiTrafficApply;

/**
 * 报销 服务层
 * 
 * @author ruoyi
 * @date 2019-07-31
 */
public interface IFacReimburseApplyService {
	/**
	 * 查询报销信息
	 * 
	 * @param id
	 *            报销ID
	 * @return 报销信息
	 */
	public FacReimburseApply selectFacReimburseApplyById(String id);

	/**
	 * 根据编号查询报销申请详情
	 * 
	 * @param num
	 * @return
	 */
	FacReimburseApply deatil(String num);

	/**
	 * 查询报销列表
	 * 
	 * @param facReimburseApply
	 *            报销信息
	 * @return 报销集合
	 */
	public List<FacReimburseApply> selectFacReimburseApplyList(FacReimburseApply facReimburseApply);

	public List<ReiHospitalityApply> selectReiHospitalityApplyList(ReiHospitalityApply reiHospitalityApply);

	public int insertFacreiHospitalityApply(
			ReiHospitalityApply reiHospitalityApply);

	public double selectDouble(String num);

	/**
	 * 查询其他报销列表
	 * 
	 * @param facReimburseApply
	 *            其他报销信息
	 * @return 报销集合
	 */
	public List<FacReiAdiApply> selectFacReiAdiApply(String num);

	/**
	 * 查询交通报销列表
	 * 
	 * @param facReimburseApply
	 *            其他报销信息
	 * @return 报销集合
	 */
	public List<ReiTrafficApply> selectReiTrafficApply(String num);

	/**
	 * 新增报销
	 * 
	 * @param facReimburseApply
	 *            报销信息
	 * @return 结果
	 */
	public AjaxResult insertFacReimburseApply(FacReimburseApply facReimburseApply);
	/**
	 * 新增报销
	 *
	 * @param facReimburseApply
	 *            报销信息
	 * @return 结果
	 */
	public int insertSaveFacReimburseApply(FacReimburseApply facReimburseApply);

	/**
	 * 修改报销
	 * 
	 * @param facReimburseApply
	 *            报销信息
	 * @return 结果
	 */
	public int updateFacReimburseApply(FacReimburseApply facReimburseApply);

	/**
	 * 删除报销信息
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	public int deleteFacReimburseApplyByIds(String ids);

	public int deleteFacReiAdiApplyByIds(Long id);
	/**
	 * 新增交通报销
	 * 
	 * @param facReimburseApply
	 *            报销信息
	 * @return 结果
	 */
	public int insertReiTrafficApply(ReiTrafficApply reiTrafficApply);
	/**
	 * 新增招待报销
	 * 
	 * @param facReimburseApply
	 *            报销信息
	 * @return 结果
	 */
	public int insertReiHospitalityApply(
			ReiHospitalityApply reiHospitalityApply);
	/**
	 * 新增其他报销
	 * 
	 * @param facReimburseApply
	 *            报销信息
	 * @return 结果
	 */
	public int insertFacreiAdiApply(FacReiAdiApply reiAdiApply);

	public int insertApply(FacReimburseApply facReimburseApply);

	ReiTrafficApply selectFacTransById(long id);
	ReiHospitalityApply selectFacHostById(long id);
	int updateReiTrafficApplyById(ReiTrafficApply reiTrafficApply);

	int deleteReiTrafficApplyById(String ids);
	int deleteZhaodaiById(String ids);

	int deleteFacReimburseApplyById(String id);

	List<Long> selectRole(long uesrId);

	void updateFacReimburseApplyByNum(FacReimburseApply facReimburseApply);

	int updateFacReiHospitalityApply(ReiHospitalityApply reiHospitalityApply);

	List<FacReimburseApply> selectFacReimburseApplyListByCreateBy(
			List<SysUser> sysUsersList);

	//查询招待申请审批后生成的招待费报销
	public List<ReiHospitalityApply> selectHospitalityApplyListByUser(Long userId);

	//查询当月的
	List<FacReimburseApply> selectCurrentMonthFacReimburseApplyList(FacReimburseApply facReimburseApply);

    List<ReiHospitalityApply> hosTail(String num);
}
