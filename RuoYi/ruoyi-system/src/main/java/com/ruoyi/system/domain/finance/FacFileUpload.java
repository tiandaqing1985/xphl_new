package com.ruoyi.system.domain.finance;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 财务文件上传记录表 fac_file_upload
 * 
 * @author ruoyi
 * @date 2020-04-22
 */
public class FacFileUpload extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 表id */
	private Long loadId;
	/** 编号 */
	private String num;
	/** 文件名 */
	private String fileName;
	/** 路径 */
	private String filePath;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setLoadId(Long loadId) 
	{
		this.loadId = loadId;
	}

	public Long getLoadId() 
	{
		return loadId;
	}
	public void setNum(String num) 
	{
		this.num = num;
	}

	public String getNum() 
	{
		return num;
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
            .append("id", getId())
            .append("loadId", getLoadId())
            .append("num", getNum())
            .append("fileName", getFileName())
            .append("filePath", getFilePath())
            .append("createTime", getCreateTime())
            .toString();
    }
}
