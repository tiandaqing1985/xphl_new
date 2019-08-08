package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.UserApply;
import com.ruoyi.system.domain.UserApplyList;

import java.util.List;	

/**
 * 申请 数据层
 * 
 * @author ruoyi
 * @date 2019-06-05
 */
public interface UserApplyMapper 
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
	 * 查询列表
	 */
	public List<UserApplyList> selectUserApplyAsList(UserApply userApply);
	/**
     * 新增申请
     * 
     * @param userApply 申请信息
     * @return 结果
     */
	public int insertUserApply(UserApply userApply);
	
	/**
     * 修改申请
     * 
     * @param userApply 申请信息
     * @return 结果
     */
	public int updateUserApply(UserApply userApply);
	
	/**
     * 删除申请
     * 
     * @param applyId 申请ID
     * @return 结果
     */
	public int deleteUserApplyById(Long applyId);
	
	/**
     * 批量删除申请
     * 
     * @param applyIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteUserApplyByIds(String[] applyIds);
	
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
	 * 根据申请id修改status状态为1（是否更新在钉钉考勤表中：0 未更新  1已更新）
	 * @param applyId
	 * @return
	 */
	public int updateUserApplyStatusById(Long applyId);
	
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
	
	/**
	 * 查找申请时间段有没有和已有申请冲突
	 */
	public List<UserApply> selectUserApplyListByStartTime(UserApply userApply);
	
	public List<UserApply> selectUserApplyListByEndTime(UserApply userApply);
}