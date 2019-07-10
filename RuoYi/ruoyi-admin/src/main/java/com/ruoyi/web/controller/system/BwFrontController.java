package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.BqZhanxian;
import com.ruoyi.system.domain.BwZhanXian;
import com.ruoyi.system.domain.JfFront;
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
import com.ruoyi.system.domain.BwFront;
import com.ruoyi.system.service.IBwFrontService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 宝沃前端 信息操作处理
 *
 * @author ruoyi
 * @date 2019-07-09
 */
@Controller
@RequestMapping("/system/bwFront")
public class BwFrontController extends BaseController {
    private String prefix = "system/bwFront";

    @Autowired
    private IBwFrontService bwFrontService;

    @RequiresPermissions("system:bwFront:view")
    @GetMapping()
    public String bwFront() {
        return prefix + "/bwFront";
    }

    /**
     * 查询宝沃前端列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BwFront bwFront) {
        startPage();
        List<BwZhanXian> list = bwFrontService.showList();
        return getDataTable(list);
    }


    /**
     * 导出宝沃前端列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BwZhanXian bwFront) {
    	List<BwZhanXian> list = bwFrontService.showList();
        ExcelUtil<BwZhanXian> util = new ExcelUtil<BwZhanXian>(BwZhanXian.class);
        return util.exportExcel(list, "宝沃前端");
    }

    /**
     * 新增宝沃前端
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存宝沃前端
     */
    @Log(title = "宝沃前端", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BwFront bwFront) {
        return toAjax(bwFrontService.insertBwFront(bwFront));
    }

    /**
     * 修改宝沃前端
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        BwFront bwFront = bwFrontService.selectBwFrontById(id);
        mmap.put("bwFront", bwFront);
        return prefix + "/edit";
    }

    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<BwFront> util = new ExcelUtil<BwFront>(BwFront.class);
        return util.importTemplateExcel("宝沃前端数据");
    }


    @Log(title = "宝沃前端", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<BwFront> util = new ExcelUtil<BwFront>(BwFront.class);
        List<BwFront> bwFront = util.importExcel(file.getInputStream(), 7, 8);
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = bwFrontService.importBwFront(bwFront, false, operName);
        return AjaxResult.success(message);
    }

    /**
     * 修改保存宝沃前端
     */
    @Log(title = "宝沃前端", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BwFront bwFront) {
        return toAjax(bwFrontService.updateBwFront(bwFront));
    }

    /**
     * 删除宝沃前端
     */
    @Log(title = "宝沃前端", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(bwFrontService.deleteBwFrontByIds(ids));
    }


    /**
     * 查询宝沃展现列表
     */
    @GetMapping("/showlist")
    @ResponseBody
    public TableDataInfo showlist() {
        List<BwZhanXian> list = bwFrontService.showList();
        return getDataTable(list);
    }


}
