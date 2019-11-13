package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.JfZhanxian;
import java.util.List;	

/**
 * 玖富展现 数据层
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
public interface JfZhanxianMapper 
{
	/**
     * 查询玖富展现信息
     * 
     * @param id 玖富展现ID
     * @return 玖富展现信息
     */
	public JfZhanxian selectJfZhanxianById(Long id);
	
	/**
     * 查询玖富展现列表
     * 
     * @param jfZhanxian 玖富展现信息
     * @return 玖富展现集合
     */
	public List<JfZhanxian> selectJfZhanxianList(JfZhanxian jfZhanxian);
	
	/**
     * 新增玖富展现
     * 
     * @param jfZhanxian 玖富展现信息
     * @return 结果
     */
	public int insertJfZhanxian(JfZhanxian jfZhanxian);
	
	/**
     * 修改玖富展现
     * 
     * @param jfZhanxian 玖富展现信息
     * @return 结果
     */
	public int updateJfZhanxian(JfZhanxian jfZhanxian);
	
	/**
     * 删除玖富展现
     * 
     * @param id 玖富展现ID
     * @return 结果
     */
	public int deleteJfZhanxianById(Long id);
	
	/**
     * 批量删除玖富展现
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteJfZhanxianByIds(String[] ids);
	
	
	public int createJfZhanxianData(JfZhanxian jfZhanxian);
	
	
	public List<JfZhanxian> selectJfZhanxianSumList(JfZhanxian jfZhanxian);
	
	public List<JfZhanxian> selectJfZhanxianAccountSumList(JfZhanxian jfZhanxian);
	
	public List<JfZhanxian> selectJfZhanxianPlanSumList(JfZhanxian jfZhanxian);
	
	public List<JfZhanxian> selectJfZhanxianUnitSumList(JfZhanxian jfZhanxian);
	
	public List<JfZhanxian> selectJfZhanxianKeywordSumList(JfZhanxian jfZhanxian);
	
	public int deleteAllJfZhanxian();
	
}