package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.MfwysEnd;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 马蜂窝原生后端 数据层
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
public interface MfwysEndMapper 
{
	/**
     * 查询马蜂窝原生后端信息
     * 
     * @param id 马蜂窝原生后端ID
     * @return 马蜂窝原生后端信息
     */
	public MfwysEnd selectMfwysEndById(Long id);
	
	/**
     * 查询马蜂窝原生后端列表
     * 
     * @param mfwysEnd 马蜂窝原生后端信息
     * @return 马蜂窝原生后端集合
     */
	public List<MfwysEnd> selectMfwysEndList(MfwysEnd mfwysEnd);
	
	/**
     * 新增马蜂窝原生后端
     * 
     * @param mfwysEnd 马蜂窝原生后端信息
     * @return 结果
     */
	public int insertMfwysEnd(MfwysEnd mfwysEnd);
	
	/**
     * 修改马蜂窝原生后端
     * 
     * @param mfwysEnd 马蜂窝原生后端信息
     * @return 结果
     */
	public int updateMfwysEnd(MfwysEnd mfwysEnd);
	
	/**
     * 删除马蜂窝原生后端
     * 
     * @param id 马蜂窝原生后端ID
     * @return 结果
     */
	public int deleteMfwysEndById(Long id);
	
	/**
     * 批量删除马蜂窝原生后端
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMfwysEndByIds(String[] ids);
	
	public  void batchInsert(@Param("list") List<MfwysEnd> list);
	
}