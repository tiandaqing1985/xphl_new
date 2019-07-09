package com.ruoyi.system.service;

import com.ruoyi.system.domain.MfwysFront;
import java.util.List;

/**
 * 马蜂窝原生前端 服务层
 * 
 * @author ruoyi
 * @date 2019-07-04
 */
public interface IMfwysFrontService 
{
	/**
     * 查询马蜂窝原生前端信息
     * 
     * @param id 马蜂窝原生前端ID
     * @return 马蜂窝原生前端信息
     */
	public MfwysFront selectMfwysFrontById(Long id);
	
	/**
     * 查询马蜂窝原生前端列表
     * 
     * @param mfwysFront 马蜂窝原生前端信息
     * @return 马蜂窝原生前端集合
     */
	public List<MfwysFront> selectMfwysFrontList(MfwysFront mfwysFront);
	
	/**
     * 新增马蜂窝原生前端
     * 
     * @param mfwysFront 马蜂窝原生前端信息
     * @return 结果
     */
	public int insertMfwysFront(MfwysFront mfwysFront);
	
	/**
     * 修改马蜂窝原生前端
     * 
     * @param mfwysFront 马蜂窝原生前端信息
     * @return 结果
     */
	public int updateMfwysFront(MfwysFront mfwysFront);
		
	/**
     * 删除马蜂窝原生前端信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMfwysFrontByIds(String ids);
	
	
	public String importMfwysFront(List<MfwysFront> mfwysList, Boolean isUpdateSupport, String operName);
	
}
