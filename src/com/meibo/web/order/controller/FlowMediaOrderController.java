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
import com.meibo.web.common.viewmodel.BasePageViewModel;
import com.meibo.web.common.viewmodel.BaseViewModel;
import com.meibo.web.media.utils.MediaTransforUtils;
import com.meibo.web.media.utils.MediaValidateUtils;
import com.meibo.web.order.dto.FlowMediaOrderDetailListDTO;
import com.meibo.web.order.dto.FlowMediaOrderListDTO;
import com.meibo.web.order.service.FlowMediaOrderService;
import com.meibo.web.order.viewmodel.FlowMediaOrderAddViewmodel;
import com.meibo.web.order.viewmodel.FlowMediaOrderBatchAddViewmodel;
import com.meibo.web.order.viewmodel.FlowMediaOrderDetailViewmodel;
import com.meibo.web.order.viewmodel.FlowMediaOrderPayViewmodel;

@RequestMapping( "/flowOrder" )
@Controller
public class FlowMediaOrderController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger( FlowMediaOrderController.class );
	
	@Autowired
	private FlowMediaOrderService flowMediaOrderService;
	
	@RequestMapping ( value = "/add", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> add( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		
		if ( requestJson == null ) {
			return ContainerUtils.buildResFailMap( "请输入手机号或选择套餐!" );
		}
		requestJson.put( "memberId", viewModel.getMemberId() );
		
		// validate inputs
		String retMsg = MediaValidateUtils.validateFlowMediaAddInputs( requestJson );
		if ( !retMsg.isEmpty() ) {
			return ContainerUtils.buildResFailMap( retMsg );
		}
		
		// transfor model
		FlowMediaOrderAddViewmodel flowMediaAddViewmodel = MediaTransforUtils.transJsonToFlowMediaAddViewmodel( requestJson );
		
		try {
			Integer ret = flowMediaOrderService.commitFlowMediaOrder( flowMediaAddViewmodel );
			if ( ret == -2 ) {
				return ContainerUtils.buildResMap( resData, -2, "余额不足!" );
			} else if ( ret == -3 ) {
				return ContainerUtils.buildResMap( resData, -3, "套餐不匹配!" );
			} else if ( ret == -4 ) {
				return ContainerUtils.buildResMap( resData, -4, "流量充值功能暂未开通!" );
			}
		} catch ( Exception e ) {
			logger.error( "流量充值失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap();

	}
	
	@RequestMapping ( value = "/batchAdd", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> batchAdd( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		
		if ( requestJson == null ) {
			return ContainerUtils.buildResFailMap( "输入信息有误!" );
		}
		requestJson.put( "memberId", viewModel.getMemberId() );
		
		// validate inputs
		String retMsg = MediaValidateUtils.validateFlowMediaBatchAddInputs( requestJson );
		if ( !retMsg.isEmpty() ) {
			return ContainerUtils.buildResFailMap( retMsg );
		}
		
		// transfor model
		FlowMediaOrderBatchAddViewmodel flowMediaBatchAddViewmodel = MediaTransforUtils.transJsonToFlowMediaBatchAddViewmodel( requestJson );
		
		try {
			Integer ret = flowMediaOrderService.batchCommitFlowMediaOrder( flowMediaBatchAddViewmodel );
			if ( ret == -2 ) {
				return ContainerUtils.buildResMap( resData, -2, "余额不足!" );
			} else if ( ret == -3 ) {
				return ContainerUtils.buildResMap( resData, -3, "套餐不匹配!" );
			} else if ( ret == -4 ) {
				return ContainerUtils.buildResMap( resData, -4, "流量充值功能暂未开通!" );
			}
		} catch ( Exception e ) {
			logger.error( "流量充值失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap();

	}
	
	@RequestMapping ( value = "/pay", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> pay( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		
		if ( requestJson == null ) {
			return ContainerUtils.buildResFailMap( "请输入手机号或选择套餐!" );
		}
		requestJson.put( "memberId", viewModel.getMemberId() );
		
		// validate inputs
		String retMsg = MediaValidateUtils.validateFlowMediaPayInputs( requestJson );
		if ( !retMsg.isEmpty() ) {
			return ContainerUtils.buildResFailMap( retMsg );
		}
		
		// transfor model
		FlowMediaOrderPayViewmodel flowMediaOrderPayViewmodel = MediaTransforUtils.transJsonToFlowMediaPayViewmodel( requestJson );
		
		try {
			Integer ret = flowMediaOrderService.payFlowMediaOrder( flowMediaOrderPayViewmodel );
			if ( ret == -2 ) {
				return ContainerUtils.buildResMap( resData, -2, "余额不足!" );
			} else if ( ret == -5 ) {
				return ContainerUtils.buildResMap( resData, 0, "套餐已过期，请重新下单!" );
			} else if ( ret == -6 ) {
				return ContainerUtils.buildResMap( resData, 0, "订单已完成，请勿重复支付!" );
			} else if ( ret == -7 ) {
				return ContainerUtils.buildResMap( resData, 0, "非法操作!" );
			} else if ( ret == 0 ) {
				return ContainerUtils.buildResFailMap( "操作失败" );
			} else if ( ret == -4 ) {
				return ContainerUtils.buildResMap( resData, -4, "流量充值功能暂未开通!" );
			}
		} catch ( Exception e ) {
			logger.error( "流量订单支付失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/list", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> list( BaseViewModel viewModel ) {

		BasePageViewModel pageViewModel = new BasePageViewModel();
		
		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		if ( requestJson.isEmpty() ) {
			requestJson = new JSONObject();
		}
		if ( requestJson.getInteger( "pageNum" ) != null ) {
			pageViewModel.setPageNum( requestJson.getInteger( "pageNum" ) );
		} else {
			pageViewModel.setPageNum( 1 );
		}
		if ( requestJson.getInteger( "pageRecorders" ) != null ) {
			pageViewModel.setPageRecorders( requestJson.getInteger( "pageRecorders" ) );
		} else {
			pageViewModel.setPageRecorders( 10 );
		}
		pageViewModel.setMemberId( viewModel.getMemberId() );
		
		try {
			FlowMediaOrderListDTO orderList = flowMediaOrderService.getFlowMediaOrderList( pageViewModel );
			return ContainerUtils.buildResSuccessMap( orderList );
		} catch ( Exception e ) {
			logger.error( "查询流量订单列表失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}

	}

	@RequestMapping ( value = "/detail", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> detail( BaseViewModel viewModel ) {

		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		if ( requestJson.isEmpty() ) {
			return ContainerUtils.buildResFailMap( "参数有误!" );
		}
		
		FlowMediaOrderDetailViewmodel detailViewmodel = new FlowMediaOrderDetailViewmodel();
		
		Integer orderId = requestJson.getInteger( "orderId" );
		if ( orderId == null ) {
			return ContainerUtils.buildResFailMap( "参数有误!" );
		}
		if ( requestJson.getInteger( "pageNum" ) != null ) {
			detailViewmodel.setPageNum( requestJson.getInteger( "pageNum" ) );
		} else {
			detailViewmodel.setPageNum( 1 );
		}
		if ( requestJson.getInteger( "pageRecorders" ) != null ) {
			detailViewmodel.setPageRecorders( requestJson.getInteger( "pageRecorders" ) );
		} else {
			detailViewmodel.setPageRecorders( 10 );
		}
		detailViewmodel.setMemberId( viewModel.getMemberId() );
		detailViewmodel.setOrderId( orderId );
		
		try {
			FlowMediaOrderDetailListDTO orderDetail = flowMediaOrderService.getFlowMediaOrderDetail( detailViewmodel );
			return ContainerUtils.buildResSuccessMap( orderDetail );
		} catch ( Exception e ) {
			logger.error( "查询流量订单详情失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}

	}

}
