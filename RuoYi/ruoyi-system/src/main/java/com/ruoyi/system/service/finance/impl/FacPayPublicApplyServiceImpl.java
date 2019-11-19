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
import com.ruoyi.system.mapper.finance.FacUserApprovalMapper;
import com.ruoyi.system.domain.finance.FacUserApproval;

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

    @Autowired
    private FacUserApprovalMapper facUserApprovalMapper;
    /**
     * 查询对公申请信息
     *
     * @param id 对公申请ID
     * @return 对公申请信息
     */
    @Override
    public FacPayPublicApply selectFacPayPublicApplyById(Integer id) {
        return facPayPublicApplyMapper.selectFacPayPublicApplyById(id);
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
    @Override
    public int insertFacPayPublicApply(FacPayPublicApply facPayPublicApply) {

        return facPayPublicApplyMapper.insertFacPayPublicApply(facPayPublicApply);
    }

    /**
     * 修改对公申请
     *
     * @param facPayPublicApply 对公申请信息
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
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFacPayPublicApplyByIds(String ids) {
        FacPayPublicApply facPayPublicApply = facPayPublicApplyMapper.selectFacPayPublicApplyById(Integer.valueOf(ids));
        FacUserApproval facUserApproval= new FacUserApproval();
        facUserApproval.setApplyId(facPayPublicApply.getNum());
        List<FacUserApproval> list =facUserApprovalMapper.selectFacUserApprovalList(facUserApproval);
        if(list!=null&&list.size()>0){
            for(FacUserApproval v :list){
                facUserApprovalMapper.deleteFacUserApprovalById(v.getApprovalId());
            }
        }

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
     * @param facPayPublicDetailed 对公明细信息
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
     * @param ids 需要删除的数据ID
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
     * @param id 对公明细ID
     * @return 对公明细信息
     */
    @Override
    public FacPayPublicDetailed selectFacPayPublicDetailedById(Long id) {
        return facPayPublicDetailedMapper.selectFacPayPublicDetailedById(id);
    }

    @Override
    public int insertApply(FacPayPublicApply facPayPublicApply) {
        facPayPublicApply.setStatus("5");
        return facPayPublicApplyMapper.insertFacPayPublicApply(facPayPublicApply);
    }

}
