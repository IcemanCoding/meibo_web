package com.meibo.web.message.service.impl;

import com.meibo.web.message.model.SendMessageModel;
import com.meibo.web.message.service.YunXinMessageService;

public class YunXinMessageServiceImpl implements YunXinMessageService {

	@Override
	public String sendMessage( SendMessageModel model ) {
		
		if ( model.getDestMobileNum().isEmpty() || model.getMessageContent().isEmpty() ) {
			return null;
		}
		
		
		return "";
		
	}

}
