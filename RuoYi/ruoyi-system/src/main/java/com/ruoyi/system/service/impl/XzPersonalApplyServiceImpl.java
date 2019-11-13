package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.XzPersonalApplyMapper;
import com.ruoyi.system.domain.XzPersonalApply;
import com.ruoyi.system.service.IXzPersonalApplyService;
import com.ruoyi.common.core.text.Convert;

/**
 * 个人申请 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-27
 */
@Service
public class XzPersonalApplyServiceImpl implements IXzPersonalApplyService 
{
	@Autowired
	private XzPersonalApplyMapper xzPersonalApplyMapper;
	
	@Autowired
	private SysUserMapper sysUserMapper;

	/**
     * 查询个人申请信息
     * 
     * @param applyId 个人申请ID
     * @return 个人申请信息
     */
    @Override
	public XzPersonalApply selectXzPersonalApplyById(Long applyId)
	{
	    return xzPersonalApplyMapper.selectXzPersonalApplyById(applyId);
	}
	
	/**
     * 查询个人申请列表
     * 
     * @param xzPersonalApply 个人申请信息
     * @return 个人申请集合
     */
	@Override
	public List<XzPersonalApply> selectXzPersonalApplyList(XzPersonalApply xzPersonalApply)
	{
	    return xzPersonalApplyMapper.selectXzPersonalApplyList(xzPersonalApply);
	}
	
    /**
     * 新增个人申请
     * 
     * @param xzPersonalApply 个人申请信息
     * @return 结果
     */
	@Override
	@Transactional
	public int insertXzPersonalApply(XzPersonalApply xzPersonalApply)
	{
		//如果是资产借用
		if(xzPersonalApply.getApplyType().equals("2")){
			sysUserMapper.selectUserIdByUserNameOnly(xzPersonalApply.getCreateByName());
		}
		
	    return xzPersonalApplyMapper.insertXzPersonalApply(xzPersonalApply);
	}
	
	/**
     * 修改个人申请
     * 
     * @param xzPersonalApply 个人申请信息
     * @return 结果
     */
	@Override
	public int updateXzPersonalApply(XzPersonalApply xzPersonalApply)
	{
	    return xzPersonalApplyMapper.updateXzPersonalApply(xzPersonalApply);
	}

	/**
     * 删除个人申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteXzPersonalApplyByIds(String ids)
	{
		return xzPersonalApplyMapper.deleteXzPersonalApplyByIds(Convert.toStrArray(ids));
	}
	
}
