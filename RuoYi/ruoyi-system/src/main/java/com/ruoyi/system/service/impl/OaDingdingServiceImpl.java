package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OaDingdingMapper;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.domain.Dingding;
import com.ruoyi.system.domain.OaDingding;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.IOaDingdingService;
import com.ruoyi.common.core.text.Convert;

/**
 * 钉钉考勤数据 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-26
 */
@Service
public class OaDingdingServiceImpl implements IOaDingdingService 
{
	private static final Set<SysDept> dSet = new TreeSet<SysDept>();  //部门集合                                                                   

	@Autowired
	private OaDingdingMapper oaDingdingMapper;
	@Autowired
    private SysUserMapper userMapper;
	@Autowired
    private SysDeptMapper deptMapper;

	/**
     * 查询钉钉考勤数据信息
     * 
     * @param userId 钉钉考勤数据ID
     * @return 钉钉考勤数据信息
     */
    @Override
	public OaDingding selectOaDingdingById(String userId)
	{
	    return oaDingdingMapper.selectOaDingdingById(userId);
	}
	
	/**
     * 查询钉钉考勤数据列表
     * 
     * @param oaDingding 钉钉考勤数据信息
     * @return 钉钉考勤数据集合
     */
	@Override
	public List<Dingding> selectOaDingdingList(Dingding ding)
	{
		if(ding.getUserId() == 1){//admin用户
			return oaDingdingMapper.selectDingData(ding);
		}
		
		//人事专员
		SysDept dept = deptMapper.selectDeptByUserId(ding.getUserId());
		if(dept != null && "宋彬".equals(dept.getLeader())){
			ding.setUserId(1L);
			return oaDingdingMapper.selectDingData(ding);
		}
		
		//普通员工
		if(dept != null && !"宋彬".equals(dept.getLeader())){
			return oaDingdingMapper.selectDingData(ding);
		}
		
		//leader
		SysUser user = userMapper.selectUserById(ding.getUserId());
		dept = new SysDept();
		dept.setLeader(user.getUserName());
		dSet.clear();
		getDeptList(dept);	
		ding.setdSet(dSet);
		ding.setUserId(null);
		return oaDingdingMapper.selectDingData(ding);
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
    /**
     * 新增钉钉考勤数据
     * 
     * @param oaDingding 钉钉考勤数据信息
     * @return 结果
     */
	@Override
	public int insertOaDingding(OaDingding oaDingding)
	{
	    return oaDingdingMapper.insertOaDingding(oaDingding);
	}
	
	/**
     * 修改钉钉考勤数据
     * 
     * @param oaDingding 钉钉考勤数据信息
     * @return 结果
     */
	@Override
	public int updateOaDingding(OaDingding oaDingding)
	{
	    return oaDingdingMapper.updateOaDingding(oaDingding);
	}

	/**
     * 删除钉钉考勤数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOaDingdingByIds(String ids)
	{
		return oaDingdingMapper.deleteOaDingdingByIds(Convert.toStrArray(ids));
	}

	@Override
	public int insertForeach(List<OaDingding> dingList) {
		
		return oaDingdingMapper.insertForeach(dingList);
	}

}
