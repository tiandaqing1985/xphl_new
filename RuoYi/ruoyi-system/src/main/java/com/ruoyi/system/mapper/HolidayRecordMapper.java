package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.HolidayRecord;
import java.util.List;	

/**
 * 假期使用记录 数据层
 * 
 * @author ruoyi
 * @date 2019-06-24
 */
public interface HolidayRecordMapper 
{
	/**
     * 查询假期使用记录信息
     * 
     * @param id 假期使用记录ID
     * @return 假期使用记录信息
     */
	public HolidayRecord selectHolidayRecordById(Long id);
	
	/**
     * 查询假期使用记录列表
     * 
     * @param holidayRecord 假期使用记录信息
     * @return 假期使用记录集合
     */
	public List<HolidayRecord> selectHolidayRecordList(HolidayRecord holidayRecord);
	
	/**
     * 查询已使用假期使用记录列表
     * 
     * @param holidayRecord 假期使用记录信息
     * @return 假期使用记录集合
     */
	public List<HolidayRecord> selectUsedHolidayRecordList(HolidayRecord holidayRecord);
	
	/**
     * 新增假期使用记录
     * 
     * @param holidayRecord 假期使用记录信息
     * @return 结果
     */
	public int insertHolidayRecord(HolidayRecord holidayRecord);
	
	/**
     * 修改假期使用记录
     * 
     * @param holidayRecord 假期使用记录信息
     * @return 结果
     */
	public int updateHolidayRecord(HolidayRecord holidayRecord);
	
	/**
     * 删除假期使用记录
     * 
     * @param id 假期使用记录ID
     * @return 结果
     */
	public int deleteHolidayRecordById(Long id);
	
	/**
     * 批量删除假期使用记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHolidayRecordByIds(String[] ids);
	
	/**
	 * 根据申请id更改假期使用记录
	 * @param holidayRecord
	 * @return
	 */
	public int updateHolidayRecordByApplyId(HolidayRecord holidayRecord);
}