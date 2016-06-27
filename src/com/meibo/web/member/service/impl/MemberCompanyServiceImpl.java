package com.meibo.web.member.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.member.dao.MemberCompanyDAO;
import com.meibo.web.member.dto.CompanyInfoDTO;
import com.meibo.web.member.dto.CompanyListDTO;
import com.meibo.web.member.entity.MemberCompanyEntity;
import com.meibo.web.member.entity.MemberDetailEntity;
import com.meibo.web.member.service.MemberCompanyService;
import com.meibo.web.member.service.MemberDetailService;
import com.meibo.web.member.utils.MemberTransforUtils;
import com.meibo.web.member.viewmodel.AuditCompanyViewmodel;
import com.meibo.web.member.viewmodel.AuthCompanyViewmodel;
import com.meibo.web.member.viewmodel.CompanyListViewmodel;

public class MemberCompanyServiceImpl implements MemberCompanyService {

	@Autowired
	private MemberCompanyDAO memberCompanyDao;
	
	@Autowired
	private MemberDetailService memberDetailService;
	
	@Override
	public Integer addCompanyInfo( AuthCompanyViewmodel viewmodel ) throws Exception {
		
		// check memberId is exist
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "memberId", viewmodel.getMemberId() );
		
		Integer companyId = memberCompanyDao.selectCompanyIdByConditions( params );
		if ( companyId != null ) {
			// 已添加公司信息
			return -2;
		}
		
		// check bizLicense is exist
		params = new HashMap<String, Object>();
		params.put( "bizLicense", viewmodel.getBizLicense() );
		params.put( "status", 1 );
		companyId = memberCompanyDao.selectCompanyIdByConditions( params );
		if ( companyId != null ) {
			// 该公司营业执照号已被认证
			return -3;
		}
		
		MemberCompanyEntity memberCompany = MemberTransforUtils.transAuthCompanyViewmodelToEntity( viewmodel );
		// 待审核
		memberCompany.setStatus( 0 );
		memberCompanyDao.insertMemberCompany( memberCompany );
		
		return 1;
		
	}

	@Override
	public Integer updateCompanyInfo( AuthCompanyViewmodel viewmodel ) throws Exception {
		
		// get memberCompanyEntity by memberId
		MemberCompanyEntity memberCompany = memberCompanyDao.selectMemberCompanyByMemberId( viewmodel.getMemberId() );
		if ( memberCompany == null ) {
			return addCompanyInfo( viewmodel );
		}
		
		// check memberCompany status
		if ( memberCompany.getStatus() == 1 ) {
			// 已通过审核
			return -2;
		}
		
		// check bizLicense is exist
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "bizLicense", viewmodel.getBizLicense() );
		params.put( "status", 1 );
		Integer companyId = memberCompanyDao.selectCompanyIdByConditions( params );
		if ( companyId != null && companyId != memberCompany.getMemberCompanyId() ) {
			// 该公司营业执照号已被认证
			return -3;
		}
		
		memberCompanyDao.updateMemberCompany( viewmodel );
		
		return 1;
		
	}

	@Override
	public CompanyListDTO getCompanyList( CompanyListViewmodel viewmodel ) throws Exception {
		
		Integer pageNum = viewmodel.getPageNum();
		Integer recorders = viewmodel.getPageRecorders();
		viewmodel.setBeginLimit( ( pageNum - 1 ) * recorders );
		viewmodel.setEndLimit( recorders );
		
		List<CompanyInfoDTO> companyInfo = memberCompanyDao.selectCompanyList( viewmodel );
		
		Integer totalPages = 0;
		Integer totalRows = memberCompanyDao.selectCompanyListByCount( viewmodel );
		if ( recorders != null && recorders != 0 ) {
			totalPages = ( totalRows / recorders ) == 0 ? 1 : totalRows / recorders;
		}
		
		CompanyListDTO companyList = new CompanyListDTO();
		companyList.setCompanyInfo( companyInfo );
		companyList.setTotalPages( totalPages );
		companyList.setTotalRows( totalRows );
		
		return companyList;
	}

	@Override
	public Integer auditCompanyInfo( AuditCompanyViewmodel viewmodel ) throws Exception {
		
		// get company entity
		MemberCompanyEntity memberCompany = memberCompanyDao.selectMemberCompanyById( viewmodel.getCompanyId() );
		if ( memberCompany == null ) {
			return -2;
		}
		
		if ( viewmodel.getAuditStatus() == 1 ) {
			
			// 审核通过
			memberCompany.setStatus( 1 );
			
		} else if ( viewmodel.getAuditStatus() == 2 ) {
			
			// 审核拒绝
			memberCompany.setStatus( 2 );
			
		} else {
			return -2;
		}
		memberCompany.setAuditUser( viewmodel.getMemberId() );
		memberCompany.setAuditDate( new Date() );
		memberCompany.setAuditMsg( viewmodel.getAuditMsg() );
		
		memberCompanyDao.authMemberCompany( memberCompany );
		
		// if access update member_detail is_auth_company
		MemberDetailEntity memberDetail = memberDetailService.getOrAddMemberDetail( memberCompany.getMemberId() );
		memberDetail.setIsAuthCompany( 1 );
		
		memberDetailService.updateMemberDetail( memberDetail );
		
		return 1;
		
	}

	@Override
	public Map<String, Object> isAuthCompany( Integer memberId ) throws Exception {
		
		Map<String, Object> resData = new HashMap<String, Object>();
		
		MemberDetailEntity memberDetail = memberDetailService.getOrAddMemberDetail( memberId );
		Integer isAuthCompany = memberDetail.getIsAuthCompany();
		if ( isAuthCompany == null ) {
			isAuthCompany = 0;
		}
		resData.put( "isAuthCompany", isAuthCompany );
		
		return resData;
		
	}

	@Override
	public MemberCompanyEntity getMemberCompany( Integer memberId ) throws Exception {
		return memberCompanyDao.selectMemberCompanyByMemberId( memberId );		
	}

}
