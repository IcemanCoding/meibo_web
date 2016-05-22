package com.meibo.web.message.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.message.model.SendMessageModel;
import com.meibo.web.message.service.MessageService;
import com.meibo.web.message.service.YunXinMessageService;

public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private YunXinMessageService yunXinMessageService;

	@Override
	public Integer getMessagePlatform() {
		
		return 1;
		
	}

	@Override
	public Boolean sendMessage( SendMessageModel model ) {
		
		// insert message_log
		
		String sendResult = "";
		
		if ( model.getMessagePlatform() == 1 ) {
			sendResult = yunXinMessageService.sendMessage( model );
		}
		
		// update message_log
		
		if ( sendResult.isEmpty() ) {
			return false;
		}
		
		return true;
		
	}

}
