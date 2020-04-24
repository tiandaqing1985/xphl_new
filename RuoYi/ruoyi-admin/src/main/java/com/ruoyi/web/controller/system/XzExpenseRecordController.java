package com.ruoyi.web.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.framework.web.service.PermissionService;
import com.ruoyi.system.domain.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.IXzExpenseRecordService;
import com.ruoyi.system.service.IXzResourceTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 费用记录 信息操作处理
 *
 * @author ruoyi
 * @date 2019-09-23
 */
@Controller
@RequestMapping("/system/xzExpenseRecord")
public class XzExpenseRecordController extends BaseController {
    private String prefix = "system/xzExpenseRecord";

    @Autowired
    private IXzExpenseRecordService xzExpenseRecordService;

    @Autowired
    private IXzResourceTypeService xzResourceTypeService;

    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private PermissionService permission;

    @RequiresPermissions("system:xzExpenseRecord:view")
    @GetMapping()
    public String xzExpenseRecord() {
        return prefix + "/xzExpenseRecord";
    }

    @GetMapping("/xzExpenseMonthRecord")
    public String xzExpenseMonthRecord(String expenseTypeParent, String expenseType, String startTime, String endTime, String month, ModelMap map) {
        map.put("expenseTypeParent", expenseTypeParent);
        map.put("expenseType", expenseType);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("month", month);
        return prefix + "/xzExpenseMonthRecord";
    }

    @GetMapping("/xzExpenseSta")
    public String xzExpenseSta(ModelMap modelMap) {
        modelMap.put("userid",ShiroUtils.getUserId());
        return "system/xzExpenseRecord/xzExpenseList";
    }

    /**
     * 查询类型费用详细统计
     */
    @GetMapping("/xzExpenseDataList")
    public String detailList(XzExpenseDetailRequestVO xzExpenseDetailRequestVO, ModelMap mmap) {
        mmap.put("xzExpenseDetailRequestVO", xzExpenseDetailRequestVO);
        return "system/xzExpenseRecord/xzExpenseDataList";
    }

    /**
     * 查询类型费用详细统计
     */
    @GetMapping("/compare")
    public String compare() {
        return "system/xzExpenseRecord/compare";
    }

    /**
     * 查询费用记录列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(XzExpenseRecord xzExpenseRecord) {
        startPage();

        SysDept dept = sysDeptService.selectDeptById(ShiroUtils.getSysUser().getDeptId());

        if (permission.hasRole("xzzj").equals("") || ShiroUtils.getUserId() == 1 || ShiroUtils.getUserId() == 103) { //超级管理员 和 任总看所有数据

        } else {
            if (ShiroUtils.getSysUser().getUserName().equals(dept.getLeader())) {  //行政部门leader查看所有

            } else {
                String region = ShiroUtils.getSysUser().getArea();
                if (xzExpenseRecord.getRegion() == null || xzExpenseRecord.getRegion().equals("")) {
                    xzExpenseRecord.setRegion(region);
                }
            }
        }
        List<XzExpenseRecord> list = xzExpenseRecordService.selectXzExpenseRecordList(xzExpenseRecord);
        return getDataTable(list);
    }

    /**
     * 查询费用统计列表
     */
    @PostMapping("/xzExpenseList")
    @ResponseBody
    public TableDataInfo xzExpenseList(XzExpenseSta xzExpenseSta, XzExpenseCompareVO compareData) {

        SysDept dept = sysDeptService.selectDeptById(ShiroUtils.getSysUser().getDeptId());

        if (permission.hasRole("xzzj").equals("") || ShiroUtils.getUserId() == 1 || ShiroUtils.getUserId() == 103) { //超级管理员 和 任总看所有数据

        } else {
            if (ShiroUtils.getSysUser().getUserName().equals(dept.getLeader())) {  //行政部门leader查看所有

            } else {

            }
        }
        startPage();
        List<XzExpenseSta> list = null;
        if (compareData.getFlg() != null && !compareData.getFlg().equals("")) {
            Map dataMap = new HashMap<>();
            dataMap.put("xzExpenseSta",xzExpenseSta);
            dataMap.put("compareData",compareData);
            list = xzExpenseRecordService.selectCompareXzExpenseList(dataMap);
        }else{
            list = xzExpenseRecordService.selectXzExpenseList(xzExpenseSta);
        }
        return getDataTable(list);
    }

    /**
     * 查询费用统计列表
     */
    @PostMapping("/xzExpenseDataList")
    @ResponseBody
    public TableDataInfo xzExpenseDataList(XzExpenseDetailRequestVO xzExpenseDetailRequestVO) {

        String region = null;

        XzExpenseSta xzExpenseSta = new XzExpenseSta();
        xzExpenseSta.setRegion(xzExpenseDetailRequestVO.getRegion());

        SysDept dept = sysDeptService.selectDeptById(ShiroUtils.getSysUser().getDeptId());

        if (permission.hasRole("xzzj").equals("") || ShiroUtils.getUserId() == 1 || ShiroUtils.getUserId() == 103) { //超级管理员 和 任总看所有数据

        } else {
            if (ShiroUtils.getSysUser().getUserName().equals(dept.getLeader())) {  //行政部门leader查看所有

            } else {
                region = ShiroUtils.getSysUser().getArea();
                xzExpenseSta.setRegion(region);
            }
        }
        xzExpenseSta.setExpenseTypeParent(xzExpenseDetailRequestVO.getExpenseTypeParent());
        xzExpenseSta.getParams().put("beginTime", xzExpenseDetailRequestVO.getStartTime());
        xzExpenseSta.getParams().put("endTime", xzExpenseDetailRequestVO.getEndTime());
        xzExpenseSta.setDepts(xzExpenseDetailRequestVO.getDepts());
        startPage();
        List<XzExpenseSta> list = null;
        if(xzExpenseDetailRequestVO.getFlg().equals("")){
            list = xzExpenseRecordService.selectXzExpenseDetailList(xzExpenseSta);
        }else{
            XzExpenseSta compareData = new XzExpenseSta();
            compareData.setRegion(xzExpenseDetailRequestVO.getRegionCompare());
            compareData.setExpenseTypeParent(xzExpenseDetailRequestVO.getExpenseTypeParent());
            compareData.getParams().put("beginTime", xzExpenseDetailRequestVO.getStartTimeCompare());
            compareData.getParams().put("endTime", xzExpenseDetailRequestVO.getEndTimeCompare());
            compareData.setDepts(xzExpenseDetailRequestVO.getDeptsCompare());

            Map map = new HashMap();
            map.put("xzExpenseSta",xzExpenseSta);
            map.put("compareData",compareData);
            list = xzExpenseRecordService.selectCompareXzExpenseDetailList(map);
        }
        return getDataTable(list);
    }


    /**
     * 导出费用记录列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XzExpenseRecord xzExpenseRecord) {
        List<XzExpenseRecord> list = xzExpenseRecordService.selectXzExpenseRecordList(xzExpenseRecord);
        ExcelUtil<XzExpenseRecord> util = new ExcelUtil<XzExpenseRecord>(XzExpenseRecord.class);
        return util.exportExcel(list, "xzExpenseRecord");
    }

    /**
     * 新增费用记录
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("applyUser", ShiroUtils.getSysUser().getUserName());
        mmap.put("region", ShiroUtils.getSysUser().getArea());
        //获取采购类型
        mmap.put("typeList", xzResourceTypeService.selectByFeiList());
        return prefix + "/add";
    }

    /**
     * 新增保存费用记录
     */
    @Log(title = "费用记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(XzExpenseRecord xzExpenseRecord) {
        xzExpenseRecord.setRegion(ShiroUtils.getSysUser().getArea());
        xzExpenseRecord.setCreateBy(ShiroUtils.getUserId().toString());
        xzExpenseRecord.setCreateTime(new Date());
        xzExpenseRecord.setApplyUser(ShiroUtils.getUserId().toString());
        xzExpenseRecord.setSubmitType("1");
        return toAjax(xzExpenseRecordService.insertXzExpenseRecord(xzExpenseRecord));
    }

    /**
     * 新增保存费用记录
     */
    @Log(title = "费用记录", businessType = BusinessType.INSERT)
    @PostMapping("/addSub")
    @ResponseBody
    public AjaxResult addSubSave(XzExpenseRecord xzExpenseRecord) {
        xzExpenseRecord.setRegion(ShiroUtils.getSysUser().getArea());
        xzExpenseRecord.setCreateBy(ShiroUtils.getUserId().toString());
        xzExpenseRecord.setCreateTime(new Date());
        xzExpenseRecord.setApplyUser(ShiroUtils.getUserId().toString());
        xzExpenseRecord.setSubmitType("2");
        return toAjax(xzExpenseRecordService.insertXzExpenseRecord(xzExpenseRecord));
    }

    /**
     * 修改费用记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        XzExpenseRecord xzExpenseRecord = xzExpenseRecordService.selectXzExpenseRecordById(id);
        mmap.put("xzExpenseRecord", xzExpenseRecord);
        //获取采购类型
        mmap.put("typeList", xzResourceTypeService.selectByFeiList());
        mmap.put("dataList", xzResourceTypeService.xzResourceDataByParentId(xzExpenseRecord.getExpenseTypeParent()));
        return prefix + "/edit";
    }

    /**
     * 费用记录详情
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap) {
        XzExpenseRecord xzExpenseRecord = xzExpenseRecordService.selectXzExpenseRecordById(id);
        mmap.put("xzExpenseRecord", xzExpenseRecord);
        //获取采购类型
        mmap.put("typeList", xzResourceTypeService.selectByFeiList());
        mmap.put("dataList", xzResourceTypeService.xzResourceDataByParentId(xzExpenseRecord.getExpenseTypeParent()));
        return prefix + "/detail";
    }

    /**
     * 修改保存费用记录
     */
    @Log(title = "费用记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(XzExpenseRecord xzExpenseRecord) {
        xzExpenseRecord.setRegion(ShiroUtils.getSysUser().getArea());
        xzExpenseRecord.setUpdateBy(ShiroUtils.getUserId().toString());
        xzExpenseRecord.setUpdateTime(new Date());
        xzExpenseRecord.setApplyUser(ShiroUtils.getUserId().toString());
        return toAjax(xzExpenseRecordService.updateXzExpenseRecord(xzExpenseRecord));
    }

    /**
     * 修改保存费用记录
     */
    @Log(title = "费用记录", businessType = BusinessType.UPDATE)
    @PostMapping("/editSub")
    @ResponseBody
    public AjaxResult editSubSave(XzExpenseRecord xzExpenseRecord) {
        xzExpenseRecord.setRegion(ShiroUtils.getSysUser().getArea());
        xzExpenseRecord.setUpdateBy(ShiroUtils.getUserId().toString());
        xzExpenseRecord.setUpdateTime(new Date());
        xzExpenseRecord.setSubmitType("2");
        xzExpenseRecord.setApplyUser(ShiroUtils.getUserId().toString());
        return toAjax(xzExpenseRecordService.updateXzExpenseRecord(xzExpenseRecord));
    }

    /**
     * 删除费用记录
     */
    @Log(title = "费用记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long id) {
        return toAjax(xzExpenseRecordService.deleteXzExpenseRecordByIds(id.toString()));
    }

}
