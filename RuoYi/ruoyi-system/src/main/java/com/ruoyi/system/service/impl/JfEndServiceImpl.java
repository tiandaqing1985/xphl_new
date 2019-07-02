package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.JfEndMapper;
import com.ruoyi.system.domain.JfEnd;
import com.ruoyi.system.service.IJfEndService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;

/**
 * 玖富后端 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
@Service
public class JfEndServiceImpl implements IJfEndService 
{
	@Autowired
	private JfEndMapper jfEndMapper;

	/**
     * 查询玖富后端信息
     * 
     * @param id 玖富后端ID
     * @return 玖富后端信息
     */
    @Override
	public JfEnd selectJfEndById(Long id)
	{
	    return jfEndMapper.selectJfEndById(id);
	}
	
	/**
     * 查询玖富后端列表
     * 
     * @param jfEnd 玖富后端信息
     * @return 玖富后端集合
     */
	@Override
	public List<JfEnd> selectJfEndList(JfEnd jfEnd)
	{
	    return jfEndMapper.selectJfEndList(jfEnd);
	}
	
    /**
     * 新增玖富后端
     * 
     * @param jfEnd 玖富后端信息
     * @return 结果
     */
	@Override
	public int insertJfEnd(JfEnd jfEnd)
	{
	    return jfEndMapper.insertJfEnd(jfEnd);
	}
	
	/**
     * 修改玖富后端
     * 
     * @param jfEnd 玖富后端信息
     * @return 结果
     */
	@Override
	public int updateJfEnd(JfEnd jfEnd)
	{
	    return jfEndMapper.updateJfEnd(jfEnd);
	}

	/**
     * 删除玖富后端对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteJfEndByIds(String ids)
	{
		return jfEndMapper.deleteJfEndByIds(Convert.toStrArray(ids));
	}
	
	@Override
	public String importEnd(List<JfEnd> jeList, Boolean isUpdateSupport, String operName) {

        if (StringUtils.isNull(jeList) || jeList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        
        try{
	        if(null!=jeList&&jeList.size()>0){
	            int pointsDataLimit = 10000;//限制条数
	            Integer size = jeList.size();
	            //判断是否有必要分批
	            if(pointsDataLimit<size){
	                int part = size/pointsDataLimit;//分批数
	                // System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
	                //
	                for (int i = 0; i < part; i++) {
	                    //1000条
	                    List<JfEnd> listPage = jeList.subList(0, pointsDataLimit);
	                    jfEndMapper.batchInsert(listPage);
	                    //剔除
	                    jeList.subList(0, pointsDataLimit).clear();
	                }
	                if(!jeList.isEmpty()){
	                    //表示最后剩下的数据
	                	jfEndMapper.batchInsert(jeList);
	                }
	            }else{
	            	jfEndMapper.batchInsert(jeList);
	            }
	        }
	        
	        return "导入成功";
        }catch (Exception e){
        	e.printStackTrace();
        	 return "导入失败";
        }
    
	}
}
