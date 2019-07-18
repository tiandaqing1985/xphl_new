package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MfwysParameterMapper;
import com.ruoyi.system.domain.MfwysParameter;
import com.ruoyi.system.service.IMfwysParameterService;
import com.ruoyi.common.core.text.Convert;

/**
 * 马蜂窝原生参数 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-18
 */
@Service
public class MfwysParameterServiceImpl implements IMfwysParameterService 
{
	@Autowired
	private MfwysParameterMapper mfwysParameterMapper;

	/**
     * 查询马蜂窝原生参数信息
     * 
     * @param id 马蜂窝原生参数ID
     * @return 马蜂窝原生参数信息
     */
    @Override
	public MfwysParameter selectMfwysParameterById(Long id)
	{
	    return mfwysParameterMapper.selectMfwysParameterById(id);
	}
	
	/**
     * 查询马蜂窝原生参数列表
     * 
     * @param mfwysParameter 马蜂窝原生参数信息
     * @return 马蜂窝原生参数集合
     */
	@Override
	public List<MfwysParameter> selectMfwysParameterList(MfwysParameter mfwysParameter)
	{
	    return mfwysParameterMapper.selectMfwysParameterList(mfwysParameter);
	}
	
    /**
     * 新增马蜂窝原生参数
     * 
     * @param mfwysParameter 马蜂窝原生参数信息
     * @return 结果
     */
	@Override
	public int insertMfwysParameter(MfwysParameter mfwysParameter)
	{
	    return mfwysParameterMapper.insertMfwysParameter(mfwysParameter);
	}
	
	/**
     * 修改马蜂窝原生参数
     * 
     * @param mfwysParameter 马蜂窝原生参数信息
     * @return 结果
     */
	@Override
	public int updateMfwysParameter(MfwysParameter mfwysParameter)
	{
	    return mfwysParameterMapper.updateMfwysParameter(mfwysParameter);
	}

	/**
     * 删除马蜂窝原生参数对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMfwysParameterByIds(String ids)
	{
		return mfwysParameterMapper.deleteMfwysParameterByIds(Convert.toStrArray(ids));
	}
	
}
