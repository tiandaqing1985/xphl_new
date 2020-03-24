package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.XzExpenseRecordMapper;
import com.ruoyi.system.domain.XzExpenseRecord;
import com.ruoyi.system.domain.XzExpenseSta;
import com.ruoyi.system.service.IXzExpenseRecordService;
import com.ruoyi.common.core.text.Convert;

/**
 * 费用记录 服务层实现
 * 
 * @author ruoyi
 * @date 2019-09-23
 */
@Service
public class XzExpenseRecordServiceImpl implements IXzExpenseRecordService {
	@Autowired
	private XzExpenseRecordMapper xzExpenseRecordMapper;

	/**
	 * 查询费用记录信息
	 * 
	 * @param id
	 *            费用记录ID
	 * @return 费用记录信息
	 */
	@Override
	public XzExpenseRecord selectXzExpenseRecordById(Long id) {
		return xzExpenseRecordMapper.selectXzExpenseRecordById(id);
	}

	/**
	 * 查询费用记录列表
	 * 
	 * @param xzExpenseRecord
	 *            费用记录信息
	 * @return 费用记录集合
	 */
	@Override
	public List<XzExpenseRecord> selectXzExpenseRecordList(XzExpenseRecord xzExpenseRecord) {
		return xzExpenseRecordMapper.selectXzExpenseRecordList(xzExpenseRecord);
	}

	/**
	 * 新增费用记录
	 * 
	 * @param xzExpenseRecord
	 *            费用记录信息
	 * @return 结果
	 */
	@Override
	public int insertXzExpenseRecord(XzExpenseRecord xzExpenseRecord) {
		// 计算总金额
		BigDecimal b1 = new BigDecimal(xzExpenseRecord.getUnitPrice());
		BigDecimal b2 = new BigDecimal(Double.valueOf(xzExpenseRecord.getCount()));
		xzExpenseRecord.setSumPrice(b1.multiply(b2).doubleValue());
		return xzExpenseRecordMapper.insertXzExpenseRecord(xzExpenseRecord);
	}

	/**
	 * 修改费用记录
	 * 
	 * @param xzExpenseRecord
	 *            费用记录信息
	 * @return 结果
	 */
	@Override
	public int updateXzExpenseRecord(XzExpenseRecord xzExpenseRecord) {
		// 计算总金额
		BigDecimal b1 = new BigDecimal(xzExpenseRecord.getUnitPrice());
		BigDecimal b2 = new BigDecimal(Double.valueOf(xzExpenseRecord.getCount()));
		xzExpenseRecord.setSumPrice(b1.multiply(b2).doubleValue());
		return xzExpenseRecordMapper.updateXzExpenseRecord(xzExpenseRecord);
	}

	/**
	 * 删除费用记录对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteXzExpenseRecordByIds(String ids) {
		return xzExpenseRecordMapper.deleteXzExpenseRecordByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<XzExpenseSta> selectXzExpenseList(XzExpenseSta xzExpenseSta) {
		return xzExpenseRecordMapper.selectXzExpenseList(xzExpenseSta);
	}

	@Override
	public List<XzExpenseSta> selectXzExpenseDetailList(XzExpenseSta xzExpenseSta) {
		return xzExpenseRecordMapper.selectXzExpenseDetailList(xzExpenseSta);
	}

	@Override
	public List<XzExpenseSta> selectCompareXzExpenseList(Map dataMap) {
		return xzExpenseRecordMapper.selectCompareXzExpenseList(dataMap);
	}

	@Override
	public List<XzExpenseSta> selectCompareXzExpenseDetailList(Map map) {
		return xzExpenseRecordMapper.selectCompareXzExpenseDetailList(map);
	}
}
