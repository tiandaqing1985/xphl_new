package com.ruoyi.web.controller.system;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 资产 信息操作处理
 *
 * @author ruoyi
 * @date 2019-08-02
 */
@Controller
@RequestMapping("/system/xzAsstes")
public class XzAsstesController extends BaseController {
    private String prefix = "system/xzAsstes";

    @Autowired
    private IXzAsstesService xzAsstesService;

    @Autowired
    private IXzAssetTypeService xzAssetTypeService;

    @Autowired
    private IXzAssetDataService xzAssetDataService;

    @Autowired
    private ISysDeptService sysDeptService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IXzCheckService xzCheckService;

    @RequiresPermissions("system:xzAsstes:view")
    @GetMapping()
    public String xzAsstes(ModelMap modelMap) {
        modelMap.put("userid",ShiroUtils.getUserId());
        return prefix + "/xzAsstes";
    }

    @GetMapping("/xzAsstesHand")
    public String xzAsstesHand(ModelMap modelMap) {
        modelMap.put("userid",ShiroUtils.getUserId());
        return prefix + "/xzAsstesHand";
    }

    @GetMapping("/xzStatistics")
    public String xzStatistics(ModelMap modelMap) {
        modelMap.put("userid",ShiroUtils.getUserId());
        return "system/xzStationeryasset/xzStatistics";
    }

    @GetMapping("/xzAllStatistics")
    public String xzAllStatistics() {
        return "system/xzStationeryasset/xzAllStatistics";
    }

    @GetMapping("/xzStationeryasset")
    public String xzStationeryasset(ModelMap modelMap) {
        modelMap.put("userid",ShiroUtils.getUserId());
        return "system/xzStationeryasset/xzStationeryasset";
    }


    /**
     * 查询资产列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(XzAsstes xzAsstes) {
        xzAsstes.setSort("1");// 固定资产
        SysDept dept = sysDeptService.selectDeptById(ShiroUtils.getSysUser().getDeptId());

        if (ShiroUtils.getUserId() == 1 || ShiroUtils.getUserId() == 103) { //超级管理员 和 任总看所有数据

        } else {
            if (ShiroUtils.getSysUser().getUserName().equals(dept.getLeader())) {  //行政部门leader查看所有

            } else {
                String region = ShiroUtils.getSysUser().getArea();
                if (xzAsstes.getRegion() == null || xzAsstes.getRegion().equals("")) {
                    xzAsstes.setRegion(region);
                }
            }
        }
        xzAsstes.setPurchaseBy(sysUserService.selectUserIdByUserNameOnly(xzAsstes.getPurchaseName()));
        startPage();
        List<XzAsstes> list = xzAsstesService.selectXzAsstesList(xzAsstes);
        return getDataTable(list);
    }

    /**
     * 查询资产分配列表
     */
    @PostMapping("/handlist")
    @ResponseBody
    public TableDataInfo handlist(XzAsstes xzAsstes) {
        xzAsstes.setSort("1");// 固定资产
//        xzAsstes.setSubmitType("2");
        SysDept dept = sysDeptService.selectDeptById(ShiroUtils.getSysUser().getDeptId());

        if (ShiroUtils.getUserId() == 1 || ShiroUtils.getUserId() == 103) { //超级管理员 和 任总看所有数据

        } else {
            if (ShiroUtils.getSysUser().getUserName().equals(dept.getLeader())) {  //行政部门leader查看所有

            } else {
                String region = ShiroUtils.getSysUser().getArea();
                if (xzAsstes.getRegion() == null || xzAsstes.getRegion().equals("")) {
                    xzAsstes.setRegion(region);
                }
            }
        }
        xzAsstes.setPurchaseBy(sysUserService.selectUserIdByUserNameOnly(xzAsstes.getPurchaseName()));
        startPage();
        List<XzAsstes> list = xzAsstesService.selectXzAsstesList(xzAsstes);
        return getDataTable(list);
    }

    /**
     * 查询资产列表
     */
    @PostMapping("/xzStationeryassetList")
    @ResponseBody
    public TableDataInfo xzStationeryassetList(XzAsstes xzAsstes) {
//        xzAsstes.setSort("2");// 办公用品资产
        xzAsstes.setPurchaseBy(sysUserService.selectUserIdByUserNameOnly(xzAsstes.getPurchaseName()));
        startPage();
        List<XzAsstes> list = xzAsstesService.selectXzAsstesList(xzAsstes);
        return getDataTable(list);
    }

    /**
     * 查询办公资产库存列表
     */
    @PostMapping("/xzStatisticsList")
    @ResponseBody
    public TableDataInfo xzStatisticsList(XzAsstesSta xzAsstesSta) {
		/*Long loginUser=ShiroUtils.getUserId();
		if(loginUser==1 ||ShiroUtils.getLoginName()=="admin" ){
			//查看全部
		}else{
			//使用者查看所在地区的资产库存
			if(xzAsstesSta.getRegion()==null||xzAsstesSta.getRegion().isEmpty()){
				xzAsstesSta.setRegion(sysUserService.selectUserById(loginUser).getArea());
			}
		}*/
        SysDept dept = sysDeptService.selectDeptById(ShiroUtils.getSysUser().getDeptId());
        if (ShiroUtils.getUserId() == 1 || ShiroUtils.getUserId() == 103) { //超级管理员 和 任总看所有数据

        } else {
            if (ShiroUtils.getSysUser().getUserName().equals(dept.getLeader())) {  //行政部门leader查看所有

            } else {
                String region = ShiroUtils.getSysUser().getArea();
                if (xzAsstesSta.getRegion() == null || xzAsstesSta.getRegion().equals("")) {
                    xzAsstesSta.setRegion(region);
                }
            }
        }
        startPage();
        List<XzAsstesSta> list = xzAsstesService.selectXzStatisticsList(xzAsstesSta);
        return getDataTable(list);
    }

    /**
     * 导出资产列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XzAsstes xzAsstes) {
        xzAsstes.setPurchaseBy(sysUserService.selectUserIdByUserNameOnly(xzAsstes.getPurchaseName()));
        List<XzAsstes> list = xzAsstesService.selectXzAsstesList(xzAsstes);
        ExcelUtil<XzAsstes> util = new ExcelUtil<XzAsstes>(XzAsstes.class);
        return util.exportExcel(list, "xzAsstes");
    }

    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<XzAsstes> util = new ExcelUtil<XzAsstes>(XzAsstes.class);
        List<XzAsstes> assetsList = util.importExcel(file.getInputStream(), 0, 1);
        String operName = ShiroUtils.getUserId().toString();
        String message = xzAsstesService.importXzAsstes(assetsList, updateSupport, operName);
        if (message.equals("导入成功")) {
            return AjaxResult.success(message);
        } else if (message.equals("导入失败")) {
            return AjaxResult.error(message);
        } else {
            return AjaxResult.warn(message);
        }

    }

    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<XzAsstes> util = new ExcelUtil<XzAsstes>(XzAsstes.class);
        return util.importTemplateExcel("资产数据");
    }

    /**
     * 新增固定资产资产
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        // 获取办公用品资产父级类型
        mmap.put("typeList", xzAssetTypeService.selectXzAssetTypeByAssAll());
        mmap.put("userid",ShiroUtils.getUserId());
        return prefix + "/add";
    }

    /**
     * 新增办公用品资产
     */
    @GetMapping("/addSta")
    public String addSta(ModelMap mmap) {
        // 获取办公用品资产父级类型
        mmap.put("typeList", xzAssetTypeService.selectXzAssetTypeByStaAll());
        return "system/xzStationeryasset/add";
    }

    /**
     * 新增固定资产（保存）
     */
    @Log(title = "固定资产", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(XzAsstes xzAsstes) {
        Date date = new Date();
        xzAsstes.setCreateBy(ShiroUtils.getSysUser().getUserId().toString());
        xzAsstes.setCreateTime(date);
        // 新增时初始值:资产状态-未入库1、资产使用状态-无1、提交方式-保存1
        xzAsstes.setAssetsStatus("1");
        xzAsstes.setUseStatus("1");
        xzAsstes.setSubmitType("1");
        xzAsstes.setSort("1");
        String str = xzAsstesService.insertXzAsstes(xzAsstes);
        if (str.equals("1")) {
            return success();
        } else {
            return error("s/n数量与录入资产数量不统一，请确认后重新录入！");
        }

    }

    /**
     * 新增办公资产（保存）
     */
    @Log(title = "办公资产", businessType = BusinessType.INSERT)
    @PostMapping("/addSta")
    @ResponseBody
    public AjaxResult addStaSave(XzAsstes xzAsstes) {
        Date date = new Date();
        xzAsstes.setCreateBy(ShiroUtils.getSysUser().getUserId().toString());
        xzAsstes.setCreateTime(date);
        // 新增时初始值:资产状态-未入库1、资产使用状态-无1、提交方式-保存1
        xzAsstes.setAssetsStatus("1");
        xzAsstes.setUseStatus("1");
        xzAsstes.setSubmitType("1");
        xzAsstes.setSort("2");
        String str = xzAsstesService.insertXzAsstes(xzAsstes);
        return success(str);
    }

    @GetMapping("/assetsCheck/{code}")
    @ResponseBody
    public String assetsCheck(@PathVariable("code") String code) {

        if (code == null || "".equals(code)) {
            return "<span style=\"font-size:100px\">无法识别的编码</span> ";
        }
        XzCheck xzCheck = new XzCheck();
        xzCheck.setAsstesCode(code);
        xzCheck.setCreateTime(DateUtils.getNowDate());
        xzCheckService.insertXzCheck(xzCheck);

        return "<span style=\"font-size:100px\">盘点完成</span> ";

    }

    /**
     * 新增固定资产（提交）
     */
    @Log(title = "固定资产", businessType = BusinessType.INSERT)
    @PostMapping("/addSub")
    @ResponseBody
    public AjaxResult addSubSave(XzAsstes xzAsstes) {
        Date date = new Date();
        xzAsstes.setCreateBy(ShiroUtils.getSysUser().getUserId().toString());
        xzAsstes.setCreateTime(date);
        // 新增时初始值:资产状态-在库2、资产使用状态-无1、提交方式-提交2
        xzAsstes.setAssetsStatus("2");
        xzAsstes.setUseStatus("1");
        xzAsstes.setSubmitType("2");
        xzAsstes.setSort("1");
        String str = xzAsstesService.insertXzAsstes(xzAsstes);
        if(str.equals("1")){
            return AjaxResult.success("录入成功");
        }else {
            return AjaxResult.error(str);
        }
    }

    //办公资产二维码下载

    /**
     * 本地资源通用下载
     */
    @GetMapping("/qrCodeDownload/{id}")
    public void qrCodeDownload(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FileWriter fileWriter = null;
        xzAsstesService.createQrCode(id);
        File file = new File("qrCode/" + id + ".png");
        ServletOutputStream outputStream = null;
        try {

            // 下载名称
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + id + ".png");
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

    /**
     * 新增办公资产（提交）
     */
    @Log(title = "办公资产", businessType = BusinessType.INSERT)
    @PostMapping("/addStaSub")
    @ResponseBody
    public AjaxResult addStaSubSave(XzAsstes xzAsstes) {
        Date date = new Date();
        xzAsstes.setCreateBy(ShiroUtils.getSysUser().getUserId().toString());
        xzAsstes.setCreateTime(date);
        // 新增时初始值:资产状态-在库2、资产使用状态-无1、提交方式-提交2
        xzAsstes.setAssetsStatus("2");
        xzAsstes.setUseStatus("1");
        xzAsstes.setSubmitType("2");
        xzAsstes.setSort("1");
        String str = xzAsstesService.insertStaAsstes(xzAsstes);
        return success(str);
    }

    /**
     * 修改资产
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        XzAsstes xzAsstes = xzAsstesService.selectXzAsstesById(id);
        mmap.put("xzAsstes", xzAsstes);
        // 获取办公用品资产父级类型
        mmap.put("typeList", xzAssetTypeService.selectXzAssetTypeByAssAll());
        mmap.put("dataList", xzAssetDataService.selectXzAssetDataByParentId(xzAsstes.getAssetsType()));
        return prefix + "/edit";
    }

    /**
     * 修改资产
     */
    @GetMapping("/editPart/{id}")
    public String editPart(@PathVariable("id") Long id, ModelMap mmap) {
        XzAsstes xzAsstes = xzAsstesService.selectXzAsstesById(id);
        mmap.put("xzAsstes", xzAsstes);
        return prefix + "/editPart";
    }

    /**
     * 修改保存资产
     */
    @Log(title = "资产", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(XzAsstes xzAsstes) {
        Date date = new Date();
        xzAsstes.setUpdateBy(ShiroUtils.getSysUser().getUserId().toString());
        xzAsstes.setUpdateTime(date);
        xzAsstes.setSubmitType("1");
        return toAjax(xzAsstesService.updateXzAsstes(xzAsstes));
    }

    /**
     * 修改提交资产
     */
    @Log(title = "资产", businessType = BusinessType.UPDATE)
    @PostMapping("/editSub")
    @ResponseBody
    public AjaxResult editSubSave(XzAsstes xzAsstes) {
        XzAsstes asset = xzAsstesService.selectXzAsstesById(xzAsstes.getId());

        Date date = new Date();
        xzAsstes.setSubBy(ShiroUtils.getSysUser().getUserId().toString());
        xzAsstes.setSubTime(date);
        xzAsstes.setUpdateBy(ShiroUtils.getSysUser().getUserId().toString());
        xzAsstes.setUpdateTime(date);
        // 保存状态下修改提交:资产状态-在库2、资产使用状态-无1、提交方式-提交2
        if (("1").equals(asset.getAssetsStatus())) {
            xzAsstes.setAssetsStatus("2");
            xzAsstes.setUseStatus("1");
            xzAsstes.setSubmitType("2");
        }

        return toAjax(xzAsstesService.updateXzAsstes(xzAsstes));
    }

    /**
     * 资产详情
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap) {
        XzAsstes xzAsstes = xzAsstesService.selectXzAsstesById(id);
        mmap.put("xzAsstes", xzAsstes);
//        System.out.println(xzAsstes);
        return prefix + "/detail";
    }

    /**
     * 删除资产
     */
    @Log(title = "资产", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        System.out.println(ids);
        return toAjax(xzAsstesService.deleteXzAsstesByIds(ids));
    }

    /**
     * 查询资产子类型详细
     */
    @PostMapping(value = "/onSelect/{id}")
    @ResponseBody
    public List<XzAssetType> onSelect(@PathVariable("id") Long id) {
        List<XzAssetType> dataInfo = xzAssetDataService.selectXzAssetDataByParentId(id);
        return dataInfo;
    }

    /**
     * 一键提交
     */
    @Log(title = "资产", businessType = BusinessType.DELETE)
    @PostMapping("/toAllSubmit")
    @ResponseBody
    public AjaxResult toAllSubmit(String ids) {
        XzAsstes xzAsstes = new XzAsstes();
        // 新增时初始值:资产状态-在库2、资产使用状态-无1、提交方式-提交2
        xzAsstes.setAssetsStatus("2");
        xzAsstes.setUseStatus("1");
        xzAsstes.setSubmitType("2");
        xzAsstes.setSort("1");
        Date date = new Date();
        xzAsstes.setSubBy(ShiroUtils.getSysUser().getUserId().toString());
        xzAsstes.setSubTime(date);
        String str = xzAsstesService.updateXzAsstesBySub(ids, xzAsstes);
        if (str == "1") {
            return error("有重复提交数据，请查证后重新提交");
        } else {
            return success(str);
        }

    }

    /**
     * 单个提交
     */
    @Log(title = "资产", businessType = BusinessType.INSERT)
    @PostMapping("/toSubmit")
    @ResponseBody
    public AjaxResult toSubmit(Long id) {
        XzAsstes xzAsstes = xzAsstesService.selectXzAsstesById(id);
        // 新增时初始值:资产状态-在库2、资产使用状态-无1、提交方式-提交2
        xzAsstes.setAssetsStatus("2");
        xzAsstes.setUseStatus("1");
        xzAsstes.setSubmitType("2");
        xzAsstes.setSort("1");
        Date date = new Date();
        xzAsstes.setSubBy(ShiroUtils.getSysUser().getUserId().toString());
        xzAsstes.setSubTime(date);
        return toAjax(xzAsstesService.updateXzAsstes(xzAsstes));
    }

}