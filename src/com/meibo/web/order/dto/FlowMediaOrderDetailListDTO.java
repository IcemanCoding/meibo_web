package com.meibo.web.order.dto;

import java.util.List;

public class FlowMediaOrderDetailListDTO {
	
	private List<FlowMediaOrderDetailDTO> orderDetail;
	private Integer totalPages;
	private Integer totalRows;
	
	public List<FlowMediaOrderDetailDTO> getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(List<FlowMediaOrderDetailDTO> orderDetail) {
		this.orderDetail = orderDetail;
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
