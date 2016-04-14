package com.meibo.web.member.dto;

import java.math.BigDecimal;

public class MemberAccountDTO {
	
	private BigDecimal availableBalance;
	private BigDecimal totalRechargeAmount;
	private BigDecimal totalConsumeAmount;
	private BigDecimal invoiceAmount;
	
	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}
	public BigDecimal getTotalConsumeAmount() {
		return totalConsumeAmount;
	}
	public void setTotalConsumeAmount(BigDecimal totalConsumeAmount) {
		this.totalConsumeAmount = totalConsumeAmount;
	}
	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public BigDecimal getTotalRechargeAmount() {
		return totalRechargeAmount;
	}
	public void setTotalRechargeAmount(BigDecimal totalRechargeAmount) {
		this.totalRechargeAmount = totalRechargeAmount;
	}
	
}
