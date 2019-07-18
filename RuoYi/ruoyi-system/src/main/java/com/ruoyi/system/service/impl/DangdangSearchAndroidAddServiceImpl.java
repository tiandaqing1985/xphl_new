package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.DangdangSearchIosAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DangdangSearchAndroidAddMapper;
import com.ruoyi.system.domain.DangdangSearchAndroidAdd;
import com.ruoyi.system.service.IDangdangSearchAndroidAddService;
import com.ruoyi.common.core.text.Convert;

/**
 * 当当搜索安卓后端数据 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-15
 */
@Service
public class DangdangSearchAndroidAddServiceImpl implements IDangdangSearchAndroidAddService 
{
	@Autowired
	private DangdangSearchAndroidAddMapper dangdangSearchAndroidAddMapper;

	/**
     * 查询当当搜索安卓后端数据信息
     * 
     * @param id 当当搜索安卓后端数据ID
     * @return 当当搜索安卓后端数据信息
     */
    @Override
	public DangdangSearchAndroidAdd selectDangdangSearchAndroidAddById(Integer id)
	{
	    return dangdangSearchAndroidAddMapper.selectDangdangSearchAndroidAddById(id);
	}
	
	/**
     * 查询当当搜索安卓后端数据列表
     * 
     * @param dangdangSearchAndroidAdd 当当搜索安卓后端数据信息
     * @return 当当搜索安卓后端数据集合
     */
	@Override
	public List<DangdangSearchAndroidAdd> selectDangdangSearchAndroidAddList(DangdangSearchAndroidAdd dangdangSearchAndroidAdd)
	{
	    return dangdangSearchAndroidAddMapper.selectDangdangSearchAndroidAddList(dangdangSearchAndroidAdd);
	}
	
    /**
     * 新增当当搜索安卓后端数据
     * 
     * @param dangdangSearchAndroidAdd 当当搜索安卓后端数据信息
     * @return 结果
     */
	@Override
	public int insertDangdangSearchAndroidAdd(DangdangSearchAndroidAdd dangdangSearchAndroidAdd)
	{
	    return dangdangSearchAndroidAddMapper.insertDangdangSearchAndroidAdd(dangdangSearchAndroidAdd);
	}
	
	/**
     * 修改当当搜索安卓后端数据
     * 
     * @param dangdangSearchAndroidAdd 当当搜索安卓后端数据信息
     * @return 结果
     */
	@Override
	public int updateDangdangSearchAndroidAdd(DangdangSearchAndroidAdd dangdangSearchAndroidAdd)
	{
	    return dangdangSearchAndroidAddMapper.updateDangdangSearchAndroidAdd(dangdangSearchAndroidAdd);
	}

	/**
     * 删除当当搜索安卓后端数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDangdangSearchAndroidAddByIds(String ids)
	{
		return dangdangSearchAndroidAddMapper.deleteDangdangSearchAndroidAddByIds(Convert.toStrArray(ids));
	}
	@Override
	public String importBwFront(List<DangdangSearchAndroidAdd> bwList, Boolean isUpdateSupport, String operName) {
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
						List<DangdangSearchAndroidAdd> listPage = bwList.subList(0, pointsDataLimit);
						dangdangSearchAndroidAddMapper.batchInsert(listPage);
						//剔除
						bwList.subList(0, pointsDataLimit).clear();
					}
					if (!bwList.isEmpty()) {
						//表示最后剩下的数据
						dangdangSearchAndroidAddMapper.batchInsert(bwList);
					}
				} else {
					dangdangSearchAndroidAddMapper.batchInsert(bwList);
				}
			}
			dangdangSearchAndroidAddMapper.updateGroupword(null);
			return "导入成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "导入失败";
		}

	}
}
