package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.mapper.OaWorkingCalendarMapper;
import com.ruoyi.system.service.IOaWorkingCalendarService;
import com.ruoyi.system.domain.WorkingCalendar;
import com.ruoyi.common.core.text.Convert;

/**
 * 节假日数据 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-27
 */
@Service
public class OaWorkingCalendarServiceImpl implements IOaWorkingCalendarService 
{
	@Autowired
	private OaWorkingCalendarMapper workingCalendarMapper;

	/**
     * 查询节假日数据信息
     * 
     * @param id 节假日数据ID
     * @return 节假日数据信息
     */
    @Override
	public WorkingCalendar selectWorkingCalendarById(Integer id)
	{
	    return workingCalendarMapper.selectWorkingCalendarById(id);
	}
	
	/**
     * 查询节假日数据列表
     * 
     * @param workingCalendar 节假日数据信息
     * @return 节假日数据集合
     */
	@Override
	public List<WorkingCalendar> selectWorkingCalendarList(WorkingCalendar workingCalendar)
	{
	    return workingCalendarMapper.selectWorkingCalendarList(workingCalendar);
	}
	
    /**
     * 新增节假日数据
     * 
     * @param workingCalendar 节假日数据信息
     * @return 结果
     */
	@Override
	public int insertWorkingCalendar(WorkingCalendar workingCalendar)
	{
	    return workingCalendarMapper.insertWorkingCalendar(workingCalendar);
	}
	
	/**
     * 修改节假日数据
     * 
     * @param workingCalendar 节假日数据信息
     * @return 结果
     */
	@Override
	public int updateWorkingCalendar(WorkingCalendar workingCalendar)
	{
	    return workingCalendarMapper.updateWorkingCalendar(workingCalendar);
	}

	/**
     * 删除节假日数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWorkingCalendarByIds(String ids)
	{
		return workingCalendarMapper.deleteWorkingCalendarByIds(Convert.toStrArray(ids));
	}
	
}
