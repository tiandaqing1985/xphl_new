package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.Data;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.UserModel;

/**
 * 用户表 数据层
 * 
 * @author ruoyi
 */
public interface SysUserMapper
{
	
	/**
	 *  分部门查询离职率
	 * @param deptName
	 * @return
	 */
	public List<Data> selectUserRatio(Data data);
	
	/**
	 * 查询各部门过三个月试用期人数
	 * @return
	 */
	public List<Data> selectUserCount(String deptName);
	
	/**
	 * @return 所有leader
	 */
	public List<SysUser> selectLeaderList();
	
    /**
     * 根据条件分页查询用户列表
     * 
     * @param sysUser 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUserList(SysUser sysUser);

    /**
     * 根据条件分页查询未已配用户角色列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectAllocatedList(SysUser user);

    /**
     * 根据条件分页查询未分配用户角色列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUnallocatedList(SysUser user);

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByLoginName(String userName);

    /**
     * 通过手机号码查询用户
     * 
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    public SysUser selectUserByPhoneNumber(String phoneNumber);

    /**
     * 通过邮箱查询用户
     * 
     * @param email 邮箱
     * @return 用户对象信息
     */
    public SysUser selectUserByEmail(String email);

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public SysUser selectUserById(Long userId);

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserById(Long userId);

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserByIds(Long[] ids);

    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(SysUser user);

    /**
     * 新增用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(SysUser user);

    /**
     * 校验用户名称是否唯一
     * 
     * @param loginName 登录名称
     * @return 结果
     */
    public int checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phonenumber 手机号码
     * @return 结果
     */
    public SysUser checkPhoneUnique(String phonenumber);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    public SysUser checkEmailUnique(String email);
    
    
    public List<SysUser> selectUserByDpetList(Long deptId);
    
	
	/**	根据用户id查询leader
	 * @param userId
	 * @return
	 */
	public SysUser selectLeaderByUserId(Long userId);
    
	/**	根据用户id查询上上级部门领导信息
	 * @param userId
	 * @return
	 */
	public SysUser selectUpLeaderByUserId(Long userId);
	
    /**
     * 根据用户id查询该部门领导id
     */
    public Long selectApproverIdByApplyerId(Long userId);
    /**
     * 根据用户id查询该部门上级领导id
     */
    public Long selectUpApproverIdByApplyerId(Long userId);
    
    /**
     * 查询所有存在用户
     */
    public List<SysUser> selectAllUser();
    
    /**
	 * 根据用户id查询祖级列表
	 * @param userId
	 * @return
	 */
	public String selectAncestorsByUserId(Long userId);
	
	/**
	 * 根据部门id查询部门负责人id
	 * @param userId
	 * @return
	 */
	public Long selectUserIdByDeptId(Long deptId);
	
	/**
	 * 根据username查询userid
	 * @param userId
	 * @return 模糊查询结果
	 */
	public Long selectUserIdByUserName(String userName);
	
	/**
	 * 根据username查询userid
	 * @param userId
	 * @return 模糊查询结果
	 */
	public Long selectUserIdByUserNameOnly(String userName);

    List<UserModel> selectAllUserModel();

    SysUser selectIsRoleByRoleId(Long roleId, Long userId);
}
