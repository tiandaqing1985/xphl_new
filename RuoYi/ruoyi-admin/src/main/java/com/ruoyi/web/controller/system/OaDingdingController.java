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
import com.ruoyi.system.domain.Dingding;
import com.ruoyi.system.domain.OaDingding;
import com.ruoyi.system.service.IOaDingdingService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 钉钉考勤数据 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-26
 */
@Controller
@RequestMapping("/system/oaDingding")
public class OaDingdingController extends BaseController
{
    private String prefix = "system/oaDingding";
	
	@Autowired
	private IOaDingdingService oaDingdingService;
	
	@RequiresPermissions("system:oaDingding:view")
	@GetMapping()
	public String oaDingding()
	{
	    return prefix + "/oaDingding";
	}
	
	/**
	 * 查询钉钉考勤数据列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Dingding ding)
	{
		startPage();
		ding.setUserId(ShiroUtils.getUserId());
        List<Dingding> list = oaDingdingService.selectOaDingdingList(ding);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出钉钉考勤数据列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Dingding oaDingding)
    {
//    	List<Dingding> list = oaDingdingService.selectOaDingdingList(oaDingding);
        return null;
    }
	
	/**
	 * 新增钉钉考勤数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存钉钉考勤数据
	 */
	@Log(title = "钉钉考勤数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(OaDingding oaDingding)
	{		
		return toAjax(oaDingdingService.insertOaDingding(oaDingding));
	}
	
	
	/**
	 * 修改钉钉考勤数据
	 */
	@GetMapping("/edit/{userId}")
	public String edit(@PathVariable("userId") String userId, ModelMap mmap)
	{
		OaDingding oaDingding = oaDingdingService.selectOaDingdingById(userId);
		mmap.put("oaDingding", oaDingding);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存钉钉考勤数据
	 */
	@Log(title = "钉钉考勤数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(OaDingding oaDingding)
	{		
		return toAjax(oaDingdingService.updateOaDingding(oaDingding));
	}
	
	/**
	 * 删除钉钉考勤数据
	 */
	@Log(title = "钉钉考勤数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(oaDingdingService.deleteOaDingdingByIds(ids));
	}
	
}
