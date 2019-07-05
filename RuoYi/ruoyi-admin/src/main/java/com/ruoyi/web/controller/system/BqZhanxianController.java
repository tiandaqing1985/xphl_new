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
import com.ruoyi.system.domain.BqZhanxian;
import com.ruoyi.system.domain.JfZhanxian;
import com.ruoyi.system.service.IBqZhanxianService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 北汽展现 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-04
 */
@Controller
@RequestMapping("/system/bqZhanxian")
public class BqZhanxianController extends BaseController
{
    private String prefix = "system/bqZhanxian";
	
	@Autowired
	private IBqZhanxianService bqZhanxianService;
	
	@RequiresPermissions("system:bqZhanxian:view")
	@GetMapping()
	public String bqZhanxian()
	{
	    return prefix + "/bqZhanxian";
	}
	
	/**
	 * 查询北汽展现列表
	 */
	/*@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BqZhanxian bqZhanxian)
	{
		startPage();

        List<BqZhanxian> list = bqZhanxianService.selectBqZhanxianList(bqZhanxian);
		return getDataTable(list);
	}*/
	
	/**
	 * 查询北汽展现列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BqZhanxian bqZhanxian,String selectflag)
	{
		startPage();

        List<BqZhanxian> list = bqZhanxianService.selectBqZhanxianSumList(selectflag);
		return getDataTable(list);
	}
	
	/**
	 * 导出北汽展现列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BqZhanxian bqZhanxian,String selectflag)
    {
    	List<BqZhanxian> list = bqZhanxianService.selectBqZhanxianSumList(selectflag);
        ExcelUtil<BqZhanxian> util = new ExcelUtil<BqZhanxian>(BqZhanxian.class);
        return util.exportExcel(list, "bqZhanxian");
    }
	
	/**
	 * 新增北汽展现
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存北汽展现
	 */
	@Log(title = "北汽展现", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BqZhanxian bqZhanxian)
	{		
		return toAjax(bqZhanxianService.insertBqZhanxian(bqZhanxian));
	}

	/**
	 * 修改北汽展现
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		BqZhanxian bqZhanxian = bqZhanxianService.selectBqZhanxianById(id);
		mmap.put("bqZhanxian", bqZhanxian);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存北汽展现
	 */
	@Log(title = "北汽展现", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BqZhanxian bqZhanxian)
	{		
		return toAjax(bqZhanxianService.updateBqZhanxian(bqZhanxian));
	}
	
	/**
	 * 删除北汽展现
	 */
	@Log(title = "北汽展现", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(bqZhanxianService.deleteBqZhanxianByIds(ids));
	}
	
	/**
	 * 生成北汽日报展现数据
	 * @param jfZhanxian
	 * @return
	 */
	@PostMapping("/createBqZhanxianData")
	@ResponseBody
	public AjaxResult createBqZhanxianData(BqZhanxian bqZhanxian)
	{		
		List<BqZhanxian> bqZhanxianList = bqZhanxianService.selectBqZhanxian();
		for(BqZhanxian bqzList : bqZhanxianList){
		
			bqzList.getBqDate();
			
			bqZhanxianService.insertBqZhanxian(bqzList);
		}
		
		return toAjax(1);
	}
	
	/**
	 * 清除数据
	 * @return
	 */
	@PostMapping( "/deleteAllBqZhanxian")
	@ResponseBody
	public AjaxResult deleteAllBqZhanxian()
	{		
		return toAjax(bqZhanxianService.deleteAllBqZhanxian());
	}
}
