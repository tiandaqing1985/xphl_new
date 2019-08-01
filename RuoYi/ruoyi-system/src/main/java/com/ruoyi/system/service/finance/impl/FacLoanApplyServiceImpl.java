package com.ruoyi.system.service.finance.impl;


import com.ruoyi.common.core.domain.AjaxResult;



import com.ruoyi.system.domain.finance.FacLoanApply;
import com.ruoyi.system.mapper.finance.FacLoanApplyMapper;
import com.ruoyi.system.service.finance.IFacLoanApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 借款申请 服务层实现
 *
 * @author ruoyi
 * @date 2019-07-30
 */
@Service
public class FacLoanApplyServiceImpl implements IFacLoanApplyService {

    private Lock lock = new ReentrantLock();


    @Autowired
    private FacLoanApplyMapper facLoanApplyMapper;

    /**
     * 查询借款申请信息
     *
     * @param id 借款申请ID
     * @return 借款申请信息
     */
    @Override
    public FacLoanApply selectFacLoanApplyById(String id) {
        return facLoanApplyMapper.selectFacLoanApplyById(id);
    }

    /**
     * 查询借款申请列表
     *
     * @param facLoanApply 借款申请信息
     * @return 借款申请集合
     */
    @Override
    public List<FacLoanApply> selectFacLoanApplyList(FacLoanApply facLoanApply) {
        return facLoanApplyMapper.selectFacLoanApplyList(facLoanApply);
    }

    /**
     * 新增借款申请
     *
     * @param facLoanApply 借款申请信息
     * @return 结果
     */
    @Transactional
    @Override
    public AjaxResult insertFacLoanApply(FacLoanApply facLoanApply) {
		lock.lock();
        try {
            int i = facLoanApplyMapper.insertFacLoanApply(facLoanApply);
            if (("").equals(facLoanApply.getApplyStatus())){
                //TODO  同步查询表
                //TODO  初始化审批流程
                return AjaxResult.success();
            }else {
                return AjaxResult.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();

        }
        return  null;

    }
    /**
     * 修改借款申请
     *
     * @param facLoanApply 借款申请信息
     * @return 结果
     */
    @Override
    public int updateFacLoanApply(FacLoanApply facLoanApply) {
        return facLoanApplyMapper.updateFacLoanApply(facLoanApply);
    }

    /**
     * 删除借款申请对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFacLoanApplyByIds(String ids) {
        return facLoanApplyMapper.deleteFacLoanApplyByIds(Convert.toStrArray(ids));
    }

}
