package com.meibo.web.common.utils;

import org.apache.commons.lang.math.RandomUtils;

public class RandomCodeGenerator {
	
	private final static String CODE_REPOSITORY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private final static String NUMBER_CODE_REPOSITORY = "1234567890";
	
	public static String produceCheckCode() {
		return generateRandomCodeWithNumeric(6);
	}
	
	public static String generateRandomCodeWithNumeric(int length) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int index = RandomUtils.nextInt(10);
			buffer.append(NUMBER_CODE_REPOSITORY.charAt(index));
		}
		return buffer.toString();
	}
	
	public static String generateRandomCode(int length) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int index = RandomUtils.nextInt(62);
			buffer.append(CODE_REPOSITORY.charAt(index));
		}
		return buffer.toString();
	}
	
	public static String producePassword() {
		return generateRandomCode(8);
	}
	
	public static void main(String[] args) {
		System.err.println(generateRandomCodeWithNumeric(8));
	}
	
}
