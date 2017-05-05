package com.eej.security.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;


public class SimpleRedirectSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
	
	private String invalidSessionUrl;
	private String defaultSavedUrl;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private final static Logger LOGGER = Logger
			.getLogger(SimpleRedirectSessionInformationExpiredStrategy.class);
	public SimpleRedirectSessionInformationExpiredStrategy(
			String invalidSessionUrl, String defaultSavedUrl,RedirectStrategy redirectStrategy) {
		super();
		this.invalidSessionUrl = invalidSessionUrl;
		this.redirectStrategy = redirectStrategy;
		this.defaultSavedUrl = defaultSavedUrl;
	}
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event)
			throws IOException, ServletException { 
		// TODO Auto-generated method stub
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		HttpSession session= request.getSession(false);
		if(session != null){
			session.invalidate();
		}

		String destinationUrl = determineTargetUrl(request);
		LOGGER.debug("Redirecting to '" + destinationUrl + "'");
		DefaultSavedRequest savedRequest = new DefaultSavedRequest(request,
				new PortResolverImpl());
		request.getSession(true).setAttribute("SPRING_SECURITY_SAVED_REQUEST", savedRequest);
		redirectStrategy.sendRedirect(event.getRequest(), event.getResponse(), destinationUrl);

	}
	
	protected String determineTargetUrl(HttpServletRequest request) {
		if(request != null && request.getServletPath() != null){
			return request.getServletPath();
		}
		return defaultSavedUrl;
	}
}