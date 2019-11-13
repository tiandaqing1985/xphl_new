package com.ruoyi.system.service;

import com.ruoyi.system.domain.DangdangPcFront;
import com.ruoyi.system.domain.TestCopy;
import java.util.List;

/**
 * 商机 服务层
 * 
 * @author ruoyi
 * @date 2019-07-23
 */
public interface ITestCopyService 
{
	/**
     * 查询商机信息
     * 
     * @param businessId 商机ID
     * @return 商机信息
     */
	public TestCopy selectTestCopyById(Long businessId);
	
	/**
     * 查询商机列表
     * 
     * @param testCopy 商机信息
     * @return 商机集合
     */
	public List<TestCopy> selectTestCopyList(TestCopy testCopy);
	
	/**
     * 新增商机
     * 
     * @param testCopy 商机信息
     * @return 结果
     */
	public int insertTestCopy(TestCopy testCopy);
	
	/**
     * 修改商机
     * 
     * @param testCopy 商机信息
     * @return 结果
     */
	public int updateTestCopy(TestCopy testCopy);
		
	/**
     * 删除商机信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTestCopyByIds(String ids);

	public String importBwFront(List<TestCopy> bwList, Boolean isUpdateSupport, String operName);

}
