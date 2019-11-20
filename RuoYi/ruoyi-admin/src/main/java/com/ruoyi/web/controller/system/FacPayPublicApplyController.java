package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.finance.FacCommonlyApply;
import com.ruoyi.system.domain.finance.FacPayPublicApply;
import com.ruoyi.system.domain.finance.FacPayPublicDetailed;
import com.ruoyi.system.domain.finance.FacUserApproval;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.IFacCommonlyApplyService;
import com.ruoyi.system.service.finance.IFacNumberTableService;
import com.ruoyi.system.service.finance.IFacPayPublicApplyService;
import com.ruoyi.system.service.finance.IFacUserApprovalService;

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
    @Autowired
    private IFacUserApprovalService facUserApprovalService;
    @Autowired
    private IFacCommonlyApplyService facCommonlyApplyService;
    @Autowired
    private IFacNumberTableService facNumberTableService;

    //@RequiresPermissions("system:facPayPublicApply:view")
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
        if(ShiroUtils.getUserId()==1L||ShiroUtils.getUserId()==154L||ShiroUtils.getUserId()==110L){
            List<FacPayPublicApply> lists = facPayPublicApplyService
                    .selectFacPayPublicApplyList(facPayPublicApply);
            for (FacPayPublicApply v : lists) {
                v.setUserName(
                        sysUserService.selectUserById(v.getUser()).getUserName());

                FacUserApproval name = facUserApprovalService
                        .selectApproval(v.getNum(), v.getUser());
                if (name != null) {
                    if(facUserApprovalService.approverName(v.getNum())!=null){
                        v.setAllName(facUserApprovalService.approverName(v.getNum()));
                    }
                    if (name.getApproverId() != null) {
                        v.setApprover(
                                sysUserService.selectUserById(name.getApproverId())
                                        .getUserName());
                    }
                    if (name.getApprovalState().equals("3") && name.getApprovalLevel().equals(1)) {
                        v.setApprovalStatus("4");
                    } else {
                        v.setApprovalStatus(name.getApprovalState());
                    }

                    if (v.getUser() == 103
                            && v.getUser() == 101) {
                        v.setApprovalStatus("1");
                    }
                } else {
                    v.setApprover("--");
                    v.setApprovalStatus("--");
                }

            }
            return getDataTable(lists);
        }
        facPayPublicApply.setUser(ShiroUtils.getUserId());
        List<FacPayPublicApply> list = facPayPublicApplyService
                .selectFacPayPublicApplyList(facPayPublicApply);
        for (FacPayPublicApply v : list) {
            v.setUserName(
                    sysUserService.selectUserById(v.getUser()).getUserName());

            FacUserApproval name = facUserApprovalService
                    .selectApproval(v.getNum(), v.getUser());
            if (name != null) {
                if(facUserApprovalService.approverName(v.getNum())!=null){
                    v.setAllName(facUserApprovalService.approverName(v.getNum()));
                }
                if (name.getApproverId() != null) {
                    v.setApprover(
                            sysUserService.selectUserById(name.getApproverId())
                                    .getUserName());
                }
                v.setApprovalStatus(name.getApprovalState());
                if (ShiroUtils.getUserId() == 103
                        && ShiroUtils.getUserId() == 101) {
                    v.setApprovalStatus("1");
                }
            } else {
                v.setApprover("--");
                v.setApprovalStatus("--");
            }

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
        mmap.put("num", facNumberTableService.getNum("DG", ShiroUtils.getDateId()));
        mmap.put("data", new FacPayPublicApply());
        return prefix + "/add";
    }

    /**
     * 新增对公常显
     */
    @GetMapping("/addCo")
    public String add() {
        return prefix + "/addCo";
    }


    @GetMapping("/cyUser")
    public String cyUser() {
        return prefix + "/cyUser";
    }

    /**
     * 修改对公常显
     */
    @GetMapping("/addCuer/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {

        mmap.put("num", facNumberTableService.getNum("DG", ShiroUtils.getDateId()));
        FacCommonlyApply facCommonlyApply = facCommonlyApplyService.selectFacCommonlyApplyById(id);
        mmap.put("facCommonlyApply", facCommonlyApply);
        return prefix + "/addCuer";
    }

    @GetMapping("/addSave")
    public String addSave(String id, ModelMap map) {
        map.put("id", id);
        return prefix + "/addSave";
    }


    /**
     * 新增保存对公常显
     */

    @Log(title = "对公常显", businessType = BusinessType.INSERT)
    @PostMapping("/addCo")
    @ResponseBody
    public AjaxResult addSave(FacCommonlyApply facCommonlyApply) {
        return toAjax(facCommonlyApplyService.insertFacCommonlyApply(facCommonlyApply));
    }

    /**
     * 新增保存对公申请
     */
    @Log(title = "对公申请", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FacPayPublicApply facPayPublicApply) {
        facPayPublicApply.setUser(ShiroUtils.getUserId());
        facPayPublicApply.setCreateTime(new Date());
        if (facPayPublicApply.getId() == null) {
            // 直接添加
//            facPayPublicApply.setNum(facNumberTableService.getNum("DG", ShiroUtils.getDateId()));
            facPayPublicApply.setUser(ShiroUtils.getUserId());
        } else {
            // 更新
            facPayPublicApplyService.deleteFacPayPublicApplyByIds(facPayPublicApply.getId() + "");
        }
        facUserApprovalService.createPublicPayApprovalProcess(facPayPublicApply.getNum(),facPayPublicApply.getAmount(),facPayPublicApply.getName(),ShiroUtils.getUserId());
        facPayPublicApply.setStatus("1");
        if(facPayPublicApply.getIsKeep()!=null){
            if(facPayPublicApply.getIsKeep().equals("1")){
                FacCommonlyApply facCommonlyApply=new FacCommonlyApply();
                facCommonlyApply.setNum(ShiroUtils.getUserId()+"");
                facCommonlyApply.setName(facPayPublicApply.getPayee());
                facCommonlyApply.setNumber(facPayPublicApply.getPayeeAccount());
                facCommonlyApply.setUserName(facPayPublicApply.getPayeeBank());
                facCommonlyApplyService.insertFacCommonlyApply(facCommonlyApply);
            }
        }

        facPayPublicApplyService.insertFacPayPublicApply(facPayPublicApply);
        return AjaxResult.success("操作成功");
    }

    /**
     * 修改对公申请
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        FacPayPublicApply facPayPublicApply = facPayPublicApplyService.selectFacPayPublicApplyById(id);
        mmap.put("data", facPayPublicApply);
        mmap.put("num", facPayPublicApply.getNum());
        return prefix + "/add";
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
			FacPayPublicApply facCostApplys = facPayPublicApplyService.selectFacPayPublicApplyById(facCostApply.getId());
			List<FacPayPublicApply> facReimburseApplies = new ArrayList<>();
			facCostApplys.setUserName(sysUserService.selectUserById(facCostApplys.getUser()).getUserName());
			facReimburseApplies.add(facCostApplys);
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
        List<FacPayPublicApply> facReimburseApplies = facPayPublicApplyService.selectFacPayPublicApplyList(facPayPublicApply);
        map.put("rid", id);
        map.put("num", facReimburseApplies.get(0).getNum());
        map.put("status", facReimburseApplies.get(0).getStatus());
        return prefix + "/public";
    }

    /**
     * 查看详情
     */
    @GetMapping("/dgDetail/{id}")
    public String dgDetail(@PathVariable("id") Integer id, ModelMap map) {
        FacPayPublicApply facPayPublicApply = new FacPayPublicApply();
        facPayPublicApply.setId(id.intValue());
        List<FacPayPublicApply> facReimburseApplies = facPayPublicApplyService
                .selectFacPayPublicApplyList(facPayPublicApply);
        map.put("rid", id);
        map.put("num", facReimburseApplies.get(0).getNum());
        map.put("status", facReimburseApplies.get(0).getStatus());
        return prefix + "/dgDetail";
    }


    /**
     * 查看明细
     */
    @PostMapping("/dgtail")
    @ResponseBody
    public TableDataInfo dgtail(String num) {

        startPage();
        List<FacPayPublicDetailed> facPayPublicDetailed = facPayPublicApplyService.dgtail(num);
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
        return toAjax(facPayPublicApplyService.insertFacPayPublicDetailed(facPayPublicDetailed));
    }

    /**
     * 新增保存
     *
     * @throws Exception
     */
    @Log(title = "借款申请", businessType = BusinessType.INSERT)
    @PostMapping("/addSove")
    @ResponseBody
    public AjaxResult addSove(FacPayPublicApply facPayPublicApply) throws Exception {
		facPayPublicApply.setUser(ShiroUtils.getUserId());
		facPayPublicApply.setCreateTime(new Date());
		if (facPayPublicApply.getId() == null) {
		} else {
			facPayPublicApplyService.deleteFacPayPublicApplyByIds(facPayPublicApply.getId().toString());
		}
        if(facPayPublicApply.getIsKeep()!=null){
            if(facPayPublicApply.getIsKeep().equals("1")){
                FacCommonlyApply facCommonlyApply=new FacCommonlyApply();
                facCommonlyApply.setNum(ShiroUtils.getUserId()+"");
                facCommonlyApply.setName(facPayPublicApply.getPayee());
                facCommonlyApply.setNumber(facPayPublicApply.getPayeeAccount());
                facCommonlyApply.setUserName(facPayPublicApply.getPayeeBank());
                facCommonlyApplyService.insertFacCommonlyApply(facCommonlyApply);
            }
        }

		facPayPublicApplyService.insertApply(facPayPublicApply);
		return AjaxResult.success("操作成功");

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
