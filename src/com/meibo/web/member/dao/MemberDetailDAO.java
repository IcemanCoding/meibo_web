package com.meibo.web.member.dao;

import com.meibo.web.member.entity.MemberDetailEntity;

public interface MemberDetailDAO {
	
	void insertMemberDetail( MemberDetailEntity entity );
	
	MemberDetailEntity selectMemberDetailByMemberId( Integer memberId );

	void updateMemberDetail( MemberDetailEntity memberDetail );

}
