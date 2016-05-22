package com.meibo.web.order.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.meibo.web.common.utils.RandomCodeGenerator;
import com.meibo.web.order.service.OrderInfoService;

public class OrderInfoServiceImpl implements OrderInfoService {

	@Override
	public String buildOrderCode( Integer mediaSource, Integer memberId ) {
		
		String orderCode = mediaSource + "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		orderCode += sdf.format( new Date() );
		
		orderCode += subStringInfo( memberId.toString(), 4 );
		orderCode += RandomCodeGenerator.generateRandomCodeWithNumeric( 4 );
		
		return orderCode;
		
	}
	
	public static void main(String[] args) {
		OrderInfoServiceImpl i = new OrderInfoServiceImpl();
		String s = i.buildOrderCode( 1, 23 );
		System.out.println( s);
	}
	
	private static String subStringInfo( String inputs, Integer length ) {
		
		String s = "";

		if ( inputs.length() > length ) {
			s = inputs.substring( inputs.length() - length );
		} else if ( inputs.length() == length ) {
			s = inputs;
		} else {
			for (int i = 0; i < length - inputs.length(); i++) {
				s += "0";
			}
			s += inputs;
		}
		
		return s;
		
	}

}
