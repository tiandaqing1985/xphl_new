package com.ruoyi.system.service.finance.impl;

import java.util.List;

import com.ruoyi.system.domain.finance.FacCostPutupReimburse;
import com.ruoyi.system.mapper.finance.FacCostPutupReimburseMapper;
import com.ruoyi.system.service.finance.IFacCostPutupReimburseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 差旅住宿报销 服务层实现
 * 
 * @author ruoyi
 * @date 2019-11-12
 */
@Service
public class FacCostPutupReimburseServiceImpl implements IFacCostPutupReimburseService
{
	@Autowired
	private FacCostPutupReimburseMapper facCostPutupReimburseMapper;

	/**
     * 查询差旅住宿报销信息
     * 
     * @param id 差旅住宿报销ID
     * @return 差旅住宿报销信息
     */
    @Override
	public FacCostPutupReimburse selectFacCostPutupReimburseById(Long id)
	{
	    return facCostPutupReimburseMapper.selectFacCostPutupReimburseById(id);
	}
	
	/**
     * 查询差旅住宿报销列表
     * 
     * @param facCostPutupReimburse 差旅住宿报销信息
     * @return 差旅住宿报销集合
     */
	@Override
	public List<FacCostPutupReimburse> selectFacCostPutupReimburseList(FacCostPutupReimburse facCostPutupReimburse)
	{
	    return facCostPutupReimburseMapper.selectFacCostPutupReimburseList(facCostPutupReimburse);
	}
	
    /**
     * 新增差旅住宿报销
     * 
     * @param facCostPutupReimburse 差旅住宿报销信息
     * @return 结果
     */
	@Override
	public int insertFacCostPutupReimburse(FacCostPutupReimburse facCostPutupReimburse)
	{
	    return facCostPutupReimburseMapper.insertFacCostPutupReimburse(facCostPutupReimburse);
	}
	
	/**
     * 修改差旅住宿报销
     * 
     * @param facCostPutupReimburse 差旅住宿报销信息
     * @return 结果
     */
	@Override
	public int updateFacCostPutupReimburse(FacCostPutupReimburse facCostPutupReimburse)
	{
	    return facCostPutupReimburseMapper.updateFacCostPutupReimburse(facCostPutupReimburse);
	}

	/**
     * 删除差旅住宿报销对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFacCostPutupReimburseByIds(String ids)
	{
		return facCostPutupReimburseMapper.deleteFacCostPutupReimburseByIds(Convert.toStrArray(ids));
	}

	@Override
	public Double selectAmountByNum(String num) {
		return facCostPutupReimburseMapper.selectAmountByNum(num);
	}
}
