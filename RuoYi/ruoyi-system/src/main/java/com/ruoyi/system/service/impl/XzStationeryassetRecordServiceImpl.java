package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.XzAssetDataMapper;
import com.ruoyi.system.mapper.XzAssetTypeMapper;
import com.ruoyi.system.mapper.XzStationeryassetMapper;
import com.ruoyi.system.mapper.XzStationeryassetRecordMapper;
import com.ruoyi.system.domain.XzAssetData;
import com.ruoyi.system.domain.XzAssetType;
import com.ruoyi.system.domain.XzStationeryasset;
import com.ruoyi.system.domain.XzStationeryassetRecord;
import com.ruoyi.system.service.IXzStationeryassetRecordService;
import com.ruoyi.common.core.text.Convert;

/**
 * 办公用品分配记录 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-12
 */
@Service
public class XzStationeryassetRecordServiceImpl implements IXzStationeryassetRecordService 
{
	@Autowired
	private XzStationeryassetRecordMapper xzStationeryassetRecordMapper;
	
	@Autowired
	private XzStationeryassetMapper xzStationeryassetMapper;
	
	@Autowired
	private XzAssetTypeMapper xzAssetTypeMapper;
	
	@Autowired
	private XzAssetDataMapper xzAssetDataMapper;
	
	/**
     * 查询办公用品分配记录信息
     * 
     * @param id 办公用品分配记录ID
     * @return 办公用品分配记录信息
     */
    @Override
	public XzStationeryassetRecord selectXzStationeryassetRecordById(Long id)
	{
	    return xzStationeryassetRecordMapper.selectXzStationeryassetRecordById(id);
	}
	
	/**
     * 查询办公用品分配记录列表
     * 
     * @param xzStationeryassetRecord 办公用品分配记录信息
     * @return 办公用品分配记录集合
     */
	@Override
	public List<XzStationeryassetRecord> selectXzStationeryassetRecordList(XzStationeryassetRecord xzStationeryassetRecord)
	{
	    return xzStationeryassetRecordMapper.selectXzStationeryassetRecordList(xzStationeryassetRecord);
	}
	
	/**
	 * 新增办公用品分配记录
	 * 
	 * @param xzStationeryassetRecord
	 *            办公用品分配记录信息
	 * @return 结果
	 */
	@Override
	public String insertXzStationeryassetRecord(XzStationeryassetRecord xzSar) {
		if (("1").equals(xzSar.getSubmitType())) {
			// 提交方式为保存时（未入库）
			xzStationeryassetRecordMapper.insertXzStationeryassetRecord(xzSar);
		}
		if(("2").equals(xzSar.getSubmitType())){
			// 提交方式为提交时（入库）
			
			//根据资产父类型及子类型，判断是否存在资产类型的数据，若有，update更正数量及信息，若无，新增一条数据
			XzStationeryasset asset=new XzStationeryasset();
			asset.setRegion(xzSar.getRegion());
			asset.setAssetTypeId(xzSar.getAssetsType());
			asset.setStationeryAssetId(xzSar.getAssetsType2());
			int count = xzStationeryassetMapper.selectXzStationeryassetByTypeId(asset);
			
			if(count>0){
				//更新
				XzStationeryasset xzst=xzStationeryassetMapper.selectXzStationeryassetByType(asset);
				
				
			}else{
				//新增
				XzStationeryasset sa=new XzStationeryasset();
				sa.setRegion(xzSar.getRegion());
				//资产类型父类型、子类型
				XzAssetType xzat=xzAssetTypeMapper.selectXzAssetTypeById(xzSar.getAssetsType());
				XzAssetData xzad=xzAssetDataMapper.selectXzAssetDataById(xzSar.getAssetsType2());
				sa.setStationeryAssetId(xzad.getId());
				sa.setStationeryAssetName(xzad.getName());
				sa.setAssetId(xzat.getId());
				sa.setAssetTypeName(xzat.getName());
				/*sa.setBrandType(xzSar.getBrand());*/
				sa.setBrand(xzSar.getBrand());
				sa.setUnit(xzSar.getUnit());
				int chushi=0,fenpei=0,ruku=0,guihuan=0,kucun=0;
				//初始数量
				sa.setChushiCount(chushi);
				//分配数量
				sa.setFenpeiCount(fenpei);
				//入库数量
				sa.setRukuCount(ruku+xzSar.getCount());
				//归还数量
				sa.setGuihaiCount(guihuan);
				//库存数量=初始数量-分配数量+入库数量+归还数量
				sa.setKucunCount(kucun-fenpei+xzSar.getCount()+guihuan);
				sa.setTime(xzSar.getCreateTime().toString());
				//资产分类-办公用品-2
				sa.setCategoryId(2);
				
				//根据资产父类型及子类型，判断是否存在次类型的数据，若有，update更正数量及信息，若无，新增一条数据
				xzStationeryassetMapper.insertXzStationeryasset(sa);
			}
			
			
			
			
			//提交成功后，记录表中新增一条购买信息（记录表，非分配表）
			/*xzStationeryassetRecordMapper.insertXzStationeryassetRecord(xzSar);*/
		}
		return "添加成功";
	}
	
	/**
     * 修改办公用品分配记录
     * 
     * @param xzStationeryassetRecord 办公用品分配记录信息
     * @return 结果
     */
	@Override
	public int updateXzStationeryassetRecord(XzStationeryassetRecord xzStationeryassetRecord)
	{
	    return xzStationeryassetRecordMapper.updateXzStationeryassetRecord(xzStationeryassetRecord);
	}

	/**
     * 删除办公用品分配记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteXzStationeryassetRecordByIds(String ids)
	{
		return xzStationeryassetRecordMapper.deleteXzStationeryassetRecordByIds(Convert.toStrArray(ids));
	}
	
}