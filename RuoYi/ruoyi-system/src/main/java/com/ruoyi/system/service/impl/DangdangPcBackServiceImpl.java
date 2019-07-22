package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.DangdangPcMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DangdangPcBackMapper;
import com.ruoyi.system.domain.DangdangPcBack;
import com.ruoyi.system.service.IDangdangPcBackService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 当当pc后端百度数据明细 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-19
 */
@Service
public class DangdangPcBackServiceImpl implements IDangdangPcBackService 
{
	@Autowired
	private DangdangPcBackMapper dangdangPcBackMapper;

	/**
     * 查询当当pc后端百度数据明细信息
     * 
     * @param id 当当pc后端百度数据明细ID
     * @return 当当pc后端百度数据明细信息
     */
    @Override
	public DangdangPcBack selectDangdangPcBackById(Integer id)
	{
	    return dangdangPcBackMapper.selectDangdangPcBackById(id);
	}
	
	/**
     * 查询当当pc后端百度数据明细列表
     * 
     * @param dangdangPcBack 当当pc后端百度数据明细信息
     * @return 当当pc后端百度数据明细集合
     */
	@Override
	public List<DangdangPcBack> selectDangdangPcBackList(DangdangPcBack dangdangPcBack)
	{
	    return dangdangPcBackMapper.selectDangdangPcBackList(dangdangPcBack);
	}
	
    /**
     * 新增当当pc后端百度数据明细
     * 
     * @param dangdangPcBack 当当pc后端百度数据明细信息
     * @return 结果
     */
	@Override
	public int insertDangdangPcBack(DangdangPcBack dangdangPcBack)
	{
	    return dangdangPcBackMapper.insertDangdangPcBack(dangdangPcBack);
	}
	
	/**
     * 修改当当pc后端百度数据明细
     * 
     * @param dangdangPcBack 当当pc后端百度数据明细信息
     * @return 结果
     */
	@Override
	public int updateDangdangPcBack(DangdangPcBack dangdangPcBack)
	{
	    return dangdangPcBackMapper.updateDangdangPcBack(dangdangPcBack);
	}

	/**
     * 删除当当pc后端百度数据明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDangdangPcBackByIds(String ids)
	{
		return dangdangPcBackMapper.deleteDangdangPcBackByIds(Convert.toStrArray(ids));
	}
	@Transactional
	@Override
	public String importBwFront(List<DangdangPcBack> bwList, Boolean isUpdateSupport, String operName) {
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
						List<DangdangPcBack> listPage = bwList.subList(0, pointsDataLimit);
						dangdangPcBackMapper.batchInsert(listPage);
						//剔除
						bwList.subList(0, pointsDataLimit).clear();
					}
					if (!bwList.isEmpty()) {
						//表示最后剩下的数据
						dangdangPcBackMapper.batchInsert(bwList);
					}
				} else {
					dangdangPcBackMapper.batchInsert(bwList);
				}
			}

			return "导入成功";
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// catch块中显示让事务回滚，保证数据完整性
			e.printStackTrace();
			return "导入失败";
		}

	}
}
