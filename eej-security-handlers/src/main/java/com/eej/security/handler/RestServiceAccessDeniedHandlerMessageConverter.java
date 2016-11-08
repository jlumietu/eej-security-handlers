/**
 * 
 */
package com.eej.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.eej.security.handler.helper.MessageConverterWrapper;
import com.eej.security.handler.model.RestServiceAccessDeniedMessageBody;
import com.eej.security.handler.model.RestServiceAccessDeniedMessageBodyImpl;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public class RestServiceAccessDeniedHandlerMessageConverter extends RestServiceAccessDeniedHandler
		implements AuthenticationEntryPoint, AccessDeniedHandler, AuthenticationFailureHandler {
	
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private MessageConverterWrapper messageConverters;
	
	/**
	 * 
	 * @param messageConverters
	 */
	public RestServiceAccessDeniedHandlerMessageConverter(MessageConverterWrapper messageConverters) {
		super();
		this.messageConverters = messageConverters;
	}

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
		if(this.messageConverters != null){
			this.sendError(HttpServletResponse.SC_FORBIDDEN, request, response, accessDeniedException);
		}else{
			super.handle(request, response, accessDeniedException);
		}

	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authenticationException)
			throws IOException, ServletException {
		logger.debug("handle(req, res, " + authenticationException.getMessage() + ")");
		if(this.messageConverters != null){
			this.sendError(HttpServletResponse.SC_FORBIDDEN, request, response, authenticationException);
		}else{
			super.onAuthenticationFailure(request, response, authenticationException);
		}
			
	}
	
	/**
	 * 
	 * @param statusCode
	 * @param request
	 * @param response
	 * @param authenticationException
	 * @throws HttpMessageNotWritableException
	 * @throws IOException
	 */
	protected void sendError(int statusCode, HttpServletRequest request,
			HttpServletResponse response, RuntimeException authenticationException) 
			throws HttpMessageNotWritableException, IOException{
		
		if (!response.isCommitted()) {
			//response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
			RestServiceAccessDeniedMessageBody body =
					new RestServiceAccessDeniedMessageBodyImpl(
							statusCode,
							authenticationException.getMessage()
					);
			this.messageConverters.write(body, request, response);
		}
	}

	/**
	 * 
	 * @return
	 */
	public MessageConverterWrapper getMessageConverters() {
		return messageConverters;
	}
	

}
