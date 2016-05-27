package com.meibo.web.external.service.impl;

import com.meibo.web.external.model.SendMessageModel;
import com.meibo.web.external.service.YunXinMessageService;

public class YunXinMessageServiceImpl implements YunXinMessageService {

	@Override
	public String sendMessage( SendMessageModel model ) {
		
		if ( model.getDestMobileNum().isEmpty() || model.getMessageContent().isEmpty() ) {
			return null;
		}
		
		
		return "";
		
	}

}
