package com.meibo.web.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HttpRequestUtils {

	public static String sendHttpPost( String postUrl, Map<String, String> postParams ) {
		
		String str = "";
		try {
			// 创建HttpClient实例
			HttpClient httpclient = new DefaultHttpClient();

			// 构造一个post对象
			HttpPost httpPost = new HttpPost( postUrl );
			
			// 添加所需要的post内容
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			
			if ( postParams != null ) {
				for ( Map.Entry<String, String> entry : postParams.entrySet() ) {
					nvps.add( new BasicNameValuePair( entry.getKey(), entry.getValue() ) );
				}
			}
			
			httpPost.setEntity( new UrlEncodedFormEntity( nvps, "UTF-8" ) );
			HttpResponse response = httpclient.execute( httpPost );
			HttpEntity entity = response.getEntity();
			if ( entity != null ) {
				InputStream instreams = entity.getContent();
				str += convertStreamToString( instreams );
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;

	}
	
	public static void main(String[] args) {
		
		sendFlow();
		
//		sendMessage();
		
	}
	
	private static void sendFlow() {
		
		// 获取用户流量套餐信息列表
		String sha1Str = DESUtils.SHA1( "SH2543" );
		sha1Str = sha1Str.toUpperCase().substring( 0, 8 );
		System.out.println( sha1Str ); // B7506071
		
		String s1 = null;
		try {
			s1 = DESUtils.encode( "userPass=SH2543".getBytes(), "B7506071" );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println( s1 ); // 4c98wZZaVrlwh8eAE/hnzw==
		
		s1 = "7A8940CB6E8EE702268F17B96E03783D809AEDC9B148641BC604284F627B92DE446B4643D0C3F1F184A61B0A5DF0F94228BF082FEF222789";
		
		String postUrl = "http://4g.1069106.com:9898/FlowAPI/GetProductsInfo.ashx";
		Map<String, String> param = new HashMap<String, String>();
		param.put( "userCode", "SBXXLL" );
		param.put( "submitInfo", s1 ); // 4c98wZZaVrlwh8eAE/hnzw==
//		param.put( "userPass", "SH2543" );
		
		String res = HttpRequestUtils.sendHttpPost( postUrl, param );
		System.out.println( res ); // -3
		
	}
	
	private static void sendMessage() {
		Map<String, String> params = new HashMap<String, String>();
		params.put( "userCode", "shsbcf" );
		params.put( "userPass", "GHYWXD423" );
		params.put( "DesNo", "13611920055" );
		params.put( "Msg", "尊敬的用户，您的验证码为：2312，请不要透露给任何人。感谢您对镁播的支持!【镁播】" );
		params.put( "Channel", "1" );
		String postUrl = "http://h.1069106.com:1210/Services/MsgSend.asmx/SendMsg";
		String s = sendHttpPost( postUrl, params );
		System.out.println( s );
	}
	
	public static String convertStreamToString( InputStream is ) {
		BufferedReader reader = new BufferedReader( new InputStreamReader( is ) );
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ( ( line = reader.readLine() ) != null ) {
				sb.append( line + "\n" );
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch ( IOException e ) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
