package com.meibo.web.member.service;

import java.util.Map;

import com.meibo.web.member.dto.CompanyListDTO;
import com.meibo.web.member.viewmodel.AuditCompanyViewmodel;
import com.meibo.web.member.viewmodel.AuthCompanyViewmodel;
import com.meibo.web.member.viewmodel.CompanyListViewmodel;

public interface MemberCompanyService {

	Integer addCompanyInfo( AuthCompanyViewmodel viewmodel ) throws Exception;

	Integer updateCompanyInfo( AuthCompanyViewmodel viewmodel ) throws Exception;

	CompanyListDTO getCompanyList( CompanyListViewmodel viewmodel ) throws Exception;

	Integer auditCompanyInfo( AuditCompanyViewmodel viewmodel ) throws Exception;

	Map<String, Object> isAuthCompany( Integer memberId ) throws Exception;

}
