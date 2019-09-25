package com.ruoyi.system.service;

import com.ruoyi.system.domain.UserApply;
import com.ruoyi.system.domain.UserApplyList;

import java.util.Date;
import java.util.List;

/**
 * 申请 服务层
 * 
 * @author ruoyi
 * @date 2019-06-05
 */
public interface IUserApplyService 
{
	/**
     * 查询申请信息
     * 
     * @param applyId 申请ID
     * @return 申请信息
     */
	public List<UserApply> selectUserApplyById(Long applyId);
	
	/**
     * 查询申请信息
     * 
     * @param applyId 申请ID
     * @return 申请信息
     */
	public UserApply selectUserApplyByIdForUndo(Long applyId);
	
	/**
     * 查询申请列表
     * 
     * @param userApply 申请信息
     * @return 申请集合
     */
	public List<UserApply> selectUserApplyList(UserApply userApply);
	/**
	 * 查询申请列表
	 * 
	 * @param userApplyList
	 * @return
	 */
	public List<UserApplyList> selectUserApplyAsList(UserApply userApply);
	
	/**
     * 新增申请
     * 
     * @param userApply 申请信息
     * @return 结果
     */
	public int insertUserApply(UserApply userApply ,Long userId);
	
	/**
     * 修改申请
     * 
     * @param userApply 申请信息
     * @return 结果
     */
	public int updateUserApply(UserApply userApply);
		
	/**
     * 删除申请信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteUserApplyByIds(String ids);
	
	/**
	 * 生成申请单号
	 *
	 */
	public String createListNumber(Long id);
	
	/**
	 * 计算时长
	 */
	public Double countTime(Date beginTime, Date endtime, String timepart1 ,String timepart2);
	
	/**
	 * 根据申请Id查询申请信息
	 */
	public UserApply selectUserApplyByApplyId(Long id);
	
	/**
	 * 根据申请id修改申请状态为撤回
	 */
	public int updateUserApplyStateById(Long applyId);
	
	/**
	 * 根据申请id销假（修改销假状态和和销假原因）
	 */
	public UserApply updateConfirmMasageById(UserApply userApply);
	/**
	 * 查出事假病假的请假单
	 */
	public Double selectLeaveUserApplyByuserId(UserApply userApply);
	
	public List<UserApply> selectLeaveUserApplyByuserIdUp(UserApply userApply);
	
	public List<UserApply> selectLeaveUserApplyByuserIdDown(UserApply userApply);
	
	/**
	 * 查出某日期是否在产假申请所占的月份里
	 */
	public UserApply selcetMaternityLeaveByUserApply(UserApply userApply);
	/**
	 * 根据条件查询已休时长 
	 * @param userApply
	 * @return
	 */
	public Double selectTimeLengthSumByUserApply(UserApply userApply);
	/**
	 * 拿到某月病、事假的请假天数的和
	 */
	public Double leaveCount(String monthName, Long userId, Date date);
	
	/**
	 * 计算请假时长
	 */
	public Double countTimeLength(Date beginTime, Date endtime);
	/**
	 * 根据条件查询需要人事确认的（待审批和撤回的）申请列表
	 * @param userApply
	 * @return
	 */
	public List<UserApplyList> selectUserApplyConfirmAsList(UserApply userApply);
	
	/**
	 * 根据条件查询小于一天的病假
	 * @param userApply
	 * @return
	 */
	public UserApply selcetSickLeaveByUserApply(UserApply userApply);

	
	public String changeChar(String leaveTypeName);
	
	public  int compare(Date date1 ,Date date2,int n);
	
	/**
	 * 查找申请时间段有没有和已有申请冲突
	 */
	public List<UserApply> selectUserApplyListByStartTime(UserApply userApply);
	
	public List<UserApply> selectUserApplyListByEndTime(UserApply userApply);
	
	public int undoSave(UserApply userApply,Long userId);
	
	public int addOvertimeSave(UserApply userApply,Long userId);
	
	/**
	 * 判断起始时间是否在加班时间范围内
	 * @param userApply
	 * @return
	 */
	public String selectUserApplyListByTime(UserApply userApply);
	
	/** 判断员工是否通过试用一期 */
	public String ifPass(Long userId);
}
