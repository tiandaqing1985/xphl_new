package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.DangdangPcFront;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TestCopyMapper;
import com.ruoyi.system.domain.TestCopy;
import com.ruoyi.system.service.ITestCopyService;
import com.ruoyi.common.core.text.Convert;

/**
 * 商机 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-23
 */
@Service
public class TestCopyServiceImpl implements ITestCopyService 
{
	@Autowired
	private TestCopyMapper testCopyMapper;

	/**
     * 查询商机信息
     * 
     * @param businessId 商机ID
     * @return 商机信息
     */
    @Override
	public TestCopy selectTestCopyById(Long businessId)
	{
	    return testCopyMapper.selectTestCopyById(businessId);
	}
	
	/**
     * 查询商机列表
     * 
     * @param testCopy 商机信息
     * @return 商机集合
     */
	@Override
	public List<TestCopy> selectTestCopyList(TestCopy testCopy)
	{
	    return testCopyMapper.selectTestCopyList(testCopy);
	}
	
    /**
     * 新增商机
     * 
     * @param testCopy 商机信息
     * @return 结果
     */
	@Override
	public int insertTestCopy(TestCopy testCopy)
	{
	    return testCopyMapper.insertTestCopy(testCopy);
	}
	
	/**
     * 修改商机
     * 
     * @param testCopy 商机信息
     * @return 结果
     */
	@Override
	public int updateTestCopy(TestCopy testCopy)
	{
	    return testCopyMapper.updateTestCopy(testCopy);
	}

	/**
     * 删除商机对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTestCopyByIds(String ids)
	{
		return testCopyMapper.deleteTestCopyByIds(Convert.toStrArray(ids));
	}

	@Override
	public String importBwFront(List<TestCopy> bwList, Boolean isUpdateSupport, String operName) {
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
						List<TestCopy> listPage = bwList.subList(0, pointsDataLimit);
						testCopyMapper.batchInsert(listPage);
						//剔除
						bwList.subList(0, pointsDataLimit).clear();
					}
					if (!bwList.isEmpty()) {
						//表示最后剩下的数据
						testCopyMapper.batchInsert(bwList);
					}
				} else {
					testCopyMapper.batchInsert(bwList);
				}

			}
			return "导入成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "导入失败";
		}

	}
}
