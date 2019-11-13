package com.ruoyi.system.mapper;



import com.ruoyi.system.domain.DangMatch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 当当词性匹配 数据层
 * 
 * @author ruoyi
 * @date 2019-07-10
 */
public interface DangMatchMapper
{
	/**
     * 查询当当词性匹配信息
     * 
     * @param id 当当词性匹配ID
     * @return 当当词性匹配信息
     */
	public DangMatch selectDangdangMatchById(Integer id);
	
	/**
     * 查询当当词性匹配列表
     * 
     * @param dangdangMatch 当当词性匹配信息
     * @return 当当词性匹配集合
     */
	public List<DangMatch> selectDangdangMatchList(DangMatch dangdangMatch);
	
	/**
     * 新增当当词性匹配
     * 
     * @param dangdangMatch 当当词性匹配信息
     * @return 结果
     */
	public int insertDangdangMatch(DangMatch dangdangMatch);
	
	/**
     * 修改当当词性匹配
     * 
     * @param dangdangMatch 当当词性匹配信息
     * @return 结果
     */
	public int updateDangdangMatch(DangMatch dangdangMatch);
	
	/**
     * 删除当当词性匹配
     * 
     * @param id 当当词性匹配ID
     * @return 结果
     */
	public int deleteDangdangMatchById(Integer id);
	
	/**
     * 批量删除当当词性匹配
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDangdangMatchByIds(String[] ids);
	void batchInsert(@Param("list") List<DangMatch> listPage);
	/**
	 * 修改
	 * @param str
	 * @return
	 */
	public int updateGroupword(String  str);
}