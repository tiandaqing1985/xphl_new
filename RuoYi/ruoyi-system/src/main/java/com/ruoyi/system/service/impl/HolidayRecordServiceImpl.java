package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.HolidayRecordMapper;
import com.ruoyi.system.domain.HolidayRecord;
import com.ruoyi.system.service.IHolidayRecordService;
import com.ruoyi.common.core.text.Convert;

/**
 * 假期使用记录 服务层实现
 * 
 * @author ruoyi
 * @date 2019-06-24
 */
@Service
public class HolidayRecordServiceImpl implements IHolidayRecordService 
{
	@Autowired
	private HolidayRecordMapper holidayRecordMapper;

	/**
     * 查询假期使用记录信息
     * 
     * @param id 假期使用记录ID
     * @return 假期使用记录信息
     */
    @Override
	public HolidayRecord selectHolidayRecordById(Long id)
	{
	    return holidayRecordMapper.selectHolidayRecordById(id);
	}
	
	/**
     * 查询假期使用记录列表
     * 
     * @param holidayRecord 假期使用记录信息
     * @return 假期使用记录集合
     */
	@Override
	public List<HolidayRecord> selectHolidayRecordList(HolidayRecord holidayRecord)
	{
	    return holidayRecordMapper.selectHolidayRecordList(holidayRecord);
	}
	
    /**
     * 新增假期使用记录
     * 
     * @param holidayRecord 假期使用记录信息
     * @return 结果
     */
	@Override
	public int insertHolidayRecord(HolidayRecord holidayRecord)
	{
	    return holidayRecordMapper.insertHolidayRecord(holidayRecord);
	}
	
	/**
     * 修改假期使用记录
     * 
     * @param holidayRecord 假期使用记录信息
     * @return 结果
     */
	@Override
	public int updateHolidayRecord(HolidayRecord holidayRecord)
	{
	    return holidayRecordMapper.updateHolidayRecord(holidayRecord);
	}

	/**
     * 删除假期使用记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHolidayRecordByIds(String ids)
	{
		return holidayRecordMapper.deleteHolidayRecordByIds(Convert.toStrArray(ids));
	}

	/**
     * 查询已使用假期使用记录列表
     * 
     * @param holidayRecord 假期使用记录信息
     * @return 假期使用记录集合
     */
	@Override
	public List<HolidayRecord> selectUsedHolidayRecordList(HolidayRecord holidayRecord) {
		
		return holidayRecordMapper.selectUsedHolidayRecordList(holidayRecord);
	}

	/**
	 * 根据申请id更改假期使用记录
	 * @param holidayRecord
	 * @return
	 */
	@Override
	public int updateHolidayRecordByApplyId(HolidayRecord holidayRecord) {
		
		return holidayRecordMapper.updateHolidayRecordByApplyId(holidayRecord);
	}
	
}
