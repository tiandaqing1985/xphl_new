package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商机表 test_copy
 * 
 * @author ruoyi
 * @date 2019-07-23
 */
public class TestCopy extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long businessId;
	/** 公司名称 */
	@Excel(name="广告主全称")
	private String companyName;
	/** 广告主 */
	private String advertiser;
	/** 简称 */
	@Excel(name = "简称")
	private String shortName;
	/** 联系人1 */
	@Excel(name="姓名1")
	private String linkname1;
	/** 职位1 */
	private String position1;
	/** 岗位职责1 */
	private String position1Remark;
	/** 职责1 */
	private String responsibility1;
	/** 具体职责1 */
	private String responsibility1Remark;
	/** 联系电话1 */
	@Excel(name = "联系方式1")
	private String linkTel1;
	/** 名片1 */
	private String businessCard1;
	/** 联系人2 */
	@Excel(name="姓名2")
	private String linkname2;
	/** 职位2 */
	private String position2;
	/** 岗位职责2 */
	private String position2Remark;
	/** 职责2 */
	private String responsibility2;
	/** 具体职责2 */
	private String responsibility2Remark;
	/** 联系电话2 */
	@Excel(name = "联系方式2")
	private String linkTel2;
	/** 名片2 */
	private String businessCard2;
	/** 联系人3 */
	@Excel(name="姓名3")
	private String linkname3;
	/** 职位3 */
	private String position3;
	/** 岗位职责3 */
	private String position3Remark;
	/** 职责3 */
	private String responsibility3;
	/** 具体职责3 */
	private String responsibility3Remark;
	/** 联系电话3 */
	@Excel(name = "联系方式3")
	private String linkTel3;
	/** 名片3 */
	private String businessCard3;
	/** 公司业务内容 */
	private String companyRemark1;
	/** 财务状况 */
	private String companyRemark2;
	/** 组织架构 */
	private String companyRemark3;
	/** 目前投放预算 */
	private String companyRemark4;
	/** 媒体渠道 */
	private String companyRemark5;
	/** 采购代理商 */
	private String companyRemark6;
	/** 预计合作媒体 */
	private String companyRemark7;
	/** 预计下单产品 */
	private String companyRemark8;
	/** 预计下单时间 */
	private String companyRemark9;
	/** 商机评分 */
	private String businessGrade;
	/** 商机状态 */
	private String businessStatus;
	/** 商机归属人 */

	private String businessUser;
	/** 下单状态 */
	private String orderStatus;
	/** 媒体 */
	private String media;

	public void setBusinessId(Long businessId) 
	{
		this.businessId = businessId;
	}

	public Long getBusinessId() 
	{
		return businessId;
	}
	public void setCompanyName(String companyName) 
	{
		this.companyName = companyName;
	}

	public String getCompanyName() 
	{
		return companyName;
	}
	public void setAdvertiser(String advertiser) 
	{
		this.advertiser = advertiser;
	}

	public String getAdvertiser() 
	{
		return advertiser;
	}
	public void setShortName(String shortName) 
	{
		this.shortName = shortName;
	}

	public String getShortName() 
	{
		return shortName;
	}
	public void setLinkname1(String linkname1) 
	{
		this.linkname1 = linkname1;
	}

	public String getLinkname1() 
	{
		return linkname1;
	}
	public void setPosition1(String position1) 
	{
		this.position1 = position1;
	}

	public String getPosition1() 
	{
		return position1;
	}
	public void setPosition1Remark(String position1Remark) 
	{
		this.position1Remark = position1Remark;
	}

	public String getPosition1Remark() 
	{
		return position1Remark;
	}
	public void setResponsibility1(String responsibility1) 
	{
		this.responsibility1 = responsibility1;
	}

	public String getResponsibility1() 
	{
		return responsibility1;
	}
	public void setResponsibility1Remark(String responsibility1Remark) 
	{
		this.responsibility1Remark = responsibility1Remark;
	}

	public String getResponsibility1Remark() 
	{
		return responsibility1Remark;
	}
	public void setLinkTel1(String linkTel1) 
	{
		this.linkTel1 = linkTel1;
	}

	public String getLinkTel1() 
	{
		return linkTel1;
	}
	public void setBusinessCard1(String businessCard1) 
	{
		this.businessCard1 = businessCard1;
	}

	public String getBusinessCard1() 
	{
		return businessCard1;
	}
	public void setLinkname2(String linkname2) 
	{
		this.linkname2 = linkname2;
	}

	public String getLinkname2() 
	{
		return linkname2;
	}
	public void setPosition2(String position2) 
	{
		this.position2 = position2;
	}

	public String getPosition2() 
	{
		return position2;
	}
	public void setPosition2Remark(String position2Remark) 
	{
		this.position2Remark = position2Remark;
	}

	public String getPosition2Remark() 
	{
		return position2Remark;
	}
	public void setResponsibility2(String responsibility2) 
	{
		this.responsibility2 = responsibility2;
	}

	public String getResponsibility2() 
	{
		return responsibility2;
	}
	public void setResponsibility2Remark(String responsibility2Remark) 
	{
		this.responsibility2Remark = responsibility2Remark;
	}

	public String getResponsibility2Remark() 
	{
		return responsibility2Remark;
	}
	public void setLinkTel2(String linkTel2) 
	{
		this.linkTel2 = linkTel2;
	}

	public String getLinkTel2() 
	{
		return linkTel2;
	}
	public void setBusinessCard2(String businessCard2) 
	{
		this.businessCard2 = businessCard2;
	}

	public String getBusinessCard2() 
	{
		return businessCard2;
	}
	public void setLinkname3(String linkname3) 
	{
		this.linkname3 = linkname3;
	}

	public String getLinkname3() 
	{
		return linkname3;
	}
	public void setPosition3(String position3) 
	{
		this.position3 = position3;
	}

	public String getPosition3() 
	{
		return position3;
	}
	public void setPosition3Remark(String position3Remark) 
	{
		this.position3Remark = position3Remark;
	}

	public String getPosition3Remark() 
	{
		return position3Remark;
	}
	public void setResponsibility3(String responsibility3) 
	{
		this.responsibility3 = responsibility3;
	}

	public String getResponsibility3() 
	{
		return responsibility3;
	}
	public void setResponsibility3Remark(String responsibility3Remark) 
	{
		this.responsibility3Remark = responsibility3Remark;
	}

	public String getResponsibility3Remark() 
	{
		return responsibility3Remark;
	}
	public void setLinkTel3(String linkTel3) 
	{
		this.linkTel3 = linkTel3;
	}

	public String getLinkTel3() 
	{
		return linkTel3;
	}
	public void setBusinessCard3(String businessCard3) 
	{
		this.businessCard3 = businessCard3;
	}

	public String getBusinessCard3() 
	{
		return businessCard3;
	}
	public void setCompanyRemark1(String companyRemark1) 
	{
		this.companyRemark1 = companyRemark1;
	}

	public String getCompanyRemark1() 
	{
		return companyRemark1;
	}
	public void setCompanyRemark2(String companyRemark2) 
	{
		this.companyRemark2 = companyRemark2;
	}

	public String getCompanyRemark2() 
	{
		return companyRemark2;
	}
	public void setCompanyRemark3(String companyRemark3) 
	{
		this.companyRemark3 = companyRemark3;
	}

	public String getCompanyRemark3() 
	{
		return companyRemark3;
	}
	public void setCompanyRemark4(String companyRemark4) 
	{
		this.companyRemark4 = companyRemark4;
	}

	public String getCompanyRemark4() 
	{
		return companyRemark4;
	}
	public void setCompanyRemark5(String companyRemark5) 
	{
		this.companyRemark5 = companyRemark5;
	}

	public String getCompanyRemark5() 
	{
		return companyRemark5;
	}
	public void setCompanyRemark6(String companyRemark6) 
	{
		this.companyRemark6 = companyRemark6;
	}

	public String getCompanyRemark6() 
	{
		return companyRemark6;
	}
	public void setCompanyRemark7(String companyRemark7) 
	{
		this.companyRemark7 = companyRemark7;
	}

	public String getCompanyRemark7() 
	{
		return companyRemark7;
	}
	public void setCompanyRemark8(String companyRemark8) 
	{
		this.companyRemark8 = companyRemark8;
	}

	public String getCompanyRemark8() 
	{
		return companyRemark8;
	}
	public void setCompanyRemark9(String companyRemark9) 
	{
		this.companyRemark9 = companyRemark9;
	}

	public String getCompanyRemark9() 
	{
		return companyRemark9;
	}
	public void setBusinessGrade(String businessGrade) 
	{
		this.businessGrade = businessGrade;
	}

	public String getBusinessGrade() 
	{
		return businessGrade;
	}
	public void setBusinessStatus(String businessStatus) 
	{
		this.businessStatus = businessStatus;
	}

	public String getBusinessStatus() 
	{
		return businessStatus;
	}
	public void setBusinessUser(String businessUser) 
	{
		this.businessUser = businessUser;
	}

	public String getBusinessUser() 
	{
		return businessUser;
	}
	public void setOrderStatus(String orderStatus) 
	{
		this.orderStatus = orderStatus;
	}

	public String getOrderStatus() 
	{
		return orderStatus;
	}
	public void setMedia(String media) 
	{
		this.media = media;
	}

	public String getMedia() 
	{
		return media;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("businessId", getBusinessId())
            .append("companyName", getCompanyName())
            .append("advertiser", getAdvertiser())
            .append("shortName", getShortName())
            .append("linkname1", getLinkname1())
            .append("position1", getPosition1())
            .append("position1Remark", getPosition1Remark())
            .append("responsibility1", getResponsibility1())
            .append("responsibility1Remark", getResponsibility1Remark())
            .append("linkTel1", getLinkTel1())
            .append("businessCard1", getBusinessCard1())
            .append("linkname2", getLinkname2())
            .append("position2", getPosition2())
            .append("position2Remark", getPosition2Remark())
            .append("responsibility2", getResponsibility2())
            .append("responsibility2Remark", getResponsibility2Remark())
            .append("linkTel2", getLinkTel2())
            .append("businessCard2", getBusinessCard2())
            .append("linkname3", getLinkname3())
            .append("position3", getPosition3())
            .append("position3Remark", getPosition3Remark())
            .append("responsibility3", getResponsibility3())
            .append("responsibility3Remark", getResponsibility3Remark())
            .append("linkTel3", getLinkTel3())
            .append("businessCard3", getBusinessCard3())
            .append("companyRemark1", getCompanyRemark1())
            .append("companyRemark2", getCompanyRemark2())
            .append("companyRemark3", getCompanyRemark3())
            .append("companyRemark4", getCompanyRemark4())
            .append("companyRemark5", getCompanyRemark5())
            .append("companyRemark6", getCompanyRemark6())
            .append("companyRemark7", getCompanyRemark7())
            .append("companyRemark8", getCompanyRemark8())
            .append("companyRemark9", getCompanyRemark9())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("businessGrade", getBusinessGrade())
            .append("businessStatus", getBusinessStatus())
            .append("businessUser", getBusinessUser())
            .append("orderStatus", getOrderStatus())
            .append("media", getMedia())
            .toString();
    }
}
