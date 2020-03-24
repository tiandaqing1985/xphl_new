package com.ruoyi.web.controller.system;

import java.util.Date;
import java.util.List;

import com.ruoyi.system.domain.XzAssetHandRecord;
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
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.XzAssetRepair;
import com.ruoyi.system.domain.XzPersonalAsset;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.IXzAssetHandRecordService;
import com.ruoyi.system.service.IXzAssetRepairService;
import com.ruoyi.system.service.IXzAsstesService;
import com.ruoyi.system.service.IXzPersonalAssetService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 资产维修 信息操作处理
 *
 * @author ruoyi
 * @date 2019-09-09
 */
@Controller
@RequestMapping("/system/xzAssetRepair")
public class XzAssetRepairController extends BaseController {
    private String prefix = "system/xzAssetRepair";

    @Autowired
    private IXzAssetRepairService xzAssetRepairService;

    @Autowired
    private IXzAsstesService xzAsstesService;

    @Autowired
    private IXzAssetHandRecordService xzAssetHandRecordService;

    @Autowired
    private IXzPersonalAssetService xzPersonalAssetService;

    @Autowired
    private ISysDeptService sysDeptService;


    @RequiresPermissions("system:xzAssetRepair:view")
    @GetMapping()
    public String xzAssetRepair() {
        return prefix + "/xzAssetRepair";
    }

    @RequiresPermissions("system:xzAssetRepairHistory:view")
    @GetMapping("/history")
    public String xzAssetRepairHistory() {
        return prefix + "/xzAssetRepairHistory";
    }

    /**
     * 查询资产维修列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(XzAssetRepair xzAssetRepair) {
        SysDept dept = sysDeptService.selectDeptById(ShiroUtils.getSysUser().getDeptId());

        if (ShiroUtils.getUserId() == 1 || ShiroUtils.getUserId() == 103 || ShiroUtils.getSysUser().getUserName().equals(dept.getLeader())) { //超级管理员 和 任总 行政部门leader看所有数据
            xzAssetRepair.setRegion(xzAssetRepair.getRegion());
        } else {
            String region = ShiroUtils.getSysUser().getArea();
            xzAssetRepair.setRegion(region);
        }
        startPage();
        List<XzAssetRepair> list = xzAssetRepairService.selectXzAssetRepairList(xzAssetRepair);
        return getDataTable(list);
    }

    /**
     * 查询资产维修列表
     */
    @PostMapping("/listHistory")
    @ResponseBody
    public TableDataInfo listHistory(XzAssetRepair xzAssetRepair) {
        SysDept dept = sysDeptService.selectDeptById(ShiroUtils.getSysUser().getDeptId());

        xzAssetRepair.setRegion(xzAssetRepair.getRegion());
        startPage();
        List<XzAssetRepair> list = xzAssetRepairService.selectXzAssetRepairHistoryList(xzAssetRepair);
        return getDataTable(list);
    }

    /**
     * 导出资产维修列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XzAssetRepair xzAssetRepair) {
        List<XzAssetRepair> list = xzAssetRepairService.selectXzAssetRepairList(xzAssetRepair);
        ExcelUtil<XzAssetRepair> util = new ExcelUtil<XzAssetRepair>(XzAssetRepair.class);
        return util.exportExcel(list, "xzAssetRepair");
    }

    /**
     * 新增资产维修
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap) {
        XzPersonalAsset xzPersonalAsset = xzPersonalAssetService.selectXzPersonalAssetById(id);
        mmap.put("id", id);
        mmap.put("xzAsstes", xzAsstesService.selectXzAsstesById(xzPersonalAsset.getAssetId()));
        XzAssetHandRecord xzAssetHandRecord = xzAssetHandRecordService.selectXzAssetHandRecordByAssetId(xzPersonalAsset.getAssetId());
        if(xzAssetHandRecord == null){
            xzAssetHandRecord = new XzAssetHandRecord();
        }
        mmap.put("xzHand", xzAssetHandRecord);
        return prefix + "/add";
    }

    /**
     * 新增保存资产维修
     */
    @Log(title = "资产维修", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(XzAssetRepair xzAssetRepair) {
        xzAssetRepair.setUserId(ShiroUtils.getUserId());
        //待维修
        xzAssetRepair.setRepairStatus("1");
        xzAssetRepair.setCreateBy(ShiroUtils.getUserId().toString());
        xzAssetRepair.setCreateTime(new Date());
        return toAjax(xzAssetRepairService.insertXzAssetRepair(xzAssetRepair));
    }

    /**
     * 修改资产维修
     */
    @GetMapping("/edit/{repairId}")
    public String edit(@PathVariable("repairId") Long repairId, ModelMap mmap) {
        XzAssetRepair xzAssetRepair = xzAssetRepairService.selectXzAssetRepairById(repairId);
        XzPersonalAsset xzPersonalAsset = xzPersonalAssetService.selectXzPersonalAssetById(xzAssetRepair.getAssetId());
        mmap.put("xzAssetRepair", xzAssetRepair);
        mmap.put("xzAsstes", xzAsstesService.selectXzAsstesById(xzPersonalAsset.getAssetId()));
        mmap.put("xzHand", xzAssetHandRecordService.selectXzAssetHandRecordByAssetId(xzPersonalAsset.getAssetId()));
        return prefix + "/edit";
    }

    /**
     * 修改保存资产维修
     */
    @Log(title = "资产维修", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(XzAssetRepair xzAssetRepair) {
        xzAssetRepair.setHandlerId(ShiroUtils.getUserId());
        xzAssetRepair.setHandlerTime(new Date());
        xzAssetRepair.setUpdateBy(ShiroUtils.getUserId().toString());
        xzAssetRepair.setUpdateTime(new Date());
        return toAjax(xzAssetRepairService.updateXzAssetRepair(xzAssetRepair));
    }

    /**
     * 删除资产维修
     */
    @Log(title = "资产维修", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(xzAssetRepairService.deleteXzAssetRepairByIds(ids));
    }

}
