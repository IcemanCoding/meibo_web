package com.meibo.web.order.controller;

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
import com.meibo.web.order.dto.RechargeOfflineListDTO;
import com.meibo.web.order.service.RechargeOrderService;
import com.meibo.web.order.utils.OrderDataTransforUtils;
import com.meibo.web.order.utils.ValidOrderInputsUtils;
import com.meibo.web.order.viewmodel.RechargeOfflineAuditViewmodel;
import com.meibo.web.order.viewmodel.RechargeOfflineCommitViewmodel;
import com.meibo.web.order.viewmodel.RechargeOfflineEditViewmodel;
import com.meibo.web.order.viewmodel.RechargeOfflineListQueryViewmodel;

@RequestMapping( "/offlineRecharge" )
@Controller
public class OfflineRechargeOrderController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger( OfflineRechargeOrderController.class );
	
	@Autowired
	private RechargeOrderService rechargeOrderService;
	
	@RequestMapping ( value = "/add", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> add( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		if ( requestJson == null ) {
			return ContainerUtils.buildResFailMap( "输入参数有误" );
		}
		
		String errorMsg = ValidOrderInputsUtils.validRechargeOfflineCommitInputs( requestJson );
		if ( !errorMsg.isEmpty() ) {
			return ContainerUtils.buildResFailMap( errorMsg );
		}
		
		requestJson.put( "memberId", viewModel.getMemberId() );
		
		RechargeOfflineCommitViewmodel viewmodel = OrderDataTransforUtils.transRechargeOfflineCommitByJson( requestJson );
		try {
			Integer ret = rechargeOrderService.addOfflineRecharge( viewmodel );
			if ( ret == -1 ) {
				return ContainerUtils.buildResFailMap( "该凭证号已被使用!" );
			}
			if ( ret < 1 ) {
				return ContainerUtils.buildResFailMap( "线下充值订单处理失败" );
			}
		} catch ( Exception e ) {
			logger.error( "线下充值订单处理失败" + e );
			return ContainerUtils.buildResFailMap( "线下充值订单处理失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/edit", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> edit( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		if ( requestJson == null ) {
			return ContainerUtils.buildResFailMap( "输入参数有误" );
		}
		
		String errorMsg = ValidOrderInputsUtils.validRechargeOfflineEditInputs( requestJson );
		if ( !errorMsg.isEmpty() ) {
			return ContainerUtils.buildResFailMap( errorMsg );
		}
		requestJson.put( "memberId", viewModel.getMemberId() );
		RechargeOfflineEditViewmodel viewmodel = OrderDataTransforUtils.transRechargeOfflineEditByJson( requestJson );
		
		try {
			Integer ret = rechargeOrderService.updateOfflineRecharge( viewmodel );
			if ( ret < 1 ) {
				return ContainerUtils.buildResFailMap( "线下充值订单修改失败" );
			}
		} catch ( Exception e ) {
			logger.error( "线下充值订单修改失败" + e );
			return ContainerUtils.buildResFailMap( "线下充值订单修改失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/audit", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> audit( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		if ( requestJson == null ) {
			return ContainerUtils.buildResFailMap( "输入参数有误" );
		}
		
		String errorMsg = ValidOrderInputsUtils.validRechargeOfflineAuditInputs( requestJson );
		if ( !errorMsg.isEmpty() ) {
			return ContainerUtils.buildResFailMap( errorMsg );
		}
		requestJson.put( "memberId", viewModel.getMemberId() );
		RechargeOfflineAuditViewmodel viewmodel = OrderDataTransforUtils.transRechargeOfflineAuditByJson( requestJson );
		
		
		try {
			Integer ret = rechargeOrderService.auditOfflineRecharge( viewmodel );
			if ( ret < 1 ) {
				return ContainerUtils.buildResFailMap( "线下充值订单审核失败" );
			}
		} catch ( Exception e ) {
			logger.error( "线下充值订单审核失败" + e );
			return ContainerUtils.buildResFailMap( "线下充值订单审核失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/list", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> list( BaseViewModel viewModel ) {

		
		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		if ( requestJson == null ) {
			requestJson = new JSONObject();
		}
		
		requestJson.put( "memberId", viewModel.getMemberId() );
		RechargeOfflineListQueryViewmodel viewmodel = OrderDataTransforUtils.transRechargeOfflineListByJson( requestJson );
		
		try {
			RechargeOfflineListDTO resData = rechargeOrderService.getOfflineRechargeList( viewmodel );
			return ContainerUtils.buildResSuccessMap( resData );
		} catch ( Exception e ) {
			logger.error( "查询线下充值列表失败" );
			return ContainerUtils.buildResFailMap( "查询线下充值列表失败" );
		}
		
	}

}
