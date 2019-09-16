package com.ruoyi.system.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.XzAssetDataMapper;
import com.ruoyi.system.mapper.XzAssetTypeMapper;
import com.ruoyi.system.mapper.XzAsstesMapper;
import com.ruoyi.system.mapper.XzPersonalAssetMapper;
import com.ruoyi.system.domain.XzAsstes;
import com.ruoyi.system.domain.XzAsstesSta;
import com.ruoyi.system.domain.XzPersonalAsset;
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

	@Autowired
	private XzAssetTypeMapper xzAssetTypeMapper;

	@Autowired
	private XzAssetDataMapper xzAssetDataMapper;
	
	@Autowired
	private XzPersonalAssetMapper xzPersonalAssetMapper;

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
		xzAsstes.setPurchaseBy(sysUserMapper.selectUserIdByUserNameOnly(xzAsstes.getPurchaseName()));
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
	@Transactional(rollbackFor = Exception.class)
	public String insertXzAsstes(XzAsstes xzAsstes) {
		XzAsstes x = new XzAsstes();
		x.setAnnex(xzAsstes.getAnnex());
		x.setAssetsModel(xzAsstes.getAssetsModel());
		x.setAssetsName(xzAsstes.getAssetsName());
		x.setAssetsStatus(xzAsstes.getAssetsStatus());
		x.setAssetsType(xzAsstes.getAssetsType());
		x.setAssetsType2(xzAsstes.getAssetsType2());
		x.setAttach(xzAsstes.getAttach());
		x.setBrand(xzAsstes.getBrand());
		x.setBuyAddress(xzAsstes.getBuyAddress());
		x.setBuyDate(xzAsstes.getBuyDate());
		x.setCreateBy(xzAsstes.getCreateBy());
		x.setCreateTime(xzAsstes.getCreateTime());
		x.setExtendContent(xzAsstes.getExtendContent());
		x.setExtendContent(xzAsstes.getExtendMoney());
		x.setExtendTime(xzAsstes.getExtendTime());
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
			// 固定资产
			if (xzAsstes.getSort().equals("1")) {
				if(xzAsstes.getCategory()==null || xzAsstes.getCategory()==""){
					x.setCategory(xzAsstes.getCategory());
				}else{
					String ca = xzAsstes.getCategory().replace("，", ",");
					List<String> category = Arrays.asList(ca.split(","));
					String[] split = ca.split(",");
					// 提交状态为1时，说明是保存数据
					if (xzAsstes.getSubmitType().equals("1")) {
						if (Integer.parseInt(xzAsstes.getCount()) == split.length) {
							for (int sn = 0; sn < category.size(); sn++) {
								x.setCount("1");
								x.setCategory(category.get(sn));
								System.out.println(category.get(sn) + "*************************");
								xzAsstesMapper.insertXzAsstes(x);
							}

						} else {
							// 错误返回1
							return "1";
						}

					}
					// 提交状态为2时，说明是数据提交入库
					if (xzAsstes.getSubmitType().equals("2")) {
						if (Integer.parseInt(xzAsstes.getCount()) == split.length) {
							for (int sn = 0; sn < category.size(); sn++) {
								x.setCount("1");
								x.setCategory(category.get(sn));
								// 需要加上资产编码
								x.setAssetsCode(createCode(xzAsstes));
								// 提交人、 提交时间
								x.setSubBy(xzAsstes.getSubBy());
								x.setSubTime(xzAsstes.getSubTime());
								xzAsstesMapper.insertXzAsstes(x);
							}
						} else {
							// 错误返回1
							return "1";
						}
					}
				}
		
			}
			if (xzAsstes.getSort().equals("2")) {
				// 办公资产
				if (xzAsstes.getSubmitType().equals("1")) {
					for (int i = 0; i < Integer.parseInt(xzAsstes.getCount()); i++) {
						x.setCount("1");
						xzAsstesMapper.insertXzAsstes(x);
					}

				}

				// 提交状态为2时，说明是数据提交入库
				if (xzAsstes.getSubmitType().equals("2")) {
					for (int i = 0; i < Integer.parseInt(xzAsstes.getCount()); i++) {
						x.setCount("1");
						// 需要加上资产编码
						x.setAssetsCode(createCode(xzAsstes));
						// 提交人、 提交时间
						x.setSubBy(xzAsstes.getSubBy());
						x.setSubTime(xzAsstes.getSubTime());
						xzAsstesMapper.insertXzAsstes(x);
					}
				}
			}

			return "录入成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "录入失败";
		}

	}

	/**
	 * 新增资产
	 * 
	 * @param xzAsstes
	 *            资产信息
	 * @return 结果
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String insertStaAsstes(XzAsstes xzAsstes) {
		XzAsstes x = new XzAsstes();
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
			for (int i = 0; i < Integer.parseInt(xzAsstes.getCount()); i++) {

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

		// 判断此条资产有无资产编码（有 代表是提交后修改；无 代表是未提交资产修改 保存/提交）
		if (xzAsstes.getAssetsCode() == null || xzAsstes.getAssetsCode().isEmpty()) {
			if ("2".equals(xzAsstes.getSubmitType())) {
				xzAsstes.setAssetsCode(createCode(xzAsstes));
			}
		}
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
		
		//修改该用户所有个人资产未领取信息状态
		XzPersonalAsset xzPersonalAsset= new XzPersonalAsset();
		xzPersonalAsset.setUserId(userId);
		xzPersonalAssetMapper.updateByAssetUserId(xzPersonalAsset);
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
		String sort = xzAsstesMapper.selectXzAsstesById(xzAsstes.getId()).getSort();
		if (("1").equals(sort)) {
			xzAsstesMapper.updateXzAsstesByAssId(xzAsstes);
		} else if (("2").equals(sort)) {
			xzAsstesMapper.updateXzAsstesByStaId(xzAsstes);
		} else {

		}
		//修改该用户所有个人资产未领取信息状态
		XzPersonalAsset xzPersonalAsset= new XzPersonalAsset();
		xzPersonalAsset.setUserId(xzAsstes.getUseBy());
		xzPersonalAsset.setAssetId(xzAsstes.getId());
		xzPersonalAssetMapper.updateByAssetId(xzPersonalAsset);
		return "领取成功";
	}

	@Override
	public String selectMaxCodeByType(XzAsstes xzAsstes) {
		return xzAsstesMapper.selectMaxCodeByType(xzAsstes);
	}

	public String createCode(XzAsstes xzAsstes) {

		String code = null;
		String region = null;

		if (xzAsstes.getRegion() != null && xzAsstes.getRegion() != "") {
			if (xzAsstes.getRegion().equals("1")) {
				region = "-XPHL";
			} else if (xzAsstes.getRegion().equals("2")) {
				region = "-SHYH";
			} else if (xzAsstes.getRegion().equals("3")) {
				region = "-XPHL(GZ)";
			} else if (xzAsstes.getRegion().equals("4")) {
				region = "-XJPFT";
			} else {
			}
		}

		// 查询类型编码
		String a = xzAssetTypeMapper.selectXzAssetTypeById(xzAsstes.getAssetsType()).getCode();
		String b = xzAssetDataMapper.selectXzAssetDataById(xzAsstes.getAssetsType2()).getCode();
		// 查询此编码格式编号的最大值，然后加1 如果没有，则从0001开始
		String maxNum = xzAsstesMapper.selectMaxCodeByType(xzAsstes);

		if (maxNum == null || maxNum.isEmpty()) {
			// 第一条数据
			code = a + b + "0001" + region;
		} else {
			// 编码加1
			int num = 10000 + Integer.parseInt(maxNum) + 1;
			// 截取：10001 截取后四位
			code = a + b + String.valueOf(num).substring(1, 5) + region;
		}
		return code;
	}

	@Override
	@Transactional
	public String updateXzAsstesBySub(String ids, XzAsstes xzAsstes) {
		try {
			List<String> id = Arrays.asList(ids.split(","));

			for (int i = 0; i < id.size(); i++) {
				XzAsstes s = xzAsstesMapper.selectXzAsstesById(Long.valueOf(id.get(i)));
				if ("1".equals(s.getSubmitType())) {
					xzAsstes.setId(Long.valueOf(id.get(i)));
					xzAsstes.setAssetsCode(createCode(s));
					xzAsstesMapper.updateXzAsstes(xzAsstes);
				} else {
					return "1";
				}
			}
			return "提交成功";
		} catch (Exception e) {
			return "提交失败";
		}

	}

	@Override
	public List<XzAsstesSta> selectXzStatisticsList(XzAsstesSta xzAsstesSta) {
		return xzAsstesMapper.selectXzStatisticsList(xzAsstesSta);
	}

}