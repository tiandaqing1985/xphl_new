package com.ruoyi.system.service;

import com.ruoyi.system.domain.WorkingCalendar;
import java.util.List;

/**
 * 节假日数据 服务层
 * 
 * @author ruoyi
 * @date 2019-08-27
 */
public interface IOaWorkingCalendarService 
{
	/**
     * 查询节假日数据信息
     * 
     * @param id 节假日数据ID
     * @return 节假日数据信息
     */
	public WorkingCalendar selectWorkingCalendarById(Integer id);
	
	/**
     * 查询节假日数据列表
     * 
     * @param workingCalendar 节假日数据信息
     * @return 节假日数据集合
     */
	public List<WorkingCalendar> selectWorkingCalendarList(WorkingCalendar workingCalendar);
	
	/**
     * 新增节假日数据
     * 
     * @param workingCalendar 节假日数据信息
     * @return 结果
     */
	public int insertWorkingCalendar(WorkingCalendar workingCalendar);
	
	/**
     * 修改节假日数据
     * 
     * @param workingCalendar 节假日数据信息
     * @return 结果
     */
	public int updateWorkingCalendar(WorkingCalendar workingCalendar);
		
	/**
     * 删除节假日数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWorkingCalendarByIds(String ids);
	
}
