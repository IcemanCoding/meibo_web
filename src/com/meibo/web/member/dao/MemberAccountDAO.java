package com.meibo.web.member.dao;

import java.util.Map;

import com.meibo.web.member.dto.MemberAccountDTO;
import com.meibo.web.member.entity.MemberAccountEntity;

public interface MemberAccountDAO {
	
	MemberAccountDTO selectMemberAccountByCondition( Map<String, Object> params );
	
	void insertMemberAccount( MemberAccountEntity accountEntity );
	
	void consumeMemberAccount( Map<String, Object> params );

}
