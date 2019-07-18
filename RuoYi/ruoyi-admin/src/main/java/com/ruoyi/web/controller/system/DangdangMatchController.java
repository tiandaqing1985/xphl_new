package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.BwFront;
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
import com.ruoyi.system.domain.DangdangMatch;
import com.ruoyi.system.service.IDangdangMatchService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 当当词性匹配 信息操作处理
 *
 * @author ruoyi
 * @date 2019-07-10
 */
@Controller
@RequestMapping("/system/dangdangMatch")
public class DangdangMatchController extends BaseController {
    private String prefix = "system/dangdangMatch";

    @Autowired
    private IDangdangMatchService dangdangMatchService;

    @RequiresPermissions("system:dangdangMatch:view")
    @GetMapping()
    public String dangdangMatch() {
        return prefix + "/dangdangMatch";
    }

    /**
     * 查询当当词性匹配列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DangdangMatch dangdangMatch) {
        startPage();
        List<DangdangMatch> list = dangdangMatchService.selectDangdangMatchList(dangdangMatch);
        return getDataTable(list);
    }


    /**
     * 导出当当词性匹配列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangdangMatch dangdangMatch) {
        List<DangdangMatch> list = dangdangMatchService.selectDangdangMatchList(dangdangMatch);
        ExcelUtil<DangdangMatch> util = new ExcelUtil<DangdangMatch>(DangdangMatch.class);
        return util.exportExcel(list, "dangdangMatch");
    }

    /**
     * 新增当当词性匹配
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存当当词性匹配
     */
    @Log(title = "当当词性匹配", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DangdangMatch dangdangMatch) {
        return toAjax(dangdangMatchService.insertDangdangMatch(dangdangMatch));
    }

    /**
     * 修改当当词性匹配
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        DangdangMatch dangdangMatch = dangdangMatchService.selectDangdangMatchById(id);
        mmap.put("dangdangMatch", dangdangMatch);
        return prefix + "/edit";
    }

    /**
     * 修改保存当当词性匹配
     */
    @Log(title = "当当词性匹配", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DangdangMatch dangdangMatch) {
        return toAjax(dangdangMatchService.updateDangdangMatch(dangdangMatch));
    }

    /**
     * 删除当当词性匹配
     */
    @Log(title = "当当词性匹配", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dangdangMatchService.deleteDangdangMatchByIds(ids));
    }

    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<DangdangMatch> util = new ExcelUtil<DangdangMatch>(DangdangMatch.class);
        return util.importTemplateExcel("当当前端数据");
    }


    @Log(title = "当当前端", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<DangdangMatch> util = new ExcelUtil<DangdangMatch>(DangdangMatch.class);
        List<DangdangMatch> dangdangMatch = util.importExcel(file.getInputStream(), 0, 1);
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = dangdangMatchService.importBwFront(dangdangMatch, false, operName);
        return AjaxResult.success(message);
    }

}
