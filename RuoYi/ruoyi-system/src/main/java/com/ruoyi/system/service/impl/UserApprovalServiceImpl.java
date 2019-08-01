package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.UserApprovalMapper;
import com.ruoyi.system.domain.QueryConditions;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.UserApproval;
import com.ruoyi.system.service.IUserApprovalService;
import com.ruoyi.common.core.text.Convert;

/**
 * 审批 服务层实现
 * 
 * @author ruoyi
 * @date 2019-06-04
 */
@Service
public class UserApprovalServiceImpl implements IUserApprovalService 
{
	private static final Set<SysDept> dSet = new TreeSet<SysDept>();  //部门集合                                                                   

	@Autowired
	private UserApprovalMapper userApprovalMapper;
    @Autowired
    private SysDeptMapper deptMapper;
    @Autowired
    private SysUserMapper userMapper;
	/**
     * 查询审批信息
     * 
     * @param approvalId 审批ID
     * @return 审批信息
     */
    @Override
	public UserApproval selectUserApprovalById(Long approvalId)
	{
	    return userApprovalMapper.selectUserApprovalById(approvalId);
	}
	
	/**
     * 查询审批列表
     * 
     * @param userApproval 审批信息
     * @return 审批集合
     */
	@Override
	public List<UserApproval> selectUserApprovalList(UserApproval userApproval)
	{
	    return userApprovalMapper.selectUserApprovalList(userApproval);
	}
	
    /**
     * 新增审批
     * 
     * @param userApproval 审批信息
     * @return 结果
     */
	@Override
	public int insertUserApproval(UserApproval userApproval)
	{
	    return userApprovalMapper.insertUserApproval(userApproval);
	}
	
	/**
     * 修改审批
     * 
     * @param userApproval 审批信息
     * @return 结果
     */
	@Override
	public int updateUserApproval(UserApproval userApproval)
	{
	    return userApprovalMapper.updateUserApproval(userApproval);
	}

	/**
     * 删除审批对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUserApprovalByIds(String ids)
	{
		return userApprovalMapper.deleteUserApprovalByIds(Convert.toStrArray(ids));
	}

	@Override
	public UserApproval selectUserApprovalByUserApproval(UserApproval userApproval) {
		
		return userApprovalMapper.selectUserApprovalByUserApproval(userApproval);
	}

	/**
     * 查询审批列表
     * 
     * @param userApproval 审批信息
     * @return 审批集合
     */
	@Override
	public List<QueryConditions> selectQueryConditionsList(QueryConditions queryConditions) {
		return userApprovalMapper.selectQueryConditionsList(queryConditions);
	}

	/**
     * 查询所有审批列表
     * 
     * @param userApproval 审批信息
     * @return 审批集合
     */
	@Override
	public List<QueryConditions> selectAllQueryConditionsList(QueryConditions queryConditions) {

		if(queryConditions.getUserId() == 1L){//admin用户
			return userApprovalMapper.selectQueryConditionsList(queryConditions);
		}
		
		//人事专员
		SysDept dept = deptMapper.selectDeptByUserId(queryConditions.getUserId());
		if(dept != null && "宋彬".equals(dept.getLeader())){
			return userApprovalMapper.selectQueryConditionsList(queryConditions);
		}
		
		//leader
		SysUser user = userMapper.selectUserById(queryConditions.getUserId());
		dept = new SysDept();
		dept.setLeader(user.getUserName());
		dSet.clear();
		getDeptList(dept);	
		queryConditions.setdSet(dSet);
		queryConditions.setUserName(user.getUserName());//查询下级员工的申请记录
		return userApprovalMapper.selectQueryConditionsList(queryConditions);
	}

	/**
	 * 递归实现获取当前用户负责的所有部门id
	 * @param dept
	 * @author wgf
	 */
	private void getDeptList(SysDept dept){
		SysDept dept2 = new SysDept();
		List<SysDept> deptList = deptMapper.selectDeptList(dept);
		dSet.addAll(deptList);
		for(SysDept d : deptList){
			dept2.setParentId(d.getDeptId());
			List<SysDept> deptList2 = deptMapper.selectDeptList(dept2);
			if(deptList2 != null && !"".equals(deptList2) && deptList2.size() != 0){
				getDeptList(dept2);
			}
		}
	}
}
