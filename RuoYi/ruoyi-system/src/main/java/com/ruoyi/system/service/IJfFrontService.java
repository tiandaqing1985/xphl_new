package com.ruoyi.system.service;

import com.ruoyi.system.domain.JfFront;

import java.util.List;

/**
 * 玖富前端 服务层
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
public interface IJfFrontService 
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
     * 删除玖富前端信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteJfFrontByIds(String ids);
	
	public String importJfFront(List<JfFront> jfList, Boolean isUpdateSupport, String operName);
	
	public int deleteAllJfFront();
	
}
