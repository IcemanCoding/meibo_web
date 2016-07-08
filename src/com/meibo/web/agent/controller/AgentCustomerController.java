package com.meibo.web.agent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meibo.web.common.controller.BaseController;
import com.meibo.web.common.utils.ContainerUtils;
import com.meibo.web.common.viewmodel.BaseViewModel;

@RequestMapping ( "/agentCustomer" )
@Controller
public class AgentCustomerController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger( AgentCustomerController.class );
	
	@RequestMapping ( value = "/add", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> add( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		try {
		} catch ( Exception e ) {
			logger.error( "查询新闻媒体类型失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		return ContainerUtils.buildResSuccessMap( resData );

	}

}
