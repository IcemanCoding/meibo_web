package com.meibo.web.core.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;


public class PropertiesUtil {

	private static Logger logger = LoggerFactory.getLogger( PropertiesUtil.class );
	public static String commonConfFilePath = "file:/datum/data/conf/config.properties";

	public static String getCommonConf( String key ) {
		
		Properties prop = getPropConf( commonConfFilePath );
		String val = null;
		if ( prop != null ) {
			val = prop.getProperty( key );
		}
		return val;
		
	}

	public static Properties getProperties() {
		Properties prop = getPropConf( commonConfFilePath );
		return prop;

	}
	
	// 配置文件的属性对象
	private static Map<String, Properties> propMap = new ConcurrentHashMap<String, Properties>(); 

	public static Properties getPropConf( String propPath ) {
		Properties sysConf = propMap.get( propPath );
		if ( sysConf == null ) {
			logger.info( "gain config " + propPath );
			synchronized ( PropertiesUtil.class ) {
				sysConf = propMap.get( propPath );
				if ( sysConf == null ) {
					sysConf = new Properties();
					FileInputStream fis = null;
					InputStreamReader isr = null;
					BufferedReader br = null;
					try {
						UrlResource urlResource = new UrlResource( propPath );
						String realPath = urlResource.getFile()
								.getAbsolutePath();

						fis = new FileInputStream( realPath );
						isr = new InputStreamReader( fis );
						br = new BufferedReader( isr );
						sysConf.load( br );
						propMap.put( propPath, sysConf );
					} catch ( Exception e ) {
						logger.info(
								"maybe propPath is not exit, it should in same disk with server"
										+ e.getMessage(), e );
						return sysConf;
					} finally {
						try {
							if ( fis != null ) {
								fis.close();
							}
							if ( isr != null ) {
								isr.close();
							}
							if ( br != null ) {
								br.close();
							}
						} catch ( Exception e ) {
							logger.info( e.getMessage(), e );
						}
					}
				}
			}

		}
		return sysConf;

	}

}
