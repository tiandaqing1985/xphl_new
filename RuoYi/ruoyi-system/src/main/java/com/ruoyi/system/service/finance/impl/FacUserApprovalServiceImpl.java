package com.ruoyi.system.service.finance.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.ruoyi.common.core.text.Convert; 
import com.ruoyi.system.domain.finance.FacUserApproval;
import com.ruoyi.system.mapper.finance.FacUserApprovalMapper;
import com.ruoyi.system.service.finance.IFacUserApprovalService;

/**
 * 财务审批 服务层实现
 * 
 * @author ruoyi
 * @date 2019-09-27
 */
@Service
public class FacUserApprovalServiceImpl implements IFacUserApprovalService 
{
	@Autowired
	private FacUserApprovalMapper facUserApprovalMapper;

	/**
     * 查询财务审批信息
     * 
     * @param approvalId 财务审批ID
     * @return 财务审批信息
     */
    @Override
	public FacUserApproval selectFacUserApprovalById(Long approvalId)
	{
	    return facUserApprovalMapper.selectFacUserApprovalById(approvalId);
	}
	
	/**
     * 查询财务审批列表
     * 
     * @param facUserApproval 财务审批信息
     * @return 财务审批集合
     */
	@Override
	public List<FacUserApproval> selectFacUserApprovalList(FacUserApproval facUserApproval)
	{
	    return facUserApprovalMapper.selectFacUserApprovalList(facUserApproval);
	}
	
    /**
     * 新增财务审批
     * 
     * @param facUserApproval 财务审批信息
     * @return 结果
     */
	@Override
	public int insertFacUserApproval(FacUserApproval facUserApproval)
	{
	    return facUserApprovalMapper.insertFacUserApproval(facUserApproval);
	}
	
	/**
     * 修改财务审批
     * 
     * @param facUserApproval 财务审批信息
     * @return 结果
     */
	@Override
	public int updateFacUserApproval(FacUserApproval facUserApproval)
	{
	    return facUserApprovalMapper.updateFacUserApproval(facUserApproval);
	}

	/**
     * 删除财务审批对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFacUserApprovalByIds(String ids)
	{
		return facUserApprovalMapper.deleteFacUserApprovalByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<FacUserApproval> selectEndFacUserApprovalList(
			FacUserApproval facUserApproval) {
		  return facUserApprovalMapper.selectEndFacUserApprovalList(facUserApproval);
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
	
}
