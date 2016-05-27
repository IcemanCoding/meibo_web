package com.meibo.web.system.dto;

public class CityAreaInfoDTO extends BaseAreaInfoDTO {
	
	private Integer preAreaId;
	private Integer level;
	
	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * @return the preAreaId
	 */
	public Integer getPreAreaId() {
		return preAreaId;
	}
	/**
	 * @param preAreaId the preAreaId to set
	 */
	public void setPreAreaId(Integer preAreaId) {
		this.preAreaId = preAreaId;
	}

}
