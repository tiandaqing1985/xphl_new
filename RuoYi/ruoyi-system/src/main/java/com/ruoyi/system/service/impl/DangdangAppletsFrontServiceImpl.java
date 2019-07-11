package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.DangdangAppFront;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DangdangAppletsFrontMapper;
import com.ruoyi.system.domain.DangdangAppletsFront;
import com.ruoyi.system.service.IDangdangAppletsFrontService;
import com.ruoyi.common.core.text.Convert;

/**
 * 当当小程序前端 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-11
 */
@Service
public class DangdangAppletsFrontServiceImpl implements IDangdangAppletsFrontService 
{
	@Autowired
	private DangdangAppletsFrontMapper dangdangAppletsFrontMapper;

	/**
     * 查询当当小程序前端信息
     * 
     * @param id 当当小程序前端ID
     * @return 当当小程序前端信息
     */
    @Override
	public DangdangAppletsFront selectDangdangAppletsFrontById(Integer id)
	{
	    return dangdangAppletsFrontMapper.selectDangdangAppletsFrontById(id);
	}
	
	/**
     * 查询当当小程序前端列表
     * 
     * @param dangdangAppletsFront 当当小程序前端信息
     * @return 当当小程序前端集合
     */
	@Override
	public List<DangdangAppletsFront> selectDangdangAppletsFrontList(DangdangAppletsFront dangdangAppletsFront)
	{
	    return dangdangAppletsFrontMapper.selectDangdangAppletsFrontList(dangdangAppletsFront);
	}
	
    /**
     * 新增当当小程序前端
     * 
     * @param dangdangAppletsFront 当当小程序前端信息
     * @return 结果
     */
	@Override
	public int insertDangdangAppletsFront(DangdangAppletsFront dangdangAppletsFront)
	{
	    return dangdangAppletsFrontMapper.insertDangdangAppletsFront(dangdangAppletsFront);
	}
	
	/**
     * 修改当当小程序前端
     * 
     * @param dangdangAppletsFront 当当小程序前端信息
     * @return 结果
     */
	@Override
	public int updateDangdangAppletsFront(DangdangAppletsFront dangdangAppletsFront)
	{
	    return dangdangAppletsFrontMapper.updateDangdangAppletsFront(dangdangAppletsFront);
	}

	/**
     * 删除当当小程序前端对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDangdangAppletsFrontByIds(String ids)
	{
		return dangdangAppletsFrontMapper.deleteDangdangAppletsFrontByIds(Convert.toStrArray(ids));
	}
	@Override
	public String importBwFront(List<DangdangAppletsFront> bwList, Boolean isUpdateSupport, String operName) {
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
						List<DangdangAppletsFront> listPage = bwList.subList(0, pointsDataLimit);
						dangdangAppletsFrontMapper.batchInsert(listPage);
						//剔除
						bwList.subList(0, pointsDataLimit).clear();
					}
					if (!bwList.isEmpty()) {
						//表示最后剩下的数据
						dangdangAppletsFrontMapper.batchInsert(bwList);
					}
				} else {
					dangdangAppletsFrontMapper.batchInsert(bwList);
				}
			}
			dangdangAppletsFrontMapper.updateGroupword(null);
			return "导入成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "导入失败";
		}

	}
}
