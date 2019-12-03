package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文件上传记录表 oa_file_upload
 * 
 * @author ruoyi
 * @date 2019-08-19
 */
public class OaFileUpload extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 文件id */
	private Long fileId;
	/** 申请id */
	private Long applyId;
	/** 用户名 */
	private String loginName;
	/** 文件名 */
	private String fileName;
	/** 路径 */
	private String filePath;

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public void setFileId(Long fileId) 
	{
		this.fileId = fileId;
	}

	public Long getFileId() 
	{
		return fileId;
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public String getFileName() 
	{
		return fileName;
	}
	public void setFilePath(String filePath) 
	{
		this.filePath = filePath;
	}

	public String getFilePath() 
	{
		return filePath;
	}

	@Override
	public String toString() {
		return "OaFileUpload [fileId=" + fileId + ", applyId=" + applyId + ", loginName=" + loginName + ", fileName="
				+ fileName + ", filePath=" + filePath + "]";
	}

}
