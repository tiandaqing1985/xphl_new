package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.JfEnd;

import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 玖富后端 数据层
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
public interface JfEndMapper 
{
	/**
     * 查询玖富后端信息
     * 
     * @param id 玖富后端ID
     * @return 玖富后端信息
     */
	public JfEnd selectJfEndById(Long id);
	
	/**
     * 查询玖富后端列表
     * 
     * @param jfEnd 玖富后端信息
     * @return 玖富后端集合
     */
	public List<JfEnd> selectJfEndList(JfEnd jfEnd);
	
	/**
     * 新增玖富后端
     * 
     * @param jfEnd 玖富后端信息
     * @return 结果
     */
	public int insertJfEnd(JfEnd jfEnd);
	
	/**
     * 修改玖富后端
     * 
     * @param jfEnd 玖富后端信息
     * @return 结果
     */
	public int updateJfEnd(JfEnd jfEnd);
	
	/**
     * 删除玖富后端
     * 
     * @param id 玖富后端ID
     * @return 结果
     */
	public int deleteJfEndById(Long id);
	
	/**
     * 批量删除玖富后端
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteJfEndByIds(String[] ids);
	
	public  void batchInsert(@Param("list") List<JfEnd> list);
	
	public int deleteAllJfEnd();
}