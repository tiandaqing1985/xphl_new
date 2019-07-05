package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.BqFront;

import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 北汽前端 数据层
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
public interface BqFrontMapper 
{
	/**
     * 查询北汽前端信息
     * 
     * @param id 北汽前端ID
     * @return 北汽前端信息
     */
	public BqFront selectBqFrontById(Long id);
	
	/**
     * 查询北汽前端列表
     * 
     * @param bqFront 北汽前端信息
     * @return 北汽前端集合
     */
	public List<BqFront> selectBqFrontList(BqFront bqFront);
	
	/**
     * 新增北汽前端
     * 
     * @param bqFront 北汽前端信息
     * @return 结果
     */
	public int insertBqFront(BqFront bqFront);
	
	/**
     * 修改北汽前端
     * 
     * @param bqFront 北汽前端信息
     * @return 结果
     */
	public int updateBqFront(BqFront bqFront);
	
	/**
     * 删除北汽前端
     * 
     * @param id 北汽前端ID
     * @return 结果
     */
	public int deleteBqFrontById(Long id);
	
	/**
     * 批量删除北汽前端
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBqFrontByIds(String[] ids);
	/**
	 * 批量插入北汽前端数据
	 * @param list
	 */
	public void batchInsert(@Param("list") List<BqFront> list);
	
	/**
	 * 修改
	 * @param str
	 * @return
	 */
	public int updateGroupword(String  str);
	
	/**
	 * 清除所有前端数据
	 * @return
	 */
	public int deleteAllBqFront();
	
}