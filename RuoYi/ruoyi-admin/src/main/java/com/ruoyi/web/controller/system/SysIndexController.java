package com.ruoyi.web.controller.system;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.QueryConditions;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.system.service.IUserApprovalService;
import com.ruoyi.system.domain.finance.FacUserApproval;
import com.ruoyi.system.service.finance.IFacUserApprovalService;

/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;  
	@Autowired
	private IUserApprovalService userApprovalService;
	@Autowired
	private IFacUserApprovalService facUserApprovalService;
	
    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", Global.getCopyrightYear());
        
        
        if(user.getUserId()==1){
        	mmap.put("demoEnabled", true);
        }else{
        	mmap.put("demoEnabled", false);
        }
        
        
        
        
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {   	
    	//待审批总数
    	QueryConditions queryConditions = new QueryConditions();
    	queryConditions.setApproverId(ShiroUtils.getUserId());//审批人是登录的用户
		queryConditions.setApprovalState("3");//审批意见是，未操作
		queryConditions.setApprovalSight("1");//审批为可见状态
		queryConditions.setApplyState("1");//申请状态 待审批
		
		List<QueryConditions> list = userApprovalService.selectQueryConditionsList(queryConditions);
    	mmap.put("unApprovalNum", list.size());


		FacUserApproval facUserApproval=new FacUserApproval();
		facUserApproval.setApprovalState("3");
		facUserApproval.setApproverId(ShiroUtils.getUserId());
		facUserApproval.setApprovalSight("1");
		List<FacUserApproval> lists = facUserApprovalService.selectFacUserApprovalList(facUserApproval);
		mmap.put("facUserNum", lists.size());



		//请假总数
    	queryConditions.setApplyType("1");
    	list = userApprovalService.selectQueryConditionsList(queryConditions);
    	mmap.put("holidayNum", list.size());
    	
    	//加班总数
    	queryConditions.setApplyType("2");
    	list = userApprovalService.selectQueryConditionsList(queryConditions);
    	mmap.put("overtimeNum", list.size());
    	
    	//销假总数
    	queryConditions.setApplyType("3");
    	list = userApprovalService.selectQueryConditionsList(queryConditions);
    	mmap.put("cancelNum", list.size());
    	
    	//外出总数
     	queryConditions.setApplyType("4");
    	list = userApprovalService.selectQueryConditionsList(queryConditions);
    	mmap.put("outNum", list.size());
    	
    	if(ShiroUtils.getUserId()==103 || ShiroUtils.getUserId()==1){
    		mmap.put("admin", true);
    	}
    	
        mmap.put("version", Global.getVersion());
        return "main";
    }
}
