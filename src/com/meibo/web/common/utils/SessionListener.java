package com.meibo.web.common.utils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		SessionContext.deleteSession(arg0.getSession().getId());
	}
	
}
