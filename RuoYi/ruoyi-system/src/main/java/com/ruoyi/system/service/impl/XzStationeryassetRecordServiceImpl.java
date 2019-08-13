package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.mapper.XzStationeryassetMapper;
import com.ruoyi.system.mapper.XzStationeryassetRecordMapper;
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
			// 提交方式为保存时
			xzStationeryassetRecordMapper.insertXzStationeryassetRecord(xzSar);
		}
		if(("2").equals(xzSar.getSubmitType())){
			XzStationeryasset sa=new XzStationeryasset();
			sa.setStationeryAssetId(xzSar.getId());
			sa.setBrand(xzSar.getBrand());
			sa.setUnit(xzSar.getUnit());
			sa.setAssetTypeId(xzSar.getAssetsType());
			/*sa.setRukuCount(xzSar.getCount());
			sa.setKucunCount(xzSar.getCount());
			sa.setFenpeiCount(xzSar.getCount());
			sa.setChushiCount(xzSar.getCount());
			sa.setGuihaiCount(xzSar.getCount());*/
			xzStationeryassetMapper.insertXzStationeryasset(sa);
			xzStationeryassetRecordMapper.insertXzStationeryassetRecord(xzSar);
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
