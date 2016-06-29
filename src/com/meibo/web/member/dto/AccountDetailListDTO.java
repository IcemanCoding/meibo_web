package com.meibo.web.member.dto;

import java.util.List;

public class AccountDetailListDTO {

	private List<AccountDetailDTO> orderInfo;
	private Integer totalPages;
	private Integer totalRows;
	
	public List<AccountDetailDTO> getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(List<AccountDetailDTO> orderInfo) {
		this.orderInfo = orderInfo;
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
