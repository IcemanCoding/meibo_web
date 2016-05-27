package com.meibo.web.order.service;

import com.meibo.web.common.viewmodel.BasePageViewModel;
import com.meibo.web.order.dto.FlowMediaOrderDetailListDTO;
import com.meibo.web.order.dto.FlowMediaOrderListDTO;
import com.meibo.web.order.viewmodel.FlowMediaOrderAddViewmodel;
import com.meibo.web.order.viewmodel.FlowMediaOrderBatchAddViewmodel;
import com.meibo.web.order.viewmodel.FlowMediaOrderDetailViewmodel;
import com.meibo.web.order.viewmodel.FlowMediaOrderPayViewmodel;

public interface FlowMediaOrderService {
	
	Integer commitFlowMediaOrder( FlowMediaOrderAddViewmodel viewmodel ) throws Exception;

	Integer batchCommitFlowMediaOrder( FlowMediaOrderBatchAddViewmodel flowMediaBatchAddViewmodel ) throws Exception;

	Integer payFlowMediaOrder( FlowMediaOrderPayViewmodel flowMediaOrderPayViewmodel ) throws Exception;

	FlowMediaOrderListDTO getFlowMediaOrderList( BasePageViewModel pageViewModel );

	FlowMediaOrderDetailListDTO getFlowMediaOrderDetail( FlowMediaOrderDetailViewmodel detailViewmodel );

}
