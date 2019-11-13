package com.ruoyi.system.service.finance.impl;

import java.util.List;

import com.ruoyi.system.domain.finance.FacCostReimburse;
import com.ruoyi.system.mapper.finance.FacCostReimburseMapper;
import com.ruoyi.system.service.finance.IFacCostReimburseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 差旅报销 服务层实现
 * 
 * @author ruoyi
 * @date 2019-11-12
 */
@Service
public class FacCostReimburseServiceImpl implements IFacCostReimburseService
{
	@Autowired
	private FacCostReimburseMapper facCostReimburseMapper;

	/**
     * 查询差旅报销信息
     * 
     * @param id 差旅报销ID
     * @return 差旅报销信息
     */
    @Override
	public FacCostReimburse selectFacCostReimburseById(Long id)
	{
	    return facCostReimburseMapper.selectFacCostReimburseById(id);
	}
	
	/**
     * 查询差旅报销列表
     * 
     * @param facCostReimburse 差旅报销信息
     * @return 差旅报销集合
     */
	@Override
	public List<FacCostReimburse> selectFacCostReimburseList(FacCostReimburse facCostReimburse)
	{
	    return facCostReimburseMapper.selectFacCostReimburseList(facCostReimburse);
	}
	
    /**
     * 新增差旅报销
     * 
     * @param facCostReimburse 差旅报销信息
     * @return 结果
     */
	@Override
	public int insertFacCostReimburse(FacCostReimburse facCostReimburse)
	{
	    return facCostReimburseMapper.insertFacCostReimburse(facCostReimburse);
	}
	
	/**
     * 修改差旅报销
     * 
     * @param facCostReimburse 差旅报销信息
     * @return 结果
     */
	@Override
	public int updateFacCostReimburse(FacCostReimburse facCostReimburse)
	{
	    return facCostReimburseMapper.updateFacCostReimburse(facCostReimburse);
	}

	/**
     * 删除差旅报销对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFacCostReimburseByIds(String ids)
	{
		return facCostReimburseMapper.deleteFacCostReimburseByIds(Convert.toStrArray(ids));
	}

	@Override
	public FacCostReimburse selectFacCostReimburseByNum(String num) {
		return facCostReimburseMapper.selectFacCostReimburseByNum(num);
	}
}
