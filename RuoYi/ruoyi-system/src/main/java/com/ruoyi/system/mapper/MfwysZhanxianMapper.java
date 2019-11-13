package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.MfwysEnd;
import com.ruoyi.system.domain.MfwysZhanxian;
import java.util.List;	

/**
 * 马蜂窝原生展现 数据层
 * 
 * @author ruoyi
 * @date 2019-07-05
 */
public interface MfwysZhanxianMapper 
{
	/**
     * 查询马蜂窝原生展现信息
     * 
     * @param id 马蜂窝原生展现ID
     * @return 马蜂窝原生展现信息
     */
	public MfwysZhanxian selectMfwysZhanxianById(Long id);
	
	/**
     * 查询马蜂窝原生展现列表
     * 
     * @param mfwysZhanxian 马蜂窝原生展现信息
     * @return 马蜂窝原生展现集合
     */
	public List<MfwysZhanxian> selectMfwysZhanxianList(MfwysZhanxian mfwysZhanxian);
	
	/**
     * 新增马蜂窝原生展现
     * 
     * @param mfwysZhanxian 马蜂窝原生展现信息
     * @return 结果
     */
	public int insertMfwysZhanxian(MfwysZhanxian mfwysZhanxian);
	
	/**
     * 修改马蜂窝原生展现
     * 
     * @param mfwysZhanxian 马蜂窝原生展现信息
     * @return 结果
     */
	public int updateMfwysZhanxian(MfwysZhanxian mfwysZhanxian);
	
	/**
     * 删除马蜂窝原生展现
     * 
     * @param id 马蜂窝原生展现ID
     * @return 结果
     */
	public int deleteMfwysZhanxianById(Long id);
	
	/**
     * 批量删除马蜂窝原生展现
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMfwysZhanxianByIds(String[] ids);
	
	public int  deleteMfwysZhanxianAll();
	
	public MfwysZhanxian selectMfwysEndByKeywordid(MfwysZhanxian mfwysZhanxian);
	
	public List<MfwysZhanxian>  selectByChnnelPackage(MfwysZhanxian mfwysZhanxian);
	
}