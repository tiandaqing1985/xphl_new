package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.DangdangBack;
import com.ruoyi.system.domain.DangdangBaiduAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DangdangPcFrontMapper;
import com.ruoyi.system.domain.DangdangPcFront;
import com.ruoyi.system.service.IDangdangPcFrontService;
import com.ruoyi.common.core.text.Convert;

/**
 * 当当pc前端 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-19
 */
@Service
public class DangdangPcFrontServiceImpl implements IDangdangPcFrontService 
{
	@Autowired
	private DangdangPcFrontMapper dangdangPcFrontMapper;

	/**
     * 查询当当pc前端信息
     * 
     * @param id 当当pc前端ID
     * @return 当当pc前端信息
     */
    @Override
	public DangdangPcFront selectDangdangPcFrontById(Integer id)
	{
	    return dangdangPcFrontMapper.selectDangdangPcFrontById(id);
	}
	
	/**
     * 查询当当pc前端列表
     * 
     * @param dangdangPcFront 当当pc前端信息
     * @return 当当pc前端集合
     */
	@Override
	public List<DangdangPcFront> selectDangdangPcFrontList(DangdangPcFront dangdangPcFront)
	{
	    return dangdangPcFrontMapper.selectDangdangPcFrontList(dangdangPcFront);
	}
	
    /**
     * 新增当当pc前端
     * 
     * @param dangdangPcFront 当当pc前端信息
     * @return 结果
     */
	@Override
	public int insertDangdangPcFront(DangdangPcFront dangdangPcFront)
	{
	    return dangdangPcFrontMapper.insertDangdangPcFront(dangdangPcFront);
	}
	
	/**
     * 修改当当pc前端
     * 
     * @param dangdangPcFront 当当pc前端信息
     * @return 结果
     */
	@Override
	public int updateDangdangPcFront(DangdangPcFront dangdangPcFront)
	{
	    return dangdangPcFrontMapper.updateDangdangPcFront(dangdangPcFront);
	}

	/**
     * 删除当当pc前端对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDangdangPcFrontByIds(String ids)
	{
		return dangdangPcFrontMapper.deleteDangdangPcFrontByIds(Convert.toStrArray(ids));
	}

	@Override
	public String importBwFront(List<DangdangPcFront> bwList, Boolean isUpdateSupport, String operName) {
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
						List<DangdangPcFront> listPage = bwList.subList(0, pointsDataLimit);
						dangdangPcFrontMapper.batchInsert(listPage);
						//剔除
						bwList.subList(0, pointsDataLimit).clear();
					}
					if (!bwList.isEmpty()) {
						//表示最后剩下的数据
						dangdangPcFrontMapper.batchInsert(bwList);
					}
				} else {
					dangdangPcFrontMapper.batchInsert(bwList);
				}

			}
			return "导入成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "导入失败";
		}

	}
}
