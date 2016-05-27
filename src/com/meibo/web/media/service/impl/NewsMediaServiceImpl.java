package com.meibo.web.media.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.common.utils.UploadUtils;
import com.meibo.web.media.dao.NewsMediaChannelDAO;
import com.meibo.web.media.dao.NewsMediaColumnDAO;
import com.meibo.web.media.dao.NewsMediaDAO;
import com.meibo.web.media.dao.NewsMediaTypeDAO;
import com.meibo.web.media.dto.AdminNewsMediaListDTO;
import com.meibo.web.media.dto.BaseNewsMediaListDTO;
import com.meibo.web.media.entity.BlogMediaInfoEntity;
import com.meibo.web.media.entity.NewsMediaChannelEntity;
import com.meibo.web.media.entity.NewsMediaColumnEntity;
import com.meibo.web.media.entity.NewsMediaEntity;
import com.meibo.web.media.entity.NewsMediaTypeEntity;
import com.meibo.web.media.service.NewsMediaChannelService;
import com.meibo.web.media.service.NewsMediaColumnService;
import com.meibo.web.media.service.NewsMediaService;
import com.meibo.web.media.service.NewsMediaTypeService;
import com.meibo.web.media.utils.MediaTransforUtils;
import com.meibo.web.media.viewmodel.NewsMediaListQueryParams;
import com.meibo.web.member.dao.MemberInfoDAO;
import com.meibo.web.member.dto.MemberInfoDTO;
import com.meibo.web.order.dto.NewsMediaOrderSplitDTO;

public class NewsMediaServiceImpl implements NewsMediaService {

	@Autowired
	private MemberInfoDAO memberInfoDao;
	
	@Autowired
	private NewsMediaDAO newsMediaDao;
	
	@Autowired
	private NewsMediaChannelDAO newsMediaChannelDao;
	
	@Autowired
	private NewsMediaTypeDAO newsMediaTypeDao;
	
	@Autowired
	private NewsMediaColumnDAO newsMediaColumnDao;
	
	@Autowired
	private NewsMediaTypeService newsMediaTypeService;
	
	@Autowired
	private NewsMediaChannelService newsMediaChannelService;
	
	@Autowired
	private NewsMediaColumnService newsMediaColumnService;
	
	@Override
	public Boolean insertNewsMedia( Map<String, Object> formData, String rootDirProject ) throws Exception {
		
		// upload image
		FileItem image = (FileItem) formData.get( "file" );
		String imageUrl = "";
		if ( image != null ) {
			imageUrl = UploadUtils.uploadFile( image, 1, rootDirProject );
		}
		
		NewsMediaEntity newsMediaEntity = MediaTransforUtils.transMapToNewsMediaEntity( formData );
		
		// type_id
		Integer typeId = newsMediaTypeService.getOrAddNewsMediaTypeId( newsMediaEntity.getChannelType() );
		
		// channel_id
		NewsMediaChannelEntity newsMediaChannelEntity = new NewsMediaChannelEntity();
		newsMediaChannelEntity.setChannelName( newsMediaEntity.getChannelName() );
		newsMediaChannelEntity.setLinkUrl( newsMediaEntity.getLinkUrl() );
		newsMediaChannelEntity.setNewsTypeId( typeId );
		newsMediaChannelEntity.setPicUrl( imageUrl );
		newsMediaChannelEntity.setStatus( 0 );
		
		Integer channelId = newsMediaChannelService.getOrAddNewsMediaChannelId( newsMediaChannelEntity );
		newsMediaEntity.setChannelId( channelId );
		
		// column_id
		Integer columnId = newsMediaColumnService.getOrAddNewsMediaColumnId( newsMediaEntity.getChannelColumn() );
		newsMediaEntity.setColumnId( columnId );
		
		// insert db
		newsMediaEntity.setImageUrl( imageUrl );
		newsMediaEntity.setAuditStatus( 0 );
		
		Integer insertCount = newsMediaDao.insertNewsMediaInfo( newsMediaEntity );
		if ( insertCount > 0 ) {
			return true;
		}
		return false;
		
	}

	@Override
	public Boolean auditNewsMedia( Integer auditUser, Integer newsMediaId, Integer auditStatus ) throws Exception {
		
		// get news_media entity
		NewsMediaEntity newsMediaEntity = newsMediaDao.selectNewsMediaInfo( newsMediaId );
		
		// check news_media audit_status
		if ( newsMediaEntity.getAuditStatus() != 0 && newsMediaEntity.getAuditStatus() != 2 ) {
			return false;
		}
		
		if ( auditStatus == 1 ) {
			
			// get news_channel entity
			Integer channelId = newsMediaEntity.getChannelId();
			NewsMediaChannelEntity newsMediaChannel = newsMediaChannelDao.selectNewsMediaChannelById( channelId );
			
			Integer channelStatus = newsMediaChannel.getStatus();
			if ( channelStatus == 0 ) {
				
				// get news_type
				Integer newsTypeId = newsMediaChannel.getNewsTypeId();
				
				// get news_type entity
				NewsMediaTypeEntity newsMediaType = newsMediaTypeDao.selectNewsMediaTypeById( newsTypeId );
				if ( newsMediaType.getStatus() == 0 ) {
					
					// update news_type status = 1
					newsMediaType.setStatus( 1 );
					newsMediaTypeDao.updateNewsMediaTypeStatus( newsMediaType );
					
				}
				
				// update news_channel status = 1
				newsMediaChannel.setStatus( 1 );
				newsMediaChannelDao.updateNewsMediaChannelStatus( newsMediaChannel );
				
			}
			
			// get news_column entity
			Integer columnId = newsMediaEntity.getColumnId();
			NewsMediaColumnEntity newsMediaColumn = newsMediaColumnDao.selectNewsMediaColumnById( columnId );
			if ( newsMediaColumn.getStatus() == 0 ) {
				
				// update news_column status = 1
				newsMediaColumn.setStatus( 1 );
				newsMediaColumnDao.updateNewsMediaColumnStatus( newsMediaColumn );
				
			}
			
		}
		
		// audit news_media
		newsMediaEntity.setAuditStatus( auditStatus );
		newsMediaEntity.setAuditUser( auditUser );
		newsMediaEntity.setAuditDate( new Date() );
		newsMediaDao.updateNewsMediaByAudit( newsMediaEntity );
		
		return true;
		
	}

	@Override
	public Map<String, Object> getNewsMediaListByAdmin( NewsMediaListQueryParams params ) throws Exception {
		
		// get member_info entity
		Map<String, Object> param = new HashMap<String, Object>();
		param.put( "memberId", params.getMemberId() );
		MemberInfoDTO memberInfo = memberInfoDao.selectMemberInfoByConditions( param );
		if ( memberInfo.getMemberType() == 2 ) {
			params.setCreatedUser( params.getMemberId() );
		}
		
		Integer pageNum = params.getPageNum();
		Integer recorders = params.getPageRecorders();
		if ( pageNum == null || recorders == null ) {
			params.setIsLimit( 0 );
		} else {
			params.setBeginLimit( ( pageNum - 1 ) * recorders );
			params.setEndLimit( recorders );
			params.setIsLimit( 1 );
		}
		
		List<AdminNewsMediaListDTO> adminNewsMediaList = newsMediaDao.selectNewsMediaListByAdmin( params );
		if ( adminNewsMediaList == null || adminNewsMediaList.size() == 0 ) {
			return null;
		}
		Integer totalPages = 0;
		Integer totalRows = newsMediaDao.selectNewsMediaListByAdminCount( params );
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
	public Map<String, Object> getNewsMediaListByMember( NewsMediaListQueryParams params ) throws Exception {
		
		Integer pageNum = params.getPageNum();
		Integer recorders = params.getPageRecorders();
		if ( pageNum == null || recorders == null ) {
			params.setIsLimit( 0 );
		} else {
			params.setBeginLimit( ( pageNum - 1 ) * recorders );
			params.setEndLimit( recorders );
			params.setIsLimit( 1 );
		}
		
		List<BaseNewsMediaListDTO> baseNewsMediaList = newsMediaDao.selectNewsMediaListByMember( params );
		if ( baseNewsMediaList == null || baseNewsMediaList.size() == 0 ) {
			return null;
		}
		Integer totalPages = 0;
		Integer totalRows = newsMediaDao.selectNewsMediaListByMemberCount( params );
		if ( recorders != null && recorders != 0 ) {
			totalPages = ( totalRows / recorders ) == 0 ? 1 : totalRows / recorders;
		}
		
		Map<String, Object> resData = new HashMap<String, Object>();
		resData.put( "mediaList", baseNewsMediaList );
		resData.put( "totalPages", totalPages );
		resData.put( "totalRows", totalRows );
		
		return resData;
		
	}

	@Override
	public List<Map<String, Object>> getNewsMediaAreaList() throws Exception {
		
		List<Map<String, Object>> areaList = newsMediaDao.selectNewsMediaArea();
		
		return areaList;
		
	}

	@Override
	public Boolean updateNewsMedia( Map<String, Object> formData, String rootDirProject ) throws Exception {
		
		// upload image
		FileItem image = (FileItem) formData.get( "file" );
		String imageUrl = "";
		if ( image != null ) {
			imageUrl = UploadUtils.uploadFile( image, 1, rootDirProject );
		}
		
		NewsMediaEntity newsMediaEntity = MediaTransforUtils.transMapToNewsMediaEntity( formData );
		
		// type_id
		Integer typeId = null;
		if ( newsMediaEntity.getChannelType() != null && !"".equals( newsMediaEntity.getChannelType() ) ) {
			typeId = newsMediaTypeService.getOrAddNewsMediaTypeId( newsMediaEntity.getChannelType() );
		}
		
		// channel_id
		Integer channelId = null;
		if ( newsMediaEntity.getChannelName() != null && !"".equals( newsMediaEntity.getChannelName() ) ) {
			
			NewsMediaChannelEntity newsMediaChannelEntity = new NewsMediaChannelEntity();
			newsMediaChannelEntity.setChannelName( newsMediaEntity.getChannelName() );
			newsMediaChannelEntity.setLinkUrl( newsMediaEntity.getLinkUrl() );
			newsMediaChannelEntity.setNewsTypeId( typeId );
			newsMediaChannelEntity.setPicUrl( imageUrl );
			newsMediaChannelEntity.setStatus( 0 );
			channelId = newsMediaChannelService.getOrAddNewsMediaChannelId( newsMediaChannelEntity );
			
		}
		
		newsMediaEntity.setChannelId( channelId );
		
		// column_id
		Integer columnId = null;
		if ( newsMediaEntity.getChannelColumn() != null && !"".equals( newsMediaEntity.getChannelColumn() ) ) {
			columnId = newsMediaColumnService.getOrAddNewsMediaColumnId( newsMediaEntity.getChannelColumn() );
		}
		newsMediaEntity.setColumnId( columnId );
		
		// insert db
		newsMediaEntity.setImageUrl( imageUrl );
		newsMediaEntity.setAuditStatus( 0 );
		
		Integer insertCount = newsMediaDao.updateNewsMediaInfo( newsMediaEntity );
		if ( insertCount > 0 ) {
			return true;
		}
		return false;
		
	}

	@Override
	public BigDecimal getOrderAmountById( int[] newsMediaId ) throws Exception {
		return newsMediaDao.selectOrderAmountById( newsMediaId );
	}

	@Override
	public List<NewsMediaOrderSplitDTO> getOrderSplitDtoById( int[] newsMediaId ) throws Exception {
		return newsMediaDao.selectOrderSplitDtoById( newsMediaId );
	}

	@Override
	public NewsMediaEntity getNewsMediaInfoById( Integer newsMediaId ) {
		return newsMediaDao.selectNewsMediaInfo( newsMediaId );
	}
	
}
