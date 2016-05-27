package com.meibo.web.media.utils;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.alibaba.fastjson.JSONObject;
import com.meibo.web.media.entity.NewsMediaEntity;
import com.meibo.web.media.viewmodel.AdminBlogMediaListQueryViewmodel;
import com.meibo.web.media.viewmodel.AdminWechatMediaListQueryViewmodel;
import com.meibo.web.media.viewmodel.BlogMediaInsertViewmodel;
import com.meibo.web.media.viewmodel.BlogMediaUpdateViewmodel;
import com.meibo.web.media.viewmodel.MemberBlogMediaListQueryViewmodel;
import com.meibo.web.media.viewmodel.MemberWechatMediaListQueryViewmodel;
import com.meibo.web.media.viewmodel.NewsMediaListQueryParams;
import com.meibo.web.media.viewmodel.WechatMediaInsertViewmodel;
import com.meibo.web.media.viewmodel.WechatMediaUpdateViewmodel;
import com.meibo.web.order.viewmodel.FlowMediaOrderAddViewmodel;
import com.meibo.web.order.viewmodel.FlowMediaOrderBatchAddViewmodel;
import com.meibo.web.order.viewmodel.FlowMediaOrderPayViewmodel;

public class MediaTransforUtils {
	
	public static NewsMediaListQueryParams transJsonToQueryParams( JSONObject requestJson ) {
		
		NewsMediaListQueryParams viewmodel = new NewsMediaListQueryParams();
		
		if ( requestJson.getInteger( "areaId" ) != null && !"".equals( requestJson.getInteger( "areaId" ) )) {
			viewmodel.setAreaId( requestJson.getInteger( "areaId" ) );
		}
		if ( requestJson.getString( "channelName" ) != null && !"".equals( requestJson.getString( "channelName" ) ) ) {
			viewmodel.setChannelName( requestJson.getString( "channelName" ) );
		}
		if ( requestJson.getInteger( "includeType" ) != null && !"".equals( requestJson.getInteger( "includeType" ) ) ) {
			viewmodel.setIncludeType( requestJson.getInteger( "includeType" ) );
		}
		if ( requestJson.getInteger( "memberId" ) != null ) {
			viewmodel.setMemberId( requestJson.getInteger( "memberId" ) );
		}
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
		if ( requestJson.getInteger( "typeId" ) != null && !"".equals( requestJson.getInteger( "typeId" ) ) ) {
			viewmodel.setTypeId( requestJson.getInteger( "typeId" ) );
		}
		if ( requestJson.getBigDecimal( "maxPrice" ) != null && !"".equals( requestJson.getBigDecimal( "maxPrice" ) ) ) {
			viewmodel.setMaxPrice( requestJson.getBigDecimal( "maxPrice" ) );
		}
		if ( requestJson.getBigDecimal( "minPrice" ) != null && !"".equals( requestJson.getBigDecimal( "minPrice" ) ) ) {
			viewmodel.setMinPrice( requestJson.getBigDecimal( "minPrice" ) );
		}
		if ( requestJson.getInteger( "auditStatus") != null && !"".equals( requestJson.getInteger( "auditStatus") ) ) {
			viewmodel.setAuditStatus( requestJson.getInteger( "auditStatus" ) );
		}
		
		return viewmodel;
		
	}
	
	public static NewsMediaEntity transMapToNewsMediaEntity( Map<String, Object> data ) {
		
		NewsMediaEntity newsMediaEntity = new NewsMediaEntity();
		
		if ( data.get( "channelName" ) != null && !"".equals( data.get( "channelName" ) + "" ) ) {
			newsMediaEntity.setChannelName( data.get( "channelName" ) + "" );
		}
		if ( data.get( "channelType" ) != null && !"".equals( data.get( "channelType" ) + "" ) ) {
			newsMediaEntity.setChannelType( data.get( "channelType" ) + "" );
		}
		if ( data.get( "channelColumn" ) != null && !"".equals( data.get( "channelColumn" ) + "" ) ) {
			newsMediaEntity.setChannelColumn( data.get( "channelColumn" ) + "" );
		}
		if ( data.get( "areaId" ) != null && !"".equals( data.get( "areaId" ) ) ) {
			newsMediaEntity.setAreaId( Integer.parseInt( data.get( "areaId" ) + "" ) );
		}
		if ( data.get( "includeType" ) != null && !"".equals( data.get( "includeType" ) )  ) {
			newsMediaEntity.setIncludeType( Integer.parseInt( data.get( "includeType" ) + "" ) );
		}
		if ( data.get( "quotePrice" ) != null && !"".equals( data.get( "quotePrice" ) )  ) {
			newsMediaEntity.setQuotePrice( new BigDecimal( data.get( "quotePrice" ) + "" ) );
		}
		if ( data.get( "remark" ) != null && !"".equals( data.get( "remark" ) + "" ) ) {
			newsMediaEntity.setRemark( data.get( "remark" ) + "" );
		}
		if ( data.get( "linkUrl" ) != null && !"".equals( data.get( "linkUrl" ) + "" ) ) {
			newsMediaEntity.setLinkUrl( data.get( "linkUrl" ) + "" );
		}
		if ( data.get( "title" ) != null && !"".equals( data.get( "title" ) + "" ) ) {
			newsMediaEntity.setTitle( data.get( "title" ) + "" );
		}
		if ( data.get( "allowLink" ) != null && !"".equals( data.get( "allowLink" ) )  ) {
			newsMediaEntity.setAllowLink( Integer.parseInt( data.get( "allowLink" ) + "" ) );
		}
		if ( data.get( "allowQRCode" ) != null && !"".equals( data.get( "allowQRCode" ) )  ) {
			newsMediaEntity.setAllowQRCode( Integer.parseInt( data.get( "allowQRCode" ) + "" ) );
		}
		if ( data.get( "allowContactWay" ) != null && !"".equals( data.get( "allowContactWay" ) )  ) {
			newsMediaEntity.setAllowContactWay( Integer.parseInt( data.get( "allowContactWay" ) + "" ) );
		}
		if ( data.get( "memberId" ) != null  ) {
			newsMediaEntity.setCreatedUser( Integer.parseInt( data.get( "memberId" ) + "" ) );
		}
		if ( data.get( "newsMediaId" ) != null && !"".equals( data.get( "newsMediaId" ) )  ) {
			newsMediaEntity.setNewsMediaId( Integer.parseInt( data.get( "newsMediaId" ) + "" ) );
		}
		
		return newsMediaEntity;
		
	}
	
	public static WechatMediaInsertViewmodel transMapToWechatMediaInsert( Map<String, Object> data ) {
		
		WechatMediaInsertViewmodel viewmodel = new WechatMediaInsertViewmodel();
		
		if ( data.get( "nickname" ) != null && !"".equals( "nickname" ) ) {
			viewmodel.setNickName( data.get( "nickname" ) + "" );
		}
		if ( data.get( "account" ) != null && !"".equals( "account" ) ) {
			viewmodel.setAccount( data.get( "account" ) + "" );
		}
		if ( data.get( "desc" ) != null && !"".equals( "desc" ) ) {
			viewmodel.setDesc( data.get( "desc" ) + "" );
		}
		if ( data.get( "authInfo" ) != null && !"".equals( "authInfo" ) ) {
			viewmodel.setAuthentication( data.get( "authInfo" ) + "" );
		}
		if ( data.get( "typeName" ) != null && !"".equals( "typeName" ) ) {
			viewmodel.setTypeName( data.get( "typeName" ) + "" );
		}
		if ( data.get( "areaId" ) != null ) {
			viewmodel.setAreaId( Integer.parseInt( data.get( "areaId" ) + "" ) );
		}
		if ( data.get( "firstPrice" ) != null ) {
			viewmodel.setFirstPrice( new BigDecimal( data.get( "firstPrice" ) + "" ) );
		}
		if ( data.get( "secondPrice" ) != null ) {
			viewmodel.setSecondPrice( new BigDecimal( data.get( "secondPrice" ) + "" ) );
		}
		if ( data.get( "otherPrice" ) != null ) {
			viewmodel.setOtherPrice( new BigDecimal( data.get( "otherPrice" ) + "" ) );
		}
		if ( data.get( "remark" ) != null && !"".equals( "remark" ) ) {
			viewmodel.setRemark( data.get( "remark" ) + "" );
		}
		if ( data.get( "memberId" ) != null ) {
			viewmodel.setMemberId( Integer.parseInt( data.get( "memberId" ) + "" ) );
		}
		if ( data.get( "fansCount" ) != null ) {
			viewmodel.setFansCount( Integer.parseInt( data.get( "fansCount" ) + "" ) );
		}
		
		return viewmodel;
		
	}
	
	public static AdminWechatMediaListQueryViewmodel transJsonToAdminMediaQuery( JSONObject requestJson ) {
		
		AdminWechatMediaListQueryViewmodel viewmodel = new AdminWechatMediaListQueryViewmodel();
		
		if ( requestJson.getInteger( "memberId" ) != null ) {
			viewmodel.setMemberId( requestJson.getInteger( "memberId" ) );
		}
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
		if ( requestJson.getInteger( "auditStatus" ) != null && !"".equals( requestJson.getInteger( "auditStatus" ) ) ) {
			viewmodel.setAuditStatus( requestJson.getInteger( "auditStatus" ) );
		}
		if ( requestJson.getString( "channelName" ) != null && !"".equals( requestJson.getString( "channelName" ) ) ) {
			viewmodel.setMediaName( requestJson.getString( "channelName" ) );
		}
		
		return viewmodel;
		
	}
	
	public static AdminBlogMediaListQueryViewmodel transJsonToAdminBlogMediaQuery( JSONObject requestJson ) {
		
		AdminBlogMediaListQueryViewmodel viewmodel = new AdminBlogMediaListQueryViewmodel();
		
		if ( requestJson.getInteger( "memberId" ) != null ) {
			viewmodel.setMemberId( requestJson.getInteger( "memberId" ) );
		}
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
		if ( requestJson.getInteger( "auditStatus" ) != null && !"".equals( requestJson.getInteger( "auditStatus" ) ) ) {
			viewmodel.setAuditStatus( requestJson.getInteger( "auditStatus" ) );
		}
		if ( requestJson.getString( "channelName" ) != null && !"".equals( requestJson.getString( "channelName" ) ) ) {
			viewmodel.setMediaName( requestJson.getString( "channelName" ) );
		}
		
		return viewmodel;
		
	}
	
	public static MemberWechatMediaListQueryViewmodel transJsonToMemberMediaQuery( JSONObject requestJson ) {
		
		MemberWechatMediaListQueryViewmodel viewmodel = new MemberWechatMediaListQueryViewmodel();
		
		if ( requestJson.getInteger( "memberId" ) != null ) {
			viewmodel.setMemberId( requestJson.getInteger( "memberId" ) );
		}
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
		if ( requestJson.getString( "typeIds" ) != null && !"".equals( requestJson.getString( "typeIds" ) ) ) {
			viewmodel.setTypeIds( requestJson.getString( "typeIds" ) );
		}
		if ( requestJson.getString( "channelName" ) != null && !"".equals( requestJson.getString( "channelName" ) ) ) {
			viewmodel.setMediaName( requestJson.getString( "channelName" ) );
		}
		if ( requestJson.getBigDecimal( "minPrice" ) != null ) {
			viewmodel.setMinPrice( requestJson.getBigDecimal( "minPrice" ) );
		}
		if ( requestJson.getBigDecimal( "maxPrice" ) != null ) {
			viewmodel.setMaxPrice( requestJson.getBigDecimal( "maxPrice" ) );
		}
		if ( requestJson.getInteger( "minFansCount" ) != null ) {
			viewmodel.setMinFansCount( requestJson.getInteger( "minFansCount" ) );
		}
		if ( requestJson.getInteger( "maxFansCount" ) != null ) {
			viewmodel.setMaxFansCount( requestJson.getInteger( "maxFansCount" ) );
		}
		if ( requestJson.getString( "areaIds" ) != null && !"".equals( requestJson.getString( "areaIds" ) ) ) {
			viewmodel.setAreaIds( requestJson.getString( "areaIds" ) );
		}
		
		return viewmodel;
		
	}
	
	public static MemberBlogMediaListQueryViewmodel transJsonToMemberBlogMediaQuery( JSONObject requestJson ) {
		
		MemberBlogMediaListQueryViewmodel viewmodel = new MemberBlogMediaListQueryViewmodel();
		
		if ( requestJson.getInteger( "memberId" ) != null ) {
			viewmodel.setMemberId( requestJson.getInteger( "memberId" ) );
		}
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
		if ( requestJson.getString( "typeIds" ) != null && !"".equals( requestJson.getString( "typeIds" ) ) ) {
			viewmodel.setTypeIds( requestJson.getString( "typeIds" ) );
		}
		if ( requestJson.getString( "channelName" ) != null && !"".equals( requestJson.getString( "channelName" ) ) ) {
			viewmodel.setMediaName( requestJson.getString( "channelName" ) );
		}
		if ( requestJson.getBigDecimal( "minPrice" ) != null && !"".equals( requestJson.getBigDecimal( "minPrice" ) ) ) {
			viewmodel.setMinPrice( requestJson.getBigDecimal( "minPrice" ) );
		}
		if ( requestJson.getBigDecimal( "maxPrice" ) != null && !"".equals( requestJson.getBigDecimal( "maxPrice" ) ) ) {
			viewmodel.setMaxPrice( requestJson.getBigDecimal( "maxPrice" ) );
		}
		if ( requestJson.getInteger( "minFansCount" ) != null && !"".equals( requestJson.getInteger( "minFansCount" ) ) ) {
			viewmodel.setMinFansCount( requestJson.getInteger( "minFansCount" ) );
		}
		if ( requestJson.getInteger( "maxFansCount" ) != null && !"".equals( requestJson.getInteger( "maxFansCount" ) ) ) {
			viewmodel.setMaxFansCount( requestJson.getInteger( "maxFansCount" ) );
		}
		if ( requestJson.getString( "areaIds" ) != null && !"".equals( requestJson.getString( "areaIds" ) ) ) {
			viewmodel.setAreaIds( requestJson.getString( "areaIds" ) );
		}
		
		return viewmodel;
		
	}
	
	public static WechatMediaUpdateViewmodel transMapToWechatMediaUpdate( Map<String, Object> data ) {
		
		WechatMediaUpdateViewmodel viewmodel = new WechatMediaUpdateViewmodel();
		
		if ( data.get( "wechatMediaId" ) != null ) {
			viewmodel.setWechatMediaId( Integer.parseInt( data.get( "wechatMediaId" ) + "" ) );
		}
		if ( data.get( "areaId" ) != null && !"".equals( data.get( "areaId" ) ) ) {
			viewmodel.setAreaId( Integer.parseInt( data.get( "areaId" ) + "" ) );
		}
		if ( data.get( "firstPrice" ) != null && !"".equals( data.get( "firstPrice" ) ) ) {
			viewmodel.setFirstPrice( new BigDecimal( data.get( "firstPrice" ) + "" ) );
		}
		if ( data.get( "secondPrice" ) != null && !"".equals( data.get( "secondPrice" ) ) ) {
			viewmodel.setSecondPrice( new BigDecimal( data.get( "secondPrice" ) + "" ) );
		}
		if ( data.get( "otherPrice" ) != null && !"".equals( data.get( "otherPrice" ) ) ) {
			viewmodel.setOtherPrice( new BigDecimal( data.get( "otherPrice" ) + "" ) );
		}
		if ( data.get( "remark" ) != null && !"".equals( data.get( "remark" ) ) ) {
			viewmodel.setRemark( data.get( "remark" ) + "" );
		}
		if ( data.get( "nickname" ) != null && !"".equals( data.get( "nickname" ) ) ) {
			viewmodel.setNickname( data.get( "nickname" ) + "" );
		}
		if ( data.get( "account" ) != null && !"".equals( data.get( "account" ) ) ) {
			viewmodel.setAccount( data.get( "account" ) + "" );
		}
		if ( data.get( "fansCount" ) != null && !"".equals( data.get( "fansCount" ) ) ) {
			viewmodel.setFansCount( Integer.parseInt( data.get( "fansCount" ) + "" ) );
		}
		if ( data.get( "file" ) != null && !"".equals( data.get( "file" ) ) ) {
			viewmodel.setFile( ( FileItem ) data.get( "file" ) );
		}
		if ( data.get( "qrCode" ) != null && !"".equals( data.get( "qrCode" ) ) ) {
			viewmodel.setQrCode( ( FileItem ) data.get( "qrCode" ) );
		}
		if ( data.get( "desc" ) != null && !"".equals( data.get( "desc" ) ) ) {
			viewmodel.setDesc( data.get( "desc" ) + "" );
		}
		if ( data.get( "authInfo" ) != null && !"".equals( data.get( "authInfo" ) ) ) {
			viewmodel.setAuthentication( data.get( "authInfo" ) + "" );
		}
		if ( data.get( "typeName" ) != null && !"".equals( data.get( "typeName" ) ) ) {
			viewmodel.setTypeName( data.get( "typeName" ) + "" );
		}
		
		return viewmodel;
		
	}
	
	public static BlogMediaUpdateViewmodel transMapToBlogMediaUpdate( Map<String, Object> data ) {
		
		BlogMediaUpdateViewmodel viewmodel = new BlogMediaUpdateViewmodel();
		
		if ( data.get( "blogMediaId" ) != null && !"".equals( data.get( "blogMediaId" ) ) ) {
			viewmodel.setBlogMediaId( Integer.parseInt( data.get( "blogMediaId" ) + "" ) );
		}
		if ( data.get( "areaId" ) != null && !"".equals( data.get( "areaId" ) ) ) {
			viewmodel.setAreaId( Integer.parseInt( data.get( "areaId" ) + "" ) );
		}
		if ( data.get( "publishPrice" ) != null && !"".equals( data.get( "publishPrice" ) ) ) {
			viewmodel.setPublishPrice( new BigDecimal( data.get( "publishPrice" ) + "" ) );
		}
		if ( data.get( "forwardPrice" ) != null && !"".equals( data.get( "forwardPrice" ) ) ) {
			viewmodel.setForwardPrice( new BigDecimal( data.get( "forwardPrice" ) + "" ) );
		}
		if ( data.get( "remark" ) != null && !"".equals( data.get( "remark" ) ) ) {
			viewmodel.setRemark( data.get( "remark" ) + "" );
		}
		if ( data.get( "nickname" ) != null && !"".equals( data.get( "nickname" ) ) ) {
			viewmodel.setNickname( data.get( "nickname" ) + "" );
		}
		if ( data.get( "fansCount" ) != null && !"".equals( data.get( "fansCount" ) ) ) {
			viewmodel.setFansCount( Integer.parseInt( data.get( "fansCount" ) + "" ) );
		}
		if ( data.get( "file" ) != null && !"".equals( data.get( "file" ) ) ) {
			viewmodel.setFile( ( FileItem ) data.get( "file" ) );
		}
		if ( data.get( "qrCode" ) != null && !"".equals( data.get( "qrCode" ) ) ) {
			viewmodel.setQrCode( ( FileItem ) data.get( "qrCode" ) );
		}
		if ( data.get( "desc" ) != null && !"".equals( data.get( "desc" ) ) ) {
			viewmodel.setDesc( data.get( "desc" ) + "" );
		}
		if ( data.get( "typeName" ) != null && !"".equals( data.get( "typeName" ) ) ) {
			viewmodel.setTypeName( data.get( "typeName" ) + "" );
		}
		if ( data.get( "authInfo" ) != null && !"".equals( data.get( "authInfo" ) ) ) {
			viewmodel.setAuthInfo( data.get( "authInfo" ) + "" );
		}
		if ( data.get( "authType" ) != null && !"".equals( data.get( "authType" ) ) ) {
			viewmodel.setAuthType( Integer.parseInt( data.get( "authType" ) + "" ) );
		}
		if ( data.get( "registerDate" ) != null && !"".equals( data.get( "registerDate" ) ) ) {
			viewmodel.setRegisterDate( data.get( "registerDate" ) + "" );
		}
		
		return viewmodel;
		
	}
	
	public static BlogMediaInsertViewmodel transMapToBlogMediaInsert( Map<String, Object> data ) {
		
		BlogMediaInsertViewmodel viewmodel = new BlogMediaInsertViewmodel();
		
		if ( data.get( "nickname" ) != null && !"".equals( "nickname" ) ) {
			viewmodel.setNickname( data.get( "nickname" ) + "" );
		}
		if ( data.get( "registerDate" ) != null && !"".equals( "registerDate" ) ) {
			viewmodel.setRegisterDate( data.get( "registerDate" ) + "" );
		}
		if ( data.get( "desc" ) != null && !"".equals( "desc" ) ) {
			viewmodel.setDesc( data.get( "desc" ) + "" );
		}
		if ( data.get( "typeName" ) != null && !"".equals( "typeName" ) ) {
			viewmodel.setTypeName( data.get( "typeName" ) + "" );
		}
		if ( data.get( "areaId" ) != null ) {
			viewmodel.setAreaId( Integer.parseInt( data.get( "areaId" ) + "" ) );
		}
		if ( data.get( "publishPrice" ) != null ) {
			viewmodel.setPublishPrice( new BigDecimal( data.get( "publishPrice" ) + "" ) );
		}
		if ( data.get( "forwardPrice" ) != null ) {
			viewmodel.setForwardPrice( new BigDecimal( data.get( "forwardPrice" ) + "" ) );
		}
		if ( data.get( "authType" ) != null ) {
			viewmodel.setAuthType( Integer.parseInt( data.get( "authType" ) + "" ) );
		}
		if ( data.get( "authInfo" ) != null && !"".equals( "authInfo" ) ) {
			viewmodel.setAuthInfo( data.get( "authInfo" ) + "" );
		}
		if ( data.get( "memberId" ) != null ) {
			viewmodel.setMemberId( Integer.parseInt( data.get( "memberId" ) + "" ) );
		}
		if ( data.get( "fansCount" ) != null ) {
			viewmodel.setFansCount( Integer.parseInt( data.get( "fansCount" ) + "" ) );
		}
		if ( data.get( "file" ) != null ) {
			viewmodel.setFile( ( FileItem ) data.get( "file" ) );
		}
		if ( data.get( "qrCode" ) != null ) {
			viewmodel.setQrCode( ( FileItem ) data.get( "qrCode" ) );
		}
		if ( data.get( "remark" ) != null && !"".equals( "remark" ) ) {
			viewmodel.setRemark( data.get( "remark" ) + "" );
		}
		
		return viewmodel;
		
	}
	
	public static FlowMediaOrderAddViewmodel transJsonToFlowMediaAddViewmodel( JSONObject requestJson ) {
		
		FlowMediaOrderAddViewmodel viewmodel = new FlowMediaOrderAddViewmodel();
		viewmodel.setMemberId( requestJson.getInteger( "memberId" ) );
		viewmodel.setMobile( requestJson.getString( "mobile" ) );
		viewmodel.setPackageId( requestJson.getInteger( "packageId" ) );
		
		return viewmodel;
		
	}

	public static FlowMediaOrderBatchAddViewmodel transJsonToFlowMediaBatchAddViewmodel(
			 JSONObject requestJson ) {
		
		String mobile = requestJson.getString( "mobile" );
		String[] mobileArr = mobile.split( "," );
		
		FlowMediaOrderBatchAddViewmodel viewmodel = new FlowMediaOrderBatchAddViewmodel();
		viewmodel.setDxPackageId( requestJson.getInteger( "dxPackageId" ) );
		viewmodel.setLtPackageId( requestJson.getInteger( "ltPackageId" ) );
		viewmodel.setYdPackageId( requestJson.getInteger( "ydPackageId" ) );
		viewmodel.setMobile( mobileArr );
		viewmodel.setOrderName( requestJson.getString( "orderName" ) );
		viewmodel.setMemberId( requestJson.getInteger( "memberId" ) );
		
		return viewmodel;
	
	}

	public static FlowMediaOrderPayViewmodel transJsonToFlowMediaPayViewmodel( JSONObject requestJson ) {
		
		FlowMediaOrderPayViewmodel viewmodel = new FlowMediaOrderPayViewmodel();
		viewmodel.setMemberId( requestJson.getInteger( "memberId" ) );
		viewmodel.setOrderId( requestJson.getInteger( "orderId" ) );
		
		return viewmodel;
		
	}

}
