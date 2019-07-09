package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 玖富展现表 jf_zhanxian
 * 
 * @author ruoyi
 * @date 2019-07-01
 */
public class JfZhanxian extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;
    /** 备注 */
	@Excel(name = "备注")
    private String remark;
	/** 日期 */
    @Excel(name = "日期" ,dateFormat = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date frontDate;
	/** 编码 */
	private String code;
	/** 组合 */
	private String groupword;
	/** 账户 */
	@Excel(name = "账户")
	private String accountname;
	/** 计划 */
	@Excel(name = "计划")
	private String plan;
	/** 单元 */
	@Excel(name = "单元")
	private String unit;
	/** 关键词 */
	@Excel(name = "关键词 ")
	private String keyword;
	/** 展现 */
	private Integer showdata;
	/** 点击 */
	@Excel(name = "点击")
	private Integer click;
	/** 消费 */
	@Excel(name = "消费")
	private Double consume;
	/** 账户数 */
	@Excel(name = "新入账户数")
	private Integer account;
	/** 开卡数 */
	@Excel(name = "开卡数")
	private Integer card;
	/** 提交人数 */
	@Excel(name = "提交信审人数  ")
	private Integer putin;
	/** 下单数 */
	@Excel(name = "下单数")
	private Integer placeOrder;
	/** 激活数 */
	@Excel(name = "激活数")
	private Integer activate;
	/**  */
	private String channle;


	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setFrontDate(Date frontDate) 
	{
		this.frontDate = frontDate;
	}

	public Date getFrontDate() 
	{
		return frontDate;
	}
	public void setCode(String code) 
	{
		this.code = code;
	}

	public String getCode() 
	{
		return code;
	}
	public void setGroupword(String groupword) 
	{
		this.groupword = groupword;
	}

	public String getGroupword() 
	{
		return groupword;
	}
	public void setShowdata(Integer showdata) 
	{
		this.showdata = showdata;
	}

	public Integer getShowdata() 
	{
		return showdata;
	}
	public void setClick(Integer click) 
	{
		this.click = click;
	}

	public Integer getClick() 
	{
		return click;
	}
	public void setConsume(Double consume) 
	{
		this.consume = consume;
	}

	public Double getConsume() 
	{
		return consume;
	}
	public void setAccount(Integer account) 
	{
		this.account = account;
	}

	public Integer getAccount() 
	{
		return account;
	}
	public void setCard(Integer card) 
	{
		this.card = card;
	}

	public Integer getCard() 
	{
		return card;
	}
	public void setPutin(Integer putin) 
	{
		this.putin = putin;
	}

	public Integer getPutin() 
	{
		return putin;
	}
	public void setPlaceOrder(Integer placeOrder) 
	{
		this.placeOrder = placeOrder;
	}

	public Integer getPlaceOrder() 
	{
		return placeOrder;
	}
	public void setActivate(Integer activate) 
	{
		this.activate = activate;
	}

	public Integer getActivate() 
	{
		return activate;
	}
	public void setChannle(String channle) 
	{
		this.channle = channle;
	}

	public String getChannle() 
	{
		return channle;
	}
	public void setAccountname(String accountname) 
	{
		this.accountname = accountname;
	}

	public String getAccountname() 
	{
		return accountname;
	}
	public void setPlan(String plan) 
	{
		this.plan = plan;
	}

	public String getPlan() 
	{
		return plan;
	}
	public void setUnit(String unit) 
	{
		this.unit = unit;
	}

	public String getUnit() 
	{
		return unit;
	}
	public void setKeyword(String keyword) 
	{
		this.keyword = keyword;
	}

	public String getKeyword() 
	{
		return keyword;
	}

    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("frontDate", getFrontDate())
            .append("code", getCode())
            .append("groupword", getGroupword())
            .append("showdata", getShowdata())
            .append("click", getClick())
            .append("consume", getConsume())
            .append("account", getAccount())
            .append("card", getCard())
            .append("putin", getPutin())
            .append("placeOrder", getPlaceOrder())
            .append("activate", getActivate())
            .append("channle", getChannle())
            .append("accountname", getAccountname())
            .append("plan", getPlan())
            .append("unit", getUnit())
            .append("keyword", getKeyword())
            .toString();
    }
}
