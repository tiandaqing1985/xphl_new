package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.enums.DangDangFileType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.DangdangBaiduAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DangdangBackMapper;
import com.ruoyi.system.domain.DangdangBack;
import com.ruoyi.system.service.IDangdangBackService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 当当后端汇总 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-18
 */
@Service
public class DangdangBackServiceImpl implements IDangdangBackService {
	@Autowired
	private DangdangBackMapper dangdangBackMapper;

	/**
	 * 文件后缀
	 */
	private final static String FILE_END = ".xls";

	/**
	 * 查询当当后端汇总信息
	 *
	 * @param id 当当后端汇总ID
	 * @return 当当后端汇总信息
	 */
	@Override
	public DangdangBack selectDangdangBackById(Integer id) {
		return dangdangBackMapper.selectDangdangBackById(id);
	}

	/**
	 * 查询当当后端汇总列表
	 *
	 * @param dangdangBack 当当后端汇总信息
	 * @return 当当后端汇总集合
	 */
	@Override
	public List<DangdangBack> selectDangdangBackList(DangdangBack dangdangBack) {
		return dangdangBackMapper.selectDangdangBackList(dangdangBack);
	}

	/**
	 * 新增当当后端汇总
	 *
	 * @param dangdangBack 当当后端汇总信息
	 * @return 结果
	 */
	@Override
	public int insertDangdangBack(DangdangBack dangdangBack) {
		return dangdangBackMapper.insertDangdangBack(dangdangBack);
	}

	/**
	 * 修改当当后端汇总
	 *
	 * @param dangdangBack 当当后端汇总信息
	 * @return 结果
	 */
	@Override
	public int updateDangdangBack(DangdangBack dangdangBack) {
		return dangdangBackMapper.updateDangdangBack(dangdangBack);
	}

	/**
	 * 删除当当后端汇总对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteDangdangBackByIds(String ids) {
		return dangdangBackMapper.deleteDangdangBackByIds(Convert.toStrArray(ids));
	}

	@Override
	public String importBwFront(List<DangdangBack> bwList, Boolean isUpdateSupport, String operName,String fileName) {
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
						List<DangdangBack> listPage = bwList.subList(0, pointsDataLimit);
						dangdangBackMapper.batchInsert(listPage);
						//剔除
						bwList.subList(0, pointsDataLimit).clear();
					}
					if (!bwList.isEmpty()) {
						//表示最后剩下的数据
						dangdangBackMapper.batchInsert(bwList);
					}
				} else {
					dangdangBackMapper.batchInsert(bwList);
				}
			}
			dangdangBackMapper.updateGroupword(null);
			return "导入成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "导入失败";
		}

	}

}
