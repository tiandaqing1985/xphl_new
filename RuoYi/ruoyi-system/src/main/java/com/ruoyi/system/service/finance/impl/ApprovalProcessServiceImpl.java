package com.ruoyi.system.service.finance.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.domain.finance.FacPayPublicApply;
import com.ruoyi.system.domain.finance.FacReimburseApply;
import com.ruoyi.system.domain.finance.FacSysUserApproval;
import com.ruoyi.system.mapper.finance.ApprovalProcessMapper;
import com.ruoyi.system.service.finance.ApprovalProcessService;


/**
 * @program: ruoyi->ApprovalProcessServiceImpl
 * @author: gakki
 * @create: 2019-07-31 14:48
 **/
@Service
public class ApprovalProcessServiceImpl implements ApprovalProcessService {

    @Autowired
    ApprovalProcessMapper approvalProcessMapper;


    @Transactional
    @Override
    public String initializeProcessPublic(FacPayPublicApply facReimburseApply) {
		return null;
    	
    	/***

        //财务，人事 << 2000 审批流
        //查询2级部门leader id
        try {
            //判断用户角色
            String roleName="";
            List<String> queryRoleName = approvalProcessMapper.queryRoleName(facReimburseApply.getUser());
            for(String name:queryRoleName){
                roleName = roleName+","+name;
            }
            //判断是否是coo
            if (roleName.contains("COO")) {
                FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
                facSysUserApproval.setApprovalLevel(1);
                facSysUserApproval.setApprovalSight("0");
                facSysUserApproval.setApproverId(facReimburseApply.getUser());
                facReimburseApply.setStatus("");
                approvalProcessMapper.insert(facSysUserApproval);
            }

            if (roleName.contains("员工")) {
                DeptUser sysDept = approvalProcessMapper.querySecondLevelDept(facReimburseApply.getUser());
                if ((sysDept.getDeptName().contains("财务") || sysDept.getDeptName().contains("人力资源")) && ((new BigDecimal("2000").compareTo(facReimburseApply.getAmount()) < 1))) {
                    FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
                    facSysUserApproval.setApplyId(facReimburseApply.getNum());
                    facSysUserApproval.setApprovalLevel(1);
                    facSysUserApproval.setApprovalSight("0");
                    facSysUserApproval.setApprovalId(sysDept.getUserId());
                    approvalProcessMapper.insert(facSysUserApproval);
                    //查询财务,人事 一级部门主管
                } else if ((sysDept.getDeptName().contains("财务") || sysDept.getDeptName().contains("人力资源"))
                        && ((new BigDecimal("2000").compareTo(facReimburseApply.getAmount()) == 1)) && (new BigDecimal("10000").compareTo(facReimburseApply.getAmount()) != 1)) {
                    FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
                    facSysUserApproval.setApplyId(facReimburseApply.getNum());
                    facSysUserApproval.setApprovalLevel(1);
                    facSysUserApproval.setApprovalSight("0");
                    facSysUserApproval.setApprovalId(sysDept.getUserId());
                    approvalProcessMapper.insert(facSysUserApproval);
                    DeptUser sysDept1 = approvalProcessMapper.querySecondLevelDept(sysDept.getDeptId());
                    FacSysUserApproval facSysUserApproval1 = new FacSysUserApproval();
                    facSysUserApproval1.setApplyId(facReimburseApply.getNum());
                    facSysUserApproval1.setApprovalLevel(2);
                    facSysUserApproval1.setApprovalSight("0");
                    facSysUserApproval1.setApprovalId(sysDept1.getUserId());
                    approvalProcessMapper.insert(facSysUserApproval1);
                    //查询财务，人事
                } else if ((sysDept.getDeptName().contains("财务") || sysDept.getDeptName().contains("人力资源"))
                        && ((new BigDecimal("10000").compareTo(facReimburseApply.getAmount()) == -1))) {
                    FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
                    facSysUserApproval.setApplyId(facReimburseApply.getNum());
                    facSysUserApproval.setApprovalLevel(1);
                    facSysUserApproval.setApprovalSight("0");
                    facSysUserApproval.setApprovalId(sysDept.getUserId());
                    approvalProcessMapper.insert(facSysUserApproval);
                    DeptUser sysDept1 = approvalProcessMapper.querySecondLevelDept(sysDept.getDeptId());
                    FacSysUserApproval facSysUserApproval1 = new FacSysUserApproval();
                    facSysUserApproval1.setApplyId(facReimburseApply.getNum());
                    facSysUserApproval1.setApprovalLevel(2);
                    facSysUserApproval1.setApprovalSight("0");
                    facSysUserApproval1.setApprovalId(sysDept1.getUserId());
                    approvalProcessMapper.insert(facSysUserApproval1);
                    DeptUser sysDept2 = approvalProcessMapper.querySecondLevelDept(sysDept1.getDeptId());
                    FacSysUserApproval facSysUserApproval2 = new FacSysUserApproval();
                    facSysUserApproval2.setApplyId(facReimburseApply.getNum());
                    facSysUserApproval2.setApprovalLevel(3);
                    facSysUserApproval2.setApprovalSight("0");
                    facSysUserApproval2.setApprovalId(sysDept2.getUserId());
                    approvalProcessMapper.insert(facSysUserApproval2);
                }
                //行政部
                //小于10000
                if ((sysDept.getDeptName().contains("财务") && ((new BigDecimal("10000").compareTo(facReimburseApply.getAmount()) != 1)))) {
                    FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
                    facSysUserApproval.setApplyId(facReimburseApply.getNum());
                    facSysUserApproval.setApprovalLevel(1);
                    facSysUserApproval.setApprovalSight("0");
                    facSysUserApproval.setApprovalId(sysDept.getUserId());
                    approvalProcessMapper.insert(facSysUserApproval);
                    DeptUser sysDept1 = approvalProcessMapper.querySecondLevelDept(sysDept.getUserId());
                    FacSysUserApproval facSysUserApproval1 = new FacSysUserApproval();
                    facSysUserApproval1.setApplyId(facReimburseApply.getNum());
                    facSysUserApproval1.setApprovalLevel(2);
                    facSysUserApproval1.setApprovalSight("0");
                    facSysUserApproval1.setApprovalId(sysDept1.getUserId());
                    approvalProcessMapper.insert(facSysUserApproval1);
                } else if ((sysDept.getDeptName().contains("财务") && ((new BigDecimal("10000").compareTo(facReimburseApply.getAmount()) == -1)))) {
                    FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
                    facSysUserApproval.setApplyId(facReimburseApply.getNum());
                    facSysUserApproval.setApprovalLevel(1);
                    facSysUserApproval.setApprovalSight("0");
                    facSysUserApproval.setApprovalId(sysDept.getUserId());
                    approvalProcessMapper.insert(facSysUserApproval);
                    DeptUser sysDept1 = approvalProcessMapper.querySecondLevelDept(sysDept.getUserId());
                    FacSysUserApproval facSysUserApproval1 = new FacSysUserApproval();
                    facSysUserApproval1.setApplyId(facReimburseApply.getNum());
                    facSysUserApproval1.setApprovalLevel(2);
                    facSysUserApproval1.setApprovalSight("0");
                    facSysUserApproval1.setApprovalId(sysDept1.getDeptId());
                    approvalProcessMapper.insert(facSysUserApproval1);
                    DeptUser sysDept2 = approvalProcessMapper.querySecondLevelDept(sysDept1.getUserId());
                    FacSysUserApproval facSysUserApproval2 = new FacSysUserApproval();
                    facSysUserApproval2.setApplyId(facReimburseApply.getNum());
                    facSysUserApproval2.setApprovalLevel(3);
                    facSysUserApproval2.setApprovalSight("0");
                    facSysUserApproval2.setApprovalId(sysDept1.getUserId());
                    approvalProcessMapper.insert(facSysUserApproval2);
                }
                FacSysUserApproval facSysUserApproval = new FacSysUserApproval();

                facSysUserApproval.setApplyId(facReimburseApply.getNum());
                facSysUserApproval.setApprovalLevel(1);
                facSysUserApproval.setApprovalSight("0");
                facSysUserApproval.setApproverId(sysDept.getUserId());
                FinanceAddHelper.set(facSysUserApproval, FacApplyType.PAY_PUBLIC);
                System.out.println("----------------------" + facSysUserApproval.toString());
                approvalProcessMapper.insert(facSysUserApproval);
                return "审批流程生成";
            } else if ("部门leader".equals(facReimburseApply.getUser())) {
                Long highLeader = approvalProcessMapper.queryHighDeptLeaderId(facReimburseApply.getUser());
                FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
                facSysUserApproval.setApprovalLevel(1);
                facSysUserApproval.setApprovalSight("0");
                facSysUserApproval.setApproverId(highLeader);
                approvalProcessMapper.insert(facSysUserApproval);
                return "审批流程生成";

            }
            return null;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println();
            e.printStackTrace();
            return "审批失败";
        }

    }

    @Override
    public String initialize(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        return null;


    }

    @Transactional
    @Override
    public String initializeProcess(FacReimburseApply facReimburseApply) {
        //财务，人事 << 2000 审批流
        //查询2级部门leader id
        try {
            DeptUser sysDept = approvalProcessMapper.querySecondLevelDept(Long.valueOf(facReimburseApply.getLoanUser()));
            if ((sysDept.getDeptName().contains("财务") || sysDept.getDeptName().contains("人力资源")) && ((new Double("2000").compareTo(facReimburseApply.getAmount()) < 1))) {
                FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
                facSysUserApproval.setApplyId(facReimburseApply.getNum());
                facSysUserApproval.setApprovalLevel(1);
                facSysUserApproval.setApprovalSight("0");
                facSysUserApproval.setApprovalId(sysDept.getUserId());
                approvalProcessMapper.insert(facSysUserApproval);
                //查询财务,人事 一级部门主管
            } else if ((sysDept.getDeptName().contains("财务") || sysDept.getDeptName().contains("人力资源"))
                    && ((new Double("2000").compareTo(facReimburseApply.getAmount()) == 1)) && (new Double("10000").compareTo(facReimburseApply.getAmount()) != 1)) {
                FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
                facSysUserApproval.setApplyId(facReimburseApply.getNum());
                facSysUserApproval.setApprovalLevel(1);
                facSysUserApproval.setApprovalSight("0");
                facSysUserApproval.setApprovalId(sysDept.getUserId());
                approvalProcessMapper.insert(facSysUserApproval);
                DeptUser sysDept1 = approvalProcessMapper.querySecondLevelDept(sysDept.getDeptId());
                FacSysUserApproval facSysUserApproval1 = new FacSysUserApproval();
                facSysUserApproval1.setApplyId(facReimburseApply.getNum());
                facSysUserApproval1.setApprovalLevel(2);
                facSysUserApproval1.setApprovalSight("0");
                facSysUserApproval1.setApprovalId(sysDept1.getUserId());
                approvalProcessMapper.insert(facSysUserApproval1);
                //查询财务，人事
            } else if ((sysDept.getDeptName().contains("财务") || sysDept.getDeptName().contains("人力资源"))
                    && ((new Double("10000").compareTo(facReimburseApply.getAmount()) == -1))) {
                FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
                facSysUserApproval.setApplyId(facReimburseApply.getNum());
                facSysUserApproval.setApprovalLevel(1);
                facSysUserApproval.setApprovalSight("0");
                facSysUserApproval.setApprovalId(sysDept.getUserId());
                approvalProcessMapper.insert(facSysUserApproval);
                DeptUser sysDept1 = approvalProcessMapper.querySecondLevelDept(sysDept.getDeptId());
                FacSysUserApproval facSysUserApproval1 = new FacSysUserApproval();
                facSysUserApproval1.setApplyId(facReimburseApply.getNum());
                facSysUserApproval1.setApprovalLevel(2);
                facSysUserApproval1.setApprovalSight("0");
                facSysUserApproval1.setApprovalId(sysDept1.getUserId());
                approvalProcessMapper.insert(facSysUserApproval1);
                DeptUser sysDept2 = approvalProcessMapper.querySecondLevelDept(sysDept1.getDeptId());
                FacSysUserApproval facSysUserApproval2 = new FacSysUserApproval();
                facSysUserApproval2.setApplyId(facReimburseApply.getNum());
                facSysUserApproval2.setApprovalLevel(3);
                facSysUserApproval2.setApprovalSight("0");
                facSysUserApproval2.setApprovalId(sysDept2.getUserId());
                approvalProcessMapper.insert(facSysUserApproval2);
            }
            //行政部
            //小于10000
            if ((sysDept.getDeptName().contains("财务") && ((new Double("10000").compareTo(facReimburseApply.getAmount()) != 1)))) {
                FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
                facSysUserApproval.setApplyId(facReimburseApply.getNum());
                facSysUserApproval.setApprovalLevel(1);
                facSysUserApproval.setApprovalSight("0");
                facSysUserApproval.setApprovalId(sysDept.getUserId());
                approvalProcessMapper.insert(facSysUserApproval);
                DeptUser sysDept1 = approvalProcessMapper.querySecondLevelDept(sysDept.getUserId());
                FacSysUserApproval facSysUserApproval1 = new FacSysUserApproval();
                facSysUserApproval1.setApplyId(facReimburseApply.getNum());
                facSysUserApproval1.setApprovalLevel(2);
                facSysUserApproval1.setApprovalSight("0");
                facSysUserApproval1.setApprovalId(sysDept1.getUserId());
                approvalProcessMapper.insert(facSysUserApproval1);
            } else if ((sysDept.getDeptName().contains("财务") && ((new Double("10000").compareTo(facReimburseApply.getAmount()) == -1)))) {
                FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
                facSysUserApproval.setApplyId(facReimburseApply.getNum());
                facSysUserApproval.setApprovalLevel(1);
                facSysUserApproval.setApprovalSight("0");
                facSysUserApproval.setApprovalId(sysDept.getUserId());
                approvalProcessMapper.insert(facSysUserApproval);
                DeptUser sysDept1 = approvalProcessMapper.querySecondLevelDept(sysDept.getUserId());
                FacSysUserApproval facSysUserApproval1 = new FacSysUserApproval();
                facSysUserApproval1.setApplyId(facReimburseApply.getNum());
                facSysUserApproval1.setApprovalLevel(2);
                facSysUserApproval1.setApprovalSight("0");
                facSysUserApproval1.setApprovalId(sysDept1.getDeptId());
                approvalProcessMapper.insert(facSysUserApproval1);
                DeptUser sysDept2 = approvalProcessMapper.querySecondLevelDept(sysDept1.getUserId());
                FacSysUserApproval facSysUserApproval2 = new FacSysUserApproval();
                facSysUserApproval2.setApplyId(facReimburseApply.getNum());
                facSysUserApproval2.setApprovalLevel(3);
                facSysUserApproval2.setApprovalSight("0");
                facSysUserApproval2.setApprovalId(sysDept1.getUserId());
                approvalProcessMapper.insert(facSysUserApproval2);
            }
            FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
            facSysUserApproval.setApplyId(facReimburseApply.getNum());
            facSysUserApproval.setApprovalLevel(1);
            facSysUserApproval.setApprovalSight("0");
            facSysUserApproval.setApprovalId(sysDept.getUserId());
            approvalProcessMapper.insert(facSysUserApproval);
            return "审批流程生成";
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "审批失败";
        }**/
    
    }


	@Override
	public String initializeProcess(FacReimburseApply facReimburseApply) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String initialize(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public void insert(FacSysUserApproval facSysUserApproval) {
        if(facSysUserApproval.getApprovalLevel()==1){
            facSysUserApproval.setApprovalSight("1");
        }else{
            facSysUserApproval.setApprovalSight("0");
        }
        approvalProcessMapper.insert(facSysUserApproval);
    }
}
