package com.ruoyi.system.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.XzExpenseRecordMapper;
import com.ruoyi.system.mapper.XzPurchaseResourceApplyMapper;
import com.ruoyi.system.mapper.XzPurchaseResourceMapper;
import com.ruoyi.system.domain.XzExpenseRecord;
import com.ruoyi.system.domain.XzPurchaseResource;
import com.ruoyi.system.domain.XzPurchaseResourceApply;
import com.ruoyi.system.service.IXzPurchaseResourceApplyService;
import com.ruoyi.common.core.text.Convert;

/**
 * 采购资源申请 服务层实现
 * 
 * @author ruoyi
 * @date 2019-09-17
 */
@Service
public class XzPurchaseResourceApplyServiceImpl implements IXzPurchaseResourceApplyService 
{
	@Autowired
	private XzPurchaseResourceApplyMapper xzPurchaseResourceApplyMapper;
	
	@Autowired
	private XzPurchaseResourceMapper xzPurchaseResourceMapper;
	
	@Autowired
	private XzExpenseRecordMapper xzExpenseRecordMapper;

	/**
     * 查询采购资源申请信息
     * 
     * @param id 采购资源申请ID
     * @return 采购资源申请信息
     */
    @Override
	public XzPurchaseResourceApply selectXzPurchaseResourceApplyById(Long id)
	{
	    return xzPurchaseResourceApplyMapper.selectXzPurchaseResourceApplyById(id);
	}
	
	/**
     * 查询采购资源申请列表
     * 
     * @param xzPurchaseResourceApply 采购资源申请信息
     * @return 采购资源申请集合
     */
	@Override
	public List<XzPurchaseResourceApply> selectXzPurchaseResourceApplyList(XzPurchaseResourceApply xzPurchaseResourceApply)
	{
	    return xzPurchaseResourceApplyMapper.selectXzPurchaseResourceApplyList(xzPurchaseResourceApply);
	}
	
    /**
     * 新增采购资源申请
     * 
     * @param xzPurchaseResourceApply 采购资源申请信息
     * @return 结果
     */
	@Override
	@Transactional
	public int insertXzPurchaseResourceApply(XzPurchaseResourceApply xzPurchaseResourceApply)
	{
		//修改：删除之前这条code数据
		xzPurchaseResourceApplyMapper.deleteXzPurchaseResourceByCode(xzPurchaseResourceApply.getCode());
		//新增数据
		XzPurchaseResourceApply xz =xzPurchaseResourceMapper.selectXzPurchaseResourceIdByCode(xzPurchaseResourceApply.getCode());
		xzPurchaseResourceApply.setApplyId(xz.getApplyId());
		xzPurchaseResourceApply.setApplyPrice(xz.getApplyPrice());
		//是否关联：1是  2否
		xzPurchaseResourceApply.setIsRelation("2");
		//审批状态：1同意 2驳回 3未审批
		xzPurchaseResourceApply.setApprovalStatus("3");
		//提交状态：1保存 2提交
	    return xzPurchaseResourceApplyMapper.insertXzPurchaseResourceApply(xzPurchaseResourceApply);
	}
	
	/**
     * 修改采购资源申请
     * 
     * @param xzPurchaseResourceApply 采购资源申请信息
     * @return 结果
     */
	@Override
	@Transactional
	public int updateXzPurchaseResourceApply(XzPurchaseResourceApply xzPurchaseResourceApply)
	{
		try {
			//查询关联详情添加到费用管理表中
			String applyId=xzPurchaseResourceApply.getApplyId().replace("，", ",");
			List <String> applyid=Arrays.asList(applyId.split(","));
			for (int sn = 0; sn < applyid.size(); sn++) {
				//查询详情
				XzPurchaseResource xzPurchaseResource=xzPurchaseResourceMapper.selectXzPurchaseResourceById(Long.parseLong(applyid.get(sn)));
				XzExpenseRecord xzExpenseRecord=new XzExpenseRecord();
				//添加一条记录
				xzExpenseRecord.setExpenseName(xzPurchaseResource.getResourceName());
				xzExpenseRecord.setExpenseTypeParent(xzPurchaseResource.getResourceParentId());
				xzExpenseRecord.setExpenseType(xzPurchaseResource.getResourceTypeId());
				xzExpenseRecord.setRegion(xzPurchaseResource.getRegion());
				xzExpenseRecord.setDevDesc(xzPurchaseResource.getResourceSpec());
				xzExpenseRecord.setPurchaseChannel(xzPurchaseResource.getPurchaseChannel());
				xzExpenseRecord.setUnitPrice(xzPurchaseResource.getResourcePrice());
				xzExpenseRecord.setCount(xzPurchaseResource.getResourceCount());
				xzExpenseRecord.setUseDate(xzPurchaseResource.getUpdateTime());
				xzExpenseRecord.setApplyUser(xzPurchaseResource.getCreateBy());
				xzExpenseRecord.setSubmitType("2");
				xzExpenseRecord.setSumPrice(xzPurchaseResource.getActualPrice());
				xzExpenseRecord.setComment(xzPurchaseResource.getRemark());
				xzExpenseRecordMapper.insertXzExpenseRecord(xzExpenseRecord);
				
			}
			//改为关联
			xzPurchaseResourceApply.setIsRelation("1");
		    return xzPurchaseResourceApplyMapper.updateXzPurchaseResourceApply(xzPurchaseResourceApply);
		} catch (Exception e) {
			return 0;
		}
		
		
	}

	/**
     * 删除采购资源申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteXzPurchaseResourceApplyByIds(String ids)
	{
		return xzPurchaseResourceApplyMapper.deleteXzPurchaseResourceApplyByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查看资源采购列表
	 */
	@Override
	public XzPurchaseResourceApply detail(String code) {
		
		XzPurchaseResourceApply xzPurchaseResourceApply=xzPurchaseResourceApplyMapper.detail(code);
		List<XzPurchaseResource> xzPurchaseResourceList =xzPurchaseResourceApplyMapper.perResDetail(code);
		if(xzPurchaseResourceList.size() > 0 && xzPurchaseResourceList != null){
			xzPurchaseResourceApply.setXzPurchaseResource(xzPurchaseResourceList);
		}
		return xzPurchaseResourceApply;
	}

	/**
	 * 根据code查询
	 */
	@Override
	public XzPurchaseResourceApply selectXzPurchaseResourceApplyByCode(String code) {
		return xzPurchaseResourceApplyMapper.selectXzPurchaseResourceApplyByCode(code);
	}

	
}
