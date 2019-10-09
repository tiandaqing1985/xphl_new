package com.ruoyi.system.service;

import com.ruoyi.system.domain.XzAsstes;
import com.ruoyi.system.domain.XzAsstesSta;

import java.util.List;

/**
 * 资产 服务层
 * 
 * @author ruoyi
 * @date 2019-08-02
 */
public interface IXzAsstesService {
	/**
	 * 查询资产信息
	 * 
	 * @param id
	 *            资产ID
	 * @return 资产信息
	 */
	public XzAsstes selectXzAsstesById(Long id);

	/**
	 * 查询资产列表
	 * 
	 * @param xzAsstes
	 *            资产信息
	 * @return 资产集合
	 */
	public List<XzAsstes> selectXzAsstesList(XzAsstes xzAsstes);

	/**
	 * 新增固定资产
	 * 
	 * @param xzAsstes
	 *            资产信息
	 * @return 结果
	 */
	public String insertXzAsstes(XzAsstes xzAsstes);

	/**
	 * 修改资产
	 * 
	 * @param xzAsstes
	 *            资产信息
	 * @return 结果
	 */
	public int updateXzAsstes(XzAsstes xzAsstes);

	/**
	 * 删除资产信息
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	public int deleteXzAsstesByIds(String ids);

	/**
	 * 
	 * @param assetsList
	 *            资产数据列表
	 * @param updateSupport
	 * @param operName
	 * @return
	 */
	public String importXzAsstes(List<XzAsstes> assetsList, boolean updateSupport, String operName);

	/**
	 * 根据使用者id修改资产状态
	 * 
	 * @param userId
	 * @return
	 */
	public String updateXzAsstesByAllDraw(Long userId);

	/**
	 * 根据使用者id查询待领取资产数量
	 * 
	 * @param userId
	 * @return
	 */
	public int countXzAsstesByAllDraw(Long userId);

	public String updateXzAsstesByAssetId(XzAsstes xzAsstes);

	public String insertStaAsstes(XzAsstes xzAsstes);

	/**
	 * 查询最大的资产编号
	 * 
	 * @param xzAsstes
	 * @return
	 */
	public String selectMaxCodeByType(XzAsstes xzAsstes);

	/**
	 * 提交多条资产记录
	 * @param ids
	 * @param xzAsstes 
	 * @return
	 */
	public String updateXzAsstesBySub(String ids, XzAsstes xzAsstes);

	/**
	 * 库存
	 * @param xzAsstes
	 * @return
	 */
	public List<XzAsstesSta> selectXzStatisticsList(XzAsstesSta xzAsstesSta);

	public int selectAssetByType12(XzAsstes xz);

}