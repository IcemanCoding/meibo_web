package com.meibo.web.member.dao;

import java.util.List;
import java.util.Map;

import com.meibo.web.member.dto.CompanyInfoDTO;
import com.meibo.web.member.entity.MemberCompanyEntity;
import com.meibo.web.member.viewmodel.AuditCompanyViewmodel;
import com.meibo.web.member.viewmodel.AuthCompanyViewmodel;
import com.meibo.web.member.viewmodel.CompanyListViewmodel;

public interface MemberCompanyDAO {

	Integer selectCompanyIdByConditions( Map<String, Object> params );

	void insertMemberCompany( MemberCompanyEntity memberCompany );
	
	MemberCompanyEntity selectMemberCompanyByMemberId( Integer memberId );
	
	MemberCompanyEntity selectMemberCompanyById( Integer companyId );
	
	void updateMemberCompany( AuthCompanyViewmodel params );
	
	void authMemberCompany( MemberCompanyEntity entity );

	List<CompanyInfoDTO> selectCompanyList( CompanyListViewmodel viewmodel );

	Integer selectCompanyListByCount( CompanyListViewmodel viewmodel );
	
}
