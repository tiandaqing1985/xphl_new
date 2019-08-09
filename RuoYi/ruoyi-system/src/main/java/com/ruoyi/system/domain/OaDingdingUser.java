package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 钉钉用户表 oa_dingding_user
 * 
 * @author ruoyi
 * @date 2019-07-26
 */
public class OaDingdingUser extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 用户id */
	private Long userId;
	/** 用户名 */
	private String userName;

	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}

	public Long getUserId() 
	{
		return userId;
	}


    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("username", getUserName())
            .toString();
    }
}
