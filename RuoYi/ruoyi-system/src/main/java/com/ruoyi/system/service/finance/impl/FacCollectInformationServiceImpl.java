package com.ruoyi.system.service.finance.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.finance.FacCollectInformation;
import com.ruoyi.system.mapper.finance.FacCollectInformationMapper;
import com.ruoyi.system.service.finance.IFacCollectInformationService;

/**
 * 团建费报销 服务层实现
 * 
 * @author ruoyi
 * @date 2019-11-07
 */
@Service
public class FacCollectInformationServiceImpl implements IFacCollectInformationService 
{
	@Autowired
	private FacCollectInformationMapper facCollectInformationMapper;

	/**
     * 查询团建费报销信息
     * 
     * @param id 团建费报销ID
     * @return 团建费报销信息
     */
    @Override
	public FacCollectInformation selectFacCollectInformationById(Long id)
	{
	    return facCollectInformationMapper.selectFacCollectInformationById(id);
	}
	
	/**
     * 查询团建费报销列表
     * 
     * @param facCollectInformation 团建费报销信息
     * @return 团建费报销集合
     */
	@Override
	public List<FacCollectInformation> selectFacCollectInformationList(FacCollectInformation facCollectInformation)
	{
	    return facCollectInformationMapper.selectFacCollectInformationList(facCollectInformation);
	}
	
    /**
     * 新增团建费报销
     * 
     * @param facCollectInformation 团建费报销信息
     * @return 结果
     */
	@Override
	public int insertFacCollectInformation(FacCollectInformation facCollectInformation)
	{
	    return facCollectInformationMapper.insertFacCollectInformation(facCollectInformation);
	}
	
	/**
     * 修改团建费报销
     * 
     * @param facCollectInformation 团建费报销信息
     * @return 结果
     */
	@Override
	public int updateFacCollectInformation(FacCollectInformation facCollectInformation)
	{
	    return facCollectInformationMapper.updateFacCollectInformation(facCollectInformation);
	}

	/**
     * 删除团建费报销对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFacCollectInformationByIds(String ids)
	{
		return facCollectInformationMapper.deleteFacCollectInformationByIds(Convert.toStrArray(ids));
	}

	@Override
	public double selectAmount(String num) {
		 
		return facCollectInformationMapper.selectAmount(num);
	}

	@Override
	public double selectMoney(String num) {

		return facCollectInformationMapper.selectMoney(num);
	}


}
