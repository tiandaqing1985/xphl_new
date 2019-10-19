package com.ruoyi.system.service.finance;

import com.ruoyi.system.domain.finance.FacPayPublicApply;
import com.ruoyi.system.domain.finance.FacReimburseApply;
import com.ruoyi.system.domain.finance.FacSysUserApproval;


/**
 * @program: ruoyi->ApprovalProcessService
 * @author: gakki
 * @create: 2019-07-31 14:31
 **/
public interface ApprovalProcessService {

    //初始化审批流
    public  String   initializeProcess(FacReimburseApply facReimburseApply);
    public  String   initializeProcessPublic(FacPayPublicApply facReimburseApply);

    public  String   initialize(Object o);


    void insert(FacSysUserApproval facSysUserApproval);
}
