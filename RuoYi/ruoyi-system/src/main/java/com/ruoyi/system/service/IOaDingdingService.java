package com.ruoyi.system.service;

import com.ruoyi.system.domain.Dingding;
import com.ruoyi.system.domain.OaDingding;
import java.util.List;

/**
 * 钉钉考勤数据 服务层
 * 
 * @author ruoyi
 * @date 2019-07-26
 */
public interface IOaDingdingService 
{
	/**
     * 查询钉钉考勤数据信息
     * 
     * @param userId 钉钉考勤数据ID
     * @return 钉钉考勤数据信息
     */
	public OaDingding selectOaDingdingById(String userId);
	
	/**
     * 查询钉钉考勤数据列表
     * 
     * @param oaDingding 钉钉考勤数据信息
     * @return 钉钉考勤数据集合
     */
	public List<Dingding> selectOaDingdingList(Dingding ding);
	
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
     * 删除钉钉考勤数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOaDingdingByIds(String ids);
	
}
