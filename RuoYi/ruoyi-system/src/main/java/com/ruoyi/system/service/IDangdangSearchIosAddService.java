package com.ruoyi.system.service;

import com.ruoyi.system.domain.DangdangSearchAdd;
import com.ruoyi.system.domain.DangdangSearchIosAdd;
import java.util.List;

/**
 * 当当ios后端数据 服务层
 * 
 * @author ruoyi
 * @date 2019-07-15
 */
public interface IDangdangSearchIosAddService 
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
     * 删除当当ios后端数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDangdangSearchIosAddByIds(String ids);

	public String importBwFront(List<DangdangSearchIosAdd> bwList, Boolean isUpdateSupport, String operName);

}
