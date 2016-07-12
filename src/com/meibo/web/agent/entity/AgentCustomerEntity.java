package com.meibo.web.agent.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AgentCustomerEntity {
	
	private Integer agentCustomerId;
	private Integer memberId;
	private String customerName;
	private BigDecimal agentRate;
	private BigDecimal totalConsume;
	private BigDecimal totalIncome;
	private Integer agentMemberId;
	private Integer status;
	private Date createDate;
	
	public BigDecimal getTotalConsume() {
		return totalConsume;
	}
	public void setTotalConsume( BigDecimal totalConsume ) {
		this.totalConsume = totalConsume;
	}
	public BigDecimal getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome( BigDecimal totalIncome ) {
		this.totalIncome = totalIncome;
	}
	public Integer getAgentCustomerId() {
		return agentCustomerId;
	}
	public void setAgentCustomerId( Integer agentCustomerId ) {
		this.agentCustomerId = agentCustomerId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId( Integer memberId ) {
		this.memberId = memberId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName( String customerName ) {
		this.customerName = customerName;
	}
	public BigDecimal getAgentRate() {
		return agentRate;
	}
	public void setAgentRate( BigDecimal agentRate ) {
		this.agentRate = agentRate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus( Integer status ) {
		this.status = status;
	}
	public Integer getAgentMemberId() {
		return agentMemberId;
	}
	public void setAgentMemberId( Integer agentMemberId ) {
		this.agentMemberId = agentMemberId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate( Date createDate ) {
		this.createDate = createDate;
	}

}
