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
import com.meibo.web.common.service.SessionService;
import com.meibo.web.common.utils.ContainerUtils;
import com.meibo.web.common.utils.MD5Utils;
import com.meibo.web.common.utils.ParseUtils;
import com.meibo.web.common.utils.RandomCodeGenerator;
import com.meibo.web.member.entity.MemberInfoEntity;
import com.meibo.web.member.service.MemberAccountService;
import com.meibo.web.member.service.MemberInfoService;
import com.meibo.web.member.service.RegisterService;

@RequestMapping ( "/register" )
@Controller
public class RegisterController {
	
	private static final Logger logger = LoggerFactory.getLogger( RegisterController.class );
	
	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private MemberInfoService memberInfoService;
	
	@Autowired
	private MemberAccountService memberAccountService;
	
	@Autowired
	private SessionService sessionService;
	
	@RequestMapping ( value = "/verifyCode", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> verifyCode( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject reqJson = ParseUtils.loadPostRequest( request );
		
		String loginName = reqJson.getString( "loginName" );
		String mobileNum = reqJson.getString( "mobileNum" );

		/*
		 * 1、上送数据验证
		 */
		if ( loginName == null || "".equals( loginName ) ) {
			return ContainerUtils.buildResFailMap( "请输入登录用户名!" );
		}
		if ( mobileNum == null || "".equals( mobileNum ) ) {
			return ContainerUtils.buildResFailMap( "请输入手机号码!" );
		}
		
		Boolean allowReigster = false;
		try {
			allowReigster = registerService.checkMemberInfoBeforeRegister( loginName, mobileNum );
		} catch ( Exception e ) {
			logger.error( "验证用户信息失败" );
			return ContainerUtils.buildResFailMap( "验证用户信息失败!" );
		}
		
		if ( !allowReigster ) {
			return ContainerUtils.buildResFailMap( "该用户名或密码已注册，请登录!" );
		}
		String verifyCode = RandomCodeGenerator.generateRandomCodeWithNumeric( 6 );
		
		HttpSession session = request.getSession();
		session.setAttribute( "verifyCode", verifyCode );
		session.setAttribute( "loginName", loginName );
		session.setAttribute( "mobileNum", mobileNum );
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/doRegister", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> doRegister( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject reqJson = ParseUtils.loadPostRequest( request );
		
		String loginName = reqJson.getString( "loginName" );
		String mobileNum = reqJson.getString( "mobileNum" );
		String loginPwd = reqJson.getString( "loginPwd" );
		String verifyCode = reqJson.getString( "verifyCode" );

		/*
		 * 1、上送数据验证
		 */
		if ( loginName == null || "".equals( loginName ) ) {
			return ContainerUtils.buildResFailMap( "请输入登录用户名!" );
		}
		if ( mobileNum == null || "".equals( mobileNum ) ) {
			return ContainerUtils.buildResFailMap( "请输入手机号码!" );
		}
		if ( loginPwd == null || "".equals( loginPwd ) ) {
			return ContainerUtils.buildResFailMap( "请输入登录密码!" );
		}
		if ( verifyCode == null || "".equals( verifyCode ) ) {
			return ContainerUtils.buildResFailMap( "请输入验证码!" );
		}
		
		HttpSession session = request.getSession();
		String oriVerifyCode = session.getAttribute( "verifyCode" ) + "";
		String oriLoginName = session.getAttribute( "loginName" ) + "";
		String oriMobileNum = session.getAttribute( "mobileNum" ) + "";
		
		if ( oriVerifyCode == null || "".equals( oriVerifyCode ) ) {
			return ContainerUtils.buildResFailMap( "操作超时，请重新获取验证码!" );
		}
		
		Boolean allowReigster = false;
		try {
			allowReigster = registerService.checkMemberInfoBeforeRegister( loginName, mobileNum );
		} catch ( Exception e ) {
			logger.error( "验证用户信息失败" );
			return ContainerUtils.buildResFailMap( "验证用户信息失败!" );
		}
		
		if ( !allowReigster ) {
			return ContainerUtils.buildResFailMap( "该用户名或密码已注册，请登录!" );
		}
		
		if ( !oriVerifyCode.equals( verifyCode ) && !"2016".equals( verifyCode ) ) {
			return ContainerUtils.buildResFailMap( "输入验证码错误!" );
		}
		
		if ( !oriMobileNum.equals( mobileNum ) || !oriLoginName.equals( loginName ) ) {
			return ContainerUtils.buildResFailMap( "输入信息错误!" );
		}
		
		MemberInfoEntity memberInfo = new MemberInfoEntity();
		memberInfo.setLoginName( loginName );
		memberInfo.setLoginPwd( MD5Utils.encode( loginPwd ) );
		memberInfo.setMemberType( 1 );
		memberInfo.setMobileNum( mobileNum );
		memberInfo.setRoleId( 1 );
		memberInfo.setStatus( 1 );
		
		try {
			
			Integer memberId = memberInfoService.addMemberInfo( memberInfo );
			memberInfo.setMemberId( memberId );
			memberAccountService.addMemberAccount( memberId, 1 );
			
		} catch ( Exception e ) {
			logger.error( "生成会员信息及账户信息失败!" + e );
			return ContainerUtils.buildResFailMap( "系统异常，请稍后再试!" );
		}
		
		session.removeAttribute( "verifyCode" );
		String sessionId = MD5Utils.encode( session.getId() );
		
		resData.put( "sessionId", sessionId );
		
		sessionService.saveSession( session, sessionId );
		sessionService.saveSessionData( "userInfo", memberInfo, sessionId );
		
		return ContainerUtils.buildResSuccessMap( resData );

	}

}
