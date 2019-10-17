package com.ruoyi.system.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.ruoyi.system.domain.Data;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.UserModel;

/**
 * 用户 业务层
 * 
 * @author ruoyi
 */
public interface ISysUserService
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
     * 根据条件分页查询用户列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUserList(SysUser user);

    /**
     * 根据条件分页查询已分配用户角色列表
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
     * @throws Exception 异常
     */
    public int deleteUserByIds(String ids) throws Exception;

    /**
     * 保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(SysUser user);

    /**
     * 保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(SysUser user);

    /**
     * 修改用户详细信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUserInfo(SysUser user);

    /**
     * 修改用户密码信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int resetUserPwd(SysUser user);

    /**
     * 校验用户名称是否唯一
     * 
     * @param loginName 登录名称
     * @return 结果
     */
    public String checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public String checkPhoneUnique(SysUser user);

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public String checkEmailUnique(SysUser user);

    /**
     * 根据用户ID查询用户所属角色组
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public String selectUserRoleGroup(Long userId);

    /**
     * 根据用户ID查询用户所属岗位组
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public String selectUserPostGroup(Long userId);

    /**
     * 导入用户数据
     * 
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName);

    /**
     * 用户状态修改
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int changeStatus(SysUser user);
    
    public List<SysUser> selectUserByDpetList(Long deptId);
    
    /**
     * 得到第n个月的入职日期
     */
    public Date getDate(String intime , int n) throws ParseException;
    
    /**
     * 得到第n个月的入职日期,计算试用一期二期时使用此方法
     */
    public Date getDate2(String intime , int n) throws ParseException;
    /**
     * 根据用户id查询该部门领导id
     */
    public Long selectApproverIdByApplyerId(Long userId);
    /**
     * 根据用户id查询该部门上级领导id
     */
    public Long selectUpApproverIdByApplyerId(Long userId);
    /**
	 * 得到当月是入职的第几个月
	 */
	public int countMonth(String intime);
	
	 /**
     * 查询所有存在用户
     */
    public List<SysUser> selectAllUser();
    
    /**
     * 根据用户id查询所有三级以上负责人的id
     */
    public List<Long> selectCenterIdByUserId(Long userId);
    
    /**
	 * 根据部门id查询部门负责人id
	 * @param userId
	 * @return
	 */
	public Long selectUserIdByDeptId(Long deptId);

	/**
	 * 根据姓名查id
	 * @param recipientName
	 * @return
	 */
	public Long selectUserIdByUserNameOnly(String recipientName);

    List<UserModel> selectAllUserModel();
}
