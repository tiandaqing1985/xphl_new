package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.XzAssetReturnMapper;
import com.ruoyi.system.mapper.XzAsstesMapper;
import com.ruoyi.system.mapper.XzPersonalAssetMapper;
import com.ruoyi.system.domain.XzAssetReturn;
import com.ruoyi.system.domain.XzAsstes;
import com.ruoyi.system.domain.XzPersonalAsset;
import com.ruoyi.system.service.IXzAssetReturnService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资产归还 服务层实现
 * 
 * @author ruoyi
 * @date 2019-09-11
 */
@Service
public class XzAssetReturnServiceImpl implements IXzAssetReturnService 
{
	@Autowired
	private XzAssetReturnMapper xzAssetReturnMapper;
	
	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private XzAsstesMapper xzAsstesMapper;
	
	@Autowired
	private XzPersonalAssetMapper xzPersonalAssetMapper;


	/**
     * 查询资产归还信息
     * 
     * @param id 资产归还ID
     * @return 资产归还信息
     */
    @Override
	public XzAssetReturn selectXzAssetReturnById(Long id)
	{
	    return xzAssetReturnMapper.selectXzAssetReturnById(id);
	}
	
	/**
     * 查询资产归还列表
     * 
     * @param xzAssetReturn 资产归还信息
     * @return 资产归还集合
     */
	@Override
	public List<XzAssetReturn> selectXzAssetReturnList(XzAssetReturn xzAssetReturn)
	{
	    return xzAssetReturnMapper.selectXzAssetReturnList(xzAssetReturn);
	}
	
    /**
     * 新增资产归还
     * 
     * @param xzAssetReturn 资产归还信息
     * @return 结果
     */
	@Override
	public int insertXzAssetReturn(XzAssetReturn xzAssetReturn)
	{
		xzAssetReturn.setUserId(sysUserMapper.selectUserIdByUserNameOnly(xzAssetReturn.getCreateByName()));
		//个人资产表信息更正
		XzPersonalAsset xzPersonalAsset=new XzPersonalAsset();
		xzPersonalAsset.setAssetId(xzAssetReturn.getAssetId());
		//个人资产状态变为已归还待确认
		xzPersonalAsset.setAssetStatus("9");
		xzPersonalAssetMapper.updateXzPersonalAsset(xzPersonalAsset);
		
		//资产表信息更正
		XzAsstes xzAsstes = new XzAsstes();
		xzAsstes.setId(xzAssetReturn.getAssetId());
		//资产状态变为已归还待确认
		xzAsstes.setUseStatus("9");
		xzAsstesMapper.updateXzAsstes(xzAsstes);
		
	    return xzAssetReturnMapper.insertXzAssetReturn(xzAssetReturn);
	}
	
	/**
     * 修改资产归还
     * 
     * @param xzAssetReturn 资产归还信息
     * @return 结果
     */
	@Override
	@Transactional
	public int updateXzAssetReturn(XzAssetReturn xzAssetReturn)
	{
		if(xzAssetReturn.getReturnStatus().equals("2")){
			//已归还
			//资产表信息更正
			XzAsstes xzAsstes = new XzAsstes();
			xzAsstes.setId(xzAssetReturn.getAssetId());
			//资产状态变为在库，无
			xzAsstes.setAssetsStatus("2");
			xzAsstes.setUseStatus("1");
			xzAsstes.setUseBy(null);
			xzAsstes.setDepartment(null);
			xzAsstes.setUseTime(null);
			xzAsstesMapper.updateXzAsstes(xzAsstes);
			
			//个人资产表信息更正
			XzPersonalAsset xzPersonalAsset=new XzPersonalAsset();
			xzPersonalAsset.setAssetId(xzAssetReturn.getAssetId());
			//个人资产状态变为已归还待确认
			xzPersonalAsset.setAssetStatus("4");
			xzPersonalAssetMapper.updateXzPersonalAsset(xzPersonalAsset);
		}
	    return xzAssetReturnMapper.updateXzAssetReturn(xzAssetReturn);
	}

	/**
     * 删除资产归还对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteXzAssetReturnByIds(String ids)
	{
		return xzAssetReturnMapper.deleteXzAssetReturnByIds(Convert.toStrArray(ids));
	}
	
}
