package com.meibo.web.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

public class MD5Utils {
	
	public static String encode(String paramStr) {
		if (StringUtils.isEmpty(paramStr)) {
			return null;
		}
		
		// DigestUtils.
		return DigestUtils.md5Hex(paramStr);
		
	}
	
}
