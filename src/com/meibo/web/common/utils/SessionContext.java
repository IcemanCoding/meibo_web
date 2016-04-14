package com.meibo.web.common.utils;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

public class SessionContext {
	
	private static ConcurrentHashMap<String, HttpSession> sessionMap = new ConcurrentHashMap<String, HttpSession>();
	
	public static synchronized void addSession(HttpSession session,
	        String sessionId) {
		if (session != null) {
			sessionMap.put(sessionId, session);
		}
	}
	
	public static synchronized void deleteSession(String sessionId) {
		
		if (sessionId != null) {
			HttpSession session = sessionMap.remove(sessionId);
			if (session != null) {
				session.invalidate();
			}
		}
	}
	
	public static synchronized HttpSession getSession(String id) {
		if (id == null) {
			return null;
		}
		return sessionMap.get(id);
	}
}