/**
 * 
 */
package com.eej.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

/**
 * @author jlumietu
 *
 */
public class RestServiceAccessDeniedHandler 
		extends	Http403ForbiddenEntryPoint 
		implements AuthenticationEntryPoint, AccessDeniedHandler, AuthenticationFailureHandler {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.Http403ForbiddenEntryPoint#commence(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authenticationException)
			throws IOException, ServletException {
		logger.debug("commence, authenticationException = " + authenticationException.getMessage(), authenticationException);
		super.commence(request, response, authenticationException);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.access.AccessDeniedHandler#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.access.AccessDeniedException)
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		logger.debug("handle(req, res, " + accessDeniedException.getMessage() + ")");
		if (!response.isCommitted()) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
		}

	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authenticationException)
			throws IOException, ServletException {
		logger.debug("handle(req, res, " + authenticationException.getMessage() + ")");
		if (!response.isCommitted()) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, authenticationException.getMessage());
		}		
	}
	
	

}
