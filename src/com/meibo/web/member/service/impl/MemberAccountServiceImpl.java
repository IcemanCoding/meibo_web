package com.meibo.web.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.member.dao.MemberAccountDAO;
import com.meibo.web.member.dto.MemberAccountDTO;
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

}
