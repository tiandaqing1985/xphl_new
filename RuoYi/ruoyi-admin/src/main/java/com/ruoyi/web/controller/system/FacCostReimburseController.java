package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.system.domain.finance.*;
import com.ruoyi.system.mapper.finance.FacCostDetailApplyMapper;
import com.ruoyi.system.service.finance.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
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
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 差旅报销 信息操作处理
 *
 * @author ruoyi
 * @date 2019-11-12
 */
@Controller
@RequestMapping("/system/facCostReimburse")
public class FacCostReimburseController extends BaseController {
    private String prefix = "system/facCostReimburse";

    @Autowired
    private IFacCostReimburseService facCostReimburseService;
    @Autowired
    private IFacCostDetailReimburseService facCostDetailReimburseService;
    @Autowired
    private IFacCostPutupReimburseService facCostPutupReimburseService;
    @Autowired
    private IFacCostApplyService facCostApplyService;
    @Autowired
    private IFacCostPutupApplyService facCostPutupApplyService;
    @Autowired
    private FacCostDetailApplyMapper facCostDetailApplyMapper;

    @RequiresPermissions("system:facCostReimburse:view")
    @GetMapping()
    public String facCostReimburse() {
        return prefix + "/facCostReimburse";
    }

    /**
     * 查询差旅报销列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FacCostReimburse facCostReimburse) {
        startPage();
        List<FacCostReimburse> list = facCostReimburseService.selectFacCostReimburseList(facCostReimburse);
        return getDataTable(list);
    }

    /**
     * 差旅报销
     *
     * @throws Exception
     */
    @GetMapping("/baoxiaoEdit")
    public String Baoxiao(String id, ModelMap mmap) {
        FacCostApply facCostApply = facCostApplyService.selectFacCostApplyById(Long.valueOf(id).longValue());
        //向差旅报销表里面插入报销信息
        FacCostReimburse facCostReimburse = new FacCostReimburse();
        facCostReimburse.setNum(facCostApply.getNum());
        List<FacCostReimburse> facCostReimburses = facCostReimburseService.selectFacCostReimburseList(facCostReimburse);
        if (facCostReimburses.size() == 0) {
            //插入主表信息
            BeanUtils.copyProperties(facCostApply, facCostReimburse);
			facCostReimburse.setId(null);
            facCostReimburseService.insertFacCostReimburse(facCostReimburse);
            //查询行程报销
            FacCostDetailApply facCostDetailApply = new FacCostDetailApply();
            facCostDetailApply.setNum(facCostApply.getNum());
            List<FacCostDetailApply> facCostDetailApplies = facCostDetailApplyMapper.selectFacCostDetailApplyList(facCostDetailApply);
            FacCostDetailReimburse facCostDetailReimburse = null;
            for (FacCostDetailApply costDetailApply : facCostDetailApplies) {
                facCostDetailReimburse = new FacCostDetailReimburse();
                BeanUtils.copyProperties(costDetailApply, facCostDetailReimburse);
				facCostDetailReimburse.setId(null);
                facCostDetailReimburseService.insertFacCostDetailReimburse(facCostDetailReimburse);
            }
            //查询住宿
            FacCostPutupApply facCostPutupApply = new FacCostPutupApply();
            facCostPutupApply.setNum(facCostApply.getNum());
            List<FacCostPutupApply> facCostPutupApplies = facCostPutupApplyService.selectFacCostPutupApplyList(facCostPutupApply);
            FacCostPutupReimburse facCostPutupReimburse = null;
            for (FacCostPutupApply costPutupApply : facCostPutupApplies) {
				facCostPutupReimburse = new FacCostPutupReimburse();
				BeanUtils.copyProperties(costPutupApply,facCostPutupReimburse);
				facCostPutupReimburse.setId(null);
				facCostPutupReimburseService.insertFacCostPutupReimburse(facCostPutupReimburse);
            }
        }else{
            facCostReimburse = facCostReimburses.get(0);
        }
        mmap.put("facCostApply", facCostReimburse);
        return "system/facCostApply/baoxiaoEdit";
    }

    /**
     * 导出差旅报销列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacCostReimburse facCostReimburse) {
        List<FacCostReimburse> list = facCostReimburseService.selectFacCostReimburseList(facCostReimburse);
        ExcelUtil<FacCostReimburse> util = new ExcelUtil<FacCostReimburse>(FacCostReimburse.class);
        return util.exportExcel(list, "facCostReimburse");
    }

    /**
     * 新增差旅报销
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存差旅报销
     */
    @Log(title = "差旅报销", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FacCostReimburse facCostReimburse) {
        return toAjax(facCostReimburseService.insertFacCostReimburse(facCostReimburse));
    }

    /**
     * 修改差旅报销
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        FacCostReimburse facCostReimburse = facCostReimburseService.selectFacCostReimburseById(id);
        mmap.put("facCostReimburse", facCostReimburse);
        return prefix + "/edit";
    }

    /**
     * 修改保存差旅报销
     */
    @Log(title = "差旅报销", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FacCostReimburse facCostReimburse) {
        //查询总金额
        Double sum1 = facCostDetailReimburseService.selectAmountByNum(facCostReimburse.getNum());
        Double sum2 = facCostPutupReimburseService.selectAmountByNum(facCostReimburse.getNum());
        facCostReimburse.setMoneyEs(sum1+sum2);
        return toAjax(facCostReimburseService.updateFacCostReimburse(facCostReimburse));
    }

    /**
     * 删除差旅报销
     */
    @Log(title = "差旅报销", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(facCostReimburseService.deleteFacCostReimburseByIds(ids));
    }

}
