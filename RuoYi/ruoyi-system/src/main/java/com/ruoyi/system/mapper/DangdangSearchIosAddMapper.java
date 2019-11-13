package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DangdangSearchAdd;
import com.ruoyi.system.domain.DangdangSearchIosAdd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 当当ios后端数据 数据层
 * 
 * @author ruoyi
 * @date 2019-07-15
 */
public interface DangdangSearchIosAddMapper 
{
	/**
     * 查询当当ios后端数据信息
     * 
     * @param id 当当ios后端数据ID
     * @return 当当ios后端数据信息
     */
	public DangdangSearchIosAdd selectDangdangSearchIosAddById(Integer id);
	
	/**
     * 查询当当ios后端数据列表
     * 
     * @param dangdangSearchIosAdd 当当ios后端数据信息
     * @return 当当ios后端数据集合
     */
	public List<DangdangSearchIosAdd> selectDangdangSearchIosAddList(DangdangSearchIosAdd dangdangSearchIosAdd);
	
	/**
     * 新增当当ios后端数据
     * 
     * @param dangdangSearchIosAdd 当当ios后端数据信息
     * @return 结果
     */
	public int insertDangdangSearchIosAdd(DangdangSearchIosAdd dangdangSearchIosAdd);
	
	/**
     * 修改当当ios后端数据
     * 
     * @param dangdangSearchIosAdd 当当ios后端数据信息
     * @return 结果
     */
	public int updateDangdangSearchIosAdd(DangdangSearchIosAdd dangdangSearchIosAdd);
	
	/**
     * 删除当当ios后端数据
     * 
     * @param id 当当ios后端数据ID
     * @return 结果
     */
	public int deleteDangdangSearchIosAddById(Integer id);
	
	/**
     * 批量删除当当ios后端数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDangdangSearchIosAddByIds(String[] ids);

	void batchInsert(@Param("list") List<DangdangSearchIosAdd> listPage);

	/**
	 * 修改
	 *
	 * @param str
	 * @return
	 */
	public int updateGroupword(String str);
}