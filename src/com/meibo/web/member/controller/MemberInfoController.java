package com.meibo.web.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.meibo.web.common.controller.BaseController;
import com.meibo.web.common.service.SessionService;
import com.meibo.web.common.utils.ContainerUtils;
import com.meibo.web.common.utils.MD5Utils;
import com.meibo.web.common.utils.RequestParseUtils;
import com.meibo.web.member.dto.MemberInfoDTO;
import com.meibo.web.member.service.MemberInfoService;

@RequestMapping ( "/member" )
@Controller
public class MemberInfoController extends BaseController {
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private MemberInfoService memberInfoService;
	
	private static final Logger logger = LoggerFactory.getLogger( MemberInfoController.class );
	
	@RequestMapping ( value = "/login", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> login( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject reqJson = RequestParseUtils.loadPostRequest( request );
		
		String loginName = reqJson.getString( "loginName" );
		String loginPwd = reqJson.getString( "loginPwd" );

		/*
		 * 1、上送数据验证
		 */
		if ( loginName == null || "".equals( loginName ) ) {
			return ContainerUtils.buildResFailMap( "请输入登录用户名!" );
		}
		if ( loginPwd == null || "".equals( loginPwd ) ) {
			return ContainerUtils.buildResFailMap( "请输入登录密码!" );
		}
		
		MemberInfoDTO memberInfo = null;
		try {
			memberInfo = memberInfoService.login( loginName, loginPwd );
		} catch ( Exception e ) {
			logger.error( "登录操作失败!" + e );
			return ContainerUtils.buildResFailMap( "登录操作失败" );
		}
		
		if ( memberInfo == null ) {
			return ContainerUtils.buildResFailMap( "用户名或密码错误!" );
		}
		
		HttpSession session = request.getSession();
		String sessionId = MD5Utils.encode( session.getId() );
		
		resData.put( "sessionId", sessionId );
		
		sessionService.saveSession( session, sessionId );
		sessionService.saveSessionData( "userInfo", memberInfo, sessionId );
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/sessionId", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> sessionId( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		String memberId = request.getAttribute( "memberId" ) + "";
		
		System.out.println( memberId + " ---- " );
		
		HttpSession session = request.getSession();
		
		System.out.println( session.getId() );
		String sessionId = session.getAttribute( "sessionId" ) + "";
		System.out.println( sessionId );

		return ContainerUtils.buildResSuccessMap( resData );

	}

}
