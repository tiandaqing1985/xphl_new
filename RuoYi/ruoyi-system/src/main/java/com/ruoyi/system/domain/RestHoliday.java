package com.ruoyi.system.domain;

public class RestHoliday {
	
	/** 用户id */
	private Long userId;
	/** 用户姓名 */
	private String userName;
	/** 年假余额 */
	private Double restAnnualLeave;
	/** 已用假期 */
	private Double usedAnnualLeave;
	/** 调休余额 */
	private Double restHolidayShift;
	/** 已用调休 */
	private Double usedHolidayShift;
	public Double getRestAnnualLeave() {
		return restAnnualLeave;
	}
	public void setRestAnnualLeave(Double restAnnualLeave) {
		this.restAnnualLeave = restAnnualLeave;
	}
	public Double getUsedAnnualLeave() {
		return usedAnnualLeave;
	}
	public void setUsedAnnualLeave(Double usedAnnualLeave) {
		this.usedAnnualLeave = usedAnnualLeave;
	}
	public Double getRestHolidayShift() {
		return restHolidayShift;
	}
	public void setRestHolidayShift(Double restHolidayShift) {
		this.restHolidayShift = restHolidayShift;
	}
	public Double getUsedHolidayShift() {
		return usedHolidayShift;
	}
	public void setUsedHolidayShift(Double usedHolidayShift) {
		this.usedHolidayShift = usedHolidayShift;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "RestHoliday [restAnnualLeave=" + restAnnualLeave + ", usedAnnualLeave=" + usedAnnualLeave
				+ ", restHolidayShift=" + restHolidayShift + ", usedHolidayShift=" + usedHolidayShift + "]";
	}
	
	
}
