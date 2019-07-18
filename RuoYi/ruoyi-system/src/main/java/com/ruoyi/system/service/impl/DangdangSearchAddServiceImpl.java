package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.DangdangAdditional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DangdangSearchAddMapper;
import com.ruoyi.system.domain.DangdangSearchAdd;
import com.ruoyi.system.service.IDangdangSearchAddService;
import com.ruoyi.common.core.text.Convert;

/**
 * 当当后端数据（搜索） 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-12
 */
@Service
public class DangdangSearchAddServiceImpl implements IDangdangSearchAddService 
{
	@Autowired
	private DangdangSearchAddMapper dangdangSearchAddMapper;

	/**
     * 查询当当后端数据（搜索）信息
     * 
     * @param id 当当后端数据（搜索）ID
     * @return 当当后端数据（搜索）信息
     */
    @Override
	public DangdangSearchAdd selectDangdangSearchAddById(Integer id)
	{
	    return dangdangSearchAddMapper.selectDangdangSearchAddById(id);
	}
	
	/**
     * 查询当当后端数据（搜索）列表
     * 
     * @param dangdangSearchAdd 当当后端数据（搜索）信息
     * @return 当当后端数据（搜索）集合
     */
	@Override
	public List<DangdangSearchAdd> selectDangdangSearchAddList(DangdangSearchAdd dangdangSearchAdd)
	{
	    return dangdangSearchAddMapper.selectDangdangSearchAddList(dangdangSearchAdd);
	}
	
    /**
     * 新增当当后端数据（搜索）
     * 
     * @param dangdangSearchAdd 当当后端数据（搜索）信息
     * @return 结果
     */
	@Override
	public int insertDangdangSearchAdd(DangdangSearchAdd dangdangSearchAdd)
	{
	    return dangdangSearchAddMapper.insertDangdangSearchAdd(dangdangSearchAdd);
	}
	
	/**
     * 修改当当后端数据（搜索）
     * 
     * @param dangdangSearchAdd 当当后端数据（搜索）信息
     * @return 结果
     */
	@Override
	public int updateDangdangSearchAdd(DangdangSearchAdd dangdangSearchAdd)
	{
	    return dangdangSearchAddMapper.updateDangdangSearchAdd(dangdangSearchAdd);
	}

	/**
     * 删除当当后端数据（搜索）对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDangdangSearchAddByIds(String ids)
	{
		return dangdangSearchAddMapper.deleteDangdangSearchAddByIds(Convert.toStrArray(ids));
	}
	@Override
	public String importBwFront(List<DangdangSearchAdd> bwList, Boolean isUpdateSupport, String operName) {
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
						List<DangdangSearchAdd> listPage = bwList.subList(0, pointsDataLimit);
						dangdangSearchAddMapper.batchInsert(listPage);
						//剔除
						bwList.subList(0, pointsDataLimit).clear();
					}
					if (!bwList.isEmpty()) {
						//表示最后剩下的数据
						dangdangSearchAddMapper.batchInsert(bwList);
					}
				} else {
					dangdangSearchAddMapper.batchInsert(bwList);
				}
			}
			dangdangSearchAddMapper.updateGroupword(null);
			return "导入成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "导入失败";
		}

	}
}
