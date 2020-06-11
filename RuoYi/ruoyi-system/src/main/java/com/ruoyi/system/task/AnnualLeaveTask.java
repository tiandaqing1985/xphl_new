package com.ruoyi.system.task;

import com.ruoyi.system.domain.Holiday;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.UserApply;
import com.ruoyi.system.mapper.HolidayMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.IHolidayService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.IUserApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component("annualLeave")
public class AnnualLeaveTask {
    @Autowired
    private HolidayMapper holidayMapper;
    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private IHolidayService holidayService;

    @Autowired
    private IUserApplyService userApplyService;

    public void newAnnualLeaveTask() {

         List<SysUser> userList = userService.selectAllUser();
//        List<SysUser> userList =new ArrayList<>();
//        SysUser sysUser = userService.selectUserById(152L);
//        userList.add(sysUser);
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        //入职日期、首次参加工作日期、现在的时间、元旦、最后一天、
        String intime, firstWorkTime, today, firstDay, lastDay = "";
        today = s.format(new Date());
        //工龄、当年元旦到今天的天数、员工今年在职天数、可休年假天数
        long workYears, days, workDay = 0;
        // 法定年假天数、可休年假天数、仍需生成的年假天数
        int statutoryAnnualLeave, selfAnnualLeave, cycleNum = 0;
        //事假天数、病假天数、本月事病假总天数
        Double cnum1, cnum2, holidayCount = 0.0;

        for (SysUser user : userList) {
            try {
                intime = s.format(user.getIntime());// 入职日期
                firstWorkTime = s.format(user.getFirstWorkDate());// 首次参加工作日期

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date strDate = simpleDateFormat.parse(firstWorkTime);
                long nd = 1000 * 24 * 60 * 60;
                // 获得两个时间的毫秒时间差异
                long diff = new Date().getTime() - strDate.getTime();
                // 计算差多少天
                long day = diff / nd;

                if (day < 438) {//判断是否从首次工作时间到今天是否可以生成年假
                    continue;
                }


                // 计算在职天数
                long onJobLength = secondsBetween(intime, today) / 3600 / 24;
                user.setOnJobLength(onJobLength);
                // 更新在职天数
                userMapper.updateUser(user);

                if (firstWorkTime.isEmpty())
                    continue;

                if (intime.isEmpty())
                    continue;

                // 计算工龄
                workYears = secondsBetween(firstWorkTime, today) / 3600 / 24 / 365;

                // 法定年假天数
                if (workYears >= 1 && workYears < 10) {
                    statutoryAnnualLeave = 5;
                } else if (workYears >= 10 && workYears < 20) {
                    statutoryAnnualLeave = 10;
                } else if (workYears >= 20) {
                    statutoryAnnualLeave = 15;
                } else {
                    continue;
                }
                /*
                 * 饶总入职时间为2013年8月9日， 那么2020年5月31日后可休年假天数计算为
                 * 2020年1月1日至2020年5月31日为152天核算可休年假天数为152/365*15=6天
                 * 劳动法规定年假取整数，小数点后全部舍去，即0.5以上都舍去不进位
                 */
                firstDay = today.substring(0, 4) + "-01-01";//元旦
                lastDay = today.substring(0, 3) + "1-03-31";//當年最後一天；年假失效时间

                /**
                 *  以下2种情况不能享受当年年休假
                 */
                // 1、当年累计事假天数，临界值20天（含）
                cnum1 = userApplyService.leaveCount(s.parse(firstDay), s.parse(lastDay), user.getUserId(), "3");
                if (cnum1 >= 20)
                    continue;

                // 2、当年累计病假天数，临界值2个月（工龄1~10年）、临界值3个月（工龄10~20年）、临界值4个月（工龄20年以上）
                cnum2 = userApplyService.leaveCount(s.parse(firstDay), s.parse(lastDay), user.getUserId(), "4");
                if (workYears >= 0 && workYears < 10) {
                    if (cnum2 >= 30)
                        continue;

                } else if (workYears >= 10 && workYears < 20) {
                    if (cnum2 >= 90)
                        continue;

                } else if (workYears >= 20) {
                    if (cnum2 >= 120)
                        continue;

                } else {
                    continue;
                }

                // 计算当年元旦到现在的天数
                days = secondsBetween(firstDay, today) / 3600 / 24;

                //计算员工今年在职天数
                if (onJobLength - days > 0) {//今年元旦之前（不含）入职的员工
                    workDay = days;
                } else {//今年元旦之后（含）刚刚入职的员工
                    workDay = onJobLength;
                }

                // 计算可休年假天数
                selfAnnualLeave = (int) Math.floor(((float) workDay / 365 * statutoryAnnualLeave));

                System.out.println("\n" + "可休年假:  " + Math.floor(selfAnnualLeave) + "当年截止今天的在职天数:  " + workDay + "元旦到今天的天数：  " + days + "入职时间：  "
                        + intime + "首次工作时间：  " + firstWorkTime + "\n");

                // 查询今年元旦到现在生成的年假天数
                Holiday h = new Holiday();
                h.setCreatedate(firstDay);
                h.setOverdate(today);
                h.setUserId(user.getUserId());
                List<Holiday> hList = holidayMapper.selectHolidayListByCondition(h);
                // 可休年假天数-系统现有年假天数=仍需生成的年假天数
                cycleNum = selfAnnualLeave - hList.size();
                if (cycleNum <= 0) continue;

                /**
                 *  员工连续休假时间超过15天（含），则所在月份当月没有年假。
                 *  产假期间不生成年假
                 */
                // 查出本月事、病假总天数
                holidayCount = userApplyService.leaveCount("本月", user.getUserId(), s.parse(today));

                // 是否请产假
                UserApply userApply = new UserApply();
                userApply.setUserId(user.getUserId());
                userApply.setTimeapart1(today);
                userApply.setStarttime(new Date());
                userApply.setEndtime(new Date());
                UserApply isMaternityLeave = userApplyService.selcetMaternityLeaveByUserApply(userApply);// 是否在产假

                if (holidayCount >= 15) {
                    Holiday holiday = new Holiday();
                    holiday.setUserId(user.getUserId());
                    holiday.setHolidayType("1");
                    holiday.setAvailability("0");// 无效
                    holiday.setCreatedate(today);
                    holiday.setOverdate(lastDay);
                    holiday.setValue(0.0);
                    holiday.setHolidayDetail("员工一个月休假时间超过 15 天（含），则当月没有年假");
                    holidayMapper.insertHoliday(holiday);
                }
                else if (isMaternityLeave != null) {
                    Holiday holiday1 = new Holiday();
                    holiday1.setUserId(user.getUserId()); // user_id
                    holiday1.setHolidayType("1"); // 类型为年假
                    holiday1.setCreatedate(today);
                    holiday1.setOverdate(lastDay);
                    holiday1.setAvailability("0");
                    holiday1.setValue(0.0);
                    holiday1.setHolidayDetail("员工产假期间的年假包含在产假内一并休完，不再额外计算年假");
                    holidayService.insertHoliday(holiday1);
                }
                else {
                    while (cycleNum > 0) {
                        Holiday holiday = new Holiday();
                        holiday.setUserId(user.getUserId());
                        holiday.setHolidayType("1");
                        holiday.setAvailability("1");// 有效
                        holiday.setCreatedate(today);
                        holiday.setOverdate(lastDay);
                        holiday.setValue(1.0);
                        holidayMapper.insertHoliday(holiday);
                        cycleNum--;
                    }
                }

                // 数据库中已有的年假失效日期，年假过期清零
                Holiday holiday = new Holiday();
                holiday.setUserId(user.getUserId());
                List<Holiday> holidayList = holidayService.selectHolidayList(holiday);

                for (Holiday holiday2 : holidayList) {
                    if (today.equals(holiday2.getOverdate())) {
                        Holiday holiday3 = new Holiday();
                        holiday3.setId(holiday2.getId());
                        holiday3.setAvailability("0");
                        holidayService.updateHoliday(holiday3);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        //当年未休年假有效期延长至次年3月31日？


        // 超过有效期的年假：处理年假、调休失效？
        // 根据当前时间（年假失效时间/调休失效时间）查询失效假期
        Holiday holiday = new Holiday();
        holiday.setOverdate(today);
        long count = holidayMapper.selectCountHoliday(holiday);
        if (count != 0) {
            holidayMapper.updateHolidayValue(holiday);
        }

    }

    public void annualLeaveTask() {

        List<SysUser> userList = userService.selectAllUser();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

        for (SysUser user : userList) {
            try {
                String intime = s.format(user.getIntime());

                // 现在是入职的第n个月
                int n = userService.countMonth(intime);

                // 新员工的当月不生成年假
                if (n < 1) {
                    continue;
                }

                // 本月年假生成（效）时间
                String creatDate = s.format(userService.getDate(intime, n));

                // 年假生效日期在今天之后的不生成年假
                String today = s.format(new Date());
                if (creatDate.compareTo(today) > 0) {
                    continue;
                }

                // 本月生成的年假的失效时间
                String overDate = s.format(userService.getDate(intime, n + 12));

                System.out.println();

                // 上个月份病、事请假天数
                String createMonth = creatDate.substring(0, 7);
                Date createDay = null;
                createDay = s.parse(creatDate);

                // 查出上月事、病假总天数
                Double holidayCount = userApplyService.leaveCount("上月", user.getUserId(), createDay);

                // 年假生成月份有没有请产假
                UserApply userApply = new UserApply();
                userApply.setUserId(user.getUserId());
                userApply.setTimeapart1(createMonth);
                UserApply isMaternityLeave = userApplyService.selcetMaternityLeaveByUserApply(userApply);// 是否在产假

                // 如果今天是年假生效日期
                if (today.equals(creatDate)) {
                    Holiday holiday1 = new Holiday();
                    holiday1.setUserId(user.getUserId()); // user_id
                    holiday1.setHolidayType("1"); // 类型为年假
                    holiday1.setCreatedate(creatDate);
                    holiday1.setOverdate(overDate);

                    if (holidayCount >= 10.0) {
                        holiday1.setValue(0.0);
                        // holiday1.setHolidayDetail("员工一个月休假时间超过 15
                        // 天（含），则当月没有年假");
                        holiday1.setHolidayDetail("员工一个月休假时间超过 10 天（含），则下月没有年假");
                    } else if (isMaternityLeave != null) {
                        holiday1.setValue(0.0);
                        holiday1.setHolidayDetail("员工产假期间的年假包含在产假内一并休完，不再额外计算年假");
                    } else {
                        holiday1.setValue(1.0);
                    }
                    holidayService.insertHoliday(holiday1);
                }

                // 根据本月年假生成时间查库，没有就生成一条
                Holiday h = new Holiday();
                h.setUserId(user.getUserId());
                h.setCreatedate(creatDate);
                long count = holidayMapper.selectCountCreateDate(h);
                if (count == 0) {// 没有及时生成年假
                    Holiday holiday1 = new Holiday();
                    holiday1.setUserId(user.getUserId()); // user_id
                    holiday1.setHolidayType("1"); // 类型为年假
                    holiday1.setCreatedate(creatDate);
                    holiday1.setOverdate(overDate);

                    if (holidayCount >= 10.0) {
                        holiday1.setValue(0.0);
                        // holiday1.setHolidayDetail("员工一个月休假时间超过 15
                        // 天（含），则当月没有年假");
                        holiday1.setHolidayDetail("员工一个月休假时间超过 10 天（含），则下月没有年假");
                    } else if (isMaternityLeave != null) {
                        holiday1.setValue(0.0);
                        holiday1.setHolidayDetail("员工产假期间的年假包含在产假内一并休完，不再额外计算年假");
                    } else {
                        holiday1.setValue(1.0);
                    }
                    holidayService.insertHoliday(holiday1);
                }

                // 数据库中已有的年假失效日期
                Holiday holiday = new Holiday();
                holiday.setUserId(user.getUserId());
                List<Holiday> holidayList = holidayService.selectHolidayList(holiday);

                for (Holiday holiday2 : holidayList) {
                    if (today.equals(holiday2.getOverdate())) {
                        Holiday holiday3 = new Holiday();
                        holiday3.setId(holiday2.getId());
                        holiday3.setAvailability("0");
                        holidayService.updateHoliday(holiday3);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        // 处理年假、调休失效
        String today = s.format(new Date());
        // 根据当前时间（年假失效时间/调休失效时间）查询失效假期
        Holiday holiday = new Holiday();
        holiday.setOverdate(today);
        long count = holidayMapper.selectCountHoliday(holiday);
        if (count != 0) {
            holidayMapper.updateHolidayValue(holiday);
        }

    }

    /**
     * 字符串的日期格式的计算
     */
    private long secondsBetween(String smdate, String bdate) {
        long time1 = 0L;
        long time2 = 0L;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
