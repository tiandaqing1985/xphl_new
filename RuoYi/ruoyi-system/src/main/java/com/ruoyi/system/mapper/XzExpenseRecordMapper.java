package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.XzExpenseRecord;
import com.ruoyi.system.domain.XzExpenseSta;

import java.util.List;
import java.util.Map;

/**
 * 费用记录 数据层
 * 
 * @author ruoyi
 * @date 2019-09-23
 */
public interface XzExpenseRecordMapper 
{
	/**
     * 查询费用记录信息
     * 
     * @param id 费用记录ID
     * @return 费用记录信息
     */
	public XzExpenseRecord selectXzExpenseRecordById(Long id);
	
	/**
     * 查询费用记录列表
     * 
     * @param xzExpenseRecord 费用记录信息
     * @return 费用记录集合
     */
	public List<XzExpenseRecord> selectXzExpenseRecordList(XzExpenseRecord xzExpenseRecord);
	
	/**
     * 新增费用记录
     * 
     * @param xzExpenseRecord 费用记录信息
     * @return 结果
     */
	public int insertXzExpenseRecord(XzExpenseRecord xzExpenseRecord);
	
	/**
     * 修改费用记录
     * 
     * @param xzExpenseRecord 费用记录信息
     * @return 结果
     */
	public int updateXzExpenseRecord(XzExpenseRecord xzExpenseRecord);
	
	/**
     * 删除费用记录
     * 
     * @param id 费用记录ID
     * @return 结果
     */
	public int deleteXzExpenseRecordById(Long id);
	
	/**
     * 批量删除费用记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteXzExpenseRecordByIds(String[] ids);

	/**
	 * 费用类型每月统计
	 * @param xzExpenseSta
	 * @return
	 */
	public List<XzExpenseSta> selectXzExpenseList(XzExpenseSta xzExpenseSta);

	/**
	 * 费用类型详情统计
	 * @param xzExpenseSta
	 * @return
	 */
	public List<XzExpenseSta> selectXzExpenseDetailList(XzExpenseSta xzExpenseSta);

    List<XzExpenseSta> selectCompareXzExpenseList(Map dataMap);

    List<XzExpenseSta> selectCompareXzExpenseDetailList(Map map);
}