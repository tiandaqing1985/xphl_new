package com.ruoyi.system.service.finance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.FacApplyType;
import com.ruoyi.common.utils.IdWorker;
import com.ruoyi.system.domain.finance.FacReiAdiApply;
import com.ruoyi.system.domain.finance.FacReimburseApply;
import com.ruoyi.system.domain.finance.FacSysUserApproval; 
import com.ruoyi.system.domain.finance.ReiHospitalityApply;
import com.ruoyi.system.domain.finance.ReiTrafficApply;
import com.ruoyi.system.mapper.UserApplyMapper;
import com.ruoyi.system.mapper.finance.ApprovalProcessMapper;
import com.ruoyi.system.mapper.finance.FacReiAdiApplyMapper;
import com.ruoyi.system.mapper.finance.FacReimburseApplyMapper;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.ApprovalProcessService;
import com.ruoyi.system.service.finance.IFacReimburseApplyService; 

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

    /**
     * 查询报销信息
     *
     * @param id 报销ID
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
      //  if (applies != null && applies.size() > 0) {
         //   facReimburseApply.setOtherReiAdiApplies(applies);
      //  }
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
     * @param facReimburseApply 报销信息
     * @return 报销集合
     */
    @Override
    public List<FacReimburseApply> selectFacReimburseApplyList(
            FacReimburseApply facReimburseApply) {
        return facReimburseApplyMapper
                .selectFacReimburseApplyList(facReimburseApply);
    }

    /**
     * 新增报销
     *
     * @param facReimburseApply 报销信息
     * @return 结果
     */
    @Transactional
    @Override
    public AjaxResult insertFacReimburseApply(
            FacReimburseApply facReimburseApply) {
        try {
            int level = 1;
            // 获取总的报销申请金额

            facReimburseApply.setType("日常报销");
            IdWorker idWorker = new IdWorker(0, 1);
            facReimburseApply.setNum(FacApplyType.REIMBURSE.getIdentification()
                    + idWorker.nextId());
            facReimburseApply.setReimburseTime(new Date()); 
             double num =20 ; 

            BigDecimal num1 = new BigDecimal("2000");

            Long leaderId = iSysUserService.selectApproverIdByApplyerId(
                    facReimburseApply.getLoanUser());
            Long upLeaderId = iSysUserService.selectUpApproverIdByApplyerId(
                    facReimburseApply.getLoanUser());
            // 报销审批流开始
            if (("1").equals(facReimburseApply.getApplyStatus())) {
                String roleName = approvalProcessMapper
                        .queryRoleName(facReimburseApply.getLoanUser()).trim()
                        .toLowerCase();
                if ("ceo".equals(roleName)) {
                    // 是ceo 直接通过
                    facReimburseApply.setStatus("3");
                    facReimburseApplyMapper
                            .insertFacReimburseApply(facReimburseApply);
                    // 如果为coo申请 交给ceo审批
                } else if ("coo".equals(roleName)) {
                    facReimburseApply.setStatus("0");
                    facReimburseApplyMapper
                            .insertFacReimburseApply(facReimburseApply);
                    FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
                    facSysUserApproval.setApprovalId(new Long(CEO_id));
                    facSysUserApproval.setApprovalTime(new Date());
                    facSysUserApproval.setApprovalLevel(1);
                    facSysUserApproval.setApplyId(facReimburseApply.getNum());
                    approvalProcessMapper.insert(facSysUserApproval);
                    // 为加班申请增加人事审批
                    if (facReimburseApply.getTrafficReiApplyList() != null
                            && facReimburseApply.getTrafficReiApplyList().get(0).getType().equals("2")) {

                        FacSysUserApproval facSysUserApproval2 = new FacSysUserApproval();

                        facSysUserApproval2.setCreateTime(new Date());
                        facSysUserApproval2.setApproverId(new Long("196"));// 审批id为人事
                        facSysUserApproval2.setApprovalLevel(2);
                        facSysUserApproval2
                                .setApplyId(facReimburseApply.getNum());
                        approvalProcessMapper.insert(facSysUserApproval2);
                        // 财务
                        FacSysUserApproval facSysUserApproval1 = new FacSysUserApproval();
                        facSysUserApproval1.setApprovalId(new Long("154"));// 审批id为财务
                        facSysUserApproval1.setApprovalTime(new Date());
                        facSysUserApproval1.setApprovalLevel(3);
                        facSysUserApproval1
                                .setApplyId(facReimburseApply.getNum());
                        approvalProcessMapper.insert(facSysUserApproval);
                    } else {
                        // 财务
                        FacSysUserApproval facSysUserApproval1 = new FacSysUserApproval();
                        facSysUserApproval1.setApprovalId(new Long("154"));//待改
                        facSysUserApproval1.setApprovalTime(new Date());
                        facSysUserApproval1.setApprovalLevel(2);
                        facSysUserApproval1
                                .setApplyId(facReimburseApply.getNum());
                        approvalProcessMapper.insert(facSysUserApproval);
                    }
                } else if (("部门leader").equals(roleName)) {
                    // 获取该申请人下的所有领导
                    LinkedList<Long> centerId = (LinkedList<Long>) iSysUserService
                            .selectCenterIdByUserId(
                                    facReimburseApply.getLoanUser());
                    if (centerId != null && centerId.size() > 0) {
                        for (int i = centerId.size() - 1; i >= 0; i--) {
                            if (centerId.get(i)
                                    .equals(facReimburseApply.getLoanUser())) {
                                centerId.remove(centerId.get(i));
                            }
                            FacSysUserApproval center = new FacSysUserApproval();// 中心负责人
                            center.setCreateTime(new Date());
                            center.setApproverId(centerId.get(i));
                            level = level + 1;
                            center.setApprovalLevel(level);
                            center.setApplyId(facReimburseApply.getNum());
                            approvalProcessMapper.insert(center);

                            facReimburseApply.setStatus("0");
                            facReimburseApplyMapper
                                    .insertFacReimburseApply(facReimburseApply);

                            // 如果是审批人是 coo 直接结束
                            if (center.getApproverId() == 103) {
                                // 如果含有加班申请 人事审批
                                if (facReimburseApply.getTrafficReiApplyList() != null
                                        && facReimburseApply.getTrafficReiApplyList().get(0).getType().equals("2")) {
                                    center.setCreateTime(new Date());
                                    center.setApproverId(new Long("196"));//待改
                                    level = level + 1;
                                    center.setApprovalLevel(level);
                                    center.setApplyId(
                                            facReimburseApply.getNum());
                                    approvalProcessMapper.insert(center);
                                }
                                // 财务
                                center.setCreateTime(new Date());
                                center.setApproverId(new Long("154"));//待改
                                center.setApprovalLevel(++level);
                                center.setApplyId(facReimburseApply.getNum());
                                approvalProcessMapper.insert(center);
                                return AjaxResult.success();
                            } else if (center.getApproverId() == 110) {// 财务审批规则

                                if (num <= 2000.00) {
                                    facReimburseApply.setStatus("3");
                                    facReimburseApplyMapper
                                            .insertFacReimburseApply(
                                                    facReimburseApply);
                                } else if (num > 2000.00 && num <= 10000.00) {
                                    facReimburseApply.setStatus("0");
                                    facReimburseApplyMapper
                                            .insertFacReimburseApply(
                                                    facReimburseApply);
                                    FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
                                    facSysUserApproval
                                            .setApprovalId(new Long(194));
                                    facSysUserApproval
                                            .setApprovalTime(new Date());
                                    facSysUserApproval.setApprovalLevel(1);
                                    facSysUserApproval.setApplyId(
                                            facReimburseApply.getNum());
                                    approvalProcessMapper
                                            .insert(facSysUserApproval);
                                }
                            } else if (center.getApproverId() == 216) {// 人事审批规则

                                if (num <= 2000.00) {
                                    facReimburseApply.setStatus("3");
                                    facReimburseApplyMapper
                                            .insertFacReimburseApply(
                                                    facReimburseApply);
                                } else if (num > 2000.00 && num <= 10000.00) {
                                    facReimburseApply.setStatus("0");
                                    facReimburseApplyMapper
                                            .insertFacReimburseApply(
                                                    facReimburseApply);
                                    FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
                                    facSysUserApproval
                                            .setApprovalId(new Long(194));
                                    facSysUserApproval
                                            .setApprovalTime(new Date());
                                    facSysUserApproval.setApprovalLevel(1);
                                    facSysUserApproval.setApplyId(
                                            facReimburseApply.getNum());
                                    approvalProcessMapper
                                            .insert(facSysUserApproval);
                                }
                            } else if (center.getApproverId() == 194) {//到韩总处
                                if (num <= 10000.00) {
                                    facReimburseApply.setStatus("3");
                                    facReimburseApplyMapper
                                            .insertFacReimburseApply(
                                                    facReimburseApply);
                                }
                            }
                        }

                    }
                }
                // 普通员工
            } else {
                // 获取该申请人下的所有领导
                LinkedList<Long> centerId = (LinkedList<Long>) iSysUserService
                        .selectCenterIdByUserId(
                                facReimburseApply.getLoanUser());
                if (centerId != null && centerId.size() > 0) {
                    for (int i = centerId.size() - 1; i >= 0; i--) {
                        if (centerId.get(i)
                                .equals(facReimburseApply.getLoanUser())) {
                            centerId.remove(centerId.get(i));
                        }
                        FacSysUserApproval center = new FacSysUserApproval();// 中心负责人
                        center.setApproverId(centerId.get(i));
                        center.setApprovalLevel(++level);
                        center.setApplyId(facReimburseApply.getNum());
                        center.setCreateTime(new Date());
                        approvalProcessMapper.insert(center);

                        facReimburseApply.setStatus("0");
                        facReimburseApplyMapper
                                .insertFacReimburseApply(facReimburseApply);

                        if (center.getApproverId() == 103) { // 如果是审批人是 coo 直接结束
                            // 如果含有加班申请 人事审批
                            if (facReimburseApply.getTrafficReiApplyList() != null && facReimburseApply.getTrafficReiApplyList().get(0).getType().equals("2")) {
                                center.setCreateTime(new Date());
                                center.setApproverId(new Long("196"));
                                level = level + 1;
                                center.setApprovalLevel(level);
                                center.setApplyId(facReimburseApply.getNum());
                                approvalProcessMapper.insert(center);
                            }
                            // 财务
                            center.setCreateTime(new Date());
                            center.setApproverId(new Long("154"));
                            center.setApprovalLevel(++level);
                            center.setApplyId(facReimburseApply.getNum());
                            approvalProcessMapper.insert(center);
                            return AjaxResult.success();
                        } else if (center.getApproverId() == 110) {// 财务审批规则

                            if (num <= 2000.00) {
                                facReimburseApply.setStatus("3");
                                facReimburseApplyMapper
                                        .insertFacReimburseApply(
                                                facReimburseApply);
                            } else if (num > 2000.00 && num <= 10000.00) {
                                facReimburseApply.setStatus("0");
                                facReimburseApplyMapper
                                        .insertFacReimburseApply(
                                                facReimburseApply);
                                FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
                                facSysUserApproval
                                        .setApprovalId(new Long(194));
                                facSysUserApproval
                                        .setApprovalTime(new Date());
                                facSysUserApproval.setApprovalLevel(1);
                                facSysUserApproval.setApplyId(
                                        facReimburseApply.getNum());
                                approvalProcessMapper
                                        .insert(facSysUserApproval);
                            }
                        } else if (center.getApproverId() == 216) {// 人事审批规则

                            if (num <= 2000.00) {
                                facReimburseApply.setStatus("3");
                                facReimburseApplyMapper
                                        .insertFacReimburseApply(
                                                facReimburseApply);
                            } else if (num > 2000.00 && num <= 10000.00) {
                                facReimburseApply.setStatus("0");
                                facReimburseApplyMapper
                                        .insertFacReimburseApply(
                                                facReimburseApply);
                                FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
                                facSysUserApproval
                                        .setApprovalId(new Long(194));
                                facSysUserApproval
                                        .setApprovalTime(new Date());
                                facSysUserApproval.setApprovalLevel(1);
                                facSysUserApproval.setApplyId(
                                        facReimburseApply.getNum());
                                approvalProcessMapper
                                        .insert(facSysUserApproval);
                            }
                        } else if (center.getApproverId() == 194) {//到韩总处
                            if (num <= 10000.00) {
                                facReimburseApply.setStatus("3");
                                facReimburseApplyMapper
                                        .insertFacReimburseApply(
                                                facReimburseApply);
                            }
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
     * @param facReimburseApply 报销信息
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
     * @param ids 需要删除的数据ID
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
		 
		return facReimburseApplyMapper.traTail(num);
	}

    @Override
    public ReiTrafficApply selectFacTransById(String id) {
        return facReimburseApplyMapper.selectFacTransById(id);
    }

    @Override
    public int updateReiTrafficApplyById(ReiTrafficApply reiTrafficApply) {
        return facReimburseApplyMapper.updateReiTrafficApplyById(reiTrafficApply);
    }

    @Override
    public int deleteReiTrafficApplyById(String id) {
        return facReimburseApplyMapper.deleteReiTrafficApplyById(id);
    }

    @Override
    public int insertSaveFacReimburseApply(FacReimburseApply facReimburseApply) {
        facReimburseApply.setSubmitStatus("save");
        return facReimburseApplyMapper.insertFacReimburseApply(facReimburseApply);
    }

    @Override
    public int deleteFacReimburseApplyById(String id) {
        return facReimburseApplyMapper.deleteFacReimburseApplyById(id);
    }
}
