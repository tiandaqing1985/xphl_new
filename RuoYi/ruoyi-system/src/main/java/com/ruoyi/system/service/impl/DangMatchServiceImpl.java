package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;

import com.ruoyi.system.domain.DangMatch;
import com.ruoyi.system.mapper.DangMatchMapper;
import com.ruoyi.system.service.IDDangMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.ruoyi.common.core.text.Convert;

/**
 * 当当词性匹配 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-10
 */
@Service
public class DangMatchServiceImpl implements IDDangMatchService
{
	@Autowired
	private DangMatchMapper dangdangMatchMapper;

	/**
     * 查询当当词性匹配信息
     * 
     * @param id 当当词性匹配ID
     * @return 当当词性匹配信息
     */
    @Override
	public DangMatch selectDangdangMatchById(Integer id)
	{
	    return dangdangMatchMapper.selectDangdangMatchById(id);
	}
	
	/**
     * 查询当当词性匹配列表
     * 
     * @param dangdangMatch 当当词性匹配信息
     * @return 当当词性匹配集合
     */
	@Override
	public List<DangMatch> selectDangdangMatchList(DangMatch dangdangMatch)
	{
	    return dangdangMatchMapper.selectDangdangMatchList(dangdangMatch);
	}
	
    /**
     * 新增当当词性匹配
     * 
     * @param dangdangMatch 当当词性匹配信息
     * @return 结果
     */
	@Override
	public int insertDangdangMatch(DangMatch dangdangMatch)
	{
	    return dangdangMatchMapper.insertDangdangMatch(dangdangMatch);
	}
	
	/**
     * 修改当当词性匹配
     * 
     * @param dangdangMatch 当当词性匹配信息
     * @return 结果
     */
	@Override
	public int updateDangdangMatch(DangMatch dangdangMatch)
	{
	    return dangdangMatchMapper.updateDangdangMatch(dangdangMatch);
	}

	/**
     * 删除当当词性匹配对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDangdangMatchByIds(String ids)
	{
		return dangdangMatchMapper.deleteDangdangMatchByIds(Convert.toStrArray(ids));
	}
	@Override
	public String importBwFront(List<DangMatch> bwList, Boolean isUpdateSupport, String operName) {
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
						List<DangMatch> listPage = bwList.subList(0, pointsDataLimit);
						dangdangMatchMapper.batchInsert(listPage);
						//剔除
						bwList.subList(0, pointsDataLimit).clear();
					}
					if (!bwList.isEmpty()) {
						//表示最后剩下的数据
						dangdangMatchMapper.batchInsert(bwList);
					}
				} else {
					dangdangMatchMapper.batchInsert(bwList);
				}
			}
			dangdangMatchMapper.updateGroupword(null);
			return "导入成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "导入失败";
		}

	}

}
