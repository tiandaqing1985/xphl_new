package com.ruoyi.system.service;

import com.ruoyi.system.domain.UserApply;
import com.ruoyi.system.domain.UserApplyList;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * 申请 服务层
 *
 * @author ruoyi
 * @date 2019-06-05
 */
public interface IUserApplyService {
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
    public int insertUserApply(UserApply userApply, Long userId);

    /**
     * 临时新增申请
     *
     * @param userApply 申请信息
     * @return 结果
     */
    public int insertUserApplyadds(UserApply userApply, Long userId);


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
     */
    public String createListNumber(Long id);

    /**
     * 计算时长
     */
    public Double countTime(Date beginTime, Date endtime, String timepart1, String timepart2);

    /**
     * 根据申请Id查询申请信息
     */
    public UserApply selectUserApplyByApplyId(Long id);

    /**
     * 根据申请id修改申请状态为撤回
     */
    public int updateUserApplyStateById(Long applyId);

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
     *
     * @param userApply
     * @return
     */
    public Double selectTimeLengthSumByUserApply(UserApply userApply);

    /**
     * 拿到某月病、事假的请假天数的和
     */
    public Double leaveCount(String monthName, Long userId, Date date);

    /**
     * 拿到某年的请假天数的和
     */
    public Double leaveCount(Date firstDay, Date lastDay, Long userId, String leaveType);


    /**
     * 计算请假时长
     */
    public Double countTimeLength(Date beginTime, Date endtime);

    /**
     * 根据条件查询小于一天的病假
     *
     * @param userApply
     * @return
     */
    public UserApply selcetSickLeaveByUserApply(UserApply userApply);

    public int frequency(UserApply userApply);

    public String changeChar(String leaveTypeName);

    public int compare(Date date1, Date date2, int n);

    /**
     * 查找申请时间段有没有和已有申请冲突
     */
    public List<UserApply> selectUserApplyListByStartTime(UserApply userApply);

    public List<UserApply> selectUserApplyListByEndTime(UserApply userApply);

//	public int undoSave(UserApply userApply,Long userId);

    public int undoSave(Long applyId);

    public int addOvertimeSave(UserApply userApply, Long userId);

    public int addOvertimeSaveadds(UserApply userApply, Long userId);

    public int addOutSave(UserApply userApply, Long userId);

    public int addOutSaveadds(UserApply userApply, Long userId);

    public Long addPicSave(UserApply userApply, Long userId);

    public Long addPicProSave(UserApply userApply, Long userId);

    public Long addPicProSaveadds(UserApply userApply, Long userId);

    /**
     * 判断起始时间是否在加班时间范围内
     *
     * @param userApply
     * @return
     */
    public String selectUserApplyListByTime(UserApply userApply);

    /**
     * 判断员工是否通过试用一期
     */
    public String ifPass(Long userId);

    /**
     * 验证假期余额是否足够
     *
     * @param userApply
     * @return
     */
    public String ifHolidayEnough(UserApply userApply);

    /**
     * 验证年假时间是否过期
     *
     * @param userApply
     * @return
     */
    public String ifOverdue(UserApply userApply);


    /**
     * 撤回
     *
     * @param userApply
     * @return
     */
    public int takeBack(Long ids);

    /**
     * 验证销假时间是否在原请假时间范围内
     *
     * @param userApply
     * @return
     */
    public boolean ifBetween(UserApply userApply);

    /**
     * 验证加班审批是否已通过且满足2.5小时
     */
    public boolean ifSatisfied(Long userId, Date time);


    /**
     * 验证加班审批是否已通过且满足2.5小时
     */
    public String Satisfied(Long userId, Date time);

    /**
     * 上传图片
     *
     * @param file_data
     * @param fileId
     * @return
     * @throws Exception
     */
    public Long uploadMateria(MultipartFile file_data, String fileId) throws Exception;

    /**
     * 验证是否重复提交补卡申请
     */
    public String ifPicRepeat(UserApply userApply);


    /**
     * 上传图片
     *
     * @param file_data
     * @param fileId
     * @return
     * @throws Exception
     */
    public Long facuploadMateria(MultipartFile file_data, String fileId, String num) throws Exception;


}
