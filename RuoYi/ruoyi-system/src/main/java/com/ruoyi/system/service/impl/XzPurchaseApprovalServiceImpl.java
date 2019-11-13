package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.XzPurchaseApprovalMapper;
import com.ruoyi.system.mapper.XzPurchaseResourceApplyMapper;
import com.ruoyi.system.domain.XzPurchaseApproval;
import com.ruoyi.system.domain.XzPurchaseResourceApply;
import com.ruoyi.system.service.IXzPurchaseApprovalService;
import com.ruoyi.common.core.text.Convert;

/**
 * 采购申请审批 服务层实现
 * 
 * @author ruoyi
 * @date 2019-09-24
 */
@Service
public class XzPurchaseApprovalServiceImpl implements IXzPurchaseApprovalService 
{
	@Autowired
	private XzPurchaseApprovalMapper xzPurchaseApprovalMapper;
	
	@Autowired
	private XzPurchaseResourceApplyMapper xzPurchaseResourceApplyMapper;

	/**
     * 查询采购申请审批信息
     * 
     * @param id 采购申请审批ID
     * @return 采购申请审批信息
     */
    @Override
	public XzPurchaseApproval selectXzPurchaseApprovalById(Long id)
	{
	    return xzPurchaseApprovalMapper.selectXzPurchaseApprovalById(id);
	}
	
	/**
     * 查询采购申请审批列表
     * 
     * @param xzPurchaseApproval 采购申请审批信息
     * @return 采购申请审批集合
     */
	@Override
	public List<XzPurchaseApproval> selectXzPurchaseApprovalList(XzPurchaseApproval xzPurchaseApproval)
	{
	    return xzPurchaseApprovalMapper.selectXzPurchaseApprovalList(xzPurchaseApproval);
	}
	
    /**
     * 新增采购申请审批
     * 
     * @param xzPurchaseApproval 采购申请审批信息
     * @return 结果
     */
	@Override
	@Transactional
	public int insertXzPurchaseApproval(XzPurchaseApproval xzPurchaseApproval)
	{
		try {
			XzPurchaseResourceApply x=new XzPurchaseResourceApply();
			x.setId(xzPurchaseApproval.getApplyId());
			//审批状态 1同意 2驳回
			x.setApprovalStatus(xzPurchaseApproval.getApprovalState());
			x.setApprovalTime(new Date());
			xzPurchaseResourceApplyMapper.updateXzPurchaseResourceApply(x);
			
			//新增一条审批记录
		    return xzPurchaseApprovalMapper.insertXzPurchaseApproval(xzPurchaseApproval);
		} catch (Exception e) {
			return 0;
		}
		
	}
	
	/**
     * 修改采购申请审批
     * 
     * @param xzPurchaseApproval 采购申请审批信息
     * @return 结果
     */
	@Override
	public int updateXzPurchaseApproval(XzPurchaseApproval xzPurchaseApproval)
	{
	    return xzPurchaseApprovalMapper.updateXzPurchaseApproval(xzPurchaseApproval);
	}

	/**
     * 删除采购申请审批对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteXzPurchaseApprovalByIds(String ids)
	{
		return xzPurchaseApprovalMapper.deleteXzPurchaseApprovalByIds(Convert.toStrArray(ids));
	}
	
}
