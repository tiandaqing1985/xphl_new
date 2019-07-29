package com.ruoyi.web.controller.system;

import java.util.List;
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
import com.ruoyi.system.domain.OaDingdingUser;
import com.ruoyi.system.service.IOaDingdingUserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 钉钉用户 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-26
 */
@Controller
@RequestMapping("/system/oaDingdingUser")
public class OaDingdingUserController extends BaseController
{
    private String prefix = "system/oaDingdingUser";
	
	@Autowired
	private IOaDingdingUserService oaDingdingUserService;
	
	@RequiresPermissions("system:oaDingdingUser:view")
	@GetMapping()
	public String oaDingdingUser()
	{
	    return prefix + "/oaDingdingUser";
	}
	
	/**
	 * 查询钉钉用户列表
	 */
	@RequiresPermissions("system:oaDingdingUser:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(OaDingdingUser oaDingdingUser)
	{
		startPage();
        List<OaDingdingUser> list = oaDingdingUserService.selectOaDingdingUserList(oaDingdingUser);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出钉钉用户列表
	 */
	@RequiresPermissions("system:oaDingdingUser:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OaDingdingUser oaDingdingUser)
    {
    	List<OaDingdingUser> list = oaDingdingUserService.selectOaDingdingUserList(oaDingdingUser);
        ExcelUtil<OaDingdingUser> util = new ExcelUtil<OaDingdingUser>(OaDingdingUser.class);
        return util.exportExcel(list, "oaDingdingUser");
    }
	
	/**
	 * 新增钉钉用户
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存钉钉用户
	 */
	@RequiresPermissions("system:oaDingdingUser:add")
	@Log(title = "钉钉用户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(OaDingdingUser oaDingdingUser)
	{		
		return toAjax(oaDingdingUserService.insertOaDingdingUser(oaDingdingUser));
	}

	/**
	 * 修改钉钉用户
	 */
	@GetMapping("/edit/{userId}")
	public String edit(@PathVariable("userId") Integer userId, ModelMap mmap)
	{
		OaDingdingUser oaDingdingUser = oaDingdingUserService.selectOaDingdingUserById(userId);
		mmap.put("oaDingdingUser", oaDingdingUser);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存钉钉用户
	 */
	@RequiresPermissions("system:oaDingdingUser:edit")
	@Log(title = "钉钉用户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(OaDingdingUser oaDingdingUser)
	{		
		return toAjax(oaDingdingUserService.updateOaDingdingUser(oaDingdingUser));
	}
	
	/**
	 * 删除钉钉用户
	 */
	@RequiresPermissions("system:oaDingdingUser:remove")
	@Log(title = "钉钉用户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(oaDingdingUserService.deleteOaDingdingUserByIds(ids));
	}
	
}
