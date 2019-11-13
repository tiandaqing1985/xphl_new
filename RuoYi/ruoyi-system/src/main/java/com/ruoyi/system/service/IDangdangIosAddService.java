package com.ruoyi.system.service;

import com.ruoyi.system.domain.DangdangIosAdd;
import com.ruoyi.system.domain.DangdangSearchAdd;

import java.util.List;

/**
 * 当当ios后端数据 服务层
 * 
 * @author ruoyi
 * @date 2019-07-15
 */
public interface IDangdangIosAddService 
{
	/**
     * 查询当当ios后端数据信息
     * 
     * @param id 当当ios后端数据ID
     * @return 当当ios后端数据信息
     */
	public DangdangIosAdd selectDangdangIosAddById(Integer id);
	
	/**
     * 查询当当ios后端数据列表
     * 
     * @param dangdangIosAdd 当当ios后端数据信息
     * @return 当当ios后端数据集合
     */
	public List<DangdangIosAdd> selectDangdangIosAddList(DangdangIosAdd dangdangIosAdd);
	
	/**
     * 新增当当ios后端数据
     * 
     * @param dangdangIosAdd 当当ios后端数据信息
     * @return 结果
     */
	public int insertDangdangIosAdd(DangdangIosAdd dangdangIosAdd);
	
	/**
     * 修改当当ios后端数据
     * 
     * @param dangdangIosAdd 当当ios后端数据信息
     * @return 结果
     */
	public int updateDangdangIosAdd(DangdangIosAdd dangdangIosAdd);
		
	/**
     * 删除当当ios后端数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDangdangIosAddByIds(String ids);

	public String importBwFront(List<DangdangIosAdd> bwList, Boolean isUpdateSupport, String operName,String fileName) throws Exception;

}
