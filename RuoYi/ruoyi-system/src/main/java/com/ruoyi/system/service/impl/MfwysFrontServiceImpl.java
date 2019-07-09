package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.MfwysFrontMapper;
import com.ruoyi.system.mapper.MfwysZhanxianMapper;
import com.ruoyi.system.domain.MfwysFront;
import com.ruoyi.system.service.IMfwysFrontService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;

/**
 * 马蜂窝原生前端 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-04
 */
@Service
public class MfwysFrontServiceImpl implements IMfwysFrontService 
{
	@Autowired
	private MfwysFrontMapper mfwysFrontMapper;

	@Autowired
	private MfwysZhanxianMapper mfwysZhanxianMapper;
	
	/**
     * 查询马蜂窝原生前端信息
     * 
     * @param id 马蜂窝原生前端ID
     * @return 马蜂窝原生前端信息
     */
    @Override
	public MfwysFront selectMfwysFrontById(Long id)
	{
	    return mfwysFrontMapper.selectMfwysFrontById(id);
	}
	
	/**
     * 查询马蜂窝原生前端列表
     * 
     * @param mfwysFront 马蜂窝原生前端信息
     * @return 马蜂窝原生前端集合
     */
	@Override
	public List<MfwysFront> selectMfwysFrontList(MfwysFront mfwysFront)
	{
	    return mfwysFrontMapper.selectMfwysFrontList(mfwysFront);
	}
	
    /**
     * 新增马蜂窝原生前端
     * 
     * @param mfwysFront 马蜂窝原生前端信息
     * @return 结果
     */
	@Override
	public int insertMfwysFront(MfwysFront mfwysFront)
	{
	    return mfwysFrontMapper.insertMfwysFront(mfwysFront);
	}
	
	/**
     * 修改马蜂窝原生前端
     * 
     * @param mfwysFront 马蜂窝原生前端信息
     * @return 结果
     */
	@Override
	public int updateMfwysFront(MfwysFront mfwysFront)
	{
	    return mfwysFrontMapper.updateMfwysFront(mfwysFront);
	}

	/**
     * 删除马蜂窝原生前端对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMfwysFrontByIds(String ids)
	{
		return mfwysFrontMapper.deleteMfwysFrontByIds(Convert.toStrArray(ids));
	}
	
	@Override
	@Transactional
	public String importMfwysFront(List<MfwysFront> mfwysList, Boolean isUpdateSupport, String operName) {

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
	                    List<MfwysFront> listPage = mfwysList.subList(0, pointsDataLimit);
	                    mfwysFrontMapper.batchInsert(listPage);
	                    //剔除
	                    mfwysList.subList(0, pointsDataLimit).clear();
	                }
	                if(!mfwysList.isEmpty()){
	                    //表示最后剩下的数据
	                	mfwysFrontMapper.batchInsert(mfwysList);
	                }
	            }else{
	            	mfwysFrontMapper.batchInsert(mfwysList);
	            }
	        }
	        mfwysZhanxianMapper.deleteMfwysZhanxianAll();
	        mfwysFrontMapper.insertMfwysZx();
	        
	        return "导入成功";
        }catch (Exception e){
        	e.printStackTrace();
        	 return "导入失败";
        }
    
	}
}
