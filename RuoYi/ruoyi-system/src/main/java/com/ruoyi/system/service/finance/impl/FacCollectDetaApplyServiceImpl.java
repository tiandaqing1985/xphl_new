package com.ruoyi.system.service.finance.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.finance.FacCollectDetaApply;
import com.ruoyi.system.mapper.finance.FacCollectDetaApplyMapper;
import com.ruoyi.system.service.finance.IFacCollectDetaApplyService;

/**
 * 团建明细 服务层实现
 * 
 * @author ruoyi
 * @date 2019-09-04
 */
@Service
public class FacCollectDetaApplyServiceImpl implements IFacCollectDetaApplyService 
{
	@Autowired
	private FacCollectDetaApplyMapper facCollectDetaApplyMapper;

	/**
     * 查询团建明细信息
     * 
     * @param id 团建明细ID
     * @return 团建明细信息
     */
    @Override
	public FacCollectDetaApply selectFacCollectDetaApplyById(Long id)
	{
	    return facCollectDetaApplyMapper.selectFacCollectDetaApplyById(id);
	}
	
	/**
     * 查询团建明细列表
     * 
     * @param facCollectDetaApply 团建明细信息
     * @return 团建明细集合
     */
	@Override
	public List<FacCollectDetaApply> selectFacCollectDetaApplyList(FacCollectDetaApply facCollectDetaApply)
	{
	    return facCollectDetaApplyMapper.selectFacCollectDetaApplyList(facCollectDetaApply);
	}
	
    /**
     * 新增团建明细
     * 
     * @param facCollectDetaApply 团建明细信息
     * @return 结果
     */
	@Override
	public int insertFacCollectDetaApply(FacCollectDetaApply facCollectDetaApply)
	{
	    return facCollectDetaApplyMapper.insertFacCollectDetaApply(facCollectDetaApply);
	}
	
	/**
     * 修改团建明细
     * 
     * @param facCollectDetaApply 团建明细信息
     * @return 结果
     */
	@Override
	public int updateFacCollectDetaApply(FacCollectDetaApply facCollectDetaApply)
	{
	    return facCollectDetaApplyMapper.updateFacCollectDetaApply(facCollectDetaApply);
	}

	/**
     * 删除团建明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFacCollectDetaApplyByIds(String ids)
	{
		return facCollectDetaApplyMapper.deleteFacCollectDetaApplyByIds(Convert.toStrArray(ids));
	}
	
}
