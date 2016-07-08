package com.meibo.web.member.dao;

import java.util.Map;

import com.meibo.web.member.dto.MemberInfoDTO;
import com.meibo.web.member.entity.MemberInfoEntity;

public interface MemberInfoDAO {
	
	MemberInfoDTO selectMemberInfoByConditions( Map<String, Object> params );
	
	Integer selectMemberIdByLoginName( String loginName );
	
	void insertMemberInfo( MemberInfoEntity memberInfoEntity );

	void updateMemberInfo( MemberInfoDTO memberInfo );

}
