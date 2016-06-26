package com.meibo.web.order.dto;

import java.util.List;

public class RechargeOfflineListDTO {

	private List<RechargeOfflineDTO> offlineList;
	private Integer totalPages;
	private Integer totalRows;
	
	public List<RechargeOfflineDTO> getOfflineList() {
		return offlineList;
	}
	public void setOfflineList(List<RechargeOfflineDTO> offlineList) {
		this.offlineList = offlineList;
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
