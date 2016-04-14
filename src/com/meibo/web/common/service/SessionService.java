package com.meibo.web.common.service;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

public interface SessionService {
	
	public JSONObject getSessionData(String sessionId);
	
	public boolean saveSessionData(String dataKey, Object sessionData,
	        String sessionId);
	
	public boolean saveSession(HttpSession session, String sessionId);
	
	public boolean deleteSession(String sessionId);
	
	public boolean exitsSession(String sessionId);

}
