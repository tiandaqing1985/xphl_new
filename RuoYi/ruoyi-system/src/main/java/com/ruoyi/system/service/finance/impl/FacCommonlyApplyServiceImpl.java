package com.ruoyi.system.service.finance.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.finance.FacCommonlyApply;
import com.ruoyi.system.mapper.finance.FacCommonlyApplyMapper;
import com.ruoyi.system.service.finance.IFacCommonlyApplyService;

/**
 * 对公常显 服务层实现
 * 
 * @author ruoyi
 * @date 2019-10-24
 */
@Service
public class FacCommonlyApplyServiceImpl implements IFacCommonlyApplyService 
{
	@Autowired
	private FacCommonlyApplyMapper facCommonlyApplyMapper;

	/**
     * 查询对公常显信息
     * 
     * @param id 对公常显ID
     * @return 对公常显信息
     */
    @Override
	public FacCommonlyApply selectFacCommonlyApplyById(Long id)
	{
	    return facCommonlyApplyMapper.selectFacCommonlyApplyById(id);
	}
	
	/**
     * 查询对公常显列表
     * 
     * @param facCommonlyApply 对公常显信息
     * @return 对公常显集合
     */
	@Override
	public List<FacCommonlyApply> selectFacCommonlyApplyList(FacCommonlyApply facCommonlyApply)
	{
	    return facCommonlyApplyMapper.selectFacCommonlyApplyList(facCommonlyApply);
	}
	
    /**
     * 新增对公常显
     * 
     * @param facCommonlyApply 对公常显信息
     * @return 结果
     */
	@Override
	public int insertFacCommonlyApply(FacCommonlyApply facCommonlyApply)
	{
	    return facCommonlyApplyMapper.insertFacCommonlyApply(facCommonlyApply);
	}
	
	/**
     * 修改对公常显
     * 
     * @param facCommonlyApply 对公常显信息
     * @return 结果
     */
	@Override
	public int updateFacCommonlyApply(FacCommonlyApply facCommonlyApply)
	{
	    return facCommonlyApplyMapper.updateFacCommonlyApply(facCommonlyApply);
	}

	/**
     * 删除对公常显对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFacCommonlyApplyByIds(String ids)
	{
		return facCommonlyApplyMapper.deleteFacCommonlyApplyByIds(Convert.toStrArray(ids));
	}
	
}
