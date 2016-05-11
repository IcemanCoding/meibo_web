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
import com.meibo.web.media.service.WechatMediaService;
import com.meibo.web.media.service.WechatMediaTypeService;
import com.meibo.web.media.utils.MediaTransforUtils;
import com.meibo.web.media.viewmodel.AdminWechatMediaListQueryViewmodel;
import com.meibo.web.media.viewmodel.MemberWechatMediaListQueryViewmodel;

@RequestMapping ( "/wechatMedia" )
@Controller
public class WechatMediaController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger( WechatMediaController.class );
	
	@Autowired
	private WechatMediaTypeService wechatMediaTypeService;
	
	@Autowired
	private WechatMediaService wechatMediaService;
	
	/**
	 * 微信媒体分类
	 * @param viewModel
	 * @return
	 */
	@RequestMapping ( value = "/type", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> type( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		try {
			List<Map<String, Object>> typeList = wechatMediaTypeService.getWechatMediaType();
			resData.put( "typeList", typeList );
		} catch ( Exception e ) {
			logger.error( "查询微信媒体类型失败!" + e );
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
			List<Map<String, Object>> areaList = wechatMediaService.getWechatMediaAreaList();
			resData.put( "areaList", areaList );
		} catch ( Exception e ) {
			logger.error( "查询微信媒体地区失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * 新增微信媒体
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
			if ( !wechatMediaService.insertWechatMedia( formData, rootDirProject ) ) {
				logger.error( "新增新闻媒体失败!" );
				return ContainerUtils.buildResFailMap( "操作失败" );
			}
		} catch ( Exception e ) {
			logger.error( "新增新闻媒体失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * 微信媒体审核
	 * @param viewModel
	 * @return
	 */
	@RequestMapping ( value = "/audit", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> audit( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		Integer memberId = viewModel.getMemberId();
		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		Integer wechatMediaId = requestJson.getInteger( "wechatMediaId" );
		
		try {
			wechatMediaService.auditWechatMedia( memberId, wechatMediaId );
		} catch ( Exception e ) {
			logger.error( "审核媒体数据失败!" + e );
			ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * 微信媒体列表（管理员）
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
		AdminWechatMediaListQueryViewmodel viewmodel = MediaTransforUtils.transJsonToAdminMediaQuery( requestJson );
		
		try {
			resData = wechatMediaService.getWechatMediaListByAdmin( viewmodel );
		} catch ( Exception e ) {
			logger.error( "查询新闻媒体失败!" + e );
			ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * 微信媒体列表（注册会员）
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
		MemberWechatMediaListQueryViewmodel params = MediaTransforUtils.transJsonToMemberMediaQuery( requestJson );
		
		try {
			resData = wechatMediaService.getWechatMediaListByMember( params );
		} catch ( Exception e ) {
			logger.error( "查询微信媒体失败!" + e );
			ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * 微信媒体修改
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
			wechatMediaService.editWechatMedia( formData, rootDirProject );
		} catch ( Exception e ) {
			logger.error( "新增新闻媒体失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}

}
