package com.ruoyi.web.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
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
	
	@GetMapping("/myApprovalList")
	public String myApprovalList()
	{
	    return prefix + "/myApprovalList";
	}
	
	/** 钉钉考勤数据+请假数据+外出报备数据联合展示  */
	@PostMapping("/workAttendance")
	@ResponseBody
	public TableDataInfo workAttendance(Dingding ding)
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
	 * 修改钉钉考勤数据,添加图片
	 */
	@GetMapping("/edit")
	public String edit(Date userCheckTime,Map<String, Date> map)
	{
		map.put("userCheckTime", userCheckTime);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改钉钉考勤数据,添加图片
	 */
	@Log(title = "钉钉考勤数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult edit(MultipartFile file)
	{
		Map<String, Date> map = new HashMap();
		Date userCheckTime = (Date) map.get("userCheckTime");
		OaDingding ding = new OaDingding();
		  try
	        {
	            // 上传文件路径
	            String filePath = Global.getUploadPath();
	            // 上传并返回新文件名称
	            String fileName = FileUploadUtils.upload(filePath, file);
//	            String url = serverConfig.getUrl() + UPLOAD_PATH + fileName;
	            String url = filePath + fileName;
	            AjaxResult ajax = AjaxResult.success();
	            ajax.put("fileName", fileName);
	            ajax.put("url", url);
	            ding.setUserId(ShiroUtils.getUserId());
	            ding.setUserCheckTime(userCheckTime);
	        }
	        catch (Exception e)
	        {
	            return AjaxResult.error(e.getMessage());
	        }

		return toAjax(oaDingdingService.updateOaDingding(ding));
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
