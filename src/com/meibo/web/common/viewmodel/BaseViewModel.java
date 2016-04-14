package com.meibo.web.common.viewmodel;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public class BaseViewModel implements Serializable {
	
	private static final long serialVersionUID = -488465188259319809L;
	
	private String sessionId;
	private Integer memberId;
	private HttpServletRequest request;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	

}
