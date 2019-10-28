package com.ruoyi.system.domain.finance;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 财务编号表 fac_number_table
 * 
 * @author ruoyi
 * @date 2019-10-25
 */
public class FacNumberTable extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 编号 */
	private String num;
	/** 编号头 */
	private String head;
	/** 编号时间 */
	private String time;
	/** 编号数字 */
	private Integer number;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setNum(String num) 
	{
		this.num = num;
	}

	public String getNum() 
	{
		return num;
	}
	public void setHead(String head) 
	{
		this.head = head;
	}

	public String getHead() 
	{
		return head;
	}
	public void setTime(String time) 
	{
		this.time = time;
	}

	public String getTime() 
	{
		return time;
	}
	public void setNumber(Integer number) 
	{
		this.number = number;
	}

	public Integer getNumber() 
	{
		return number;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("num", getNum())
            .append("head", getHead())
            .append("time", getTime())
            .append("number", getNumber())
            .toString();
    }
}
