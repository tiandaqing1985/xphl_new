package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.OaFileUpload;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.UserApply;
import com.ruoyi.system.domain.UserApplyList;
import com.ruoyi.system.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 申请 信息操作处理
 *
 * @author ruoyi
 * @date 2019-06-05
 */
@Controller
@RequestMapping("/system/userApply")
public class UserApplyController extends BaseController {
    private String prefix = "system/userApply";

    @Autowired
    private IUserApplyService userApplyService;

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private ISysRoleService iSysRoleService;

    @Autowired
    private IOaFileUploadService oaFileUploadService;

    @RequiresPermissions("system:userApply:view")
    @GetMapping()
    public String userApply() {
        return prefix + "/userApply";
    }

    /**
     * 查询我的申请列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo myList(UserApply userApply) {
        startPage();

        userApply.setUserId(ShiroUtils.getUserId());
        userApply.setApprovalS("1");
        List<UserApplyList> list = userApplyService.selectUserApplyAsList(userApply);
        return getDataTable(list);
    }

    /**
     * 查询我的加班申请列表
     */
    @PostMapping("/overTimeList")
    @ResponseBody
    public TableDataInfo myOverTimeList(UserApply userApply) {
        startPage();

        userApply.setUserId(ShiroUtils.getUserId());
        userApply.setApprovalS("1");
        userApply.setApplyType("2");
        List<UserApplyList> list = userApplyService.selectUserApplyAsList(userApply);
        return getDataTable(list);
    }

    /**
     * 查询我的加班申请列表
     */
    @PostMapping("/undoList")
    @ResponseBody
    public TableDataInfo myUndoList(UserApply userApply) {
        startPage();

        userApply.setUserId(ShiroUtils.getUserId());
        userApply.setApprovalS("1");
        userApply.setApplyType("2");
        List<UserApplyList> list = userApplyService.selectUserApplyAsList(userApply);
        return getDataTable(list);
    }

    /**
     * 查看某个申请
     */
    @GetMapping("/toView/{applyId}")
    public String toCheck(@PathVariable("applyId") Long applyId, Model m) {
        boolean showFlag = false;
        SysUser user = iSysUserService.selectUserById(ShiroUtils.getUserId());
        SysUser user2 = new SysUser();
        user2.setRoleId(3L);
        user2.setArea(user.getArea());
        Long personnelId = iSysRoleService.selectUserIdByRoleId(user2);//查询人事id
        if (personnelId == null) {

        } else {
            if (personnelId.equals(ShiroUtils.getUserId())) {
                showFlag = true;
            }
        }


        List<UserApply> applyList = userApplyService.selectUserApplyById(applyId);
        for (UserApply apply : applyList) {
            if (apply.getRemark() != null)
                m.addAttribute("userApply", apply);
            if (apply.getApplyType().equals("5")) {//补卡
                OaFileUpload oaFileUpload = new OaFileUpload();
                oaFileUpload.setApplyId(apply.getApplyId());
                m.addAttribute("picList", oaFileUploadService.selectOaFileUploadList(oaFileUpload));
            }
        }
        if (!m.containsAttribute("userApply"))
            m.addAttribute("userApply", applyList.get(0));

        m.addAttribute("showFlag", showFlag);
        return prefix + "/toView";
    }

    /**
     * 打开假期余额
     */
    @GetMapping("/holidayRest")
    public String holidayRest(ModelMap m) {
        long postId = postService.selectPostIdByUserId(ShiroUtils.getUserId());
        m.put("postId", postId);

        return prefix + "/holidayRest";
    }

    /**
     * 查看假期余额
     */
    @PostMapping("/holidayRest")
    @ResponseBody
    public AjaxResult holiday(UserApply userApply) {


        return toAjax(1);
    }

    /**
     * 打开我的假期余额
     */
    @GetMapping("/myHolidayRest")
    public String myHolidayRest(ModelMap m) {
		/*long postId = postService.selectPostIdByUserId(ShiroUtils.getUserId());
		m.put("postId", postId);*/

        return prefix + "/myHolidayRest";
    }

    /**
     * 查看我的假期余额
     */
    @PostMapping("/myHolidayRest")
    @ResponseBody
    public AjaxResult myHolidayRest(UserApply userApply) {


        return toAjax(1);
    }

    /**
     * 导出申请列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserApply userApply) {

        List<UserApply> list = userApplyService.selectUserApplyList(userApply);
        ExcelUtil<UserApply> util = new ExcelUtil<UserApply>(UserApply.class);

        return util.exportExcel(list, "userApply");
    }

    /**
     * 新增请假申请
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存请假申请
     */
    @Log(title = "申请", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserApply userApply) {
        userApply.setUserId(ShiroUtils.getUserId());
        int i = userApplyService.insertUserApply(userApply, ShiroUtils.getUserId());
        return toAjax(i);
    }

    /**
     * 修改申请
     */
    @GetMapping("/edit/{applyId}")
    public String edit(@PathVariable("applyId") Long applyId, ModelMap mmap) {
        UserApply userApply = userApplyService.selectUserApplyByIdForUndo(applyId);
        mmap.put("userApply", userApply);
        String applyType = userApply.getApplyType();
        if (applyType.equals("加班")) {
            return prefix + "/editOther";

        } else if (applyType.equals("外出")) {
            return prefix + "/editOut";

        } else if (applyType.equals("补卡")) {
            return prefix + "/editPic";

        } else {//请假、销假
            return prefix + "/edit";

        }
    }

    /**
     * 修改保存申请
     */
    @Log(title = "申请", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserApply userApply) {
        userApply.setUserId(ShiroUtils.getUserId());
        return toAjax(userApplyService.updateUserApply(userApply));
    }

    /**
     * 删除申请
     */
    @Log(title = "申请", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(userApplyService.deleteUserApplyByIds(ids));
    }

    /**
     * 撤回申请
     */
    @PostMapping("/takeBack")
    @ResponseBody
    public AjaxResult takeBack(Long ids) {
        return toAjax(userApplyService.takeBack(ids));
    }

    /**
     * 销假申请
     */
    @PostMapping("/undo")
    @ResponseBody
    public AjaxResult undo(Long ids) {
        int i = userApplyService.undoSave(ids);
        return toAjax(i);
    }

//    /**
//     * 销假申请
//     */
//    @GetMapping("/undo/{applyId}")
//    public String undo(@PathVariable("applyId") Long applyId, ModelMap mmap) {
//        UserApply userApply = userApplyService.selectUserApplyByIdForUndo(applyId);
//        System.out.println(userApply.getTimeapart1());
//        System.out.println(userApply.getTimeapart2());
//        mmap.addAttribute(userApply);
//        return prefix + "/undo";
//    }

    /**
     * 保存销假申请
     */
//    @PostMapping("/undo")
//    @ResponseBody
//    public AjaxResult undoSave(UserApply userApply) {
//        int i = userApplyService.undoSave(userApply, ShiroUtils.getUserId());
//
//        return toAjax(i);
//    }

    /**
     * 新增加班申请
     */
    @GetMapping("/addOther")
    public String addOther() {
        return prefix + "/addOther";
    }

    /**
     * 新增保存加班申请
     */
    @Log(title = "加班申请", businessType = BusinessType.INSERT)
    @PostMapping("/addOther")
    @ResponseBody
    public AjaxResult addOvertimeSave(UserApply userApply) {
        int i = userApplyService.addOvertimeSave(userApply, ShiroUtils.getUserId());
        return toAjax(i);
    }

    /**
     * 新增外出申请
     */
    @GetMapping("/addOut")
    public String addOut() {
        return prefix + "/addOut";
    }

    /**
     * 新增保存外出申请
     */
    @Log(title = "外出申请", businessType = BusinessType.INSERT)
    @PostMapping("/addOut")
    @ResponseBody
    public AjaxResult addOutSave(UserApply userApply) {
        int i = userApplyService.addOutSave(userApply, ShiroUtils.getUserId());
        return toAjax(i);
    }

    /**
     * 新增补卡申请
     */
    @GetMapping("/addPic")
    public String addPic() {
        return prefix + "/addPic";
    }


    /**
     * 新增补卡申请3次版
     */
    @GetMapping("/addPicPro")
    public String addPicPro() {
        return prefix + "/addPicPro";
    }


    /**
     * 新增保存补卡申请
     */
    @Log(title = "补卡申请", businessType = BusinessType.INSERT)
    @PostMapping("/addPic")
    @ResponseBody
    public AjaxResult addPicSave(UserApply userApply) {
        Long i = userApplyService.addPicSave(userApply, ShiroUtils.getUserId());
        return toAjax(i.intValue());
    }


    /**
     * 新增保存补卡申请
     */
    @Log(title = "补卡申请", businessType = BusinessType.INSERT)
    @PostMapping("/addPicPro")
    @ResponseBody
    public AjaxResult addPicProSave(UserApply userApply) {
        Long i = userApplyService.addPicProSave(userApply, ShiroUtils.getUserId());
        return toAjax(i.intValue());
    }


    /**
     * 上传多张图片
     */
    @PostMapping("/uploadList")
    @ResponseBody
    public AjaxResult uploadList(MultipartFile file_data, String fileId) throws Exception {
        userApplyService.uploadMateria(file_data, fileId);
        return AjaxResult.error();
    }

    /**
     * 验证员工是否通过试用一期
     */
    @PostMapping("/ifPass")
    @ResponseBody
    public String ifPass() {
        return userApplyService.ifPass(ShiroUtils.getUserId());
    }


    /**
     * 验证起始时间是否在加班时间范围内
     */
    @PostMapping("/ifSatisfy")
    @ResponseBody
    public String ifSatisfy(UserApply userApply) {
        userApply.setUserId(ShiroUtils.getUserId());
        return userApplyService.selectUserApplyListByTime(userApply);
    }

    /**
     * 验证是否能做销假操作
     */
    @PostMapping("/ifUndo")
    @ResponseBody
    public String ifUndo(Long applyId) {

        UserApply userApply = userApplyService.selectUserApplyByIdForUndo(applyId);
        //请假单是请假申请并且申请成功的时候可以做销假
        if (userApply.getApplyType().equals("请假") && userApply.getApplyState().equals("申请成功")) {
            return "0";
        } else {
            return "1";
        }
    }

    /**
     * 验证是否已经审核过
     */
    @PostMapping("/ifTakeback")
    @ResponseBody
    public String ifTakeback(Long applyId) {
        UserApply userApply = userApplyService.selectUserApplyByIdForUndo(applyId);
        System.out.println(userApply);
        //请假单是请假申请并且申请成功的时候可以做销假
        if (userApply.getApplyState().equals("已撤回")) {
            return "0";
        } else if (userApply.getApplyState().equals("待审批") && userApply.getApprovalState().equals("未操作")) {
            return "0";
        } else {
            return "1";
        }
    }

    /**
     * 验证有没有足够的年假或调休可请
     */
    @PostMapping("/ifHolidayEnough")
    @ResponseBody
    public String ifHolidayEnough(UserApply userApply) {
        userApply.setUserId(ShiroUtils.getUserId());
        return userApplyService.ifHolidayEnough(userApply);
    }

    /**
     * 验证选择的
     */
    @PostMapping("/ifUndoTimeRight")
    @ResponseBody
    public String ifUndoTimeRight(UserApply userApply) {


        return "0";
    }

    /**
     * 验证选择的加班开始时间是否超过可选时间
     */
    @PostMapping("/overWorkTimeRight")
    @ResponseBody
    public String overWorkTimeRight(UserApply userApply) {

        Date starttime = userApply.getStarttime();
        int res = userApplyService.compare(new Date(), starttime, 3);
        if (res == 1) {
            return "0";
        } else {
            return "1";
        }
    }


    /**
     * 验证选择的年假是否过期
     */
    @PostMapping("/ifOverdue")
    @ResponseBody
    public String ifOverdue(UserApply userApply) {
        userApply.setUserId(ShiroUtils.getUserId());
        String over = userApplyService.ifOverdue(userApply);
        if (over.equals("1")) {
            return "0";
        } else {
            return "1";
        }
    }


    /**
     * 判断是否时间超过27号,超过27号则不可以提交本月申请
     * 如果提交时间没有超过27号则可以更改上月26日至本月25日
     */
    @PostMapping("/frequency")
    @ResponseBody
    public String frequency(UserApply userApply) {
        userApply.setUserId(ShiroUtils.getUserId());
        if (userApplyService.frequency(userApply) > 2) {
            return "1";
        }
        return "0";
    }


    /**
     * 判断是否时间超过27号,超过27号则不可以提交本月申请
     * 如果提交时间没有超过27号则可以更改上月26日至本月25日
     *
     * 修改为判断时间是否超过26号16点
     */
//    @PostMapping("/overtime")
//    @ResponseBody
//    public String overtime(UserApply userApply) {
//        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
//        String newDate = dateformat.format(new Date());
//        String starttime = dateformat.format(userApply.getStarttime());
//        int newNumber = Integer.valueOf(newDate.substring(newDate.length() - 2));
//        int start = Integer.valueOf(starttime);
//        int startNumber = Integer.valueOf(starttime.substring(starttime.length() - 2));
//        int newnum = Integer.valueOf(newDate);
//        int newNumber4 = Integer.valueOf(newDate.substring(4, 6));
//        int startNumber4 = Integer.valueOf(starttime.substring(4, 6));
//        if (start >= newnum) {//提前申请直接通过
//            return "0";
//        }
//        if (newNumber >= 27 && newNumber4 == startNumber4) {
//            if (startNumber > 25) {
//                return "0";
//            } else {
//                return "1";
//            }
//        } else {
//            if (startNumber < 25 && newNumber4 == startNumber4) {
//                return "0";
//            } else if (startNumber > 25 && (newNumber4 - startNumber4) <= 1) {
//                return "0";
//            } else {
//                return "1";
//            }
//        }
//
//    }
    @PostMapping("/overtime")
    @ResponseBody
    public String overtime(UserApply userApply) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHH");
        String newDate = dateformat.format(new Date());
        String starttime = dateformat.format(userApply.getStarttime());
        int newNumber = Integer.valueOf(newDate.substring(newDate.length() - 4));
        int start = Integer.valueOf(starttime);
        int startNumber = Integer.valueOf(starttime.substring(starttime.length() - 4));
        int newnum = Integer.valueOf(newDate);
        int newNumber4 = Integer.valueOf(newDate.substring(4, 6));
        int startNumber4 = Integer.valueOf(starttime.substring(4, 6));
        if (start >= newnum) {//提前申请直接通过
            return "0";
        }
        if (newNumber >= 2616 && newNumber4 == startNumber4) {
            if (startNumber > 2500) {
                return "0";
            } else {
                return "1";
            }
        } else {
            if (startNumber < 2500 && newNumber4 == startNumber4) {
                return "0";
            } else if (startNumber > 2500 && (newNumber4 - startNumber4) <= 1) {
                return "0";
            } else {
                return "1";
            }
        }

    }

    /**
     * 验证选择的
     */
    @PostMapping("/ifRepeat")
    @ResponseBody
    public String ifRepeat(UserApply userApply) {
        userApply.setUserId(ShiroUtils.getUserId());

        List<UserApply> ifStarttimeRepeat = userApplyService.selectUserApplyListByStartTime(userApply);
        List<UserApply> ifEndtimeRepeat = userApplyService.selectUserApplyListByEndTime(userApply);
        if (ifStarttimeRepeat != null && ifStarttimeRepeat.size() > 0) {
            if (ifStarttimeRepeat.size() == 1 && userApply.getApplyId() != null) {
                //查出本身
                if (ifStarttimeRepeat.get(0).getApplyId().longValue() == userApply.getApplyId().longValue()) {
                    return "0";
                }
            }
            if (userApply.getTimeapart1() != null) {
                for (UserApply sUserApply : ifStarttimeRepeat) {
                    if (sUserApply.getTimeapart1().equals("1")) {
                        return "1";
                    } else if (userApply.getTimeapart1().equals("2")) {
                        if (sUserApply.getTimeapart1().equals("2")) {
                            return "1";
                        }
                    }
                }
            } else {
                return "1";
            }

        } else if (ifEndtimeRepeat != null && ifEndtimeRepeat.size() > 0) {
            if (ifEndtimeRepeat.size() == 1) {
                //查出本身
                if (ifEndtimeRepeat.get(0).getApplyId().longValue() == userApply.getApplyId().longValue()) {
                    return "0";
                }
            }
            if (userApply.getTimeapart2() != null) {
                for (UserApply sUserApply : ifEndtimeRepeat) {
                    if (sUserApply.getTimeapart2().equals("1")) {
                        if (userApply.getTimeapart1().equals("1")) {
                            return "1";
                        }
                    } else if (sUserApply.getTimeapart2().equals("2")) {
                        return "1";
                    }
                }
            } else {
                return "1";
            }

        }


        return "0";

    }

    /**
     * 验证选择的
     */
    @PostMapping("/ifMarriage")
    @ResponseBody
    public String ifMarriage(Long applyId) {
        List<UserApply> userApply = userApplyService.selectUserApplyById(applyId);
        if (userApply.get(0).getLeaveType().equals("产假")) {
            return "0";
        } else {
            return "1";
        }

    }

    /**
     * 验证销假时间是否在原请假时间范围内
     */
    @PostMapping("/ifBetween")
    @ResponseBody
    public String ifBetween(UserApply userApply) {
        boolean flag = userApplyService.ifBetween(userApply);
        if (flag) {
            return "0";
        } else {
            return "1";
        }

    }

    /**
     * 验证补卡申请是否重复
     */
    @PostMapping("/ifPicRepeat")
    @ResponseBody
    public String ifPicRepeat(UserApply userApply) {
        userApply.setUserId(ShiroUtils.getUserId());
        String result = userApplyService.ifPicRepeat(userApply);
        return result;
    }
}
