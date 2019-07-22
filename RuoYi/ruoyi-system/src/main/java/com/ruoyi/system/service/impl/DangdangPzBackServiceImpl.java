package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.DangdangPcMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DangdangPzBackMapper;
import com.ruoyi.system.domain.DangdangPzBack;
import com.ruoyi.system.service.IDangdangPzBackService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 当当pc端品专 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-21
 */
@Service
public class DangdangPzBackServiceImpl implements IDangdangPzBackService 
{
	@Autowired
	private DangdangPzBackMapper dangdangPzBackMapper;

	/**
     * 查询当当pc端品专信息
     * 
     * @param id 当当pc端品专ID
     * @return 当当pc端品专信息
     */
    @Override
	public DangdangPzBack selectDangdangPzBackById(Integer id)
	{
	    return dangdangPzBackMapper.selectDangdangPzBackById(id);
	}
	
	/**
     * 查询当当pc端品专列表
     * 
     * @param dangdangPzBack 当当pc端品专信息
     * @return 当当pc端品专集合
     */
	@Override
	public List<DangdangPzBack> selectDangdangPzBackList(DangdangPzBack dangdangPzBack)
	{
	    return dangdangPzBackMapper.importPz(dangdangPzBack);
	}
	
    /**
     * 新增当当pc端品专
     * 
     * @param dangdangPzBack 当当pc端品专信息
     * @return 结果
     */
	@Override
	public int insertDangdangPzBack(DangdangPzBack dangdangPzBack)
	{
	    return dangdangPzBackMapper.insertDangdangPzBack(dangdangPzBack);
	}
	
	/**
     * 修改当当pc端品专
     * 
     * @param dangdangPzBack 当当pc端品专信息
     * @return 结果
     */
	@Override
	public int updateDangdangPzBack(DangdangPzBack dangdangPzBack)
	{
	    return dangdangPzBackMapper.updateDangdangPzBack(dangdangPzBack);
	}

	/**
     * 删除当当pc端品专对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDangdangPzBackByIds(String ids)
	{
		return dangdangPzBackMapper.deleteDangdangPzBackByIds(Convert.toStrArray(ids));
	}
	@Transactional
	@Override
	public String importBwFront(List<DangdangPzBack> bwList, Boolean isUpdateSupport, String operName) {
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
						List<DangdangPzBack> listPage = bwList.subList(0, pointsDataLimit);
						dangdangPzBackMapper.batchInsert(listPage);
						//剔除
						bwList.subList(0, pointsDataLimit).clear();
					}
					if (!bwList.isEmpty()) {
						//表示最后剩下的数据
						dangdangPzBackMapper.batchInsert(bwList);
					}
				} else {
					dangdangPzBackMapper.batchInsert(bwList);
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
