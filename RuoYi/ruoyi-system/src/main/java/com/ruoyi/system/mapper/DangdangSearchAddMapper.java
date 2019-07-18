package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DangdangAdditional;
import com.ruoyi.system.domain.DangdangSearchAdd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 当当后端数据（搜索） 数据层
 * 
 * @author ruoyi
 * @date 2019-07-12
 */
public interface DangdangSearchAddMapper 
{
	/**
     * 查询当当后端数据（搜索）信息
     * 
     * @param id 当当后端数据（搜索）ID
     * @return 当当后端数据（搜索）信息
     */
	public DangdangSearchAdd selectDangdangSearchAddById(Integer id);
	
	/**
     * 查询当当后端数据（搜索）列表
     * 
     * @param dangdangSearchAdd 当当后端数据（搜索）信息
     * @return 当当后端数据（搜索）集合
     */
	public List<DangdangSearchAdd> selectDangdangSearchAddList(DangdangSearchAdd dangdangSearchAdd);
	
	/**
     * 新增当当后端数据（搜索）
     * 
     * @param dangdangSearchAdd 当当后端数据（搜索）信息
     * @return 结果
     */
	public int insertDangdangSearchAdd(DangdangSearchAdd dangdangSearchAdd);
	
	/**
     * 修改当当后端数据（搜索）
     * 
     * @param dangdangSearchAdd 当当后端数据（搜索）信息
     * @return 结果
     */
	public int updateDangdangSearchAdd(DangdangSearchAdd dangdangSearchAdd);
	
	/**
     * 删除当当后端数据（搜索）
     * 
     * @param id 当当后端数据（搜索）ID
     * @return 结果
     */
	public int deleteDangdangSearchAddById(Integer id);
	
	/**
     * 批量删除当当后端数据（搜索）
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDangdangSearchAddByIds(String[] ids);

	void batchInsert(@Param("list") List<DangdangSearchAdd> listPage);

	/**
	 * 修改
	 *
	 * @param str
	 * @return
	 */
	public int updateGroupword(String str);
}