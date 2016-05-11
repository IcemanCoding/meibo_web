package com.meibo.web.system.dto;

public class SystemAreaInfoDTO {
	
	private Integer areaId;
	private String areaName;
	private String initialLetters;
	private Integer isHot;
	
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getInitialLetters() {
		return initialLetters;
	}
	public void setInitialLetters(String initialLetters) {
		this.initialLetters = initialLetters;
	}
	public Integer getIsHot() {
		return isHot;
	}
	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}
	
}
