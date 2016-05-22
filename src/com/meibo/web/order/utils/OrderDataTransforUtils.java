package com.meibo.web.order.utils;

import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.alibaba.fastjson.JSONObject;
import com.meibo.web.order.viewmodel.BaseMediaOrderListQueryViewmodel;
import com.meibo.web.order.viewmodel.BlogMediaCommitOrderViewmodel;
import com.meibo.web.order.viewmodel.NewsMediaCommitOrderViewmodel;
import com.meibo.web.order.viewmodel.WechatMediaCommitOrderViewmodel;

public class OrderDataTransforUtils {
	
	public static NewsMediaCommitOrderViewmodel transNewsMediaCommitOrderByFormData( Map<String, Object> formData ) {
		
		String newsMediaId = formData.get( "newsMediaId" ) + "";
		String[] idStrArr = newsMediaId.split( "," );
		int[] idIntArr = new int[idStrArr.length];
		for (int i = 0; i < idStrArr.length; i++) {
			idIntArr[i] = Integer.parseInt( idStrArr[i] );
		}
		
		NewsMediaCommitOrderViewmodel viewmodel = new NewsMediaCommitOrderViewmodel();
		viewmodel.setExecuteDate( formData.get( "executeDate" ) + "" );
		viewmodel.setNewsMediaId( idIntArr );
		viewmodel.setRemark( formData.get( "remark" ) + "" );
		viewmodel.setResourceLink( formData.get( "resourceLink" ) + "" );
		viewmodel.setTitle( formData.get( "title" ) + "" );
		viewmodel.setUploadFile( ( FileItem ) formData.get( "uploadFile" ) );
		
		return viewmodel;
		
	}
	
	public static BlogMediaCommitOrderViewmodel transBlogMediaCommitOrderByFormData( Map<String, Object> formData ) {
		
		BlogMediaCommitOrderViewmodel viewmodel = new BlogMediaCommitOrderViewmodel();
		
		String blogMediaId = formData.get( "blogMediaId" ) + "";
		String[] idStrArr = blogMediaId.split( "," );
		int[] idIntArr = new int[idStrArr.length];
		for (int i = 0; i < idStrArr.length; i++) {
			idIntArr[i] = Integer.parseInt( idStrArr[i] );
		}
		
		String selectedId = formData.get( "selectedId" ) + "";
		String[] selectStrArr = selectedId.split( "," );
		int[] selectIntArr = new int[selectStrArr.length];
		for (int i = 0; i < selectStrArr.length; i++) {
			selectIntArr[i] = Integer.parseInt( selectStrArr[i] );
		}
		
		viewmodel.setExecuteDate( formData.get( "executeDate" ) + "" );
		viewmodel.setBlogMediaId( idIntArr );
		viewmodel.setRemark( formData.get( "remark" ) + "" );
		viewmodel.setResourceLink( formData.get( "resourceLink" ) + "" );
		viewmodel.setUploadFile( ( FileItem ) formData.get( "uploadFile" ) );
		viewmodel.setContent( formData.get( "content" ) + "" );
		viewmodel.setSelectedId( selectIntArr );
		
		return viewmodel;
		
	}
	
	public static BaseMediaOrderListQueryViewmodel transNewsMediaListQueryByRequest( JSONObject requestJson ) {
		
		BaseMediaOrderListQueryViewmodel viewmodel = new BaseMediaOrderListQueryViewmodel();
		
		if ( requestJson.getString( "orderCode" ) != null && !requestJson.getString( "orderCode" ).equals( "" ) ) {
			viewmodel.setOrderCode( requestJson.getString( "orderCode" ) );
		}
		if ( requestJson.getInteger( "orderStatus" ) != null && !"".equals( requestJson.getInteger( "orderStatus" ) ) ) {
			viewmodel.setOrderStatus( requestJson.getInteger( "orderStatus" ) );
		}
		if ( requestJson.getInteger( "pageNum" ) != null && !"".equals( requestJson.getInteger( "pageNum" ) ) ) {
			viewmodel.setPageNum( requestJson.getInteger( "pageNum" ) );
		} else {
			viewmodel.setPageNum( 1 );
		}
		if ( requestJson.getInteger( "pageRecorders" ) != null && !"".equals( requestJson.getInteger( "pageRecorders" ) ) ) {
			viewmodel.setPageRecorders( requestJson.getInteger( "pageRecorders" ) );
		} else {
			viewmodel.setPageRecorders( 10 );
		}
		
		return viewmodel;
		
	}

	public static WechatMediaCommitOrderViewmodel transWechatMediaCommitOrderByFormData( Map<String, Object> formData ) {
		
		WechatMediaCommitOrderViewmodel viewmodel = new WechatMediaCommitOrderViewmodel();
		
		String wechatMediaId = formData.get( "wechatMediaId" ) + "";
		String[] idStrArr = wechatMediaId.split( "," );
		int[] idIntArr = new int[idStrArr.length];
		for (int i = 0; i < idStrArr.length; i++) {
			idIntArr[i] = Integer.parseInt( idStrArr[i] );
		}
		
		String selectedId = formData.get( "selectedId" ) + "";
		String[] selectStrArr = selectedId.split( "," );
		int[] selectIntArr = new int[selectStrArr.length];
		for (int i = 0; i < selectStrArr.length; i++) {
			selectIntArr[i] = Integer.parseInt( selectStrArr[i] );
		}
		
		viewmodel.setExecuteDate( formData.get( "executeDate" ) + "" );
		viewmodel.setWechatMediaId( idIntArr );
		viewmodel.setRemark( formData.get( "remark" ) + "" );
		viewmodel.setResourceLink( formData.get( "resourceLink" ) + "" );
		viewmodel.setUploadFile( ( FileItem ) formData.get( "uploadFile" ) );
		viewmodel.setContent( formData.get( "content" ) + "" );
		viewmodel.setSelectedId( selectIntArr );
		viewmodel.setOriginalLink( formData.get( "originalLink" ) + "" );
		viewmodel.setTitle( formData.get( "title" ) + "" );
		
		return viewmodel;
		
	}

}
