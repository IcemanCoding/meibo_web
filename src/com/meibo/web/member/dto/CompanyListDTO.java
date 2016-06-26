package com.meibo.web.member.dto;

import java.util.List;

public class CompanyListDTO {

	private List<CompanyInfoDTO> companyInfo;
	private Integer totalPages;
	private Integer totalRows;
	
	public List<CompanyInfoDTO> getCompanyInfo() {
		return companyInfo;
	}
	public void setCompanyInfo(List<CompanyInfoDTO> companyInfo) {
		this.companyInfo = companyInfo;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public Integer getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}
	
}
