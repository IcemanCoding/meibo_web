package com.meibo.web.external.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.common.utils.HttpRequestUtils;
import com.meibo.web.external.service.YunXinFlowMediaService;
import com.meibo.web.external.utils.YunXinFlowUtils;
import com.meibo.web.media.dao.FlowMediaInfoDAO;
import com.meibo.web.media.entity.FlowMediaInfoEntity;
import com.meibo.web.media.service.FlowMediaService;
import com.meibo.web.utils.constants.ConstantsForYunXinFlow;

public class YunXinFlowMediaServiceImpl implements YunXinFlowMediaService {

	@Autowired
	private FlowMediaInfoDAO flowMediaInfoDao;
	
	@Autowired
	private FlowMediaService flowMediaService;
	
	@Override
	public void updateYunXinFlowPackage() throws Exception {
		
		String submitInfo = YunXinFlowUtils.buildSubmitInfoByPackageList();
		String postUrl = ConstantsForYunXinFlow.YUNXIN_FLOW_PACKAGELIST_URL;
		String userCode = ConstantsForYunXinFlow.YUNXIN_FLOW_ACCOUNT;
		
		Map<String, String> params = new HashMap<String, String>();
		params.put( "userCode", userCode );
		params.put( "submitInfo", submitInfo ); 
		
		// 165|,|电信全国 5M|,|LJ流量包|,|电信|,|1.0000|;|172|,|移动全国 10M|,|LJ流量包|,|移动|,|3.0000|;|160|,|联通全国 20M|,|lj流量|,|联通|,|3.0000
		String res = HttpRequestUtils.sendHttpPost( postUrl, params );
		if ( res == null || "".equals( res ) ) {
			return;
		}
		
		List<FlowMediaInfoEntity> packageList = YunXinFlowUtils.parseYunXinPackageListResp( res );
		int[] packageIdArr = new int[ packageList.size() ];
		for ( int i = 0; i < packageList.size(); i++ ) {
			
			FlowMediaInfoEntity flowEntity = packageList.get( i );
			flowEntity.setPlatformId( ConstantsForYunXinFlow.YUNXIN_FLOW_PLATFORMID );
			flowEntity.setStatus( 1 );
			
			flowMediaService.addFlowMediaInfo( flowEntity );
			
			packageIdArr[i] = flowEntity.getPackageId();
			
		}
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put( "platformId", ConstantsForYunXinFlow.YUNXIN_FLOW_PLATFORMID );
		queryParams.put( "packageIdArr", packageIdArr);
		
		flowMediaInfoDao.updateFLowPackageUnused( queryParams );
		
	}

	@Override
	public String rechargeFlow( String mobile, Integer dxPackageId, Integer ltPackageId, Integer ydPackageId ) throws Exception {
		
		String submitInfo = YunXinFlowUtils.buildSubmitInfoByRecharge( mobile, dxPackageId, ltPackageId, ydPackageId );
		String postUrl = ConstantsForYunXinFlow.YUNXIN_FLOW_FLOWORDER_URL;
		String userCode = ConstantsForYunXinFlow.YUNXIN_FLOW_ACCOUNT;
		
		Map<String, String> params = new HashMap<String, String>();
		params.put( "userCode", userCode );
		params.put( "submitInfo", submitInfo ); 
		
		// insert requestLog
		
		// 165|,|电信全国 5M|,|LJ流量包|,|电信|,|1.0000|;|172|,|移动全国 10M|,|LJ流量包|,|移动|,|3.0000|;|160|,|联通全国 20M|,|lj流量|,|联通|,|3.0000
		String res = HttpRequestUtils.sendHttpPost( postUrl, params );
		
		// insert responseLog
		return res;
		
	}

	@Override
	public String batchRechargeFlow( String[] mobile, Integer dxCode, Integer ltCode, Integer ydCode ) throws Exception {
		
		String desNo = "";
		for ( int i = 0; i < mobile.length; i++ ) {
			desNo += mobile[i] + ",";
		}
		desNo = desNo.substring( 0, desNo.length() - 1 );
		
		String submitInfo = YunXinFlowUtils.buildSubmitInfoByRecharge( desNo, dxCode, ltCode, ydCode );
		String postUrl = ConstantsForYunXinFlow.YUNXIN_FLOW_FLOWORDER_URL;
		String userCode = ConstantsForYunXinFlow.YUNXIN_FLOW_ACCOUNT;
		
		Map<String, String> params = new HashMap<String, String>();
		params.put( "userCode", userCode );
		params.put( "submitInfo", submitInfo ); 
		
		// insert requestLog
		
		// 165|,|电信全国 5M|,|LJ流量包|,|电信|,|1.0000|;|172|,|移动全国 10M|,|LJ流量包|,|移动|,|3.0000|;|160|,|联通全国 20M|,|lj流量|,|联通|,|3.0000
		String res = HttpRequestUtils.sendHttpPost( postUrl, params );
		
		// insert responseLog
		return res;
		
	}

}
