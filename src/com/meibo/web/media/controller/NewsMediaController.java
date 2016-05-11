package com.meibo.web.media.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.meibo.web.common.controller.BaseController;
import com.meibo.web.common.utils.ContainerUtils;
import com.meibo.web.common.utils.RequestParseUtils;
import com.meibo.web.common.viewmodel.BaseViewModel;
import com.meibo.web.media.entity.NewsMediaChannelEntity;
import com.meibo.web.media.service.NewsMediaChannelService;
import com.meibo.web.media.service.NewsMediaService;
import com.meibo.web.media.service.NewsMediaTypeService;
import com.meibo.web.media.utils.MediaTransforUtils;
import com.meibo.web.media.viewmodel.NewsMediaListQueryParams;

@RequestMapping ( "/newsMedia" )
@Controller
public class NewsMediaController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger( NewsMediaController.class );
	
	@Autowired
	private NewsMediaService newsMediaService;
	
	@Autowired
	private NewsMediaTypeService newsMediaTypeService;
	
	@Autowired
	private NewsMediaChannelService newsMediaChannelService;
	
	/**
	 * 新闻媒体分类
	 * @param viewModel
	 * @return
	 */
	@RequestMapping ( value = "/type", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> type( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		try {
			List<Map<String, Object>> typeList = newsMediaTypeService.getNewsMediaType();
			resData.put( "newsMediaType", typeList );
		} catch ( Exception e ) {
			logger.error( "查询新闻媒体类型失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * 新闻媒体渠道
	 * @param viewModel
	 * @return
	 */
	@RequestMapping ( value = "/channel", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> channel( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		try {
			List<NewsMediaChannelEntity> channelList = newsMediaChannelService.getNewsMediaChannel();
			resData.put( "newsMediaChannel", channelList );
		} catch ( Exception e ) {
			logger.error( "查询新闻媒体渠道失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * 地区
	 * @param viewModel
	 * @return
	 */
	@RequestMapping ( value = "/area", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> area( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		try {
			List<Map<String, Object>> areaList = newsMediaService.getNewsMediaAreaList();
			resData.put( "areaInfo", areaList );
		} catch ( Exception e ) {
			logger.error( "查询新闻媒体类型失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * 新增新闻媒体
	 * @param viewModel
	 * @return
	 */
	@RequestMapping ( value = "/add", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> add( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		Map<String, Object> formData = new HashMap<String, Object>();
		
		Integer memberId = viewModel.getMemberId();
		
		try {
			formData = RequestParseUtils.loadFormRequest( viewModel.getRequest() );
			formData.put( "memberId", memberId );
		} catch ( Exception e ) {
			logger.error( "转换form数据失败" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		String rootDirProject = viewModel.getRequest().getSession().getServletContext().getRealPath("/");
		try {
			if ( !newsMediaService.insertNewsMedia( formData, rootDirProject ) ) {
				return ContainerUtils.buildResFailMap( "操作失败" );
			}
		} catch ( Exception e ) {
			logger.error( "新增新闻媒体失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * 新闻媒体审核
	 * @param viewModel
	 * @return
	 */
	@RequestMapping ( value = "/audit", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> audit( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		Integer memberId = viewModel.getMemberId();
		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		Integer newsMediaId = requestJson.getInteger( "newsMediaId" );
		
		try {
			if ( !newsMediaService.auditNewsMedia( memberId, newsMediaId ) ) {
				ContainerUtils.buildResFailMap( "该媒体数据已审核" );
			}
		} catch ( Exception e ) {
			logger.error( "审核媒体数据失败!" + e );
			ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * 新闻媒体列表（管理员）
	 * @param viewModel
	 * @return
	 */
	@RequestMapping ( value = "/adminList", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> adminList( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		Integer memberId = viewModel.getMemberId();
		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		if ( requestJson == null ) {
			requestJson = new JSONObject();
		}
		requestJson.put( "memberId", memberId );
		NewsMediaListQueryParams params = MediaTransforUtils.transJsonToQueryParams( requestJson );
		
		try {
			resData = newsMediaService.getNewsMediaListByAdmin( params );
		} catch ( Exception e ) {
			logger.error( "查询新闻媒体失败!" + e );
			ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * 新闻媒体列表（注册会员）
	 * @param viewModel
	 * @return
	 */
	@RequestMapping ( value = "/memberList", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> memberList( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		Integer memberId = viewModel.getMemberId();
		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		if ( requestJson == null ) {
			requestJson = new JSONObject();
		}
		requestJson.put( "memberId", memberId );
		NewsMediaListQueryParams params = MediaTransforUtils.transJsonToQueryParams( requestJson );
		
		try {
			resData = newsMediaService.getNewsMediaListByMember( params );
		} catch ( Exception e ) {
			logger.error( "查询新闻媒体失败!" + e );
			ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * 新闻媒体修改
	 * @param viewModel
	 * @return
	 */
	@RequestMapping ( value = "/edit", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> edit( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		Map<String, Object> formData = new HashMap<String, Object>();
		
		Integer memberId = viewModel.getMemberId();
		
		try {
			formData = RequestParseUtils.loadFormRequest( viewModel.getRequest() );
			formData.put( "memberId", memberId );
		} catch ( Exception e ) {
			logger.error( "转换form数据失败" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		String rootDirProject = viewModel.getRequest().getSession().getServletContext().getRealPath("/");
		try {
			if ( !newsMediaService.updateNewsMedia( formData, rootDirProject ) ) {
				return ContainerUtils.buildResFailMap( "操作失败" );
			}
		} catch ( Exception e ) {
			logger.error( "更新新闻媒体失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}

}
