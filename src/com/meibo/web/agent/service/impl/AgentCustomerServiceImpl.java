package com.meibo.web.agent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.agent.service.AgentCustomerService;
import com.meibo.web.agent.vo.AgentCustomerAddVO;
import com.meibo.web.member.service.MemberInfoService;

public class AgentCustomerServiceImpl implements AgentCustomerService {

	@Autowired
	private MemberInfoService memberInfoService;
	
	@Override
	public Boolean addAgentCustomer( AgentCustomerAddVO agentCustomerAddVo ) throws Exception {
		
		if ( memberInfoService.isMemberExist( agentCustomerAddVo.getLoginName() ) ) {
			return false;
		}
		
		return null;
		
	}

}
