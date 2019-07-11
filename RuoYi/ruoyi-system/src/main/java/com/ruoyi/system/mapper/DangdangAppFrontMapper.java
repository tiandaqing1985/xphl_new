package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DangdangAppFront;
import com.ruoyi.system.domain.DangdangMatch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 当当APP前端 数据层
 * 
 * @author ruoyi
 * @date 2019-07-11
 */
public interface DangdangAppFrontMapper 
{
	/**
     * 查询当当APP前端信息
     * 
     * @param id 当当APP前端ID
     * @return 当当APP前端信息
     */
	public DangdangAppFront selectDangdangAppFrontById(Integer id);
	
	/**
     * 查询当当APP前端列表
     * 
     * @param dangdangAppFront 当当APP前端信息
     * @return 当当APP前端集合
     */
	public List<DangdangAppFront> selectDangdangAppFrontList(DangdangAppFront dangdangAppFront);
	
	/**
     * 新增当当APP前端
     * 
     * @param dangdangAppFront 当当APP前端信息
     * @return 结果
     */
	public int insertDangdangAppFront(DangdangAppFront dangdangAppFront);
	
	/**
     * 修改当当APP前端
     * 
     * @param dangdangAppFront 当当APP前端信息
     * @return 结果
     */
	public int updateDangdangAppFront(DangdangAppFront dangdangAppFront);
	
	/**
     * 删除当当APP前端
     * 
     * @param id 当当APP前端ID
     * @return 结果
     */
	public int deleteDangdangAppFrontById(Integer id);
	
	/**
     * 批量删除当当APP前端
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDangdangAppFrontByIds(String[] ids);

	void batchInsert(@Param("list") List<DangdangAppFront> listPage);
	/**
	 * 修改
	 * @param str
	 * @return
	 */
	public int updateGroupword(String  str);
}