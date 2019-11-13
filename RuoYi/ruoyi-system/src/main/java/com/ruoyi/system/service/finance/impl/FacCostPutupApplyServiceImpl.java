package com.ruoyi.system.service.finance.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.finance.FacCostPutupApply;
import com.ruoyi.system.mapper.finance.FacCostPutupApplyMapper;
import com.ruoyi.system.service.finance.IFacCostPutupApplyService;

/**
 * 差旅住宿 服务层实现
 * 
 * @author ruoyi
 * @date 2019-10-14
 */
@Service
public class FacCostPutupApplyServiceImpl implements IFacCostPutupApplyService 
{
	@Autowired
	private FacCostPutupApplyMapper facCostPutupApplyMapper;

	/**
     * 查询差旅住宿信息
     * 
     * @param id 差旅住宿ID
     * @return 差旅住宿信息
     */
    @Override
	public FacCostPutupApply selectFacCostPutupApplyById(Long id)
	{
	    return facCostPutupApplyMapper.selectFacCostPutupApplyById(id);
	}
	
	/**
     * 查询差旅住宿列表
     * 
     * @param facCostPutupApply 差旅住宿信息
     * @return 差旅住宿集合
     */
	@Override
	public List<FacCostPutupApply> selectFacCostPutupApplyList(FacCostPutupApply facCostPutupApply)
	{
	    return facCostPutupApplyMapper.selectFacCostPutupApplyList(facCostPutupApply);
	}
	
    /**
     * 新增差旅住宿
     * 
     * @param facCostPutupApply 差旅住宿信息
     * @return 结果
     */
	@Override
	public int insertFacCostPutupApply(FacCostPutupApply facCostPutupApply)
	{
	    return facCostPutupApplyMapper.insertFacCostPutupApply(facCostPutupApply);
	}
	
	/**
     * 修改差旅住宿
     * 
     * @param facCostPutupApply 差旅住宿信息
     * @return 结果
     */
	@Override
	public int updateFacCostPutupApply(FacCostPutupApply facCostPutupApply)
	{
	    return facCostPutupApplyMapper.updateFacCostPutupApply(facCostPutupApply);
	}

	/**
     * 删除差旅住宿对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFacCostPutupApplyByIds(String ids)
	{
		return facCostPutupApplyMapper.deleteFacCostPutupApplyByIds(Convert.toStrArray(ids));
	}

	/*
		查询金额合计
	 */
	@Override
	public double selectMoney(String num) {
		return facCostPutupApplyMapper.selectMoney(num);
	}
}
