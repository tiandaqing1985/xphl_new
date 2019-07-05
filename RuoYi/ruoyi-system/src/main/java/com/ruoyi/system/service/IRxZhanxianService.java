package com.ruoyi.system.service;

import com.ruoyi.system.domain.RxZhanxian;
import java.util.List;

/**
 * 瑞幸展现 服务层
 * 
 * @author ruoyi
 * @date 2019-07-05
 */
public interface IRxZhanxianService 
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
     * 删除瑞幸展现信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteRxZhanxianByIds(String ids);
	
	/**
	 * 从瑞幸前端查询到瑞幸展现表所需要的数据
	 */
	public List<RxZhanxian> selectRxZhanxian();
	
	/**
	 * 清除瑞幸展现数据
	 */
	public int deleteAllRxZhanxian();
	
	/**
	 * 层级搜索
	 */
	public List<RxZhanxian> selectRxZhanxianSumList(String selectflag);
}
