package com.ruoyi.system.service.finance.impl;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.FacApplyType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.FinanceAddHelper;
import com.ruoyi.system.domain.finance.FacReimburseApply;
import com.ruoyi.system.mapper.finance.FacReimburseApplyMapper;
import com.ruoyi.system.service.finance.ApprovalProcessService;
import com.ruoyi.system.service.finance.IFacReimburseApplyService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 报销 服务层实现
 *
 * @author ruoyi
 * @date 2019-07-31
 */
@Service
public class FacReimburseApplyServiceImpl implements IFacReimburseApplyService {
    private Lock lock = new ReentrantLock();

    @Autowired
    private FacReimburseApplyMapper facReimburseApplyMapper;
    @Autowired
    ApprovalProcessService approvalProcessService;

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

    /**
     * 查询报销列表
     *
     * @param facReimburseApply 报销信息
     * @return 报销集合
     */
    @Override
    public List<FacReimburseApply> selectFacReimburseApplyList(FacReimburseApply facReimburseApply) {
        return facReimburseApplyMapper.selectFacReimburseApplyList(facReimburseApply);
    }

    /**
     * 新增报销
     *
     * @param facReimburseApply 报销信息
     * @return 结果
     */
    @Transactional
    @Override
    public AjaxResult insertFacReimburseApply(FacReimburseApply facReimburseApply) {
        lock.lock();
        try {
            FinanceAddHelper.set(facReimburseApply, FacApplyType.REIMBURSE);
            facReimburseApply.setLoanUser(new Long("1"));
            facReimburseApplyMapper.insertFacReimburseApply(facReimburseApply);
            if (("1").equals(facReimburseApply.getApplyStatus())) {
                //TODO 审批开始
                String s = approvalProcessService.initializeProcess(facReimburseApply);
                if (!("审批流程生成").equals(s)){
                    throw new BusinessException("审批流程异常");
                }
            }
            return AjaxResult.success("报销申请成功");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("添加报销申请" + e.getMessage());
            e.printStackTrace();
            return AjaxResult.error();
        } finally {
            lock.unlock();
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
        return facReimburseApplyMapper.updateFacReimburseApply(facReimburseApply);
    }

    /**
     * 删除报销对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFacReimburseApplyByIds(String ids) {
        return facReimburseApplyMapper.deleteFacReimburseApplyByIds(Convert.toStrArray(ids));
    }

}
