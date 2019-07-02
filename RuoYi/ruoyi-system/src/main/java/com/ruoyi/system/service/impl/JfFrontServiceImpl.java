package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.JfFrontMapper;
import com.ruoyi.system.domain.JfFront;
import com.ruoyi.system.service.IJfFrontService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;

/**
 * 玖富前端 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
@Service
public class JfFrontServiceImpl implements IJfFrontService 
{
	@Autowired
	private JfFrontMapper jfFrontMapper;

	/**
     * 查询玖富前端信息
     * 
     * @param id 玖富前端ID
     * @return 玖富前端信息
     */
    @Override
	public JfFront selectJfFrontById(Long id)
	{
	    return jfFrontMapper.selectJfFrontById(id);
	}
	
	/**
     * 查询玖富前端列表
     * 
     * @param jfFront 玖富前端信息
     * @return 玖富前端集合
     */
	@Override
	public List<JfFront> selectJfFrontList(JfFront jfFront)
	{
	    return jfFrontMapper.selectJfFrontList(jfFront);
	}
	
    /**
     * 新增玖富前端
     * 
     * @param jfFront 玖富前端信息
     * @return 结果
     */
	@Override
	public int insertJfFront(JfFront jfFront)
	{
	    return jfFrontMapper.insertJfFront(jfFront);
	}
	
	/**
     * 修改玖富前端
     * 
     * @param jfFront 玖富前端信息
     * @return 结果
     */
	@Override
	public int updateJfFront(JfFront jfFront)
	{
	    return jfFrontMapper.updateJfFront(jfFront);
	}

	/**
     * 删除玖富前端对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteJfFrontByIds(String ids)
	{
		return jfFrontMapper.deleteJfFrontByIds(Convert.toStrArray(ids));
	}
	
	@Override
	public String importJfFront(List<JfFront> jffList, Boolean isUpdateSupport, String operName) {

        if (StringUtils.isNull(jffList) || jffList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        
        try{
        String flag = "";
	        if(null!=jffList&&jffList.size()>0){
	            int pointsDataLimit = 10000;//限制条数
	            Integer size = jffList.size();
	            //判断是否有必要分批
	            if(pointsDataLimit<size){
	                int part = size/pointsDataLimit;//分批数
	                // System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
	                //
	                for (int i = 0; i < part; i++) {
	                    //1000条
	                    List<JfFront> listPage = jffList.subList(0, pointsDataLimit);
	                    jfFrontMapper.batchInsert(listPage);
	                    //剔除
	                    jffList.subList(0, pointsDataLimit).clear();
	                }
	                if(!jffList.isEmpty()){
	                    //表示最后剩下的数据
	                	jfFrontMapper.batchInsert(jffList);
	                }
	            }else{
	            	jfFrontMapper.batchInsert(jffList);
	            }
	        }
	        jfFrontMapper.updateGroupword(null);
	        return "导入成功";
        }catch (Exception e){
        	e.printStackTrace();
        	 return "导入失败";
        }
    
	}

	
}
