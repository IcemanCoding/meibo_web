package com.meibo.web.agent.utils;

import com.alibaba.fastjson.JSONObject;
import com.meibo.web.agent.vo.AgentCustomerAddVO;

public class AgentCustomerTransforUtils {
	
	public static AgentCustomerAddVO transAgentCustomerAddByJson( JSONObject requestJson ) {
		
		AgentCustomerAddVO agentCustomerAddVo = new AgentCustomerAddVO();
		
		agentCustomerAddVo.setAgentRate( requestJson.getBigDecimal( "agentRate" ) );
		agentCustomerAddVo.setCustomerName( requestJson.getString( "customerName" ) );
		agentCustomerAddVo.setLoginName( requestJson.getString( "loginName" ) );
		agentCustomerAddVo.setLoginPwd( requestJson.getString( "loginPwd" ) );
		agentCustomerAddVo.setMemberId( requestJson.getInteger( "memberId" ) );
		
		return agentCustomerAddVo;
		
	}

}
