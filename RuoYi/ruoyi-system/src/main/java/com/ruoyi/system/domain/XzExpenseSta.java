package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资产表 xz_expense_record
 * 
 * @author ruoyi
 * @date 
 */
public class XzExpenseSta extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	/** 采购类型*/
	private Long expenseTypeParent;
	private String expenseTypeParentName;
	/** 采购分类*/
	private Long expenseType;
	private String expenseTypeName;
	/** 地域*/
	private String region; 
	/** 年*/
	private String year;
	/** 部门 */
	private String dept;
	/** 部门 查询用 */
	private String[] depts;
	/** 月总金额*/
	private Double month1;
	private Double month2;
	private Double month3;
	private Double month4;
	private Double month5;
	private Double month6;
	private Double month7;
	private Double month8;
	private Double month9;
	private Double month10;
	private Double month11;
	private Double month12;
	/** 合计*/
	private Double sum;

	public String[] getDepts() {
		return depts;
	}

	public void setDepts(String[] depts) {
		this.depts = depts;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Double getMonth1() {
		return month1;
	}
	public void setMonth1(Double month1) {
		this.month1 = month1;
	}
	public Double getMonth2() {
		return month2;
	}
	public void setMonth2(Double month2) {
		this.month2 = month2;
	}
	public Double getMonth3() {
		return month3;
	}
	public void setMonth3(Double month3) {
		this.month3 = month3;
	}
	public Double getMonth4() {
		return month4;
	}
	public void setMonth4(Double month4) {
		this.month4 = month4;
	}
	public Double getMonth5() {
		return month5;
	}
	public void setMonth5(Double month5) {
		this.month5 = month5;
	}
	public Double getMonth6() {
		return month6;
	}
	public void setMonth6(Double month6) {
		this.month6 = month6;
	}
	public Double getMonth7() {
		return month7;
	}
	public void setMonth7(Double month7) {
		this.month7 = month7;
	}
	public Double getMonth8() {
		return month8;
	}
	public void setMonth8(Double month8) {
		this.month8 = month8;
	}
	public Double getMonth9() {
		return month9;
	}
	public void setMonth9(Double month9) {
		this.month9 = month9;
	}
	public Double getMonth10() {
		return month10;
	}
	public void setMonth10(Double month10) {
		this.month10 = month10;
	}
	public Double getMonth11() {
		return month11;
	}
	public void setMonth11(Double month11) {
		this.month11 = month11;
	}
	public Double getMonth12() {
		return month12;
	}
	public void setMonth12(Double month12) {
		this.month12 = month12;
	}
	public Long getExpenseTypeParent() {
		return expenseTypeParent;
	}
	public void setExpenseTypeParent(Long expenseTypeParent) {
		this.expenseTypeParent = expenseTypeParent;
	}
	public String getExpenseTypeParentName() {
		return expenseTypeParentName;
	}
	public void setExpenseTypeParentName(String expenseTypeParentName) {
		this.expenseTypeParentName = expenseTypeParentName;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Long getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(Long expenseType) {
		this.expenseType = expenseType;
	}
	public String getExpenseTypeName() {
		return expenseTypeName;
	}
	public void setExpenseTypeName(String expenseTypeName) {
		this.expenseTypeName = expenseTypeName;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}
	
	
	
	
}