package com.meibo.web.member.dao;

import java.util.Map;

import com.meibo.web.member.dto.MemberAccountDTO;

public interface MemberAccountDAO {
	
	MemberAccountDTO selectMemberAccountByCondition( Map<String, Object> params );

}
