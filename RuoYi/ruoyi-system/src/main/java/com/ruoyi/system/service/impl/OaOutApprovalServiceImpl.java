package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OaOutApprovalMapper;
import com.ruoyi.system.domain.OaOutApproval;
import com.ruoyi.system.service.IOaOutApprovalService;
import com.ruoyi.common.core.text.Convert;

/**
 * 外出报备审批 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-01
 */
@Service
public class OaOutApprovalServiceImpl implements IOaOutApprovalService 
{
	@Autowired
	private OaOutApprovalMapper oaOutApprovalMapper;

	/**
     * 查询外出报备审批信息
     * 
     * @param id 外出报备审批ID
     * @return 外出报备审批信息
     */
    @Override
	public OaOutApproval selectOaOutApprovalById(Long id)
	{
	    return oaOutApprovalMapper.selectOaOutApprovalById(id);
	}
	
	/**
     * 查询外出报备审批列表
     * 
     * @param oaOutApproval 外出报备审批信息
     * @return 外出报备审批集合
     */
	@Override
	public List<OaOutApproval> selectOaOutApprovalList(OaOutApproval oaOutApproval)
	{
	    return oaOutApprovalMapper.selectOaOutApprovalList(oaOutApproval);
	}
	
    /**
     * 新增外出报备审批
     * 
     * @param oaOutApproval 外出报备审批信息
     * @return 结果
     */
	@Override
	public int insertOaOutApproval(OaOutApproval oaOutApproval)
	{
	    return oaOutApprovalMapper.insertOaOutApproval(oaOutApproval);
	}
	
	/**
     * 修改外出报备审批
     * 
     * @param oaOutApproval 外出报备审批信息
     * @return 结果
     */
	@Override
	public int updateOaOutApproval(OaOutApproval oaOutApproval)
	{
	    return oaOutApprovalMapper.updateOaOutApproval(oaOutApproval);
	}

	/**
     * 删除外出报备审批对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOaOutApprovalByIds(String ids)
	{
		return oaOutApprovalMapper.deleteOaOutApprovalByOutIds(Convert.toStrArray(ids));
	}
	
}
