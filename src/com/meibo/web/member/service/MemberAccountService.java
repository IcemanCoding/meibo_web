package com.meibo.web.member.service;

import java.math.BigDecimal;

import com.meibo.web.member.dto.AccountDetailListDTO;
import com.meibo.web.member.dto.MemberAccountDTO;
import com.meibo.web.member.viewmodel.AccountDetailQueryViewmodel;

public interface MemberAccountService {
	
	MemberAccountDTO getMemberAccount( Integer memberId ) throws Exception;
	
	void addMemberAccount( Integer memberId, Integer accountType ) throws Exception;
	
	Boolean cosumeMemberAccount( Integer memberId, BigDecimal transAmount );

	Boolean rechargeMemberAccount( Integer memberId, BigDecimal transAmount );

	AccountDetailListDTO getMemberAccountDetail( AccountDetailQueryViewmodel viewmodel );

}
