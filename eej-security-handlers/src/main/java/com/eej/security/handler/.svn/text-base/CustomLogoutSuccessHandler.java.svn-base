/**
 * 
 */
package com.eej.security.handler;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

/**
 * @author doibalmi
 *
 */
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler{

	private Logger logger = Logger.getLogger(this.getClass());
	
	private Map<String, String> uriResolverMap;

	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler#onLogoutSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		logger.debug(this.getClass().getSimpleName() + ".onLogoutSuccess");
		
		for(Enumeration en = request.getHeaderNames(); en.hasMoreElements();){
			String header = (String)en.nextElement(); 
			logger.debug(header + " = " + request.getHeader(header));
		}
		logger.debug(this.getClass().getSimpleName() + ".onLogoutSuccess Fin de headers");
		
		String referer = request.getHeader("referer");
		if(referer == null){
			referer = request.getHeader("host");
		}
		for(String s: uriResolverMap.keySet()){
			logger.debug(this.getClass().getSimpleName() + 
					".onLogoutSuccess: referer= " + referer + ", key=" + s);
			if(referer.contains(s)){
				logger.debug(this.getClass().getSimpleName() + 
						".onLogoutSuccess: Pattern in referer: " + s + " -> " + referer);
				this.setDefaultTargetUrl(this.uriResolverMap.get(s));
				break;
			}
		}
		super.onLogoutSuccess(request, response, authentication);
	}

	/**
	 * @return the uriResolverMap
	 */
	public Map<String, String> getUriResolverMap() {
		return uriResolverMap;
	}

	/**
	 * @param uriResolverMap the uriResolverMap to set
	 */
	public void setUriResolverMap(Map<String, String> uriResolverMap) {
		this.uriResolverMap = uriResolverMap;
	}
		
}
