package com.ruoyi.system.service;

import com.ruoyi.system.domain.OaDingdingUser;
import java.util.List;

/**
 * 钉钉用户 服务层
 * 
 * @author ruoyi
 * @date 2019-07-26
 */
public interface IOaDingdingUserService 
{
	/**
     * 查询钉钉用户信息
     * 
     * @param userId 钉钉用户ID
     * @return 钉钉用户信息
     */
	public OaDingdingUser selectOaDingdingUserById(Integer userId);
	
	/**
     * 查询钉钉用户列表
     * 
     * @param oaDingdingUser 钉钉用户信息
     * @return 钉钉用户集合
     */
	public List<OaDingdingUser> selectOaDingdingUserList(OaDingdingUser oaDingdingUser);
	
	/**
     * 新增钉钉用户
     * 
     * @param oaDingdingUser 钉钉用户信息
     * @return 结果
     */
	public int insertOaDingdingUser(OaDingdingUser oaDingdingUser);
	
	/**
     * 修改钉钉用户
     * 
     * @param oaDingdingUser 钉钉用户信息
     * @return 结果
     */
	public int updateOaDingdingUser(OaDingdingUser oaDingdingUser);
		
	/**
     * 删除钉钉用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOaDingdingUserByIds(String ids);
	
	
	/**
	 * 批量插入钉钉用户
	 * @param userList
	 * @return
	 */
	public int insertForeach(List<OaDingdingUser> userList);
}
