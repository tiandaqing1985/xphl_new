package com.ruoyi.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.OaOut;
import com.ruoyi.system.domain.OutApproval;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.IOaOutService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 外出报备 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-08-01
 */
@Controller
@RequestMapping("/system/oaOut")
public class OaOutController extends BaseController
{
    private String prefix = "system/oaOut";
	
	@Autowired
	private IOaOutService oaOutService;
	@Autowired
	private ISysRoleService iSysRoleService;
	@Autowired
	private ISysUserService iSysUserService;
	
	@RequiresPermissions("system:oaOut:view")
	@GetMapping()
	public String oaOut()
	{
	    return prefix + "/oaOut";
	}
	
	@GetMapping("/toUnApprovalList")
	public String toUnApprovalList()
	{
	    return prefix + "/unApprovalList";
	}
	
	@GetMapping("/approvalModer")
	public String approvalModer(Long outId,Model m)
	{
		m.addAttribute("outId", outId);
	    return prefix + "/approvalModer";
	}
	/**
	 * 查询我的外出报备列表
	 */
	@PostMapping("/myOutApprovalList")
	@ResponseBody
	public TableDataInfo myOutApprovalList(OaOut oaOut)
	{
		startPage();
		oaOut.setUserId(ShiroUtils.getUserId());
//		oaOut.setApprovalId(ShiroUtils.getUserId());
		oaOut.setApprovalSight("1");
        List<OutApproval> list = oaOutService.selectMyOutApprovalList(oaOut);
		return getDataTable(list);
	}
	
	/**
	 * 查询待审批列表
	 */
	@PostMapping("/unApprovalList")
	@ResponseBody
	public TableDataInfo unApprovalList(OaOut oaOut)
	{
		startPage();
//		oaOut.setUserId(ShiroUtils.getUserId());
		oaOut.setApprovalId(ShiroUtils.getUserId());
		oaOut.setApprovalState("3");//审批状态（1同意，2驳回 ，3未操作）
		oaOut.setApprovalSight("1");
		oaOut.setState("1");
        List<OutApproval> list = oaOutService.selectOutApprovalList(oaOut);
		return getDataTable(list);
	}
	
	@GetMapping("/myApprovalList")
	public String myApprovalList()
	{
	    return prefix + "/myApprovalList";
	}
	
	/**
	 * 查询我的审批列表
	 */
	@PostMapping("/myApprovalList")
	@ResponseBody
	public TableDataInfo myApprovalList(OaOut oaOut)
	{
		startPage();
//		oaOut.setUserId(ShiroUtils.getUserId());
		oaOut.setApprovalId(ShiroUtils.getUserId());
		oaOut.setApprovalState("4");//审批状态（1同意，2驳回 ，3未操作）
        List<OutApproval> list = oaOutService.selectOutApprovalList(oaOut);
		return getDataTable(list);
	}
	
	@GetMapping("/allList")
	public String allList()
	{
	    return prefix + "/allList";
	}
	
	/** 
	 * 查询申请记录
	 * */
	@PostMapping("/allList")
	@ResponseBody
	public TableDataInfo allList(OaOut oaOut)
	{
		startPage();
//		oaOut.setUserId(ShiroUtils.getUserId());
		oaOut.setApprovalId(ShiroUtils.getUserId());
		oaOut.setRemark("查询申请记录");
		oaOut.setApprovalSight("1");
        List<OutApproval> list = oaOutService.selectOutApprovalList(oaOut);
		return getDataTable(list);
	}
	
	
	/**
	 * 查看某个申请
	 */
	@GetMapping("/toView/{outId}")
	public String toCheck(@PathVariable("outId") Long outId,  Model m)
	{
		boolean showFlag = false;
		SysUser user = iSysUserService.selectUserById(ShiroUtils.getUserId());
		SysUser user2 = new SysUser();
		user2.setRoleId(3L);
		user2.setArea(user.getArea());
		Long personnelId = iSysRoleService.selectUserIdByRoleId(user2);//查询人事id
		if(personnelId.equals(ShiroUtils.getUserId())){
			showFlag = true;
		}
		OaOut out = oaOutService.selectOaOutById(outId);
		m.addAttribute("out", out);
		m.addAttribute("showFlag", showFlag);
	    return prefix + "/toView";
	}
	
	/**
	 * 同意申请
	 */
	@PostMapping("/agree")
	@ResponseBody
	public AjaxResult agree(Long outId)
	{	
		OaOut out = new OaOut();
		out.setOutId(outId);
		out.setApprovalState("1");//1同意 2驳回 3未操作
		out.setApprovalId(ShiroUtils.getUserId());//审批人id
		oaOutService.updateOaOut(out,"");
		return toAjax(1);
	}
	

	/**
	 * 驳回申请
	 */
	@PostMapping("/reject")
	@ResponseBody
	public AjaxResult reject(Long outId, String remark)
	{	
		OaOut out = new OaOut();
		out.setOutId(outId);
		out.setApprovalState("2");//1同意 2驳回 3未操作
		out.setApprovalId(ShiroUtils.getUserId());//审批人id
		oaOutService.updateOaOut(out,remark);
		return toAjax(1);
	}
	
	/**
	 * 导出外出报备列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OaOut oaOut)
    {
    	List<OaOut> list = oaOutService.selectOaOutList(oaOut);
        ExcelUtil<OaOut> util = new ExcelUtil<OaOut>(OaOut.class);
        return util.exportExcel(list, "oaOut");
    }
	
	/**
	 * 新增外出报备
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存外出报备
	 */
	@Log(title = "外出报备", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(OaOut oaOut)
	{		
		oaOut.setUserId(ShiroUtils.getUserId());
		return toAjax(oaOutService.insertOaOut(oaOut));
	}

	/**
	 * 修改外出报备
	 */
	@GetMapping("/edit/{outId}")
	public String edit(@PathVariable("outId") Long outId, ModelMap mmap)
	{
		OaOut oaOut = oaOutService.selectOaOutById(outId);
		mmap.put("oaOut", oaOut);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存外出报备申请
	 */
	@Log(title = "修改外出报备申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(OaOut out)
	{	
		out.setUserId(ShiroUtils.getUserId());
		return toAjax(oaOutService.updateOaOut(out));
	}
	
	/**
	 * 删除外出报备
	 */
	@Log(title = "外出报备", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(oaOutService.deleteOaOutByIds(ids));
	}

	/**
	 * 撤回申请
	 */
	@PostMapping("/takeBack")
	@ResponseBody
	public AjaxResult takeBack(Long outId)
	{	
		return toAjax(oaOutService.takeBack(outId));
	}
	
	/**
	 * 验证是否审批操作: 0 未审批 	1 已审批
	 */
    @PostMapping("/ifTakeback")
    @ResponseBody
    public String ifTakeback(Long outId)
    {
    	return oaOutService.ifTakeback(outId);
    }
    
    /**
   	 * 验证选择的时间是否已经提交了：0 未申请  1已申请
   	 */
   @PostMapping("/ifRepeat")
   @ResponseBody
   public String ifRepeat(OaOut oaOut){
	  oaOut.setUserId(ShiroUtils.getUserId());
      return oaOutService.ifRepeat(oaOut);
   }
}
