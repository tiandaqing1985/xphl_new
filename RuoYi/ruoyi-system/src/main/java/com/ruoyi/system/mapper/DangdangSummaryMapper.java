package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DangdangAppletsFront;
import com.ruoyi.system.domain.DangdangSummary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 当当前端消费汇总 数据层
 * 
 * @author ruoyi
 * @date 2019-07-11
 */
public interface DangdangSummaryMapper 
{
	/**
     * 查询当当前端消费汇总信息
     * 
     * @param id 当当前端消费汇总ID
     * @return 当当前端消费汇总信息
     */
	public DangdangSummary selectDangdangSummaryById(Integer id);
	
	/**
     * 查询当当前端消费汇总列表
     * 
     * @param dangdangSummary 当当前端消费汇总信息
     * @return 当当前端消费汇总集合
     */
	public List<DangdangSummary> selectDangdangSummaryList(DangdangSummary dangdangSummary);
	
	/**
     * 新增当当前端消费汇总
     * 
     * @param dangdangSummary 当当前端消费汇总信息
     * @return 结果
     */
	public int insertDangdangSummary(DangdangSummary dangdangSummary);
	
	/**
     * 修改当当前端消费汇总
     * 
     * @param dangdangSummary 当当前端消费汇总信息
     * @return 结果
     */
	public int updateDangdangSummary(DangdangSummary dangdangSummary);
	
	/**
     * 删除当当前端消费汇总
     * 
     * @param id 当当前端消费汇总ID
     * @return 结果
     */
	public int deleteDangdangSummaryById(Integer id);
	
	/**
     * 批量删除当当前端消费汇总
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDangdangSummaryByIds(String[] ids);

	void batchInsert(@Param("list") List<DangdangSummary> listPage);
	/**
	 * 修改
	 * @param str
	 * @return
	 */
	public int updateGroupword(String  str);
}