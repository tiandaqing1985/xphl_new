package com.ruoyi.system.service;

import com.ruoyi.system.domain.XzPersonalApply;
import java.util.List;

/**
 * 个人申请 服务层
 * 
 * @author ruoyi
 * @date 2019-08-27
 */
public interface IXzPersonalApplyService 
{
	/**
     * 查询个人申请信息
     * 
     * @param applyId 个人申请ID
     * @return 个人申请信息
     */
	public XzPersonalApply selectXzPersonalApplyById(Long applyId);
	
	/**
     * 查询个人申请列表
     * 
     * @param xzPersonalApply 个人申请信息
     * @return 个人申请集合
     */
	public List<XzPersonalApply> selectXzPersonalApplyList(XzPersonalApply xzPersonalApply);
	
	/**
     * 新增个人申请
     * 
     * @param xzPersonalApply 个人申请信息
     * @return 结果
     */
	public int insertXzPersonalApply(XzPersonalApply xzPersonalApply);
	
	/**
     * 修改个人申请
     * 
     * @param xzPersonalApply 个人申请信息
     * @return 结果
     */
	public int updateXzPersonalApply(XzPersonalApply xzPersonalApply);
		
	/**
     * 删除个人申请信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzPersonalApplyByIds(String ids);
	
}
