package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.IdWorker;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.finance.FacPayPublicApply;
import com.ruoyi.system.domain.finance.FacPayPublicDetailed;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.IFacPayPublicApplyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 对公申请 信息操作处理
 *
 * @author ruoyi
 * @date 2019-10-10
 */
@Controller
@RequestMapping("/system/facPayPublicApply")
public class FacPayPublicApplyController extends BaseController {
    private String prefix = "system/facPayPublicApply";

    @Autowired
    private IFacPayPublicApplyService facPayPublicApplyService;
    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("system:facPayPublicApply:view")
    @GetMapping()
    public String facPayPublicApply() {
        return prefix + "/facPayPublicApply";
    }

    /**
     * 查询对公申请列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FacPayPublicApply facPayPublicApply) {
        startPage();
        facPayPublicApply.setUser(ShiroUtils.getUserId());
        List<FacPayPublicApply> list = facPayPublicApplyService
                .selectFacPayPublicApplyList(facPayPublicApply);
        for (FacPayPublicApply v : list) {
            v.setUserName(sysUserService.selectUserById(v.getUser()).getUserName());
        }
        return getDataTable(list);
    }

    /**
     * 导出对公申请列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacPayPublicApply facPayPublicApply) {
        List<FacPayPublicApply> list = facPayPublicApplyService
                .selectFacPayPublicApplyList(facPayPublicApply);
        ExcelUtil<FacPayPublicApply> util = new ExcelUtil<FacPayPublicApply>(
                FacPayPublicApply.class);
        return util.exportExcel(list, "facPayPublicApply");
    }

    /**
     * 新增对公申请
     *
     * @throws Exception
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) throws Exception {
        IdWorker idWorker = new IdWorker(0, 1);
        mmap.put("num", "DG" + idWorker.nextId());
        return prefix + "/add";
    }

    /**
     * 新增保存对公申请
     */
    @Log(title = "对公申请", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FacPayPublicApply facPayPublicApply) {
        facPayPublicApply.setUser(ShiroUtils.getUserId());
        if (facPayPublicApply.getId() == null) {

            // 直接添加
            IdWorker idWorker = new IdWorker(0, 1);
            facPayPublicApply.setNum("JK" + idWorker.nextId());
            facPayPublicApply.setUser(ShiroUtils.getUserId());
        } else {
            // 更新
            facPayPublicApply = facPayPublicApplyService.selectFacPayPublicApplyById(facPayPublicApply.getId());
            facPayPublicApplyService.deleteFacPayPublicApplyByIds(facPayPublicApply.getId() + "");
        }
        return toAjax(facPayPublicApplyService
                .insertFacPayPublicApply(facPayPublicApply));
    }


    /**
     * 修改对公申请
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        FacPayPublicApply facPayPublicApply = facPayPublicApplyService
                .selectFacPayPublicApplyById(id);
        mmap.put("facPayPublicApply", facPayPublicApply);
        return prefix + "/edit";
    }

    /**
     * 修改保存对公申请
     */
    @Log(title = "对公申请", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FacPayPublicApply facPayPublicApply) {
        facPayPublicApply.setCreateTime(new Date());
        return toAjax(facPayPublicApplyService
                .updateFacPayPublicApply(facPayPublicApply));
    }

    /**
     * 删除对公申请
     */
    @Log(title = "对公申请", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(
                facPayPublicApplyService.deleteFacPayPublicApplyByIds(ids));
    }

    /**
     * 查看行程安排详情
     */
    @PostMapping("/query")
    @ResponseBody
    public TableDataInfo detail1(String num) {
        startPage();
        FacPayPublicApply facCostApply = facPayPublicApplyService.deatil(num);
        if (facCostApply != null) {
            List<FacPayPublicApply> facReimburseApplies = new ArrayList<>();
            facReimburseApplies.add(facCostApply);
            return getDataTable(facReimburseApplies);
        } else {
            List<String> a = new ArrayList<>();
            return getDataTable(a);
        }
    }

    /**
     * 查看详情
     */
    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id, ModelMap map) {
        FacPayPublicApply facPayPublicApply = new FacPayPublicApply();
        facPayPublicApply.setId(id.intValue());
        List<FacPayPublicApply> facReimburseApplies = facPayPublicApplyService
                .selectFacPayPublicApplyList(facPayPublicApply);
        map.put("rid", id);
        map.put("num", facReimburseApplies.get(0).getNum());
        map.put("status", facReimburseApplies.get(0).getStatus());
        return prefix + "/public";
    }

    /**
     * 查看明细
     */
    @PostMapping("/dgtail")
    @ResponseBody
    public TableDataInfo dgtail(String num) {

        startPage();
        List<FacPayPublicDetailed> facPayPublicDetailed = facPayPublicApplyService
                .dgtail(num);
        if (facPayPublicDetailed != null) {
            return getDataTable(facPayPublicDetailed);
        } else {
            List<String> a = new ArrayList<>();
            return getDataTable(a);
        }
    }

    /**
     * 新增借款申请
     */
    @GetMapping("/offset")
    public String offset(@RequestParam String num, ModelMap map) {
        map.put("num", num);
        return prefix + "/offset";
    }

    /**
     * 新增保存对公申请
     */
    @Log(title = "对公申请", businessType = BusinessType.INSERT)
    @PostMapping("/addDetail")
    @ResponseBody
    public AjaxResult addDetail(FacPayPublicDetailed facPayPublicDetailed) {

        return toAjax(facPayPublicApplyService
                .insertFacPayPublicDetailed(facPayPublicDetailed));
    }


    /**
     * 新增保存
     *
     * @throws Exception
     */
    @Log(title = "借款申请", businessType = BusinessType.INSERT)
    @PostMapping("/addSove")
    @ResponseBody
    public AjaxResult addSove(FacPayPublicApply FacPayPublicApply) throws Exception {

        return toAjax(facPayPublicApplyService.insertApply(FacPayPublicApply));

    }


    /**
     * 修改对公明细
     */
    @GetMapping("/editPub/{id}")
    public String editPub(@PathVariable("id") Long id, ModelMap mmap) {
        FacPayPublicDetailed facPayPublicDetailed = facPayPublicApplyService.selectFacPayPublicDetailedById(id);
        mmap.put("facPayPublicDetailed", facPayPublicDetailed);
        mmap.put("dgnum", facPayPublicDetailed.getNum());
        return prefix + "/editPub";
    }

    /**
     * 修改保存对公明细
     */

    @Log(title = "对公明细", businessType = BusinessType.UPDATE)
    @PostMapping("/editPub")
    @ResponseBody
    public AjaxResult editPubSave(FacPayPublicDetailed facPayPublicDetailed) {
        return toAjax(facPayPublicApplyService.updateFacPayPublicDetailed(facPayPublicDetailed));
    }


    /**
     * 删除对公明细
     */

    @Log(title = "对公明细", businessType = BusinessType.DELETE)
    @PostMapping("/removePub")
    @ResponseBody
    public AjaxResult removepub(String ids) {
        return toAjax(facPayPublicApplyService.deleteFacPayPublicDetailedByIds(ids));
    }

}
