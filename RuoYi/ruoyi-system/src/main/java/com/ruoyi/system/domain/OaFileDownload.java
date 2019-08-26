package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文件下载记录表 oa_file_download
 * 
 * @author ruoyi
 * @date 2019-08-16
 */
public class OaFileDownload extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 唯一标识 */
	private Long id;
	/** 文件id */
	private Long fileId;
	/** 用户id */
	private Long userId;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fileId", getFileId())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
