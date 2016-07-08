package com.meibo.web.agent.utils;


import com.alibaba.fastjson.JSONObject;

public class AgentCustomerValidateUtils {
	
	public static String validateAgentCustomerAddInputsByJson( JSONObject requestJson ) {
		
		String errorMsg = "";
		
		if ( requestJson.getString( "customerName" ) == null || "".equals( requestJson.getString( "customerName" ) ) ) {
			errorMsg = "请输入客户名称!";
		}
		if ( requestJson.getBigDecimal( "agentRate" ) == null ) {
			errorMsg = "请输入客户扣点额度!";
		}
		if ( requestJson.getString( "loginName" ) == null || "".equals( requestJson.getString( "loginName" ) ) ) {
			errorMsg = "请输入客户用户名!";
		}
		if ( requestJson.getString( "loginPwd" ) == null || "".equals( requestJson.getString( "loginPwd" ) ) ) {
			errorMsg = "请输入客户密码!";
		}
		
		return errorMsg;
		
	}

}
