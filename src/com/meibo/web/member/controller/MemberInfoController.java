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
import com.meibo.web.common.viewmodel.BaseViewModel;
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
		resData.put( "memberType", memberInfo.getMemberType() );
		resData.put( "loginName", memberInfo.getLoginName() );
		resData.put( "roleType", memberInfo.getRoleId() );
		resData.put( "mobileNum", memberInfo.getMobileNum() );
		
		sessionService.saveSession( session, sessionId );
		sessionService.saveSessionData( "userInfo", memberInfo, sessionId );
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/sessionId", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> sessionId( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		String memberId = request.getAttribute( "memberId" ) + "";
		HttpSession session = request.getSession();
		String sessionId = session.getAttribute( "sessionId" ) + "";
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/editPassword", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> editPassword( BaseViewModel _viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject reqJson = RequestParseUtils.loadPostRequest( _viewModel.getRequest() );
		
		String oriPwd = reqJson.getString( "oriPwd" );
		String newPwd = reqJson.getString( "newPwd" );

		/*
		 * 1、上送数据验证
		 */
		if ( oriPwd == null || "".equals( oriPwd ) ) {
			return ContainerUtils.buildResFailMap( "请输入原登录密码!" );
		}
		if ( newPwd == null || "".equals( newPwd ) ) {
			return ContainerUtils.buildResFailMap( "请输入新登录密码!" );
		}
		if ( newPwd.equals( oriPwd ) ) {
			return ContainerUtils.buildResFailMap( "新密码与原密码重复!" );
		}
		
		try {
			Integer ret = memberInfoService.editLoginPassword( _viewModel.getMemberId(), oriPwd, newPwd );
			if ( ret == -2 ) {
				return ContainerUtils.buildResFailMap( "账户异常，请重新登录!" );
			} else if ( ret == -3 ) {
				return ContainerUtils.buildResFailMap( "原密码错误!" );
			}
		} catch ( Exception e ) {
			logger.error( "修改登录密码失败!" + e );
			return ContainerUtils.buildResFailMap( "修改登录密码失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}

}
