package com.meibo.web.common.service.impl;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.meibo.web.common.service.SessionService;
import com.meibo.web.common.utils.SessionContext;

public class SessionServiceImpl implements SessionService {

	@Override
	public JSONObject getSessionData(String sessionId) {
		HttpSession session = SessionContext.getSession(sessionId);
		if (session == null) {
			return null;
		}
		try {
			Enumeration<String> attributeNames = session.getAttributeNames();
			if (attributeNames == null) {
				return null;
			}
			JSONObject resultJson = new JSONObject();
			while (attributeNames.hasMoreElements()) {
				String attributeName = attributeNames.nextElement();
				resultJson.put(attributeName,
				        session.getAttribute(attributeName));
			}
			return resultJson;
		} catch (IllegalStateException e) {
			// 如果是session失效直接返回
			return null;
		} catch (Throwable e) {
			return null;
		}
		
	}
	
	@Override
	public boolean saveSessionData(String dataKey, Object sessionData,
	        String sessionId) {
		HttpSession session = SessionContext.getSession(sessionId);
		if (session != null) {
			session.setAttribute(dataKey, sessionData);
		} else {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean saveSession(HttpSession session, String sessionId) {
		
		SessionContext.addSession(session, sessionId);
		return true;
	}
	
	@Override
	public boolean deleteSession(String sessionId) {
		
		SessionContext.deleteSession(sessionId);
		return true;
	}
	
	/**
	 * 判断sessionId对应数据是否存在
	 */
	@Override
	public boolean exitsSession(String sessionId) {
		return SessionContext.getSession(sessionId) == null ? false : true;
		
	}
	
	
}
