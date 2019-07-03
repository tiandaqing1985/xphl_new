package com.ruoyi.system.service;

import com.ruoyi.system.domain.MfwysEnd;
import java.util.List;

/**
 * 马蜂窝原生后端 服务层
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
public interface IMfwysEndService 
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
     * 删除马蜂窝原生后端信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMfwysEndByIds(String ids);
	
	public String importMfwysEnd(List<MfwysEnd> mfwysList, Boolean isUpdateSupport, String operName);
	
	
}
