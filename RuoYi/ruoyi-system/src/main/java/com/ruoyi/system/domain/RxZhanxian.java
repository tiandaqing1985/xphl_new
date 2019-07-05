package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 瑞幸展现表 rx_zhanxian
 * 
 * @author ruoyi
 * @date 2019-07-05
 */
public class RxZhanxian extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 月份 */
	@Excel(name = "月份")
	private String rxMonth;
	/** 日期 */
	@Excel(name = "日期",dateFormat = "yyyy-MM-dd")
	private Date rxDate;
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
	
	/** pc-展现 */
	@Excel(name = "PC-展现")
	private Integer pcShowdata;
	/** pc-点击 */
	@Excel(name = "PC-点击")
	private Integer pcClick;
	/** pc-消费 */
	@Excel(name = "PC-消费")
	private String pcConsume;
	/** pc-CTR */
	@Excel(name = "PC-CTR")
	private String pcCtr;
	/** pc-CPC */
	@Excel(name = "PC-CPC")
	private String pcCpc;
	
	/** m-展现 */
	@Excel(name = "M-展现")
	private Integer mdShowdata;
	/** m-点击 */
	@Excel(name = "M-点击")
	private Integer mdClick;
	/** m-消费 */
	@Excel(name = "M-消费")
	private String mdConsume;
	/** m-CTR */
	@Excel(name = "M-CTR")
	private String mdCtr;
	/** m-CPC */
	@Excel(name = "M-CPC")
	private String mdCpc;
	
	/** pc+m-展现 */
	@Excel(name = "PC+M-展现")
	private Integer allShowdata;
	/** pc+m-点击 */
	@Excel(name = "PC+M-点击")
	private Integer allClick;
	/** pc+m-消费 */
	@Excel(name = "PC+M-消费")
	private String allConsume;
	/** pc+m-CTR */
	@Excel(name = "PC+M-CTR")
	private String allCtr;
	/** pc+m-CPC */
	@Excel(name = "PC+M-CPC")
	private String allCpc;
	

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setRxMonth(String rxMonth) 
	{
		this.rxMonth = rxMonth;
	}

	public String getRxMonth() 
	{
		return rxMonth;
	}
	public void setRxDate(Date rxDate) 
	{
		this.rxDate = rxDate;
	}

	public Date getRxDate() 
	{
		return rxDate;
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
	public void setPcConsume(String pcConsume) 
	{
		this.pcConsume = pcConsume;
	}

	public String getPcConsume() 
	{
		return pcConsume;
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
	public void setMdConsume(String mdConsume) 
	{
		this.mdConsume = mdConsume;
	}

	public String getMdConsume() 
	{
		return mdConsume;
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
	public void setAllConsume(String allConsume) 
	{
		this.allConsume = allConsume;
	}

	public String getAllConsume() 
	{
		return allConsume;
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
	public void setAccount(String account) 
	{
		this.account = account;
	}

	public String getAccount() 
	{
		return account;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("rxMonth", getRxMonth())
            .append("rxDate", getRxDate())
            .append("pcShowdata", getPcShowdata())
            .append("pcClick", getPcClick())
            .append("pcConsume", getPcConsume())
            .append("pcCtr", getPcCtr())
            .append("pcCpc", getPcCpc())
            .append("mdShowdata", getMdShowdata())
            .append("mdClick", getMdClick())
            .append("mdConsume", getMdConsume())
            .append("mdCtr", getMdCtr())
            .append("mdCpc", getMdCpc())
            .append("allShowdata", getAllShowdata())
            .append("allClick", getAllClick())
            .append("allConsume", getAllConsume())
            .append("allCtr", getAllCtr())
            .append("allCpc", getAllCpc())
            .append("account", getAccount())
            .append("plan", getPlan())
            .append("unit", getUnit())
            .append("keyword", getKeyword())
            .toString();
    }
}
