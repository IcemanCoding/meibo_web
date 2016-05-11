package com.meibo.web.media.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.common.utils.UploadUtils;
import com.meibo.web.media.dao.WechatMediaChannelDAO;
import com.meibo.web.media.dao.WechatMediaDAO;
import com.meibo.web.media.dao.WechatMediaTypeDAO;
import com.meibo.web.media.dto.AdminWechatMediaListDTO;
import com.meibo.web.media.dto.BaseWechatMediaListDTO;
import com.meibo.web.media.entity.NewsMediaTypeEntity;
import com.meibo.web.media.entity.WechatMediaChannelEntity;
import com.meibo.web.media.entity.WechatMediaInfoEntity;
import com.meibo.web.media.service.WechatMediaChannelService;
import com.meibo.web.media.service.WechatMediaService;
import com.meibo.web.media.service.WechatMediaTypeService;
import com.meibo.web.media.utils.MediaTransforUtils;
import com.meibo.web.media.viewmodel.AdminWechatMediaListQueryViewmodel;
import com.meibo.web.media.viewmodel.MemberWechatMediaListQueryViewmodel;
import com.meibo.web.media.viewmodel.WechatMediaInsertViewmodel;
import com.meibo.web.media.viewmodel.WechatMediaUpdateViewmodel;
import com.meibo.web.member.dao.MemberInfoDAO;
import com.meibo.web.member.dto.MemberInfoDTO;

public class WechatMediaServiceImpl implements WechatMediaService {
	
	@Autowired
	private MemberInfoDAO memberInfoDao;
	
	@Autowired
	private WechatMediaDAO wechatMediaDao;
	
	@Autowired
	private WechatMediaChannelDAO wechatMediaChannelDao;
	
	@Autowired
	private WechatMediaTypeDAO wechatMediaTypeDao;
	
	@Autowired
	private WechatMediaTypeService wechatMediaTypeService;
	
	@Autowired
	private WechatMediaChannelService wechatMediaChannelService;
	
	@Override
	public Boolean insertWechatMedia( Map<String, Object> formData, String rootDirProject ) throws Exception {
		
		WechatMediaInsertViewmodel viewmodel = MediaTransforUtils.transMapToWechatMediaInsert( formData );
		
		// get channel_id
		String wechatAccount = viewmodel.getAccount();
		Integer channelId = wechatMediaChannelDao.selectWechatMediaChannelId( wechatAccount );
		if ( channelId == null || channelId == 0 ) {
			
			// get type_id by type_name
			Integer typeId = wechatMediaTypeService.getOrAddWechatMediaTypeId( viewmodel.getTypeName() );
			
			// upload image
			FileItem image = ( FileItem ) formData.get( "file" );
			String headImage = "";
			if ( image != null ) {
				headImage = UploadUtils.uploadFile( image, 2, rootDirProject );
			}
			
			image = ( FileItem ) formData.get( "qrCode" );
			String qrCode = "";
			if ( image != null ) {
				qrCode = UploadUtils.uploadFile( image, 4, rootDirProject );
			}
			
			// insert channel entity
			WechatMediaChannelEntity channelEntity = new WechatMediaChannelEntity();
			channelEntity.setAccount( viewmodel.getAccount() );
			channelEntity.setAuthentication( viewmodel.getAuthentication() );
			channelEntity.setDesc( viewmodel.getDesc() );
			channelEntity.setNickName( viewmodel.getNickName() );
			channelEntity.setHeadImage( headImage );
			channelEntity.setQrCode( qrCode );
			channelEntity.setStatus( 1 );
			channelEntity.setTypeId( typeId );
			channelEntity.setFansCount( viewmodel.getFansCount() );
			
			wechatMediaChannelDao.insertWechatMediaChannel( channelEntity );
			channelId = channelEntity.getChannelId();
			
		}
		
		// insert wechat_media_info
		WechatMediaInfoEntity wechatMediaInfo = new WechatMediaInfoEntity();
		wechatMediaInfo.setAreaId( viewmodel.getAreaId() );
		wechatMediaInfo.setAuditStatus( 0 );
		wechatMediaInfo.setChannelId( channelId );
		wechatMediaInfo.setCreatedUser( viewmodel.getMemberId() );
		wechatMediaInfo.setFirstPrice( viewmodel.getFirstPrice() );
		wechatMediaInfo.setSecondPrice( viewmodel.getSecondPrice() );
		wechatMediaInfo.setOtherPrice( viewmodel.getOtherPrice() );
		wechatMediaInfo.setRemark( viewmodel.getRemark() );
		wechatMediaInfo.setStatus( 1 );
		
		wechatMediaDao.insertWechatMediaInfo( wechatMediaInfo );
		
		return true;
		
	}

	@Override
	public List<Map<String, Object>> getWechatMediaAreaList() throws Exception {
		
		return wechatMediaDao.selectWechatMediaAreaList();
		
	}

	@Override
	public Boolean auditWechatMedia( Integer auditUser, Integer wechatMediaId ) throws Exception {
		
		// get wechatMedia entity by wechatMediaId
		WechatMediaInfoEntity wechatMediaInfo = wechatMediaDao.selectWechatMediaInfoById( wechatMediaId );
		
		// get wechatMediaChannel entity by channelId
		Integer channelId = wechatMediaInfo.getChannelId();
		WechatMediaChannelEntity wechatMediaChannel = wechatMediaChannelDao.selectWechatMediaChannelById( channelId );
		
		// update wechatMediaChannel status = 1
		if ( wechatMediaChannel.getStatus() != 1 ) {
			wechatMediaChannel.setStatus( 1 );
			wechatMediaChannelDao.updateWechatMediaChannelStatus( wechatMediaChannel );
		}
		
		// get wechatMediaType entity by typeId
		NewsMediaTypeEntity wechatMediaType = wechatMediaTypeDao.selectWechatMediaTypeById( wechatMediaChannel.getTypeId() );
		
		// update wechatMediaType status = 1
		if ( wechatMediaType.getStatus() != 1 ) {
			wechatMediaType.setStatus( 1 );
			wechatMediaTypeDao.updateWechatMediaTypeStatus( wechatMediaType );
		}
		
		// audit wechatMediaEntity
		wechatMediaInfo.setAuditStatus( 1 );
		wechatMediaInfo.setAuditDate( new Date() );
		wechatMediaInfo.setAuditUser( auditUser );
		
		wechatMediaDao.auditWechatMediaInfo( wechatMediaInfo );
		
		return true;
		
	}

	@Override
	public Map<String, Object> getWechatMediaListByAdmin( AdminWechatMediaListQueryViewmodel viewmodel ) throws Exception {
		
		// get member_info entity
		Map<String, Object> param = new HashMap<String, Object>();
		param.put( "memberId", viewmodel.getMemberId() );
		MemberInfoDTO memberInfo = memberInfoDao.selectMemberInfoByConditions( param );
		if ( memberInfo.getMemberType() == 2 ) {
			viewmodel.setCreatedUser( viewmodel.getMemberId() );
		}
		
		Integer pageNum = viewmodel.getPageNum();
		Integer recorders = viewmodel.getPageRecorders();
		if ( pageNum == null || recorders == null ) {
			viewmodel.setIsLimit( 0 );
		} else {
			viewmodel.setBeginLimit( ( pageNum - 1 ) * recorders );
			viewmodel.setEndLimit( recorders );
			viewmodel.setIsLimit( 1 );
		}
		
		List<AdminWechatMediaListDTO> adminNewsMediaList = wechatMediaDao.selectWechatMediaListByAdmin( viewmodel );
		if ( adminNewsMediaList == null || adminNewsMediaList.size() == 0 ) {
			return null;
		}
		Integer totalPages = 0;
		Integer totalRows = wechatMediaDao.selectWechatMediaListByAdminCount( viewmodel );
		if ( recorders != null && recorders != 0 ) {
			totalPages = ( totalRows / recorders ) == 0 ? 1 : totalRows / recorders;
		}
		
		Map<String, Object> resData = new HashMap<String, Object>();
		resData.put( "mediaList", adminNewsMediaList );
		resData.put( "totalPages", totalPages );
		resData.put( "totalRows", totalRows );
		
		return resData;
		
	}

	@Override
	public Map<String, Object> getWechatMediaListByMember( MemberWechatMediaListQueryViewmodel viewmodel ) throws Exception {
		
		Integer pageNum = viewmodel.getPageNum();
		Integer recorders = viewmodel.getPageRecorders();
		if ( pageNum == null || recorders == null ) {
			viewmodel.setIsLimit( 0 );
		} else {
			viewmodel.setBeginLimit( ( pageNum - 1 ) * recorders );
			viewmodel.setEndLimit( recorders );
			viewmodel.setIsLimit( 1 );
		}
		
		List<BaseWechatMediaListDTO> adminNewsMediaList = wechatMediaDao.selectWechatMediaListByMember( viewmodel );
		if ( adminNewsMediaList == null || adminNewsMediaList.size() == 0 ) {
			return null;
		}
		Integer totalPages = 0;
		Integer totalRows = wechatMediaDao.selectWechatMediaListByMemberCount( viewmodel );
		if ( recorders != null && recorders != 0 ) {
			totalPages = ( totalRows / recorders ) == 0 ? 1 : totalRows / recorders;
		}
		
		Map<String, Object> resData = new HashMap<String, Object>();
		resData.put( "mediaList", adminNewsMediaList );
		resData.put( "totalPages", totalPages );
		resData.put( "totalRows", totalRows );
		
		return resData;
		
	}

	@Override
	public Boolean editWechatMedia( Map<String, Object> formData, String rootDirProject ) throws Exception {
		
		WechatMediaUpdateViewmodel viewmodel = MediaTransforUtils.transMapToWechatMediaUpdate( formData );
		
		if ( viewmodel.getWechatMediaId() == null ) {
			return false;
		}
		
		// get typeId
		Integer typeId = null;
		if ( viewmodel.getTypeName() != null && !"".equals( viewmodel.getTypeName() ) ) {
			typeId = wechatMediaTypeService.getOrAddWechatMediaTypeId( viewmodel.getTypeName() );
		}
		
		// get channelId
		Integer channelId = null;
		if ( viewmodel.getAccount() != null && !"".equals( viewmodel.getAccount() ) ) {
			
			WechatMediaChannelEntity wechatMediaChannel = wechatMediaChannelDao.selectWechatMediaChannelByName( viewmodel.getAccount() );
			if ( wechatMediaChannel == null ) {
				
				wechatMediaChannel = new WechatMediaChannelEntity();
				
				FileItem image = viewmodel.getFile();
				String headImage = "";
				if ( image != null ) {
					headImage = UploadUtils.uploadFile( image, 2, rootDirProject );
				}
				
				image = viewmodel.getQrCode();
				String qrCode = "";
				if ( image != null ) {
					qrCode = UploadUtils.uploadFile( image, 4, rootDirProject );
				}
				
				wechatMediaChannel.setAccount( viewmodel.getAccount() );
				wechatMediaChannel.setAuthentication( viewmodel.getAuthentication() );
				wechatMediaChannel.setDesc( viewmodel.getDesc() );
				wechatMediaChannel.setFansCount( viewmodel.getFansCount() );
				wechatMediaChannel.setHeadImage(headImage);
				wechatMediaChannel.setNickName( viewmodel.getNickname() );
				wechatMediaChannel.setQrCode(qrCode);
				wechatMediaChannel.setStatus( 1 );
				wechatMediaChannel.setTypeId( typeId );
				channelId = wechatMediaChannelService.getOrAddWechatMediaChannelId( wechatMediaChannel );
				
			} else {
				channelId = wechatMediaChannel.getChannelId();
			}
			
		}
		
		// update wechat_media_info
		viewmodel.setChannelId( channelId );
		wechatMediaDao.updateWechatMediaInfo( viewmodel );
		
		return true;
		
	}


}
