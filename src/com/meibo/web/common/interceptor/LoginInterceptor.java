package com.meibo.web.common.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.meibo.web.common.service.SessionService;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private SessionService sessionService;
	
	/**
	 * 登录、登出、注册不进行拦截
	 */
	private List<String> excludeUrls = Arrays.asList( new String[] { "login", "doRegister", "verifyCode", 
			"test", "testUpload" } );
	
	private String handUrl = "handCal";
	private List<String> images = Arrays.asList( "jpg", "gif", "jpeg", "png" );
	
	public boolean preHandle(HttpServletRequest request,
	        HttpServletResponse response, Object handler) {
		
		String uri = request.getRequestURI();
		String lastUrl = uri.substring(uri.lastIndexOf("/") + 1);

		if (excludeUrls.contains(lastUrl)) {
			return true;
		}
		if (uri.contains(handUrl)) {
			return true;
		}
		if (isImage(uri)) {
			return true;
		}
		String sessionId = request.getHeader( "sessionId" );
		JSONObject session = sessionService.getSessionData( sessionId );
		if (session != null
		        && session.get( "userInfo" ) != null) {
			
			JSONObject userInfo = session.getJSONObject( "userInfo" );
			String memberId = userInfo.getInteger( "memberId" ) + "";
			request.setAttribute( "memberId", memberId );
			
			return true;
		
		} else {
			PrintWriter writer = null;
			try {
				response.setContentType("application/json; charset=UTF-8");
				JSONObject fail = new JSONObject();
				fail.put( "flag", -1 );// 未登录
				fail.put( "msg", "登录超时" );
				writer = response.getWriter();
				writer.append( fail.toString() );
				writer.flush();
			} catch ( IOException e ) {
			} finally {
				IOUtils.closeQuietly( writer );
			}
			return false;
		}
		
	}
	
	private boolean isImage(String uri) {
		try {
			int suffixStart = uri.lastIndexOf(".");
			if (suffixStart > 0) {
				String suffix = uri.substring(suffixStart + 1, uri.length());
				if (images.contains(suffix)
				        || images.contains(suffix.toLowerCase())) {
					return true;
				}
			}
		} catch (Exception e) {
		}
		return false;
	}

}
