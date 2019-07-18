package com.ruoyi.system.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.MfwysEndMapper;
import com.ruoyi.system.mapper.MfwysParameterMapper;
import com.ruoyi.system.mapper.MfwysZhanxianMapper;
import com.ruoyi.system.domain.MfwysEnd;
import com.ruoyi.system.domain.MfwysParameter;
import com.ruoyi.system.domain.MfwysZhanxian;
import com.ruoyi.system.service.IMfwysZhanxianService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.bean.BeanUtils;

/**
 * 马蜂窝原生展现 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-05
 */
@Service
public class MfwysZhanxianServiceImpl implements IMfwysZhanxianService 
{
	@Autowired
	private MfwysZhanxianMapper mfwysZhanxianMapper;
	
	@Autowired
	private MfwysEndMapper mfwysEndMapper;
	
	@Autowired
	private MfwysParameterMapper mfwysParameterMapper;

	/**
     * 查询马蜂窝原生展现信息
     * 
     * @param id 马蜂窝原生展现ID
     * @return 马蜂窝原生展现信息
     */
    @Override
	public MfwysZhanxian selectMfwysZhanxianById(Long id)
	{
	    return mfwysZhanxianMapper.selectMfwysZhanxianById(id);
	}
	
	/**
     * 查询马蜂窝原生展现列表
     * 
     * @param mfwysZhanxian 马蜂窝原生展现信息
     * @return 马蜂窝原生展现集合
     */
	@Override
	public List<MfwysZhanxian> selectMfwysZhanxianList(MfwysZhanxian mfwysZhanxian)
	{
	    return mfwysZhanxianMapper.selectMfwysZhanxianList(mfwysZhanxian);
	}
	
    /**
     * 新增马蜂窝原生展现
     * 
     * @param mfwysZhanxian 马蜂窝原生展现信息
     * @return 结果
     */
	@Override
	public int insertMfwysZhanxian(MfwysZhanxian mfwysZhanxian)
	{
	    return mfwysZhanxianMapper.insertMfwysZhanxian(mfwysZhanxian);
	}
	
	/**
     * 修改马蜂窝原生展现
     * 
     * @param mfwysZhanxian 马蜂窝原生展现信息
     * @return 结果
     */
	@Override
	public int updateMfwysZhanxian(MfwysZhanxian mfwysZhanxian)
	{
	    return mfwysZhanxianMapper.updateMfwysZhanxian(mfwysZhanxian);
	}

	/**
     * 删除马蜂窝原生展现对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMfwysZhanxianByIds(String ids)
	{
		return mfwysZhanxianMapper.deleteMfwysZhanxianByIds(Convert.toStrArray(ids));
	}

	@Override
	@Transactional
	public int createMfwysZhanxianData() {
		
		try{
		
				List<MfwysEnd> eList = mfwysEndMapper.selectMfwysEndList(null);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for(int i=0;i<eList.size();i++){
					
					//后端数据
					MfwysEnd mfwysEnd = eList.get(i);
					
					MfwysZhanxian mfwysZhanxian = new MfwysZhanxian();
					mfwysZhanxian.setKeywordid(mfwysEnd.getAid());
					
					mfwysZhanxian.setFrontDate(sdf.parse(mfwysEnd.getNewtime()));
					
					MfwysZhanxian zx = mfwysZhanxianMapper.selectMfwysEndByKeywordid(mfwysZhanxian);
					
					if(zx!=null){ //能匹配上的
						zx.setMfwSystem(mfwysEnd.getMfwSystem());
						zx.setChannelPackage(mfwysEnd.getChannelPackage());
						zx.setChannel(mfwysEnd.getChannel());
						zx.setNewFacility(mfwysEnd.getNewFacility());
						zx.setAccount(mfwysEnd.getAccount());
						zx.setAid(mfwysEnd.getAid());
						zx.setKeep(mfwysEnd.getKeep());
						
						//补齐数据
						mfwysZhanxianMapper.updateMfwysZhanxian(zx);
					}else{ //匹配不上的
						String str = mfwysEnd.getChannelPackage();
						String s = "("+str.substring(str.indexOf("XPHL-")+5,str.length())+")";
						
						MfwysZhanxian zx1  = new MfwysZhanxian();
						
						zx1.setChannelPackage(s);
						
						zx1.setFrontDate(sdf.parse(mfwysEnd.getNewtime()));
						
						//能匹配上计划的
						List<MfwysZhanxian> zxList = mfwysZhanxianMapper.selectByChnnelPackage(zx1);
						if(zxList!=null && zxList.size()>0){
							
							MfwysZhanxian zx2 = zxList.get(0);
							MfwysZhanxian zx3 = new MfwysZhanxian();
							zx3.setFrontDate(zx2.getFrontDate());
							zx3.setAccountname(zx2.getAccountname());
							zx3.setPlan(zx2.getPlan());
							zx3.setPlanid(zx2.getPlanid());
							zx3.setMfwSystem(mfwysEnd.getMfwSystem());
							zx3.setChannelPackage(mfwysEnd.getChannelPackage());
							zx3.setChannel(mfwysEnd.getChannel());
							zx3.setNewFacility(mfwysEnd.getNewFacility());
							zx3.setAccount(mfwysEnd.getAccount());
							zx3.setAid(mfwysEnd.getAid());
							zx3.setKeep(mfwysEnd.getKeep());
							
							//新增
							mfwysZhanxianMapper.insertMfwysZhanxian(zx3);
						}else{
							MfwysParameter mp = mfwysParameterMapper.getMfwysParameterByChannel(mfwysEnd.getChannel());
							
							MfwysZhanxian zx4 = new MfwysZhanxian();
							zx4.setFrontDate(sdf.parse(mfwysEnd.getNewtime()));
							zx4.setAccountname(mp.getAccount());
							zx4.setPlan(mp.getPlan());
							zx4.setMfwSystem(mfwysEnd.getMfwSystem());
							zx4.setChannelPackage(mfwysEnd.getChannelPackage());
							zx4.setChannel(mfwysEnd.getChannel());
							zx4.setNewFacility(mfwysEnd.getNewFacility());
							zx4.setAccount(mfwysEnd.getAccount());
							zx4.setAid(mfwysEnd.getAid());
							zx4.setKeep(mfwysEnd.getKeep());
							//新增
							mfwysZhanxianMapper.insertMfwysZhanxian(zx4);
						}
					}
					
					
				}
		
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public static void main(String[] args) {
		String str = "MFW-BDST-XPHL-1";
		
		String s = str.substring(str.indexOf("XPHL-")+5,str.length());
		
		System.out.println(s);
	}
	
}
