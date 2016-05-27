package com.meibo.web.order.controller;

import java.util.Date;
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
import com.meibo.web.order.dto.BaseMediaOrderStatusDetailDTO;
import com.meibo.web.order.dto.WechatMediaOrderDetailDTO;
import com.meibo.web.order.service.WechatMediaOrderService;
import com.meibo.web.order.utils.OrderDataTransforUtils;
import com.meibo.web.order.utils.ValidOrderInputsUtils;
import com.meibo.web.order.viewmodel.BaseMediaOrderListQueryViewmodel;
import com.meibo.web.order.viewmodel.WechatMediaCommitOrderViewmodel;

@RequestMapping ( "/wechatOrder" )
@Controller
public class WechatMediaOrderController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger( WechatMediaOrderController.class );
	
	@Autowired
	private WechatMediaOrderService wechatMediaOrderService;
	
	@RequestMapping ( value = "/list", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> list( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		
		if ( requestJson == null ) {
			requestJson = new JSONObject();
		}
		BaseMediaOrderListQueryViewmodel viewmodel = OrderDataTransforUtils.transNewsMediaListQueryByRequest( requestJson );
		
		viewmodel.setMemberId( viewModel.getMemberId() );
		
		try {
			resData = wechatMediaOrderService.getWechatMediaOrderList( viewmodel );
		} catch ( Exception e ) {
			logger.error( "查询微信媒体订单列表失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/detail", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> detail( BaseViewModel viewModel ) {

		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		Integer orderId = requestJson.getInteger( "orderId" );
		if ( orderId == null ) {
			return ContainerUtils.buildResFailMap( "请输入orderId" );
		}
		
		try {
			WechatMediaOrderDetailDTO orderDetail = wechatMediaOrderService.getWechatMediaOrderDetail( orderId );
			return ContainerUtils.buildResSuccessMap( orderDetail );
		} catch ( Exception e ) {
			logger.error( "查询微信媒体详情失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		
	}
	
	@RequestMapping ( value = "/orderSplitStatus", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> orderSplitStatus( BaseViewModel viewModel ) {
		
		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		if ( requestJson == null ) {
			return ContainerUtils.buildResFailMap( "参数有误!" );
		}
		Integer orderSplitId = requestJson.getInteger( "orderSplitId" );
		if ( orderSplitId == null ) {
			return ContainerUtils.buildResFailMap( "请输入orderSplitId!" );
		}
		
		try {
			BaseMediaOrderStatusDetailDTO orderStatusDetail = wechatMediaOrderService.getOrderSplitStatusDetail( orderSplitId );
			return ContainerUtils.buildResSuccessMap( orderStatusDetail );
		} catch ( Exception e ) {
			logger.error( "查询微信订单状态失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败!" );
		}
		
	}
	
	@RequestMapping ( value = "/editStatus", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> editStatus( BaseViewModel viewModel ) {
		
		Map<String, Object> resData = new HashMap<String, Object>();

		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		Integer orderSplitId = requestJson.getInteger( "orderSplitId" );
		// 1-接受 2-拒绝 3-已投放
		Integer procType = requestJson.getInteger( "procType" );
		if ( orderSplitId == null ) {
			return ContainerUtils.buildResFailMap( "请输入orderSplitId" );
		}
		if ( procType == null ) {
			return ContainerUtils.buildResFailMap( "请输入procType" );
		}
		
		try {
			Boolean ret = wechatMediaOrderService.editWechatMediaOrderSplitStatus( orderSplitId, procType );
			if ( !ret ) {
				return ContainerUtils.buildResFailMap( "操作类型错误或未投放媒体!" );
			}
		} catch ( Exception e ) {
			logger.error( "修改微信订单状态失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败!" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/editLaunch", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> editLaunch( BaseViewModel viewModel ) {
		
		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		Integer orderSplitId = requestJson.getInteger( "orderSplitId" );
		String launchUrl = requestJson.getString( "launchUrl" );
		// 2016-10-10
		Date launchDate = requestJson.getDate( "launchDate" );
		
		if ( orderSplitId == null ) {
			return ContainerUtils.buildResFailMap( "请输入orderSplitId!" );
		}
		if ( launchUrl.isEmpty() && launchDate == null ) {
			return ContainerUtils.buildResFailMap( "请输入投放地址或投放时间!" );
		}
		
		try {
			Boolean ret = wechatMediaOrderService.editWechatMediaOrderLaunch( orderSplitId, launchUrl, launchDate );
			if ( !ret ) {
				return ContainerUtils.buildResFailMap( "操作失败!" );
			}
		} catch ( Exception e ) {
			logger.error( "修改微信订单投放情况失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败!" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );
		
	}
	
	@RequestMapping ( value = "/add", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> add( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		Map<String, Object> formData = new HashMap<String, Object>();
		
		Integer memberId = viewModel.getMemberId();
		
		try {
			
			formData = RequestParseUtils.loadFormRequest( viewModel.getRequest() );
			formData.put( "memberId", memberId );
			
			// valid inputs
			String retMsg = ValidOrderInputsUtils.validWechatMediaCommitOrderInputs( formData );
			if ( !retMsg.equals( "" ) ) {
				return ContainerUtils.buildResFailMap( retMsg );
			}
			
			WechatMediaCommitOrderViewmodel viewmodel = OrderDataTransforUtils.transWechatMediaCommitOrderByFormData( formData );
			
			String rootDirProject = viewModel.getRequest().getSession().getServletContext().getRealPath("/");
			viewmodel.setRootDirProject( rootDirProject );
			viewmodel.setMemberId( memberId );
			
			Integer resFlag = wechatMediaOrderService.commitWechatMediaOrder( viewmodel );
			if ( resFlag == -2 ) {
				return ContainerUtils.buildResMap( resData, -2, "余额不足" );
			}
			
		} catch ( Exception e ) {
			logger.error( "微信媒体下单失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/pay", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> pay( BaseViewModel viewModel ) {
		
		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		if ( requestJson.isEmpty() ) {
			return ContainerUtils.buildResFailMap( "请输入orderId!" );
		}
		Integer orderId = requestJson.getInteger( "orderId" );
		if ( orderId == null ) {
			return ContainerUtils.buildResFailMap( "请输入orderId!" );
		}
		
		try {
			Integer ret = wechatMediaOrderService.payWechatMediaOrder( orderId );
			if ( ret == -2 ) {
				return ContainerUtils.buildResMap( resData, -2, "余额不足!" );
			} else if ( ret == -5 ) {
				return ContainerUtils.buildResMap( resData, 0, "商品已过期，请重新下单!" );
			} else if ( ret == -6 ) {
				return ContainerUtils.buildResMap( resData, 0, "订单已完成，请勿重复支付!" );
			} else if ( ret == -7 ) {
				return ContainerUtils.buildResMap( resData, 0, "非法操作!" );
			} else if ( ret == 0 ) {
				return ContainerUtils.buildResFailMap( "操作失败" );
			} 
		} catch ( Exception e ) {
			logger.error( "修改微博订单投放详情失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败!" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );
		
	}
	
}
