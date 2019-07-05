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
import com.ruoyi.system.domain.RxZhanxian;
import com.ruoyi.system.service.IRxZhanxianService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 瑞幸展现 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-05
 */
@Controller
@RequestMapping("/system/rxZhanxian")
public class RxZhanxianController extends BaseController
{
    private String prefix = "system/rxZhanxian";
	
	@Autowired
	private IRxZhanxianService rxZhanxianService;
	
	@RequiresPermissions("system:rxZhanxian:view")
	@GetMapping()
	public String rxZhanxian()
	{
	    return prefix + "/rxZhanxian";
	}
	
	/**
	 * 查询瑞幸展现列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(RxZhanxian rxZhanxian,String selectflag)
	{
		startPage();
        //List<RxZhanxian> list = rxZhanxianService.selectRxZhanxianList(rxZhanxian);
		List<RxZhanxian> list = rxZhanxianService.selectRxZhanxianSumList(selectflag);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出瑞幸展现列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RxZhanxian rxZhanxian,String selectflag)
    {
    	List<RxZhanxian> list = rxZhanxianService.selectRxZhanxianSumList(selectflag);
        ExcelUtil<RxZhanxian> util = new ExcelUtil<RxZhanxian>(RxZhanxian.class);
        return util.exportExcel(list, "rxZhanxian");
    }
	
	/**
	 * 新增瑞幸展现
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存瑞幸展现
	 */
	@Log(title = "瑞幸展现", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(RxZhanxian rxZhanxian)
	{		
		return toAjax(rxZhanxianService.insertRxZhanxian(rxZhanxian));
	}

	/**
	 * 修改瑞幸展现
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		RxZhanxian rxZhanxian = rxZhanxianService.selectRxZhanxianById(id);
		mmap.put("rxZhanxian", rxZhanxian);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存瑞幸展现
	 */
	@Log(title = "瑞幸展现", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(RxZhanxian rxZhanxian)
	{		
		return toAjax(rxZhanxianService.updateRxZhanxian(rxZhanxian));
	}
	
	/**
	 * 删除瑞幸展现
	 */
	@Log(title = "瑞幸展现", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(rxZhanxianService.deleteRxZhanxianByIds(ids));
	}
	
	/**
	 * 生成瑞幸日报展现数据
	 * @param jfZhanxian
	 * @return
	 */
	@PostMapping("/createRxZhanxianData")
	@ResponseBody
	public AjaxResult createRxZhanxianData(RxZhanxian rxZhanxian)
	{		
		List<RxZhanxian> rxZhanxianList = rxZhanxianService.selectRxZhanxian();
		for(RxZhanxian rxzList : rxZhanxianList){
		
			rxzList.getRxDate();
			
			rxZhanxianService.insertRxZhanxian(rxzList);
		}
		
		return toAjax(1);
	}
	
	/**
	 * 清除数据
	 * @return
	 */
	@PostMapping( "/deleteAllRxZhanxian")
	@ResponseBody
	public AjaxResult deleteAllRxZhanxian()
	{		
		return toAjax(rxZhanxianService.deleteAllRxZhanxian());
	}
}
