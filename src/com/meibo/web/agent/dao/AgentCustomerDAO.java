package com.meibo.web.agent.dao;

import java.math.BigDecimal;
import java.util.List;

import com.meibo.web.agent.entity.AgentCustomerEntity;

public interface AgentCustomerDAO {
	
	void insertAgentCustomer( AgentCustomerEntity entity );
	
	List<AgentCustomerEntity> selectAgentCustomerList( Integer agentMemberId );
	
	void updateAgentCustomer( AgentCustomerEntity entity );
	
	Integer countAgentCustomerByAgent( Integer agentMemberId );
	
	BigDecimal totalConsumeByAgent( Integer agentMemberId );
	
	BigDecimal totalIncomeByAgent( Integer agentMemberId );

}
