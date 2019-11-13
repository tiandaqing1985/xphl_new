package com.ruoyi.system.service;

import com.ruoyi.system.domain.BqFront;
import com.ruoyi.system.domain.RxFront;
import java.util.List;

/**
 * 瑞幸前端 服务层
 * 
 * @author ruoyi
 * @date 2019-07-05
 */
public interface IRxFrontService 
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
     * 删除瑞幸前端信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteRxFrontByIds(String ids);
	
	public String importRxFront(List<RxFront> rxfList, Boolean isUpdateSupport, String operName);
	
	/**
	 * 清除所有前端数据
	 * @return
	 */
	public int deleteAllRxFront();
}
