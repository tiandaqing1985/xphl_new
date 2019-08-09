package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Dingding;
import com.ruoyi.system.domain.OaDingding;
import java.util.List;	

/**
 * 钉钉考勤数据 数据层
 * 
 * @author ruoyi
 * @date 2019-07-26
 */
public interface OaDingdingMapper 
{
	/**
     * 查询钉钉考勤数据信息
     * 
     * @param userId 钉钉考勤数据ID
     * @return 钉钉考勤数据信息
     */
	public OaDingding selectOaDingdingById(Long userId);
	
	/**
     * 查询钉钉考勤数据列表
     * 
     * @param oaDingding 钉钉考勤数据信息
     * @return 钉钉考勤数据集合
     */
	public List<OaDingding> selectOaDingdingList(OaDingding oaDingding);
	
	/**
     * 新增钉钉考勤数据
     * 
     * @param oaDingding 钉钉考勤数据信息
     * @return 结果
     */
	public int insertOaDingding(OaDingding oaDingding);
	
	/**
	 * 批量插入钉钉考勤数据
	 * @param dingList
	 * @return
	 */
	public int insertForeach(List<OaDingding> dingList);
	
	/**
     * 修改钉钉考勤数据
     * 
     * @param oaDingding 钉钉考勤数据信息
     * @return 结果
     */
	public int updateOaDingding(OaDingding oaDingding);
	
	/**
     * 删除钉钉考勤数据
     * 
     * @param userId 钉钉考勤数据ID
     * @return 结果
     */
	public int deleteOaDingdingById(Long userId);
	
	/**
     * 批量删除钉钉考勤数据
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteOaDingdingByIds(Long[] userIds);
	
	/**
	 * 查看钉钉考勤数据
	 * @param ding
	 * @return
	 */
	public List<Dingding> selectDingData(Dingding ding);
	
	/**
	 * 根据请假时间、外出报备时间修改钉钉考勤结果
	 * @param ding
	 * @return
	 */
	public int updateOaDingDingByTime(Dingding ding);
	
	/**
	 * 根据user_name checkType startTime endTime查询
	 * @param ding
	 * @return
	 */
	public List<Dingding> selectOaDingdingListByCondition(Dingding ding);
}