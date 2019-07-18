package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.DangdangSearchAdd;
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
import com.ruoyi.system.domain.DangdangIosAdd;
import com.ruoyi.system.service.IDangdangIosAddService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 当当ios后端数据 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-15
 */
@Controller
@RequestMapping("/system/dangdangIosAdd")
public class DangdangIosAddController extends BaseController
{
    private String prefix = "system/dangdangIosAdd";
	
	@Autowired
	private IDangdangIosAddService dangdangIosAddService;
	
	@RequiresPermissions("system:dangdangIosAdd:view")
	@GetMapping()
	public String dangdangIosAdd()
	{
	    return prefix + "/dangdangIosAdd";
	}
	
	/**
	 * 查询当当ios后端数据列表
	 */
	@RequiresPermissions("system:dangdangIosAdd:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DangdangIosAdd dangdangIosAdd)
	{
		startPage();
        List<DangdangIosAdd> list = dangdangIosAddService.selectDangdangIosAddList(dangdangIosAdd);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出当当ios后端数据列表
	 */
	@RequiresPermissions("system:dangdangIosAdd:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangdangIosAdd dangdangIosAdd)
    {
    	List<DangdangIosAdd> list = dangdangIosAddService.selectDangdangIosAddList(dangdangIosAdd);
        ExcelUtil<DangdangIosAdd> util = new ExcelUtil<DangdangIosAdd>(DangdangIosAdd.class);
        return util.exportExcel(list, "dangdangIosAdd");
    }
	
	/**
	 * 新增当当ios后端数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存当当ios后端数据
	 */
	@RequiresPermissions("system:dangdangIosAdd:add")
	@Log(title = "当当ios后端数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DangdangIosAdd dangdangIosAdd)
	{		
		return toAjax(dangdangIosAddService.insertDangdangIosAdd(dangdangIosAdd));
	}

	/**
	 * 修改当当ios后端数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DangdangIosAdd dangdangIosAdd = dangdangIosAddService.selectDangdangIosAddById(id);
		mmap.put("dangdangIosAdd", dangdangIosAdd);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存当当ios后端数据
	 */
	@RequiresPermissions("system:dangdangIosAdd:edit")
	@Log(title = "当当ios后端数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DangdangIosAdd dangdangIosAdd)
	{		
		return toAjax(dangdangIosAddService.updateDangdangIosAdd(dangdangIosAdd));
	}
	
	/**
	 * 删除当当ios后端数据
	 */
	@RequiresPermissions("system:dangdangIosAdd:remove")
	@Log(title = "当当ios后端数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangdangIosAddService.deleteDangdangIosAddByIds(ids));
	}




	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<DangdangIosAdd> util = new ExcelUtil<>(DangdangIosAdd.class);
		return util.importTemplateExcel("IOS");
	}


	@Log(title = "当当IOS后端", businessType = BusinessType.IMPORT)
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file) throws Exception {
		if (("").equals(file.getOriginalFilename())|| file.getOriginalFilename()==null){
			return AjaxResult.error("表名不存在");
		}
		System.out.println("IOS/搜索IOS导入表名----"+file.getOriginalFilename());
		ExcelUtil<DangdangIosAdd> util = new ExcelUtil<>(DangdangIosAdd.class);
		List<DangdangIosAdd> dangdangIosAdds= util.importExcel(file.getInputStream(), 0, 1);
		String operName = ShiroUtils.getSysUser().getLoginName();
		String message = dangdangIosAddService.importBwFront(dangdangIosAdds, false, operName,file.getOriginalFilename());
		return AjaxResult.success(message);
	}
}
