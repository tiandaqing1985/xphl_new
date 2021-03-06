package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Holiday;
import com.ruoyi.system.domain.RestHoliday;
import com.ruoyi.system.domain.SysUser;

import java.util.List;	

/**
 * 假期 数据层
 * 
 * @author ruoyi
 * @date 2019-06-17
 */
public interface HolidayMapper 
{
	/**
     * 查询假期信息
     * 
     * @param id 假期ID
     * @return 假期信息
     */
	public Holiday selectHolidayById(Integer id);
	
	/**
	 * 根据生效日期查询假期
	 * @param littledate
	 * @param bigdate
	 * @return
	 */
	public List<Holiday> selectHolidayListByCondition(Holiday holiday);


	/**
	 * 根据请假最后时间确定年假是否过期
	 * @param littledate
	 * @param bigdate
	 * @return
	 */
	public List<Holiday> selectHolidayOverdue(Holiday holiday);
	
	/**
     * 查询假期列表
     * 
     * @param holiday 假期信息
     * @return 假期集合
     */
	public List<Holiday> selectHolidayList(Holiday holiday);
	
	/**
     * 新增假期
     * 
     * @param holiday 假期信息
     * @return 结果
     */
	public int insertHoliday(Holiday holiday);
	
	/**
     * 修改假期
     * 
     * @param holiday 假期信息
     * @return 结果
     */
	public int updateHoliday(Holiday holiday);
	
	/**
     * 删除假期
     * 
     * @param id 假期ID
     * @return 结果
     */
	public int deleteHolidayById(Integer id);
	
	public int deleteHolidayByApplyId(Long applyId);
	
	/**
     * 批量删除假期
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHolidayByIds(String[] ids);
	
	/**
	 * 根据生成日期查出失效日期
	 */
	public String selectOverdateByCreatedate(String createdate);
	
	/**
	 * 根据用户id查出年假余额
	 */
	public Double selectHolidaySumByUserId(Holiday holiday);
	
	/**
	 * 根据日期查出当月的生成的年假
	 * @param holiday
	 * @return
	 */
	public Holiday selectHolidayByDate(Holiday holiday);
	
	/**
	 * 查询假期余额
	 * @param sysUser
	 * @return
	 */
	public List<RestHoliday> selectRestByUserId(SysUser sysUser);
	
	/**
	 * 查询我的假期余额
	 * @param sysUser
	 * @return
	 */
	public List<RestHoliday> selectMyRestByUserId(SysUser sysUser);
	
	/**
	 * 根据年假生成有效时间查询假期
	 * @param holiday
	 * @return
	 */
	public long selectCountCreateDate(Holiday holiday);
	
	/**
	 * 根据失效时间查询假期
	 * @param holiday
	 * @return
	 */
	public long selectCountHoliday(Holiday holiday);
	
	/**
	 * 根据失效时间修改假期
	 * @param holiday
	 * @return
	 */
	public long updateHolidayValue(Holiday holiday);
}