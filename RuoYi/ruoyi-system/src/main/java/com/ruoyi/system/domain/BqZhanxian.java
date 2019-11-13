package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 北汽展现表 bq_zhanxian
 * 
 * @author ruoyi
 * @date 2019-07-04
 */
public class BqZhanxian extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;
	/** 月份 */
	@Excel(name = "月份")
	private String bqMonth;
	/** 日期 */
	@Excel(name = "日期",dateFormat = "yyyy-MM-dd")
	private Date bqDate;
	/** 账户 */
	@Excel(name = "账户")
	private String account;
	/** 计划 */
	@Excel(name = "计划")
	private String plan;
	/** 单元 */
	@Excel(name = "单元")
	private String unit;
	/** 关键词 */
	@Excel(name = "关键词")
	private String keyword;
	
	/** PC端展现 */
	@Excel(name = "PC-展现")
	private Integer pcShowdata;
	/** PC端点击 */
	@Excel(name = "PC-点击")
	private Integer pcClick;
	/** PC端消费 */
	@Excel(name = "PC-消费")
	private String pcCost;
	/** PC端品宣消费 */
	@Excel(name = "PC-品宣消费")
	private String pcPxcost;
	/** PC端CTR */
	@Excel(name = "PC-CTR")
	private String pcCtr;
	/** PC端CPC */
	@Excel(name = "PC-CPC")
	private String pcCpc;
	/** PC端留资 */
	@Excel(name = "PC-留资")
	private Integer pcOrderCount;
	/** PC端留资成本 */
	@Excel(name = "PC-留资成本")
	private String pcCostof;
	
	/** M端展现 */
	@Excel(name = "M-展现")
	private Integer mdShowdata;
	/** M端点击 */
	@Excel(name = "M-点击")
	private Integer mdClick;
	/** M端消费 */
	@Excel(name = "M-消费")
	private String mdCost;
	/** M端品宣消费 */
	@Excel(name = "M-品宣消费")
	private String mdPxcost;
	/** M端CTR */
	@Excel(name = "M-CTR")
	private String mdCtr;
	/** M端CPC */
	@Excel(name = "M-CPC")
	private String mdCpc;
	/** M端留资 */
	@Excel(name = "M-留资")
	private Integer mdOrderCount;
	/** M端留资成本 */
	@Excel(name = "M-留资成本")
	private String mdCostof;
	
	/** PC+M端展现 */
	@Excel(name = "PC+M-展现")
	private Integer allShowdata;
	/** PC+M端点击 */
	@Excel(name = "PC+M-点击")
	private Integer allClick;
	/** PC+M端消费 */
	@Excel(name = "PC+M-消费")
	private String allCost;
	/** PC+M端品宣消费 */
	@Excel(name = "PC+M-品宣消费")
	private String allPxcost;
	/** PC+M端CTR */
	@Excel(name = "PC+M-CTR")
	private String allCtr;
	/** PC+M端CPC */
	@Excel(name = "PC+M-CPC")
	private String allCpc;
	/** PC+M端留资 */
	@Excel(name = "PC+M-留资")
	private Integer allOrderCount;
	/** PC+M端留资成本 */
	@Excel(name = "PC+M-留资成本")
	private String allCostof;
	
	/** 备注 */
	private String remark;
	
	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setBqMonth(String bqMonth) 
	{
		this.bqMonth = bqMonth;
	}

	public String getBqMonth() 
	{
		return bqMonth;
	}
	public void setBqDate(Date bqDate) 
	{
		this.bqDate = bqDate;
	}

	public Date getBqDate() 
	{
		return bqDate;
	}
	public void setMdShowdata(Integer mdShowdata) 
	{
		this.mdShowdata = mdShowdata;
	}

	public Integer getMdShowdata() 
	{
		return mdShowdata;
	}
	public void setMdClick(Integer mdClick) 
	{
		this.mdClick = mdClick;
	}

	public Integer getMdClick() 
	{
		return mdClick;
	}
	public void setMdCost(String mdCost) 
	{
		this.mdCost = mdCost;
	}

	public String getMdCost() 
	{
		return mdCost;
	}
	public void setMdPxcost(String mdPxcost) 
	{
		this.mdPxcost = mdPxcost;
	}

	public String getMdPxcost() 
	{
		return mdPxcost;
	}
	public void setMdCtr(String mdCtr) 
	{
		this.mdCtr = mdCtr;
	}

	public String getMdCtr() 
	{
		return mdCtr;
	}
	public void setMdCpc(String mdCpc) 
	{
		this.mdCpc = mdCpc;
	}

	public String getMdCpc() 
	{
		return mdCpc;
	}
	public void setMdOrderCount(Integer mdOrderCount) 
	{
		this.mdOrderCount = mdOrderCount;
	}

	public Integer getMdOrderCount() 
	{
		return mdOrderCount;
	}
	public void setMdCostof(String mdCostof) 
	{
		this.mdCostof = mdCostof;
	}

	public String getMdCostof() 
	{
		return mdCostof;
	}
	public void setPcShowdata(Integer pcShowdata) 
	{
		this.pcShowdata = pcShowdata;
	}

	public Integer getPcShowdata() 
	{
		return pcShowdata;
	}
	public void setPcClick(Integer pcClick) 
	{
		this.pcClick = pcClick;
	}

	public Integer getPcClick() 
	{
		return pcClick;
	}
	public void setPcCost(String pcCost) 
	{
		this.pcCost = pcCost;
	}

	public String getPcCost() 
	{
		return pcCost;
	}
	public void setPcPxcost(String pcPxcost) 
	{
		this.pcPxcost = pcPxcost;
	}

	public String getPcPxcost() 
	{
		return pcPxcost;
	}
	public void setPcCtr(String pcCtr) 
	{
		this.pcCtr = pcCtr;
	}

	public String getPcCtr() 
	{
		return pcCtr;
	}
	public void setPcCpc(String pcCpc) 
	{
		this.pcCpc = pcCpc;
	}

	public String getPcCpc() 
	{
		return pcCpc;
	}
	public void setPcOrderCount(Integer pcOrderCount) 
	{
		this.pcOrderCount = pcOrderCount;
	}

	public Integer getPcOrderCount() 
	{
		return pcOrderCount;
	}
	public void setPcCostof(String pcCostof) 
	{
		this.pcCostof = pcCostof;
	}

	public String getPcCostof() 
	{
		return pcCostof;
	}
	public void setAllShowdata(Integer allShowdata) 
	{
		this.allShowdata = allShowdata;
	}

	public Integer getAllShowdata() 
	{
		return allShowdata;
	}
	public void setAllClick(Integer allClick) 
	{
		this.allClick = allClick;
	}

	public Integer getAllClick() 
	{
		return allClick;
	}
	public void setAllCost(String allCost) 
	{
		this.allCost = allCost;
	}

	public String getAllCost() 
	{
		return allCost;
	}
	public void setAllPxcost(String allPxcost) 
	{
		this.allPxcost = allPxcost;
	}

	public String getAllPxcost() 
	{
		return allPxcost;
	}
	public void setAllCtr(String allCtr) 
	{
		this.allCtr = allCtr;
	}

	public String getAllCtr() 
	{
		return allCtr;
	}
	public void setAllCpc(String allCpc) 
	{
		this.allCpc = allCpc;
	}

	public String getAllCpc() 
	{
		return allCpc;
	}
	public void setAllOrderCount(Integer allOrderCount) 
	{
		this.allOrderCount = allOrderCount;
	}

	public Integer getAllOrderCount() 
	{
		return allOrderCount;
	}
	public void setAllCostof(String allCostof) 
	{
		this.allCostof = allCostof;
	}

	public String getAllCostof() 
	{
		return allCostof;
	}
	
    public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
            .append("bqMonth", getBqMonth())
            .append("bqDate", getBqDate())
            .append("mdShowdata", getMdShowdata())
            .append("mdClick", getMdClick())
            .append("mdCost", getMdCost())
            .append("mdPxcost", getMdPxcost())
            .append("mdCtr", getMdCtr())
            .append("mdCpc", getMdCpc())
            .append("mdOrderCount", getMdOrderCount())
            .append("mdCostof", getMdCostof())
            .append("pcShowdata", getPcShowdata())
            .append("pcClick", getPcClick())
            .append("pcCost", getPcCost())
            .append("pcPxcost", getPcPxcost())
            .append("pcCtr", getPcCtr())
            .append("pcCpc", getPcCpc())
            .append("pcOrderCount", getPcOrderCount())
            .append("pcCostof", getPcCostof())
            .append("allShowdata", getAllShowdata())
            .append("allClick", getAllClick())
            .append("allCost", getAllCost())
            .append("allPxcost", getAllPxcost())
            .append("allCtr", getAllCtr())
            .append("allCpc", getAllCpc())
            .append("allOrderCount", getAllOrderCount())
            .append("allCostof", getAllCostof())
            .toString();
    }
}
