package com.ruoyi.system.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.OaDingdingMapper;
import com.ruoyi.system.mapper.OaDingdingUserMapper;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.mapper.UserApplyMapper;
import com.ruoyi.system.domain.Dingding;
import com.ruoyi.system.domain.OaDingding;
import com.ruoyi.system.domain.OaDingdingUser;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.UserApply;
import com.ruoyi.system.service.IOaDingdingService;
import com.ruoyi.system.service.ISysUserService;

/**
 * 钉钉考勤数据 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-26
 */
@Transactional
@Service
public class OaDingdingServiceImpl implements IOaDingdingService {
	private static final Set<SysDept> dSet = new TreeSet<SysDept>(); // 部门集合

	@Autowired
	private ISysUserService userService;
	@Autowired
	private OaDingdingMapper oaDingdingMapper;
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysDeptMapper deptMapper;
	@Autowired
	private SysUserRoleMapper userRoleMapper;
	@Autowired
	private UserApplyMapper applyMapper;
	@Autowired
	private OaDingdingUserMapper oaDingdingUserMapper;

	/**
	 * 查询钉钉考勤数据信息
	 * 
	 * @param userId
	 *            钉钉考勤数据ID
	 * @return 钉钉考勤数据信息
	 */
	@Override
	public OaDingding selectOaDingdingById(Long userId) {
		return oaDingdingMapper.selectOaDingdingById(userId);
	}

	/**
	 * 查询钉钉考勤数据列表
	 * 
	 * @param oaDingding
	 *            钉钉考勤数据信息
	 * @return 钉钉考勤数据集合
	 */
	@Override
	public List<Dingding> selectOaDingdingList(Dingding ding) {
		if (ding.getUserId() == 1) {// admin用户
			ding.setUserId(1L);
			return oaDingdingMapper.selectDingData(ding);
		}

		SysUser user = userMapper.selectUserById(ding.getUserId());

		if (user.getUserId() == 103L) {// COO
			// leader
			SysDept dept = deptMapper.selectDeptByUserId(ding.getUserId());
			dept = new SysDept();
			dept.setLeader(user.getUserName());
			dSet.clear();
			getDeptList(dept);
			ding.setdSet(dSet);
			ding.setUserId(0L);
			return oaDingdingMapper.selectDingData(ding);
		}

		if (user.getUserId() == 101L) {// CEO
			ding.setUserId(1L);
			return oaDingdingMapper.selectDingData(ding);
		}
		// ding.setArea(user.getArea());

		// 查询当前用户的角色id
		List<Long> roleIdList = userRoleMapper.selectRoleIdByUserId(user);

		// 查看数据权限
		SysUser user4 = new SysUser();
		user4.setUserId(ding.getUserId());
		user4.setRoleId(15L);
		Long id = userRoleMapper.selectUserIdByRoleId(user4);// 具备查看数据权限的用户id
		if (id != null) {
			ding.setUserId(1L);
			ding.setArea(null);
			return oaDingdingMapper.selectDingData(ding);
		}

		// 人事总监
		SysUser user2 = new SysUser();
		user2.setRoleId(6L);// 人事总监
		Long chiefId = userRoleMapper.selectUserIdByRoleId(user2);// 人事总监id
		if (chiefId.longValue() == user.getUserId().longValue()) {
			ding.setUserId(1L);
			return oaDingdingMapper.selectDingData(ding);
		}

		if (user.getArea().equals("3")) {
			user.setArea("2");
		}
		SysUser user3 = new SysUser();
		user3.setRoleId(3L);// 人事专员
		user3.setArea(user.getArea());
		Long hrId = userRoleMapper.selectUserIdByRoleId(user3);// 人事专员id
		SysDept dept = deptMapper.selectDeptByUserId(ding.getUserId());// 查询用户所在部门

		// 人事专员
		if (hrId != null && user.getUserId().longValue() == hrId.longValue()) {
			ding.setUserId(1L);
			return oaDingdingMapper.selectDingData(ding);

		} else if (hrId != null && roleIdList.get(0) == 4L) {
			// 普通员工
			return oaDingdingMapper.selectDingData(ding);

		} else {
			// leader
			dept = new SysDept();
			dept.setLeader(user.getUserName());
			dSet.clear();
			getDeptList(dept);
			ding.setdSet(dSet);
			ding.setUserId(null);
			return oaDingdingMapper.selectDingData(ding);
		}
	}

	/**
	 * 递归实现获取当前用户负责的所有部门id
	 * 
	 * @param dept
	 * @author wgf
	 */
	private void getDeptList(SysDept dept) {
		SysDept dept2 = new SysDept();
		List<SysDept> deptList = deptMapper.selectDeptList(dept);
		dSet.addAll(deptList);
		for (SysDept d : deptList) {
			dept2.setParentId(d.getDeptId());
			List<SysDept> deptList2 = deptMapper.selectDeptList(dept2);
			if (deptList2 != null && !"".equals(deptList2) && deptList2.size() != 0) {
				getDeptList(dept2);
			}
		}
	}

	/**
	 * 新增钉钉考勤数据
	 * 
	 * @param oaDingding
	 *            钉钉考勤数据信息
	 * @return 结果
	 */
	@Override
	public int insertOaDingding(OaDingding oaDingding) {
		return oaDingdingMapper.insertOaDingding(oaDingding);
	}

	/**
	 * 修改钉钉考勤数据
	 * 
	 * @param oaDingding
	 *            钉钉考勤数据信息
	 * @return 结果
	 */
	@Override
	public int updateOaDingding(OaDingding oaDingding) {
		oaDingding.setCreateTime(new Date());
		return oaDingdingMapper.updateOaDingding(oaDingding);
	}

	/**
	 * 删除钉钉考勤数据对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteOaDingdingByIds(String ids) {
		// return
		// oaDingdingMapper.deleteOaDingdingByIds(Convert.toStrArray(ids));
		return oaDingdingMapper.deleteOaDingdingByIds(null);

	}

	@Override
	public int insertForeach(List<OaDingding> dingList) {

		return oaDingdingMapper.insertForeach(dingList);
	}

	/**
	 * 计算请假后的打卡时间
	 * 
	 * @param 请假时间
	 * @return 标准打卡时间
	 */
	private Date getWorkDate(Date d, int n) {
		Date result = new Date();
		long t = d.getTime();
		t += n * 60 * 60 * 1000;
		result.setTime(t);
		return result;
	}

	@Override
	public int updateOaDingDingByOutAndApply() {
		// （一）根据请假情况更新钉钉考勤表
		UserApply apply = new UserApply();
		// apply.setApplyState(applyState);//申请状态（1 待审批，2已撤回，3申请成功，4申请失败）
		apply.setApplyType("1");// 申请类型（1请假，2加班，3销假，4外出）
		List<UserApply> applyList = applyMapper.selectApplyList(apply);// 请假申请记录

		if (applyList == null) {
			return 0;// 没有申请成功的请假记录需要更新在钉钉考勤表中
		}

		for (UserApply a : applyList) {
			String applyState = a.getApplyState();// 申请状态（1
													// 待审批，2已撤回，3申请成功，4申请失败）

			OaDingdingUser dingUser = new OaDingdingUser();
			dingUser.setUserName(a.getUserName());
			List<OaDingdingUser> dingUserList = oaDingdingUserMapper.selectOaDingdingUserList(dingUser);// 根据userName查找钉钉考勤用户表中的userId
			if (dingUserList.size() == 0)
				continue;

			Date startTime = a.getStarttime();
			Date endTime = getWorkDate(a.getEndtime(), 24);
			double timeLength = a.getTimelength();
			String timepart1 = a.getTimeapart1();
			String timepart2 = a.getTimeapart2();
			String timepart3 = a.getTimeapart3();
			System.out.println();
			System.out.println("startTime: " + startTime + " endTime: " + endTime);
			System.out.println(" timeLength: " + timeLength + " timepart1: " + timepart1 + " timepart2: " + timepart2
					+ " timepart3: " + timepart3);
			System.out.println(" userId: " + dingUserList.get(0).getUserId() + " userName: "
					+ dingUserList.get(0).getUserName() + " applyId: " + a.getApplyId());
			System.out.println();

			Dingding di = new Dingding();
			di.setUserId(dingUserList.get(0).getUserId());
			di.setStartTime(startTime);
			di.setEndTime(endTime);
			List<Dingding> diList = oaDingdingMapper.selectOaDingdingListByCondition(di);// 查询实际打卡时间
			if (diList.size() == 0)
				continue;// 请假时间区间在oa_dingding表中没有打卡记录

			String leaveType = a.getLeaveType();
			Long userId = dingUserList.get(0).getUserId();

			// 非哺乳假 上午(10:00——15:00) 下午(15:00——19:00)
			if (!leaveType.equals("10")) {
				if (a.getTimelength() % 1 == 0) {// 整数假期天数
					Dingding ding = new Dingding();
					ding.setUserId(userId);
					ding.setTimeResult(leaveType);
					ding.setApplyState(applyState);
					ding.setStartTime(startTime);
					ding.setEndTime(endTime);
					ding.setStatus("1");
					oaDingdingMapper.updateOaDingDingByTime(ding);
				} else {
					if (timepart1.equals("2") && timepart2.equals("1")) {// starttime
																			// 15:00
																			// ---endtime
																			// 15:00
						Date startDate = getWorkDate(startTime, 15);// 打卡标准值：15点前
						Date endDate = getWorkDate(endTime, 15);
						System.out.println("\n 21" + startDate + "  " + endDate + "\n");

						Dingding ding = new Dingding();
						ding.setUserId(userId);
						ding.setTimeResult(leaveType);
						ding.setApplyState(applyState);
						ding.setStartTime(startDate);
						ding.setEndTime(endDate);
						ding.setStatus("1");
						oaDingdingMapper.updateOaDingDingByTime(ding);
					} else if (timepart1.equals("1") && timepart2.equals("1")) {// 上午
																				// starttime
																				// -
																				// starttime
																				// 15:00
						Date startDate = startTime;// 打卡标准值：15点前
						Date endDate = getWorkDate(endTime, 15);
						System.out.println("\n 11" + startDate + "  " + endDate + "\n");

						Dingding ding = new Dingding();
						ding.setUserId(userId);
						ding.setTimeResult(leaveType);
						ding.setApplyState(applyState);
						ding.setStartTime(startDate);
						ding.setEndTime(endDate);
						ding.setStatus("1");
						oaDingdingMapper.updateOaDingDingByTime(ding);
					} else if (timepart1.equals("2") && timepart2.equals("2")) {// 22
																				// 下午，第二天下午，
																				// starttime
																				// 15:00
																				// ---
																				// endtime
						Date startDate = getWorkDate(startTime, 15);// 打卡标准值：15点前
						Date endDate = endTime;
						if (timeLength < 1)
							getWorkDate(endTime, 15);
						System.out.println("\n 22" + startDate + "  " + endDate + "\n");

						Dingding ding = new Dingding();
						ding.setUserId(userId);
						ding.setTimeResult(leaveType);
						ding.setApplyState(applyState);
						ding.setStartTime(startDate);
						ding.setEndTime(endDate);
						ding.setStatus("1");
						if (timeLength < 1)
							ding.setCheckType("OffDuty");
						oaDingdingMapper.updateOaDingDingByTime(ding);
					}
				}
			}

			// 哺乳假 早上(10:00——11:00) 下午(18:00——19:00)
			if (leaveType.equals("10")) {
				Dingding ding = new Dingding();
				ding.setUserId(userId);
				ding.setStartTime(startTime);
				ding.setEndTime(endTime);
				List<Dingding> dingList = oaDingdingMapper.selectOaDingdingListByCondition(ding);// 查询实际打卡时间

				if (dingList != null) {
					if (a.getTimeapart3().equals("1")) {// 上午请假
						Date startDate = getWorkDate(startTime, 11);// 打卡标准值：11点前
						for (Dingding d : dingList) {
							if (d.getUserCheckTime().compareTo(startDate) < 0) {// 打卡时间在标准值内
								Dingding ding2 = new Dingding();
								ding2.setUserId(dingUserList.get(0).getUserId());
								ding2.setCheckType("OnDuty");
								ding2.setTimeResult(leaveType);
								ding.setApplyState(applyState);
								ding2.setStatus("1");
								oaDingdingMapper.updateOaDingDingByTime(ding2);
							}
						}
					} else {// 下午请假
						Date endDate = getWorkDate(endTime, 18);
						for (Dingding d : dingList) {
							if (d.getUserCheckTime().compareTo(endDate) > 0) {// 打卡时间在标准值内
								Dingding ding1 = new Dingding();
								ding1.setUserId(dingUserList.get(0).getUserId());
								ding1.setTimeResult(leaveType);
								ding.setApplyState(applyState);
								ding1.setStatus("1");
								ding1.setCheckType("OffDuty");
								oaDingdingMapper.updateOaDingDingByTime(ding1);
							}
						}
					}

				}
			}
			// applyMapper.updateUserApplyStatusById(a.getApplyId());//0 未更新
			// 1已更新
		}

		// （二）根据外出报备情况更新钉钉考勤表
		UserApply apply2 = new UserApply();
		apply2.setApplyType("4");// 申请类型（1请假，2加班，3销假，4外出）
		List<UserApply> applyList2 = applyMapper.selectApplyList(apply2);// 外出申请记录
		if (applyList2.size() == 0)
			return 1;

		for (UserApply o : applyList2) {
			String state = o.getApplyState();// 申请状态（1 待审批，2已撤回，3申请成功，4申请失败）

			Dingding ding = new Dingding();
			ding.setUserName(o.getUserName());
			ding.setStartTime(o.getStarttime());
			if (o.getStarttime().equals(o.getEndtime())) {
				ding.setEndTime(getWorkDate(o.getEndtime(), 24));
			}
			ding.setEndTime(o.getEndtime());
			List<Dingding> dingList = oaDingdingMapper.selectOaDingdingListByCondition(ding);// 查询
			if (dingList.size() == 0)
				continue;

			ding.setTimeResult("out");
			ding.setApplyState(state);
			ding.setStatus("1");
			oaDingdingMapper.updateOaDingDingByTime(ding);
		}

		// （三）根据补卡情况更新钉钉考勤表
		// 18 Clocked 已补卡 oa_ding_timeResult
		UserApply apply3 = new UserApply();
		apply3.setApplyType("5");// 申请类型（1请假，2加班，3销假，4外出，5补卡）
		List<UserApply> applyList3 = applyMapper.selectApplyList(apply3);// 补卡申请记录
		if (applyList3.size() == 0)
			return 1;

		for (UserApply o : applyList3) {
			Dingding ding = new Dingding();
			ding.setUserName(o.getUserName());
			if (o.getCtype().equals("上班")) {
				ding.setCheckType("OnDuty");
			} else {
				ding.setCheckType("OffDuty");
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String time = sdf.format(o.getStarttime());
			time.substring(0, 9);
			ding.setTime(time);
			ding.setTimeResult("Clocked");
			ding.setApplyState(o.getApplyState());// 申请状态（1
													// 待审批，2已撤回，3申请成功，4申请失败）
			ding.setStatus("1");
			ding.setUserCheckTime(o.getStarttime());
			oaDingdingMapper.updateOaDingDingByTime(ding);

			// 查询补卡当天另一条正常打卡记录（除了未打卡之外的结果都是正常）
			OaDingding oaDing = new OaDingding();
			oaDing.setUserName(o.getUserName());
			oaDing.setTime(time);
			if (o.getCtype().equals("上班")) {// 补上班卡
				oaDing.setCheckType("OffDuty");// 查下班卡
			} else {
				oaDing.setCheckType("OnDuty");// 查上班卡
			}
			List<OaDingding> dingList = oaDingdingMapper.selectDingdingCondition(oaDing);
			if (dingList.size() == 0)
				continue;

			// 计算两条打卡时间差是否大于等于9h
			Date endDate = null;
			boolean flag = false;// 是否满足9h
			if (o.getCtype().equals("上班")) {// 补上班卡
				endDate = getWorkDate(o.getStarttime(), 9);// 计算9h以后的下班打卡时间
				if (dingList.get(0).getUserCheckTime().after(endDate)) {
					flag = true;
				}
			} else {// 补下班卡
				endDate = getWorkDate(dingList.get(0).getUserCheckTime(), 9);// 计算9h以后的下班打卡时间
				if (o.getStarttime().after(endDate)) {
					flag = true;
				}
			}

			// 工时达到9小时，更新另一条打卡数据为Normal
			if (flag) {
				Dingding ding2 = new Dingding();
				ding2.setUserName(o.getUserName());
				if (o.getCtype().equals("上班")) {
					ding2.setCheckType("OffDuty");
				} else {
					ding2.setCheckType("OnDuty");
				}
				ding2.setTime(time);
				ding2.setTimeResult("Normal");
				ding2.setStatus("0");
				oaDingdingMapper.updateOaDingDingByTime(ding2);
			}
		}
		return 1;
	}

	@Override
	public int updateOaDingDingByElasticTime(String yesterday) {
		Dingding ding = new Dingding();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(yesterday);
			ding.setWorkDate(date);
			// 查询十点-十点半之间迟到的记录
			List<Dingding> dingList = oaDingdingMapper.selectOaDingByTime(ding);

			String onduty = "";
			String offduty = "";
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			for (Dingding d : dingList) {
				onduty = sdf.format(d.getUserCheckTime());

				OaDingding od = new OaDingding();
				od.setWorkDate(d.getWorkDate());
				od.setUserId(d.getUserId());
				od.setCheckType("OffDuty");
				od.setTimeResult("Normal");
				List<OaDingding> odList = oaDingdingMapper.selectOaDingdingList(od);
				if (odList.size() == 0)
					continue;

				OaDingding oad = odList.get(0);// 查询下班打卡记录

				offduty = sdf.format(oad.getUserCheckTime());

				long hour = secondsBetween(onduty, offduty) / 3600;

				System.out.println("\n" + onduty + " " + offduty + "  " + hour + "\n");

				// 早晚打卡时间差大于等于9h
				if (hour >= 9) {
					Dingding di2 = new Dingding();
					di2.setUserId(d.getUserId());
					di2.setWorkDate(d.getWorkDate());
					di2.setCheckType("OnDuty");
					di2.setTimeResult("Normal");
					di2.setStatus("0");
					oaDingdingMapper.updateOaDingDingByTime(di2);// 修改上班打卡结果
				} else {
					Dingding di = new Dingding();
					di.setUserId(oad.getUserId());
					di.setWorkDate(oad.getWorkDate());
					di.setCheckType("OffDuty");
					di.setTimeResult("Early");
					di.setStatus("1");
					oaDingdingMapper.updateOaDingDingByTime(di);// 修改下班打卡结果
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int updateOaDingDingBySpecialTime(String time) {
		Dingding ding = new Dingding();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(time);
			ding.setWorkDate(date);
			ding.setCheckType("OnDuty");
			// 查询某一天上班打卡的记录
			List<Dingding> dingList = oaDingdingMapper.selectOaDingdingListByCondition(ding);

			String onduty = "";
			String offduty = "";
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			for (Dingding d : dingList) {
				onduty = sdf2.format(d.getUserCheckTime());

				OaDingding od = new OaDingding();
				od.setWorkDate(date);
				od.setUserId(d.getUserId());
				od.setCheckType("OffDuty");
				od.setTimeResult("Early");
				List<OaDingding> odList = oaDingdingMapper.selectOaDingdingList(od);// 下班早退的记录
				if (odList.size() == 0)
					continue;

				OaDingding oad = odList.get(0);// 查询下班打卡记录

				offduty = sdf2.format(oad.getUserCheckTime());

				long hour = secondsBetween(onduty, offduty) / 3600;

				System.out.println("\n" + onduty + " " + offduty + "  " + hour + "\n");

				// 早晚打卡时间差大于等于6h
				if (hour >= 6) {
					Dingding di2 = new Dingding();
					di2.setUserId(d.getUserId());
					di2.setWorkDate(d.getWorkDate());
					// di2.setCheckType("OnDuty");
					di2.setTimeResult("Normal");
					di2.setStatus("0");
					oaDingdingMapper.updateOaDingDingByTime(di2);// 修改上下班打卡结果
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * 计算当月三次迟到累计迟到时长是否满足小于等于60min
	 */
	@Override
	public int updateOaDingDingByCountLate() {
		Dingding ding = new Dingding();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = s.format(new Date());
		String standardTime, userCheckTime, firstDay, lastDay = "";
		long timeLength, timeSum = 0L;
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = s.parse(today);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		if (maxDay > day) {
			c.set(year, month, maxDay);
		} else {
			c.set(year, month, day);
		}

		// 月初
		firstDay = today.substring(0, 7) + "-01";
		if (firstDay.equals("2020-01-01")) {// 从2020.1.13开始执行
			firstDay = "2020-01-13";
		}
		// 月末
		lastDay = s.format(c.getTime());

		List<SysUser> userList = userService.selectAllUser();
		List<Dingding> dingList = new ArrayList<Dingding>();
		for (SysUser user : userList) {

			// 查询当月10:00以后的上班考勤
			ding.setUserName(user.getUserName());
			ding.setFirstDay(firstDay);
			ding.setLastDay(lastDay);
			dingList = oaDingdingMapper.selectOaDingByTime3(ding);
			if (dingList.size() == 0)
				continue;

			timeSum = 0;
			for (Dingding d : dingList) {
				
				standardTime = s.format(d.getWorkDate()) + " 10:00:00";
				userCheckTime = sdf.format(d.getUserCheckTime());
				timeLength = secondsBetween(standardTime, userCheckTime) / 60;// 迟到时长（分钟）
				System.out.println(d.getUserName() + "  standardTime: " + standardTime + "  userCheckTime: "
						+ userCheckTime + "  timeLength: " + timeLength);
				if (timeLength > 60)
					continue;

				timeSum += timeLength;// 迟到时长总和（分钟）

				if (timeSum > 60)
					continue;
				
				if(d.getTimeResult().equals("NotSigned")) continue;

				Dingding d2 = new Dingding();
				d2.setUserName(d.getUserName());
				d2.setTimeResult("normal");
				d2.setStatus("1");
				d2.setWorkDate(d.getWorkDate());
				d2.setCheckType("OnDuty");
				oaDingdingMapper.updateOaDingDingByTime(d2);
			}

		}

		return 0;
	}

	/**
	 * 字符串的日期格式的计算
	 */
	private long secondsBetween(String smdate, String bdate) {
		long time1 = 0L;
		long time2 = 0L;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(smdate));
			time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bdate));
			time2 = cal.getTimeInMillis();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (time2 - time1) / 1000;
	}

}
