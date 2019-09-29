package com.ruoyi.system.service.finance.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.finance.FacCollectApply;
import com.ruoyi.system.mapper.finance.FacCollectApplyMapper;
import com.ruoyi.system.service.finance.IFacCollectApplyService; 

/**
 * 团建申请 服务层实现
 * 
 * @author ruoyi
 * @date 2019-09-03
 */
@Service
public class FacCollectApplyServiceImpl implements IFacCollectApplyService 
{
	@Autowired
	private FacCollectApplyMapper facCollectApplyMapper;

	/**
     * 查询团建申请信息
     * 
     * @param id 团建申请ID
     * @return 团建申请信息
     */
    @Override
	public FacCollectApply selectFacCollectApplyById(Long id)
	{
	    return facCollectApplyMapper.selectFacCollectApplyById(id);
	}
	
	/**
     * 查询团建申请列表
     * 
     * @param facCollectApply 团建申请信息
     * @return 团建申请集合
     */
	@Override
	public List<FacCollectApply> selectFacCollectApplyList(FacCollectApply facCollectApply)
	{
	    return facCollectApplyMapper.selectFacCollectApplyList(facCollectApply);
	}
	
    /**
     * 新增团建申请
     * 
     * @param facCollectApply 团建申请信息
     * @return 结果
     */
	@Override
	public int insertFacCollectApply(FacCollectApply facCollectApply)
	{
	    return facCollectApplyMapper.insertFacCollectApply(facCollectApply);
	}
	
	/**
     * 修改团建申请
     * 
     * @param facCollectApply 团建申请信息
     * @return 结果
     */
	@Override
	public int updateFacCollectApply(FacCollectApply facCollectApply)
	{
	    return facCollectApplyMapper.updateFacCollectApply(facCollectApply);
	}

	/**
     * 删除团建申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFacCollectApplyByIds(String ids)
	{
		return facCollectApplyMapper.deleteFacCollectApplyByIds(Convert.toStrArray(ids));
	}
	
}
