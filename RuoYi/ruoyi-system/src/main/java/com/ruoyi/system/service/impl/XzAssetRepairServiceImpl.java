package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.XzAssetRepairMapper;
import com.ruoyi.system.mapper.XzAsstesMapper;
import com.ruoyi.system.domain.XzAssetRepair;
import com.ruoyi.system.domain.XzAsstes;
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
		// 资产表要变更资产使用状态为待维修
		XzAsstes xzAsstes = new XzAsstes();
		xzAsstes.setId(xzAssetRepair.getAssetId());
		xzAsstes.setUseStatus("7");
		xzAsstesMapper.updateXzAsstes(xzAsstes);
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
	public int updateXzAssetRepair(XzAssetRepair xzAssetRepair) {
		xzAssetRepair.setInspectorId(sysUserMapper.selectUserIdByUserNameOnly(xzAssetRepair.getInspectorName()));
		// 资产表要变更资产使用状态
		XzAsstes xzAsstes = new XzAsstes();
		xzAsstes.setId(xzAssetRepair.getAssetId());
		if(xzAssetRepair.getRepairStatus()!=null || xzAssetRepair.getRepairStatus()!=""){
			if(xzAssetRepair.getRepairStatus().equals("2")){
				//维修中
				xzAsstes.setUseStatus("8");
			}else if(xzAssetRepair.getRepairStatus().equals("3")){
				//已维修
				xzAsstes.setUseStatus("3");
			}else if(xzAssetRepair.getRepairStatus().equals("4")){
				//已报废-需要归还
				xzAsstes.setUseStatus("6");
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

}
