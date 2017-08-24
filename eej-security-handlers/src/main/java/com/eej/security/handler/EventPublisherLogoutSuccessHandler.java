/**
 * 
 */
package com.eej.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.eej.security.events.InteractiveLogoutSuccessEvent;

/**
 * @author jlumietu - Mikel Ibiricu Alfaro
 *
 */
public class EventPublisherLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private ApplicationEventPublisher applicationEventPublisher;

	/**
	 * 
	 */	
	public EventPublisherLogoutSuccessHandler() {
		super();
	}

	/**
	 * @param logger
	 * @param applicationEventPublisher
	 */
	@Autowired(required=false)
	public EventPublisherLogoutSuccessHandler(ApplicationEventPublisher applicationEventPublisher) {
		super();
		this.applicationEventPublisher = applicationEventPublisher;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler#onLogoutSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if(this.applicationEventPublisher != null){
			this.applicationEventPublisher.publishEvent(new InteractiveLogoutSuccessEvent(authentication));
		}
		super.onLogoutSuccess(request, response, authentication);
	}
	
	

}
