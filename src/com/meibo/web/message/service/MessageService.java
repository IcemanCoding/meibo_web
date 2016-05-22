package com.meibo.web.message.service;

import com.meibo.web.message.model.SendMessageModel;

public interface MessageService {
	
	Integer getMessagePlatform();
	
	Boolean sendMessage( SendMessageModel model );

}
