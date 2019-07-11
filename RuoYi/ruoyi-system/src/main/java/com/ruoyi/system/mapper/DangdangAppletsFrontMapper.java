package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DangdangAppFront;
import com.ruoyi.system.domain.DangdangAppletsFront;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 当当小程序前端 数据层
 * 
 * @author ruoyi
 * @date 2019-07-11
 */
public interface DangdangAppletsFrontMapper 
{
	/**
     * 查询当当小程序前端信息
     * 
     * @param id 当当小程序前端ID
     * @return 当当小程序前端信息
     */
	public DangdangAppletsFront selectDangdangAppletsFrontById(Integer id);
	
	/**
     * 查询当当小程序前端列表
     * 
     * @param dangdangAppletsFront 当当小程序前端信息
     * @return 当当小程序前端集合
     */
	public List<DangdangAppletsFront> selectDangdangAppletsFrontList(DangdangAppletsFront dangdangAppletsFront);
	
	/**
     * 新增当当小程序前端
     * 
     * @param dangdangAppletsFront 当当小程序前端信息
     * @return 结果
     */
	public int insertDangdangAppletsFront(DangdangAppletsFront dangdangAppletsFront);
	
	/**
     * 修改当当小程序前端
     * 
     * @param dangdangAppletsFront 当当小程序前端信息
     * @return 结果
     */
	public int updateDangdangAppletsFront(DangdangAppletsFront dangdangAppletsFront);
	
	/**
     * 删除当当小程序前端
     * 
     * @param id 当当小程序前端ID
     * @return 结果
     */
	public int deleteDangdangAppletsFrontById(Integer id);
	
	/**
     * 批量删除当当小程序前端
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDangdangAppletsFrontByIds(String[] ids);

	void batchInsert(@Param("list") List<DangdangAppletsFront> listPage);
	/**
	 * 修改
	 * @param str
	 * @return
	 */
	public int updateGroupword(String  str);
}