package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.JfMatchingMapper;
import com.ruoyi.system.domain.JfMatching;
import com.ruoyi.system.service.IJfMatchingService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;

/**
 * 玖富匹配 服务层实现
 * 
 * @author ruoyi
 * @date 2019-06-28
 */
@Service
public class JfMatchingServiceImpl implements IJfMatchingService 
{
	@Autowired
	private JfMatchingMapper jfMatchingMapper;

	/**
     * 查询玖富匹配信息
     * 
     * @param id 玖富匹配ID
     * @return 玖富匹配信息
     */
    @Override
	public JfMatching selectJfMatchingById(Long id)
	{
	    return jfMatchingMapper.selectJfMatchingById(id);
	}
	
	/**
     * 查询玖富匹配列表
     * 
     * @param jfMatching 玖富匹配信息
     * @return 玖富匹配集合
     */
	@Override
	public List<JfMatching> selectJfMatchingList(JfMatching jfMatching)
	{
	    return jfMatchingMapper.selectJfMatchingList(jfMatching);
	}
	
    /**
     * 新增玖富匹配
     * 
     * @param jfMatching 玖富匹配信息
     * @return 结果
     */
	@Override
	public int insertJfMatching(JfMatching jfMatching)
	{
	    return jfMatchingMapper.insertJfMatching(jfMatching);
	}
	
	/**
     * 修改玖富匹配
     * 
     * @param jfMatching 玖富匹配信息
     * @return 结果
     */
	@Override
	public int updateJfMatching(JfMatching jfMatching)
	{
	    return jfMatchingMapper.updateJfMatching(jfMatching);
	}

	/**
     * 删除玖富匹配对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteJfMatchingByIds(String ids)
	{
		return jfMatchingMapper.deleteJfMatchingByIds(Convert.toStrArray(ids));
	}

	@Override
	public String importMatching(List<JfMatching> jfList, Boolean isUpdateSupport, String operName) {

        if (StringUtils.isNull(jfList) || jfList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        
        try{
        String flag = "";
	        if(null!=jfList&&jfList.size()>0){
	        	flag = jfList.get(0).getFlag();
	            int pointsDataLimit = 10000;//限制条数
	            Integer size = jfList.size();
	            //判断是否有必要分批
	            if(pointsDataLimit<size){
	                int part = size/pointsDataLimit;//分批数
	                // System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
	                //
	                for (int i = 0; i < part; i++) {
	                    //1000条
	                    List<JfMatching> listPage = jfList.subList(0, pointsDataLimit);
	                    jfMatchingMapper.batchInsert(listPage);
	                    //剔除
	                    jfList.subList(0, pointsDataLimit).clear();
	                }
	                if(!jfList.isEmpty()){
	                    //表示最后剩下的数据
	                	jfMatchingMapper.batchInsert(jfList);
	                }
	            }else{
	            	jfMatchingMapper.batchInsert(jfList);
	            }
	        }
	        
	        if(!"".equals(flag)){
	        	jfMatchingMapper.updateGroupword(flag);
	        }
	        return "导入成功";
        }catch (Exception e){
        	e.printStackTrace();
        	 return "导入失败";
        }
    
	}
	
	
}
