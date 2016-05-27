package com.meibo.web.system.dto;

public class ProvinceAreaInfoDTO extends BaseAreaInfoDTO {
	
	private String initialLetters;
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
	 * @return the initialLetters
	 */
	public String getInitialLetters() {
		return initialLetters;
	}
	/**
	 * @param initialLetters the initialLetters to set
	 */
	public void setInitialLetters(String initialLetters) {
		this.initialLetters = initialLetters;
	}

}
