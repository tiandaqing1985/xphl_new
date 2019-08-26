package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
	/** 用户id */
	private Long userId;
	/** 文件名 */
	private String fileName;
	/** 路径 */
	private String filePath;

	public void setFileId(Long fileId) 
	{
		this.fileId = fileId;
	}

	public Long getFileId() 
	{
		return fileId;
	}
	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}

	public Long getUserId() 
	{
		return userId;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fileId", getFileId())
            .append("userId", getUserId())
            .append("fileName", getFileName())
            .append("filePath", getFilePath())
            .append("createTime", getCreateTime())
            .toString();
    }
}
