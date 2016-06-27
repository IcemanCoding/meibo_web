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
import com.meibo.web.member.dto.CompanyListDTO;
import com.meibo.web.member.entity.MemberCompanyEntity;
import com.meibo.web.member.service.MemberCompanyService;
import com.meibo.web.member.utils.MemberTransforUtils;
import com.meibo.web.member.utils.MemberValidateUtils;
import com.meibo.web.member.viewmodel.AuditCompanyViewmodel;
import com.meibo.web.member.viewmodel.AuthCompanyViewmodel;
import com.meibo.web.member.viewmodel.CompanyListViewmodel;

@RequestMapping( "/company" )
@Controller
public class MemberCompanyController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger( MemberCompanyController.class );
	
	@Autowired
	private MemberCompanyService memberCompanyService;
	
	@RequestMapping ( value = "/add", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> add( BaseViewModel _viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		JSONObject requestJson = RequestParseUtils.loadPostRequest( _viewModel.getRequest() );
		if ( requestJson.isEmpty() ) {
			return ContainerUtils.buildResFailMap( "请输入参数!" );
		}
		String errorMsg = MemberValidateUtils.validateAuthCompanyInputsByJson( requestJson );
		if ( !errorMsg.isEmpty() ) {
			return ContainerUtils.buildResFailMap( errorMsg );
		}
		requestJson.put( "memberId", _viewModel.getMemberId() );
		AuthCompanyViewmodel viewmodel = MemberTransforUtils.transAuthCompanyByJson( requestJson );
		
		try {
			Integer ret = memberCompanyService.addCompanyInfo( viewmodel );
			if ( ret == -2 ) {
				return ContainerUtils.buildResFailMap( "已添加公司信息!" );
			} else if ( ret == -3 ) {
				return ContainerUtils.buildResFailMap( "该公司营业执照号已被认证!" );
			}
		} catch ( Exception e ) {
			logger.error( "保存公司信息失败!" + e );
			return ContainerUtils.buildResFailMap( "保存公司信息失败!" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/edit", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> edit( BaseViewModel _viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		JSONObject requestJson = RequestParseUtils.loadPostRequest( _viewModel.getRequest() );
		if ( requestJson.isEmpty() ) {
			return ContainerUtils.buildResFailMap( "请输入参数!" );
		}
		requestJson.put( "memberId", _viewModel.getMemberId() );
		AuthCompanyViewmodel viewmodel = MemberTransforUtils.transAuthCompanyByJson( requestJson );
		
		try {
			Integer ret = memberCompanyService.updateCompanyInfo( viewmodel );
			if ( ret == -2 ) {
				return ContainerUtils.buildResFailMap( "公司信息已通过审核!" );
			} else if ( ret == -3 ) {
				return ContainerUtils.buildResFailMap( "该公司营业执照号已被认证!" );
			}
		} catch ( Exception e ) {
			logger.error( "保存公司信息失败!" + e );
			return ContainerUtils.buildResFailMap( "保存公司信息失败!" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/list", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> list( BaseViewModel _viewModel ) {

		JSONObject requestJson = RequestParseUtils.loadPostRequest( _viewModel.getRequest() );
		if ( requestJson == null ) {
			requestJson = new JSONObject();
		}
		requestJson.put( "memberId", _viewModel.getMemberId() );
		CompanyListViewmodel viewmodel = MemberTransforUtils.transCompanyListByJson( requestJson );
		
		try {
			CompanyListDTO companyList = memberCompanyService.getCompanyList( viewmodel );
			return ContainerUtils.buildResSuccessMap( companyList );
		} catch ( Exception e ) {
			logger.error( "查询公司列表失败!" + e );
			return ContainerUtils.buildResFailMap( "查询公司列表失败!" );
		}
		
	}
	
	@RequestMapping ( value = "/audit", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> audit( BaseViewModel _viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		JSONObject requestJson = RequestParseUtils.loadPostRequest( _viewModel.getRequest() );
		if ( requestJson == null || requestJson.isEmpty() ) {
			return ContainerUtils.buildResFailMap( "请输入参数!" );
		}
		String errorMsg = MemberValidateUtils.validateAuditCompanyInputsByJson( requestJson );
		if ( !errorMsg.isEmpty() ) {
			return ContainerUtils.buildResFailMap( errorMsg );
		}
		requestJson.put( "memberId", _viewModel.getMemberId() );
		AuditCompanyViewmodel viewmodel = MemberTransforUtils.transAuditCompanyByJson( requestJson );
		
		try {
			Integer ret = memberCompanyService.auditCompanyInfo( viewmodel );
			if ( ret < 1 ) {
				return ContainerUtils.buildResFailMap( "审核公司信息失败!" );
			}
		} catch ( Exception e ) {
			logger.error( "审核公司信息失败!" + e );
			return ContainerUtils.buildResFailMap( "审核公司信息失败!" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/isAuthCompany", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> isAuthCompany( BaseViewModel _viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		try {
			resData = memberCompanyService.isAuthCompany( _viewModel.getMemberId() );
		} catch ( Exception e ) {
			logger.error( "审核公司信息失败!" + e );
			return ContainerUtils.buildResFailMap( "审核公司信息失败!" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/info", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> info( BaseViewModel _viewModel ) {

		try {
			MemberCompanyEntity memberCompany = memberCompanyService.getMemberCompany( _viewModel.getMemberId() );
			return ContainerUtils.buildResSuccessMap( memberCompany );
		} catch ( Exception e ) {
			logger.error( "查询公司信息失败!" + e );
			return ContainerUtils.buildResFailMap( "查询公司信息失败!" );
		}

	}

}
