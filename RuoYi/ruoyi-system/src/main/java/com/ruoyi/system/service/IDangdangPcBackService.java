package com.ruoyi.system.service;

import com.ruoyi.system.domain.DangdangPcBack;
import com.ruoyi.system.domain.DangdangPcMatch;

import java.util.List;

/**
 * 当当pc后端百度数据明细 服务层
 * 
 * @author ruoyi
 * @date 2019-07-19
 */
public interface IDangdangPcBackService 
{
	/**
     * 查询当当pc后端百度数据明细信息
     * 
     * @param id 当当pc后端百度数据明细ID
     * @return 当当pc后端百度数据明细信息
     */
	public DangdangPcBack selectDangdangPcBackById(Integer id);
	
	/**
     * 查询当当pc后端百度数据明细列表
     * 
     * @param dangdangPcBack 当当pc后端百度数据明细信息
     * @return 当当pc后端百度数据明细集合
     */
	public List<DangdangPcBack> selectDangdangPcBackList(DangdangPcBack dangdangPcBack);
	
	/**
     * 新增当当pc后端百度数据明细
     * 
     * @param dangdangPcBack 当当pc后端百度数据明细信息
     * @return 结果
     */
	public int insertDangdangPcBack(DangdangPcBack dangdangPcBack);
	
	/**
     * 修改当当pc后端百度数据明细
     * 
     * @param dangdangPcBack 当当pc后端百度数据明细信息
     * @return 结果
     */
	public int updateDangdangPcBack(DangdangPcBack dangdangPcBack);
		
	/**
     * 删除当当pc后端百度数据明细信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDangdangPcBackByIds(String ids);

	public String importBwFront(List<DangdangPcBack> bwList, Boolean isUpdateSupport, String operName);

}
