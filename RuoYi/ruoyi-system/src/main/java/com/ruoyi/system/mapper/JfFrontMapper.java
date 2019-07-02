package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.JfFront;

import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 玖富前端 数据层
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
public interface JfFrontMapper 
{
	/**
     * 查询玖富前端信息
     * 
     * @param id 玖富前端ID
     * @return 玖富前端信息
     */
	public JfFront selectJfFrontById(Long id);
	
	/**
     * 查询玖富前端列表
     * 
     * @param jfFront 玖富前端信息
     * @return 玖富前端集合
     */
	public List<JfFront> selectJfFrontList(JfFront jfFront);
	
	/**
     * 新增玖富前端
     * 
     * @param jfFront 玖富前端信息
     * @return 结果
     */
	public int insertJfFront(JfFront jfFront);
	
	/**
     * 修改玖富前端
     * 
     * @param jfFront 玖富前端信息
     * @return 结果
     */
	public int updateJfFront(JfFront jfFront);
	
	/**
     * 删除玖富前端
     * 
     * @param id 玖富前端ID
     * @return 结果
     */
	public int deleteJfFrontById(Long id);
	
	/**
     * 批量删除玖富前端
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteJfFrontByIds(String[] ids);
	
	public  void batchInsert(@Param("list") List<JfFront> list);
	
	public int updateGroupword(String  str);
}