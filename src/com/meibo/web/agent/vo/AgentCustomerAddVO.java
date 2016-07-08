package com.meibo.web.agent.vo;

import java.math.BigDecimal;

public class AgentCustomerAddVO {
	
	private String customerName;
	private BigDecimal agentRate;
	private String loginName;
	private String loginPwd;
	private Integer memberId;
	
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
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName( String loginName ) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd( String loginPwd ) {
		this.loginPwd = loginPwd;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId( Integer memberId ) {
		this.memberId = memberId;
	}

}
