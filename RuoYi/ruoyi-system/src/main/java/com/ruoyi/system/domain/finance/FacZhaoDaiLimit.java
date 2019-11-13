package com.ruoyi.system.domain.finance;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 招待费报销限额表 fac_zhao_dai_limit
 * 
 * @author ruoyi
 * @date 2019-11-12
 */
public class FacZhaoDaiLimit extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 角色id */
	private Long roleId;
	/** 角色名称 */
	private String roleName;
	/** 限额 */
	private BigDecimal limitAmount;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setRoleId(Long roleId) 
	{
		this.roleId = roleId;
	}

	public Long getRoleId() 
	{
		return roleId;
	}
	public void setRoleName(String roleName) 
	{
		this.roleName = roleName;
	}

	public String getRoleName() 
	{
		return roleName;
	}


    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roleId", getRoleId())
            .append("roleName", getRoleName())
            .append("limit", getLimitAmount())
            .toString();
    }
}
