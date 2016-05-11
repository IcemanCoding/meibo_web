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

public class MediaTransforUtils {
	
	public static NewsMediaListQueryParams transJsonToQueryParams( JSONObject requestJson ) {
		
		NewsMediaListQueryParams viewmodel = new NewsMediaListQueryParams();
		
		if ( requestJson.getInteger( "areaId" ) != null ) {
			viewmodel.setAreaId( requestJson.getInteger( "areaId" ) );
		}
		if ( requestJson.getString( "channelName" ) != null ) {
			viewmodel.setChannelName( requestJson.getString( "channelName" ) );
		}
		if ( requestJson.getInteger( "includeType" ) != null ) {
			viewmodel.setIncludeType( requestJson.getInteger( "includeType" ) );
		}
		if ( requestJson.getInteger( "memberId" ) != null ) {
			viewmodel.setMemberId( requestJson.getInteger( "memberId" ) );
		}
		if ( requestJson.getInteger( "pageNum" ) != null ) {
			viewmodel.setPageNum( requestJson.getInteger( "pageNum" ) );
		}
		if ( requestJson.getInteger( "pageRecorders" ) != null ) {
			viewmodel.setPageRecorders( requestJson.getInteger( "pageRecorders" ) );
		}
		if ( requestJson.getInteger( "typeId" ) != null ) {
			viewmodel.setTypeId( requestJson.getInteger( "typeId" ) );
		}
		if ( requestJson.getBigDecimal( "maxPrice" ) != null ) {
			viewmodel.setMaxPrice( requestJson.getBigDecimal( "maxPrice" ) );
		}
		if ( requestJson.getBigDecimal( "minPrice" ) != null ) {
			viewmodel.setMinPrice( requestJson.getBigDecimal( "minPrice" ) );
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
		if ( data.get( "areaId" ) != null ) {
			newsMediaEntity.setAreaId( Integer.parseInt( data.get( "areaId" ) + "" ) );
		}
		if ( data.get( "includeType" ) != null  ) {
			newsMediaEntity.setIncludeType( Integer.parseInt( data.get( "includeType" ) + "" ) );
		}
		if ( data.get( "quotePrice" ) != null  ) {
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
		if ( data.get( "allowLink" ) != null  ) {
			newsMediaEntity.setAllowLink( Integer.parseInt( data.get( "allowLink" ) + "" ) );
		}
		if ( data.get( "allowQRCode" ) != null  ) {
			newsMediaEntity.setAllowQRCode( Integer.parseInt( data.get( "allowQRCode" ) + "" ) );
		}
		if ( data.get( "allowContactWay" ) != null  ) {
			newsMediaEntity.setAllowContactWay( Integer.parseInt( data.get( "allowContactWay" ) + "" ) );
		}
		if ( data.get( "memberId" ) != null  ) {
			newsMediaEntity.setCreatedUser( Integer.parseInt( data.get( "memberId" ) + "" ) );
		}
		if ( data.get( "newsMediaId" ) != null  ) {
			newsMediaEntity.setNewsMediaId( Integer.parseInt( data.get( "newsMediaId" ) + "" ) );
		}
		
		return newsMediaEntity;
		
	}
	
	public static WechatMediaInsertViewmodel transMapToWechatMediaInsert( Map<String, Object> data ) {
		
		WechatMediaInsertViewmodel viewmodel = new WechatMediaInsertViewmodel();
		
		if ( data.get( "nickname" ) != null && !"".equals( "nickname" ) ) {
			viewmodel.setNickName( data.get( "nickName" ) + "" );
		}
		if ( data.get( "account" ) != null && !"".equals( "account" ) ) {
			viewmodel.setAccount( data.get( "account" ) + "" );
		}
		if ( data.get( "desc" ) != null && !"".equals( "desc" ) ) {
			viewmodel.setDesc( data.get( "desc" ) + "" );
		}
		if ( data.get( "authentication" ) != null && !"".equals( "authentication" ) ) {
			viewmodel.setAuthentication( data.get( "authentication" ) + "" );
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
		if ( requestJson.getString( "mediaName" ) != null && !"".equals( requestJson.getString( "mediaName" ) ) ) {
			viewmodel.setMediaName( requestJson.getString( "mediaName" ) );
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
		if ( requestJson.getString( "mediaName" ) != null && !"".equals( requestJson.getString( "mediaName" ) ) ) {
			viewmodel.setMediaName( requestJson.getString( "mediaName" ) );
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
	
	public static WechatMediaUpdateViewmodel transMapToWechatMediaUpdate( Map<String, Object> data ) {
		
		WechatMediaUpdateViewmodel viewmodel = new WechatMediaUpdateViewmodel();
		
		if ( data.get( "wechatMediaId" ) != null ) {
			viewmodel.setWechatMediaId( Integer.parseInt( data.get( "wechatMediaId" ) + "" ) );
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
		if ( data.get( "remark" ) != null && !"".equals( data.get( "remark" ) ) ) {
			viewmodel.setRemark( data.get( "remark" ) + "" );
		}
		if ( data.get( "nickname" ) != null && !"".equals( data.get( "nickname" ) ) ) {
			viewmodel.setNickname( data.get( "nickname" ) + "" );
		}
		if ( data.get( "account" ) != null && !"".equals( data.get( "account" ) ) ) {
			viewmodel.setAccount( data.get( "account" ) + "" );
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
		if ( data.get( "desc" ) != null && !"".equals( data.get( "desc" ) ) ) {
			viewmodel.setDesc( data.get( "desc" ) + "" );
		}
		if ( data.get( "authentication" ) != null && !"".equals( data.get( "authentication" ) ) ) {
			viewmodel.setAuthentication( data.get( "authentication" ) + "" );
		}
		if ( data.get( "typeName" ) != null && !"".equals( data.get( "typeName" ) ) ) {
			viewmodel.setTypeName( data.get( "typeName" ) + "" );
		}
		
		return viewmodel;
		
	}
	
	public static BlogMediaUpdateViewmodel transMapToBlogMediaUpdate( Map<String, Object> data ) {
		
		BlogMediaUpdateViewmodel viewmodel = new BlogMediaUpdateViewmodel();
		
		if ( data.get( "blogMediaId" ) != null ) {
			viewmodel.setBlogMediaId( Integer.parseInt( data.get( "blogMediaId" ) + "" ) );
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
		if ( data.get( "remark" ) != null && !"".equals( data.get( "remark" ) ) ) {
			viewmodel.setRemark( data.get( "remark" ) + "" );
		}
		if ( data.get( "nickname" ) != null && !"".equals( data.get( "nickname" ) ) ) {
			viewmodel.setNickname( data.get( "nickname" ) + "" );
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
		if ( data.get( "desc" ) != null && !"".equals( data.get( "desc" ) ) ) {
			viewmodel.setDesc( data.get( "desc" ) + "" );
		}
		if ( data.get( "typeName" ) != null && !"".equals( data.get( "typeName" ) ) ) {
			viewmodel.setTypeName( data.get( "typeName" ) + "" );
		}
		if ( data.get( "authInfo" ) != null && !"".equals( data.get( "authInfo" ) ) ) {
			viewmodel.setAuthInfo( data.get( "authInfo" ) + "" );
		}
		if ( data.get( "authType" ) != null ) {
			viewmodel.setAuthType( Integer.parseInt( data.get( "authType" ) + "" ) );
		}
		
		return viewmodel;
		
	}
	
	public static BlogMediaInsertViewmodel transMapToBlogMediaInsert( Map<String, Object> data ) {
		
		BlogMediaInsertViewmodel viewmodel = new BlogMediaInsertViewmodel();
		
		if ( data.get( "nickname" ) != null && !"".equals( "nickname" ) ) {
			viewmodel.setNickname( data.get( "nickName" ) + "" );
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

}
