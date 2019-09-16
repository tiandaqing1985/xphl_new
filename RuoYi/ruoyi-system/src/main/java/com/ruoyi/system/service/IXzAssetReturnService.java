package com.ruoyi.system.service;

import com.ruoyi.system.domain.XzAssetReturn;
import java.util.List;

/**
 * 资产归还 服务层
 * 
 * @author ruoyi
 * @date 2019-09-11
 */
public interface IXzAssetReturnService 
{
	/**
     * 查询资产归还信息
     * 
     * @param id 资产归还ID
     * @return 资产归还信息
     */
	public XzAssetReturn selectXzAssetReturnById(Long id);
	
	/**
     * 查询资产归还列表
     * 
     * @param xzAssetReturn 资产归还信息
     * @return 资产归还集合
     */
	public List<XzAssetReturn> selectXzAssetReturnList(XzAssetReturn xzAssetReturn);
	
	/**
     * 新增资产归还
     * 
     * @param xzAssetReturn 资产归还信息
     * @return 结果
     */
	public int insertXzAssetReturn(XzAssetReturn xzAssetReturn);
	
	/**
     * 修改资产归还
     * 
     * @param xzAssetReturn 资产归还信息
     * @return 结果
     */
	public int updateXzAssetReturn(XzAssetReturn xzAssetReturn);
		
	/**
     * 删除资产归还信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzAssetReturnByIds(String ids);
	
}
