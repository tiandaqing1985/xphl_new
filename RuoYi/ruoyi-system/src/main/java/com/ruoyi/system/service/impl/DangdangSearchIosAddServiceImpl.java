package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.DangdangIosAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DangdangSearchIosAddMapper;
import com.ruoyi.system.domain.DangdangSearchIosAdd;
import com.ruoyi.system.service.IDangdangSearchIosAddService;
import com.ruoyi.common.core.text.Convert;

/**
 * 当当ios后端数据 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-15
 */
@Service
public class DangdangSearchIosAddServiceImpl implements IDangdangSearchIosAddService 
{
	@Autowired
	private DangdangSearchIosAddMapper dangdangSearchIosAddMapper;

	/**
     * 查询当当ios后端数据信息
     * 
     * @param id 当当ios后端数据ID
     * @return 当当ios后端数据信息
     */
    @Override
	public DangdangSearchIosAdd selectDangdangSearchIosAddById(Integer id)
	{
	    return dangdangSearchIosAddMapper.selectDangdangSearchIosAddById(id);
	}
	
	/**
     * 查询当当ios后端数据列表
     * 
     * @param dangdangSearchIosAdd 当当ios后端数据信息
     * @return 当当ios后端数据集合
     */
	@Override
	public List<DangdangSearchIosAdd> selectDangdangSearchIosAddList(DangdangSearchIosAdd dangdangSearchIosAdd)
	{
	    return dangdangSearchIosAddMapper.selectDangdangSearchIosAddList(dangdangSearchIosAdd);
	}
	
    /**
     * 新增当当ios后端数据
     * 
     * @param dangdangSearchIosAdd 当当ios后端数据信息
     * @return 结果
     */
	@Override
	public int insertDangdangSearchIosAdd(DangdangSearchIosAdd dangdangSearchIosAdd)
	{
	    return dangdangSearchIosAddMapper.insertDangdangSearchIosAdd(dangdangSearchIosAdd);
	}
	
	/**
     * 修改当当ios后端数据
     * 
     * @param dangdangSearchIosAdd 当当ios后端数据信息
     * @return 结果
     */
	@Override
	public int updateDangdangSearchIosAdd(DangdangSearchIosAdd dangdangSearchIosAdd)
	{
	    return dangdangSearchIosAddMapper.updateDangdangSearchIosAdd(dangdangSearchIosAdd);
	}

	/**
     * 删除当当ios后端数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDangdangSearchIosAddByIds(String ids)
	{
		return dangdangSearchIosAddMapper.deleteDangdangSearchIosAddByIds(Convert.toStrArray(ids));
	}
	@Override
	public String importBwFront(List<DangdangSearchIosAdd> bwList, Boolean isUpdateSupport, String operName) {
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
						List<DangdangSearchIosAdd> listPage = bwList.subList(0, pointsDataLimit);
						dangdangSearchIosAddMapper.batchInsert(listPage);
						//剔除
						bwList.subList(0, pointsDataLimit).clear();
					}
					if (!bwList.isEmpty()) {
						//表示最后剩下的数据
						dangdangSearchIosAddMapper.batchInsert(bwList);
					}
				} else {
					dangdangSearchIosAddMapper.batchInsert(bwList);
				}
			}
			dangdangSearchIosAddMapper.updateGroupword(null);
			return "导入成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "导入失败";
		}

	}
}
