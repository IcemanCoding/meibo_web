package com.meibo.web.member.service;

import com.meibo.web.member.dto.MemberAccountDTO;

public interface MemberAccountService {
	
	MemberAccountDTO getMemberAccount( Integer memberId ) throws Exception;
	
	void addMemberAccount( Integer memberId, Integer accountType ) throws Exception;

}
