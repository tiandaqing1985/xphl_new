package com.ruoyi.system.service.finance.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.finance.FacPayPublicApply;
import com.ruoyi.system.domain.finance.FacPayPublicDetailed;
import com.ruoyi.system.domain.finance.FacSysUserApproval;
import com.ruoyi.system.mapper.finance.ApprovalProcessMapper;
import com.ruoyi.system.mapper.finance.FacPayPublicApplyMapper;
import com.ruoyi.system.mapper.finance.FacPayPublicDetailedMapper;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.IFacPayPublicApplyService;

/**
 * 对公申请 服务层实现
 * 
 * @author ruoyi
 * @date 2019-10-10
 */
@Service
public class FacPayPublicApplyServiceImpl implements IFacPayPublicApplyService {
	@Autowired
	private FacPayPublicApplyMapper facPayPublicApplyMapper;
	@Autowired
	ApprovalProcessMapper approvalProcessMapper;
	@Autowired
	private ISysUserService iSysUserService;
	@Autowired
	private FacPayPublicDetailedMapper facPayPublicDetailedMapper;

	/**
	 * 查询对公申请信息
	 * 
	 * @param id
	 *            对公申请ID
	 * @return 对公申请信息
	 */
	@Override
	public FacPayPublicApply selectFacPayPublicApplyById(Integer id) {
		return facPayPublicApplyMapper.selectFacPayPublicApplyById(id);
	}

	/**
	 * 查询对公申请列表
	 * 
	 * @param facPayPublicApply
	 *            对公申请信息
	 * @return 对公申请集合
	 */
	@Override
	public List<FacPayPublicApply> selectFacPayPublicApplyList(
			FacPayPublicApply facPayPublicApply) {
		return facPayPublicApplyMapper
				.selectFacPayPublicApplyList(facPayPublicApply);
	}

	/**
	 * 新增对公申请
	 * 
	 * @param facPayPublicApply
	 *            对公申请信息
	 * @return 结果
	 */
	@Override
	public int insertFacPayPublicApply(FacPayPublicApply facPayPublicApply) {
		facPayPublicApply.setStatus("3");
		FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
		facSysUserApproval.setApprovalId(facPayPublicApply.getUser());
		facSysUserApproval.setApprovalTime(new Date());
		facSysUserApproval.setApprovalLevel(1);
		facSysUserApproval.setApplicantId(facPayPublicApply.getUser());
		facSysUserApproval.setApprovalState("3");
		facSysUserApproval.setApprovalSight("1");
		facSysUserApproval.setApplyId(facPayPublicApply.getNum());
		facSysUserApproval.setAmount(facPayPublicApply.getAmount());
		facSysUserApproval.setProjectName(facPayPublicApply.getName());
		Long leaderId = iSysUserService
				.selectApproverIdByApplyerId(facPayPublicApply.getUser());// 所在部门负责人id
		Long upLeaderId = iSysUserService
				.selectUpApproverIdByApplyerId(facPayPublicApply.getUser());// 所在部门负责人的上级leader
		Long approvalId = 0L;// 部门负责人id 审批人
		if (facPayPublicApply.getUser().equals(leaderId)) { // 判断用户是否部门负责人
															// 确定一、二级审批人id
			facSysUserApproval.setApproverId(upLeaderId); // 一级审批人id
			approvalId = upLeaderId;
		} else {
			facSysUserApproval.setApproverId(leaderId);
			approvalId = leaderId;
		}
		if(facPayPublicApply.getUser() == 103
				|| facPayPublicApply.getUser() == 101){
			facSysUserApproval.setApproverId(101L);
		}
		approvalProcessMapper.insert(facSysUserApproval); // 插入一级审批记录
		if (facPayPublicApply.getUser() == 103
				|| facPayPublicApply.getUser() == 101) {
			facPayPublicApply.setStatus("1");
			return facPayPublicApplyMapper
					.insertFacPayPublicApply(facPayPublicApply);
		}
		Long approverId2 = iSysUserService.selectUpApproverIdByApplyerId(
				facSysUserApproval.getApproverId()); // 审批人id的上级id
		int level = 1;
		LinkedList<Long> centerId = (LinkedList<Long>) iSysUserService
				.selectCenterIdByUserId(approvalId);
		if (centerId != null && centerId.size() > 0) {
			centerId.remove(approverId2);
			for (int i = centerId.size() - 1; i >= 0; i--) {
				FacSysUserApproval center = new FacSysUserApproval();// 中心负责人
				center.setApproverId(centerId.get(i));// 审批人ID
				center.setApprovalLevel(++level);// 审批等级
				center.setApplyId(facSysUserApproval.getApplyId()); // 申请人ID
				center.setApprovalState("3");
				center.setApprovalSight("0");// 可见性
				center.setCreateTime(new Date());// 创建时间
				center.setApplyId(facPayPublicApply.getNum());
				center.setAmount(facPayPublicApply.getAmount());
				center.setProjectName(facPayPublicApply.getName());
				approvalProcessMapper.insert(center);
				if (center.getApproverId() == 103) { // 如果是审批人是 coo 直接结束
					return facPayPublicApplyMapper
							.insertFacPayPublicApply(facPayPublicApply);
				}

			}
		}
		return facPayPublicApplyMapper
				.insertFacPayPublicApply(facPayPublicApply);
	}

	/**
	 * 修改对公申请
	 * 
	 * @param facPayPublicApply
	 *            对公申请信息
	 * @return 结果
	 */
	@Override
	public int updateFacPayPublicApply(FacPayPublicApply facPayPublicApply) {
		return facPayPublicApplyMapper
				.updateFacPayPublicApply(facPayPublicApply);
	}

	/**
	 * 删除对公申请对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteFacPayPublicApplyByIds(String ids) {
		return facPayPublicApplyMapper
				.deleteFacPayPublicApplyByIds(Convert.toStrArray(ids));
	}

	@Override
	public FacPayPublicApply deatil(String num) {
		FacPayPublicApply facCostApply = facPayPublicApplyMapper.detail(num);
		return facCostApply;
	}

	@Override
	public List<FacPayPublicDetailed> dgtail(String num) {
		return facPayPublicDetailedMapper.selectDetailedList(num);
	}

	@Override
	public int insertFacPayPublicDetailed(
			FacPayPublicDetailed facPayPublicDetailed) {

		return facPayPublicDetailedMapper
				.insertFacPayPublicDetailed(facPayPublicDetailed);
	}

	/**
	 * 修改对公明细
	 * 
	 * @param facPayPublicDetailed
	 *            对公明细信息
	 * @return 结果
	 */
	@Override
	public int updateFacPayPublicDetailed(
			FacPayPublicDetailed facPayPublicDetailed) {
		return facPayPublicDetailedMapper
				.updateFacPayPublicDetailed(facPayPublicDetailed);
	}

	/**
	 * 删除对公明细对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteFacPayPublicDetailedByIds(String ids) {
		return facPayPublicDetailedMapper
				.deleteFacPayPublicDetailedByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询对公明细信息
	 * 
	 * @param id
	 *            对公明细ID
	 * @return 对公明细信息
	 */
	@Override
	public FacPayPublicDetailed selectFacPayPublicDetailedById(Long id) {
		return facPayPublicDetailedMapper.selectFacPayPublicDetailedById(id);
	}

	@Override
	public int insertApply(FacPayPublicApply facPayPublicApply) {
		facPayPublicApply.setStatus("5");
		return facPayPublicApplyMapper
				.insertFacPayPublicApply(facPayPublicApply);
	}

}
