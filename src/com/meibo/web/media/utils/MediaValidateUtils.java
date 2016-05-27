package com.meibo.web.media.utils;

import com.alibaba.fastjson.JSONObject;

public class MediaValidateUtils {
	
	public static String validateFlowMediaAddInputs( JSONObject requestJson ) {
		
		String msg = "";
		if ( requestJson.getString( "packageId" ) == null || "".equals( requestJson.getString( "packageId" ) ) ) {
			msg = "请选择套餐类型!";
		}
		if ( requestJson.getString( "mobile" ) == null || "".equals( requestJson.getString( "mobile" ) ) ) {
			msg = "请输入手机号!";
		}
		return msg;
		
	}

	public static String validateFlowMediaBatchAddInputs( JSONObject requestJson ) {
		
		String msg = "";
		if ( requestJson.getString( "dxPackageId" ) == null || "".equals( requestJson.getString( "dxPackageId" ) ) ) {
			msg = "请选择套餐类型!";
		}
		if ( requestJson.getString( "ltPackageId" ) == null || "".equals( requestJson.getString( "ltPackageId" ) ) ) {
			msg = "请选择套餐类型!";
		}
		if ( requestJson.getString( "ydPackageId" ) == null || "".equals( requestJson.getString( "ydPackageId" ) ) ) {
			msg = "请选择套餐类型!";
		}
		if ( requestJson.getString( "mobile" ) == null || "".equals( requestJson.getString( "mobile" ) ) ) {
			msg = "请输入手机号!";
		}
		if ( requestJson.getString( "orderName" ) == null || "".equals( requestJson.getString( "orderName" ) ) ) {
			msg = "请输入活动名称!";
		}
		return msg;
		
	}

	public static String validateFlowMediaPayInputs( JSONObject requestJson ) {
		
		String msg = "";
		if ( requestJson.getString( "orderId" ) == null || "".equals( requestJson.getString( "orderId" ) ) ) {
			msg = "请选择订单!";
		}
		return msg;
		
	}

}
