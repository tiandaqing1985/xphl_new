package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OaDingdingUserMapper;
import com.ruoyi.system.domain.OaDingdingUser;
import com.ruoyi.system.service.IOaDingdingUserService;
import com.ruoyi.common.core.text.Convert;

/**
 * 钉钉用户 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-26
 */
@Service
public class OaDingdingUserServiceImpl implements IOaDingdingUserService 
{
	@Autowired
	private OaDingdingUserMapper oaDingdingUserMapper;

	/**
     * 查询钉钉用户信息
     * 
     * @param userId 钉钉用户ID
     * @return 钉钉用户信息
     */
    @Override
	public OaDingdingUser selectOaDingdingUserById(Integer userId)
	{
	    return oaDingdingUserMapper.selectOaDingdingUserById(userId);
	}
	
	/**
     * 查询钉钉用户列表
     * 
     * @param oaDingdingUser 钉钉用户信息
     * @return 钉钉用户集合
     */
	@Override
	public List<OaDingdingUser> selectOaDingdingUserList(OaDingdingUser oaDingdingUser)
	{
	    return oaDingdingUserMapper.selectOaDingdingUserList(oaDingdingUser);
	}
	
    /**
     * 新增钉钉用户
     * 
     * @param oaDingdingUser 钉钉用户信息
     * @return 结果
     */
	@Override
	public int insertOaDingdingUser(OaDingdingUser oaDingdingUser)
	{
	    return oaDingdingUserMapper.insertOaDingdingUser(oaDingdingUser);
	}
	
	/**
     * 修改钉钉用户
     * 
     * @param oaDingdingUser 钉钉用户信息
     * @return 结果
     */
	@Override
	public int updateOaDingdingUser(OaDingdingUser oaDingdingUser)
	{
	    return oaDingdingUserMapper.updateOaDingdingUser(oaDingdingUser);
	}

	/**
     * 删除钉钉用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOaDingdingUserByIds(String ids)
	{
		return oaDingdingUserMapper.deleteOaDingdingUserByIds(Convert.toStrArray(ids));
	}

	@Override
	public int insertForeach(List<OaDingdingUser> userList) {
		oaDingdingUserMapper.deleteOaDingdingUser();//删除钉钉用户表中所有数据
		return oaDingdingUserMapper.insertForeach(userList);
	}
	
}
