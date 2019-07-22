package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.DangdangPcBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DangdangBackSearchMapper;
import com.ruoyi.system.domain.DangdangBackSearch;
import com.ruoyi.system.service.IDangdangBackSearchService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 当当pc端搜索广告后端 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-21
 */
@Service
public class DangdangBackSearchServiceImpl implements IDangdangBackSearchService 
{
	@Autowired
	private DangdangBackSearchMapper dangdangBackSearchMapper;

	/**
     * 查询当当pc端搜索广告后端信息
     * 
     * @param id 当当pc端搜索广告后端ID
     * @return 当当pc端搜索广告后端信息
     */
    @Override
	public DangdangBackSearch selectDangdangBackSearchById(Integer id)
	{
	    return dangdangBackSearchMapper.selectDangdangBackSearchById(id);
	}
	
	/**
     * 查询当当pc端搜索广告后端列表
     * 
     * @param dangdangBackSearch 当当pc端搜索广告后端信息
     * @return 当当pc端搜索广告后端集合
     */
	@Override
	public List<DangdangBackSearch> selectDangdangBackSearchList(DangdangBackSearch dangdangBackSearch)
	{
	    return dangdangBackSearchMapper.selectDangdangBackSearchList(dangdangBackSearch);
	}
	
    /**
     * 新增当当pc端搜索广告后端
     * 
     * @param dangdangBackSearch 当当pc端搜索广告后端信息
     * @return 结果
     */
	@Override
	public int insertDangdangBackSearch(DangdangBackSearch dangdangBackSearch)
	{
	    return dangdangBackSearchMapper.insertDangdangBackSearch(dangdangBackSearch);
	}
	
	/**
     * 修改当当pc端搜索广告后端
     * 
     * @param dangdangBackSearch 当当pc端搜索广告后端信息
     * @return 结果
     */
	@Override
	public int updateDangdangBackSearch(DangdangBackSearch dangdangBackSearch)
	{
	    return dangdangBackSearchMapper.updateDangdangBackSearch(dangdangBackSearch);
	}

	/**
     * 删除当当pc端搜索广告后端对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDangdangBackSearchByIds(String ids)
	{
		return dangdangBackSearchMapper.deleteDangdangBackSearchByIds(Convert.toStrArray(ids));
	}
	@Transactional
	@Override
	public String importBwFront(List<DangdangBackSearch> bwList, Boolean isUpdateSupport, String operName) {
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
						List<DangdangBackSearch> listPage = bwList.subList(0, pointsDataLimit);
						dangdangBackSearchMapper.batchInsert(listPage);
						//剔除
						bwList.subList(0, pointsDataLimit).clear();
					}
					if (!bwList.isEmpty()) {
						//表示最后剩下的数据
						dangdangBackSearchMapper.batchInsert(bwList);
					}
				} else {
					dangdangBackSearchMapper.batchInsert(bwList);
				}
			}
			dangdangBackSearchMapper.updateJoin();

			return "导入成功";
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// catch块中显示让事务回滚，保证数据完整性
			e.printStackTrace();
			return "导入失败";
		}

	}
}
