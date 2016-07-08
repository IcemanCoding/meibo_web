package com.meibo.web.agent.dao;

import java.util.List;

import com.meibo.web.agent.entity.AgentCustomerEntity;

public interface AgentCustomerDAO {
	
	void insertAgentCustomer( AgentCustomerEntity entity );
	
	List<AgentCustomerEntity> selectAgentCustomerList( Integer agentMemberId );

}
