package com.meibo.web.common.utils;

import java.io.File;
import java.util.Date;

import org.apache.commons.fileupload.FileItem;

import com.meibo.web.utils.constants.ConstantsForUpload;

public class UploadUtils {
	
	public static String uploadFile( FileItem file, Integer fileType, String dirPath ) throws Exception {

		String fileName = file.getName();
		String saveFilePath = "";

		// 用户选择了上传的文件, 并且该文件的名字不为空
		if ( file.getName() != null && !"".equals( file.getName() ) ) {
			
			if ( !checkContentType( fileName, fileType ) ) {
				return null;
			}

			if ( fileType.equals( ConstantsForUpload.NEWS_MEDIA_IMAGE_TYPE ) ) {
				dirPath += ConstantsForUpload.NEWS_MEDIA_IMAGE_DIR;
				saveFilePath = ConstantsForUpload.NEWS_MEDIA_IMAGE_DIR;
			} else if ( fileType.equals( ConstantsForUpload.WECHAT_MEDIA_IMAGE_TYPE ) ) {
				dirPath += ConstantsForUpload.WECHAT_MEDIA_IMAGE_DIR;
				saveFilePath = ConstantsForUpload.WECHAT_MEDIA_IMAGE_DIR;
			} else if ( fileType.equals( ConstantsForUpload.BLOG_MEDIA_IMAGE_TYPE ) ) {
				dirPath += ConstantsForUpload.BLOG_MEDIA_IMAGE_DIR;
				saveFilePath = ConstantsForUpload.BLOG_MEDIA_IMAGE_DIR;
			} else if ( fileType.equals( ConstantsForUpload.WECHAT_QRCODE_IMAGE_TYPE ) ) {
				dirPath += ConstantsForUpload.WECHAT_QRCODE_IMAGE_DIR;
				saveFilePath = ConstantsForUpload.WECHAT_QRCODE_IMAGE_DIR;
			} else if ( fileType.equals( ConstantsForUpload.BLOG_QRCODE_IMAGE_TYPE ) ) {
				dirPath += ConstantsForUpload.BLOGQRCODE_IMAGE_DIR;
				saveFilePath = ConstantsForUpload.BLOGQRCODE_IMAGE_DIR;
			} else if ( fileType.equals( ConstantsForUpload.ORDER_BLOG_UPLOAD_TYPE ) ) {
				dirPath += ConstantsForUpload.ORDER_BLOG_UPLOAD_DIR;
				saveFilePath = ConstantsForUpload.ORDER_BLOG_UPLOAD_DIR;
			} else if ( fileType.equals( ConstantsForUpload.ORDER_NEWS_UPLOAD_TYPE ) ) {
				dirPath += ConstantsForUpload.ORDER_NEWS_UPLOAD_DIR;
				saveFilePath = ConstantsForUpload.ORDER_NEWS_UPLOAD_DIR;
			} else if ( fileType.equals( ConstantsForUpload.ORDER_WECHAT_UPLOAD_TYPE ) ) {
				dirPath += ConstantsForUpload.ORDER_WECHAT_UPLOAD_DIR;
				saveFilePath = ConstantsForUpload.ORDER_WECHAT_UPLOAD_DIR;
			}

			// 拼接保存的上传文件的目录
			File saveFileUploadFile = new File( dirPath );

			// 如果保存上传文件的目录不存在, 创建
			if (!saveFileUploadFile.exists()) {
				saveFileUploadFile.mkdirs();
			}
			
			// 获得上传文件的类型
			String contentType = fileName.split( "\\." )[1];
			String saveFileName = buildFileName( contentType );
			saveFilePath += "/" + saveFileName;

			// 拼接保存上传内容的文件
			File saveTheFileUpload = new File( saveFileUploadFile, saveFileName );

			// 保存上传的文件
			file.write( saveTheFileUpload );
			
		}

		return saveFilePath;

	}
	
	private static Boolean checkContentType( String fileName, Integer fileType ) {
		
		try {
			if ( fileType.equals( ConstantsForUpload.NEWS_MEDIA_IMAGE_TYPE )  ) {
				String filterType = ConstantsForUpload.MEDIA_IMAGE_FILTER;
				String contentType = fileName.split( "\\." )[1];
				if ( filterType.contains( contentType.toUpperCase() ) ) {
					return true;
				}
			} else if ( fileType.equals( ConstantsForUpload.WECHAT_MEDIA_IMAGE_TYPE ) ) {
				String filterType = ConstantsForUpload.MEDIA_IMAGE_FILTER;
				String contentType = fileName.split( "\\." )[1];
				if ( filterType.contains( contentType.toUpperCase() ) ) {
					return true;
				}
			} else if ( fileType.equals( ConstantsForUpload.BLOG_MEDIA_IMAGE_TYPE ) ) {
				String filterType = ConstantsForUpload.MEDIA_IMAGE_FILTER;
				String contentType = fileName.split( "\\." )[1];
				if ( filterType.contains( contentType.toUpperCase() ) ) {
					return true;
				}
			} else if ( fileType.equals( ConstantsForUpload.WECHAT_QRCODE_IMAGE_TYPE ) ) {
				String filterType = ConstantsForUpload.MEDIA_IMAGE_FILTER;
				String contentType = fileName.split( "\\." )[1];
				if ( filterType.contains( contentType.toUpperCase() ) ) {
					return true;
				}
			} else if ( fileType.equals( ConstantsForUpload.BLOG_QRCODE_IMAGE_TYPE ) ) {
				String filterType = ConstantsForUpload.MEDIA_IMAGE_FILTER;
				String contentType = fileName.split( "\\." )[1];
				if ( filterType.contains( contentType.toUpperCase() ) ) {
					return true;
				}
			} else if ( fileType.equals( ConstantsForUpload.ORDER_BLOG_UPLOAD_TYPE ) 
					|| fileType.equals( ConstantsForUpload.ORDER_WECHAT_UPLOAD_TYPE ) 
					|| fileType.equals( ConstantsForUpload.ORDER_NEWS_UPLOAD_TYPE ) ) {
				return true;
			}
		} catch ( Exception e ) {
			return false;
		}
		
		return false;
		
	}
	
	private static String buildFileName( String fileContentType ) {
		
		String fileName = "";
		fileName += new Date().getTime();
		fileName += RandomCodeGenerator.generateRandomCodeWithNumeric( 6 );
		fileName += "." + fileContentType;
		
		return fileName;
		
	}
	
	public static void main(String[] args) {
		
		String i = buildFileName( "jpg" );
		System.out.println(i);
		
	}

}
