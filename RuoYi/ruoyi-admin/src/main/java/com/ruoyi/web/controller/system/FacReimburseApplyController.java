package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.ruoyi.common.utils.IdWorker;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.finance.FacReimburseApply;
import com.ruoyi.system.domain.finance.ReiAdiApply;
import com.ruoyi.system.domain.finance.ReiHospitalityApply;
import com.ruoyi.system.domain.finance.ReiTrafficApply;
import com.ruoyi.system.service.finance.IFacReimburseApplyService;


/**
 * 报销 信息操作处理
 *
 * @author ruoyi
 * @date 2019-07-31
 */
@Controller
@RequestMapping("/system/facReimburseApply")
public class FacReimburseApplyController extends BaseController {
    private String prefix = "system/facReimburseApply";

    @Autowired
    private IFacReimburseApplyService facReimburseApplyService;

    @RequiresPermissions("system:facReimburseApply:view")
    @GetMapping()
    public String facReimburseApply() {
        return prefix + "/facReimburseApply";
    } 
    /**
     * 查询报销列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FacReimburseApply facReimburseApply) {
        startPage();
        List<FacReimburseApply> list = facReimburseApplyService.selectFacReimburseApplyList(facReimburseApply);
        return getDataTable(list);
    }  
    /**
     * 导出报销列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FacReimburseApply facReimburseApply) {
        List<FacReimburseApply> list = facReimburseApplyService.selectFacReimburseApplyList(facReimburseApply);
        ExcelUtil<FacReimburseApply> util = new ExcelUtil<FacReimburseApply>(FacReimburseApply.class);
        return util.exportExcel(list, "facReimburseApply");
    } 
    /**
     *查看详情
     */
    @RequiresPermissions("system:facReimburseApply:detail")
    @GetMapping("/detail")
    public String detail(@RequestParam("id") String id, ModelMap map) {
        FacReimburseApply facReimburseApply = new FacReimburseApply();
        facReimburseApply.setId(id);
        List<FacReimburseApply> facReimburseApplies = facReimburseApplyService.selectFacReimburseApplyList(facReimburseApply);
        map.put("rid", id);
        map.put("num", facReimburseApplies.get(0).getNum());
        map.put("status", facReimburseApplies.get(0).getStatus());
        return prefix + "/detail";
    }  
    /**
     * 新增报销
     */
    @GetMapping("/add")
    public String add(ModelMap mmp) {
        IdWorker idWorker = new IdWorker(0, 1);
        mmp.put("num", "BX" + idWorker.nextId());
        mmp.put("msg","1");
        mmp.put("userId",ShiroUtils.getUserId());
        return prefix + "/reimbuseDetail";
    } 
    /**
     * 新增保存报销
     */
    @Log(title = "报销", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FacReimburseApply facReimburseApply) {
        facReimburseApply.setLoanUser(ShiroUtils.getUserId());
        return facReimburseApplyService.insertFacReimburseApply(facReimburseApply);
    } 
    /**
     *新增招待费用 
     * */ 
    @Log(title = "招待费报销", businessType = BusinessType.INSERT)
    @PostMapping("/addAll")
    @ResponseBody
    public AjaxResult addAllSave(FacReimburseApply facReimburseApply) {
        facReimburseApply.setLoanUser(ShiroUtils.getUserId());
        return facReimburseApplyService.insertFacReimburseApply(facReimburseApply);
    }
    /**
     * 招待费用报销
     */
    @GetMapping("/addAll")
    public String addAll(ModelMap mmp) {
    	IdWorker idWorker = new IdWorker(0, 1);
        mmp.put("num", "BX" + idWorker.nextId());
        mmp.put("msg","1");
        return prefix + "/addAll";
    } 

    /**
     *
     * 其他报销申请
 	* */
    @GetMapping("/otherDetail")
    public String otherDetails(){
		return prefix+"/otherDetail";
    	
    } 
    
    /**
     *新增其他报销
     * */ 
    @Log(title = "招待费报销", businessType = BusinessType.INSERT)
    @PostMapping("/Arbitrarily")
    @ResponseBody
    public AjaxResult Save(FacReimburseApply facReimburseApply) {
        facReimburseApply.setLoanUser(ShiroUtils.getUserId());
        return facReimburseApplyService.insertFacReimburseApply(facReimburseApply);
    }  
    
    /**
     * 交通费用报销
     */
    @GetMapping("/tranDetail")
    public String tranDetail() {
        return prefix + "/tranDetail";
    }
 
    
    /**
     *新增交通费用
     * */ 
    @Log(title = "交通费报销", businessType = BusinessType.INSERT)
    @PostMapping("/tranDetail")
    @ResponseBody
    public AjaxResult tranDetailSave(ReiTrafficApply reiTrafficApply) { 
    	List<ReiTrafficApply> list=new ArrayList<>();
    	list.add(reiTrafficApply); 
    	FacReimburseApply facReimburseApply= new FacReimburseApply();
        facReimburseApply.setLoanUser(ShiroUtils.getUserId()); 
        facReimburseApply.setTrafficReiApplyList(list);  
        return facReimburseApplyService.insertFacReimburseApply(facReimburseApply);
    } 
     
    /**
     * 查看报销详情
     */
    @PostMapping("/query")
    @ResponseBody
    public TableDataInfo detail1(String num) {
        startPage();
        FacReimburseApply facReimburseApply = facReimburseApplyService.deatil(num);
        if (facReimburseApply!=null){
            List<FacReimburseApply> facReimburseApplies = new ArrayList<>();
            facReimburseApplies.add(facReimburseApply);
            return getDataTable(facReimburseApplies);
        }else {
            List<String> a = new ArrayList<>();
            return getDataTable(a);
        }
    }

    /**
     * 查看招待费报销申请详情
     */
    @PostMapping("/hospDetail")
    @ResponseBody
    public TableDataInfo hospDtail(String num) {
        startPage();
        FacReimburseApply facReimburseApply = facReimburseApplyService.deatil(num);
        if (facReimburseApply!=null){
            List<ReiHospitalityApply> reiAdiApplies = facReimburseApply.getHospitalityApplies();

            if (reiAdiApplies != null && reiAdiApplies.size() > 0) {
                return getDataTable(reiAdiApplies);
            } else {
                List<String> a = new ArrayList<>();
                return getDataTable(a);
            }
        }else {
            List<String> a = new ArrayList<>();
            return getDataTable(a);
        }
    }  

    /**
     * 查看交通费报销申请详情
     */
    @PostMapping("/addTraDetail")
    @ResponseBody
    public TableDataInfo addTraDetail(String num) {
        startPage();
        FacReimburseApply facReimburseApply = facReimburseApplyService.deatil(num);
        if (facReimburseApply != null) {
            List<ReiTrafficApply> reiAdiApplies = facReimburseApply.getTrafficReiApplyList();
            if (reiAdiApplies != null && reiAdiApplies.size() > 0) {
                return getDataTable(reiAdiApplies);
            } else {
                List<String> a = new ArrayList<>();
                return getDataTable(a);
            }
        } else {
            List<String> a = new ArrayList<>();
            return getDataTable(a);
        }
    }  
    
    /**
     * 查看其他报销费申请详情
     */

    @PostMapping("/otherDetail")
    @ResponseBody
    public TableDataInfo otherDetail(String num) {
        startPage();
        FacReimburseApply facReimburseApply = facReimburseApplyService.deatil(num);
        if (facReimburseApply != null) {
            List<ReiAdiApply> reiAdiApplies = facReimburseApply.getOtherReiAdiApplies();
            if (reiAdiApplies != null && reiAdiApplies.size() > 0) {
                return getDataTable(reiAdiApplies);
            } else {
                List<String> a = new ArrayList<>();
                return getDataTable(a);
            }
        }else {
            List<String> a = new ArrayList<>();
            return getDataTable(a);
        }
    }

    /**
     * 修改保存报销
     */
    @RequiresPermissions("system:facReimburseApply:detail")
    @Log(title = "报销", businessType = BusinessType.OTHER)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FacReimburseApply facReimburseApply) {
        return toAjax(facReimburseApplyService.updateFacReimburseApply(facReimburseApply));
    }
  
  /**
   * 修改报销
   */
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") String id, ModelMap mmap) {
      FacReimburseApply facReimburseApply = facReimburseApplyService.selectFacReimburseApplyById(id);
      mmap.put("fac", facReimburseApply);
      return prefix + "/edit";
  } 
    
    /**
     * 删除报销
     */
    @Log(title = "报销", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
    	  try
          {
             return toAjax(facReimburseApplyService.deleteFacReimburseApplyByIds(ids));
          }
          catch (Exception e)
          {
              return error(e.getMessage());
          }
    } 
}
