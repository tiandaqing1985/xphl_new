package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;

import com.ruoyi.system.domain.DangSummary;
import com.ruoyi.system.mapper.DangSummaryMapper;
import com.ruoyi.system.service.IDangSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.ruoyi.common.core.text.Convert;

/**
 * 当当前端消费汇总 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-11
 */
@Service
public class DangSummaryServiceImpl implements IDangSummaryService
{
	@Autowired
	private DangSummaryMapper dangdangSummaryMapper;

	/**
     * 查询当当前端消费汇总信息
     * 
     * @param id 当当前端消费汇总ID
     * @return 当当前端消费汇总信息
     */
    @Override
	public DangSummary selectDangdangSummaryById(Integer id)
	{
	    return dangdangSummaryMapper.selectDangdangSummaryById(id);
	}
	
	/**
     * 查询当当前端消费汇总列表
     * 
     * @param dangdangSummary 当当前端消费汇总信息
     * @return 当当前端消费汇总集合
     */
	@Override
	public List<DangSummary> selectDangdangSummaryList(DangSummary dangdangSummary)
	{
	    return dangdangSummaryMapper.selectDangdangSummaryList(dangdangSummary);
	}
	
    /**
     * 新增当当前端消费汇总
     * 
     * @param dangdangSummary 当当前端消费汇总信息
     * @return 结果
     */
	@Override
	public int insertDangdangSummary(DangSummary dangdangSummary)
	{
	    return dangdangSummaryMapper.insertDangdangSummary(dangdangSummary);
	}
	
	/**
     * 修改当当前端消费汇总
     * 
     * @param dangdangSummary 当当前端消费汇总信息
     * @return 结果
     */
	@Override
	public int updateDangdangSummary(DangSummary dangdangSummary)
	{
	    return dangdangSummaryMapper.updateDangdangSummary(dangdangSummary);
	}

	/**
     * 删除当当前端消费汇总对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDangdangSummaryByIds(String ids)
	{
		return dangdangSummaryMapper.deleteDangdangSummaryByIds(Convert.toStrArray(ids));
	}
	@Override
	public String importBwFront(List<DangSummary> bwList, Boolean isUpdateSupport, String operName) {
		if (StringUtils.isNull(bwList) || bwList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}

		try {
			String flag = "";
			if (null != bwList && bwList.size() > 0) {
				int pointsDataLimit = 10000;//限制条数
				Integer size = bwList.size();
				//判断是否有必要分批
				if (pointsDataLimit < size) {
					int part = size / pointsDataLimit;//分批数
					// System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
					//
					for (int i = 0; i < part; i++) {
						//1000条
						List<DangSummary> listPage = bwList.subList(0, pointsDataLimit);
						dangdangSummaryMapper.batchInsert(listPage);
						//剔除
						bwList.subList(0, pointsDataLimit).clear();
					}
					if (!bwList.isEmpty()) {
						//表示最后剩下的数据
						dangdangSummaryMapper.batchInsert(bwList);
					}
				} else {
					dangdangSummaryMapper.batchInsert(bwList);
				}
			}
			dangdangSummaryMapper.updateGroupword(null);
			return "导入成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "导入失败";
		}

	}
}
