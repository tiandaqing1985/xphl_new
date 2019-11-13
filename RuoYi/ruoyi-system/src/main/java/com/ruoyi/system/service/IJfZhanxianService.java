package com.ruoyi.system.service;

import com.ruoyi.system.domain.JfZhanxian;
import java.util.List;

/**
 * 玖富展现 服务层
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
public interface IJfZhanxianService 
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
     * 删除玖富展现信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteJfZhanxianByIds(String ids);
	
	
	public int createJfZhanxianData(JfZhanxian jfZhanxian);
	
	public List<JfZhanxian> selectJfZhanxianSumList(JfZhanxian jfZhanxian,String selectflag);
	
	public int deleteAllJfZhanxian();
	
}
