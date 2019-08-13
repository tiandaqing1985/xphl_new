package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.XzAsstesMapper;
import com.ruoyi.system.domain.XzAsstes;
import com.ruoyi.system.service.IXzAsstesService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;

/**
 * 资产 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-02
 */
@Service
public class XzAsstesServiceImpl implements IXzAsstesService 
{
	@Autowired
	private XzAsstesMapper xzAsstesMapper;

	/**
     * 查询资产信息
     * 
     * @param id 资产ID
     * @return 资产信息
     */
    @Override
	public XzAsstes selectXzAsstesById(Long id)
	{
	    return xzAsstesMapper.selectXzAsstesById(id);
	}
	
	/**
     * 查询资产列表
     * 
     * @param xzAsstes 资产信息
     * @return 资产集合
     */
	@Override
	public List<XzAsstes> selectXzAsstesList(XzAsstes xzAsstes)
	{
	    return xzAsstesMapper.selectXzAsstesList(xzAsstes);
	}
	
    /**
     * 新增资产
     * 
     * @param xzAsstes 资产信息
     * @return 结果
     */
	@Override
	public int insertXzAsstes(XzAsstes xzAsstes)
	{
	     return  xzAsstesMapper.insertXzAsstes(xzAsstes);
	}
	
	/**
     * 修改资产
     * 
     * @param xzAsstes 资产信息
     * @return 结果
     */
	@Override
	public int updateXzAsstes(XzAsstes xzAsstes)
	{
		return xzAsstesMapper.updateXzAsstes(xzAsstes);
	    
	}

	/**
     * 删除资产对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteXzAsstesByIds(String ids)
	{
		return xzAsstesMapper.deleteXzAsstesByIds(Convert.toStrArray(ids));
	}
	
	@Override
	@Transactional
	public String importXzAsstes(List<XzAsstes> assetsList, boolean updateSupport, String operName){
		
		if (StringUtils.isNull(assetsList) || assetsList.size() == 0)
        {
            throw new BusinessException("导入资产数据不能为空！");
        }
	      try{
		        if(null!=assetsList&&assetsList.size()>0){
		            int pointsDataLimit = 10000;//限制条数
		            Integer size = assetsList.size();
		            //判断是否有必要分批
		            if(pointsDataLimit<size){
		                int part = size/pointsDataLimit;//分批数
		                 System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
		                for (int i = 0; i < part; i++) {
		                    //1000条
		                   List<XzAsstes> listPage = assetsList.subList(0, pointsDataLimit);
		                   xzAsstesMapper.batchInsert(listPage);
		                    //剔除
		                    assetsList.subList(0, pointsDataLimit).clear();
		                }
		                if(!assetsList.isEmpty()){
		                    //表示最后剩下的数据
		                	xzAsstesMapper.batchInsert(assetsList);
		                }
		            }else{
		            	xzAsstesMapper.batchInsert(assetsList);
		            }
		        }
		        
		        return "导入成功";
	        }catch (Exception e){
	        	e.printStackTrace();
	        	 return "导入失败";
	        }
		
	}
}
