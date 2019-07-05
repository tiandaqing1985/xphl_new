package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BqEndMapper;
import com.ruoyi.system.domain.BqEnd;
import com.ruoyi.system.domain.BqFront;
import com.ruoyi.system.service.IBqEndService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;

/**
 * 北汽后端 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
@Service
public class BqEndServiceImpl implements IBqEndService 
{
	@Autowired
	private BqEndMapper bqEndMapper;

	/**
     * 查询北汽后端信息
     * 
     * @param id 北汽后端ID
     * @return 北汽后端信息
     */
    @Override
	public BqEnd selectBqEndById(Long id)
	{
	    return bqEndMapper.selectBqEndById(id);
	}
	
	/**
     * 查询北汽后端列表
     * 
     * @param bqEnd 北汽后端信息
     * @return 北汽后端集合
     */
	@Override
	public List<BqEnd> selectBqEndList(BqEnd bqEnd)
	{
	    return bqEndMapper.selectBqEndList(bqEnd);
	}
	
    /**
     * 新增北汽后端
     * 
     * @param bqEnd 北汽后端信息
     * @return 结果
     */
	@Override
	public int insertBqEnd(BqEnd bqEnd)
	{
	    return bqEndMapper.insertBqEnd(bqEnd);
	}
	
	/**
     * 修改北汽后端
     * 
     * @param bqEnd 北汽后端信息
     * @return 结果
     */
	@Override
	public int updateBqEnd(BqEnd bqEnd)
	{
	    return bqEndMapper.updateBqEnd(bqEnd);
	}

	/**
     * 删除北汽后端对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBqEndByIds(String ids)
	{
		return bqEndMapper.deleteBqEndByIds(Convert.toStrArray(ids));
	}

	@Override
	public String importBqEnd(List<BqEnd> bqeList, Boolean isUpdateSupport, String operName) {
		if (StringUtils.isNull(bqeList) || bqeList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        
        try{
        String flag = "";
	        if(null!=bqeList&&bqeList.size()>0){
	            int pointsDataLimit = 10000;//限制条数
	            Integer size = bqeList.size();
	            //判断是否有必要分批
	            if(pointsDataLimit<size){
	                int part = size/pointsDataLimit;//分批数
	                // System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
	                //
	                for (int i = 0; i < part; i++) {
	                    //1000条
	                    List<BqEnd> listPage = bqeList.subList(0, pointsDataLimit);
	                    bqEndMapper.batchInsert(listPage);
	                    //剔除
	                    bqeList.subList(0, pointsDataLimit).clear();
	                }
	                if(!bqeList.isEmpty()){
	                    //表示最后剩下的数据
	                	bqEndMapper.batchInsert(bqeList);
	                }
	            }else{
	            	bqEndMapper.batchInsert(bqeList);
	            }
	        }
	        bqEndMapper.updateGroupword(null);
	        return "导入成功";
        }catch (Exception e){
        	e.printStackTrace();
        	 return "导入失败";
        }
	}

	/**
	 * 清除所有北汽前端数据
	 * @return
	 */
	@Override
	public int deleteAllBqEnd() {
		
		return bqEndMapper.deleteAllBqEnd();
	}
	
}
