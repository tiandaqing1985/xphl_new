package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BqZhanxianMapper;
import com.ruoyi.system.domain.BqZhanxian;
import com.ruoyi.system.service.IBqZhanxianService;
import com.ruoyi.common.core.text.Convert;

/**
 * 北汽展现 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-04
 */
@Service
public class BqZhanxianServiceImpl implements IBqZhanxianService 
{
	@Autowired
	private BqZhanxianMapper bqZhanxianMapper;

	/**
     * 查询北汽展现信息
     * 
     * @param id 北汽展现ID
     * @return 北汽展现信息
     */
    @Override
	public BqZhanxian selectBqZhanxianById(Long id)
	{
	    return bqZhanxianMapper.selectBqZhanxianById(id);
	}
	
	/**
     * 查询北汽展现列表
     * 
     * @param bqZhanxian 北汽展现信息
     * @return 北汽展现集合
     */
	@Override
	public List<BqZhanxian> selectBqZhanxianList(BqZhanxian bqZhanxian)
	{
	    return bqZhanxianMapper.selectBqZhanxianList(bqZhanxian);
	}
	
    /**
     * 新增北汽展现
     * 
     * @param bqZhanxian 北汽展现信息
     * @return 结果
     */
	@Override
	public int insertBqZhanxian(BqZhanxian bqZhanxian)
	{
	    return bqZhanxianMapper.insertBqZhanxian(bqZhanxian);
	}
	
	/**
     * 修改北汽展现
     * 
     * @param bqZhanxian 北汽展现信息
     * @return 结果
     */
	@Override
	public int updateBqZhanxian(BqZhanxian bqZhanxian)
	{
	    return bqZhanxianMapper.updateBqZhanxian(bqZhanxian);
	}

	/**
     * 删除北汽展现对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBqZhanxianByIds(String ids)
	{
		return bqZhanxianMapper.deleteBqZhanxianByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<BqZhanxian> selectBqZhanxian() {
		
		return bqZhanxianMapper.selectBqZhanxian();
	}
	
	/**
	 * 清除所有数据
	 * @return
	 */
	@Override
	public int deleteAllBqZhanxian() {
		
		return bqZhanxianMapper.deleteAllBqZhanxian();
	}

	@Override
	public List<BqZhanxian> selectBqZhanxianSumList(String selectflag) {
		if("0".equals(selectflag)){
			return bqZhanxianMapper.selectBqZhanxianSumList();
		}else if("1".equals(selectflag)){
			return bqZhanxianMapper.selectBqZhanxianAccountSumList();
		}else if("2".equals(selectflag)){
			return bqZhanxianMapper.selectBqZhanxianPlanSumList();
		}else if("3".equals(selectflag)){
			return bqZhanxianMapper.selectBqZhanxianUnitSumList();
		}else if("4".equals(selectflag)){
			return bqZhanxianMapper.selectBqZhanxianKeywordSumList();
		}else{
			return bqZhanxianMapper.selectBqZhanxianSumList();
		}
		
	}
	
	
}
