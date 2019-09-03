package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.XzAsstesMapper;
import com.ruoyi.system.domain.XzAsstes;
import com.ruoyi.system.service.IXzAsstesService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;

/**
 * 资产 服务层实现
 * 
 * @author ruoyi
 * @date 2019-08-02
 */
@Service
public class XzAsstesServiceImpl implements IXzAsstesService {
	@Autowired
	private XzAsstesMapper xzAsstesMapper;
	
	@Autowired
	private SysUserMapper sysUserMapper;

	/**
	 * 查询资产信息
	 * 
	 * @param id
	 *            资产ID
	 * @return 资产信息
	 */
	@Override
	public XzAsstes selectXzAsstesById(Long id) {
		return xzAsstesMapper.selectXzAsstesById(id);
	}

	/**
	 * 查询资产列表
	 * 
	 * @param xzAsstes
	 *            资产信息
	 * @return 资产集合
	 */
	@Override
	public List<XzAsstes> selectXzAsstesList(XzAsstes xzAsstes) {
		return xzAsstesMapper.selectXzAsstesList(xzAsstes);
	}

	/**
	 * 新增资产
	 * 
	 * @param xzAsstes
	 *            资产信息
	 * @return 结果
	 */
	@Override
	public int insertXzAsstes(XzAsstes xzAsstes) {
		xzAsstes.setPurchaseBy(sysUserMapper.selectUserIdByUserNameOnly(xzAsstes.getPurchaseName()));
		return xzAsstesMapper.insertXzAsstes(xzAsstes);
	}
	
	/**
	 * 新增资产
	 * 
	 * @param xzAsstes
	 *            资产信息
	 * @return 结果
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String insertStaAsstes(XzAsstes xzAsstes) {
		XzAsstes x=new XzAsstes();
		x.setAnnex(xzAsstes.getAnnex());
		x.setAssetsModel(xzAsstes.getAssetsModel());
		x.setAssetsName(xzAsstes.getAssetsName());
		x.setAssetsStatus(xzAsstes.getAssetsStatus());
		x.setAssetsType(xzAsstes.getAssetsType());
		x.setAssetsType2(xzAsstes.getAssetsType2());
		x.setBrand(xzAsstes.getBrand());
		x.setBuyAddress(xzAsstes.getBuyAddress());
		x.setBuyDate(xzAsstes.getBuyDate());
		x.setCreateBy(xzAsstes.getCreateBy());
		x.setCreateTime(xzAsstes.getCreateTime());
		x.setInvoiceNum(xzAsstes.getInvoiceNum());
		x.setInvoiceType(xzAsstes.getInvoiceType());
		x.setPrice(xzAsstes.getPrice());
		x.setPurchaseBy(sysUserMapper.selectUserIdByUserNameOnly(xzAsstes.getPurchaseName()));
		x.setPurchaseNum(xzAsstes.getPurchaseNum());
		x.setRegion(xzAsstes.getRegion());
		x.setRemarks(xzAsstes.getRemarks());
		x.setSort(xzAsstes.getSort());
		x.setSubmitType(xzAsstes.getSubmitType());
		x.setUnit(xzAsstes.getUnit());
		x.setUseStatus(xzAsstes.getUseStatus());
		try {
			for(int i=0; i<Integer.parseInt(xzAsstes.getCount());i++){
				x.setCount("1");
				xzAsstesMapper.insertXzAsstes(x);
			}
			
			return "录入成功";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "录入失败";
		}
		
		
	}
	

	/**
	 * 修改资产
	 * 
	 * @param xzAsstes
	 *            资产信息
	 * @return 结果
	 */
	@Override
	public int updateXzAsstes(XzAsstes xzAsstes) {
		return xzAsstesMapper.updateXzAsstes(xzAsstes);

	}

	/**
	 * 删除资产对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteXzAsstesByIds(String ids) {
		return xzAsstesMapper.deleteXzAsstesByIds(Convert.toStrArray(ids));
	}

	@Override
	@Transactional
	public String importXzAsstes(List<XzAsstes> assetsList, boolean updateSupport, String operName) {

		if (StringUtils.isNull(assetsList) || assetsList.size() == 0) {
			throw new BusinessException("导入资产数据不能为空！");
		}
		try {
			if (null != assetsList && assetsList.size() > 0) {
				int pointsDataLimit = 10000;// 限制条数
				Integer size = assetsList.size();
				// 判断是否有必要分批
				if (pointsDataLimit < size) {
					int part = size / pointsDataLimit;// 分批数
					System.out.println("共有 ： " + size + "条，！" + " 分为 ：" + part + "批");
					for (int i = 0; i < part; i++) {
						// 1000条
						List<XzAsstes> listPage = assetsList.subList(0, pointsDataLimit);
						xzAsstesMapper.batchInsert(listPage);
						// 剔除
						assetsList.subList(0, pointsDataLimit).clear();
					}
					if (!assetsList.isEmpty()) {
						// 表示最后剩下的数据
						xzAsstesMapper.batchInsert(assetsList);
					}
				} else {
					xzAsstesMapper.batchInsert(assetsList);
				}
			}

			return "导入成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "导入失败";
		}

	}

	@Override
	@Transactional
	public String updateXzAsstesByAllDraw(Long userId) {
		XzAsstes xzAsstes = new XzAsstes();
		xzAsstes.setUseBy(userId);
		xzAsstes.setUseTime(new Date());
		// 修改固定资产状态
		xzAsstesMapper.updateXzAsstesByAssDraw(xzAsstes);
		// 修改办公资产状态
		xzAsstesMapper.updateXzAsstesByStaDraw(xzAsstes);
		return "领取成功";
	}

	/**
	 * 查询数量
	 */
	@Override
	public int countXzAsstesByAllDraw(Long userId) {
		return xzAsstesMapper.countXzAsstesByAllDraw(userId);
	}

	/**
	 * 修改资产使用状态
	 */
	@Override
	@Transactional
	public String updateXzAsstesByAssetId(XzAsstes xzAsstes) {
		String sort=xzAsstesMapper.selectXzAsstesById(xzAsstes.getId()).getSort();
		if(("1").equals(sort)){
			xzAsstesMapper.updateXzAsstesByAssId(xzAsstes);
		}else if(("2").equals(sort)){
			xzAsstesMapper.updateXzAsstesByStaId(xzAsstes);
		}else{
			
		}
		 return "领取成功";
	}
}