package com.meibo.web.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSONObject;
import com.meibo.web.common.service.SessionService;
import com.meibo.web.common.viewmodel.BaseViewModel;


public class BaseController {
	
	@Autowired
	private SessionService sessionService;
	
	@ModelAttribute
	public BaseViewModel buildRequest( HttpServletRequest request ) {
		
		BaseViewModel viewModel = new BaseViewModel();
		JSONObject reqJson = sessionService.getSessionData( request.getHeader( "sessionId" ) );
		
		if ( reqJson == null || "".equals( reqJson ) ) {
			return null;
		}
		
		JSONObject userInfo = reqJson.getJSONObject( "userInfo" );
		
		viewModel.setMemberId( userInfo.getInteger( "memberId" ) );
		viewModel.setRequest( request );
		viewModel.setSessionId( request.getHeader( "sessionId" ) );
		
		return viewModel;
		
	}
	
}
