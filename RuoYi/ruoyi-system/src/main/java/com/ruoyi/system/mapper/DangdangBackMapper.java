package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DangdangBack;
import com.ruoyi.system.domain.DangdangBaiduAdd;
import com.ruoyi.system.domain.DangdangSearchAdd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 当当后端汇总 数据层
 * 
 * @author ruoyi
 * @date 2019-07-18
 */
public interface DangdangBackMapper 
{
	/**
     * 查询当当后端汇总信息
     * 
     * @param id 当当后端汇总ID
     * @return 当当后端汇总信息
     */
	public DangdangBack selectDangdangBackById(Integer id);
	
	/**
     * 查询当当后端汇总列表
     * 
     * @param dangdangBack 当当后端汇总信息
     * @return 当当后端汇总集合
     */
	public List<DangdangBack> selectDangdangBackList(DangdangBack dangdangBack);
	
	/**
     * 新增当当后端汇总
     * 
     * @param dangdangBack 当当后端汇总信息
     * @return 结果
     */
	public int insertDangdangBack(DangdangBack dangdangBack);
	
	/**
     * 修改当当后端汇总
     * 
     * @param dangdangBack 当当后端汇总信息
     * @return 结果
     */
	public int updateDangdangBack(DangdangBack dangdangBack);
	
	/**
     * 删除当当后端汇总
     * 
     * @param id 当当后端汇总ID
     * @return 结果
     */
	public int deleteDangdangBackById(Integer id);
	
	/**
     * 批量删除当当后端汇总
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDangdangBackByIds(String[] ids);

	void batchInsert(@Param("list") List<DangdangBack> listPage);
	/**
	 * 修改
	 *
	 * @param str
	 * @return
	 */
	public int updateGroupword(String str);

	/**
	 * @return 更改百度5201
	 */
	public int updateBaiduIdentification();

	List<DangdangBack> queryUrl(DangdangBack date);

	/**
	 * 查询移动端url的匹配
	 * @param
	 * @return
	 */
	List<DangdangBack> queryMod(DangdangBack date);



}