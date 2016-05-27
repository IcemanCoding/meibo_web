package com.meibo.web.order.dto;

import java.util.List;

public class FlowMediaOrderListDTO {
	
	private List<FlowMediaOrderDTO> orderList;
	private Integer totalPages;
	private Integer totalRows;
	
	public List<FlowMediaOrderDTO> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<FlowMediaOrderDTO> orderList) {
		this.orderList = orderList;
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
