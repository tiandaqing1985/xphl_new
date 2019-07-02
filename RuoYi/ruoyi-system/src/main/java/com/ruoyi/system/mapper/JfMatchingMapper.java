package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.JfMatching;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 玖富匹配 数据层
 * 
 * @author ruoyi
 * @date 2019-06-28
 */
public interface JfMatchingMapper 
{
	/**
     * 查询玖富匹配信息
     * 
     * @param id 玖富匹配ID
     * @return 玖富匹配信息
     */
	public JfMatching selectJfMatchingById(Long id);
	
	/**
     * 查询玖富匹配列表
     * 
     * @param jfMatching 玖富匹配信息
     * @return 玖富匹配集合
     */
	public List<JfMatching> selectJfMatchingList(JfMatching jfMatching);
	
	/**
     * 新增玖富匹配
     * 
     * @param jfMatching 玖富匹配信息
     * @return 结果
     */
	public int insertJfMatching(JfMatching jfMatching);
	
	/**
     * 修改玖富匹配
     * 
     * @param jfMatching 玖富匹配信息
     * @return 结果
     */
	public int updateJfMatching(JfMatching jfMatching);
	
	/**
     * 删除玖富匹配
     * 
     * @param id 玖富匹配ID
     * @return 结果
     */
	public int deleteJfMatchingById(Long id);
	
	/**
     * 批量删除玖富匹配
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteJfMatchingByIds(String[] ids);
	
	public  void batchInsert(@Param("list") List<JfMatching> list);
	
	public int updateGroupword(String  flag);
}