package com.meibo.web.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.meibo.web.common.controller.BaseController;
import com.meibo.web.common.utils.ContainerUtils;
import com.meibo.web.common.utils.RequestParseUtils;
import com.meibo.web.common.viewmodel.BaseViewModel;
import com.meibo.web.member.dto.AccountDetailListDTO;
import com.meibo.web.member.dto.MemberAccountDTO;
import com.meibo.web.member.service.MemberAccountService;
import com.meibo.web.member.utils.MemberTransforUtils;
import com.meibo.web.member.viewmodel.AccountDetailQueryViewmodel;

@RequestMapping ( "/member" )
@Controller
public class MemberAccountController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger( MemberAccountController.class );
	
	@Autowired
	private MemberAccountService memberAccountService;
	
	@RequestMapping ( value = "/account", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> accountInfo( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		Integer memberId = viewModel.getMemberId();
		
		MemberAccountDTO accountDto = null;
		
		try {
			accountDto = memberAccountService.getMemberAccount( memberId );
		} catch ( Exception e ) {
			logger.error( "获取账户信息失败" + memberId );
			return ContainerUtils.buildResFailMap( "获取账户信息失败" );
		}
		
		if ( accountDto == null ) {
			return ContainerUtils.buildResFailMap( "获取账户信息失败" );
		}
		
		resData.put( "accountInfo", accountDto );
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/accountDetail", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> accountDetail( BaseViewModel _viewModel ) {

		JSONObject requestJson = RequestParseUtils.loadPostRequest( _viewModel.getRequest() );
		if ( requestJson.isEmpty() ) {
			requestJson = new JSONObject();
		}
		requestJson.put( "memberId", _viewModel.getMemberId() );
		AccountDetailQueryViewmodel viewmodel = MemberTransforUtils.transAccountDetailQueryByJson( requestJson );
		
		try {
			AccountDetailListDTO resData = memberAccountService.getMemberAccountDetail( viewmodel );
			return ContainerUtils.buildResSuccessMap( resData );
		} catch ( Exception e ) {
//			logger.error( "获取账户信息失败" + memberId );
			return ContainerUtils.buildResFailMap( "获取账户信息失败" );
		}
		
	}

}
