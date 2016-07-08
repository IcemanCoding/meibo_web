package com.meibo.web.agent.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.meibo.web.agent.utils.AgentCustomerTransforUtils;
import com.meibo.web.agent.utils.AgentCustomerValidateUtils;
import com.meibo.web.agent.vo.AgentCustomerAddVO;
import com.meibo.web.common.controller.BaseController;
import com.meibo.web.common.utils.ContainerUtils;
import com.meibo.web.common.utils.RequestParseUtils;
import com.meibo.web.common.viewmodel.BaseViewModel;

@RequestMapping ( "/agentCustomer" )
@Controller
public class AgentCustomerController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger( AgentCustomerController.class );
	
	@RequestMapping ( value = "/add", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> add( BaseViewModel _viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject requestJson = RequestParseUtils.loadPostRequest( _viewModel.getRequest() );
		if ( requestJson.isEmpty() ) {
			return ContainerUtils.buildResFailMap( "请输入参数!" );
		}
		
		String errorMsg = AgentCustomerValidateUtils.validateAgentCustomerAddInputsByJson( requestJson );
		if ( !errorMsg.isEmpty() ) {
			return ContainerUtils.buildResFailMap( errorMsg );
		}
		requestJson.put( "memberId", _viewModel.getMemberId() );
		AgentCustomerAddVO agentCustomerAddVo = AgentCustomerTransforUtils.transAgentCustomerAddByJson( requestJson );
		
		try {
		} catch ( Exception e ) {
			logger.error( "新增代理商客户失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		return ContainerUtils.buildResSuccessMap( resData );

	}

}
