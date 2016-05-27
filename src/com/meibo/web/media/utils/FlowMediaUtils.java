package com.meibo.web.media.utils;

public class FlowMediaUtils {
	
	// 中国移动
	private static String CHINA_MOBILE = "134,135,136,137,138,139,147,150,151,152,157,158,159,182,183,187,188";
	// 中国联通
	private static String CHINA_UNICOM = "130,131,132,145,155,156,185,186";
	// 中国电信
	private static String CHINA_TELECOM = "133,153,180,181,189";
	
	public static Integer checkMobileCarrier( String mobile ) {
		
		if ( mobile == null || mobile.equals( "" ) ) {
			return null;
		}
		
		// 运营商类型：1-电信 2-联通 3-移动
		String mobileSub = mobile.substring( 0, 3 );
		if ( CHINA_MOBILE.indexOf( mobileSub ) != -1 ) {
			return 3;
		} else if ( CHINA_UNICOM.indexOf( mobileSub ) != -1 ) {
			return 2;
		} else if ( CHINA_TELECOM.indexOf( mobileSub ) != -1 ) {
			return 1;
		} else {
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		Integer r = checkMobileCarrier( "13611920055" );
		System.out.println( r );
	}

}
