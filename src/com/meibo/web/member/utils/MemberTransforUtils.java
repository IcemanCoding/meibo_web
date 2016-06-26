package com.meibo.web.member.utils;

import com.alibaba.fastjson.JSONObject;
import com.meibo.web.member.entity.MemberCompanyEntity;
import com.meibo.web.member.viewmodel.AuditCompanyViewmodel;
import com.meibo.web.member.viewmodel.AuthCompanyViewmodel;
import com.meibo.web.member.viewmodel.CompanyListViewmodel;

public class MemberTransforUtils {

	public static AuthCompanyViewmodel transAuthCompanyByJson( JSONObject requestJson ) {
		
		AuthCompanyViewmodel viewmodel = new AuthCompanyViewmodel();
		
		viewmodel.setBizChargeName( requestJson.getString( "bizChargeName" ) );
		viewmodel.setBizChargeTel( requestJson.getString( "bizChargeTel" ) );
		viewmodel.setBizLicense( requestJson.getString( "bizLicense" ) );
		viewmodel.setCompanyAddr( requestJson.getString( "companyAddr" ) );
		viewmodel.setCompanyName( requestJson.getString( "companyName" ) );
		viewmodel.setCompanyTel( requestJson.getString( "companyTel" ) );
		viewmodel.setLegalPersonName( requestJson.getString( "legalPersonName" ) );
		viewmodel.setLegalPersonTel( requestJson.getString( "legalPersonTel" ) );
		viewmodel.setMemberId( requestJson.getInteger( "memberId" ) );
		viewmodel.setTechChargeName( requestJson.getString( "techChargeName" ) );
		viewmodel.setTechChargeTel( requestJson.getString( "techChargeTel" ) );
		viewmodel.setWebsiteUrl( requestJson.getString( "websiteUrl" ) );
		
		return viewmodel;
		
	}
	
	public static MemberCompanyEntity transAuthCompanyViewmodelToEntity( AuthCompanyViewmodel viewmodel ) {
		
		MemberCompanyEntity entity = new MemberCompanyEntity();
		
		entity.setBizChargeName( viewmodel.getBizChargeName() );
		entity.setBizChargeTel( viewmodel.getBizChargeTel() );
		entity.setBizLicense( viewmodel.getBizLicense() );
		entity.setCompanyAddr( viewmodel.getCompanyAddr() );
		entity.setCompanyName( viewmodel.getCompanyName() );
		entity.setCompanyTel( viewmodel.getCompanyTel() );
		entity.setLegalPersonName( viewmodel.getLegalPersonName() );
		entity.setLegalPersonTel( viewmodel.getLegalPersonTel() );
		entity.setMemberId( viewmodel.getMemberId() );
		entity.setTechChargeName( viewmodel.getTechChargeName() );
		entity.setTechChargeTel( viewmodel.getTechChargeTel() );
		entity.setWebsiteUrl( viewmodel.getWebsiteUrl() );
		
		return entity;
		
	}

	public static CompanyListViewmodel transCompanyListByJson( JSONObject requestJson ) {
		
		CompanyListViewmodel viewmodel = new CompanyListViewmodel();
		
		if ( requestJson.getInteger( "pageNum" ) != null ) {
			viewmodel.setPageNum( requestJson.getInteger( "pageNum" ) );
		} else {
			viewmodel.setPageNum( 1 );
		}
		if ( requestJson.getInteger( "pageRecorders" ) != null ) {
			viewmodel.setPageRecorders( requestJson.getInteger( "pageRecorders" ) );
		} else {
			viewmodel.setPageRecorders( 10 );
		}
		if ( requestJson.getInteger( "auditStatus" ) != null ) {
			viewmodel.setAuditStatus( requestJson.getInteger( "auditStatus" ) );
		}
		viewmodel.setMemberId( requestJson.getInteger( "memberId" ) );
		
		return viewmodel;
	}

	public static AuditCompanyViewmodel transAuditCompanyByJson( JSONObject requestJson ) {

		AuditCompanyViewmodel viewmodel = new AuditCompanyViewmodel();
		
		viewmodel.setCompanyId( requestJson.getInteger( "companyId" ) );
		viewmodel.setAuditStatus( requestJson.getInteger( "auditStatus" ) );
		viewmodel.setMemberId( requestJson.getInteger( "memberId" ) );
		viewmodel.setAuditMsg( requestJson.getString( "auditMsg" ) );
		
		return viewmodel;
		
	}
	

}
