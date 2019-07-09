package com.ruoyi.system.service;

import com.ruoyi.system.domain.MfwysZhanxian;
import java.util.List;

/**
 * 马蜂窝原生展现 服务层
 * 
 * @author ruoyi
 * @date 2019-07-05
 */
public interface IMfwysZhanxianService 
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
     * 删除马蜂窝原生展现信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMfwysZhanxianByIds(String ids);
	
	
	public int createMfwysZhanxianData();
	
}
