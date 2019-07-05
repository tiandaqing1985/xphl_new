package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BqFrontMapper;
import com.ruoyi.system.domain.BqFront;
import com.ruoyi.system.service.IBqFrontService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;

/**
 * 北汽前端 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
@Service
public class BqFrontServiceImpl implements IBqFrontService 
{
	@Autowired
	private BqFrontMapper bqFrontMapper;

	/**
     * 查询北汽前端信息
     * 
     * @param id 北汽前端ID
     * @return 北汽前端信息
     */
    @Override
	public BqFront selectBqFrontById(Long id)
	{
	    return bqFrontMapper.selectBqFrontById(id);
	}
	
	/**
     * 查询北汽前端列表
     * 
     * @param bqFront 北汽前端信息
     * @return 北汽前端集合
     */
	@Override
	public List<BqFront> selectBqFrontList(BqFront bqFront)
	{
	    return bqFrontMapper.selectBqFrontList(bqFront);
	}
	
    /**
     * 新增北汽前端
     * 
     * @param bqFront 北汽前端信息
     * @return 结果
     */
	@Override
	public int insertBqFront(BqFront bqFront)
	{
	    return bqFrontMapper.insertBqFront(bqFront);
	}
	
	/**
     * 修改北汽前端
     * 
     * @param bqFront 北汽前端信息
     * @return 结果
     */
	@Override
	public int updateBqFront(BqFront bqFront)
	{
	    return bqFrontMapper.updateBqFront(bqFront);
	}

	/**
     * 删除北汽前端对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBqFrontByIds(String ids)
	{
		return bqFrontMapper.deleteBqFrontByIds(Convert.toStrArray(ids));
	}

	/**
	 * 批量导入数据
	 */
	@Override
	public String importBqFront(List<BqFront> bqfList, Boolean isUpdateSupport, String operName) {
		if (StringUtils.isNull(bqfList) || bqfList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        
        try{
        String flag = "";
	        if(null!=bqfList&&bqfList.size()>0){
	            int pointsDataLimit = 10000;//限制条数
	            Integer size = bqfList.size();
	            //判断是否有必要分批
	            if(pointsDataLimit<size){
	                int part = size/pointsDataLimit;//分批数
	                // System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
	                //
	                for (int i = 0; i < part; i++) {
	                    //1000条
	                    List<BqFront> listPage = bqfList.subList(0, pointsDataLimit);
	                    bqFrontMapper.batchInsert(listPage);
	                    //剔除
	                    bqfList.subList(0, pointsDataLimit).clear();
	                }
	                if(!bqfList.isEmpty()){
	                    //表示最后剩下的数据
	                	bqFrontMapper.batchInsert(bqfList);
	                }
	            }else{
	            	bqFrontMapper.batchInsert(bqfList);
	            }
	        }
	        bqFrontMapper.updateGroupword(null);
	        return "导入成功";
        }catch (Exception e){
        	e.printStackTrace();
        	 return "导入失败";
        }
	}

	/**
	 * 清除所有前端数据
	 * @return
	 */
	@Override
	public int deleteAllBqFront() {
		
		return bqFrontMapper.deleteAllBqFront();
	}
	
}
