package com.meibo.web.system.service;

import com.meibo.web.system.model.ConsumeTransModel;

public interface TradeCenterService {
	
	Boolean consumeTransHandle( ConsumeTransModel transModel );
	
	Boolean rechargeTransHandle( ConsumeTransModel transModel );

}
