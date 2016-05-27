package com.meibo.web.external.service;

import com.meibo.web.external.model.SendMessageModel;

public interface MessageService {
	
	Integer getMessagePlatform();
	
	Boolean sendMessage( SendMessageModel model );

}
