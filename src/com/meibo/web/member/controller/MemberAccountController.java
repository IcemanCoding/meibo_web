package com.meibo.web.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.meibo.web.common.utils.MD5Utils;
import com.meibo.web.common.utils.ParseUtils;
import com.meibo.web.common.viewmodel.BaseViewModel;
import com.meibo.web.member.dto.MemberAccountDTO;
import com.meibo.web.member.entity.MemberInfoEntity;
import com.meibo.web.member.service.MemberAccountService;

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

}
