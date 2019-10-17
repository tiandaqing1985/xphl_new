package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.XzAssetRepairMapper;
import com.ruoyi.system.mapper.XzAsstesMapper;
import com.ruoyi.system.mapper.XzPersonalAssetMapper;
import com.ruoyi.system.domain.XzAssetRepair;
import com.ruoyi.system.domain.XzAsstes;
import com.ruoyi.system.domain.XzPersonalAsset;
import com.ruoyi.system.service.IXzAssetRepairService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资产维修 服务层实现
 * 
 * @author ruoyi
 * @date 2019-09-09
 */
@Service
public class XzAssetRepairServiceImpl implements IXzAssetRepairService {
	@Autowired
	private XzAssetRepairMapper xzAssetRepairMapper;

	@Autowired
	private XzAsstesMapper xzAsstesMapper;

	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private XzPersonalAssetMapper xzPersonalAssetMapper;


	/**
	 * 查询资产维修信息
	 * 
	 * @param repairId
	 *            资产维修ID
	 * @return 资产维修信息
	 */
	@Override
	public XzAssetRepair selectXzAssetRepairById(Long repairId) {
		return xzAssetRepairMapper.selectXzAssetRepairById(repairId);
	}

	/**
	 * 查询资产维修列表
	 * 
	 * @param xzAssetRepair
	 *            资产维修信息
	 * @return 资产维修集合
	 */
	@Override
	public List<XzAssetRepair> selectXzAssetRepairList(XzAssetRepair xzAssetRepair) {
		return xzAssetRepairMapper.selectXzAssetRepairList(xzAssetRepair);
	}

	/**
	 * 新增资产维修
	 * 
	 * @param xzAssetRepair
	 *            资产维修信息
	 * @return 结果
	 */
	@Override
	@Transactional
	public int insertXzAssetRepair(XzAssetRepair xzAssetRepair) {
		
		// 1.个人资产表信息更正(维修中)
		XzPersonalAsset xzPersonalAsset = new XzPersonalAsset();
		xzPersonalAsset.setId(xzAssetRepair.getAssetId());
		xzPersonalAsset.setAssetStatus("8");// 维修中
		xzPersonalAssetMapper.updateXzPersonalAsset(xzPersonalAsset);
		// 新增一条维修记录
		return xzAssetRepairMapper.insertXzAssetRepair(xzAssetRepair);
	}

	/**
	 * 修改资产维修
	 * 
	 * @param xzAssetRepair
	 *            资产维修信息
	 * @return 结果
	 */
	@Override
	@Transactional
	public int updateXzAssetRepair(XzAssetRepair xzAssetRepair) {
		xzAssetRepair.setInspectorId(sysUserMapper.selectUserIdByUserNameOnly(xzAssetRepair.getInspectorName()));
		// 资产表要变更资产使用状态
		XzAsstes xzAsstes = new XzAsstes();
		xzAsstes.setId(xzPersonalAssetMapper.selectXzPersonalAssetById(xzAssetRepair.getAssetId()).getAssetId());
		if(xzAssetRepair.getRepairStatus()!=null || xzAssetRepair.getRepairStatus()!=""){
			if(xzAssetRepair.getRepairStatus().equals("2")){//维修中
				//维修中
				xzAsstes.setUseStatus("8");
				xzAsstes.setAssetsStatus("5");
			}else if(xzAssetRepair.getRepairStatus().equals("3")){//已维修
				//使用中
				xzAsstes.setUseStatus("3");
				xzAsstes.setAssetsStatus("3");
				// 1.个人资产表信息更正(使用中)
				XzPersonalAsset xzPersonalAsset = new XzPersonalAsset();
				xzPersonalAsset.setId(xzAssetRepair.getAssetId());
				xzPersonalAsset.setAssetStatus("3");// 使用中
				xzPersonalAssetMapper.updateXzPersonalAsset(xzPersonalAsset);
			}else if(xzAssetRepair.getRepairStatus().equals("4")){
				//已报废
				xzAsstes.setUseStatus("6");
				xzAsstes.setAssetsStatus("6");
				// 1.个人资产表信息更正(使用中)
				XzPersonalAsset xzPersonalAsset = new XzPersonalAsset();
				xzPersonalAsset.setId(xzAssetRepair.getAssetId());
				xzPersonalAsset.setAssetStatus("6");// 已报废
				xzPersonalAssetMapper.updateXzPersonalAsset(xzPersonalAsset);
			}else{
				//不做变动
			}
		}
		xzAsstesMapper.updateXzAsstes(xzAsstes);
		
		return xzAssetRepairMapper.updateXzAssetRepair(xzAssetRepair);
	}

	/**
	 * 删除资产维修对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteXzAssetRepairByIds(String ids) {
		return xzAssetRepairMapper.deleteXzAssetRepairByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<XzAssetRepair> selectXzAssetRepairHistoryList(XzAssetRepair xzAssetRepair) {
		return xzAssetRepairMapper.selectXzAssetRepairHistoryList(xzAssetRepair);
	}
}
