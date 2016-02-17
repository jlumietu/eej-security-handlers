/**
 * 
 */
package com.eej.security.handler;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;


/**
 * @author jlumietu
 *
 */
public class CustomAuthenticationFailureHandler extends
	SimpleUrlAuthenticationFailureHandler{

	private Logger logger = Logger.getLogger(this.getClass());
	
	private Map<String, String> uriResolverMap;

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		logger.debug(this.getClass().getSimpleName() + ".onAuthenticationFailure");
		if(exception != null){
			logger.info("AuthenticationException : " + exception.getMessage());
		}
		
		String referer = request.getHeader("referer"); 
		for(String s: uriResolverMap.keySet()){
			if(referer.contains(s)){
				logger.debug(this.getClass().getSimpleName() + 
						".onAuthenticationFailure: Pattern in referer: " + s + " -> " + referer);
				this.setDefaultFailureUrl(this.uriResolverMap.get(s));
				break;
			}
		}
		
		super.onAuthenticationFailure(request, response, exception);
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
