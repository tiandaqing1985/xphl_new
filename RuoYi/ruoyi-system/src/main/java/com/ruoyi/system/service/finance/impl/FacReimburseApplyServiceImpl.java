package com.ruoyi.system.service.finance.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.finance.*;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.mapper.UserApplyMapper;
import com.ruoyi.system.mapper.finance.ApprovalProcessMapper;
import com.ruoyi.system.mapper.finance.FacReiAdiApplyMapper;
import com.ruoyi.system.mapper.finance.FacReimburseApplyMapper;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.ApprovalProcessService;
import com.ruoyi.system.service.finance.IFacReimburseApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 报销 服务层实现
 *
 * @author ruoyi
 * @date 2019-07-31
 */
@Service
public class FacReimburseApplyServiceImpl implements IFacReimburseApplyService {

	private static final String CEO_id = "101";

	private static final String COO_id = "103";
	@Autowired
	ISysUserService iSysUserService;
	@Autowired
	ApprovalProcessService approvalProcessService;
	@Autowired
	ApprovalProcessMapper approvalProcessMapper;
	@Autowired
	private UserApplyMapper userApplyMapper;
	@Autowired
	private FacReiAdiApplyMapper facReiAdiApplyMapper;

	@Autowired
	private FacReimburseApplyMapper facReimburseApplyMapper;

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private ISysRoleService sysRoleService;

	/**
	 * 查询报销信息
	 *
	 * @param id
	 *            报销ID
	 * @return 报销信息
	 */
	@Override
	public FacReimburseApply selectFacReimburseApplyById(String id) {
		return facReimburseApplyMapper.selectFacReimburseApplyById(id);
	}

	@Override
	public FacReimburseApply deatil(String num) {
		FacReimburseApply facReimburseApply = facReimburseApplyMapper
				.detail(num);
		// 招待费用
		List<ReiHospitalityApply> hospitalityApplyList = facReimburseApplyMapper
				.hosTail(num);
		if (hospitalityApplyList.size() > 0 && hospitalityApplyList != null) {
			facReimburseApply.setHospitalityApplies(hospitalityApplyList);
		}
		// 行政和其他
		// List<FacReiAdiApply> applies = facReimburseApplyMapper.adiTail(num);
		// if (applies != null && applies.size() > 0) {
		// facReimburseApply.setOtherReiAdiApplies(applies);
		// }
		// 加班交通申请和公共交通申请
		List<ReiTrafficApply> traTail = facReimburseApplyMapper.traTail(num);
		if (traTail != null && traTail.size() > 0) {
			facReimburseApply.setTrafficReiApplyList(traTail);
		}
		return facReimburseApply;
	}

	/**
	 * 查询报销列表
	 *
	 * @param facReimburseApply
	 *            报销信息
	 * @return 报销集合
	 */
	@Override
	public List<FacReimburseApply> selectFacReimburseApplyList(
			FacReimburseApply facReimburseApply) {
		return facReimburseApplyMapper
				.selectFacReimburseApplyList(facReimburseApply);
	}

	@Override
	public double selectDouble(String num) {
		double a = facReiAdiApplyMapper.selectAmount(num);
		double b = facReimburseApplyMapper.selectHospAmount(num);
		double c = facReimburseApplyMapper.selectTraAmount(num);
		return a + b + c;
	}

	@Override
	@Transactional
	public AjaxResult insertFacReimburseApply(
			FacReimburseApply facReimburseApply) {

		String area = facReimburseApplyMapper
				.selectsysuser(facReimburseApply.getLoanUser());

		try {
			int level = 1;
			// 获取总的报销申请金额
			FacSysUserApproval center = new FacSysUserApproval();// 中心负责人
			center.setApplicantId(facReimburseApply.getLoanUser());
			center.setApplyId(facReimburseApply.getNum());
			center.setApplicantId(facReimburseApply.getLoanUser());
			center.setCreateTime(new Date());
			center.setApprovalState("3");
			center.setApprovalTime(new Date());

			facReimburseApply.setType("日常报销");
			facReimburseApply.setApplyStatus("1");

			double a = facReiAdiApplyMapper
					.selectAmount(facReimburseApply.getNum());
			double b = facReimburseApplyMapper
					.selectHospAmount(facReimburseApply.getNum());
			double c = facReimburseApplyMapper
					.selectTraAmount(facReimburseApply.getNum());
			facReimburseApply.setAmount(a + b + c);
			center.setAmount(a + b + c);
			facReimburseApply.setReimburseTime(new Date());
			double num = center.getAmount();
			Long leaderId = iSysUserService.selectApproverIdByApplyerId(
					facReimburseApply.getLoanUser());
			Long upLeaderId = iSysUserService.selectUpApproverIdByApplyerId(
					facReimburseApply.getLoanUser());

			List<ReiTrafficApply> traList = facReimburseApplyMapper
					.traTail(facReimburseApply.getNum());
			facReimburseApply.setTrafficReiApplyList(traList);

			// 报销审批流开始
			String roleName = "";
			List<String> queryRoleName = approvalProcessMapper
					.queryRoleName(facReimburseApply.getLoanUser());
			for (String name : queryRoleName) {
				roleName = roleName + "," + name.trim().toLowerCase();
			}
			if (roleName.contains("ceo")) {
				// 是ceo 直接通过
				facReimburseApply.setStatus("3");
				facReimburseApplyMapper
						.insertFacReimburseApply(facReimburseApply);
				// 如果为coo申请 交给ceo审批
			} else if (roleName.contains("coo")) {
				facReimburseApply.setStatus("0");
				facReimburseApplyMapper
						.insertFacReimburseApply(facReimburseApply);
				FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
				facSysUserApproval.setApprovalId(new Long(CEO_id));
				facSysUserApproval.setApprovalTime(new Date());
				facSysUserApproval.setApprovalLevel(level++);
				facSysUserApproval.setApplyId(facReimburseApply.getNum());
				facSysUserApproval.setProjectName(facReimburseApply.getName());
				facSysUserApproval.setApprovalState("3");
				approvalProcessService.insert(facSysUserApproval);
				// 为加班申请增加人事审批
				if (facReimburseApply.getTrafficReiApplyList() != null
						&& facReimburseApply.getTrafficReiApplyList().size() > 0
						&& facReimburseApply.getTrafficReiApplyList().get(0)
								.getType().equals("2")) {

					FacSysUserApproval facSysUserApproval2 = new FacSysUserApproval();
					facSysUserApproval2.setCreateTime(new Date());

					if (area.equals("1")) {
						facSysUserApproval2.setApproverId(new Long("253"));// 审批id为人事
					} else {
						center.setApproverId(new Long("257"));// 审批id为人事
					}

					facSysUserApproval2.setApprovalTime(new Date());
					facSysUserApproval2.setApprovalLevel(level++);
					facSysUserApproval2.setApplyId(facReimburseApply.getNum());
					facSysUserApproval2
							.setProjectName(facReimburseApply.getName());
					facSysUserApproval2.setApprovalState("3");
					approvalProcessService.insert(facSysUserApproval2);
				} else {
				}
			}
			// 普通员工
			else {
				if (!roleName.contains("部门leader")) {
					center.setApproverId(leaderId);
					center.setApprovalLevel(level++);
					center.setProjectName(facReimburseApply.getName());
					approvalProcessService.insert(center);
					if (level == 2) {
						// 如果含有加班申请 人事审批
						if (facReimburseApply.getTrafficReiApplyList() != null
								&& facReimburseApply.getTrafficReiApplyList()
										.size() != 0) {
							List<ReiTrafficApply> trafficReiApplyList = facReimburseApply
									.getTrafficReiApplyList();
							for (ReiTrafficApply reiTrafficApply : trafficReiApplyList) {
								if (reiTrafficApply.getType().equals("加班")) {

									if (area.equals("1")) {
										center.setApproverId(new Long("253"));// 审批id为人事
									} else {
										center.setApproverId(new Long("257"));// 审批id为人事
									}
									center.setApprovalLevel(level++);
									center.setProjectName(
											facReimburseApply.getName());
									approvalProcessService.insert(center);
									break;
								}
							}
						}
					}

				}
				// 获取该申请人下的所有领导
				LinkedList<Long> centerId = (LinkedList<Long>) iSysUserService
						.selectCenterIdByUserId(
								facReimburseApply.getLoanUser());

				if (centerId != null && centerId.size() > 0) {
					for (int i = centerId.size() - 1; i >= 0; i--) {
						boolean flg = false;
						if (centerId.get(i)
								.equals(facReimburseApply.getLoanUser())) {
							centerId.remove(centerId.get(i));
						}
						if (centerId.get(i) == leaderId) {
							if (roleName.contains("部门leader")) {
								center.setApproverId(centerId.get(i));
								center.setApprovalLevel(level++);
								center.setProjectName(
										facReimburseApply.getName());
								approvalProcessService.insert(center);
							}
						} else {
							center.setApproverId(centerId.get(i));
							center.setApprovalLevel(level++);
							center.setProjectName(facReimburseApply.getName());
							approvalProcessService.insert(center);
							if (level == 2) {
								// 如果含有加班申请 人事审批
								if (facReimburseApply
										.getTrafficReiApplyList() != null
										&& facReimburseApply
												.getTrafficReiApplyList()
												.size() != 0) {
									List<ReiTrafficApply> trafficReiApplyList = facReimburseApply
											.getTrafficReiApplyList();
									for (ReiTrafficApply reiTrafficApply : trafficReiApplyList) {
										if (reiTrafficApply.getType()
												.equals("加班")) {
											if (area.equals("1")) {
												center.setApproverId(
														new Long("253"));// 审批id为人事
											} else {
												center.setApproverId(
														new Long("257"));// 审批id为人事
											}
											center.setApprovalLevel(level++);
											center.setProjectName(
													facReimburseApply
															.getName());
											approvalProcessService
													.insert(center);
											break;
										}
									}
								}
							}
						}
						List<SysRole> sysRoles = sysRoleService
								.selectRolesByUserId(centerId.get(i));
						boolean isCaywu = false;
						boolean isRenshi = false;
						boolean isXingzheng = false;
						for (SysRole role : sysRoles) {
							if (role.isFlag()) {
								if (role.getRoleId() == 13) {
									// 是否财务
									isCaywu = true;
								} else if (role.getRoleId().equals(6)
										|| role.getRoleId().equals(3)) {
									// 是否人事
									isRenshi = true;
								} else if (role.getRoleId().equals(14)) {
									// 是否行政
									isXingzheng = true;
								}
							}
						}

						facReimburseApply.setStatus("3");

						if (centerId.get(i).intValue() == 103) { // 如果是审批人是 coo
							// 直接结束
							if (level == 2) {
								// 如果含有加班申请 人事审批
								if (facReimburseApply
										.getTrafficReiApplyList() != null
										&& facReimburseApply
												.getTrafficReiApplyList()
												.size() != 0) {
									List<ReiTrafficApply> trafficReiApplyList = facReimburseApply
											.getTrafficReiApplyList();
									for (ReiTrafficApply reiTrafficApply : trafficReiApplyList) {
										if (reiTrafficApply.getType()
												.equals("加班")) {
											if (area.equals("1")) {
												center.setApproverId(
														new Long("253"));// 审批id为人事
											} else {
												center.setApproverId(
														new Long("257"));// 审批id为人事
											}
											center.setApprovalLevel(level++);
											center.setProjectName(
													facReimburseApply
															.getName());
											approvalProcessService
													.insert(center);
											break;
										}
									}
								}
							}
							facReimburseApplyMapper
									.insertFacReimburseApply(facReimburseApply);
							flg = true;
						} else if (isCaywu || isRenshi || isXingzheng) {// 财务审批规则

							if (num <= 2000.00) {
								facReimburseApply.setStatus("3");
								facReimburseApplyMapper.insertFacReimburseApply(
										facReimburseApply);
								flg = true;
							} else if (num > 2000.00) {
								center.setApproverId(new Long(194));
								center.setApprovalLevel(level++);
								center.setProjectName(
										facReimburseApply.getName());
								approvalProcessService.insert(center);
								if (num > 10000.00) {
									center.setApproverId(new Long(103));
									center.setApprovalLevel(level++);
									center.setProjectName(
											facReimburseApply.getName());
									approvalProcessService.insert(center);
								}
								facReimburseApplyMapper.insertFacReimburseApply(
										facReimburseApply);
								flg = true;
							}

						} else if (centerId.get(i).intValue() == 194) {// 到韩总处

							if (num <= 10000.00) {
								facReimburseApply.setStatus("3");
								facReimburseApplyMapper.insertFacReimburseApply(
										facReimburseApply);
								flg = true;
							}

						} else {
							if (level == 2) {
								// 如果含有加班申请 人事审批
								if (facReimburseApply
										.getTrafficReiApplyList() != null
										&& facReimburseApply
												.getTrafficReiApplyList()
												.size() != 0) {
									List<ReiTrafficApply> trafficReiApplyList = facReimburseApply
											.getTrafficReiApplyList();
									for (ReiTrafficApply reiTrafficApply : trafficReiApplyList) {
										if (reiTrafficApply.getType()
												.equals("加班")) {
											if (area.equals("1")) {
												center.setApproverId(
														new Long("253"));// 审批id为人事
											} else {
												center.setApproverId(
														new Long("257"));// 审批id为人事
											}
											center.setApprovalLevel(level++);
											center.setProjectName(
													facReimburseApply
															.getName());
											approvalProcessService
													.insert(center);
											break;
										}
									}
								}
							}
						}

						if (flg) {
							return AjaxResult.success();
						}
					}

				}
			}
			facReimburseApplyMapper.insertFacReimburseApply(facReimburseApply);
			return AjaxResult.success();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			System.out.println("添加报销申请" + e.getMessage());
			e.printStackTrace();
			return AjaxResult.error();

		}

	}

	/**
	 * 修改报销
	 *
	 * @param facReimburseApply
	 *            报销信息
	 * @return 结果
	 */
	@Override
	public int updateFacReimburseApply(FacReimburseApply facReimburseApply) {
		return facReimburseApplyMapper
				.updateFacReimburseApply(facReimburseApply);
	}

	/**
	 * 删除报销对象
	 *
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteFacReimburseApplyByIds(String ids) {
		return facReimburseApplyMapper
				.deleteFacReimburseApplyByIds(Convert.toStrArray(ids));
	}

	@Override
	public int insertFacreiAdiApply(FacReiAdiApply reiAdiApply) {
		List<FacReiAdiApply> reiAdiApplies = new ArrayList<FacReiAdiApply>();
		reiAdiApplies.add(reiAdiApply);
		return facReiAdiApplyMapper.insertFacReiAdiApply(reiAdiApply);
	}

	@Override
	public int insertReiTrafficApply(ReiTrafficApply reiTrafficApply) {
		List<ReiTrafficApply> reiAdiApplies = new ArrayList<ReiTrafficApply>();
		reiAdiApplies.add(reiTrafficApply);
		return facReimburseApplyMapper.TrafficBatchInsert(reiAdiApplies);

	}

	@Override
	public int insertReiHospitalityApply(
			ReiHospitalityApply reiHospitalityApply) {
		List<ReiHospitalityApply> reiAdiApplies = new ArrayList<ReiHospitalityApply>();
		reiAdiApplies.add(reiHospitalityApply);
		return facReimburseApplyMapper.HospBatchInsert(reiAdiApplies);

	}

	@Override
	public List<FacReiAdiApply> selectFacReiAdiApply(String num) {
		FacReiAdiApply facReiAdiApply = new FacReiAdiApply();
		facReiAdiApply.setNum(num);
		return facReiAdiApplyMapper.selectFacReiAdiApplyList(facReiAdiApply);
	}

	@Override
	public List<ReiTrafficApply> selectReiTrafficApply(String num) {
		List<ReiTrafficApply> list=	 facReimburseApplyMapper.traTail(num);
		for( ReiTrafficApply rei:list ){ 
			if(rei.getType().equals("加班")){
				
			}else{
				
			} 
		}
		
		
		
		return facReimburseApplyMapper.traTail(num);
	}

	@Override
	public ReiTrafficApply selectFacTransById(long id) {
		return facReimburseApplyMapper.selectFacTransById(id);
	}

	@Override
	public int updateReiTrafficApplyById(ReiTrafficApply reiTrafficApply) {
		return facReimburseApplyMapper
				.updateReiTrafficApplyById(reiTrafficApply);
	}

	@Override
	public int deleteReiTrafficApplyById(String id) {
		return facReimburseApplyMapper.deleteReiTrafficApplyById(id);
	}

	@Override
	public int deleteFacReiAdiApplyByIds(Long id) {
		return facReiAdiApplyMapper.deleteFacReiAdiApplyById(id);
	}

	@Override
	public int insertSaveFacReimburseApply(
			FacReimburseApply facReimburseApply) {
		facReimburseApply.setSubmitStatus("save");
		double a = facReiAdiApplyMapper
				.selectAmount(facReimburseApply.getNum());
		double b = facReimburseApplyMapper
				.selectHospAmount(facReimburseApply.getNum());
		double c = facReimburseApplyMapper
				.selectTraAmount(facReimburseApply.getNum());
		facReimburseApply.setAmount(a + b + c);
		facReimburseApply.setStatus("5");
		return facReimburseApplyMapper
				.insertFacReimburseApply(facReimburseApply);
	}

	@Override
	public int deleteFacReimburseApplyById(String id) {
		return facReimburseApplyMapper.deleteFacReimburseApplyById(id);
	}

	@Override
	public List<Long> selectRole(long uesrId) {
		SysUser sysUserRole = new SysUser();
		sysUserRole.setUserId(uesrId);
		return sysUserRoleMapper.selectRoleIdByUserId(sysUserRole);

	}

	@Override
	public List<ReiHospitalityApply> selectReiHospitalityApplyList(
			ReiHospitalityApply reiHospitalityApply) {

		return facReimburseApplyMapper
				.selectReiHospitalityApplyList(reiHospitalityApply);
	}

	@Override
	public void updateFacReimburseApplyByNum(
			FacReimburseApply facReimburseApply) {
		facReimburseApplyMapper.updateFacReimburseApplyByNum(facReimburseApply);
	}

	@Override
	public List<FacReimburseApply> selectFacReimburseApplyListByCreateBy(
			List<SysUser> sysUsersList) {
		return facReimburseApplyMapper
				.selectFacReimburseApplyListByCreateBy(sysUsersList);
	}

	@Override
	public int insertFacreiHospitalityApply(
			ReiHospitalityApply reiHospitalityApply) {

		List<ReiHospitalityApply> list = new ArrayList<>();
		list.add(reiHospitalityApply);

		return facReimburseApplyMapper.HospBatchInsert(list);
	}

	@Override
	public int deleteZhaodaiById(String ids) {
		// TODO Auto-generated method stub
		return facReimburseApplyMapper.deleteZhaodaiById(ids);
	}

	@Override
	public ReiHospitalityApply selectFacHostById(long id) {
		return facReimburseApplyMapper.selectFacHostById(id);
	}

	@Override
	public int insertApply(FacReimburseApply facReimburseApply) {
		
		return 	facReimburseApplyMapper.insertFacReimburseApply(facReimburseApply);
	}

	@Override
	public int updateFacReiHospitalityApply(
			ReiHospitalityApply reiHospitalityApply) {
		return  facReimburseApplyMapper.updateFacReiHospitalityApply(reiHospitalityApply);
		
	}

}
