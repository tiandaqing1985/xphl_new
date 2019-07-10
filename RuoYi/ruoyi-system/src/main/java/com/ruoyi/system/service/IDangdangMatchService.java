package com.ruoyi.system.service;

import com.ruoyi.system.domain.BwFront;
import com.ruoyi.system.domain.DangdangMatch;
import java.util.List;

/**
 * 当当词性匹配 服务层
 * 
 * @author ruoyi
 * @date 2019-07-10
 */
public interface IDangdangMatchService 
{
	/**
     * 查询当当词性匹配信息
     * 
     * @param id 当当词性匹配ID
     * @return 当当词性匹配信息
     */
	public DangdangMatch selectDangdangMatchById(Integer id);
	
	/**
     * 查询当当词性匹配列表
     * 
     * @param dangdangMatch 当当词性匹配信息
     * @return 当当词性匹配集合
     */
	public List<DangdangMatch> selectDangdangMatchList(DangdangMatch dangdangMatch);
	
	/**
     * 新增当当词性匹配
     * 
     * @param dangdangMatch 当当词性匹配信息
     * @return 结果
     */
	public int insertDangdangMatch(DangdangMatch dangdangMatch);
	
	/**
     * 修改当当词性匹配
     * 
     * @param dangdangMatch 当当词性匹配信息
     * @return 结果
     */
	public int updateDangdangMatch(DangdangMatch dangdangMatch);
		
	/**
     * 删除当当词性匹配信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDangdangMatchByIds(String ids);

	public String importBwFront(List<DangdangMatch> bwList, Boolean isUpdateSupport, String operName);


}
