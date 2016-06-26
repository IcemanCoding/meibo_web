package com.meibo.web.member.utils;

import com.alibaba.fastjson.JSONObject;

public class MemberValidateUtils {
	
public static String validateAuthCompanyInputsByJson( JSONObject requestJson ) {
		
		String msg = "";
		
		if ( requestJson.getString( "companyName" ) == null || requestJson.getString( "companyName" ).equals( "" ) ) {
			msg = "请输入公司名称!";
		}
		if ( requestJson.getString( "bizLicense" ) == null || requestJson.getString( "bizLicense" ).equals( "" ) ) {
			msg = "请输入公司营业执照号!";
		}
		if ( requestJson.getString( "companyTel" ) == null || requestJson.getString( "companyTel" ).equals( "" ) ) {
			msg = "请输入公司电话!";
		}
		if ( requestJson.getString( "companyAddr" ) == null || requestJson.getString( "companyAddr" ).equals( "" ) ) {
			msg = "请输入公司地址!";
		}
		if ( requestJson.getString( "legalPersonName" ) == null || requestJson.getString( "legalPersonName" ).equals( "" ) ) {
			msg = "请输入法人姓名!";
		}
		if ( requestJson.getString( "legalPersonTel" ) == null || requestJson.getString( "legalPersonTel" ).equals( "" ) ) {
			msg = "请输入法人联系电话!";
		}
		if ( requestJson.getString( "bizChargeName" ) == null || requestJson.getString( "bizChargeName" ).equals( "" ) ) {
			msg = "请输入商务负责人!";
		}
		if ( requestJson.getString( "bizChargeTel" ) == null || requestJson.getString( "bizChargeTel" ).equals( "" ) ) {
			msg = "请输入商务负责人电话!";
		}
		if ( requestJson.getString( "techChargeName" ) == null || requestJson.getString( "techChargeName" ).equals( "" ) ) {
			msg = "请输入技术负责人!";
		}
		if ( requestJson.getString( "techChargeTel" ) == null || requestJson.getString( "techChargeTel" ).equals( "" ) ) {
			msg = "请输入技术负责人电话!";
		}
		if ( requestJson.getString( "websiteUrl" ) == null || requestJson.getString( "websiteUrl" ).equals( "" ) ) {
			msg = "请输入官方网站!";
		}
		return msg;
		
	}

	public static String validateAuditCompanyInputsByJson( JSONObject requestJson ) {
		
		String msg = "";
		
		if ( requestJson.getInteger( "auditStatus" ) == null || "".equals( requestJson.getInteger( "auditStatus" ) ) ) {
			msg = "请选择审核状态!";
		} else {
			Integer auditStatus = requestJson.getInteger( "auditStatus" );
			if ( auditStatus != 1 || auditStatus != 2 ) {
				msg = "审核状态错误!";
			}
		}
		if ( requestJson.getInteger( "companyId" ) == null || "".equals( requestJson.getInteger( "companyId" ) ) ) {
			msg = "请选择需要审核的信息!";
		}
		return msg;
		
	}

}
