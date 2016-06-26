package com.meibo.web.order.utils;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class ValidOrderInputsUtils {
	
	public static String validNewsMediaCommitOrderInputs( Map<String, Object> formData ) {
		
		String retMsg = "";
		
		if ( formData.get( "newsMediaId" ) == null || formData.get( "newsMediaId" ).equals( "" ) ) {
			retMsg = "请选择媒体!";
		}
		if ( formData.get( "executeDate" ) == null || formData.get( "executeDate" ).equals( "" ) ) {
			retMsg = "请选择执行时间!";
		}
		if ( formData.get( "title" ) == null || formData.get( "title" ).equals( "" ) ) {
			retMsg = "请输入标题内容!";
		}
		if ( formData.get( "resourceLink" ) == null || formData.get( "resourceLink" ).equals( "" ) ) {
			retMsg = "请输入来源链接!";
		}
		if ( formData.get( "uploadFile" ) == null || formData.get( "uploadFile" ).equals( "" ) ) {
			retMsg = "请上传附件!";
		}
		
		return retMsg;
		
	}

	public static String validBlogMediaCommitOrderInputs( Map<String, Object> formData ) {
		
		String retMsg = "";
		
		if ( formData.get( "selectedId" ) == null || formData.get( "selectedId" ).equals( "" ) ) {
			retMsg = "请选择媒体投放类型!";
		}
		if ( formData.get( "blogMediaId" ) == null || formData.get( "blogMediaId" ).equals( "" ) ) {
			retMsg = "请选择媒体!";
		}
		if ( formData.get( "executeDate" ) == null || formData.get( "executeDate" ).equals( "" ) ) {
			retMsg = "请选择执行时间!";
		}
		if ( formData.get( "uploadFile" ) == null || formData.get( "uploadFile" ).equals( "" ) ) {
			retMsg = "请上传附件!";
		}
		
		return retMsg;
		
	}

	public static String validWechatMediaCommitOrderInputs( Map<String, Object> formData ) {

		String retMsg = "";
		
		if ( formData.get( "selectedId" ) == null || formData.get( "selectedId" ).equals( "" ) ) {
			retMsg = "请选择媒体投放类型!";
		}
		if ( formData.get( "wechatMediaId" ) == null || formData.get( "wechatMediaId" ).equals( "" ) ) {
			retMsg = "请选择媒体!";
		}
		if ( formData.get( "executeDate" ) == null || formData.get( "executeDate" ).equals( "" ) ) {
			retMsg = "请选择执行时间!";
		}
		if ( formData.get( "content" ) == null || formData.get( "content" ).equals( "" ) ) {
			retMsg = "请输入标题内容!";
		}
		if ( formData.get( "originalLink" ) == null || formData.get( "originalLink" ).equals( "" ) ) {
			retMsg = "请输入原文链接!";
		}
		if ( formData.get( "resourceLink" ) == null || formData.get( "resourceLink" ).equals( "" ) ) {
			retMsg = "请输入来源链接!";
		}
		if ( formData.get( "uploadFile" ) == null || formData.get( "uploadFile" ).equals( "" ) ) {
			retMsg = "请上传附件!";
		}
		if ( formData.get( "title" ) == null || formData.get( "title" ).equals( "" ) ) {
			retMsg = "请输入标题!";
		}
		
		return retMsg;
		
	}
	
	public static String validRechargeOfflineCommitInputs( JSONObject requestJson ) {
		
		String retMsg = "";
		
		if ( requestJson.getInteger( "platformId" ) == null ) {
			retMsg = "请选择充值平台!";
		}
		if ( requestJson.getBigDecimal( "transAmount" ) == null ) {
			retMsg = "请输入转账金额";
		}
		if ( requestJson.getString( "voucherNum" ) == null || "".equals( requestJson.getString( "voucherNum" ) ) ) {
			retMsg = "请输入转账凭证号";
		}
			
		return retMsg;
		
	}

	public static String validRechargeOfflineEditInputs( JSONObject requestJson ) {
		
		String retMsg = "";
		
		if ( requestJson.getInteger( "orderId" ) == null ) {
			retMsg = "请选择要修改的订单!";
		}
		if ( requestJson.getBigDecimal( "transAmount" ) == null ) {
			retMsg = "请输入转账金额!";
		}
			
		return retMsg;
		
	}

	public static String validRechargeOfflineAuditInputs( JSONObject requestJson ) {
		
		String retMsg = "";
		
		if ( requestJson.getInteger( "orderId" ) == null ) {
			retMsg = "请选择要审核的订单!";
		}
		if ( requestJson.getInteger( "auditStatus" ) == null ) {
			retMsg = "请确认审核状态!";
		}
			
		return retMsg;
		
	}

}
