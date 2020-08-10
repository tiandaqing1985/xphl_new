package com.ruoyi.web.controller.system;

import java.io.File;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.OaFileDownload;
import com.ruoyi.system.domain.OaFileUpload;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.IOaFileDownloadService;
import com.ruoyi.system.service.IOaFileUploadService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 文件上传记录 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-08-16
 */
@Controller
@RequestMapping("/system/oaFileUpload")
public class OaFileUploadController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(OaFileUploadController.class);

	private String prefix = "system/oaFileUploadDownload";

	@Autowired
	private IOaFileUploadService oaFileUploadService;
	@Autowired
	private ISysRoleService iSysRoleService;
	@Autowired
	private ISysUserService iSysUserService;
	@Autowired
	private IOaFileDownloadService oaFileDownloadService;

	// @RequiresPermissions("system:oaFileUpload:view")
	@GetMapping()
	public String oaFileUpload() {
		return prefix + "/oaFileUpload";
	}

	/**
	 * 查询文件上传记录列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(OaFileUpload oaFileUpload, Model m) {
		startPage();
		List<OaFileUpload> list = oaFileUploadService.selectOaFileUploadList(oaFileUpload);
		boolean removeFlag = false;

		// m.addAttribute("file", list);

		SysUser user = new SysUser();
		user.setRoleId(6L);// 人事总监
		user.setArea("1");
		Long hrId = iSysRoleService.selectUserIdByRoleId(user);// 人事总监id
		Long userId = ShiroUtils.getUserId();

		if (userId == 101L || userId == 103L) {
			removeFlag = false;
			m.addAttribute("removeFlag", removeFlag);
			return getDataTable(list);
		}

		Long leaderId = iSysUserService.selectApproverIdByApplyerId(userId);// 所在部门负责人id
		Long upLeaderId = iSysUserService.selectUpApproverIdByApplyerId(userId);// 所在部门负责人的上级leader

		// 判断当前登陆用户是否是人事
		if (userId.longValue() == 1L || hrId == userId.longValue() || hrId.longValue() == leaderId.longValue()
				|| upLeaderId.longValue() == hrId.longValue()) {
			removeFlag = true;
			m.addAttribute("removeFlag", removeFlag);
		}
		return getDataTable(list);
	}

	/**
	 * 本地资源通用下载
	 */
	@GetMapping("/common/download/resource")
	public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		File file = null;

		file = new File("download/1.pdf");

		ServletOutputStream outputStream = null;
		try {

			// 下载名称
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName=yuangongshouce.pdf");
			outputStream = response.getOutputStream();
			FileUtils.writeBytes(file.getAbsolutePath(), outputStream);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}

	}

	/**
	 * 本地资源通用下载
	 */
	@GetMapping("/common/download/resource2")
	public void resourceDownload2(String resource, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		File file = null;

		file = new File("download/2.pdf");

		ServletOutputStream outputStream = null;
		try {

			// 下载名称
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName=kaoqinzhidu.pdf");
			outputStream = response.getOutputStream();
			FileUtils.writeBytes(file.getAbsolutePath(), outputStream);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}

	}


	/**
	 * 本地资源通用下载
	 */
	@GetMapping("/common/download/resource3")
	public void resourceDownload3(String resource, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		File file = null;

		file = new File("download/3.pdf");

		ServletOutputStream outputStream = null;
		try {

			// 下载名称
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName=renzhizige.pdf");
			outputStream = response.getOutputStream();
			FileUtils.writeBytes(file.getAbsolutePath(), outputStream);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}

	}
	
	/**
	 * 导出文件上传记录列表
	 */
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(OaFileUpload oaFileUpload) {
		List<OaFileUpload> list = oaFileUploadService.selectOaFileUploadList(oaFileUpload);
		ExcelUtil<OaFileUpload> util = new ExcelUtil<OaFileUpload>(OaFileUpload.class);
		return util.exportExcel(list, "oaFileUpload");
	}

	/**
	 * 新增文件上传记录
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存文件上传记录
	 */
	@Log(title = "文件上传记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MultipartFile file) {
		OaFileUpload fileUpload = new OaFileUpload();
		try {
			// 上传文件路径
			String filePath = Global.getUploadPath();
			// 上传并返回新文件名称
			String fileName = FileUploadUtils.upload(filePath, file);
			// String url = serverConfig.getUrl() + UPLOAD_PATH + fileName;
			String url = filePath + fileName;
			AjaxResult ajax = AjaxResult.success();
			ajax.put("fileName", fileName);
			ajax.put("url", url);
			// fileUpload.setApplyId(applyId);
			fileUpload.setLoginName(ShiroUtils.getLoginName());
			fileUpload.setFileName(file.getOriginalFilename());
			fileUpload.setFilePath(url);
		} catch (Exception e) {
			return AjaxResult.error(e.getMessage());
		}

		return toAjax(oaFileUploadService.insertOaFileUpload(fileUpload));
	}

	/**
	 * 新增保存文件下载记录
	 */
	@Log(title = "文件下载记录", businessType = BusinessType.INSERT)
	@GetMapping("/download/{fileId}")
	@ResponseBody
	public AjaxResult addSave(@PathVariable("fileId") Long fileId, HttpServletResponse response,
			HttpServletRequest request) {

		try {
			OaFileUpload file = oaFileUploadService.selectOaFileUploadById(fileId);
			String fileName = file.getFileName();
			if (!FileUtils.isValidFilename(fileName)) {
				throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
			}
			// String realFileName = System.currentTimeMillis() +
			// fileName.substring(fileName.indexOf("_") + 1);
			// String filePath = Global.getUploadPath() + fileName;
			String filePath = file.getFilePath();

			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition",
					"attachment;fileName=" + FileUtils.setFileDownloadHeader(request, fileName));

			FileUtils.writeBytes(filePath, response.getOutputStream());
		} catch (Exception e) {
			log.error("下载文件失败", e);
		}
		OaFileDownload fileDownload = new OaFileDownload();
		fileDownload.setUserId(ShiroUtils.getUserId());
		fileDownload.setFileId(fileId);
		return toAjax(oaFileDownloadService.insertOaFileDownload(fileDownload));
	}

	/**
	 * 修改文件上传记录
	 */
	@GetMapping("/edit/{fileId}")
	public String edit(@PathVariable("fileId") Long fileId, ModelMap mmap) {
		OaFileUpload oaFileUpload = oaFileUploadService.selectOaFileUploadById(fileId);
		mmap.put("oaFileUpload", oaFileUpload);
		return prefix + "/edit";
	}

	/**
	 * 修改保存文件上传记录
	 */
	@Log(title = "文件上传记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(OaFileUpload oaFileUpload) {
		return toAjax(oaFileUploadService.updateOaFileUpload(oaFileUpload));
	}

	/**
	 * 删除文件上传记录及文件
	 */
	@Log(title = "文件上传记录", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		OaFileUpload file = oaFileUploadService.selectOaFileUploadById(Long.valueOf(ids));
		String filePath = Global.getUploadPath() + file.getFileName();
		FileUtils.deleteFile(filePath);// 删除文件
		return toAjax(oaFileUploadService.deleteOaFileUploadByIds(ids));
	}

	/**
	 * 验证文件是否已经上传过
	 */
	@Log(title = "文件上传记录")
	@PostMapping("/ifUpload/{fileName}")
	@ResponseBody
	public boolean ifUpload(@PathVariable("fileName") String fileName) {
		OaFileUpload file = new OaFileUpload();
		file.setFileName(fileName);
		List<OaFileUpload> fList = oaFileUploadService.selectOaFileUploadList(file);
		if (fList.size() != 0)
			return true;
		return false;
	}

	/** 验证图片是否上传 */
	@Log(title = "图片是否已经上传")
	@PostMapping("/ifPicUpload")
	@ResponseBody
	public boolean ifPicUpload() {
		boolean flag = oaFileUploadService.ifPicUpload(ShiroUtils.getSysUser().getLoginName());
		return flag;
	}
}
