package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.BqZhanxian;
import java.util.List;	

/**
 * 北汽展现 数据层
 * 
 * @author ruoyi
 * @date 2019-07-04
 */
public interface BqZhanxianMapper 
{
	/**
     * 查询北汽展现信息
     * 
     * @param id 北汽展现ID
     * @return 北汽展现信息
     */
	public BqZhanxian selectBqZhanxianById(Long id);
	
	/**
     * 查询北汽展现列表
     * 
     * @param bqZhanxian 北汽展现信息
     * @return 北汽展现集合
     */
	public List<BqZhanxian> selectBqZhanxianList(BqZhanxian bqZhanxian);
	
	/**
     * 新增北汽展现
     * 
     * @param bqZhanxian 北汽展现信息
     * @return 结果
     */
	public int insertBqZhanxian(BqZhanxian bqZhanxian);
	
	/**
     * 修改北汽展现
     * 
     * @param bqZhanxian 北汽展现信息
     * @return 结果
     */
	public int updateBqZhanxian(BqZhanxian bqZhanxian);
	
	/**
     * 删除北汽展现
     * 
     * @param id 北汽展现ID
     * @return 结果
     */
	public int deleteBqZhanxianById(Long id);
	
	/**
     * 批量删除北汽展现
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBqZhanxianByIds(String[] ids);
	
	/**
	 * 从其他表查询到展现表的所有字段数据
	 */
	public List<BqZhanxian> selectBqZhanxian();
	
	/**
	 * 清除所有数据
	 * @return
	 */
	public int deleteAllBqZhanxian();
	
	/**
	 * 层级搜索
	 */
	/*汇总*/
	public List<BqZhanxian> selectBqZhanxianSumList();
	/*账户*/
	public List<BqZhanxian> selectBqZhanxianAccountSumList();
	/*计划*/
	public List<BqZhanxian> selectBqZhanxianPlanSumList();
	/*单元*/
	public List<BqZhanxian> selectBqZhanxianUnitSumList();
	/*关键词*/
	public List<BqZhanxian> selectBqZhanxianKeywordSumList();
}