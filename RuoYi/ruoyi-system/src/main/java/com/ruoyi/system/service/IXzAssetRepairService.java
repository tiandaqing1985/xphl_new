package com.ruoyi.system.service;

import com.ruoyi.system.domain.XzAssetRepair;
import java.util.List;

/**
 * 资产维修 服务层
 * 
 * @author ruoyi
 * @date 2019-09-09
 */
public interface IXzAssetRepairService 
{
	/**
     * 查询资产维修信息
     * 
     * @param repairId 资产维修ID
     * @return 资产维修信息
     */
	public XzAssetRepair selectXzAssetRepairById(Long repairId);
	
	/**
     * 查询资产维修列表
     * 
     * @param xzAssetRepair 资产维修信息
     * @return 资产维修集合
     */
	public List<XzAssetRepair> selectXzAssetRepairList(XzAssetRepair xzAssetRepair);
	
	/**
     * 新增资产维修
     * 
     * @param xzAssetRepair 资产维修信息
     * @return 结果
     */
	public int insertXzAssetRepair(XzAssetRepair xzAssetRepair);
	
	/**
     * 修改资产维修
     * 
     * @param xzAssetRepair 资产维修信息
     * @return 结果
     */
	public int updateXzAssetRepair(XzAssetRepair xzAssetRepair);
		
	/**
     * 删除资产维修信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzAssetRepairByIds(String ids);
	
}
