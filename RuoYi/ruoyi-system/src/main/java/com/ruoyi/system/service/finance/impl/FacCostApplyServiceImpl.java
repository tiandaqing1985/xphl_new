package com.ruoyi.system.service.finance.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.finance.FacCostApply;
import com.ruoyi.system.domain.finance.FacCostDetailApply;
import com.ruoyi.system.domain.finance.FacCostPutupApply;
import com.ruoyi.system.domain.finance.FacSysUserApproval;
import com.ruoyi.system.mapper.finance.ApprovalProcessMapper;
import com.ruoyi.system.mapper.finance.FacCostApplyMapper;
import com.ruoyi.system.mapper.finance.FacCostPutupApplyMapper;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.IFacCostApplyService;

/**
 * 差旅申请 服务层实现
 * 
 * @author ruoyi
 * @date 2019-09-02
 */
@Service
public class FacCostApplyServiceImpl implements IFacCostApplyService {
	@Autowired
	private FacCostApplyMapper facCostApplyMapper;
	@Autowired
	private ApprovalProcessMapper approvalProcessMapper;
	@Autowired
	private ISysUserService iSysUserService;
	@Autowired
	private FacCostPutupApplyMapper facCostPutupApplyMapper; 
	/**
	 * 查询差旅申请信息
	 * 
	 * @param id
	 *            差旅申请ID
	 * @return 差旅申请信息
	 */
	@Override
	public FacCostApply selectFacCostApplyById(Long id) {
		return facCostApplyMapper.selectFacCostApplyById(id);
	}

	/**
	 * 查询差旅申请列表
	 * 
	 * @param facCostApply
	 *            差旅申请信息
	 * @return 差旅申请集合
	 */
	@Override
	public List<FacCostApply> selectFacCostApplyList(
			FacCostApply facCostApply) { 
		List<FacCostApply> list =  facCostApplyMapper.selectFacCostApplyList(facCostApply);
		return list;
	}

	/**
	 * 新增差旅申请
	 * 
	 * @param facCostApply
	 *            差旅申请信息
	 * @return 结果
	 */
	@Override
	public int insertFacCostApply(FacCostApply facCostApply) { 
		
		double a=facCostPutupApplyMapper.selectMoney(facCostApply.getNum());
	 	double b=facCostApplyMapper.selectAmount(facCostApply.getNum());  
	 	facCostApply.setMoneyEs(a);  
		facCostApply.setStatus("3");
		FacSysUserApproval facSysUserApproval = new FacSysUserApproval();
		facSysUserApproval.setApprovalId(facCostApply.getUserId());
		facSysUserApproval.setApprovalTime(new Date());
		facSysUserApproval.setApprovalLevel(1);
		facSysUserApproval.setApplicantId(facCostApply.getUserId()); 
		facSysUserApproval.setApprovalState("3");
		facSysUserApproval.setApprovalSight("1");
		Long leaderId = iSysUserService.selectApproverIdByApplyerId(facCostApply.getUserId());//所在部门负责人id
		Long upLeaderId =iSysUserService.selectUpApproverIdByApplyerId(facCostApply.getUserId());//所在部门负责人的上级leader
		Long approvalId = 0L;//部门负责人id  审批人
		if(facCostApply.getUserId().equals(leaderId)){	//判断用户是否部门负责人  确定一、二级审批人id
			facSysUserApproval.setApproverId(upLeaderId); //一级审批人id	
			approvalId = upLeaderId; 
		} 
		else{
			facSysUserApproval.setApproverId(leaderId);
			approvalId = leaderId;
		} 
		approvalProcessMapper.insert(facSysUserApproval); //插入一级审批记录 
		if(facCostApply.getUserId()==103||facCostApply.getUserId()==101){
			facCostApply.setStatus("1");
			return facCostApplyMapper.insertFacCostApply(facCostApply);
		}
		Long approverId2 = iSysUserService.selectUpApproverIdByApplyerId(facSysUserApproval.getApproverId()); //审批人id的上级id
		int level = 1;  
		LinkedList<Long> centerId = (LinkedList<Long>)iSysUserService.selectCenterIdByUserId(approvalId);
		if(centerId!=null && centerId.size()>0){
			centerId.remove(approverId2); 
			for(int i=centerId.size()-1;i>=0;i--){
				FacSysUserApproval center = new FacSysUserApproval();//中心负责人  
				center.setApproverId(centerId.get(i));// 审批人ID
				center.setApprovalLevel(++level);// 审批等级
				center.setApplyId(facSysUserApproval.getApplyId()); // 申请人ID
				center.setApprovalState("3");
				center.setApprovalSight("1");//可见性 
				center.setCreateTime(new Date());//创建时间
				if(center.getApproverId()==103){ //如果是审批人是 coo 直接结束 
					return facCostApplyMapper.insertFacCostApply(facCostApply);
				}
				approvalProcessMapper.insert(center);
			}   
		}
		return facCostApplyMapper.insertFacCostApply(facCostApply);
	}

	/**
	 * 修改差旅申请
	 * 
	 * @param facCostApply
	 *            差旅申请信息
	 * @return 结果
	 */
	@Override
	public int updateFacCostApply(FacCostApply facCostApply) {
		return facCostApplyMapper.updateFacCostApply(facCostApply);
	}

	/**
	 * 删除差旅申请对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteFacCostApplyByIds(String ids) {
		return facCostApplyMapper
				.deleteFacCostApplyByIds(Convert.toStrArray(ids));
	}
	
	
	/**
	 * 差旅行程详细信息
	 * */

	@Override
	public FacCostApply deatil(String num) { 
		FacCostApply facCostApply = facCostApplyMapper.detail(num);
			
//			List<FacCostDetailApply> facCostDetailApplyList = facCostApplyMapper.adiTail(num);
//			if (facCostDetailApplyList.size() > 0 && facCostDetailApplyList != null) {
//				facCostApply.setFacCostDetail(facCostDetailApplyList); 
//			}  
		return facCostApply;
	}
	
	
	@Override
	public List<FacCostDetailApply> deatils(String num) { 
		List<FacCostDetailApply> facCostDetailApplyList = facCostApplyMapper.adiTail(num);
			if (facCostDetailApplyList.size() > 0 && facCostDetailApplyList != null) {
				return facCostDetailApplyList; 
			}  else{
				 List<FacCostDetailApply> a = new ArrayList<>();
				return a;
			}
		
	}
 

	@Override
	public int insertFacCostDetailApply(FacCostDetailApply facCostDetailApply) {
		 
		return facCostApplyMapper.insertFacCostDetailApply(facCostDetailApply);
		 
	}

	@Override
	public int insertFacCostPutupApply(FacCostPutupApply facCostPutupApply) {
		// TODO Auto-generated method stub
		return facCostPutupApplyMapper.insertFacCostPutupApply(facCostPutupApply);
	}
	
	
}
