package com.ruoyi.system.service;

import com.ruoyi.system.domain.Holiday;
import com.ruoyi.system.domain.RestHoliday;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.UserApply;

import java.util.List;

/**
 * 假期 服务层
 * 
 * @author ruoyi
 * @date 2019-06-17
 */
public interface IHolidayService 
{
	/**
     * 查询假期信息
     * 
     * @param id 假期ID
     * @return 假期信息
     */
	public Holiday selectHolidayById(Integer id);
	
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
     * 删除假期信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHolidayByIds(String ids);
	/**
	 * 根据生成日期查出失效日期
	 */
	public String selectOverdateByCreatedate(String createdate);
	
	/**
	 * 根据用户id查出年假余额
	 */
	public Double selectHolidaySumByUserId(Holiday holiday);
	
	public List<RestHoliday> selectHolidayRestList(List<SysUser> userList);
	
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
	 * 根据原申请还原假期和假期使用记录
	 * @param applyId 申请id
	 * @param useState 使用状态(1申请中，2已使用，3撤销，4销假，5被销假，6被驳回)
	 */
	public void restoreHoliday(Long applyId, String useState);
	
	/**
	 * 更新假期表，生成年假记录表
	 */
	public void createHolidayAndRecord(UserApply userApply);
}
