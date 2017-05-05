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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class MySimpleUrlAuthenticationSuccessHandler 
		extends SimpleUrlAuthenticationSuccessHandler 
		implements AuthenticationSuccessHandler {

	protected Logger logger = Logger.getLogger(this.getClass());

	private Map<String, String> authorityRedirectionMap;
	
	public MySimpleUrlAuthenticationSuccessHandler(Map<String, String> authorityRedirectionMap) {
		super();
		this.authorityRedirectionMap = authorityRedirectionMap;
	}
	
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String targetUrl = determineTargetUrl(request, response, authentication);

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to "
					+ targetUrl);
			return;
		}

		this.getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param authentication
	 * @return
	 */
	protected String determineTargetUrl(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
			if(this.authorityRedirectionMap.containsKey(grantedAuthority.getAuthority())){
				return this.authorityRedirectionMap.get(grantedAuthority.getAuthority());
			}
		}
		return super.determineTargetUrl(request, response);		
	}

}
