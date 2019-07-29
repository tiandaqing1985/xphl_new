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
import com.ruoyi.system.domain.TestCopy;
import com.ruoyi.system.service.ITestCopyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商机 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-07-23
 */
@Controller
@RequestMapping("/system/testCopy")
public class TestCopyController extends BaseController
{
    private String prefix = "system/testCopy";
	
	@Autowired
	private ITestCopyService testCopyService;
	
	@RequiresPermissions("system:testCopy:view")
	@GetMapping()
	public String testCopy()
	{
	    return prefix + "/testCopy";
	}
	
	/**
	 * 查询商机列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TestCopy testCopy)
	{
		startPage();
        List<TestCopy> list = testCopyService.selectTestCopyList(testCopy);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商机列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TestCopy testCopy)
    {
    	List<TestCopy> list = testCopyService.selectTestCopyList(testCopy);
        ExcelUtil<TestCopy> util = new ExcelUtil<TestCopy>(TestCopy.class);
        return util.exportExcel(list, "testCopy");
    }
	
	/**
	 * 新增商机
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商机
	 */
	@Log(title = "商机", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TestCopy testCopy)
	{		
		return toAjax(testCopyService.insertTestCopy(testCopy));
	}

	/**
	 * 修改商机
	 */
	@GetMapping("/edit/{businessId}")
	public String edit(@PathVariable("businessId") Long businessId, ModelMap mmap)
	{
		TestCopy testCopy = testCopyService.selectTestCopyById(businessId);
		mmap.put("testCopy", testCopy);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商机
	 */
	@Log(title = "商机", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TestCopy testCopy)
	{		
		return toAjax(testCopyService.updateTestCopy(testCopy));
	}
	
	/**
	 * 删除商机
	 */
	@Log(title = "商机", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(testCopyService.deleteTestCopyByIds(ids));
	}


	@ResponseBody
	public AjaxResult importTemplate() {
		ExcelUtil<TestCopy> util = new ExcelUtil<>(TestCopy.class);
		return util.importTemplateExcel("搜索");
	}


	@Log(title = "当当搜索后端", businessType = BusinessType.IMPORT)
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file) throws Exception {
		ExcelUtil<TestCopy> util = new ExcelUtil<>(TestCopy.class);
		List<TestCopy> dangdangSearchAdds= util.importExcel(file.getInputStream(), 0, 1);
		String operName = ShiroUtils.getSysUser().getLoginName();
		String message = testCopyService.importBwFront(dangdangSearchAdds, false, operName);
		return AjaxResult.success(message);
	}
}
