package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MfwysEndMapper;
import com.ruoyi.system.domain.JfEnd;
import com.ruoyi.system.domain.MfwysEnd;
import com.ruoyi.system.service.IMfwysEndService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;

/**
 * 马蜂窝原生后端 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-03
 */
@Service
public class MfwysEndServiceImpl implements IMfwysEndService 
{
	@Autowired
	private MfwysEndMapper mfwysEndMapper;

	/**
     * 查询马蜂窝原生后端信息
     * 
     * @param id 马蜂窝原生后端ID
     * @return 马蜂窝原生后端信息
     */
    @Override
	public MfwysEnd selectMfwysEndById(Long id)
	{
	    return mfwysEndMapper.selectMfwysEndById(id);
	}
	
	/**
     * 查询马蜂窝原生后端列表
     * 
     * @param mfwysEnd 马蜂窝原生后端信息
     * @return 马蜂窝原生后端集合
     */
	@Override
	public List<MfwysEnd> selectMfwysEndList(MfwysEnd mfwysEnd)
	{
	    return mfwysEndMapper.selectMfwysEndList(mfwysEnd);
	}
	
    /**
     * 新增马蜂窝原生后端
     * 
     * @param mfwysEnd 马蜂窝原生后端信息
     * @return 结果
     */
	@Override
	public int insertMfwysEnd(MfwysEnd mfwysEnd)
	{
	    return mfwysEndMapper.insertMfwysEnd(mfwysEnd);
	}
	
	/**
     * 修改马蜂窝原生后端
     * 
     * @param mfwysEnd 马蜂窝原生后端信息
     * @return 结果
     */
	@Override
	public int updateMfwysEnd(MfwysEnd mfwysEnd)
	{
	    return mfwysEndMapper.updateMfwysEnd(mfwysEnd);
	}

	/**
     * 删除马蜂窝原生后端对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMfwysEndByIds(String ids)
	{
		return mfwysEndMapper.deleteMfwysEndByIds(Convert.toStrArray(ids));
	}
	
	@Override
	public String importMfwysEnd(List<MfwysEnd> mfwysList, Boolean isUpdateSupport, String operName) {

        if (StringUtils.isNull(mfwysList) || mfwysList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        
        try{
	        if(null!=mfwysList&&mfwysList.size()>0){
	            int pointsDataLimit = 10000;//限制条数
	            Integer size = mfwysList.size();
	            //判断是否有必要分批
	            if(pointsDataLimit<size){
	                int part = size/pointsDataLimit;//分批数
	                // System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
	                //
	                for (int i = 0; i < part; i++) {
	                    //1000条
	                    List<MfwysEnd> listPage = mfwysList.subList(0, pointsDataLimit);
	                    mfwysEndMapper.batchInsert(listPage);
	                    //剔除
	                    mfwysList.subList(0, pointsDataLimit).clear();
	                }
	                if(!mfwysList.isEmpty()){
	                    //表示最后剩下的数据
	                	mfwysEndMapper.batchInsert(mfwysList);
	                }
	            }else{
	            	mfwysEndMapper.batchInsert(mfwysList);
	            }
	        }
	        
	        return "导入成功";
        }catch (Exception e){
        	e.printStackTrace();
        	 return "导入失败";
        }
    
	}
}
