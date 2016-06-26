package com.meibo.web.media.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.meibo.web.media.dto.BaseNewsMediaListDTO;
import com.meibo.web.media.dto.BaseWechatMediaListDTO;
import com.meibo.web.media.entity.BlogMediaInfoEntity;
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
import com.meibo.web.order.dto.BlogMediaOrderSplitDTO;
import com.meibo.web.order.dto.WechatMediaOrderSplitDTO;
import com.meibo.web.system.dao.SystemParamsInfoDAO;
import com.meibo.web.utils.constants.ConstantsForSystemParams;

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
	private SystemParamsInfoDAO systemParamsInfoDao;
	
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
	public Boolean auditWechatMedia( Integer auditUser, Integer wechatMediaId, Integer auditStatus ) throws Exception {
		
		// get wechatMedia entity by wechatMediaId
		WechatMediaInfoEntity wechatMediaInfo = wechatMediaDao.selectWechatMediaInfoById( wechatMediaId );
		
		if ( auditStatus == 1 ) {
			
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
			
		}
		
		// audit wechatMediaEntity
		wechatMediaInfo.setAuditStatus( auditStatus );
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
		
		String rate = systemParamsInfoDao.selectSystemParamsInfoByKey( ConstantsForSystemParams.MEIBO_STAGE_RATE );
		viewmodel.setRate( rate );
		
		List<BaseWechatMediaListDTO> wechatMediaList = wechatMediaDao.selectWechatMediaListByMember( viewmodel );
		if ( wechatMediaList == null || wechatMediaList.size() == 0 ) {
			return null;
		}
		
		Integer totalPages = 0;
		Integer totalRows = wechatMediaDao.selectWechatMediaListByMemberCount( viewmodel );
		if ( recorders != null && recorders != 0 ) {
			totalPages = ( totalRows / recorders ) == 0 ? 1 : totalRows / recorders;
		}
		
		Map<String, Object> resData = new HashMap<String, Object>();
		resData.put( "mediaList", wechatMediaList );
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
		
		// upload file
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
		
		// get ori media_wechat_channel
		WechatMediaInfoEntity _wechatMediaInfo = wechatMediaDao.selectWechatMediaInfoById( viewmodel.getWechatMediaId() );
		WechatMediaChannelEntity _wechatMediaChannel = wechatMediaChannelDao.selectWechatMediaChannelById( _wechatMediaInfo.getChannelId() );
		if ( !headImage.isEmpty() ) {
			_wechatMediaChannel.setHeadImage( headImage );
		}
		if ( !qrCode.isEmpty() ) {
			_wechatMediaChannel.setQrCode( qrCode );
		}
		if ( viewmodel.getAccount() != null && !viewmodel.getAccount().isEmpty() ) {
			_wechatMediaChannel.setAccount( viewmodel.getAccount() );
		}
		if ( viewmodel.getNickname() != null && !viewmodel.getNickname().isEmpty() ) {
			_wechatMediaChannel.setNickName( viewmodel.getNickname() );
		}
		if ( viewmodel.getFansCount() != null ) {
			_wechatMediaChannel.setFansCount( viewmodel.getFansCount() );
		}
		if ( viewmodel.getDesc() != null && !viewmodel.getDesc().isEmpty() ) {
			_wechatMediaChannel.setDesc( viewmodel.getDesc() );
		}
		if ( viewmodel.getAuthentication() != null && !viewmodel.getAuthentication().isEmpty() ) {
			_wechatMediaChannel.setAuthentication( viewmodel.getAuthentication() );
		}
		if ( typeId != null ) {
			_wechatMediaChannel.setTypeId( typeId );
		}
		
		// get channelId
		Integer channelId = null;
		if ( viewmodel.getAccount() != null && !"".equals( viewmodel.getAccount() ) ) {
			
			WechatMediaChannelEntity wechatMediaChannel = wechatMediaChannelDao.selectWechatMediaChannelByName( viewmodel.getAccount() );
			if ( wechatMediaChannel == null ) {
				_wechatMediaChannel.setStatus( 1 );
				channelId = wechatMediaChannelService.getOrAddWechatMediaChannelId( _wechatMediaChannel );
				_wechatMediaChannel.setChannelId( null );
			} else {
				channelId = wechatMediaChannel.getChannelId();
			}
			
		}
		
		// update wechat_media_channel
		if ( _wechatMediaChannel.getChannelId() != null ) {
			wechatMediaChannelDao.updateWechatMediaChannelByChannelId( _wechatMediaChannel );
		}
		
		// update wechat_media_info
		viewmodel.setChannelId( channelId );
		wechatMediaDao.updateWechatMediaInfo( viewmodel );
		
		return true;
		
	}

	@Override
	public BigDecimal getOrderAmountById( int[] wechatMediaId, int[] selectedId ) {
		
		BigDecimal transAmount = BigDecimal.ZERO;
		
		for ( int i = 0; i < wechatMediaId.length; i++ ) {
			
			WechatMediaInfoEntity mediaInfo = wechatMediaDao.selectWechatMediaInfoById( wechatMediaId[i] );
			
			if ( selectedId[i] == 1 ) {
				transAmount = transAmount.add( mediaInfo.getFirstPrice() );
			} else if ( selectedId[i] == 2 ) {
				transAmount = transAmount.add( mediaInfo.getSecondPrice() );
			} else if ( selectedId[i] == 3 ) {
				transAmount = transAmount.add( mediaInfo.getOtherPrice() );
			} 
			
		}
		
		return transAmount;
		
	}

	@Override
	public List<WechatMediaOrderSplitDTO> getOrderSplitDtoById( int[] wechatMediaId, int[] selectedId ) {
		
		List<WechatMediaOrderSplitDTO> orderSplitDtos = new ArrayList<WechatMediaOrderSplitDTO>();
		
		for ( int i = 0; i < wechatMediaId.length; i++ ) {
			
			WechatMediaOrderSplitDTO orderSplitDto = new WechatMediaOrderSplitDTO();
			WechatMediaInfoEntity mediaInfo = wechatMediaDao.selectWechatMediaInfoById( wechatMediaId[i] );
			
			if ( selectedId[i] == 1 ) {
				orderSplitDto.setTransAmount( mediaInfo.getFirstPrice() );
			} else if ( selectedId[i] == 2 ) {
				orderSplitDto.setTransAmount( mediaInfo.getSecondPrice() );
			} else if ( selectedId[i] == 3 ) {
				orderSplitDto.setTransAmount( mediaInfo.getOtherPrice() );
			} 
			
			orderSplitDto.setWechatMediaId( wechatMediaId[i] );
			orderSplitDto.setMediaMemberId( mediaInfo.getCreatedUser() );
			orderSplitDto.setPriceType( selectedId[i] );
			
			orderSplitDtos.add( orderSplitDto );
			
		}
		
		return orderSplitDtos;
		
	}

	@Override
	public WechatMediaInfoEntity getWechatMediaInfoById( Integer wechatMediaId ) {
		return wechatMediaDao.selectWechatMediaInfoById( wechatMediaId );
	}


}
