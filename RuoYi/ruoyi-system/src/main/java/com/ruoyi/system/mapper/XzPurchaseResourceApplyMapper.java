package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.XzPurchaseResource;
import com.ruoyi.system.domain.XzPurchaseResourceApply;
import java.util.List;	

/**
 * 采购资源申请 数据层
 * 
 * @author ruoyi
 * @date 2019-09-17
 */
public interface XzPurchaseResourceApplyMapper 
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
     * 删除采购资源申请
     * 
     * @param id 采购资源申请ID
     * @return 结果
     */
	public int deleteXzPurchaseResourceApplyById(Long id);
	
	/**
     * 批量删除采购资源申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzPurchaseResourceApplyByIds(String[] ids);

	/**采购物资列表
	 * 
	 * @param code
	 * @return
	 */
	public List<XzPurchaseResource> perResDetail(String code);

	/**
	 * 查看采购列表
	 * @param code
	 * @return
	 */
	public XzPurchaseResourceApply detail(String code);

	/**
	 * 根据code删除采购列表信息
	 * @param code
	 * @return
	 */
	public int deleteXzPurchaseResourceByCode(String code);

	/**
	 * 根据code查询
	 * @param code
	 * @return
	 */
	public XzPurchaseResourceApply selectXzPurchaseResourceApplyByCode(String code);
	
}