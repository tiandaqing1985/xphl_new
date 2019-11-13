package com.ruoyi.system.service;

import com.ruoyi.system.domain.BqEnd;
import com.ruoyi.system.domain.BqFront;

import java.util.List;

/**
 * 北汽后端 服务层
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
public interface IBqEndService 
{
	/**
     * 查询北汽后端信息
     * 
     * @param id 北汽后端ID
     * @return 北汽后端信息
     */
	public BqEnd selectBqEndById(Long id);
	
	/**
     * 查询北汽后端列表
     * 
     * @param bqEnd 北汽后端信息
     * @return 北汽后端集合
     */
	public List<BqEnd> selectBqEndList(BqEnd bqEnd);
	
	/**
     * 新增北汽后端
     * 
     * @param bqEnd 北汽后端信息
     * @return 结果
     */
	public int insertBqEnd(BqEnd bqEnd);
	
	/**
     * 修改北汽后端
     * 
     * @param bqEnd 北汽后端信息
     * @return 结果
     */
	public int updateBqEnd(BqEnd bqEnd);
		
	/**
     * 删除北汽后端信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBqEndByIds(String ids);
	
	public String importBqEnd(List<BqEnd> bqeList, Boolean isUpdateSupport, String operName);
	
	/**
	 * 清除所有北汽前端数据
	 * @return
	 */
	public int deleteAllBqEnd();
}
