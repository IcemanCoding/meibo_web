package com.meibo.web.external.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meibo.web.common.utils.DESUtils;
import com.meibo.web.common.utils.HttpRequestUtils;
import com.meibo.web.media.entity.FlowMediaInfoEntity;
import com.meibo.web.utils.constants.ConstantsForYunXinFlow;

public class YunXinFlowUtils {
	
	public static String buildSubmitInfoByPackageList() {
		
		String desKey = DESUtils.SHA1( ConstantsForYunXinFlow.YUNXIN_FLOW_PASSWORD ).toUpperCase().substring( 0, 8 );
		String content = "userPass=" + ConstantsForYunXinFlow.YUNXIN_FLOW_PASSWORD;
		String submitInfo = DESUtils.encrypt( content.getBytes(), desKey, desKey );
		
		return submitInfo.toUpperCase();
		
	}
	
	public static String buildSubmitInfoByRecharge( String mobile, Integer packageIdLT, Integer packageIdYD, Integer packageIdDX ) {
		
		// 运营商类型：1-电信 2-联通 3-移动
		
		String desKey = DESUtils.SHA1( ConstantsForYunXinFlow.YUNXIN_FLOW_PASSWORD ).toUpperCase().substring( 0, 8 );
		String content = "userPass=" + ConstantsForYunXinFlow.YUNXIN_FLOW_PASSWORD;
		content += "&DesNo=" + mobile;
		content += "&LTCode=" + packageIdLT;
		content += "&DXCode=" + packageIdDX;
		content += "&YDCode=" + packageIdYD;
		content += "&isNowActive=0";
		
		System.out.println( content );
		
		String submitInfo = DESUtils.encrypt( content.getBytes(), desKey, desKey );
		System.out.println( submitInfo );
		return submitInfo.toUpperCase();
		
	}
	
	public static void main(String[] args) {
//		System.out.println( buildSubmitInfoByPackageList() );
//		String res = "165|,|电信全国 5M|,|LJ流量包|,|电信|,|1.0000|;|172|,|移动全国 10M|,|LJ流量包|,|移动|,|3.0000|;|160|,|联通全国 20M|,|lj流量|,|联通|,|3.0000";
//		parseYunXinPackageListResp( res );
		String s = testRecharge();
//		String s = buildSubmitInfoByRecharge( "13611920055", 3, 165 );
		System.out.println( s );
	}
	
	private static String testRecharge() {
		
		String submitInfo = YunXinFlowUtils.buildSubmitInfoByRecharge( "13611920055", 160, 172, 165 );
		String postUrl = ConstantsForYunXinFlow.YUNXIN_FLOW_FLOWORDER_URL;
		String userCode = ConstantsForYunXinFlow.YUNXIN_FLOW_ACCOUNT;
		
		Map<String, String> params = new HashMap<String, String>();
		params.put( "userCode", userCode );
		params.put( "submitInfo", submitInfo ); 
		
		// insert requestLog
		
		// 165|,|电信全国 5M|,|LJ流量包|,|电信|,|1.0000|;|172|,|移动全国 10M|,|LJ流量包|,|移动|,|3.0000|;|160|,|联通全国 20M|,|lj流量|,|联通|,|3.0000
		String res = HttpRequestUtils.sendHttpPost( postUrl, params );
		
		// insert responseLog
		System.out.println( res + "----");
		
		if ( res == null || "".equals( res ) ) {
			return null;
		}
		
		System.out.println( res );
		return res;
		
	}

	public static List<FlowMediaInfoEntity> parseYunXinPackageListResp( String res ) {
		
		List<FlowMediaInfoEntity> packageList = new ArrayList<FlowMediaInfoEntity>();
		
		DecimalFormat df = new DecimalFormat( "0.00" );
		
		String[] packageListArr = res.split( "\\|;\\|" );
		for ( int i = 0; i < packageListArr.length; i++ ) {
			
			if ( packageListArr[i] == null || "".equals( packageListArr[i] ) ) {
				continue;
			}
			
			FlowMediaInfoEntity packageEntity = new FlowMediaInfoEntity();
			String packageInfo = packageListArr[i];
			String[] packageInfoArr = packageInfo.split( "\\|,\\|" );
			String carrierName = packageInfoArr[3];
			Integer carrierType = 1;
			// 运营商类型：1-电信 2-联通 3-移动
			if ( carrierName.equals( "电信" ) ) {
				carrierType = 1;
			} else if ( carrierName.equals( "联通" ) ) {
				carrierType = 2;
			} else if ( carrierName.equals( "移动" ) ) {
				carrierType = 3;
			} else {
				continue;
			}
			String price = packageInfoArr[4];
			price = df.format( new BigDecimal( price ) );
			packageEntity.setPackageId( Integer.parseInt( packageInfoArr[0] ) );
			packageEntity.setPackageName( packageInfoArr[1].split( " " )[1] );
			packageEntity.setExplain( packageInfoArr[2] );
			packageEntity.setPrice( new BigDecimal( price ) );
			packageEntity.setCarrierName( carrierName );
			packageEntity.setCarrierType(carrierType);
			
			packageList.add( packageEntity );
			
		}
		
		return packageList;
		
	}

}
