package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.finance.FacFileUpload;
import com.ruoyi.system.service.finance.IFacFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * 财务文件上传记录 信息操作处理
 *
 * @author ruoyi
 * @date 2020-04-22
 */
@Controller
@RequestMapping("/system/facFileUpload")
public class FacFileUploadController extends BaseController {
    private String prefix = "system/facFileUpload";

    @Autowired
    private IFacFileUploadService facFileUploadService;


    @GetMapping()
    public String facFileUpload() {
        return prefix + "/facFileUpload";
    }

    /**
     * 查询财务文件上传记录列表
     */

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FacFileUpload facFileUpload) {
        startPage();
        List<FacFileUpload> list = facFileUploadService.selectFacFileUploadList(facFileUpload);
        return getDataTable(list);
    }


    /**
     * 导出财务文件上传记录列表
     */

    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacFileUpload facFileUpload) {
        List<FacFileUpload> list = facFileUploadService.selectFacFileUploadList(facFileUpload);
        ExcelUtil<FacFileUpload> util = new ExcelUtil<FacFileUpload>(FacFileUpload.class);
        return util.exportExcel(list, "facFileUpload");
    }

    /**
     * 新增财务文件上传记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存财务文件上传记录
     */

    @Log(title = "财务文件上传记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FacFileUpload facFileUpload) {
        return toAjax(facFileUploadService.insertFacFileUpload(facFileUpload));
    }

    /**
     * 修改财务文件上传记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        FacFileUpload facFileUpload = facFileUploadService.selectFacFileUploadById(id);
        mmap.put("facFileUpload", facFileUpload);
        return prefix + "/edit";
    }

    /**
     * 修改保存财务文件上传记录
     */

    @Log(title = "财务文件上传记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FacFileUpload facFileUpload) {
        return toAjax(facFileUploadService.updateFacFileUpload(facFileUpload));
    }

    /**
     * 删除财务文件上传记录
     */

    @Log(title = "财务文件上传记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(facFileUploadService.deleteFacFileUploadByIds(ids));
    }


    /**
     * 验证图片是否上传
     */
    @Log(title = "图片是否已经上传")
    @PostMapping("/ifPicUpload")
    @ResponseBody
    public boolean ifPicUpload(String num) {
        boolean flag = facFileUploadService.ifPicUpload(num);

        return flag;
    }






    @Log(title = "下载图片")
    @GetMapping("/xaizai/num")
    @ResponseBody
    public void xaizai(@PathVariable("num") String num, HttpServletResponse response) throws Exception {
        facFileUploadService.xaizainum(num);
        File file = null;
        String filePath = Global.getUploadPath();
        file = new File(filePath+num+1+File.separator+num+".zip");

        ServletOutputStream outputStream = null;
        try {

            // 下载名称
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName="+num+".zip");
            outputStream = response.getOutputStream();
            FileUtils.writeBytes(file.getAbsolutePath(), outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }



}
