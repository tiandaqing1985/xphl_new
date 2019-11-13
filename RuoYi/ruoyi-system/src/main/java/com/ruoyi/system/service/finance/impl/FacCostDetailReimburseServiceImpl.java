package com.ruoyi.system.service.finance.impl;

import com.ruoyi.system.domain.finance.FacCostDetailReimburse;
import com.ruoyi.system.mapper.finance.FacCostDetailReimburseMapper;
import com.ruoyi.system.service.finance.IFacCostDetailReimburseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

import java.util.List;

/**
 * 差旅申请详细报销列 服务层实现
 * 
 * @author ruoyi
 * @date 2019-11-12
 */
@Service
public class FacCostDetailReimburseServiceImpl implements IFacCostDetailReimburseService
{
	@Autowired
	private FacCostDetailReimburseMapper facCostDetailReimburseMapper;

	/**
     * 查询差旅申请详细报销列信息
     * 
     * @param id 差旅申请详细报销列ID
     * @return 差旅申请详细报销列信息
     */
    @Override
	public FacCostDetailReimburse selectFacCostDetailReimburseById(Long id)
	{
	    return facCostDetailReimburseMapper.selectFacCostDetailReimburseById(id);
	}
	
	/**
     * 查询差旅申请详细报销列列表
     * 
     * @param facCostDetailReimburse 差旅申请详细报销列信息
     * @return 差旅申请详细报销列集合
     */
	@Override
	public List<FacCostDetailReimburse> selectFacCostDetailReimburseList(FacCostDetailReimburse facCostDetailReimburse)
	{
	    return facCostDetailReimburseMapper.selectFacCostDetailReimburseList(facCostDetailReimburse);
	}
	
    /**
     * 新增差旅申请详细报销列
     * 
     * @param facCostDetailReimburse 差旅申请详细报销列信息
     * @return 结果
     */
	@Override
	public int insertFacCostDetailReimburse(FacCostDetailReimburse facCostDetailReimburse)
	{
	    return facCostDetailReimburseMapper.insertFacCostDetailReimburse(facCostDetailReimburse);
	}
	
	/**
     * 修改差旅申请详细报销列
     * 
     * @param facCostDetailReimburse 差旅申请详细报销列信息
     * @return 结果
     */
	@Override
	public int updateFacCostDetailReimburse(FacCostDetailReimburse facCostDetailReimburse)
	{
	    return facCostDetailReimburseMapper.updateFacCostDetailReimburse(facCostDetailReimburse);
	}

	/**
     * 删除差旅申请详细报销列对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFacCostDetailReimburseByIds(String ids)
	{
		return facCostDetailReimburseMapper.deleteFacCostDetailReimburseByIds(Convert.toStrArray(ids));
	}

	@Override
	public Double selectAmountByNum(String num) {
		return facCostDetailReimburseMapper.selectAmountByNum(num);
	}
}
