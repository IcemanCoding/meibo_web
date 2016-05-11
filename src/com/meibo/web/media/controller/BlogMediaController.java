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
import com.meibo.web.common.utils.ContainerUtils;
import com.meibo.web.common.utils.RequestParseUtils;
import com.meibo.web.common.viewmodel.BaseViewModel;
import com.meibo.web.media.service.BlogMediaService;
import com.meibo.web.media.service.BlogMediaTypeService;
import com.meibo.web.media.utils.MediaTransforUtils;
import com.meibo.web.media.viewmodel.AdminBlogMediaListQueryViewmodel;
import com.meibo.web.media.viewmodel.MemberBlogMediaListQueryViewmodel;

@RequestMapping ( "/blogMedia" )
@Controller
public class BlogMediaController {
	
	private static final Logger logger = LoggerFactory.getLogger( BlogMediaController.class );
	
	@Autowired
	private BlogMediaService blogMediaService;
	
	@Autowired
	private BlogMediaTypeService blogMediaTypeService;
	
	/**
	 * 微博媒体分类
	 * @param viewModel
	 * @return
	 */
	@RequestMapping ( value = "/type", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> type( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		try {
			List<Map<String, Object>> typeList = blogMediaTypeService.getBlogMediaType();
			resData.put( "typeList", typeList );
		} catch ( Exception e ) {
			logger.error( "查询新闻媒体类型失败!" + e );
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
			List<Map<String, Object>> areaList = blogMediaService.getBlogMediaAreaList();
			resData.put( "areaList", areaList );
		} catch ( Exception e ) {
			logger.error( "查询新闻媒体类型失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * 新增微博媒体
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
			blogMediaService.insertBlogMedia( formData, rootDirProject );
		} catch ( Exception e ) {
			logger.error( "新增微博媒体失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * 微博媒体审核
	 * @param viewModel
	 * @return
	 */
	@RequestMapping ( value = "/audit", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> audit( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		Integer memberId = viewModel.getMemberId();
		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		Integer blogMediaId = requestJson.getInteger( "blogMediaId" );
		
		try {
			blogMediaService.auditBlogMedia( memberId, blogMediaId );
		} catch ( Exception e ) {
			logger.error( "审核媒体数据失败!" + e );
			ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * 微博媒体列表（管理员）
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
		AdminBlogMediaListQueryViewmodel viewmodel = MediaTransforUtils.transJsonToAdminBlogMediaQuery( requestJson );
		
		try {
			resData = blogMediaService.getBlogMediaListByAdmin( viewmodel );
		} catch ( Exception e ) {
			logger.error( "查询新闻媒体失败!" + e );
			ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * 微博媒体列表（注册会员）
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
		MemberBlogMediaListQueryViewmodel params = MediaTransforUtils.transJsonToMemberBlogMediaQuery( requestJson );
		
		try {
			resData = blogMediaService.getBlogMediaListByMember( params );
		} catch ( Exception e ) {
			logger.error( "查询新闻媒体失败!" + e );
			ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	/**
	 * 微博媒体修改
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
			blogMediaService.editBlogMedia( formData, rootDirProject );
		} catch ( Exception e ) {
			logger.error( "修改微博媒体失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}

}
