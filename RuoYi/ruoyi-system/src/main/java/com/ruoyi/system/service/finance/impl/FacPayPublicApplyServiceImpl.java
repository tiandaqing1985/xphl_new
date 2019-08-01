package com.ruoyi.system.service.finance.impl;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.FacApplyType;
import com.ruoyi.common.utils.FinanceAddHelper;
import com.ruoyi.common.utils.ValidResult;
import com.ruoyi.common.utils.ValidationUtils;
import com.ruoyi.system.domain.finance.FacPayPublicApply;
import com.ruoyi.system.mapper.finance.FacPayPublicApplyMapper;
import com.ruoyi.system.service.finance.ApprovalProcessService;
import com.ruoyi.system.service.finance.IFacPayPublicApplyService;
import org.apache.ibatis.javassist.util.proxy.FactoryHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.xml.bind.ValidationException;

/**
 * 对公申请 服务层实现
 *
 * @author ruoyi
 * @date 2019-08-01
 */
@Service
public class FacPayPublicApplyServiceImpl implements IFacPayPublicApplyService {

    private Lock lock = new ReentrantLock();
    @Autowired
    private FacPayPublicApplyMapper facPayPublicApplyMapper;
    @Autowired
    ApprovalProcessService approvalProcessService;

    /**
     * 查询对公申请信息
     *
     * @param id 对公申请ID
     * @return 对公申请信息
     */
    @Override
    public FacPayPublicApply selectFacPayPublicApplyById(String num) {
        return facPayPublicApplyMapper.selectFacPayPublicApplyById(num);
    }

    /**
     * 查询对公申请列表
     *
     * @param facPayPublicApply 对公申请信息
     * @return 对公申请集合
     */
    @Override
    public List<FacPayPublicApply> selectFacPayPublicApplyList(FacPayPublicApply facPayPublicApply) {
        return facPayPublicApplyMapper.selectFacPayPublicApplyList(facPayPublicApply);
    }

    /**
     * 新增对公申请
     *
     * @param facPayPublicApply 对公申请信息
     * @return 结果
     */
    @Transactional
    @Override
    public AjaxResult insertFacPayPublicApply(FacPayPublicApply facPayPublicApply) {
        lock.lock();
        try {
            ValidResult validResult = ValidationUtils.validateBean(facPayPublicApply);
            if (validResult.hasErrors()) {
                throw new ValidationException(validResult.getErrors());
            }
            //开始审批
            FinanceAddHelper.set(facPayPublicApply, FacApplyType.PAY_PUBLIC);
            if ("提交审批".equals(facPayPublicApply.getWetherSave())) {
                approvalProcessService.initializeProcessPublic(facPayPublicApply);
                facPayPublicApply.setStatus("待审批");
            }
            facPayPublicApplyMapper.insertFacPayPublicApply(facPayPublicApply);
            return AjaxResult.success();
        } catch (ValidationException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("增加对公支付申请异常" + e.getMessage());
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("增加对公支付申请异常" + e.getMessage());
            e.printStackTrace();
            return AjaxResult.error("申请失败");
        } finally {
            lock.unlock();
        }

    }

    /**
     * 修改对公申请
     *
     * @param facPayPublicApply 对公申请信息
     * @return 结果
     */
    @Override
    public int updateFacPayPublicApply(FacPayPublicApply facPayPublicApply) {
        return facPayPublicApplyMapper.updateFacPayPublicApply(facPayPublicApply);
    }

    /**
     * 删除对公申请对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFacPayPublicApplyByIds(String ids) {
        return facPayPublicApplyMapper.deleteFacPayPublicApplyByIds(Convert.toStrArray(ids));
    }

}
