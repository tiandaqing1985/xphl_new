package com.ruoyi.system.service.finance.impl;
import java.util.Date;
import java.util.List;

import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.finance.FacReimburseApply;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.finance.IFacReimburseApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.finance.FacUserApproval;
import com.ruoyi.system.mapper.finance.FacUserApprovalMapper;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.IFacUserApprovalService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 财务审批 服务层实现
 * 
 * @author ruoyi
 * @date 2019-09-27
 */
@Service
public class FacUserApprovalServiceImpl implements IFacUserApprovalService {
	@Autowired
	private FacUserApprovalMapper facUserApprovalMapper;
	@Autowired
	private IFacReimburseApplyService facReimburseApplyService;

	@Autowired
	ISysUserService iSysUserService;
	@Autowired
	ISysDeptService sysDeptService;
	/**
	 * 查询财务审批信息
	 * 
	 * @param approvalId
	 *            财务审批ID
	 * @return 财务审批信息
	 */
	@Override
	public FacUserApproval selectFacUserApprovalById(Long approvalId) {
		return facUserApprovalMapper.selectFacUserApprovalById(approvalId);
	}

	/**
	 * 查询财务审批列表
	 * 
	 * @param facUserApproval
	 *            财务审批信息
	 * @return 财务审批集合
	 */
	@Override
	public List<FacUserApproval> selectFacUserApprovalList(
			FacUserApproval facUserApproval) {
		return facUserApprovalMapper.selectFacUserApprovalList(facUserApproval);
	}

	/**
	 * 新增财务审批
	 * 
	 * @param facUserApproval
	 *            财务审批信息
	 * @return 结果
	 */
	@Override
	public int insertFacUserApproval(FacUserApproval facUserApproval) {
		return facUserApprovalMapper.insertFacUserApproval(facUserApproval);
	}

	/**
	 * 修改财务审批
	 * 
	 * @param facUserApproval
	 *            财务审批信息
	 * @return 结果
	 */
	@Override
	public int updateFacUserApproval(FacUserApproval facUserApproval) {

        FacReimburseApply facReimburseApply = new FacReimburseApply();
        facReimburseApply.setNum(facUserApproval.getApplyId());
		//更新当前审批步骤中的状态
		facUserApproval.setApprovalTime(new Date());
		facUserApprovalMapper.updateFacUserApproval(facUserApproval);
        //若是审批通过
        if(facUserApproval.getApprovalState().equals("1")){
            //审批状态为审批中
            facReimburseApply.setStatus("3");
            //查询下一个审批步骤
			FacUserApproval nextFac = new FacUserApproval();
			nextFac.setApplyId(facUserApproval.getApplyId());
			nextFac.setApprovalLevel(facUserApproval.getApprovalLevel()+1);
			List<FacUserApproval> approvals = facUserApprovalMapper.selectFacUserApprovalList(nextFac);
			//若无下一步则审批成功，否则审批中
			if(approvals.size()==0){
				facReimburseApply.setStatus("1");
			}else{
				FacUserApproval next = approvals.get(0);
				next.setApprovalSight("1");
				facUserApprovalMapper.updateFacUserApproval(next);
			}
        }else{
            //审批状态为失败
			facReimburseApply.setStatus("2");
        }

        //将当前审批状态改为审批中
		facReimburseApplyService.updateFacReimburseApplyByNum(facReimburseApply);


//		SysUser sysUser = iSysUserService.selectUserById(facUserApproval.getApproverId());
//		SysDept sysDept = sysDeptService.selectDeptById(sysUser.getDeptId());
//		if(sysDept.getParentId()==0){
//			return 1;
//		}
//		Long leaderId = null;
//		if(sysDept.getLeader().equals(sysUser.getUserName())){
//			sysDept = sysDeptService.selectDeptById(sysDept.getParentId());
//			SysUser selectVO = new SysUser();
//			selectVO.setUserName(sysDept.getLeader());
//			selectVO.setDeptId(sysDept.getDeptId());
//			List<SysUser> sysUsers = iSysUserService.selectUserList(selectVO);
//			leaderId = sysUsers.get(0).getUserId();
//		}
//
//		FacUserApproval fac =new  FacUserApproval();
//
//		fac.setApproverId(leaderId);
//		fac.setApplyId(facUserApproval.getApplyId());
//
//		FacUserApproval facUserApproval2 = facUserApprovalMapper
//				.selectEndFacUserApprovalList(fac);
//        if(facUserApproval2==null){
//			facReimburseApply.setStatus("1");
//			facReimburseApplyService.updateFacReimburseApplyByNum(facReimburseApply);
//			return 1;
//		}
//		facUserApproval2.setApprovalSight("1");

		return 1;
	}

	/**
	 * 删除财务审批对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteFacUserApprovalByIds(String ids) {
		return facUserApprovalMapper
				.deleteFacUserApprovalByIds(Convert.toStrArray(ids));
	}
 

	@Override
	public List<FacUserApproval> selectApplicantIdList(
			FacUserApproval facUserApproval) {
		return facUserApprovalMapper.selectApplicantIdList(facUserApproval);
	}

	@Override
	public List<FacUserApproval> selectApproverIdList(
			FacUserApproval facUserApproval) {
		return facUserApprovalMapper.selectApproverIdList(facUserApproval);
	}

	@Override
	public List<FacUserApproval> selectEndFacUserApprovalList(
			FacUserApproval facUserApproval) {
		// TODO Auto-generated method stub
		return null;
	}

	 
}
