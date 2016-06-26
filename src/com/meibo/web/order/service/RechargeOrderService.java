package com.meibo.web.order.service;

import com.meibo.web.order.dto.RechargeOfflineListDTO;
import com.meibo.web.order.viewmodel.RechargeOfflineAuditViewmodel;
import com.meibo.web.order.viewmodel.RechargeOfflineCommitViewmodel;
import com.meibo.web.order.viewmodel.RechargeOfflineEditViewmodel;
import com.meibo.web.order.viewmodel.RechargeOfflineListQueryViewmodel;

public interface RechargeOrderService {
	
	Integer addOfflineRecharge( RechargeOfflineCommitViewmodel viewmodel ) throws Exception;

	Integer updateOfflineRecharge( RechargeOfflineEditViewmodel viewmodel ) throws Exception;

	Integer auditOfflineRecharge( RechargeOfflineAuditViewmodel viewmodel ) throws Exception;

	RechargeOfflineListDTO getOfflineRechargeList( RechargeOfflineListQueryViewmodel viewmodel ) throws Exception;

}
