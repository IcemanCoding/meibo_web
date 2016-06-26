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
import com.meibo.web.media.dao.BlogMediaChannelDAO;
import com.meibo.web.media.dao.BlogMediaDAO;
import com.meibo.web.media.dao.BlogMediaTypeDAO;
import com.meibo.web.media.dto.AdminBlogMediaListDTO;
import com.meibo.web.media.dto.BaseBlogMediaListDTO;
import com.meibo.web.media.entity.BlogMediaChannelEntity;
import com.meibo.web.media.entity.BlogMediaInfoEntity;
import com.meibo.web.media.entity.NewsMediaTypeEntity;
import com.meibo.web.media.service.BlogMediaChannelService;
import com.meibo.web.media.service.BlogMediaService;
import com.meibo.web.media.service.BlogMediaTypeService;
import com.meibo.web.media.utils.MediaTransforUtils;
import com.meibo.web.media.viewmodel.AdminBlogMediaListQueryViewmodel;
import com.meibo.web.media.viewmodel.BlogMediaInsertViewmodel;
import com.meibo.web.media.viewmodel.BlogMediaUpdateViewmodel;
import com.meibo.web.media.viewmodel.MemberBlogMediaListQueryViewmodel;
import com.meibo.web.member.dao.MemberInfoDAO;
import com.meibo.web.member.dto.MemberInfoDTO;
import com.meibo.web.order.dto.BlogMediaOrderSplitDTO;
import com.meibo.web.system.dao.SystemParamsInfoDAO;
import com.meibo.web.utils.constants.ConstantsForSystemParams;

public class BlogMediaServiceImpl implements BlogMediaService {
	
	@Autowired
	private BlogMediaDAO blogMediaDao;
	
	@Autowired
	private BlogMediaChannelDAO blogMediaChannelDao;
	
	@Autowired
	private BlogMediaTypeDAO blogMediaTypeDao;
	
	@Autowired
	private MemberInfoDAO memberInfoDao;
	
	@Autowired
	private SystemParamsInfoDAO systemParamsInfoDao;
	
	@Autowired
	private BlogMediaTypeService blogMediaTypeService;
	
	@Autowired
	private BlogMediaChannelService blogMediaChannelService;

	@Override
	public Boolean insertBlogMedia( Map<String, Object> formData, String rootDirProject ) throws Exception {
		
		BlogMediaInsertViewmodel viewmodel = MediaTransforUtils.transMapToBlogMediaInsert( formData );
		
		// get channel_id
		String nickname = viewmodel.getNickname();
		Integer channelId = blogMediaChannelDao.selectBlogMediaChannelId( nickname );
		if ( channelId == null || channelId == 0 ) {
			
			// get type_id by type_name
			Integer typeId = blogMediaTypeService.getOrAddBlogMediaTypeId( viewmodel.getTypeName() );
			
			// upload image
			FileItem image = ( FileItem ) formData.get( "file" );
			String headImage = "";
			if ( image != null ) {
				headImage = UploadUtils.uploadFile( image, 3, rootDirProject );
			}
			
			image = ( FileItem ) formData.get( "qrCode" );
			String qrCode = "";
			if ( image != null ) {
				qrCode = UploadUtils.uploadFile( image, 5, rootDirProject );
			}
			
			// insert channel entity
			BlogMediaChannelEntity channelEntity = new BlogMediaChannelEntity();
			channelEntity.setAuthInfo( viewmodel.getAuthInfo() );
			channelEntity.setAuthType( viewmodel.getAuthType() );
			channelEntity.setDesc( viewmodel.getDesc() );
			channelEntity.setFansCount( viewmodel.getFansCount() );
			channelEntity.setHeadImage( headImage );
			channelEntity.setNickname( nickname );
			channelEntity.setQrCode( qrCode );
			channelEntity.setStatus( 1 );
			channelEntity.setTypeId( typeId );
			channelEntity.setRegisterDate( viewmodel.getRegisterDate() );
			
			blogMediaChannelDao.insertBlogMediaChannel( channelEntity );
			channelId = channelEntity.getChannelId();
			
		}
		
		// insert blog_media_info
		BlogMediaInfoEntity blogMediaInfo = new BlogMediaInfoEntity();
		blogMediaInfo.setAreaId( viewmodel.getAreaId() );
		blogMediaInfo.setChannelId( channelId );
		blogMediaInfo.setCreatedUser( viewmodel.getMemberId() );
		blogMediaInfo.setForwardPrice( viewmodel.getForwardPrice() );
		blogMediaInfo.setPublishPrice( viewmodel.getPublishPrice() );
		blogMediaInfo.setRemark( viewmodel.getRemark() );
		blogMediaInfo.setStatus( 1 );
		
		blogMediaDao.insertBlogMediaInfo( blogMediaInfo );
		
		return true;
		
	}

	@Override
	public Boolean auditBlogMedia( Integer auditUser, Integer blogMediaId, Integer auditStatus ) throws Exception {
		
		// get blogMedia entity by blogMediaId
		BlogMediaInfoEntity blogMediaInfo = blogMediaDao.selectBlogMediaInfoById( blogMediaId );
		
		if ( auditStatus == 1 ) {
			
			// get blogMediaChannel entity by channelId
			Integer channelId = blogMediaInfo.getChannelId();
			BlogMediaChannelEntity blogMediaChannel = blogMediaChannelDao.selectBlogMediaChannelById( channelId );
			
			// update blogMediaChannel status = 1
			if ( blogMediaChannel.getStatus() != 1 ) {
				blogMediaChannel.setStatus( 1 );
				blogMediaChannelDao.updateBlogMediaChannelStatus( blogMediaChannel );
			}
			
			// get blogMediaType entity by typeId
			NewsMediaTypeEntity blogMediaType = blogMediaTypeDao.selectBlogMediaTypeById( blogMediaChannel.getTypeId() );
			
			// update blogMediaType status = 1
			if ( blogMediaType.getStatus() != 1 ) {
				blogMediaType.setStatus( 1 );
				blogMediaTypeDao.updateBlogMediaTypeStatus( blogMediaType );
			}
			
		}
		
		// audit blogMediaEntity
		blogMediaInfo.setAuditStatus( auditStatus );
		blogMediaInfo.setAuditDate( new Date() );
		blogMediaInfo.setAuditUser( auditUser );
		
		blogMediaDao.auditBlogMediaInfo( blogMediaInfo );
		
		return true;
		
	}

	@Override
	public Map<String, Object> getBlogMediaListByAdmin( AdminBlogMediaListQueryViewmodel viewmodel ) throws Exception {
		
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
		
		List<AdminBlogMediaListDTO> adminBlogMediaList = blogMediaDao.selectBlogMediaListByAdmin( viewmodel );
		if ( adminBlogMediaList == null || adminBlogMediaList.size() == 0 ) {
			return null;
		}
		Integer totalPages = 0;
		Integer totalRows = blogMediaDao.selectBlogMediaListByAdminCount( viewmodel );
		if ( recorders != null && recorders != 0 ) {
			totalPages = ( totalRows / recorders ) == 0 ? 1 : totalRows / recorders;
		}
		
		Map<String, Object> resData = new HashMap<String, Object>();
		resData.put( "mediaList", adminBlogMediaList );
		resData.put( "totalPages", totalPages );
		resData.put( "totalRows", totalRows );
		
		return resData;
		
	}

	@Override
	public Map<String, Object> getBlogMediaListByMember( MemberBlogMediaListQueryViewmodel viewmodel ) throws Exception {
		
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
		
		List<BaseBlogMediaListDTO> memberBlogMediaList = blogMediaDao.selectBlogMediaListByMember( viewmodel );
		if ( memberBlogMediaList == null || memberBlogMediaList.size() == 0 ) {
			return null;
		}
		
		Integer totalPages = 0;
		Integer totalRows = blogMediaDao.selectBlogMediaListByMemberCount( viewmodel );
		if ( recorders != null && recorders != 0 ) {
			totalPages = ( totalRows / recorders ) == 0 ? 1 : totalRows / recorders;
		}
		
		Map<String, Object> resData = new HashMap<String, Object>();
		resData.put( "mediaList", memberBlogMediaList );
		resData.put( "totalPages", totalPages );
		resData.put( "totalRows", totalRows );
		
		return resData;
		
		
	}

	@Override
	public Boolean editBlogMedia( Map<String, Object> formData, String rootDirProject ) throws Exception {
		
		BlogMediaUpdateViewmodel viewmodel = MediaTransforUtils.transMapToBlogMediaUpdate( formData );
		
		if ( viewmodel.getBlogMediaId() == null ) {
			return false;
		}
		
		// get typeId
		Integer typeId = null;
		if ( viewmodel.getTypeName() != null && !"".equals( viewmodel.getTypeName() ) ) {
			typeId = blogMediaTypeService.getOrAddBlogMediaTypeId( viewmodel.getTypeName() );
		}
		
		FileItem image = viewmodel.getFile();
		String headImage = "";
		if ( image != null ) {
			headImage = UploadUtils.uploadFile( image, 3, rootDirProject );
		}
		
		image = viewmodel.getQrCode();
		String qrCode = "";
		if ( image != null ) {
			qrCode = UploadUtils.uploadFile( image, 5, rootDirProject );
		}
		
		BlogMediaInfoEntity _blogMediaInfo = blogMediaDao.selectBlogMediaInfoById( viewmodel.getBlogMediaId() );
		BlogMediaChannelEntity _blogMediaChannel = blogMediaChannelDao.selectBlogMediaChannelById( _blogMediaInfo.getChannelId() );
		if ( viewmodel.getFansCount() != null ) {
			_blogMediaChannel.setFansCount( viewmodel.getFansCount() );
		}
		if ( !headImage.isEmpty() ) {
			_blogMediaChannel.setHeadImage( headImage );
		}
		if ( !qrCode.isEmpty() ) {
			_blogMediaChannel.setQrCode( qrCode );
		}
		if ( viewmodel.getRegisterDate() != null && !viewmodel.getRegisterDate().isEmpty() ) {
			_blogMediaChannel.setRegisterDate( viewmodel.getRegisterDate() );
		}
		if ( viewmodel.getAuthType() != null ) {
			_blogMediaChannel.setAuthType( viewmodel.getAuthType() );
		}
		if ( viewmodel.getAuthInfo() != null && !viewmodel.getAuthInfo().isEmpty() ) {
			_blogMediaChannel.setAuthInfo( viewmodel.getAuthInfo() );
		}
		if ( viewmodel.getDesc() != null && !viewmodel.getDesc().isEmpty() ) {
			_blogMediaChannel.setDesc( viewmodel.getDesc() );
		}
		if ( viewmodel.getNickname() != null && !viewmodel.getNickname().isEmpty() ) {
			_blogMediaChannel.setNickname( viewmodel.getNickname() );
		}
		if ( typeId != null ) {
			_blogMediaChannel.setTypeId( typeId );
		}
		
		// get channelId
		Integer channelId = null;
		if ( viewmodel.getNickname() != null && !"".equals( viewmodel.getNickname() ) ) {
			
			BlogMediaChannelEntity blogMediaChannel = blogMediaChannelDao.selectBlogMediaChannelByName( viewmodel.getNickname() );
			if ( blogMediaChannel == null ) {
				// insert
				_blogMediaChannel.setStatus( 1 );
				channelId = blogMediaChannelService.getOrAddBlogMediaChannelId( _blogMediaChannel );
				
				_blogMediaChannel.setChannelId( null );
			} else {
				channelId = _blogMediaChannel.getChannelId();
			}
			
		} else {
			channelId = _blogMediaChannel.getChannelId();
		}
		
		if ( _blogMediaChannel.getChannelId() != null ) {
			
			// update blog_media_channel
			blogMediaChannelDao.updateBlogMediaChannelByChannelId( _blogMediaChannel );
		}
		
		// update blog_media_info
		viewmodel.setChannelId( channelId );
		blogMediaDao.updateBlogMediaInfo( viewmodel );
		
		return true;
		
	}

	@Override
	public List<Map<String, Object>> getBlogMediaAreaList() throws Exception {
		
		return blogMediaDao.selectBlogMediaAreaList();
		
	}

	@Override
	public BigDecimal getOrderAmountById( int[] blogMediaId, int[] selectedId ) {
		
		BigDecimal transAmount = BigDecimal.ZERO;
		
		for ( int i = 0; i < blogMediaId.length; i++ ) {
			
			BlogMediaInfoEntity mediaInfo = blogMediaDao.selectBlogMediaInfoById( blogMediaId[i] );
			
			if ( selectedId[i] == 1 ) {
				transAmount = transAmount.add( mediaInfo.getPublishPrice() );
			} else if ( selectedId[i] == 2 ) {
				transAmount = transAmount.add( mediaInfo.getForwardPrice() );
			} else {
				return transAmount;
			}
			
		}
		
		return transAmount;
		
	}

	@Override
	public List<BlogMediaOrderSplitDTO> getOrderSplitDtoById( int[] blogMediaId,
			int[] selectedId ) {
		
		List<BlogMediaOrderSplitDTO> orderSplitDtos = new ArrayList<BlogMediaOrderSplitDTO>();
		
		for ( int i = 0; i < blogMediaId.length; i++ ) {
			
			BlogMediaOrderSplitDTO orderSplitDto = new BlogMediaOrderSplitDTO();
			BlogMediaInfoEntity mediaInfo = blogMediaDao.selectBlogMediaInfoById( blogMediaId[i] );
			
			if ( selectedId[i] == 1 ) {
				orderSplitDto.setTransAmount( mediaInfo.getPublishPrice() );
			} else if ( selectedId[i] == 2 ) {
				orderSplitDto.setTransAmount( mediaInfo.getForwardPrice() );
			} 
			
			orderSplitDto.setBlogMediaId( blogMediaId[i] );
			orderSplitDto.setMediaMemberId( mediaInfo.getCreatedUser() );
			orderSplitDto.setPriceType( selectedId[i] );
			
			orderSplitDtos.add( orderSplitDto );
			
		}
		
		return orderSplitDtos;
		
	}

	@Override
	public BlogMediaInfoEntity getBlogMediaInfoById( Integer blogMediaId ) {
		
		return blogMediaDao.selectBlogMediaInfoById( blogMediaId );
		
	}


}
