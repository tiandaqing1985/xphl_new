package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.RxZhanxianMapper;
import com.ruoyi.system.domain.RxZhanxian;
import com.ruoyi.system.service.IRxZhanxianService;
import com.ruoyi.common.core.text.Convert;

/**
 * 瑞幸展现 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-05
 */
@Service
public class RxZhanxianServiceImpl implements IRxZhanxianService 
{
	@Autowired
	private RxZhanxianMapper rxZhanxianMapper;

	/**
     * 查询瑞幸展现信息
     * 
     * @param id 瑞幸展现ID
     * @return 瑞幸展现信息
     */
    @Override
	public RxZhanxian selectRxZhanxianById(Integer id)
	{
	    return rxZhanxianMapper.selectRxZhanxianById(id);
	}
	
	/**
     * 查询瑞幸展现列表
     * 
     * @param rxZhanxian 瑞幸展现信息
     * @return 瑞幸展现集合
     */
	@Override
	public List<RxZhanxian> selectRxZhanxianList(RxZhanxian rxZhanxian)
	{
	    return rxZhanxianMapper.selectRxZhanxianList(rxZhanxian);
	}
	
    /**
     * 新增瑞幸展现
     * 
     * @param rxZhanxian 瑞幸展现信息
     * @return 结果
     */
	@Override
	public int insertRxZhanxian(RxZhanxian rxZhanxian)
	{
	    return rxZhanxianMapper.insertRxZhanxian(rxZhanxian);
	}
	
	/**
     * 修改瑞幸展现
     * 
     * @param rxZhanxian 瑞幸展现信息
     * @return 结果
     */
	@Override
	public int updateRxZhanxian(RxZhanxian rxZhanxian)
	{
	    return rxZhanxianMapper.updateRxZhanxian(rxZhanxian);
	}

	/**
     * 删除瑞幸展现对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteRxZhanxianByIds(String ids)
	{
		return rxZhanxianMapper.deleteRxZhanxianByIds(Convert.toStrArray(ids));
	}

	/**
	 * 从瑞幸前端查询到瑞幸展现表所需要的数据
	 */
	@Override
	public List<RxZhanxian> selectRxZhanxian() {
		
		return rxZhanxianMapper.selectRxZhanxian();
	}
	
}
