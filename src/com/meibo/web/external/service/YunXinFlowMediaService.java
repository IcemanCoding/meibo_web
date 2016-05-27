package com.meibo.web.external.service;


public interface YunXinFlowMediaService {
	
	void updateYunXinFlowPackage() throws Exception;
	
	String rechargeFlow( String mobile, Integer dxPackageId, Integer ltPackageId, Integer ydPackageId ) throws Exception;
	
	String batchRechargeFlow( String[] mobile, Integer dxCode, Integer ltCode, Integer ydCode ) throws Exception;

}
