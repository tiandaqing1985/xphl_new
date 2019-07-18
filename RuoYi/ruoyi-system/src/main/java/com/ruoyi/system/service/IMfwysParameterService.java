package com.ruoyi.system.service;

import com.ruoyi.system.domain.MfwysParameter;
import java.util.List;

/**
 * 马蜂窝原生参数 服务层
 * 
 * @author ruoyi
 * @date 2019-07-18
 */
public interface IMfwysParameterService 
{
	/**
     * 查询马蜂窝原生参数信息
     * 
     * @param id 马蜂窝原生参数ID
     * @return 马蜂窝原生参数信息
     */
	public MfwysParameter selectMfwysParameterById(Long id);
	
	/**
     * 查询马蜂窝原生参数列表
     * 
     * @param mfwysParameter 马蜂窝原生参数信息
     * @return 马蜂窝原生参数集合
     */
	public List<MfwysParameter> selectMfwysParameterList(MfwysParameter mfwysParameter);
	
	/**
     * 新增马蜂窝原生参数
     * 
     * @param mfwysParameter 马蜂窝原生参数信息
     * @return 结果
     */
	public int insertMfwysParameter(MfwysParameter mfwysParameter);
	
	/**
     * 修改马蜂窝原生参数
     * 
     * @param mfwysParameter 马蜂窝原生参数信息
     * @return 结果
     */
	public int updateMfwysParameter(MfwysParameter mfwysParameter);
		
	/**
     * 删除马蜂窝原生参数信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMfwysParameterByIds(String ids);
	
}
