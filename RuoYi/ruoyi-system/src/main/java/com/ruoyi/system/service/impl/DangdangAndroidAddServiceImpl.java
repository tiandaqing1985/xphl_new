package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.DangdangSearchAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DangdangAndroidAddMapper;
import com.ruoyi.system.domain.DangdangAndroidAdd;
import com.ruoyi.system.service.IDangdangAndroidAddService;
import com.ruoyi.common.core.text.Convert;

/**
 * 当当安卓后端数据 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-15
 */
@Service
public class DangdangAndroidAddServiceImpl implements IDangdangAndroidAddService 
{
	@Autowired
	private DangdangAndroidAddMapper dangdangAndroidAddMapper;

	/**
     * 查询当当安卓后端数据信息
     * 
     * @param id 当当安卓后端数据ID
     * @return 当当安卓后端数据信息
     */
    @Override
	public DangdangAndroidAdd selectDangdangAndroidAddById(Integer id)
	{
	    return dangdangAndroidAddMapper.selectDangdangAndroidAddById(id);
	}
	
	/**
     * 查询当当安卓后端数据列表
     * 
     * @param dangdangAndroidAdd 当当安卓后端数据信息
     * @return 当当安卓后端数据集合
     */
	@Override
	public List<DangdangAndroidAdd> selectDangdangAndroidAddList(DangdangAndroidAdd dangdangAndroidAdd)
	{
	    return dangdangAndroidAddMapper.selectDangdangAndroidAddList(dangdangAndroidAdd);
	}
	
    /**
     * 新增当当安卓后端数据
     * 
     * @param dangdangAndroidAdd 当当安卓后端数据信息
     * @return 结果
     */
	@Override
	public int insertDangdangAndroidAdd(DangdangAndroidAdd dangdangAndroidAdd)
	{
	    return dangdangAndroidAddMapper.insertDangdangAndroidAdd(dangdangAndroidAdd);
	}
	
	/**
     * 修改当当安卓后端数据
     * 
     * @param dangdangAndroidAdd 当当安卓后端数据信息
     * @return 结果
     */
	@Override
	public int updateDangdangAndroidAdd(DangdangAndroidAdd dangdangAndroidAdd)
	{
	    return dangdangAndroidAddMapper.updateDangdangAndroidAdd(dangdangAndroidAdd);
	}

	/**
     * 删除当当安卓后端数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDangdangAndroidAddByIds(String ids)
	{
		return dangdangAndroidAddMapper.deleteDangdangAndroidAddByIds(Convert.toStrArray(ids));
	}
	@Override
	public String importBwFront(List<DangdangAndroidAdd> bwList, Boolean isUpdateSupport, String operName) {
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
						List<DangdangAndroidAdd> listPage = bwList.subList(0, pointsDataLimit);
						dangdangAndroidAddMapper.batchInsert(listPage);
						//剔除
						bwList.subList(0, pointsDataLimit).clear();
					}
					if (!bwList.isEmpty()) {
						//表示最后剩下的数据
						dangdangAndroidAddMapper.batchInsert(bwList);
					}
				} else {
					dangdangAndroidAddMapper.batchInsert(bwList);
				}
			}
			dangdangAndroidAddMapper.updateGroupword(null);
			return "导入成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "导入失败";
		}

	}
}
