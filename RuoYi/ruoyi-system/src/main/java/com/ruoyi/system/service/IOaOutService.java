package com.ruoyi.system.service;

import com.ruoyi.system.domain.OaOut;
import com.ruoyi.system.domain.OutApproval;

import java.util.List;

/**
 * 外出报备 服务层
 * 
 * @author ruoyi
 * @date 2019-08-01
 */
public interface IOaOutService 
{
	/**
     * 查询外出报备信息
     * 
     * @param outId 外出报备ID
     * @return 外出报备信息
     */
	public OaOut selectOaOutById(Long outId);
	
	/**
	 * 查询我的外出报备信息
	 * @param oaOut
	 * @return 我的外出报备信息
	 */
	public List<OutApproval> selectMyOutApprovalList(OaOut oaOut);
	
	/**
	 *  查询外出报备审批结果
	 * @param oaOut
	 * @return 外出报备审批结果
	 */
	public List<OutApproval> selectOutApprovalList(OaOut oaOut);
	
	/**
     * 查询外出报备列表
     * 
     * @param oaOut 外出报备信息
     * @return 外出报备集合
     */
	public List<OaOut> selectOaOutList(OaOut oaOut);
	
	/**
     * 新增外出报备
     * 
     * @param oaOut 外出报备信息
     * @return 结果
     */
	public int insertOaOut(OaOut oaOut);
	
	/**
     * 审批修改外出报备
     * 
     * @param oaOut 外出报备信息
     * @return 结果
     */
	public int updateOaOut(OaOut oaOut,String remark);
	
	/**
	 * 编辑修改外出报备
	 * @param oaOut
	 * @return
	 */
	public int updateOaOut(OaOut oaOut);
	
	/**
	 * 删除外出报备信息
	 * @param ids
	 * @return
	 */
	public int deleteOaOutByIds(String ids);
	
	/**
	 * 撤回外出报备申请
	 * @param outId
	 * @return
	 */
	public int takeBack(Long outId);
	
	/**
	 * 验证是否能撤回外出报备申请
	 * @param outId
	 * @return
	 */
	public String ifTakeback(Long outId);
	
	/** 验证申请时间是否已经提交
	 * @param oaOut
	 * @return 
	 */
	public String ifRepeat(OaOut oaOut);
	
}
