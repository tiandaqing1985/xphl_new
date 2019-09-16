package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.XzAssetHandRecordMapper;
import com.ruoyi.system.mapper.XzAsstesMapper;
import com.ruoyi.system.mapper.XzPersonalApplyMapper;
import com.ruoyi.system.mapper.XzPersonalAssetMapper;
import com.ruoyi.system.domain.XzAssetHandRecord;
import com.ruoyi.system.domain.XzAsstes;
import com.ruoyi.system.domain.XzPersonalApply;
import com.ruoyi.system.domain.XzPersonalAsset;
import com.ruoyi.system.service.IXzAssetHandRecordService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资产分配记录 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-07
 */
@Service
public class XzAssetHandRecordServiceImpl implements IXzAssetHandRecordService 
{
	@Autowired
	private XzAssetHandRecordMapper xzAssetHandRecordMapper;
	
	@Autowired
	private XzPersonalAssetMapper xzPersonalAssetMapper;
	
	@Autowired
	private XzPersonalApplyMapper xzPersonalApplyMapper;
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private XzAsstesMapper xzAsstesMapper;
    
	/**
     * 查询资产分配记录信息
     * 
     * @param id 资产分配记录ID
     * @return 资产分配记录信息
     */
    @Override
	public XzAssetHandRecord selectXzAssetHandRecordById(Long id)
	{
	    return xzAssetHandRecordMapper.selectXzAssetHandRecordById(id);
	}
	
	/**
     * 查询资产分配记录列表
     * 
     * @param xzAssetHandRecord 资产分配记录信息
     * @return 资产分配记录集合
     */
	@Override
	public List<XzAssetHandRecord> selectXzAssetHandRecordList(XzAssetHandRecord xzAssetHandRecord)
	{
	    return xzAssetHandRecordMapper.selectXzAssetHandRecordList(xzAssetHandRecord);
	}
	
    /**
     * 新增资产分配记录
     * 
     * @param xzAssetHandRecord 资产分配记录信息
     * @return 结果
     */
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, value = "transactionManager")
	public String insertXzAssetHandRecord(XzAssetHandRecord xzAssetHandRecord)
	{
		Long userId=sysUserMapper.selectUserIdByUserNameOnly(xzAssetHandRecord.getRecipientName());
		if(userId==null){
			return "1";
		}
		Date date=new Date();
		XzAsstes xzAsstes=xzAsstesMapper.selectXzAsstesById(xzAssetHandRecord.getId());
		//分配后资产状态为-已分配3，使用状态为-待领取2
		xzAsstes.setAssetsStatus("3");
		xzAsstes.setUseStatus("2");
		xzAsstes.setUseBy(userId);
		Long deptId=sysUserMapper.selectUserById(userId).getDeptId();
		xzAsstes.setDepartment(deptId);
		xzAsstesMapper.updateXzAsstes(xzAsstes);
		
		//新增一条个人资产信息
		XzPersonalAsset person =new XzPersonalAsset();
		person.setAssetId(xzAssetHandRecord.getId());//xzAssetHandRecord.getId()为资产id
		person.setUserId(userId);
		person.setAssetStatus("2");
		person.setCreateBy(xzAssetHandRecord.getCreateBy());
		person.setCreateTime(date);
		xzPersonalAssetMapper.insertXzPersonalAsset(person);
		//新增一条分配记录
		XzAssetHandRecord xz=new XzAssetHandRecord();
		xz.setAssetId(xzAsstes.getId());
		xz.setDistributor(xzAssetHandRecord.getDistributor());
		xz.setDistributionDate(date);
		xz.setRecipient(userId);
		xz.setCreateBy(xzAssetHandRecord.getCreateBy());
		xz.setCreateTime(date);
		xz.setApplyId(xzAssetHandRecord.getApplyId());
	     xzAssetHandRecordMapper.insertXzAssetHandRecord(xz);
	     return "0";
	}
	
	/**
     * 修改资产分配记录
     * 
     * @param xzAssetHandRecord 资产分配记录信息
     * @return 结果
     */
	@Override
	public int updateXzAssetHandRecord(XzAssetHandRecord xzAssetHandRecord)
	{
	    return xzAssetHandRecordMapper.updateXzAssetHandRecord(xzAssetHandRecord);
	}

	/**
     * 删除资产分配记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteXzAssetHandRecordByIds(String ids)
	{
		return xzAssetHandRecordMapper.deleteXzAssetHandRecordByIds(Convert.toStrArray(ids));
	}

	/**
	 * 新增办公资产分配记录
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String insertXzAssetHandStaRecord(XzAssetHandRecord xzAssetHandRecord) {
		XzAsstes xz=new XzAsstes();
		xz.setAssetsType2(xzAssetHandRecord.getAssetType2Id());
		xz.setRegion(xzAssetHandRecord.getRegion());
		//查询符合的数据数量
		int count =xzAsstesMapper.countAssetByType2AndRegion(xz);
		
		//对比申请数量
		if(count>0){
			if(count>xzAssetHandRecord.getCount() || count==xzAssetHandRecord.getCount()){
				
				try {
						for(int i=0; i<xzAssetHandRecord.getCount();i++){
							
							//根据资产子类型查询最小的在库未分配的办公资产
							XzAsstes xzAsstes=xzAsstesMapper.selectMinAssetByType2AndRegion(xz);
							
							//新增一条分配记录
							XzAssetHandRecord hand=new XzAssetHandRecord();
							hand.setAssetId(xzAsstes.getId());
							hand.setDistributor(xzAssetHandRecord.getDistributor());
							hand.setDistributorStatus("2");
							hand.setRecipient(xzAssetHandRecord.getRecipient());
							hand.setDistributionDate(new Date());
							hand.setCreateBy(xzAssetHandRecord.getDistributor().toString());
							hand.setCreateTime(new Date());
							hand.setApplyId(xzAssetHandRecord.getApplyId());
							xzAssetHandRecordMapper.insertXzAssetHandRecord(hand);
							
							//分配后资产状态为-已分配3，使用状态为-待领取2
							xzAsstes.setAssetsStatus("3");
							xzAsstes.setUseStatus("2");
							xzAsstes.setUseBy(xzAssetHandRecord.getRecipient());
							Long deptId=sysUserMapper.selectUserById(xzAssetHandRecord.getRecipient()).getDeptId();
							xzAsstes.setDepartment(deptId);
							xzAsstesMapper.updateXzAsstes(xzAsstes);
							
							//新增一条个人资产信息
							XzPersonalAsset person =new XzPersonalAsset();
							person.setAssetId(xzAsstes.getId());
							person.setUserId(xzAssetHandRecord.getRecipient());
							person.setAssetStatus("2");
							person.setCreateBy(xzAssetHandRecord.getRecipient().toString());
							person.setCreateTime(new Date());
							xzPersonalAssetMapper.insertXzPersonalAsset(person);
							
							//修改个人申请资产信息-状态
							XzPersonalApply apply =new XzPersonalApply();
							//分配成功为2，表示修改成功
							apply.setApplyStatus("2");
							apply.setApplyId(xzAssetHandRecord.getApplyId());
							xzPersonalApplyMapper.updateXzPersonalApply(apply);
						}
						
						//成功
						return "1";
				} catch (Exception e) {
					e.printStackTrace();
					//失败
					return "2";
				}
				
				
			}else{
				//库存不足
				return "3";
			}
			
		}else{
			return "3";
		}
		

		
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public String cancelHandRecord(XzAssetHandRecord xzAssetHandRecord) {
		try {
			//修改个人申请资产信息-状态
			XzPersonalApply apply =new XzPersonalApply();
			//申请失败 -3
			apply.setApplyStatus("3");
			apply.setApplyId(xzAssetHandRecord.getApplyId());
			xzPersonalApplyMapper.updateXzPersonalApply(apply);
			
			//增加一条取消分配的记录
			XzAssetHandRecord hand=new XzAssetHandRecord();
			hand.setDistributor(xzAssetHandRecord.getDistributor());
			hand.setDistributorStatus("3");
			hand.setRecipient(xzAssetHandRecord.getRecipient());
			hand.setDistributionDate(new Date());
			hand.setCreateBy(xzAssetHandRecord.getDistributor().toString());
			hand.setCreateTime(new Date());
			hand.setApplyId(xzAssetHandRecord.getApplyId());
			hand.setReason(xzAssetHandRecord.getReason());
			xzAssetHandRecordMapper.insertXzAssetHandRecord(hand);
			return "操作成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "操作失败";		}
		
	}

	@Override
	public XzAssetHandRecord selectXzAssetHandRecordByAssetId(Long assetId) {
		return xzAssetHandRecordMapper.selectXzAssetHandRecordByAssetId(assetId);
	}
	
}