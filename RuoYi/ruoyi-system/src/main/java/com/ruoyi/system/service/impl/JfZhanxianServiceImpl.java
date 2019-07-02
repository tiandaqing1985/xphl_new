package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.JfZhanxianMapper;
import com.ruoyi.system.domain.JfZhanxian;
import com.ruoyi.system.service.IJfZhanxianService;
import com.ruoyi.common.core.text.Convert;

/**
 * 玖富展现 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
@Service
public class JfZhanxianServiceImpl implements IJfZhanxianService 
{
	@Autowired
	private JfZhanxianMapper jfZhanxianMapper;

	/**
     * 查询玖富展现信息
     * 
     * @param id 玖富展现ID
     * @return 玖富展现信息
     */
    @Override
	public JfZhanxian selectJfZhanxianById(Long id)
	{
	    return jfZhanxianMapper.selectJfZhanxianById(id);
	}
	
	/**
     * 查询玖富展现列表
     * 
     * @param jfZhanxian 玖富展现信息
     * @return 玖富展现集合
     */
	@Override
	public List<JfZhanxian> selectJfZhanxianList(JfZhanxian jfZhanxian)
	{
	    return jfZhanxianMapper.selectJfZhanxianList(jfZhanxian);
	}
	
    /**
     * 新增玖富展现
     * 
     * @param jfZhanxian 玖富展现信息
     * @return 结果
     */
	@Override
	public int insertJfZhanxian(JfZhanxian jfZhanxian)
	{
	    return jfZhanxianMapper.insertJfZhanxian(jfZhanxian);
	}
	
	/**
     * 修改玖富展现
     * 
     * @param jfZhanxian 玖富展现信息
     * @return 结果
     */
	@Override
	public int updateJfZhanxian(JfZhanxian jfZhanxian)
	{
	    return jfZhanxianMapper.updateJfZhanxian(jfZhanxian);
	}

	/**
     * 删除玖富展现对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteJfZhanxianByIds(String ids)
	{
		return jfZhanxianMapper.deleteJfZhanxianByIds(Convert.toStrArray(ids));
	}
	
	@Override
	public int createJfZhanxianData(JfZhanxian jfZhanxian)
	{
	    return jfZhanxianMapper.createJfZhanxianData(jfZhanxian);
	}
}
