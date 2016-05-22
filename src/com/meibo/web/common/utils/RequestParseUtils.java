package com.meibo.web.common.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSONObject;

public class RequestParseUtils {
	
	public static JSONObject loadPostRequest( HttpServletRequest request ) {
		
		StringBuffer info = new StringBuffer();
		InputStream in;
		try {
			in = request.getInputStream();
			BufferedInputStream buf = new BufferedInputStream( in );
			byte[] buffer = new byte[10240000];
			int iRead;
			while ( ( iRead = buf.read( buffer ) ) != -1 ) {
				info.append( new String( buffer, 0, iRead, "UTF-8" ) );
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		
		JSONObject inputJson = JSONObject.parseObject( info.toString() );
		
		return inputJson;
		
	}
	
	public static Map<String, Object> loadFormRequest( HttpServletRequest request ) throws Exception {
		
		Map<String, Object> formData = new HashMap<String, Object>();
		
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload( diskFileItemFactory );

		List<FileItem> fileItems = servletFileUpload.parseRequest( request );
		for ( FileItem fileItem : fileItems ) {
			if ( fileItem.isFormField() ) {
				String paramValue = fileItem.getString( "UTF-8" );
				String paramKey = fileItem.getFieldName();
				formData.put( paramKey, paramValue ); 
			} else {
				String name = fileItem.getFieldName();
				if ( name.equals( "headImage" ) ) {
					formData.put( "file", fileItem );
				} else if ( name.equals( "qrCode" ) ) {
					formData.put( "qrCode", fileItem );
				} else if ( name.equals( "orderUploadFile" ) ) {
					formData.put( "uploadFile", fileItem );
				}
				
			}
		}
		
		return formData;
		
	}

}
