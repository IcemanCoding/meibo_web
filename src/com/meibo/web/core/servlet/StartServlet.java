package com.meibo.web.core.servlet;

import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meibo.web.core.utils.PropertiesUtil;

public class StartServlet extends HttpServlet {

	private static final long serialVersionUID = -6118618471272222481L;
	private static Logger logger = LoggerFactory.getLogger( StartServlet.class );

	@Override
	public void init( ServletConfig config ) throws ServletException {

		try {
			ServletContext servletContext = config.getServletContext();
			Properties prop = PropertiesUtil.getProperties();
			if ( MapUtils.isNotEmpty( prop ) ) {
				logger.info( "start to load prop" );
				for ( Map.Entry mapEntry : prop.entrySet() ) {
					if ( mapEntry == null ) {
						continue;
					}
					if ( mapEntry.getKey() == null
							|| mapEntry.getValue() == null ) {
						continue;
					}
					String key = ( String ) mapEntry.getKey();
					String val = ( String ) mapEntry.getValue();
					servletContext.setAttribute( key, val );
					logger.info( "key=" + key + ", val=" + val );
				}
				logger.info( "end to load prop" );
			}
		} catch ( Exception e ) {
			logger.error( "start fail......", e );
		}
	}

}
