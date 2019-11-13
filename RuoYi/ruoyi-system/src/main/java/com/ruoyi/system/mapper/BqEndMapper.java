package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.BqEnd;
import com.ruoyi.system.domain.BqFront;

import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 北汽后端 数据层
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
public interface BqEndMapper 
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
     * 删除北汽后端
     * 
     * @param id 北汽后端ID
     * @return 结果
     */
	public int deleteBqEndById(Long id);
	
	/**
     * 批量删除北汽后端
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBqEndByIds(String[] ids);
	/**
	 * 批量插入北汽前端数据
	 * @param list
	 */
	public void batchInsert(@Param("list") List<BqEnd> list);
	
	/**
	 * 修改
	 * @param str
	 * @return
	 */
	public int updateGroupword(String  str);
	
	/**
	 * 清除所有北汽前端数据
	 * @return
	 */
	public int deleteAllBqEnd();
}