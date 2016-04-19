package com.meibo.web.member.entity;

import java.math.BigDecimal;

public class MemberAccountEntity {
	
	private Integer memberId;
	private Integer accountType;
	private BigDecimal availableBalance;
	private BigDecimal totalRechargeAmount;
	private BigDecimal totalConsumeAmount;
	private BigDecimal invoiceAmount;
	private BigDecimal lockBalance;
	private BigDecimal totalBalance;
	private Integer status;
	
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}
	public BigDecimal getTotalRechargeAmount() {
		return totalRechargeAmount;
	}
	public void setTotalRechargeAmount(BigDecimal totalRechargeAmount) {
		this.totalRechargeAmount = totalRechargeAmount;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigDecimal getLockBalance() {
		return lockBalance;
	}
	public void setLockBalance(BigDecimal lockBalance) {
		this.lockBalance = lockBalance;
	}
	public BigDecimal getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(BigDecimal totalBalance) {
		this.totalBalance = totalBalance;
	}

}
