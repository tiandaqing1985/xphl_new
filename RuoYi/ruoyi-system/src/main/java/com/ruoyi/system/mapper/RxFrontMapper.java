package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.BqFront;
import com.ruoyi.system.domain.RxFront;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 瑞幸前端 数据层
 * 
 * @author ruoyi
 * @date 2019-07-05
 */
public interface RxFrontMapper 
{
	/**
     * 查询瑞幸前端信息
     * 
     * @param id 瑞幸前端ID
     * @return 瑞幸前端信息
     */
	public RxFront selectRxFrontById(Integer id);
	
	/**
     * 查询瑞幸前端列表
     * 
     * @param rxFront 瑞幸前端信息
     * @return 瑞幸前端集合
     */
	public List<RxFront> selectRxFrontList(RxFront rxFront);
	
	/**
     * 新增瑞幸前端
     * 
     * @param rxFront 瑞幸前端信息
     * @return 结果
     */
	public int insertRxFront(RxFront rxFront);
	
	/**
     * 修改瑞幸前端
     * 
     * @param rxFront 瑞幸前端信息
     * @return 结果
     */
	public int updateRxFront(RxFront rxFront);
	
	/**
     * 删除瑞幸前端
     * 
     * @param id 瑞幸前端ID
     * @return 结果
     */
	public int deleteRxFrontById(Integer id);
	
	/**
     * 批量删除瑞幸前端
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteRxFrontByIds(String[] ids);
	/**
	 * 批量插入北汽前端数据
	 * @param list
	 */
	public void batchInsert(@Param("list") List<RxFront> list);
	
	/**
	 * 修改
	 * @param str
	 * @return
	 */
	public int updateGroupword(String  str);
	
	/**
	 * 清除所有前端数据
	 * @return
	 */
	public int deleteAllRxFront();
}