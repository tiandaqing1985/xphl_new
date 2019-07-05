package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.RxFrontMapper;
import com.ruoyi.system.domain.BqFront;
import com.ruoyi.system.domain.RxFront;
import com.ruoyi.system.service.IRxFrontService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;

/**
 * 瑞幸前端 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-05
 */
@Service
public class RxFrontServiceImpl implements IRxFrontService 
{
	@Autowired
	private RxFrontMapper rxFrontMapper;

	/**
     * 查询瑞幸前端信息
     * 
     * @param id 瑞幸前端ID
     * @return 瑞幸前端信息
     */
    @Override
	public RxFront selectRxFrontById(Integer id)
	{
	    return rxFrontMapper.selectRxFrontById(id);
	}
	
	/**
     * 查询瑞幸前端列表
     * 
     * @param rxFront 瑞幸前端信息
     * @return 瑞幸前端集合
     */
	@Override
	public List<RxFront> selectRxFrontList(RxFront rxFront)
	{
	    return rxFrontMapper.selectRxFrontList(rxFront);
	}
	
    /**
     * 新增瑞幸前端
     * 
     * @param rxFront 瑞幸前端信息
     * @return 结果
     */
	@Override
	public int insertRxFront(RxFront rxFront)
	{
	    return rxFrontMapper.insertRxFront(rxFront);
	}
	
	/**
     * 修改瑞幸前端
     * 
     * @param rxFront 瑞幸前端信息
     * @return 结果
     */
	@Override
	public int updateRxFront(RxFront rxFront)
	{
	    return rxFrontMapper.updateRxFront(rxFront);
	}

	/**
     * 删除瑞幸前端对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteRxFrontByIds(String ids)
	{
		return rxFrontMapper.deleteRxFrontByIds(Convert.toStrArray(ids));
	}

	@Override
	public String importRxFront(List<RxFront> rxfList, Boolean isUpdateSupport, String operName) {
		if (StringUtils.isNull(rxfList) || rxfList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        
        try{
        String flag = "";
	        if(null!=rxfList&&rxfList.size()>0){
	            int pointsDataLimit = 10000;//限制条数
	            Integer size = rxfList.size();
	            //判断是否有必要分批
	            if(pointsDataLimit<size){
	                int part = size/pointsDataLimit;//分批数
	                // System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
	                //
	                for (int i = 0; i < part; i++) {
	                    //1000条
	                    List<RxFront> listPage = rxfList.subList(0, pointsDataLimit);
	                    rxFrontMapper.batchInsert(listPage);
	                    //剔除
	                    rxfList.subList(0, pointsDataLimit).clear();
	                }
	                if(!rxfList.isEmpty()){
	                    //表示最后剩下的数据
	                	rxFrontMapper.batchInsert(rxfList);
	                }
	            }else{
	            	rxFrontMapper.batchInsert(rxfList);
	            }
	        }
	        rxFrontMapper.updateGroupword(null);
	        return "导入成功";
        }catch (Exception e){
        	e.printStackTrace();
        	 return "导入失败";
        }
	}

	@Override
	public int deleteAllRxFront() {
		
		return rxFrontMapper.deleteAllRxFront();
	}
	
}
