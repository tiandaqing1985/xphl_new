package com.ruoyi.system.service;

import com.ruoyi.system.domain.XzPurchaseResourceApply;
import java.util.List;

/**
 * 采购资源申请 服务层
 * 
 * @author ruoyi
 * @date 2019-09-17
 */
public interface IXzPurchaseResourceApplyService 
{
	/**
     * 查询采购资源申请信息
     * 
     * @param id 采购资源申请ID
     * @return 采购资源申请信息
     */
	public XzPurchaseResourceApply selectXzPurchaseResourceApplyById(Long id);
	
	/**
     * 查询采购资源申请列表
     * 
     * @param xzPurchaseResourceApply 采购资源申请信息
     * @return 采购资源申请集合
     */
	public List<XzPurchaseResourceApply> selectXzPurchaseResourceApplyList(XzPurchaseResourceApply xzPurchaseResourceApply);
	
	/**
     * 新增采购资源申请
     * 
     * @param xzPurchaseResourceApply 采购资源申请信息
     * @return 结果
     */
	public int insertXzPurchaseResourceApply(XzPurchaseResourceApply xzPurchaseResourceApply);
	
	/**
     * 修改采购资源申请
     * 
     * @param xzPurchaseResourceApply 采购资源申请信息
     * @return 结果
     */
	public int updateXzPurchaseResourceApply(XzPurchaseResourceApply xzPurchaseResourceApply);
		
	/**
     * 删除采购资源申请信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzPurchaseResourceApplyByIds(String ids);
	/**
	 * 查询物资类表详情
	 * @param code
	 * @return
	 */
	public XzPurchaseResourceApply detail(String code);

	/**
	 * 查询id
	 * @param code
	 * @return
	 */
	public XzPurchaseResourceApply selectXzPurchaseResourceApplyByCode(String code);
	
}
