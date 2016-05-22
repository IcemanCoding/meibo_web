package com.meibo.web.member.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.member.dao.MemberAccountDAO;
import com.meibo.web.member.dto.MemberAccountDTO;
import com.meibo.web.member.entity.MemberAccountEntity;
import com.meibo.web.member.service.MemberAccountService;

public class MemberAccountServiceImpl implements MemberAccountService {

	@Autowired
	private MemberAccountDAO memberAccountDao;
	
	@Override
	public MemberAccountDTO getMemberAccount( Integer memberId ) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put( "memberId", memberId );
		params.put( "accountType", 1 );
		params.put( "status", 1 );
		
		MemberAccountDTO accountDto = memberAccountDao.selectMemberAccountByCondition( params );
		if ( accountDto == null ) {
			return null;
		}
		
		return accountDto;
		
	}

	@Override
	public void addMemberAccount( Integer memberId, Integer accountType ) throws Exception {
		
		MemberAccountEntity accountEntity = new MemberAccountEntity();
		accountEntity.setAccountType( 1 );
		accountEntity.setAvailableBalance( BigDecimal.ZERO );
		accountEntity.setInvoiceAmount( BigDecimal.ZERO );
		accountEntity.setMemberId( memberId );
		accountEntity.setStatus( 1 );
		accountEntity.setTotalConsumeAmount( BigDecimal.ZERO );
		accountEntity.setTotalRechargeAmount( BigDecimal.ZERO );
		accountEntity.setLockBalance( BigDecimal.ZERO );
		accountEntity.setTotalBalance( BigDecimal.ZERO );
		memberAccountDao.insertMemberAccount( accountEntity );
		
	}

	@Override
	public Boolean cosumeMemberAccount( Integer memberId, BigDecimal transAmount ) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "memberId", memberId );
		params.put( "transAmount", transAmount );
		
		memberAccountDao.consumeMemberAccount( params );
		
		return true;
		
	}

}
