package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.RxZhanxian;
import java.util.List;	

/**
 * 瑞幸展现 数据层
 * 
 * @author ruoyi
 * @date 2019-07-05
 */
public interface RxZhanxianMapper 
{
	/**
     * 查询瑞幸展现信息
     * 
     * @param id 瑞幸展现ID
     * @return 瑞幸展现信息
     */
	public RxZhanxian selectRxZhanxianById(Integer id);
	
	/**
     * 查询瑞幸展现列表
     * 
     * @param rxZhanxian 瑞幸展现信息
     * @return 瑞幸展现集合
     */
	public List<RxZhanxian> selectRxZhanxianList(RxZhanxian rxZhanxian);
	
	/**
     * 新增瑞幸展现
     * 
     * @param rxZhanxian 瑞幸展现信息
     * @return 结果
     */
	public int insertRxZhanxian(RxZhanxian rxZhanxian);
	
	/**
     * 修改瑞幸展现
     * 
     * @param rxZhanxian 瑞幸展现信息
     * @return 结果
     */
	public int updateRxZhanxian(RxZhanxian rxZhanxian);
	
	/**
     * 删除瑞幸展现
     * 
     * @param id 瑞幸展现ID
     * @return 结果
     */
	public int deleteRxZhanxianById(Integer id);
	
	/**
     * 批量删除瑞幸展现
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteRxZhanxianByIds(String[] ids);
	
	/**
	 * 从瑞幸前端查询到瑞幸展现表所需要的数据
	 */
	public List<RxZhanxian> selectRxZhanxian();
}