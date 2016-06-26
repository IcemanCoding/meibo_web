package com.meibo.web.member.service;

import com.meibo.web.member.entity.MemberDetailEntity;

public interface MemberDetailService {

	void addMemberDetail( Integer memberId ) throws Exception;
	
	MemberDetailEntity getOrAddMemberDetail( Integer memberId ) throws Exception;

	void updateMemberDetail( MemberDetailEntity memberDetail ) throws Exception;
	
}
